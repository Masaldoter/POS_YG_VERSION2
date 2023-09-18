/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Configuraciones;

import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;

/**
 *
 * @author MASALDOTER_GT
 */
public class Productos_Config {
    static Properties properties;
    static InputStream entrada = null;
    public void CargarDatos(JCheckBoxMenuItem CheckIngresoAutomatico){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/CONFIGURACIONES/CERRAR_VENTANA_PRODUCTOS.properties").getAbsolutePath());
            properties.load(entrada);
            CheckIngresoAutomatico.setSelected(Boolean.parseBoolean(properties.getProperty("ventana")));    
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }
    }

    public void Recordar(Boolean CheckIngresoAutomatico) {
        try {
            properties = new Properties();
            properties.setProperty("ventana", String.valueOf(CheckIngresoAutomatico));
            properties.store(new FileWriter(new File(PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ + "/CONFIGURACIONES/CERRAR_VENTANA_PRODUCTOS.properties").getAbsolutePath()), "CONFIGURACIÓN DE VENTAS");
        } catch (IOException e) {
        }
    }
}
