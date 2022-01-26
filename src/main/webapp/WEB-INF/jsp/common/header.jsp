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
	<nav class="navbar navbar-expand-lg navbar-light"
		style="background-color: #e3f2fd;">
		<div class="container-fluid">
			<a class="navbar-brand" href="/">Diagnostic</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/">Home</a></li>


					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						aria-expanded="false" id="navbarDropdownReport" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> Reports
							configuration </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownReport">
							<li><a class="dropdown-item" href="/fields">Fields</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="/report-configuration">Configure report</a></li>
							
						</ul></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Patients </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="/patient-list">List</a></li>
							<li><a class="dropdown-item" href="/patient">Add new</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="#">Flag reports</a></li>
						</ul></li>

					<li class="nav-item"><a class="nav-link" href="#"
						tabindex="-1" aria-disabled="true">Help me </a></li>
				</ul>
				<!-- 
				<form class="d-flex">
					<input class="form-control me-2" type="search" placeholder="Search"
						aria-label="Search">
					<button class="btn btn-outline-success" type="submit">Search</button>
				</form>
				<span style="width: 20px"></span>
				 -->
				<form action="/perform_logout" class="d-flex">
					<button class="btn btn-outline-warning" type="submit">Logout</button>
				</form>

			</div>
		</div>
	</nav>