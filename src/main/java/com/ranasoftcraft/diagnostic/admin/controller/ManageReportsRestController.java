/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sandeep.rana
 *
 */

@RestController
public class ManageReportsRestController {
	
	
	@GetMapping("/")
	public void availableReportTypes() {
		
	}
	

}
