/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author MASALDOTER_GT
 */
public class AbonosVales {
    private float Total_Abonado, Saldo;
    private String Fecha_Abono, PersonaAbono;
    private int Usuario_registroPago, id_Vale;
    
    public AbonosVales(){
        
    }

    public AbonosVales(float Total_Abonado, float Saldo, String Fecha_Abono, String PersonaAbono, int Usuario_registroPago, int id_Vale) {
        this.Total_Abonado = Total_Abonado;
        this.Saldo = Saldo;
        this.Fecha_Abono = Fecha_Abono;
        this.PersonaAbono = PersonaAbono;
        this.Usuario_registroPago = Usuario_registroPago;
        this.id_Vale = id_Vale;
    }

    public float getTotal_Abonado() {
        return Total_Abonado;
    }

    public void setTotal_Abonado(float Total_Abonado) {
        this.Total_Abonado = Total_Abonado;
    }

    public float getSaldo() {
        return Saldo;
    }

    public void setSaldo(float Saldo) {
        this.Saldo = Saldo;
    }

    public String getFecha_Abono() {
        return Fecha_Abono;
    }

    public void setFecha_Abono(String Fecha_Abono) {
        this.Fecha_Abono = Fecha_Abono;
    }

    public String getPersonaAbono() {
        return PersonaAbono;
    }

    public void setPersonaAbono(String PersonaAbono) {
        this.PersonaAbono = PersonaAbono;
    }

    public int getUsuario_registroPago() {
        return Usuario_registroPago;
    }

    public void setUsuario_registroPago(int Usuario_registroPago) {
        this.Usuario_registroPago = Usuario_registroPago;
    }

    public int getId_Vale() {
        return id_Vale;
    }

    public void setId_Vale(int id_Vale) {
        this.id_Vale = id_Vale;
    }
}
