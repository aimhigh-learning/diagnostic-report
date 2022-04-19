<%@include file="/WEB-INF/jsp/common/header.jsp"%>

<div class="container-fluid ">
	<div class="row" style="height: 16px;"></div>
	<div class="row">
		<div class="col-4">
			<nav aria-label="breadcrumb">
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Home</a></li>
					<li class="breadcrumb-item active" aria-current="page"><a
						href="#">Forms</a></li>
				</ol>
			</nav>
		</div>
		<div class="col-8" style="align-items: flex-end; text-align: right;">
			<button type="button" class="btn btn-primary"
				onclick="createUpdateForm()">Create new</button>

		</div>
	</div>
	<div class="row" style="height: 16px;"></div>

	<ul class="list-group" id="form_container">

	</ul>

	<div class="row" style="height: 16px;"></div>


</div>

<div class="modal fade" id="createUpdateFormModal" tabindex="-1"
	role="dialog" aria-labelledby="createUpdateFormModalTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="createUpdateFormModalTitle">Create
					form</h5>

			</div>

			<input type="hidden" id="formId" />

			<div class="modal-body" id="rBody">
				<div class="row">
					<div class="col-12">

						<div class="mb-6">
							<label for="name" class="form-label">Name</label> <input
								type="text" class="form-control" name="name" id="name"
								aria-describedby="nameHelp">
							<div id="nameHelp" class="form-text">Name of the form !</div>
						</div>
					</div>


					<div class="col-12">

						<div class="mb-6">
							<label for="description" class="form-label">Is Active ?</label> <input
								type="checkbox" name="isActive" id="isActive"
								aria-describedby="isActiveHelp">

							<div id="isActiveHelp" class="form-text">Enable for use</div>
						</div>
					</div>


				</div>
			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					onclick="saveUpdateForm()">Save changes</button>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="mapFieldToForm" tabindex="-1" role="dialog"
	aria-labelledby="mapFieldToFormTitle" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="mapFieldToFormTitle">Map fields</h5>

			</div>

			<input type="hidden" id="formId" />

			<div class="modal-body" id="rBody" style="height: 500px; overflow: auto;">

				<div class="row">
					<div class="col-5">
						<h5>Un assigned fields</h5>
						<ul class="list-group" id="fieldL">
							
						</ul>
					</div>

					<div class="col-2"></div>

					<div class="col-5">
						<h5>Assigned fields</h5>
						<ul class="list-group" id="aFieldL">
							
						</ul>
					</div>

				</div>

			</div>

			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					onclick="saveUpdateForm()">Save changes</button>
			</div>
		</div>
	</div>
</div>



<script src="bootstrap-5.0/js/jquery-3.6.0.min.js"></script>

<script src="bootstrap-5.0/javascripts/form.js"></script>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
