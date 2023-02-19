
package IMPRESORAS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OBTENERIMPRESORAS {
    private String IMPRESORA_TICKET, IMPRESORA_HOJACARTA, IMPRESORA_ETIQUETAS;
    Properties properties= new Properties();
    InputStream entrada = null;
    public OBTENERIMPRESORAS(){
        
    }
    
    public void CargarDatosImpresoras(){
            
        
            try {
            
            entrada = new FileInputStream(new File ("\\Sistema Punto de Venta YG\\CONFIGURACIONES\\IMPRESORAS.properties").getAbsolutePath());
            properties.load(entrada);
            IMPRESORA_HOJACARTA= properties.getProperty("hojascarta");
            IMPRESORA_TICKET = properties.getProperty("tickets");
            IMPRESORA_ETIQUETAS = properties.getProperty("etiquetas");
            
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    

    public OBTENERIMPRESORAS(String IMPRESORA_TICKET, String IMPRESORA_HOJACARTA, String IMPRESORA_ETIQUETAS) {
        this.IMPRESORA_TICKET = IMPRESORA_TICKET;
        this.IMPRESORA_HOJACARTA = IMPRESORA_HOJACARTA;
        this.IMPRESORA_ETIQUETAS = IMPRESORA_ETIQUETAS;
    }

    public String getIMPRESORA_TICKET() {
        return IMPRESORA_TICKET;
    }

    public String getIMPRESORA_HOJACARTA() {
        return IMPRESORA_HOJACARTA;
    }

    public String getIMPRESORA_ETIQUETAS() {
        return IMPRESORA_ETIQUETAS;
    }
}
