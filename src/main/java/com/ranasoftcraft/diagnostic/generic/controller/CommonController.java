/**
 * 
 */
package com.ranasoftcraft.diagnostic.generic.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

/**
 * @author sandeep.rana
 *
 */
@Controller
public class CommonController {

	
	@Autowired
    ServletContext servletContext;

    private final TemplateEngine templateEngine;
    
    public CommonController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }
    
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		return "dashboard/welcome";
	}

	@GetMapping({ "/patient" })
	public String patient(Model model) {
		return "patients/save-edit";
	}

	@GetMapping({ "/patient-list" })
	public String patientList(Model model) {
		return "patients/list";
	}

	@GetMapping("/report")
	public String reportPage(Model model) {
		return "patients/report";
	}

	@GetMapping(path = "/report/pdf")
	public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		/* Create HTML using Thymeleaf template Engine */

		WebContext context = new WebContext(request, response, servletContext);
		String orderHtml = templateEngine.process("/diagnostic-report/src/main/webapp/WEB-INF/jsp/patients/report.jsp", context);

		/* Setup Source and target I/O streams */

		ByteArrayOutputStream target = new ByteArrayOutputStream();
		ConverterProperties converterProperties = new ConverterProperties();
		converterProperties.setBaseUri("http://localhost:1001");
		/* Call convert method */
		HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

		/* extract output as bytes */
		byte[] bytes = target.toByteArray();

		/* Send the response as downloadable PDF */

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
				.contentType(MediaType.APPLICATION_PDF).body(bytes);

	}

}
