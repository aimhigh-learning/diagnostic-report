/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;
import com.ranasoftcraft.diagnostic.admin.repository.FieldRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FormFieldMappingRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FormRepository;
import com.ranasoftcraft.diagnostic.admin.repository.ReportModuleRepository;

/**
 * @author sandeep.rana
 *
 */
@Service
public class FormFieldServiceImpl implements FormFieldService {

	@Autowired
	private ReportModuleRepository reportModuleRepository;
	
	@Autowired
	private FormRepository formRepository;
	
	
	@Autowired
	private FieldRepository fieldRepository;
	
	@Autowired
	private FormFieldMappingRepository formFieldMappingRepository;
	
	
	@Override
	public ReportModuleEntiry saveUpdateReportType(final ReportModuleEntiry reportModuleEntiry ) {
		if(!StringUtils.hasText(reportModuleEntiry.getId())) {
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
		if(!StringUtils.hasText(fieldEntiry.getId())) {
			fieldEntiry.setId(UUID.randomUUID().toString());
		}
		fieldRepository.save(fieldEntiry);
		return Boolean.TRUE;
	}
	
	@Override
	public Iterable<FieldEntiry> fList(org.springframework.data.domain.Pageable pageable) {
		return fieldRepository.findAll(pageable);
	}
	
	
}
