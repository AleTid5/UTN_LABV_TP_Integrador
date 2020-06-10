<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
    <jsp:body>
        <div class="row">
            <div class="col-md-12">
                <div class="card ">
                    <div class="card-header ">
                        <div class="row">
                            <div class="col-sm-11">
                                <h4 class="card-title">Detalle del cliente -
                                    <small class="description">Herrera Tamara</small>
                                </h4>
                            </div>
                            <div class="col-sm-1">
                                <a href="uploadNotes?course-id=${ courseId }" class="btn btn-warning btn-round btn-fab">
                                    <i class="material-icons">dvr</i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card-body ">
                        <div class="row">
                            <div class="col-md-4">
                                <ul class="nav nav-pills nav-pills-rose flex-column" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link active" data-toggle="tab"
                                           href="#link4" role="tablist">
                                            Laboratorio IV
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-8">
                                <div class="material-datatables">
                                    <table id="students-datatable" class="table table-striped table-no-bordered table-hover"
                                           cellspacing="0" width="100%" style="width:100%">
                                        <thead>
                                        <tr>
                                            <th>Legajo</th>
                                            <th>DNI</th>
                                            <th>Nombre y apellido</th>
                                            <th>Email</th>
                                        </tr>
                                        </thead>
                                        <tfoot>
                                        <tr>
                                            <th>Legajo</th>
                                            <th>DNI</th>
                                            <th>Nombre y apellido</th>
                                            <th>Email</th>
                                        </tr>
                                        </tfoot>
                                        <tbody>
                                        <c:forEach var="student" items="${ students }">
                                            <tr id="students-${ student.getDocket() }">
                                                <td>${ student.getDocket() }</td>
                                                <td>${ student.getDNI() }</td>
                                                <td>${ student }</td>
                                                <td>${ student.getEmail() }</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</layout:authorized>
<script src="${ assetsPath }/js/plugins/jquery.dataTables.min.js"></script>
<script src="${ assetsPath }/js/components/datatable.js"></script>