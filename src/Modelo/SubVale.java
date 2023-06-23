/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MASALDOTER_GT
 */
public class SubVale {
    private int idVale, Usuario_registro, UsuarioModifico;
    private String NoSubVale, Fecha_Hora_Ingreso, Fecha_Hora_Modifico, Estado;
    private Float Total;
    
    public SubVale(){
    
    }

    public SubVale(int idVale, int Usuario_registro, int UsuarioModifico, String NoSubVale, String Fecha_Hora_Ingreso, String Fecha_Hora_Modifico, String Estado, Float Total) {
        this.idVale = idVale;
        this.Usuario_registro = Usuario_registro;
        this.UsuarioModifico = UsuarioModifico;
        this.NoSubVale = NoSubVale;
        this.Fecha_Hora_Ingreso = Fecha_Hora_Ingreso;
        this.Fecha_Hora_Modifico = Fecha_Hora_Modifico;
        this.Estado = Estado;
        this.Total = Total;
    }

    public int getIdVale() {
        return idVale;
    }

    public void setIdVale(int idVale) {
        this.idVale = idVale;
    }

    public int getUsuario_registro() {
        return Usuario_registro;
    }

    public void setUsuario_registro(int Usuario_registro) {
        this.Usuario_registro = Usuario_registro;
    }

    public int getUsuarioModifico() {
        return UsuarioModifico;
    }

    public void setUsuarioModifico(int UsuarioModifico) {
        this.UsuarioModifico = UsuarioModifico;
    }

    public String getNoSubVale() {
        return NoSubVale;
    }

    public void setNoSubVale(String NoSubVale) {
        this.NoSubVale = NoSubVale;
    }

    public String getFecha_Hora_Ingreso() {
        return Fecha_Hora_Ingreso;
    }

    public void setFecha_Hora_Ingreso(String Fecha_Hora_Ingreso) {
        this.Fecha_Hora_Ingreso = Fecha_Hora_Ingreso;
    }

    public String getFecha_Hora_Modifico() {
        return Fecha_Hora_Modifico;
    }

    public void setFecha_Hora_Modifico(String Fecha_Hora_Modifico) {
        this.Fecha_Hora_Modifico = Fecha_Hora_Modifico;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }
   
}
