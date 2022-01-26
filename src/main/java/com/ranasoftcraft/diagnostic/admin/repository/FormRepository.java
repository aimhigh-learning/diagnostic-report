/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;

/**
 * @author sandeep.rana
 *
 */
public interface FormRepository extends PagingAndSortingRepository<FormEntity, String> {

}
