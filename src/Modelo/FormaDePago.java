/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MASALDOTER_GT
 */
public class FormaDePago {
    private int idforma_pago;
    private String Numero;
    private Float Efectivo, Tarjeta, Transferencia, Cheque, Otro; 

    public int getIdforma_pago() {
        return idforma_pago;
    }

    public void setIdforma_pago(int idforma_pago) {
        this.idforma_pago = idforma_pago;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public Float getEfectivo() {
        return Efectivo;
    }

    public void setEfectivo(Float Efectivo) {
        this.Efectivo = Efectivo;
    }

    public Float getTarjeta() {
        return Tarjeta;
    }

    public void setTarjeta(Float Tarjeta) {
        this.Tarjeta = Tarjeta;
    }

    public Float getTransferencia() {
        return Transferencia;
    }

    public void setTransferencia(Float Transferencia) {
        this.Transferencia = Transferencia;
    }

    public Float getCheque() {
        return Cheque;
    }

    public void setCheque(Float Cheque) {
        this.Cheque = Cheque;
    }

    public Float getOtro() {
        return Otro;
    }

    public void setOtro(Float Otro) {
        this.Otro = Otro;
    }
    
    
}
