
package CodigosDeBarras;

import CLASES_GLOBALES.METODOS_GLOBALES;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import Clases_Reportes.Codigos;
import IMPRESORAS.OBTENERIMPRESORAS;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CodigosDeBarras {
    OBTENERIMPRESORAS Impresora= new OBTENERIMPRESORAS();
    JasperPrint imprimirReporte;
    
    public void CodigoBarrasMediano(ParametrosCodigosDeBarras parametros, String NombreEmpresa){
        Codigos codigos;
        codigos= new Codigos(parametros.getNombreproducto(), parametros.getPrecioPublico(), parametros.getCodigoDeBarrasOId(), parametros.getCostoLetras());
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<>();
                par.put("cod", codigos.getId());
                par.put("nom", codigos.getNombreproducto());
                par.put("pre", codigos.getPublico());
                par.put("cost", codigos.getCostoLetras());
                par.put("empresa", NombreEmpresa);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
            lista.add(codigos);   
                try {
            JasperReport reporte;
           
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/ModeloDeImpresiones/Etiquetas/CodigoBarrasMediano.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            
            
            Impresora.CargarDatosImpresoras();
            String ImpresoraSeleccionada = Impresora.getIMPRESORA_ETIQUETAS();
            
            estableceImpresoraPredeterminada(ImpresoraSeleccionada);
            
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            vistaReporte.setZoomRatio(4);
            new Thread(() -> {
                try {
                    Boolean P = JasperPrintManager.printReport(imprimirReporte, true);
                    if (P == true) {
                        vistaReporte.dispose();
                    }
                } catch (JRException ex) {
                    Logger.getLogger(CodigosDeBarras.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }).start();
            
        } catch (JRException ex) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR AL GENERAR CODIGO DE BARRAS MEDIANO", "NO SE PUDO REALIZAR LA ACCIÓN\n"+ ex, DesktopNotify.SUCCESS, 9000L);
        }

    }
    
    
    public void CodigoBarrasGrande(ParametrosCodigosDeBarras parametros, String NombreEmpresa, String NombreEmpresaLargo){
        Codigos codigos;
        codigos= new Codigos(parametros.getNombreproducto(), parametros.getPrecioPublico(), parametros.getCodigoDeBarrasOId(), parametros.getCostoLetras());
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<>();
                par.put("cod", codigos.getId());
                par.put("nom", codigos.getNombreproducto());
                par.put("pre", codigos.getPublico());
                par.put("cost", codigos.getCostoLetras());
                par.put("empresa", NombreEmpresa);
                par.put("empresalargo", NombreEmpresaLargo);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
            lista.add(codigos);   
                try {
            JasperReport reporte = null;
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/ModeloDeImpresiones/Etiquetas/CodigoBarrasGrande.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            
            Impresora.CargarDatosImpresoras();
            String ImpresoraSeleccionada = Impresora.getIMPRESORA_ETIQUETAS();
            
            estableceImpresoraPredeterminada(ImpresoraSeleccionada);
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            vistaReporte.setZoomRatio(1);
            Boolean P=  JasperPrintManager.printReport(imprimirReporte, true);
            if(P==true){
              vistaReporte.dispose();
            }
            
        } catch (JRException ex) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR AL GENERAR CODIGO DE BARRAS GRANDE", "NO SE PUDO REALIZAR LA ACCIÓN\n"+ ex, DesktopNotify.SUCCESS, 9000L);
        }

    }
    
    public void CodigoBarras3x1(ParametrosCodigosDeBarras parametros, String NombreEmpresa){
        Codigos codigos;
        codigos= new Codigos(parametros.getNombreproducto(), parametros.getPrecioPublico(), parametros.getCodigoDeBarrasOId(), parametros.getCostoLetras());
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("cod", codigos.getId());
                par.put("nom", codigos.getNombreproducto());
                par.put("pre", codigos.getPublico());
                par.put("cost", codigos.getCostoLetras());
                par.put("empresa", NombreEmpresa);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
            lista.add(codigos);   
                try {
            JasperReport reporte = null;
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/ModeloDeImpresiones/Etiquetas/CodigoBarras3x1.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            
            
            Impresora.CargarDatosImpresoras();
            String ImpresoraSeleccionada = Impresora.getIMPRESORA_ETIQUETAS();
            estableceImpresoraPredeterminada(ImpresoraSeleccionada);
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            vistaReporte.setZoomRatio(1);
            new Thread(() -> {
                try {
                    Boolean P = JasperPrintManager.printReport(imprimirReporte, true);
                    if (P == true) {
                        vistaReporte.dispose();
                    }
                } catch (JRException ex) {
                    Logger.getLogger(CodigosDeBarras.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }).start();
        } catch (JRException ex) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR AL GENERAR CODIGO DE BARRAS 3X1", "NO SE PUDO REALIZAR LA ACCIÓN\n"+ ex, DesktopNotify.SUCCESS, 9000L);
        }

    }
    
    
    public void CodigoBarrasPequeño(ParametrosCodigosDeBarras parametros, String NombreEmpresa){
        Codigos codigos;
        codigos= new Codigos(parametros.getNombreproducto(), parametros.getPrecioPublico(), parametros.getCodigoDeBarrasOId(), parametros.getCostoLetras());
        List<Codigos> lista = new ArrayList<>();
        
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("cod", codigos.getId());
                par.put("nom", codigos.getNombreproducto());
                par.put("pre", codigos.getPublico());
                par.put("cost", codigos.getCostoLetras());
                par.put("empresa", NombreEmpresa);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
            lista.add(codigos);   
                try {
            JasperReport reporte = null;
            String directorio2 = new File (PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/ModeloDeImpresiones/Etiquetas/CodigoBarrasPequeño.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            
            Impresora.CargarDatosImpresoras();
            String ImpresoraSeleccionada = Impresora.getIMPRESORA_ETIQUETAS();
            
            estableceImpresoraPredeterminada(ImpresoraSeleccionada);
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
                    vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    vistaReporte.setVisible(true);
                    vistaReporte.setTitle("ETIQUETA 1X1");
                    //vistaReporte.setExtendedState(10);
                    vistaReporte.setZoomRatio(7);
                    new Thread(() -> {
                try {
                    Boolean P = JasperPrintManager.printReport(imprimirReporte, true);
                    if (P == true) {
                        vistaReporte.dispose();
                    }
                } catch (JRException ex) {
                    Logger.getLogger(CodigosDeBarras.class.getName()).log(Level.SEVERE, null, ex);
                }
                    }).start();

                } catch (JRException ex) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR AL GENERAR CODIGO DE BARRAS PEQUEÑO", "NO SE PUDO REALIZAR LA ACCIÓN\n"+ ex, DesktopNotify.SUCCESS, 9000L);
        }

    }
    
    private void estableceImpresoraPredeterminada(String printerName) {
        String cmdLine = String.format("RUNDLL32 PRINTUI.DLL,PrintUIEntry /y /n \"%s\"", printerName);
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmdLine);
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = new String();
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e) {
            }
            if (line == null) {
                break;
            }
        }
    }
}
