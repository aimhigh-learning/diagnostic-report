/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ranasoftcraft.diagnostic.admin.entity.DropValueEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry.FieldType;
import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FormFieldMappingEntity;
import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.ReportTemplatesEntity;
import com.ranasoftcraft.diagnostic.admin.repository.DropValueRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FieldRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FormFieldMappingRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FormRepository;
import com.ranasoftcraft.diagnostic.admin.repository.ReportModuleRepository;
import com.ranasoftcraft.diagnostic.admin.repository.ReportTemplatesRepository;
import com.ranasoftcraft.diagnostic.patient.entity.GeneratedReportTransactionsEntity;
import com.ranasoftcraft.diagnostic.patient.entity.PatientInfo;
import com.ranasoftcraft.diagnostic.patient.entity.PatientReports.Status;
import com.ranasoftcraft.diagnostic.patient.repository.GeneratedReportTransactionsRepository;
import com.ranasoftcraft.diagnostic.patient.repository.PatientInfoRepository;
import com.ranasoftcraft.diagnostic.patient.service.PatientInfoService;
import com.ranasoftcraft.diagnostic.security.services.UsersService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sandeep.rana
 *
 */
@Service
@Transactional
@Slf4j
public class FormFieldServiceImpl implements FormFieldService {

	@Autowired
	private ReportModuleRepository reportModuleRepository;

	@Autowired
	private FormRepository formRepository;

	@Autowired
	private FieldRepository fieldRepository;

	@Autowired
	private FormFieldMappingRepository formFieldMappingRepository;

	@Autowired
	private DropValueRepository dropValueRepository;

	@Autowired
	private GeneratedReportTransactionsRepository generatedReportTransactionsRepository;

	@Autowired
	private PatientInfoService patientInfoService;

	@Autowired
	private ReportTemplatesRepository reportTemplatesRepository;

	@Autowired
	private PatientInfoRepository patientInfoRepository;

	@Autowired
	private UsersService usersService;

	@Override
	public ReportModuleEntiry saveUpdateReportType(final ReportModuleEntiry reportModuleEntiry) {
		if (!StringUtils.hasText(reportModuleEntiry.getId())) {
			reportModuleEntiry.setId(UUID.randomUUID().toString());
		}
		return this.reportModuleRepository.save(reportModuleEntiry);
	}

	@Override
	public Iterable<ReportModuleEntiry> getAllReportModuleEntities() {
		return reportModuleRepository.findAll();
	}

	@Override
	public boolean saveUpdateField(final FieldEntiry fieldEntiry) {
		if (!StringUtils.hasText(fieldEntiry.getId())) {
			fieldEntiry.setId(UUID.randomUUID().toString());
		}
		fieldRepository.save(fieldEntiry);
		return Boolean.TRUE;
	}

	@Override
	public Iterable<FieldEntiry> fList(org.springframework.data.domain.Pageable pageable) {
		return fieldRepository.findAll(pageable);
	}

	@Override
	public boolean saveFieldDropValues(List<DropValueEntity> entities, String fieldId) {
		// delete exiting mappings
		dropValueRepository.deleteByFieldId(fieldId);

		entities.forEach(e -> {
			e.setId(StringUtils.hasText(e.getId()) ? e.getId() : UUID.randomUUID().toString());
		});
		dropValueRepository.saveAll(entities);
		return Boolean.TRUE;
	}

	@Override
	public Page<DropValueEntity> fDropValList(String fieldId) {
		return dropValueRepository.findByFieldId(fieldId, PageRequest.of(0, Integer.MAX_VALUE));
	}

	@Override
	public boolean saveForms(final FormEntity formEntity) {
		if (!StringUtils.hasText(formEntity.getId())) {
			formEntity.setId(UUID.randomUUID().toString());
		}
		formRepository.save(formEntity);
		return Boolean.TRUE;
	}

	@Override
	public Page<FormEntity> formL(String rTypeId) {
		return formRepository.findByReportModuleId(rTypeId, PageRequest.of(0, Integer.MAX_VALUE));
	}

	@Override
	public boolean saveUpdateFormFieldMapping(String formId,
			final List<FormFieldMappingEntity> formFieldMappingEntity) {
		formFieldMappingRepository.deleteByFormId(formId);

		formFieldMappingEntity.forEach(f -> {
			f.setUuid(StringUtils.hasText(f.getUuid()) ? f.getUuid() : UUID.randomUUID().toString());
		});
		formFieldMappingRepository.saveAll(formFieldMappingEntity);
		return Boolean.TRUE;
	}

	@Override
	public Page<FormFieldMappingEntity> getAssignedFields(String formId) {
		Page<FormFieldMappingEntity> res = formFieldMappingRepository.findByFormId(formId,
				PageRequest.of(0, Integer.MAX_VALUE));
		res.getContent().stream().forEach(f -> {
			Optional<FieldEntiry> fldDetails = fieldRepository.findById(f.getFieldId());
			if (fldDetails.isPresent()) {
				f.setName(fldDetails.get().getName());
			}
		});
		return res;
	}

	@Override
	public Page<FormFieldMappingEntity> getDefaultFormBasedOnReportType(String reportTypeId) {
		List<FormEntity> forms = formRepository.findByReportModuleIdAndIsActive(reportTypeId, Boolean.TRUE);
		if (forms.isEmpty()) {
			return new PageImpl<>(Arrays.asList());
		}
		Page<FormFieldMappingEntity> res = formFieldMappingRepository.findByFormId(forms.get(0).getId(),
				PageRequest.of(0, Integer.MAX_VALUE));
		res.getContent().stream().forEach(f -> {
			Optional<FieldEntiry> fldDetails = fieldRepository.findById(f.getFieldId());
			if (fldDetails.isPresent()) {
				f.setName(fldDetails.get().getName());
				f.setFieldType(fldDetails.get().getFieldType());
			}
		});
		return res;
	}

	@Override
	public boolean generateReport(String reportId, String patientId, String reportModuelId,
			List<GeneratedReportTransactionsEntity> data) {
		// delete the saved stuff first
		generatedReportTransactionsRepository.deleteByPatientIdAndReportIdAndReportModuleId(patientId, reportId,
				reportModuelId);

		data.forEach(d -> {
			d.setId(StringUtils.hasText(d.getId()) ? d.getId() : UUID.randomUUID().toString());
			d.setReportId(reportId);
			d.setReportModuleId(reportModuelId);
			d.setPatientId(patientId);
		});

		generatedReportTransactionsRepository.saveAll(data);
		// update staus
		patientInfoService.updateStatus(Status.done, reportId);
		return Boolean.TRUE;

	}

	@Override
	public List<GeneratedReportTransactionsEntity> getGenerateReport(String reportId, String patientId,
			String reportModuelId) {
		return generatedReportTransactionsRepository.findByPatientIdAndReportIdAndReportModuleId(patientId, reportId,
				reportModuelId);
	}

	@Override
	public boolean uploadTemplate(String reportModuleId, MultipartFile multipartFile) throws IOException {
		// delete the exiting one
		reportTemplatesRepository.deleteByReportModuleId(reportModuleId);

		// save the new
		ReportTemplatesEntity reportTemplatesEntity = new ReportTemplatesEntity();
		reportTemplatesEntity.setReportModuleId(reportModuleId);
		reportTemplatesEntity.setDocId(UUID.randomUUID().toString());
		reportTemplatesEntity.setData(multipartFile.getBytes());

		System.out.println(new String(multipartFile.getBytes()));

		log.info("Byte[] data ... ", new String(multipartFile.getBytes()));

		reportTemplatesEntity.setFileName(multipartFile.getOriginalFilename());
		reportTemplatesEntity.setIsActive(Boolean.TRUE);
		reportTemplatesEntity.setFileType(multipartFile.getContentType());
		reportTemplatesRepository.save(reportTemplatesEntity);
		return Boolean.TRUE;
	}

	@Override
	public ReportTemplatesEntity downloadTemplate(String reportModuleId) {
		return reportTemplatesRepository.findByReportModuleIdAndIsActive(reportModuleId, Boolean.TRUE);
	}

	
	@Override
	public byte[] downloadGeneratedReport(String reportModuleId, String patientId, String reportId) {

		StopWatch sw = new StopWatch();

		sw.start("Get the template which is uploaded by the users ... ");

		ReportTemplatesEntity reportTemplatesEntity = reportTemplatesRepository
				.findByReportModuleIdAndIsActive(reportModuleId, Boolean.TRUE);
		if (reportTemplatesEntity == null) {
			throw new RuntimeException("Template not foud exception");
		}
		byte[] fileTData = reportTemplatesEntity.getData();

		sw.start("Get the generate report data ... to help patch the data into file ... ");

		List<GeneratedReportTransactionsEntity> data = getGenerateReport(reportId, patientId, reportModuleId);
		data.stream().forEach(f -> {
			Optional<FieldEntiry> fldEntity = fieldRepository.findById(f.getFieldId());
			if (fldEntity.isPresent() && fldEntity.get().getFieldType().equals(FieldType.dropdown)) {
				f.setValue(dropValueRepository.findById(f.getValue()).get().getValue());
			}
		});

		sw.start("Create file from byte to replace the contenet");

		String actualData = new String(fileTData);

		Optional<PatientInfo> patientInfo = patientInfoRepository.findById(patientId);
		if (!patientInfo.isPresent()) {
			throw new NullPointerException("Provided patient id not foud exception .... ");
		}
		PatientInfo pInfo = patientInfo.get();
		pInfo.setUsers(usersService.findByUserId(pInfo.getPatientId()).get());

		return (modifyWithStaticFields(actualData, pInfo) + modifyTheDatawithDyncData(actualData, pInfo, data)).getBytes();
	}

	private String modifyWithStaticFields(String data, PatientInfo patientInfo) {

		DateFormat obj = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
		Date admitDate = new Date(patientInfo.getAdmitDate());

		data = data.replaceAll(Pattern.quote("${PATIENT_ID}"), patientInfo.getPatientId());
		data = data.replaceAll(Pattern.quote("${NAME}"), patientInfo.getName());
		data = data.replaceAll(Pattern.quote("${AGE}"), String.valueOf(patientInfo.getAge()));
		data = data.replaceAll(Pattern.quote("${SEX}"), String.valueOf(patientInfo.getGeneder()));
		data = data.replaceAll(Pattern.quote("${ADMIT_DATE}"), obj.format(admitDate));
		data = data.replaceAll(Pattern.quote("${PHONE}"), patientInfo.getUsers().getPhone());
		data = data.replaceAll(Pattern.quote("${GENDER}"), String.valueOf(patientInfo.getGeneder()));
		data = data.replaceAll(Pattern.quote("${REF_BY_DR}"), patientInfo.getReferredByDr());
		data = data.replaceAll(Pattern.quote("${CONSULT_BY_DR}"), patientInfo.getConsultByDr());
		data = data.replaceAll(Pattern.quote("${ADDRESS}"), patientInfo.getAddress());
		return data;
	}

	private String modifyTheDatawithDyncData(String data, PatientInfo patientInfo,
			List<GeneratedReportTransactionsEntity> savedData) {
		for (GeneratedReportTransactionsEntity f : savedData) {
			if (data.contains(f.getFieldId())) {
				data = data.replaceAll(Pattern.quote("${" + f.getFieldId() + "}"), f.getValue());
			}
		}
		return data;
	}

}
