
package Clases_Reportes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import Conexiones.conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class codigos2 {
  conexion cn = conexion.getInstancia();
  PreparedStatement ps;
    ResultSet rs;
    Codigos codigos;
    
    public List<Codigos> leertodo(){
     List <Codigos> lista = new ArrayList<>();   
        try {
            String sql= "Select from Productos";
            Connection c= cn.getConnection();
            ps= c.prepareStatement(sql);
            rs= ps.executeQuery();
            
            while(rs.next()){
                codigos.getId();
                codigos.getNombreproducto();
                codigos.getPublico();
                lista.add(codigos);
            }
            c.close();
        } catch (SQLException e) {
            System.out.println("Error en codigos2"+e);
        }
      return lista;
    }
}
