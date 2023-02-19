
package Modelo;

import javax.swing.JButton;

public class Categoria {
    private int idCategoria;
    private String Categoria;
    private JButton VerSubCategoria;
    public Categoria(){
        
    }

    public Categoria(int idCategoria, String Categoria, JButton VerSubCategoria) {
        this.idCategoria = idCategoria;
        this.Categoria = Categoria;
        this.VerSubCategoria = VerSubCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public JButton getVerSubCategoria() {
        return VerSubCategoria;
    }

    public void setVerSubCategoria(JButton VerSubCategoria) {
        this.VerSubCategoria = VerSubCategoria;
    }
}
