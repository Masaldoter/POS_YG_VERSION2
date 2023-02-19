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
public class Detalle {
    /*
    iddetalle int AI PK 
CodigoBarras varchar(200) 
Nombre varchar(200) 
Cantidad float 
Precio float 
Descuento_Detalle float 
Precio_Con_Descuento_Detalle float 
Total varchar(45) 
Validar_Descuento_detalle varchar(45) 
ProductoRegistrado int 
NoFactura varchar(200) 
IdProductos
    */
    private int iddetalle, IdProductos;
    private String CodigoBarras, Nombre, NoFactura, ValidacionProductoExistente;
    private Float Cantidad, Precio, Descuento, Precio_Con_Descuento, Total;
    private Boolean Aplicar_Descuento;
    public Detalle(){
        
    }

    public Detalle(int iddetalle, int IdProductos, String CodigoBarras, String Nombre, String NoFactura, String ValidacionProductoExistente, Float Cantidad, Float Precio, Float Descuento, Float Precio_Con_Descuento, Float Total, Boolean Aplicar_Descuento) {
        this.iddetalle = iddetalle;
        this.IdProductos = IdProductos;
        this.CodigoBarras = CodigoBarras;
        this.Nombre = Nombre;
        this.NoFactura = NoFactura;
        this.ValidacionProductoExistente = ValidacionProductoExistente;
        this.Cantidad = Cantidad;
        this.Precio = Precio;
        this.Descuento = Descuento;
        this.Precio_Con_Descuento = Precio_Con_Descuento;
        this.Total = Total;
        this.Aplicar_Descuento = Aplicar_Descuento;
    }

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIdProductos() {
        return IdProductos;
    }

    public void setIdProductos(int IdProductos) {
        this.IdProductos = IdProductos;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String CodigoBarras) {
        this.CodigoBarras = CodigoBarras;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getNoFactura() {
        return NoFactura;
    }

    public void setNoFactura(String NoFactura) {
        this.NoFactura = NoFactura;
    }

    public String getValidacionProductoExistente() {
        return ValidacionProductoExistente;
    }

    public void setValidacionProductoExistente(String ValidacionProductoExistente) {
        this.ValidacionProductoExistente = ValidacionProductoExistente;
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

    public Float getDescuento() {
        return Descuento;
    }

    public void setDescuento(Float Descuento) {
        this.Descuento = Descuento;
    }

    public Float getPrecio_Con_Descuento() {
        return Precio_Con_Descuento;
    }

    public void setPrecio_Con_Descuento(Float Precio_Con_Descuento) {
        this.Precio_Con_Descuento = Precio_Con_Descuento;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }

    public Boolean getAplicar_Descuento() {
        return Aplicar_Descuento;
    }

    public void setAplicar_Descuento(Boolean Aplicar_Descuento) {
        this.Aplicar_Descuento = Aplicar_Descuento;
    }
}
