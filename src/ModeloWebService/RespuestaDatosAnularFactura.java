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
public class RespuestaDatosAnularFactura {
    private String Codigo, Mensaje, AcuseReciboSAT, CodigosSAT, ResponseDATA1, ResponseDATA2, ResponseDATA3, Autorizacion, NOMBRE_COMPRADOR, NIT_COMPRADOR;
    private Boolean ESTADO;
    
    public RespuestaDatosAnularFactura(){
        
    }

    public RespuestaDatosAnularFactura(String Codigo, String Mensaje, String AcuseReciboSAT, String CodigosSAT, String ResponseDATA1, String ResponseDATA2, String ResponseDATA3, String Autorizacion, String NOMBRE_COMPRADOR, String NIT_COMPRADOR, Boolean ESTADO) {
        this.Codigo = Codigo;
        this.Mensaje = Mensaje;
        this.AcuseReciboSAT = AcuseReciboSAT;
        this.CodigosSAT = CodigosSAT;
        this.ResponseDATA1 = ResponseDATA1;
        this.ResponseDATA2 = ResponseDATA2;
        this.ResponseDATA3 = ResponseDATA3;
        this.Autorizacion = Autorizacion;
        this.NOMBRE_COMPRADOR = NOMBRE_COMPRADOR;
        this.NIT_COMPRADOR = NIT_COMPRADOR;
        this.ESTADO = ESTADO;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
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

    public String getNOMBRE_COMPRADOR() {
        return NOMBRE_COMPRADOR;
    }

    public void setNOMBRE_COMPRADOR(String NOMBRE_COMPRADOR) {
        this.NOMBRE_COMPRADOR = NOMBRE_COMPRADOR;
    }

    public String getNIT_COMPRADOR() {
        return NIT_COMPRADOR;
    }

    public void setNIT_COMPRADOR(String NIT_COMPRADOR) {
        this.NIT_COMPRADOR = NIT_COMPRADOR;
    }

    public Boolean getESTADO() {
        return ESTADO;
    }

    public void setESTADO(Boolean ESTADO) {
        this.ESTADO = ESTADO;
    }
}
