/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.util.Locale;

/**
 *
 * @author MASALDOTER_GT
 */
public class AL_INICIAR {
    Locale local;
    public void CARGAR_CONFIGURACIONES(){
        Locale.setDefault(Locale.US);

        // Imprimir la localización actual
        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("CONFIGURACION", "LA REGIÓN ACTUAL ES: " + Locale.getDefault(), DesktopNotify.INFORMATION, 14000L);
         /*           try {
            cn = conexion.getInstancia().getConnection();
            String tableName = "productos";
            String columnName1 = "Nombre";
            String columnName2 = "Descripcion";

            //"select Nombre, ruta from productos WHERE (Estado_Productos='ACTIVO') ORDER BY idProductos DESC"
            ps = cn.prepareStatement("SHOW INDEXES FROM " + tableName + " WHERE COLUMN_NAME IN ('" + columnName1 + "', '" + columnName2 + "')");
            rs = ps.executeQuery();

            boolean hasFulltextIndex = false;

            while (rs.next()) {
                String indexName = rs.getString("Column_name");
                if (indexName != null && indexName.startsWith("FULLTEXT")) {
                    hasFulltextIndex = true;
                    break;
                }
            }

            if (!hasFulltextIndex) {
                // El índice FULLTEXT no existe en las columnas especificadas, lo creamos
                ps.execute("ALTER TABLE " + tableName + " ADD FULLTEXT(" + columnName1 + ", " + columnName2 + ")");
            }

        } catch (SQLException e) {
            System.err.println("ERROR AL EJECUTAR COMPROBACION Y CONSULTA AL FULLTEXT, " + e);
        } finally {
            RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }*/

    }
    
    public static void OBTENER_REGION(){
        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("CONFIGURACION", "LA REGIÓN ACTUAL ES: " + Locale.getDefault(), DesktopNotify.INFORMATION, 14000L);
    }
}
