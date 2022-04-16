let reportModuleId = '';

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
								<a href="#" class="btn btn-outline-info" onClick="createUpdateForm('${d.id}','${d.name}',${d.isActive})">Edit</a>
								<a href="#" class="btn btn-outline-danger">Delete</a>
							</div>
						<div>
					</li>
				`;
			});
			$('#form_container').html(html);
		}

	});
}