$(document).ready(function() {
	reportModuleList();
});



function reset(obj) {
	$("#patient-form").reset();
}



function signUp(obj) {

	const request = {};
	request.username = $('#userNameInput').val();
//	request.password = $('#exampleInputPassword1').val();
	request.email = $('#emailCtrl').val();
	request.phone = $('#phone').val();
//	request.position = $('#position').val();
//	const role = $('#role').val();
	
	
	const reports  = $('#reportType').val();
	const sendTheRe = [];
	if(reports) {
		reports.forEach(r=>{
			sendTheRe.push({
				patientId: request.username,
				reportType: r,
				status: 'init'
			});
		});
	}
	
	
	request.patientInfo = {
		patientId: request.username,
		name: $('#name').val(),
		age: $('#age').val(),
		geneder: $('#geneder').val(),
		address: $('#address').val(),
		referredByDr: $('#referredByDr').val(),
		consultByDr: $('#consultByDr').val(),
		reports:sendTheRe
			
	}
	
	
	
	console.log(request);
	$.ajax({
		url : `api/patient/save-update`,
		cache : false,
		type : "post",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(request),
		success : function(r) {
			console.log(r);
			$('#patient-form')[0].reset();
			$('#successState').show();
			$('#successMessage').text('Successfully register !!');
		},
		error : function(e) {
			console.log(e);
			$('#errorState').show();
			$('#errorMessage').text(e.message);
		},
		beforeSend : function() {
			$(obj).attr('disable','disabled');
		},
		complete : function() {
			$(obj).removeAttr('disable');
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
			let html = ``;
			data.forEach(f=>{
				html +=`<option value="${f.id}">${f.name}</option>`;
			});
			$('#reportType').html(html);
		}

	});
}
