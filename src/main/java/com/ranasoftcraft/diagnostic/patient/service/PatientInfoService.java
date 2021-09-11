/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.service;

import com.ranasoftcraft.diagnostic.patient.entity.PatientInfo;
import com.ranasoftcraft.diagnostic.patient.entity.PatientReports;
import com.ranasoftcraft.diagnostic.security.entity.Users;

/**
 * @author sandeep.rana
 *
 */
public interface PatientInfoService {

	PatientInfo saveUpdate(Users user);

	PatientReports saveUpdateRepory(PatientReports patientReports);

}
