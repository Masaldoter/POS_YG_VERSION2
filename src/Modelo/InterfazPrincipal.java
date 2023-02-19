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
public class InterfazPrincipal {
    private String ProductosRegistrados, ClientesRegistrados, UsuariosEnLinea, Categorias;
    
    public InterfazPrincipal(){
        
    }

    public InterfazPrincipal(String ProductosRegistrados, String ClientesRegistrados, String UsuariosEnLinea, String Categorias) {
        this.ProductosRegistrados = ProductosRegistrados;
        this.ClientesRegistrados = ClientesRegistrados;
        this.UsuariosEnLinea = UsuariosEnLinea;
        this.Categorias = Categorias;
    }

    public String getProductosRegistrados() {
        return ProductosRegistrados;
    }

    public void setProductosRegistrados(String ProductosRegistrados) {
        this.ProductosRegistrados = ProductosRegistrados;
    }

    public String getClientesRegistrados() {
        return ClientesRegistrados;
    }

    public void setClientesRegistrados(String ClientesRegistrados) {
        this.ClientesRegistrados = ClientesRegistrados;
    }

    public String getUsuariosEnLinea() {
        return UsuariosEnLinea;
    }

    public void setUsuariosEnLinea(String UsuariosEnLinea) {
        this.UsuariosEnLinea = UsuariosEnLinea;
    }

    public String getCategorias() {
        return Categorias;
    }

    public void setCategorias(String Categorias) {
        this.Categorias = Categorias;
    }
}
