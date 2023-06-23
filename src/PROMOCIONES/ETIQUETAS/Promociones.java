/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PROMOCIONES.ETIQUETAS;

import CodigosDeBarras.*;
import Clases_Reportes.Codigos;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Masaldoter
 */
public class Promociones {
    
    public void LogoYNombre(String Detalle, String NombreEmpresa){
        Codigos codigos;
        codigos= new Codigos(Detalle, "", "", "");
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("nom", Detalle);
                par.put("empresa", NombreEmpresa);
            lista.add(codigos);   
                try {
            JasperReport reporte = null;
           
            String directorio2 = new File ("C:\\Sistema Punto de Venta YG\\ModeloDeImpresiones\\Etiquetas\\Promociones.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            vistaReporte.setZoomRatio(4);
            Boolean P=  JasperPrintManager.printReport(imprimirReporte, true);
            if(P==true){
              vistaReporte.dispose();
            }
        } catch (JRException ex) {
            System.out.println("Error, " + ex);

        }

    }
    
    
    public void Ofertas(){
        Codigos codigos;
        codigos= new Codigos("", "", "", "");
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("ruta", "C:\\Sistema Punto de Venta YG\\oferta.png");
            lista.add(codigos);   
                try {
            JasperReport reporte = null;
            String directorio2 = new File ("C:\\Sistema Punto de Venta YG\\ModeloDeImpresiones\\Etiquetas\\Ofertas.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            vistaReporte.setZoomRatio(1);
            Boolean P=  JasperPrintManager.printReport(imprimirReporte, true);
            if(P==true){
              vistaReporte.dispose();
            }
        } catch (JRException ex) {
            System.out.println("Error, " + ex);

        }

    }
    
    public void Logo1(boolean Visible){
        Codigos codigos;
        codigos= new Codigos("","","", "");
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("ruta", "C:\\Sistema Punto de Venta YG\\LogoConNombre.png");
            lista.add(codigos);   
                try {
            JasperReport reporte = null;
            String directorio2 = new File ("C:\\Sistema Punto de Venta YG\\ModeloDeImpresiones\\Etiquetas\\Logo1.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(Visible);
            vistaReporte.setZoomRatio(1);
            Boolean P=  JasperPrintManager.printReport(imprimirReporte, Visible);
            if(P==true){
              vistaReporte.dispose();
            }
        } catch (JRException ex) {
            System.out.println("Error, " + ex);

        }

    }
    
    
    public void Logo2(ParametrosCodigosDeBarras parametros, String NombreEmpresa){
        Codigos codigos;
        codigos= new Codigos(parametros.getNombreproducto(), parametros.getPrecioPublico(), parametros.getCodigoDeBarrasOId(), parametros.getCostoLetras());
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("cod", codigos.getId());
                par.put("nom", codigos.getNombreproducto());
                par.put("pre", codigos.getPublico());
                par.put("cost", codigos.getCostoLetras());
                par.put("empresa", NombreEmpresa);
            lista.add(codigos);   
                try {
            JasperReport reporte = null;
            String directorio2 = new File ("C:\\Sistema Punto de Venta YG\\CodigoBarrasPequeño.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            vistaReporte.setTitle("ETIQUETA 1X1");
            //vistaReporte.setExtendedState(10);
            vistaReporte.setZoomRatio(7);
            Boolean P=  JasperPrintManager.printReport(imprimirReporte, true);
            
            if(P==true){
              vistaReporte.dispose();
            }
        } catch (JRException ex) {
            System.out.println("Error, " + ex);

        }

    }
}
