
package ModeloWebService;

public class DatosConsultarDTE {
    private String NIT_EMISOR, TIPO_DTE, GUID, SERIE, NUMERO, ESTATUS, FECHA_DE_EMISION, FECHA_DE_CERTIFICACION, NIT_COMPRADOR, NOMBRE_COMPRADOR, ACUSE_RECIBO_SAT_DTE, ACUSE_RECIBO_ANULACION, ReferenciaInterna;
    private Boolean Estado;
    public DatosConsultarDTE(){
        
    }

    public DatosConsultarDTE(String NIT_EMISOR, String TIPO_DTE, String GUID, String SERIE, String NUMERO, String ESTATUS, String FECHA_DE_EMISION, String FECHA_DE_CERTIFICACION, String NIT_COMPRADOR, String NOMBRE_COMPRADOR, String ACUSE_RECIBO_SAT_DTE, String ACUSE_RECIBO_ANULACION, String ReferenciaInterna, Boolean Estado) {
        this.NIT_EMISOR = NIT_EMISOR;
        this.TIPO_DTE = TIPO_DTE;
        this.GUID = GUID;
        this.SERIE = SERIE;
        this.NUMERO = NUMERO;
        this.ESTATUS = ESTATUS;
        this.FECHA_DE_EMISION = FECHA_DE_EMISION;
        this.FECHA_DE_CERTIFICACION = FECHA_DE_CERTIFICACION;
        this.NIT_COMPRADOR = NIT_COMPRADOR;
        this.NOMBRE_COMPRADOR = NOMBRE_COMPRADOR;
        this.ACUSE_RECIBO_SAT_DTE = ACUSE_RECIBO_SAT_DTE;
        this.ACUSE_RECIBO_ANULACION = ACUSE_RECIBO_ANULACION;
        this.ReferenciaInterna = ReferenciaInterna;
        this.Estado = Estado;
    }
    public String getNIT_EMISOR() {
        return NIT_EMISOR;
    }

    public void setNIT_EMISOR(String NIT_EMISOR) {
        this.NIT_EMISOR = NIT_EMISOR;
    }

    public String getTIPO_DTE() {
        return TIPO_DTE;
    }

    public void setTIPO_DTE(String TIPO_DTE) {
        this.TIPO_DTE = TIPO_DTE;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getSERIE() {
        return SERIE;
    }

    public void setSERIE(String SERIE) {
        this.SERIE = SERIE;
    }

    public String getNUMERO() {
        return NUMERO;
    }

    public void setNUMERO(String NUMERO) {
        this.NUMERO = NUMERO;
    }

    public String getESTATUS() {
        return ESTATUS;
    }

    public void setESTATUS(String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }

    public String getFECHA_DE_EMISION() {
        return FECHA_DE_EMISION;
    }

    public void setFECHA_DE_EMISION(String FECHA_DE_EMISION) {
        this.FECHA_DE_EMISION = FECHA_DE_EMISION;
    }

    public String getFECHA_DE_CERTIFICACION() {
        return FECHA_DE_CERTIFICACION;
    }

    public void setFECHA_DE_CERTIFICACION(String FECHA_DE_CERTIFICACION) {
        this.FECHA_DE_CERTIFICACION = FECHA_DE_CERTIFICACION;
    }

    public String getNIT_COMPRADOR() {
        return NIT_COMPRADOR;
    }

    public void setNIT_COMPRADOR(String NIT_COMPRADOR) {
        this.NIT_COMPRADOR = NIT_COMPRADOR;
    }

    public String getNOMBRE_COMPRADOR() {
        return NOMBRE_COMPRADOR;
    }

    public void setNOMBRE_COMPRADOR(String NOMBRE_COMPRADOR) {
        this.NOMBRE_COMPRADOR = NOMBRE_COMPRADOR;
    }

    public String getACUSE_RECIBO_SAT_DTE() {
        return ACUSE_RECIBO_SAT_DTE;
    }

    public void setACUSE_RECIBO_SAT_DTE(String ACUSE_RECIBO_SAT_DTE) {
        this.ACUSE_RECIBO_SAT_DTE = ACUSE_RECIBO_SAT_DTE;
    }

    public String getACUSE_RECIBO_ANULACION() {
        return ACUSE_RECIBO_ANULACION;
    }

    public void setACUSE_RECIBO_ANULACION(String ACUSE_RECIBO_ANULACION) {
        this.ACUSE_RECIBO_ANULACION = ACUSE_RECIBO_ANULACION;
    }

    public String getReferenciaInterna() {
        return ReferenciaInterna;
    }

    public void setReferenciaInterna(String ReferenciaInterna) {
        this.ReferenciaInterna = ReferenciaInterna;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean Estado) {
        this.Estado = Estado;
    }
}
