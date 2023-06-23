/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tablas;

import CLASES_GLOBALES.METODOS_GLOBALES;
import Conexiones.ConexionesSQL;
import Conexiones.conexion;
import Modelo.CAJA;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

/**
 *
 * @author aldoy
 */
public class ACTUALIZA_CAJA extends ConexionesSQL{
    
    public static List LISTAR_CAJAS(String Fecha, String Fecha2){
        List<CAJA> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            CAJA caja;
             ps = cn.prepareStatement("SELECT idcaja, ESTADO_DE_CAJA, Total_inicial_CAJA, FECHA_HORA_APERTURA_CAJA, FECHA_HORA_CIERRE_CAJA, USUARIO_APERTURO_CAJA, USUARIO_CERRO_CAJA, ARQUEO_DE_CAJA, FECHA from caja WHERE FECHA BETWEEN '"+Fecha+"' AND '"+Fecha2+"' ORDER BY idcaja DESC");
             rs = ps.executeQuery();
             while (rs.next()) {
                caja= new CAJA();
                caja.setIdcaja(rs.getInt("idcaja"));
                caja.setESTADO_DE_CAJA(rs.getString("ESTADO_DE_CAJA"));
                caja.setTotal_inicial_CAJA(rs.getFloat("Total_inicial_CAJA"));
                caja.setFECHA_HORA_APERTURA_CAJA(rs.getString("FECHA_HORA_APERTURA_CAJA"));
                caja.setFECHA_HORA_CIERRE_CAJA(rs.getString("FECHA_HORA_CIERRE_CAJA"));
                caja.setUSUARIO_APERTURO_CAJA(rs.getInt("USUARIO_APERTURO_CAJA"));
                caja.setUSUARIO_CERRO_CAJA(rs.getInt("USUARIO_CERRO_CAJA"));
                caja.setUSUARIO_APERTURO_CAJA_LETRAS("");
                caja.setUSUARIO_CERRO_CAJA_LETRAS("");
                caja.setARQUEO_DE_CAJA(rs.getString("ARQUEO_DE_CAJA"));
                caja.setFECHA(rs.getString("FECHA"));
               Listapro.add(caja);
            }

        } catch (SQLException e) {
            System.err.println("Error Listar CAJAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
    
    public static List LISTAR_CAJAS_ACTIVAS(String Fecha){
        List<CAJA> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            CAJA caja;
             ps = cn.prepareStatement("SELECT idcaja, ESTADO_DE_CAJA, Total_inicial_CAJA, FECHA_HORA_APERTURA_CAJA, FECHA_HORA_CIERRE_CAJA, USUARIO_APERTURO_CAJA, USUARIO_CERRO_CAJA, ARQUEO_DE_CAJA, FECHA from caja WHERE FECHA= '"+Fecha+"' AND ESTADO_DE_CAJA='APERTURADO' ORDER BY idcaja desc");
             rs = ps.executeQuery();
             while (rs.next()) {
                caja= new CAJA();
                caja.setIdcaja(rs.getInt("idcaja"));
                caja.setESTADO_DE_CAJA(rs.getString("ESTADO_DE_CAJA"));
                caja.setTotal_inicial_CAJA(rs.getFloat("Total_inicial_CAJA"));
                caja.setFECHA_HORA_APERTURA_CAJA(rs.getString("FECHA_HORA_APERTURA_CAJA"));
                caja.setFECHA_HORA_CIERRE_CAJA(rs.getString("FECHA_HORA_CIERRE_CAJA"));
                caja.setUSUARIO_APERTURO_CAJA(rs.getInt("USUARIO_APERTURO_CAJA"));
                caja.setUSUARIO_CERRO_CAJA(rs.getInt("USUARIO_CERRO_CAJA"));
                caja.setUSUARIO_APERTURO_CAJA_LETRAS("");
                caja.setUSUARIO_CERRO_CAJA_LETRAS("");
                caja.setARQUEO_DE_CAJA(rs.getString("ARQUEO_DE_CAJA"));
                caja.setFECHA(rs.getString("FECHA"));
               Listapro.add(caja);
            }

        } catch (SQLException e) {
            System.err.println("Error Listar CAJAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
}
