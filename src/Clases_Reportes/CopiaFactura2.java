
package Clases_Reportes;

/**
 *
 * @author Masaldoter
 */
public class CopiaFactura2 {
    private String CajaCliente, CajaDireccion, CajaNit, CajaPago, CajaCambio, CajaTotal, CajaVendedor;
    public CopiaFactura2(){
        
    }

    public CopiaFactura2(String CajaCliente, String CajaDireccion, String CajaNit, String CajaPago, String CajaCambio, String CajaTotal, String CajaVendedor) {
        this.CajaCliente = CajaCliente;
        this.CajaDireccion = CajaDireccion;
        this.CajaNit = CajaNit;
        this.CajaPago = CajaPago;
        this.CajaCambio = CajaCambio;
        this.CajaTotal = CajaTotal;
        this.CajaVendedor = CajaVendedor;
    }

    public String getCajaCliente() {
        return CajaCliente;
    }

    public void setCajaCliente(String CajaCliente) {
        this.CajaCliente = CajaCliente;
    }

    public String getCajaDireccion() {
        return CajaDireccion;
    }

    public void setCajaDireccion(String CajaDireccion) {
        this.CajaDireccion = CajaDireccion;
    }

    public String getCajaNit() {
        return CajaNit;
    }

    public void setCajaNit(String CajaNit) {
        this.CajaNit = CajaNit;
    }

    public String getCajaPago() {
        return CajaPago;
    }

    public void setCajaPago(String CajaPago) {
        this.CajaPago = CajaPago;
    }

    public String getCajaCambio() {
        return CajaCambio;
    }

    public void setCajaCambio(String CajaCambio) {
        this.CajaCambio = CajaCambio;
    }

    public String getCajaTotal() {
        return CajaTotal;
    }

    public void setCajaTotal(String CajaTotal) {
        this.CajaTotal = CajaTotal;
    }

    public String getCajaVendedor() {
        return CajaVendedor;
    }

    public void setCajaVendedor(String CajaVendedor) {
        this.CajaVendedor = CajaVendedor;
    }
}
