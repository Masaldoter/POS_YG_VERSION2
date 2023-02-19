package ModeloWebService;

public class EnviarDatosNotaDeCredito {
    private String FechaHoraEmision, TipoDocumento, CodigoMoneda;
    private String NITEmisor, NombreEmisor, CodigoEstablecimiento, NombreComercial, AfiliacionIVA, DireccionEmisor, CodigoPostalEmisor, MunicipioEmisor, DepartamentoEmisor, PaisEmisor;
    private String NombreReceptor, IDReceptor, DireccionReceptor, CodigoPostalReceptor, MunicipioReceptor, DepartamentoReceptor, PaisReceptor;
    private String TipoFrase, CodigoEscenario;
    private String NumeroLineaProducto, BienOServicioProducto, CantidadProducto, UnidadMedidaProducto, DescripcionProducto, PrecioUnitarioProducto, PrecioProducto, DescuentoProducto, TotalProducto;
    private String NombreCortoImpuesto, CodigoUnidadGravableImpuesto, MontoGravableImpuesto, MontoImpuesto;
    private String NombreCortoTotales, TotalMontoImpuestoTotales, GranTotalTotales;
    private String NumeroAutorizacionDocumentoOrigen, FechaEmisionDocumentoOrigen, MotivoAjuste, NumeroDocumentoOrigen, SerieDocumentoOrigen;
    private int TotalDeProductos;
    
    public EnviarDatosNotaDeCredito(){
        
    }

    public EnviarDatosNotaDeCredito(String FechaHoraEmision, String TipoDocumento, String CodigoMoneda, String NITEmisor, String NombreEmisor, String CodigoEstablecimiento, String NombreComercial, String AfiliacionIVA, String DireccionEmisor, String CodigoPostalEmisor, String MunicipioEmisor, String DepartamentoEmisor, String PaisEmisor, String NombreReceptor, String IDReceptor, String DireccionReceptor, String CodigoPostalReceptor, String MunicipioReceptor, String DepartamentoReceptor, String PaisReceptor, String TipoFrase, String CodigoEscenario, String NumeroLineaProducto, String BienOServicioProducto, String CantidadProducto, String UnidadMedidaProducto, String DescripcionProducto, String PrecioUnitarioProducto, String PrecioProducto, String DescuentoProducto, String TotalProducto, String NombreCortoImpuesto, String CodigoUnidadGravableImpuesto, String MontoGravableImpuesto, String MontoImpuesto, String NombreCortoTotales, String TotalMontoImpuestoTotales, String GranTotalTotales, String NumeroAutorizacionDocumentoOrigen, String FechaEmisionDocumentoOrigen, String MotivoAjuste, String NumeroDocumentoOrigen, String SerieDocumentoOrigen, int TotalDeProductos) {
        this.FechaHoraEmision = FechaHoraEmision;
        this.TipoDocumento = TipoDocumento;
        this.CodigoMoneda = CodigoMoneda;
        this.NITEmisor = NITEmisor;
        this.NombreEmisor = NombreEmisor;
        this.CodigoEstablecimiento = CodigoEstablecimiento;
        this.NombreComercial = NombreComercial;
        this.AfiliacionIVA = AfiliacionIVA;
        this.DireccionEmisor = DireccionEmisor;
        this.CodigoPostalEmisor = CodigoPostalEmisor;
        this.MunicipioEmisor = MunicipioEmisor;
        this.DepartamentoEmisor = DepartamentoEmisor;
        this.PaisEmisor = PaisEmisor;
        this.NombreReceptor = NombreReceptor;
        this.IDReceptor = IDReceptor;
        this.DireccionReceptor = DireccionReceptor;
        this.CodigoPostalReceptor = CodigoPostalReceptor;
        this.MunicipioReceptor = MunicipioReceptor;
        this.DepartamentoReceptor = DepartamentoReceptor;
        this.PaisReceptor = PaisReceptor;
        this.TipoFrase = TipoFrase;
        this.CodigoEscenario = CodigoEscenario;
        this.NumeroLineaProducto = NumeroLineaProducto;
        this.BienOServicioProducto = BienOServicioProducto;
        this.CantidadProducto = CantidadProducto;
        this.UnidadMedidaProducto = UnidadMedidaProducto;
        this.DescripcionProducto = DescripcionProducto;
        this.PrecioUnitarioProducto = PrecioUnitarioProducto;
        this.PrecioProducto = PrecioProducto;
        this.DescuentoProducto = DescuentoProducto;
        this.TotalProducto = TotalProducto;
        this.NombreCortoImpuesto = NombreCortoImpuesto;
        this.CodigoUnidadGravableImpuesto = CodigoUnidadGravableImpuesto;
        this.MontoGravableImpuesto = MontoGravableImpuesto;
        this.MontoImpuesto = MontoImpuesto;
        this.NombreCortoTotales = NombreCortoTotales;
        this.TotalMontoImpuestoTotales = TotalMontoImpuestoTotales;
        this.GranTotalTotales = GranTotalTotales;
        this.NumeroAutorizacionDocumentoOrigen = NumeroAutorizacionDocumentoOrigen;
        this.FechaEmisionDocumentoOrigen = FechaEmisionDocumentoOrigen;
        this.MotivoAjuste = MotivoAjuste;
        this.NumeroDocumentoOrigen = NumeroDocumentoOrigen;
        this.SerieDocumentoOrigen = SerieDocumentoOrigen;
        this.TotalDeProductos = TotalDeProductos;
    }

    public String getFechaHoraEmision() {
        return FechaHoraEmision;
    }

    public void setFechaHoraEmision(String FechaHoraEmision) {
        this.FechaHoraEmision = FechaHoraEmision;
    }

    public String getTipoDocumento() {
        return TipoDocumento;
    }

    public void setTipoDocumento(String TipoDocumento) {
        this.TipoDocumento = TipoDocumento;
    }

    public String getCodigoMoneda() {
        return CodigoMoneda;
    }

    public void setCodigoMoneda(String CodigoMoneda) {
        this.CodigoMoneda = CodigoMoneda;
    }

    public String getNITEmisor() {
        return NITEmisor;
    }

    public void setNITEmisor(String NITEmisor) {
        this.NITEmisor = NITEmisor;
    }

    public String getNombreEmisor() {
        return NombreEmisor;
    }

    public void setNombreEmisor(String NombreEmisor) {
        this.NombreEmisor = NombreEmisor;
    }

    public String getCodigoEstablecimiento() {
        return CodigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String CodigoEstablecimiento) {
        this.CodigoEstablecimiento = CodigoEstablecimiento;
    }

    public String getNombreComercial() {
        return NombreComercial;
    }

    public void setNombreComercial(String NombreComercial) {
        this.NombreComercial = NombreComercial;
    }

    public String getAfiliacionIVA() {
        return AfiliacionIVA;
    }

    public void setAfiliacionIVA(String AfiliacionIVA) {
        this.AfiliacionIVA = AfiliacionIVA;
    }

    public String getDireccionEmisor() {
        return DireccionEmisor;
    }

    public void setDireccionEmisor(String DireccionEmisor) {
        this.DireccionEmisor = DireccionEmisor;
    }

    public String getCodigoPostalEmisor() {
        return CodigoPostalEmisor;
    }

    public void setCodigoPostalEmisor(String CodigoPostalEmisor) {
        this.CodigoPostalEmisor = CodigoPostalEmisor;
    }

    public String getMunicipioEmisor() {
        return MunicipioEmisor;
    }

    public void setMunicipioEmisor(String MunicipioEmisor) {
        this.MunicipioEmisor = MunicipioEmisor;
    }

    public String getDepartamentoEmisor() {
        return DepartamentoEmisor;
    }

    public void setDepartamentoEmisor(String DepartamentoEmisor) {
        this.DepartamentoEmisor = DepartamentoEmisor;
    }

    public String getPaisEmisor() {
        return PaisEmisor;
    }

    public void setPaisEmisor(String PaisEmisor) {
        this.PaisEmisor = PaisEmisor;
    }

    public String getNombreReceptor() {
        return NombreReceptor;
    }

    public void setNombreReceptor(String NombreReceptor) {
        this.NombreReceptor = NombreReceptor;
    }

    public String getIDReceptor() {
        return IDReceptor;
    }

    public void setIDReceptor(String IDReceptor) {
        this.IDReceptor = IDReceptor;
    }

    public String getDireccionReceptor() {
        return DireccionReceptor;
    }

    public void setDireccionReceptor(String DireccionReceptor) {
        this.DireccionReceptor = DireccionReceptor;
    }

    public String getCodigoPostalReceptor() {
        return CodigoPostalReceptor;
    }

    public void setCodigoPostalReceptor(String CodigoPostalReceptor) {
        this.CodigoPostalReceptor = CodigoPostalReceptor;
    }

    public String getMunicipioReceptor() {
        return MunicipioReceptor;
    }

    public void setMunicipioReceptor(String MunicipioReceptor) {
        this.MunicipioReceptor = MunicipioReceptor;
    }

    public String getDepartamentoReceptor() {
        return DepartamentoReceptor;
    }

    public void setDepartamentoReceptor(String DepartamentoReceptor) {
        this.DepartamentoReceptor = DepartamentoReceptor;
    }

    public String getPaisReceptor() {
        return PaisReceptor;
    }

    public void setPaisReceptor(String PaisReceptor) {
        this.PaisReceptor = PaisReceptor;
    }

    public String getTipoFrase() {
        return TipoFrase;
    }

    public void setTipoFrase(String TipoFrase) {
        this.TipoFrase = TipoFrase;
    }

    public String getCodigoEscenario() {
        return CodigoEscenario;
    }

    public void setCodigoEscenario(String CodigoEscenario) {
        this.CodigoEscenario = CodigoEscenario;
    }

    public String getNumeroLineaProducto() {
        return NumeroLineaProducto;
    }

    public void setNumeroLineaProducto(String NumeroLineaProducto) {
        this.NumeroLineaProducto = NumeroLineaProducto;
    }

    public String getBienOServicioProducto() {
        return BienOServicioProducto;
    }

    public void setBienOServicioProducto(String BienOServicioProducto) {
        this.BienOServicioProducto = BienOServicioProducto;
    }

    public String getCantidadProducto() {
        return CantidadProducto;
    }

    public void setCantidadProducto(String CantidadProducto) {
        this.CantidadProducto = CantidadProducto;
    }

    public String getUnidadMedidaProducto() {
        return UnidadMedidaProducto;
    }

    public void setUnidadMedidaProducto(String UnidadMedidaProducto) {
        this.UnidadMedidaProducto = UnidadMedidaProducto;
    }

    public String getDescripcionProducto() {
        return DescripcionProducto;
    }

    public void setDescripcionProducto(String DescripcionProducto) {
        this.DescripcionProducto = DescripcionProducto;
    }

    public String getPrecioUnitarioProducto() {
        return PrecioUnitarioProducto;
    }

    public void setPrecioUnitarioProducto(String PrecioUnitarioProducto) {
        this.PrecioUnitarioProducto = PrecioUnitarioProducto;
    }

    public String getPrecioProducto() {
        return PrecioProducto;
    }

    public void setPrecioProducto(String PrecioProducto) {
        this.PrecioProducto = PrecioProducto;
    }

    public String getDescuentoProducto() {
        return DescuentoProducto;
    }

    public void setDescuentoProducto(String DescuentoProducto) {
        this.DescuentoProducto = DescuentoProducto;
    }

    public String getTotalProducto() {
        return TotalProducto;
    }

    public void setTotalProducto(String TotalProducto) {
        this.TotalProducto = TotalProducto;
    }

    public String getNombreCortoImpuesto() {
        return NombreCortoImpuesto;
    }

    public void setNombreCortoImpuesto(String NombreCortoImpuesto) {
        this.NombreCortoImpuesto = NombreCortoImpuesto;
    }

    public String getCodigoUnidadGravableImpuesto() {
        return CodigoUnidadGravableImpuesto;
    }

    public void setCodigoUnidadGravableImpuesto(String CodigoUnidadGravableImpuesto) {
        this.CodigoUnidadGravableImpuesto = CodigoUnidadGravableImpuesto;
    }

    public String getMontoGravableImpuesto() {
        return MontoGravableImpuesto;
    }

    public void setMontoGravableImpuesto(String MontoGravableImpuesto) {
        this.MontoGravableImpuesto = MontoGravableImpuesto;
    }

    public String getMontoImpuesto() {
        return MontoImpuesto;
    }

    public void setMontoImpuesto(String MontoImpuesto) {
        this.MontoImpuesto = MontoImpuesto;
    }

    public String getNombreCortoTotales() {
        return NombreCortoTotales;
    }

    public void setNombreCortoTotales(String NombreCortoTotales) {
        this.NombreCortoTotales = NombreCortoTotales;
    }

    public String getTotalMontoImpuestoTotales() {
        return TotalMontoImpuestoTotales;
    }

    public void setTotalMontoImpuestoTotales(String TotalMontoImpuestoTotales) {
        this.TotalMontoImpuestoTotales = TotalMontoImpuestoTotales;
    }

    public String getGranTotalTotales() {
        return GranTotalTotales;
    }

    public void setGranTotalTotales(String GranTotalTotales) {
        this.GranTotalTotales = GranTotalTotales;
    }

    public String getNumeroAutorizacionDocumentoOrigen() {
        return NumeroAutorizacionDocumentoOrigen;
    }

    public void setNumeroAutorizacionDocumentoOrigen(String NumeroAutorizacionDocumentoOrigen) {
        this.NumeroAutorizacionDocumentoOrigen = NumeroAutorizacionDocumentoOrigen;
    }

    public String getFechaEmisionDocumentoOrigen() {
        return FechaEmisionDocumentoOrigen;
    }

    public void setFechaEmisionDocumentoOrigen(String FechaEmisionDocumentoOrigen) {
        this.FechaEmisionDocumentoOrigen = FechaEmisionDocumentoOrigen;
    }

    public String getMotivoAjuste() {
        return MotivoAjuste;
    }

    public void setMotivoAjuste(String MotivoAjuste) {
        this.MotivoAjuste = MotivoAjuste;
    }

    public String getNumeroDocumentoOrigen() {
        return NumeroDocumentoOrigen;
    }

    public void setNumeroDocumentoOrigen(String NumeroDocumentoOrigen) {
        this.NumeroDocumentoOrigen = NumeroDocumentoOrigen;
    }

    public String getSerieDocumentoOrigen() {
        return SerieDocumentoOrigen;
    }

    public void setSerieDocumentoOrigen(String SerieDocumentoOrigen) {
        this.SerieDocumentoOrigen = SerieDocumentoOrigen;
    }

    public int getTotalDeProductos() {
        return TotalDeProductos;
    }

    public void setTotalDeProductos(int TotalDeProductos) {
        this.TotalDeProductos = TotalDeProductos;
    }
}
