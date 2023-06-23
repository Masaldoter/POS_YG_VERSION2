/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MASALDOTER_GT
 */
public class Vales_V1 {
    int IdUsuario, UsuarioModifico;
    String NitCliente, NombreCliente, TotalProductos, TotalLetras, Fecha_Hora, EstadoVale, FechaModifico, Observacion;
    Float Total_Vale;
    
    public Vales_V1(){
        
    }

    public Vales_V1(int IdUsuario, int UsuarioModifico, String NitCliente, String NombreCliente, String TotalProductos, String TotalLetras, String Fecha_Hora, String EstadoVale, String FechaModifico, String Observacion, Float Total_Vale) {
        this.IdUsuario = IdUsuario;
        this.UsuarioModifico = UsuarioModifico;
        this.NitCliente = NitCliente;
        this.NombreCliente = NombreCliente;
        this.TotalProductos = TotalProductos;
        this.TotalLetras = TotalLetras;
        this.Fecha_Hora = Fecha_Hora;
        this.EstadoVale = EstadoVale;
        this.FechaModifico = FechaModifico;
        this.Observacion = Observacion;
        this.Total_Vale = Total_Vale;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public int getUsuarioModifico() {
        return UsuarioModifico;
    }

    public void setUsuarioModifico(int UsuarioModifico) {
        this.UsuarioModifico = UsuarioModifico;
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

    public String getTotalProductos() {
        return TotalProductos;
    }

    public void setTotalProductos(String TotalProductos) {
        this.TotalProductos = TotalProductos;
    }

    public String getTotalLetras() {
        return TotalLetras;
    }

    public void setTotalLetras(String TotalLetras) {
        this.TotalLetras = TotalLetras;
    }

    public String getFecha_Hora() {
        return Fecha_Hora;
    }

    public void setFecha_Hora(String Fecha_Hora) {
        this.Fecha_Hora = Fecha_Hora;
    }

    public String getEstadoVale() {
        return EstadoVale;
    }

    public void setEstadoVale(String EstadoVale) {
        this.EstadoVale = EstadoVale;
    }

    public String getFechaModifico() {
        return FechaModifico;
    }

    public void setFechaModifico(String FechaModifico) {
        this.FechaModifico = FechaModifico;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public Float getTotal_Vale() {
        return Total_Vale;
    }

    public void setTotal_Vale(Float Total_Vale) {
        this.Total_Vale = Total_Vale;
    }
}
