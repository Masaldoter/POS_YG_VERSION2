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
import com.groupdocs.conversion.options.load.WordProcessingLoadOptions;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    public static ExecutorService executorService = Executors.newCachedThreadPool();
    public static Timer timer = new Timer();
    

    public static void PintarImagen_BOTON(JButton boton, String ruta){
        boton.removeAll();
        boton.setHorizontalAlignment(SwingConstants.CENTER);
        METODOS_GLOBALES.imagenI = new ImageIcon(ruta);
        METODOS_GLOBALES.icono = new ImageIcon(METODOS_GLOBALES.imagenI.getImage().getScaledInstance(
                boton.getWidth()+20, 
                boton.getHeight()+20, 
                Image.SCALE_DEFAULT));
        boton.setIcon(METODOS_GLOBALES.icono);
        boton.repaint();
        
        
        // Cargar la imagen original
       /* BufferedImage imagenOriginal;
        try {
            imagenOriginal = ImageIO.read(new File(ruta));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Obtener el tamaño cuadrado
        int squareSize = Math.min(imagenOriginal.getWidth(), imagenOriginal.getHeight());

        // Redimensionar la imagen para que se ajuste al cuadro sin cortarse
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH);

        // Crear una nueva imagen cuadrada con fondo blanco
        BufferedImage imagenCuadrada = new BufferedImage(squareSize, squareSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagenCuadrada.createGraphics();

        // Dibujar el fondo blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, squareSize, squareSize);

        // Calcular las coordenadas para centrar la imagen redimensionada en la imagen cuadrada
        int x = (squareSize - imagenRedimensionada.getWidth(null)) / 2;
        int y = (squareSize - imagenRedimensionada.getHeight(null)) / 2;

        // Dibujar la imagen redimensionada en la imagen cuadrada
        g2d.drawImage(imagenRedimensionada, x, y, null);
        g2d.dispose();

        // Crear un ImageIcon con la imagen cuadrada
        ImageIcon imagenIcono = new ImageIcon(imagenCuadrada);
        
        lbl.setIcon(imagenIcono);
        lbl.repaint();*/

    }
    
     public static void PintarImagen_BOTON_ICON(JButton boton, ImageIcon ruta) {
        boton.removeAll();
        METODOS_GLOBALES.imagenI = new ImageIcon();
        METODOS_GLOBALES.icono = new ImageIcon(ruta.getImage().getScaledInstance(
                boton.getWidth(), 
                boton.getHeight(), 
                Image.SCALE_DEFAULT));
        boton.setIcon(METODOS_GLOBALES.icono);
        boton.repaint();
    }
    
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
        
        
        // Cargar la imagen original
       /* BufferedImage imagenOriginal;
        try {
            imagenOriginal = ImageIO.read(new File(ruta));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Obtener el tamaño cuadrado
        int squareSize = Math.min(imagenOriginal.getWidth(), imagenOriginal.getHeight());

        // Redimensionar la imagen para que se ajuste al cuadro sin cortarse
        Image imagenRedimensionada = imagenOriginal.getScaledInstance(squareSize, squareSize, Image.SCALE_SMOOTH);

        // Crear una nueva imagen cuadrada con fondo blanco
        BufferedImage imagenCuadrada = new BufferedImage(squareSize, squareSize, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = imagenCuadrada.createGraphics();

        // Dibujar el fondo blanco
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, squareSize, squareSize);

        // Calcular las coordenadas para centrar la imagen redimensionada en la imagen cuadrada
        int x = (squareSize - imagenRedimensionada.getWidth(null)) / 2;
        int y = (squareSize - imagenRedimensionada.getHeight(null)) / 2;

        // Dibujar la imagen redimensionada en la imagen cuadrada
        g2d.drawImage(imagenRedimensionada, x, y, null);
        g2d.dispose();

        // Crear un ImageIcon con la imagen cuadrada
        ImageIcon imagenIcono = new ImageIcon(imagenCuadrada);
        
        lbl.setIcon(imagenIcono);
        lbl.repaint();*/

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

    public static Boolean VALIDAR_ANULAR_DOCUMENTO(String Fecha) {
        Boolean Estado = false;
        // Obtener la fecha del label (ejemplo)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaLabel = LocalDate.parse(Fecha, formatter);

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Calcular la fecha límite (20 días antes)
        LocalDate fechaLimite = fechaActual.minusDays(20);

        // Comparar las fechas
        if (fechaLabel.isBefore(fechaLimite)) {
            // La fecha del label es demasiado antigua
            Estado = false;
        } else {
            // La fecha del label es actual o posterior
            Estado = true;
        }
        return Estado;
    }

    /*public static void Activar_Inactividad(java.awt.Frame parent, boolean modal){
        timer.cancel();
        timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                // Aquí puedes colocar el código para enviar la notificación
                INACTIVIDAD I= new INACTIVIDAD(parent, modal);
                I.setVisible(true);
                System.out.println("Se ha detectado inactividad durante 20 segundos. Enviar notificación.");
            }
        };

        timer.schedule(tarea, 10000);
    }
    
    public static void Desactivar_Inactividad(){
        timer.cancel();
    }*/
    public static void GuardarBoton_Fecha(Boolean Validar) {
        Properties propertie3= new Properties();
        InputStream entrada = null;
        try {
            propertie3.setProperty("fecha_venta", String.valueOf(Validar));
            
            propertie3.store(new FileWriter(new File("/Sistema Punto de Venta YG/CONFIGURACIONES/CAMBIAR_FECHA.properties").getAbsolutePath()), "CONFIGURACIÓN DE INGRESO DE DATOS CON FECHA DIFRENTE");
        } catch (IOException ex) {
        }
    }
    
    public static String Cargar_Boton_Fecha() {
        String Ruta = "";
        try {
            Properties propertie3 = new Properties();
            InputStream entrada = null;
            entrada = new FileInputStream(new File("/Sistema Punto de Venta YG/CONFIGURACIONES/CAMBIAR_FECHA.properties").getAbsolutePath());
            propertie3.load(entrada);
            Ruta = propertie3.getProperty("fecha_venta");
            if(Ruta.equals("")  || Ruta.equals(null)){
                GuardarBoton_Fecha(false);
                Ruta = "false";
            }else{
                Ruta = propertie3.getProperty("fecha_venta");
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return Ruta;
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
            }else if(TipoRuta==3){
               Ruta=  propertie3.getProperty("ruta_imagenes_usuarios");
            }else {
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
        Locale.setDefault(new Locale("es"));
        WordProcessingLoadOptions loadOptions = new WordProcessingLoadOptions();
        loadOptions.setPassword("12345");
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
             
        executorService.execute(new Runnable() {
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
        });
        }
    
    
    public static void INSERTAR_TEXTO_TABLA(JTable Tabla, int Columna, int Alto, int Ancho) {
             
        executorService.execute(new Runnable() {
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
        });
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
    
    public static String CONVERTIR_FECHA_DATE_STRING(Date Fecha){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); // Formato deseado
        String fechaString = formato.format(Fecha); // Convertir Date a String
        
        
        return fechaString;
    }
    public static String CONVERTIR_FECHA(String Fecha){
        LocalDate fecha = LocalDate.parse(Fecha);

        // Crear un objeto DateTimeFormatter para el nuevo formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Convertir la fecha al nuevo formato
        String fechaFormateada = fecha.format(formatter);
        
        return fechaFormateada;
    }
    
    public static String CONVERTIR_HORA(String Hora){
        LocalTime hora = LocalTime.parse(Hora);

        // Crear un objeto DateTimeFormatter para el nuevo formato
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");

        // Convertir la hora al nuevo formato
        String horaFormateada = hora.format(formatter);
        
        return horaFormateada;
    }
    
    public static void HORA_FECHA(JLabel ETIQUETA){
        executorService.execute(new Runnable() {
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
        });
    }
    

    public static void CREAR_CARPETA(String RUTA) {

        // Crear objeto File
        File carpeta = new File(RUTA);

        // Comprobar si la carpeta ya existe
        if (!carpeta.exists()) {

            // Crear la carpeta usando mkdir() o mkdirs()
            if (carpeta.mkdir()) {
            } else {
                System.out.println("Error al crear la carpeta.");
            }

            /*
            // Si quieres crear varias carpetas en una sola llamada, utiliza mkdirs():
            if (carpeta.mkdirs()) {
                System.out.println("Carpetas creadas correctamente.");
            } else {
                System.out.println("Error al crear las carpetas.");
            }
             */
        }
    }
}
