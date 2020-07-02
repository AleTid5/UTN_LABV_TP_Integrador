<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
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
                <a class="nav-link active show" data-toggle="tab" href="#unseen-loans" role="tablist">
                  <i class="material-icons">sync</i>
                  Préstamos sin aprobar
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#approved-loans" role="tablist">
                  <i class="material-icons">check_circle_outline</i>
                  Préstamos aprobados
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#rejected-loans" role="tablist">
                  <i class="material-icons">highlight_off</i>
                  Préstamos rechazados
                </a>
              </li>
            </ul>
            <div class="tab-content tab-space tab-subcategories">
              <div class="tab-pane active show" id="unseen-loans">
                <div class="material-datatables">
                  <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                    <thead>
                    <tr>
                      <th>Tipo de cuenta</th>
                      <th>Monto solicitado</th>
                      <th>Cuotas</th>
                      <th>Valor cuota</th>
                      <th></th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                      <th>Tipo de cuenta</th>
                      <th>Monto solicitado</th>
                      <th>Cuotas</th>
                      <th>Valor cuota</th>
                      <th></th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach var="loan" items="${ unseenLoans }">
                      <tr id="loan-${ loan.id }">
                        <td>${ loan.account.accountType.name }</td>
                        <td>${ loan.amount }</td>
                        <td>${ loan.feesToPay }</td>
                        <td>${ loan.feeValue }</td>
                        <td>
                          <button onclick="onApprove(${ loan.id })" class="btn btn-link btn-success btn-just-icon">
                            <i class="material-icons">check_circle_outline</i>
                          </button>
                          <button onclick="onReject(${ loan.id })" class="btn btn-link btn-danger btn-just-icon">
                            <i class="material-icons">highlight_off</i>
                          </button>
                        </td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="tab-pane" id="approved-loans">
                <div class="material-datatables">
                  <table id="datatables2" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                    <thead>
                    <tr>
                      <th>Tipo de cuenta</th>
                      <th>Monto solicitado</th>
                      <th>Cuotas</th>
                      <th>Valor cuota</th>
                      <th>Cuotas pagas</th>
                      <th>Adminstrador que aprobó</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                      <th>Tipo de cuenta</th>
                      <th>Monto solicitado</th>
                      <th>Cuotas</th>
                      <th>Valor cuota</th>
                      <th>Cuotas pagas</th>
                      <th>Adminstrador que aprobó</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach var="loan" items="${ approvedLoans }">
                      <tr>
                        <td>${ loan.account.accountType.name }</td>
                        <td>${ loan.amount }</td>
                        <td>${ loan.feesToPay }</td>
                        <td>${ loan.feeValue }</td>
                        <td>${ loan.payedFees }</td>
                        <td title="${ loan.bankAdministrator.lastName }, ${ loan.bankAdministrator.name }">${ loan.bankAdministrator.email }</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="tab-pane" id="rejected-loans">
                <div class="material-datatables">
                  <table id="datatables3" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                    <thead>
                    <tr>
                      <th>Tipo de cuenta</th>
                      <th>Monto solicitado</th>
                      <th>Cuotas</th>
                      <th>Valor cuota</th>
                      <th>Adminstrador que rechazó</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr>
                      <th>Tipo de cuenta</th>
                      <th>Monto solicitado</th>
                      <th>Cuotas</th>
                      <th>Valor cuota</th>
                      <th>Adminstrador que rechazó</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <c:forEach var="loan" items="${ rejectedLoans }">
                      <tr>
                        <td>${ loan.account.accountType.name }</td>
                        <td>${ loan.amount }</td>
                        <td>${ loan.feesToPay }</td>
                        <td>${ loan.feeValue }</td>
                        <td title="${ loan.bankAdministrator.lastName }, ${ loan.bankAdministrator.name }">${ loan.bankAdministrator.email }</td>
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
<script src="<c:url value="/assets/js/plugins/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/assets/js/components/datatable.js" />"></script>
<script>
  onApprove = id => {
    Swal.fire({
      title: '¿Está seguro que desea aprobar el préstamo?',
      text: "Le enviaremos un E-Mail al cliente. No podrá revertir la acción",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#52af50',
      cancelButtonColor: '#ea4a64',
      cancelButtonText: 'Cancelar',
      confirmButtonText: '¡Si, quiero aprobarlo!'
    }).then((result) => {
      if (result.value) {
        $.ajax({
          url: '${request.getContextPath()}/TP_L5_GRUPO_1/loans/approve/' + id,
          type: 'POST',
          data: {
            id
          },
          success: function (data) {
            return data && data.status ? handleSuccessApprove(id) : handleError();
          }
        });
      }
    });
  };

  onReject = id => {
    Swal.fire({
      title: '¿Está seguro que desea rechazar éste préstamo?',
      text: "Le enviaremos un E-Mail al cliente. No podrá revertir la acción",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#52af50',
      cancelButtonColor: '#ea4a64',
      cancelButtonText: 'Cancelar',
      confirmButtonText: '¡Si, recházalo!'
    }).then((result) => {
      if (result.value) {
        $.ajax({
          url: '${request.getContextPath()}/TP_L5_GRUPO_1/loans/reject/' + id,
          type: 'POST',
          data: {
            id
          },
          success: function (data) {
            return data && data.status ? handleSuccessReject(id) : handleError();
          }
        });
      }
    });
  };

  handleSuccessApprove = id => {
    const [accountType, amount, fees, feesValue] = $('#loan-' + id)[0].children;

    $('#datatables').DataTable().rows('#loan-' + id).remove().draw();
    $('#datatables2').DataTable().row.add([accountType.innerHTML, amount.innerHTML, fees.innerHTML, feesValue.innerHTML, "0", "lperisich@bank.com"]).draw();

    Swal.fire({
      icon: 'success',
      title: '¡Aprobado!',
      text: 'El cliente ha sido eliminado exitosamente.',
      confirmButtonColor: '#52af50',
    })
  };

  handleSuccessReject = id => {
    const [accountType, amount, fees, feesValue] = $('#loan-' + id)[0].children;

    $('#datatables').DataTable().rows('#loan-' + id).remove().draw();
    $('#datatables3').DataTable().row.add([accountType.innerHTML, amount.innerHTML, fees.innerHTML, feesValue.innerHTML, "lperisich@bank.com"]).draw();

    Swal.fire({
      icon: 'success',
      title: '¡Rechazado!',
      text: 'El cliente ha sido eliminado exitosamente.',
      confirmButtonColor: '#52af50',
    })
  };

  handleError = () => {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: '¡Algo ha salido mal!',
      confirmButtonColor: '#52af50',
    })
  };
</script>