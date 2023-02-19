/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aldoy
 */
public class Kardex {
    int idkardex, ID_Codigo_Producto_Kardex, Usuario_Modifico_Kardex;
    String Codigo_Producto_NOMBRE_Kardex, NOMBRE_Producto_NOMBRE_Kardex, STOCK_PRODUCTO_KARDEX, Fecha_Modificacion_Kardex, Titulo_Kardex, Entrada_Kardex, Salida_Kardex, Despues_Kardex, Antes_Kardex, Modulo_Kardex, Usuario_Modifico_LETRAS_Kardex;
    
    public Kardex(){
        
    }

    public Kardex(int idkardex, int ID_Codigo_Producto_Kardex, int Usuario_Modifico_Kardex, String Codigo_Producto_NOMBRE_Kardex, String NOMBRE_Producto_NOMBRE_Kardex, String STOCK_PRODUCTO_KARDEX, String Fecha_Modificacion_Kardex, String Titulo_Kardex, String Entrada_Kardex, String Salida_Kardex, String Despues_Kardex, String Antes_Kardex, String Modulo_Kardex, String Usuario_Modifico_LETRAS_Kardex) {
        this.idkardex = idkardex;
        this.ID_Codigo_Producto_Kardex = ID_Codigo_Producto_Kardex;
        this.Usuario_Modifico_Kardex = Usuario_Modifico_Kardex;
        this.Codigo_Producto_NOMBRE_Kardex = Codigo_Producto_NOMBRE_Kardex;
        this.NOMBRE_Producto_NOMBRE_Kardex = NOMBRE_Producto_NOMBRE_Kardex;
        this.STOCK_PRODUCTO_KARDEX = STOCK_PRODUCTO_KARDEX;
        this.Fecha_Modificacion_Kardex = Fecha_Modificacion_Kardex;
        this.Titulo_Kardex = Titulo_Kardex;
        this.Entrada_Kardex = Entrada_Kardex;
        this.Salida_Kardex = Salida_Kardex;
        this.Despues_Kardex = Despues_Kardex;
        this.Antes_Kardex = Antes_Kardex;
        this.Modulo_Kardex = Modulo_Kardex;
        this.Usuario_Modifico_LETRAS_Kardex = Usuario_Modifico_LETRAS_Kardex;
    }

    public int getIdkardex() {
        return idkardex;
    }

    public void setIdkardex(int idkardex) {
        this.idkardex = idkardex;
    }

    public int getID_Codigo_Producto_Kardex() {
        return ID_Codigo_Producto_Kardex;
    }

    public void setID_Codigo_Producto_Kardex(int ID_Codigo_Producto_Kardex) {
        this.ID_Codigo_Producto_Kardex = ID_Codigo_Producto_Kardex;
    }

    public int getUsuario_Modifico_Kardex() {
        return Usuario_Modifico_Kardex;
    }

    public void setUsuario_Modifico_Kardex(int Usuario_Modifico_Kardex) {
        this.Usuario_Modifico_Kardex = Usuario_Modifico_Kardex;
    }

    public String getCodigo_Producto_NOMBRE_Kardex() {
        return Codigo_Producto_NOMBRE_Kardex;
    }

    public void setCodigo_Producto_NOMBRE_Kardex(String Codigo_Producto_NOMBRE_Kardex) {
        this.Codigo_Producto_NOMBRE_Kardex = Codigo_Producto_NOMBRE_Kardex;
    }

    public String getNOMBRE_Producto_NOMBRE_Kardex() {
        return NOMBRE_Producto_NOMBRE_Kardex;
    }

    public void setNOMBRE_Producto_NOMBRE_Kardex(String NOMBRE_Producto_NOMBRE_Kardex) {
        this.NOMBRE_Producto_NOMBRE_Kardex = NOMBRE_Producto_NOMBRE_Kardex;
    }

    public String getSTOCK_PRODUCTO_KARDEX() {
        return STOCK_PRODUCTO_KARDEX;
    }

    public void setSTOCK_PRODUCTO_KARDEX(String STOCK_PRODUCTO_KARDEX) {
        this.STOCK_PRODUCTO_KARDEX = STOCK_PRODUCTO_KARDEX;
    }

    public String getFecha_Modificacion_Kardex() {
        return Fecha_Modificacion_Kardex;
    }

    public void setFecha_Modificacion_Kardex(String Fecha_Modificacion_Kardex) {
        this.Fecha_Modificacion_Kardex = Fecha_Modificacion_Kardex;
    }

    public String getTitulo_Kardex() {
        return Titulo_Kardex;
    }

    public void setTitulo_Kardex(String Titulo_Kardex) {
        this.Titulo_Kardex = Titulo_Kardex;
    }

    public String getEntrada_Kardex() {
        return Entrada_Kardex;
    }

    public void setEntrada_Kardex(String Entrada_Kardex) {
        this.Entrada_Kardex = Entrada_Kardex;
    }

    public String getSalida_Kardex() {
        return Salida_Kardex;
    }

    public void setSalida_Kardex(String Salida_Kardex) {
        this.Salida_Kardex = Salida_Kardex;
    }

    public String getDespues_Kardex() {
        return Despues_Kardex;
    }

    public void setDespues_Kardex(String Despues_Kardex) {
        this.Despues_Kardex = Despues_Kardex;
    }

    public String getAntes_Kardex() {
        return Antes_Kardex;
    }

    public void setAntes_Kardex(String Antes_Kardex) {
        this.Antes_Kardex = Antes_Kardex;
    }

    public String getModulo_Kardex() {
        return Modulo_Kardex;
    }

    public void setModulo_Kardex(String Modulo_Kardex) {
        this.Modulo_Kardex = Modulo_Kardex;
    }

    public String getUsuario_Modifico_LETRAS_Kardex() {
        return Usuario_Modifico_LETRAS_Kardex;
    }

    public void setUsuario_Modifico_LETRAS_Kardex(String Usuario_Modifico_LETRAS_Kardex) {
        this.Usuario_Modifico_LETRAS_Kardex = Usuario_Modifico_LETRAS_Kardex;
    }
}
