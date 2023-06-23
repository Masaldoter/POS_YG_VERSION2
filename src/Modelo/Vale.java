
package Modelo;

public class Vale {
    private int id_Caja;
    private int Id_Cliente;
    private String NoVale, Estado;
    private String FechaEmision, HoraEmision;
    private String  Fecha_Anulacion, Hora_Anulacion;
    private int Usuario_registro, Usuario_Anulo;
    public Vale(){
        
    }

    public Vale(int id_Caja, int Id_Cliente, String NoVale, String Estado, String FechaEmision, String HoraEmision, String Fecha_Anulacion, String Hora_Anulacion, int Usuario_registro, int Usuario_Anulo) {
        this.id_Caja = id_Caja;
        this.Id_Cliente = Id_Cliente;
        this.NoVale = NoVale;
        this.Estado = Estado;
        this.FechaEmision = FechaEmision;
        this.HoraEmision = HoraEmision;
        this.Fecha_Anulacion = Fecha_Anulacion;
        this.Hora_Anulacion = Hora_Anulacion;
        this.Usuario_registro = Usuario_registro;
        this.Usuario_Anulo = Usuario_Anulo;
    }

    public int getId_Caja() {
        return id_Caja;
    }

    public void setId_Caja(int id_Caja) {
        this.id_Caja = id_Caja;
    }

    public int getId_Cliente() {
        return Id_Cliente;
    }

    public void setId_Cliente(int Id_Cliente) {
        this.Id_Cliente = Id_Cliente;
    }

    public String getNoVale() {
        return NoVale;
    }

    public void setNoVale(String NoVale) {
        this.NoVale = NoVale;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFechaEmision() {
        return FechaEmision;
    }

    public void setFechaEmision(String FechaEmision) {
        this.FechaEmision = FechaEmision;
    }

    public String getHoraEmision() {
        return HoraEmision;
    }

    public void setHoraEmision(String HoraEmision) {
        this.HoraEmision = HoraEmision;
    }

    public String getFecha_Anulacion() {
        return Fecha_Anulacion;
    }

    public void setFecha_Anulacion(String Fecha_Anulacion) {
        this.Fecha_Anulacion = Fecha_Anulacion;
    }

    public String getHora_Anulacion() {
        return Hora_Anulacion;
    }

    public void setHora_Anulacion(String Hora_Anulacion) {
        this.Hora_Anulacion = Hora_Anulacion;
    }

    public int getUsuario_registro() {
        return Usuario_registro;
    }

    public void setUsuario_registro(int Usuario_registro) {
        this.Usuario_registro = Usuario_registro;
    }

    public int getUsuario_Anulo() {
        return Usuario_Anulo;
    }

    public void setUsuario_Anulo(int Usuario_Anulo) {
        this.Usuario_Anulo = Usuario_Anulo;
    }
}
