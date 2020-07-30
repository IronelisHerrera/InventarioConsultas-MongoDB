import com.mongodb.*;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class OrdenCompra {

    int noCompra;
    List<DetalleComponenteOrden> listaDeComponentes;

    public OrdenCompra(int noCompra, List<DetalleComponenteOrden> listaDeComponentes) {
        this.noCompra = noCompra;
        this.listaDeComponentes = listaDeComponentes;
    }

    public int getNoCompra() {
        return noCompra;
    }

    public void setNoCompra(int noCompra) {
        this.noCompra = noCompra;
    }

    public List<DetalleComponenteOrden> getListaDeComponentes() {
        return listaDeComponentes;
    }

    public void setListaDeComponentes(List<DetalleComponenteOrden> listaDeComponentes) {
        this.listaDeComponentes = listaDeComponentes;
    }

    MongoClient mongoClient;
    {
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    DB database = mongoClient.getDB("sInventarioMongo");
    DBCollection ordenesCompras = database.getCollection("OrdenCompra");

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();

    DBObject ordcomp1 = new BasicDBObject("codigoOrdenCompra", 1)
            .append("codigoSuplidor", 1)
            .append("fechaOrden", dtf.format(now))
            .append("montoTotal", 2500)
            .append("", Arrays.asList(
                    new BasicDBObject("codigoArticulo", 1)
                            .append("cantidadOrdenada", 7)
                            .append("unidadCompra", "items")
                            .append("precioCompra", 100),
                    new BasicDBObject("codigoArticulo", 2)
                            .append("cantidadOrdenada", 5)
                            .append("unidadCompra", "lbs")
                            .append("precioCompra", 200),
                    new BasicDBObject("codigoArticulo", 3)
                            .append("cantidadOrdenada", 8)
                            .append("unidadCompra", "kg")
                            .append("precioCompra", 100)));

    public void insertarOrdenCompra(BasicDBObject ordenCompra){
        ordenesCompras.insert(ordenCompra);
    }

    public void eliminarOrdenCompra(BasicDBObject ordenCompra){ ;
        ordenesCompras.remove(ordenCompra);
    }

    public DBObject buscarOrdenCompra(int codigoSuplidor){
        DBCursor cursor = ordenesCompras.find(new BasicDBObject("codigoSuplidor", codigoSuplidor));
        return cursor.one();
    }

    public void actualizaOrdenCompra(BasicDBObject ordcomp1, BasicDBObject ordcomp2){
        ordenesCompras.update(ordcomp1, ordcomp2);
    }

    public int getCount() {
        int cantidad = 0;
        DBCursor cursor = ordenesCompras.find();
        DBObject obj = null;
        while (cursor.hasNext()) {
            obj = cursor.next();
            cantidad += 1;
        }
        return cantidad;
    }
}
