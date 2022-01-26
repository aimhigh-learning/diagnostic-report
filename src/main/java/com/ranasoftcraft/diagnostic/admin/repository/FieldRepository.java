/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;

/**
 * @author sandeep.rana
 *
 */
public interface FieldRepository extends PagingAndSortingRepository<FieldEntiry, String> {

}
