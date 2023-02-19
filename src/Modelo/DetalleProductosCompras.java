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
public class DetalleProductosCompras {
    
    private int IdCompra;
    private String Nombre, CodigoDeBarras, CodigodeEmpresas;
    private Float Cantidad, PrecioUnitario, Total;
    
    public DetalleProductosCompras(){
        
    }

    public DetalleProductosCompras(int IdCompra, String Nombre, String CodigoDeBarras, String CodigodeEmpresas, Float Cantidad, Float PrecioUnitario, Float Total) {
        this.IdCompra = IdCompra;
        this.Nombre = Nombre;
        this.CodigoDeBarras = CodigoDeBarras;
        this.CodigodeEmpresas = CodigodeEmpresas;
        this.Cantidad = Cantidad;
        this.PrecioUnitario = PrecioUnitario;
        this.Total = Total;
    }

    public int getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(int IdCompra) {
        this.IdCompra = IdCompra;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getCodigoDeBarras() {
        return CodigoDeBarras;
    }

    public void setCodigoDeBarras(String CodigoDeBarras) {
        this.CodigoDeBarras = CodigoDeBarras;
    }

    public String getCodigodeEmpresas() {
        return CodigodeEmpresas;
    }

    public void setCodigodeEmpresas(String CodigodeEmpresas) {
        this.CodigodeEmpresas = CodigodeEmpresas;
    }

    public Float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Float Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Float getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(Float PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }
}
