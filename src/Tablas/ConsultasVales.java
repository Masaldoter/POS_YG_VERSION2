/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Modelo.Cotizaciones;
import Modelo.Vales;
import java.awt.Color;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author Masaldoter
 */
public class ConsultasVales extends ConexionesSQL{
    
    
    public List ListarValesPorEstado(JComboBox Seleccion, int TipoBusqueda, String Parametro1, String Parametro2){
        String Sql = "";
        String Filtro_Estado = "";
        String Filtro_Estado2 = "";
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");
        
        
         btn1.setBackground(new Color(0, 195, 0));
        btn1.setText("DETALLES");
        List<Vales> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps = null;
        rs = null;
        String Filtro = "";
        switch (Seleccion.getSelectedIndex()) {
            case 0:
                Filtro = "VIGENTE";
                Filtro_Estado = "WHERE vale_v1.EstadoVale= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE vale_v1.EstadoVale= '" + Filtro + "' ";
                break;
            case 1:
                Filtro = "REALIZADO";
                Filtro_Estado = "WHERE vale_v1.EstadoVale= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE vale_v1.EstadoVale= '" + Filtro + "' ";
                break;
            case 2:
                Filtro = "ANULADO";
                Filtro_Estado = "WHERE vale_v1.EstadoVale= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE vale_v1.EstadoVale= '" + Filtro + "' ";
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
            Sql = "SELECT vale_v1.idvale_v1, vale_v1.TotalProductos, vale_v1.TotalVale, vale_v1.NoVale,"
                    + " vale_v1.FechaRealizada, vale_v1.HoraRealizada, vale_v1.EstadoVale, vale_v1.NitCliente, vale_v1.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, vale_v1.FechaModifico, vale_v1.HoraModifico "
                    + "FROM vale_v1 "
                    + "INNER JOIN login1 as tablaA1 ON (vale_v1.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (vale_v1.IdUsuario = tablaA2.idlogin1) "
                    +  Filtro_Estado2 + " "
                    + "ORDER BY vale_v1.idvale_v1 DESC";
        }else if(TipoBusqueda == 2){
            Sql = "SELECT vale_v1.idvale_v1, vale_v1.TotalProductos, vale_v1.TotalVale, vale_v1.NoVale,"
                    + " vale_v1.FechaRealizada, vale_v1.HoraRealizada, vale_v1.EstadoVale, vale_v1.NitCliente, vale_v1.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, vale_v1.FechaModifico, vale_v1.HoraModifico "
                    + "FROM vale_v1 "
                    + "INNER JOIN login1 as tablaA1 ON (vale_v1.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (vale_v1.IdUsuario = tablaA2.idlogin1) "
                    +  Filtro_Estado + " (vale_v1.NoVale="+Parametro1+ ") ORDER BY vale_v1.idvale_v1 DESC";
        }else if(TipoBusqueda == 3){
            Sql = "SELECT vale_v1.idvale_v1, vale_v1.TotalProductos, vale_v1.TotalVale, vale_v1.NoVale,"
                    + " vale_v1.FechaRealizada, vale_v1.HoraRealizada, vale_v1.EstadoVale, vale_v1.NitCliente, vale_v1.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, vale_v1.FechaModifico, vale_v1.HoraModifico "
                    + "FROM vale_v1 "
                    + "INNER JOIN login1 as tablaA1 ON (vale_v1.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (vale_v1.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado + " (vale_v1.NitCliente LIKE '%"+Parametro1+"%' OR vale_v1.NombreCliente LIKE '%"+Parametro1+"%')"
                    + " ORDER BY vale_v1.idvale_v1 DESC";
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
            Sql = "SELECT vale_v1.idvale_v1, vale_v1.TotalProductos, vale_v1.TotalVale, vale_v1.NoVale,"
                    + " vale_v1.FechaRealizada, vale_v1.HoraRealizada, vale_v1.EstadoVale, vale_v1.NitCliente, vale_v1.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, vale_v1.FechaModifico, vale_v1.HoraModifico "
                    + "FROM vale_v1 "
                    + "INNER JOIN login1 as tablaA1 ON (vale_v1.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (vale_v1.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado + " vale_v1.FechaRealizada BETWEEN '"+fechaFormateada+"' AND '"+fechaFormateada2+"'"   
                    + " ORDER BY vale_v1.idvale_v1 DESC";
        }
        try {
            Vales vales;
             ps = cn.prepareStatement(Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                vales = new Vales();
                vales.setIdvale_v1(rs.getInt("vale_v1.idvale_v1"));
                vales.setNombreClienteString(rs.getString("vale_v1.NombreCliente"));
                vales.setNitClienteString(rs.getString("vale_v1.NitCliente"));
                vales.setTotalProductos(rs.getFloat("vale_v1.TotalProductos"));
                vales.setTotal_Vale(rs.getFloat("vale_v1.TotalVale"));
                vales.setNoVale(rs.getString("vale_v1.NoVale"));
                vales.setFechaRealizada(rs.getString("vale_v1.FechaRealizada"));
                vales.setHoraRealizada(rs.getString("vale_v1.HoraRealizada"));
                vales.setFechaModifico(rs.getString("vale_v1.FechaModifico"));
                vales.setHoraModifico(rs.getString("vale_v1.HoraModifico"));
                vales.setNombreUsuarioString(rs.getString("NombreUsuario"));
                vales.setNombreUsuarioModificoString(rs.getString("UsuarioModifico"));
                vales.setEstadoVale(rs.getString("vale_v1.EstadoVale"));
                vales.setBtnDetalles(btn1);
               Listapro.add(vales);
            }

        } catch (SQLException e) {
            System.err.println("Error ConsultasVales, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
}
