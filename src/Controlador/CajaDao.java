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
    
    
}
