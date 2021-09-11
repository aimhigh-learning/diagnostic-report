/**
 * 
 */
package com.ranasoftcraft.diagnostic.security.entity;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ranasoftcraft.diagnostic.patient.entity.PatientInfo;

import lombok.Data;

/**
 * @author sandeep.rana
 *
 */
@Data
@Entity(name = "users")
@Table(name = "users")
public class Users {
	
	@Id
	@NotNull
	private String username;
	
	@NotNull
	private String name;
	
	@NotNull
	@Min(value = 10, message = "Phone can't be less then 12 ")
	@Max(value = 12, message = "Phone can't be more then 12 ")
	private String phone;
	
	@NotNull
	@javax.validation.constraints.Email
	private String email;
	
	@NotNull
	private String position;
	
	
	private Long createdAt;
	
	
	private Long updatedAt;
	
	private Boolean isEnabled;
	
	@NotNull
//	@JsonIgnore
	private String password; 
	

    @ManyToMany(fetch = FetchType.LAZY)
	private Set<Roles> roles;

    
    @Transient
    private PatientInfo patientInfo;

}
