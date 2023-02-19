/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Masaldoter
 */
public class DatosEmpresaGeneral {
    private String NombreEmpresa, Direccion, Nit, Tel, Eslogan, Politicas, Correo, Contrasenia, Iva, NombreEtiquetas, Municipio, Departamento, Pais, CodigoPostal, CajaAfilicacionEmpresa, CodigoEstablecimiento, Propietario;
    private String rutaimagenlogo, rutaimagensistema;
    public DatosEmpresaGeneral(){
        
    }

    public DatosEmpresaGeneral(String NombreEmpresa, String Direccion, String Nit, String Tel, String Eslogan, String Politicas, String Correo, String Contrasenia, String Iva, String NombreEtiquetas, String Municipio, String Departamento, String Pais, String CodigoPostal, String CajaAfilicacionEmpresa, String CodigoEstablecimiento, String Propietario, String rutaimagenlogo, String rutaimagensistema) {
        this.NombreEmpresa = NombreEmpresa;
        this.Direccion = Direccion;
        this.Nit = Nit;
        this.Tel = Tel;
        this.Eslogan = Eslogan;
        this.Politicas = Politicas;
        this.Correo = Correo;
        this.Contrasenia = Contrasenia;
        this.Iva = Iva;
        this.NombreEtiquetas = NombreEtiquetas;
        this.Municipio = Municipio;
        this.Departamento = Departamento;
        this.Pais = Pais;
        this.CodigoPostal = CodigoPostal;
        this.CajaAfilicacionEmpresa = CajaAfilicacionEmpresa;
        this.CodigoEstablecimiento = CodigoEstablecimiento;
        this.Propietario = Propietario;
        this.rutaimagenlogo = rutaimagenlogo;
        this.rutaimagensistema = rutaimagensistema;
    }

    public String getNombreEmpresa() {
        return NombreEmpresa;
    }

    public void setNombreEmpresa(String NombreEmpresa) {
        this.NombreEmpresa = NombreEmpresa;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
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

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public String getIva() {
        return Iva;
    }

    public void setIva(String Iva) {
        this.Iva = Iva;
    }

    public String getNombreEtiquetas() {
        return NombreEtiquetas;
    }

    public void setNombreEtiquetas(String NombreEtiquetas) {
        this.NombreEtiquetas = NombreEtiquetas;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public String getCajaAfilicacionEmpresa() {
        return CajaAfilicacionEmpresa;
    }

    public void setCajaAfilicacionEmpresa(String CajaAfilicacionEmpresa) {
        this.CajaAfilicacionEmpresa = CajaAfilicacionEmpresa;
    }

    public String getCodigoEstablecimiento() {
        return CodigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String CodigoEstablecimiento) {
        this.CodigoEstablecimiento = CodigoEstablecimiento;
    }

    public String getPropietario() {
        return Propietario;
    }

    public void setPropietario(String Propietario) {
        this.Propietario = Propietario;
    }

    public String getRutaimagenlogo() {
        return rutaimagenlogo;
    }

    public void setRutaimagenlogo(String rutaimagenlogo) {
        this.rutaimagenlogo = rutaimagenlogo;
    }

    public String getRutaimagensistema() {
        return rutaimagensistema;
    }

    public void setRutaimagensistema(String rutaimagensistema) {
        this.rutaimagensistema = rutaimagensistema;
    }
}
