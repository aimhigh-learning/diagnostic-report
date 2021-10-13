<%@include file="/WEB-INF/jsp/common/header.jsp"%>

<link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.0.1/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.datatables.net/1.11.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">

<div class="container-fluid ">
	<div class="row" style="padding-top: 10px"></div>
	<table id="example" class="table table-striped" style="width: 100%">
		<thead>
			<tr>
				<th>Name</th>
				<th>Disease </th>
				<th>Phone</th>
				<th>Email</th>
				<th>Address</th>
				<th>Action</th>
			</tr>
		</thead>
		
	</table>

</div>


<script
	src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.11.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.11.1/js/dataTables.bootstrap5.min.js"></script>

<script
	src="bootstrap-5.0/javascripts/patient-list.js"></script>
	
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
