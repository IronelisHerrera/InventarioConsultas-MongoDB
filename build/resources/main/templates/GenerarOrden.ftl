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
    <div style="margin-top: 75px">
        <form method="post" action="/AgregarDetalle">
                <div class="form-group">
                    <label for="fechaOrden" >Fecha requerida </label>
                    <input id="date" class="mb-2" type="date" id="fechaOrden" name="fechaOrden">
                </div>

                <div class="form-group" style="display: inline">
                    <label for="componentePC">Componente </label>
                    <select class="mr-4" name="componentePC">
                    <#list listaNombres as nombre>
                        <option value="${nombre}">${nombre}</option>
                    </#list>
                    </select>
                </div>
                &nbsp;&nbsp;
                <div class="form-group mb-2" style="display: inline">
                    <label for="cantidadcompPC">Cantidad componentes </label>
                    <input type="number" id="cantidadcompPC" name="cantidadcompPC">
                </div>

                <input type="submit" class="ml-3 btn btn-outline-primary" style="display: inline" value="Agregar">
        </form>
    </div>

    <div class="bg-light text-center text-white mt-4" style="padding: 80px">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th>Componente</th>
                <th>Descripcion</th>
                <th>Cantidad</th>
            </tr>
            </thead>
            <tbody>
                <#if listaDetalles ??>
                    <#assign count=1>
                    <#list listaDetalles as el>
                        <tr>
                            <td scope="row">${count}</td>
                            <td>${el.componente}</td>
                            <td>${el.descripcion}</td>
                            <td>${el.cantidad}</td>
                        </tr>
                        <#assign count++>
                    </#list>
                </#if>
            </tbody>
        </table>
    </div>

    <form method="get" class="mt-3" action="/Ordenes">
        <input type="submit" class="mt-4 btn btn-primary" style="margin-left: 300px" value="Generar orden">
    </form>

    <!-- /.row -->

</div>
<!-- /.container -->

</body>

</html>