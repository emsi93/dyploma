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
<title>Serwis aukcyjny - Wystaw ocenę i komentarz</title>
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
			<div class="col-lg-3"></div>
			<div class="col-lg-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h1 class="panel-title">Wystawianie oceny i komentarza firmie: ${companyName }</h1>
						<h6>*Pola wymagane</h6>
					</div>
					<div class="panel-body">
						<c:if test="${not empty wiadomosc}">
							<div class="alert alert-info">
								<center>${wiadomosc}</center>
							</div>
						</c:if>
						<label>Skala ocen: 1 - najgorsza ocena, 5 - najlepsza ocena</label>
						<form:form method="post" modelAttribute="noteCommentForm"
							action="/auction-system/webapp/newNote/${goodID }" role="form">
							<div class="form-group">
								<label>*Oceny</label>
								<form:select path="note" items="${notes}" class="form-control" />
								<div class="errors">
									<form:errors path="note" element="div" />
								</div>
							</div>
							<div class="form-group">
								<label>*Treść komentarza:</label>
								<form:textarea path="comment" type="text" class="form-control"
									placeholder="Treść komentarza..."  rows="5" cols="50" />
								<div class="errors">
									<form:errors path="comment" element="div" />
								</div>
							</div>
							<form:input class="submit btn btn-primary" path="" type="submit"
						value="Wyślij komentarz"></form:input>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-lg-3"></div>
		</div>
	</div>

</body>
</html>