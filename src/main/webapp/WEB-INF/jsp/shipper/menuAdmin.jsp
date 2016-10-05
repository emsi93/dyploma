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
<title>Serwis aukcyjny - menu</title>
<link href="/auction-system/static/menu/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/auction-system/static/menu/css/sb-admin.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="/auction-system/static/menu/css/plugins/morris.css"
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

<!-- Morris Charts JavaScript -->
<script
	src="/auction-system/static/menu/js/plugins/morris/raphael.min.js"></script>
<script
	src="/auction-system/static/menu/js/plugins/morris/morris.min.js"></script>
<script
	src="/auction-system/static/menu/js/plugins/morris/morris-data.js"></script>




</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-ex1-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="">Serwis aukcyjny</a>
		</div>
		<!-- Top Menu Items -->
		<ul class="nav navbar-right top-nav">



			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown"><i class="fa fa-user"></i> User<b
					class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a class="submit" id="twojProfil1"><i
							class="fa fa-fw fa-user"></i>Edytuj profil</a></li>
					<li><a href="#"><i class="fa fa-fw fa-gear"></i> Wyloguj</a></li>
				</ul></li>
		</ul>
		<!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
		<div class="collapse navbar-collapse navbar-ex1-collapse">
			<ul class="nav navbar-nav side-nav">
				<li><a href="javascript:;" class="submit" id="szukajTowaru"><i
						class="fa fa-search"></i>Szukaj towaru</a></li>
				<li><a href="javascript:;" class="submit"
					id="listaUżytkowników"><i class="fa fa-list-alt"></i>Lista
						użytkowników</a></li>
				<li><a href="javascript:;" class="submit" id="dodajPracownika"><i
						class="fa fa-plus-circle"></i>Nowy pracownik</a></li>
				<li><a href="javascript:;" class="submit" id="twojaFirma"><i
						class="fa fa-pencil-square-o"></i>Twoja firma</a></li>
				<li><a href="javascript:;" type="submit" id="twojProfil"><i
						class="glyphicon glyphicon-user"></i> Twój profil</a></li>
				<li><a href="javascript:;" class="submit"
					id="historiaTransakcji"><i class="fa fa-fw fa-history"></i>Historia
						transakcji</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse --> </nav>

		<div id="page-wrapper">

			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-6">
						<center>
							<div style="display: none" id="viewSzukajTowaru">siema1</div>
							<div style="display: none" id="viewListaUżytkowników">
								<iframe src="employeesList.jsp" name="targetframe"
								allowTransparency="true" scrolling="no" frameborder="0" width="800" height="680">
							</iframe>
							</div>
							<div style="display: none" id="viewDodajPracownika">
								<iframe src="newUser.jsp" name="targetframe"
								allowTransparency="true" scrolling="no" frameborder="0" width="300" height="650">
							</iframe>
							</div>
						</center>
						<div style="display: none" id="viewTwojaFirma">
							<iframe src="editCompany.jsp" name="targetframe"
								allowTransparency="true" scrolling="no" frameborder="0" width="300" height="680">
							</iframe>
						
						</div>

						<div style="display: none" id="viewTwojProfil">
							<iframe src="editProfile.jsp" name="targetframe"
								allowTransparency="true" scrolling="no" frameborder="0" width="300" height="650">
							</iframe>
						</div>
						<div style="display: none" id="viewHistoriaTransakcji">siema6</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->



</body>
<style>
div.content2 {
	position: absolute;
	left: 0;
	bottom: 0;
	right: 0
}
</style>
<script>
	$("#szukajTowaru")
			.click(
					function() {
						document.getElementById("viewSzukajTowaru").style.display = "block";
						document.getElementById("viewListaUżytkowników").style.display = "none";
						document.getElementById("viewDodajPracownika").style.display = "none";
						document.getElementById("viewTwojaFirma").style.display = "none";
						document.getElementById("viewTwojProfil").style.display = "none";
						document.getElementById("viewHistoriaTransakcji").style.display = "none";
					});
	$("#listaUżytkowników")
			.click(
					function() {
						document.getElementById("viewSzukajTowaru").style.display = "none";
						document.getElementById("viewListaUżytkowników").style.display = "block";
						document.getElementById("viewDodajPracownika").style.display = "none";
						document.getElementById("viewTwojaFirma").style.display = "none";
						document.getElementById("viewTwojProfil").style.display = "none";
						document.getElementById("viewHistoriaTransakcji").style.display = "none";
					});
	$("#dodajPracownika")
			.click(
					function() {
						document.getElementById("viewSzukajTowaru").style.display = "none";
						document.getElementById("viewListaUżytkowników").style.display = "none";
						document.getElementById("viewDodajPracownika").style.display = "block";
						document.getElementById("viewTwojaFirma").style.display = "none";
						document.getElementById("viewTwojProfil").style.display = "none";
						document.getElementById("viewHistoriaTransakcji").style.display = "none";
					});
	$("#twojaFirma")
			.click(
					function() {
						document.getElementById("viewSzukajTowaru").style.display = "none";
						document.getElementById("viewListaUżytkowników").style.display = "none";
						document.getElementById("viewDodajPracownika").style.display = "none";
						document.getElementById("viewTwojaFirma").style.display = "block";
						document.getElementById("viewTwojProfil").style.display = "none";
						document.getElementById("viewHistoriaTransakcji").style.display = "none";
					});
	$("#twojProfil")
			.click(
					function() {
						document.getElementById("viewSzukajTowaru").style.display = "none";
						document.getElementById("viewListaUżytkowników").style.display = "none";
						document.getElementById("viewDodajPracownika").style.display = "none";
						document.getElementById("viewTwojaFirma").style.display = "none";
						document.getElementById("viewTwojProfil").style.display = "block";
						document.getElementById("viewHistoriaTransakcji").style.display = "none";
					});
	$("#historiaTransakcji")
			.click(
					function() {
						document.getElementById("viewSzukajTowaru").style.display = "none";
						document.getElementById("viewListaUżytkowników").style.display = "none";
						document.getElementById("viewDodajPracownika").style.display = "none";
						document.getElementById("viewTwojaFirma").style.display = "none";
						document.getElementById("viewTwojProfil").style.display = "none";
						document.getElementById("viewHistoriaTransakcji").style.display = "block";
					});
</script>

</html>
