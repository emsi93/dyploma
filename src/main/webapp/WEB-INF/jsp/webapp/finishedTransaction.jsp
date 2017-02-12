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
<title>Serwis aukcyjny - Zakończone transakcje</title>
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
		<%@include file="navbar.jsp"%>
		<div class="row" ng-controller="myController">
		<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Zakończone transakcje</h3>
					</div>
					<div class="panel-body">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>LP.</th>
					<th ng-click="sortData('title')">Nagłówek</th>
					<th ng-click="sortData('login')">Login</th>
					<th ng-click="sortData('to')">Miejsce rozładunku</th>
					<th ng-click="sortData('price')">Kwota(zł)</th>
					<th ng-click="sortData('dateTransaction')">Data transakcji</th>
					<th>Szczegóły</th>
					<th>Wystaw ocenę</th>
				</tr>
				<tr>
				
					<th></th>
					<th><input type="text" ng-model="searchText.title" size="12"/></th>
					<th><input type="text" ng-model="searchText.login" size="12"/></th>
					<th><input type="text" ng-model="searchText.to" size="12"/></th>
					<th><input type="text" ng-model="searchText.price" size="12"/></th>
					<th><input type="text" ng-model="searchText.dateTransaction" size="12"/></th>				
				</tr>
			</thead>
			<tbody>
					<tr ng-repeat="good in data | filter:searchText | orderBy:sortColumn:reverseSort">
						<td>{{$index+1 }}</td>
						<td>{{good.title }}</td>
						<td>{{good.login }}</td>
						<td>{{good.to }}</td>
						<td>{{good.price }}</td>
						<td>{{good.dateTransaction}}</td>
						<td><c:if test="${typeOfCompany==2}"><a class="btn btn-primary"
							href="/auction-system/webapp/detailsTransaction/{{good.idGood }}">Szczegóły</a></c:if>
							<c:if test="${typeOfCompany==1}"><a class="btn btn-primary"
							href="/auction-system/webapp/detailsTransaction2/{{good.idGood }}">Szczegóły</a></c:if></td>
						<td>
						<a class="btn btn-primary"
							href="/auction-system/webapp/newNote/{{good.idGood }}">Wystaw ocenę</a>
						</td>
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
	console.log(datas);
</script>
</html>