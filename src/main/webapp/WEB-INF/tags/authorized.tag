<%@ tag import="UTN.FRGP.TP_L5_GRUPO_1.Enums.ErrorCodeEnum" %>
<%@ tag import="UTN.FRGP.TP_L5_GRUPO_1.Enums.SuccessCodeEnum" %>
<%@ tag import="UTN.FRGP.TP_L5_GRUPO_1.Factories.ErrorCodeFactory" %>
<%@ tag import="UTN.FRGP.TP_L5_GRUPO_1.Factories.SuccessCodeFactory" %>
<%@ tag import="UTN.FRGP.TP_L5_GRUPO_1.Factories.NotificationFactory" %>
<%@ tag import="UTN.FRGP.TP_L5_GRUPO_1.Enums.NotificationEnum" %>
<%@ tag import="java.util.Arrays" %>
<%@tag description="Authorized Layout" pageEncoding="UTF-8"%>
<%@attribute name="head" fragment="true"%>
<%@attribute name="scripts" fragment="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	try {
		if (request.getParameter("errorCode") != null)
			request.setAttribute("errorMessage", ErrorCodeFactory.getDescription(ErrorCodeEnum.valueOf(request.getParameter("errorCode"))));

		if (request.getParameter("successCode") != null)
			request.setAttribute("successMessage", SuccessCodeFactory.getDescription(SuccessCodeEnum.valueOf(request.getParameter("successCode"))));

		if (request.getParameter("notifications") != null)
			request.setAttribute("notifications", Arrays.asList(request.getParameter("notifications").split("\\s*,\\s*")));
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<html>
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="/App/Assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="<c:url value="/assets/img/favicon.png" />">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title>UTN | FRGP</title>
	<meta name="description" content="Sistema de alumnos y profesores">
	<link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
	<link href="<c:url value="/assets/css/app.css" />" rel="stylesheet" />
	<link href="<c:url value="/assets/css/app.min.css" />" rel="stylesheet" />
	<link href="<c:url value="/assets/css/chartist.min.css" />" rel="stylesheet" />
</head>
<body>
<div id="app" class="wrapper">
	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<i class="material-icons">close</i>
			</button>
			<span>
				<b>Ups.. 🤦‍️</b>
				<br>
					${ errorMessage }
			</span>
		</div>
	</c:if>
	<c:if test="${successMessage != null}">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<i class="material-icons">close</i>
			</button>
			<span>
				<b>En hora buena! 🎉</b>
				<br>
					${ successMessage }
			</span>
		</div>
	</c:if>
	<c:forEach var="notification" items="${ notifications }" varStatus="loop">
		<div class="alert alert-${ notification.equals(NotificationEnum.LOAN_APPROVED.name()) ? "success" : "danger" }"
			 style="margin-top: ${loop.index * 120}px">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<i class="material-icons">close</i>
			</button>
			<span>
				<b>${ notification.equals(NotificationEnum.LOAN_APPROVED.name()) ? "En hora buena! 🎉" : "Ups..." }</b>
				<br>
					${ NotificationFactory.getDescription(NotificationEnum.valueOf(notification)) }
			</span>
		</div>
	</c:forEach>
	<jsp:include page="../Components/sidebar.jsp" />
	<div class="main-panel">
		<jsp:include page="../Components/navbar.jsp" />
		<div class="content">
			<div class="content">
				<div class="container-fluid">
					<jsp:doBody />
				</div>
			</div>
		</div>
	</div>
</div>
<script src="<c:url value="/assets/js/core/jquery.min.js" />"></script>
<script src="<c:url value="/assets/js/core/popper.min.js" />"></script>
<script src="<c:url value="/assets/js/core/bootstrap-material-design.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/perfect-scrollbar.jquery.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/moment.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/sweetalert2.js" />"></script>
<script src="<c:url value="/assets/js/plugins/bootstrap-selectpicker.js" />"></script>
<script src="<c:url value="/assets/js/plugins/bootstrap-datetimepicker.min.js" />"></script>
<script src="<c:url value="/assets/js/plugins/fullcalendar.min.js" />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js"></script>
<script src="<c:url value="/assets/js/plugins/bootstrap-notify.js" />"></script>
<script src="<c:url value="/assets/js/app.min.js" />"></script>
<script src="<c:url value="/assets/js/components/sidebar.js" />"></script>
<script src="<c:url value="/assets/js/plugins/chartist.min.js" />"></script>
</body>
</html>