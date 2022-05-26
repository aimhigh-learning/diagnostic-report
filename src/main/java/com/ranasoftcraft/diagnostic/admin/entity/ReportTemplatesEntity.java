/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */
@Entity
@Table(name = "report_templates")
@Data
public class ReportTemplatesEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1234323543L;
	
	@Id
	private String docId;
	
	@NotNull
	private String reportModuleId;
	
	private String fileName;
	
	private String fileType;
	
	private byte[] data;
	
	private Boolean isActive;
	
	@CreatedDate
	private Long createdAt;
	
	@LastModifiedDate
	private Long updatedAt;
	
	
	

}
