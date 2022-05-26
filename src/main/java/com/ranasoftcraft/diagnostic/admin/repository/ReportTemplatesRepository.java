/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.diagnostic.admin.entity.ReportTemplatesEntity;

/**
 * @author sandeep.rana
 *
 */
@Repository
public interface ReportTemplatesRepository extends PagingAndSortingRepository<ReportTemplatesEntity, String> {

	@Modifying
	void deleteByReportModuleId(String reportModuleId);
	
	Page<ReportTemplatesEntity> findByReportModuleId(String reportModuleId, PageRequest pageRequest);
	
	ReportTemplatesEntity findByReportModuleIdAndIsActive(String reportModuleId , Boolean isActive);
}
