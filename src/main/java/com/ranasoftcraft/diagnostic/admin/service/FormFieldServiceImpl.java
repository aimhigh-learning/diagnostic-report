/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ranasoftcraft.diagnostic.admin.entity.DropValueEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;
import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;
import com.ranasoftcraft.diagnostic.admin.repository.DropValueRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FieldRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FormFieldMappingRepository;
import com.ranasoftcraft.diagnostic.admin.repository.FormRepository;
import com.ranasoftcraft.diagnostic.admin.repository.ReportModuleRepository;

/**
 * @author sandeep.rana
 *
 */
@Service
@Transactional
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
	
	@Override
	public boolean saveFieldDropValues(List<DropValueEntity> entities, String fieldId) {
		// delete exiting mappings  
		dropValueRepository.deleteByFieldId(fieldId);
		
		entities.forEach(e->{
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
		if(!StringUtils.hasText(formEntity.getId())) {
			formEntity.setId(UUID.randomUUID().toString());
		}
		formRepository.save(formEntity);
		return Boolean.TRUE;
	}
	
	@Override
	public Page<FormEntity> formL(String rTypeId) {
		return formRepository.findByReportModuleId(rTypeId, PageRequest.of(0, Integer.MAX_VALUE));
	}
	
}
