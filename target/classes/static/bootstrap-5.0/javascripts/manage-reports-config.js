
let currentReport = null;

$(document).ready(function() {
	if(window.location.pathname === '/fields') {
		fieldList();
	} else {
		reportModuleList();
	}
	
});


const createNew = () => {
	$('#createUpdateReportModule').modal('show')
	currentReport = null;
}

const createEditField = (data) => {
	let editData = {
			'id':'',
			'name':'',
			'fieldType':'',
			'maxLength':'',
			'isMultiselect':false,
			'isRequired':false
	}
	
	if(data) {
		editData = JSON.parse(unescape(data));
	}
	$('#createUpdateFieldModal').modal('show');
	$('#name').val(editData.name || ''); $('#fieldType').val(editData.fieldType || '');
	$('#maxLength').val(editData.maxLength || ''); $('#isMultiselect').prop('checked', editData.isMultiselect || false);
	$('#isRequired').prop('checked', editData.isRequired || false);
	$('#id').val(editData.id || '');
}

const maintainDropValues = (fieldId)=>{
	$('#maintainDropValues').modal('show');
	$('#fieldId').val(fieldId || '');
}


const editReport = (id , name , description)=>{
	currentReport = id;
	$('#createUpdateReportModule').modal('show')
	$('#name').val(name || '');
	$('#description').val(description || '');
}

const saveUpdateReport = () => {
	const request = {
			'name':$('#name').val().trim() || '',
			'description':$('#description').val().trim() || '',
			'reportType':$('#name').val().trim() || '',
			'id':currentReport || ''
	}
	
	 $.ajax({
	
			url : '/admin/manage/report/save',
			method : 'post',
			dataType : 'json',
			contentType : "application/json",
			data : JSON.stringify(request),
			success : function(data) {
				$('#createUpdateReportModule').modal('hide');
				reportModuleList();
			}
	
		});
	
}


const reportModuleList = () => {
	
	$.ajax({
		
		url : '/admin/manage/report/_all',
		method : 'get',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			let finalH = ``;
			data.forEach(r=>{
				finalH += moduelP(r);
			});
			$('#data').html(finalH);
		}

	});
}

const moduelP = (report) =>{
	return `
		<div class="col-4">
			<div class="card">
				<div class="card-body">
					<h5 class="card-title">${report.reportType}</h5>
					<p class="card-text">${report.description}</p>
					<a href="#" class="btn btn-info" onclick="editReport('${report.id}','${report.name}', '${report.description}')">Edit</a>
					<a href="forms?_id=${report.id}" class="btn btn-primary" style="">Forms</a>
				</div>
			</div>


		</div>
	`;
}



const saveUpdateField = () => {
	const request = {
			'id':$('#id').val() || '',
			'name':$('#name').val().trim() || '',
			'fieldType':$('#fieldType').val().trim() || '',
			'maxLength':$('#maxLength').val().trim() || '',
			'isMultiselect':$('#isMultiselect').prop('checked') || false,
			'isRequired':$('#isRequired').prop('checked') || false
	}
	
	 $.ajax({
	
			url : '/admin/manage/report/field/save',
			method : 'post',
			dataType : 'json',
			contentType : "application/json",
			data : JSON.stringify(request),
			success : function(data) {
				$('#createUpdateFieldModal').modal('hide');		
				fieldList();
			}
	
		});
	
}


const fieldList = () => {
	
	$.ajax({
		
		url : '/admin/manage/report/field/_all',
		method : 'get',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			let finalH = ``;
			data?.content?.forEach(r=>{
				finalH += fieldP(r);
			});
			$('#fieldL').html(finalH);
		}

	});
}

const fieldP = (f) =>{
	const valueBtnS = f.fieldType === 'dropdown' ? `<a href="#" class="btn btn-outline-secondary" onClick="maintainDropValues('${f.id}')">Values</a>` : ``;
	return `
			<li class="list-group-item" _id="'${f.id}'">
				<div class="row">
					<div class="col-9">
						${f.name}
					</div>
					
					<div class="col-3" style="text-align:right;">
						<a href="#" class="btn btn-outline-info" onClick="createEditField('${escape(JSON.stringify(f))}')">Edit</a>
						<a href="#" class="btn btn-outline-danger">Delete</a>
						${valueBtnS}
					</div>
				<div>
			
			</li>
		`;
}