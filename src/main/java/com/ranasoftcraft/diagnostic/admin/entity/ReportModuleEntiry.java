/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.ranasoftcraft.diagnostic.patient.entity.PatientReports.ReportType;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */
@Entity
@Table(name = "report_module")

@Data
public class ReportModuleEntiry {
	
	
	@Id
	private String id;
	
	
	private String name;
	
	
	private String description;
	
	private String tags;
	
	@UniqueElements
	private String reportType; 
	
	
	@CreatedDate
	private Long createdAt;
	
	@LastModifiedDate
	private Long updatedAt;
	
	

}
