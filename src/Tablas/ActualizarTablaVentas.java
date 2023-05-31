/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import Conexiones.conexion;
import Modelo.ConsultasVentas;
import Modelo.Productos;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ActualizarTablaVentas extends ConexionesSQL{
    
    ConsultasVentas v = new ConsultasVentas();

    private final int anchotabla[] = {8, 130, 6, 6, 6, 6, 6, 4, 4, 2};

    public List ListarProductosTienda() {

        List<Productos> Listapro = new ArrayList();
        Productos pro;
        cn = conexion.getInstancia().getConnection();
        try {

            ps = cn.prepareStatement("select idProductos, CodigoBarras, Nombre, Cantidad, Costo, Publico, CodigoLetras, PrecioEs, PrecioRe from productos ORDER BY idProductos DESC");
            rs = ps.executeQuery();

            while (rs.next()) {
                pro = new Productos();
                pro.setIdProductos(rs.getInt("idProductos"));
                pro.setCodigoBarras(rs.getString("CodigoBarras"));
                pro.setNombre(rs.getString("Nombre"));
                pro.setCantidad(rs.getFloat("Cantidad"));
                pro.setCosto(rs.getFloat("Costo"));
                pro.setPublico(rs.getFloat("Publico"));
                pro.setCodigoLetras(rs.getString("CodigoLetras"));
                pro.setPrecioEs(rs.getFloat("PrecioEs"));
                pro.setPrecioRe(rs.getFloat("PrecioRe"));
                Listapro.add(pro);
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        } finally {
            RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }

        return Listapro;
    }

    public List ListarProductosTiendaNombre(String PARAMETRO) {

        List<Productos> Listapro = new ArrayList();
        Productos pro;
        cn = conexion.getInstancia().getConnection();
        try {
            
            String[] palabras = PARAMETRO.split(" ");
StringBuilder terminosBusqueda = new StringBuilder();

for (String palabra : palabras) {
    terminosBusqueda.append("+" + palabra + "* ");
}
            //"select Nombre, ruta from productos WHERE (Estado_Productos='ACTIVO') ORDER BY idProductos DESC"
            ps = cn.prepareStatement("SELECT Nombre FROM productos WHERE MATCH (Nombre, Descripcion) AGAINST ('"+terminosBusqueda.toString()+"' IN BOOLEAN MODE) AND Estado_Productos='ACTIVO' ORDER BY idProductos DESC");
            rs = ps.executeQuery();

            while (rs.next()) {
               pro = new Productos();
                pro.setNombre(rs.getString("Nombre"));
               Listapro.add(pro);
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
    
    public void TablaVentaId(JTable TablaVentas1, String valor){
         rs = null;
               ps = null;
        cn = conexion.getInstancia().getConnection();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVentas1.setModel(modeloTabla);

        try {
            ps = cn.prepareStatement("select idProductos, Nombre, Cantidad, Publico, CodigoLetras from Productos where idProductos=?");
            ps.setString(1, valor);

            rs = ps.executeQuery();

            modeloTabla.addColumn("id");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Stock");
            modeloTabla.addColumn("Precio");
            modeloTabla.addColumn("Costo");

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            /*int anchotabla[] ={5,150,10,10,10};
            
            for(int i=0; i<cantidadColumnas;i++){
                tablaProductos.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }*/
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
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }

    public void ConsultaVentaPorCodigoBarras(JTable TablaVentas1, String valor) {
        cn = conexion.getInstancia().getConnection();
         rs = null;
               ps = null;
        try {
            ps = cn.prepareStatement("select CodigoBarras, Nombre, Cantidad, Publico, PrecioEs, PrecioRe, Ubicacion, CodigoLetras from Productos where CodigoBarras=?");
            ps.setString(1, valor);
            //ps.setString(2, Cantidad.getText());

            rs = ps.executeQuery();

            if (rs.next()) {
                v.setIdVenta(rs.getInt("CodigoBarras"));
                v.setNombreVenta(rs.getString("Nombre"));
                v.setStockVenta(String.valueOf(rs.getFloat("Cantidad")));
                v.setPrecioPublicoVenta(String.valueOf(rs.getFloat("Publico")));
                v.setPrecioEspecialVenta(String.valueOf(rs.getFloat("PrecioEs")));
                v.setPrecioReventaVenta(String.valueOf(rs.getFloat("PrecioRe")));        
                v.setUbicacionVentaVenta(rs.getString("Ubicacion"));
                v.setCostoLetrasVenta(rs.getString("CodigoLetras"));
            } else {
                //JOptionPane.showMessageDialog(null, "¡El producto no existe!");
            }

        } catch (HeadlessException | SQLException e) {
            System.err.println("Error, " + e);
        }finally{
            RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
        
       /* DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVentas1.setModel(modeloTabla);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modeloTabla);
        TablaVentas1.setRowSorter(elQueOrdena);
        try {
            ps = cn.prepareStatement("select CodigoBarras, Nombre, Cantidad, Publico, PrecioEs, PrecioRe, CodigoLetras from Productos where CodigoBarras=?");
            ps.setString(1, valor);
            rs = ps.executeQuery();

            modeloTabla.addColumn("CodigoBarras");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Cantidad");
            modeloTabla.addColumn("Publico");
            modeloTabla.addColumn("P/Especial");
            modeloTabla.addColumn("P/Reventa");
            modeloTabla.addColumn("Costo");

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchotabla[] = {8, 190, 5, 5, 5, 5, 5};

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVentas1.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
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
        }*/
    }
    
    public void ActualizarTablaVenta(JTable TablaVentas1) {
         rs = null;
               ps = null;
        cn = conexion.getInstancia().getConnection();
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVentas1.setModel(modeloTabla);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(modeloTabla);
        TablaVentas1.setRowSorter(elQueOrdena);
        
        try {

            ps = cn.prepareStatement("select CodigoBarras, Nombre, Cantidad, Publico,  PrecioEs, PrecioRe, CodigoLetras, Ubicacion, Descripcion, IdProductos from productos where idProductos ORDER BY idProductos DESC");

            rs = ps.executeQuery();

            modeloTabla.addColumn("Codigo");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Stock");
            modeloTabla.addColumn("Precio U");
            modeloTabla.addColumn("P/Especial");
            modeloTabla.addColumn("P/Reventa");
            modeloTabla.addColumn("Costo");
            modeloTabla.addColumn("Ubicación");
            modeloTabla.addColumn("Descripción");
            modeloTabla.addColumn("Id");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVentas1.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
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
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }

    public void ConsultaPorCodigo(JTable TablaVentas1, String valor){
         rs = null;
               ps = null;
        cn = conexion.getInstancia().getConnection();
        
            DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVentas1.setModel(modeloTabla);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(modeloTabla);
        TablaVentas1.setRowSorter(elQueOrdena);

        try {

            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre, Cantidad, Publico, PrecioEs, PrecioRe, CodigoLetras, Ubicacion, Descripcion, IdProductos from productos WHERE CodigoBarras LIKE '%" + valor + "%'");
            // ps.setString(1, Nombre_producto.getText());

            rs = ps.executeQuery();

            modeloTabla.addColumn("Codigo");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Stock");
            modeloTabla.addColumn("Precio "); 
            modeloTabla.addColumn("P/Especial");    
            modeloTabla.addColumn("P/Reventa");    
            modeloTabla.addColumn("Costo");
            modeloTabla.addColumn("Ubicación");
            modeloTabla.addColumn("Descripción");
            modeloTabla.addColumn("Id");

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVentas1.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
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
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        }
    
    public void ConsultaPorDescripcion(JTable TablaVentas1, String valor){
         rs = null;
               ps = null;
        cn = conexion.getInstancia().getConnection();
        
            DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVentas1.setModel(modeloTabla);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(modeloTabla);
        TablaVentas1.setRowSorter(elQueOrdena);

        try {

            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre, Cantidad, Publico, PrecioEs, PrecioRe, CodigoLetras, Ubicacion, Descripcion, IdProductos from productos WHERE Descripcion LIKE '%" + valor + "%'");
            // ps.setString(1, Nombre_producto.getText());

            rs = ps.executeQuery();

            modeloTabla.addColumn("Codigo");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Stock");
            modeloTabla.addColumn("Precio "); 
            modeloTabla.addColumn("P/Especial");    
            modeloTabla.addColumn("P/Reventa");    
            modeloTabla.addColumn("Costo");
            modeloTabla.addColumn("Ubicación");
            modeloTabla.addColumn("Descripción");
            modeloTabla.addColumn("Id");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVentas1.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
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
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        }
    
    public void ConsultaPorPalabraClave(JTable TablaVentas1, String valor){
             rs = null;
               ps = null;
                cn = conexion.getInstancia().getConnection();
            
            DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVentas1.setModel(modeloTabla);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<>(modeloTabla);
        TablaVentas1.setRowSorter(elQueOrdena);

        try {

            ps = cn.prepareStatement("SELECT CodigoBarras, Nombre, Cantidad, Publico, PrecioEs, PrecioRe, CodigoLetras, Ubicacion, Descripcion, IdProductos from productos WHERE Nombre LIKE '%" + valor + "%'");
            // ps.setString(1, Nombre_producto.getText());
            
            rs = ps.executeQuery();

            modeloTabla.addColumn("Codigo");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Stock");
            modeloTabla.addColumn("Precio "); 
            modeloTabla.addColumn("P/Especial");    
            modeloTabla.addColumn("P/Reventa");    
            modeloTabla.addColumn("Costo");
            modeloTabla.addColumn("Ubicación");
            modeloTabla.addColumn("Descripción");
            modeloTabla.addColumn("Id");
            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVentas1.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
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
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        }
    
   /* public void BusquedaDeProductoVentanaEmergente(JTable TablaVentas1, String valor){
             
        cn = conexion.getInstancia().getConnection();
        rs = null;
        ps = null;
        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaVentas1.setModel(modeloTabla);
        try {

            ps = cn.prepareStatement("SELECT Nombre from productos WHERE Nombre LIKE '%" + valor + "%' OR Descripcion LIKE '%" + valor + "%' OR CodigoBarras LIKE '%" + valor + "%'");
            // ps.setString(1, Nombre_producto.getText());
            
            rs = ps.executeQuery();

            modeloTabla.addColumn("Nombre");
            ResultSetMetaData rsMD = rs.getMetaData();
            
            int cantidadColumnas = rsMD.getColumnCount();

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaVentas1.getColumnModel().getColumn(i).setPreferredWidth(100);
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
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        }*/
    
    public List ConsultaPorNombreVentanaEmergente(String valor, int Inicio, int Final) {
        rs = null;
        ps = null;
        ImageIcon imagenI;
        Icon icono;
        JLabel lbl;
        List<Productos> Listapro = new ArrayList();
        try {
            
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Productos pro1;
             ps = cn.prepareStatement("SELECT productos.Nombre, productos.Cantidad, productos.Publico, productos.ruta FROM productos WHERE productos.Nombre LIKE '%" + valor + "%' or productos.Descripcion LIKE '%" + valor + "%' or productos.CodigoBarras LIKE '%" + valor + "%' ORDER BY idProductos DESC LIMIT "+Inicio+","+Final);
            rs = ps.executeQuery();

            while (rs.next()) {
                pro1 = new Productos();
                lbl = new JLabel();
                lbl.setSize(100, 65);
                lbl.setName("imagen");
                lbl.removeAll();
                lbl.setHorizontalAlignment(SwingConstants.CENTER);
                imagenI = new ImageIcon(CargarDatosRutas(1)+"\\"+rs.getString("productos.ruta"));
                icono = new ImageIcon(imagenI.getImage().getScaledInstance(
                        lbl.getWidth(),
                        lbl.getHeight(),
                        Image.SCALE_DEFAULT));
                lbl.setIcon(icono);
                lbl.repaint();
        
        
                pro1.setNombre(rs.getString("productos.Nombre"));
                pro1.setCantidad(rs.getFloat("productos.Cantidad"));
                pro1.setPublico(rs.getFloat("productos.Publico"));
                pro1.setImagen(lbl);
               Listapro.add(pro1);
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
    
    public List ConsultaEnTodosLosAmbitos(String valor, String valor2, int TipoConsulta, Boolean IMPORTAR_ORDEN) {
        /*
        1: ACTUALIZAR RESULTADOS
        2: CONSULTA POR CODIGO DE BARRAS, NOMBRE Y DESCRIPCIÓN
        3: CONSULTA POR PROVEEDOR
        4: CONSULTA POR CATEGORIA
        5: CONSULTA POR SUBCATEGORIA CON CATEGORIA
        6: CONSULTA POR UBICACION
        */
        
        String SQL="";
        String SLQ2="";
        if(IMPORTAR_ORDEN==true){
                  SLQ2 = "SELECT CodigoBarras,Nombre, Cantidad, CodigoLetras, Publico, ruta "
                        +"FROM productos"
                        +" WHERE CodigoBarras LIKE '%" + valor + "%' or Nombre LIKE '%" + valor + "%' or Descripcion LIKE '%" + valor + "%'";  
                }else{
                  SLQ2 = "SELECT CodigoBarras,Nombre, Cantidad, CodigoLetras, Publico, ruta "
                        +"FROM productos"
                        +" WHERE MATCH(CodigoBarras, Nombre, Descripcion) AGAINST('"+valor+"' IN NATURAL LANGUAGE MODE)";  
                }
        switch (TipoConsulta) {
            case 1 -> SQL = "SELECT CodigoBarras,Nombre, Cantidad, CodigoLetras, Publico, ruta "
                        +"FROM productos ORDER BY idProductos DESC";
            case 2 -> SQL = SLQ2;
            
            case 3 -> SQL = "SELECT CodigoBarras,Nombre, Cantidad, CodigoLetras, Publico, ruta "
                        +"FROM productos"
                        +" WHERE Proveedores="+valor;
            case 4 -> SQL =   "SELECT CodigoBarras,Nombre, Cantidad, CodigoLetras, Publico, ruta "
                        +"FROM productos"
                        +" WHERE Categoria="+valor +" OR Categoria2="+valor;
            case 6 -> SQL = "SELECT CodigoBarras,Nombre, Cantidad, CodigoLetras, Publico, ruta "
                        +"FROM productos"
                        +" WHERE Ubicacion1="+valor+" OR Ubicacion2="+valor2;
                        default -> {
            }
        }
         ImageIcon imagenI;
        Icon icono;
        JLabel lbl;
        List<Productos> Listapro = new ArrayList();
        try {
            
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Productos pro1;
             ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                pro1 = new Productos();
                pro1.setCodigoBarras(rs.getString("productos.CodigoBarras"));
                pro1.setNombre(rs.getString("productos.Nombre"));
                pro1.setCantidad(rs.getFloat("productos.Cantidad"));
                pro1.setCodigoLetras(rs.getString("productos.CodigoLetras"));
                pro1.setPublico(rs.getFloat("productos.Publico"));
                pro1.setRuta(rs.getString("productos.ruta"));
               Listapro.add(pro1);
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
    
    public static String CargarDatosRutas(int TipoRuta){
        String Ruta="";
            try {
                Properties propertie3= new Properties();
    InputStream entrada = null;
            entrada = new FileInputStream(new File ("/Sistema Punto de Venta YG/CONFIGURACIONES/RUTASERVIDORIMAGENES.properties").getAbsolutePath());
            propertie3.load(entrada);
            if(TipoRuta==0){
                Ruta=propertie3.getProperty("rutasistema");
            }else{
               Ruta=  propertie3.getProperty("ruta");
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
            return Ruta;
    }
}
