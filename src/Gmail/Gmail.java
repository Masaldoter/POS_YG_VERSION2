/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gmail;

/**
 *
 * @author Masaldoter
 */
public class Gmail {
    private String Remitente, Receptor, Mensaje, Asunto;

    public Gmail(){
        
    }
    public Gmail(String Remitente, String Receptor, String Mensaje, String Asunto) {
        this.Remitente = Remitente;
        this.Receptor = Receptor;
        this.Mensaje = Mensaje;
        this.Asunto = Asunto;
    }

    public String getRemitente() {
        return Remitente;
    }

    public void setRemitente(String Remitente) {
        this.Remitente = Remitente;
    }

    public String getReceptor() {
        return Receptor;
    }

    public void setReceptor(String Receptor) {
        this.Receptor = Receptor;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getAsunto() {
        return Asunto;
    }

    public void setAsunto(String Asunto) {
        this.Asunto = Asunto;
    }
    
    
}
