/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FEL;

/**
 *
 * @author Masaldoter
 */
public class DocumentoFel {
    
    private String TipoDocumento, NumeroDocumento, SerieDocumento, NumeroAutorizacion, FechaAutorizacion, Frase;
    
    public DocumentoFel(){
        
    }

    public DocumentoFel(String TipoDocumento, String NumeroDocumento, String SerieDocumento, String NumeroAutorizacion, String FechaAutorizacion, String Frase) {
        this.TipoDocumento = TipoDocumento;
        this.NumeroDocumento = NumeroDocumento;
        this.SerieDocumento = SerieDocumento;
        this.NumeroAutorizacion = NumeroAutorizacion;
        this.FechaAutorizacion = FechaAutorizacion;
        this.Frase = Frase;
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

    public String getNumeroAutorizacion() {
        return NumeroAutorizacion;
    }

    public void setNumeroAutorizacion(String NumeroAutorizacion) {
        this.NumeroAutorizacion = NumeroAutorizacion;
    }

    public String getFechaAutorizacion() {
        return FechaAutorizacion;
    }

    public void setFechaAutorizacion(String FechaAutorizacion) {
        this.FechaAutorizacion = FechaAutorizacion;
    }

    public String getFrase() {
        return Frase;
    }

    public void setFrase(String Frase) {
        this.Frase = Frase;
    }
}
