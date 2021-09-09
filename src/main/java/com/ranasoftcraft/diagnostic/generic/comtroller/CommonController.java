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
	
	@GetMapping("/foot-value")
	public String footValue(Model model) {
		return "dashboard/foot-value";
	}
	
	
	@GetMapping("/players")
	public String playesr(Model model) {
		return "dashboard/players";
	}
	
	@GetMapping("/stats")
	public String stats(Model model) {
		return "dashboard/stats";
	}
	
}
