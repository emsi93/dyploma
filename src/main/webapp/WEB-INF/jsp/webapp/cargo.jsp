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

<body background="/auction-system/static/img/background.jpg">
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
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#myModal">Oferty przewozu</button>
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Zamknij</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">Oferty przewozu</h4>
									</div>
									<div class="modal-body">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>Użytkownik</th>
													<th>Kwota</th>
													<th>Data</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${purchaseOffer}" var="offer">
													<tr>
														<td>${offer.login }</td>
														<td>${offer.price }</td>
														<td>${offer.data }</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Zamknij</button>
									</div>
								</div>
							</div>
						</div>
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
									<td>Typ ładunku:</td>
									<td>${detailsCargoModel.typeGood }</td>
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
									<td>Koniec transakcji:</td>
									<td>${detailsCargoModel.deadlineAuction }</td>
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
									<td>${detailsCargoModel.name }${detailsCargoModel.surname}</td>
								</tr>
								<tr>
									<td>Nazwa firmy:</td>
									<td>${detailsCargoModel.company }</td>
								</tr>
							</tbody>
						</table>
						<div id="licytacja" style="display: none">

							<form:form method="post" modelAttribute="priceForm"
								action="/auction-system/webapp/cargo/${detailsCargoModel.id}"
								role="form">
								<div class="form-group">
									<label class="sr-only">Kwota</label>
									<form:input path="price" type="number" class="form-control"
										placeholder="*Kwota..." />
									<div class="errors">
										<form:errors path="price" element="div" />
									</div>
									<div class="form-group">
										<form:input path="actualPrice" type="hidden"
											class="form-control" value="${detailsCargoModel.actualPrice}" />
									</div>
									<div class="form-group">
										<form:input path="maxPrice" type="hidden" class="form-control"
											value="${detailsCargoModel.maxPrice}" />
									</div>
									<div class="form-group">
										<form:input path="id" type="hidden" class="form-control"
											value="${detailsCargoModel.id}" />
									</div>
									<form:input class="submit btn btn-primary" path=""
										type="submit" value="Licytuj"></form:input>

								</div>
							</form:form>

						</div>
						<c:if test="${typeOfCompany=='2' }">
							<button class="btn btn-primary" id="licytacjaButton">Licytuj</button>
							<a class="btn btn-primary"
								href="/auction-system/webapp/searchCargo">Lista towarów</a>
							<a class="btn btn-primary"
								href="/auction-system/webapp/aboutCompany/${detailsCargoModel.idCompany }">O
								firmie</a>
						</c:if>
					</div>
					<div class="col-lg-8" id="map"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$("#licytacjaButton").click(function() {
		var button = document.getElementById('licytacjaButton');
		var div = document.getElementById('licytacja');
		if (div.style.display !== 'none') {
			div.style.display = 'none';
		} else {
			div.style.display = 'block';
		}
		if (button.value == "Licytuj")
			button.value == "Zamknij";
		else
			button.value == "Licytuj";
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
	height: 700px;
	width: 65%;
}
</style>
</html>