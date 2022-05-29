/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.ranasoftcraft.diagnostic.admin.entity.DropValueEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FormFieldMappingEntity;
import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.ReportTemplatesEntity;
import com.ranasoftcraft.diagnostic.patient.entity.GeneratedReportTransactionsEntity;
/**
 * @author sandeep.rana
 *
 */
public interface FormFieldService {

	ReportModuleEntiry saveUpdateReportType(ReportModuleEntiry reportModuleEntiry);

	Iterable<ReportModuleEntiry> getAllReportModuleEntities();

	boolean saveUpdateField(FieldEntiry fieldEntiry);

	Iterable<FieldEntiry> fList(Pageable pageable);

	boolean saveFieldDropValues(List<DropValueEntity> entities, String fieldId);

	Page<DropValueEntity> fDropValList(String fieldId);

	boolean saveForms(FormEntity formEntity);

	Page<FormEntity> formL(String rTypeId);

	boolean saveUpdateFormFieldMapping(String formId, List<FormFieldMappingEntity> formFieldMappingEntity);

	Page<FormFieldMappingEntity> getAssignedFields(String formId);

	Page<FormFieldMappingEntity> getDefaultFormBasedOnReportType(String reportTypeId);

	boolean generateReport(String reportId, String patientId, String reportModuelId,
			List<GeneratedReportTransactionsEntity> data);

	List<GeneratedReportTransactionsEntity> getGenerateReport(String reportId, String patientId, String reportModuelId);

	boolean uploadTemplate(String reportModuleId, MultipartFile multipartFile) throws IOException;

	ReportTemplatesEntity downloadTemplate(String reportModuleId);

	byte[] downloadGeneratedReport(String reportModuleId, String patientId, String reportId);

}
