/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONSULTAS;

import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import Modelo.Kardex;
import Tablas.ACTUALIZAR_KARDEX;
import Tablas.RenderTablas;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aldoy
 */
public class CONSULTAS_KARDEX {
    
    public static void ListarKardex(JTable TABLA, String PARAMETRO, int TIPO_CONSULTA, Boolean IMPORTAR_ORDEN) {
        LIMPIAR_TABLA(TABLA);
        TABLA.setDefaultRenderer(Object.class, new RenderTablas());
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) TABLA.getModel();
        ACTUALIZAR_KARDEX CON_KARDEX = new ACTUALIZAR_KARDEX();
        List<Kardex> ListarPr = CON_KARDEX.CONSULTAR_KARDEX(PARAMETRO, TIPO_CONSULTA, IMPORTAR_ORDEN);

        Object[] ob = new Object[13];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdkardex();
            ob[1] = ListarPr.get(i).getID_Codigo_Producto_Kardex();
            ob[2] = ListarPr.get(i).getCodigo_Producto_NOMBRE_Kardex();
            ob[3] = "<html>"+ListarPr.get(i).getNOMBRE_Producto_NOMBRE_Kardex()+"</html>";
            ob[4] = "<html>"+ListarPr.get(i).getTitulo_Kardex()+"</html>";
            ob[5] = "<html>"+ListarPr.get(i).getEntrada_Kardex()+"</html>";
            ob[6] = "<html>"+ListarPr.get(i).getSalida_Kardex()+"</html>";
            ob[7] = "<html>"+ListarPr.get(i).getAntes_Kardex()+"</html>";
            ob[8] = "<html>"+ListarPr.get(i).getDespues_Kardex()+"</html>";
            ob[9] = ListarPr.get(i).getSTOCK_PRODUCTO_KARDEX();
            ob[10] = ListarPr.get(i).getFecha_Modificacion_Kardex();
            ob[11] = "<html>"+ListarPr.get(i).getUsuario_Modifico_Kardex()+" | " + ListarPr.get(i).getUsuario_Modifico_LETRAS_Kardex()+"</html>";
            ob[12] = ListarPr.get(i).getModulo_Kardex();
            
            modelo2.addRow(ob);
        }
        TABLA.setModel(modelo2);

    }
    
}
