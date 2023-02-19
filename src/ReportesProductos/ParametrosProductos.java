/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesProductos;

/**
 *
 * @author Masaldoter
 */
public class ParametrosProductos {
    private String codigobarras, nombreproducto, stock, preciopublico, codigoletras, proveedor;
    
    public ParametrosProductos(){
        
    }

    public ParametrosProductos(String codigobarras, String nombreproducto, String stock, String preciopublico, String codigoletras, String proveedor) {
        this.codigobarras = codigobarras;
        this.nombreproducto = nombreproducto;
        this.stock = stock;
        this.preciopublico = preciopublico;
        this.codigoletras = codigoletras;
        this.proveedor = proveedor;
    }

    public String getCodigobarras() {
        return codigobarras;
    }

    public void setCodigobarras(String codigobarras) {
        this.codigobarras = codigobarras;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPreciopublico() {
        return preciopublico;
    }

    public void setPreciopublico(String preciopublico) {
        this.preciopublico = preciopublico;
    }

    public String getCodigoletras() {
        return codigoletras;
    }

    public void setCodigoletras(String codigoletras) {
        this.codigoletras = codigoletras;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
