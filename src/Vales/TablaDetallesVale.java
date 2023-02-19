
package Vales;

public class TablaDetallesVale {
    String codigo, cantidad, Nombre, punitario, total;
    public TablaDetallesVale(){
        
    }

    public TablaDetallesVale(String Codigo, String cantidad, String Nombre, String punitario, String total) {
        this.codigo = Codigo;
        this.cantidad = cantidad;
        this.Nombre = Nombre;
        this.punitario = punitario;
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String Codigo) {
        this.codigo = Codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPunitario() {
        return punitario;
    }

    public void setPunitario(String punitario) {
        this.punitario = punitario;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
