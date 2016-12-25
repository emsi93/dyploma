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
<title>Serwis aukcyjny - szczegóły ładunku</title>
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
		<%@include file="navbar.jsp" %>
		<div class="row">
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
							<td>Kwota początkowa:</td>
							<td>${detailsCargoModel.maxPrice }zł</td>
						</tr>
						<tr>
							<td>Aktualna kwota:</td>
							<td>${detailsCargoModel.actualPrice }zł</td>
						</tr>
						<tr>
							<td>Autor:</td>
							<td>${detailsCargoModel.name } ${detailsCargoModel.surname}</td>
						</tr>
						<tr>
							<td>Nazwa firmy:</td>
							<td>${detailsCargoModel.company }</td>
						</tr>
					</tbody>
				</table>
				<c:if test="${typeOfCompany=='1' }">
					<a class="btn btn-primary" href="/auction-system/webapp/newCargo">Dodaj
						towar</a>
					<a class="btn btn-primary"
						href="/auction-system/webapp/cargosList">Lista towarów</a>
					<a class="btn btn-primary"
						href="/auction-system/webapp/editCargo/${detailsCargoModel.id }">Edycja</a>
				</c:if>
				<c:if test="${typeOfCompany=='2' }">
					<a class="btn btn-primary"
						href="/auction-system/webapp/searchCargo">Lista towarów</a>
				</c:if>
			</div>
			<div class="col-lg-8" id="map">
			
			</div>
		</div>
	</div>

</body>
<script>

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
		directionsService.route({
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
	height: 550px;
	width: 65%;
}
</style>
</html>