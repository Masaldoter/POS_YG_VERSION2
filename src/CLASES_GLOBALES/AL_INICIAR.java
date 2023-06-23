/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import Conexiones.conexion;
import java.sql.SQLException;

/**
 *
 * @author MASALDOTER_GT
 */
public class AL_INICIAR extends ConexionesSQL{
    
    public void VERIFICAR_FULLTEXT_PRODUCTOS(){
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
    
}
