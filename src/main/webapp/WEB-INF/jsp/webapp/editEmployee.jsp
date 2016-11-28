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
<title>Serwis aukcyjny - edycja pracownika</title>
<link href="/auction-system/static/menu/css/bootstrap.min.css"
	rel="stylesheet">



<!-- Custom Fonts -->
<link
	href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- jQuery -->
<script src="/auction-system/static/menu/js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="/auction-system/static/menu/js/bootstrap.min.js"></script>

</head>
<body>
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
				<a class="navbar-brand" href="/auction-system/webapp/menu">System aukcyjny towarów</a>
			</div>

			<!-- Grupowanie elementów menu w celu lepszego wyświetlania na urządzeniach moblinych -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
										<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Menu<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<c:if test="${ role=='ROLE_ADMIN_CARRIER' || role=='ROLE_USER_CARRIER'}">
								<li><a href="/auction-system/webapp/searchCargo">Szukaj
										towaru</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_USER_SHIPPER'}">
								<li><a href="/auction-system/webapp/newCargo">Dodaj
										towar</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_USER_SHIPPER'}">
								<li><a href="/auction-system/webapp/cargosList">Lista
										towarów</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
								<li><a href="/auction-system/webapp/employeesList">Lista
										użytkowników</a></li>
								<li class="divider"></li>
							</c:if>

							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
									<li><a href="/auction-system/webapp/newUser">Nowy
										pracownik</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
									<li><a href="/auction-system/webapp/editCompany">Twoja
										firma</a></li>
								<li class="divider"></li>
							</c:if>
							<li><a href="/auction-system/webapp/editProfile">Twój
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
							<li><a href="/auction-system/webapp/editProfile">Edytuj profil</a></li>
							<li><a href="<c:url value="/j_spring_security_logout" />">Wyloguj
									się</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		</nav>
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
				${wiadomosc }
				<h2>Edycja profilu</h2>
				<form:form method="post" modelAttribute="employeeForm"
					action="/auction-system/webapp/editEmployee/${employeeForm.id }"
					role="form">
					<div class="form-group">
						<label class="sr-only">Imie</label>
						<form:input path="name" type="text" class="form-control"
							placeholder="*Imię..." value="${employeeForm.name }" />
						<div class="errors">
							<form:errors path="name" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Nazwisko</label>
						<form:input path="surname" type="text" class="form-control"
							placeholder="*Nazwisko..." value="${employeeForm.surname }" />
						<div class="errors">
							<form:errors path="surname" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Login</label>
						<form:input path="login" type="text" class="form-control"
							placeholder="*Login..." value="${employeeForm.login }" />
						<div class="errors">
							<form:errors path="login" element="div" />
						</div>
					</div>

					<div class="form-group">
						<label class="sr-only">Aktywność</label>
						<form:select items="${activityList }" path="activity" type="text"
							class="form-control" placeholder="*Aktywność..."
							value="${employeeForm.activity }" />
						<div class="errors">
							<form:errors path="activity" element="div" />
						</div>
					</div>

					<div class="form-group">
						<label class="sr-only">Rola</label>
						<form:select path="role" type="text" class="form-control"
							placeholder="*Role..." items="${rolesList }"
							value="${employeeForm.role }" />
						<div class="errors">
							<form:errors path="role" element="div" />
						</div>
					</div>

					<div class="form-group">
						<label class="sr-only">Numer telefonu</label>
						<form:input path="phoneNumber" type="text" class="form-control"
							placeholder="*Numer telefonu..."
							value="${employeeForm.phoneNumber }" />
						<div class="errors">
							<form:errors path="phoneNumber" element="div" />
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only">Email</label>
						<form:input path="email" type="email" class="form-control"
							placeholder="*Email..." value="${employeeForm.email }" />
						<div class="errors">
							<form:errors path="email" element="div" />
						</div>
					</div>
					<form:input class="submit btn btn-primary" path="" type="submit"
						value="Edytuj profil"></form:input>
					<a class="btn btn-primary"
						href="/auction-system/webapp/employeesList">Lista pracowników</a>
				</form:form>
			</div>
			<div class="col-lg-4"></div>


		</div>
	</div>
</body>
</html>