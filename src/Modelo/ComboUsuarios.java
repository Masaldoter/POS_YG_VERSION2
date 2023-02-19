
package Modelo;


public class ComboUsuarios {
    private int idUsuario;
    private String NombreUsuario;

    public ComboUsuarios(int idUsuario, String NombreUsuario) {
        this.idUsuario = idUsuario;
        this.NombreUsuario = NombreUsuario;
    }
    public int getidUsuario() {
        return idUsuario;
    }

    public void setidUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }
    
    @Override
    public String toString(){
        return this.getNombreUsuario();
    }
}
