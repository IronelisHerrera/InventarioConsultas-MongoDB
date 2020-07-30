<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Bienvenidos</title>
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
<!--
<nav class="navbar navbar-expand-lg navbar-dark bg-primary fixed-top">
    <div class="container">
        <a class="navbar-brand" href="#">INVENTARIO</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="/">Inicio
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Agregar">Agregar movimiento</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Generar">Generar Orden</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
-->
<div>
    <nav class="navbar-light bg-light" style="font-family: 'Lato Semibold'">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">
                    <img src="/templates/img/inventory.png" width="30" height="30" alt="MANEJO INVENTARIO">

                </a>

            </div>
            <ul class="nav navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/Agregar">Agregar orden</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/Generar">Registrar movimiento</a>
                </li>
            </ul>
        </div>
    </nav>
</div>

<!-- Page Content -->
<div class="container" style="padding: 80px">

    <!-- Page Features -->
    <div class="bg-light text-center text-white">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th>Movimiento</th>
                <th>Almacen</th>
                <th>Tipo movimiento</th>
                <th>Articulo</th>
                <th>Cantidad</th>
                <th>Unidad</th>
            </tr>
            </thead>
            <tbody>
            <#if listaMovimientos ??>
                <#assign count=1>
                <#list listaMovimientos as mov>
                    <tr>
                        <td scope="row">${count}</td>
                        <td>${mov.codigoMovimiento}</td>
                        <td>${mov.codigoAlmacen}</td>
                        <td>${mov.tipoMovimiento}</td>
                        <td>${mov.codigoArticulo}</td>
                        <td>${mov.cantidad}</td>
                        <td>${mov.unidad}</td>
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