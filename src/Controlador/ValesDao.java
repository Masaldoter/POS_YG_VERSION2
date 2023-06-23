
package Controlador;

import CLASES_GLOBALES.METODOS_GLOBALES;
import Modelo.Vales;
import Modelo.DetalleVales;
import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.Unionsis2;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import Conexiones.conexion;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;

public class ValesDao extends ConexionesSQL{
    
    public String numeroserieVales(){
            ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
           String seriea="";
           String sql="SELECT MAX(NumeroVale) FROM seriesvales;";
            try {
                ps=cn.prepareStatement(sql);
                rs=ps.executeQuery();
                
                if(rs.next()){
                    seriea= rs.getString(1);
                }
                
            } catch (SQLException e) {
                System.out.println("Error en numero de numeroserieVales, "+e);
            }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
            return seriea;
        }
    
    public void numeroserieIncrementarVales(int NumeroFactura){
           
           int increment = NumeroFactura;
            increment = increment + 1;
            
           String sql="INSERT INTO seriesvales (NumeroVale) VALUES(?)";
            try {              
                ps = null;
                cn = Unionsis2.getConnection();
                ps=cn.prepareStatement(sql);
                ps.setInt(1, increment);
                boolean resultado = ps.execute();
                if(resultado == true){
                    System.out.println("SE AUMENTO UNO");
                }
                
            } catch (SQLException e) {
                System.out.println("Error en numero de numeroserieIncrementarVales, "+e);
            }finally{
                PsClose(ps);
            ConnectionClose(cn);
        }
        }
    
    
    public boolean RegistrarVales(Vales Datos) {
        boolean Resultado=true;
        ps = null;
        cn = conexion.getInstancia().getConnection();

        String sql = "INSERT INTO vale_v1(NitCliente, NombreCliente, TotalProductos, TotalVale, "
                + "TotalLetras, NoVale, FechaRealizada, HoraRealizada, IdUsuario, "
                + "NombreUsuario, EstadoVale, UsuarioModifico, FechaModifico, HoraModifico, Observacion) VALUES  "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, Datos.getNitCliente());
            ps.setString(2, Datos.getNombreCliente());
            ps.setFloat(3, Datos.getTotalProductos());
            ps.setFloat(4, Datos.getTotal_Vale());
            ps.setString(5, Datos.getTotalLetras());
            ps.setString(6, Datos.getNoVale());
            ps.setString(7, METODOS_GLOBALES.Fecha());
            ps.setString(8, METODOS_GLOBALES.Hora());
            ps.setInt(9, Datos.getIdUsuario());
            ps.setInt(10, Datos.getNombreUsuario());
            ps.setString(11, "VIGENTE");
            ps.setInt(12, Datos.getNombreUsuario());
            ps.setString(13, null);
            ps.setString(14, null);
            ps.setString(15,Datos.getObservacion());
            ps.execute();
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("¡PROCESO EXITOSO!", "SE REGISTRO CORRECTAMENTE EL VALE "+Datos.getNoVale() ,DesktopNotify.SUCCESS, 9000L);
                
            
            
        } catch (SQLException e) {
            System.out.println(e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ÉRROR AL REGISTRAR EL VALE", "" + e, DesktopNotify.ERROR, 10000L);
            Resultado = false;
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }

        return Resultado;
    }
    
    public boolean RegistraDetallesVales(DetalleVales DC) {
        boolean Resultado=true;
        ps = null;
        cn = conexion.getInstancia().getConnection();

        String sql = "INSERT INTO detalles_vales_v1 (CodigoBarras, NombreProducto, CantidadProductos, Descuento, "
                + "PrecioUnitario, Total, ProductoRegistrado, Fecha, NoVale, IdProducto) VALUES  "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, DC.getCodigoBarras());
            ps.setString(2, DC.getNombreProducto());
            ps.setFloat(3, DC.getCantidadProductos());
            ps.setFloat(4, DC.getDescuento());
            ps.setFloat(5, DC.getPrecioUnitario());
            ps.setFloat(6, DC.getTotal());
            ps.setInt(7, DC.getProductoRegistrado());
            ps.setString(8, METODOS_GLOBALES.Fecha());
            ps.setString(9, DC.getNoVale());
            ps.setInt(10, DC.getIdProducto());
            ps.execute();
            
            
        } catch (SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ÉRROR AL REGISTRAR DETALLES DE VALE", "" + e, DesktopNotify.ERROR, 10000L);
            Resultado = false;
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }

        return Resultado;
    }
    
    public int ModificarVale(String Usuario, String NuevoEstadoVale, String NumeroVale){
        int Resultado = 0;
        ps = null;
        cn = conexion.getInstancia().getConnection();

        String sql = "UPDATE vale_v1 SET EstadoVale=?, UsuarioModifico=?, FechaModifico=?, HoraModifico=? WHERE NoVale=?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, NuevoEstadoVale);
            ps.setInt(2, Integer.parseInt(Usuario));
            ps.setString(3, METODOS_GLOBALES.Fecha());
            ps.setString(4, METODOS_GLOBALES.Hora());
            ps.setString(5, NumeroVale);
            Resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ÉRROR AL MODIFICAR VALE", "" + e, DesktopNotify.ERROR, 10000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Resultado;
    }
    
}
