
let currentReport = null;

$(document).ready(function() {
	reportModuleList();
});


const createNew = () => {
	$('#createUpdateReportModule').modal('show')
	currentReport = null;
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
					<a href="fields?_id=${report.id}" class="btn btn-primary" style="">Fields</a>
				</div>
			</div>


		</div>
	`;
}
