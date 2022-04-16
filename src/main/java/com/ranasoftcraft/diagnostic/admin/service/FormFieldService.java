/**
 * 
 */
package com.ranasoftcraft.diagnostic.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ranasoftcraft.diagnostic.admin.entity.DropValueEntity;
import com.ranasoftcraft.diagnostic.admin.entity.FieldEntiry;
import com.ranasoftcraft.diagnostic.admin.entity.FormEntity;
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

	boolean saveFieldDropValues(List<DropValueEntity> entities, String fieldId);

	Page<DropValueEntity> fDropValList(String fieldId);

	boolean saveForms(FormEntity formEntity);

	Page<FormEntity> formL(String rTypeId);

}
