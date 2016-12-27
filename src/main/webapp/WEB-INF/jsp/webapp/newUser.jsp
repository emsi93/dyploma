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
<title>Serwis aukcyjny - nowy użytkownik</title>
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
	<c:set var="role" value="${role}" />
	<c:set var="typeOfCompany" value="${typeOfCompany }" />
	<div class="container">
	<%@include file="navbar.jsp" %>
		<div class="row">
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Dodawanie nowego pracownika</h1>
					<h6>*Pola wymagane</h6>
				</div>
			<div class="panel-body">
			<c:if test="${not empty wiadomosc}">
									<div class="alert alert-info"><center>${wiadomosc}</center></div>
								</c:if>
				
				<form:form method="post" modelAttribute="userForm"
					action="/auction-system/webapp/newUser" role="form">
					<div class="form-group">
						<label class="sr-only">Imie</label>
						<form:input path="name" type="text" class="form-control"
							placeholder="*Imię..." />
						<div class="errors">
							<form:errors path="name" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Nazwisko</label>
						<form:input path="surname" type="text" class="form-control"
							placeholder="*Nazwisko..." />
						<div class="errors">
							<form:errors path="surname" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Login</label>
						<form:input path="login" type="text" class="form-control"
							placeholder="*Login..." />
						<div class="errors">
							<form:errors path="login" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Hasło</label>
						<form:input path="password" type="password" class="form-control"
							placeholder="*Hasło..." />
						<div class="errors">
							<form:errors path="password" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Powtórz hasło</label>
						<form:input path="password2" type="password" class="form-control"
							placeholder="*Powtórz hasło..." />
						<div class="errors">
							<form:errors path="password2" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Numer telefonu</label>
						<form:input path="phoneNumberUser" type="text"
							class="form-control" placeholder="*Numer telefonu..." />
						<div class="errors">
							<form:errors path="phoneNumberUser" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Email</label>
						<form:input path="emailUser" type="email" class="form-control"
							placeholder="*Email..." />
						<div class="errors">
							<form:errors path="emailUser" element="div" />
						</div>
					</div>
					<form:input class="submit btn btn-primary" path="" type="submit"
						value="Dodaj pracownika" id="register"></form:input>
				</form:form>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
		</div>
	</div>
</body>
</html>