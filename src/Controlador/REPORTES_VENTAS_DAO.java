/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexiones.ConexionesSQL;
import Conexiones.conexion;
import Modelo.REPORTES_VENTAS;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MASALDOTER_GT
 */
public class REPORTES_VENTAS_DAO extends ConexionesSQL {

    public List CONSULTAR_VENTAS_MENSUALES_INVENTAREADOS() {
        /*
        1: LISTAR
        2: CONSULTA POR CODIGO DE BARRAS NOMBRE Y DESCRIPCIÓN
        3: CONSULTA POR USUARIO
        4: CONSULTA POR MODULO
         */
        String SQL = "SELECT\n"
                + "    Anio,\n"
                + "    Mes,\n"
                + "    SUM(TotalPrecio) AS SumaTotalPrecio,\n"
                + "    SUM(TotalCosto) AS SumaTotalCosto,\n"
                + "    SUM(Ganancia) AS SumaTotalGanancia\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        YEAR(r.Fecha) AS Anio,\n"
                + "        MONTH(r.Fecha) AS Mes,\n"
                + "        d.Cantidad,\n"
                + "        SUM(d.Precio * d.Cantidad) AS TotalPrecio,\n"
                + "        SUM(p.Costo * d.Cantidad) AS TotalCosto,\n"
                + "        SUM((d.Precio * d.Cantidad) - (p.Costo * d.Cantidad)) AS Ganancia\n"
                + "    FROM detalle d\n"
                + "    INNER JOIN productos p ON d.CodigoBarras = p.CodigoBarras\n"
                + "    INNER JOIN registro r ON d.NoFactura = r.NoFactura\n"
                + "    WHERE d.ProductoRegistrado = 1\n"
                + "    GROUP BY YEAR(r.Fecha), MONTH(r.Fecha), d.Cantidad\n"
                + ") AS Subconsulta\n"
                + "GROUP BY Anio, Mes\n"
                + "ORDER BY Anio ASC, Mes ASC;";

        rs = null;
        ps = null;
        List<REPORTES_VENTAS> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            REPORTES_VENTAS Kd;
             ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Kd= new REPORTES_VENTAS();
                Kd.setAnio(rs.getString("Anio"));
                Kd.setMes(rs.getString("Mes"));
                Kd.setTotal_Precio(rs.getString("SumaTotalPrecio"));
                Kd.setTotal_Costos(rs.getString("SumaTotalCosto"));
                Kd.setTotal_Ganancia(rs.getString("SumaTotalGanancia"));
               Listapro.add(Kd);
            }

        } catch (SQLException e) {
            System.err.println("ERROR EN CONSULTAR_VENTAS_MENSUALES_INVENTAREADOS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }

    public List CONSULTAR_VENTAS_DIARIAS_INVENTAREADOS() {
        /*
        1: LISTAR
        2: CONSULTA POR CODIGO DE BARRAS NOMBRE Y DESCRIPCIÓN
        3: CONSULTA POR USUARIO
        4: CONSULTA POR MODULO
         */
        String SQL = "SELECT\n"
                + "    Dia,\n"
                + "    SUM(TotalPrecio) AS SumaTotalPrecio,\n"
                + "    SUM(TotalCosto) AS SumaTotalCosto,\n"
                + "    SUM(Ganancia) AS SumaTotalGanancia\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        DATE(r.Fecha) AS Dia,\n"
                + "        d.Cantidad,\n"
                + "        SUM(d.Precio * d.Cantidad) AS TotalPrecio,\n"
                + "        SUM(p.Costo * d.Cantidad) AS TotalCosto,\n"
                + "        SUM((d.Precio * d.Cantidad) - (p.Costo * d.Cantidad)) AS Ganancia\n"
                + "    FROM detalle d\n"
                + "    INNER JOIN productos p ON d.CodigoBarras = p.CodigoBarras\n"
                + "    INNER JOIN registro r ON d.NoFactura = r.NoFactura\n"
                + "    WHERE d.ProductoRegistrado = 1 \n"
                + "    GROUP BY Dia, d.Cantidad \n"
                + ") AS Subconsulta \n"
                + "GROUP BY Dia\n"
                + "ORDER BY Dia DESC";
        
        rs = null;
        ps = null;
        List<REPORTES_VENTAS> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            REPORTES_VENTAS Kd;
             ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Kd= new REPORTES_VENTAS();
                Kd.setFecha(rs.getString("Dia"));
                Kd.setTotal_Precio(rs.getString("SumaTotalPrecio"));
                Kd.setTotal_Costos(rs.getString("SumaTotalCosto"));
                Kd.setTotal_Ganancia(rs.getString("SumaTotalGanancia"));
               Listapro.add(Kd);
            }

        } catch (SQLException e) {
            System.err.println("ERROR EN CONSULTAR_VENTAS_DIARIAS_INVENTAREADOS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
    
    public List CONSULTAR_VENTAS_DIARIAS_NO_INVENTAREADOS() {
        /*
        1: LISTAR
        2: CONSULTA POR CODIGO DE BARRAS NOMBRE Y DESCRIPCIÓN
        3: CONSULTA POR USUARIO
        4: CONSULTA POR MODULO
         */
        String SQL = "SELECT\n"
                + "    Dia,\n"
                + "    SUM(CantidadTotal) AS CantidadTotal,\n"
                + "    SUM(TotalProductosPersonalizados) AS TotalProductosPersonalizados\n"
                + "FROM (\n"
                + "    SELECT\n"
                + "        DATE(r.Fecha) AS Dia,\n"
                + "        SUM(d.Cantidad) AS CantidadTotal,\n"
                + "        SUM(d.Precio * d.Cantidad) AS TotalProductosPersonalizados\n"
                + "    FROM detalle d\n"
                + "    INNER JOIN registro r ON d.NoFactura = r.NoFactura\n"
                + "    WHERE d.ProductoRegistrado = 0\n"
                + "    GROUP BY DATE(r.Fecha)\n"
                + ") AS Subconsulta\n"
                + "GROUP BY Dia\n"
                + "ORDER BY Dia DESC";

        rs = null;
        ps = null;
        List<REPORTES_VENTAS> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            REPORTES_VENTAS Kd;
             ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Kd= new REPORTES_VENTAS();
                Kd.setFecha(rs.getString("Dia"));
                Kd.setCantidadPersonalizados(rs.getString("CantidadTotal"));
                Kd.setTotalPersonalizados(rs.getString("TotalProductosPersonalizados"));
               Listapro.add(Kd);
            }

        } catch (SQLException e) {
            System.err.println("ERROR EN CONSULTAR_VENTAS_MENSUALES_INVENTAREADOS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
    
    public List CONSULTAR_VENTAS_MENSUALES_NO_INVENTAREADOS() {
        String SQL = "SELECT\n"
                + "    Anio,\n"
                + "    Mes,\n"
                + "    SUM(CantidadTotal) AS CantidadTotal,\n"
                + "    SUM(TotalProductosPersonalizados) AS TotalProductosPersonalizados\n"
                + "FROM (\n"
                + "    SELECT\n"
                + "        YEAR(r.Fecha) AS Anio,\n"
                + "        MONTH(r.Fecha) AS Mes,\n"
                + "        SUM(d.Cantidad) AS CantidadTotal,\n"
                + "        SUM(d.Precio * d.Cantidad) AS TotalProductosPersonalizados\n"
                + "    FROM detalle d\n"
                + "    INNER JOIN registro r ON d.NoFactura = r.NoFactura\n"
                + "    WHERE d.ProductoRegistrado = 0\n"
                + "    GROUP BY YEAR(r.Fecha), MONTH(r.Fecha)\n"
                + ") AS Subconsulta\n"
                + "GROUP BY Anio, Mes\n"
                + "ORDER BY Anio DESC, Mes DESC";

        rs = null;
        ps = null;
        List<REPORTES_VENTAS> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            REPORTES_VENTAS Kd;
             ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Kd= new REPORTES_VENTAS();
                Kd.setAnio(rs.getString("Anio"));
                Kd.setMes(rs.getString("Mes"));
                Kd.setCantidadPersonalizados(rs.getString("CantidadTotal"));
                Kd.setTotalPersonalizados(rs.getString("TotalProductosPersonalizados"));
               Listapro.add(Kd);
            }

        } catch (SQLException e) {
            System.err.println("ERROR EN CONSULTAR_VENTAS_MENSUALES_INVENTAREADOS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
}
