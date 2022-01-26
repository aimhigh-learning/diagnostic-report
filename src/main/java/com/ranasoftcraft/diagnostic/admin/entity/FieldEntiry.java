/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author sandeep.rana
 *
 */

@Entity
@Table(name = "fields")
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
	
	private boolean isMultiselect;
	
	private Integer maxLength;
	
	private boolean isRequired;
	
	
	@CreatedDate
	private Long createdAt;
	
	@LastModifiedDate
	private Long updatedAt;
	
	
	
	enum FieldType {
		inpput_text,
		dropdown,
		input_numeric,
		input_percentage
	}

}
