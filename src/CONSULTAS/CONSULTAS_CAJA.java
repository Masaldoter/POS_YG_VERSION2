/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONSULTAS;

import CLASES_GLOBALES.METODOS_GLOBALES;
import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.Unionsis2;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import Conexiones.conexion;
import Modelo.CAJA;
import Modelo.Compras;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aldoy
 */
public class CONSULTAS_CAJA extends ConexionesSQL{
    
    public static CAJA CAJA_CONSULTA() {
        CAJA caja = null;
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "SELECT ESTADO_DE_CAJA, FECHA_HORA_APERTURA_CAJA, USUARIO_APERTURO_CAJA, USUARIO_CERRO_CAJA, ARQUEO_DE_CAJA from caja WHERE FECHA="+METODOS_GLOBALES.Fecha()+" ORDER BY idcaja DESC";
            try {
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    caja= new CAJA();
                    System.out.println(rs.getString("ESTADO_DE_CAJA"));
                    caja.setESTADO_DE_CAJA(rs.getString("ESTADO_DE_CAJA"));
                    caja.setFECHA_HORA_APERTURA_CAJA(rs.getString("FECHA_HORA_APERTURA_CAJA"));
                    caja.setUSUARIO_APERTURO_CAJA(rs.getInt("USUARIO_APERTURO_CAJA"));
                    caja.setUSUARIO_CERRO_CAJA(rs.getInt("USUARIO_CERRO_CAJA"));
                    caja.setARQUEO_DE_CAJA(rs.getString("ARQUEO_DE_CAJA"));
                }

        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return caja;
    }
    
    public static CAJA CAJA_CONSULTA_NUMERO(int NUMERO_CAJA) {
        CAJA caja = null;
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "SELECT ESTADO_DE_CAJA, Total_inicial_CAJA, Total_Compras_CAJA, Total_Ventas_CAJA, Total_Gastos_CAJA, Total_Efectivo_CAJA, Total_Transferencia_CAJA, Total_Cheque_CAJA,"
                + " Total_Tarjeta_CAJA, Total_Compartido_CAJA, ARQUEO_DE_CAJA "
                + "from caja "
                + "WHERE idcaja="+NUMERO_CAJA;
            try {
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    caja= new CAJA();
                    caja.setESTADO_DE_CAJA(rs.getString("ESTADO_DE_CAJA"));
                    caja.setTotal_inicial_CAJA(rs.getFloat("Total_inicial_CAJA"));
                    caja.setTotal_Compras_CAJA(rs.getFloat("Total_Compras_CAJA"));
                    caja.setTotal_Ventas_CAJA(rs.getFloat("Total_Ventas_CAJA"));
                    caja.setTotal_Gastos_CAJA(rs.getFloat("Total_Gastos_CAJA"));
                    caja.setTotal_Efectivo_CAJA(rs.getFloat("Total_Efectivo_CAJA"));
                    caja.setTotal_Transferencia_CAJA(rs.getFloat("Total_Transferencia_CAJA"));
                    caja.setTotal_Cheque_CAJA(rs.getFloat("Total_Cheque_CAJA"));
                    caja.setTotal_Tarjeta_CAJA(rs.getFloat("Total_Tarjeta_CAJA"));
                    caja.setTotal_Compartido_CAJA(rs.getFloat("Total_Compartido_CAJA"));
                    caja.setARQUEO_DE_CAJA(rs.getString("ARQUEO_DE_CAJA"));
                }

        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return caja;
    }
    
    public Float Total_Ventas(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(Total) from registro where Estado='"+Filtro+"' AND id_CAJA_registro="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(Total)");
            }

        } catch (SQLException e) {
            System.err.println("Error Total Ventas, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_Compras(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(TOTAL) from compras where TIPO_COMPRA='COMPRA' AND id_CAJA="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(TOTAL)");
            }

        } catch (SQLException e) {
            System.err.println("Error TOTAL EN COMPRAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_ENTRADAS(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(TOTAL) from compras where  TIPO_COMPRA='ENTRADA' AND id_CAJA="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(TOTAL)");
            }

        } catch (SQLException e) {
            System.err.println("Error TOTAL EN COMPRAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_SALIDAS(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(TOTAL) from compras where TIPO_COMPRA='SALIDA' AND id_CAJA="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(TOTAL)");
            }

        } catch (SQLException e) {
            System.err.println("Error TOTAL EN COMPRAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_GANANCIAS(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("SELECT "
                     + "SUM(TotalPrecio) AS SumaTotalPrecio, "
                     + "SUM(TotalCosto) AS SumaTotalCosto, SUM(Ganancia) AS SumaTotalGanancia "
                     + "FROM (SELECT d.Cantidad, "
                     + "SUM(d.Precio * d.Cantidad) AS TotalPrecio, "
                     + "SUM(p.Costo * d.Cantidad) AS TotalCosto, "
                     + "SUM((d.Precio * d.Cantidad) - (p.Costo * d.Cantidad)) AS Ganancia "
                     + "FROM detalle d "
                     + "INNER JOIN productos p ON d.CodigoBarras = p.CodigoBarras "
                     + "INNER JOIN registro r ON d.NoFactura = r.NoFactura "
                     + "WHERE d.ProductoRegistrado = 1 "
                     + "AND r.id_CAJA_registro = ? "
                     + "GROUP BY d.Cantidad) AS Subconsulta");
             ps.setInt(1, CAJA);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                Total_Final = rs.getFloat("SumaTotalGanancia");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_GANANCIAS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_COSTOS(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("SELECT "
                     + "SUM(TotalPrecio) AS SumaTotalPrecio,"
                     + "SUM(TotalCosto) AS SumaTotalCosto, SUM(Ganancia) AS SumaTotalGanancia "
                     + "FROM (SELECT d.Cantidad, "
                     + "SUM(d.Precio * d.Cantidad) AS TotalPrecio,"
                     + "SUM(p.Costo * d.Cantidad) AS TotalCosto,"
                     + "SUM((d.Precio * d.Cantidad) - (p.Costo * d.Cantidad)) AS Ganancia "
                     + "FROM detalle d "
                     + "INNER JOIN productos p ON d.CodigoBarras = p.CodigoBarras "
                     + "INNER JOIN registro r ON d.NoFactura = r.NoFactura "
                     + "WHERE d.ProductoRegistrado = 1 "
                     + "AND r.id_CAJA_registro = ? "
                     + "GROUP BY d.Cantidad) AS Subconsulta");
             ps.setInt(1, CAJA);
            rs = ps.executeQuery();
            if (rs.next()) {
                Total_Final = rs.getFloat("SumaTotalCosto");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_COSTOS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_CANTIDAD_PRODUCTOS_PERSONALIZADOS(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("SELECT SUM(d.Cantidad) AS CantidadTotal, "
                     + "SUM(d.Precio * d.Cantidad) AS TotalProductosPersonalizados "
                     + "FROM detalle d "
                     + "INNER JOIN registro r ON d.NoFactura = r.NoFactura "
                     + "WHERE d.ProductoRegistrado = 0 "
                     + "AND  r.id_CAJA_registro = ?");
             ps.setInt(1, CAJA);
            rs = ps.executeQuery();
            if (rs.next()) {
                Total_Final = rs.getFloat("CantidadTotal");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_COSTOS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_TOTAL_PRODUCTOS_PERSONALIZADOS(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("SELECT SUM(d.Cantidad) AS CantidadTotal, "
                     + "SUM(d.Precio * d.Cantidad) AS TotalProductosPersonalizados "
                     + "FROM detalle d "
                     + "INNER JOIN registro r ON d.NoFactura = r.NoFactura "
                     + "WHERE d.ProductoRegistrado = 0 "
                     + "AND  r.id_CAJA_registro = ?");
             ps.setInt(1, CAJA);
            rs = ps.executeQuery();
            if (rs.next()) {
                Total_Final = rs.getFloat("TotalProductosPersonalizados");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_COSTOS, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public List Total_ENTRADAS_SALIDAS_DETALLES(int CAJA, String PARAMETRO){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Compras c;
        List<Compras> Lista = new ArrayList();
        try {
             ps = cn.prepareStatement("select idcompras, TOTAL, DESCRIPCION_COMPRA, FECHA_HORA_COMPRA, USUARIO_REGISTRO from compras where TIPO_COMPRA='"+PARAMETRO+"' AND id_CAJA="+CAJA);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                c= new Compras();
                c.setIdCAJA(rs.getInt("idcompras"));
                c.setTOTAL_COMPRA(rs.getFloat("TOTAL"));
                c.setDESCRIPCION_COMPRAS(rs.getString("DESCRIPCION_COMPRA"));
                c.setFECHA_HORA_COMPRA(rs.getString("FECHA_HORA_COMPRA"));
                c.setUSUARIO_REGISTRO_COMPRA(rs.getInt("USUARIO_REGISTRO"));
                Lista.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Error TOTAL EN ENTRADAS, " + e);
        } finally {
            RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Lista;
    }
    
    public Float Total_Ventas_EFECTIVO(int CAJA){
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="FACTURADO";
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("select SUM(Total) from registro where  Estado='"+Filtro+"' AND FormaPago ='EFECTIVO' AND id_CAJA_registro="+CAJA);
            rs = ps.executeQuery();

            if (rs.next()) {
                Total_Final = rs.getFloat("SUM(Total)");
            }

        } catch (SQLException e) {
            System.err.println("Error Total Ventas, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_EFECTIVO(int CAJA) {
        cn = Unionsis2.getConnection();
        ps= null;
        rs = null;
        Float Total_Final = 0f;
        try {
            ps = cn.prepareStatement("SELECT SUM(Efectivo) AS Total "
                    + "FROM forma_pago "
                    + "INNER JOIN registro AS reg ON forma_pago.Numero = reg.NoFactura " +
            "WHERE reg.Estado = 'FACTURADO' and reg.id_CAJA_registro=?");
            ps.setInt(1, CAJA);
            rs = ps.executeQuery();

            if (rs.next()){
            Total_Final = rs.getFloat("Total");
            }

        } catch (SQLException e) {
            
            System.err.println("Error Total_EFECTIVO, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_CHEQUE(int CAJA) {
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
            ps = cn.prepareStatement("SELECT SUM(CHEQUE) AS Total "
                    + "FROM forma_pago "
                    + "INNER JOIN registro AS reg ON forma_pago.Numero = reg.NoFactura " +
            "WHERE reg.Estado = 'FACTURADO' and reg.id_CAJA_registro=?");
            ps.setInt(1, CAJA);
            rs = ps.executeQuery();
            if (rs.next()){
            Total_Final = rs.getFloat("Total");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_CHEQUE, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_TRANSFERENCIA(int CAJA) {
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
            ps = cn.prepareStatement("SELECT SUM(TRANSFERENCIA) AS Total "
                    + "FROM forma_pago "
                    + "INNER JOIN registro AS reg ON forma_pago.Numero = reg.NoFactura " +
            "WHERE reg.Estado = 'FACTURADO' and reg.id_CAJA_registro=?");
            ps.setInt(1, CAJA);
            rs = ps.executeQuery();
            if (rs.next()){
            Total_Final = rs.getFloat("Total");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_TRANSFERENCIA, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_TARJETA(int CAJA) {
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
            ps = cn.prepareStatement("SELECT SUM(TARJETA) AS Total "
                    + "FROM forma_pago "
                    + "INNER JOIN registro AS reg ON forma_pago.Numero = reg.NoFactura " +
            "WHERE reg.Estado = 'FACTURADO' and reg.id_CAJA_registro=?");
            ps.setInt(1, CAJA);
            rs = ps.executeQuery();
            if (rs.next()){
            Total_Final = rs.getFloat("Total");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_TARJETA, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_COMPARTIDO(int CAJA) {
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("SELECT SUM(Cambio) AS total_cambios FROM registro WHERE Estado='FACTURADO' AND id_CAJA_registro=?");
            ps.setInt(1, CAJA);
            rs = ps.executeQuery();

            if (rs.next()){
            Total_Final =rs.getFloat("total_cambios");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_COMPARTIDO, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
    
    public Float Total_VENTAS(int CAJA, String Tipo_Documento, String Estado) {
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        Float Total_Final = 0f;
        try {
             ps = cn.prepareStatement("SELECT SUM(CASE WHEN TipoDocumentoFel = ? AND Estado = ? THEN Total ELSE 0 END) AS TOTAL FROM registro WHERE Estado='FACTURADO' AND id_CAJA_registro=?");
            ps.setString(1, Tipo_Documento);
            ps.setString(2, Estado);
            ps.setInt(3, CAJA);
             rs = ps.executeQuery();

            if (rs.next()){
            Total_Final = rs.getFloat("TOTAL");
            }

        } catch (SQLException e) {
            System.err.println("Error Total_COMPARTIDO, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Total_Final;
    }
}
