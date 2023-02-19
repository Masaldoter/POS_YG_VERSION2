/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigosDeBarras;

/**
 *
 * @author Masaldoter
 */
public class ParametrosCodigosDeBarras {
    private String Nombreproducto, PrecioPublico, CodigoDeBarrasOId,  CostoLetras;
    public ParametrosCodigosDeBarras(){
        
    }

    public ParametrosCodigosDeBarras(String Nombreproducto, String PrecioPublico, String CodigoDeBarrasOId, String CostoLetras) {
        this.Nombreproducto = Nombreproducto;
        this.PrecioPublico = PrecioPublico;
        this.CodigoDeBarrasOId = CodigoDeBarrasOId;
        this.CostoLetras = CostoLetras;
    }

    public String getNombreproducto() {
        return Nombreproducto;
    }

    public void setNombreproducto(String Nombreproducto) {
        this.Nombreproducto = Nombreproducto;
    }

    public String getPrecioPublico() {
        return PrecioPublico;
    }

    public void setPrecioPublico(String PrecioPublico) {
        this.PrecioPublico = PrecioPublico;
    }

    public String getCodigoDeBarrasOId() {
        return CodigoDeBarrasOId;
    }

    public void setCodigoDeBarrasOId(String CodigoDeBarrasOId) {
        this.CodigoDeBarrasOId = CodigoDeBarrasOId;
    }

    public String getCostoLetras() {
        return CostoLetras;
    }

    public void setCostoLetras(String CostoLetras) {
        this.CostoLetras = CostoLetras;
    }
}
