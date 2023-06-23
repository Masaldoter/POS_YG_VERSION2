/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;
import Conexiones.ConexionesSQL;
import Conexiones.conexion;
import Modelo.Productos;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import com.toedter.calendar.JDateChooser;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class Actualizartablas extends ConexionesSQL{
    
    public static final int anchotabla[] = {5, 190, 10, 10, 10, 10, 10, 10, 20, 30, 8, 8, 5, 5};

    public List ConsultaEnTodosLosAmbitos(String valor, String valor2, String Estado, int TipoConsulta, Boolean IMPORTAR_ORDEN) {
        /*
        1: CONSULTA POR CODIGO DE BARRAS
        2: CONSULTA POR NOMBRE Y DESCRIPCIÓN
        3: CONSULTA POR PROVEEDOR
        4: CONSULTA POR STOCK
        5: CONSULTA POR STOCK Y PROVEEDOR
        6: CONSULTA POR CATEGORIA
        7: CONSULTA POR SUBCATEGORIA CON CATEGORIA
        8: CONSULTA POR UBICACION
        */
        String ESTADO_REGISTRO;
        String SQL = "SELECT productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.CodigoLetras, productos.Publico, productos.PrecioEs, productos.PrecioRe, productos.Estado_Productos, productos.ruta,"
                + "categoria.Categoria AS Categoria, proveedores.Proveedor AS Proveedores, productos.fechaingreso, tablaA1.Nombre AS UsuarioRegistro, productos.fechamodificacion,"
                + " tablaA2.Nombre AS UsuarioModifico FROM productos "
                + "INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores)"
                + " INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) "
                + "INNER JOIN login1 as tablaA1 ON (productos.UsuarioIngreso = tablaA1.idlogin1) "
                + "INNER JOIN login1 as tablaA2 ON (productos.UsuarioModifico = tablaA2.idlogin1) WHERE ";
        String es = "";
        if (Estado.equals("TODOS")) {
            ESTADO_REGISTRO=" Estado_Productos IN ('ACTIVO', 'INACTIVO')";
        }else{
            ESTADO_REGISTRO=" Estado_Productos= '"+Estado+"'";
        }
        switch (TipoConsulta) {
            case 1:
                SQL = SQL+"(productos.CodigoBarras LIKE '%" + valor + "%') AND "+ESTADO_REGISTRO+" ORDER BY productos.idProductos DESC";
                break;
            case 2:
                if(IMPORTAR_ORDEN==true){
                  SQL = SQL+"(productos.Nombre LIKE '%" + valor + "%' or productos.Descripcion LIKE '%" + valor + "%') AND"+ESTADO_REGISTRO;  
                }else{
                  SQL = SQL+"MATCH(productos.Nombre, productos.Descripcion) AGAINST('"+valor+"' IN BOOLEAN MODE) AND"+ESTADO_REGISTRO+ 
                          " ORDER BY MATCH (productos.Nombre, productos.Descripcion) AGAINST ('"+valor+"' IN BOOLEAN MODE) DESC, productos.idProductos DESC";  
                }
                break;
            case 3:
               SQL = SQL+"(productos.Proveedores="+valor +") AND"+ESTADO_REGISTRO;
                break;
            case 4:
                SQL = SQL+"(productos.Cantidad <="+valor+") AND" +ESTADO_REGISTRO;
                break;   
            case 5: 
                SQL = SQL+"(productos.Proveedores="+valor+" && productos.Cantidad<="+valor2+") AND" +ESTADO_REGISTRO;
            break;  
            case 6: 
                SQL = SQL+"(productos.Categoria="+valor +" OR productos.Categoria2="+valor +") AND"+ESTADO_REGISTRO;
                break; 
            case 7: 
                SQL = SQL+"(productos.Categoria="+valor+" AND productos.subcategoria="+valor2+" "
                        + "OR productos.subcategoria2="+valor2 +" OR productos.subcategoria3="+valor2 +" OR productos.subcategoria4="+valor2+") AND" +ESTADO_REGISTRO;
                break;    
            case 8: 
                SQL = SQL+"(productos.Ubicacion1="+valor+" OR productos.Ubicacion2="+valor2+") AND" +ESTADO_REGISTRO;
                break;     
                        default:
                break;
        }
        rs = null;
        ps = null;
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/editar.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Carrito_32PX.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);
        
        btn2.setIcon(ro2);
        List<Productos> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Productos pro1;
             ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                pro1= new Productos();
                pro1.setCodigoBarras(rs.getString("productos.CodigoBarras"));
                pro1.setNombre(rs.getString("productos.Nombre"));
                pro1.setCantidad(rs.getFloat("productos.Cantidad"));
                pro1.setCosto(rs.getFloat("productos.Costo"));
                pro1.setCodigoLetras(rs.getString("productos.CodigoLetras"));
                pro1.setPublico(rs.getFloat("productos.Publico"));
                pro1.setPrecioEs(rs.getFloat("productos.PrecioEs"));
                pro1.setPrecioRe(rs.getFloat("productos.PrecioRe"));
                pro1.setRuta(rs.getString("productos.ruta"));
                pro1.setProveedorNombre(rs.getString("Proveedores"));
                pro1.setCategoriaNombre(rs.getString("Categoria"));
                pro1.setFechaingreso(rs.getString("productos.fechaingreso"));
                pro1.setUsuarioIngresoLetras(rs.getString("UsuarioRegistro"));
                pro1.setFechamodificacion(rs.getString("productos.fechamodificacion"));
                pro1.setUsuarioModificoLetras(rs.getString("UsuarioModifico"));
                pro1.setEstado_Producto(rs.getString("productos.Estado_Productos"));
                pro1.setBotoneditar(btn1);
                pro1.setBotoneliminar(btn2);
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
    
    public static List ListarProductosSistema(JComboBox Seleccion, String Estado){
        
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/editar.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Carrito_32PX.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);
        
        btn2.setIcon(ro2);
        List<Productos> Listapro = new ArrayList();
        String Filtro ="";
        switch (Seleccion.getSelectedIndex()) {
            case 0 -> Filtro="idProductos";
            case 1 -> Filtro="categoria.idCategoria";
            case 2 -> Filtro="proveedores.idproveedores";
            case 3 -> Filtro="tablaA2.idlogin1";
            default -> {
            }
        }
        String SQL="";
        if(Estado.equals("TODOS")){
            SQL="WHERE Estado_Productos= 'ACTIVO' OR Estado_Productos= 'INACTIVO' ORDER BY "+Filtro+" DESC";
        }else{
            SQL="WHERE Estado_Productos= '"+Estado+"' ORDER BY "+Filtro+" DESC";
        }
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Productos pro1;
             ps = cn.prepareStatement("SELECT productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.CodigoLetras, productos.Publico, "
                     + "productos.PrecioEs, productos.PrecioRe, productos.Estado_Productos, productos.ruta," +
"categoria.Categoria AS Categoria, proveedores.Proveedor AS Proveedores, productos.fechaingreso, tablaA1.Nombre AS UsuarioRegistro, productos.fechamodificacion,"
                     + " tablaA2.Nombre AS UsuarioModifico FROM productos INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores)" +
" INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) INNER JOIN login1 as tablaA1 ON (productos.UsuarioIngreso = tablaA1.idlogin1) "
                     + "INNER JOIN login1 as tablaA2 ON (productos.UsuarioModifico = tablaA2.idlogin1) "+SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                pro1= new Productos();
                //pro1.setIdProductos(rs.getInt("productos.CodigoBarras"));
                pro1.setCodigoBarras(rs.getString("productos.CodigoBarras"));
                pro1.setNombre(rs.getString("productos.Nombre"));
                pro1.setCantidad(rs.getFloat("productos.Cantidad"));
                pro1.setCosto(rs.getFloat("productos.Costo"));
                pro1.setCodigoLetras(rs.getString("productos.CodigoLetras"));
                pro1.setPublico(rs.getFloat("productos.Publico"));
                pro1.setPrecioEs(rs.getFloat("productos.PrecioEs"));
                pro1.setPrecioRe(rs.getFloat("productos.PrecioRe"));
                pro1.setRuta(rs.getString("productos.ruta"));
                pro1.setProveedorNombre(rs.getString("Proveedores"));
                pro1.setCategoriaNombre(rs.getString("Categoria"));
                pro1.setFechaingreso(rs.getString("productos.fechaingreso"));
                pro1.setUsuarioIngresoLetras(rs.getString("UsuarioRegistro"));
                pro1.setFechamodificacion(rs.getString("productos.fechamodificacion"));
                pro1.setUsuarioModificoLetras(rs.getString("UsuarioModifico"));
                pro1.setEstado_Producto(rs.getString("productos.Estado_Productos"));
                pro1.setBotoneditar(btn1);
                pro1.setBotoneliminar(btn2);
               Listapro.add(pro1);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarProductosSistema, " + e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
    
    public List ConsultaPorFechas(JDateChooser FechaInicial, JDateChooser FechaFinal, String Estado, int Metodo) {
        Date date = FechaInicial.getDate();
        Date date2 = FechaFinal.getDate();
        String strDateFormat = "YYYY-MM-dd";
        String strDateFormat2 = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        SimpleDateFormat objSDF2 = new SimpleDateFormat(strDateFormat2);
        String fecha = objSDF.format(date);
        String fecha2 = objSDF2.format(date2);
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/editar.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Carrito_32PX.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);

        btn2.setIcon(ro2);
        List<Productos> Listapro = new ArrayList();
        String sql = "SELECT productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.CodigoLetras, "
                + "productos.Publico, productos.PrecioEs, productos.PrecioRe, productos.Estado_Productos, productos.ruta,"
                + "categoria.Categoria AS Categoria, proveedores.Proveedor AS Proveedores, productos.fechaingreso, "
                + "tablaA1.Nombre AS UsuarioRegistro, "
                + "productos.fechamodificacion,"
                + " tablaA2.Nombre AS UsuarioModifico "
                + "FROM productos "
                + "INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores)"
                + " INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) "
                + "INNER JOIN login1 as tablaA1 ON (productos.UsuarioIngreso = tablaA1.idlogin1) "
                + "INNER JOIN login1 as tablaA2 ON (productos.UsuarioModifico = tablaA2.idlogin1) ";
        if (Metodo == 1) {
            
            sql = sql + " WHERE productos.fechaingreso BETWEEN '" + fecha + "' AND '" + fecha2 + "'";
        } else {
            sql = sql + " WHERE productos.fechamodificacion BETWEEN '"+fecha+"' AND '"+fecha2+"'";
            
        }
        if (Estado.equals("TODOS")) {
            sql= sql+ " AND Estado_Productos='ACTIVO' OR Estado_Productos='INACTIVO' ORDER BY productos.idProductos DESC";
        }else{
            sql = sql + " AND Estado_Productos='"+Estado+"' ORDER BY productos.idProductos DESC"; 
        }
        
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Productos pro1;
             ps = cn.prepareStatement(sql);

             rs = ps.executeQuery();

            while (rs.next()) {
                pro1= new Productos();
                pro1.setCodigoBarras(rs.getString("productos.CodigoBarras"));
                pro1.setNombre(rs.getString("productos.Nombre"));
                pro1.setCantidad(rs.getFloat("productos.Cantidad"));
                pro1.setCosto(rs.getFloat("productos.Costo"));
                pro1.setCodigoLetras(rs.getString("productos.CodigoLetras"));
                pro1.setPublico(rs.getFloat("productos.Publico"));
                pro1.setPrecioEs(rs.getFloat("productos.PrecioEs"));
                pro1.setPrecioRe(rs.getFloat("productos.PrecioRe"));
                pro1.setRuta(rs.getString("productos.ruta"));
                pro1.setProveedorNombre(rs.getString("Proveedores"));
                pro1.setCategoriaNombre(rs.getString("Categoria"));
                pro1.setFechaingreso(rs.getString("productos.fechaingreso"));
                pro1.setUsuarioIngresoLetras(rs.getString("UsuarioRegistro"));
                pro1.setFechamodificacion(rs.getString("productos.fechamodificacion"));
                pro1.setUsuarioModificoLetras(rs.getString("UsuarioModifico"));
                pro1.setEstado_Producto(rs.getString("productos.Estado_Productos"));
                pro1.setBotoneditar(btn1);
                pro1.setBotoneliminar(btn2);
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
    
    public List ConsultaGeneral(String valor) {
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/editar.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Carrito_32PX.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);
        
        btn2.setIcon(ro2);
        List<Productos> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Productos pro1;
             ps = cn.prepareStatement("SELECT productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.CodigoLetras, productos.Publico, productos.PrecioEs, productos.PrecioRe, " +
"categoria.Categoria AS Categoria, proveedores.Proveedor AS Proveedores, productos.fechaingreso, tablaA1.Nombre AS UsuarioRegistro, productos.fechamodificacion,"
                     + " tablaA2.Nombre AS UsuarioModifico FROM productos INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores)" +
" INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) INNER JOIN login1 as tablaA1 ON (productos.UsuarioIngreso = tablaA1.idlogin1) INNER JOIN login1 as tablaA2 ON (productos.UsuarioModifico = tablaA2.idlogin1) WHERE productos.CodigoBarras LIKE '%" + valor + "%' OR productos.Nombre LIKE '%" + valor + "%' OR productos.Descripcion LIKE '%" + valor + "%' ORDER BY productos.idProductos DESC" );
            rs = ps.executeQuery();

            while (rs.next()) {
                pro1= new Productos();
                //pro1.setIdProductos(rs.getInt("productos.CodigoBarras"));
                pro1.setCodigoBarras(rs.getString("productos.CodigoBarras"));
                pro1.setNombre(rs.getString("productos.Nombre"));
                pro1.setCantidad(rs.getFloat("productos.Cantidad"));
                pro1.setCosto(rs.getFloat("productos.Costo"));
                pro1.setCodigoLetras(rs.getString("productos.CodigoLetras"));
                pro1.setPublico(rs.getFloat("productos.Publico"));
                pro1.setPrecioEs(rs.getFloat("productos.PrecioEs"));
                pro1.setPrecioRe(rs.getFloat("productos.PrecioRe"));
                pro1.setProveedorNombre(rs.getString("Proveedores"));
                pro1.setCategoriaNombre(rs.getString("Categoria"));
                pro1.setFechaingreso(rs.getString("productos.fechaingreso"));
                pro1.setUsuarioIngresoLetras(rs.getString("UsuarioRegistro"));
                pro1.setFechamodificacion(rs.getString("productos.fechamodificacion"));
                pro1.setUsuarioModificoLetras(rs.getString("UsuarioModifico"));
                pro1.setBotoneditar(btn1);
                pro1.setBotoneliminar(btn2);
               Listapro.add(pro1);
            }

        } catch (SQLException e) {
            System.err.println("Error en ConsultaCodigoBarras, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
        
    }
    
}
    
