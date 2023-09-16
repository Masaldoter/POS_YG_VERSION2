/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import FEL.DatosCertificador;
import Modelo.DatosEmpresaGeneral;
import Conexiones.ConexionesSQL;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Masaldoter
 */
public class DatosEmpresaDao extends ConexionesSQL {

    public DatosEmpresaGeneral VerDatos() {
        DatosEmpresaGeneral DaEm = new DatosEmpresaGeneral();
        String sql = "SELECT Version_Sistema, NombreEmpresa, Direccion, Nit, Tel, Eslogan, Politicas, Correo, ContraseniaCorreo, Iva, NombreEtiquetas, "
                + "Municipio, Departamento, Pais, CodigoPostal, CajaAfilicacionEmpresa, CodigoEstablecimiento, Propietario, rutaimagenlogo, rutaimagensistema, "
                + "ClaveInternaCostos FROM datosempresa";
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                DaEm.setVersion_Sistema(rs.getString("Version_Sistema"));
                DaEm.setNombreEmpresa(rs.getString("NombreEmpresa"));
                DaEm.setDireccion(rs.getString("Direccion"));
                DaEm.setNit(rs.getString("Nit"));
                DaEm.setTel(rs.getString("Tel"));
                DaEm.setEslogan(rs.getString("Eslogan"));
                DaEm.setPoliticas(rs.getString("Politicas"));
                DaEm.setContrasenia(rs.getString("ContraseniaCorreo"));
                DaEm.setCorreo(rs.getString("Correo"));
                DaEm.setIva(rs.getString("Iva"));
                DaEm.setNombreEtiquetas(rs.getString("NombreEtiquetas"));
                DaEm.setMunicipio(rs.getString("Municipio"));
                DaEm.setDepartamento(rs.getString("Departamento"));
                DaEm.setPais(rs.getString("Pais"));
                DaEm.setCodigoPostal(rs.getString("CodigoPostal"));
                DaEm.setCajaAfilicacionEmpresa(rs.getString("CajaAfilicacionEmpresa"));
                DaEm.setCodigoEstablecimiento(rs.getString("CodigoEstablecimiento"));
                DaEm.setPropietario(rs.getString("Propietario"));
                DaEm.setRutaimagenlogo(rs.getString("rutaimagenlogo"));
                DaEm.setRutaimagensistema(rs.getString("rutaimagensistema"));
                DaEm.setRutaimagensistema(rs.getString("rutaimagensistema"));
                
                PARAMETROS_VERSION_SISTEMA.VERSION = rs.getString("Version_Sistema");
                PARAMETROS_EMPRESA.NOMBRE_EMPRESA = rs.getString("NombreEmpresa");
                PARAMETROS_EMPRESA.DIRECCION_EMPRESA = rs.getString("Direccion");
                PARAMETROS_EMPRESA.NIT_EMPRESA = rs.getString("Nit");
                PARAMETROS_EMPRESA.TEL_EMPRESA = rs.getString("Tel");
                PARAMETROS_EMPRESA.ESLOGAN_EMPRESA = rs.getString("Eslogan");
                PARAMETROS_EMPRESA.POLITICAS_EMPRESA = rs.getString("Politicas");
                PARAMETROS_EMPRESA.CONTRASENIA_EMPRESA = rs.getString("ContraseniaCorreo");
                PARAMETROS_EMPRESA.CORREO_EMPRESA = rs.getString("Correo");
                PARAMETROS_EMPRESA.IVA_EMPRESA = rs.getString("Iva");
                PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA = rs.getString("NombreEtiquetas");
                PARAMETROS_EMPRESA.MUNICIPIO_EMPRESA = rs.getString("Municipio");
                PARAMETROS_EMPRESA.DEPARTAMENTO_EMPRESA = rs.getString("Departamento");
                PARAMETROS_EMPRESA.PAIS_EMPRESA = rs.getString("Pais");
                PARAMETROS_EMPRESA.CODIGOPOSTAL_EMPRESA = rs.getString("CodigoPostal");
                PARAMETROS_EMPRESA.AFILICACIONIVA_EMPRESA = rs.getString("CajaAfilicacionEmpresa");
                PARAMETROS_EMPRESA.CODIGOESTABLECIMIENTO_EMPRESA = rs.getString("CodigoEstablecimiento");
                PARAMETROS_EMPRESA.PROPIETARIO_EMPRESA = rs.getString("Propietario");
                PARAMETROS_EMPRESA.RUTADEIMAGEN_DOCUMENTOS_EMPRESA = rs.getString("rutaimagenlogo");
                PARAMETROS_EMPRESA.ClaveInternaCostos = rs.getString("ClaveInternaCostos");
                PARAMETROS_EMPRESA.ACTUALIZAR_CLAVE();
            }

        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Error con base de Datos", JOptionPane.ERROR_MESSAGE);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }

        return DaEm;
    }
    
    public DatosCertificador VerDatosCertificador(){
        DatosCertificador DaCert = new DatosCertificador();
        String sql="SELECT NombreEmpresaCertificadora, NitCertificador, TelefonoCertificador, CorreoCertificador, UsuarioToken, ContraseniaToken, Token FROM certificador";
        ps = null;
        rs= null;
        cn = Unionsis2.getConnection();
    
        try {
            ps= cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if(rs.next()){
                DaCert.setNombreCertificador(rs.getString("NombreEmpresaCertificadora"));
                DaCert.setNitCertificador(rs.getString("NitCertificador"));
                DaCert.setTelefonoCertificador(rs.getString("TelefonoCertificador"));
                DaCert.setCorreoCertificador(rs.getString("CorreoCertificador"));
                DaCert.setUsuarioToken(rs.getString("UsuarioToken"));
                DaCert.setContraseniaToken(rs.getString("ContraseniaToken"));
                DaCert.setToken(rs.getString("Token"));
                PARAMETROS_EMPRESA.NOMBRE_CERTIFICADOR = rs.getString("NombreEmpresaCertificadora");
                PARAMETROS_EMPRESA.NIT_CERTIFICADOR = rs.getString("NitCertificador");
                PARAMETROS_EMPRESA.TELEFONO_CERTIFICADOR=rs.getString("TelefonoCertificador");
                PARAMETROS_EMPRESA.CORREO_CERTIFICADOR=rs.getString("CorreoCertificador");
                PARAMETROS_EMPRESA.USUARIO_CERTIFICADOR=rs.getString("UsuarioToken");
                PARAMETROS_EMPRESA.CONTRASENIA_CERTIFICADOR=rs.getString("ContraseniaToken");
                //PARAMETROS_EMPRESA.TOKEN_CERTIFICADOR=rs.getString("Token");
            }
            
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Error Con base de Datos", JOptionPane.ERROR_MESSAGE);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
      
        return DaCert;
    }
    
    public void ActualizarDatos(DatosEmpresaGeneral DaEm){
        ps = null;
        cn = Unionsis2.getConnection();
        
        int seleccion= JOptionPane.showOptionDialog(null, "LOS DATOS QUE HAYA INGRESADO SE MOSTRARÁN EN LOS DOCUMENTOS", //contenido de la ventana
                "¡IMPORTANTE!" , //titulo de la ventana
                JOptionPane.YES_NO_CANCEL_OPTION, //para 3 botones si/no/cancel
                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                null,    // null para icono por defecto.
                new Object[] { "OK, LO ENTIENDO", "ABORTAR"},//objeto para las opciones
                //null para YES, NO y CANCEL
                "OK, LO ENTIENDO"); //selección predeterminada
                
                if(seleccion == 0){
                  
        
       String sql="UPDATE datosempresa set NombreEmpresa=?, Direccion=?, Nit=?, Tel=?, Eslogan=?, Politicas=?, Correo=?, ContraseniaCorreo=?, Iva=?, NombreEtiquetas=?, Municipio=?"
               + ", Departamento=?, Pais=?, CodigoPostal=?, CajaAfilicacionEmpresa=?, CodigoEstablecimiento=?, Propietario=?, rutaimagenlogo=?, rutaimagensistema=?, ClaveInternaCostos=? WHERE iddatosempresa=?";
    
        try {
            ps= cn.prepareStatement(sql);
            ps.setString(1, DaEm.getNombreEmpresa());
            ps.setString(2, DaEm.getDireccion());
            ps.setString(3, DaEm.getNit());
            ps.setString(4, DaEm.getTel());
            ps.setString(5, DaEm.getEslogan());
            ps.setString(6, DaEm.getPoliticas());
            ps.setString(7, DaEm.getCorreo());
            ps.setString(8, DaEm.getContrasenia());
            ps.setString(9, DaEm.getIva());
            ps.setString(10, DaEm.getNombreEtiquetas());
            ps.setString(11, DaEm.getMunicipio());
            ps.setString(12, DaEm.getDepartamento());
            ps.setString(13, DaEm.getPais());
            ps.setString(14, DaEm.getCodigoPostal());
            ps.setString(15, DaEm.getCajaAfilicacionEmpresa());
            ps.setString(16, DaEm.getCodigoEstablecimiento());
            ps.setString(17, DaEm.getPropietario());
            ps.setString(18, DaEm.getRutaimagenlogo());
            ps.setString(19, DaEm.getRutaimagensistema());
            ps.setString(20, DaEm.getClaveInternaCostos());
            ps.setInt(21, 1);
            int Resultado = ps.executeUpdate();
            
            if(Resultado>0){
                javax.swing.JOptionPane.showMessageDialog(null, "LOS DATOS SE ACTUALIZARON CORRECTAMENTE", "DATOS ACTUALIZADOS", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Error Al Actualizar Datos", JOptionPane.ERROR_MESSAGE);
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }  
                }else{
                }
        
    }
    
    public void ActualizarDatosCertificador(DatosCertificador DaCert){
        ps = null;
        cn = Unionsis2.getConnection();  
        
       String sql="UPDATE certificador set NombreEmpresaCertificadora=?, NitCertificador=?, TelefonoCertificador=?, CorreoCertificador=?, UsuarioToken=?, ContraseniaToken=?, Token=? WHERE idcertificador=?";
    
        try {
            ps= cn.prepareStatement(sql);
            ps.setString(1, DaCert.getNombreCertificador());
            ps.setString(2, DaCert.getNitCertificador());
            ps.setString(3, DaCert.getTelefonoCertificador());
            ps.setString(4, DaCert.getCorreoCertificador());
            ps.setString(5, DaCert.getUsuarioToken());
            ps.setString(6, DaCert.getContraseniaToken());
            ps.setString(7, DaCert.getToken());
            ps.setInt(8, 1);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e, "Error Al Actualizar Datos", JOptionPane.ERROR_MESSAGE);
        }finally{
            PsClose(ps);
            ConnectionClose(cn);
        }  
               
        
    }
    
    
}
