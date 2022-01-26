/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.diagnostic.admin.entity.FormFieldMappingEntity;

/**
 * @author sandeep.rana
 *
 */
public interface FormFieldMappingRepository extends PagingAndSortingRepository<FormFieldMappingEntity, String> {

}
