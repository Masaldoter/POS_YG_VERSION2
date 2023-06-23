
package Modelo;


public class DetalleVales {
    private int iddetalles_vales_v1, IdVales, ProductoRegistrado, IdProducto;
    private Float CantidadProductos, Descuento, PrecioUnitario, Total;
    private String Fecha, NoVale, CodigoBarras, NombreProducto;

    public DetalleVales(){
        
    }

    public DetalleVales(int iddetalles_vales_v1, int IdVales, int ProductoRegistrado, int IdProducto, Float CantidadProductos, Float Descuento, Float PrecioUnitario, Float Total, String Fecha, String NoVale, String CodigoBarras, String NombreProducto) {
        this.iddetalles_vales_v1 = iddetalles_vales_v1;
        this.IdVales = IdVales;
        this.ProductoRegistrado = ProductoRegistrado;
        this.IdProducto = IdProducto;
        this.CantidadProductos = CantidadProductos;
        this.Descuento = Descuento;
        this.PrecioUnitario = PrecioUnitario;
        this.Total = Total;
        this.Fecha = Fecha;
        this.NoVale = NoVale;
        this.CodigoBarras = CodigoBarras;
        this.NombreProducto = NombreProducto;
    }

    public int getIddetalles_vales_v1() {
        return iddetalles_vales_v1;
    }

    public void setIddetalles_vales_v1(int iddetalles_vales_v1) {
        this.iddetalles_vales_v1 = iddetalles_vales_v1;
    }

    public int getIdVales() {
        return IdVales;
    }

    public void setIdVales(int IdVales) {
        this.IdVales = IdVales;
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

    public String getNoVale() {
        return NoVale;
    }

    public void setNoVale(String NoVale) {
        this.NoVale = NoVale;
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
