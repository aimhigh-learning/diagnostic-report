<%@include file="/WEB-INF/jsp/common/header.jsp"%>

<div class="container-fluid ">
	<div class="row">
		<div class="row" style="display: none" id="errorState">
			<div class="col-4"></div>
			<div class="col-4 alert alert-danger" role="alert">
				<span id="errorMessage"></span>
			</div>
		</div>

		<div class="row" style="display: none" id="successState">
			<div class="col-4"></div>
			<div class="col-4 alert alert-success" role="alert">
				<span id="successMessage"></span>
			</div>
		</div>

		<form id="patient-form">
			<div class="row">
				<div class="col-4">
					<div class="mb-3">
						<label for="userNameInput" class="form-label">Patient Id</label> <input
							type="text" name="username" class="form-control"
							id="userNameInput" aria-describedby="userNameHelp">
						<div id="userNameHelp" class="form-text">Enter unique
							patient id , will not share to anyone !!</div>
					</div>
				</div>

				<div class="col-4">
					<div class="mb-3">
						<label for="nameCtrl" class="form-label">Name</label> <input
							type="text" class="form-control" name="name" id="name"
							aria-describedby="nameHelp">
						<div id="nameHelp" class="form-text">Patient name !</div>
					</div>
				</div>

				<div class="col-4">

					<div class="mb-3">
						<label for="phone" class="form-label">Phone</label> <input
							type="text" class="form-control" name="phone" id="phone"
							aria-describedby="phoneHelp">
					</div>
				</div>



			</div>



			<div class="row">

				<div class="col-4">

					<div class="mb-3">
						<label for="age" class="form-label">Age</label> <input type="text"
							class="form-control" name="age" id="age"
							aria-describedby="ageHelp">

						<div id="ageHelp" class="form-text">Enter the age of patient
							!</div>
					</div>
				</div>


				<div class="col-4">
					<div class="mb-3">
						<label for="geneder" class="form-label">Gender</label> <select
							class="form-control" name="geneder" id="geneder"
							aria-describedby="genederHelp">
							<option value="male">Male</option>
							<option value="female">Female</option>
							<option value="other">Other</option>
						</select>
						<div id="genederHelp" class="form-text">Please select the
							gender type !</div>
					</div>
				</div>

				<div class="col-4">

					<div class="mb-3">
						<label for="referredByDr" class="form-label">Referred By
							Dr.</label> <input type="text" class="form-control" name="referredByDr"
							id="referredByDr" aria-describedby="referredByDrHelp">

						<div id="referredByDrHelp" class="form-text">Please enter
							the doctor name if referred !!</div>
					</div>
				</div>
			</div>


			<div class="row">

				<div class="col-4">
					<div class="mb-3">
						<label for="consultByDr" class="form-label">Consult by Dr.</label>
						<input type="text" class="form-control" name="consultByDr"
							id="consultByDr" aria-describedby="consultByDrHelp">

						<div id="consultByDrHelp" class="form-text">Please enter
							consult Dr. name</div>
					</div>
				</div>


				<div class="col-4">
					<div class="mb-3">
						<label for="reportType" class="form-label">Report for</label> <select
							class="form-control" name="reportType" multiple id="reportType"
							aria-describedby="reportTypeHelp">
							<option value="blodd_glucose">Blood Glucose</option>
							<option value="widle_test">Widle Test</option>
							<option value="other">Other</option>
						</select>
						<div id="reportTypeHelp" class="form-text">Please select the type of report !!</div>
					</div>
				</div>


				<div class="col-4">
					<div class="mb-3">
						<label for="address" class="form-label">Address</label>
						<textarea rows="3" cols="" class="form-control" name="address"
							id="address" aria-describedby="addressHelp"></textarea>
						<div id="addressHelp" class="form-text">Please enter
							Patient's address !</div>
					</div>
				</div>


			</div>

			<div class="row">
				<div class="col-1">
					<button type="button" class="btn btn-danger" onclick="reset(this)">Reset</button>
				</div>
				
				<div class="col-1">
					<button type="button" class="btn btn-primary" onclick="signUp(this)">Submit</button>
				</div>
				
				<div class="col-8"></div>
			
			</div>

						
		</form>

	</div>

	<div class="row" style="height: 20px"></div>

</div>

<script src="bootstrap-5.0/javascripts/patient-save-update.js"></script>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
