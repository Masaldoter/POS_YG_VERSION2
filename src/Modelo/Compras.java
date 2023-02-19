/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Masaldoter
 */
public class Compras {
    private int IdCompra, Proveedor, UsuarioRegistro, UsuarioModifico;
    private String TipoDocumento, NumeroDocumento, SerieDocumento;
    private Float Total;
    private Date FechaCompra, FechaRegistro, FechaModificacion;
    
    public Compras(){
        
    }

    public Compras(int IdCompra, int Proveedor, int UsuarioRegistro, int UsuarioModifico, String TipoDocumento, String NumeroDocumento, String SerieDocumento, Float Total, Date FechaCompra, Date FechaRegistro, Date FechaModificacion) {
        this.IdCompra = IdCompra;
        this.Proveedor = Proveedor;
        this.UsuarioRegistro = UsuarioRegistro;
        this.UsuarioModifico = UsuarioModifico;
        this.TipoDocumento = TipoDocumento;
        this.NumeroDocumento = NumeroDocumento;
        this.SerieDocumento = SerieDocumento;
        this.Total = Total;
        this.FechaCompra = FechaCompra;
        this.FechaRegistro = FechaRegistro;
        this.FechaModificacion = FechaModificacion;
    }

    public int getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(int IdCompra) {
        this.IdCompra = IdCompra;
    }

    public int getProveedor() {
        return Proveedor;
    }

    public void setProveedor(int Proveedor) {
        this.Proveedor = Proveedor;
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

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String TipoDocumento) {
        this.TipoDocumento = TipoDocumento;
    }

    public String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public void setNumeroDocumento(String NumeroDocumento) {
        this.NumeroDocumento = NumeroDocumento;
    }

    public String getSerieDocumento() {
        return SerieDocumento;
    }

    public void setSerieDocumento(String SerieDocumento) {
        this.SerieDocumento = SerieDocumento;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }

    public Date getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(Date FechaCompra) {
        this.FechaCompra = FechaCompra;
    }

    public Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(Date FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public Date getFechaModificacion() {
        return FechaModificacion;
    }

    public void setFechaModificacion(Date FechaModificacion) {
        this.FechaModificacion = FechaModificacion;
    }
    
}
