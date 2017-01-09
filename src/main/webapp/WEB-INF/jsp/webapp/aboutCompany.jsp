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
<title>Serwis aukcyjny - o firmie</title>
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
						<h1 class="panel-title">O firmie</h1>

					</div>
					<div class="panel-body">
						<table class="table table-striped">
							<tbody>
								<tr>
									<td>Nazwa firmy:</td>
									<td>${companyModel.companyName }</td>
								</tr>
								<tr>
									<td>Kraj:</td>
									<td>${companyModel.country }</td>
								</tr>
								<tr>
									<td>Kod pocztowy:</td>
									<td>${companyModel.postcode }</td>
								</tr>
								<tr>
									<td>Miasto:</td>
									<td>${companyModel.city }</td>
								</tr>
								<tr>
									<td>Ulica:</td>
									<td>${companyModel.street }</td>
								</tr>
								<tr>
									<td>Numer domu:</td>
									<td>${companyModel.flatNumber }</td>
								</tr>
								<tr>
									<td>Numer NIP:</td>
									<td>${companyModel.nipNumber }</td>
								</tr>
								<tr>
									<td>Numer telefonu:</td>
									<td>${companyModel.phoneNumber }</td>
								</tr>
								<tr>
									<td>Strona internetowa:</td>
									<td>${companyModel.website }</td>
								</tr>
								<tr>
									<td>E-mail:</td>
									<td>${companyModel.email }</td>
								</tr>
								<tr>
									<td>Opis firmy:</td>
									<td>${companyModel.description }</td>
								</tr>
								<tr>
									<td>Ocena firmy:</td>
									<td>${companyModel.note }</td>
								</tr>
							</tbody>
						</table>
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#myModal">Komentarze</button>
						<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Zamknij</span>
										</button>
										<h4 class="modal-title" id="myModalLabel">Komentarze
											firmy: ${companyModel.companyName }</h4>
									</div>
									<div class="modal-body">
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
												<c:forEach items="${commentsWithNotes }"
													var="commentWithNote" varStatus="loop">
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
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Zamknij</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3"></div>
			</div>
		</div>
	</div>

</body>
</html>