package ReportesImpresion;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.executorService;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import Clases_Reportes.DatosEmpresa;
import Clases_Reportes.Empleado;
import Clases_Reportes.Fac;
import FEL.DatosCertificador;
import FEL.DocumentoFel;
import Gmail.CargarDatosGmail;
import IMPRESORAS.OBTENERIMPRESORAS;
import Modelo.DatosEmpresaGeneral;
import ReportesProductos.ParametrosProductos;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Documentos {

    CargarDatosGmail EmpresaDao;
    DatosEmpresaGeneral EmpresaReporte;
    OBTENERIMPRESORAS Impresora = new OBTENERIMPRESORAS();

            public void Facturaa(DatosClienteYFactura datos, DatosEmpresa Empresa, DatosCertificador DatosCertificador, DocumentoFel Fel, String TipoDocumento, JTable tabla, int TipoDocumentoImpresion, String LinkFelQR) {
        
                Empleado em;// Instaciamos la clase empleado
                Fac datoscliente;
                Impresora.CargarDatosImpresoras();
                String ImpresoraSeleccionada = null;
                datoscliente = new Fac(datos.getNombre(), datos.getNit(), datos.getDireccion(), datos.getNoDocumento(), datos.getTotal(), datos.getEfectivo(), datos.getCambio(),
                        datos.getNoVale(), datos.getNoCotizacion(),
                        datos.getFormadePago(), datos.getNumeroTransaccion(), datos.getVendedor(), datos.getObservacion(), datos.getTotalLetras(), datos.getFechaEmision(), datos.getFechaVenta());

                List<Empleado> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
                for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
                    em = new Empleado(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                            tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), tabla.getValueAt(i, 6).toString());
                    lista.add(em); //Agregamos el objeto empleado a la lista
                }
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("factura", datoscliente.getFactura());
                par.put("nombre", datoscliente.getNombre());
                par.put("nit", datoscliente.getNit());
                par.put("direccion", datoscliente.getDireccion());
                par.put("labeltotal", datoscliente.getLabeltotal());
                par.put("pagocon", datoscliente.getPagocon());
                par.put("cambio", datoscliente.getCambio());
                par.put("observa", datoscliente.getObservacion());
                par.put("Vende", datoscliente.getVendedor());
                par.put("formapago", datoscliente.getFormaPago());
                par.put("totalletras", datoscliente.getTotalEnLetras());
                par.put("fechaemision", datoscliente.getFechaEmision());
                par.put("fechaventa", datoscliente.getFechaVenta());
                par.put("nombreempresa", Empresa.getNombreEmpresa());
                par.put("nitempresa", Empresa.getNit());
                par.put("direccionempresa", Empresa.getDireccion());
                par.put("telempresa", Empresa.getTel());
                par.put("eslogan", Empresa.getEslogan());
                par.put("tipodocumento", TipoDocumento);

                par.put("politicas", Empresa.getPoliticas());

                par.put("TipoDocumento", Fel.getTipoDocumento());
                par.put("NumeroDocumento", Fel.getNumeroDocumento());
                par.put("SerieDocumento", Fel.getSerieDocumento());
                par.put("NumeroAutorizacion", Fel.getNumeroAutorizacion());
                par.put("FechaAutorizacion", Fel.getFechaAutorizacion());

                par.put("NombreCertificador", DatosCertificador.getNombreCertificador());
                par.put("NitCertificador", DatosCertificador.getNitCertificador());
                

                //LINK
                //par.put("LinkQR", LinkFelQR);
                try {
                    JasperReport reporte = null;
                    String directorio2 = null;

                    if (TipoDocumentoImpresion == 0) {
                        par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
                        directorio2 = new File("\\Sistema Punto de Venta YG\\Factura_Carta.jasper").getAbsolutePath();
                        ImpresoraSeleccionada = Impresora.getIMPRESORA_HOJACARTA();
                    } else if (TipoDocumentoImpresion == 1) {
                        par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA2);
                        directorio2 = new File("C:\\Sistema Punto de Venta YG\\TicketFel.jasper").getAbsolutePath();
                        ImpresoraSeleccionada = Impresora.getIMPRESORA_TICKET();
                    }
                    File prove = new File(directorio2);
                    // File archivo = new File(ruta2);
                    reporte = (JasperReport) JRLoader.loadObject(prove);
                    JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
                    try {
                        estableceImpresoraPredeterminada(ImpresoraSeleccionada);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "" + e, "ERROR AL ESTABLECER IMPRESORA EN FACTURA", JOptionPane.ERROR);
                    }
                    try {

                        JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
                        vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        vistaReporte.setVisible(true);
                        Boolean P = JasperPrintManager.printReport(imprimirReporte, true);

                        if (P == true) {
                            vistaReporte.dispose();
                        } else if (P == false) {

                            int Seleccion = JOptionPane.showConfirmDialog(null, "¿CERRAR EL DOCUMENTO?");
                            if (Seleccion == 0) {
                                vistaReporte.dispose();
                            }
                        }
                    } catch (HeadlessException | JRException e) {
                        JOptionPane.showMessageDialog(null, "" + e, "ERROR AL IMPRIMIR FACTURA", JOptionPane.ERROR);
                    }
                } catch (JRException ex) {
                    System.out.println("Error  en Reporte Factura, " + ex);

                }

    }

    public void FacturaaCrearSinImprimir(DatosClienteYFactura datos, DatosEmpresa Empresa, DatosCertificador DatosCertificador, DocumentoFel Fel, String TipoDocumento, JTable tabla) {

        Empleado em;// Instaciamos la clase empleado
        Fac datoscliente;
        datoscliente = new Fac(datos.getNombre(), datos.getNit(), datos.getDireccion(), datos.getNoDocumento(), datos.getTotal(), datos.getEfectivo(), datos.getCambio(),
                datos.getNoVale(), datos.getNoCotizacion(),
                datos.getFormadePago(), datos.getNumeroTransaccion(), datos.getVendedor(), datos.getObservacion(), datos.getTotalLetras(), datos.getFechaEmision(), datos.getFechaVenta());

        List<Empleado> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
            em = new Empleado(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                    tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), tabla.getValueAt(i, 6).toString());
            lista.add(em); //Agregamos el objeto empleado a la lista
        }
        HashMap<String, Object> par = new HashMap<String, Object>();
        par.put("factura", datoscliente.getFactura());
        par.put("nombre", datoscliente.getNombre());
        par.put("nit", datoscliente.getNit());
        par.put("direccion", datoscliente.getDireccion());
        par.put("labeltotal", datoscliente.getLabeltotal());
        par.put("pagocon", datoscliente.getPagocon());
        par.put("cambio", datoscliente.getCambio());
        par.put("observa", datoscliente.getObservacion());
        par.put("Vende", datoscliente.getVendedor());
        par.put("formapago", datoscliente.getFormaPago());
        par.put("totalletras", datoscliente.getTotalEnLetras());
        par.put("fechaemision", datoscliente.getFechaEmision());
        par.put("fecha_venta", datoscliente.getFechaVenta());
        par.put("nombreempresa", Empresa.getNombreEmpresa());
        par.put("nitempresa", Empresa.getNit());
        par.put("direccionempresa", Empresa.getDireccion());
        par.put("telempresa", Empresa.getTel());
        par.put("eslogan", Empresa.getEslogan());
        par.put("tipodocumento", TipoDocumento);

        par.put("politicas", Empresa.getPoliticas());

        par.put("TipoDocumento", Fel.getTipoDocumento());
        par.put("NumeroDocumento", Fel.getNumeroDocumento());
        par.put("SerieDocumento", Fel.getSerieDocumento());
        par.put("NumeroAutorizacion", Fel.getNumeroAutorizacion());
        par.put("FechaAutorizacion", Fel.getFechaAutorizacion());

        par.put("NombreCertificador", DatosCertificador.getNombreCertificador());
        par.put("NitCertificador", DatosCertificador.getNitCertificador());
        par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
        String Doc = "";
        if (Fel.getTipoDocumento().equals("PROFORMA")) {
            
            Doc = "Factura_Carta_NoFel";
        } else {
            Doc = "Factura_Carta";
        }

        try {
            JasperReport reporte = null;

            String directorio2 = new File("/Sistema Punto de Venta YG/" + Doc + ".jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
        } catch (JRException ex) {
            System.out.println("Error  en Reporte Factura, " + ex);

        }

    }

    public void FacturaNoDTE(DatosClienteYFactura datos, DatosEmpresa Empresa, String TipoDocumento, JTable tabla, DocumentoFel Fel, int TipoDocumentoImpresion) throws PrinterException {
        
                Empleado em;// Instaciamos la clase empleado
                Fac datoscliente;
                datoscliente = new Fac(datos.getNombre(), datos.getNit(), datos.getDireccion(), datos.getNoDocumento(), datos.getTotal(), datos.getEfectivo(), datos.getCambio(),
                        datos.getNoVale(), datos.getNoCotizacion(),
                        datos.getFormadePago(), datos.getNumeroTransaccion(), datos.getVendedor(), datos.getObservacion(), datos.getTotalLetras(), datos.getFechaEmision(), datos.getFechaVenta());

                List<Empleado> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
                for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
                    em = new Empleado(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                            tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), tabla.getValueAt(i, 6).toString());
                    lista.add(em); //Agregamos el objeto empleado a la lista
                }
                HashMap<String, Object> par = new HashMap<String, Object>();
                par.put("factura", datoscliente.getFactura());
                par.put("nombre", datoscliente.getNombre());
                par.put("nit", datoscliente.getNit());
                par.put("direccion", datoscliente.getDireccion());
                par.put("labeltotal", datoscliente.getLabeltotal());
                par.put("pagocon", datoscliente.getPagocon());
                par.put("cambio", datoscliente.getCambio());
                par.put("observa", datoscliente.getObservacion());
                par.put("Vende", datoscliente.getVendedor());
                par.put("formapago", datoscliente.getFormaPago());
                par.put("totalletras", datoscliente.getTotalEnLetras());
                par.put("fechaemision", datoscliente.getFechaEmision());
                par.put("fecha_venta", datoscliente.getFechaVenta());
                par.put("nombreempresa", Empresa.getNombreEmpresa());
                par.put("nitempresa", Empresa.getNit());
                par.put("direccionempresa", Empresa.getDireccion());
                par.put("telempresa", Empresa.getTel());
                par.put("eslogan", Empresa.getEslogan());
                par.put("tipodocumento", TipoDocumento);
                par.put("politicas", Empresa.getPoliticas());

                par.put("TipoDocumento", Fel.getTipoDocumento());

                try {
                    JasperReport reporte = null;
                    Impresora.CargarDatosImpresoras();
                    String ImpresoraSeleccionada = null;

                    String directorio2 = null;
                    if (TipoDocumentoImpresion == 0) {
                        par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
                        directorio2 = new File("C:\\Sistema Punto de Venta YG\\Factura_Carta_NoFel.jasper").getAbsolutePath();
                        ImpresoraSeleccionada = Impresora.getIMPRESORA_HOJACARTA();
                    } else if (TipoDocumentoImpresion == 1) {
                        par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA2);
                        directorio2 = new File("C:\\Sistema Punto de Venta YG\\Factura_Ticket_NoFel_.jasper").getAbsolutePath();
                        ImpresoraSeleccionada = Impresora.getIMPRESORA_TICKET();
                    }

                    File prove = new File(directorio2);
                    reporte = (JasperReport) JRLoader.loadObject(prove);
                    JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));

                    JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);

                    estableceImpresoraPredeterminada(ImpresoraSeleccionada);
                    vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    vistaReporte.setVisible(true);

                    Boolean P = JasperPrintManager.printReport(imprimirReporte, true);

                    if (P == true) {
                        vistaReporte.dispose();
                    } else if (P == false) {
                        vistaReporte.dispose();
                    }

                } catch (JRException ex) {
                    System.out.println("Error  en Reporte Proforma, " + ex);

                }
    }

    public void Productos(DatosEmpresa datos, JTable tabla, Boolean Catalago) {

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ParametrosProductos em;// Instaciamos la clase empleado
                DatosCertificador DatosCertificador = new DatosCertificador();
                DatosEmpresa datoscliente;
                datoscliente = new DatosEmpresa(datos.getUsuario(), datos.getNombreEmpresa(), datos.getNit(), datos.getDireccion(), datos.getTel(), datos.getLugar(), datos.getEslogan());

                List<ParametrosProductos> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
                for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
                    em = new ParametrosProductos(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                            tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 4).toString(), tabla.getValueAt(i, 5).toString(), tabla.getValueAt(i, 9).toString());
                    lista.add(em); //Agregamos el objeto empleado a la lista
                }
                HashMap<String, Object> par = new HashMap<>();
                par.put("nombreempresa", PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
                par.put("nitempresa", PARAMETROS_EMPRESA.NIT_EMPRESA);
                par.put("direccionempresa", PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
                par.put("telempresa", PARAMETROS_EMPRESA.TEL_EMPRESA);
                par.put("eslogan", PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
                par.put("politicas", PARAMETROS_EMPRESA.POLITICAS_EMPRESA);
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);

                try {
                    String directorio2 = null;
                    if (Catalago == true) {
                        directorio2 = new File(PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/REPORTES/ProductosParametrosCatalago.jasper").getAbsolutePath();
                    } else if (Catalago == false) {
                        directorio2 = new File(PARAMETROS_VERSION_SISTEMA.RUTA_RAIZ+"/REPORTES/ProductosParametros.jasper").getAbsolutePath();
                    }
                    JasperReport reporte = null;

                    File prove = new File(directorio2);
                    reporte = (JasperReport) JRLoader.loadObject(prove);
                    JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
                    JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
                    vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    vistaReporte.setVisible(true);

                } catch (JRException ex) {
                    System.out.println("Error  en Reporte de Productos, " + ex);

                }

            }
        });

    }

   /* public void FacturaVale(DatosClienteYFactura datos, DatosEmpresa Empresa, String TipoDocumento, JTable tabla) {
        Empleado em;// Instaciamos la clase empleado
        Fac datoscliente;
        datoscliente = new Fac(datos.getNombre(), datos.getNit(), datos.getDireccion(), datos.getNoDocumento(), datos.getTotal(), datos.getEfectivo(), datos.getCambio(),
                datos.getNoVale(), datos.getNoCotizacion(),
                datos.getFormadePago(), datos.getNumeroTransaccion(), datos.getVendedor(), datos.getObservacion(), datos.getTotalLetras(), datos.getFechaEmision());

        List<Empleado> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
            em = new Empleado(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                    tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), tabla.getValueAt(i, 4).toString());
            lista.add(em); //Agregamos el objeto empleado a la lista
        }
        HashMap<String, Object> par = new HashMap<String, Object>();
        par.put("factura", datoscliente.getFactura());
        par.put("nombre", datoscliente.getNombre());
        par.put("nit", datoscliente.getNit());
        par.put("direccion", datoscliente.getDireccion());
        par.put("labeltotal", datoscliente.getLabeltotal());
        par.put("pagocon", datoscliente.getPagocon());
        par.put("cambio", datoscliente.getCambio());
        par.put("observa", datoscliente.getObservacion());
        par.put("Vende", datoscliente.getVendedor());
        par.put("formapago", datoscliente.getFormaPago());
        par.put("totalletras", datoscliente.getTotalEnLetras());
        par.put("fechaemision", datoscliente.getFechaEmision());
        par.put("nombreempresa", Empresa.getNombreEmpresa());
        par.put("nitempresa", Empresa.getNit());
        par.put("direccionempresa", Empresa.getDireccion());
        par.put("telempresa", Empresa.getTel());
        par.put("eslogan", Empresa.getEslogan());
        par.put("tipodocumento", TipoDocumento);

        try {
            JasperReport reporte = null;
            String a = datos.getNoDocumento() + ".pdf";
            String b = datos.getNoDocumento() + ".xml";
            File directorio = new File("/" + Empresa.getNombreEmpresa() + "/Ventas de " + Empresa.getNombreEmpresa());

            if (!directorio.exists()) {
                if (directorio.mkdirs()) {
                    JOptionPane.showMessageDialog(null, "Directorio creado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al crear directorio");
                }
            }
            String ruta2 = directorio + "\\" + a;
            String directorio2 = new File("C:\\Sistema Punto de Venta YG\\Factura_Vale.jasper").getAbsolutePath();
            File prove = new File(directorio2);
            File archivo = new File(ruta2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperExportManager.exportReportToPdfFile(imprimirReporte, ruta2);
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            JasperExportManager.exportReportToXmlFile(imprimirReporte, directorio + "\\" + b, true);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);

        } catch (JRException ex) {
            System.out.println("Error  en Reporte Vale, " + ex);

        }

    }*/
    
    public void DocumentoCotizacion(DatosClienteYFactura datos, DatosEmpresa Empresa, String TipoDocumento, JTable tabla, DocumentoFel Fel, int TipoDocumentoImpresion) throws PrinterException {
       
        Empleado em;// Instaciamos la clase empleado
        Fac datoscliente;
        Impresora.CargarDatosImpresoras();
        String ImpresoraSeleccionada = null;
        datoscliente = new Fac(datos.getNombre(), datos.getNit(), datos.getDireccion(), datos.getNoDocumento(), datos.getTotal(), datos.getEfectivo(), datos.getCambio(),
                datos.getNoVale(), datos.getNoCotizacion(),
                datos.getFormadePago(), datos.getNumeroTransaccion(), datos.getVendedor(), datos.getObservacion(), datos.getTotalLetras(), datos.getFechaEmision(), datos.getFechaVenta());

        List<Empleado> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
            em = new Empleado(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                    tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), tabla.getValueAt(i, 4).toString());
            lista.add(em); //Agregamos el objeto empleado a la lista
        }
        HashMap<String, Object> par = new HashMap<String, Object>();
        par.put("factura", datoscliente.getFactura());
        par.put("nombre", datoscliente.getNombre());
        par.put("nit", datoscliente.getNit());
        par.put("direccion", datoscliente.getDireccion());
        par.put("labeltotal", datoscliente.getLabeltotal());
        par.put("pagocon", datoscliente.getPagocon());
        par.put("cambio", datoscliente.getCambio());
        par.put("observa", datoscliente.getObservacion());
        par.put("Vende", datoscliente.getVendedor());
        par.put("formapago", datoscliente.getFormaPago());
        par.put("totalletras", datoscliente.getTotalEnLetras());
        par.put("fechaemision", datoscliente.getFechaEmision());
        par.put("fecha_venta", datoscliente.getFechaVenta());
        par.put("nombreempresa", Empresa.getNombreEmpresa());
        par.put("nitempresa", Empresa.getNit());
        par.put("direccionempresa", Empresa.getDireccion());
        par.put("telempresa", Empresa.getTel());
        par.put("eslogan", Empresa.getEslogan());
        par.put("tipodocumento", TipoDocumento);
        par.put("politicas", Empresa.getPoliticas());

        par.put("TipoDocumento", Fel.getTipoDocumento());
        

        try {
            JasperReport reporte = null;
            
            String directorio2 = null;
            if (TipoDocumentoImpresion == 0) {
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
                directorio2 = new File("C:\\Sistema Punto de Venta YG\\CotizacionCarta.jasper").getAbsolutePath();
                ImpresoraSeleccionada = Impresora.getIMPRESORA_HOJACARTA();
            } else if (TipoDocumentoImpresion == 1) {
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA2);
                directorio2 = new File("C:\\Sistema Punto de Venta YG\\CotizacionTicket.jasper").getAbsolutePath();
                ImpresoraSeleccionada = Impresora.getIMPRESORA_TICKET();
            }

            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            
            estableceImpresoraPredeterminada(ImpresoraSeleccionada);
            
            JasperViewer vistaReporte = new JasperViewer(imprimirReporte, false);
            vistaReporte.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vistaReporte.setVisible(true);
            
            Boolean P = JasperPrintManager.printReport(imprimirReporte, true);

            if (P == true) {
                vistaReporte.dispose();
            }else if(P == false){
                
               int Seleccion = JOptionPane.showConfirmDialog(null, "¿CERRAR EL DOCUMENTO?");
                if(Seleccion==0){
                vistaReporte.dispose();
                }
            }

        } catch (JRException ex) {
            System.out.println("Error  en Reporte Proforma, " + ex);

        }
    }

    public String DocumentoCotizacionGuardar(DatosClienteYFactura datos, DatosEmpresa Empresa, String TipoDocumento, JTable tabla, DocumentoFel Fel, int TipoDocumentoImpresion) throws PrinterException {
        String ruta2 = "";
        Empleado em;// Instaciamos la clase empleado
        Fac datoscliente;
        Impresora.CargarDatosImpresoras();
        datoscliente = new Fac(datos.getNombre(), datos.getNit(), datos.getDireccion(), datos.getNoDocumento(), datos.getTotal(), datos.getEfectivo(), datos.getCambio(),
                datos.getNoVale(), datos.getNoCotizacion(),
                datos.getFormadePago(), datos.getNumeroTransaccion(), datos.getVendedor(), datos.getObservacion(), datos.getTotalLetras(), datos.getFechaEmision(), datos.getFechaVenta());

        List<Empleado> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
            em = new Empleado(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                    tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), tabla.getValueAt(i, 4).toString());
            lista.add(em); //Agregamos el objeto empleado a la lista
        }
        HashMap<String, Object> par = new HashMap<String, Object>();
        par.put("factura", datoscliente.getFactura());
        par.put("nombre", datoscliente.getNombre());
        par.put("nit", datoscliente.getNit());
        par.put("direccion", datoscliente.getDireccion());
        par.put("labeltotal", datoscliente.getLabeltotal());
        par.put("pagocon", datoscliente.getPagocon());
        par.put("cambio", datoscliente.getCambio());
        par.put("observa", datoscliente.getObservacion());
        par.put("Vende", datoscliente.getVendedor());
        par.put("formapago", datoscliente.getFormaPago());
        par.put("totalletras", datoscliente.getTotalEnLetras());
        par.put("fechaemision", datoscliente.getFechaEmision());
        par.put("fecha_venta", datoscliente.getFechaVenta());
        par.put("nombreempresa", Empresa.getNombreEmpresa());
        par.put("nitempresa", Empresa.getNit());
        par.put("direccionempresa", Empresa.getDireccion());
        par.put("telempresa", Empresa.getTel());
        par.put("eslogan", Empresa.getEslogan());
        par.put("tipodocumento", TipoDocumento);
        par.put("politicas", Empresa.getPoliticas());

        par.put("TipoDocumento", Fel.getTipoDocumento());
        String directorio2 = null;
        try {
            JasperReport reporte = null;
            String a = datos.getNoDocumento() + ".pdf";
            if (TipoDocumentoImpresion == 0) {
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
                directorio2 = new File("C:\\Sistema Punto de Venta YG\\CotizacionCarta.jasper").getAbsolutePath();
                Impresora.getIMPRESORA_HOJACARTA();
            } else if (TipoDocumentoImpresion == 1) {
                par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA2);
                directorio2 = new File("C:\\Sistema Punto de Venta YG\\CotizacionTicket.jasper").getAbsolutePath();
                Impresora.getIMPRESORA_TICKET();
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            String selectPath = null;
            Component parent = null;
            int returnVal = chooser.showSaveDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            selectPath = chooser.getSelectedFile().getPath();
        }
            
            ruta2 = selectPath + "\\"+Fel.getTipoDocumento()+"-" + a;
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperExportManager.exportReportToPdfFile(imprimirReporte, ruta2);

        } catch (JRException ex) {
            System.out.println("Error  en Reporte Proforma, " + ex);

        }
        return ruta2;
    }

    
    public String CrearYGuardarDocumento(DatosClienteYFactura datos, DatosEmpresa Empresa, DatosCertificador DatosCertificador, DocumentoFel Fel, String TipoDocumento, JTable tabla, int TipoDocumentoImpresion) {
        String ruta2 = null;
        Empleado em;// Instaciamos la clase empleado
        Fac datoscliente;
        String a = null;
        datoscliente = new Fac(datos.getNombre(), datos.getNit(), datos.getDireccion(), datos.getNoDocumento(), datos.getTotal(), datos.getEfectivo(), datos.getCambio(),
                datos.getNoVale(), datos.getNoCotizacion(),
                datos.getFormadePago(), datos.getNumeroTransaccion(), datos.getVendedor(), datos.getObservacion(), datos.getTotalLetras(), datos.getFechaEmision(), datos.getFechaVenta());

        List<Empleado> lista = new ArrayList<>(); //Creamos una lista de empleados con ArrayList para obtener cada empleado
        for (int i = 0; i < tabla.getRowCount(); i++) { // Iterena cada fila de la tabla
            em = new Empleado(tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(), //Tomamos de la tabla el valor de cada columna y creamos un objeto empleado
                    tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), tabla.getValueAt(i, 6).toString());
            lista.add(em); //Agregamos el objeto empleado a la lista
        }
        HashMap<String, Object> par = new HashMap<String, Object>();
        par.put("factura", datoscliente.getFactura());
        par.put("nombre", datoscliente.getNombre());
        par.put("nit", datoscliente.getNit());
        par.put("direccion", datoscliente.getDireccion());
        par.put("labeltotal", datoscliente.getLabeltotal());
        par.put("pagocon", datoscliente.getPagocon());
        par.put("cambio", datoscliente.getCambio());
        par.put("observa", datoscliente.getObservacion());
        par.put("Vende", datoscliente.getVendedor());
        par.put("formapago", datoscliente.getFormaPago());
        par.put("totalletras", datoscliente.getTotalEnLetras());
        par.put("fechaemision", datoscliente.getFechaEmision());
        par.put("fecha_venta", datoscliente.getFechaVenta());
        par.put("nombreempresa", Empresa.getNombreEmpresa());
        par.put("nitempresa", Empresa.getNit());
        par.put("direccionempresa", Empresa.getDireccion());
        par.put("telempresa", Empresa.getTel());
        par.put("eslogan", Empresa.getEslogan());
        par.put("tipodocumento", TipoDocumento);

        par.put("politicas", Empresa.getPoliticas());

        par.put("TipoDocumento", Fel.getTipoDocumento());
        par.put("NumeroDocumento", Fel.getNumeroDocumento());
        par.put("SerieDocumento", Fel.getSerieDocumento());
        par.put("NumeroAutorizacion", Fel.getNumeroAutorizacion());
        par.put("FechaAutorizacion", Fel.getFechaAutorizacion());

        par.put("NombreCertificador", DatosCertificador.getNombreCertificador());
        par.put("NitCertificador", DatosCertificador.getNitCertificador());
        String Doc = "";
        if (TipoDocumentoImpresion == 0) {
            par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA);
            if (Fel.getTipoDocumento().equals("PROFORMA")) {
                
                Doc = "Factura_Carta_NoFel";
                a = datos.getNoDocumento() + ".pdf";
            } else {
                Doc = "Factura_Carta";
                a = Fel.getNumeroAutorizacion() + ".pdf";
            }
        } else if (TipoDocumentoImpresion == 1) {
            par.put("rutaimagen", METODOS_GLOBALES.CargarDatosRutas(0)+"\\"+PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA2);
            if (Fel.getTipoDocumento().equals("FACTURA")) {
                Doc = "TicketFel";
            a = Fel.getNumeroAutorizacion()+"_TICKET" + ".pdf";
            
             } else {
            Doc = "Factura_Ticket_NoFel_";
            a = datos.getNoDocumento()+"_TICKET" + ".pdf";
                }
            }
        try {
            JasperReport reporte = null;
            
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

            // obtener ruta
            selectPath = chooser.getSelectedFile().getPath();
        }
            
            ruta2 = selectPath + "\\" + a;
            String directorio2 = new File("/Sistema Punto de Venta YG/" + Doc + ".jasper").getAbsolutePath();
            File prove = new File(directorio2);
            reporte = (JasperReport) JRLoader.loadObject(prove);
            JasperPrint imprimirReporte = JasperFillManager.fillReport(reporte, par, new JRBeanCollectionDataSource(lista));
            JasperExportManager.exportReportToPdfFile(imprimirReporte, ruta2);
           
        } catch (JRException ex) {
            System.out.println("Error  en Reporte Factura, " + ex);

        }
        return ruta2;
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
