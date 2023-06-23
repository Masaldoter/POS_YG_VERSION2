/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexiones.ConexionesSQL;
import Modelo.Compras;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;
/**
 *
 * @author MASALDOTER_GT
 */
public class ComprasDao extends ConexionesSQL{
    
    
    public boolean REGISTRAR_GASTOS_COMPRAS(Compras pro){
        
        ps = null;
        cn = Unionsis2.getConnection();
        String sql="insert into compras (TOTAL, DESCRIPCION_COMPRA, USUARIO_REGISTRO, FECHA_HORA_COMPRA, FECHA,"
                + "id_CAJA, TIPO_COMPRA) "
                + "values (?, ?, ?, ?, ?, ?, ?)";       
        try {
            
            
            ps=cn.prepareStatement(sql);
            
            ps.setFloat(1, pro.getTOTAL_COMPRA());
            ps.setString(2, pro.getDESCRIPCION_COMPRAS());
            ps.setInt(3, pro.getUSUARIO_REGISTRO_COMPRA());
            ps.setString(4, pro.getFECHA_HORA_COMPRA());
            ps.setString(5, pro.getFECHA());
            ps.setInt(6, pro.getIdCAJA());
            ps.setString(7, pro.getTIPO_COMPRA());
            ps.execute();  
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("¡MOVIMIENTO EXITOSO!", "¡LA "+pro.getTIPO_COMPRA()+" DE "+pro.getTOTAL_COMPRA()+" SE REGISTRÓ CORRECTAMENTE!", DesktopNotify.SUCCESS, 10000L);
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
