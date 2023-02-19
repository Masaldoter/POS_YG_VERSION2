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
public class ConsultasVentas {
    private int IdVenta;
    private String NombreVenta, StockVenta, PrecioPublicoVenta, PrecioEspecialVenta, PrecioReventaVenta, UbicacionVentaVenta, CostoLetrasVenta;
    public ConsultasVentas(){
        
    }

    public ConsultasVentas(int IdVenta, String NombreVenta, String StockVenta, String PrecioPublicoVenta, String PrecioEspecialVenta, String PrecioReventaVenta, String UbicacionVentaVenta, String CostoLetrasVenta) {
        this.IdVenta = IdVenta;
        this.NombreVenta = NombreVenta;
        this.StockVenta = StockVenta;
        this.PrecioPublicoVenta = PrecioPublicoVenta;
        this.PrecioEspecialVenta = PrecioEspecialVenta;
        this.PrecioReventaVenta = PrecioReventaVenta;
        this.UbicacionVentaVenta = UbicacionVentaVenta;
        this.CostoLetrasVenta = CostoLetrasVenta;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public String getNombreVenta() {
        return NombreVenta;
    }

    public void setNombreVenta(String NombreVenta) {
        this.NombreVenta = NombreVenta;
    }

    public String getStockVenta() {
        return StockVenta;
    }

    public void setStockVenta(String StockVenta) {
        this.StockVenta = StockVenta;
    }

    public String getPrecioPublicoVenta() {
        return PrecioPublicoVenta;
    }

    public void setPrecioPublicoVenta(String PrecioPublicoVenta) {
        this.PrecioPublicoVenta = PrecioPublicoVenta;
    }

    public String getPrecioEspecialVenta() {
        return PrecioEspecialVenta;
    }

    public void setPrecioEspecialVenta(String PrecioEspecialVenta) {
        this.PrecioEspecialVenta = PrecioEspecialVenta;
    }

    public String getPrecioReventaVenta() {
        return PrecioReventaVenta;
    }

    public void setPrecioReventaVenta(String PrecioReventaVenta) {
        this.PrecioReventaVenta = PrecioReventaVenta;
    }

    public String getUbicacionVentaVenta() {
        return UbicacionVentaVenta;
    }

    public void setUbicacionVentaVenta(String UbicacionVentaVenta) {
        this.UbicacionVentaVenta = UbicacionVentaVenta;
    }

    public String getCostoLetrasVenta() {
        return CostoLetrasVenta;
    }

    public void setCostoLetrasVenta(String CostoLetrasVenta) {
        this.CostoLetrasVenta = CostoLetrasVenta;
    }
}
