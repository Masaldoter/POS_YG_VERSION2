package Vista.POS;

public class ObtenerTotales {
 
    
    public PagosTotales ObtenerTotalesEnGeneral(PagosTotales PT){
        PT.setTipoMoneda(PT.getTipoMoneda());
        return PT;
    }
}
