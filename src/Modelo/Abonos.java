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
public class Abonos {
    private Float TotalAbono;
    private String PersonaAbono;
    private String FechaAbono;
    private String HoraAbono;
    private int idVale;
    private int UsuarioAbono;
    
    public Abonos(){
        
    }

    public Abonos(Float TotalAbono, String PersonaAbono, String FechaAbono, String HoraAbono, int idVale, int UsuarioAbono) {
        this.TotalAbono = TotalAbono;
        this.PersonaAbono = PersonaAbono;
        this.FechaAbono = FechaAbono;
        this.HoraAbono = HoraAbono;
        this.idVale = idVale;
        this.UsuarioAbono = UsuarioAbono;
    }

    public Float getTotalAbono() {
        return TotalAbono;
    }

    public void setTotalAbono(Float TotalAbono) {
        this.TotalAbono = TotalAbono;
    }

    public String getPersonaAbono() {
        return PersonaAbono;
    }

    public void setPersonaAbono(String PersonaAbono) {
        this.PersonaAbono = PersonaAbono;
    }

    public String getFechaAbono() {
        return FechaAbono;
    }

    public void setFechaAbono(String FechaAbono) {
        this.FechaAbono = FechaAbono;
    }

    public String getHoraAbono() {
        return HoraAbono;
    }

    public void setHoraAbono(String HoraAbono) {
        this.HoraAbono = HoraAbono;
    }

    public int getIdVale() {
        return idVale;
    }

    public void setIdVale(int idVale) {
        this.idVale = idVale;
    }

    public int getUsuarioAbono() {
        return UsuarioAbono;
    }

    public void setUsuarioAbono(int UsuarioAbono) {
        this.UsuarioAbono = UsuarioAbono;
    }
}
