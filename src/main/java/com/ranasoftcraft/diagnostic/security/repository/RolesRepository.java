/**
 * 
 */
package com.ranasoftcraft.diagnostic.security.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ranasoftcraft.diagnostic.security.entity.Roles;

/**
 * @author sandeep.rana
 *
 */
@Repository
public interface RolesRepository extends PagingAndSortingRepository<Roles, String> {

}
