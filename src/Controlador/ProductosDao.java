
package Controlador;

import CLASES_GLOBALES.METODOS_GLOBALES;
import Conexiones.conexion;
import Modelo.Productos;
import Modelo.Categoria;
import Modelo.Usuarios;
import Conexiones.ConexionesSQL;
import Modelo.SubCategoria;
import Modelo.Ubicacion;
import Vista.ADMINISTRACION.INVENTARIO.ADMINISTRARPRODUCTO;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class ProductosDao extends ConexionesSQL{
    Productos producto= new Productos();
            
        public List ListarProductosCombo(){
        cn= Unionsis2.getConnection();
        List<Productos> Listapr = new ArrayList();
        String sql = "SELECT CodigoBarras, Nombre FROM productos";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Productos pro= new Productos();
                pro.setNombre(rs.getString("Nombre"));
                pro.setCodigoBarras(rs.getString("CodigoBarras"));
                Listapr.add(pro);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Listapr;
    }   
            
        public String RetornarProductosDuplicados(int Metodo, String Parametro) {
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "";
        String Datos = "";
            if (Metodo == 1) {
                sql = "SELECT CodigoBarras, Estado_Productos from productos WHERE CodigoBarras=?";
            } else {
                sql = "SELECT Nombre, Estado_Productos from productos WHERE Nombre=?";
            }
            try {
                ps = cn.prepareStatement(sql);
                ps.setString(1, Parametro);
                rs = ps.executeQuery();
                if (rs.next()) {
                    if (Metodo == 1) {
                        Datos = rs.getString("CodigoBarras");
                    } else {
                        Datos = rs.getString("Nombre");
                    }
                }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Datos;
    }
            
        public int ConsultaIdProveedor(JComboBox ComboProveedor) {

        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        int Resultado = 0;
        String seleccion = String.valueOf(ComboProveedor.getSelectedItem());

        String sql = "SELECT idproveedores FROM proveedores WHERE Proveedor=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, seleccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                Resultado = rs.getInt("idproveedores");
                return Resultado;
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return 0;
    }
            
            public int ConsultaIdCategoria(JComboBox ComboCategoria) {
                ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                
        int Resultado = 0;
        String seleccion = String.valueOf(ComboCategoria.getSelectedItem());

        String sql = "SELECT idCategoria FROM categoria WHERE Categoria=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, seleccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                Resultado = rs.getInt("idCategoria");
                return Resultado;
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return 0;
    }
            
            public int ConsultaIdSubCategoria(int IdCategoria, JComboBox CombosubCategoria) {
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                
        int Resultado = 0;

        String sql = "SELECT idsubcategoria FROM subcategoria WHERE IdCategoria=? AND NombreSubCategoria=?";
        String seleccion = String.valueOf(CombosubCategoria.getSelectedItem());
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, IdCategoria);
            ps.setString(2, seleccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                Resultado = rs.getInt("idsubcategoria");
                return Resultado;
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Resultado;
    }
            
            public int ConsultaIdUbicacion(JComboBox ComboUbicacion) {
                ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                
        int Resultado = 0;
        String seleccion = String.valueOf(ComboUbicacion.getSelectedItem());

        String sql = "SELECT idubicaciones FROM ubicaciones WHERE NombreUbicacion=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, seleccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                Resultado = rs.getInt("idubicaciones");
                return Resultado;
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return 0;
    }
            
            public int ConsultaIdUsuario(JComboBox ComboUsuario) {
                ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                
        int Resultado = 0;
        String seleccion = String.valueOf(ComboUsuario.getSelectedItem());

        String sql = "SELECT idlogin1 FROM login1 WHERE NombreUsuario=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, seleccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                Resultado = rs.getInt("idlogin1");
                return Resultado;
            }

        } catch (SQLException e) {
            System.out.println("Error en ConsultaIdUsuario "+e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return 0;
    }
                    
    
    public boolean RegistrarProductos(Productos pro){
        
        ps = null;
        cn = Unionsis2.getConnection();
        String sql="insert into productos (Nombre, Cantidad,  Costo, Publico, Proveedores, CodigoBarras, CodigoLetras, PrecioRe, PrecioEs, Categoria, "
                + "fechaingreso, UsuarioIngreso, fechamodificacion, UsuarioModifico, Descripcion, "
                + "Precio1, Precio2, Precio3, ruta, subcategoria, Ubicacion1, Ubicacion2, Estado_Productos, Permitir_Descuentos) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";       
        try {
            
            
            ps=cn.prepareStatement(sql);
            
            
            
             ps.setString(1, pro.getNombre());
              ps.setFloat(2, pro.getCantidad());
               ps.setFloat(3, pro.getCosto());
                ps.setFloat(4, pro.getPublico());
                ps.setInt(5, pro.getIdProveedores());
                ps.setString(6, pro.getCodigoBarras());
                ps.setString(7, pro.getCodigoLetras());
                ps.setFloat(8, pro.getPrecioRe());
                ps.setFloat(9, pro.getPrecioEs());
                ps.setInt(10, pro.getCategoria()); 
                ps.setString(11, METODOS_GLOBALES.Fecha());
                ps.setInt(12, pro.getUsuarioIngreso());
                ps.setString(13, METODOS_GLOBALES.Fecha());
                ps.setInt(14, pro.getUsuarioModifico());
                ps.setString(15, pro.getDescripcion());
                ps.setString(16, pro.getNombreTiposDePrecio1());
                ps.setString(17, pro.getNombreTiposDePrecio2());
                ps.setString(18, pro.getNombreTiposDePrecio3());
                ps.setString(19, pro.getRuta());
                ps.setInt(20, pro.getSubcategoria());
                ps.setInt(21, pro.getUbicacion1());
                ps.setInt(22, pro.getUbicacion2());
                ps.setString(23, pro.getEstado_Producto());
                ps.setBoolean(24, pro.getAPLICAR_DESCUENTO());
                ps.execute();       
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO ÉXITOSO", "¡PRODUCTO "+pro.getNombre()+" REGISTRADO CORRECTAMENTE!", DesktopNotify.SUCCESS, 10000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO ERRÓNEO", "¡PRODUCTO "+pro.getNombre()+" NO SE PUDO REGISTRAR DEBIDO A EL SIGUIENTE ERROR!\n "+e, DesktopNotify.ERROR, 10000L);
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean EliminarProducto(Productos pro){
        ps = null;
        cn = Unionsis2.getConnection();
        String sql="delete from productos where idProductos=?";       
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, pro.getIdProductos());     
                ps.execute();       
                
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ELIMINACIÓN ÉXITOSA", "¡EL PRODUCTO SE ELIMINÓ CORRECTAMENTE!", DesktopNotify.SUCCESS, 2000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la eliminación!");
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean EditarProducto(Productos pro, String ruta){
        
        ps = null;
        cn = Unionsis2.getConnection();
        String sql="update productos set CodigoBarras=?, Nombre=?, Cantidad=?, Costo=?, Publico=?, Proveedores=?, "
                + "CodigoLetras=?, PrecioRe=?, PrecioEs=?, Categoria=?, fechamodificacion=?, UsuarioModifico=?, Descripcion=?, "
                + "Precio1=?, Precio2=?, Precio3=?, ruta=?, subcategoria=?, Ubicacion1=?, Ubicacion2=?, Estado_Productos=? where idProductos=?";       
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, pro.getCodigoBarras());     
            ps.setString(2, pro.getNombre());
            ps.setFloat(3, pro.getCantidad());
            ps.setFloat(4, pro.getCosto());
            ps.setFloat(5, pro.getPublico());
            ps.setInt(6, pro.getIdProveedores());
            ps.setString(7, pro.getCodigoLetras());
            ps.setFloat(8, pro.getPrecioRe());
            ps.setFloat(9, pro.getPrecioEs());
            ps.setInt(10, pro.getCategoria());
            ps.setString(11, METODOS_GLOBALES.Fecha()); 
            ps.setInt(12, pro.getUsuarioModifico());
            
                ps.setString(13, pro.getDescripcion());
                ps.setString(14, pro.getNombreTiposDePrecio1());
                ps.setString(15, pro.getNombreTiposDePrecio2());
                ps.setString(16, pro.getNombreTiposDePrecio3());
                ps.setString(17, pro.getRuta());
                
                ps.setInt(18, pro.getSubcategoria());
                ps.setInt(20, pro.getUbicacion1());
                ps.setInt(21, pro.getUbicacion2());
                ps.setString(21, pro.getEstado_Producto());
                ps.setBoolean(22, pro.getAPLICAR_DESCUENTO());
            ps.setInt(25, pro.getIdProductos());
            
            ps.executeUpdate(); 
            
            
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("EDICIÓN ÉXITOSO", "¡PRODUCTO "+pro.getNombre()+" EDITADO CORRECTAMENTE!", DesktopNotify.SUCCESS, 10000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la modificación!");
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean EditarProductoSinImagen(Productos pro){
        ps = null;
        cn = Unionsis2.getConnection();
        
        String sql="update productos set CodigoBarras=?, Nombre=?, Cantidad=?, Costo=?, Publico=?, Proveedores=?, CodigoLetras=?, PrecioRe=?, PrecioEs=?, Categoria=?, fechamodificacion=?, UsuarioModifico=?, Descripcion=?,"
                + "Precio1=?, Precio2=?, Precio3=?, ruta=?, subcategoria=?, Ubicacion1=?, Ubicacion2=?, Estado_Productos=?, Permitir_Descuentos=? where idProductos=?";       
        try {
            ps=cn.prepareStatement(sql);
            ps.setString(1, pro.getCodigoBarras());     
            ps.setString(2, pro.getNombre());
            ps.setFloat(3, pro.getCantidad());
            ps.setFloat(4, pro.getCosto());
            ps.setFloat(5, pro.getPublico());
            ps.setInt(6, pro.getIdProveedores());
            ps.setString(7, pro.getCodigoLetras());
            ps.setFloat(8, pro.getPrecioRe());
            ps.setFloat(9, pro.getPrecioEs());
            ps.setInt(10, pro.getCategoria());
            ps.setString(11, METODOS_GLOBALES.Fecha()); 
            ps.setInt(12, pro.getUsuarioModifico());
            ps.setString(13, pro.getDescripcion());
            ps.setString(14, pro.getNombreTiposDePrecio1());
                ps.setString(15, pro.getNombreTiposDePrecio2());
                ps.setString(16, pro.getNombreTiposDePrecio3());
                ps.setString(17, pro.getRuta());
                ps.setInt(18, pro.getSubcategoria());
                ps.setInt(19, pro.getUbicacion1());
                ps.setInt(20, pro.getUbicacion2());
                ps.setString(21, pro.getEstado_Producto());
                ps.setBoolean(22, pro.getAPLICAR_DESCUENTO());
            ps.setInt(23, pro.getIdProductos());
            
            ps.executeUpdate(); 
            
            
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("EDICIÓN ÉXITOSO", "¡PRODUCTO "+pro.getNombre()+" EDITADO CORRECTAMENTE!", DesktopNotify.SUCCESS, 10000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la modificación!");
            return false;
        }finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public static boolean ActualizarStock(Float cant, int cod) {
        Boolean Estado=false;
        ps = null;
        cn = Unionsis2.getConnection();

        String sql = "update productos set Cantidad=? where idProductos=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setFloat(1, cant);
            ps.setInt(2, cod);
            ps.execute();
            Estado=true;

        } catch (SQLException e) {
            Estado=false;
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR AL ACTUALIZAR STOCK", "NO SE ACTUALIZÓ EL STOCK\n"+ e, DesktopNotify.SUCCESS, 9000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Estado;
    }
    
    public Productos VerDatosTabla(String CodigoBarras){
        Productos pro= new Productos();
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        
        String sql="SELECT productos.IdProductos,productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.Publico, productos.CodigoLetras, productos.Estado_Productos,"
                + "categoria.Categoria AS CategoriaNombre, " +
"productos.Proveedores, productos.Categoria,productos.Proveedores, proveedores.Proveedor AS ProveedoresNombre, productos.PrecioEs, productos.PrecioRe, productos.ruta, productos.Descripcion, productos.Precio1, productos.Precio2, productos.Precio3 " +
"FROM productos INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores) INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) WHERE CodigoBarras=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, CodigoBarras);
            rs = ps.executeQuery();
            
            while(rs.next()){
            pro.setIdProductos(rs.getInt("IdProductos"));
                pro.setCodigoBarras(rs.getString("CodigoBarras"));
                pro.setNombre(rs.getString("Nombre"));
                pro.setCantidad(rs.getFloat("Cantidad"));
                pro.setCosto(rs.getFloat("Costo"));
                pro.setPublico(rs.getFloat("Publico"));
                pro.setIdProveedores(rs.getInt("Proveedores"));
                pro.setCategoriaNombre(rs.getString("CategoriaNombre"));
                pro.setProveedorNombre(rs.getString("ProveedoresNombre"));
                pro.setCodigoLetras(rs.getString("CodigoLetras"));
                pro.setPrecioEs(rs.getFloat("PrecioEs"));
                pro.setPrecioRe(rs.getFloat("PrecioRe"));
                pro.setCategoria(rs.getInt("Categoria"));
                pro.setRuta(rs.getString("ruta"));
                pro.setDescripcion(rs.getString("Descripcion"));
                pro.setNombreTiposDePrecio1(rs.getString("Precio1"));
                pro.setNombreTiposDePrecio2(rs.getString("Precio2"));
                pro.setNombreTiposDePrecio3(rs.getString("Precio3"));
                pro.setEstado_Producto(rs.getString("Estado_Productos"));
            }
        } catch (SQLException e) {
            System.out.println("Error en Método Visualizar, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return pro;
    }
    
    public void ActualizarTabla(Productos pro){
        ps = null;
        cn = Unionsis2.getConnection();
        rs = null;
        String sql = "SELECT productos.IdProductos,productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.Publico, "
                + "productos.Estado_Productos, productos.Permitir_Descuentos,\n" +
"                productos.CodigoLetras, \n" +
"                categoria.Categoria AS CategoriaNombre,\n" +
"                productos.Proveedores, productos.Categoria,\n" +
"                proveedores.Proveedor AS ProveedoresNombre, \n" +
"                subcategoria.NombreSubCategoria AS subcategoria1, \n" +
"                productos.PrecioEs, productos.PrecioRe, productos.ruta, \n" +
"                productos.Descripcion, productos.Precio1, productos.Precio2, productos.Precio3,\n" +
"                ubicaciones.NombreUbicacion AS NOMBREUBICACION1,\n" +
"                ubicacionestabla2.NombreUbicacion AS NOMBREUBICACION2,\n" +
"                productos.fechaingreso, productos.fechamodificacion,\n" +
"                Usuarios.NombreUsuario AS USUARIOINGRESO, \n" +
"                Usuarios2.NombreUsuario AS USUARIOMODIFICO\n" +
"                FROM productos \n" +
"                INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores) \n" +
"                INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) \n" +
"			    INNER JOIN subcategoria ON (productos.subcategoria = subcategoria.idsubcategoria) \n" +
"                INNER JOIN ubicaciones ON (productos.Ubicacion1 = ubicaciones.idubicaciones) \n" +
"                INNER JOIN ubicaciones AS ubicacionestabla2 ON (productos.Ubicacion2 = ubicacionestabla2.idubicaciones)"
                + "INNER JOIN login1 AS Usuarios ON (productos.UsuarioIngreso = Usuarios.idlogin1) \n" +
"                INNER JOIN login1 AS Usuarios2 ON (productos.UsuarioModifico = Usuarios2.idlogin1) WHERE CodigoBarras=?;";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, pro.getCodigoBarras());
            rs = ps.executeQuery();

            while (rs.next()) {
                pro.setIdProductos(rs.getInt("productos.IdProductos"));
                pro.setCodigoBarras(rs.getString("productos.CodigoBarras"));
                pro.setNombre(rs.getString("productos.Nombre"));
                pro.setCantidad(rs.getFloat("productos.Cantidad"));
                pro.setCosto(rs.getFloat("productos.Costo"));
                pro.setPublico(rs.getFloat("productos.Publico"));
                pro.setCodigoLetras(rs.getString("productos.CodigoLetras"));
                pro.setCategoriaNombre(rs.getString("CategoriaNombre"));
                pro.setIdProveedores(rs.getInt("productos.Proveedores"));
                pro.setCategoria(rs.getInt("productos.Categoria"));
                pro.setProveedorNombre(rs.getString("ProveedoresNombre"));
                pro.setPrecioEs(rs.getFloat("productos.PrecioEs"));
                pro.setPrecioRe(rs.getFloat("productos.PrecioRe"));
                pro.setRuta(rs.getString("productos.ruta"));
                pro.setDescripcion(rs.getString("productos.Descripcion"));
                pro.setNombreTiposDePrecio1(rs.getString("productos.Precio1"));
                pro.setNombreTiposDePrecio2(rs.getString("productos.Precio2"));
                pro.setNombreTiposDePrecio3(rs.getString("productos.Precio3"));
                pro.setSubcategoriaNombre(rs.getString("subcategoria1"));
                pro.setUbicacionNombre1(rs.getString("NOMBREUBICACION1"));
                pro.setUbicacionNombre2(rs.getString("NOMBREUBICACION2"));
                pro.setEstado_Producto(rs.getString("Estado_Productos"));
                pro.setAPLICAR_DESCUENTO(rs.getBoolean("Permitir_Descuentos"));
                pro.setFechaingreso("<html>"+rs.getString("fechaingreso")+ " POR: "+rs.getString("USUARIOINGRESO")+"</html>");
                pro.setFechamodificacion("<html>"+rs.getString("fechamodificacion")+ " POR: "+rs.getString("USUARIOMODIFICO")+"</html>");
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    public void Categorias(JComboBox ComboCategorias){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                String sql= "select Categoria from categoria";
        try {
                ps=(PreparedStatement) cn.prepareStatement(sql);
                rs=ps.executeQuery();
            
            while(rs.next()){
                ComboCategorias.addItem(rs.getString("Categoria"));
            }
        } catch (SQLException e) {
                System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        
         
    }
    
    public List ListarCategoria(){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        List<Categoria> Listapr = new ArrayList();
        String sql = "SELECT idCategoria, Categoria FROM categoria ORDER BY idCategoria DESC";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Categoria Cat = new Categoria();
                Cat.setIdCategoria(rs.getInt("idCategoria"));
                Cat.setCategoria(rs.getString("Categoria"));
                Listapr.add(Cat);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Listapr;
    }
    
    public List ListarSubCategoria(int IdCategoria){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        List<SubCategoria> Listapr = new ArrayList();
        String sql = "select NombreSubCategoria from subcategoria where IdCategoria=? ORDER BY idsubcategoria DESC";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, IdCategoria);
            rs = ps.executeQuery();
            while (rs.next()) {                
                SubCategoria SubCat = new SubCategoria();
                SubCat.setNombreSubcategoria(rs.getString("NombreSubCategoria"));
                Listapr.add(SubCat);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Listapr;
    }
    
    public List ListarUbicacion(){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        List<Ubicacion> Listapr = new ArrayList();
        String sql = "SELECT NombreUbicacion FROM ubicaciones";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                Ubicacion Ubi = new Ubicacion();
                Ubi.setNombreUbicacion(rs.getString("NombreUbicacion"));
                Listapr.add(Ubi);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Listapr;
    }
    
    public void ConsultaProveedor(JComboBox ProveedoresCombo){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                String sql= "select Proveedor from proveedores";
        try {
                ps=(PreparedStatement) cn.prepareStatement(sql);
                rs=ps.executeQuery();
            
            while(rs.next()){
                ProveedoresCombo.addItem(rs.getString("Proveedor"));
            }
        } catch (SQLException e) {
                System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    public List Usuarios(){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                String sql= "select idlogin1, NombreUsuario from login1 WHERE Estado_Registro='ACTIVO'";
                
                List<Usuarios> ListaUsuarios = new ArrayList();
        try {
                ps=cn.prepareStatement(sql);
                rs=ps.executeQuery();
            
            while (rs.next()) {                
                Usuarios log = new Usuarios();
                log.setIdUsuario(rs.getInt("idlogin1"));
                log.setNombreUsuario(rs.getString("NombreUsuario"));
                ListaUsuarios.add(log);
            }
            
        } catch (SQLException e) {
                System.err.println("Error en ProductosDao / Usuarios, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return ListaUsuarios;
    }
    
    public Productos BuscarPro(String cod){
        
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
    
    String sql= "select CodigoBarras, Nombre, Cantidad, Costo, Publico, Proveedores, CodigoLetras, PrecioRe, PrecioEs, Categoria, Descripcion, ruta, Permitir_Descuentos from productos where CodigoBarras=?";
        try {
            
            ps=(PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, cod);
            rs= ps.executeQuery();
            if(rs.next()){
                producto.setCodigoBarras(rs.getString("CodigoBarras"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setCantidad(rs.getFloat("Cantidad"));
                producto.setCosto(rs.getFloat("Costo"));
                producto.setPublico(rs.getFloat("Publico"));
                producto.setIdProveedores(rs.getInt("Proveedores"));
                producto.setCodigoLetras(rs.getString("CodigoLetras"));
                producto.setPrecioRe(rs.getFloat("PrecioRe"));
                producto.setPrecioEs(rs.getFloat("PrecioEs"));
                producto.setCategoria(rs.getInt("Categoria"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setRuta(rs.getString("ruta"));
                producto.setAPLICAR_DESCUENTO(rs.getBoolean("Permitir_Descuentos"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
    public Productos BuscarProID_VENTA(String cod){
        
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        producto = new Productos();
    
    String sql= "select CodigoBarras, Nombre, Publico, PrecioRe, PrecioEs, Cantidad, Precio1, Precio2, Precio3, Descripcion, ruta, Estado_Productos, Permitir_Descuentos from productos where idProductos="+cod;
        try {
            
            ps=(PreparedStatement) cn.prepareStatement(sql);
            rs= ps.executeQuery();
            if(rs.next()){
                producto.setCodigoBarras(rs.getString("CodigoBarras"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setPublico(rs.getFloat("Publico"));
                producto.setPrecioRe(rs.getFloat("PrecioRe"));
                producto.setPrecioEs(rs.getFloat("PrecioEs"));
                producto.setCantidad(rs.getFloat("Cantidad"));
                producto.setNombreTiposDePrecio1(rs.getString("Precio1"));
                producto.setNombreTiposDePrecio2(rs.getString("Precio2"));
                producto.setNombreTiposDePrecio3(rs.getString("Precio3"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setRuta(rs.getString("ruta"));
                producto.setEstado_Producto(rs.getString("Estado_Productos"));
                producto.setAPLICAR_DESCUENTO(rs.getBoolean("Permitir_Descuentos"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
    public Productos BuscarProCodigoBarras(String cod){
        
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        producto = new Productos();
    
    String sql= "select idProductos, CodigoBarras, Nombre, Publico, PrecioRe, PrecioEs, Cantidad, Precio1, Precio2, Precio3, Descripcion, ruta, Estado_Productos, Permitir_Descuentos from productos where CodigoBarras=?";
        try {
            
            ps=(PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, cod);
            rs= ps.executeQuery();
            if(rs.next()){
                producto.setIdProductos(rs.getInt("idProductos"));
                producto.setCodigoBarras(rs.getString("CodigoBarras"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setPublico(rs.getFloat("Publico"));
                producto.setPrecioRe(rs.getFloat("PrecioRe"));
                producto.setPrecioEs(rs.getFloat("PrecioEs"));
                producto.setCantidad(rs.getFloat("Cantidad"));
                producto.setNombreTiposDePrecio1(rs.getString("Precio1"));
                producto.setNombreTiposDePrecio2(rs.getString("Precio2"));
                producto.setNombreTiposDePrecio3(rs.getString("Precio3"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setRuta(rs.getString("ruta"));
                producto.setEstado_Producto(rs.getString("Estado_Productos"));
                producto.setAPLICAR_DESCUENTO(rs.getBoolean("Permitir_Descuentos"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
    public Productos BuscarProPorNombre(String Nombre){
        
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
    
    String sql= "select idProductos, CodigoBarras, Nombre, Cantidad, Publico, PrecioRe, PrecioEs, Precio1, Precio2, Precio3, Descripcion, ruta, Estado_Productos, Permitir_Descuentos from productos where Nombre=?";
        try {
            
            ps=(PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, Nombre);
            rs= ps.executeQuery();
            if(rs.next()){
                 producto.setIdProductos(rs.getInt("idProductos"));
                producto.setCodigoBarras(rs.getString("CodigoBarras"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setCantidad(rs.getFloat("Cantidad"));
                producto.setPublico(rs.getFloat("Publico"));
                producto.setPrecioRe(rs.getFloat("PrecioRe"));
                producto.setPrecioEs(rs.getFloat("PrecioEs"));
                producto.setNombreTiposDePrecio1(rs.getString("Precio1"));
                producto.setNombreTiposDePrecio2(rs.getString("Precio2"));
                producto.setNombreTiposDePrecio3(rs.getString("Precio3"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setRuta(rs.getString("ruta"));
                producto.setEstado_Producto(rs.getString("Estado_Productos"));
                producto.setAPLICAR_DESCUENTO(rs.getBoolean("Permitir_Descuentos"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
    public Productos BuscarProId(String CodigoBarras){
        Productos producto = new Productos();
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
    String sql= """
                SELECT productos.IdProductos,productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.Publico, productos.CodigoLetras, 
                categoria.Categoria AS CategoriaNombre,
                productos.Proveedores, productos.Categoria,productos.Proveedores, Permitir_Descuentos,
                proveedores.Proveedor AS ProveedoresNombre, 
                productos.PrecioEs, productos.PrecioRe, productos.Descripcion,  productos.Precio1, productos.Precio2, productos.Precio3, productos.ruta,
                subcategoria.NombreSubCategoria AS SUBCATEGORIANOMBRE, 
                ubicaciones.NombreUbicacion AS UBICACIONNOMBRE, 
                ubicacionesTabla2.NombreUbicacion AS UBICACIONNOMBRE2 
                FROM productos 
                INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores) 
                INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) 
                INNER JOIN subcategoria ON (productos.subcategoria = subcategoria.idsubcategoria) 
                INNER JOIN ubicaciones ON (productos.Ubicacion1 = ubicaciones.idubicaciones) 
                INNER JOIN ubicaciones AS ubicacionesTabla2 ON (productos.Ubicacion2 = ubicacionesTabla2.idubicaciones) 
                WHERE productos.CodigoBarras=?""";
        try {
            
            ps=(PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, CodigoBarras);
            rs= ps.executeQuery();
            if(rs.next()){
                 producto.setIdProductos(rs.getInt("IdProductos"));
                producto.setCodigoBarras(rs.getString("CodigoBarras"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setCantidad(rs.getFloat("Cantidad"));
                producto.setCosto(rs.getFloat("Costo"));
                producto.setPublico(rs.getFloat("Publico"));
                producto.setIdProveedores(rs.getInt("Proveedores"));
                producto.setCodigoLetras(rs.getString("CodigoLetras"));
                producto.setCategoriaNombre(rs.getString("CategoriaNombre"));
                producto.setProveedorNombre(rs.getString("ProveedoresNombre"));
                producto.setPrecioRe(rs.getFloat("PrecioRe"));
                producto.setPrecioEs(rs.getFloat("PrecioEs"));
                producto.setCategoria(rs.getInt("Categoria"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setNombreTiposDePrecio1(rs.getString("Precio1"));
                producto.setNombreTiposDePrecio2(rs.getString("Precio2"));
                producto.setNombreTiposDePrecio3(rs.getString("Precio3"));
                producto.setRuta(rs.getString("ruta"));
                
                producto.setSubcategoriaNombre(rs.getString("SUBCATEGORIANOMBRE"));
                producto.setUbicacionNombre1(rs.getString("UBICACIONNOMBRE"));
                producto.setUbicacionNombre2(rs.getString("UBICACIONNOMBRE2"));
                producto.setAPLICAR_DESCUENTO(rs.getBoolean("Permitir_Descuentos"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
    public Productos BuscarProNombre(String NombreProducto){
        Productos producto = new Productos();
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
    String sql= """
                SELECT productos.IdProductos,productos.CodigoBarras, productos.Nombre, productos.Cantidad, productos.Costo, productos.Publico, productos.CodigoLetras, 
                categoria.Categoria AS CategoriaNombre,
                productos.Proveedores, productos.Categoria, productos.Proveedores, productos.Permitir_Descuentos,
                proveedores.Proveedor AS ProveedoresNombre, 
                productos.PrecioEs, productos.PrecioRe, productos.Descripcion, productos.Precio1, productos.Precio2, productos.Precio3, productos.ruta, productos.Estado_Productos,
                subcategoria.NombreSubCategoria AS SUBCATEGORIANOMBRE, 
                ubicaciones.NombreUbicacion AS UBICACIONNOMBRE, 
                ubicacionesTabla2.NombreUbicacion AS UBICACIONNOMBRE2,
                productos.fechaingreso, productos.fechamodificacion,
                Usuarios.NombreUsuario AS USUARIOINGRESO, 
                Usuarios2.NombreUsuario AS USUARIOMODIFICO
                FROM productos 
                INNER JOIN proveedores ON (productos.Proveedores = proveedores.idproveedores) 
                INNER JOIN categoria ON (productos.Categoria = categoria.idCategoria) 
                INNER JOIN subcategoria ON (productos.subcategoria = subcategoria.idsubcategoria) 
                INNER JOIN ubicaciones ON (productos.Ubicacion1 = ubicaciones.idubicaciones) 
                INNER JOIN ubicaciones AS ubicacionesTabla2 ON (productos.Ubicacion2 = ubicacionesTabla2.idubicaciones) 
                INNER JOIN login1 AS Usuarios ON (productos.UsuarioIngreso = Usuarios.idlogin1) 
                INNER JOIN login1 AS Usuarios2 ON (productos.UsuarioModifico = Usuarios2.idlogin1) 
                WHERE productos.Nombre=?""";
        try {

            ps = (PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, NombreProducto);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setIdProductos(rs.getInt("IdProductos"));
                producto.setCodigoBarras(rs.getString("CodigoBarras"));
                producto.setNombre(rs.getString("Nombre"));
                producto.setCantidad(rs.getFloat("Cantidad"));
                producto.setCosto(rs.getFloat("Costo"));
                producto.setPublico(rs.getFloat("Publico"));
                producto.setIdProveedores(rs.getInt("Proveedores"));
                producto.setCodigoLetras(rs.getString("CodigoLetras"));
                producto.setCategoriaNombre(rs.getString("CategoriaNombre"));
                producto.setProveedorNombre(rs.getString("ProveedoresNombre"));
                producto.setPrecioRe(rs.getFloat("PrecioRe"));
                producto.setPrecioEs(rs.getFloat("PrecioEs"));
                producto.setCategoria(rs.getInt("Categoria"));
                // producto.setRuta(rs.getString("ruta"));
                producto.setDescripcion(rs.getString("Descripcion"));
                producto.setNombreTiposDePrecio1(rs.getString("Precio1"));
                producto.setNombreTiposDePrecio2(rs.getString("Precio2"));
                producto.setNombreTiposDePrecio3(rs.getString("Precio3"));
                producto.setRuta(rs.getString("ruta"));

                producto.setSubcategoriaNombre(rs.getString("SUBCATEGORIANOMBRE"));
                producto.setUbicacionNombre1(rs.getString("UBICACIONNOMBRE"));
                producto.setUbicacionNombre2(rs.getString("UBICACIONNOMBRE2"));
                producto.setEstado_Producto(rs.getString("Estado_Productos"));
                producto.setAPLICAR_DESCUENTO(rs.getBoolean("Permitir_Descuentos"));
                producto.setFechaingreso("<html>"+rs.getString("fechaingreso")+ " POR: "+rs.getString("USUARIOINGRESO")+"</html>");
                producto.setFechamodificacion("<html>"+rs.getString("fechamodificacion")+ " POR: "+rs.getString("USUARIOMODIFICO")+"</html>");
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
    public Productos BuscarProIdSimplificado(String CodigoBarras){
        producto= new Productos();
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
    String sql= "SELECT IdProductos, imagen FROM productos WHERE CodigoBarras=?";
        try {
            
            ps=(PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, CodigoBarras);
            rs= ps.executeQuery();
            if(rs.next()){
                 producto.setIdProductos(rs.getInt("IdProductos"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
    public Productos BuscarProductosNombre(String Nom){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        
        String sql= "select CodigoBarras, Nombre, Cantidad, Costo, CodigoLetras, Publico, PrecioEs, PrecioRe, Proveedores, Categoria, ruta from productos where Nombre=?";
        try {
            
            ps= cn.prepareStatement(sql);
        ps.setString(1, Nom);
        rs= ps.executeQuery();
        if(rs.next()){
        producto.setCodigoBarras(rs.getString("CodigoBarras"));
        producto.setNombre(rs.getString("Nombre"));
        producto.setCantidad(rs.getFloat("Cantidad"));
        producto.setCosto(rs.getFloat("Costo"));
        producto.setCodigoLetras(rs.getString("CodigoLetras"));
        producto.setPublico(rs.getFloat("Publico"));
        producto.setPrecioEs(rs.getFloat("PrecioEs"));
        producto.setPrecioRe(rs.getFloat("PrecioRe"));
        producto.setIdProveedores(rs.getInt("Proveedores"));
        producto.setCategoria(rs.getInt("Categoria"));
        producto.setRuta(rs.getString("ruta"));
        }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en consulta, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
    
   public Productos VerStock(String cod){
        
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
    
    String sql= "select Cantidad from productos where CodigoBarras=?";
        try {
            
            ps=(PreparedStatement) cn.prepareStatement(sql);
            ps.setString(1, cod);
            rs= ps.executeQuery();
            if(rs.next()){
                producto.setCantidad(rs.getFloat("Cantidad"));
            }
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return producto;
    }
}
