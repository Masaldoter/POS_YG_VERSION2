
package Gmail;

import Modelo.DatosEmpresaGeneral;
import Conexiones.ConexionesSQL;
import java.sql.SQLException;

public class CargarDatosGmail extends ConexionesSQL{
    
    
    public DatosEmpresaGeneral CargarDatosEmpresa(){
        DatosEmpresaGeneral empresa= new DatosEmpresaGeneral();
        cn= Unionsis2.getConnection();
        ps=null;
        rs=null;
        String sql="SELECT NombreEmpresa, Direccion, Correo, ContraseniaCorreo FROM datosempresa";
        try {
            ps= cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()){
                empresa.setNombreEmpresa(rs.getString("NombreEmpresa"));
                empresa.setDireccion(rs.getString("Direccion"));
                empresa.setCorreo(rs.getString("Correo"));
                empresa.setContrasenia(rs.getString("ContraseniaCorreo"));
            }
            
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Error en Clase CargarDatosGmail/CargarDatosEmpresa "+ e);
        }
        return empresa;
    }
}
