<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
    <jsp:body>
        <form method="POST" action="" class="form-horizontal" onsubmit="return onSubmit()">
            <div class="row">
                <div class="col-md-12">
                    <div class="card ">
                        <div class="card-header card-header-rose card-header-text">
                            <div class="card-text">
                                <h4 class="card-title">Editar Cuenta</h4>
                            </div>
                        </div>
                        <div class="card-body ">
                            <ul class="nav nav-pills nav-pills-rose nav-pills-icons justify-content-center" role="tablist">
                                <li class="nav-item">
                                    <a class="nav-link active show" data-toggle="tab" href="#base-info" role="tablist">
                                        <i class="material-icons">settings</i>
                                        <span class="notification pulse" id="base-info-error" style="
                                        display: none;
                                        position: absolute;
                                        top: 4px;
                                        border: 1px solid #fff;
                                        font-size: 9px;
                                        background: #f44336;
                                        color: #fff;
                                        min-width: 20px;
                                        height: 20px;
                                        border-radius: 10px;
                                        line-height: 19px;">
                                          !
                                        </span>
                                        Información básica
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#customer-data" role="tablist">
                                        <i class="material-icons">person</i>
                                        <span class="notification pulse" id="customer-error" style="
                                        display: none;
                                        position: absolute;
                                        top: 4px;
                                        border: 1px solid #fff;
                                        font-size: 9px;
                                        background: #f44336;
                                        color: #fff;
                                        min-width: 20px;
                                        height: 20px;
                                        border-radius: 10px;
                                        line-height: 19px;">
                                          !
                                        </span>
                                        Cliente
                                    </a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" data-toggle="tab" href="#account-type" role="tablist">
                                        <i class="material-icons">work</i>
                                        <span class="notification pulse" id="account-type-error" style="
                                            display: none;
                                            position: absolute;
                                            top: 4px;
                                            border: 1px solid #fff;
                                            font-size: 9px;
                                            background: #f44336;
                                            color: #fff;
                                            min-width: 20px;
                                            height: 20px;
                                            border-radius: 10px;
                                            line-height: 19px;">
                                              !
                                            </span>
                                        Tipo de cuenta
                                    </a>
                                </li>
                            </ul>
                            <div class="tab-content tab-space tab-subcategories">
                                <div class="tab-pane active show" id="base-info">
                                    <div class="row">
                                        <label class="col-sm-2 col-form-label">CBU</label>
                                        <div class="col-sm-10">
                                            <div class="form-group">
                                                <input type="number" class="form-control" value="${ account.CBU }" disabled>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-sm-2 col-form-label">Número de cuenta</label>
                                        <div class="col-sm-10">
                                            <div class="form-group">
                                                <input type="number" name="accountNumber" class="form-control" placeholder="Ingrese número de cuenta" value="${ account.accountNumber }">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <label class="col-sm-2 col-form-label">Alias</label>
                                        <div class="col-sm-10">
                                            <div class="form-group">
                                                <input type="text" name="alias" class="form-control" placeholder="Ingrese el alias del usuario" value="${ account.alias }">
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
                                                                <input class="form-check-input" type="radio" name="customer" value="${ customer.id }"
                                                                    ${ account.customer.id.equals(customer.id) ? "checked" : "" }>
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
                                        <table id="datatables2" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
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
                                                                <input class="form-check-input" type="radio" name="accountType" value="${ accountType.id }"
                                                                    ${ account.accountType.id.equals(accountType.id) ? "checked" : "" }>
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
                                    <button type="submit" class="btn btn-fill btn-rose" style="float: right;">Editar cuenta</button>
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
    function onSubmit() {
        const validations = [];
        validations.push(document.getElementsByName("accountNumber")[0].value !== "");
        validations.push(document.getElementsByName("alias")[0].value !== "");
        validations.push(Array.from(document.getElementsByName("customer")).some(input => input.checked));
        validations.push(Array.from(document.getElementsByName("accountType")).some(input => input.checked));

        const [accountNumberValidated, aliasValidated, customerValidated, accountTypeValidated] = validations;

        toggleError(accountNumberValidated && aliasValidated, "base-info-error");
        toggleError(customerValidated, "customer-error");
        toggleError(accountTypeValidated, "account-type-error");

        return ! validations.some(validation => ! validation); // Si alguna validación es falsa, no puede submitear.
    }

    const toggleError = (isValid, id) => {
        const selector = $("#" + id);

        isValid ? selector.hide("slow") : selector.fadeIn("slow");
    }
</script>