
package Modelo;

public class Vale {
    private int idVale;
    private int Cliente, Nit, Direccion;
    private Float Total, Pago, Cambio;
    private int NoVale;
    private String Estado;
    private String FechaEmision, HoraEmision;
    private String  Fecha2Pago;
    private int Vendedor;
    private int NoFactura;
    public Vale(){
        
    }

    public Vale(int idVale, int Cliente, int Nit, int Direccion, Float Total, Float Pago, Float Cambio, int NoVale, String Estado, String FechaEmision, String HoraEmision, String Fecha2Pago, int Vendedor, int NoFactura) {
        this.idVale = idVale;
        this.Cliente = Cliente;
        this.Nit = Nit;
        this.Direccion = Direccion;
        this.Total = Total;
        this.Pago = Pago;
        this.Cambio = Cambio;
        this.NoVale = NoVale;
        this.Estado = Estado;
        this.FechaEmision = FechaEmision;
        this.HoraEmision = HoraEmision;
        this.Fecha2Pago = Fecha2Pago;
        this.Vendedor = Vendedor;
        this.NoFactura = NoFactura;
    }

    public int getIdVale() {
        return idVale;
    }

    public void setIdVale(int idVale) {
        this.idVale = idVale;
    }

    public int getCliente() {
        return Cliente;
    }

    public void setCliente(int Cliente) {
        this.Cliente = Cliente;
    }

    public int getNit() {
        return Nit;
    }

    public void setNit(int Nit) {
        this.Nit = Nit;
    }

    public int getDireccion() {
        return Direccion;
    }

    public void setDireccion(int Direccion) {
        this.Direccion = Direccion;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }

    public Float getPago() {
        return Pago;
    }

    public void setPago(Float Pago) {
        this.Pago = Pago;
    }

    public Float getCambio() {
        return Cambio;
    }

    public void setCambio(Float Cambio) {
        this.Cambio = Cambio;
    }

    public int getNoVale() {
        return NoVale;
    }

    public void setNoVale(int NoVale) {
        this.NoVale = NoVale;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(String FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    public String getHoraEmision() {
        return HoraEmision;
    }

    public void setHoraEmision(String HoraEmision) {
        this.HoraEmision = HoraEmision;
    }

    public String getFecha2Pago() {
        return Fecha2Pago;
    }

    public void setFecha2Pago(String Fecha2Pago) {
        this.Fecha2Pago = Fecha2Pago;
    }

    public int getVendedor() {
        return Vendedor;
    }

    public void setVendedor(int Vendedor) {
        this.Vendedor = Vendedor;
    }

    public int getNoFactura() {
        return NoFactura;
    }

    public void setNoFactura(int NoFactura) {
        this.NoFactura = NoFactura;
    }
}
