/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Conexiones.conexion;
import Modelo.Proveedor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaldoter
 */
public class ActualizarTablaProveedor extends ConexionesSQL{
    
    public static List ListarProveedores(){
        List<Proveedor> Listapro = new ArrayList();
        try {
            cn = Unionsis2.getInstancia().getConnection();
            rs = null;
            ps = null;
            Proveedor pro1;
             ps = cn.prepareStatement("select idproveedores, Proveedor, Vendedor, Tel from proveedores ORDER BY idproveedores DESC");
             rs = ps.executeQuery();

            while (rs.next()) {
                pro1= new Proveedor();
                //pro1.setIdProductos(rs.getInt("productos.CodigoBarras"));
                pro1.setIdproveedores(rs.getInt("idproveedores"));
                pro1.setProveedor(rs.getString("Proveedor"));
                pro1.setVendedor(rs.getString("Vendedor"));
                pro1.setTel(rs.getString("Tel"));
               Listapro.add(pro1);
            }

        } catch (SQLException e) {
            System.err.println("Error Listar Proveedores, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
}
