<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
    <jsp:body>
        <form method="POST" action="">
            <input type="hidden" name="method" value="PUT">
            <input type="hidden" name="courseId" value="${ courseId }">
            <div class="row">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header card-header-icon card-header-rose">
                            <div class="card-icon">
                                <i class="material-icons">assignment</i>
                            </div>
                            <h4 class="card-title ">Cargar notas</h4>
                        </div>
                        <div class="card-body">
                            <div class="material-datatables">
                                <table id="students-datatable" class="table table-striped table-no-bordered table-hover"
                                       cellspacing="0" width="100%" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th>Legajo</th>
                                        <th>DNI</th>
                                        <th>Nombre y apellido</th>
                                        <th>Nota Parcial 1</th>
                                        <th>Nota Parcial 2</th>
                                        <th>Nota Recuperatorio 1</th>
                                        <th>Nota Recuperatorio 2</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tfoot>
                                    <tr>
                                        <th>Legajo</th>
                                        <th>DNI</th>
                                        <th>Nombre y apellido</th>
                                        <th>Nota Parcial 1</th>
                                        <th>Nota Parcial 2</th>
                                        <th>Nota Recuperatorio 1</th>
                                        <th>Nota Recuperatorio 2</th>
                                        <th></th>
                                    </tr>
                                    </tfoot>
                                    <tbody>
                                    <c:forEach var="studentPerCourse" items="${ studentsPerCourse }">
                                        <tr>
                                            <td>
                                                <input type="hidden" name="students[]" value="${ studentPerCourse.getStudent().getDocket() }">
                                                    ${ studentPerCourse.getStudent().getDocket() }
                                            </td>
                                            <td>${ studentPerCourse.getStudent().getDNI() }</td>
                                            <td>${ studentPerCourse.getStudent() }</td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-10">
                                                        <div class="form-group">
                                                            <input type="number"
                                                                   min="0"
                                                                   max="10"
                                                                   class="form-control"
                                                                   placeholder="Nota"
                                                                   name="gradeP1-${ studentPerCourse.getStudent().getDocket() }"
                                                                   value="${ studentPerCourse.getGradeP1() }">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-10">
                                                        <div class="form-group">
                                                            <input type="number"
                                                                   min="0"
                                                                   max="10"
                                                                   class="form-control"
                                                                   placeholder="Nota"
                                                                   name="gradeP2-${ studentPerCourse.getStudent().getDocket() }"
                                                                   value="${ studentPerCourse.getGradeP2() }">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-10">
                                                        <div class="form-group">
                                                            <input type="number"
                                                                   min="0"
                                                                   max="10"
                                                                   class="form-control"
                                                                   placeholder="Nota"
                                                                   name="gradeR1-${ studentPerCourse.getStudent().getDocket() }"
                                                                   value="${ studentPerCourse.getGradeR1() }">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <div class="row">
                                                    <div class="col-sm-10">
                                                        <div class="form-group">
                                                            <input type="number"
                                                                   min="0"
                                                                   max="10"
                                                                   class="form-control"
                                                                   placeholder="Nota"
                                                                   name="gradeR2-${ studentPerCourse.getStudent().getDocket() }"
                                                                   value="${ studentPerCourse.getGradeR2() }">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                            <td>
                                                <select name="studentCondition-${ studentPerCourse.getStudent().getDocket() }" class="selectpicker" data-style="select-with-transition">
                                                    <option value="L" ${ studentPerCourse.getStudentCondition().equals("L") ? "selected" : "" }>Libre</option>
                                                    <option value="R" ${ studentPerCourse.getStudentCondition().equals("R") ? "selected" : "" }>Regular</option>
                                                </select>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="card-footer" style="margin: 0 0px 10px;">
                            <div class="row">
                                <div class="offset-8 offset-sm-10 col-sm-2">
                                    <button type="submit" class="btn btn-fill btn-rose">Guardar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </jsp:body>
</layout:authorized>
<script src="${ assetsPath }/js/plugins/jquery.dataTables.min.js"></script>
<script src="${ assetsPath }/js/components/datatable.js"></script>