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
<title>Serwis aukcyjny - lista pracowników</title>
<link href="/auction-system/static/menu/css/bootstrap.min.css"
	rel="stylesheet">



<!-- Custom Fonts -->
<link
	href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="/auction-system/static/menu/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
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
				<a class="navbar-brand" href="menu">System aukcyjny towarów</a>
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
									<li><a href="newCargo">Dodaj towar</a></li>
									<li class="divider"></li>
								</c:if>
							</c:if>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="newUser">Nowy pracownik</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="editCompany">Twoja firma</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
							<li><a href="editProfile">Twój profil</a></li>
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
			<table class="table table-striped">
				<thead>
					<tr>
						<th>LP.</th>
						<th>Imię</th>
						<th>Nazwisko</th>
						<th>Numer telefonu</th>
						<th>Email</th>
						<th>Edycja</th>
						<th>Szczegóły</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${employeesList}" var="employee" varStatus="loop">
						<tr>
							<td>${loop.count}</td>
							<td>${employee.name }</td>
							<td>${employee.surname }</td>
							<td>${employee.phoneNumber }</td>
							<td>${employee.email }</td>
							<td><a class="btn btn-primary" href="/auction-system/shipper/editEmployee/${employee.id }">Edytuj</a></td>
							<td><a class="btn btn-primary" href="/auction-system/shipper/detailsEmployee/${employee.id }">Szczegóły</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<a class="btn btn-primary" href="newUser">Dodaj pracownika</a></td>
		</div>
	</div>
</body>
</html>