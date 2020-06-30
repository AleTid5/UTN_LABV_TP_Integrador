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
            <h4 class="card-title">Listado de clientes</h4>
            <c:if test="true">
              <a href="customers/add">Añadir cliente</a>
            </c:if>
          </div>
          <div class="card-body">
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
                      <a href="customers/edit/${ customer.id }" class="btn btn-link btn-warning btn-just-icon edit">
                        <i class="material-icons">dvr</i>
                      </a>
                      <button onclick="onRemove(${ customer.id })" class="btn btn-link btn-danger btn-just-icon remove">
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
  window.history.replaceState({}, document.title, "/TP_L5_GRUPO_1/customers")

  onRemove = id => {
    Swal.fire({
      title: '¿Está seguro de eliminar al usuario?',
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
          url: '${request.getContextPath()}/TP_L5_GRUPO_1/customers/delete/' + id,
          type: 'POST',
          data: {
            id
          },
          success: function (data) {
            return data && data.status ? handleSuccess(id) : handleError();
          }
        });
      }
    });
  };

  handleSuccess = id => {
    $('#datatables').DataTable().rows('#customer-' + id).remove().draw();

    Swal.fire({
      icon: 'success',
      title: '¡Eliminado!',
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