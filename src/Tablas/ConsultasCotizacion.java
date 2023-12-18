/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Modelo.Cotizaciones;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author Masaldoter
 */
public class ConsultasCotizacion extends ConexionesSQL{
    
    public String Fecha(){
        Date fech = new Date();
        String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fecha = Fechas.format(fech);
        
        return fecha;
    }
    
    /*public List ListarCotizacionesPorEstado(JComboBox Seleccion, String Parametro1, String Parametro2){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        /*Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/editar.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);
        
        btn2.setIcon(ro2);*/
       /* btn1.setBackground(new Color(0,195,0));
        btn1.setText("DETALLES");
        List<Cotizaciones> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="";
        switch (Seleccion.getSelectedIndex()) {
            case 0:
                Filtro="VIGENTE";
                break;
            case 1:
                Filtro="VENCIDO";
                break;
            case 2:
                Filtro="REALIZADO";
                break;   
            case 3:
                Filtro="ANULADO";
                break;        
            default:
                break;
        }
        try {
            Cotizaciones co;
             ps = cn.prepareStatement("SELECT cotizacion.idCotizacion, cotizacion.TotalProductos, cotizacion.TotalCotizacion, cotizacion.NoCotizacion,"
                     + " cotizacion.FechaRealizada, cotizacion.FechaVencimiento, cotizacion.EstadoCotizacion, cotizacion.NitCliente, cotizacion.NombreCliente, " +
"tablaA2.Nombre AS NombreUsuario " +
"FROM cotizacion " +            
"INNER JOIN login1 as tablaA1 ON (cotizacion.IdUsuario = tablaA1.idlogin1) " +
"INNER JOIN login1 as tablaA2 ON (cotizacion.NombreUsuario = tablaA2.idlogin1) WHERE cotizacion.EstadoCotizacion= '"+Filtro+"' ORDER BY cotizacion.idCotizacion DESC");
            rs = ps.executeQuery();

            while (rs.next()) {
                co = new Cotizaciones();
                co.setIdCotizacion(rs.getInt("cotizacion.idCotizacion"));
                co.setNombreClienteString(rs.getString("cotizacion.NombreCliente"));
                co.setNitClienteString(rs.getString("cotizacion.NitCliente"));
                co.setTotalProductos(rs.getFloat("cotizacion.TotalProductos"));
                co.setTotalCotizacion(rs.getFloat("cotizacion.TotalCotizacion"));
                co.setNoCotizacion(rs.getString("cotizacion.NoCotizacion"));
                co.setFechaRealizada(rs.getString("cotizacion.FechaRealizada"));
                co.setFechaVencimiento(rs.getString("cotizacion.FechaVencimiento"));
                co.setNombreUsuarioString(rs.getString("NombreUsuario"));
                co.setEstadoCotizacion(rs.getString("cotizacion.EstadoCotizacion"));
                co.setBtnDetalles(btn1);
               Listapro.add(co);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasDiarias, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }*/
    
    public List ListarCotizaciones(JComboBox Seleccion, int TipoBusqueda, String Parametro1, String Parametro2){
        String Sql = "";
        String Filtro_Estado = "";
        String Filtro_Estado2 = "";
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");
        
        
         btn1.setBackground(new Color(0, 195, 0));
        btn1.setText("DETALLES");
        List<Cotizaciones> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps = null;
        rs = null;
        String Filtro = "";
        switch (Seleccion.getSelectedIndex()) {
            case 0:
                Filtro = "VIGENTE";
                Filtro_Estado = "WHERE cotizacion.EstadoCotizacion= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE cotizacion.EstadoCotizacion= '" + Filtro + "' ";
                break;
            case 1:
                Filtro = "REALIZADO";
                Filtro_Estado = "WHERE cotizacion.EstadoCotizacion= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE cotizacion.EstadoCotizacion= '" + Filtro + "' ";
                break;
            case 2:
                Filtro = "ANULADO";
                Filtro_Estado = "WHERE cotizacion.EstadoCotizacion= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE cotizacion.EstadoCotizacion= '" + Filtro + "' ";
                break;
            case 3:
                Filtro = "TODOS";
                Filtro_Estado = "WHERE";
                Filtro_Estado2 = "";
                break;    
            default:
                break;
        }

        
        if (TipoBusqueda == 1) {
            Sql = "SELECT cotizacion.idCotizacion, cotizacion.TotalProductos, cotizacion.TotalCotizacion, cotizacion.NoCotizacion,"
                    + " cotizacion.FechaRealizada, cotizacion.HoraRealizada, cotizacion.EstadoCotizacion, cotizacion.NitCliente, cotizacion.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, cotizacion.FechaVencimiento "
                    + "FROM cotizacion "
                    + "INNER JOIN login1 as tablaA1 ON (cotizacion.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (cotizacion.IdUsuario = tablaA2.idlogin1) "
                    +  Filtro_Estado2 + " "
                    + "ORDER BY cotizacion.idCotizacion DESC";
        }else if(TipoBusqueda == 2){
            Sql = "SELECT cotizacion.idCotizacion, cotizacion.TotalProductos, cotizacion.TotalCotizacion, cotizacion.NoCotizacion,"
                    + " cotizacion.FechaRealizada, cotizacion.HoraRealizada, cotizacion.EstadoCotizacion, cotizacion.NitCliente, cotizacion.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, cotizacion.FechaVencimiento"
                    + "FROM cotizacion "
                    + "INNER JOIN login1 as tablaA1 ON (cotizacion.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (cotizacion.IdUsuario = tablaA2.idlogin1) "
                    +  Filtro_Estado + " (cotizacion.NoCotizacion="+Parametro1+ ") ORDER BY cotizacion.idCotizacion DESC";
        }else if(TipoBusqueda == 3){
            Sql = "SELECT cotizacion.idCotizacion, cotizacion.TotalProductos, cotizacion.TotalCotizacion, cotizacion.NoCotizacion,"
                    + " cotizacion.FechaRealizada, cotizacion.HoraRealizada, cotizacion.EstadoCotizacion, cotizacion.NitCliente, cotizacion.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, cotizacion.FechaVencimiento "
                    + "FROM cotizacion "
                    + "INNER JOIN login1 as tablaA1 ON (cotizacion.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (cotizacion.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado + " (cotizacion.NitCliente LIKE '%"+Parametro1+"%' OR cotizacion.NombreCliente LIKE '%"+Parametro1+"%')"
                    + " ORDER BY cotizacion.idCotizacion DESC";
        } else if (TipoBusqueda == 4) {
            SimpleDateFormat formatoEntrada = new SimpleDateFormat("d MMM yyyy");
            SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha1_String = null;
            Date fecha_2_String = null;
            String fechaFormateada = null, fechaFormateada2 = null;
            try {
                fecha1_String = formatoEntrada.parse(Parametro1); // Convertir la cadena a Date
                fechaFormateada = formatoSalida.format(fecha1_String); // Formatear la fecha a "YYYY-MM-dd"
                fecha_2_String = formatoEntrada.parse(Parametro2); // Convertir la cadena a Date
                fechaFormateada2 = formatoSalida.format(fecha_2_String); // Formatear la fecha a "YYYY-MM-dd"
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println(fechaFormateada);
            System.out.println(fechaFormateada2);
            Sql = "SELECT cotizacion.idCotizacion, cotizacion.TotalProductos, cotizacion.TotalCotizacion, cotizacion.NoCotizacion,"
                    + " cotizacion.FechaRealizada, cotizacion.HoraRealizada, cotizacion.EstadoCotizacion, cotizacion.NitCliente, cotizacion.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, cotizacion.FechaVencimiento "
                    + "FROM cotizacion "
                    + "INNER JOIN login1 as tablaA1 ON (cotizacion.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (cotizacion.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado + " cotizacion.FechaRealizada BETWEEN '"+fechaFormateada+"' AND '"+fechaFormateada2+"'"   
                    + " ORDER BY cotizacion.idCotizacion DESC";
        }
        try {
                Cotizaciones co;
             ps = cn.prepareStatement(Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                co = new Cotizaciones();
                co.setIdCotizacion(rs.getInt("cotizacion.idCotizacion"));
                co.setNombreClienteString(rs.getString("cotizacion.NombreCliente"));
                co.setNitClienteString(rs.getString("cotizacion.NitCliente"));
                co.setTotalProductos(rs.getFloat("cotizacion.TotalProductos"));
                co.setTotalCotizacion(rs.getFloat("cotizacion.TotalCotizacion"));
                co.setNoCotizacion(rs.getString("cotizacion.NoCotizacion"));
                co.setFechaRealizada(rs.getString("cotizacion.FechaRealizada"));
                co.setHoraRealizada(rs.getString("cotizacion.HoraRealizada"));
                co.setFechaVencimiento(rs.getString("cotizacion.FechaVencimiento"));
                co.setNombreUsuarioString(rs.getString("NombreUsuario"));
                co.setNombreUsuarioModificoString(rs.getString("UsuarioModifico"));
                co.setEstadoCotizacion(rs.getString("cotizacion.EstadoCotizacion"));
                co.setBtnDetalles(btn1);
               Listapro.add(co);
            }

        } catch (SQLException e) {
            System.err.println("Error Consultas Cotizaciones, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
    
    public List ListarCotizaciones_POR_PRODUCTO(String Parametro1){
        String Sql = "";
        String Filtro_Estado = "";
        String Filtro_Estado2 = "";
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");
        
        
         btn1.setBackground(new Color(0, 195, 0));
        btn1.setText("DETALLES");
        List<Cotizaciones> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps = null;
        rs = null;
            Sql = "SELECT cotizacion.idCotizacion, cotizacion.TotalProductos, cotizacion.TotalCotizacion, cotizacion.NoCotizacion,"
                    + " cotizacion.FechaRealizada, cotizacion.HoraRealizada, cotizacion.EstadoCotizacion, cotizacion.NitCliente, cotizacion.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, cotizacion.FechaVencimiento "
                    + "FROM cotizacion "
                    + "INNER JOIN login1 as tablaA1 ON (cotizacion.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (cotizacion.IdUsuario = tablaA2.idlogin1) "
                    +   " JOIN detalle_cotizacion as dc ON (cotizacion.NoCotizacion = dc.NoCotizacion) WHERE dc.NombreProducto LIKE '%"+Parametro1+"%' "
                    + "ORDER BY cotizacion.idCotizacion DESC";
        
        try {
                Cotizaciones co;
             ps = cn.prepareStatement(Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                co = new Cotizaciones();
                co.setIdCotizacion(rs.getInt("cotizacion.idCotizacion"));
                co.setNombreClienteString(rs.getString("cotizacion.NombreCliente"));
                co.setNitClienteString(rs.getString("cotizacion.NitCliente"));
                co.setTotalProductos(rs.getFloat("cotizacion.TotalProductos"));
                co.setTotalCotizacion(rs.getFloat("cotizacion.TotalCotizacion"));
                co.setNoCotizacion(rs.getString("cotizacion.NoCotizacion"));
                co.setFechaRealizada(rs.getString("cotizacion.FechaRealizada"));
                co.setHoraRealizada(rs.getString("cotizacion.HoraRealizada"));
                co.setFechaVencimiento(rs.getString("cotizacion.FechaVencimiento"));
                co.setNombreUsuarioString(rs.getString("NombreUsuario"));
                co.setNombreUsuarioModificoString(rs.getString("UsuarioModifico"));
                co.setEstadoCotizacion(rs.getString("cotizacion.EstadoCotizacion"));
                co.setBtnDetalles(btn1);
               Listapro.add(co);
            }

        } catch (SQLException e) {
            System.err.println("Error Consultas Cotizaciones, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
}
