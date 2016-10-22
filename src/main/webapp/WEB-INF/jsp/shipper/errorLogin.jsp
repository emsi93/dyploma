<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Serwis aukcyjny - błąd logowania</title>
<link href="/auction-system/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/auction-system/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body background="/auction-system/static/img/background.jpg">

	<div class="container">
		<div class="row">
			</br>
			</br>
			</br>
			</br>
			</br>
			</br>
			</br>
			</br>
			</br>
			<br>
			<nav id="mainNav" class="navbar navbar-inverse navbar-fixed-top">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header">
						<button type="button" class="navbar-toggle collapsed"
							data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span> Menu <i
								class="fa fa-bars"></i>
						</button>
						<a class="navbar-brand page-scroll" href="index">System
							aukcyjny towarów</a>
					</div>
				</div>
			</nav>
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Błąd logowania</h3>
					</div>
					<div class="panel-body">
						<h4>
							Podałeś niepoprawny login lub hasło.
							<h4>
								</br> <a class="btn btn-lg btn-primary btn-block" type="submit"
									href="login">Zaloguj jeszcze raz</a>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>