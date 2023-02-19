/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Abonos;
import Modelo.Detalle;
import Modelo.Detalle2;
import Modelo.Vale;
import Conexiones.ConexionesSQL;
import java.awt.HeadlessException;

import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/**
 *
 * @author Masaldoter
 */
public class ValeDao extends ConexionesSQL{
    Vale val= new Vale();
    Date fech = new Date();
            String strDateFormat = "YYYY-MM-dd HH:mm:ss";
            String strDateFormat1 = "YYYY-MM-dd";
            String strDateFormat2 = "HH:mm:ss";
            SimpleDateFormat FechasyHoras = new SimpleDateFormat(strDateFormat);
            SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
            SimpleDateFormat Horas = new SimpleDateFormat(strDateFormat2);
            String fechaYHora=FechasyHoras.format(fech);
            String fecha=Fechas.format(fech);
            String Hora=Horas.format(fech);
     public void RegistrarVale(Vale val){

            
        String sql = "INSERT INTO  vale (Cliente, Total, NoVale, Estado, FechaEmision, HoraEmision, Fecha2, Vendedor, Nit, Direccion, Pago, Cambio) VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, val.getCliente());  
            ps.setFloat(2, val.getTotal());  
            ps.setInt(3, val.getNoVale()); 
            ps.setString(4, val.getEstado());   
            ps.setString(5, fecha);
            ps.setString(6, Hora);
            ps.setString(7, null);
            ps.setInt(8, val.getVendedor());
            ps.setInt(9, val.getNit());
            ps.setInt(10, val.getDireccion());
            ps.setFloat(11, 0);
            ps.setFloat(12, 0);
            boolean resultado = ps.execute();
                      
            if(resultado == true){
            JOptionPane.showMessageDialog(null, "¡El Vale se proceso correctamente!");    
            }
            
        } catch (SQLException e) {
            System.err.println("Error en registrar vale, "+e);
        }
    }

    
    public boolean ActualizarVale(Float cant, String cod){
        String sql= "update vale set Total=? where idVale=?";
        try {
            ps= cn.prepareStatement(sql);
            ps.setFloat(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }return false;
    }
    
    public boolean ActualizarVale2(String cod, String No){

        String sql= "update vale set Estado=?, Fecha2=?, NoFactura=? where idVale=?";
        try {
            ps= cn.prepareStatement(sql);
            ps.setString(1, "Pagado");
            ps.setString(2, fechaYHora);
            ps.setString(3, No);
            ps.setString(4, cod);
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error, "+e);
        }return false;
    }
    
    public Vale BuscarVale(String Nom2){
        String sql= "select * from vale where idVale=?";
        try {
            ps= cn.prepareStatement(sql);
        
        ps.setString(1, Nom2);
        rs= ps.executeQuery();
        
        if(rs.next()){
        val.setIdVale(rs.getInt("IdVale"));
        val.setTotal(rs.getFloat("Total"));
        val.setCliente(rs.getInt("Cliente"));
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en consulta, "+e);
        }finally{
            try {
                if (rs != null) rs.close();
                
                if (ps != null) ps.close();
            } catch (Exception e) {
            }
        }
        return val;
    }
    
    public void RegistrarDetalleVale(Detalle2 val){
            String strDateFormat = "dd-MM-YYYY";
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            String fecha=objSDF.format(fech);
        String sql="insert into detalle2 (CodigoBarras, Cantidad, Precio, IdVale, NoVale, Total, Nombre, Fecha, Usuario) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, val.getCodigoBarras());
            ps.setFloat(2, val.getCantidad());
            ps.setFloat(3, val.getPrecio());
            ps.setInt(4, val.getIdVale());
            ps.setString(5, val.getNoVale());
            ps.setString(6, val.getTotal());
            ps.setInt(7, val.getNombre());
            ps.setString(8, fecha);
            ps.setInt(9, val.getUsuario());
            
            ps.execute();
            
        } catch (SQLException e) {
            System.err.println("Error en VentaDao Registrar detalle de Vale, "+e);
        }
    }
    
    public void Abonar(Abonos abonos){    
        String sql="INSERT INTO abonovale (TotalAbono, PersonaAbono, FechaAbono, idVale, HoraAbono, UsuarioAbono) VALUES(?, ? ,?, ?, ?, ?)";
        try {
            ps= cn.prepareStatement(sql);
            ps.setFloat(1, abonos.getTotalAbono());
            ps.setString(2, abonos.getPersonaAbono());
            ps.setString(3, abonos.getFechaAbono());
            ps.setInt(4, abonos.getIdVale());
            ps.setString(5, fecha);
            ps.setInt(6, abonos.getUsuarioAbono());
            int Resultado = ps.executeUpdate();
            
            if(Resultado > 0){
                JOptionPane.showMessageDialog(null, "SE HA ABONADO CORRECTAMENTE Q"+abonos.getTotalAbono()+" A LA CUENTA DEL VALE N°"+ abonos.getIdVale(), "¡PROCESO EXITOSO!", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "¡ERROR AL REALIZAR EL PROCESO!"+e, "¡MÉTODO ABONAR FALLIDO!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Abonos VerAbonosEnDetalle(int IdVale){
        Abonos abonos= new Abonos();
        String sql="SELECT SUM(TotalAbono) FROM abonovale WHERE idVale=?";
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, IdVale);
            rs = ps.executeQuery();
            
            if(rs.next()){
                abonos.setTotalAbono(rs.getFloat("SUM(TotalAbono)"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡ERROR AL REALIZAR EL PROCESO!"+e, "¡MÉTODO ABONAR FALLIDO!", JOptionPane.ERROR_MESSAGE);
        }
        return abonos;
    }
    
    public Vale VerDetallesValeClientesYTotalesSinCancelar(Vale val){
        String sql="SELECT Estado, Cliente, Nit, Direccion, Total, NoVale, FechaEmision, Fecha2, Vendedor, HoraEmision FROM vale WHERE idVale=?";
        try {
            ps=cn.prepareStatement(sql);
            ps.setInt(1, val.getIdVale());
            boolean resultado=ps.execute();
            
            if(resultado == true){
                val.setEstado(rs.getString("Estado"));
                val.setCliente(rs.getInt("Cliente"));
                val.setNit(rs.getInt("Nit"));
                val.setDireccion(rs.getInt("Direccion"));
                val.setTotal(rs.getFloat("Total"));
                val.setFechaEmision(rs.getString("FechaEmision"));
                val.setFecha2Pago(rs.getString("Fecha2"));
                val.setVendedor(rs.getInt("Vendedor"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡ERROR AL REALIZAR EL PROCESO!"+e, "¡MÉTODO VerDetallesValeClientesYTotalesSinCancelar FALLIDO!", JOptionPane.ERROR_MESSAGE);
        }
        return val;
    }
    
    public Abonos VerDetalleValeProductos(){
        Abonos abonos= new Abonos();
        String sql="SELECT SUM(TotalAbono) FROM abonovale WHERE idVale=?";
        try {
            ps=cn.prepareStatement(sql);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "¡ERROR AL REALIZAR EL PROCESO!"+e, "¡MÉTODO ABONAR FALLIDO!", JOptionPane.ERROR_MESSAGE);
        }
        return abonos;
    }
}
