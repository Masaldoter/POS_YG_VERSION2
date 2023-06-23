/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MASALDOTER_GT
 */
public class Detalle_SubVales {
    int ID_Producto, Id_Registro_SubVale, NoVale;
    String NombreProducto;
    float Precio, CantidadVendida;
    
    public Detalle_SubVales(){
        
    }

    public Detalle_SubVales(int ID_Producto, int Id_Registro_SubVale, int NoVale, String NombreProducto, float Precio, float CantidadVendida) {
        this.ID_Producto = ID_Producto;
        this.Id_Registro_SubVale = Id_Registro_SubVale;
        this.NoVale = NoVale;
        this.NombreProducto = NombreProducto;
        this.Precio = Precio;
        this.CantidadVendida = CantidadVendida;
    }

    public int getID_Producto() {
        return ID_Producto;
    }

    public void setID_Producto(int ID_Producto) {
        this.ID_Producto = ID_Producto;
    }

    public int getId_Registro_SubVale() {
        return Id_Registro_SubVale;
    }

    public void setId_Registro_SubVale(int Id_Registro_SubVale) {
        this.Id_Registro_SubVale = Id_Registro_SubVale;
    }

    public int getNoVale() {
        return NoVale;
    }

    public void setNoVale(int NoVale) {
        this.NoVale = NoVale;
    }

    public String getNombreProducto() {
        return NombreProducto;
    }

    public void setNombreProducto(String NombreProducto) {
        this.NombreProducto = NombreProducto;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float Precio) {
        this.Precio = Precio;
    }

    public float getCantidadVendida() {
        return CantidadVendida;
    }

    public void setCantidadVendida(float CantidadVendida) {
        this.CantidadVendida = CantidadVendida;
    }
}
