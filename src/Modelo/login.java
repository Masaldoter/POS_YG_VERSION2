
package Modelo;


public class login {
     private String Nombre;
    private String Contraseña;
    private String Rol;
    private int idlogin1;
    private String NombreUsuario;
    private String ruta;
    private byte[] imagen;
    private String FechaIngreso, UltimaVezIngreso, Estado;
    public login(){
        
    }

    public login(String Nombre, String Contraseña, String Rol, int idlogin1, String NombreUsuario, String ruta, byte[] imagen, String FechaIngreso, String UltimaVezIngreso, String Estado) {
        this.Nombre = Nombre;
        this.Contraseña = Contraseña;
        this.Rol = Rol;
        this.idlogin1 = idlogin1;
        this.NombreUsuario = NombreUsuario;
        this.ruta = ruta;
        this.imagen = imagen;
        this.FechaIngreso = FechaIngreso;
        this.UltimaVezIngreso = UltimaVezIngreso;
        this.Estado = Estado;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public int getIdlogin1() {
        return idlogin1;
    }

    public void setIdlogin1(int idlogin1) {
        this.idlogin1 = idlogin1;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

    public String getUltimaVezIngreso() {
        return UltimaVezIngreso;
    }

    public void setUltimaVezIngreso(String UltimaVezIngreso) {
        this.UltimaVezIngreso = UltimaVezIngreso;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
}
