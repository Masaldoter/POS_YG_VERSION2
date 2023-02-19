
package Vista.POS;

public class PagosTotales {
   private String FormaDePago;
   private String TotalEfectivoPagado, TotalTarjetaPagada, TotalDepositoTrasnferenciaPagada, TotalChequePagado;
   private int FormaDePagoEntero;
   private String NumeroDeTransacciones;
   private String TotalIvaAPagar, SubTotalAPagar, TotalAPagar, TotalPagado, TotalCambio, TipoMoneda;
   
   
   public PagosTotales(){
       
   }

    public PagosTotales(String FormaDePago, String TotalEfectivoPagado, String TotalTarjetaPagada, String TotalDepositoTrasnferenciaPagada, String TotalChequePagado, int FormaDePagoEntero, String NumeroDeTransacciones, String TotalIvaAPagar, String SubTotalAPagar, String TotalAPagar, String TotalPagado, String TotalCambio, String TipoMoneda) {
        this.FormaDePago = FormaDePago;
        this.TotalEfectivoPagado = TotalEfectivoPagado;
        this.TotalTarjetaPagada = TotalTarjetaPagada;
        this.TotalDepositoTrasnferenciaPagada = TotalDepositoTrasnferenciaPagada;
        this.TotalChequePagado = TotalChequePagado;
        this.FormaDePagoEntero = FormaDePagoEntero;
        this.NumeroDeTransacciones = NumeroDeTransacciones;
        this.TotalIvaAPagar = TotalIvaAPagar;
        this.SubTotalAPagar = SubTotalAPagar;
        this.TotalAPagar = TotalAPagar;
        this.TotalPagado = TotalPagado;
        this.TotalCambio = TotalCambio;
        this.TipoMoneda = TipoMoneda;
    }

   


    public String getFormaDePago() {
        return FormaDePago;
    }

    public void setFormaDePago(String FormaDePago) {
        this.FormaDePago = FormaDePago;
    }

    public String getTotalEfectivoPagado() {
        return TotalEfectivoPagado;
    }

    public void setTotalEfectivoPagado(String TotalEfectivoPagado) {
        this.TotalEfectivoPagado = TotalEfectivoPagado;
    }

    public String getTotalTarjetaPagada() {
        return TotalTarjetaPagada;
    }

    public void setTotalTarjetaPagada(String TotalTarjetaPagada) {
        this.TotalTarjetaPagada = TotalTarjetaPagada;
    }

    public String getTotalDepositoTrasnferenciaPagada() {
        return TotalDepositoTrasnferenciaPagada;
    }

    public void setTotalDepositoTrasnferenciaPagada(String TotalDepositoTrasnferenciaPagada) {
        this.TotalDepositoTrasnferenciaPagada = TotalDepositoTrasnferenciaPagada;
    }

    public String getTotalChequePagado() {
        return TotalChequePagado;
    }

    public void setTotalChequePagado(String TotalChequePagado) {
        this.TotalChequePagado = TotalChequePagado;
    }

    public int getFormaDePagoEntero() {
        return FormaDePagoEntero;
    }

    public void setFormaDePagoEntero(int FormaDePagoEntero) {
        this.FormaDePagoEntero = FormaDePagoEntero;
    }

    public String getNumeroDeTransacciones() {
        return NumeroDeTransacciones;
    }

    public void setNumeroDeTransacciones(String NumeroDeTransacciones) {
        this.NumeroDeTransacciones = NumeroDeTransacciones;
    }

    public String getTotalAPagar() {
        return TotalAPagar;
    }

    public void setTotalAPagar(String TotalAPagar) {
        this.TotalAPagar = TotalAPagar;
    }

    public String getTotalPagado() {
        return TotalPagado;
    }

    public void setTotalPagado(String TotalPagado) {
        this.TotalPagado = TotalPagado;
    }

    public String getTotalCambio() {
        return TotalCambio;
    }

    public void setTotalCambio(String TotalCambio) {
        this.TotalCambio = TotalCambio;
    } 

    public String getTotalIvaAPagar() {
        return TotalIvaAPagar;
    }

    public void setTotalIvaAPagar(String TotalIvaAPagar) {
        this.TotalIvaAPagar = TotalIvaAPagar;
    }

    public String getSubTotalAPagar() {
        return SubTotalAPagar;
    }

    public void setSubTotalAPagar(String SubTotalAPagar) {
        this.SubTotalAPagar = SubTotalAPagar;
    }

    public String getTipoMoneda() {
        return TipoMoneda;
    }

    public void setTipoMoneda(String TipoMoneda) {
        this.TipoMoneda = TipoMoneda;
    }
}
