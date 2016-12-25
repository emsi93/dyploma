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
<body>
	<div class="container">
		<%@include file="navbar.jsp" %>
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				<h4>ID:${detailsEmployeeModel.id }</h4>
				<h4>Imię:${detailsEmployeeModel.name }</h4>
				<h4>Nazwisko:${detailsEmployeeModel.surname }</h4>
				<h4>Telefon:${detailsEmployeeModel.phoneNumber }</h4>
				<h4>Email:${detailsEmployeeModel.email }</h4>
				<h4>Login:${detailsEmployeeModel.login }</h4>
				<h4>Aktywność:${detailsEmployeeModel.activity }</h4>
				<h4>Rola:${detailsEmployeeModel.role }</h4>
				<a class="btn btn-primary"
					href="/auction-system/webapp/editEmployee/${ detailsEmployeeModel.id }">Edycja</a>
				<a class="btn btn-primary"
					href="/auction-system/webapp/employeesList">Lista pracowników</a>
			</div>
			<div class="col-lg-4"></div>

		</div>
	</div>
</body>
</html>