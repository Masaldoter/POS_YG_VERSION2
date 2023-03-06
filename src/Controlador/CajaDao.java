/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexiones.ConexionesSQL;
import Modelo.CAJA;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author aldoy
 */
public class CajaDao extends ConexionesSQL{
    
    public boolean REGISTRAR_APERTURAS_CIERRES_CAJA(CAJA pro){
        
        ps = null;
        cn = Unionsis2.getConnection();
        String sql="insert into caja (ESTADO_DE_CAJA, FECHA_HORA_APERTURA_CAJA, USUARIO_APERTURO_CAJA, ARQUEO_DE_CAJA, FECHA,"
                + "Total_inicial_CAJA) "
                + "values (?, ?, ?, ?, ?, ?)";       
        try {
            
            
            ps=cn.prepareStatement(sql);
            
            
            
             ps.setString(1, pro.getESTADO_DE_CAJA());
              ps.setString(2, pro.getFECHA_HORA_APERTURA_CAJA());
                ps.setInt(3, pro.getUSUARIO_APERTURO_CAJA());
                ps.setString(4, pro.getARQUEO_DE_CAJA());
                ps.setString(5, pro.getFECHA());
                ps.setFloat(6, pro.getTotal_inicial_CAJA());
                ps.execute();       
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("¡MOVIMIENTO EXITOSO!", "¡SE "+pro.getESTADO_DE_CAJA()+" CAJA CORRECTAMENTE!", DesktopNotify.SUCCESS, 10000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("NO SE PUDO REGISTRAR DEBIDO A EL SIGUIENTE ERROR!\n "+e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO ERRÓNEO","NO SE PUDO REGISTRAR DEBIDO A EL SIGUIENTE ERROR!\n "+e, DesktopNotify.ERROR, 10000L);
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean EDITAR_CIERRES_DE_CAJA(CAJA pro){
        ps = null;
        cn = Unionsis2.getConnection();
        
        String sql="update caja set ESTADO_DE_CAJA=?, FECHA_HORA_CIERRE_CAJA=?, USUARIO_CERRO_CAJA=?, ARQUEO_DE_CAJA=?, Total_Compras_CAJA=?, Total_Ventas_CAJA=?,  Total_Gastos_CAJA=?,"
                + "Total_Efectivo_CAJA=?, Total_Transferencia_CAJA=?, Total_Cheque_CAJA=?, Total_Tarjeta_CAJA=?"
                + " where idcaja=?";       
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, pro.getESTADO_DE_CAJA());
              ps.setString(2, pro.getFECHA_HORA_CIERRE_CAJA());
                ps.setInt(3, pro.getUSUARIO_CERRO_CAJA());
                ps.setString(4, pro.getARQUEO_DE_CAJA());
                ps.setFloat(5, pro.getTotal_Ventas_CAJA());
                ps.setFloat(6, pro.getTotal_Compras_CAJA());
                ps.setFloat(7, pro.getTotal_Gastos_CAJA());
                ps.setFloat(8, pro.getTotal_Efectivo_CAJA());
                ps.setFloat(9, pro.getTotal_Transferencia_CAJA());
                ps.setFloat(10, pro.getTotal_Cheque_CAJA());
                ps.setFloat(11, pro.getTotal_Tarjeta_CAJA());
                ps.setInt(12,pro.getIdcaja());
            
            ps.executeUpdate(); 
            
            
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("GUARDADO ÉXITOSO", "¡CAJA #"+pro.getIdcaja()+" CERRADA ÉXITOSAMENTE!", DesktopNotify.SUCCESS, 10000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ERROR", "¡HUBO UN ERROR AL CERRAR LA CAJA!\n"+e, DesktopNotify.SUCCESS, 10000L);
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean REAPERTURAR_CAJA(CAJA pro){
        ps = null;
        cn = Unionsis2.getConnection();
        
        String sql="update caja set ESTADO_DE_CAJA=?, FECHA_HORA_CIERRE_CAJA=?, USUARIO_CERRO_CAJA=?, ARQUEO_DE_CAJA=?, Total_Compras_CAJA=?, Total_Ventas_CAJA=?,  Total_Gastos_CAJA=?,"
                + "Total_Efectivo_CAJA=?, Total_Transferencia_CAJA=?, Total_Cheque_CAJA=?, Total_Tarjeta_CAJA=?"
                + " where idcaja=?";       
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, pro.getESTADO_DE_CAJA());
              ps.setString(2, pro.getFECHA_HORA_CIERRE_CAJA());
                ps.setInt(3, pro.getUSUARIO_CERRO_CAJA());
                ps.setString(4, pro.getARQUEO_DE_CAJA());
                ps.setFloat(5, pro.getTotal_Ventas_CAJA());
                ps.setFloat(6, pro.getTotal_Compras_CAJA());
                ps.setFloat(7, pro.getTotal_Gastos_CAJA());
                ps.setFloat(8, pro.getTotal_Efectivo_CAJA());
                ps.setFloat(9, pro.getTotal_Transferencia_CAJA());
                ps.setFloat(10, pro.getTotal_Cheque_CAJA());
                ps.setFloat(11, pro.getTotal_Tarjeta_CAJA());
                ps.setInt(12,pro.getIdcaja());
            
            ps.executeUpdate(); 
            
            
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("GUARDADO ÉXITOSO", "¡CAJA #"+pro.getIdcaja()+" REAPERTURADA ÉXITOSAMENTE!", DesktopNotify.SUCCESS, 10000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ERROR", "¡HUBO UN ERROR AL REAPERTURAR LA CAJA!\n"+e, DesktopNotify.SUCCESS, 10000L);
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
}
