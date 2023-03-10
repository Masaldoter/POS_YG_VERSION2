
package Controlador;

import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Modelo.login;
import Modelo.DatosEmpresaGeneral;
import Conexiones.ConexionesSQL;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public login log(String Nombre, String Contrase??a){    
        ps = null;
                rs = null;
                cn = Unionsis2.getConnection();
        
        String sql="select Nombre, Contrase??a, Rol, NombreUsuario, idlogin1, Estado from login1 where Nombre=? AND Contrase??a=?";
        try {
            ps= cn.prepareStatement(sql);
            ps.setString(1, Nombre);
            ps.setString(2, Contrase??a);
            
            ps.executeQuery();
            rs=ps.executeQuery();
            
            if(rs.next()){
                l.setNombre(rs.getString("Nombre"));
                l.setContrase??a(rs.getString("Contrase??a"));
                l.setRol(rs.getString("Rol"));
                l.setNombreUsuario(rs.getString("NombreUsuario"));
                l.setIdlogin1(rs.getInt("idlogin1"));
                l.setEstado(rs.getString("Estado"));
                PARAMETROS_USUARIOS.ID_USUARIO=rs.getInt("idlogin1");
                PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO=rs.getString("NombreUsuario");   
                PARAMETROS_USUARIOS.NOMBRE_USUARIO=rs.getString("Nombre");
                PARAMETROS_USUARIOS.CONTRASENIA_USUARIO=rs.getString("Contrase??a");
                PARAMETROS_USUARIOS.ROL_USUARIO=rs.getString("Rol");
                PARAMETROS_USUARIOS.ESTADO_USUARIO=rs.getString("Estado");
                
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
            ps = cn.prepareStatement("select idlogin1, Nombre, Contrase??a, Rol, NombreUsuario from login1 where idlogin1=?");
            ps.setInt(1, log.getIdlogin1());
            rs = ps.executeQuery();
            
            while(rs.next()){
            log.setIdlogin1(rs.getInt("idlogin1"));
                log.setNombre(rs.getString("Nombre"));
                log.setRol(rs.getString("Rol"));
                log.setContrase??a(rs.getString("Contrase??a"));
                log.setNombreUsuario(rs.getString("NombreUsuario"));
            }
        } catch (SQLException e) {
            System.out.println("Error en M??todo Visualizar, "+e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
    
    public Boolean Registrar(login reg){
        
        ps = null;
        cn = Unionsis2.getConnection();
        String slq= "insert into login1 (Nombre, Contrase??a, Rol, NombreUsuario, FechaIngreso, UltimaVezIngreso, Ingreso) values (?, ?, ?, ?, ?, ?, ?)";
        
        try {
           ps=  cn.prepareStatement(slq);
           ps.setString(1,reg.getNombre());
           ps.setString(2,reg.getContrase??a());
           ps.setString(3,reg.getRol());
           ps.setString(4, reg.getNombreUsuario());
            ps.setString(5, fecha);
           ps.setString(6, "NO SE HA INGRESADO");
           ps.setString(7, "NO SE HA INGRESADO");
           Boolean Resultado = ps.execute();
           if(Resultado==true){
           DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("??REGISTRO ??XITOSO!", "EL USUARIO "+reg.getNombre()+" SE REGISTR?? CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
           return true;
           }
           
           
        } catch (SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("????RROR AL REGISTRAR!", "EL USUARIO "+reg.getNombre()+" NO SE PUDO REGISTRAR\n"+e, DesktopNotify.FAIL, 14000L);
            
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
        
        String slq= "update login1 set Nombre=?, Contrase??a=?, Rol=?, NombreUsuario=? where idlogin1=?";
        try {
           ps=  cn.prepareStatement(slq);
           ps.setString(1,reg.getNombre());
           ps.setString(2,reg.getContrase??a());
           ps.setString(3,reg.getRol());
           ps.setString(4, reg.getNombreUsuario());
           ps.setInt(5, reg.getIdlogin1());
           Boolean Resultado = ps.execute();
           if(Resultado==true){
           DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("??EDIC??N ??XITOSA!", "EL USUARIO "+reg.getNombre()+" SE EDIT?? CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
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
        
        String slq= "update login1 set Nombre=?, Contrase??a=?, Rol=?, NombreUsuario=? where idlogin1=?";
        try {
           ps=  cn.prepareStatement(slq);
           ps.setString(1,reg.getNombre());
           ps.setString(2,reg.getContrase??a());
           ps.setString(3,reg.getRol());
           ps.setString(4, reg.getNombreUsuario());
           ps.setInt(5, reg.getIdlogin1());
           Boolean Resultado = ps.execute();
           if(Resultado==true){
           DesktopNotify.setDefaultTheme(NotifyTheme.Light);
           DesktopNotify.showDesktopMessage("??EDIC??N ??XITOSA!", "EL USUARIO "+reg.getNombre()+" SE EDIT?? CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
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
           DesktopNotify.showDesktopMessage("??ELIMINACI??N ??XITOSA!", "EL USUARIO "+reg.getNombre()+" SE ELIMIN?? CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);  
           
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
            System.out.println("Error en Ver Datos de Empresa, conexi??n fallida \n"+ e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return DE;
    }
    
}
