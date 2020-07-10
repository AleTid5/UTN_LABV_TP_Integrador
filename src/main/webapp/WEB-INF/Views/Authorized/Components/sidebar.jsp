<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar" data-color="orange" data-background-color="black" data-image="<c:url value="/assets/img/sidebar-1.jpg" />">
	<div class="logo">
		<a href="#" class="simple-text logo-mini" style="width: 34px;">
			<div class="user mt-0">
				<div class="photo ml-0">
					<img src="<c:url value="/assets/img/utn.png" />" />
				</div>
			</div>
		</a> <a href="http://www.creative-tim.com" class="simple-text logo-normal">
		UTN FRGP </a>
	</div>
	<div class="sidebar-wrapper">
		<div class="user" style="padding-bottom: 15px">
			<div class="photo">
				<img src="<c:url value="/assets/img/default-avatar.png" />" />
			</div>
			<div class="user-info">
				<a data-toggle="collapse" class="username">
					<span style="margin-top: -7px;">
						${ pageContext.getSession().getAttribute("name") } ${ pageContext.getSession().getAttribute("lastName") }<br>
							<span style="color: #88876e;">${ pageContext.getSession().getAttribute("email") }</span>
					</span>
				</a>
			</div>
		</div>
		<ul class="nav">
			<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/dashboard") ? "active" : ""}">
				<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/dashboard"> <i class="material-icons">dashboard</i>
					<p>Dashboard</p>
				</a>
			</li>
			<c:choose>
				<c:when test="${pageContext.getSession().getAttribute(\"isAdministrator\").equals(true)}">
					<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/customers") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#customers"> <i class="material-icons">person</i>
							<p>
								Clientes <b class="caret"></b>
							</p>
						</a>
						<div class="collapse ${ requestScope["javax.servlet.forward.request_uri"].contains("/customers") ? "show" : ""}" id="customers">
							<ul class="nav">
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/customers") ? "active" : "" }">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/customers">
										<span class="sidebar-mini">L</span> <span class="sidebar-normal">Listado</span>
									</a>
								</li>
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/customers/add") ? "active" : "" }">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/customers/add">
										<span class="sidebar-mini">A</span> <span class="sidebar-normal">Agregar cliente</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/accounts") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#accounts"> <i class="material-icons">card_travel</i>
							<p>
								Cuentas <b class="caret"></b>
							</p>
						</a>
						<div class="collapse ${ requestScope["javax.servlet.forward.request_uri"].contains("/accounts") ? "show" : ""}" id="accounts">
							<ul class="nav">
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/accounts") ? "active" : "" }">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/accounts">
										<span class="sidebar-mini">L</span> <span class="sidebar-normal">Listado</span>
									</a>
								</li>
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/accounts/add") ? "active" : "" }">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/accounts/add">
										<span class="sidebar-mini">A</span> <span class="sidebar-normal">Agregar cuenta</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/loans") ? "active" : ""}">
						<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/loans"> <i class="material-icons">credit_card</i>
							<p>Préstamos</p>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/dollars") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#dollars"> <i class="material-icons">credit_card</i>
							<p>
								Compra-Venta Dólares <b class="caret"></b>
							</p>
						</a>
						<div class="collapse ${ requestScope["javax.servlet.forward.request_uri"].contains("/dollars") ? "show" : ""}" id="dollars">
							<ul class="nav">
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/dollars/buy") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/dollars/buy">
										<span class="sidebar-mini">C</span> <span class="sidebar-normal">Comprar</span>
									</a>
								</li>
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/dollars/sell") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/dollars/sell">
										<span class="sidebar-mini">V</span> <span class="sidebar-normal">Vender</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/loans") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#loans"> <i class="material-icons">credit_card</i>
							<p>
								Préstamos <b class="caret"></b>
							</p>
						</a>
						<div class="collapse ${ requestScope["javax.servlet.forward.request_uri"].contains("/loans") ? "show" : ""}" id="loans">
							<ul class="nav">
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/loans") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/loans">
										<span class="sidebar-mini">AC</span> <span class="sidebar-normal">Abonar cuota</span>
									</a>
								</li>
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/loans/request") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/loans/request">
										<span class="sidebar-mini">SD</span> <span class="sidebar-normal">Solicitar dinero</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/transfers") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#transfers"> <i class="material-icons">compare_arrows</i>
							<p>Transferencias <b class="caret"></b></p>
						</a>
						<div class="collapse ${ requestScope["javax.servlet.forward.request_uri"].contains("/transfers") ? "show" : ""}"  id="transfers">
							<ul class="nav">
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/transfers/thirdParty") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/transfers/thirdParty">
										<span class="sidebar-mini">OC</span> <span class="sidebar-normal">A otra cuenta de terceros</span>
									</a>
								</li>
								<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].equals("/TP_L5_GRUPO_1/transfers/own") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/transfers/own">
										<span class="sidebar-mini">CP</span> <span class="sidebar-normal">A cuenta propia</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item ${ requestScope["javax.servlet.forward.request_uri"].contains("/accounts") ? "active" : ""}">
						<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/accounts"> <i class="material-icons">history</i>
							<p>Movimientos de cuentas</p>
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
