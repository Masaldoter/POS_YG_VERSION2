/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Modelo.Traslados;
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
public class ConsultasTraslados extends ConexionesSQL {

    public List ListarTrasladosPorEstado(JComboBox Seleccion, int TipoBusqueda, String Parametro1, String Parametro2) {
        String Sql = "";
        String Filtro_Estado = "";
        String Filtro_Estado2 = "";
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        btn1.setBackground(new Color(0, 195, 0));
        btn1.setText("DETALLES");
        List<Traslados> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps = null;
        rs = null;
        String Filtro = "";
        switch (Seleccion.getSelectedIndex()) {
            case 0:
                Filtro = "VIGENTE";
                Filtro_Estado = "WHERE traslados.EstadoTraslado= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE traslados.EstadoTraslado= '" + Filtro + "' ";
                break;
            case 1:
                Filtro = "REALIZADO";
                Filtro_Estado = "WHERE traslados.EstadoTraslado= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE traslados.EstadoTraslado= '" + Filtro + "' ";
                break;
            case 2:
                Filtro = "ANULADO";
                Filtro_Estado = "WHERE traslados.EstadoTraslado= '" + Filtro + "' AND";
                Filtro_Estado2 = "WHERE traslados.EstadoTraslado= '" + Filtro + "' ";
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
            Sql = "SELECT traslados.idtraslados, traslados.TotalProductos, traslados.TotalTraslado, traslados.NoTraslado,"
                    + " traslados.FechaRealizada, traslados.EstadoTraslado, traslados.NitCliente, traslados.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, traslados.FechaModifico, traslados.HoraModifico "
                    + "FROM traslados "
                    + "INNER JOIN login1 as tablaA1 ON (traslados.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (traslados.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado2 + " "
                    + "ORDER BY traslados.idtraslados DESC";
        } else if (TipoBusqueda == 2) {
            Sql = "SELECT traslados.idtraslados, traslados.TotalProductos, traslados.TotalTraslado, traslados.NoTraslado,"
                    + " traslados.FechaRealizada, traslados.EstadoTraslado, traslados.NitCliente, traslados.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, traslados.FechaModifico, traslados.HoraModifico "
                    + "FROM traslados "
                    + "INNER JOIN login1 as tablaA1 ON (traslados.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (traslados.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado + " (traslados.NoTraslado=" + Parametro1 + ") ORDER BY traslados.idtraslados DESC";
        } else if (TipoBusqueda == 3) {
            Sql = "SELECT traslados.idtraslados, traslados.TotalProductos, traslados.TotalTraslado, traslados.NoTraslado,"
                    + " traslados.FechaRealizada, traslados.EstadoTraslado, traslados.NitCliente, traslados.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, traslados.FechaModifico, traslados.HoraModifico "
                    + "FROM traslados "
                    + "INNER JOIN login1 as tablaA1 ON (traslados.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (traslados.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado + " (traslados.NitCliente LIKE '%" + Parametro1 + "%' OR traslados.NombreCliente LIKE '%" + Parametro1 + "%')"
                    + " ORDER BY traslados.idtraslados DESC";
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
            }
            System.out.println(fechaFormateada);
            System.out.println(fechaFormateada2);
            Sql = "SELECT traslados.idtraslados, traslados.TotalProductos, traslados.TotalTraslado, traslados.NoTraslado,"
                    + " traslados.FechaRealizada, traslados.EstadoTraslado, traslados.NitCliente, traslados.NombreCliente, "
                    + "tablaA2.Nombre AS NombreUsuario, tablaA1.Nombre AS UsuarioModifico, traslados.FechaModifico, traslados.HoraModifico "
                    + "FROM traslados "
                    + "INNER JOIN login1 as tablaA1 ON (traslados.UsuarioModifico = tablaA1.idlogin1) "
                    + "INNER JOIN login1 as tablaA2 ON (traslados.IdUsuario = tablaA2.idlogin1) "
                    + Filtro_Estado + " traslados.FechaRealizada BETWEEN '" + fechaFormateada + "' AND '" + fechaFormateada2 + "'"
                    + " ORDER BY traslados.idtraslados DESC";
        }
        try {
            Traslados vales;
            ps = cn.prepareStatement(Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                vales = new Traslados();
                vales.setIdTraslado(rs.getInt("traslados.idtraslados"));
                vales.setNombreClienteString(rs.getString("traslados.NombreCliente"));
                vales.setNitClienteString(rs.getString("traslados.NitCliente"));
                vales.setTotalProductos(rs.getFloat("traslados.TotalProductos"));
                vales.setTotal_Traslado(rs.getFloat("traslados.TotalTraslado"));
                vales.setNoTraslado(rs.getString("traslados.NoTraslado"));
                vales.setFechaRealizada(rs.getString("traslados.FechaRealizada"));
                vales.setFechaModifico(rs.getString("traslados.FechaModifico"));
                vales.setHoraModifico(rs.getString("traslados.HoraModifico"));
                vales.setNombreUsuarioString(rs.getString("NombreUsuario"));
                vales.setNombreUsuarioModificoString(rs.getString("UsuarioModifico"));
                vales.setEstadoTraslado(rs.getString("traslados.EstadoTraslado"));
                vales.setBtnDetalles(btn1);
                Listapro.add(vales);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarTrasladosPorEstado, " + e);
        } finally {
            RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
}
