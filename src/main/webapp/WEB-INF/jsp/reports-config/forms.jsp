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
			<button type="button" class="btn btn-primary" onclick="createNewField()">Create
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

<div class="modal fade" id="createUpdateFieldModal" tabindex="-1"
	role="dialog" aria-labelledby="createUpdateFieldModalTitle"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="createUpdateFieldModalTitle">New
					field</h5>

			</div>

			<div class="modal-body" id="rBody">
				<div class="row">
					<div class="col-12">

						<div class="mb-6">
							<label for="name" class="form-label">Name</label> <input
								type="text" class="form-control" name="name" id="name"
								aria-describedby="nameHelp">
							<div id="nameHelp" class="form-text">Name of the field !</div>
						</div>
					</div>

					<div class="col-12">

						<div class="mb-6">
							<label for="name" class="form-label">Type</label> <select
								class="form-control" name="fieldType" id="fieldType"
								aria-describedby="fieldTypeHelp">
								<option value="inpput_text">Input text</option>
								<option value="input_numeric">Input numeric</option>
								<option value="input_percentage">Input percentage</option>
								<option value="dropdown">Dropdown</option>
							</select>
							<div id="fieldTypeHelp" class="form-text">Select the type
								of field !</div>
						</div>
					</div>



					<div class="col-12">

						<div class="mb-6">
							<label for="name" class="form-label">Max length</label> <input
								type="number" class="form-control" name="maxLength" id="maxLength"
								aria-describedby="maxLengthHelp">
							<div id="maxLengthHelp" class="form-text">Total how many char allowed !</div>
						</div>
					</div>


					<div class="col-12">

						<div class="mb-6">
							<label for="description" class="form-label">Is multiple
								select ?</label> <input type="checkbox"
								name="isMultiselect" id="isMultiselect"
								aria-describedby="isMultiselectHelp">

							<div id="isMultiselectHelp" class="form-text">If the field
								is multiselect then please enable this !</div>
						</div>
					</div>

					<div class="col-12">

						<div class="mb-6">
							<label for="description" class="form-label">Is required ?</label>
							<input type="checkbox" name="isRequired"
								id="isRequired" aria-describedby="isRequiredHelp">

							<div id="isRequiredHelp" class="form-text">If enable then
								will make that field as mandatory !</div>
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
