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
<link href="/auction-system/static/css/errors.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/auction-system/static/bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tworzenie użytkownika</title>
</head>
<body>
	<div class="container">
		<div class="row"></div>
		<div class="col-lg-6">
			<form:form method="post" modelAttribute="userForm"
				action="/auction-system/shipper/registerUser/${firm}/" role="form">
				<label>${wiadomosc }</label>
				<label>Dane użytkownika:</label>
				<div class="form-group">
					<label>Imie</label> <br />
					<form:input path="name" type="text" class="form-control" />
					<div class="errors">
					<form:errors path="name" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Nazwisko</label> <br />
					<form:input path="surname" type="text" class="form-control" />
					<div class="errors">
					<form:errors path="surname" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Login</label> <br />
					<form:input path="login" type="text" class="form-control" />
					<div class="errors">
					<form:errors path="login" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Hasło</label> <br />
					<form:input path="password" type="password" class="form-control" />
					<div class="errors">
					<form:errors path="password" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Kraj</label> <br />
					<form:select path="country" type="text" class="form-control" items="${country }" />
					<div class="errors">
					<form:errors path="country" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Kod pocztowy</label> <br />
					<form:input path="postcode" type="text" class="form-control" />
					<div class="errors">
					<form:errors path="postcode" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Miejscowość</label> <br />
					<form:input path="city" type="text" class="form-control" />
					<div class="errors">
					<form:errors path="city" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Ulica</label> <br />
					<form:input path="street" type="text" class="form-control" />
					<div class="errors">
					<form:errors path="street" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Numer budynku</label> <br />
					<form:input path="houseNumber" type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
					<div class="errors">
					<form:errors path="houseNumber" element="div" />
					</div>
				</div>

				<div class="form-group">
					<label>Numer lokalu</label> <br />
					<form:input path="flatNumber" type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
					<div class="errors">
					<form:errors path="flatNumber" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Numer PESEL</label> <br />
					<form:input path="peselNumber" type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
					<div class="errors">
					<form:errors path="peselNumber" element="div" />
					</div>
				</div>
				<div class="form-group">
					<label>Telefon</label> <br />
					<form:input path="phoneNumber" type="text" class="form-control" onkeypress='return event.charCode >= 48 && event.charCode <= 57' />
					<div class="errors">
					<form:errors path="phoneNumber" element="div" />
					</div>
				</div>
				<div class="form-group">
						<label>Email</label> <br />
						<form:input path="email" type="email" class="form-control" />
						<div class="errors">
						<form:errors path="email" element="div" />
						</div>
					</div>
				<div>
					<form:input class="submit btn btn-primary" path="" type="submit"
						value="Zakończ rejestrację"></form:input>
					<a href="/auction-system/shipper/">Index</a>
				</div>
			</form:form>
			<br />
		</div>
	</div>
</body>
</html>