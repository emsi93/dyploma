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
<title>Serwis aukcyjny - Szczegóły transakcji</title>
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
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Szczegóły towaru:
						${detailsCargoModel.title }</h1>
				</div>
				<div class="panel-body">
					<c:if test="${not empty wiadomosc}">
						<div class="alert alert-info">
							<center>${wiadomosc}</center>
						</div>
					</c:if>
					<div class="col-lg-4">
						<table class="table table-striped">
							<tbody>
								<tr>
									<td>Nagłówek:</td>
									<td>${detailsCargoModel.title }</td>
								</tr>
								<tr>
									<td>Opis:</td>
									<td>${detailsCargoModel.content }</td>
								</tr>
								<tr>
									<td>Rodzaj naczepy:</td>
									<td>${detailsCargoModel.trailer }</td>
								</tr>
								<tr>
									<td>Miejsce załadunku:</td>
									<td>${detailsCargoModel.fromCountry },
										${detailsCargoModel.fromCity }, ${detailsCargoModel.fromStreet }</td>
								</tr>
								<tr>
									<td>Miejsce rozładunku:</td>
									<td>${detailsCargoModel.toCountry },
										${detailsCargoModel.toCity }, ${detailsCargoModel.toStreet }</td>
								</tr>
								<tr>
									<td>Data dodania:</td>
									<td>${detailsCargoModel.dateAdding }</td>
								</tr>
								<tr>
									<td>Waga towaru(t):</td>
									<td>${detailsCargoModel.weight }</td>
								</tr>
								<tr>
									<td>Termin dostarczenia:</td>
									<td>${detailsCargoModel.dateOfDelivery }</td>
								</tr>
								<tr>
									<td>Termin dostarczenia:</td>
									<td>${detailsCargoModel.deadlineAuction }</td>
								</tr>
								<tr>
									<td>Kwota początkowa:</td>
									<td>${detailsCargoModel.maxPrice }zł</td>
								</tr>
								<tr>
									<td>Kwota transakcji:</td>
									<td>${detailsCargoModel.actualPrice }zł</td>
								</tr>
								<tr>
									<td>Wystawił:</td>
									<td>${detailsCargoModel.name } ${detailsCargoModel.surname}</td>
								</tr>
								<tr>
									<td>Nazwa firmy wystawiającej:</td>
									<td>${detailsCargoModel.company }</td>
								</tr>
								<tr>
									<td>Wylicytował: </td>
									<td>${loginCustomer }</td>
								</tr>
								<tr>
									<td>Data transakcji: </td>
									<td>${dataTransaction}</td>
								</tr>
							</tbody>
						</table>
						<div id="form" style="display: none">

							<form:form method="post" modelAttribute="mailForm"
								action="/auction-system/webapp/detailsTransaction/${detailsCargoModel.id}"
								role="form">
								<div class="form-group">
									<label class="sr-only">Adres email</label>
									<form:input path="mailAddress" type="email" class="form-control"
										placeholder="*Adres email..." />
									<div class="errors">
										<form:errors path="mailAddress" element="div" />
									</div>
									<div class="form-group">
										<form:input path="link" type="hidden" class="form-control"
											value="https://www.google.com/maps/dir/${detailsCargoModel.fromCity },${detailsCargoModel.fromStreet }/${detailsCargoModel.toCity },${detailsCargoModel.toStreet }" />
									</div>
									<form:input class="submit btn btn-primary" path=""
										type="submit" value="Wyślij trasę kierowcy"></form:input>

								</div>
							</form:form>

						</div>
							<button class="btn btn-primary" id="wyslijMail">Wyślij trasę</button>
							<a class="btn btn-primary"
								href="/auction-system/webapp/aboutCompany/${detailsCargoModel.idCompany }">O
								firmie</a>
							<a class="btn btn-primary"
								href="/auction-system/webapp/finishedTransaction">Wstecz</a>
					</div>
					<div class="col-lg-8" id="map"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
$("#wyslijMail").click(function() {
	var button = document.getElementById('wyslijMail');
	var div = document.getElementById('form');
	if (div.style.display !== 'none') {
		div.style.display = 'none';
	} else {
		div.style.display = 'block';
	}
});
function initMap() {

	var directionsDisplay = new google.maps.DirectionsRenderer;
	var directionsService = new google.maps.DirectionsService;
	var map = new google.maps.Map(document.getElementById('map'), {
		zoom : 8,
		center : {
			lat : 52.13,
			lng : 21.00
		}
	});
	var trafficLayer = new google.maps.TrafficLayer();
	trafficLayer.setMap(map);
	directionsDisplay.setMap(map);
	calculateAndDisplayRoute(directionsService, directionsDisplay);
	
	
}

function calculateAndDisplayRoute(directionsService, directionsDisplay) {
	directionsService
			.route(
					{
						origin : "${detailsCargoModel.fromCountry }, ${detailsCargoModel.fromCity }, ${detailsCargoModel.fromStreet }",
						destination : "${detailsCargoModel.toCountry }, ${detailsCargoModel.toCity }, ${detailsCargoModel.toStreet }",
						travelMode : google.maps.TravelMode.DRIVING
					}, function(response, status) {
						if (status == google.maps.DirectionsStatus.OK) {
							directionsDisplay.setDirections(response);
							
						}
					});
}
</script>
<script async defer
src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBQAR809YkzO5lbIQ_dht4OlSFEaznt2T4&callback=initMap">

</script>
<style>
#map {
height: 675px;
width: 65%;
}
</style>
</html>