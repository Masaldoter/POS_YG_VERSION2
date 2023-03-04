
package Modelo;
import javax.swing.JButton;


public class Venta {
    private int IdRegistro, id_CAJA_registro;
    private String Cliente, IDENTIFICACION_CLIENTE, TIPO_IDENTIFICACION_CLIENTE, DireccionCliente;
    private Float Total, pagocon, cambio;
    private String FormaPago, NumeroTransaccion, TotalEnLetras, NoFactura;
    private String Fecha, Hora;
    private int Usuario;
    private String Observacion;
    private String NombreCertificador, NitCertificador, FechaAutorizacion, NumeroAutorizacion,
    NumeroDocumento, SerieDocumento, TipoDocumentoFel, Estado, NitEmisor, NUMERO_INTERNO;
    private JButton Detalles,Anular;

    public Venta(){
        
    }

    public Venta(int IdRegistro, int id_CAJA_registro, String Cliente, String IDENTIFICACION_CLIENTE, String TIPO_IDENTIFICACION_CLIENTE, String DireccionCliente, Float Total, Float pagocon, Float cambio, String FormaPago, String NumeroTransaccion, String TotalEnLetras, String NoFactura, String Fecha, String Hora, int Usuario, String Observacion, String NombreCertificador, String NitCertificador, String FechaAutorizacion, String NumeroAutorizacion, String NumeroDocumento, String SerieDocumento, String TipoDocumentoFel, String Estado, String NitEmisor, String NUMERO_INTERNO, JButton Detalles, JButton Anular) {
        this.IdRegistro = IdRegistro;
        this.id_CAJA_registro = id_CAJA_registro;
        this.Cliente = Cliente;
        this.IDENTIFICACION_CLIENTE = IDENTIFICACION_CLIENTE;
        this.TIPO_IDENTIFICACION_CLIENTE = TIPO_IDENTIFICACION_CLIENTE;
        this.DireccionCliente = DireccionCliente;
        this.Total = Total;
        this.pagocon = pagocon;
        this.cambio = cambio;
        this.FormaPago = FormaPago;
        this.NumeroTransaccion = NumeroTransaccion;
        this.TotalEnLetras = TotalEnLetras;
        this.NoFactura = NoFactura;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.Usuario = Usuario;
        this.Observacion = Observacion;
        this.NombreCertificador = NombreCertificador;
        this.NitCertificador = NitCertificador;
        this.FechaAutorizacion = FechaAutorizacion;
        this.NumeroAutorizacion = NumeroAutorizacion;
        this.NumeroDocumento = NumeroDocumento;
        this.SerieDocumento = SerieDocumento;
        this.TipoDocumentoFel = TipoDocumentoFel;
        this.Estado = Estado;
        this.NitEmisor = NitEmisor;
        this.NUMERO_INTERNO = NUMERO_INTERNO;
        this.Detalles = Detalles;
        this.Anular = Anular;
    }

    public int getIdRegistro() {
        return IdRegistro;
    }

    public void setIdRegistro(int IdRegistro) {
        this.IdRegistro = IdRegistro;
    }

    public int getId_CAJA_registro() {
        return id_CAJA_registro;
    }

    public void setId_CAJA_registro(int id_CAJA_registro) {
        this.id_CAJA_registro = id_CAJA_registro;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public String getIDENTIFICACION_CLIENTE() {
        return IDENTIFICACION_CLIENTE;
    }

    public void setIDENTIFICACION_CLIENTE(String IDENTIFICACION_CLIENTE) {
        this.IDENTIFICACION_CLIENTE = IDENTIFICACION_CLIENTE;
    }

    public String getTIPO_IDENTIFICACION_CLIENTE() {
        return TIPO_IDENTIFICACION_CLIENTE;
    }

    public void setTIPO_IDENTIFICACION_CLIENTE(String TIPO_IDENTIFICACION_CLIENTE) {
        this.TIPO_IDENTIFICACION_CLIENTE = TIPO_IDENTIFICACION_CLIENTE;
    }

    public String getDireccionCliente() {
        return DireccionCliente;
    }

    public void setDireccionCliente(String DireccionCliente) {
        this.DireccionCliente = DireccionCliente;
    }

    public Float getTotal() {
        return Total;
    }

    public void setTotal(Float Total) {
        this.Total = Total;
    }

    public Float getPagocon() {
        return pagocon;
    }

    public void setPagocon(Float pagocon) {
        this.pagocon = pagocon;
    }

    public Float getCambio() {
        return cambio;
    }

    public void setCambio(Float cambio) {
        this.cambio = cambio;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String FormaPago) {
        this.FormaPago = FormaPago;
    }

    public String getNumeroTransaccion() {
        return NumeroTransaccion;
    }

    public void setNumeroTransaccion(String NumeroTransaccion) {
        this.NumeroTransaccion = NumeroTransaccion;
    }

    public String getTotalEnLetras() {
        return TotalEnLetras;
    }

    public void setTotalEnLetras(String TotalEnLetras) {
        this.TotalEnLetras = TotalEnLetras;
    }

    public String getNoFactura() {
        return NoFactura;
    }

    public void setNoFactura(String NoFactura) {
        this.NoFactura = NoFactura;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public String getHora() {
        return Hora;
    }

    public void setHora(String Hora) {
        this.Hora = Hora;
    }

    public int getUsuario() {
        return Usuario;
    }

    public void setUsuario(int Usuario) {
        this.Usuario = Usuario;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public String getNombreCertificador() {
        return NombreCertificador;
    }

    public void setNombreCertificador(String NombreCertificador) {
        this.NombreCertificador = NombreCertificador;
    }

    public String getNitCertificador() {
        return NitCertificador;
    }

    public void setNitCertificador(String NitCertificador) {
        this.NitCertificador = NitCertificador;
    }

    public String getFechaAutorizacion() {
        return FechaAutorizacion;
    }

    public void setFechaAutorizacion(String FechaAutorizacion) {
        this.FechaAutorizacion = FechaAutorizacion;
    }

    public String getNumeroAutorizacion() {
        return NumeroAutorizacion;
    }

    public void setNumeroAutorizacion(String NumeroAutorizacion) {
        this.NumeroAutorizacion = NumeroAutorizacion;
    }

    public String getNumeroDocumento() {
        return NumeroDocumento;
    }

    public void setNumeroDocumento(String NumeroDocumento) {
        this.NumeroDocumento = NumeroDocumento;
    }

    public String getSerieDocumento() {
        return SerieDocumento;
    }

    public void setSerieDocumento(String SerieDocumento) {
        this.SerieDocumento = SerieDocumento;
    }

    public String getTipoDocumentoFel() {
        return TipoDocumentoFel;
    }

    public void setTipoDocumentoFel(String TipoDocumentoFel) {
        this.TipoDocumentoFel = TipoDocumentoFel;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getNitEmisor() {
        return NitEmisor;
    }

    public void setNitEmisor(String NitEmisor) {
        this.NitEmisor = NitEmisor;
    }

    public String getNUMERO_INTERNO() {
        return NUMERO_INTERNO;
    }

    public void setNUMERO_INTERNO(String NUMERO_INTERNO) {
        this.NUMERO_INTERNO = NUMERO_INTERNO;
    }

    public JButton getDetalles() {
        return Detalles;
    }

    public void setDetalles(JButton Detalles) {
        this.Detalles = Detalles;
    }

    public JButton getAnular() {
        return Anular;
    }

    public void setAnular(JButton Anular) {
        this.Anular = Anular;
    }
}
