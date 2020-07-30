<!DOCTYPE html>
<html lang="en">


<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Movimiento</title>
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
        <form method="post" action="/AgregarMovimiento">

            <div class="form-group">
                <label for="codigoMovimiento">Codigo movimiento</label>
                <input type="text" class="form-control" id="codigoMovimiento" name="codigoMovimiento" value='${movimiento}' readonly>
            </div>

            <div class="form-group">
                <label for="codigoAlmacen">Codigo almacen</label>
                <select name="codigoAlmacen">
                    <option value="1" selected>1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>

            <div class="form-group">
                <label for="tipoMovimiento">Tipo movimiento</label>
                <select name="tipoMovimiento">
                    <option value="ENTRADA">ENTRADA</option>
                    <option value="SALIDA" selected>SALIDA</option>
                </select>
            </div>

            <div class="form-group">
                <label for="codigoArticulo">Codigo articulo</label>
                <select name="codigoArticulo">
                    <#list codigos as codigo>
                        <option value="${codigo}">${codigo}</option>
                    </#list>
                </select>
            </div>

            <div class="form-group">
                <label for="cantidad" class="text-dark">Cantidad</label>
                <input type="number" class="form-control"  id="cantidad" name="cantidad" placeholder="Cantidad" required>
            </div>

            <div class="form-group">
                <label for="unidad">Unidad</label>
                <input type="text" class="form-control"  id="unidad" name="unidad" placeholder="Eje: U" required>
            </div>

            <input type="submit" class="btn btn-primary" value="Agregar movimiento">

        </form>
    </div>
</div>

</body>

</html>