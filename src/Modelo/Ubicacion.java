
package Modelo;

public class Ubicacion {
    private int idubicaciones;
    private String NombreUbicacion, DetalleUbicacion;
    public Ubicacion(){
        
    }

    public Ubicacion(int idubicaciones, String NombreUbicacion, String DetalleUbicacion) {
        this.idubicaciones = idubicaciones;
        this.NombreUbicacion = NombreUbicacion;
        this.DetalleUbicacion = DetalleUbicacion;
    }

    public int getIdubicaciones() {
        return idubicaciones;
    }

    public void setIdubicaciones(int idubicaciones) {
        this.idubicaciones = idubicaciones;
    }

    public String getNombreUbicacion() {
        return NombreUbicacion;
    }

    public void setNombreUbicacion(String NombreUbicacion) {
        this.NombreUbicacion = NombreUbicacion;
    }

    public String getDetalleUbicacion() {
        return DetalleUbicacion;
    }

    public void setDetalleUbicacion(String DetalleUbicacion) {
        this.DetalleUbicacion = DetalleUbicacion;
    }
}
