/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ranasoftcraft.diagnostic.patient.entity.PatientInfo;
import com.ranasoftcraft.diagnostic.patient.entity.PatientReports;
import com.ranasoftcraft.diagnostic.patient.repository.PatientInfoRepository;
import com.ranasoftcraft.diagnostic.patient.repository.PatientReportsRepository;
import com.ranasoftcraft.diagnostic.security.entity.Roles;
import com.ranasoftcraft.diagnostic.security.entity.Users;
import com.ranasoftcraft.diagnostic.security.services.UsersService;

/**
 * @author sandeep.rana
 *
 */
@Service
@Transactional
public class PatientInfoServiceImpl implements PatientInfoService {

	
	@Autowired
	private PatientInfoRepository patientInfoRepository;
	
	@Autowired
	private PatientReportsRepository patientReportsRepository;
	
	@Autowired
	private UsersService userService;
	
	@Override
	public PatientInfo saveUpdate(final Users user) {
		final Roles roleModel = new Roles();
		roleModel.setRoleId("103");
		roleModel.setName("PATIENT");
		roleModel.setRoleDesc("patient");
		Set<Roles> roles = new HashSet<>(); roles.add(roleModel);
		user.setRoles(roles);
		userService.signUp(user);
		return patientInfoRepository.save(user.getPatientInfo());
	}
	
	@Override
	public PatientReports saveUpdateRepory(final PatientReports patientReports) {
		return patientReportsRepository.save(patientReports);
	}
	
}
