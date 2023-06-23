
package Configuraciones;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;

public class Ventas {
    static Properties properties;
    static InputStream entrada = null;
    public void CargarDatos(JCheckBox CheckIngresoAutomatico){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTAS.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("ingresoautomatico");
            
            if(Estado.equals("true")){
            CheckIngresoAutomatico.setSelected(true);    
            }else{
                CheckIngresoAutomatico.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public void Recordar(JCheckBox CheckIngresoAutomatico){
        String Estado;
        try {
            if(CheckIngresoAutomatico.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("ingresoautomatico", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTAS.properties").getAbsolutePath()), "CONFIGURACIÓN DE VENTAS");
             }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("ingresoautomatico", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTAS.properties").getAbsolutePath()), "CONFIGURACIÓN DE VENTAS");
             }
            
            
        } catch (IOException e) {
        }
    }
    
    
    
    
    public void CargarDatosVistaPreviaVenta(JCheckBox VistaPreviaVenta){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTASVISTA.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("vistapreviaventa");
            
            if(Estado.equals("true")){
            VistaPreviaVenta.setSelected(true);    
            }else{
                VistaPreviaVenta.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public void RecordarVistaPreviaVenta(JCheckBox VistaPreviaVenta){
        String Estado;
        try {
            if(VistaPreviaVenta.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("vistapreviaventa", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTASVISTA.properties").getAbsolutePath()), "CONFIGURACIÓN DE VENTAS");
             }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("vistapreviaventa", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTASVISTA.properties").getAbsolutePath()), "CONFIGURACIÓN DE VENTAS");
             }
            
            
        } catch (IOException e) {
        }
    }
    
    public void CargarDatosMODO_REVENTA(JCheckBox VistaPreviaVenta){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTAS_MODO_REVENTA.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("MODO_REVENTA");
            
            if(Estado.equals("true")){
            VistaPreviaVenta.setSelected(true);    
            }else{
                VistaPreviaVenta.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public void RecordarMODO_REVENTA(JCheckBox VistaPreviaVenta){
        String Estado;
        try {
            if(VistaPreviaVenta.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("MODO_REVENTA", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTAS_MODO_REVENTA.properties").getAbsolutePath()), "CONFIGURACIÓN DE VENTAS");
             }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("MODO_REVENTA", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTAS_MODO_REVENTA.properties").getAbsolutePath()), "CONFIGURACIÓN DE VENTAS");
             }
            
            
        } catch (IOException e) {
        }
    }
    
    
    public void CargarDatosImprimir(JCheckBox VistaPreviaVenta){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\VENTASIMPRIMIR.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("imprimirautomatico");
            
            if(Estado.equals("true")){
            VistaPreviaVenta.setSelected(true);    
            }else{
                VistaPreviaVenta.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public void RecordarImprimir(JCheckBox VistaPreviaVenta){
        String Estado;
        try {
            if(VistaPreviaVenta.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("imprimirautomatico", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG/CONFIGURACIONES\\VENTASIMPRIMIR.properties").getAbsolutePath()), "CONFIGURACIÓN DE IMPRESIÓN");
             }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("imprimirautomatico", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG/CONFIGURACIONES\\VENTASIMPRIMIR.properties").getAbsolutePath()), "CONFIGURACIÓN DE IMPRESIÓN");
             }
            
            
        } catch (IOException e) {
        }
    }
    
    
    public void CargarDatosFormatoImpresion(JComboBox Seleccion){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\FORMATOIMPRESION.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("formatodeimpresion");
            
            if(Estado.equals("0")){
            Seleccion.setSelectedIndex(0);
            }else if(Estado.equals("1")){
                Seleccion.setSelectedIndex(1);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public void RecordarFormatoImpresion(JComboBox Seleccion){
        String Estado;
        try {
            if(Seleccion.getSelectedIndex()== 0){
                 Estado ="0";
                 properties= new Properties();
            properties.setProperty("formatodeimpresion", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\FORMATOIMPRESION.properties").getAbsolutePath()), "CONFIGURACIÓN DE IMPRESIÓN");
             }else if(Seleccion.getSelectedIndex()== 1){
                 Estado = "1";
                 properties= new Properties();
            properties.setProperty("formatodeimpresion", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\FORMATOIMPRESION.properties").getAbsolutePath()), "CONFIGURACIÓN DE IMPRESIÓN");
             }
            
            
        } catch (IOException e) {
        }
    }
    
    
    public void CargarDatosTipoDocumento(JComboBox Seleccion){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG/CONFIGURACIONES\\TIPODOCUMENTO.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("tipodocumento");
            Seleccion.setSelectedIndex(Integer.parseInt(Estado));
        } catch (FileNotFoundException e) {
        }catch(IOException e) {
        }
    }

    public void RecordarTipoDocumento(JComboBox Seleccion) {
        String Estado;
        try {
            Estado = String.valueOf(Seleccion.getSelectedIndex());
            properties = new Properties();
            properties.setProperty("tipodocumento", Estado);
            properties.store(new FileWriter(new File("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\TIPODOCUMENTO.properties").getAbsolutePath()), "CONFIGURACIÓN DE TIPO DE DOCUMENTO");
        } catch (IOException e) {
        }
    }
    
    
    public void CargarDatosModoReinventario(JCheckBoxMenuItem ModoActivado){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOREINVENTARIO.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("modoreinventario");
            
            if(Estado.equals("true")){
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxModoStockCero=true;
            ModoActivado.setSelected(true);    
            }else{
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxModoStockCero=false;    
                ModoActivado.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public void RecordarModoReinventario(JCheckBoxMenuItem ModoActivado){
        String Estado;
        try {
            if(ModoActivado.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("modoreinventario", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOREINVENTARIO.properties").getAbsolutePath()), "CONFIGURACIÓN DE ModoReinventario");
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxModoStockCero=true; 
            }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("modoreinventario", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOREINVENTARIO.properties").getAbsolutePath()), "CONFIGURACIÓN DE ModoReinventario");
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxModoStockCero=false;    
            }
            
            
        } catch (IOException e) {
        }
    }
    
    public void CargarDatosProductosPersonalizados(JCheckBoxMenuItem ModoActivado){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOPRODUCTOSPERSONALIZADOS.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("ProductosPersonalizados");
            
            if(Estado.equals("true")){
            ModoActivado.setSelected(true);    
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckPermitirProductosPersonalizados = true;
            }else{
                CLASES_GLOBALES.PARAMETROS_VENTAS.CheckPermitirProductosPersonalizados = false;
                ModoActivado.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public void RecordarProductosPersonalizados(JCheckBoxMenuItem ModoActivado){
        String Estado;
        try {
            if(ModoActivado.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("ProductosPersonalizados", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOPRODUCTOSPERSONALIZADOS.properties").getAbsolutePath()), "CONFIGURACIÓN DE MODO PRODUCTOS PERSONALIZADOS");
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckPermitirProductosPersonalizados = true; 
            }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("ProductosPersonalizados", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOPRODUCTOSPERSONALIZADOS.properties").getAbsolutePath()), "CONFIGURACIÓN DE MODO PRODUCTOS PERSONALIZADOS");
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckPermitirProductosPersonalizados = false;
            }
            
            
        } catch (IOException e) {
        }
    }
    
    public static void CargarDatosImpresionRapida(JCheckBoxMenuItem ModoActivado){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\IMPRESIONRAPIDA.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("ImpresionRapida");
            
            if(Estado.equals("true")){
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxImpresionRapida=true;
            ModoActivado.setSelected(true);    
            }else{
            CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxImpresionRapida =false;   
                ModoActivado.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public static void RecordarImpresionRapida(JCheckBoxMenuItem ModoActivado){
        String Estado;
        try {
            if(ModoActivado.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("ImpresionRapida", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\IMPRESIONRAPIDA.properties").getAbsolutePath()), "CONFIGURACIÓN DE IMPRESION RAPIDA");
             }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("ImpresionRapida", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\IMPRESIONRAPIDA.properties").getAbsolutePath()), "CONFIGURACIÓN DE IMPRESION RAPIDA");
             }
            
            
        } catch (IOException e) {
        }
    }
}
