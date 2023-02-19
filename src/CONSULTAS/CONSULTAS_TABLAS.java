package CONSULTAS;


import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import Modelo.Clientes;
import Tablas.ActualizarTablaClientes;
import Tablas.RenderTablas;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aldoy
 */
public class CONSULTAS_TABLAS {
    
    public static void ListarClientes(JTable TABLA, String TIPO_CONSULTA, String PARAMETRO) {
        LIMPIAR_TABLA(TABLA);
        TABLA.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) TABLA.getModel();
        ActualizarTablaClientes tablasclientes = new ActualizarTablaClientes();
        List<Clientes> ListarPr = tablasclientes.ListarClientes(TIPO_CONSULTA, PARAMETRO);

        Object[] ob = new Object[8];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdclientes();
            ob[1] = ListarPr.get(i).getNombre();
            ob[2] = ListarPr.get(i).getIDENTIFICACION();
            ob[3] = ListarPr.get(i).getTIPO_IDENTIFICACION();
            ob[4] = ListarPr.get(i).getDireccion();
            ob[5] = ListarPr.get(i).getTelefono();
            ob[6] = ListarPr.get(i).getCorreo();
            ob[7] = ListarPr.get(i).getBotoneditar();
            modelo2.addRow(ob);
        }
        TABLA.setModel(modelo2);

    }
}
