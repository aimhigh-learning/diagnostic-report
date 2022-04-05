/**
 * 
 */
package com.ranasoftcraft.diagnostic.generic.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.util.FileUtil;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.resolve.shorthand.impl.PaddingShorthandResolver;

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
	
	@GetMapping("/report-configuration")
	public String reportConfig(Model model) {
		return "reports-config/report-configuration";
	}
	
	@GetMapping("/fields")
	public String fields(Model model, @RequestParam(name = "_id") String id) {
		model.addAttribute("_id", id);
		return "reports-config/fields";
	}

	@GetMapping(path = "/report/pdf")
	public ResponseEntity<?> getPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {

		
		/* Create HTML using Thymeleaf template Engine */
//
//		WebContext context = new WebContext(request, response, servletContext);
//		String orderHtml = templateEngine.process("/diagnostic-report/src/main/webapp/WEB-INF/jsp/patients/report.jsp", context);
//
//		/* Setup Source and target I/O streams */
//
//		ByteArrayOutputStream target = new ByteArrayOutputStream();
//		ConverterProperties converterProperties = new ConverterProperties();
//		converterProperties.setBaseUri("http://localhost:1001");
//		/* Call convert method */
//		HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
//
//		/* extract output as bytes */
//		byte[] bytes = target.toByteArray();

		/* Send the response as downloadable PDF */
		
		String ADDRESS = "http://localhost:1001/report";
		
		PdfWriter writer = new PdfWriter(new File("D:\\Personal\\Apps\\diagnostic-report\\src\\main\\webapp\\WEB-INF\\jsp\\patients\\test.pdf"));
	    PdfDocument pdf = new PdfDocument(writer);
	    PageSize pageSize = new PageSize(850, 1700);
	    pdf.setDefaultPageSize(pageSize);
	    ConverterProperties properties = new ConverterProperties();
	    MediaDeviceDescription mediaDeviceDescription =
	        new MediaDeviceDescription(com.itextpdf.styledxmlparser.css.media.MediaType.SCREEN);
	    mediaDeviceDescription.setWidth(pageSize.getWidth());
	    properties.setMediaDeviceDescription(mediaDeviceDescription);
	    HtmlConverter.convertToPdf(new URL(ADDRESS).openStream(), pdf, properties);
		
		
		
		 
//		ByteArrayOutputStream buffer = new ByteArrayOutputStream();              
//		  HtmlConverter.convertToPdf(new FileInputStream(new File("D:\\Personal\\Apps\\diagnostic-report\\src\\main\\webapp\\WEB-INF\\jsp\\patients\\report.html")), buffer, converterProperties);
//		
//		byte[] pdfAsBytes = buffer.toByteArray();
//		  try (FileOutputStream fos = new FileOutputStream("test")) {
//		   fos.write(pdfAsBytes);
//		  }
//		  
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
//				.contentType(MediaType.APPLICATION_PDF).body(pdfAsBytes);
	    return null;

	}

}
