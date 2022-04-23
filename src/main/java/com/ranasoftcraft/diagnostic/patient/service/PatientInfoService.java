/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.service;

import org.springframework.data.domain.Page;

import com.ranasoftcraft.diagnostic.patient.entity.PatientInfo;
import com.ranasoftcraft.diagnostic.patient.entity.PatientReports;
import com.ranasoftcraft.diagnostic.patient.entity.PatientReports.Status;
import com.ranasoftcraft.diagnostic.security.entity.Users;

/**
 * @author sandeep.rana
 *
 */
public interface PatientInfoService {

	PatientInfo saveUpdate(Users user);

	PatientReports saveUpdateRepory(PatientReports patientReports);

	Page<PatientInfo> getPatientList(int _page, int _size);

	void updateStatus(Status status, String reportId);

}
