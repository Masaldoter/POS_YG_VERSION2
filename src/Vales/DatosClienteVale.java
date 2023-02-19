
package Vales;

public class DatosClienteVale {
    private String CajaCliente;
    private String CajaNitVale;
    private String CajaDireccion;
    private String CajaTotalVale, PagoVale, CambioVale, CajaVendedorVale;
    private String NoFacturaVale;
    public DatosClienteVale(){
        
    }

    public DatosClienteVale(String CajaCliente, String CajaNitVale, String CajaDireccion, String CajaTotalVale, String PagoVale, String CambioVale, String CajaVendedorVale, String NoFacturaVale) {
        this.CajaCliente = CajaCliente;
        this.CajaNitVale = CajaNitVale;
        this.CajaDireccion = CajaDireccion;
        this.CajaTotalVale = CajaTotalVale;
        this.PagoVale = PagoVale;
        this.CambioVale = CambioVale;
        this.CajaVendedorVale = CajaVendedorVale;
        this.NoFacturaVale = NoFacturaVale;
    }

    public String getCajaCliente() {
        return CajaCliente;
    }

    public void setCajaCliente(String CajaCliente) {
        this.CajaCliente = CajaCliente;
    }

    public String getCajaNitVale() {
        return CajaNitVale;
    }

    public void setCajaNitVale(String CajaNitVale) {
        this.CajaNitVale = CajaNitVale;
    }

    public String getCajaDireccion() {
        return CajaDireccion;
    }

    public void setCajaDireccion(String CajaDireccion) {
        this.CajaDireccion = CajaDireccion;
    }

    public String getCajaTotalVale() {
        return CajaTotalVale;
    }

    public void setCajaTotalVale(String CajaTotalVale) {
        this.CajaTotalVale = CajaTotalVale;
    }

    public String getPagoVale() {
        return PagoVale;
    }

    public void setPagoVale(String PagoVale) {
        this.PagoVale = PagoVale;
    }

    public String getCambioVale() {
        return CambioVale;
    }

    public void setCambioVale(String CambioVale) {
        this.CambioVale = CambioVale;
    }

    public String getCajaVendedorVale() {
        return CajaVendedorVale;
    }

    public void setCajaVendedorVale(String CajaVendedorVale) {
        this.CajaVendedorVale = CajaVendedorVale;
    }

    public String getNoFacturaVale() {
        return NoFacturaVale;
    }

    public void setNoFacturaVale(String NoFacturaVale) {
        this.NoFacturaVale = NoFacturaVale;
    }
}
