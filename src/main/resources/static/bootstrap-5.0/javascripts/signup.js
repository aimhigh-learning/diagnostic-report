function signUp(obj) {

	const request = {};
	request.username = $('#userNameInput').val();
	request.password = $('#exampleInputPassword1').val();
	request.email = $('#emailCtrl').val();
	request.phone = $('#phone').val();
	request.position = $('#position').val();
	const role = $('#role').val();
	console.log(request);
	$.ajax({
		url : `/signup?role=${role}`,
		cache : false,
		type : "post",
		contentType : "application/json",
		dataType : "json",
		data : JSON.stringify(request),
		success : function(r) {
			console.log(r);
			$('#signup_form')[0].reset();
			$('#successState').show();
			$('#successMessage').text('Successfully signup !! Please login with same credentials');
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
