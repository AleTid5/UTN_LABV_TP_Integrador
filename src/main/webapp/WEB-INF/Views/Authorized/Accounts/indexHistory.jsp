<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <div class="modal" id="loading" style="display: none">
      <div class="loader"></div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="card ">
          <div class="card-header card-header-rose card-header-text">
            <div class="card-text">
              <h4 class="card-title">Ultimos movimientos</h4>
            </div>
          </div>
          <div class="card-body ">
            <ul class="nav nav-pills nav-pills-rose nav-pills-icons justify-content-center" role="tablist">
              <c:forEach items="${ accounts }" var="account" varStatus="loop">
                <li class="nav-item">
                  <a class="nav-link ${ loop.index == 0 ? "active show" : "" }" data-toggle="tab" href="#account-${ account.CBU }" role="tablist">
                    <i class="material-icons">sync</i>
                      ${ account.accountType } <br/>
                      ${ account.accountNumber }
                  </a>
                </li>
              </c:forEach>
            </ul>
            <div class="tab-content tab-space tab-subcategories">
              <c:forEach items="${ accounts }" var="account" varStatus="loop">
                <div class="tab-pane ${ loop.index == 0 ? "active show" : "" }" id="account-${ account.CBU }">
                  <div class="material-datatables">
                    <table id="datatables${ loop.index }" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                      <thead>
                      <tr>
                        <th>Fecha</th>
                        <th>Cuenta Origen</th>
                        <th>Cuenta Destino</th>
                        <th>Monto</th>
                        <th>Moneda</th>
                        <th>Concepto</th>
                      </tr>
                      </thead>
                      <tfoot>
                      <tr>
                        <th>Fecha</th>
                        <th>Cuenta Origen</th>
                        <th>Cuenta Destino</th>
                        <th>Monto</th>
                        <th>Moneda</th>
                        <th>Concepto</th>
                      </tr>
                      </tfoot>
                      <tbody>
                      <c:forEach var="movement" items="${ movements }">
                        <c:if test="${ movement.originAccount.CBU.equals(account.CBU) || movement.destinationAccount.CBU.equals(account.CBU) }">
                          <tr>
                            <td>${ movement.creationDate }</td>
                            <td>${ movement.originAccount.accountNumber }</td>
                            <td>${ movement.destinationAccount.accountNumber }</td>
                            <td>${ movement.amount }</td>
                            <td>${ movement.destinationAccount.accountType.currencyType }</td>
                            <td>${ movement.concept }</td>
                          </tr>
                        </c:if>
                      </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
              </c:forEach>
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
  const onApprove = id => {
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
        $("#loading").css("display", "flex");
        $.ajax({
          url: '${request.getContextPath()}/TP_L5_GRUPO_1/loans/approve/' + id,
          type: 'POST',
          success: function (data) {
            $("#loading").hide();
            return data && data.status ? handleSuccessApprove(id) : handleError();
          }
        });
      }
    });
  };

  const onReject = id => {
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
        $("#loading").css("display", "flex");
        $.ajax({
          url: '${request.getContextPath()}/TP_L5_GRUPO_1/loans/reject/' + id,
          type: 'POST',
          success: function (data) {
            $("#loading").hide();
            return data && data.status ? handleSuccessReject(id) : handleError();
          }
        });
      }
    });
  };

  const handleSuccessApprove = id => {
    const [accountType, customer, amount, fees, feesValue] = $('#loan-' + id)[0].children;

    $('#datatables').DataTable().rows('#loan-' + id).remove().draw();
    $('#datatables2').DataTable().row.add([accountType.innerHTML, customer.innerHTML, amount.innerHTML, fees.innerHTML, feesValue.innerHTML, "0", "${ request.getSession().getAttribute("email") }"]).draw();

    Swal.fire({
      icon: 'success',
      title: '¡Aprobado!',
      text: 'El préstamos ha sido aprobado exitosamente.',
      confirmButtonColor: '#52af50',
    })
  };

  const handleSuccessReject = id => {
    const [accountType, customer, amount, fees, feesValue] = $('#loan-' + id)[0].children;

    $('#datatables').DataTable().rows('#loan-' + id).remove().draw();
    $('#datatables3').DataTable().row.add([accountType.innerHTML, customer.innerHTML, amount.innerHTML, fees.innerHTML, feesValue.innerHTML, "${ request.getSession().getAttribute("email") }"]).draw();

    Swal.fire({
      icon: 'success',
      title: '¡Rechazado!',
      text: 'El préstamos ha sido rechazado exitosamente.',
      confirmButtonColor: '#52af50',
    })
  };

  const handleError = () => {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text: '¡Algo ha salido mal!',
      confirmButtonColor: '#52af50',
    })
  };
</script>