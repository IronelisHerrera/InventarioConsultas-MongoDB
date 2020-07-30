<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Generar orden</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.js" type="text/javascript"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/bootstrap.css" rel="stylesheet">
</head>

<body>

<!-- Navigation -->
<div>
    <nav class="navbar-light bg-light" style="font-family: 'Lato Semibold'">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">
                    <img src="/templates/img/inventory.png" width="30" height="30" alt="MANEJO INVENTARIO">

                </a>
                <!--<a class="navbar-brand" href="/">MANEJO INVENTARIO</a>-->
            </div>
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/Agregar">Registrar movimiento</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Generar">Agregar orden</a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<div class="container" style="margin-top: 100px">

    <hr>
    <p class="h2" style="color: #007bff">Orden 1</p>
    <hr>

    <div class="form-group">
        <label>No. Orden </label>
        <input type="text" value="1" class="form-control w-25" style="display: inline" readonly>

        <label style="margin-left: 146px;">Fecha orden: </label>
        <input type="text" class="form-control w-25" value="${fecha1}" style="display: inline" readonly>
        </br>
        </br>

        <label>Suplidor: </label>
        <input type="text" class="form-control w-25" value="${suplidor1}" style="display: inline" readonly>

        <label style="margin-left: 165px;">Monto total: </label>
        <input type="text" class="form-control w-25"  value="${monto1}" style="display: inline" readonly>
        </br>
        </br>
    </div>
    </br>
    <div class="bg-light text-center text-white mt-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th>Componente</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Importe</th>
            </tr>
            </thead>
            <tbody>
            <#if orden1 ??>
                <#assign count=1>
                <#list orden1 as or1>
                    <tr>
                        <td scope="row">${count}</td>
                        <td>${or1.componente}</td>
                        <td>${or1.descripcion}</td>
                        <td>${or1.cantidad}</td>
                        <td>${or1.precio}</td>
                        <td>${or1.importe}</td>
                    </tr>
                    <#assign count++>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

    <hr>
    <p class="h2" style="color: #007bff">Orden 2</p>
    <hr>

    <#-- Orden #2-->
    <div class="form-group">
        <label>Número orden:</label>
        <input type="text" value="2" class="form-control w-25" style="display: inline" readonly>

        <label style="margin-left: 146px;">Fecha orden:</label>
        <input type="text" value="${fecha2}" class="form-control w-25" style="display: inline" readonly>
        </br>
        </br>

        <label>Suplidor </label>
        <input type="text" class="form-control w-25" value="${suplidor2}" style="display: inline" readonly>

        <label style="margin-left: 165px;">Monto total: </label>
        <input type="text" class="form-control w-25"  value="${monto2}" style="display: inline" readonly>
        </br>
        </br>
    </div>
    </br>
    <div class="bg-light text-center text-white mt-4">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th>Componente</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
                <th>Precio</th>
                <th>Importe</th>
            </tr>
            </thead>
            <tbody>
            <#if orden2 ??>
                <#assign count=1>
                <#list orden2 as or2>
                    <tr>
                        <td scope="row">${count}</td>
                        <td>${el.componente}</td>
                        <td>${or2.componente}</td>
                        <td>${or2.descripcion}</td>
                        <td>${or2.cantidad}</td>
                        <td>${or2.precio}</td>
                        <td>${or2.importe}</td>
                    </tr>
                    <#assign count++>
                </#list>
            </#if>
            </tbody>
        </table>
    </div>

</div>
</body>

</html>