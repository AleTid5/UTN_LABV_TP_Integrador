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
            <h4 class="card-title">Listado de cuentas</h4>
            <c:if test="true">
              <a href="accounts/add">Añadir cuenta</a>
            </c:if>
          </div>
          <div class="card-body">
            <div class="material-datatables">
              <table id="datatables" class="table table-striped table-no-bordered table-hover" cellspacing="0" width="100%" style="width:100%">
                <thead>
                <tr>
                  <th>CBU</th>
                  <th>Alias</th>
                  <th>Cliente</th>
                  <th>Tipo de Cuenta</th>
                  <th>Numero de Cuenta</th>
                  <th></th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                  <th>CBU</th>
                  <th>Alias</th>
                  <th>Cliente</th>
                  <th>Tipo de Cuenta</th>
                  <th>Numero de Cuenta</th>
                  <th></th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach var="account" items="${ accounts }">
                  <tr id="account-${ account.CBU }">
                    <td>${ account.alias }</td>
                    <td>${ account.customer }</td>
                    <td>${ account.accountType.name }</td>
                    <td>${ account.accountNumber }</td>
                    <td>
                      <a href="accounts/edit/${ account.CBU }" class="btn btn-link btn-warning btn-just-icon edit">
                        <i class="material-icons">dvr</i>
                      </a>
                      <button onclick="onRemove(${ account.CBU })" class="btn btn-link btn-danger btn-just-icon remove">
                        <i class="material-icons">close</i>
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
  onRemove = CBU => {
    Swal.fire({
      title: '¿Está seguro de eliminar la cuenta?',
      text: "No podrá revertir la acción",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#52af50',
      cancelButtonColor: '#ea4a64',
      cancelButtonText: 'Cancelar',
      confirmButtonText: '¡Si, elimínalo!'
    }).then((result) => {
      if (result.value) {
        $.ajax({
          url: '${request.getContextPath()}/UTN_LABV_TP_Integrador/accounts/delete/' + CBU,
          type: 'POST',
          data: {
            CBU
          },
          success: function (data) {
            return data && data.status ? handleSuccess(CBU) : handleError();
          }
        });
      }
    });
  };

  handleSuccess = CBU => {
    $('#datatables').DataTable().rows('#account-' + CBU).remove().draw();

    Swal.fire({
      icon: 'success',
      title: '¡Eliminado!',
      text: 'La cuenta ha sido eliminada exitosamente.',
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