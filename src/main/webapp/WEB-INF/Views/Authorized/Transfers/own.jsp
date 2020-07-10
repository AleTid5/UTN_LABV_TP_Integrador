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
                <h4 class="card-title">Transferencia a cuenta propia</h4>
              </div>
            </div>
            <div class="card-body ">
              <div class="row">
                <label class="col-sm-2 col-form-label" for="originAccount">Cuenta origen</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <select name="originAccount" id="originAccount" class="selectpicker" data-live-search="true" data-style="select-with-transition" onchange="onAccountChange()">
                      <option value="" data-value="0" selected>Seleccione una cuenta</option>
                      <c:forEach var="account" items="${ accounts }">
                        <option value="${ account.CBU }" data-value="${ account.balance }" data-currency="${ account.accountType.currencyType.id }">
                            ${ account.accountType } ($${ account.balance })
                        </option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label" for="destinationAccount">Cuenta destino</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <select name="destinationAccount" id="destinationAccount" class="selectpicker" data-live-search="true" data-style="select-with-transition" onchange="onAccountChange()">
                      <option value="" data-value="0" selected>Seleccione una cuenta</option>
                      <c:forEach var="account" items="${ accounts }">
                        <option value="${ account.CBU }" data-currency="${ account.accountType.currencyType.id }">
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
                    <input required type="number" name="amount" class="form-control" placeholder="Ingrese monto" min="0" max="0" step="0.01" disabled>
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
  window.history.replaceState({}, document.title, "/TP_L5_GRUPO_1/transfers/own");

  const onAccountChange = () => {
    const originAccount = $('[name="originAccount"]');
    const destinationAccount = $('[name="destinationAccount"]');
    const maxValue = parseFloat(originAccount[0].options[originAccount[0].selectedIndex].getAttribute('data-value'));
    const originAccountCurrency = parseInt(originAccount[0].options[originAccount[0].selectedIndex].getAttribute('data-currency'));
    const destinationAccountCurrency = parseInt(destinationAccount[0].options[destinationAccount[0].selectedIndex].getAttribute('data-currency'));
    $('[name="amount"]').attr("max", maxValue);

    if (maxValue === 0) {
      $('[name="destinationAccount"], [name="amount"],  #save-button').prop('disabled', true);
    } else {
      $('[name="destinationAccount"], [name="amount"],  #save-button').prop('disabled', false);
    }

    if (originAccount.val() === destinationAccount.val() || originAccountCurrency !== destinationAccountCurrency) {
      $('[name="amount"],  #save-button').prop('disabled', true);
    } else {
      $('[name="amount"],  #save-button').prop('disabled', false);
    }
  }
</script>