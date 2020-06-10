<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
    <jsp:body>
        <form method="POST" action="" class="form-horizontal" validate>
            <div class="row">
                <div class="col-md-12">
                    <div class="card ">
                        <div class="card-header card-header-rose card-header-text">
                            <div class="card-text">
                                <h4 class="card-title">Agregar Transferencia</h4>
                            </div>
                        </div>
                        <div class="card-body ">
                            <div class="row">
                                <label class="col-sm-2 col-form-label">DNI</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="number" name="DNI" class="form-control" placeholder="Ingrese DNI">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Nombre</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="name" class="form-control" placeholder="Ingrese nombre">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Apellido</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="lastname" class="form-control" placeholder="Ingrese apellido">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Fecha Nacimiento</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="borndate" class="form-control datepicker" placeholder="Ingrese fecha de nacimiento">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Dirección</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="text" name="address" class="form-control" placeholder="Ingrese la dirección">
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Localidad</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <select required name="location" class="selectpicker" data-style="select-with-transition">
                                            <c:forEach var="location" items="${ locations }">
                                                <option value="${ location.getId() }"
                                                    ${! location.getStatus().equals("A") ? "disabled": ""}>
                                                        ${ location.getName() }
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <label class="col-sm-2 col-form-label">Provincia</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <select required name="province" class="selectpicker" data-style="select-with-transition">
                                            <c:forEach var="province" items="${ provinces }">
                                                <option value="${ province.getId() }"
                                                        ${! province.getStatus().equals("A") ? "disabled": ""}>
                                                        ${ province.getName() }
                                                </option>
                                            </c:forEach>
                                        </select>
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
                                <label class="col-sm-2 col-form-label">Teléfono</label>
                                <div class="col-sm-10">
                                    <div class="form-group">
                                        <input required type="number" name="phoneNumber" class="form-control" placeholder="Ingrese el teléfono">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer" style="margin: 0 0px 10px;">
                            <div class="row" style="width: 100%">
                                <div class="col-2" style="padding-left: 30px;">
                                    <a href="../teachers" class="btn btn-fill btn-dark">Cancelar</a>
                                </div>
                                <div class="offset-6 col-2 offset-sm-8 col-sm-2" style="padding-right: 5px;">
                                    <button type="submit" class="btn btn-fill btn-rose" style="float: right;">Finalizar</button>
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
</script>