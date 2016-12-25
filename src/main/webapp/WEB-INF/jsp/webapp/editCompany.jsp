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
<body>
	<c:set var="role" value="${role}" />
	<c:set var="typeOfCompany" value="${typeOfCompany }" />
	<div class="container">
		<%@include file="navbar.jsp" %>
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				${wiadomosc}
				<h2>Edycja firmy</h2>
				<form:form method="post" modelAttribute="companyForm"
					action="/auction-system/webapp/editCompany" role="form">
					<div class="form-group">
						<label class="sr-only">Nazwa firmy</label>
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
						<label class="sr-only">Kod pocztowy</label>
						<form:input path="postcode" type="text" class="form-control"
							placeholder="*Kod pocztowy..." value="${companyForm.postcode }" />
						<div class="errors">
							<form:errors path="postcode" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Miasto</label>
						<form:input path="city" type="text" class="form-control"
							placeholder="*Miasto..." value="${companyForm.city }" />
						<div class="errors">
							<form:errors path="postcode" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Ulica</label>
						<form:input path="street" type="text" class="form-control"
							placeholder="*Ulica..." value="${companyForm.street }" />
						<div class="errors">
							<form:errors path="street" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Numer domu</label>
						<form:input path="flatNumber" type="text" class="form-control"
							placeholder="Numer domu..." value="${companyForm.flatNumber }" />
						<div class="errors">
							<form:errors path="flatNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">NIP</label>
						<form:input path="nipNumber" type="text" class="form-control"
							placeholder="*NIP..." value="${companyForm.nipNumber }" />
						<div class="errors">
							<form:errors path="nipNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Numer telefonu</label>
						<form:input path="phoneNumber" type="text" class="form-control"
							placeholder="*Numer telefonu..."
							value="${companyForm.phoneNumber }" />
						<div class="errors">
							<form:errors path="phoneNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Email</label>
						<form:input path="email" type="email" class="form-control"
							placeholder="*Email..." value="${companyForm.email }" />
						<div class="errors">
							<form:errors path="email" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Opis firmy</label>
						<form:input path="description" type="text" class="form-control"
							placeholder="*Opis firmy..." value="${companyForm.description }" />
						<div class="errors">
							<form:errors path="description" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Strona internetowa</label>
						<form:input path="website" type="text" class="form-control"
							placeholder="*Strona internetowa..."
							value="${companyForm.website }" />
						<div class="errors">
							<form:errors path="website" element="div" />
						</div>
					</div>
					<form:input class="submit btn btn-primary" path="" type="submit"
						value="Zakończ edycję"></form:input>
				</form:form>
			</div>
			<div class="col-lg-4"></div>
		</div>
	</div>
</body>
</html>