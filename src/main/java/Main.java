import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.joda.time.DateTime;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static spark.Spark.staticFileLocation;
import static spark.debug.DebugScreen.enableDebugScreen;


public class Main {
    public static MovimientoInventario movimientoInventario = new MovimientoInventario();

    public static List<DetalleOrden> listaDetalles = new ArrayList<>();

    public static List<OrdenCompra> listaDeOrdenesComprasFinal = new ArrayList<>();

    public static int montoTotal1 = 0;
    public static int montoTotal2 = 0;

    public static int max1 = 0;
    public static int max2 = 0;

    public static Date fec1;
    public static Date fec2;

    public static void main(String[] args) {
        enableDebugScreen();

        Articulos articulos = new Articulos();
        articulos.insertarArticulo(new BasicDBObject("codigoArticulo", "TEC-001")
                        .append("descripcion", "TECLADO")
                        .append("almacenYBalance", Arrays.asList(
                                new BasicDBObject("codigoAlmacen", 1)
                                        .append("balanceActual", 500),
                                new BasicDBObject("codigoAlmacen", 2)
                                        .append("balanceActual", 100),
                                new BasicDBObject("codigoAlmacen", 3)
                                        .append("balanceActual", 50)))
                        .append("unidadCompra", "unidades"));

        articulos.insertarArticulo(new BasicDBObject("codigoArticulo", "MOU-001")
                .append("descripcion", "MOUSE")
                .append("almacenYBalance", Arrays.asList(
                        new BasicDBObject("codigoAlmacen", 1)
                                .append("balanceActual", 200),
                        new BasicDBObject("codigoAlmacen", 2)
                                .append("balanceActual", 150),
                        new BasicDBObject("codigoAlmacen", 3)
                                .append("balanceActual", 100)))
                .append("unidadCompra", "unidades"));

        articulos.insertarArticulo(new BasicDBObject("codigoArticulo", "MON-001")
                .append("descripcion", "MONITOR")
                .append("almacenYBalance", Arrays.asList(
                        new BasicDBObject("codigoAlmacen", 1)
                                .append("balanceActual", 200),
                        new BasicDBObject("codigoAlmacen", 2)
                                .append("balanceActual", 150),
                        new BasicDBObject("codigoAlmacen", 3)
                                .append("balanceActual", 100)))
                .append("unidadCompra", "unidades"));

        // Suplidor 1
        ArticuloSuplidor articuloSuplidor = new ArticuloSuplidor();
        articuloSuplidor.insertarArtSuplidor(new BasicDBObject("codigoArticulo", "TEC-001")
                .append("codigoSuplidor", 1)
                .append("tiempoEntrega", 1)
                .append("precioCompra", 350));

        articuloSuplidor.insertarArtSuplidor(new BasicDBObject("codigoArticulo", "MON-001")
                .append("codigoSuplidor", 1)
                .append("tiempoEntrega", 2)
                .append("precioCompra", 3500));

        // Suplidor 2
        articuloSuplidor.insertarArtSuplidor(new BasicDBObject("codigoArticulo", "MOU-001")
                .append("codigoSuplidor", 2)
                .append("tiempoEntrega", 3)
                .append("precioCompra", 200));

        articuloSuplidor.insertarArtSuplidor(new BasicDBObject("codigoArticulo", "TEC-001")
                .append("codigoSuplidor", 2)
                .append("tiempoEntrega", 2)
                .append("precioCompra", 350));

        articuloSuplidor.insertarArtSuplidor(new BasicDBObject("codigoArticulo", "MON-001")
                .append("codigoSuplidor", 2)
                .append("tiempoEntrega", 3)
                .append("precioCompra", 3500));


        getRutas();
    }

    public static void getRutas() {
        staticFileLocation("/");

        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(Main.class, "/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        Spark.get("/", (request, response) -> {

            List<DBObject> todos = movimientoInventario.todos();

            Template plantillaPaginaInicio = configuration.getTemplate("Home.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            ArrayList<MovimientoInventario> lista = new ArrayList<>();

            for (DBObject objeto : todos) {
                MovimientoInventario movInt1 = new MovimientoInventario(
                        (int) objeto.get("codigoMovimiento"),
                        (int) objeto.get("codigoAlmacen"),
                        (String) objeto.get("tipoMovimiento"),
                        (String) objeto.get("codigoArticulo"),
                        (int) objeto.get("cantidad"),
                        (String) objeto.get("unidad")
                );
                lista.add(movInt1);
            }

            attributes.put("listaMovimientos", lista);
            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.get("/Agregar", (request, response) -> {

            Template plantillaPaginaInicio = configuration.getTemplate("RegistroMovimiento.ftl");
            StringWriter writer = new StringWriter();

            Map<String, Object> attributes = new HashMap<>();
            ArrayList<MovimientoInventario> lista = new ArrayList<>();

            Articulos articulos = new Articulos();
            List<DBObject> listaArticulos = articulos.getAll();

            List<String> codigos = new ArrayList<>();

            for(DBObject obj: listaArticulos){
                codigos.add(obj.get("codigoArticulo").toString());
            }

            attributes.put("movimiento", movimientoInventario.contador() + 1);
            attributes.put("codigos", codigos);

            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.get("/Generar", (request, response) -> {

            Template plantillaPaginaInicio = configuration.getTemplate("GenerarOrden.ftl");
            StringWriter writer = new StringWriter();

            List<OrdenCompra> ordenCompras = new ArrayList<>();

            Map<String, Object> attributes = new HashMap<>();
            ArrayList<MovimientoInventario> lista = new ArrayList<>();

            Articulos articulos = new Articulos();
            List<DBObject> listaArticulos = articulos.getAll();

            List<String> codigos = new ArrayList<>();
            List<String> nombres = new ArrayList<>();

            for(DBObject obj: listaArticulos){
                codigos.add(obj.get("codigoArticulo").toString());
                nombres.add(obj.get("descripcion").toString());
            }

            // Parametros de la orden

            ArticuloSuplidor articuloSuplidor = new ArticuloSuplidor();

            int min = 9999;
            String suplidor = "";
            int precio = 0;
            String componente = "";
            List<String> suplidores = new ArrayList<>();
            List<DetalleComponenteOrden> listaDeComponentesPorOrden = new ArrayList<>();

            for(DetalleOrden detalleOrden1: listaDetalles)
            {
                min = 9999;
                for(DBObject obj2: articuloSuplidor.getSuplidorPorArticulo(detalleOrden1.getComponente()))
                {
                    if ((int) obj2.get("tiempoEntrega") < min)
                    {
                        min = (int) obj2.get("tiempoEntrega");
                        suplidor = "Suplidor-" + obj2.get("codigoSuplidor");
                        precio = Integer.parseInt(String.valueOf(obj2.get("precioCompra")));
                    }
                }
                listaDeComponentesPorOrden.add(new DetalleComponenteOrden(
                        detalleOrden1.getComponente(),
                        detalleOrden1.getDescripcion(),
                        detalleOrden1.getCantidad(),
                        precio,
                        precio * detalleOrden1.getCantidad(), suplidor));
            }

            int maxi1 = 0;
            int maxi2 = 0;
            for(DetalleOrden detalleOrden1: listaDetalles)
            {
                maxi1 = 0;
                maxi2 = 0;
                for(DBObject obj3: articuloSuplidor.getSuplidorPorArticulo(detalleOrden1.getComponente()))
                {
                    if ((int) obj3.get("tiempoEntrega") > maxi1 && (int) obj3.get("codigoSuplidor") == 1)
                    {
//                        System.out.println("TIEMPO ENTREGA 1: " + obj3.get("tiempoEntrega"));
                        maxi1 = (int) obj3.get("tiempoEntrega");
                    }
                    if ((int) obj3.get("tiempoEntrega") > maxi2 && (int) obj3.get("codigoSuplidor") == 2)
                    {
                        maxi2 = (int) obj3.get("tiempoEntrega");
                    }
                }
            }

            if(maxi1 > 0) {
                max1 = maxi1;
            }
            if(maxi2 > 0) {
                max2 = maxi2;
            }

            List<DetalleComponenteOrden> lista1 = new ArrayList<>();
            List<DetalleComponenteOrden> lista2 = new ArrayList<>();

            int montoNoFinal1 = 0;
            int montoNoFinal2 = 0;

            for(DetalleComponenteOrden detalleComponenteOrden : listaDeComponentesPorOrden){
                if(detalleComponenteOrden.getSuplidor().equals("Suplidor-1")){
                    lista1.add(detalleComponenteOrden);
                }
                if(detalleComponenteOrden.getSuplidor().equals("Suplidor-2")){
                    lista2.add(detalleComponenteOrden);
                }
            }
            int contador = 1;
            if(!lista1.isEmpty()) {
                ordenCompras.add(new OrdenCompra(1, lista1));
            }
            if(!lista2.isEmpty()){
                ordenCompras.add(new OrdenCompra(2, lista2));
            }

            listaDeOrdenesComprasFinal = ordenCompras;

            for(OrdenCompra ordenCompra: listaDeOrdenesComprasFinal){
                System.out.println("ORDEN " + contador);
                for(DetalleComponenteOrden detalleComponenteOrden : ordenCompra.getListaDeComponentes()){
                    System.out.println(detalleComponenteOrden.getComponente());
                    System.out.println(detalleComponenteOrden.getDescripcion());
                    System.out.println(detalleComponenteOrden.getCantidad());
                    System.out.println(detalleComponenteOrden.getPrecio());
                    System.out.println(detalleComponenteOrden.getSuplidor());
                    System.out.println(detalleComponenteOrden.getImporte());
                    if(contador == 1){
                        montoNoFinal1 += detalleComponenteOrden.getImporte();
                    } else if(contador == 2){
                        montoNoFinal2 += detalleComponenteOrden.getImporte();
                    }
                }
                contador += 1;
            }

            montoTotal1 = montoNoFinal1;
            montoTotal2 = montoNoFinal2;

            attributes.put("listaCodigos", codigos);
            attributes.put("listaNombres", nombres);

            attributes.put("listaDetalles", listaDetalles);

            plantillaPaginaInicio.process(attributes, writer);
            return writer;
        });

        Spark.get("/Ordenes", (request, response) -> {

            Template plantilla = configuration.getTemplate("Ordenes.ftl");
            StringWriter writer = new StringWriter();
            Map<String, Object> attributes = new HashMap<>();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String fecha1 = dateFormat.format(fec1);
            String fecha2 = dateFormat.format(fec2);

            if(listaDeOrdenesComprasFinal.size() > 0) {
                attributes.put("orden1", listaDeOrdenesComprasFinal.get(0).getListaDeComponentes());
                attributes.put("suplidor1", listaDeOrdenesComprasFinal.get(0).getListaDeComponentes().get(0).getSuplidor());
                attributes.put("monto1", montoTotal1);
                attributes.put("fecha1", fecha1);

                if(listaDeOrdenesComprasFinal.size() > 1) {
                    attributes.put("orden2", listaDeOrdenesComprasFinal.get(1).getListaDeComponentes());
                    attributes.put("suplidor2", listaDeOrdenesComprasFinal.get(1).getListaDeComponentes().get(0).getSuplidor());
                    attributes.put("monto2", montoTotal2);
                    attributes.put("fecha2", fecha2);
                }
            }
            plantilla.process(attributes, writer);
            return writer;
        });

        Spark.post("/AgregarDetalle", (request, response) -> {

            Articulos articulos = new Articulos();
            DBObject articulo = articulos.encontrarPorDescripcion(request.queryParams("componentePC"));

            DetalleOrden detalleOrden = new DetalleOrden(
                    (String)articulo.get("codigoArticulo"),
                    (String)articulo.get("descripcion"),
                    Integer.parseInt(request.queryParams("cantidadcompPC")));

            listaDetalles.add(detalleOrden);

            // Formatear la fecha
            if(!request.queryParams("fechaOrden").equals("")) {
                SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaAntesDeRestar = formatter1.parse(request.queryParams("fechaOrden"));
                Date fecha1 = new Date();
                Date fecha2 = new Date();

                if (max1 > 0) {
                    Date fechaDespuesDeRestar1 = new DateTime(fechaAntesDeRestar).minusDays(max1).toDate();
                    fecha1 = formatter1.parse(formatter1.format(fechaDespuesDeRestar1));
                    fec1 = fecha1;
                }

                if (max2 > 0) {
                    Date fechaDespuesDeRestar2 = new DateTime(fechaAntesDeRestar).minusDays(max2).toDate();
                    fecha2 = formatter1.parse(formatter1.format(fechaDespuesDeRestar2));
                    fec2 = fecha2;
                }
            }

            response.redirect("/Generar");
            return "";
        });

        Spark.post("/AgregarMovimiento", (request, response) -> {

            Articulos articulos = new Articulos();
            DBObject art = articulos.buscarArticulo(request.queryParams("codigoArticulo"));

            List<Integer> lista = articulos.conseguirAlmacenYBalance(request.queryParams("codigoArticulo"));
            int codigoAlmacen = Integer.parseInt(request.queryParams("codigoAlmacen"));
            int contador = 0;
            int nuevoBalance = 0;
            int balanceAQuitarOSumar = Integer.parseInt(request.queryParams("cantidad"));

            for(int x: lista){
                if(contador % 2 == 0){
                    if(x == codigoAlmacen){
                        if(request.queryParams("tipoMovimiento").equals("SALIDA")) {
                            nuevoBalance = lista.get(contador + 1) - balanceAQuitarOSumar;
                            break;
                        } else if(request.queryParams("tipoMovimiento").equals("ENTRADA")) {
                            nuevoBalance = lista.get(contador + 1) + balanceAQuitarOSumar;
                            break;
                        }
                    }
                }
                contador += 1;
            }

            int contador2 = 0;

            for(int y: lista){
                if(y == codigoAlmacen){
                    lista.remove(contador2);
                    lista.remove(contador2);
                    break;
                }
                contador2++;
            }

            System.out.println("LISTA DE ACTUALIZADOS:");
            System.out.println(codigoAlmacen + ", " + nuevoBalance);
            System.out.println("LISTA DE NO-ACTUALIZADOS: ");
            System.out.println(lista);
            System.out.println(lista.get(0));

            if(nuevoBalance >= 0){
                movimientoInventario.insertarMovInventario(
                        new BasicDBObject("codigoMovimiento", Integer.parseInt(request.queryParams("codigoMovimiento")))
                                .append("codigoAlmacen", Integer.parseInt(request.queryParams("codigoAlmacen")))
                                .append("tipoMovimiento", request.queryParams("tipoMovimiento"))
                                .append("codigoArticulo", request.queryParams("codigoArticulo"))
                                .append("cantidad", Integer.parseInt(request.queryParams("cantidad")))
                                .append("unidad", request.queryParams("unidad"))
                );

                String codigoArticulo = (String) art.get("codigoArticulo");
                String descripcion = (String) art.get("descripcion");
                List<DBObject> list = Arrays.asList(
                        new BasicDBObject("codigoAlmacen", codigoAlmacen)
                        .append("balanceActual", nuevoBalance),
                        new BasicDBObject("codigoAlmacen", lista.get(0))
                        .append("balanceActual", lista.get(1)),
                        new BasicDBObject("codigoAlmacen", lista.get(2))
                                .append("balanceActual", lista.get(3)));
                String unidadDeCompra = (String) art.get("unidadCompra");

                articulos.actualizarArticulo((BasicDBObject) art,
                        new BasicDBObject("codigoArticulo", codigoArticulo)
                        .append("descripcion", descripcion)
                        .append("almacenYBalance", list)
                        .append("unidadCompra", unidadDeCompra));

            } else {
                System.out.println("ERROR: NO SE PUEDE AGREGAR ESTE MOVIMIENTO.");
            }
            response.redirect("/");
            return "";
        });

    }
}
