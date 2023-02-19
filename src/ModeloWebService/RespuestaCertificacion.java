/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloWebService;

/**
 *
 * @author Masaldoter
 */
public class RespuestaCertificacion {
    private String Codigo, EstadoCertificacion, AcuseReciboSAT, CodigosSAT, ResponseDATA1, ResponseDATA2, ResponseDATA3,
            Autorizacion, Serie, NUMERO, Fecha_DTE, NIT_EFACE, NOMBRE_EFACE, NIT_COMPRADOR, NOMBRE_COMPRADOR, BACKPROCESOR, Fecha_de_certificacion;
    private boolean Validado;
    
    public RespuestaCertificacion(){
        
    }

    public RespuestaCertificacion(String Codigo, String EstadoCertificacion, String AcuseReciboSAT, String CodigosSAT, String ResponseDATA1, String ResponseDATA2, String ResponseDATA3, String Autorizacion, String Serie, String NUMERO, String Fecha_DTE, String NIT_EFACE, String NOMBRE_EFACE, String NIT_COMPRADOR, String NOMBRE_COMPRADOR, String BACKPROCESOR, String Fecha_de_certificacion, boolean Validado) {
        this.Codigo = Codigo;
        this.EstadoCertificacion = EstadoCertificacion;
        this.AcuseReciboSAT = AcuseReciboSAT;
        this.CodigosSAT = CodigosSAT;
        this.ResponseDATA1 = ResponseDATA1;
        this.ResponseDATA2 = ResponseDATA2;
        this.ResponseDATA3 = ResponseDATA3;
        this.Autorizacion = Autorizacion;
        this.Serie = Serie;
        this.NUMERO = NUMERO;
        this.Fecha_DTE = Fecha_DTE;
        this.NIT_EFACE = NIT_EFACE;
        this.NOMBRE_EFACE = NOMBRE_EFACE;
        this.NIT_COMPRADOR = NIT_COMPRADOR;
        this.NOMBRE_COMPRADOR = NOMBRE_COMPRADOR;
        this.BACKPROCESOR = BACKPROCESOR;
        this.Fecha_de_certificacion = Fecha_de_certificacion;
        this.Validado = Validado;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getEstadoCertificacion() {
        return EstadoCertificacion;
    }

    public void setEstadoCertificacion(String EstadoCertificacion) {
        this.EstadoCertificacion = EstadoCertificacion;
    }

    public String getAcuseReciboSAT() {
        return AcuseReciboSAT;
    }

    public void setAcuseReciboSAT(String AcuseReciboSAT) {
        this.AcuseReciboSAT = AcuseReciboSAT;
    }

    public String getCodigosSAT() {
        return CodigosSAT;
    }

    public void setCodigosSAT(String CodigosSAT) {
        this.CodigosSAT = CodigosSAT;
    }

    public String getResponseDATA1() {
        return ResponseDATA1;
    }

    public void setResponseDATA1(String ResponseDATA1) {
        this.ResponseDATA1 = ResponseDATA1;
    }

    public String getResponseDATA2() {
        return ResponseDATA2;
    }

    public void setResponseDATA2(String ResponseDATA2) {
        this.ResponseDATA2 = ResponseDATA2;
    }

    public String getResponseDATA3() {
        return ResponseDATA3;
    }

    public void setResponseDATA3(String ResponseDATA3) {
        this.ResponseDATA3 = ResponseDATA3;
    }

    public String getAutorizacion() {
        return Autorizacion;
    }

    public void setAutorizacion(String Autorizacion) {
        this.Autorizacion = Autorizacion;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String Serie) {
        this.Serie = Serie;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getFecha_DTE() {
        return Fecha_DTE;
    }

    public void setFecha_DTE(String Fecha_DTE) {
        this.Fecha_DTE = Fecha_DTE;
    }

    public String getNIT_EFACE() {
        return NIT_EFACE;
    }

    public void setNIT_EFACE(String NIT_EFACE) {
        this.NIT_EFACE = NIT_EFACE;
    }

    public String getNOMBRE_EFACE() {
        return NOMBRE_EFACE;
    }

    public void setNOMBRE_EFACE(String NOMBRE_EFACE) {
        this.NOMBRE_EFACE = NOMBRE_EFACE;
    }

    public String getNIT_COMPRADOR() {
        return NIT_COMPRADOR;
    }

    public void setNIT_COMPRADOR(String NIT_COMPRADOR) {
        this.NIT_COMPRADOR = NIT_COMPRADOR;
    }

    public String getNOMBRE_COMPRADOR() {
        return NOMBRE_COMPRADOR;
    }

    public void setNOMBRE_COMPRADOR(String NOMBRE_COMPRADOR) {
        this.NOMBRE_COMPRADOR = NOMBRE_COMPRADOR;
    }

    public String getBACKPROCESOR() {
        return BACKPROCESOR;
    }

    public void setBACKPROCESOR(String BACKPROCESOR) {
        this.BACKPROCESOR = BACKPROCESOR;
    }

    public String getFecha_de_certificacion() {
        return Fecha_de_certificacion;
    }

    public void setFecha_de_certificacion(String Fecha_de_certificacion) {
        this.Fecha_de_certificacion = Fecha_de_certificacion;
    }

    public boolean isValidado() {
        return Validado;
    }

    public void setValidado(boolean Validado) {
        this.Validado = Validado;
    }
}
