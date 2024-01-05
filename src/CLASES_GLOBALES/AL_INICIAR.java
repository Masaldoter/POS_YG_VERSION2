/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.util.Locale;

/**
 *
 * @author MASALDOTER_GT
 */
public class AL_INICIAR {
    Locale local;
    public void CARGAR_CONFIGURACIONES(){
        Locale.setDefault(Locale.US);

        // Imprimir la localización actual
        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("CONFIGURACION", "LA REGIÓN ACTUAL ES: " + Locale.getDefault(), DesktopNotify.INFORMATION, 14000L);
         

    }
    
    public static void OBTENER_REGION(){
        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("CONFIGURACION", "LA REGIÓN ACTUAL ES: " + Locale.getDefault(), DesktopNotify.INFORMATION, 14000L);
    }
}
