$(document).ready(function() {

 $.ajax({

		url : '/api/patient/list?_page=0&_size=10',
		method : 'get',
		dataType : 'json',
		success : function(data) {
			
			// transform data before render on DOM
			const array = [];
			data.content.forEach(f=>{
				array.push({					
					name :f.name || '',
					disease:'',
					phone:f?.users?.phone || '',
					email:f?.email?.email || '',
					address:f.address || '',
					actions:''
				});
				
			});
			
			__inItDatatable(array);

		}

	});
	

});


function __inItDatatable(data) {
	console.log(data);
	$('#example').DataTable({
		pagination: "bootstrap",
	    filter:true,
	    data: data,
	    destroy: true,
	    lengthMenu:[5,10,25],
	    pageLength: 10,
		columns : [
			{ "data": "name" },
			{ "data": "disease" },
			{ "data": "phone" },
			{ "data": "email" },
			{ "data": "address" },
			{ "data": "actions" }]
	});
}