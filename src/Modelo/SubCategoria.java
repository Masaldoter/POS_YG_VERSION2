package Modelo;

import javax.swing.JButton;

public class SubCategoria {
    private int idsubcategoria, IdCategoria;
    private String NombreSubcategoria;
    private JButton Eliminar;
    
    public SubCategoria(){
        
    }

    public SubCategoria(int idsubcategoria, int IdCategoria, String NombreSubcategoria, JButton Eliminar) {
        this.idsubcategoria = idsubcategoria;
        this.IdCategoria = IdCategoria;
        this.NombreSubcategoria = NombreSubcategoria;
        this.Eliminar = Eliminar;
    }

    public int getIdsubcategoria() {
        return idsubcategoria;
    }

    public void setIdsubcategoria(int idsubcategoria) {
        this.idsubcategoria = idsubcategoria;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getNombreSubcategoria() {
        return NombreSubcategoria;
    }

    public void setNombreSubcategoria(String NombreSubcategoria) {
        this.NombreSubcategoria = NombreSubcategoria;
    }

    public JButton getEliminar() {
        return Eliminar;
    }

    public void setEliminar(JButton Eliminar) {
        this.Eliminar = Eliminar;
    }
}
