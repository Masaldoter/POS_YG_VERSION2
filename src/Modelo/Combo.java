
package Modelo;

public class Combo {
    private int id;
    private String NombreProveedor;

    public Combo(int id, String NombreProveedor) {
        this.id = id;
        this.NombreProveedor = NombreProveedor;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreProveedor() {
        return NombreProveedor;
    }

    public void setNombreProveedor(String NombreProveedor) {
        this.NombreProveedor = NombreProveedor;
    }
    
    @Override
    public String toString(){
        return this.getNombreProveedor();
    }
}
