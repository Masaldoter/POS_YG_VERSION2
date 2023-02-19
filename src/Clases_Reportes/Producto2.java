/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases_Reportes;

public class Producto2 {
    String Codigo, Nombre, Stock, PrecioCosto,CostoEnLetras,PrecioPúblico,PrecioEspecial,PrecioReventa, Proveedor;
public Producto2(){
    
}

    public Producto2(String Codigo, String Nombre, String Stock, String PrecioCosto, String CostoEnLetras, String PrecioPúblico, String PrecioEspecial, String PrecioReventa, String Proveedor) {
        this.Codigo = Codigo;
        this.Nombre = Nombre;
        this.Stock = Stock;
        this.PrecioCosto = PrecioCosto;
        this.CostoEnLetras = CostoEnLetras;
        this.PrecioPúblico = PrecioPúblico;
        this.PrecioEspecial = PrecioEspecial;
        this.PrecioReventa = PrecioReventa;
        this.Proveedor = Proveedor;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String Stock) {
        this.Stock = Stock;
    }

    public String getPrecioCosto() {
        return PrecioCosto;
    }

    public void setPrecioCosto(String PrecioCosto) {
        this.PrecioCosto = PrecioCosto;
    }

    public String getCostoEnLetras() {
        return CostoEnLetras;
    }

    public void setCostoEnLetras(String CostoEnLetras) {
        this.CostoEnLetras = CostoEnLetras;
    }

    public String getPrecioPúblico() {
        return PrecioPúblico;
    }

    public void setPrecioPúblico(String PrecioPúblico) {
        this.PrecioPúblico = PrecioPúblico;
    }

    public String getPrecioEspecial() {
        return PrecioEspecial;
    }

    public void setPrecioEspecial(String PrecioEspecial) {
        this.PrecioEspecial = PrecioEspecial;
    }

    public String getPrecioReventa() {
        return PrecioReventa;
    }

    public void setPrecioReventa(String PrecioReventa) {
        this.PrecioReventa = PrecioReventa;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }
    
}
