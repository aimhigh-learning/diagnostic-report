/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;

/**
 * @author sandeep.rana
 *
 */
public interface FormRepository extends PagingAndSortingRepository<FormEntity, String> {

	Page<FormEntity> findByReportModuleId(String reportModuleId, Pageable page);
}
