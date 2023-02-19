
package Modelo;

import javax.swing.JButton;

public class Clientes {
  private Boolean ResutaldoConsulta;
  private String Nombre;
  private String IDENTIFICACION, TIPO_IDENTIFICACION;
  private String Direccion;
  private int idclientes;
  private String Municipio, Departamento, Pais, CodigoPostal, Correo, Telefono;
  private JButton botoneditar, botoneliminar;
  public Clientes(){
      
  }

    public Clientes(Boolean ResutaldoConsulta, String Nombre, String IDENTIFICACION, String TIPO_IDENTIFICACION, String Direccion, int idclientes, String Municipio, String Departamento, String Pais, String CodigoPostal, String Correo, String Telefono, JButton botoneditar, JButton botoneliminar) {
        this.ResutaldoConsulta = ResutaldoConsulta;
        this.Nombre = Nombre;
        this.IDENTIFICACION = IDENTIFICACION;
        this.TIPO_IDENTIFICACION = TIPO_IDENTIFICACION;
        this.Direccion = Direccion;
        this.idclientes = idclientes;
        this.Municipio = Municipio;
        this.Departamento = Departamento;
        this.Pais = Pais;
        this.CodigoPostal = CodigoPostal;
        this.Correo = Correo;
        this.Telefono = Telefono;
        this.botoneditar = botoneditar;
        this.botoneliminar = botoneliminar;
    }

    public Boolean getResutaldoConsulta() {
        return ResutaldoConsulta;
    }

    public void setResutaldoConsulta(Boolean ResutaldoConsulta) {
        this.ResutaldoConsulta = ResutaldoConsulta;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getIDENTIFICACION() {
        return IDENTIFICACION;
    }

    public void setIDENTIFICACION(String IDENTIFICACION) {
        this.IDENTIFICACION = IDENTIFICACION;
    }

    public String getTIPO_IDENTIFICACION() {
        return TIPO_IDENTIFICACION;
    }

    public void setTIPO_IDENTIFICACION(String TIPO_IDENTIFICACION) {
        this.TIPO_IDENTIFICACION = TIPO_IDENTIFICACION;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getIdclientes() {
        return idclientes;
    }

    public void setIdclientes(int idclientes) {
        this.idclientes = idclientes;
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

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public JButton getBotoneditar() {
        return botoneditar;
    }

    public void setBotoneditar(JButton botoneditar) {
        this.botoneditar = botoneditar;
    }

    public JButton getBotoneliminar() {
        return botoneliminar;
    }

    public void setBotoneliminar(JButton botoneliminar) {
        this.botoneliminar = botoneliminar;
    }
}
