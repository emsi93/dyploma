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
<title>Logowanie</title>
<link href="/auction-system/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="/auction-system/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="controller">
		<div class="row">
			<div class="col-lg-4"></div>
			<div class="col-lg-4">
		
				 <div class="form-group">
						<label class="sr-only">Login</label>
						<input  type="text" class="form-control"
							placeholder="*Login..." />
						
					</div>
					 <div class="form-group">
						<label class="sr-only">Hasło</label>
					<input  type="password" class="form-control"
							placeholder="*Hasło..." />
					</div>
					<input class="submit btn btn-primary"  type="submit"
					value="Zaloguj">
		
			</div>
			<div class="col-lg-4"></div>
		</div>
	</div>
</body>
</html>