/**
 * 
 */
package com.ranasoftcraft.diagnostic.generic.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sandeep.rana
 *
 */
@Controller
public class CommonController {

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "dashboard/welcome";
	}
	
	@GetMapping({"/patient"})
	public String patient(Model model) {
		return "patients/save-edit";
	}
	
	@GetMapping({"/patient-list"})
	public String patientList(Model model) {
		return "patients/list";
	}
	
	
}
