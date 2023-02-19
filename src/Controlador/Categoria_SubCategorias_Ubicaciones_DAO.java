package Controlador;

import Conexiones.ConexionesSQL;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Categoria_SubCategorias_Ubicaciones_DAO extends ConexionesSQL{
  
    
    public Boolean RegistrarUbicacion(String Valor){
        Boolean Entrada = false;
        cn= Unionsis2.getConnection();
        ps = null;
            try {
                ps = cn.prepareStatement("INSERT INTO ubicaciones (NombreUbicacion) VALUES (?)");
                ps.setString(1, Valor);
                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    Entrada = true;
                    JOptionPane.showMessageDialog(null, "¡UBICACION "+Valor+" REGISTRADA EXITOSAMENTE!");         
                } else {
                    Entrada = false;
                    JOptionPane.showMessageDialog(null, "¡Hubo un error en el registro!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR EN REGISTRAR UBICACIÓN, " + ex);           
                Entrada = false;
            }finally{
            try {
                ps.close();
                cn.close();
            } catch (SQLException e) {
            }
        }
        return Entrada;
    }
    
    public Boolean EliminarUbicacion(String Valor){
        Boolean EntradaEliminacion = false;
        cn= Unionsis2.getConnection();
        ps = null;
        if(Valor != null){
        int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la Ubicacion "+Valor+"?\n¡ES POSIBLE QUE UNOS PRODUCTOS QUE ESTÉN CON ESTA UBICACION NO FUNCIONEN CORRECTAMENTE!", "IMPORTANTE", JOptionPane.WARNING_MESSAGE);

        if (i == 0) {
            try {
                ps = cn.prepareStatement("delete from ubicaciones where idubicaciones=?");
                ps.setInt(1, Integer.parseInt(Valor));
                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "¡UBICACION ELIMINADA CORRECTAMENTE!");
                    EntradaEliminacion = true;
                }
//QUINTAL DE ALAMBRE
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR LA UBICACIÓN, " + e);
                EntradaEliminacion = false;
            }finally{
            try {
                ps.close();
                cn.close();
            } catch (SQLException e) {
            }
        }    
        }else{
            JOptionPane.showMessageDialog(null, "¡Debe seleccionar una Categoría!");
        }
        }
        return EntradaEliminacion;
    }
    
    public Boolean EditarUbicacion(String Valor, String Valor2, String Id){
        
        Boolean EntradaEditar = false;
        cn= Unionsis2.getConnection();
        ps = null;

            try {
                ps = cn.prepareStatement("update ubicaciones set NombreUbicacion=?, DetalleUbicacion=? where idubicaciones=?");
                ps.setString(1, Valor);
                ps.setString(2, Valor2);
                ps.setInt(3, Integer.parseInt(Id));

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "¡ÚBICACIÓN "+Valor+ " EDITADA CORRECTAMENTE!");
                    EntradaEditar = true;
                } else {
                    JOptionPane.showMessageDialog(null, "¡HUBO UN ERROR AL EDITAR LA ÚBICACIÓN!");
                    EntradaEditar = false;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR AL EDITAR LA UBICACIÓN, " + e);
                EntradaEditar = false;
            }finally{
            try {
                ps.close();
                cn.close();
            } catch (SQLException e) {
            }
        }
            return EntradaEditar;
    }
    
    public Boolean RegistrarSubCategoria(String Valor, String IdCategoria){
        Boolean Entrada = false;
        cn= Unionsis2.getConnection();
        ps = null;
            try {
                ps = cn.prepareStatement("INSERT INTO subcategoria (NombreSubCategoria, IdCategoria) VALUES (?, ?)");
                ps.setString(1, Valor);
                ps.setInt(2, Integer.parseInt(IdCategoria));
                
                int resultado = ps.executeUpdate();
                if (resultado > 0) {
                    Entrada = true;
                    JOptionPane.showMessageDialog(null, "¡SUBCATEGORÍA "+Valor+" REGISTRADA EXITOSAMENTE!");         
                } else {
                    Entrada = false;
                    JOptionPane.showMessageDialog(null, "¡HUBO UN ERROR EN EL REGISTRO DE LA SUBCATEGORÍA!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR EN REGISTRAR LA SUBCATEGORÍA, " + ex);           
                Entrada = false;
            }finally{
            try {
                ps.close();
                cn.close();
            } catch (SQLException e) {
            }
        }
        return Entrada;
    }
    
    public Boolean EditarSubCategoria(String NombreSubCategoria, String idSubCategoria){
        
        Boolean EntradaEditar = false;
        cn= Unionsis2.getConnection();
        ps = null;

            try {
                ps = cn.prepareStatement("update subcategoria set NombreSubCategoria=? where idsubcategoria=?");
                ps.setString(1, NombreSubCategoria);
                ps.setInt(2, Integer.parseInt(idSubCategoria));

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "¡SUBCATEGORÍA "+NombreSubCategoria+ " EDITADA CORRECTAMENTE!");
                    EntradaEditar = true;
                } else {
                    JOptionPane.showMessageDialog(null, "¡HUBO UN ERROR AL EDITAR LA SUBCATEGORÍA!");
                    EntradaEditar = false;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR AL EDITAR LA SUBCATEGORÍA, " + e);
                EntradaEditar = false;
            }finally{
            try {
                ps.close();
                cn.close();
            } catch (SQLException e) {
            }
        }
            return EntradaEditar;
    }
    
    /*public Boolean AgregarSubCategoriaACategoria(int IdCategoria, int Subcategoria, String NombreCategoria){
        
        Boolean EntradaEditar = false;
        cn= Unionsis2.getConnection();
        ps = null;

            try {
                ps = cn.prepareStatement("update categoria set Subcategoria=? where idCategoria=?");
                ps.setInt(1, Subcategoria);
                ps.setInt(2, IdCategoria);

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "¡SUBCATEGORÍA AGREGADA A  "+NombreCategoria+ " CORRECTAMENTE!");
                    EntradaEditar = true;
                } else {
                    JOptionPane.showMessageDialog(null, "¡HUBO UN ERROR AL AGREGAR LA SUBCATEGORÍA!");
                    EntradaEditar = false;
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR AL AGREGAR LA SUBCATEGORÍA, " + e);
                EntradaEditar = false;
            }finally{
            try {
                ps.close();
                cn.close();
            } catch (SQLException e) {
            }
        }
            return EntradaEditar;
    }
    */
    public Boolean EliminarSubCategoria(String Valor){
        Boolean EntradaEliminacion = false;
        cn= Unionsis2.getConnection();
        ps = null;
        if(Valor != null){
        int i = JOptionPane.showConfirmDialog(null, "¿SEGURO QUE DESEA ELIMINAR LA SUBCATEGORÍA "+Valor+"?\n¡ES POSIBLE QUE UNOS PRODUCTOS QUE ESTÉN CON ESTA SUBCATEGORIA NO FUNCIONEN CORRECTAMENTE!", "IMPORTANTE", JOptionPane.WARNING_MESSAGE);

        if (i == 0) {
            try {
                ps = cn.prepareStatement("delete from subcategoria where idsubcategoria=?");
                ps.setInt(1, Integer.parseInt(Valor));
                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "¡SUBCATEGORÍA ELIMINADA CORRECTAMENTE!");
                    EntradaEliminacion = true;
                }
//QUINTAL DE ALAMBRE
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR LA SUBCATEGORÍA, " + e);
                EntradaEliminacion = false;
            }finally{
            try {
                ps.close();
                cn.close();
            } catch (SQLException e) {
            }
        }    
        }
        }
        return EntradaEliminacion;
    }
    
}
