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
@Table(name = "drop_values")
@Data
public class DropValueEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1732477246763L;
	
	@Id
	private String id;
	
	@NotNull
	private String fieldId;
	
	private String value;
	
	private Boolean isActive;
	
	@CreatedDate
	private Long createdAt;
	
	@LastModifiedDate
	private Long updatedAt;

}
