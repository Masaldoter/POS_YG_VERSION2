/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesImpresion;

/**
 *
 * @author Masaldoter
 */
public class DatosClienteYFactura {
    private String Nombre, Nit, Direccion, NoDocumento, Total, Efectivo, Cambio,  NoVale, NoCotizacion, 
            FormadePago, NumeroTransaccion, Vendedor, Observacion, TotalLetras, FechaEmision, FechaVenta;
    public DatosClienteYFactura(){
        
    }

    public DatosClienteYFactura(String Nombre, String Nit, String Direccion, String NoDocumento, String Total, String Efectivo, String Cambio, String NoVale, String NoCotizacion, String FormadePago, String NumeroTransaccion, String Vendedor, String Observacion, String TotalLetras, String FechaEmision, String FechaVenta) {
        this.Nombre = Nombre;
        this.Nit = Nit;
        this.Direccion = Direccion;
        this.NoDocumento = NoDocumento;
        this.Total = Total;
        this.Efectivo = Efectivo;
        this.Cambio = Cambio;
        this.NoVale = NoVale;
        this.NoCotizacion = NoCotizacion;
        this.FormadePago = FormadePago;
        this.NumeroTransaccion = NumeroTransaccion;
        this.Vendedor = Vendedor;
        this.Observacion = Observacion;
        this.TotalLetras = TotalLetras;
        this.FechaEmision = FechaEmision;
        this.FechaVenta = FechaVenta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getNoDocumento() {
        return NoDocumento;
    }

    public void setNoDocumento(String NoDocumento) {
        this.NoDocumento = NoDocumento;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public String getEfectivo() {
        return Efectivo;
    }

    public void setEfectivo(String Efectivo) {
        this.Efectivo = Efectivo;
    }

    public String getCambio() {
        return Cambio;
    }

    public void setCambio(String Cambio) {
        this.Cambio = Cambio;
    }

    public String getNoVale() {
        return NoVale;
    }

    public void setNoVale(String NoVale) {
        this.NoVale = NoVale;
    }

    public String getNoCotizacion() {
        return NoCotizacion;
    }

    public void setNoCotizacion(String NoCotizacion) {
        this.NoCotizacion = NoCotizacion;
    }

    public String getFormadePago() {
        return FormadePago;
    }

    public void setFormadePago(String FormadePago) {
        this.FormadePago = FormadePago;
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

    public String getTotalLetras() {
        return TotalLetras;
    }

    public void setTotalLetras(String TotalLetras) {
        this.TotalLetras = TotalLetras;
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
