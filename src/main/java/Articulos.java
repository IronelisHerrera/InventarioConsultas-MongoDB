import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Articulos {

    String codigoArticulo;
    String descripcion;
    DBObject[] almacenYBalance;
    String unidadCompra;

    public Articulos(){

    }

    public Articulos(String codigoArticulo, String descripcion, DBObject[] almacenYBalance, String unidadCompra){
        this.codigoArticulo = codigoArticulo;
        this.descripcion = descripcion;
        this.almacenYBalance = almacenYBalance;
        this.unidadCompra = unidadCompra;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DBObject[] getAlmacenYBalance() {
        return almacenYBalance;
    }

    public void setAlmacenYBalance(DBObject[] almacenYBalance) {
        this.almacenYBalance = almacenYBalance;
    }

    public String getUnidadCompra() {
        return unidadCompra;
    }

    public void setUnidadCompra(String unidadCompra) {
        this.unidadCompra = unidadCompra;
    }

    MongoClient mongoClient;
    {
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    DB database = mongoClient.getDB("InventarioMongo");
    DBCollection articulos = database.getCollection("Articulos");

    DBObject art1 = new BasicDBObject("codigoArticulo", "TEC-001")
            .append("descripcion", "TECLADO")
            .append("almacenYBalance", Arrays.asList(
                    new BasicDBObject("codigoAlmacen", 1)
                            .append("balanceActual", 500),
                    new BasicDBObject("codigoAlmacen", 2)
                            .append("balanceActual", 100),
                    new BasicDBObject("codigoAlmacen", 3)
                            .append("balanceActual", 50)))
            .append("unidadCompra", "unidades");

    DBObject art2 = new BasicDBObject("codigoArticulo", "MOU-001")
            .append("descripcion", "MOUSE")
            .append("almacenYBalance", Arrays.asList(
                    new BasicDBObject("codigoAlmacen", 1)
                            .append("balanceActual", 200),
                    new BasicDBObject("codigoAlmacen", 2)
                            .append("balanceActual", 150),
                    new BasicDBObject("codigoAlmacen", 3)
                            .append("balanceActual", 100)))
            .append("unidadCompra", "unidades");

    DBObject art3 = new BasicDBObject("codigoArticulo", "MON-001")
            .append("descripcion", "MONITOR")
            .append("almacenYBalance", Arrays.asList(
                    new BasicDBObject("codigoAlmacen", 1)
                            .append("balanceActual", 200),
                    new BasicDBObject("codigoAlmacen", 2)
                            .append("balanceActual", 150),
                    new BasicDBObject("codigoAlmacen", 3)
                            .append("balanceActual", 100)))
            .append("unidadCompra", "unidades");

    public DBObject encontrarPorDescripcion(String descripcion){
        DBCursor cursor = articulos.find(new BasicDBObject("descripcion", descripcion));
        return cursor.one();
    }

    public void insertarArticulo(BasicDBObject articulo){
        articulos.insert(articulo);
    }

    public void eliminarArticulo(BasicDBObject articulo){ ;
        articulos.remove(articulo);
    }

    public DBObject buscarArticulo(String codigo){
        DBCursor cursor = articulos.find(new BasicDBObject("codigoArticulo", codigo));
        return cursor.one();
    }

    public void actualizarArticulo(BasicDBObject art1, BasicDBObject art2){
        articulos.update(art1, art2);
    }

    public List<Integer> conseguirAlmacenYBalance(String id) {
        List<Integer> resultados = new ArrayList<>();
        DBObject articulo1 = buscarArticulo(id);
        String[] arreglo = articulo1.get("almacenYBalance").toString().split(":");
        int contador = 0;
        List<Integer> codigosYBalances = new ArrayList<>();
        for(String str: arreglo) {
            String[] arreglo2 = str.split("}");
            String[] codigos = arreglo2[0].split(",");
            if(contador == 0){
                contador += 1;
                continue;
            } else{
                codigosYBalances.add(Integer.parseInt(codigos[0].trim()));
                resultados.add(Integer.parseInt(codigos[0].trim()));
                contador += 1;
            }
        }
        return resultados;
    }

    public List<DBObject> getAll(){
        List<DBObject> lista = new ArrayList<>();
        lista.add(art1);
        lista.add(art2);
        lista.add(art3);
        return lista;
    }
}
