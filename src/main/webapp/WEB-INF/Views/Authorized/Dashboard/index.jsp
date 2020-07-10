<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <div class="row">
      <div class="col-lg-4 col-md-6 col-sm-6">
        <div class="card card-stats">
          <div class="card-header card-header-rose card-header-icon">
            <div class="card-icon">
              <i class="fa fa-line-chart"></i>
            </div>
            <p class="card-category">Cuentas abiertas</p>
            <h3 class="card-title">${ accounts.size() }</h3>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">date_range</i> Desde el inicio
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-6">
        <div class="card card-stats">
          <div class="card-header card-header-success card-header-icon">
            <div class="card-icon">
              <i class="material-icons">add_box</i>
            </div>
            <p class="card-category">Préstamos otorgados</p>
            <h3 class="card-title">${ approvedLoans }</h3>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">date_range</i> Desde el inicio
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-6">
        <div class="card card-stats">
          <div class="card-header card-header-info card-header-icon">
            <div class="card-icon">
              <i class="material-icons">compare_arrows</i>
            </div>
            <p class="card-category">Movimientos realizados</p>
            <h3 class="card-title">${ movements }</h3>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">update</i> Desde el inicio
            </div>
          </div>
        </div>
      </div>
    </div>
    <c:if test="${ accounts.size() > 0 }">
      <h3>Cuentas</h3>
    </c:if>
    <div class="row">
      <c:forEach items="${ accounts }" var="account">
        <div class="col-md-${ Math.round(12 / accounts.size()) } col-sm-6">
          <div class="card card-stats">
            <div class="card-header card-header-warning card-header-icon">
              <div class="card-icon">
                <i class="material-icons">store</i>
              </div>
              <p class="card-category">Balance</p>
              <h3 class="card-title">$${ account.balance }</h3>
            </div>
            <div class="card-footer">
              <div class="stats">
                <i class="material-icons">attach_money</i> ${ account.accountType }<br/>CBU: ${ account.CBU }
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </jsp:body>
</layout:authorized>