/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Modelo.InterfazPrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Masaldoter
 */
public class REPORTESGRAFICOS extends ConexionesSQL {
    
    public void RegistrosGeneral(int Usuario, JPanel panel) {
    ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
    Date fecha = new Date();
    String strDateFormat = "YYYY-MM-dd";
    SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
    String fechaFormateada = objSDF.format(fecha);

    try {
        // Consulta SQL para obtener los datos
        String sql = "SELECT SUM(Total) AS TOTAL, EXTRACT(MONTH FROM fecha) AS MES, EXTRACT(YEAR FROM fecha) AS AÑO FROM registro WHERE Estado='FACTURADO' GROUP BY MES, AÑO ORDER BY AÑO, MES";
        ps = cn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        // Crear el conjunto de datos para el gráfico
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (rs.next()) {
            dataset.setValue(rs.getDouble("TOTAL"), "MES", rs.getString("MES") + "-" + rs.getString("AÑO"));
        }
        
        // Crear el gráfico de líneas
        JFreeChart chart = ChartFactory.createLineChart("VENTAS GENERALES", "FECHAS", "TOTALES", dataset, PlotOrientation.VERTICAL, true, true, false);
        
        // Rotar las etiquetas del eje X
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        
        // Crear el panel del gráfico y agregarlo al panel principal
        panel.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        panel.add(chartPanel, BorderLayout.CENTER);
        panel.validate();
    } catch (SQLException e) {
        System.out.println(e.toString());
    } finally {
        PsClose(ps);
        RsClose(rs);
        ConnectionClose(cn);
    }
}

    
    public void ProductosMasVendidos(JTable tablaProductos) {
    cn = Unionsis2.getConnection();
    DefaultTableModel modeloTabla = new DefaultTableModel();
    tablaProductos.setModel(modeloTabla);
    
    modeloTabla.addColumn("CÓDIGO");
    modeloTabla.addColumn("NOMBRE");
    modeloTabla.addColumn("CANTIDAD VENDIDA");
    
    try {
        ps = cn.prepareStatement("SELECT CodigoBarras, Nombre, SUM(CANTIDAD) AS VENDIDOS FROM detalle GROUP BY CodigoBarras, Nombre ORDER BY SUM(CANTIDAD) DESC LIMIT 0, 500");
        rs = ps.executeQuery();
        
        ResultSetMetaData rsMD = rs.getMetaData();
        int cantidadColumnas = rsMD.getColumnCount();
        int anchotabla[] = {5, 190, 10};
        
        for (int i = 0; i < cantidadColumnas; i++) {
            tablaProductos.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
        }
        
        while (rs.next()) {
            Object fila[] = new Object[cantidadColumnas];
            for (int i = 0; i < cantidadColumnas; i++) {
                fila[i] = rs.getObject(i + 1);
            }
            modeloTabla.addRow(fila);
        }
    } catch (SQLException e) {
        // Manejo de excepciones
    }
}

    
    public void AvisoDeStock(JTable tablaProductos) {
                cn = Unionsis2.getConnection();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaProductos.setModel(modeloTabla);
        try {

            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre, Cantidad FROM productos WHERE Cantidad <= 10 LIMIT 0,500");

            rs = ps.executeQuery();

            modeloTabla.addColumn("CÓDIGO");
            modeloTabla.addColumn("NOMBRE");
            modeloTabla.addColumn("CANTIDAD");

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchotabla[] = {5, 190, 10};

            for (int i = 0; i < cantidadColumnas; i++) {
                tablaProductos.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }
            while (rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                modeloTabla.addRow(fila);

            }

        } catch (SQLException e) {
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    public void Registros(int Usuario, JPanel panel){
                cn = Unionsis2.getConnection();
        Date fech = new Date();
        String strDateFormat = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha=objSDF.format(fech);
        
         try {
            String sql = "SELECT rg.Total,lg.Nombre AS Usuario , rg.Hora FROM registro AS rg INNER JOIN login1 As lg ON(rg.Usuario= lg.idlogin1) WHERE Usuario=? AND Fecha=? LIMIT 10";
             ps = cn.prepareStatement(sql);
             ps.setInt(1, Usuario);
             ps.setString(2, fecha);
             rs = ps.executeQuery();
             //DefaultPieDataset dateset = new DefaultPieDataset();
             DefaultCategoryDataset Datos = new DefaultCategoryDataset();
             while (rs.next()) {
                 //dateset.setValue(rs.getString("Total"), rs.getFloat("Total"));
                 Datos.setValue(rs.getDouble("Total"), rs.getString("Usuario"), rs.getString("Hora"));
             }
             JFreeChart Barras = ChartFactory.createLineChart("Ventas del Día", "Usuario", "Ventas", Datos, PlotOrientation.VERTICAL, true, true, false);
             //JFreeChart jf = ChartFactory.createPieChart("VENTAS DEL DÍA", (PieDataset) Datos);

             // Rotar las etiquetas del eje X
             CategoryPlot plot = Barras.getCategoryPlot();
             CategoryAxis domainAxis = plot.getDomainAxis();
             domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

             //DISEÑO
            //
            
             panel.setLayout(new java.awt.BorderLayout());
             ChartPanel CP = new ChartPanel(Barras);
             panel.add(CP, BorderLayout.CENTER);
             panel.validate();
             
             
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    /*public void RegistrosGeneral(int Usuario, JPanel panel){
        ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        Date fech = new Date();
        String strDateFormat = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha=objSDF.format(fech);
        
         try {
            String sql = "SELECT SUM(Total) AS TOTAL, EXTRACT(MONTH FROM fecha) AS MES, EXTRACT(YEAR FROM fecha) AS AÑO FROM registro WHERE Estado='FACTURADO' GROUP BY MES, AÑO, idregistro ORDER BY MES, AÑO, idregistro";
            //      "SELECT rg.Total ,lg.Nombre AS Usuario , rg.Fecha FROM registro  AS rg INNER JOIN login1 As lg ON(rg.Usuario= lg.idlogin1) WHERE Usuario=? AND Fecha BETWEEN '"+EstablecerTiempo(-10)+"' AND '"+Fecha()+"'"
            
            ps = cn.prepareStatement(sql);
            //ps.setInt(1, Usuario);
            rs = ps.executeQuery();
            //DefaultPieDataset dateset = new DefaultPieDataset();
            DefaultCategoryDataset Datos= new DefaultCategoryDataset();
            while(rs.next()){
                //dateset.setValue(rs.getString("Total"), rs.getFloat("Total"));
                Datos.setValue(rs.getDouble("TOTAL"), "MES", rs.getString("MES")+"-"+rs.getString("AÑO"));
            }
            JFreeChart Barras = ChartFactory.createLineChart("VENTAS GENERALES", "FECHAS", "TOTALES", Datos, PlotOrientation.VERTICAL, true, true, false);
            
            //JFreeChart jf = ChartFactory.createPieChart("VENTAS DEL DÍA", (PieDataset) Datos);
             panel.setLayout(new java.awt.BorderLayout());
             ChartPanel CP = new ChartPanel(Barras);
             panel.add(CP, BorderLayout.CENTER);
             panel.validate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }*/
    
    
    public InterfazPrincipal Registros(){
                ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
                InterfazPrincipal IP = new InterfazPrincipal();
        
        String sqlProductos ="SELECT COUNT(idProductos) FROM productos";
        String sqlClientes = "SELECT COUNT(idclientes) FROM clientes";
        String sqlUsuariosEnLinea = "SELECT COUNT(idlogin1) FROM login1 WHERE Estado = 'ONLINE'";
        String sqlCategorias = "SELECT COUNT(idCategoria) FROM categoria";
        
        
        try {
            ps = cn.prepareStatement(sqlProductos);
            rs = ps.executeQuery();
            if(rs.next()){
                IP.setProductosRegistrados(rs.getString("COUNT(idProductos)"));
            }
        } catch (SQLException e) {
            System.err.println("Error en Cargar Registro de Productos " +e);
        }
        
        
        
        
        try {
            ps = cn.prepareStatement(sqlClientes);
            rs = ps.executeQuery();
            if(rs.next()){
                IP.setClientesRegistrados(rs.getString("COUNT(idclientes)"));
            }
        } catch (SQLException e) {
            System.err.println("Error en Cargar Registro de Productos " +e);
        }
        
        
        
        
        try {
            ps = cn.prepareStatement(sqlUsuariosEnLinea);
            rs = ps.executeQuery();
            if(rs.next()){
                IP.setUsuariosEnLinea(rs.getString("COUNT(idlogin1)"));
            }
        } catch (SQLException e) {
            System.err.println("Error en Cargar Registro de Productos " +e);
        }
        
        
        try {
            ps = cn.prepareStatement(sqlCategorias);
            rs = ps.executeQuery();
            if(rs.next()){
                IP.setCategorias(rs.getString("COUNT(idCategoria)"));
            }
        } catch (SQLException e) {
            System.err.println("Error en Cargar Registro de Productos " +e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        
        return IP;
    }
    
    
    public String UsuariosEnLinea(){
        ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
                InterfazPrincipal IP = new InterfazPrincipal();
        String sql = "SELECT Nombre FROM login1 WHERE Estado ='ONLINE'";
        String Usuarios= "";
        
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Usuarios = rs.getString("Nombre")+"\n";
            }
            
        } catch (SQLException e) {
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        
        return Usuarios;
    }
    
     public List ListarUsuariosEnLinea(){
        ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        List<String> Listapro = new ArrayList();
        
        try {
            
            ps = cn.prepareStatement("SELECT Nombre FROM login1 WHERE Estado ='ONLINE'");
            rs = ps.executeQuery();

            while (rs.next()) {
                String Nombres;
                Nombres = rs.getString("Nombre");
               Listapro.add(Nombres);
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
     
     public static String EstablecerTiempo(int Dias){
        Date fech = new Date();
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(fech); // Configuramos la fecha que se recibe
      calendar.add(Calendar.DAY_OF_YEAR, Dias);  // numero de horas a añadir, o restar en caso de horas<0
      String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fechas = Fechas.format(calendar.getTime());
      return fechas; // Devuelve el objeto Date con las nuevas horas añadidas
 }
     
     public String Fecha(){
        Date fech = new Date();
        String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fecha = Fechas.format(fech);
        
        return fecha;
    }
}
