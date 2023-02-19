package Vista.POS;

public class ObtenerTotales {
 
    
    public PagosTotales ObtenerTotalesEnGeneral(PagosTotales PT){

        System.out.println(PT.getTipoMoneda());
        PT.setTipoMoneda(PT.getTipoMoneda());
        
        return PT;
    }
}
