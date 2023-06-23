
package Modelo;


public class login {
     private String Nombre;
    private String Contraseña;
    private String Rol;
    private int idlogin1;
    private String NombreUsuario;
    private String ruta;
    private String FechaIngreso, UltimaVezIngreso, Estado, Color, Estado_Registro, AccesoDirecto, Imagen;
    public login(){
        
    }

    public login(String Nombre, String Contraseña, String Rol, int idlogin1, String NombreUsuario, String ruta, String FechaIngreso, String UltimaVezIngreso, String Estado, String Color, String Estado_Registro, String AccesoDirecto, String Imagen) {
        this.Nombre = Nombre;
        this.Contraseña = Contraseña;
        this.Rol = Rol;
        this.idlogin1 = idlogin1;
        this.NombreUsuario = NombreUsuario;
        this.ruta = ruta;
        this.FechaIngreso = FechaIngreso;
        this.UltimaVezIngreso = UltimaVezIngreso;
        this.Estado = Estado;
        this.Color = Color;
        this.Estado_Registro = Estado_Registro;
        this.AccesoDirecto = AccesoDirecto;
        this.Imagen = Imagen;
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

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getEstado_Registro() {
        return Estado_Registro;
    }

    public void setEstado_Registro(String Estado_Registro) {
        this.Estado_Registro = Estado_Registro;
    }

    public String getAccesoDirecto() {
        return AccesoDirecto;
    }

    public void setAccesoDirecto(String AccesoDirecto) {
        this.AccesoDirecto = AccesoDirecto;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
}
