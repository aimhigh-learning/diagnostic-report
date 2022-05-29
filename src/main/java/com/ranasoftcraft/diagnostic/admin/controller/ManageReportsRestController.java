/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ranasoftcraft.diagnostic.admin.entity.DropValueEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FormFieldMappingEntity;
import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.ReportTemplatesEntity;
import com.ranasoftcraft.diagnostic.admin.service.FormFieldService;
import com.ranasoftcraft.diagnostic.patient.entity.GeneratedReportTransactionsEntity;

/**
 * @author sandeep.rana
 *
 */

@RestController
@RequestMapping(path =  "/admin/manage/report")
public class ManageReportsRestController {
	
	@Autowired
	private FormFieldService formFieldService;
	
	
	@GetMapping("/_all")
	public Iterable<ReportModuleEntiry> list() {
		return formFieldService.getAllReportModuleEntities();
	}
	
	
	
	@PostMapping(path = {"/save", "/update"})
	public ReportModuleEntiry saveUpdateReportModule(@RequestBody ReportModuleEntiry reportModuleEntiry) {
		return formFieldService.saveUpdateReportType(reportModuleEntiry);
	}
	
	@PostMapping(path = {"/field/save","/field/update"})
	public boolean saveField(@RequestBody FieldEntiry fieldEntiry) {
		return formFieldService.saveUpdateField(fieldEntiry);
	}
	
	
	@GetMapping(path = "/field/_all")
	public Iterable<FieldEntiry> fList(@RequestParam(required = false, defaultValue = "0") String _page,
			@RequestParam(required = false, defaultValue = "50") String _size,
			@RequestParam(required = false, defaultValue = "") String _search) {
		int s = Integer.parseInt(_size);
		int p = Integer.parseInt(_page);
		return formFieldService.fList(PageRequest.of(p, s));
	}
	
	@PostMapping(path = {"/field/dropval/save","/field/dropval/update"})
	public boolean saveFieldDropVal(@RequestBody List<DropValueEntity> dropValueEntities, @RequestParam String fieldId) {
		return formFieldService.saveFieldDropValues(dropValueEntities, fieldId);
	}
	
	@GetMapping(path = "/field/dropval/_all")
	public Page<DropValueEntity> fDropValList(@RequestParam String fieldId) {
		return formFieldService.fDropValList(fieldId);
	}
	
	@PostMapping(path = {"/form/save","/form/update"})
	public boolean saveForm(@RequestBody FormEntity formEntity) {
		return formFieldService.saveForms(formEntity);
	}
	
	@GetMapping(path = "/form/_all")
	public Page<FormEntity> formL(@RequestParam String _id) {
		return formFieldService.formL(_id);
	}
	
	
	@PostMapping(path = {"/form/field/assign/save","/form/field/assign/update"})
	public boolean assignFieldToForm(@RequestParam String formId ,  @RequestBody List<FormFieldMappingEntity> formFieldMappingEntities) {
		return formFieldService.saveUpdateFormFieldMapping(formId, formFieldMappingEntities);
	}
	
	@GetMapping(path = "/form/field/assign/_all")
	public Page<FormFieldMappingEntity> assignedFieldL(@RequestParam String formId) {
		return formFieldService.getAssignedFields(formId);
	}
	
	@GetMapping(path = "/form/transaction-page/field/_all")
	public Page<FormFieldMappingEntity> getDefaultFormBasedOnReportType(@RequestParam String reportTypeId) {
		return formFieldService.getDefaultFormBasedOnReportType(reportTypeId);
	}
	
	@PostMapping(path = {"/generate"})
	public boolean generateReport(@RequestParam String reportId, @RequestParam String patientId ,
			@RequestParam String reportModuleId, @RequestBody List<GeneratedReportTransactionsEntity> fieldsdatas) {
		
		return formFieldService.generateReport(reportId, patientId, reportModuleId, fieldsdatas);
	}
	
	@GetMapping(path = "/data/_all")
	public List<GeneratedReportTransactionsEntity> getGeneratedR(@RequestParam String reportId, @RequestParam String patientId ,
			@RequestParam String reportModuleId) {
		return formFieldService.getGenerateReport(reportId, patientId, reportModuleId);
	}
	
	
	@PostMapping(path = {"/template/_upload"})
	public boolean saveTheTemplate(@RequestParam String reportId, @RequestPart MultipartFile file) throws IOException {
		return formFieldService.uploadTemplate(reportId, file);
	}
	
	
	@GetMapping(path = {"/template/_download"}, produces = {"application/vnd.openxmlformats-officedocument.wordprocessingml.document"})
	public ResponseEntity<byte[]> getTemplate(@RequestParam String reportId) throws IOException {
		ReportTemplatesEntity reportTemplatesEntity = formFieldService.downloadTemplate(reportId);
		return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  reportTemplatesEntity.getFileName() + "." +reportTemplatesEntity.getFileType() + "\"")
	            .body(reportTemplatesEntity.getData()); 
	}
	
	
	
	@GetMapping(path = {"/download/generated/report"}, produces = {"application/vnd.openxmlformats-officedocument.wordprocessingml.document"})
	public ResponseEntity<byte[]> downloadGeneratedReport(@RequestParam String reportId, @RequestParam String patientId , @RequestParam String reportModuleId) throws IOException {
		byte[] data =   formFieldService.downloadGeneratedReport(reportModuleId, patientId, reportId);
		return ResponseEntity.ok()
	            .contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + patientId  + ".application_vnd.openxmlformats-officedocument.wordprocessingml")
	            .body(data); 
	} 
	
	
}
