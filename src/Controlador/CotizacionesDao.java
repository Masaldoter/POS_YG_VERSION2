
package Controlador;

import CLASES_GLOBALES.METODOS_GLOBALES;
import Modelo.Cotizaciones;
import Modelo.DetalleCotizaciones;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CotizacionesDao extends ConexionesSQL{
    
    public String numeroserieCotizaciones(){
            ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
           String seriea="";
           String sql="SELECT MAX(NumeroCotizacion) FROM seriescotizacion;";
            try {
                ps=cn.prepareStatement(sql);
                rs=ps.executeQuery();
                
                if(rs.next()){
                    seriea= rs.getString(1);
                }
                
            } catch (SQLException e) {
                System.out.println("Error en numero de numeroserieCotizaciones, "+e);
            }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
            return seriea;
        }
    
    public void numeroserieIncrementarCotizacion(int NumeroFactura){
           
           int increment = NumeroFactura;
            increment = increment + 1;
            
           String sql="INSERT INTO seriescotizacion (NumeroCotizacion) VALUES(?)";
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
                System.out.println("Error en numero de numeroserieIncrementarFactura, "+e);
            }finally{
                PsClose(ps);
            ConnectionClose(cn);
        }
        }
    
    public static String EstablecerTiempo(){
        Date fech = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fech); // Configuramos la fecha que se recibe
      calendar.add(Calendar.DAY_OF_YEAR, 15);  // numero de horas a añadir, o restar en caso de horas<0
      String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fechas = Fechas.format(calendar.getTime());
      return fechas; // Devuelve el objeto Date con las nuevas horas añadidas
 }
    
    public boolean RegistraCotizacion(Cotizaciones Datos) {
        boolean Resultado=true;
        ps = null;
        cn = conexion.getInstancia().getConnection();

        String sql = "INSERT INTO cotizacion(NitCliente, NombreCliente, TotalProductos, TotalCotizacion, "
                + "TotalLetras, NoCotizacion, FechaRealizada, HoraRealizada, FechaVencimiento, IdUsuario, "
                + "NombreUsuario, EstadoCotizacion, UsuarioModifico, FechaModifico, HoraModifico, Observacion) VALUES  "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, Datos.getNitCliente());
            ps.setString(2, Datos.getNombreCliente());
            ps.setFloat(3, Datos.getTotalProductos());
            ps.setFloat(4, Datos.getTotalCotizacion());
            ps.setString(5, Datos.getTotalLetras());
            ps.setString(6, Datos.getNoCotizacion());
            ps.setString(7, METODOS_GLOBALES.Fecha());
            ps.setString(8, METODOS_GLOBALES.Hora());
            ps.setString(9, EstablecerTiempo());
            ps.setInt(10, Datos.getIdUsuario());
            ps.setInt(11, Datos.getNombreUsuario());
            ps.setString(12, "VIGENTE");
            ps.setInt(13, Datos.getNombreUsuario());
            ps.setString(14, METODOS_GLOBALES.Fecha());
            ps.setString(15, METODOS_GLOBALES.Hora());
            ps.setString(16,Datos.getObservacion());
            ps.execute();
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("¡PROCESO EXITOSO!", "SE REGISTRO CORRECTAMENTE LA COTIZACIÓN "+Datos.getNoCotizacion() ,DesktopNotify.SUCCESS, 9000L);
                
            
            
        } catch (SQLException e) {
            System.out.println(e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ÉRROR AL REGISTRAR COTIZACION", "" + e, DesktopNotify.ERROR, 10000L);
            Resultado = false;
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }

        return Resultado;
    }
    
    public boolean RegistraDetallesCotizacion(DetalleCotizaciones DC) {
        boolean Resultado=true;
        ps = null;
        cn = conexion.getInstancia().getConnection();

        String sql = "INSERT INTO  detalle_cotizacion (CodigoBarras, NombreProducto, CantidadProductos, Descuento, "
                + "PrecioUnitario, Total, ProductoRegistrado, Fecha, NoCotizacion) VALUES  "
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            ps.setString(9, DC.getNoCotizacion());
            ps.execute();
            
            
        } catch (SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ÉRROR AL REGISTRAR DETALLES DE COTIZACION", "" + e, DesktopNotify.ERROR, 10000L);
            Resultado = false;
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }

        return Resultado;
    }
    
    public int ModificarCotizacion(String Usuario, String NuevoEstadoCotizacion, String NumeroCotizacion){
        int Resultado = 0;
        ps = null;
        cn = conexion.getInstancia().getConnection();

        String sql = "UPDATE cotizacion SET EstadoCotizacion=?, UsuarioModifico=?, FechaModifico=?, HoraModifico=? WHERE NoCotizacion=?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, NuevoEstadoCotizacion);
            ps.setInt(2, Integer.parseInt(Usuario));
            ps.setString(3, METODOS_GLOBALES.Fecha());
            ps.setString(4, METODOS_GLOBALES.Hora());
            ps.setString(5, NumeroCotizacion);
            Resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ÉRROR AL REGISTRAR COTIZACION", "" + e, DesktopNotify.ERROR, 10000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Resultado;
    }
    
}
