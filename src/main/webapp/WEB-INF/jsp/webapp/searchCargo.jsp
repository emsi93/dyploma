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
<body ng-app="myModule" background="/auction-system/static/img/background.jpg">
	<c:set var="role" value="${role}" />
	<c:set var="typeOfCompany" value="${typeOfCompany }" />
	<div class="container">
	<%@include file="navbar.jsp" %>
		<div class="row" ng-controller="myController">
		<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Towary</h3>
					</div>
					<div class="panel-body">
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
					<th><input type="text" ng-model="searchText.title" size="12"/></th>
					<th><input type="text" ng-model="searchText.from" size="12"/></th>
					<th><input type="text" ng-model="searchText.to" size="12"/></th>
					<th><input type="text" ng-model="searchText.dateAdding" size="12"/></th>
					<th><input type="text" ng-model="searchText.dateDelivery" size="12"/></th>
					<th><input type="text" ng-model="searchText.prices" size="12"/></th>
				
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
							href="/auction-system/webapp/cargo/{{good.id }}">Szczegóły</a></td>
					</tr>
			</tbody>
		</table>
		</div>
		</div>
		</div>
		
		
	</div>
	
</body>
<script >
	var datas = ${jsonA};
</script>
</html>