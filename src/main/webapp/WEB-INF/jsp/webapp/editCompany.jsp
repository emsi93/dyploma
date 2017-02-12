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
<title>Serwis aukcyjny - edycja firmy</title>
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
				<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Edycja firmy</h1>
					
				</div>
			<div class="panel-body">
			<c:if test="${not empty wiadomosc}">
									<div class="alert alert-info"><center>${wiadomosc}</center></div>
								</c:if>
				<form:form method="post" modelAttribute="companyForm"
					action="/auction-system/webapp/editCompany" role="form">
					<div class="col-lg-2"></div>
				<div class="col-lg-4">
					<div class="form-group">
						<label>Nazwa firmy</label>
						<form:input path="companyName" type="text" class="form-control"
							placeholder="*Nazwa firmy..." value="${companyForm.companyName }" />
						<div class="errors">
							<form:errors path="companyName" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Kraj</label>
						<form:select path="country" items="${countryList}"
							class="form-control" value="${companyForm.country }" />
						<div class="errors">
							<form:errors path="country" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Kod pocztowy</label>
						<form:input path="postcode" type="text" class="form-control"
							placeholder="*Kod pocztowy..." value="${companyForm.postcode }" />
						<div class="errors">
							<form:errors path="postcode" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Miasto</label>
						<form:input path="city" type="text" class="form-control"
							placeholder="*Miasto..." value="${companyForm.city }" />
						<div class="errors">
							<form:errors path="postcode" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Ulica</label>
						<form:input path="street" type="text" class="form-control"
							placeholder="*Ulica..." value="${companyForm.street }" />
						<div class="errors">
							<form:errors path="street" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Numer domu</label>
						<form:input path="flatNumber" type="text" class="form-control"
							placeholder="Numer domu..." value="${companyForm.flatNumber }" />
						<div class="errors">
							<form:errors path="flatNumber" element="div" />
						</div>
					</div>
					</div>
					<div class="col-lg-4">
					<div class="form-group">
						<label>NIP</label>
						<form:input path="nipNumber" type="text" class="form-control"
							placeholder="*NIP..." value="${companyForm.nipNumber }" />
						<div class="errors">
							<form:errors path="nipNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Numer telefonu</label>
						<form:input path="phoneNumber" type="text" class="form-control"
							placeholder="*Numer telefonu..."
							value="${companyForm.phoneNumber }" />
						<div class="errors">
							<form:errors path="phoneNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Email</label>
						<form:input path="email" type="email" class="form-control"
							placeholder="*Email..." value="${companyForm.email }" />
						<div class="errors">
							<form:errors path="email" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Opis firmy</label>
						<form:textarea path="description" type="text" class="form-control"
							placeholder="Opis firmy..." value="${companyForm.description }" rows="5" cols="50"/>
						<div class="errors">
							<form:errors path="description" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Strona internetowa</label>
						<form:input path="website" type="text" class="form-control"
							placeholder="Strona internetowa..."
							value="${companyForm.website }" />
						<div class="errors">
							<form:errors path="website" element="div" />
						</div>
					</div>
					<form:input class="submit btn btn-primary" path="" type="submit"
						value="Zakończ edycję"></form:input>
						</div>
					<div class="col-lg-2"></div>
				</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>