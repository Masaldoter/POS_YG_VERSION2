package Clases_Reportes;

public class Empleado {
    String codigo,producto, cantidad, preciou, total;
    
    Empleado(){
        
    }

    public Empleado(String codigo, String producto, String cantidad, String preciou, String total) {
        this.codigo = codigo;
        this.producto = producto;
        this.cantidad = cantidad;
        this.preciou = preciou;
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPreciou() {
        return preciou;
    }

    public void setPreciou(String preciou) {
        this.preciou = preciou;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }



    
    
}
