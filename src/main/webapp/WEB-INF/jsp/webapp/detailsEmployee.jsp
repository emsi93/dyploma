<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Serwis aukcyjny - szczegóły pracownika</title>
<link href="/auction-system/static/menu/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">
<script src="/auction-system/static/menu/js/jquery.js"></script>

<script src="/auction-system/static/menu/js/bootstrap.min.js"></script>
</head>
<body background="/auction-system/static/img/background.jpg">
	<div class="container">
		<%@include file="navbar.jsp" %>
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Szczegóły pracownika: ${detailsEmployeeModel.name } ${detailsEmployeeModel.surname } </h1>
				</div>
				<div class="panel-body">
				<table class="table table-striped">
					<tbody>
						<tr>
							<td>ID:</td>
							<td>${detailsEmployeeModel.id }</td>
						</tr>
						<tr>
							<td>Imie:</td>
							<td>${detailsEmployeeModel.name }</td>
						</tr>
						<tr>
							<td>Nazwisko:</td>
							<td>${detailsEmployeeModel.surname }</td>
						</tr>
						<tr>
							<td>Telefon:</td>
							<td>${detailsEmployeeModel.phoneNumber }</td>
						</tr>
						<tr>
							<td>Email:</td>
							<td>${detailsEmployeeModel.email }</td>
						</tr>
						<tr>
							<td>Login:</td>
							<td>${detailsEmployeeModel.login }</td>
						</tr>
						<tr>
							<td>Aktywność:</td>
							<td>${detailsEmployeeModel.activity }</td>
						</tr>
						<tr>
							<td>Rola:</td>
							<td>${detailsEmployeeModel.role }</td>
						</tr>
					</tbody>
				</table>
				<a class="btn btn-primary"
					href="/auction-system/webapp/editEmployee/${ detailsEmployeeModel.id }">Edycja</a>
				<a class="btn btn-primary"
					href="/auction-system/webapp/employeesList">Lista pracowników</a>
			</div>
			</div>
			<div class="col-lg-3"></div>

		</div>
	</div>
	</div>
</body>
</html>