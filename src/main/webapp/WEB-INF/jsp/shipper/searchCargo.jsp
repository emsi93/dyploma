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
<title>Serwis aukcyjny - szukaj towaru</title>
<link href="/auction-system/static/menu/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">
<script src="/auction-system/static/menu/js/jquery.js"></script>
<script src="/auction-system/static/menu/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<script src="/auction-system/static/angular/filterModule.js"></script>
</head>
<body ng-app="myModule">
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
							<c:choose>
								<c:when test="${ role=='ROLE_ADMIN'}">
									<li><a href="employeesList">Lista użytkowników</a></li>
									<li class="divider"></li>
								</c:when>
							</c:choose>
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
		<div class="row" ng-controller="myController">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>LP.</th>
					<th ng-click="sortData('title')">Nagłówek</th>
					<th ng-click="sortData('from')">Miejsce załadunku</th>
					<th ng-click="sortData('to')">Miejsce rozładunku</th>
					<th ng-click="sortData('dateAdding')">Data dodania</th>
					<th ng-click="sortData('dateDelivery')">Termin dostarczenia</th>
					<th ng-click="sortData('prices')">Cena początkowa/Cena aktualna</th>
					<th>Szczegóły</th>
				</tr>
				<tr>
				
					<th></th>
					<th><input type="text" ng-model="searchText.title" size="15"/></th>
					<th><input type="text" ng-model="searchText.from" size="15"/></th>
					<th><input type="text" ng-model="searchText.to" size="15"/></th>
					<th><input type="text" ng-model="searchText.dateAdding" size="15"/></th>
					<th><input type="text" ng-model="searchText.dateDelivery" size="15"/></th>
					<th><input type="text" ng-model="searchText.prices" size="15"/></th>
				
				</tr>
			</thead>
			<tbody>
					<tr ng-repeat="good in data | filter:searchText | orderBy:sortColumn:reverseSort">
						<td>{{$index+1 }}</td>
						<td>{{good.title }}</td>
						<td>{{good.from }}</td>
						<td>{{good.to }}</td>
						<td>{{good.dateAdding | date:"dd.MM.yyyy" }}</td>
						<td>{{good.dateDelivery | date:"dd.MM.yyyy"}}</td>
						<td>{{good.prices }}</td>
						<td><a class="btn btn-primary"
							href="/auction-system/shipper/cargo/{{good.id }}">Szczegóły</a></td>
					</tr>
			</tbody>
		</table>
		</div>
		
		
	</div>
	
</body>
<script >
	var datas = ${jsonA};
</script>
</html>