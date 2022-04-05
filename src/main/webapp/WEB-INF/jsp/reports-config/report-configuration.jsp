<%@include file="/WEB-INF/jsp/common/header.jsp"%>

<div class="container-fluid ">
	<div class="row" style="height: 16px;"></div>
	<div class="row">
		<div class="col-4">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item active"><a href="#">Home</a></li>
					<li class="breadcrumb-item" aria-current="page"><a href="#">Fields</a></li>
				</ol>
			</nav>
		</div>
		<div class="col-8" style="align-items: flex-end; text-align: right;">
			<button type="button" class="btn btn-primary" onclick="createNew()">Create
				new</button>

		</div>
	</div>
	<div class="row" style="height: 16px;"></div>
	<div class="row" id="data"></div>

	<div class="row" style="padding-bottom: 10px"></div>

</div>


<div class="modal fade" id="createUpdateReportModule" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalCenterTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="createUpdateReportModuleTitle">Create
					new report type</h5>

			</div>

			<div class="modal-body" id="rBody">
				<div class="row">
					<div class="col-12">

						<div class="mb-6">
							<label for="name" class="form-label">Report type name</label> <input
								type="text" class="form-control" name="name" id="name"
								aria-describedby="nameHelp">
							<div id="name" class="form-text">Name of the report type !</div>
						</div>
					</div>

					<div class="col-12">

						<div class="mb-6">
							<label for="description" class="form-label">Description</label> <input
								type="text" class="form-control" name="description"
								id="description" aria-describedby="descriptionhelp">

							<div id="descriptionhelp" class="form-text">Description for
								the report type</div>
						</div>
					</div>


				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					onclick="saveUpdateReport()"">Save changes</button>
			</div>
		</div>
	</div>
</div>

<script src="bootstrap-5.0/js/jquery-3.6.0.min.js"></script>

<script src="bootstrap-5.0/javascripts/manage-reports-config.js"></script>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
