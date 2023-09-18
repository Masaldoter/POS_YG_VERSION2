/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import CLASES_GLOBALES.METODOS_GLOBALES;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import Conexiones.conexion;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
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
        String directorio2 = new File(PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/REPORTES/rProductos.jasper").getAbsolutePath();
        HashMap<String, Object> par = new HashMap<>();
                par.put("nombreempresa", PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
                par.put("nitempresa", PARAMETROS_EMPRESA.NIT_EMPRESA);
                par.put("direccionempresa", PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
                par.put("telempresa", PARAMETROS_EMPRESA.TEL_EMPRESA);
                par.put("eslogan", PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
                par.put("politicas", PARAMETROS_EMPRESA.POLITICAS_EMPRESA);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
        try {
            JasperReport doc = null;

            File Prod = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(Prod);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, par, cn);
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
        HashMap<String, Object> par = new HashMap<>();
                par.put("nombreempresa", PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
                par.put("nitempresa", PARAMETROS_EMPRESA.NIT_EMPRESA);
                par.put("direccionempresa", PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
                par.put("telempresa", PARAMETROS_EMPRESA.TEL_EMPRESA);
                par.put("eslogan", PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
                par.put("politicas", PARAMETROS_EMPRESA.POLITICAS_EMPRESA);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
        try {
            JasperReport doc = null;
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/REPORTES/Ventas.jasper").getAbsolutePath();
            File Ven = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(Ven);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, par, cn);
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
        HashMap<String, Object> par = new HashMap<>();
                par.put("nombreempresa", PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
                par.put("nitempresa", PARAMETROS_EMPRESA.NIT_EMPRESA);
                par.put("direccionempresa", PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
                par.put("telempresa", PARAMETROS_EMPRESA.TEL_EMPRESA);
                par.put("eslogan", PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
                par.put("politicas", PARAMETROS_EMPRESA.POLITICAS_EMPRESA);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
        try {
            JasperReport doc = null;
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/REPORTES/Clientes.jasper").getAbsolutePath();
            File clie = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(clie);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, par, cn);
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
        HashMap<String, Object> par = new HashMap<>();
                par.put("nombreempresa", PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
                par.put("nitempresa", PARAMETROS_EMPRESA.NIT_EMPRESA);
                par.put("direccionempresa", PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
                par.put("telempresa", PARAMETROS_EMPRESA.TEL_EMPRESA);
                par.put("eslogan", PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
                par.put("politicas", PARAMETROS_EMPRESA.POLITICAS_EMPRESA);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
        cn = Union.getConnection();
        try {
            JasperReport doc = null;
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/REPORTES/Proveedor.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, par, cn);
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
        HashMap<String, Object> par = new HashMap<>();
                par.put("nombreempresa", PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
                par.put("nitempresa", PARAMETROS_EMPRESA.NIT_EMPRESA);
                par.put("direccionempresa", PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
                par.put("telempresa", PARAMETROS_EMPRESA.TEL_EMPRESA);
                par.put("eslogan", PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
                par.put("politicas", PARAMETROS_EMPRESA.POLITICAS_EMPRESA);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
        cn = Union.getConnection();
        try {
            JasperReport doc = null;           
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/REPORTES/Usuarios.jasper").getAbsolutePath();
            File usu = new File(directorio2);
            doc = (JasperReport) JRLoader.loadObject(usu);
            JasperPrint Ejecutar = JasperFillManager.fillReport(doc, par, cn);
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
