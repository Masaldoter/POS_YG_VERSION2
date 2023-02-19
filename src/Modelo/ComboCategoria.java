
package Modelo;

public class ComboCategoria {
    private int id;
    private String NombreCategoria;

    public ComboCategoria(int id, String NombreCategoria) {
        this.id = id;
        this.NombreCategoria = NombreCategoria;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setNombreCategoria(String NombreCategoria) {
        this.NombreCategoria = NombreCategoria;
    }
    
    @Override
    public String toString(){
        return this.getNombreCategoria();
    }
}
