/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesImpresion;

/**
 *
 * @author Masaldoter
 */
public class DatosTabla {
    private String Codigo, Cantidad, Nombre, PrecioUnitario, Total;
    
    public DatosTabla(){
        
    }

    public DatosTabla(String Codigo, String Cantidad, String Nombre, String PrecioUnitario, String Total) {
        this.Codigo = Codigo;
        this.Cantidad = Cantidad;
        this.Nombre = Nombre;
        this.PrecioUnitario = PrecioUnitario;
        this.Total = Total;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String Cantidad) {
        this.Cantidad = Cantidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(String PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total = Total;
    }
}
