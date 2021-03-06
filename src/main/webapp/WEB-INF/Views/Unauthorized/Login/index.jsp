<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:unauthorized>
  <jsp:body>
    <form class="form" method="POST" action="">
      <div class="card card-login">
        <div class="card-header card-header-rose text-center">
          <h4 class="card-title">Iniciar sesión</h4>
        </div>
        <div class="card-body">
          <p class="card-description text-center">Ingrese sus credenciales</p>
          <span class="bmd-form-group">
                    <div class="input-group">
                      <div class="input-group-prepend">
                        <span class="input-group-text">
                          <i class="material-icons">email</i>
                        </span>
                      </div>
                      <input required type="email" name="email" class="form-control" placeholder="Email...">
                    </div>
                  </span>
          <span class="bmd-form-group">
                    <div class="input-group">
                      <div class="input-group-prepend">
                        <span class="input-group-text">
                          <i class="material-icons">lock_outline</i>
                        </span>
                      </div>
                      <input required type="password" name="password" class="form-control" placeholder="Contaseña...">
                    </div>
                  </span>
        </div>
        <div class="card-footer justify-content-center">
          <button type="submit" class="btn btn-rose btn-link btn-lg">Acceder</button>
        </div>
      </div>
    </form>
  </jsp:body>
</layout:unauthorized>
<script>
  window.history.replaceState({}, document.title, "/TP_L5_GRUPO_1/")
</script>