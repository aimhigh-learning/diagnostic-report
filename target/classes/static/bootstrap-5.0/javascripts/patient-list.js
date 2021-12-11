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
	

});

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
	let data = ``;
	row.reports.forEach(f=>{
		data += `<button type="button" class="btn btn-info col-4" reportId="${f.reportId}" onclick="generateReport('${f.reportId}', '${row.patientId}')">Generate ${getReportType([f])}</button> <span class="col-1"></span>`;
	});
	return data;
}

function generateReport(reportId, patientId) {
	$('#generateReportModal').modal('show')
	console.log(`${reportId} - ${patientId}`);
}