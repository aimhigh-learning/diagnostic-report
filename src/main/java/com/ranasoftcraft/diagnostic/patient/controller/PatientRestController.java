/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ranasoftcraft.diagnostic.patient.entity.PatientInfo;
import com.ranasoftcraft.diagnostic.patient.service.PatientInfoService;
import com.ranasoftcraft.diagnostic.security.entity.Users;

/**
 * @author sandeep.rana
 *
 */
@RestController
@RequestMapping("/api/patient")
public class PatientRestController {
	
	@Autowired
	private PatientInfoService patientInfoService;
	
	
	@PostMapping("/save-update")
	public PatientInfo saveUpdate(@RequestBody Users user) {
		return patientInfoService.saveUpdate(user);
	}
	
	@GetMapping("/list")
	public Page<PatientInfo> list(@RequestParam Integer _page , @RequestParam Integer _size) {
		return patientInfoService.getPatientList(_page, _size);
	}

}
