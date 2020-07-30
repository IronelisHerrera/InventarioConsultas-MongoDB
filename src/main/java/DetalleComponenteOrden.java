public class DetalleComponenteOrden {

    String componente;
    String descripcion;
    int cantidad;
    int precio;
    int importe;
    String suplidor;

    public DetalleComponenteOrden() {
    }

    public DetalleComponenteOrden(String componente, String descripcion, int cantidad, int precio, int importe, String suplidor) {
        this.componente = componente;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
        this.suplidor = suplidor;
    }

    public String getSuplidor() {
        return suplidor;
    }

    public void setSuplidor(String suplidor) {
        this.suplidor = suplidor;
    }

    public String getComponente() {
        return componente;
    }

    public void setComponente(String component) {
        this.componente = component;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }
}
