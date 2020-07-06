<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <div class="row">
      <div class="col-md-12">
        <div class="card ">
          <div class="card-header card-header-rose card-header-text">
            <div class="card-text">
              <h4 class="card-title">Préstamos</h4>
            </div>
          </div>
          <div class="card-body ">
            <div class="material-datatables">
              <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                <thead>
                <tr>
                  <th>Monto solicitado</th>
                  <th>Monto a pagar</th>
                  <th>Cuotas a pagar</th>
                  <th>Valor cuota</th>
                  <th>Cuenta a debitar</th>
                  <th>Pagar</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>Monto solicitado</th>
                  <th>Monto a pagar</th>
                  <th>Cuotas a pagar</th>
                  <th>Valor cuota</th>
                  <th>Cuenta a debitar</th>
                  <th>Pagar</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="loan" items="${ approvedLoans }">
                  <tr id="loan-${ loan.id }">
                    <td>${ loan.account.accountType.currencyType } $${ loan.amount }</td>
                    <td>$${ loan.amount - (loan.payedFees * loan.feeValue) }</td>
                    <td>${ loan.feesToPay - loan.payedFees }</td>
                    <td>$${ loan.feeValue }</td>
                    <td>
                      <select name="account-${ loan.id }" class="selectpicker" data-live-search="true" data-style="select-with-transition">
                        <option value="" selected>Seleccione una cuenta</option>
                        <c:forEach var="account" items="${ accounts }">
                          <option value="${ account.CBU }"
                            ${ !account.accountType.currencyType.id.equals(loan.account.accountType.currencyType.id) || loan.feeValue > account.balance ? "disabled" : "" }>
                              ${ account.accountType } ($${ account.balance })
                          </option>
                        </c:forEach>
                      </select>
                    </td>
                    <td>
                      <button onclick="onPayFee(${ loan.id })" class="btn btn-link btn-success btn-just-icon">
                        <i class="material-icons">credit_card</i>
                      </button>
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
  </jsp:body>
</layout:authorized>
<script src="<c:url value="/assets/js/plugins/jquery.dataTables.min.js" />"></script>
<script src="<c:url value="/assets/js/components/datatable.js" />"></script>
<script>
  const onPayFee = id => {
    const account = $('[name="account-' + id + '"]').val();

    if (! account) {
      return handleError("¡Debes seleccionar una cuenta de la cual debitar!");
    }

    Swal.fire({
      title: '¿Está seguro que desea abonar la cuota del préstamo?',
      text: "No podrá revertir la acción",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#52af50',
      cancelButtonColor: '#ea4a64',
      cancelButtonText: 'Cancelar',
      confirmButtonText: '¡Si, quiero pagarlo!'
    }).then((result) => {
      if (result.value) {
        $.ajax({
          url: '${request.getContextPath()}/TP_L5_GRUPO_1/loans/pay/' + id,
          type: 'POST',
          data: {
            account
          },
          success: function (data) {
            return data && data.status ? handleSuccess() : handleError();
          }
        });
      }
    });
  };

  const handleSuccess = () => {
    Swal.fire({
      icon: 'success',
      title: '¡Aprobado!',
      text: 'El cliente ha sido eliminado exitosamente.',
      confirmButtonColor: '#52af50',
    }).then(() => location.reload());
  };

  const handleError = (text = "¡Algo ha salido mal!") => {
    Swal.fire({
      icon: 'error',
      title: 'Oops...',
      text,
      confirmButtonColor: '#52af50',
    })
  };
</script>