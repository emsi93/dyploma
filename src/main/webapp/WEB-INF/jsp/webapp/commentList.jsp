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
<title>Serwis aukcyjny - Komentarze</title>
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
					<h1 class="panel-title">Komentarze</h1>
				</div>
				<div class="panel-body">
					<label>Skala ocen: 1 - najgorsza ocena, 5 - najlepsza ocena</label>
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Komentarz</th>
								<th>Wystawiający</th>
								<th>ID towaru</th>
								<th>Nazwa towaru</th>
								<th>Ocena</th>
								<th>Data</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${commentsWithNotes }" var="commentWithNote" varStatus="loop">
						<tr>
							
							
								<td>${commentWithNote.comment }</td>
								<td>${commentWithNote.login }</td>
								<td>${commentWithNote.idGood }</td>
								<td>${commentWithNote.good }</td>
								<td>${commentWithNote.note }</td>
								<td>${commentWithNote.data }</td>
							
							</tr>
							</c:forEach>
						</tbody>
					</table>
					Średnia ocen firmy: <label>${note }</label>
				</div>
			</div>
		</div>
	</div>

</body>
</html>