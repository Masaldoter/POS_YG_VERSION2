/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import Conexiones.conexion;
import Modelo.Kardex;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;

/**
 *
 * @author aldoy
 */
public class KardexDao {
    
    public boolean RegistrarKARDEX(Kardex Kd) {
        boolean Resultado = false;
        ps = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "insert into kardex (Codigo_Producto_Kardex, Usuario_Modifico_Kardex, Fecha_Modificacion_Kardex, Titulo_Kardex, Entrada_Kardex, Salida_Kardex, Antes_Kardex, Despues_Kardex, Modulo_Kardex)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, Kd.getID_Codigo_Producto_Kardex());
            ps.setInt(2, Kd.getUsuario_Modifico_Kardex());
            ps.setString(3, Kd.getFecha_Modificacion_Kardex());
            ps.setString(4, Kd.getTitulo_Kardex());
            ps.setString(5, Kd.getEntrada_Kardex());
            ps.setString(6, Kd.getSalida_Kardex());
            ps.setString(7, Kd.getAntes_Kardex());
            ps.setString(8, Kd.getDespues_Kardex());  
            ps.setString(9, Kd.getModulo_Kardex());  
            ps.execute();
            Resultado =true;

        } catch (SQLException e) {
            Resultado =false;
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR EN BASE DE DATOS", "ERROR AL REGISTRAR\n"+ e, DesktopNotify.FAIL, 9000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Resultado;
    }
    
}
