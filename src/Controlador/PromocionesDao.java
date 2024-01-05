
package Controlador;
import Conexiones.conexion;
import Modelo.Proveedor;
import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.Unionsis2;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import INTERFACES.CRUD;
import Modelo.Promociones;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/*
idpromociones int AI PK 
CodigoPromocion varchar(45) 
NombrePromocion varchar(45) 
PorcentajeDescuento float 
FechaInicio date 
FechaVencimiento date 
HoraRegistro time 
UsuarioRegistro int 
UsuarioModifico int 
FechaRegistro date 
Estado
*/
public class PromocionesDao extends ConexionesSQL implements CRUD<Promociones>{

    @Override
    public int OBTENER_ID() {
      
        return 0;
      
    }

    @Override
    public Boolean AGREGAR(Promociones objeto) {
        cn= Unionsis2.getConnection();
        
        try {
            ps = cn.prepareStatement("insert into promociones ("
                    + "CodigoPromocion, "
                    + "NombrePromocion,  "
                    + "PorcentajeDescuento,"
                    + "FechaInicio, FechaVencimiento, "
                    + "FechaRegistro, HoraRegistro, "
                    + "UsuarioRegistro, UsuarioModifico, "
                    + "Estado) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, objeto.getCodigoPromocion());
            ps.setString(2, objeto.getNombrePromocion());
            ps.setFloat(3, objeto.getPorcentajeDescuento());
            ps.setString(4, objeto.getFechaInicio());
            ps.setString(5, objeto.getFechaVencimiento());
            ps.setString(6, objeto.getFechaRegistro());
            ps.setString(7, objeto.getHoraRegistro());
            ps.setInt(8, objeto.getUsuarioRegistro());
            ps.setInt(9, objeto.getUsuarioModifico());
            ps.setString(10, objeto.getEstado());
            
            ps.executeUpdate();
                   JOptionPane.showMessageDialog(null, "¡REGISTRO "+objeto.getNombrePromocion()+" EXITOSO!");                   
            return true;
        } catch (SQLException e) {
            System.out.println("HUBO UN ERRIR AL AGREGAR EL REGISTRO DE PROMOCION, " + e);
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }

    @Override
    public Promociones BUSCAR_POR_ID(int id) {
          Promociones PROMOS= new Promociones();
    String sql= "select * from proveedores where idproveedores=?";
    cn= Unionsis2.getConnection();
        try {

            ps=cn.prepareStatement(sql);
            ps.setInt(1, id);
            rs= ps.executeQuery();
            if(rs.next()){
                PROMOS.setCodigoPromocion(rs.getString("Proveedor"));
                PROMOS.setNombrePromocion(rs.getString("Vendedor"));
                PROMOS.setPorcentajeDescuento(rs.getFloat("Tel"));
                PROMOS.setEstado(rs.getString("idproveedores"));
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
        return PROMOS;
    }

    @Override
    public Boolean EDITAR(Promociones objeto, int ID) {
         cn= Unionsis2.getConnection();
        try {
            ps = cn.prepareStatement("UPDATE promociones SET CodigoPromocion = ?, NombrePromocion = ?, "
                    + "PorcentajeDescuento = ?, FechaInicio = ?, FechaVencimiento = ?, HoraRegistro = ?, "
                    + "FechaRegistro = ?, UsuarioRegistro = ?, UsuarioModifico = ?, Estado = ? WHERE idpromociones = ?;");
            
            ps.setString(1, objeto.getCodigoPromocion());
        ps.setString(2, objeto.getNombrePromocion());
        ps.setFloat(3, objeto.getPorcentajeDescuento());
        ps.setString(4, objeto.getFechaInicio());
        ps.setString(5, objeto.getFechaVencimiento());
        ps.setString(6, objeto.getHoraRegistro());
        ps.setString(7, objeto.getFechaRegistro());
        ps.setInt(8, objeto.getUsuarioRegistro());
        ps.setInt(9, objeto.getUsuarioModifico());
        ps.setString(10, objeto.getEstado());
        
        // Agrega el ID de la promoción al final para la cláusula WHERE
        ps.setInt(11, objeto.getIdpromociones());

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "¡ACTUALIZACIÓN DE REGISTRO EXITOSA!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se encontró la promoción con ID: " + objeto.getIdpromociones());
            return false;
        }
                
        }catch (SQLException e) {
        System.out.println("HUBO UN ERROR AL EDITAR EL REGISTRO DE PROMOCION: " + e);
        return false;
    }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }

    @Override
    public Boolean DESACTIVAR(int id) {
      /*  cn= Unionsis2.getConnection();
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
        }*/
      /*  cn= Unionsis2.getConnection();
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
        }*/
      return null;
    }

    @Override
    public List<Promociones> LISTAR() {
    cn = Unionsis2.getConnection();
    List<Promociones> listaPromociones = new ArrayList<>();
    String sql = "SELECT idpromociones, CodigoPromocion, NombrePromocion, PorcentajeDescuento, FechaInicio, FechaVencimiento, Estado FROM promociones ORDER BY idpromociones DESC";
    
    try {
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {                
            Promociones promocion = new Promociones();
            promocion.setIdpromociones(rs.getInt("idpromociones"));
            promocion.setCodigoPromocion(rs.getString("CodigoPromocion"));
            promocion.setNombrePromocion(rs.getString("NombrePromocion"));
            promocion.setPorcentajeDescuento(rs.getFloat("PorcentajeDescuento"));
            promocion.setFechaInicio(rs.getString("FechaInicio"));
            promocion.setFechaVencimiento(rs.getString("FechaVencimiento"));
            promocion.setEstado(rs.getString("Estado"));
            
            listaPromociones.add(promocion);
        }
    } catch (SQLException e) {
        System.out.println("Error al listar promociones: " + e.toString());
    } finally {
        PsClose(ps);
        RsClose(rs);
        ConnectionClose(cn);
    }
    
    return listaPromociones;
}
    
    public Float Consulta_ComboBox(String Id) {

        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        Float Resultado = 0f;

        String sql = "SELECT PorcentajeDescuento FROM promociones WHERE idpromociones=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(Id));
            rs = ps.executeQuery();
            if (rs.next()) {
                Resultado = rs.getFloat("PorcentajeDescuento");
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Resultado;
    }
    
    public List ListarPromociones(){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        List<Promociones> Listapr = new ArrayList();
        // Agrega la primera línea adicional
    Promociones sinPromocion = new Promociones();
    sinPromocion.setIdpromociones(0); // Puedes ajustar este valor según tu lógica
    sinPromocion.setCodigoPromocion("--SIN PROMOCION--");
    Listapr.add(sinPromocion);
        String sql = "SELECT idpromociones, CodigoPromocion FROM promociones WHERE CURDATE() BETWEEN FechaInicio AND FechaVencimiento AND Estado = 'ACTIVO'"
                + " ORDER BY idpromociones DESC";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Promociones Cat = new Promociones();
                Cat.setIdpromociones(rs.getInt("idpromociones"));
                Cat.setCodigoPromocion(rs.getString("CodigoPromocion"));
                Listapr.add(Cat);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Listapr;
    }

    
}
