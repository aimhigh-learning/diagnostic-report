/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.diagnostic.admin.entity.FormFieldMappingEntity;

/**
 * @author sandeep.rana
 *
 */
public interface FormFieldMappingRepository extends PagingAndSortingRepository<FormFieldMappingEntity, String> {

	@Modifying
	void deleteByFormId(String fieldId);
	
//	@Query("select new com.ranasoftcraft.diagnostic.admin.entity.FormFieldMappingEntity(fm.uuid, fm.formId, fm.fieldId, fm.fldOrder, fld.name) from FormFieldMappingEntity fm , FieldEntiry fld where fm.fieldId = fld.id and fm.formId :=formId")
	Page<FormFieldMappingEntity> findByFormId(String formId, Pageable pageable);
}
