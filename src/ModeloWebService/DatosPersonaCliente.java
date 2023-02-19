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
public class DatosPersonaCliente {
    private Boolean Estado;
    private String Pais, NIT_CUI, Nombre, Direccion, Departamento, Municipio;
    private Boolean EstadoConsulta;
    
    public DatosPersonaCliente(){
        
    }

    public DatosPersonaCliente(Boolean Estado, String Pais, String NIT_CUI, String Nombre, String Direccion, String Departamento, String Municipio, Boolean EstadoConsulta) {
        this.Estado = Estado;
        this.Pais = Pais;
        this.NIT_CUI = NIT_CUI;
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.Departamento = Departamento;
        this.Municipio = Municipio;
        this.EstadoConsulta = EstadoConsulta;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean Estado) {
        this.Estado = Estado;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getNIT_CUI() {
        return NIT_CUI;
    }

    public void setNIT_CUI(String NIT_CUI) {
        this.NIT_CUI = NIT_CUI;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public Boolean getEstadoConsulta() {
        return EstadoConsulta;
    }

    public void setEstadoConsulta(Boolean EstadoConsulta) {
        this.EstadoConsulta = EstadoConsulta;
    }
}
