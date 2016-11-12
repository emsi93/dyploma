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
						<td>Termin dostarczenia:</td>
						<td>${detailsCargoModel.dateOfDelivery }</td>
					</tr>
					<tr>
						<td>Kwota początkowa:</td>
						<td>${detailsCargoModel.maxPrice } zł</td>
					</tr>
					<tr>
						<td>Aktualna kwota:</td>
						<td>${detailsCargoModel.actualPrice } zł</td>
					</tr>
					<tr>
						<td>Autor:</td>
						<td>${detailsCargoModel.name }
					${detailsCargoModel.surname}</td>
					</tr>
					<tr>
						<td>Nazwa firmy:</td>
						<td>${detailsCargoModel.company }</td>
					</tr>
				</tbody>
			</table>
				<div id="licytacja" style="display: none">
				${wiadomosc }
				<form:form method="post" modelAttribute="priceForm"
					action="/auction-system/shipper/cargo/${detailsCargoModel.id}"
					role="form">
					<div class="form-group">
						<label class="sr-only">Kwota</label>
						<form:input path="price" type="number" class="form-control"
							placeholder="*Kwota..." />
						<div class="errors">
							<form:errors path="price" element="div" />
						</div>
						<div class="form-group">
							<form:input path="actualPrice" type="hidden" class="form-control"
								value="${detailsCargoModel.actualPrice}" />
						</div>
						<div class="form-group">
							<form:input path="maxPrice" type="hidden" class="form-control"
								value="${detailsCargoModel.maxPrice}" />
						</div>
						<form:input class="submit btn btn-primary" path="" type="submit"
							value="Licytuj"></form:input>
					</div>
				</form:form>
				</div>
				<button class="btn btn-primary" id="licytacjaButton">Licytuj</button>
				<c:if test="${typeOfCompany=='2' }">
					<a class="btn btn-primary"
						href="/auction-system/shipper/searchCargo">Lista towarów</a>
					<a class="btn btn-primary"
						href="/auction-system/shipper/aboutCompany/${detailsCargoModel.idCompany }">O firmie</a>
				</c:if>
			</div>
			<div class="col-lg-8" id="map">
			
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
          zoom: 8,
          center: {lat:52.13, lng: 21.00}
        });
		var trafficLayer = new google.maps.TrafficLayer();
		trafficLayer.setMap(map);
        directionsDisplay.setMap(map);
		calculateAndDisplayRoute(directionsService, directionsDisplay);
        
      }

      function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
           origin: "Warszawa",
          destination: "Łódź, Ofiarna",
          travelMode: google.maps.TravelMode.DRIVING
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