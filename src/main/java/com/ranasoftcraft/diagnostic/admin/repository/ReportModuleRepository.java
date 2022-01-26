/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;

/**
 * @author sandeep.rana
 *
 */
public interface ReportModuleRepository extends PagingAndSortingRepository<ReportModuleEntiry, String> {

}
