/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Controlador.Categoria_SubCategorias_Ubicaciones_DAO;
import Modelo.Categoria;
import Modelo.SubCategoria;
import Modelo.Ubicacion;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author aldoy
 */
public class ActualizarTablasCategorias_SubCategorias_Ubicaciones extends ConexionesSQL{
    
    public List ActualizarCategorias(String Valor, Boolean Buscar) {
        JButton btn1 = new JButton();
        btn1.setName("botonsubcategorias");
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/SubCategoria.png"));
        ImageIcon ro = new ImageIcon(retValue);
        btn1.setIcon(ro);
      List<Categoria> Listapro = new ArrayList();
      String sql="";
      if(Buscar == true){
       sql="select idCategoria, Categoria from categoria where Categoria LIKE '%' '"+Valor+"' '%'";   
      }else{
          sql="select idCategoria, Categoria from categoria ORDER BY idCategoria DESC";   
      }
      try {
          Categoria cat;
          cn= Unionsis2.getConnection();
          ps = null;
          rs = null;
          
          
          ps = cn.prepareStatement(sql);
          rs = ps.executeQuery();
          
          while (rs.next()) {
              cat = new Categoria();
              cat.setIdCategoria(rs.getInt("idCategoria"));
              cat.setCategoria(rs.getString("Categoria"));
              cat.setVerSubCategoria(btn1);
              Listapro.add(cat);
          }
          
      } catch (SQLException ex) {
          javax.swing.JOptionPane.showMessageDialog(null, "ERROR AL CONSULTAR LAS CATEGORIAS EN LA BASE DE DATOS\n "+ex, "ERROR CON BASE DE DATOS", JOptionPane.ERROR_MESSAGE);
          Logger.getLogger(Categoria_SubCategorias_Ubicaciones_DAO.class.getName()).log(Level.SEVERE, null, ex);
      }finally{
          RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
      }
                  
      return Listapro;
    } 

    public List ActualizarUbicaciones() {
      List<Ubicacion> Listapro = new ArrayList();
      try {
          Ubicacion ub;
          cn= Unionsis2.getConnection();
          ps = null;
          rs = null;
          
          
          ps = cn.prepareStatement("select idubicaciones, NombreUbicacion, DetalleUbicacion from ubicaciones");
          rs = ps.executeQuery();
          
          while (rs.next()) {
              ub = new Ubicacion();
              ub.setIdubicaciones(rs.getInt("idubicaciones"));
              ub.setNombreUbicacion(rs.getString("NombreUbicacion"));
              Listapro.add(ub);
          }
          
      } catch (SQLException ex) {
          javax.swing.JOptionPane.showMessageDialog(null, "ERROR AL CONSULTAR LAS UBICACIONES EN LA BASE DE DATOS\n "+ex, "ERROR CON BASE DE DATOS", JOptionPane.ERROR_MESSAGE);
          Logger.getLogger(Categoria_SubCategorias_Ubicaciones_DAO.class.getName()).log(Level.SEVERE, null, ex);
      }finally{
                  RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
                  }
      return Listapro;
    } 
    
    public List ActualizarSubCategorias(String IdCategoria) {
        JButton btn1 = new JButton();
        btn1.setName("eliminarsubcategoria");
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/delete_40623.png"));
        ImageIcon ro = new ImageIcon(retValue);
        btn1.setIcon(ro);
      List<SubCategoria> Listapro = new ArrayList();
      try {
          SubCategoria Subcat;
          cn= Unionsis2.getConnection();
          ps = null;
          rs = null;
          
          
          ps = cn.prepareStatement("select idsubcategoria, NombreSubCategoria from subcategoria where IdCategoria=? ORDER BY idsubcategoria DESC");
          ps.setInt(1, Integer.parseInt(IdCategoria));
          rs = ps.executeQuery();
          
          while (rs.next()) {
              Subcat = new SubCategoria();
              Subcat.setIdsubcategoria(rs.getInt("idsubcategoria"));
              Subcat.setNombreSubcategoria(rs.getString("NombreSubCategoria"));
              Subcat.setEliminar(btn1);
              Listapro.add(Subcat);
          }
          
      } catch (SQLException ex) {
          javax.swing.JOptionPane.showMessageDialog(null, "ERROR AL CONSULTAR LAS SUBCATEGORIAS EN LA BASE DE DATOS\n "+ex, "ERROR CON BASE DE DATOS", JOptionPane.ERROR_MESSAGE);
          Logger.getLogger(Categoria_SubCategorias_Ubicaciones_DAO.class.getName()).log(Level.SEVERE, null, ex);
      }finally{
                  RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
                  }
      return Listapro;
    } 
}
