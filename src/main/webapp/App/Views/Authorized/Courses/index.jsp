<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <div class="row">
      <div class="col-md-12">
        <div class="card">
          <div class="card-header card-header-primary card-header-icon">
            <div class="card-icon">
              <i class="material-icons">assignment</i>
            </div>
            <h4 class="card-title">Listado de cursos</h4>
            <c:if test="true">
              <a href="courses/add">Añadir curso</a>
            </c:if>
          </div>
          <div class="card-body">
            <div class="material-datatables">
              <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                <thead>
                <tr>
                  <th>Materia</th>
                  <th>Semestre</th>
                  <th>Año</th>
                  <c:if test="true">
                    <th>Docente</th>
                  </c:if>
                  <c:if test="true">
                    <th class="disabled-sorting text-right"></th>
                  </c:if>
                </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>Materia</th>
                  <th>Semestre</th>
                  <th>Año</th>
                  <c:if test="true">
                    <th>Docente</th>
                  </c:if>
                  <c:if test="true">
                    <th class="text-right"></th>
                  </c:if>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="course" items="${ courses }">
                  <tr id="course-${ course.getId() }">
                    <td>${ course.getSubject().getName() }</td>
                    <td>${ course.getSemester() }</td>
                    <td>${ course.getYear() }</td>
                    <c:if test="true">
                      <td>${ course.getTeacher() }</td>
                    </c:if>
                    <c:if test="true">
                      <td>
                        <a href="courses/details?course-id=${ course.getId() }" class="btn btn-link btn-warning btn-just-icon edit">
                          <i class="material-icons">dvr</i>
                        </a>
                      </td>
                    </c:if>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</layout:authorized>
<script src="${ assetsPath }/js/plugins/jquery.dataTables.min.js"></script>
<script src="${ assetsPath }/js/components/datatable.js"></script>