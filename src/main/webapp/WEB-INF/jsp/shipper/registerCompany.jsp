<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<link href="/auction-system/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/auction-system/static/bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rejestracja</title>
</head>
<body>
	<div class="container">
		<div class="row"></div>
		<div class="col-lg-12">
			<form:form method="post" modelAttribute="shipperForm"
				action="/auction-system/shipper/registerCompany" role="form">
				<label>${wiadomosc }</label>

				<div class="form-group">
					<label>Nazwa firmy</label> <br />
					<form:input path="companyName" type="text" class="form-control" />
					<form:errors path="companyName" element="div" />
				</div>
				<div class="form-group">
					<label>Kraj</label> <br />
					<form:input path="country" type="text" class="form-control" />
					<form:errors path="country" element="div" />
				</div>
				<div class="form-group">
					<label>Kod pocztowy</label> <br />
					<form:input path="postcode" type="text" class="form-control" />
					<form:errors path="postcode" element="div" />
				</div>
				<div class="form-group">
					<label>Miejscowość</label> <br />
					<form:input path="city" type="text" class="form-control" />
					<form:errors path="city" element="div" />
				</div>
				<div class="form-group">
					<label>Ulica</label> <br />
					<form:input path="street" type="text" class="form-control" />
					<form:errors path="street" element="div" />
				</div>
				<div class="form-group">
					<label>NIP</label> <br />
					<form:input path="nipNumber" type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					<form:errors path="nipNumber" element="div" />
				</div>
				<div class="form-group">
					<label>REGON</label> <br />
					<form:input path="regonNumber" type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					<form:errors path="regonNumber" element="div" />
				</div>
				<div class="form-group">
					<label>Telefon</label> <br />
					<form:input path="phoneNumber" type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/>
					<form:errors path="phoneNumber" element="div" />
				</div>
				<div class="form-group">
					<label>Email</label> <br />
					<form:input path="email" type="email" class="form-control" />
					<form:errors path="email" element="div" />
				</div>
				<div>
					<form:input class="submit btn btn-primary" path="" type="submit"
						value="Wyślij"></form:input>

				</div>
			</form:form>
			<br />
		</div>
	</div>
</body>
</html>