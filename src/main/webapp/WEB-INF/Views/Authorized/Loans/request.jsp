<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <form method="POST" action="" class="form-horizontal">
      <div class="row">
        <div class="col-md-12">
          <div class="card ">
            <div class="card-header card-header-rose card-header-text">
              <div class="card-text">
                <h4 class="card-title">Solicitar Préstamo</h4>
              </div>
            </div>
            <div class="card-body ">
              <div class="row">
                <label class="col-sm-2 col-form-label" for="originAccount">Cuenta a depositar</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <select name="originAccount" id="originAccount" class="selectpicker" data-live-search="true" data-style="select-with-transition" onchange="onAccountChange(this)">
                      <option value="" data-value="0" selected>Seleccione una cuenta</option>
                      <c:forEach var="account" items="${ accounts }">
                        <option value="${ account.CBU }" data-value="${ account.balance }">
                            ${ account.accountType } ($${ account.balance })
                        </option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Monto</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input required type="number" name="amount" class="form-control" placeholder="Ingrese monto" min="0" step="0.01" disabled>
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label" for="feesToPay">Cuotas</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <select name="feesToPay" id="feesToPay" class="selectpicker" data-live-search="true" data-style="select-with-transition" onchange="onFeesChange(this.value)">
                      <option value="2">2 cuotas</option>
                      <option value="3">3 cuotas</option>
                      <option value="6" selected>6 cuotas</option>
                      <option value="12">12 cuotas</option>
                      <option value="18">18 cuotas</option>
                    </select>
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label" for="endDate">Fecha de fin</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input required type="text" class="form-control" placeholder="Ingrese monto" min="0" id="endDate" disabled>
                  </div>
                </div>
              </div>
              <div class="card-footer" style="margin: 0 0px 10px;">
                <div class="row" style="width: 100%">
                  <div class="col-2" style="padding-left: 30px;">
                    <a href="${request.getContextPath()}/TP_L5_GRUPO_1/accounts" class="btn btn-fill btn-dark">Cancelar</a>
                  </div>
                  <div class="offset-6 col-2 offset-sm-8 col-sm-2" style="padding-right: 5px;">
                    <button type="submit" class="btn btn-fill btn-rose" style="float: right;" id="save-button" disabled>Finalizar</button>
                  </div>
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
  window.history.replaceState({}, document.title, "/TP_L5_GRUPO_1/loans/request");

  const onAccountChange = select => {
    const maxValue = parseFloat(select.options[select.selectedIndex].getAttribute('data-value'));

    if (maxValue === 0) {
      $('[name="destinationAccount"], [name="amount"], #save-button').prop('disabled', true);
    } else {
      $('[name="destinationAccount"], [name="amount"], #save-button').prop('disabled', false);
    }
  }

  const onFeesChange = feesQuantity => {
    const date = new Date();
    date.setMonth(date.getMonth() + parseInt(feesQuantity));
    console.log(date)

    $('#endDate').val(date.getFullYear() + "-" +
            (date.getMonth() + 1 > 9 ? date.getMonth() + 1 : "0" + (date.getMonth() + 1)) +
            "-" + (date.getDate() > 9 ? date.getDate() : "0" + (date.getDate())));
  }

  onFeesChange(6);
</script>