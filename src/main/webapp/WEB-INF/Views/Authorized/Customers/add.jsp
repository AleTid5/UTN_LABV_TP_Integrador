<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <form method="POST" action="" class="form-horizontal">
      <div class="row">
        <div class="col-md-12">
          <div class="card ">
            <div class="card-header card-header-rose card-header-text">
              <div class="card-text">
                <h4 class="card-title">Agregar Cliente</h4>
              </div>
            </div>
            <div class="card-body ">
              <div class="row">
                <label class="col-sm-2 col-form-label">DNI</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input required type="number" name="dni" class="form-control" placeholder="Ingrese DNI">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Nombre de usuario</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input type="text" required name="userName" class="form-control" placeholder="Ingrese el nombre de usuario">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Nombre</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input type="text" required name="name" class="form-control" placeholder="Ingrese nombre">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Apellido</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input type="text" required name="lastName" class="form-control" placeholder="Ingrese apellido">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">E-Mail</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input required type="email" name="email" class="form-control" placeholder="Ingrese el e-mail">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Fecha Nacimiento</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input type="text" required name="bornDate" class="form-control datepicker" placeholder="Ingrese fecha de nacimiento">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Dirección</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input type="text" required name="address" class="form-control" placeholder="Ingrese la dirección">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Monto máximo de préstamos</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <input type="number" required name="maxLoanAmount" class="form-control" placeholder="Ingrese el monto máximo">
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Pais</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <select required name="country" class="selectpicker" data-live-search="true" data-style="select-with-transition" onchange="onCountryChange()">
                      <option value="" selected>Seleccione un país</option>
                      <c:forEach var="country" items="${ countries }">
                        <option value="${ country.id }">
                            ${ country.name }
                        </option>
                      </c:forEach>
                    </select>
                  </div>
                </div>
              </div>
              <div class="row hidden" id="province">
                <label class="col-sm-2 col-form-label">Provincia</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <select required name="province" class="selectpicker" data-live-search="true" data-style="select-with-transition" onchange="onProvinceChange()">
                    </select>
                  </div>
                </div>
              </div>
              <div class="row hidden" id="locality">
                <label class="col-sm-2 col-form-label">Localidad</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <select required name="locality" class="selectpicker" data-live-search="true" data-style="select-with-transition">

                    </select>
                  </div>
                </div>
              </div>
              <div class="row">
                <label class="col-sm-2 col-form-label">Género</label>
                <div class="col-sm-10">
                  <div class="form-group">
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="gender" value="M" checked=""> Masculino
                        <span class="circle">
                              <span class="check"></span>
                            </span>
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="gender" value="F"> Femenino
                        <span class="circle">
                              <span class="check"></span>
                            </span>
                      </label>
                    </div>
                    <div class="form-check">
                      <label class="form-check-label">
                        <input class="form-check-input" type="radio" name="gender" value="O"> Otro
                        <span class="circle">
                              <span class="check"></span>
                            </span>
                      </label>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="card-footer" style="margin: 0 0px 10px;">
              <div class="row" style="width: 100%">
                <div class="col-2" style="padding-left: 30px;">
                  <a href="${request.getContextPath()}/UTN_LABV_TP_Integrador/customers" class="btn btn-fill btn-dark">Cancelar</a>
                </div>
                <div class="offset-6 col-2 offset-sm-8 col-sm-2" style="padding-right: 5px;">
                  <button type="submit" class="btn btn-fill btn-rose" style="float: right;">Finalizar</button>
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
  md.initFormExtendedDatetimepickers();

  const onCountryChange = () => {
    $("#province").removeClass("hidden");
    $("#locality").addClass("hidden");

    $.ajax({
      url: "${request.getContextPath()}/UTN_LABV_TP_Integrador/locations/provinces/" + $('[name="country"]').val(),
      success: provinces => {
        if (! provinces || ! provinces.status) return;

        $('[name="province"]')
                .html('<option value="" selected>Seleccione una provincia</option>' +
                        provinces.data.map(province => '<option value="' + province.id + '">' + province.name + '</option>').join(""))
                .selectpicker('refresh')
      }
    });
  }

  const onProvinceChange = () => {
    $("#locality").removeClass("hidden");

    $.ajax({
      url: "${request.getContextPath()}/UTN_LABV_TP_Integrador/locations/localities/" + $('[name="province"]').val(),
      success: localities => {
        if (! localities || ! localities.status) return;

        $('[name="locality"]')
                .html('<option value="" selected>Seleccione una localidad</option>' +
                        localities.data.map(locality => '<option value="' + locality.id + '">' + locality.name + '</option>').join(""))
                .selectpicker('refresh')
      }
    });
  }
</script>