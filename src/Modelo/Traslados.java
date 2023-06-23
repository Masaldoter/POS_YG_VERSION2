
package Modelo;

import javax.swing.JButton;

public class Traslados {
    
    private int idTraslado, IdUsuario, NombreUsuario, UsuarioModifico, NumeroVenta;
    private Float TotalProductos, Total_Traslado;
    private String TotalLetras, NoTraslado, FechaRealizada, HoraRealizada, FechaVencimiento, 
            EstadoTraslado, FechaModifico, HoraModifico, NitCliente, NombreCliente;
    private JButton BtnDetalles;
    
    private String NombreClienteString, NitClienteString, NombreUsuarioString, NombreUsuarioModificoString, Observacion;
    
    public Traslados(){
        
    }

    public Traslados(int idTraslado, int IdUsuario, int NombreUsuario, int UsuarioModifico, int NumeroVenta, Float TotalProductos, Float Total_Traslado, String TotalLetras, String NoTraslado, String FechaRealizada, String HoraRealizada, String FechaVencimiento, String EstadoTraslado, String FechaModifico, String HoraModifico, String NitCliente, String NombreCliente, JButton BtnDetalles, String NombreClienteString, String NitClienteString, String NombreUsuarioString, String NombreUsuarioModificoString, String Observacion) {
        this.idTraslado = idTraslado;
        this.IdUsuario = IdUsuario;
        this.NombreUsuario = NombreUsuario;
        this.UsuarioModifico = UsuarioModifico;
        this.NumeroVenta = NumeroVenta;
        this.TotalProductos = TotalProductos;
        this.Total_Traslado = Total_Traslado;
        this.TotalLetras = TotalLetras;
        this.NoTraslado = NoTraslado;
        this.FechaRealizada = FechaRealizada;
        this.HoraRealizada = HoraRealizada;
        this.FechaVencimiento = FechaVencimiento;
        this.EstadoTraslado = EstadoTraslado;
        this.FechaModifico = FechaModifico;
        this.HoraModifico = HoraModifico;
        this.NitCliente = NitCliente;
        this.NombreCliente = NombreCliente;
        this.BtnDetalles = BtnDetalles;
        this.NombreClienteString = NombreClienteString;
        this.NitClienteString = NitClienteString;
        this.NombreUsuarioString = NombreUsuarioString;
        this.NombreUsuarioModificoString = NombreUsuarioModificoString;
        this.Observacion = Observacion;
    }

    public int getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(int idTraslado) {
        this.idTraslado = idTraslado;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(int NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public int getUsuarioModifico() {
        return UsuarioModifico;
    }

    public void setUsuarioModifico(int UsuarioModifico) {
        this.UsuarioModifico = UsuarioModifico;
    }

    public int getNumeroVenta() {
        return NumeroVenta;
    }

    public void setNumeroVenta(int NumeroVenta) {
        this.NumeroVenta = NumeroVenta;
    }

    public Float getTotalProductos() {
        return TotalProductos;
    }

    public void setTotalProductos(Float TotalProductos) {
        this.TotalProductos = TotalProductos;
    }

    public Float getTotal_Traslado() {
        return Total_Traslado;
    }

    public void setTotal_Traslado(Float Total_Traslado) {
        this.Total_Traslado = Total_Traslado;
    }

    public String getTotalLetras() {
        return TotalLetras;
    }

    public void setTotalLetras(String TotalLetras) {
        this.TotalLetras = TotalLetras;
    }

    public String getNoTraslado() {
        return NoTraslado;
    }

    public void setNoTraslado(String NoTraslado) {
        this.NoTraslado = NoTraslado;
    }

    public String getFechaRealizada() {
        return FechaRealizada;
    }

    public void setFechaRealizada(String FechaRealizada) {
        this.FechaRealizada = FechaRealizada;
    }

    public String getHoraRealizada() {
        return HoraRealizada;
    }

    public void setHoraRealizada(String HoraRealizada) {
        this.HoraRealizada = HoraRealizada;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public String getEstadoTraslado() {
        return EstadoTraslado;
    }

    public void setEstadoTraslado(String EstadoTraslado) {
        this.EstadoTraslado = EstadoTraslado;
    }

    public String getFechaModifico() {
        return FechaModifico;
    }

    public void setFechaModifico(String FechaModifico) {
        this.FechaModifico = FechaModifico;
    }

    public String getHoraModifico() {
        return HoraModifico;
    }

    public void setHoraModifico(String HoraModifico) {
        this.HoraModifico = HoraModifico;
    }

    public String getNitCliente() {
        return NitCliente;
    }

    public void setNitCliente(String NitCliente) {
        this.NitCliente = NitCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public JButton getBtnDetalles() {
        return BtnDetalles;
    }

    public void setBtnDetalles(JButton BtnDetalles) {
        this.BtnDetalles = BtnDetalles;
    }

    public String getNombreClienteString() {
        return NombreClienteString;
    }

    public void setNombreClienteString(String NombreClienteString) {
        this.NombreClienteString = NombreClienteString;
    }

    public String getNitClienteString() {
        return NitClienteString;
    }

    public void setNitClienteString(String NitClienteString) {
        this.NitClienteString = NitClienteString;
    }

    public String getNombreUsuarioString() {
        return NombreUsuarioString;
    }

    public void setNombreUsuarioString(String NombreUsuarioString) {
        this.NombreUsuarioString = NombreUsuarioString;
    }

    public String getNombreUsuarioModificoString() {
        return NombreUsuarioModificoString;
    }

    public void setNombreUsuarioModificoString(String NombreUsuarioModificoString) {
        this.NombreUsuarioModificoString = NombreUsuarioModificoString;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
}
