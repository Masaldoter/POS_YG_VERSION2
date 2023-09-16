/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexiones.ConexionesSQL;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;

/**
 *
 * @author MASALDOTER_GT
 */
public class SerialesDao extends ConexionesSQL{
    
    public Boolean VERIFICAR_SERIAL(String Parametro) {
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        Boolean Datos = false;
                String sql = "SELECT Seriales from seriales WHERE Seriales=?";
            try {
                ps = cn.prepareStatement(sql);
                ps.setString(1, Parametro);
                rs = ps.executeQuery();
                if (rs.next()) {
                        Datos = true;
                }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Datos;
    }
    
    public boolean RegistrarSerial(String Serie){
        
        ps = null;
        cn = Unionsis2.getConnection();
        String sql="insert into seriales (Seriales) "
                + "values (?)";       
        try {
            ps=cn.prepareStatement(sql);
             ps.setString(1, Serie);
                ps.execute();       
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO ERRÓNEO", "¡EL SERIAL NO SE REGISTRÓ CORRECTAMENTE, POR FAVOR CONTACTAR CON EL PROGRAMADOR!\n "+e, DesktopNotify.ERROR, 10000L);
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
}
