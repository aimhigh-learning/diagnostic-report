<%@include file="/WEB-INF/jsp/common/header.jsp"%>

<div class="container-fluid ">
	<div class="row" style="height: 16px;"></div>
	<div class="row">
		<div class="col-4">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item active" aria-current="page"><a href="#">Fields</a></li>
				</ol>
			</nav>
		</div>
		<div class="col-8" style="align-items: flex-end; text-align: right;">
			<button type="button" class="btn btn-primary" onclick="createNew()">Create
				new</button>

		</div>
	</div>
	<div class="row" style="height: 16px;"></div>
	
	<ul class="list-group">
		<li class="list-group-item">Cras justo odio</li>
		<li class="list-group-item">Dapibus ac facilisis in</li>
		<li class="list-group-item">Morbi leo risus</li>
		<li class="list-group-item">Porta ac consectetur ac</li>
		<li class="list-group-item">Vestibulum at eros</li>
	</ul>
	
	<div class="row" style="height: 16px;"></div>

</div>

<script src="bootstrap-5.0/js/jquery-3.6.0.min.js"></script>

<script src="bootstrap-5.0/javascripts/manage-reports-config.js"></script>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
