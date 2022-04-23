/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.diagnostic.patient.entity.GeneratedReportTransactionsEntity;

/**
 * @author sandeep.rana
 *
 */
@Repository
public interface GeneratedReportTransactionsRepository extends PagingAndSortingRepository<GeneratedReportTransactionsEntity, String> {

	
	@Modifying
	void deleteByPatientIdAndReportIdAndReportModuleId(String patientId, String reportId, String reportModuleId);
	
	List<GeneratedReportTransactionsEntity> findByPatientIdAndReportIdAndReportModuleId(String patientId, String reportId, String reportModuleId);
	
}
