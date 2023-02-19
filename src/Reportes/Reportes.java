/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Conexiones.conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Reportes{
 
    conexion Union =conexion.getInstancia();
    Connection cn = null;

    public void ReporteProductos() {
        cn = Union.getConnection();
        String directorio2 = new File("/Sistema Punto de Venta YG/rProductos.jasper").getAbsolutePath();
        try {
            JasperReport doc = null;

            File Prod = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(Prod);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, null, cn);
            JasperViewer vistaReporte = new JasperViewer(Ejecutar, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error, " + e);
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
            }
        }

    }
    
    public void ReporteVentas() {
        cn = Union.getConnection();
        try {
            JasperReport doc = null;
            String directorio2 = new File ("/Sistema Punto de Venta YG/Ventas.jasper").getAbsolutePath();
            File Ven = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(Ven);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, null, cn);
            JasperViewer vistaReporte = new JasperViewer(Ejecutar, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
        } catch (JRException e) {
            System.out.println("Error, " + e);
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
            }
        }

    }
    
    public void ReporteClientes() {
        cn = Union.getConnection();
        try {
            JasperReport doc = null;
            String directorio2 = new File ("/Sistema Punto de Venta YG/Clientes.jasper").getAbsolutePath();
            File clie = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(clie);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, null, cn);
            JasperViewer vistaReporte = new JasperViewer(Ejecutar, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public void ReporteProveedores() {
        cn = Union.getConnection();
        try {
            JasperReport doc = null;
            String directorio2 = new File ("/Sistema Punto de Venta YG/Proveedor.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, null, cn);
            JasperViewer vistaReporte = new JasperViewer(Ejecutar, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public void ReporteUsuarios() {
        cn = Union.getConnection();
        try {
            JasperReport doc = null;           
            String directorio2 = new File ("/Sistema Punto de Venta YG/Usuarios.jasper").getAbsolutePath();
            File usu = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(usu);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, null, cn);
            JasperViewer vistaReporte = new JasperViewer(Ejecutar, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
            }
        }
    }
}
