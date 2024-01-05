package Excel;
import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.CargarDatosRutas;
import static CLASES_GLOBALES.METODOS_GLOBALES.executorService;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modelo.login;
import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.rs;
import Tablas.RenderTablas;
import Vista.AVISOS;
import Vista.POS.POS;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel extends ConexionesSQL {

    int item;
    public void reporte() throws URISyntaxException {
        
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                
            
        JFileChooser chooser = new JFileChooser();
        /*
* De acuerdo con JFileChooser para seleccionar el cuadro de carpeta emergente 1. Sólo seleccione el directorio JFileChooser.DIRECTORIES_ONLY
 * 2. Seleccione solo el archivo JFileChooser.FILES_ONLY
 * 3. Tanto los directorios como los archivos pueden ser JFileChooser.FILES_AND_DIRECTORIES
             */
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setCurrentDirectory(new File(METODOS_GLOBALES.CargarDatosRutasAlBuscarImagen()));
            String selectPath = null;
            // Guardar el directorio seleccionado chooser.showSaveDialog (parent);
            Component parent = null;
            int returnVal = chooser.showSaveDialog(parent);

        // Obtener el objeto de archivo seleccionado JFileChooser.APPROVE_OPTION
        // Si el directorio guardado es consistente con el objeto de archivo seleccionado, devolverá 0 si tiene éxito
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            AVISOS AV = new AVISOS("CARGANDO DATOS", "POR FAVOR, ESPERE");
                                AV.setVisible(true);

            // obtener ruta
            selectPath = chooser.getSelectedFile().getPath();
        
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");

        try {

            String directorio2 = new File (METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA).getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3.5);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Times New Roman");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 16);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"CÓDIGO", "NOMBRE", "STOCK", "COSTO", " COSTO LETRAS", "PRECIO 1", "PRECIO 2", "PRECIO 3", "ID CATEGORÍA", "ID PROVEEDOR", 
                "INGRESO", "ID USUARIO REGISTRO", 
            "MODIFICACIÓN", "ID USUARIO MODIFICÓ", "RUTA IMAGEN", "UBICACION", "DESCRIPCIÓN"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Times New Roman");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            
            
            String fileName = "REPORTE_DE_PRODUCTOS_"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"_"+METODOS_GLOBALES.Fecha()+"_";
           
            
             
             
      
            File file = new File(selectPath+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
            AV.dispose();
        }
        
        }
        
        }
        });
 
    }
    
    public void reporteACertificador() throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");
 
        try {
            String[] cabecera = new String[]{"ID", "Descripcion", "Precio", "Tipo", "Unidad"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre, Publico FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_TIENDA";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
    public void reporteSinBD(JTable tabla) throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS");
        ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
        try {
            
            //String imag= "C:\\Imágenes de Sistema de Ferretería El Amigo\\FerreteríaPequeño.png";
            String rutaimagen= System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"imagenes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+"FerreteríaPequeño"+".png";
            String directorio2 = new File ("/Sistema Punto de Venta YG/FerreteríaPequeño.png").getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.5, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Categoría", "Proveedor", "Fecha de Ingreso", "Registrado Por", 
            "Última vez modificado", "Modifcado Por", "Ruta", "Ubicacion", "Descripción"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            String Codigo="";
            for (int i = 0; i < tabla.getRowCount(); i++) {
                Codigo = tabla.getValueAt(i, 0).toString();
            
            
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos WHERE CodigoBarras=?");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            }
            
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_Parametros";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException e) {
        } catch (SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
    
    public void reporteCategorias() throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");
 
        try {
            
            //String imag= "C:\\Imágenes de Sistema de Ferretería El Amigo\\FerreteríaPequeño.png";
            String rutaimagen= System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"imagenes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+"FerreteríaPequeño"+".png";
            String directorio2 = new File ("/Sistema Punto de Venta YG/FerreteríaPequeño.png").getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.5, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Categoría", "Proveedor", "Fecha de Ingreso", "Registrado Por", 
            "Última vez modificado", "Modifcado Por", "Ruta", "Ubicacion", "Descripción"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_TIENDA";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
    public void reporteProveedores() throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");
 
        try {
            
            //String imag= "C:\\Imágenes de Sistema de Ferretería El Amigo\\FerreteríaPequeño.png";
            String rutaimagen= System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"imagenes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+"FerreteríaPequeño"+".png";
            String directorio2 = new File ("/Sistema Punto de Venta YG/FerreteríaPequeño.png").getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.5, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Categoría", "Proveedor", "Fecha de Ingreso", "Registrado Por", 
            "Última vez modificado", "Modifcado Por", "Ruta", "Ubicacion", "Descripción"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_TIENDA";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
    public void Usuarios() throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");
 
        try {
            
            //String imag= "C:\\Imágenes de Sistema de Ferretería El Amigo\\FerreteríaPequeño.png";
            String rutaimagen= System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"imagenes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+"FerreteríaPequeño"+".png";
            String directorio2 = new File ("/Sistema Punto de Venta YG/FerreteríaPequeño.png").getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.5, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Categoría", "Proveedor", "Fecha de Ingreso", "Registrado Por", 
            "Última vez modificado", "Modifcado Por", "Ruta", "Ubicacion", "Descripción"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_TIENDA";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
    public void reporteVentas() throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");
 
        try {
            
            //String imag= "C:\\Imágenes de Sistema de Ferretería El Amigo\\FerreteríaPequeño.png";
            String rutaimagen= System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"imagenes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+"FerreteríaPequeño"+".png";
            String directorio2 = new File ("/Sistema Punto de Venta YG/FerreteríaPequeño.png").getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.5, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Categoría", "Proveedor", "Fecha de Ingreso", "Registrado Por", 
            "Última vez modificado", "Modifcado Por", "Ruta", "Ubicacion", "Descripción"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_TIENDA";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
    public void reporteDetalleVenta() throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");
 
        try {
            
            //String imag= "C:\\Imágenes de Sistema de Ferretería El Amigo\\FerreteríaPequeño.png";
            String rutaimagen= System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"imagenes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+"FerreteríaPequeño"+".png";
            String directorio2 = new File ("/Sistema Punto de Venta YG/FerreteríaPequeño.png").getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.5, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Categoría", "Proveedor", "Fecha de Ingreso", "Registrado Por", 
            "Última vez modificado", "Modifcado Por", "Ruta", "Ubicacion", "Descripción"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_TIENDA";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
    public void reporteNumeroFactura() throws URISyntaxException {
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN TIENDA");
 
        try {
            
            //String imag= "C:\\Imágenes de Sistema de Ferretería El Amigo\\FerreteríaPequeño.png";
            String rutaimagen= System.getProperty("user.dir") + System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"imagenes"+System.getProperty("file.separator")+
            System.getProperty("file.separator")+"FerreteríaPequeño"+".png";
            String directorio2 = new File ("/Sistema Punto de Venta YG/FerreteríaPequeño.png").getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1.5, 3);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Arial");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 14);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("PRODUCTOS EN INVENTARIO");
 
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Categoría", "Proveedor", "Fecha de Ingreso", "Registrado Por", 
            "Última vez modificado", "Modifcado Por", "Ruta", "Ubicacion", "Descripción"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
 
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 5;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria, Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion FROM productos");
            
            rs = ps.executeQuery();
 
            int numCol = rs.getMetaData().getColumnCount();
 
            while (rs.next()) {
                Row filaDatos = sheet.createRow(numFilaDatos);
 
                for (int a = 0; a < numCol; a++) {
 
                    Cell CeldaDatos = filaDatos.createCell(a);
                    CeldaDatos.setCellStyle(datosEstilo);
                    CeldaDatos.setCellValue(rs.getString(a + 1));
                }
 
 
                numFilaDatos++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            sheet.autoSizeColumn(13);
            sheet.autoSizeColumn(14);
            sheet.autoSizeColumn(15);
            sheet.autoSizeColumn(16);
            sheet.autoSizeColumn(17);
            sheet.autoSizeColumn(18);
            sheet.autoSizeColumn(19);
            
            sheet.setZoom(90);
            String fileName = "Reporte_de_Productos_TIENDA";
            File directorio = new File("/"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"/Reporte de Productos");
            

             if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                JOptionPane.showMessageDialog(null,"DIRECTORIO CREADO\n"+directorio.toString());
            } else {
               JOptionPane.showMessageDialog(null,"Error al crear directorio");
            }
             }
      
            File file = new File(directorio+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
            JOptionPane.showMessageDialog(null, "¡REPORTE GENERADO EXITOSAMENTE!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
 
    }
    
        public void exportarExcelProductosNoBD(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("ARCHIVO EXCEL", "xlsx");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("GUARDAR ARCHIVO");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xlsx");
            try {
                File archivoXLSX = new File(ruta);
                if (archivoXLSX.exists()) {
                    archivoXLSX.delete();
                }
                archivoXLSX.createNewFile();
                Workbook libro = new XSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLSX);
                Sheet hoja = libro.createSheet("PRODUCTOS SIN INGRESO A BD");
                hoja.setDisplayGridlines(true);

                CellStyle headerStyle = libro.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                headerStyle.setBorderBottom(BorderStyle.THIN);
                headerStyle.setBorderLeft(BorderStyle.THIN);
                headerStyle.setBorderRight(BorderStyle.THIN);
                headerStyle.setBorderBottom(BorderStyle.THIN);

                Font font = libro.createFont();
                font.setFontName("Arial");
                font.setBold(true);
                font.setColor(IndexedColors.WHITE.getIndex());
                font.setFontHeightInPoints((short) 12);
                headerStyle.setFont(font);

                CellStyle datosEstilo = libro.createCellStyle();
                datosEstilo.setBorderBottom(BorderStyle.THIN);
                datosEstilo.setBorderLeft(BorderStyle.THIN);
                datosEstilo.setBorderRight(BorderStyle.THIN);
                datosEstilo.setBorderBottom(BorderStyle.THIN);

                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    //t.getColumnCount()
                    for (int c = 0; c < 15; c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(t.getColumnName(c));
                        }
                    }
                }
                int filaInicio = 1;
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    //t.getColumnCount()
                    for (int c = 0; c < 15; c++) {
                        Cell celda = fila.createCell(c);
                        if (t.getValueAt(f, c) instanceof String) {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        } else if (t.getValueAt(f, c) instanceof String) {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        } else {
                            celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                        }
                    }
                }
                FileOutputStream fileOut = new FileOutputStream(archivoXLSX);
                libro.write(fileOut);
                fileOut.close();
                //archivo.close();
                Desktop.getDesktop().open(archivoXLSX);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public void exportarExcelProductosNoBDAutomatico(JTable t, String Usuario) throws IOException {
        Date fech = new Date();
        login l = new login();
        String strDateFormat = "dd-MM-YYYY";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha = objSDF.format(fech);
        String ruta = fecha + "_" + Usuario + "xlsx";
        try {
            File archivoXLSX = new File(ruta);
            if (archivoXLSX.exists()) {
                archivoXLSX.delete();
            }
            archivoXLSX.createNewFile();
            Workbook libro = new XSSFWorkbook();
            FileOutputStream archivo = new FileOutputStream(archivoXLSX);
            Sheet hoja = libro.createSheet("PRODUCTOS SIN INGRESO A BD");
            hoja.setDisplayGridlines(true);

            CellStyle headerStyle = libro.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = libro.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            CellStyle datosEstilo = libro.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(f);
                //t.getColumnCount()
                for (int c = 0; c < 15; c++) {
                    Cell celda = fila.createCell(c);
                    if (f == 0) {
                        celda.setCellValue(t.getColumnName(c));
                    }
                }
            }
            int filaInicio = 1;
            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(filaInicio);
                filaInicio++;
                //t.getColumnCount()
                for (int c = 0; c < 15; c++) {
                    Cell celda = fila.createCell(c);
                    if (t.getValueAt(f, c) instanceof Double) {
                        celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                    } else if (t.getValueAt(f, c) instanceof Float) {
                        celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));
                    } else {
                        celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                    }
                }
            }
            FileOutputStream fileOut = new FileOutputStream(archivoXLSX);
            libro.write(fileOut);
            fileOut.close();
            //archivo.close();
            Desktop.getDesktop().open(archivoXLSX);
        } catch (IOException | NumberFormatException e) {
            throw e;
        }

    }

    public void exportarExcel_JTABLE(JTable t, String Usuario) {

        try {
            LocalDate date = LocalDate.now();
            int month = date.getMonthValue();
            int year = date.getYear();
            int day = date.getDayOfMonth();
            METODOS_GLOBALES.CREAR_CARPETA("C://DATOS PERDIDOS EN POS YG");

            METODOS_GLOBALES.CREAR_CARPETA("C://DATOS PERDIDOS EN POS YG//" + month + "-" + year);
            String ruta = "C://DATOS PERDIDOS EN POS YG//" + month + "-" + year + "//" + Usuario + "-" + day +"-"+ String.valueOf(new Random().nextLong()).substring(15) + ".xlsx";
            File archivoXLSX = new File(ruta);
            if (archivoXLSX.exists()) {
                archivoXLSX.delete();
            }
            archivoXLSX.createNewFile();
            Workbook libro = new XSSFWorkbook();
            FileOutputStream archivo = new FileOutputStream(archivoXLSX);
            Sheet hoja = libro.createSheet("" + Usuario);
            hoja.setDisplayGridlines(true);

            CellStyle headerStyle = libro.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);

            Font font = libro.createFont();
            font.setFontName("Arial");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);

            CellStyle datosEstilo = libro.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);

            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(f);
                //t.getColumnCount()
                for (int c = 0; c < t.getColumnCount()-1; c++) {
                    Cell celda = fila.createCell(c);
                    if (f == 0) {
                        celda.setCellValue(t.getColumnName(c));
                    }
                }
            }
            int filaInicio = 1;
            for (int f = 0; f < t.getRowCount(); f++) {
                Row fila = hoja.createRow(filaInicio);
                filaInicio++;
                //t.getColumnCount()
                for (int c = 0; c < t.getColumnCount()-1; c++) {
                    Cell celda = fila.createCell(c);
                    celda.setCellValue(String.valueOf(t.getValueAt(f, c)));

                }
            }
            FileOutputStream fileOut = new FileOutputStream(archivoXLSX);
            libro.write(fileOut);
            fileOut.close();
            //archivo.close();
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("¡ÉXITO!", "¡DATOS GUARDADOS!\nRUTA: " + ruta, DesktopNotify.SUCCESS, 14000L);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }

    }

    public void ImportarExcel_JTABLE(POS P_OS) {
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) P_OS.TablaVentas.getModel();
        try {
            LocalDate date = LocalDate.now();
            int month = date.getMonthValue();
            int year = date.getYear();
            int day = date.getDayOfMonth();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("EXCEL XLSX(*.XLSX;)", "XLSX");
            JFileChooser archivo_File = new JFileChooser();
            archivo_File.addChoosableFileFilter(filtro);
            archivo_File.setFileFilter(filtro);
            archivo_File.setCurrentDirectory(new File("C://DATOS PERDIDOS EN POS YG//" + month + "-" + year));
            archivo_File.setDialogTitle("SELECCIONE UN ARCHIVO EXCEL");
            int ventana = archivo_File.showOpenDialog(null);
            if (ventana == JFileChooser.APPROVE_OPTION) {
                File file = archivo_File.getSelectedFile();
                FileInputStream archivo = new FileInputStream(file);
                XSSFWorkbook libroLectura = new XSSFWorkbook(archivo);
                XSSFSheet hojaLectura = libroLectura.getSheetAt(0);

                int NumeroFilas = hojaLectura.getLastRowNum();
                
                for (int i = 1; i <= NumeroFilas; i++) {
                    JButton btn1 = new JButton("ELIMINAR");

                    Image retValue = Toolkit.getDefaultToolkit().
                            getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
                    ImageIcon ro = new ImageIcon(retValue);

                    btn1.setIcon(ro);
                    Row fila = hojaLectura.getRow(i);

                    P_OS.TablaVentas.setDefaultRenderer(Object.class, new RenderTablas());

                    item = item + 1;
                    
                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(fila.getCell(0).toString());
                    lista.add(fila.getCell(1).toString());
                    lista.add(fila.getCell(2).toString());
                    lista.add(fila.getCell(3).toString());
                    lista.add(fila.getCell(4).toString());
                    lista.add(fila.getCell(5).toString());
                    lista.add(fila.getCell(6).toString());
                    lista.add(fila.getCell(7).toString());
                    lista.add(fila.getCell(8).toString());
                    lista.add(btn1);
                    Object[] O = new Object[10];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    O[5] = lista.get(6);
                    O[6] = lista.get(7);
                    O[7] = lista.get(8);
                    O[8] = lista.get(9);
                    O[9] = lista.get(10);
                    modelo.addRow(O);
                }
                P_OS.TablaVentas.setModel(modelo);
                ELIMINAR_EXCEL(archivo_File.getSelectedFile());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Importar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Importar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void ELIMINAR_EXCEL(File archivo){
        // Verificar si el archivo existe
        if (archivo.exists()) {
            // Eliminar archivo
            archivo.delete();
            System.out.println("El archivo ha sido eliminado");
        }
    }
    
    
    public void EXPORTAR_EXCEL_JTABLE(JTable Tabla, int Columna, int Columna2, String Traslado) throws URISyntaxException {
        
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                
            
        JFileChooser chooser = new JFileChooser();
        /*
* De acuerdo con JFileChooser para seleccionar el cuadro de carpeta emergente 1. Sólo seleccione el directorio JFileChooser.DIRECTORIES_ONLY
 * 2. Seleccione solo el archivo JFileChooser.FILES_ONLY
 * 3. Tanto los directorios como los archivos pueden ser JFileChooser.FILES_AND_DIRECTORIES
             */
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.setCurrentDirectory(new File(METODOS_GLOBALES.CargarDatosRutasAlBuscarImagen()));
            String selectPath = null;
            // Guardar el directorio seleccionado chooser.showSaveDialog (parent);
            Component parent = null;
            int returnVal = chooser.showSaveDialog(parent);
            

        // Obtener el objeto de archivo seleccionado JFileChooser.APPROVE_OPTION
        // Si el directorio guardado es consistente con el objeto de archivo seleccionado, devolverá 0 si tiene éxito
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            AVISOS AV = new AVISOS("CARGANDO DATOS", "POR FAVOR, ESPERE");
                                AV.setVisible(true);
         // obtener ruta
            selectPath = chooser.getSelectedFile().getPath();
            // Copiar imágenes a una nueva carpeta
            String nuevaCarpeta = selectPath + "\\TRASLADO "+Traslado;
            File nuevaCarpetaFile = new File(nuevaCarpeta);
            if (!nuevaCarpetaFile.exists()) {
                nuevaCarpetaFile.mkdir();
            }
            
        
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("TRASLADO DE PRODUCTOS DE "+PARAMETROS_EMPRESA.NOMBRE_EMPRESA);

        try {

            String directorio2 = new File (METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA).getAbsolutePath();
            InputStream is = new FileInputStream(directorio2);
            byte[] bytes = IOUtils.toByteArray(is);
            int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            is.close();
 
            CreationHelper help = book.getCreationHelper();
            Drawing draw = sheet.createDrawingPatriarch();
 
            ClientAnchor anchor = help.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = draw.createPicture(anchor, imgIndex);
            pict.resize(1, 3.5);
 
            CellStyle tituloEstilo = book.createCellStyle();
            tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
            tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
            Font fuenteTitulo = book.createFont();
            fuenteTitulo.setFontName("Times New Roman");
            fuenteTitulo.setBold(true);
            fuenteTitulo.setFontHeightInPoints((short) 16);
            tituloEstilo.setFont(fuenteTitulo);
 
            Row filaTitulo = sheet.createRow(1);
            Cell celdaTitulo = filaTitulo.createCell(1);
            celdaTitulo.setCellStyle(tituloEstilo);
            celdaTitulo.setCellValue("TRASLADO DE PRODUCTOS DE "+PARAMETROS_EMPRESA.NOMBRE_EMPRESA +" | FECHA: "+ METODOS_GLOBALES.Fecha() + " | POR: "+PARAMETROS_USUARIOS.ID_USUARIO+"-"+ PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
            sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 12));
 
            String[] cabecera = new String[]{"CÓDIGO", "NOMBRE", "CANTIDAD", "COSTO", "PRECIO 1", "PRECIO 2", "PRECIO 3", "NOMBRE PRECIO 1", "NOMBRE PRECIO 2",
                "NOMBRE PRECIO 3", "RUTA IMAGEN", "DESCRIPCIÓN"};
 
            CellStyle headerStyle = book.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
 
            Font font = book.createFont();
            font.setFontName("Times New Roman");
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            font.setFontHeightInPoints((short) 12);
            headerStyle.setFont(font);
 
            Row filaEncabezados = sheet.createRow(4);
            
            for (int i = 0; i < cabecera.length; i++) {
                Cell celdaEnzabezado = filaEncabezados.createCell(i);
                celdaEnzabezado.setCellStyle(headerStyle);
                celdaEnzabezado.setCellValue(cabecera[i]);
            }
 
            ps = null;
            rs = null;
            cn = Unionsis2.getConnection();
 
            int numFilaDatos = 1;
 
            CellStyle datosEstilo = book.createCellStyle();
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            datosEstilo.setBorderLeft(BorderStyle.THIN);
            datosEstilo.setBorderRight(BorderStyle.THIN);
            datosEstilo.setBorderBottom(BorderStyle.THIN);
            int filaInicio = 5;
            for (int i = 0; i < Tabla.getRowCount(); i++) {
                
            
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre, Costo,Precio1, Precio2, Precio3, Publico, PrecioEs, PrecioRe, ruta, Descripcion FROM productos where idProductos=?");
            ps.setInt(1, Integer.parseInt(Tabla.getValueAt(i, Columna2).toString()));
            rs = ps.executeQuery();
            
            if (rs.next()) {
                
                    Row fila = sheet.createRow(filaInicio);
                
                    Cell CeldaCodigoBarras = fila.createCell(0);
                    CeldaCodigoBarras.setCellStyle(datosEstilo);
                    CeldaCodigoBarras.setCellValue(rs.getString("CodigoBarras"));
                    
                    Cell CeldaNombre = fila.createCell(1);
                    CeldaNombre.setCellStyle(datosEstilo);
                    CeldaNombre.setCellValue(rs.getString("Nombre"));
                    
                    Cell CeldaCantidad = fila.createCell(2);
                    CeldaCantidad.setCellStyle(datosEstilo);
                    CeldaCantidad.setCellValue(Tabla.getValueAt(i, Columna).toString());
                    
                    Cell CeldaCosto = fila.createCell(3);
                    CeldaCosto.setCellStyle(datosEstilo);
                    CeldaCosto.setCellValue(rs.getString("Costo"));
                    
                    Cell CeldaPublico = fila.createCell(4);
                    CeldaPublico.setCellStyle(datosEstilo);
                    CeldaPublico.setCellValue(rs.getString("Publico"));
                    
                    Cell CeldaPrecioEs = fila.createCell(5);
                    CeldaPrecioEs.setCellStyle(datosEstilo);
                    CeldaPrecioEs.setCellValue(rs.getString("PrecioEs"));
                    
                    Cell CeldaPrecioRe = fila.createCell(6);
                    CeldaPrecioRe.setCellStyle(datosEstilo);
                    CeldaPrecioRe.setCellValue(rs.getString("PrecioRe"));
                    
                    Cell CeldaPrecio1 = fila.createCell(7);
                    CeldaPrecio1.setCellStyle(datosEstilo);
                    CeldaPrecio1.setCellValue(rs.getString("Precio1"));
                    
                    Cell CeldaPrecio2 = fila.createCell(8);
                    CeldaPrecio2.setCellStyle(datosEstilo);
                    CeldaPrecio2.setCellValue(rs.getString("Precio2"));
                    
                    Cell CeldaPrecio3 = fila.createCell(9);
                    CeldaPrecio3.setCellStyle(datosEstilo);
                    CeldaPrecio3.setCellValue(rs.getString("Precio3"));
                    
                    Cell Celdaruta = fila.createCell(10);
                    Celdaruta.setCellStyle(datosEstilo);
                    Celdaruta.setCellValue(rs.getString("ruta"));
                    
                    Cell CeldaDescripcion = fila.createCell(11);
                    CeldaDescripcion.setCellStyle(datosEstilo);
                    CeldaDescripcion.setCellValue(rs.getString("Descripcion"));
                    
                    executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if(rs.getString("ruta").equals("FerreteríaPequeño.png")){
                        
                    }else{
                     // Construir la ruta de destino para la nueva carpeta
                    String nuevaRutaImagen = nuevaCarpeta +"\\"+ rs.getString("ruta");
                    
                    // Copiar el archivo
                    try {
                        Files.copy(Paths.get(CargarDatosRutas(1) + "\\" + rs.getString("ruta")), Paths.get(nuevaRutaImagen), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                    DesktopNotify.showDesktopMessage("ERROR", "NO SE PUDO OBTENER LAS IMAGENES\n"+ex.toString(), DesktopNotify.ERROR, 14000L);
                        ex.printStackTrace();  // Manejar la excepción apropiadamente en tu aplicación
                    }
                }
                   
                } catch (SQLException ex) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                    DesktopNotify.showDesktopMessage("ERROR", "NO SE PUDO OBTENER LAS IMAGENES\n"+ex.toString(), DesktopNotify.ERROR, 14000L);
                }
            }
        });
                

                numFilaDatos++;

            
                
            }
            filaInicio++;
            }
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
            sheet.autoSizeColumn(4);
            sheet.autoSizeColumn(5);
            sheet.autoSizeColumn(6);
            sheet.autoSizeColumn(7);
            sheet.autoSizeColumn(8);
            sheet.autoSizeColumn(9);
            sheet.autoSizeColumn(10);
            sheet.autoSizeColumn(11);
            sheet.autoSizeColumn(12);
            
            sheet.setZoom(90);
            
            
            String fileName = "TRASLADO_DE_PRODUCTOS_"+PARAMETROS_EMPRESA.NOMBRE_EMPRESA+"_"+METODOS_GLOBALES.Fecha()+"_";
           
            
             
             
      
            File file = new File(nuevaCarpeta+"\\"+fileName + ".xlsx");
            FileOutputStream fileOut = new FileOutputStream(file);
            book.write(fileOut);
            fileOut.close();
            
            Desktop.getDesktop().open(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error, "+ex);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error dos, "+ex);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
            AV.dispose();
        }
        
        }
        
        }
        });
 
    }
    
}