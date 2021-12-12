
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
		data += `<button type="button" class="btn btn-info col-4" reportId="${f.reportId}" onclick="generateReport('${f.reportId}','${f.reportType}', '${row.patientId}')">Generate ${getReportType([f])}</button> <span class="col-1"></span>`;
	});
	return data;
}

function generateReport(reportId,reportType, patientId) {
	$('#generateReportModal').modal('show')
	console.log(`${reportId} - ${reportType} -  ${patientId}`);
	
	switch(reportType) {
		case 'widle_test':
			$('#rBody').html(getLayoutForWidleTest());
			break;
			
		case 'blodd_glucose':
//			fl.push('Blood Glucose');
			break;
			
		default:
//			fl.push('Other');
			break;
	
	}
}


function getLayoutForWidleTest() {
	return `
				<div class="container-fluid">
					<div class="row">
						<div class="col-6">

							<div class="mb-6">
								<label for="sTyphiOAntigenValue" class="form-label">S. typhi 'O' Antigen</label> <input
									type="text" class="form-control" name="sTyphiOAntigenValue" id="sTyphiOAntigenValue"
									aria-describedby="oAntigenHelp">
	
								<div id="oAntigenHelp" class="form-text">Enter S. typhi 'O' Antigen  !</div>
							</div>
						</div>
						
						<div class="col-6">

							<div class="mb-6">
								<label for="sTyphiHAntigenValue" class="form-label">S. typhi 'H' Antigen</label> <input
									type="text" class="form-control" name="sTyphiHAntigenValue" id="sTyphiHAntigenValue"
									aria-describedby="hAntigenHelp">
	
								<div id="hAntigenHelp" class="form-text">Enter S. typhi 'H' Antigen  !</div>
							</div>
						</div>
						
						
					</div>
					
										
					<div class="row">
						<div class="col-6">

							<div class="mb-6">
								<label for="sTyphiAHAntigenCnt" class="form-label">S. paratyphi A(H) Antigen</label> <input
									type="text" class="form-control" name="sTyphiAHAntigenCnt" id="sTyphiAHAntigenCnt"
									aria-describedby="ahAntigenHelp">
	
								<div id="ahAntigenHelp" class="form-text">Enter S. paratyphi A(H) Antigen  !</div>
							</div>
						</div>
						
						<div class="col-6">

							<div class="mb-6">
								<label for="sTyphiBHAntigenValue" class="form-label">S. paratyphi B(H) Antigen</label> <input
									type="text" class="form-control" name="sTyphiBHAntigenValue" id="sTyphiBHAntigenValue"
									aria-describedby="bhAntigenHelp">
	
								<div id="bhAntigenHelp" class="form-text">Enter S. paratyphi B(H) Antigen  !</div>
							</div>
						</div>
						
						
					</div>
					
					<div class="row">
						<div class="col-6">

							<div class="mb-6">
								<label for="inteprentationM" class="form-label">Interpretation</label> <input
									type="text" class="form-control" name="inteprentationM" id="inteprentationM"
									aria-describedby="inteprentationMHelp">
	
								<div id="inteprentationMHelp" class="form-text">Enter Interpretation !</div>
							</div>
						</div>
						
					</div>
					
				</div>
	
	`;
}