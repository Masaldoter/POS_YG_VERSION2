/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Reportes;

/**
 *
 * @author Masaldoter
 */
public class DatosEmpresa {
    private String Usuario, NombreEmpresa, Tel, Nit, Direccion, Lugar, Eslogan, Politicas;
    
    public DatosEmpresa(){
        
    }

    public DatosEmpresa(String Usuario, String NombreEmpresa, String Tel, String Nit, String Direccion, String Lugar, String Eslogan) {
        this.Usuario = Usuario;
        this.NombreEmpresa = NombreEmpresa;
        this.Tel = Tel;
        this.Nit = Nit;
        this.Direccion = Direccion;
        this.Lugar = Lugar;
        this.Eslogan = Eslogan;
    }

    public DatosEmpresa(String Politicas) {
        this.Politicas = Politicas;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    public void setNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String Lugar) {
        this.Lugar = Lugar;
    }

    public String getEslogan() {
        return Eslogan;
    }

    public void setEslogan(String Eslogan) {
        this.Eslogan = Eslogan;
    }

    public String getPoliticas() {
        return Politicas;
    }

    public void setPoliticas(String Politicas) {
        this.Politicas = Politicas;
    }
}
