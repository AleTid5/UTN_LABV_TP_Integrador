<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <form method="POST" action="" class="form-horizontal">
      <div class="row">
        <div class="col-md-12">
          <div class="card ">
            <div class="card-header card-header-rose card-header-text">
              <div class="card-text">
                <h4 class="card-title">Agregar Cuenta</h4>
              </div>
            </div>
            <div class="card-body ">
              <ul class="nav nav-pills nav-pills-rose nav-pills-icons justify-content-center" role="tablist">
                <li class="nav-item">
                  <a class="nav-link active show" data-toggle="tab" href="#base-info" role="tablist">
                    <i class="material-icons">info</i> Información básica
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#customer-data" role="tablist">
                    <i class="material-icons">location_on</i> Cliente
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link" data-toggle="tab" href="#account-type" role="tablist">
                    <i class="material-icons">gavel</i> Tipo de cuenta
                  </a>
                </li>
              </ul>
              <div class="tab-content tab-space tab-subcategories">
                <div class="tab-pane active show" id="base-info">
                  <div class="row">
                    <label class="col-sm-2 col-form-label">Número de cuenta</label>
                    <div class="col-sm-10">
                      <div class="form-group">
                        <input required type="number" name="accountNumber" class="form-control" placeholder="Ingrese número de cuenta">
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <label class="col-sm-2 col-form-label">Alias</label>
                    <div class="col-sm-10">
                      <div class="form-group">
                        <input type="text" required name="alias" class="form-control" placeholder="Ingrese el alias del usuario">
                      </div>
                    </div>
                  </div>
                </div>
                <div class="tab-pane" id="customer-data">
                  <div class="material-datatables">
                    <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                      <thead>
                      <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>DNI</th>
                        <th>E-Mail</th>
                        <th>Usuario</th>
                        <th>Monto máximo para préstamo</th>
                        <th></th>
                      </tr>
                      </thead>
                      <tfoot>
                      <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>DNI</th>
                        <th>E-Mail</th>
                        <th>Usuario</th>
                        <th>Monto máximo para préstamo</th>
                        <th></th>
                      </tr>
                      </tfoot>
                      <tbody>
                      <c:forEach var="customer" items="${ customers }">
                        <tr id="customer-${ customer.id }">
                          <td>${ customer.name }</td>
                          <td>${ customer.lastName }</td>
                          <td>${ customer.dni }</td>
                          <td>${ customer.email }</td>
                          <td>${ customer.userName }</td>
                          <td>${ customer.maxLoanAmount }</td>
                          <td>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="customer" value="${ customer.id }">
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
                <div class="tab-pane" id="account-type">
                  <div class="material-datatables">
                    <table id="datatables2" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%" required>
                      <thead>
                      <tr>
                        <th>Tipo de cuenta</th>
                        <th>Moneda</th>
                        <th></th>
                      </tr>
                      </thead>
                      <tfoot>
                      <tr>
                        <th>Tipo de cuenta</th>
                        <th>Moneda</th>
                        <th></th>
                      </tr>
                      </tfoot>
                      <tbody>
                      <c:forEach var="accountType" items="${ accountTypes }">
                        <tr id="customer-${ accountType.id }">
                          <td>${ accountType.name }</td>
                          <td>${ accountType.currencyType }</td>
                          <td>
                            <div class="form-check">
                              <label class="form-check-label">
                                <input class="form-check-input" type="radio" name="currencyType" value="${ accountType.id }" required>
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
            <div class="card-footer" style="margin: 0 0px 10px;">
              <div class="row" style="width: 100%">
                <div class="col-2" style="padding-left: 30px;">
                  <a href="${request.getContextPath()}/UTN_LABV_TP_Integrador/accounts" class="btn btn-fill btn-dark">Cancelar</a>
                </div>
                <div class="offset-6 col-2 offset-sm-8 col-sm-2" style="padding-right: 5px;">
                  <button type="submit" class="btn btn-fill btn-rose" style="float: right;">Agregar cuenta</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </jsp:body>
</layout:authorized>
<script>

</script>