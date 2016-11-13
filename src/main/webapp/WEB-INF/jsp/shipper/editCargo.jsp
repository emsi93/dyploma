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
<title>Serwis aukcyjny - edycja ładunku</title>
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
				<a class="navbar-brand" href="/auction-system/shipper/menu">System
					aukcyjny towarów</a>
			</div>

			<!-- Grupowanie elementów menu w celu lepszego wyświetlania na urządzeniach moblinych -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Menu<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<c:if test="${ role=='ROLE_ADMIN' || role=='ROLE_USER'}">
								<c:if test="${typeOfCompany=='2' }">
									<li><a href="#">Szukaj towaru</a></li>
									<li class="divider"></li>
								</c:if>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN' || role=='ROLE_USER'}">
								<c:if test="${typeOfCompany=='1' }">
									<li><a href="/auction-system/shipper/newCargo">Dodaj
											towar</a></li>
									<li class="divider"></li>
								</c:if>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN' || role=='ROLE_USER'}">
								<c:if test="${typeOfCompany=='1' }">
									<li><a href="/auction-system/shipper/cargosList">Lista
											towarów</a></li>
									<li class="divider"></li>
								</c:if>
							</c:if>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="/auction-system/shipper/employeesList">Lista
											użytkowników</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="/auction-system/shipper/newUser">Nowy
											pracownik</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="/auction-system/shipper/editCompany">Twoja
											firma</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<li><a href="/auction-system/shipper/editProfile">Twój
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
							<li><a href="/auction-system/shipper/editProfile">Edytuj
									profil</a></li>
							<li><a href="<c:url value="/j_spring_security_logout" />">Wyloguj
									się</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		</nav>
		<div class="row">
			<form:form method="post" modelAttribute="editGoodForm"
				action="/auction-system/shipper/editCargo/${editGoodForm.id }"
				role="form">

				<div class="col-lg-2">*Pola wymagane</div>
				<div class="col-lg-4">
					<h2>Nowy towar</h2>
					${wiadomosc}
					<div class="form-group">
						<label class="sr-only">Nagłówek</label>
						<form:input path="title" type="text" class="form-control"
							placeholder="*Nagłówek..." value="${editGoodForm.title }" />
						<div class="errors">
							<form:errors path="title" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Opis</label>
						<form:textarea rows="4" path="content" type="text"
							class="form-control" placeholder="Opis..."
							value="${editGoodForm.content }" />
						<div class="errors">
							<form:errors path="content" element="div" />
						</div>
					</div>
					<label>Miejsce załadunku</label>
					<div class="form-group">
						<label>Kraj</label>
						<form:select path="fromCountry" items="${countryList}"
							class="form-control" value="${editGoodForm.fromCountry }" />
						<div class="errors">
							<form:errors path="fromCountry" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Miasto</label>
						<form:input path="fromCity" type="text" class="form-control"
							placeholder="*Miasto..." value="${editGoodForm.fromCity }" />
						<div class="errors">
							<form:errors path="fromCity" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Ulica</label>
						<form:input path="fromStreet" type="text" class="form-control"
							placeholder="*Ulica..." value="${editGoodForm.fromStreet }" />
						<div class="errors">
							<form:errors path="fromStreet" element="div" />
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="form-group">
						<label class="sr-only">Cena przewozu</label>
						<div class="row">
							<form:input path="maxPrice" min="0" type="number"
								class="form-control" placeholder="*Cena przewozu"
								value="${editGoodForm.maxPrice }" />
						</div>
						<div class="errors">
							<form:errors path="maxPrice" element="div" />
						</div>
					</div>
					<div class="form-group">
						</br> <label>Rodzaj naczepy</label>
						<form:select path="trailer" items="${trailersList}"
							class="form-control" value="${editGoodForm.trailer }" />
						<div class="errors">
							<form:errors path="trailer" element="div" />
						</div>
					</div>
					<label>Termin dostarczenia</label></br>
					<div class="form-group">
						<form:input type='date' path="dateOfDelivery" class="form-control"
							value="${editGoodForm.dateOfDelivery }" />

						<div class="errors">
							<form:errors path="dateOfDelivery" element="div" />
						</div>
					</div>
					<label>Miejsce dostarczenia</label>
					<div class="form-group">
						<label>Kraj</label>
						<form:select path="toCountry" items="${countryList}"
							class="form-control" value="${editGoodForm.toCountry }" />
						<div class="errors">
							<form:errors path="toCountry" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Miasto</label>
						<form:input path="toCity" type="text" class="form-control"
							placeholder="*Miasto..." value="${editGoodForm.toCity }" />
						<div class="errors">
							<form:errors path="toCity" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Ulica</label>
						<form:input path="toStreet" type="text" class="form-control"
							placeholder="*Ulica..." value="${editGoodForm.toStreet }" />
						<div class="errors">
							<form:errors path="toStreet" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label>Wystawiony na aukcje:</label>
						<form:select path="status" items="${activityList}"
							class="form-control" value="${editGoodForm.status }" />
						<div class="errors">
							<form:errors path="status" element="div" />
						</div>
					</div>
					<form:input class="submit btn btn-primary" path="" type="submit"
						value="Edytuj"></form:input>
				</div>

				<div class="col-lg-2"></div>
			</form:form>
		</div>
	</div>
</body>
</html>