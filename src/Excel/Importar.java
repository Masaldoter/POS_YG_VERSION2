    package Excel;

import Conexiones.conexion;
import Modelo.Productos;
import Conexiones.ConexionesSQL;
import Tablas.RenderTablas;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Importar extends ConexionesSQL {

    Date fech = new Date();
    String strDateFormat = "YYYY-MM-dd";
    SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
    String fecha = objSDF.format(fech);
    int item;

    public void CargarExcel(String Ruta) {
        FileInputStream archivoFoto;
        conexion Unionsis2 = conexion.getInstancia();
        Connection cn = null;
        try {
            cn = Unionsis2.getConnection();
            ps = null;
            FileInputStream archivo = new FileInputStream(new File(Ruta));
            XSSFWorkbook libroLectura = new XSSFWorkbook(archivo);
            XSSFSheet hojaLectura = libroLectura.getSheetAt(0);

            int NumeroFilas = hojaLectura.getLastRowNum();

            for (int i = 5; i < NumeroFilas; i++) {
                Row fila = hojaLectura.getRow(i);

                ps = cn.prepareStatement("INSERT INTO productos (CodigoBarras, Nombre, Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria,"
                        + "Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion) VALUES(?, ?, ?"
                        + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, fila.getCell(0).getStringCellValue());
                ps.setString(2, fila.getCell(1).getStringCellValue());
                ps.setFloat(3, Float.parseFloat(fila.getCell(2).getStringCellValue()));
                /*Costo*/ ps.setFloat(4, Float.parseFloat(fila.getCell(3).getStringCellValue()));
                /*Letras*/ ps.setString(5, fila.getCell(4).getStringCellValue());
                /*Publico*/ ps.setFloat(6, Float.parseFloat(fila.getCell(5).getStringCellValue()));
                /*Esecial*/ ps.setFloat(7, Float.parseFloat(fila.getCell(6).getStringCellValue()));
                /*Reventa*/ ps.setFloat(8, Float.parseFloat(fila.getCell(7).getStringCellValue()));
                /*Categoria*/ ps.setInt(9, Integer.parseInt(fila.getCell(8).getStringCellValue()));
                /*Proveedor*/ ps.setInt(10, Integer.parseInt(fila.getCell(9).getStringCellValue()));
                /*FechaIngreso*///ps.setString(11, fila.getCell(10).getStringCellValue());

                ps.setString(11, fecha);
                /*UsuarioIngreso*/
                ps.setInt(12, Integer.parseInt(fila.getCell(11).getStringCellValue()));
                /*FechaModifico*///ps.setString(13, fila.getCell(12).getStringCellValue());
                ps.setString(13, fecha);

                /*UsuarioModifico*/
                ps.setInt(14, Integer.parseInt(fila.getCell(13).getStringCellValue()));
                /*ruta*/
                ps.setString(15, fila.getCell(14).getStringCellValue());
                /*Ubicacion*/
                ps.setString(16, fila.getCell(15).getStringCellValue());
                /*Descripcion*/
                ps.setString(17, fila.getCell(16).getStringCellValue());
                /*archivoFoto = new FileInputStream("C:\\Sistema Punto de Venta YG\\FerreteríaPequeño.png");
                ps.setBinaryStream(18, archivoFoto);*/
               /*ps.setString(18, fila.getCell(16).getStringCellValue());
                ps.setString(19, fila.getCell(16).getStringCellValue());
                ps.setString(20, fila.getCell(16).getStringCellValue());*/
                boolean Resultado = ps.execute();
                if (Resultado == true) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("¡ÉXITO!", "¡SE REGISTRARON CORRECTAMENTE LOS PRODUCTOS!", DesktopNotify.SUCCESS, 4000L);
                }

            }

        } catch (IOException | SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("¡ERROR!", "¡HUBO UN FALLO EN EL REGISTRO :(!\n" + e, DesktopNotify.ERROR, 4000L);

        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }

    }

    public void CargarExcel2(String Ruta) {
        FileInputStream archivoFoto;
        conexion Unionsis2 = conexion.getInstancia();
        Connection cn = null;
        try {
            cn = Unionsis2.getConnection();
            ps = null;
            FileInputStream archivo = new FileInputStream(new File(Ruta));
            XSSFWorkbook libroLectura = new XSSFWorkbook(archivo);
            XSSFSheet hojaLectura = libroLectura.getSheetAt(0);

            int NumeroFilas = hojaLectura.getLastRowNum();

            for (int i = 5; i < NumeroFilas; i++) {
                Row fila = hojaLectura.getRow(i);

                ps = cn.prepareStatement("INSERT INTO productos (CodigoBarras, Nombre, Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Categoria,"
                        + "Proveedores, fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, ruta, Ubicacion, Descripcion, imagen) VALUES(?, ?, ?, ?, ?, ?"
                        + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, fila.getCell(0).getStringCellValue());
                ps.setString(2, fila.getCell(1).getStringCellValue());
                ps.setFloat(3, Float.parseFloat(fila.getCell(2).getStringCellValue()));
                /*Costo*/ ps.setFloat(4, Float.parseFloat(fila.getCell(3).getStringCellValue()));
                /*Letras*/ ps.setString(5, "");
                /*Publico*/ ps.setFloat(6, Float.parseFloat(fila.getCell(4).getStringCellValue()));
                /*Esecial*/ ps.setFloat(7, 0);
                /*Reventa*/ ps.setFloat(8, 0);
                /*Categoria*/ ps.setInt(9, 2);
                /*Proveedor*/ ps.setInt(10, 16);
                /*FechaIngreso*/
                ps.setString(11, fecha);
                /*UsuarioIngreso*/
                ps.setInt(12, 1);
                /*FechaModifico*/
                ps.setString(13, fecha);
                /*UsuarioModifico*/
                ps.setInt(14, 1);
                /*ruta*/
                ps.setString(15, "C:\\Sistema Punto de Venta YG\\FerreteríaPequeño.png");
                /*Ubicacion*/
                ps.setString(16, "Tienda");
                /*Descripcion*/
                ps.setString(17, "");
                archivoFoto = new FileInputStream("C:\\Sistema Punto de Venta YG\\FerreteríaPequeño.png");
                ps.setBinaryStream(18, archivoFoto);
                boolean Resultado = ps.execute();
                if (Resultado == true) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("¡ÉXITO!", "¡SE REGISTRARON CORRECTAMENTE LOS PRODUCTOS!", DesktopNotify.SUCCESS, 4000L);
                }

            }

        } catch (IOException | SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("¡ERROR!", "¡HUBO UN FALLO EN EL REGISTRO :(!\n" + e, DesktopNotify.ERROR, 4000L);

        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }

    }

    public void CargarExcelBodega(String Ruta) {
        FileInputStream archivoFoto;
        try {
            cn = Unionsis2.getConnection();
            ps = null;
            FileInputStream archivo = new FileInputStream(new File(Ruta));
            XSSFWorkbook libroLectura = new XSSFWorkbook(archivo);
            XSSFSheet hojaLectura = libroLectura.getSheetAt(0);

            int NumeroFilas = hojaLectura.getLastRowNum();

            for (int i = 5; i < NumeroFilas; i++) {
                Row fila = hojaLectura.getRow(i);

                ps = cn.prepareStatement("INSERT INTO productos (CodigoBarras, Nombre, Cantidad, Costo, Publico, fechaingreso, UsuarioIngreso, ruta, Ubicacion, PrecioRe, PrecioEs, imagen, UsuarioModifico, Categoria, Proveedores) VALUES(?, ?, ?, ?, ?, ?"
                        + ", ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, fila.getCell(0).getStringCellValue());
                ps.setString(2, fila.getCell(1).getStringCellValue());
                ps.setFloat(3, Float.parseFloat(fila.getCell(2).getStringCellValue()));
                /*Costo*/ ps.setFloat(4, Float.parseFloat(fila.getCell(3).getStringCellValue()));
                /*Publico*/ ps.setFloat(5, Float.parseFloat(fila.getCell(4).getStringCellValue()));
                ps.setString(6, fecha);
                ps.setInt(7, 1);
                ps.setString(8, "C:\\Sistema Punto de Venta YG\\FerreteríaPequeño.png");
                ps.setString(9, "Tienda");
                ps.setFloat(10, 0);
                ps.setFloat(11, 0);
                archivoFoto = new FileInputStream("C:\\Sistema Punto de Venta YG\\FerreteríaPequeño.png");
                ps.setBinaryStream(12, archivoFoto);
                ps.setInt(13, 1);
                ps.setInt(14, 2);
                ps.setInt(15, 16);
                boolean Resultado = ps.execute();
                if (Resultado == true) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("¡ÉXITO!", "¡SE REGISTRARON CORRECTAMENTE LOS PRODUCTOS!", DesktopNotify.SUCCESS, 4000L);
                }

            }

        } catch (IOException | SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("¡ERROR!", "¡HUBO UN FALLO EN EL REGISTRO :(!\n" + e, DesktopNotify.ERROR, 4000L);

        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }

    }

    public void CargarExcelProductosSinBD(File Ruta, JTable tabla) {

        System.out.println(Ruta);
        try {
            cn = Unionsis2.getConnection();
            ps = null;
            FileInputStream archivo = new FileInputStream(Ruta);
            XSSFWorkbook libroLectura = new XSSFWorkbook(archivo);
            XSSFSheet hojaLectura = libroLectura.getSheetAt(0);

            int NumeroFilas = hojaLectura.getLastRowNum();

            for (int i = 1; i <= NumeroFilas; i++) {
                Row fila = hojaLectura.getRow(i);

                tabla.setDefaultRenderer(Object.class, new RenderTablas());

                JButton btn1 = new JButton("ELIMINAR");

                Image retValue = Toolkit.getDefaultToolkit().
                        getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
                ImageIcon ro = new ImageIcon(retValue);

                btn1.setIcon(ro);
                DefaultTableModel modelo = new DefaultTableModel();

                for (int k = 1; k < tabla.getRowCount(); k++) {
                    if (tabla.getValueAt(k, 0).equals(fila.getCell(0).toString())) {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                        DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL CÓDIGO DE BARRAS: "+fila.getCell(0).toString()+" YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);
                        return;
                    }

                    if (tabla.getValueAt(k, 1).equals(fila.getCell(1).toString())) {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                        DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL NOMBRE DEL PRODUCTO: "+fila.getCell(1).toString()+" YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);
                        return;
                    }
                    
                }
            
                item = item + 1;
                modelo = (DefaultTableModel) tabla.getModel();
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
                lista.add(fila.getCell(9).toString());
                lista.add(fila.getCell(10).toString());
                lista.add(fila.getCell(11).toString());
                lista.add(fila.getCell(12).toString());
                lista.add(fila.getCell(13).toString());
                lista.add(fila.getCell(14).toString());
                lista.add(btn1);
                //lista.add(ubicacion2);

                Object[] O = new Object[16];
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
                O[10] = lista.get(11);
                O[11] = lista.get(12);
                O[12] = lista.get(13);
                O[13] = lista.get(14);
                O[14] = lista.get(15);
                O[15] = lista.get(16);
                modelo.addRow(O);
                tabla.setModel(modelo);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Importar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Importar.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
