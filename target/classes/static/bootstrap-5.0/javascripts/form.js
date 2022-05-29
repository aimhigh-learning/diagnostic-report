let reportModuleId = '';

let assignedFldLst  = [];

$(document).ready(function() {

	const queryString = window.location.search;
	reportModuleId = queryString.replaceAll('?_id=','');
	
	// get the forms list 
	formList();
	
});


const createUpdateForm = (formId, name , isActive) =>{
	$('#createUpdateFormModal').modal('show');
	$('#formId').val(formId || '');
	$('#name').val(name || '');
	$('#isActive').prop('checked', isActive === true ?true : false);
}

const mapFormFields = (formId) => {
	$('#mapFieldToForm').modal('show');
	$('#formId').val(formId || '');
	fieldList();
	
	// get assigned field list 
	getAssignedFeild();
}
 


const saveUpdateForm = () =>{
	const request = {
		reportModuleId: reportModuleId || '',
		name: $('#name').val()?.trim() || '',
		isActive: $('#isActive').prop('checked') || false,
		id: $('#formId').val() || ''
	};
	
	$.ajax({
		
		url : '/admin/manage/report/form/save',
		method : 'post',
		dataType : 'json',
		contentType : "application/json",
		data : JSON.stringify(request),
		success : function(data) {
			$('#createUpdateFormModal').modal('hide');
			formList();
		}

	});
}

const formList = () =>{
	$.ajax({
		
		url : `/admin/manage/report/form/_all?_id=${reportModuleId}`,
		method : 'get',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			let html = ``;
			data?.content?.forEach(d=>{
				const valueBtnS = d.isActive ? `<a href="#" class="btn btn-outline-success">In used</a>` : `<a href="#" class="btn btn-outline-danger">Un used</a>`;
				html += `
					<li class="list-group-item">
						<div class="row">
							<div class="col-9">
								${d.name || d.id}
							</div>
							
							<div class="col-3" style="text-align:right;">
								${valueBtnS}
								<a href="#" class="btn btn-outline-secondary" onClick="mapFormFields('${d.id}')">Map fields</a>
								<a href="#" class="btn btn-outline-info" onClick="createUpdateForm('${d.id}','${d.name}',${d.isActive})">Edit</a>
								<a href="#" class="btn btn-outline-danger">Delete</a>
							</div>
						</div>
					</li>
				`;
			});
			$('#form_container').html(html);
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
	return `
			<li class="list-group-item" _id="'${f.id}'">
				<div class="row">
					<div class="col-9">
						${f.name}
					</div>
					
					<div class="col-3" style="text-align:right;">
						<a href="#" class="btn btn-outline-info btn-sm" onClick="addField('${f.id}','${f.name}')">Add</a>
					</div>
				<div>
			
			</li>
		`;
}


const getAssignedFeild = () => {
	const formId = $('#formId').val();
	$.ajax({
		
		url : `/admin/manage/report/form/field/assign/_all?formId=${formId}`,
		method : 'get',
		dataType : 'json',
		contentType : "application/json",
		success : function(data) {
			assignedFldLst = [...data?.content];
			drawAssignedFld();
		}

	});
}



const addField = (fieldId, name) =>{
	if(assignedFldLst.find(f=> f.fieldId === fieldId)) {
		throw new Error(`The ${fieldId} is already assigned `);
	}
	assignedFldLst.push({
		uuid:'',
		formId:$('#formId').val(),
		fieldId: fieldId,
		fldOrder:assignedFldLst.length,
		name: name
	});
	console.log(assignedFldLst);
	drawAssignedFld();
	
}

const removeAssignedFld = (fieldId) =>{
	const idx = assignedFldLst.findIndex(f=> f.fieldId === fieldId);
	assignedFldLst.splice(idx,1);
	drawAssignedFld();
	
}

const drawAssignedFld = () =>{
	let html = ``;
	assignedFldLst.forEach(f=>{
		html += `
			<li class="list-group-item" _id="assign_'${f.fieldId}'">
				<div class="row">
					<div class="col-4">
						${f.name}
						<p style="display:none;" id="copy_${f.fieldId}" >${f.fieldId}</p>
					</div>
					
					<div class="col-8" style="text-align:right;">
						<a href="#" class="btn btn-outline-success btn-sm" onClick="copyToClipboard('${f.fieldId}')" title="Copy field id ">Ctrl+C</a>
						<a href="#" class="btn btn-outline-danger btn-sm" onClick="removeAssignedFld('${f.fieldId}')">Remove</a>
					</div>
				<div>
			
			</li>
		`;
	});
	$('#aFieldL').html(html);
}

const  copyToClipboard = (fieldId)=> {
    var copyText = document.getElementById(`copy_`+ fieldId).value;
    navigator.clipboard.writeText(copyText).then(() => {
        console.log(copyText);
    });
 }


const saveTheAssignedFields = () =>{
	const request = assignedFldLst;
	const formId = $('#formId').val();
	$.ajax({
		
		url : `/admin/manage/report/form/field/assign/save?formId=${formId}`,
		method : 'post',
		dataType : 'json',
		contentType : "application/json",
		data : JSON.stringify(request),
		success : function(data) {
			$('#mapFieldToForm').modal('hide');
		}

	});
}