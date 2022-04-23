
$(document).ready(function() {
	dataTableData();
});

const dataTableData = () =>{
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
					reports: getReportType(f.reports),
					phone:f?.users?.phone || '',
					geneder:f?.geneder || '',
					address:f.address || '',
					status: '',
					actions: f
				});
				
			});
			
			__inItDatatable(array);

		}

	});
}

function getReportType(reports) {
	const fl = [];
	reports.forEach(f=>{
		switch(f.reportType) {
			case 'widle_test':
				fl.push('Widle Test');
				break;
				
			case 'blodd_glucose':
				fl.push('Blood Glucose');
				break;
				
			default:
				fl.push('Other');
				break;
		}
	});
	return fl.toString();
}

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
			{ "data": "reports" },
			{ "data": "phone" },
			{ "data": "geneder" },
			{ "data": "address" },
			{ "data": "status" },
			{ "data": "actions", render : function ( data, type, row, meta ) {
	              					return `<div class="row"> ${actionsBtnCnt(data)}</div>`;
			}}
			]
	});
}

function actionsBtnCnt(row) {
	
	let data = `
		<div class="col-6">
			<li class="nav-item dropdown" style="list-style: none;"><a class="nav-link dropdown-toggle" href="#" aria-expanded="false" id="${row.patientId}" role="button" data-bs-toggle="dropdown">Generate Reports
			 </a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdownReport" style="">
	`;
	row.reports.forEach(f=>{
		data += `<li reportId="${f.reportId}" onclick="generateR('${f.reportType}', '${f.patientId}','${f.reportId}')" ><a class="dropdown-item" href="#">Generate ${getReportType([f])}</a></li>`;
	});
	data += `</ul></li> </div>`;
	
	if(row.reports.find(f=>f.status ==='done')) {
		let done = `
		<div class="col-6">
			<li class="nav-item dropdown" style="list-style: none;"><a class="nav-link dropdown-toggle" href="#" aria-expanded="false" id="${row.patientId}" role="button" data-bs-toggle="dropdown">Download</a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdownReport" style="">
			`;
			row.reports.filter(f=> f.status === 'done').forEach(f=>{
				done += `<li reportId="${f.reportId}" onclick="generateR('${f.reportType}', '${f.patientId}','${f.reportId}')" ><a class="dropdown-item" href="#">${getReportType([f])}</a></li>`;
			});
			done += `</ul></li></div>`;
			
			data += done;
	}
	
	return data;
}
