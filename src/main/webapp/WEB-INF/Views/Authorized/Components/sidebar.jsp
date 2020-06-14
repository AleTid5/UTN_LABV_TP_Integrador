<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar" data-color="orange" data-background-color="black" data-image="${ assetsPath }/img/sidebar-1.jpg">
	<div class="logo">
		<a href="http://www.creative-tim.com" class="simple-text logo-mini" style="width: 34px;">
			<div class="user mt-0">
				<div class="photo ml-0">
					<img src="${ assetsPath }/img/utn.png" />
				</div>
			</div>
		</a> <a href="http://www.creative-tim.com" class="simple-text logo-normal">
		UTN FRGP </a>
	</div>
	<div class="sidebar-wrapper">
		<div class="user">
			<div class="photo">
				<img src="${ assetsPath }/img/default-avatar.png" />
			</div>
			<div class="user-info">
				<a data-toggle="collapse" class="username">
					<span></span>
				</a>
			</div>
		</div>
		<ul class="nav">
			<c:if test="true">
				<li class="nav-item ${ currentLink.equals("/dashboard") ? "active" : ""}">
					<a class="nav-link" href="${ mainPath }/dashboard"> <i class="material-icons">dashboard</i>
						<p>Dashboard</p>
					</a>
				</li>
				<li class="nav-item ${ currentLink.contains("/students") ? "active" : ""}">
					<a class="nav-link" data-toggle="collapse" href="#students"> <i class="material-icons">accessibility_new</i>
						<p>
							Ver Alumnos <b class="caret"></b>
						</p>
					</a>
					<div class="collapse ${ currentLink.contains("/students") ? "show" : ""}" id="students">
						<ul class="nav">
							<li class="nav-item ${ currentLink.equals("/students") ? "active" : ""}">
								<a class="nav-link" href="${ mainPath }/students">
									<span class="sidebar-mini">L</span> <span class="sidebar-normal">Listado</span>
								</a>
							</li>
							<li class="nav-item ${ currentLink.equals("/students/add") ? "active" : ""}">
								<a class="nav-link" href="${ mainPath }/students/add">
									<span class="sidebar-mini">A</span> <span class="sidebar-normal">Agregar Alumno</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				<li class="nav-item ${ currentLink.contains("/teachers") ? "active" : ""}">
					<a class="nav-link" data-toggle="collapse" href="#teachers"> <i class="material-icons">person</i>
						<p>Ver Profesores <b class="caret"></b></p>
					</a>
					<div class="collapse ${ currentLink.contains("/teachers") ? "show" : ""}" id="teachers">
						<ul class="nav">
							<li class="nav-item ${ currentLink.equals("/teachers") ? "active" : ""}">
								<a class="nav-link" href="${ mainPath }/teachers">
									<span class="sidebar-mini">L</span> <span class="sidebar-normal">Listado</span>
								</a>
							</li>
							<li class="nav-item ${ currentLink.equals("/teachers/add") ? "active" : ""}">
								<a class="nav-link" href="${ mainPath }/teachers/add">
									<span class="sidebar-mini">A</span> <span class="sidebar-normal">Agregar Profesor</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
				<li class="nav-item ${ currentLink.contains("/courses") ? "active" : ""}">
					<a class="nav-link" data-toggle="collapse" href="#courses"> <i class="material-icons">school</i>
						<p>
							Ver Cursos <b class="caret"></b>
						</p>
					</a>
					<div class="collapse ${ currentLink.contains("/courses") ? "show" : ""}" id="courses">
						<ul class="nav">
							<li class="nav-item ${ currentLink.equals("/courses") ? "active" : ""}">
								<a class="nav-link" href="${ mainPath }/courses">
									<span class="sidebar-mini">L</span> <span class="sidebar-normal">Listado</span>
								</a>
							</li>
							<li class="nav-item ${ currentLink.equals("/courses/add") ? "active" : ""}">
								<a class="nav-link" href="${ mainPath }/courses/add">
									<span class="sidebar-mini">A</span> <span class="sidebar-normal">Agregar Curso</span>
								</a>
							</li>
						</ul>
					</div>
				</li>
			</c:if>
			<c:if test="true">
				<li class="nav-item ${ currentLink.contains("/courses") ? "active" : ""}">
					<a class="nav-link" href="${ mainPath }/courses"> <i class="material-icons">school</i>
						<p>Ver Cursos</p>
					</a>
				</li>
			</c:if>
		</ul>
	</div>
</div>
