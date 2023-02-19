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
public class Detalle2 {
    private int iddetalle2;
    private int CodigoBarras;
    private Float Cantidad;
    private Float Precio;
    private int IdVale;
    private String NoVale;
    private String Total;
    private int Nombre;
    private String Fecha;
    private int Usuario;
    private int Cliente;
    public Detalle2(){
        
    }

    public Detalle2(int iddetalle2, int CodigoBarras, Float Cantidad, Float Precio, int IdVale, String NoVale, String Total, int Nombre, String Fecha, int Usuario, int Cliente) {
        this.iddetalle2 = iddetalle2;
        this.CodigoBarras = CodigoBarras;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
        this.IdVale = IdVale;
        this.NoVale = NoVale;
        this.Total = Total;
        this.Nombre = Nombre;
        this.Fecha = Fecha;
        this.Usuario = Usuario;
        this.Cliente = Cliente;
    }

    public int getIddetalle2() {
        return iddetalle2;
    }

    public void setIddetalle2(int iddetalle2) {
        this.iddetalle2 = iddetalle2;
    }

    public int getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(int CodigoBarras) {
        this.CodigoBarras = CodigoBarras;
    }

    public Float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Float Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Float getPrecio() {
        return Precio;
    }

    public void setPrecio(Float Precio) {
        this.Precio = Precio;
    }

    public int getIdVale() {
        return IdVale;
    }

    public void setIdVale(int IdVale) {
        this.IdVale = IdVale;
    }

    public String getNoVale() {
        return NoVale;
    }

    public void setNoVale(String NoVale) {
        this.NoVale = NoVale;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }

    public int getNombre() {
        return Nombre;
    }

    public void setNombre(int Nombre) {
        this.Nombre = Nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public int getCliente() {
        return Cliente;
    }

    public void setCliente(int Cliente) {
        this.Cliente = Cliente;
    }
}
