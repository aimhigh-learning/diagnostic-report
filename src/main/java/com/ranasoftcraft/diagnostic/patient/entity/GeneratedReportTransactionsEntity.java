/**
 * 
 */
package com.ranasoftcraft.diagnostic.patient.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */
@Entity
@Data
@Table(name = "generated_report_transactions")
public class GeneratedReportTransactionsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 178236746723L;
	
	@Id
	private String id;
	
	@NotNull
	private String patientId;
	
	@NotNull
	private String reportId;
	
	@NotNull
	private String reportModuleId;
	
	private String fieldId;
	
	private Integer fldOrder;
	
	private String value;
	
	@CreatedDate
	private Long createdAt;
	
	@LastModifiedDate
	private Long updatedAt;

}
