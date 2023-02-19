/*
Conexion a la base de datos
*/

package Conexiones;

import CLASES_GLOBALES.PARAMETROS_BASE_DE_DATOS;
import Vista.Conexion;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.apache.commons.dbcp2.BasicDataSource;

public class conexion{
    modeloconexion datos= new modeloconexion();
    Properties properties= new Properties();
    InputStream entrada = null;
    public static final String URL = "jdbc:mysql://";
    public static final String SSL ="?autoReconnect=true&useSSL=false&serverTimeZone=UTC";
    private static conexion datasource;
    private BasicDataSource basicDataSource= null;

    
    
    conexion(){
        datos = new modeloconexion();
        datos = PropiedadesSistema();
        String URL2 = URL+datos.getIp()+":"+datos.getPuerto()+"/"+datos.getNombreBase()+SSL;
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUsername(datos.getNombre());
        basicDataSource.setPassword(datos.getContrasena());
        basicDataSource.setUrl(URL2);
    }
    
    public modeloconexion PropiedadesSistema(){
        
        try {
            
           // entrada = new FileInputStream(Paths.get(res.toURI()).toFile());
           entrada = new FileInputStream(new File ("/Sistema Punto de Venta YG/configuraciones.properties").getAbsolutePath());
            properties.load(entrada);
            datos.setIp(properties.getProperty("ipcentral"));
            datos.setPuerto(properties.getProperty("puertocentral"));
            datos.setNombre(properties.getProperty("usuariocentral"));
            datos.setContrasena(properties.getProperty("contrasenacentral"));
            datos.setNombreBase(properties.getProperty("nombrecentral"));
            PARAMETROS_BASE_DE_DATOS.URL_BASE_DE_DATOS = properties.getProperty("ipcentral");
            PARAMETROS_BASE_DE_DATOS.NOMBRE_BASE_DE_DATOS = properties.getProperty("nombrecentral");
            PARAMETROS_BASE_DE_DATOS.PUERTO_BASE_DE_DATOS = properties.getProperty("puertocentral");
            PARAMETROS_BASE_DE_DATOS.USUARIO_BASE_DE_DATOS = properties.getProperty("usuariocentral");
            PARAMETROS_BASE_DE_DATOS.CONTRASENIA_BASE_DE_DATOS = properties.getProperty("contrasenacentral");
            
            
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }
        return datos;
    }

    public Connection getConnection() {
        Connection Union = null;

        try {
            Union = this.basicDataSource.getConnection();

        } catch (SQLException e) {
            Conexion BasesdeDatos = new Conexion();
            BasesdeDatos.setVisible(true);
        }

        return Union;
    }
    
   
    
    public static conexion getInstancia(){
        if (datasource == null) {
            datasource = new conexion();
        } 
        return datasource;
    
    }
}
