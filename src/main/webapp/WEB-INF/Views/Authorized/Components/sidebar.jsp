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
		<div class="user" style="display: flex;justify-content: center;">
			<div class="photo">
				<img src="https://lh3.googleusercontent.com/proxy/LFGOHR1CHpRQCzBEmipbFmmDjI-x-p7rKDaDKnx7OQz3BgyG7hEhxyVCCqDLZKh6zRHh4Ic2rhauJTHC2pgikKAXtVi0MArRQsOAvr33dB_xaeZCjIMhmw" />
			</div>
			<div class="user-info">
				<a data-toggle="collapse" class="username">
					<span></span>
				</a>
			</div>
		</div>
		<ul class="nav">
			<li class="nav-item disabled">
				<a class="nav-link" href="${ mainPath }/dashboard"> <i class="material-icons" style="color: #616161">dashboard</i>
					<p style="color: #616161">Dashboard (Deshabilitado)</p>
				</a>
			</li>
			<c:choose>
				<c:when test="false">
					<li class="nav-item ${ currentLink.contains("/students") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#students"> <i class="material-icons">person</i>
							<p>
								Clientes <b class="caret"></b>
							</p>
						</a>
						<div class="collapse ${ currentLink.contains("/students") ? "show" : ""}">
							<ul class="nav">
								<li class="nav-item ${ currentLink.equals("/students") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/customers">
										<span class="sidebar-mini">L</span> <span class="sidebar-normal">Listado</span>
									</a>
								</li>
								<li class="nav-item ${ currentLink.equals("/students/add") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/customers/add">
										<span class="sidebar-mini">A</span> <span class="sidebar-normal">Agregar cliente</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item ${ currentLink.contains("/teachers") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#teachers"> <i class="material-icons">card_travel</i>
							<p>Cuentas <b class="caret"></b></p>
						</a>
						<div class="collapse ${ currentLink.contains("/teachers") ? "show" : ""}">
							<ul class="nav">
								<li class="nav-item ${ currentLink.equals("/teachers") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/accounts">
										<span class="sidebar-mini">L</span> <span class="sidebar-normal">Listado</span>
									</a>
								</li>
								<li class="nav-item ${ currentLink.equals("/teachers/add") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/accounts/add">
										<span class="sidebar-mini">A</span> <span class="sidebar-normal">Agregar cuenta</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/loans"> <i class="material-icons">credit_card</i>
							<p>Préstamos</p>
						</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item ${ currentLink.contains("/students") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#students"> <i class="material-icons">credit_card</i>
							<p>
								Préstamos <b class="caret"></b>
							</p>
						</a>
						<div class="collapse ${ currentLink.contains("/students") ? "show" : ""}">
							<ul class="nav">
								<li class="nav-item ${ currentLink.equals("/students/add") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/loans">
										<span class="sidebar-mini">A</span> <span class="sidebar-normal">Abonar cuota</span>
									</a>
								</li>
								<li class="nav-item ${ currentLink.equals("/students") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/loans/request">
										<span class="sidebar-mini">L</span> <span class="sidebar-normal">Solicitar dinero</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item ${ currentLink.contains("/teachers") ? "active" : ""}">
						<a class="nav-link" data-toggle="collapse" href="#teachers"> <i class="material-icons">compare_arrows</i>
							<p>Transferencias <b class="caret"></b></p>
						</a>
						<div class="collapse ${ currentLink.contains("/teachers") ? "show" : ""}">
							<ul class="nav">
								<li class="nav-item ${ currentLink.equals("/teachers") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/transfers/thirdParty">
										<span class="sidebar-mini">L</span> <span class="sidebar-normal">A otra cuenta de terceros</span>
									</a>
								</li>
								<li class="nav-item ${ currentLink.equals("/teachers/add") ? "active" : ""}">
									<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/transfers/own">
										<span class="sidebar-mini">A</span> <span class="sidebar-normal">A cuenta propia</span>
									</a>
								</li>
							</ul>
						</div>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${request.getContextPath()}/TP_L5_GRUPO_1/accounts"> <i class="material-icons">history</i>
							<p>Historial de cuentas</p>
						</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
