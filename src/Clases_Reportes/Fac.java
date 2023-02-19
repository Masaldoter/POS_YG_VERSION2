
package Clases_Reportes;

public class Fac {
    private String nombre;
    private String nit;
    private String direccion;
    private String factura;
    private String labeltotal;
    private String pagocon;
    private String cambio;
    private String cotizacion;
    private String Vale;
    private String FormaPago;
    private String NumeroTransaccion;
    private String Vendedor;
    private String Observacion;
    private String TotalEnLetras;
    private String FechaEmision;
    private String FechaVenta;
    
    public Fac(){
        
    }

    public Fac(String nombre, String nit, String direccion, String factura, String labeltotal, String pagocon, String cambio, String cotizacion, String Vale, String FormaPago, String NumeroTransaccion, String Vendedor, String Observacion, String TotalEnLetras, String FechaEmision, String FechaVenta) {
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.factura = factura;
        this.labeltotal = labeltotal;
        this.pagocon = pagocon;
        this.cambio = cambio;
        this.cotizacion = cotizacion;
        this.Vale = Vale;
        this.FormaPago = FormaPago;
        this.NumeroTransaccion = NumeroTransaccion;
        this.Vendedor = Vendedor;
        this.Observacion = Observacion;
        this.TotalEnLetras = TotalEnLetras;
        this.FechaEmision = FechaEmision;
        this.FechaVenta = FechaVenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getLabeltotal() {
        return labeltotal;
    }

    public void setLabeltotal(String labeltotal) {
        this.labeltotal = labeltotal;
    }

    public String getPagocon() {
        return pagocon;
    }

    public void setPagocon(String pagocon) {
        this.pagocon = pagocon;
    }

    public String getCambio() {
        return cambio;
    }

    public void setCambio(String cambio) {
        this.cambio = cambio;
    }

    public String getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(String cotizacion) {
        this.cotizacion = cotizacion;
    }

    public String getVale() {
        return Vale;
    }

    public void setVale(String Vale) {
        this.Vale = Vale;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String FormaPago) {
        this.FormaPago = FormaPago;
    }

    public String getNumeroTransaccion() {
        return NumeroTransaccion;
    }

    public void setNumeroTransaccion(String NumeroTransaccion) {
        this.NumeroTransaccion = NumeroTransaccion;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String Vendedor) {
        this.Vendedor = Vendedor;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getTotalEnLetras() {
        return TotalEnLetras;
    }

    public void setTotalEnLetras(String TotalEnLetras) {
        this.TotalEnLetras = TotalEnLetras;
    }

    public String getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(String FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    public String getFechaVenta() {
        return FechaVenta;
    }

    public void setFechaVenta(String FechaVenta) {
        this.FechaVenta = FechaVenta;
    }
}
