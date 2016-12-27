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
<title>Wykresy</title>
</head>
<link href="/auction-system/static/menu/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="/auction-system/static/menu/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="/auction-system/static/css/errors.css" rel="stylesheet">
<script src="/auction-system/static/menu/js/jquery.js"></script>
<script src="/auction-system/static/menu/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<body background="/auction-system/static/img/background.jpg">
<c:set var="role" value="${role}" />
	<c:set var="typeOfCompany" value="${typeOfCompany }" />
	<div class="container">
		
		<%@include file="navbar.jsp" %>
		
		<div class="row">
			<div class="col-lg-2"></div>
			<div class="col-lg-8">
				<div class="panel panel-default">
				<div class="panel-heading">
					<h1 class="panel-title">Wykres</h1>
				</div>
				<div class="panel-body">
				<div id="barchart_values" style="width: 900px; height: 400px;"></div></div>
			</div>
			<div class="col-lg-2"></div>
			</div>
		</div>
		</div>



	<script type="text/javascript">
		google.charts.load("current", {
			packages : [ "corechart" ]
		});
		var zmienna = ${goodData};
		var tablica = [ [ "Produkt", "Aktualna cena", {
			role : "style"
		} ] ];
		var size = ${size};
		for (i = 0; i < size; i++) {
			element = [ zmienna.GoodList[i].name,
					zmienna.GoodList[i].actualPrice, "color: #e5e4e2" ];
			tablica.push(element);
		}
		google.charts.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = google.visualization.arrayToDataTable(tablica);

			var view = new google.visualization.DataView(data);
			view.setColumns([ 0, 1, {
				calc : "stringify",
				sourceColumn : 1,
				type : "string",
				role : "annotation"
			}, 2 ]);

			var options = {
				title : "Zestawienie towarów",
				width : 600,
				height : 400,
				bar : {
					groupWidth : "95%"
				},
				legend : {
					position : "none"
				},
			};
			var chart = new google.visualization.BarChart(document
					.getElementById("barchart_values"));
			chart.draw(view, options);
		}
	</script>
</body>
</html>