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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja załadowcy</title>
<link href="/auction-system/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/auction-system/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div >${wiadomosc } </div>
			<form:form method="post" modelAttribute="registerForm"
				action="/auction-system/shipper/register" role="form">
				<div class="col-lg-6">
					<label> Dane załadowcy</label>
					<div class="form-group">
						<label class="sr-only">Nazwa firmy</label>
						<form:input path="companyName" type="text" class="form-control"
							placeholder="*Nazwa firmy..." />
						<div class="errors">
							<form:errors path="companyName" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Kraj</label>
						<form:select path="country" items="${countryList}"
							class="form-control"/>
						<div class="errors">
							<form:errors path="country" element="div"/>
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Kod pocztowy</label>
						<form:input path="postcode" type="text" class="form-control"
							placeholder="*Kod pocztowy..." />
						<div class="errors">
							<form:errors path="postcode" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Miasto</label>
						<form:input path="city" type="text" class="form-control"
							placeholder="*Miasto..." />
						<div class="errors">
							<form:errors path="postcode" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Ulica</label>
						<form:input path="street" type="text" class="form-control"
							placeholder="*Ulica..." />
						<div class="errors">
							<form:errors path="street" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Numer domu</label>
						<form:input path="flatNumber" type="text" class="form-control"
							placeholder="Numer domu..." />
						<div class="errors">
							<form:errors path="flatNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">NIP</label>
						<form:input path="nipNumber" type="text" class="form-control"
							placeholder="*NIP..." />
						<div class="errors">
							<form:errors path="nipNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Numer telefonu</label>
						<form:input path="phoneNumber" type="text" class="form-control"
							placeholder="*Numer telefonu..." />
						<div class="errors">
							<form:errors path="phoneNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Email</label>
						<form:input path="email" type="text" class="form-control"
							placeholder="*Email..." />
						<div class="errors">
							<form:errors path="email" element="div" />
						</div>
					</div>
					<form:input class="submit btn btn-primary" path="" type="submit"
					value="Zakończ rejestrację" id="register"></form:input>
				</div>
				
				<div class="col-lg-6">
					<label> Dane użytkownika</label>
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
				</div>
			</form:form>
		</div>
	</div>
</body>
</html>