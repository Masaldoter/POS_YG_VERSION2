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
public class TokenParametros {
    private String Usuario, Contrasenia, TokenGenerado;
   
    public TokenParametros(){
        
    }

    public TokenParametros(String Usuario, String Contrasenia, String TokenGenerado) {
        this.Usuario = Usuario;
        this.Contrasenia = Contrasenia;
        this.TokenGenerado = TokenGenerado;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public String getTokenGenerado() {
        return TokenGenerado;
    }

    public void setTokenGenerado(String TokenGenerado) {
        this.TokenGenerado = TokenGenerado;
    }
}
