/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;
import Conexiones.ConexionesSQL;
import Conexiones.conexion;
import Modelo.Detalle2;
import Modelo.Vale;
import static Conexiones.ConexionesSQL.cn;
import Vales.DatosClienteVale;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaldoter
 */
public class ActualizarTablaVale extends ConexionesSQL{
    DatosClienteVale dcv = new DatosClienteVale();
    Vale Datos= new Vale();
    Detalle2 dt2= new Detalle2();
    
    public void ActualizarTablaValesPorEstado(JTable TablaVales) {
        
        cn = Unionsis2.getConnection();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVales.setModel(modeloTabla);
        String estado="No pagado";
        try {

            ps = cn.prepareStatement("select idVale, Cliente, Total, NoVale, Estado, HoraEmision, FechaEmision, Fecha2, Vendedor from vale where Estado=?");
            ps.setString(1, estado);

            rs = ps.executeQuery();

            modeloTabla.addColumn("Id Vale");
            modeloTabla.addColumn("Cliente");
            modeloTabla.addColumn("Total");
            modeloTabla.addColumn("NoVale");
            modeloTabla.addColumn("Estado");
            modeloTabla.addColumn("Hora Inicio");
            modeloTabla.addColumn("Fecha Inicio");
            modeloTabla.addColumn("Fecha de Pago");
            modeloTabla.addColumn("Vendedor");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchotabla[] = {5, 30, 30, 20, 30, 30, 20, 20, 10};

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVales.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }

            while (rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(conexionlocal);
        }
    }
    
    public void BusquedaVale(JTable TablaVales, Date date){
        cn = Unionsis2.getConnection();
            DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVales.setModel(modeloTabla);
        String strDateFormat = "dd-MMM-YYYY";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha = objSDF.format(date);
            try {

                ps = cn.prepareStatement("select idVale, Cliente, Total, NoVale, Estado, FechaEmision, Fecha2 from vale where Fecha LIKE '%" + fecha + "%'");

                rs = ps.executeQuery();

                modeloTabla.addColumn("idVale");
                modeloTabla.addColumn("Cliente");
                modeloTabla.addColumn("Total");
                modeloTabla.addColumn("NoVale");
                modeloTabla.addColumn("Estado");
                modeloTabla.addColumn("Fecha");
                modeloTabla.addColumn("Fecha2");
                while (rs.next()) {

                    Object fila[] = new Object[7];
                    for (int i = 0; i < 7; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }

                    modeloTabla.addRow(fila);

                }

            } catch (SQLException e) {
                System.err.println("Error, " + e);
            }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(conexionlocal);
        }
        }
    
    public void ActulaizarDetalleVale(JTable DetalleVale, String id) {
        cn = Unionsis2.getConnection();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        DetalleVale.setModel(modeloTabla);
        try {

            ps = cn.prepareStatement("select CodigoBarras, Cantidad, Nombre, Precio, Total, FechaEmision, Usuario, Ubicacion, ClienteCompro from detalle2 where NoVale=?");
            ps.setString(1, id);

            rs = ps.executeQuery();

            modeloTabla.addColumn("Codigo");
            modeloTabla.addColumn("Cantidad");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("P/Unitario");
            modeloTabla.addColumn("Total");
            modeloTabla.addColumn("Fecha de Compra");
            modeloTabla.addColumn("Vendedor");
            modeloTabla.addColumn("Úbicación");
            modeloTabla.addColumn("Comprado Por");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchotabla[] = {5, 30, 30, 30, 30, 15, 15, 10, 20};

            for (int i = 0; i < cantidadColumnas; i++) {
                DetalleVale.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }

            while (rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(conexionlocal);
        }
    } 
    
    public void ActualizarTablaValeVentaYValeYaExistente(JTable jTable1) {
        cn = Unionsis2.getConnection();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        jTable1.setModel(modeloTabla);
        String estado="No pagado";
        try {
            ps = cn.prepareStatement("select IdVale, Cliente, NoVale, Total, FechaEmision from vale where Estado=?");
            ps.setString(1, estado);
            rs = ps.executeQuery();

            modeloTabla.addColumn("Id");
            modeloTabla.addColumn("Cliente");
            modeloTabla.addColumn("NoVale");
            modeloTabla.addColumn("Total");
            modeloTabla.addColumn("Fecha");

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchotabla[] = {5, 200, 10, 15, 10};

            for (int i = 0; i < cantidadColumnas; i++) {
                jTable1.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }
            while (rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                modeloTabla.addRow(fila);

            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(conexionlocal);
        }
    }
    
    public void ActulaizarTablaVales2(JTable TablaVales) {
        cn = Unionsis2.getConnection();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVales.setModel(modeloTabla);
        String estado="Pagado";
        try {

            ps = cn.prepareStatement("select idVale, Cliente, Total, NoVale, Estado, FechaEmision, Fecha2, Vendedor, NoFactura from vale where Estado=?");
            ps.setString(1, estado);

            rs = ps.executeQuery();

            modeloTabla.addColumn("Id Vale");
            modeloTabla.addColumn("Cliente");
            modeloTabla.addColumn("Total");
            modeloTabla.addColumn("NoVale");
            modeloTabla.addColumn("Estado");
            modeloTabla.addColumn("Fecha Inicio");
            modeloTabla.addColumn("Fecha de Pago");
            modeloTabla.addColumn("Vendedor");
            modeloTabla.addColumn("N° Factura");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchotabla[] = {5, 30, 30, 30, 30, 20, 20, 10, 10};

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVales.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }

            while (rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(conexionlocal);
        }
    }
    
    
   
}
