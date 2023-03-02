/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONSULTAS;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.Hora;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import Conexiones.conexion;
import Modelo.CAJA;
import java.sql.SQLException;

/**
 *
 * @author aldoy
 */
public class CONSULTAS_CAJA {
    
    public static CAJA CAJA_CONSULTA() {
        CAJA caja = null;
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "SELECT ESTADO_DE_CAJA, FECHA_HORA_APERTURA_CAJA, USUARIO_APERTURO_CAJA, USUARIO_CERRO_CAJA, ARQUEO_DE_CAJA from caja WHERE FECHA="+METODOS_GLOBALES.Fecha();
            try {
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    caja= new CAJA();
                    System.out.println(rs.getString("ESTADO_DE_CAJA"));
                    caja.setESTADO_DE_CAJA(rs.getString("ESTADO_DE_CAJA"));
                    caja.setFECHA_HORA_APERTURA_CAJA(rs.getString("FECHA_HORA_APERTURA_CAJA"));
                    caja.setUSUARIO_APERTURO_CAJA(rs.getInt("USUARIO_APERTURO_CAJA"));
                    caja.setUSUARIO_CERRO_CAJA(rs.getInt("USUARIO_CERRO_CAJA"));
                    caja.setARQUEO_DE_CAJA(rs.getString("ARQUEO_DE_CAJA"));
                }

        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return caja;
    }
}
