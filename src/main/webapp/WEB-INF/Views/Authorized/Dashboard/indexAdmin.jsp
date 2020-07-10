<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="layout" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<layout:authorized>
  <jsp:body>
    <div class="row">
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="card card-stats">
          <div class="card-header card-header-success card-header-icon">
            <div class="card-icon">
              <i class="material-icons">add_box</i>
            </div>
            <p class="card-category">Préstamos aprobados</p>
            <h3 class="card-title">${ approvedLoans }</h3>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">date_range</i> En el año
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="card card-stats">
          <div class="card-header card-header-danger card-header-icon">
            <div class="card-icon">
              <i class="material-icons">indeterminate_check_box</i>
            </div>
            <p class="card-category">Préstamos rechazados</p>
            <h3 class="card-title">${ rejectedLoans }</h3>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">date_range</i> En el año
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="card card-stats">
          <div class="card-header card-header-info card-header-icon">
            <div class="card-icon">
              <i class="material-icons">people_alt</i>
            </div>
            <p class="card-category">Clientes totales</p>
            <h3 class="card-title">${ customers }</h3>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">date_range</i> Desde el inicio
            </div>
          </div>
        </div>
      </div>
      <div class="col-lg-3 col-md-6 col-sm-6">
        <div class="card card-stats">
          <div class="card-header card-header-warning card-header-icon">
            <div class="card-icon">
              <i class="material-icons">compare_arrows</i>
            </div>
            <p class="card-category">Movimientos generados</p>
            <h3 class="card-title">${ movements }</h3>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">date_range</i> Desde el inicio
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        <div class="card card-chart">
          <div class="card-header card-header-success" data-header-animation="true">
            <div class="ct-chart" id="dailySalesChart" style="height: 150px"></div>
          </div>
          <div class="card-body">
            <div class="card-actions">
              <button type="button" class="btn btn-danger btn-link fix-broken-card">
                <i class="material-icons">build</i> Fix Header!
              </button>
              <div class="row">
                <div class="col-4"><input type="text" required name="from" class="form-control datepicker" placeholder="Ingrese fecha desde"></div>
                <div class="col-4"><input type="text" required name="to" class="form-control datepicker" placeholder="Ingrese fecha hasta"></div>
                <div class="col-4">
                  <button type="button" class="btn btn-info btn-link mt-4" rel="tooltip" data-placement="bottom" title="Actualizar" onclick="refreshDashboard()">
                    <i class="material-icons" style="font-size: 30px">refresh</i>
                  </button>
                </div>
              </div>
            </div>
            <h4 class="card-title">Cuentas creadas</h4>
            <p class="card-category">
              <span class="text-success" id="color"><i class="fa fa-long-arrow-up" id="arrow"></i> <span id="percentage">0</span>% </span> con respecto al período anterior.</p>
          </div>
          <div class="card-footer">
            <div class="stats">
              <i class="material-icons">access_time</i> Última actualización&nbsp;<b id="updated_time"></b>
            </div>
          </div>
        </div>
      </div>
    </div>
  </jsp:body>
</layout:authorized>
<script>
  md.initFormExtendedDatetimepickers();

  let dailySalesChart = null;

  const setDashboardData = (from, to) => {
    $.ajax({
      url: '${request.getContextPath()}/TP_L5_GRUPO_1/dashboard/createdAccounts',
      data: {
        from,
        to
      },
      success: data => {
        const labels = Object.keys(data.data)
        const series = Object.keys(data.data).map(createdDate => data.data[createdDate]);

        setDashboardPercentage(series[series.length - 2], series[series.length - 1]);

        dailySalesChart = new Chartist.Line('.ct-chart', {
          labels,
          series: [
            series
          ]
        }, {
          low: 0,
          showArea: true
        });
      }
    })
  }

  const setDashboardPercentage = async (lastMonth, actualMonth) => {
    const value = ((actualMonth - lastMonth) / lastMonth) * 100;
    const date = new Date();

    if (value > 0) {
      $("#color").removeClass().addClass("text-success");
      $("#arrow").removeClass().addClass("fa fa-long-arrow-up");
    } else if (value < 0) {
      $("#color").removeClass().addClass("text-danger");
      $("#arrow").removeClass().addClass("fa fa-long-arrow-down");
    } else {
      $("#color").removeClass().addClass("text-info");
      $("#arrow").removeClass().addClass("fa fa-long-arrow-right");
    }

    $('#updated_time').text(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds())
    $("#percentage").text(isNaN(value) ? 0 : Math.round((value + Number.EPSILON) * 100) / 100);
  }

  setDashboardData("2020-01-01", "2020-12-12");

  const refreshDashboard = () => {
    let from = $('[name="from"]').val();
    let to = $('[name="to"]').val();
    const from_date = Date.parse(from);
    const to_date = Date.parse(to);

    if (! (! isNaN(from_date) && ! isNaN(to_date) && to_date >= from_date)) {
      return $('#app').prepend('<div class="alert alert-danger" style="z-index: 10">' +
              '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
              '<i class="material-icons">close</i>' +
              '</button>' +
              '<span>' +
              '<b>Ups.. 🤦‍️</b>' +
              '<br>' +
              'Lo sentimos, la fecha ingresada es inválida' +
              '</span>' +
              '</div>');
    }

    from = from.substring(6, 10) + "-" + from.substring(0, 2)+ "-" + from.substring(3,5) ;
    to = to.substring(6, 10) + "-" + to.substring(0, 2) + "-" + to.substring(3,5);

    setDashboardData(from, to);
  }

  md.startAnimationForLineChart(dailySalesChart);
</script>