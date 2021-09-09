<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
			<ranasoftcraft:if test="${errorMsg != null}">
				<div class="row">
					<div class="col-4"></div>
					<div class="col-4 alert alert-danger" role="alert">
						<ranasoftcraft:out value="${errorMsg}" />
					</div>
				</div>
			</ranasoftcraft:if>

			<ranasoftcraft:if test="${msg != null}">
				<div class="row">
					<div class="col-4"></div>
					<div class="col-4 alert alert-success" role="alert">
						<ranasoftcraft:out value="${msg}" />
					</div>
				</div>
			</ranasoftcraft:if>

			<div class="row">
				<div class="col-4"></div>
				<div class="col-4">
					<form method="post" action="/perform_login">
						<div class="mb-3">
							<label for="userNameInput" class="form-label">User name</label> <input
								type="text" name="user_name" class="form-control"
								id="userNameInput" aria-describedby="userNameHelp">
							<div id="userNameHelp" class="form-text">We'll never share
								your email/username with anyone else.</div>
						</div>
						<div class="mb-3">
							<label for="exampleInputPassword1" class="form-label">Password</label>
							<input type="password" class="form-control" name="password"
								id="exampleInputPassword1">
						</div>
						<div class="mb-3 form-check">

							<div class="row">
								<div class="col-6">
									<input type="checkbox" class="form-check-input"
										id="exampleCheck1"> <label class="form-check-label"
										for="exampleCheck1">Remember me</label>
								</div>

								<div class="col-6" style="text-align: right;">
									<label  class="form-check-label">
										<a href="/signup">Signup</a>
									</label>
								</div>

							</div>

						</div>
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>
				</div>
			</div>


		</div>
	</div>


</body>
</html>