<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <div class="col-12">
      <div class="wizard-container">
        <div class="card card-wizard active" data-color="rose" id="wizardProfile">
          <form action="" method="POST">
            <div class="card-header text-center">
              <h3 class="card-title">
                Creando un movimiento
              </h3>
              <h5 class="card-description">Es necesario completar todos los campos.</h5>
            </div>
            <div class="wizard-navigation">
              <ul class="nav nav-pills">
                <li class="nav-item">
                  <a class="nav-link active" href="#teacher-tab" data-toggle="tab" role="tab">
                    Información inicial
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" href="#students-tab" data-toggle="tab" role="tab">
                    Alumnos
                  </a>
                </li>
              </ul>
            </div>
            <div class="card-body">
              <div class="tab-content">
                <div class="tab-pane active" id="teacher-tab">
                  <h5 class="info-text">
                    Primero, ingrese información básica acerca del profesor, el cuatrimestre y la materia
                  </h5>
                  <div class="row justify-content-center">
                    <div class="col-6">
                      <div class="input-group form-control-lg">
                        <div class="input-group-prepend">
                          <span class="input-group-text">
                            <i class="material-icons">calendar_today</i>
                          </span>
                        </div>
                        <div class="form-group">
                          <label for="semester" class="bmd-label-floating mr-3">Cuatrimestre</label>
                          <select name="semester" id="semester" class="selectpicker" data-style="select-with-transition">
                            <option value="1">Primer cuatrimestre</option>
                            <option value="2">Segundo cuatrimestre</option>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-6">
                      <div class="input-group form-control-lg">
                        <div class="input-group-prepend">
                          <span class="input-group-text">
                            <i class="material-icons">calendar_today</i>
                          </span>
                        </div>
                        <div class="form-group">
                          <label for="subject" class="bmd-label-floating mr-3">Materia</label>
                          <select required name="subject" id="subject" class="selectpicker" data-style="select-with-transition">
                            <c:forEach var="subject" items="${ subjects }">
                              <option value="${ subject.getId() }"
                                ${! subject.getStatus().equals("A") ? "disabled": ""}>
                                  ${ subject.getName() }
                              </option>
                            </c:forEach>
                          </select>
                        </div>
                      </div>
                    </div>
                    <div class="col-12">
                      <div class="material-datatables">
                        <table id="teachers-datatable" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                          <thead>
                          <tr>
                            <th>Legajo</th>
                            <th>DNI</th>
                            <th>Nombre y apellido</th>
                            <th>Email</th>
                            <th class="disabled-sorting text-right"></th>
                          </tr>
                          </thead>
                          <tfoot>
                          <tr>
                            <th>Legajo</th>
                            <th>DNI</th>
                            <th>Nombre y apellido</th>
                            <th>Email</th>
                            <th class="text-right"></th>
                          </tr>
                          </tfoot>
                          <tbody>
                          <c:forEach var="teacher" items="${ teachers }">
                            <tr id="teacher-${ teacher.getDocket() }">
                              <td>${ teacher.getDocket() }</td>
                              <td>${ teacher.getDNI() }</td>
                              <td>${ teacher }</td>
                              <td>${ teacher.getEmail() }</td>
                              <td>
                                <div class="form-check" style="margin-top: -6px;">
                                  <label class="form-check-label">
                                    <input class="form-check-input" type="radio" id="teacher" name="teacher[]" value="${ teacher.getDocket() }">
                                    <span class="circle">
                                      <span class="check"></span>
                                    </span>
                                  </label>
                                </div>
                              </td>
                            </tr>
                          </c:forEach>
                          </tbody>
                        </table>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="tab-pane" id="students-tab">
                  <div class="row justify-content-center">
                    <div class="col-12">
                      <div class="material-datatables">
                        <table id="students-datatable" class="table table-striped table-no-bordered table-hover"
                               cellspacing="0" width="100%" style="width:100%">
                          <thead>
                          <tr>
                            <th>Legajo</th>
                            <th>DNI</th>
                            <th>Nombre y apellido</th>
                            <th>Email</th>
                            <th class="disabled-sorting text-right"></th>
                          </tr>
                          </thead>
                          <tfoot>
                          <tr>
                            <th>Legajo</th>
                            <th>DNI</th>
                            <th>Nombre y apellido</th>
                            <th>Email</th>
                            <th class="text-right"></th>
                          </tr>
                          </tfoot>
                          <tbody>
                          <c:forEach var="student" items="${ students }">
                            <tr id="students-${ student.getDocket() }">
                              <td>${ student.getDocket() }</td>
                              <td>${ student.getDNI() }</td>
                              <td>${ student }</td>
                              <td>${ student.getEmail() }</td>
                              <td>
                                <div class="form-check" style="padding-top: 9px;">
                                  <label class="form-check-label">
                                    <input class="form-check-input" type="checkbox" id="students" name="students[]" value="${ student.getDocket() }">
                                    <span class="form-check-sign">
                                      <span class="check"></span>
                                    </span>
                                  </label>
                                </div>
                              </td>
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
            <div class="card-footer">
              <div class="mr-auto">
                <input type="button" class="btn btn-previous btn-fill btn-default btn-wd disabled" name="previous" value="Previous">
              </div>
              <div class="ml-auto">
                <input type="button" class="btn btn-next btn-fill btn-rose btn-wd" name="next" value="Next">
                <input type="submit" class="btn btn-finish btn-fill btn-rose btn-wd" name="finish" value="Finish" style="display: none;">
              </div>
              <div class="clearfix"></div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </jsp:body>
</layout:authorized>
<script src="${ assetsPath }/js/plugins/jquery.validate.min.js"></script>
<script src="${ assetsPath }/js/plugins/jquery.bootstrap-wizard.js"></script>
<script src="${ assetsPath }/js/plugins/jquery.dataTables.min.js"></script>
<script src="${ assetsPath }/js/components/datatable.js"></script>
<script src="${ assetsPath }/js/components/wizard.js"></script>