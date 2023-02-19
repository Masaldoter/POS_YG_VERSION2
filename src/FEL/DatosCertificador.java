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
public class DatosCertificador {
    
    private String NombreCertificador, NitCertificador, TelefonoCertificador, CorreoCertificador, UsuarioToken, ContraseniaToken, Token;
    
    public DatosCertificador(){
        
    }

    public DatosCertificador(String NombreCertificador, String NitCertificador, String TelefonoCertificador, String CorreoCertificador, String UsuarioToken, String ContraseniaToken, String Token) {
        this.NombreCertificador = NombreCertificador;
        this.NitCertificador = NitCertificador;
        this.TelefonoCertificador = TelefonoCertificador;
        this.CorreoCertificador = CorreoCertificador;
        this.UsuarioToken = UsuarioToken;
        this.ContraseniaToken = ContraseniaToken;
        this.Token = Token;
    }
    public String getNombreCertificador() {
        return NombreCertificador;
    }

    public void setNombreCertificador(String NombreCertificador) {
        this.NombreCertificador = NombreCertificador;
    }

    public String getNitCertificador() {
        return NitCertificador;
    }

    public void setNitCertificador(String NitCertificador) {
        this.NitCertificador = NitCertificador;
    }

    public String getTelefonoCertificador() {
        return TelefonoCertificador;
    }

    public void setTelefonoCertificador(String TelefonoCertificador) {
        this.TelefonoCertificador = TelefonoCertificador;
    }

    public String getCorreoCertificador() {
        return CorreoCertificador;
    }

    public void setCorreoCertificador(String CorreoCertificador) {
        this.CorreoCertificador = CorreoCertificador;
    }

    public String getUsuarioToken() {
        return UsuarioToken;
    }

    public void setUsuarioToken(String UsuarioToken) {
        this.UsuarioToken = UsuarioToken;
    }

    public String getContraseniaToken() {
        return ContraseniaToken;
    }

    public void setContraseniaToken(String ContraseniaToken) {
        this.ContraseniaToken = ContraseniaToken;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
    
}
