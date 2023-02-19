
package Controlador;
import Conexiones.conexion;
import Modelo.Proveedor;
import Conexiones.ConexionesSQL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class ProveedoresDao extends ConexionesSQL{
    Proveedor prove= new Proveedor();
    
    public List ListarProveedor(){
        cn= Unionsis2.getConnection();
        List<Proveedor> Listapr = new ArrayList();
        String sql = "SELECT idproveedores, Proveedor, Tel, Vendedor FROM proveedores ORDER BY idproveedores DESC";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Proveedor pr = new Proveedor();
                pr.setIdproveedores(rs.getInt("idproveedores"));
                pr.setProveedor(rs.getString("Proveedor"));
                pr.setTel(rs.getString("Tel"));
                pr.setVendedor(rs.getString("Vendedor"));
                Listapr.add(pr);
            }
            
        } catch (SQLException e) {
            System.out.println("Error al ListarProveedor "+ e.toString());
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Listapr;
    }
    
    public boolean IngresoProveedor(Proveedor prove){
        cn= Unionsis2.getConnection();
        
        try {
            ps = cn.prepareStatement("insert into proveedores (Proveedor, Vendedor,  Tel) values (?, ?, ?)");
            ps.setString(1, prove.getProveedor());
            ps.setString(2, prove.getVendedor());
            ps.setString(3, prove.getTel());
            
            ps.executeUpdate();
                   JOptionPane.showMessageDialog(null, "¡Registro "+prove.getProveedor()+" exitoso!");                   
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Ingreso de Proveedor, " + e);
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean EliminarProveedor(Proveedor prove){    
        cn= Unionsis2.getConnection();
        try {
            
            
             ps = cn.prepareStatement("delete from proveedores where idproveedores=?");
                ps.setInt(1, prove.getIdproveedores());
                ps.execute();       
                
                JOptionPane.showMessageDialog(null, "¡Proveedor "+prove.getProveedor()+" eliminado correctamente!");
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la eliminación del proveedor!");
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean EditarProducto(Proveedor prove){
              cn= Unionsis2.getConnection();
        try {
            ps = cn.prepareStatement("update proveedores set Proveedor=?, Vendedor=?, Tel=? where idproveedores=?");
            ps.setString(1, prove.getProveedor());
            ps.setString(2, prove.getVendedor());
            ps.setString(3, prove.getTel());
            ps.setInt(4, prove.getIdproveedores());
            ps.executeUpdate();
            
            
                JOptionPane.showMessageDialog(null, "¡Producto "+prove.getProveedor()+" editado correctamente!");
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la modificación!");
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    

     public Proveedor BuscarProveedor(String idproveedores){
         Proveedor prove= new Proveedor();
    String sql= "select * from proveedores where idproveedores=?";
    cn= Unionsis2.getConnection();
        try {

            ps=cn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(idproveedores));
            rs= ps.executeQuery();
            if(rs.next()){
                prove.setProveedor(rs.getString("Proveedor"));
                prove.setVendedor(rs.getString("Vendedor"));
                prove.setTel(rs.getString("Tel"));
                prove.setIdproveedores(rs.getInt("idproveedores"));
            }else{
                JOptionPane.showMessageDialog(null, "¡El proveedor no existe!");
            }
        } catch (SQLException e) {
            System.err.println("¡Error en consulta de Proveedor!"+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return prove;
    }
    
}
