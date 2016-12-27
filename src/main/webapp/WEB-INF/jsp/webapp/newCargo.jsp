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
<title>System aukcyjny - dodawanie towaru</title>
<link href="/auction-system/static/menu/css/bootstrap.css"
	rel="stylesheet">
<link
	href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">
<script src="/auction-system/static/menu/js/jquery.js"></script>
<script src="/auction-system/static/menu/js/bootstrap.js"></script>
</head>
<body background="/auction-system/static/img/background.jpg">
	<c:set var="role" value="${role}" />
	<c:set var="typeOfCompany" value="${typeOfCompany }" />
	<div class="container">
		<%@include file="navbar.jsp"%>
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Nowy towar</h1>
					<h6>*Pola wymagane</h6>
				</div>
				<div class="panel-body">
					<form:form method="post" modelAttribute="goodForm"
						action="/auction-system/webapp/newCargo/${editGoodForm.id }"
						role="form">
						<div class="row">
							<div class="col-lg-2"></div>
							<div class="col-lg-8">
								<c:if test="${not empty wiadomosc}">
									<div class="alert alert-info"><center>${wiadomosc}</center></div>
								</c:if>
							</div>
							<div class="col-lg-2"></div>
						</div>
						<div class="row">
						<div class="col-lg-2"></div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="sr-only">Nagłówek</label>
								<form:input path="title" type="text" class="form-control"
									placeholder="*Nagłówek..." />
								<div class="errors">
									<form:errors path="title" element="div" />
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only">Opis</label>
								<form:textarea rows="4" path="content" type="text"
									class="form-control" placeholder="Opis..." />
								<div class="errors">
									<form:errors path="content" element="div" />
								</div>
							</div>
							<label>Miejsce załadunku</label>
							<div class="form-group">
								<label>Kraj</label>
								<form:select path="fromCountry" items="${countryList}"
									class="form-control" />
								<div class="errors">
									<form:errors path="fromCountry" element="div" />
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only">Miasto</label>
								<form:input path="fromCity" type="text" class="form-control"
									placeholder="*Miasto..." />
								<div class="errors">
									<form:errors path="fromCity" element="div" />
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only">Ulica</label>
								<form:input path="fromStreet" type="text" class="form-control"
									placeholder="*Ulica..." />
								<div class="errors">
									<form:errors path="fromStreet" element="div" />
								</div>
							</div>
						</div>
						<div class="col-lg-4">
							<div class="form-group">
								<label class="sr-only">Cena przewozu</label>
								<div class="row">
									<form:input path="maxPrice" min="0" type="number"
										class="form-control" placeholder="*Cena przewozu" />
								</div>
								<div class="errors">
									<form:errors path="maxPrice" element="div" />
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only">Waga towaru (t)</label>
								<div class="row">
									<form:input path="weight" min="0" type="number"
										class="form-control" placeholder="*Waga towaru" />
								</div>
								<div class="errors">
									<form:errors path="weight" element="div" />
								</div>
							</div>
							<div class="form-group">
								</br> <label>Rodzaj naczepy</label>
								<form:select path="trailer" items="${trailersList}"
									class="form-control" />
								<div class="errors">
									<form:errors path="trailer" element="div" />
								</div>
							</div>
							<label>Termin dostarczenia</label></br>
							<div class="form-group">
								<form:input type='date' path="dateOfDelivery"
									class="form-control" />

								<div class="errors">
									<form:errors path="dateOfDelivery" element="div" />
								</div>
							</div>
							<label>Termin licytacji</label>
							<div class="form-group">
								<form:input type='date' path="deadlineAuction"
									class="form-control" />

								<div class="errors">
									<form:errors path="deadlineAuction" element="div" />
								</div>
							</div>
							<label>Miejsce dostarczenia</label>
							<div class="form-group">
								<label>Kraj</label>
								<form:select path="toCountry" items="${countryList}"
									class="form-control" />
								<div class="errors">
									<form:errors path="toCountry" element="div" />
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only">Miasto</label>
								<form:input path="toCity" type="text" class="form-control"
									placeholder="*Miasto..." />
								<div class="errors">
									<form:errors path="toCity" element="div" />
								</div>
							</div>
							<div class="form-group">
								<label class="sr-only">Ulica</label>
								<form:input path="toStreet" type="text" class="form-control"
									placeholder="*Ulica..." />
								<div class="errors">
									<form:errors path="toStreet" element="div" />
								</div>
							</div>
							<form:input class="submit btn btn-primary" path="" type="submit"
								value="Dodaj"></form:input>
						</div>

						<div class="col-lg-2"></div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>