/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReportesImpresion;

import Vales.DatosClienteVale;
import Vales.TablaDetallesVale;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Masaldoter
 */
public class Recibos {
    public void FacturaVale(JTable tabla){
            TablaDetallesVale em = new TablaDetallesVale();// Instaciamos la clase empleado
        DatosClienteVale datoscliente;
        datoscliente = new DatosClienteVale();
        List<TablaDetallesVale> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
            em.getCodigo();
            em.getCantidad();
            em.getNombre();
            em.getPunitario();
            em.getTotal();
            lista.add(em); //Agregamos el objeto empleado a la lista
        }
        HashMap<String, Object> par = new HashMap<String, Object>();
        par.put("CajaCliente", datoscliente.getCajaCliente());
        par.put("CajaDireccion", datoscliente.getCajaDireccion());
        par.put("CajaNit", datoscliente.getCajaNitVale());
        par.put("CajaPago", datoscliente.getPagoVale());
        par.put("CajaCambio", datoscliente.getCambioVale());
        par.put("CajaTotal", datoscliente.getCajaTotalVale());
        par.put("CajaVendedor", datoscliente.getCajaVendedorVale());
        par.put("factura", datoscliente.getNoFacturaVale());
              
        
 try {
            JasperReport reporte = null;       
            String directorio2 = new File ("C:\\Sistema Punto de Venta YG\\ReciboVale_1_1.jasper").getAbsolutePath();
            File prove = new File(directorio2);

            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
           
                   
        } catch (JRException ex) {
            System.out.println("Error  en Reporte Factura, " + ex);

        }
        }
}
