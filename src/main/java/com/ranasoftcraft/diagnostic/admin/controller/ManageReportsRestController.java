/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ranasoftcraft.diagnostic.admin.entity.DropValueEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;
import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;
import com.ranasoftcraft.diagnostic.admin.service.FormFieldService;

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
}
