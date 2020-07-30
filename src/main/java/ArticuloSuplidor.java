import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class ArticuloSuplidor {

    MongoClient mongoClient;
    {
        try {
            mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    DB database = mongoClient.getDB("InventarioMongo");
    DBCollection articuloSuplidor = database.getCollection("ArticuloSuplidor");

    DBObject artSup1 = new BasicDBObject("codigoArticulo", "TEC-001")
                .append("codigoSuplidor", 1)
                .append("tiempoEntrega", 1)
                .append("precioCompra", 150);

    DBObject artSup2 = new BasicDBObject(new BasicDBObject("codigoArticulo", "MON-001")
                .append("codigoSuplidor", 1)
            .append("tiempoEntrega", 2)
            .append("precioCompra", 3500));

    DBObject artSup3 = new BasicDBObject(new BasicDBObject("codigoArticulo", "MOU-001")
                .append("codigoSuplidor", 2)
                .append("tiempoEntrega", 3)
                .append("precioCompra", 200));

    DBObject artSup4 = new BasicDBObject(new BasicDBObject("codigoArticulo", "TEC-001")
                .append("codigoSuplidor", 2)
                .append("tiempoEntrega", 2)
                .append("precioCompra", 150));

    DBObject artSup5 = new BasicDBObject(new BasicDBObject("codigoArticulo", "MON-001")
                .append("codigoSuplidor", 2)
                .append("tiempoEntrega", 3)
                .append("precioCompra", 3500));

    public void insertarArtSuplidor(BasicDBObject artSup){
        articuloSuplidor.insert(artSup);
    }

    public void eliminarArtSuplidor(BasicDBObject artSup){ ;
        articuloSuplidor.remove(artSup);
    }

    public DBObject buscarArtSuplidor(int codigo){
        DBCursor cursor = articuloSuplidor.find(new BasicDBObject("codigoArticulo", codigo));
        return cursor.one();
    }

    public List<DBObject> getSuplidorPorArticulo(String codigo){
        DBCursor cursor = articuloSuplidor.find(new BasicDBObject("codigoArticulo", codigo));
        List<DBObject> lista = new ArrayList<>();
        while(cursor.hasNext()) {
            DBObject obj = cursor.next();
            lista.add(obj);
        }
        return lista;
    }

    public void actualizaArtSuplidor(BasicDBObject artSup1, BasicDBObject artSup2){
        articuloSuplidor.update(artSup1, artSup2);
    }

    public List<DBObject> todos(){
        List<DBObject> objetos = new ArrayList<>();
        objetos.add(artSup1);
        objetos.add(artSup2);
        objetos.add(artSup3);
        objetos.add(artSup4);
        objetos.add(artSup5);
        return objetos;
    }
}
