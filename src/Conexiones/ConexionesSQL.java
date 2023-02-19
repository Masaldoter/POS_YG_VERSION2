/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexiones;

import Conexiones.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionesSQL{
  
    public static conexion Unionsis2= conexion.getInstancia();
    
    public static PreparedStatement ps;
    public static ResultSet rs = null;
    public static Connection cn = null;
    public static Connection conexionlocal = null;
    
    public static void RsClose(ResultSet rs){
        try {
            rs.close();
        } catch (SQLException ex) {
        ex.printStackTrace(System.out);
        }
    }
    
    public static void PsClose(PreparedStatement ps){
        try {
            ps.close();
        } catch (SQLException ex) {
            System.out.println("En PS es el error / CLASE ConexionesSQL" +ex);
        ex.printStackTrace(System.out);
        }
    }
    
    public static void ConnectionClose(Connection getConnection){
        try {
            getConnection.close();
        } catch (SQLException ex) {
        ex.printStackTrace(System.out);
        }
    }
}
