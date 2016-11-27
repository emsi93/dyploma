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
<title>Serwis aukcyjny - menu</title>
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
				<a class="navbar-brand" href="/auction-system/shipper/menu">System aukcyjny towarów</a>
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
									<li><a href="/auction-system/shipper/searchCargo">Szukaj towaru</a></li>
									<li class="divider"></li>
								</c:if>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN' || role=='ROLE_USER'}">
								<c:if test="${typeOfCompany=='1' }">
									<li><a href="/auction-system/shipper/newCargo">Dodaj towar</a></li>
									<li class="divider"></li>
								</c:if>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN' || role=='ROLE_USER'}">
								<c:if test="${typeOfCompany=='1' }">
									<li><a href="/auction-system/shipper/cargosList">Lista towarów</a></li>
									<li class="divider"></li>
								</c:if>
							</c:if>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="/auction-system/shipper/employeesList">Lista użytkowników</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="/auction-system/shipper/newUser">Nowy pracownik</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="/auction-system/shipper/editCompany">Twoja firma</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<li><a href="/auction-system/shipper/editProfile">Twój profil</a></li>
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
		<div class="col-lg-2"></div>
		<div class="col-lg-8">	
			<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">${username }</h3>
					</div>
					<div class="panel-body"><center><h3>Witamy w systemie aukcyjnym przewozu towarów</h3></center></div>
				</div>
		</div>
		<div class="col-lg-2"></div>
		</div>
	</div>
</body>
</html>
