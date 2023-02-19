
package Controlador;

import Modelo.Clientes;
import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.Unionsis2;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class ClientesDao extends ConexionesSQL{
    
    
    public static Clientes BuscarClie(String Nit){
            cn = Unionsis2.getConnection();
            ps = null;
            rs =null;
        
            Clientes clientes = new Clientes();
        String sql= "select Nombre, Nit, TIPO_IDENTIFICACION, Direccion, idclientes, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono from clientes where Nit=?";
        try {
            
            ps=cn.prepareStatement(sql);
            ps.setString(1, Nit);
            rs= ps.executeQuery();
            if(rs.next()){
                    clientes.setResutaldoConsulta(true);
                    clientes.setNombre(rs.getString("Nombre"));
                    clientes.setIDENTIFICACION(rs.getString("Nit"));
                    clientes.setTIPO_IDENTIFICACION(rs.getString("TIPO_IDENTIFICACION"));
                    clientes.setDireccion(rs.getString("Direccion"));
                    clientes.setIdclientes(rs.getInt("idclientes"));
                    clientes.setCodigoPostal(rs.getString("CodigoPostal"));
                    clientes.setMunicipio(rs.getString("Municipio"));
                    clientes.setDepartamento(rs.getString("Departamento"));
                    clientes.setPais(rs.getString("Pais"));
                    clientes.setCorreo(rs.getString("Correo"));
                    clientes.setTelefono(rs.getString("Telefono"));
                    clientes.setResutaldoConsulta(true);
                
            }else{
                clientes.setResutaldoConsulta(false);
            }
                
        } catch (SQLException e) {
            clientes.setResutaldoConsulta(false);
            System.err.println("¡Hubo un error en la consulta! "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return clientes;
    }
    
    public static Clientes BuscarClieNombre(String NOMBRE){
        
            cn = Unionsis2.getConnection();
            ps = null;
            rs =null;
        
            Clientes clientes = null;
        String sql= "select Nombre, Nit, TIPO_IDENTIFICACION, Direccion, idclientes, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono from clientes where Nombre=?";
        try {
            
            ps=cn.prepareStatement(sql);
            ps.setString(1, NOMBRE);
            rs= ps.executeQuery();
            if(rs.next()){
                clientes= new Clientes();
                if(rs.getString("Nombre")!=null){
                clientes.setResutaldoConsulta(true);
                clientes.setNombre(rs.getString("Nombre"));
                clientes.setIDENTIFICACION(rs.getString("Nit"));
                clientes.setTIPO_IDENTIFICACION(rs.getString("TIPO_IDENTIFICACION"));
                clientes.setDireccion(rs.getString("Direccion"));
                clientes.setIdclientes(rs.getInt("idclientes"));
                clientes.setCodigoPostal(rs.getString("CodigoPostal"));
                clientes.setMunicipio(rs.getString("Municipio"));
                clientes.setDepartamento(rs.getString("Departamento"));
                clientes.setPais(rs.getString("Pais"));
                clientes.setCorreo(rs.getString("Correo"));
                clientes.setTelefono(rs.getString("Telefono"));    
                }else{
                    clientes.setResutaldoConsulta(false);
                }
                
            }    
        } catch (SQLException e) {
            System.err.println("¡Hubo un error en la consulta! "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return clientes;
    }
    
    public List BuscarClienteLista(){
        List<Clientes> Listaclientes = new ArrayList();
            cn = Unionsis2.getConnection();
            ps = null;
            rs =null;
        
            Clientes clientes = null;
        String sql= "select Nombre, Nit, TIPO_IDENTIFICACION, idclientes from clientes ORDER BY idclientes DESC";
        try {
            
            ps=cn.prepareStatement(sql);
            rs= ps.executeQuery();
            while(rs.next()){
                clientes= new Clientes();
                clientes.setNombre(rs.getString("Nombre"));
                clientes.setIDENTIFICACION(rs.getString("Nit"));
                clientes.setTIPO_IDENTIFICACION(rs.getString("TIPO_IDENTIFICACION"));
                clientes.setIdclientes(rs.getInt("idclientes"));
                Listaclientes.add(clientes);
            }      
        } catch (SQLException e) {
            System.err.println("¡Hubo un error en la consulta! "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Listaclientes;
    }
    
    public String RetornarNit(String Nit){
        cn = Unionsis2.getConnection();
            ps = null;
            rs =null;
            String nit = null;
        String sql= "select Nit from clientes where Nit=?";
        try {
            
            ps=cn.prepareStatement(sql);
            ps.setString(1, Nit);
            rs= ps.executeQuery();
            if(rs.next()){
                nit = rs.getString("Nit");
            }      
        } catch (SQLException e) {
            System.err.println("¡Hubo un error en la consulta! "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return nit;
    }
    
    public boolean IngresoCliente(Clientes cl){
        ps = null;
        cn = Unionsis2.getConnection();
        
        
        try {
            ps = cn.prepareStatement("insert into clientes (Nombre , Nit, TIPO_IDENTIFICACION,  Direccion, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIDENTIFICACION());
            ps.setString(3, cl.getTIPO_IDENTIFICACION());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getCodigoPostal());
            ps.setString(6, cl.getMunicipio());
            ps.setString(7, cl.getDepartamento());
            ps.setString(8, cl.getPais());
            ps.setString(9, cl.getCorreo());
            ps.setString(10, cl.getTelefono());
            
            Boolean Resultado = ps.execute();
            
            if(Resultado==true){
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                      DesktopNotify.showDesktopMessage("¡REGISTRO ÉXITOSO!", "EL CLIENTE "+cl.getNombre()+" SE REGISTRÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);        
            return true;    
            }
            
        } catch (SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                      DesktopNotify.showDesktopMessage("¡REGISTRO INTERRUMPIDO!", "HUBO UN ERROR EN EL REGISTRO, PROBLEMA DE SISTEMA " + e, DesktopNotify.FAIL, 14000L);  
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
        return false;
    }
    
    
    public boolean EditarCliente(Clientes cl){
        ps = null;
              cn = Unionsis2.getConnection();
        try {
            ps = cn.prepareStatement("update clientes set Nombre=?, Nit=?, TIPO_IDENTIFICACION=?, Direccion=?, CodigoPostal=?, Municipio=?, Departamento=?, Pais=?, Correo=?, Telefono=? where idclientes=?");
            ps.setString(1, cl.getNombre());
            ps.setString(2, cl.getIDENTIFICACION());
            ps.setString(3, cl.getTIPO_IDENTIFICACION());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getCodigoPostal());
            ps.setString(6, cl.getMunicipio());
            ps.setString(7, cl.getDepartamento());
            ps.setString(8, cl.getPais());
            ps.setString(9, cl.getCorreo());
            ps.setString(10, cl.getTelefono());
            ps.setInt(11, cl.getIdclientes());
            int Resultado=ps.executeUpdate();
            
            if(Resultado >=1){
            }
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                      DesktopNotify.showDesktopMessage("¡EDICIÓN ÉXITOSA!", "EL CLIENTE "+cl.getIDENTIFICACION()+" SE EDITÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);
                return true;
                
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la modificación!");
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public boolean EliminarCliente(Clientes cl){     
        cn = Unionsis2.getConnection();
        ps = null;
        try {
            
            
             ps = cn.prepareStatement("delete from clientes where idclientes=?");
                ps.setInt(1, cl.getIdclientes());
                Boolean Resultado =  ps.execute();       
                if(Resultado==true){
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                      DesktopNotify.showDesktopMessage("¡REGISTRO ÉXITOSO!", "EL CLIENTE "+cl.getNombre()+" SE REGISTRÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);        
                
            }
            return true;    
        } catch (SQLException e) {
            System.err.println("Error,"+e);
            JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la eliminación del Cliente!");
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public void SeleccionarClienteTabla(Clientes cli){
        ps = null;
        rs =null;
        cn = Unionsis2.getConnection();
        try {
            ps = cn.prepareStatement("select idclientes, Nombre, Nit, TIPO_IDENTIFICACION, Direccion, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono from clientes where Nombre=?");
            ps.setString(1, cli.getNombre());
            rs = ps.executeQuery();

            while (rs.next()) {
                cli.setNombre(rs.getString("Nombre"));
                cli.setIDENTIFICACION(rs.getString("Nit"));
                cli.setTIPO_IDENTIFICACION(rs.getString("TIPO_IDENTIFICACION"));
                cli.setDireccion(rs.getString("Direccion"));
                cli.setIdclientes(rs.getInt("idclientes"));
                cli.setCodigoPostal(rs.getString("CodigoPostal"));
                cli.setMunicipio(rs.getString("Municipio"));
                cli.setDepartamento(rs.getString("Departamento"));
                cli.setPais(rs.getString("Pais"));
                cli.setCorreo(rs.getString("Correo"));
                cli.setTelefono(rs.getString("Telefono"));
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    public static void BuscarClienteTabla(Clientes cli){
        ps = null;
        rs =null;
        cn = Unionsis2.getConnection();
        try {
            ps = cn.prepareStatement("select idclientes, Nombre, Nit, TIPO_IDENTIFICACION, Direccion, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono from clientes where idclientes=?");
            ps.setInt(1, cli.getIdclientes());
            rs = ps.executeQuery();

            while (rs.next()) {
                cli.setNombre(rs.getString("Nombre"));
                cli.setIDENTIFICACION(rs.getString("Nit"));
                cli.setTIPO_IDENTIFICACION(rs.getString("TIPO_IDENTIFICACION"));
                cli.setDireccion(rs.getString("Direccion"));
                cli.setIdclientes(rs.getInt("idclientes"));
                cli.setCodigoPostal(rs.getString("CodigoPostal"));
                cli.setMunicipio(rs.getString("Municipio"));
                cli.setDepartamento(rs.getString("Departamento"));
                cli.setPais(rs.getString("Pais"));
                cli.setCorreo(rs.getString("Correo"));
                cli.setTelefono(rs.getString("Telefono"));
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    public void Pais(JComboBox ComboPaises){
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
                String sql= "select nombre from paises";
        try {
                ps=cn.prepareStatement(sql);
                rs=ps.executeQuery();
            
            while(rs.next()){
                ComboPaises.addItem(rs.getString("nombre").toUpperCase());
            }
        } catch (SQLException e) {
                System.err.println("Error, "+e);
        }finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        } 
    }
    
    public synchronized String ConsultaSiglaPais(JComboBox ComboPais) {
        String Resultado = null;
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        String seleccion = String.valueOf(ComboPais.getSelectedItem());

        String sql = "SELECT iso FROM paises WHERE nombre=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, seleccion);
            rs = ps.executeQuery();
            if (rs.next()) {
                Resultado = rs.getString("iso");
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Resultado;
    }
}
