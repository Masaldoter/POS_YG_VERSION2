/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

/**
 *
 * @author Masaldoter
 */
public class ConexionModelo {
    private String IP, PUERTO, NOMBRE, CONTRASENA, NOMBREBASE;
    public ConexionModelo(){
        
    }
    public ConexionModelo(String IP, String PUERTO, String NOMBRE, String CONTRASENA, String NOMBREBASE) {
        this.IP = IP;
        this.PUERTO = PUERTO;
        this.NOMBRE = NOMBRE;
        this.CONTRASENA = CONTRASENA;
        this.NOMBREBASE = NOMBREBASE;
    }
    
    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getPUERTO() {
        return PUERTO;
    }

    public void setPUERTO(String PUERTO) {
        this.PUERTO = PUERTO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getCONTRASENA() {
        return CONTRASENA;
    }

    public void setCONTRASENA(String CONTRASENA) {
        this.CONTRASENA = CONTRASENA;
    }

    public String getNOMBREBASE() {
        return NOMBREBASE;
    }

    public void setNOMBREBASE(String NOMBREBASE) {
        this.NOMBREBASE = NOMBREBASE;
    }
    
}
