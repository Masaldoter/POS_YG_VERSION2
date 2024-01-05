/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MASALDOTER_GT
 */
public class Promociones {
    int idpromociones, UsuarioRegistro, UsuarioModifico;
    String UsuarioRegistro_LETRAS,  UsuarioModifico_LETRAS;
    String CodigoPromocion, NombrePromocion, FechaInicio, FechaVencimiento, FechaRegistro, HoraRegistro, Estado; 
    Float PorcentajeDescuento;
    public Promociones(){
        
    }

    public Promociones(int idpromociones, int UsuarioRegistro, int UsuarioModifico, String UsuarioRegistro_LETRAS, String UsuarioModifico_LETRAS, String CodigoPromocion, String NombrePromocion, String FechaInicio, String FechaVencimiento, String FechaRegistro, String HoraRegistro, String Estado, Float PorcentajeDescuento) {
        this.idpromociones = idpromociones;
        this.UsuarioRegistro = UsuarioRegistro;
        this.UsuarioModifico = UsuarioModifico;
        this.UsuarioRegistro_LETRAS = UsuarioRegistro_LETRAS;
        this.UsuarioModifico_LETRAS = UsuarioModifico_LETRAS;
        this.CodigoPromocion = CodigoPromocion;
        this.NombrePromocion = NombrePromocion;
        this.FechaInicio = FechaInicio;
        this.FechaVencimiento = FechaVencimiento;
        this.FechaRegistro = FechaRegistro;
        this.HoraRegistro = HoraRegistro;
        this.Estado = Estado;
        this.PorcentajeDescuento = PorcentajeDescuento;
    }

    public int getIdpromociones() {
        return idpromociones;
    }

    public void setIdpromociones(int idpromociones) {
        this.idpromociones = idpromociones;
    }

    public int getUsuarioRegistro() {
        return UsuarioRegistro;
    }

    public void setUsuarioRegistro(int UsuarioRegistro) {
        this.UsuarioRegistro = UsuarioRegistro;
    }

    public int getUsuarioModifico() {
        return UsuarioModifico;
    }

    public void setUsuarioModifico(int UsuarioModifico) {
        this.UsuarioModifico = UsuarioModifico;
    }

    public String getUsuarioRegistro_LETRAS() {
        return UsuarioRegistro_LETRAS;
    }

    public void setUsuarioRegistro_LETRAS(String UsuarioRegistro_LETRAS) {
        this.UsuarioRegistro_LETRAS = UsuarioRegistro_LETRAS;
    }

    public String getUsuarioModifico_LETRAS() {
        return UsuarioModifico_LETRAS;
    }

    public void setUsuarioModifico_LETRAS(String UsuarioModifico_LETRAS) {
        this.UsuarioModifico_LETRAS = UsuarioModifico_LETRAS;
    }

    public String getCodigoPromocion() {
        return CodigoPromocion;
    }

    public void setCodigoPromocion(String CodigoPromocion) {
        this.CodigoPromocion = CodigoPromocion;
    }

    public String getNombrePromocion() {
        return NombrePromocion;
    }

    public void setNombrePromocion(String NombrePromocion) {
        this.NombrePromocion = NombrePromocion;
    }

    public String getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public String getHoraRegistro() {
        return HoraRegistro;
    }

    public void setHoraRegistro(String HoraRegistro) {
        this.HoraRegistro = HoraRegistro;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public Float getPorcentajeDescuento() {
        return PorcentajeDescuento;
    }

    public void setPorcentajeDescuento(Float PorcentajeDescuento) {
        this.PorcentajeDescuento = PorcentajeDescuento;
    }
    
    
}
