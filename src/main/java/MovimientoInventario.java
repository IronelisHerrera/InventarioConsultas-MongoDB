import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.List;

public class MovimientoInventario {

    int codigoMovimiento;
    int codigoAlmacen;
    String tipoMovimiento;
    String codigoArticulo;
    int cantidad;
    String unidad;

    public int getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(int codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public int getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(int codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public MovimientoInventario(){

    }

    public MovimientoInventario(int codigoMovimiento, int codigoAlmacen, String tipoMovimiento, String codigoArticulo, int cantidad, String unidad){
        this.codigoMovimiento = codigoMovimiento;
        this.codigoAlmacen = codigoAlmacen;
        this.tipoMovimiento = tipoMovimiento;
        this.codigoArticulo = codigoArticulo;
        this.cantidad = cantidad;
        this.unidad = unidad;
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
    DBCollection movimientoInventario = database.getCollection("MovimientoInventario");

    DBObject movInv1 = new BasicDBObject("codigoMovimiento", 1)
            .append("codigoAlmacen", 1)
            .append("tipoMovimiento", "ENTRADA")
            .append("codigoArticulo", 1)
            .append("cantidad", 5)
            .append("unidad", "lb");

    public void insertarMovInventario(BasicDBObject movInv){
        movimientoInventario.insert(movInv);
    }

    public void eliminarMovInventario(BasicDBObject movInv){ ;
        movimientoInventario.remove(movInv);
    }

    public DBObject buscarMovInventario(int codigo){
        DBCursor cursor = movimientoInventario.find(new BasicDBObject("codigoMovimiento", codigo));
        return cursor.one();
    }

    public void actualizaMovInventario(BasicDBObject movInv1, BasicDBObject movInv2){
        movimientoInventario.update(movInv1, movInv2);
    }

    public List<DBObject> todos(){
       return movimientoInventario.find().toArray();
    }

    public long contador(){
        return movimientoInventario.getCount();
    }
}
