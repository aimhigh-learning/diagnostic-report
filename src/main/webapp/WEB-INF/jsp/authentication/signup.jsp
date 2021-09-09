<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="/WEB-INF/jsp/common/includeTag.jsp"%>

<!-- Bootstrap Core Css -->
<link href="bootstrap-5.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid  h-100">
		<div
			class="row d-flex flex-column min-vh-100 justify-content-center align-items-center">
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

			<div class="row">
				<div class="col-4"></div>
				<div class="col-4">
					<form id="signup_form">
						<div class="mb-3">
							<label for="userNameInput" class="form-label">User name</label> <input
								type="text" name="username" class="form-control"
								id="userNameInput" aria-describedby="userNameHelp">
							<div id="userNameHelp" class="form-text">Enter unique
								username , will not share to anyone !!</div>
						</div>
						<div class="mb-3">
							<label for="exampleInputPassword1" class="form-label">Password</label>
							<input type="password" class="form-control" name="password"
								id="exampleInputPassword1" aria-describedby="passWordHelp">
							<div id="passWordHelp" class="form-text">More complex
								password will have be more secured</div>
						</div>


						<div class="mb-3">
							<label for="emailCtrl" class="form-label">Email</label> <input
								type="text" class="form-control" name="email" id="emailCtrl"
								aria-describedby="emailHelp">
							<div id="emailHelp" class="form-text">This will use for
								notification</div>
						</div>



						<div class="mb-3">
							<label for="phone" class="form-label">Phone</label> <input
								type="text" class="form-control" name="phone" id="phone"
								aria-describedby="phoneHelp">
						</div>



						<div class="mb-3">
							<label for="position" class="form-label">Position</label>
							<select
								class="form-control" name="position" id="position" aria-describedby="positionHelp">
								<option value="Goalkeeper">Goalkeeper</option>
								<option value="Defender">Defender </option>
								<option value="Midfielder">Midfielder </option>
								<option value="Forward">Forward  </option>
							</select>
							<div id="positionHelp" class="form-text">This is the player position where they want to play !!!</div>
						</div>

						<div class="mb-3">
							<label for="role" class="form-label">Role</label> <select
								class="form-control" name="role" id="role">
								<option value="101">User</option>
								<option value="103">Player</option>
								<option value="102">Administrator</option>
							</select>
						</div>

						<button type="button" class="btn btn-primary"
							onclick="signUp(this)">Submit</button>
					</form>
				</div>
			</div>


		</div>
	</div>


</body>
<script src="bootstrap-5.0/js/jquery.min.js"></script>
<script src="bootstrap-5.0/javascripts/signup.js"></script>
</html>