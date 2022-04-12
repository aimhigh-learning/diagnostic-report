/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.service;

import org.springframework.data.domain.Pageable;

import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.ReportModuleEntiry;
/**
 * @author sandeep.rana
 *
 */
public interface FormFieldService {

	ReportModuleEntiry saveUpdateReportType(ReportModuleEntiry reportModuleEntiry);

	Iterable<ReportModuleEntiry> getAllReportModuleEntities();

	boolean saveUpdateField(FieldEntiry fieldEntiry);

	Iterable<FieldEntiry> fList(Pageable pageable);

}
