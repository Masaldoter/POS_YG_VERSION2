//Getters de codigos

package Clases_Reportes;


public class Codigos {
    private String Nombreproducto;
    private String Publico;
    private String Id, CostoLetras;
    public Codigos(){
        
    } 

    public Codigos(String Nombreproducto, String Publico, String Id, String CostoLetras) {
        this.Nombreproducto = Nombreproducto;
        this.Publico = Publico;
        this.Id = Id;
        this.CostoLetras = CostoLetras;
    }

    public String getNombreproducto() {
        return Nombreproducto;
    }

    public void setNombreproducto(String Nombreproducto) {
        this.Nombreproducto = Nombreproducto;
    }

    public String getPublico() {
        return Publico;
    }

    public void setPublico(String Publico) {
        this.Publico = Publico;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getCostoLetras() {
        return CostoLetras;
    }

    public void setCostoLetras(String CostoLetras) {
        this.CostoLetras = CostoLetras;
    }
}
