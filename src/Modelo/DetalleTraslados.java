
package Modelo;


public class DetalleTraslados {
    private int iddetalles_Traslados, IdTraslados, ProductoRegistrado, IdProducto;
    private Float CantidadProductos, Descuento, PrecioUnitario, Total;
    private String Fecha, NoTraslado, CodigoBarras, NombreProducto;

    public DetalleTraslados(){
        
    }

    public DetalleTraslados(int iddetalles_Traslados, int IdTraslados, int ProductoRegistrado, int IdProducto, Float CantidadProductos, Float Descuento, Float PrecioUnitario, Float Total, String Fecha, String NoTraslado, String CodigoBarras, String NombreProducto) {
        this.iddetalles_Traslados = iddetalles_Traslados;
        this.IdTraslados = IdTraslados;
        this.ProductoRegistrado = ProductoRegistrado;
        this.IdProducto = IdProducto;
        this.CantidadProductos = CantidadProductos;
        this.Descuento = Descuento;
        this.PrecioUnitario = PrecioUnitario;
        this.Total = Total;
        this.Fecha = Fecha;
        this.NoTraslado = NoTraslado;
        this.CodigoBarras = CodigoBarras;
        this.NombreProducto = NombreProducto;
    }

    public int getIddetalles_Traslados() {
        return iddetalles_Traslados;
    }

    public void setIddetalles_Traslados(int iddetalles_Traslados) {
        this.iddetalles_Traslados = iddetalles_Traslados;
    }

    public int getIdTraslados() {
        return IdTraslados;
    }

    public void setIdTraslados(int IdTraslados) {
        this.IdTraslados = IdTraslados;
    }

    public int getProductoRegistrado() {
        return ProductoRegistrado;
    }

    public void setProductoRegistrado(int ProductoRegistrado) {
        this.ProductoRegistrado = ProductoRegistrado;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public Float getCantidadProductos() {
        return CantidadProductos;
    }

    public void setCantidadProductos(Float CantidadProductos) {
        this.CantidadProductos = CantidadProductos;
    }

    public Float getDescuento() {
        return Descuento;
    }

    public void setDescuento(Float Descuento) {
        this.Descuento = Descuento;
    }

    public Float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(Float PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getNoTraslado() {
        return NoTraslado;
    }

    public void setNoTraslado(String NoTraslado) {
        this.NoTraslado = NoTraslado;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String CodigoBarras) {
        this.CodigoBarras = CodigoBarras;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }
}
