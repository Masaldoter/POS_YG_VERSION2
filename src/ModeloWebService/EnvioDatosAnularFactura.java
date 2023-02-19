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
public class EnvioDatosAnularFactura {
    private String NumeroDocumentoAAnularDatosGenerales, NITEmisor, NitReceptor, FechaEmisionDocumentoAnular, FechaHoraAnulacion, MotivoAnulacion;
    
    public EnvioDatosAnularFactura(){
        
    }

    public EnvioDatosAnularFactura(String NumeroDocumentoAAnularDatosGenerales, String NITEmisor, String NitReceptor, String FechaEmisionDocumentoAnular, String FechaHoraAnulacion, String MotivoAnulacion) {
        this.NumeroDocumentoAAnularDatosGenerales = NumeroDocumentoAAnularDatosGenerales;
        this.NITEmisor = NITEmisor;
        this.NitReceptor = NitReceptor;
        this.FechaEmisionDocumentoAnular = FechaEmisionDocumentoAnular;
        this.FechaHoraAnulacion = FechaHoraAnulacion;
        this.MotivoAnulacion = MotivoAnulacion;
    }

    public String getNumeroDocumentoAAnularDatosGenerales() {
        return NumeroDocumentoAAnularDatosGenerales;
    }

    public void setNumeroDocumentoAAnularDatosGenerales(String NumeroDocumentoAAnularDatosGenerales) {
        this.NumeroDocumentoAAnularDatosGenerales = NumeroDocumentoAAnularDatosGenerales;
    }

    public String getNITEmisor() {
        return NITEmisor;
    }

    public void setNITEmisor(String NITEmisor) {
        this.NITEmisor = NITEmisor;
    }

    public String getNitReceptor() {
        return NitReceptor;
    }

    public void setNitReceptor(String NitReceptor) {
        this.NitReceptor = NitReceptor;
    }

    public String getFechaEmisionDocumentoAnular() {
        return FechaEmisionDocumentoAnular;
    }

    public void setFechaEmisionDocumentoAnular(String FechaEmisionDocumentoAnular) {
        this.FechaEmisionDocumentoAnular = FechaEmisionDocumentoAnular;
    }

    public String getFechaHoraAnulacion() {
        return FechaHoraAnulacion;
    }

    public void setFechaHoraAnulacion(String FechaHoraAnulacion) {
        this.FechaHoraAnulacion = FechaHoraAnulacion;
    }

    public String getMotivoAnulacion() {
        return MotivoAnulacion;
    }

    public void setMotivoAnulacion(String MotivoAnulacion) {
        this.MotivoAnulacion = MotivoAnulacion;
    }
}
