/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */

@Entity
@Table(name = "fields")
@Data
public class FieldEntiry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 17346263746732L;
	
	
	@Id
	private String id;
	
	
	private String name;
	
	@Enumerated(EnumType.STRING)
	private FieldType fieldType;
	
	private Boolean isMultiselect;
	
	private Integer maxLength;
	
	private Boolean isRequired;
	
	
	@CreatedDate
	private Long createdAt;
	
	@LastModifiedDate
	private Long updatedAt;
	
	@Transient
	private List<DropValueEntity> values;
	
	
	public FieldEntiry() {
		this.setCreatedAt(Instant.now().toEpochMilli());
		this.setUpdatedAt(Instant.now().toEpochMilli());
	}
	
	
	public enum FieldType {
		inpput_text,
		dropdown,
		input_numeric,
		input_percentage
	}

}
