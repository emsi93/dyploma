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
		<nav class="navbar navbar-default" role="navigation">
		<div class="container-fluid">
			<!-- Grupowanie "marki" i przycisku rozwijania mobilnego menu -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Rozwiń nawigację</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/auction-system/webapp/menu">System aukcyjny towarów</a>
			</div>

			<!-- Grupowanie elementów menu w celu lepszego wyświetlania na urządzeniach moblinych -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
										<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Menu<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<c:if test="${ role=='ROLE_ADMIN_CARRIER' || role=='ROLE_USER_CARRIER'}">
								<li><a href="/auction-system/webapp/searchCargo">Szukaj
										towaru</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_USER_SHIPPER'}">
								<li><a href="/auction-system/webapp/newCargo">Dodaj
										towar</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_USER_SHIPPER'}">
								<li><a href="/auction-system/webapp/cargosList">Lista
										towarów</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
								<li><a href="/auction-system/webapp/employeesList">Lista
										użytkowników</a></li>
								<li class="divider"></li>
							</c:if>

							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
									<li><a href="/auction-system/webapp/newUser">Nowy
										pracownik</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
									<li><a href="/auction-system/webapp/editCompany">Twoja
										firma</a></li>
								<li class="divider"></li>
							</c:if>
							<li><a href="/auction-system/webapp/editProfile">Twój
									profil</a></li>
							<li class="divider"></li>
							<li><a href="#">Historia transakcji</a></li>
						</ul></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<form class="navbar-form navbar-left" role="search">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Szukaj">
						</div>
						<button type="submit" class="btn btn-default">Wyślij</button>
					</form>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">${username }<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="editProfile">Edytuj profil</a></li>
							<li><a href="<c:url value="/j_spring_security_logout" />">Wyloguj
									się</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		</nav>
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