<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>

<layout:authorized>
    <div class="wrapper wrapper-full-page">
        <div class="page-header error-page header-filter" style="background-image: url('${ assetsPath }/img/bg-pricing.jpg')">
            <div class="content-center">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="title">404</h1>
                        <h2>Parece que ésta página no existe :(</h2>
                        <h4 style="background-color: rgba(0, 0, 0, 0.3)">
                            <a href="${ mainPath }/dashboard" class="text-gray">Volver a la página principal</a>
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</layout:authorized>