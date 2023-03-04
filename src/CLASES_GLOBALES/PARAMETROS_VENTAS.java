/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

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

/**
 *
 * @author aldoy
 */
public class PARAMETROS_VENTAS {
    public static Boolean CheckBoxImpresionRapida, CheckImprimir, CheckBoxModoStockCero, CheckPermitirProductosPersonalizados;
    public static String NumeroInterno_VENTAS, NumeroInterno_VENTAS_FINAL, NumeroInterno_COTIZACIONES, NumeroInterno_FINAL, 
            NumeroInterno_VALES, NumeroInterno_VALES_FINAL, NumeroInterno_ADMINISTRACION, NumeroInterno_ADMINISTRACION_FINAL, NUMERO_CAJA;
    
    
    static Properties properties;
    static InputStream entrada = null;
    
    public static void CargarDatos(JCheckBox CheckIngresoAutomatico){
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
    
    public static void Recordar(JCheckBox CheckIngresoAutomatico){
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
    
    public static void CargarDatosVistaPreviaVenta(JCheckBox VistaPreviaVenta){
            
        
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
    
    public static void RecordarVistaPreviaVenta(JCheckBox VistaPreviaVenta){
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
    
    
    public static void CargarDatosImprimir(JCheckBox VistaPreviaVenta){
            
        
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
    
    public static void RecordarImprimir(JCheckBox VistaPreviaVenta){
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
    
    
    public static void CargarDatosFormatoImpresion(JComboBox Seleccion){
            
        
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
    
    public static void RecordarFormatoImpresion(JComboBox Seleccion){
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
    
    
    public static void CargarDatosTipoDocumento(JComboBox Seleccion){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG/CONFIGURACIONES\\TIPODOCUMENTO.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("tipodocumento");
            
            if(Estado.equals("0")){
            Seleccion.setSelectedIndex(0);
            }else if(Estado.equals("1")){
                Seleccion.setSelectedIndex(1);
            }else if(Estado.equals("2")){
                Seleccion.setSelectedIndex(2);
            }else if(Estado.equals("3")){
                Seleccion.setSelectedIndex(3);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public static void RecordarTipoDocumento(JComboBox Seleccion){
        String Estado;
        try {
            if(Seleccion.getSelectedIndex()== 0){
                 Estado ="0";
                 properties= new Properties();
            properties.setProperty("tipodocumento", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\TIPODOCUMENTO.properties").getAbsolutePath()), "CONFIGURACIÓN DE TIPO DE DOCUMENTO");
             }else if(Seleccion.getSelectedIndex()== 1){
                 Estado = "1";
                 properties= new Properties();
            properties.setProperty("tipodocumento", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\TIPODOCUMENTO.properties").getAbsolutePath()), "CONFIGURACIÓN DE TIPO DE DOCUMENTO");
             }else if(Seleccion.getSelectedIndex()== 2){
                 Estado = "2";
                 properties= new Properties();
            properties.setProperty("tipodocumento", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\TIPODOCUMENTO.properties").getAbsolutePath()), "CONFIGURACIÓN DE TIPO DE DOCUMENTO");
             }else if(Seleccion.getSelectedIndex()== 3 ){
                 Estado = "3";
                 properties= new Properties();
            properties.setProperty("tipodocumento", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\TIPODOCUMENTO.properties").getAbsolutePath()), "CONFIGURACIÓN DE TIPO DE DOCUMENTO");
             }
            
            
        } catch (IOException e) {
        }
    }
    
    
    public static void CargarDatosModoReinventario(JCheckBoxMenuItem ModoActivado){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOREINVENTARIO.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("modoreinventario");
            
            if(Estado.equals("true")){
            ModoActivado.setSelected(true);    
            }else{
                ModoActivado.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public static void RecordarModoReinventario(JCheckBoxMenuItem ModoActivado){
        String Estado;
        try {
            if(ModoActivado.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("modoreinventario", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOREINVENTARIO.properties").getAbsolutePath()), "CONFIGURACIÓN DE ModoReinventario");
             }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("modoreinventario", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOREINVENTARIO.properties").getAbsolutePath()), "CONFIGURACIÓN DE ModoReinventario");
             }
            
            
        } catch (IOException e) {
        }
    }
    
    public static void CargarDatosProductosPersonalizados(JCheckBoxMenuItem ModoActivado){
            
        
            try {
            properties= new Properties();
            entrada = new FileInputStream(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOPRODUCTOSPERSONALIZADOS.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("ProductosPersonalizados");
            
            if(Estado.equals("true")){
            ModoActivado.setSelected(true);    
            }else{
                ModoActivado.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
    }
    
    public static void RecordarProductosPersonalizados(JCheckBoxMenuItem ModoActivado){
        String Estado;
        try {
            if(ModoActivado.isSelected()){
                 Estado ="true";
                 properties= new Properties();
            properties.setProperty("ProductosPersonalizados", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOPRODUCTOSPERSONALIZADOS.properties").getAbsolutePath()), "CONFIGURACIÓN DE MODO PRODUCTOS PERSONALIZADOS");
             }else{
                 Estado = "false";
                 properties= new Properties();
            properties.setProperty("ProductosPersonalizados", Estado);
            properties.store(new FileWriter(new File ("C:\\Sistema Punto de Venta YG\\CONFIGURACIONES\\MODOPRODUCTOSPERSONALIZADOS.properties").getAbsolutePath()), "CONFIGURACIÓN DE MODO PRODUCTOS PERSONALIZADOS");
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
            ModoActivado.setSelected(true);    
            }else{
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
