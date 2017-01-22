<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false" isELIgnored="false"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
				<a class="navbar-brand" href="/auction-system/webapp/menu">System obsługi przewozu towaru</a>
			</div>

			<!-- Grupowanie elementów menu w celu lepszego wyświetlania na urządzeniach moblinych -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">Menu<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<c:if
								test="${ role=='ROLE_ADMIN_CARRIER' || role=='ROLE_USER_CARRIER'}">
								<li><a href="/auction-system/webapp/searchCargo">Szukaj
										towaru</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if
								test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_USER_SHIPPER'}">
								<li><a href="/auction-system/webapp/newCargo">Dodaj
										towar</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if
								test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_USER_SHIPPER'}">
								<li><a href="/auction-system/webapp/cargosList">Lista
										towarów</a></li>
								<li class="divider"></li>
							</c:if>
							<!--<c:if
								test="${ role=='ROLE_ADMIN_SHIPPER'}">
								<li><a href="/auction-system/webapp/charts">Raport</a></li>
								<li class="divider"></li>
							</c:if>-->
							<c:if
								test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
								<li><a href="/auction-system/webapp/employeesList">Lista
										użytkowników</a></li>
								<li class="divider"></li>
							</c:if>

							<c:if
								test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
								<li><a href="/auction-system/webapp/newUser">Nowy
										pracownik</a></li>
								<li class="divider"></li>
							</c:if>
							<c:if
								test="${ role=='ROLE_ADMIN_SHIPPER' || role=='ROLE_ADMIN_CARRIER'}">
								<li><a href="/auction-system/webapp/editCompany">Twoja
										firma</a></li>
								<li class="divider"></li>
							</c:if>
							<li><a href="/auction-system/webapp/commentList">Komentarze firmy</a></li>
							<li class="divider"></li>
							<li><a href="/auction-system/webapp/editProfile">Twój
									profil</a></li>
							<li class="divider"></li>
							<li><a href="/auction-system/webapp/finishedTransaction">Zakończone transakcje</a></li>
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
							<li><a href="editProfile">Edytuj profil</a></li>
							<li><a href="<c:url value="/j_spring_security_logout" />">Wyloguj
									się</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
		</nav>