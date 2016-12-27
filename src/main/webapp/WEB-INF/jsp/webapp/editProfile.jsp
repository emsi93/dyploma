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
<title>Serwis aukcyjny - edycja profilu</title>
<link href="/auction-system/static/menu/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">
<script src="/auction-system/static/menu/js/jquery.js"></script>
<script src="/auction-system/static/menu/js/bootstrap.min.js"></script>

</head>
<body background="/auction-system/static/img/background.jpg">
	<div class="container">
		<c:set var="role" value="${role}" />
		<c:set var="typeOfCompany" value="${typeOfCompany }" />
		<%@include file="navbar.jsp" %>
		<div class="row">

			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Edycja profilu</h1>
					
				</div>
			<div class="panel-body">
			<c:if test="${not empty wiadomosc}">
									<div class="alert alert-info"><center>${wiadomosc}</center></div>
								</c:if>
				<form:form method="post" modelAttribute="profileForm"
				action="/auction-system/webapp/editProfile" role="form">
				<div class="form-group">
					<label>Imie</label>
					<form:input path="name" type="text" class="form-control"
						placeholder="*Imię..." value="${profileForm.name }" />
					<div class="errors">
						<form:errors path="name" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Nazwisko</label>
					<form:input path="surname" type="text" class="form-control"
						placeholder="*Nazwisko..." value="${profileForm.surname }" />
					<div class="errors">
						<form:errors path="surname" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Login</label>
					<form:input path="login" type="text" class="form-control"
						placeholder="*Login..." value="${profileForm.login }" />
					<div class="errors">
						<form:errors path="login" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Hasło</label>
					<form:input path="password" type="password" class="form-control"
						placeholder="*Hasło..." />
					<div class="errors">
						<form:errors path="password" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Powtórz hasło</label>
					<form:input path="password2" type="password" class="form-control"
						placeholder="*Powtórz hasło..." value="${profileForm.password }" />
					<div class="errors">
						<form:errors path="password2" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Numer telefonu</label>
					<form:input path="phoneNumber" type="text" class="form-control"
						placeholder="*Numer telefonu..."
						value="${profileForm.phoneNumber }" />
					<div class="errors">
						<form:errors path="phoneNumber" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Email</label>
					<form:input path="email" type="email" class="form-control"
						placeholder="*Email..." value="${profileForm.email }" />
					<div class="errors">
						<form:errors path="email" element="div" />
					</div>
				</div>
				<form:input class="submit btn btn-primary" path="" type="submit"
					value="Edytuj profil"></form:input>
			</form:form>
			</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>
	</div>
</body>
</html>