
$(document).ready(function() {
	
});

const generateR = (reportModuelId, patientId, reportId) =>{
	$('#generateReportModal').modal('show');
	$('#reportModuelId').val(reportModuelId || '');
	$('#patientId').val(patientId || '');
	$('#reportId').val(reportId || '');
	getFields(reportModuelId);
	
	getGeneratedRInfo();
}


const input = (ctrl) =>{
	return 	`
			<div class="row">
					<div class="col-12">
						<label for="${ctrl.fieldId}" class="form-label">${ctrl.name}</label>
						<input type="text" name="${ctrl.fieldId}" id="${ctrl.fieldId}" class="form-control"
								aria-describedby="${ctrl.fieldId}_help">
						<div id="${ctrl.fieldId}_help" class="form-text">${ctrl.name}</div>
					</div>
			</div>
	`;
}



const dropdown = (ctrl) =>{
	return 	`
			<div class="row">
					<div class="col-12">
					
						<label for="${ctrl.fieldId}" class="form-label">${ctrl.name}</label>
						<select
								class="form-control" name="${ctrl.fieldId}" id="${ctrl.fieldId}"
								aria-describedby="${ctrl.fieldId}_help">
								
						</select>
						<div id="${ctrl.fieldId}_help" class="form-text">Please enter ${ctrl.name}</div>
					</div>
			</div>
	`;
}


const getFields = (formId) => {
	$.ajax({
		
		url : `/admin/manage/report/form/transaction-page/field/_all?reportTypeId=${formId}`,
		method : 'get',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			console.log(data);
			let html = ``;
			data?.content?.forEach(f=>{
				if(f.fieldType === 'dropdown') {
					html += dropdown(f);
					dropDownVal(f.fieldId);
				} else {
					html += input(f);
				}
			});
			$('#rBody').html(html);
		}

	});
}


const dropDownVal = (fielId) =>{
	
	$.ajax({		
		url : `/admin/manage/report/field/dropval/_all?fieldId=${fielId}`,
		method : 'get',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			let html = `<option value="">Select the option</option>`;
			data?.content?.forEach(d=>{
				html += `<option value="${d.id}">${d.value}</option>`;
			});
			
			$(`#${fielId}`).html(html);
			
		}

	});
	
}


const saveReportData = () => {
	const request = [];
	const reportId = $('#reportId').val().trim() || '';
	const patientId = $('#patientId').val().trim() || '';
	const reportModuleId = $('#reportModuelId').val().trim() || '';
	
	$.each($('#rBody').serializeArray(), function(i, field) {
		request.push({
			fieldId: field.name,
			fldOrder : i,
			value: field.value
		})
	});
	
	console.log(request);
	
	$.ajax({		
		url : `/admin/manage/report/generate?reportId=${reportId}&patientId=${patientId}&reportModuleId=${reportModuleId}`,
		method : 'post',
		dataType : 'json',
		data : JSON.stringify(request),
		contentType : "application/json",
		success : function(data) {
			$('#generateReportModal').modal('hide');
			dataTableData();
		}

	});
}


const getGeneratedRInfo = () => {
	const reportId = $('#reportId').val().trim() || '';
	const patientId = $('#patientId').val().trim() || '';
	const reportModuleId = $('#reportModuelId').val().trim() || '';
	
	$.ajax({
		
		url : `/admin/manage/report/data/_all?reportId=${reportId}&patientId=${patientId}&reportModuleId=${reportModuleId}`,
		method : 'get',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			console.log(data);
			setTimeout(()=>{
				data.forEach(f=>{
					$(`#${f.fieldId}`).val(f.value || '');
				});
			},500)
		}

	});
}
