/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONSULTAS;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.Hora;
import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.Unionsis2;
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
public class CONSULTAS_CAJA extends ConexionesSQL{
    
    public static CAJA CAJA_CONSULTA() {
        CAJA caja = null;
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "SELECT ESTADO_DE_CAJA, FECHA_HORA_APERTURA_CAJA, USUARIO_APERTURO_CAJA, USUARIO_CERRO_CAJA, ARQUEO_DE_CAJA from caja WHERE FECHA="+METODOS_GLOBALES.Fecha()+" ORDER BY idcaja DESC";
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
    
    public static CAJA CAJA_CONSULTA_NUMERO(int NUMERO_CAJA) {
        CAJA caja = null;
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "SELECT ESTADO_DE_CAJA, Total_inicial_CAJA, Total_Compras_CAJA, Total_Ventas_CAJA, Total_Gastos_CAJA, Total_Efectivo_CAJA, Total_Transferencia_CAJA, Total_Cheque_CAJA,"
                + " Total_Tarjeta_CAJA, ARQUEO_DE_CAJA "
                + "from caja "
                + "WHERE idcaja="+NUMERO_CAJA;
            try {
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    caja= new CAJA();
                    System.out.println(rs.getString("ESTADO_DE_CAJA"));
                    caja.setESTADO_DE_CAJA(rs.getString("ESTADO_DE_CAJA"));
                    caja.setTotal_inicial_CAJA(rs.getFloat("Total_inicial_CAJA"));
                    caja.setTotal_Compras_CAJA(rs.getFloat("Total_Compras_CAJA"));
                    caja.setTotal_Ventas_CAJA(rs.getFloat("Total_Ventas_CAJA"));
                    caja.setTotal_Gastos_CAJA(rs.getFloat("Total_Gastos_CAJA"));
                    caja.setTotal_Efectivo_CAJA(rs.getFloat("Total_Efectivo_CAJA"));
                    caja.setTotal_Transferencia_CAJA(rs.getFloat("Total_Transferencia_CAJA"));
                    caja.setTotal_Cheque_CAJA(rs.getFloat("Total_Cheque_CAJA"));
                    caja.setTotal_Tarjeta_CAJA(rs.getFloat("Total_Tarjeta_CAJA"));
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
    
    public Float Total_Ventas(String Fecha, int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(Total) from registro where Fecha LIKE '%" + Fecha + "%' AND Estado='"+Filtro+"' AND id_CAJA_registro="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(Total)");
            }

        } catch (SQLException e) {
            System.err.println("Error Total Ventas, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_Compras(String Fecha, int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(TOTAL) from compras where FECHA = '" + Fecha + "' AND TIPO_COMPRA='COMPRA' AND id_CAJA="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(TOTAL)");
            }

        } catch (SQLException e) {
            System.err.println("Error TOTAL EN COMPRAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_ENTRADAS(String Fecha, int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(TOTAL) from compras where FECHA = '" + Fecha + "' AND TIPO_COMPRA='SALIDA' AND id_CAJA="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(TOTAL)");
            }

        } catch (SQLException e) {
            System.err.println("Error TOTAL EN COMPRAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_SALIDAS(String Fecha, int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(TOTAL) from compras where FECHA = '" + Fecha + "' AND TIPO_COMPRA='ENTRADA' AND id_CAJA="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(TOTAL)");
            }

        } catch (SQLException e) {
            System.err.println("Error TOTAL EN COMPRAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_Ventas_EFECTIVO(String Fecha, int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(Total) from registro where Fecha LIKE '%" + Fecha + "%' AND Estado='"+Filtro+"' AND FormaPago ='EFECTIVO' AND id_CAJA_registro="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(Total)");
            }

        } catch (SQLException e) {
            System.err.println("Error Total Ventas, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
}
