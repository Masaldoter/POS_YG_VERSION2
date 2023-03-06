/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

import Controlador.loginDao;
import Modelo.DatosEmpresaGeneral;
import WebServiceDigifact.ObtenerToken;
import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.filetypes.ImageFileType;
import com.groupdocs.conversion.options.convert.ImageConvertOptions;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aldoy
 */
public class METODOS_GLOBALES {
    private static ImageIcon imagenI;
    private static Icon icono;
    
    public static void PintarImagen(JLabel lbl, String ruta){
        lbl.removeAll();
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        METODOS_GLOBALES.imagenI = new ImageIcon(ruta);
        METODOS_GLOBALES.icono = new ImageIcon(METODOS_GLOBALES.imagenI.getImage().getScaledInstance(
                lbl.getWidth(), 
                lbl.getHeight(), 
                Image.SCALE_DEFAULT));
        lbl.setIcon(METODOS_GLOBALES.icono);
        lbl.repaint();
    }
    
    public static void PintarImagen2(JLabel lbl, ImageIcon ruta){
        lbl.removeAll();
        METODOS_GLOBALES.imagenI = new ImageIcon();
        METODOS_GLOBALES.icono = new ImageIcon(ruta.getImage().getScaledInstance(
                lbl.getWidth(), 
                lbl.getHeight(), 
                Image.SCALE_DEFAULT));
        lbl.setIcon(METODOS_GLOBALES.icono);
        lbl.repaint();
    }
    
    public static String CargarDatosRutas(int TipoRuta){
        String Ruta="";
            try {
                Properties propertie3= new Properties();
    InputStream entrada = null;
            entrada = new FileInputStream(new File ("/Sistema Punto de Venta YG/CONFIGURACIONES/RUTASERVIDORIMAGENES.properties").getAbsolutePath());
            propertie3.load(entrada);
            if(TipoRuta==0){
                Ruta=propertie3.getProperty("rutasistema");
            }else{
               Ruta=  propertie3.getProperty("ruta");
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
            return Ruta;
    }
    
    public static String CargarDatosRutasAlBuscarImagen(){
        String RutaDeBusquedas="";
        Properties propertie3= new Properties();
        InputStream entrada = null;
            try {
            entrada = new FileInputStream(new File ("/Sistema Punto de Venta YG/CONFIGURACIONES/RUTASERVIDORIMAGENES.properties").getAbsolutePath());
            propertie3.load(entrada);
            RutaDeBusquedas = propertie3.getProperty("rutabusqueda");
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
            return RutaDeBusquedas;
    }
    
    public static String ObtenerRutaImagen(int Seleccion){
        String Ruta ="";
        DatosEmpresaGeneral DE= new DatosEmpresaGeneral();
        loginDao login = new loginDao();
        DE = login.VerDatosEmpresaEnLogin();    
        if(Seleccion==0){
        Ruta = CargarDatosRutas(0)+"\\" +DE.getRutaimagenlogo();
        }else{
        Ruta = CargarDatosRutas(0)+"\\" +DE.getRutaimagensistema();
        }
        return Ruta;
    }
    
    public static void CONVERSION_WEBP_IMAGE(String RUTA_ORIGEN, String RUTA_DESTINO, ImageFileType TIPO_IMAGEN) {
        
                Converter converter = new Converter(RUTA_ORIGEN);
                ImageConvertOptions options = new ImageConvertOptions();
                options.setFormat(TIPO_IMAGEN);
                converter.convert(RUTA_DESTINO, options);
                
    }
    
    public static void LIMPIAR_TABLA(JTable Tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        for (int e = 0; e < 15; e++) {
            
        
        for (int i = 0; i < Tabla.getRowCount(); i++) {
            modelo = (DefaultTableModel) Tabla.getModel();
            modelo.removeRow(i);
        }
        }
    }
    
    public void GenerarToken() {
       /* Runnable runnable = new Runnable() {
            @Override
            public void run() {*/
                try {
                    ObtenerToken OT = new ObtenerToken();
                    OT.ObtenerToken();
                } catch (Exception e) {
                }/*
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
*/
    }
    
    public static String OBTENER_EXTENSION_ARCHIVO(String RUTA){
        String Extension="";
		String fe = "";
		char ch;
		int len;
		if(RUTA==null || (len = RUTA.length())==0 || (ch = RUTA.charAt(len-1))=='/' || ch=='\\' ||ch=='.' ) {
			fe = "";
		}
		int dotInd = RUTA.lastIndexOf('.'),	sepInd = Math.max(RUTA.lastIndexOf('/'), RUTA.lastIndexOf('\\'));
		if( dotInd <= sepInd ) {
			fe = "";
		}
		else {
			fe = RUTA.substring(dotInd+1).toLowerCase();
		}
                Extension = fe;
                return Extension;
    }
    
    public static void INSERTAR_IMAGEN_TABLA(JTable Tabla, int Columna_RutaImagen, int Columna, int Alto, int Ancho) {
             
        Runnable runnable = new Runnable() {
            @Override
            public void run() {  
                    for (int i = 0; i < Tabla.getRowCount(); i++) {
                    ImageIcon imagenI;
                    Icon icono;
                    JLabel lbl = new JLabel();
                    lbl.setName("imagen");
                        lbl.setSize(Ancho, Alto);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        lbl.removeAll();
                        try {
                            imagenI = new ImageIcon(CargarDatosRutas(1) + "\\" + Tabla.getValueAt(i, Columna_RutaImagen));
                            icono = new ImageIcon(imagenI.getImage().getScaledInstance(
                                    lbl.getWidth(),
                                    lbl.getHeight(),
                                    Image.SCALE_DEFAULT));
                            lbl.setIcon(icono);
                            lbl.repaint();

                        for (int j = 0; j < Tabla.getRowCount(); j++) {
                            Tabla.setValueAt(lbl, i, Columna);
                        }
                        } catch (Exception e) {
                        }
                        
                    
                }
                    
                    
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        }
    
    
    public static void INSERTAR_TEXTO_TABLA(JTable Tabla, int Columna, int Alto, int Ancho) {
             
        Runnable runnable = new Runnable() {
            @Override
            public void run() {  
                    for (int i = 0; i < Tabla.getRowCount(); i++) {
                    ImageIcon imagenI;
                    Icon icono;
                    JLabel lbl = new JLabel();
                    lbl.setName("imagen");
                        lbl.setSize(Ancho, Alto);
                        lbl.setHorizontalAlignment(SwingConstants.CENTER);
                        lbl.removeAll();
                        try {
                            lbl.setText("<html>"+Tabla.getValueAt(i, Columna).toString()+"</html>");
                            lbl.repaint();

                        for (int j = 0; j < Tabla.getRowCount(); j++) {
                            Tabla.setValueAt(lbl, i, Columna);
                        }
                        } catch (Exception e) {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                            DesktopNotify.showDesktopMessage("ERROR EN INSERTAR_TEXTO_TABLA:\n", ""+e, DesktopNotify.ERROR, 10000L);
                        }
                        
                    
                }
                    
                    
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
        }
    
    public static String Hora(){
            Date hour = new Date();
            String strDateFormat = "HH:mm:ss";
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            String hora=objSDF.format(hour);
            
       return hora;
   }   
    
    public static String Fecha(){
        Date fech = new Date();
        String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fecha = Fechas.format(fech);
        
        return fecha;
    }
    
    public static Date Fecha_DATE(){
        Date fech = new Date();
        String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        Fechas.format(fech);
        
        return fech;
    }
    
    public static void HORA_FECHA(JLabel ETIQUETA){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String HORA_FECHA="";
                Date objDate = new Date();
                String strDateFormat = "dd-MMM-yyyy"; // El formato de fecha está especificado  
                SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
                while (true) {
                    try {
                Thread.sleep(500);
                SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss aa");
                Date date = new Date();
                ETIQUETA.setText(formatHora.format(date) +" "+objSDF.format(objDate));
                
            } catch (InterruptedException e) {
                        javax.swing.JOptionPane.showMessageDialog(null, "ERROR EN OBTENER FECHA Y HORA\n"+e);
            }
        }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }
}
