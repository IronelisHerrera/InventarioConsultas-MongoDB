public class DetalleOrden {
   String componente;
   String descripcion;
   int cantidad;

    public DetalleOrden(){

    }

    public DetalleOrden(String componente, String descripcion, int cantidad) {
        this.componente = componente;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String componente) {
        this.componente = componente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


}
