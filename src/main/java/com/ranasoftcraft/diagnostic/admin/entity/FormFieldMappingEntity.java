/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry.FieldType;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */

@Entity
@Table(name = "form_field_mappings")
@Data
public class FormFieldMappingEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 177533L;
	
	@Id
	private String uuid;
	
	private String formId;
	
	private String fieldId;
	
	private Integer fldOrder;
	
	@Transient
	private String name;
	
	@Transient
	private FieldType fieldType;
	

}
