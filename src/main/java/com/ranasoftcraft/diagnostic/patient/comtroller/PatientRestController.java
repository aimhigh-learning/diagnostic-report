/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.comtroller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ranasoftcraft.diagnostic.patient.entity.PatientInfo;
import com.ranasoftcraft.diagnostic.patient.service.PatientInfoService;
import com.ranasoftcraft.diagnostic.security.entity.Users;

/**
 * @author sandeep.rana
 *
 */
@RestController(value = "/patient/api")
public class PatientRestController {
	
	private PatientInfoService patientInfoService;
	
	
	@PostMapping("/save-update")
	public PatientInfo saveUpdate(@RequestBody Users user) {
		return patientInfoService.saveUpdate(user);
	}

}
