/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author sandeep.rana
 *
 */

@Entity
@Table(name = "form_field_mappings")
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
	

}
