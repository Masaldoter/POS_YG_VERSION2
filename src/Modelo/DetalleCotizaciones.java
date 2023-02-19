
package Modelo;


public class DetalleCotizaciones {
    private int iddetalle_cotizacion, IdCotizacion, ProductoRegistrado;
    private Float CantidadProductos, Descuento, PrecioUnitario, Total;
    private String Fecha, NoCotizacion, CodigoBarras, NombreProducto;

    public DetalleCotizaciones(){
        
    }

    public DetalleCotizaciones(int iddetalle_cotizacion, int IdCotizacion, int ProductoRegistrado, Float CantidadProductos, Float Descuento, Float PrecioUnitario, Float Total, String Fecha, String NoCotizacion, String CodigoBarras, String NombreProducto) {
        this.iddetalle_cotizacion = iddetalle_cotizacion;
        this.IdCotizacion = IdCotizacion;
        this.ProductoRegistrado = ProductoRegistrado;
        this.CantidadProductos = CantidadProductos;
        this.Descuento = Descuento;
        this.PrecioUnitario = PrecioUnitario;
        this.Total = Total;
        this.Fecha = Fecha;
        this.NoCotizacion = NoCotizacion;
        this.CodigoBarras = CodigoBarras;
        this.NombreProducto = NombreProducto;
    }

    public int getIddetalle_cotizacion() {
        return iddetalle_cotizacion;
    }

    public void setIddetalle_cotizacion(int iddetalle_cotizacion) {
        this.iddetalle_cotizacion = iddetalle_cotizacion;
    }

    public int getIdCotizacion() {
        return IdCotizacion;
    }

    public void setIdCotizacion(int IdCotizacion) {
        this.IdCotizacion = IdCotizacion;
    }

    public int getProductoRegistrado() {
        return ProductoRegistrado;
    }

    public void setProductoRegistrado(int ProductoRegistrado) {
        this.ProductoRegistrado = ProductoRegistrado;
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

    public String getNoCotizacion() {
        return NoCotizacion;
    }

    public void setNoCotizacion(String NoCotizacion) {
        this.NoCotizacion = NoCotizacion;
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
