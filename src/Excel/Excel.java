package Excel;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Conexiones.conexion;
import Modelo.Productos;
import Modelo.login;
import Conexiones.ConexionesSQL;
import Vista.Principal;
import java.awt.Image;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class Excel extends ConexionesSQL{
            
    public static void reporte() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void reporteACertificador() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void reporteSinBD(JTable tabla) throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void reporteBodega() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
        Workbook book = new XSSFWorkbook();
        Sheet sheet = book.createSheet("INVENTARIO DE PRODUCTOS EN BODEGA");
 
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
 
            String[] cabecera = new String[]{"Codigo", "Nombre", "Stock", "Precio Costo", " Costo en Letras", "Precio Público,", "Precio Especial", "Precio Reventa", "Fecha de Ingreso", "Última vez modificado", "Ruta", "Ubicación", "Decripción "};
 
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
 
            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre,  Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, fechaingreso, UsuarioIngreso, fechamodificacion, ruta, Ubicacion, Descripcion FROM productos");
            
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
            String fileName = "Reporte_de_Productos_BODEGA";
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    
    public static void reporteCategorias() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void reporteProveedores() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void Usuarios() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void reporteVentas() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void reporteDetalleVenta() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
    
    public static void reporteNumeroFactura() throws URISyntaxException {
        
        Principal p= new Principal();
        String NombreEmpresa = PARAMETROS_EMPRESA.NOMBRE_EMPRESA;
 
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
            File directorio = new File("/"+NombreEmpresa+"/Reporte de Productos");
            

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
                            celda.setCellValue(String.valueOf( t.getValueAt(f, c)));
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
    login l= new login();
    String strDateFormat = "dd-MM-YYYY";
    SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
    String fecha = objSDF.format(fech );
            String ruta = fecha+"_"+Usuario+"xlsx";
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
}