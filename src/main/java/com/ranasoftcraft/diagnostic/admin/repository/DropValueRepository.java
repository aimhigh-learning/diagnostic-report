/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.diagnostic.admin.entity.DropValueEntity;

/**
 * @author sandeep.rana
 *
 */
@Repository
public interface DropValueRepository extends Serializable, PagingAndSortingRepository<DropValueEntity, String> {

	@Modifying
	void deleteByFieldId(String fieldId);
	
	Page<DropValueEntity> findByFieldId(String fieldId, org.springframework.data.domain.Pageable pageable);
	
}
