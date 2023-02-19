/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tablas;

import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import java.sql.SQLException;
import Conexiones.conexion;
import Modelo.Kardex;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aldoy
 */
public class ACTUALIZAR_KARDEX {
    
    public List CONSULTAR_KARDEX(String valor, int TipoConsulta, Boolean IMPORTAR_ORDEN) {
        /*
        1: LISTAR
        2: CONSULTA POR CODIGO DE BARRAS NOMBRE Y DESCRIPCIÓN
        3: CONSULTA POR USUARIO
        4: CONSULTA POR MODULO
        */
        String SQL = "select kardex.idkardex, kardex.Codigo_Producto_Kardex, kardex.Titulo_Kardex, Entrada_Kardex, Salida_Kardex, kardex.Antes_Kardex, kardex.Despues_Kardex, "
                + "productos.Nombre AS Nombre, "
                + "productos.CodigoBarras AS CodigoBarras, "
                + "productos.Cantidad AS STOCK,"
                + "login1.NombreUsuario AS Nombre_Usuario, "
                + "kardex.Usuario_Modifico_Kardex, "
                + "kardex.Fecha_Modificacion_Kardex, kardex.Modulo_Kardex "
                + "from kardex "
                + "INNER JOIN productos ON (kardex.Codigo_Producto_Kardex = productos.idProductos) "
                + "INNER JOIN productos AS productos2 ON (kardex.Codigo_Producto_Kardex = productos2.idProductos) "
                + "INNER JOIN login1  ON (kardex.Usuario_Modifico_Kardex = login1.idlogin1) ";
        switch (TipoConsulta) {
            case 1:
                SQL = SQL+" ORDER BY kardex.idkardex DESC";
                break;
            case 2:
                if(IMPORTAR_ORDEN==true){
                  SQL = SQL+"WHERE (productos.CodigoBarras LIKE '%' '" + valor + "' '%' OR productos.Nombre LIKE '%' '" + valor + "' '%' or productos.Descripcion LIKE '%' '" + valor + "' '%') "
                          + "OR (kardex.Titulo_Kardex LIKE '%' '" + valor + "' '%' OR kardex.Fecha_Modificacion_Kardex LIKE '%' '" + valor + "' '%') ORDER BY kardex.idkardex DESC";  
                }else{
                  SQL = SQL+"WHERE MATCH(productos.CodigoBarras, productos.Nombre, productos.Descripcion) AGAINST('"+valor+"' IN NATURAL LANGUAGE MODE) ORDER BY kardex.idkardex DESC";  
                }
                break;
            /*case 3:
               SQL = SQL+"WHERE Usuario_Modifico_Kardex="+USUARIOS;
                break;
            case 4:
                SQL = SQL+"(productos.Cantidad <="+valor+") AND" +ESTADO_REGISTRO;
                break;  */   
                        default:
                break;
        }
        rs = null;
        ps = null;
        List<Kardex> Listapro = new ArrayList();
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Kardex Kd;
             ps = cn.prepareStatement(SQL);
            rs = ps.executeQuery();

            while (rs.next()) {
                Kd= new Kardex();
                Kd.setIdkardex(rs.getInt("kardex.idkardex"));
                Kd.setID_Codigo_Producto_Kardex(rs.getInt("kardex.Codigo_Producto_Kardex"));
                Kd.setCodigo_Producto_NOMBRE_Kardex(rs.getString("CodigoBarras"));
                Kd.setNOMBRE_Producto_NOMBRE_Kardex(rs.getString("Nombre"));
                Kd.setTitulo_Kardex(rs.getString("kardex.Titulo_Kardex"));
                Kd.setEntrada_Kardex(rs.getString("kardex.Entrada_Kardex"));
                Kd.setSalida_Kardex(rs.getString("kardex.Salida_Kardex"));
                Kd.setAntes_Kardex(rs.getString("kardex.Antes_Kardex"));
                Kd.setDespues_Kardex(rs.getString("kardex.Despues_Kardex"));
                Kd.setSTOCK_PRODUCTO_KARDEX(rs.getString("STOCK"));
                Kd.setFecha_Modificacion_Kardex(rs.getString("kardex.Fecha_Modificacion_Kardex"));
                Kd.setUsuario_Modifico_Kardex(rs.getInt("kardex.Usuario_Modifico_Kardex"));
                Kd.setUsuario_Modifico_LETRAS_Kardex(rs.getString("Nombre_Usuario"));
                Kd.setModulo_Kardex(rs.getString("kardex.Modulo_Kardex"));
               Listapro.add(Kd);
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
    
}
