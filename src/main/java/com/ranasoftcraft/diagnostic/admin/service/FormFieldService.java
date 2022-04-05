/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.service;

import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;

/**
 * @author sandeep.rana
 *
 */
public interface FormFieldService {

	ReportModuleEntiry saveUpdateReportType(ReportModuleEntiry reportModuleEntiry);

	Iterable<ReportModuleEntiry> getAllReportModuleEntities();

}
