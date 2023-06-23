
package Controlador;

import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Modelo.login;
import Modelo.DatosEmpresaGeneral;
import Conexiones.ConexionesSQL;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Masaldoter
 */
public class loginDao extends ConexionesSQL{
    Date fech = new Date();
    login l= new login();
    String strDateFormat = "dd-MM-YYYY";
    SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
    String fecha = objSDF.format(fech );

    public String Hora() {
        Date hour = new Date();
        String strDateFormat = "hh:mm:ss a";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String hora = objSDF.format(hour);

        return hora;
    }
    
    public static String RetornarNombreUsuarioDuplicados(String Parametro) {
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        String sql = "";
        String Datos = "";
            sql = "SELECT Nombre from login1 WHERE Nombre=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, Parametro);
            rs = ps.executeQuery();
            if(rs.next()){
                
            Datos = rs.getString("Nombre");
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Datos;
    }

    public String VerRutaUsuario(String idUsuario){

         ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        String Ruta="";
        String sql="SELECT ruta FROM login1 WHERE idlogin1=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, idUsuario);
            rs = ps.executeQuery();
            
            if(rs.next()){
                Ruta = rs.getString("ruta");
            }
        } catch (SQLException e) {
            System.out.println("No existe ruta! " +e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
       return Ruta;
        
    }

    public login log(String Nombre, String Contraseña){    
        ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        
        String sql="select Nombre, Contraseña, Rol, NombreUsuario, idlogin1, Estado, Estado_Registro, Color, Imagen from login1 where Nombre=? AND Contraseña=?";
        try {
            ps= cn.prepareStatement(sql);
            ps.setString(1, Nombre);
            ps.setString(2, Contraseña);
            
            ps.executeQuery();
            rs=ps.executeQuery();
            
            if(rs.next()){
                l.setNombre(rs.getString("Nombre"));
                l.setContraseña(rs.getString("Contraseña"));
                l.setRol(rs.getString("Rol"));
                l.setNombreUsuario(rs.getString("NombreUsuario"));
                l.setIdlogin1(rs.getInt("idlogin1"));
                l.setEstado(rs.getString("Estado"));
                l.setEstado_Registro(rs.getString("Estado_Registro"));
                l.setColor(rs.getString("Color"));
                PARAMETROS_USUARIOS.ID_USUARIO=rs.getInt("idlogin1");
                PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO=rs.getString("NombreUsuario");   
                PARAMETROS_USUARIOS.NOMBRE_USUARIO=rs.getString("Nombre");
                PARAMETROS_USUARIOS.CONTRASENIA_USUARIO=rs.getString("Contraseña");
                PARAMETROS_USUARIOS.ROL_USUARIO=rs.getString("Rol");
                PARAMETROS_USUARIOS.ESTADO_USUARIO=rs.getString("Estado_Registro");
                PARAMETROS_USUARIOS.COLOR_USUARIO = rs.getString("Color");
                PARAMETROS_USUARIOS.RUTAIMAGEN_USUARIO = rs.getString("Imagen");
                
            }

        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return l;
    }
    
    public List VER_USUARIOS_ACCESOS_DIRECTOS(){
        List<String> lista = new ArrayList<>();
       ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        
        String sql="select Nombre, Contraseña, Rol, NombreUsuario, idlogin1, Estado, Estado_Registro, Color, Imagen from login1 where AccesoDirecto=?";
        try {
            ps= cn.prepareStatement(sql);
            ps.setString(1, "true");
            
            ps.executeQuery();
            rs=ps.executeQuery();
            
            while(rs.next()){
                lista.add(String.valueOf(rs.getInt("idlogin1")));
                l.setNombre(rs.getString("Nombre"));
                l.setContraseña(rs.getString("Contraseña"));
                l.setImagen(rs.getString("Imagen"));
                l.setIdlogin1(rs.getInt("idlogin1"));
            }

        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return lista; 
    }
    
    public login ABRIR_SESION_CON_ID(String ID){    
        ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        
        String sql="select Nombre, Contraseña, Rol, NombreUsuario, idlogin1, Estado, Estado_Registro, Color, Imagen from login1 where idlogin1=?";
        try {
            ps= cn.prepareStatement(sql);
            ps.setString(1, ID);
            
            ps.executeQuery();
            rs=ps.executeQuery();
            
            while(rs.next()){
                l.setNombre(rs.getString("Nombre"));
                l.setContraseña(rs.getString("Contraseña"));
                l.setRol(rs.getString("Rol"));
                l.setNombreUsuario(rs.getString("NombreUsuario"));
                l.setIdlogin1(rs.getInt("idlogin1"));
                l.setEstado(rs.getString("Estado"));
                l.setEstado_Registro(rs.getString("Estado_Registro"));
                l.setColor(rs.getString("Color"));
                l.setImagen(rs.getString("Imagen"));
                PARAMETROS_USUARIOS.ID_USUARIO=rs.getInt("idlogin1");
                PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO=rs.getString("NombreUsuario");   
                PARAMETROS_USUARIOS.NOMBRE_USUARIO=rs.getString("Nombre");
                PARAMETROS_USUARIOS.CONTRASENIA_USUARIO=rs.getString("Contraseña");
                PARAMETROS_USUARIOS.ROL_USUARIO=rs.getString("Rol");
                PARAMETROS_USUARIOS.ESTADO_USUARIO=rs.getString("Estado_Registro");
                PARAMETROS_USUARIOS.COLOR_USUARIO = rs.getString("Color");
                PARAMETROS_USUARIOS.RUTAIMAGEN_USUARIO = rs.getString("Imagen");
                
            }

        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return l;
    }
    
    public void VerDatosTablaLogin(login log){
        ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        try {
            ps = cn.prepareStatement("select idlogin1, Nombre, Contraseña, Rol, NombreUsuario, Color, Estado_Registro, AccesoDirecto, Imagen from login1 where idlogin1=?");
            ps.setInt(1, log.getIdlogin1());
            rs = ps.executeQuery();
            
            while(rs.next()){
            log.setIdlogin1(rs.getInt("idlogin1"));
                log.setNombre(rs.getString("Nombre"));
                log.setRol(rs.getString("Rol"));
                log.setContraseña(rs.getString("Contraseña"));
                log.setNombreUsuario(rs.getString("NombreUsuario"));
                log.setColor(rs.getString("Color"));
                log.setEstado_Registro(rs.getString("Estado_Registro"));
                log.setAccesoDirecto(rs.getString("AccesoDirecto"));
                log.setImagen(rs.getString("Imagen"));
            }
        } catch (SQLException e) {
            System.out.println("Error en Método Visualizar, "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    public Boolean Registrar(login reg){
        
        ps = null;
        cn = Unionsis2.getConnection();
        String slq= "insert into login1 (Nombre, Contraseña, Rol, NombreUsuario, FechaIngreso, UltimaVezIngreso, Ingreso,  Color, Estado_Registro, AccesoDirecto, Imagen) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
           ps=  cn.prepareStatement(slq);
           ps.setString(1,reg.getNombre());
           ps.setString(2,reg.getContraseña());
           ps.setString(3,reg.getRol());
           ps.setString(4, reg.getNombreUsuario());
            ps.setString(5, fecha);
           ps.setString(6, "NO SE HA INGRESADO");
           ps.setString(7, "NO SE HA INGRESADO");
           ps.setString(8, reg.getColor());
           ps.setString(9, reg.getEstado_Registro());
           ps.setString(10, reg.getAccesoDirecto());
           ps.setString(11, reg.getImagen());
           Boolean Resultado = ps.execute();
           if(Resultado==true){
           DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("¡REGISTRO ÉXITOSO!", "EL USUARIO "+reg.getNombre()+" SE REGISTRÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
           return true;
           }
           
           
        } catch (SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("¡ÉRROR AL REGISTRAR!", "EL USUARIO "+reg.getNombre()+" NO SE PUDO REGISTRAR\n"+e, DesktopNotify.FAIL, 14000L);
            
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }return false;
    }
    
    public Boolean MovimientosUsuarios(int IdLogin){
        
        ps = null;
                cn = Unionsis2.getConnection();
        
        String slq= "update login1 set Ingreso=?, UltimaVezIngreso=?, Estado=? where idlogin1=?";
        try {
           ps=  cn.prepareStatement(slq);
           ps.setString(1,Hora()+ " / "+ fecha);
           ps.setString(2,Hora()+ " / "+ fecha);
           ps.setString(3, "ONLINE");
           ps.setInt(4, IdLogin);
           ps.executeUpdate();
           
           return true;
           
        } catch (SQLException e) {
            System.err.println("Error, "+e);
            
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }return false;
    }
    
    public Boolean EditarUsuarios(login reg){
        
        ps = null;
                cn = Unionsis2.getConnection();
        
        String slq= "update login1 set Nombre=?, Contraseña=?, Rol=?, NombreUsuario=?, Color=?, Estado_Registro=?, AccesoDirecto=?, Imagen=? where idlogin1=?";
        try {
           ps=  cn.prepareStatement(slq);
           ps.setString(1,reg.getNombre());
           ps.setString(2, reg.getContraseña());
            ps.setString(3, reg.getRol());
            ps.setString(4, reg.getNombreUsuario());
            ps.setString(5, reg.getColor());
            ps.setString(6, reg.getEstado_Registro());
            ps.setString(7, reg.getAccesoDirecto());
            ps.setString(8, reg.getImagen());
            ps.setInt(9, reg.getIdlogin1());
            Boolean Resultado = ps.execute();
            if (Resultado == true) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("¡EDICÓN ÉXITOSA!", "EL USUARIO "+reg.getNombre()+" SE EDITÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
           return true;
           }
        } catch (SQLException e) {
            System.err.println("Error, "+e);
            
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
return false;
    }
    
    public Boolean EditarUsuariosSinImagen(login reg){
        
        ps = null;
                cn = Unionsis2.getConnection();

        String slq = "update login1 set Nombre=?, Contraseña=?, Rol=?, NombreUsuario=?, Color=?, Estado_Registro=?, AccesoDirecto=?, Imagen=? where idlogin1=?";
        try {
            ps = cn.prepareStatement(slq);
            ps.setString(1, reg.getNombre());
            ps.setString(2, reg.getContraseña());
            ps.setString(3, reg.getRol());
            ps.setString(4, reg.getNombreUsuario());
            ps.setString(5, reg.getColor());
            ps.setString(6, reg.getEstado_Registro());
            ps.setString(7, reg.getAccesoDirecto());
            ps.setString(8, reg.getImagen());
            ps.setInt(9, reg.getIdlogin1());
            Boolean Resultado = ps.execute();
            if (Resultado == true) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("¡EDICÓN ÉXITOSA!", "EL USUARIO "+reg.getNombre()+" SE EDITÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
           return true;
           }
           
        } catch (SQLException e) {
            System.err.println("Error, "+e);
            
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }return false;
    }
    
    
    public Boolean EditarUsuarios2(int IdLogin){
        
        ps = null;
                cn = Unionsis2.getConnection();
        
        String slq= "update login1 set UltimaVezIngreso=?, Estado=? where idlogin1=?";
        String Fechas= Hora() +" / "+ fecha;
        try {
           ps=  cn.prepareStatement(slq);
           ps.setString(1,Fechas);
           ps.setString(2, "OFFLINE");
           ps.setInt(3, IdLogin);
           ps.executeUpdate();
           
           return true;
           
        } catch (SQLException e) {
            System.err.println("Error, "+e);
            
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }return false;
    }
    
    public Boolean EliminarUsuario(login reg){
        
        ps = null;
                cn = Unionsis2.getConnection();
        
        String slq= "delete from login1 where idlogin1=?";
        
        try {
           ps=  cn.prepareStatement(slq);
           ps.setInt(1, reg.getIdlogin1());
           Boolean Resultado = ps.execute();
           if(Resultado==true){
           DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("¡ELIMINACIÓN ÉXITOSA!", "EL USUARIO "+reg.getNombre()+" SE ELIMINÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
           
           }
           return true;
        } catch (SQLException e) {
            System.err.println("Error, "+e);
            return false;
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public DatosEmpresaGeneral VerDatosEmpresaEnLogin(){
              ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        DatosEmpresaGeneral DE = null;
        String sql="SELECT NombreEmpresa, Eslogan, rutaimagenlogo, rutaimagensistema from datosempresa";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if(rs.next()){
              DE= new DatosEmpresaGeneral();
             DE.setNombreEmpresa(rs.getString("NombreEmpresa"));
             DE.setEslogan(rs.getString("Eslogan"));
             DE.setRutaimagenlogo(rs.getString("rutaimagenlogo"));
             DE.setRutaimagensistema(rs.getString("rutaimagensistema"));
            }
            
        } catch (SQLException e) {
            System.out.println("Error en Ver Datos de Empresa, conexión fallida \n"+ e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return DE;
    }
    
}
