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
    private int idcompras, Proveedor, USUARIO_REGISTRO_COMPRA, USUARIO_MODIFICO_COMPRA;
    private String TipoDocumento, NumeroDocumento, SerieDocumento, DESCRIPCION_COMPRAS, FECHA_HORA_COMPRA;
    private Float TOTAL_COMPRA;
    
    public Compras(){
        
    }

    public Compras(int idcompras, int Proveedor, int USUARIO_REGISTRO_COMPRA, int USUARIO_MODIFICO_COMPRA, String TipoDocumento, String NumeroDocumento, String SerieDocumento, String DESCRIPCION_COMPRAS, String FECHA_HORA_COMPRA, Float TOTAL_COMPRA) {
        this.idcompras = idcompras;
        this.Proveedor = Proveedor;
        this.USUARIO_REGISTRO_COMPRA = USUARIO_REGISTRO_COMPRA;
        this.USUARIO_MODIFICO_COMPRA = USUARIO_MODIFICO_COMPRA;
        this.TipoDocumento = TipoDocumento;
        this.NumeroDocumento = NumeroDocumento;
        this.SerieDocumento = SerieDocumento;
        this.DESCRIPCION_COMPRAS = DESCRIPCION_COMPRAS;
        this.FECHA_HORA_COMPRA = FECHA_HORA_COMPRA;
        this.TOTAL_COMPRA = TOTAL_COMPRA;
    }

    public int getIdcompras() {
        return idcompras;
    }

    public void setIdcompras(int idcompras) {
        this.idcompras = idcompras;
    }

    public int getProveedor() {
        return Proveedor;
    }

    public void setProveedor(int Proveedor) {
        this.Proveedor = Proveedor;
    }

    public int getUSUARIO_REGISTRO_COMPRA() {
        return USUARIO_REGISTRO_COMPRA;
    }

    public void setUSUARIO_REGISTRO_COMPRA(int USUARIO_REGISTRO_COMPRA) {
        this.USUARIO_REGISTRO_COMPRA = USUARIO_REGISTRO_COMPRA;
    }

    public int getUSUARIO_MODIFICO_COMPRA() {
        return USUARIO_MODIFICO_COMPRA;
    }

    public void setUSUARIO_MODIFICO_COMPRA(int USUARIO_MODIFICO_COMPRA) {
        this.USUARIO_MODIFICO_COMPRA = USUARIO_MODIFICO_COMPRA;
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

    public String getDESCRIPCION_COMPRAS() {
        return DESCRIPCION_COMPRAS;
    }

    public void setDESCRIPCION_COMPRAS(String DESCRIPCION_COMPRAS) {
        this.DESCRIPCION_COMPRAS = DESCRIPCION_COMPRAS;
    }

    public String getFECHA_HORA_COMPRA() {
        return FECHA_HORA_COMPRA;
    }

    public void setFECHA_HORA_COMPRA(String FECHA_HORA_COMPRA) {
        this.FECHA_HORA_COMPRA = FECHA_HORA_COMPRA;
    }

    public Float getTOTAL_COMPRA() {
        return TOTAL_COMPRA;
    }

    public void setTOTAL_COMPRA(Float TOTAL_COMPRA) {
        this.TOTAL_COMPRA = TOTAL_COMPRA;
    }
}
