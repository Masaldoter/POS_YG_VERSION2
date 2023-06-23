/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.conexion;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaldoter
 */
public class ActualizarTablaUsuarios {
    
    
    public static void Usuarios(JTable TablalUsuarios){
        conexion Unionsis2= conexion.getInstancia();
        Connection cn = Unionsis2.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
       DefaultTableModel modeloTabla = new DefaultTableModel();
        TablalUsuarios.setModel(modeloTabla); 
        try {
            ps= cn.prepareStatement("select idlogin1, Nombre, Contraseña, Rol, NombreUsuario, FechaIngreso, Ingreso, UltimaVezIngreso, Estado, Estado_Registro from login1");
            rs= ps.executeQuery();
            
            modeloTabla.addColumn("Id");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Contraseña");
            modeloTabla.addColumn("Rol");
            modeloTabla.addColumn("NombreUsuario");
            modeloTabla.addColumn("Fecha de Registro");
            modeloTabla.addColumn("Fecha de Ingreso");
            modeloTabla.addColumn("Última Vez.");
            modeloTabla.addColumn("LÍNEA");
            modeloTabla.addColumn("ESTADO");

            while (rs.next()) {

                Object fila[] = new Object[10];
                for (int i = 0; i < 10; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR USUARIOS \n"+e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
}
