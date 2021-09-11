/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.diagnostic.patient.entity.PatientReports;

/**
 * @author sandeep.rana
 *
 */
@Repository
public interface PatientReportsRepository extends PagingAndSortingRepository<PatientReports, String> {

}
