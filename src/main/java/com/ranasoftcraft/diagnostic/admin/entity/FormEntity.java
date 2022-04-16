/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.entity;

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
@Table(name = "forms")
@Data
public class FormEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 17367462764L;
	
	
	@Id
	private String id;
	
	
	private String name;
	
	@NotNull
	private String reportModuleId;
	
	@CreatedDate
	private Long createdAt;
	
	@LastModifiedDate
	private Long updatedAt;
	
	
	private Boolean isActive;
	
	

}
