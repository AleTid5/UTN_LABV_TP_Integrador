<%@tag description="Unauthorized Layout" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="scripts" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="/App/Assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>UTN | FRGP</title>
	<meta name="description" content="Sistema de alumnos y profesores">
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
	<link href="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/css/app.min.css" rel="stylesheet" />
	<link href="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/css/app.css" rel="stylesheet" />
</head>
<body class="off-canvas-sidebar">
<div id="app" class="wrapper wrapper-full-page">
	<div class="page-header login-page header-filter" filter-color="black"
		 style="background-image: url('http://localhost:8085/TP_L5_GRUPO_1/App/Assets/img/login.jpg'); background-size: cover; background-position: top center;">
		<div class="container">
			<c:forEach var="message" items="${ messages }">
				<div class="alert alert-${ message.getClassName() }">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close">
						<i class="material-icons">close</i>
					</button>
					<span>
						<b>${ message.getTitle() }</b><br>
						${ message.getBody() }
					</span>
				</div>
			</c:forEach>
			<div class="row">
				<div class="col-lg-4 col-md-6 col-sm-8 ml-auto mr-auto">
					<jsp:doBody />
				</div>
			</div>
		</div>
	</div>
</div>
<script src="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/js/core/jquery.min.js"></script>
<script src="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/js/core/popper.min.js"></script>
<script src="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/js/core/bootstrap-material-design.min.js"></script>
<script src="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/js/app.min.js"></script>
<script src="http://localhost:8085/TP_L5_GRUPO_1/App/Assets/js/components/login.js"></script>
</body>
</html>