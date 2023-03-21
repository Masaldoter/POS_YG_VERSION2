/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONSULTAS;

import static CLASES_GLOBALES.METODOS_GLOBALES.CONVERTIR_FECHA;
import static CLASES_GLOBALES.METODOS_GLOBALES.CONVERTIR_HORA;
import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Modelo.Venta;
import Tablas.ActualizarTablaVentasDiariasYGenerales;
import Tablas.RenderTablas;
import com.toedter.calendar.JDateChooser;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MASALDOTER_GT
 */
public class CONSULTAS_VENTAS {

    ActualizarTablaVentasDiariasYGenerales tablaVentasDiariasGenerales = new ActualizarTablaVentasDiariasYGenerales();

    public synchronized void ActualizarRegistroVenta(JTable TABLA, JComboBox EstadoVentaGeneral, JComboBox TipoDocumento) {

        LIMPIAR_TABLA(TABLA);
        TABLA.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) TABLA.getModel();
        List<Venta> ListarPr = tablaVentasDiariasGenerales.ListarVentasGenerales(EstadoVentaGeneral, TipoDocumento);

        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNoFactura();
            ob[1] = ListarPr.get(i).getIDENTIFICACION_CLIENTE() + " | " + ListarPr.get(i).getCliente();
            ob[2] = CONVERTIR_HORA(ListarPr.get(i).getHora()) + " | " + CONVERTIR_FECHA(ListarPr.get(i).getFecha());
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getPagocon();
            ob[5] = ListarPr.get(i).getCambio();
            ob[6] = ListarPr.get(i).getFormaPago();
            ob[7] = ListarPr.get(i).getUsuario() + " - " + ListarPr.get(i).getUSUARIO_REGISTRO_LETRAS();
            ob[8] = ListarPr.get(i).getTipoDocumentoFel();
            ob[9] = ListarPr.get(i).getEstado();
            ob[10] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        TABLA.setModel(modelo2);

        Double TotalPagar2 = 0.00;
        int numFila = TABLA.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TABLA.getValueAt(i, 4)));
            TotalPagar2 = TotalPagar2 + cal;

        }
    }

    public synchronized void ActualizarRegistroVentaPorFecha(JTable TABLA, JDateChooser fechabus, JDateChooser Fecha2, JComboBox EstadoVentaGeneral, JComboBox TipoDocumento) {
        LIMPIAR_TABLA(TABLA);
        TABLA.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) TABLA.getModel();
        List<Venta> ListarPr = tablaVentasDiariasGenerales.ListarVentasGeneralesPorFecha(fechabus, Fecha2, EstadoVentaGeneral, TipoDocumento);

        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNoFactura();
            ob[1] = ListarPr.get(i).getIDENTIFICACION_CLIENTE() + " | " + ListarPr.get(i).getCliente();
            ob[2] = CONVERTIR_HORA(ListarPr.get(i).getHora()) + " | " + CONVERTIR_FECHA(ListarPr.get(i).getFecha());
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getPagocon();
            ob[5] = ListarPr.get(i).getCambio();
            ob[6] = ListarPr.get(i).getFormaPago();
            ob[7] = ListarPr.get(i).getUsuario() + " | " + ListarPr.get(i).getUSUARIO_REGISTRO_LETRAS();
            ob[8] = ListarPr.get(i).getTipoDocumentoFel();
            ob[9] = ListarPr.get(i).getEstado();
            ob[10] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        TABLA.setModel(modelo2);
    }

    public synchronized void ActualizarRegistroVentaPorNit(String NitNombre, JTable Tabla, JComboBox EstadoVenta) {
        LIMPIAR_TABLA(Tabla);
        Tabla.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) Tabla.getModel();
        List<Venta> ListarPr = tablaVentasDiariasGenerales.ListarVentasGeneralesPorCliente(NitNombre, EstadoVenta);

        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNoFactura();
            ob[1] = ListarPr.get(i).getIDENTIFICACION_CLIENTE() + " | " + ListarPr.get(i).getCliente();
            ob[2] = CONVERTIR_HORA(ListarPr.get(i).getHora()) + " | " + CONVERTIR_FECHA(ListarPr.get(i).getFecha());
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getPagocon();
            ob[5] = ListarPr.get(i).getCambio();
            ob[6] = ListarPr.get(i).getFormaPago();
            ob[7] = ListarPr.get(i).getUsuario() + " - " + ListarPr.get(i).getUSUARIO_REGISTRO_LETRAS();
            ob[8] = ListarPr.get(i).getTipoDocumentoFel();
            ob[9] = ListarPr.get(i).getEstado();
            ob[10] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        Tabla.setModel(modelo2);
    }

    public synchronized void ActualizarRegistroVentaPorDocumento(JTextField NumeroDocumento, Boolean IncluirFecha, JTable Tabla) {
        LIMPIAR_TABLA(Tabla);
        Tabla.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) Tabla.getModel();
        List<Venta> ListarPr = tablaVentasDiariasGenerales.ListarVentasGeneralesPorDocumento(NumeroDocumento, IncluirFecha);

        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNoFactura();
            ob[1] = ListarPr.get(i).getIDENTIFICACION_CLIENTE() + " | " + ListarPr.get(i).getCliente();
            ob[2] = CONVERTIR_HORA(ListarPr.get(i).getHora()) + " | " + CONVERTIR_FECHA(ListarPr.get(i).getFecha());
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getPagocon();
            ob[5] = ListarPr.get(i).getCambio();
            ob[6] = ListarPr.get(i).getFormaPago();
            ob[7] = ListarPr.get(i).getUsuario() + " - " + ListarPr.get(i).getUSUARIO_REGISTRO_LETRAS();
            ob[8] = ListarPr.get(i).getTipoDocumentoFel();
            ob[9] = ListarPr.get(i).getEstado();
            ob[10] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        Tabla.setModel(modelo2);
    }

    public synchronized void ActualizarRegistroPorNombreDetalleProductos(Boolean IncluirFecha, JTextField Producto, JTable tabla) {
        LIMPIAR_TABLA(tabla);
        tabla.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) tabla.getModel();
        List<Venta> ListarPr = tablaVentasDiariasGenerales.ListarVentasGeneralesPorNombreDetalleProducto(Producto, IncluirFecha);

        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getCliente();
            ob[1] = ListarPr.get(i).getHora();
            ob[2] = ListarPr.get(i).getFecha();
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getNoFactura();
            ob[5] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        tabla.setModel(modelo2);
    }

    public synchronized void ActualizarRegistroVentaPorTipoDocumento(javax.swing.JComboBox NumeroDocumento, Boolean IncluirFecha, JTable Tabla) {
        LIMPIAR_TABLA(Tabla);
        Tabla.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) Tabla.getModel();
        List<Venta> ListarPr = null;
        if (PARAMETROS_USUARIOS.ROL_USUARIO.equals("Usuario")) {
            //   ListarPr = tablaVentasDiariasGenerales.ListarVentasGeneralesPorTipoDocumento(NumeroDocumento, IncluirFecha, true, Sele.getText());
        } else {
            ListarPr = tablaVentasDiariasGenerales.ListarVentasGeneralesPorTipoDocumento(NumeroDocumento, IncluirFecha, false, "");
        }

        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNoFactura();
            ob[1] = ListarPr.get(i).getIDENTIFICACION_CLIENTE() + " | " + ListarPr.get(i).getCliente();
            ob[2] = CONVERTIR_HORA(ListarPr.get(i).getHora()) + " | " + CONVERTIR_FECHA(ListarPr.get(i).getFecha());
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getPagocon();
            ob[5] = ListarPr.get(i).getCambio();
            ob[6] = ListarPr.get(i).getFormaPago();
            ob[7] = ListarPr.get(i).getUsuario() + " - " + ListarPr.get(i).getUSUARIO_REGISTRO_LETRAS();
            ob[8] = ListarPr.get(i).getTipoDocumentoFel();
            ob[9] = ListarPr.get(i).getEstado();
            ob[10] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        Tabla.setModel(modelo2);
    }

    public void Registros(JTable TABLA, JComboBox EstadoVentas) {
        LIMPIAR_TABLA(TABLA);
        TABLA.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) TABLA.getModel();
        List<Venta> ListarPr = tablaVentasDiariasGenerales.ListarVentasDiarias(EstadoVentas);

        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNoFactura();
            ob[1] = ListarPr.get(i).getIDENTIFICACION_CLIENTE() + " | " + ListarPr.get(i).getCliente();
            ob[2] = CONVERTIR_HORA(ListarPr.get(i).getHora()) + " | " + CONVERTIR_FECHA(ListarPr.get(i).getFecha()) ;
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getPagocon();
            ob[5] = ListarPr.get(i).getCambio();
            ob[6] = ListarPr.get(i).getFormaPago();
            ob[7] = ListarPr.get(i).getUsuario() + " | " + ListarPr.get(i).getUSUARIO_REGISTRO_LETRAS();
            ob[8] = ListarPr.get(i).getTipoDocumentoFel();
            ob[9] = ListarPr.get(i).getEstado();
            ob[10] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        TABLA.setModel(modelo2);

    }

    public void RegistrosUsuario(JTable TABLA, JComboBox EstadoVentas, String Usuario) {
        //tablaVentasDiariasGenerales.RegistrosUsuario(VD, fechas, Integer.parseInt(Sele.getText()), TP, Pago, jLabel41);
        LIMPIAR_TABLA(TABLA);
        TABLA.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();

        List<Venta> ListarPr = tablaVentasDiariasGenerales.ListarVentasDiariasPorUsuario(EstadoVentas, Integer.parseInt(Usuario));
        modelo2 = (DefaultTableModel) TABLA.getModel();
        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNoFactura();
            ob[1] = ListarPr.get(i).getIDENTIFICACION_CLIENTE() + " | " + ListarPr.get(i).getCliente();
            ob[2] = CONVERTIR_HORA(ListarPr.get(i).getHora()) + " | " + CONVERTIR_FECHA(ListarPr.get(i).getFecha());
            ob[3] = ListarPr.get(i).getTotal();
            ob[4] = ListarPr.get(i).getPagocon();
            ob[5] = ListarPr.get(i).getCambio();
            ob[6] = ListarPr.get(i).getFormaPago();
            ob[7] = ListarPr.get(i).getUsuario() + " - " + ListarPr.get(i).getUSUARIO_REGISTRO_LETRAS();
            ob[8] = ListarPr.get(i).getTipoDocumentoFel();
            ob[9] = ListarPr.get(i).getEstado();
            ob[10] = ListarPr.get(i).getDetalles();
            modelo2.addRow(ob);
        }
        TABLA.setModel(modelo2);
    }

}
