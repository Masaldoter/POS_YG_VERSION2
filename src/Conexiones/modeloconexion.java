
package Conexiones;

public class modeloconexion {
    
    private String Ip, Puerto, Nombre, Contrasena, NombreBase;

    public modeloconexion(){
        
    }
    public modeloconexion(String Ip, String Puerto, String Nombre, String Contrasena, String NombreBase) {
        this.Ip = Ip;
        this.Puerto = Puerto;
        this.Nombre = Nombre;
        this.Contrasena = Contrasena;
        this.NombreBase = NombreBase;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String Ip) {
        this.Ip = Ip;
    }

    public String getPuerto() {
        return Puerto;
    }

    public void setPuerto(String Puerto) {
        this.Puerto = Puerto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getNombreBase() {
        return NombreBase;
    }

    public void setNombreBase(String NombreBase) {
        this.NombreBase = NombreBase;
    }
    
    
}
