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
<link
	href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">
<script src="/auction-system/static/menu/js/jquery.js"></script>
<script src="/auction-system/static/menu/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.min.js"></script>
<script src="/auction-system/static/angular/filterModule.js"></script>
</head>
<body ng-app="myModule" background="/auction-system/static/img/background.jpg">
	<c:set var="role" value="${role}" />
	<c:set var="typeOfCompany" value="${typeOfCompany }" />
	<div class="container">
		<%@include file="navbar.jsp" %>
		<div class="row" ng-controller="myController">
		<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Lista pracowników</h1>
				</div>
				<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>LP.</th>
						<th ng-click="sortData('name')">Imię</th>
						<th ng-click="sortData('surname')">Nazwisko</th>
						<th ng-click="sortData('phoneNumber')">Numer telefonu</th>
						<th ng-click="sortData('email')">Email</th>
						<th>Edycja</th>
						<th>Szczegóły</th>
					</tr>
					<tr>
						<th></th>
						<th><input type="text" ng-model="searchText.name" size="15" /></th>
						<th><input type="text" ng-model="searchText.surname" size="15" /></th>
						<th><input type="text" ng-model="searchText.phoneNumber" size="15" /></th>
						<th><input type="text" ng-model="searchText.email" size="15" /></th>
					</tr>
				</thead>
				<tbody>
				
						<tr ng-repeat="employee in data | filter:searchText | orderBy:sortColumn:reverseSort">
							<td>{{$index+1}}</td>
							<td>{{employee.name }}</td>
							<td>{{employee.surname }}</td>
							<td>{{employee.phoneNumber }}</td>
							<td>{{employee.email }}</td>
							<td><a class="btn btn-primary"
								href="/auction-system/webapp/editEmployee/{{employee.id }}">Edytuj</a></td>
							<td><a class="btn btn-primary"
								href="/auction-system/webapp/detailsEmployee/{{employee.id }}">Szczegóły</a></td>
						</tr>
				</tbody>
			</table>
			<a class="btn btn-primary" href="newUser">Dodaj pracownika</a>
			</div>
		</div>
		</div>
	</div>
</body>
<script>
	var datas = ${jsonA	};
</script>
</html>