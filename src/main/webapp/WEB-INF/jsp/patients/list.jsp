<%@include file="/WEB-INF/jsp/common/header.jsp"%>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdn.datatables.net/1.11.1/css/dataTables.bootstrap5.min.css"
	rel="stylesheet">

<div class="container-fluid ">
	<div class="row" style="padding-top: 10px"></div>
	<table id="example" class="table table-striped" style="width: 100%">
		<thead>
			<tr>
				<th>Name</th>
				<th>Reports</th>
				<th>Phone</th>
				<th>Gender</th>
				<th>Address</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>

	</table>

</div>

<!--  Modal for the generate report  -->

<div class="modal fade" id="generateReportModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="generateReportModalTitle">Generate
					report (TODO)</h5>

			</div>
			
			
			
			<div class="modal-body" id="rBody">
				
				
			</div>
			
			
			
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>


<script src="bootstrap-5.0/js/jquery-3.6.0.min.js"></script>

<script
	src="https://cdn.datatables.net/1.11.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.11.1/js/dataTables.bootstrap5.min.js"></script>

<script src="bootstrap-5.0/javascripts/patient-list.js"></script>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
