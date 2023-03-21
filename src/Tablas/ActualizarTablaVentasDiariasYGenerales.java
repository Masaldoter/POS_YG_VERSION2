/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Conexiones.conexion;
import Modelo.Detalle;
import Modelo.Productos;
import Modelo.Venta;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaldoter
 */
public class ActualizarTablaVentasDiariasYGenerales extends ConexionesSQL{
    Date fech = new Date();
        String strDateFormat = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha=objSDF.format(fech);
        
         public String Fecha(){
        Date fech = new Date();
        String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fecha = Fechas.format(fech);
        
        return fecha;
    }
        
        public List ListarVentasDiarias(JComboBox Seleccion){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/ojoabierto.png"));
        ImageIcon ro = new ImageIcon(retValue);

        btn1.setIcon(ro);
        List<Venta> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="";
        switch (Seleccion.getSelectedIndex()) {
            case 0:
                Filtro="FACTURADO";
                break;
            case 1:
                Filtro="ANULADO";
                break;
            default:
                break;
        }
        try {
            Venta v;
             ps = cn.prepareStatement("select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where Fecha LIKE '%" + fecha + "%' AND registro.Estado='"+Filtro+"' ORDER BY IdRegistro DESC");
            rs = ps.executeQuery();

            while (rs.next()) {
                v = new Venta();
                 v.setIdRegistro(rs.getInt("IdRegistro"));
                 v.setIDENTIFICACION_CLIENTE(rs.getString("NitCliente"));
                 v.setCliente(rs.getString("Cliente"));
                 v.setHora(rs.getString("Hora"));
                 v.setFecha(rs.getString("Fecha"));
                 v.setTotal(rs.getFloat("Total"));
                 v.setPagocon(rs.getFloat("Pago"));
                 v.setCambio(rs.getFloat("Cambio"));
                 v.setNoFactura(rs.getString("NoFactura"));
                 v.setFormaPago(rs.getString("FormaPago"));
                 v.setUsuario(rs.getInt("Usuario"));
                 v.setUSUARIO_REGISTRO_LETRAS(rs.getString("NombreUsuario"));
                 v.setTipoDocumentoFel(rs.getString("TipoDocumentoFel"));
                 v.setEstado(rs.getString("Estado"));
                 v.setDetalles(btn1);
               Listapro.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasDiarias, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
        
        public List ListarVentasDiariasPorUsuario(JComboBox Seleccion, int Usuario){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

        /*Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/editar.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);
        
        btn2.setIcon(ro2);*/
        btn1.setBackground(new Color(0,195,0));
        btn1.setText("DETALLES");
        List<Venta> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="";
        switch (Seleccion.getSelectedIndex()) {
            case 0:
                Filtro="FACTURADO";
                break;
            case 1:
                Filtro="ANULADO";
                break;
            default:
                break;
        }
        try {
            Venta v;
             ps = cn.prepareStatement("select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where Usuario=? AND Fecha=? AND registro.Estado='"+Filtro+"' ORDER BY IdRegistro DESC");
             ps.setInt(1, Usuario);
             ps.setString(2, fecha);
             rs = ps.executeQuery();

            while (rs.next()) {
                v = new Venta();
                 v.setIdRegistro(rs.getInt("IdRegistro"));
                 v.setIDENTIFICACION_CLIENTE(rs.getString("NitCliente"));
                 v.setCliente(rs.getString("Cliente"));
                 v.setHora(rs.getString("Hora"));
                 v.setFecha(rs.getString("Fecha"));
                 v.setTotal(rs.getFloat("Total"));
                 v.setPagocon(rs.getFloat("Pago"));
                 v.setCambio(rs.getFloat("Cambio"));
                 v.setNoFactura(rs.getString("NoFactura"));
                 v.setFormaPago(rs.getString("FormaPago"));
                 v.setUsuario(rs.getInt("Usuario"));
                 v.setUSUARIO_REGISTRO_LETRAS(rs.getString("NombreUsuario"));
                 v.setTipoDocumentoFel(rs.getString("TipoDocumentoFel"));
                 v.setEstado(rs.getString("Estado"));
                 v.setDetalles(btn1);
               Listapro.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasDiariasPorUsuario, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       return Listapro;
    }
        
    public int BusquedaDeUsuario(JComboBox Seleccion){
        rs = null;
        ps = null;
        cn = Unionsis2.getConnection();
        int IdUsuario=0;
        String sql = "SELECT idlogin1 FROM login1 WHERE NombreUsuario=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, String.valueOf(Seleccion));
            rs = ps.executeQuery();
            
            if(rs.next()){
            IdUsuario = rs.getInt("idlogin1");
        }
        } catch (SQLException e) {
            System.err.println("Error en ActualizarTablaVentasDiariasYGenerales / BusquedaDeUsuario " +e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return IdUsuario;
    }
     ///Ventas Generales
     public void ActualizarRegistroVenta(JTable TablaReporteVentas) {
        ps = null;
        rs = null;
        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaReporteVentas.setModel(modeloTabla);
        //conexion Unionsis2 = conexion.getInstancia();
        cn = Unionsis2.getConnection();
        try {

            ps = cn.prepareStatement("select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)");

            rs = ps.executeQuery();

            modeloTabla.addColumn("No. Venta");
            modeloTabla.addColumn("Cliente");
            modeloTabla.addColumn("Hora");
            modeloTabla.addColumn("Fecha");
            modeloTabla.addColumn("Pago");
            modeloTabla.addColumn("Cambio");
            modeloTabla.addColumn("Total");
            modeloTabla.addColumn("No. Factura");
            modeloTabla.addColumn("Forma de Pago");
            modeloTabla.addColumn("Estado");

            while (rs.next()) {

                Object fila[] = new Object[10];
                for (int i = 0; i < 10; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                modeloTabla.addRow(fila);

            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
    }
     
     
     public void BuscarVentaPorFechas(JTable TablaReporteVentas, JDateChooser FechaInicial, JDateChooser FechaFinal){
         conexion Unionsis2= conexion.getInstancia();
         cn = Unionsis2.getConnection();
         ps = null;
         rs = null;


        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaReporteVentas.setModel(modeloTabla);
        Date date = FechaInicial.getDate();
        Date date2 = FechaFinal.getDate();
        String strDateFormat = "YYYY-MM-dd";
        String strDateFormat2 = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        SimpleDateFormat objSDF2 = new SimpleDateFormat(strDateFormat2);
        String fecha = objSDF.format(date);
        String fecha2 = objSDF2.format(date2);
        if (!"".equals(fecha)) {

            try {
                ps = cn.prepareStatement("select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where Fecha BETWEEN '"+fecha+"' AND '"+fecha2+"' ORDER BY IdRegistro DESC");
                rs = ps.executeQuery();

                modeloTabla.addColumn("No. Venta");
                modeloTabla.addColumn("Cliente");
                modeloTabla.addColumn("Hora");
                modeloTabla.addColumn("Fecha");
                modeloTabla.addColumn("Pago");
                modeloTabla.addColumn("Cambio");
                modeloTabla.addColumn("Total");
                modeloTabla.addColumn("No. Factura");
                modeloTabla.addColumn("Serie");
                modeloTabla.addColumn("Estado");
                while (rs.next()) {
                    Object fila[] = new Object[10];
                    for (int i = 0; i < 10; i++) {
                        fila[i] = rs.getObject(i + 1);
                    }
                    modeloTabla.addRow(fila);
                }
            } catch (SQLException e) {
                System.err.println("Error, " + e);
            }finally{
                    ConnectionClose(cn);
                    PsClose(ps);
                    RsClose(rs);
            }
        } else if(fecha.equals("")){
            JOptionPane.showMessageDialog(null, "¡Seleccione una fecha!");
        }
     }
     
     
     
     public List ListarVentasGenerales(JComboBox Seleccion, JComboBox Seleccion2){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

       /* Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Settings_30027.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);*/
         btn1.setBackground(new Color(0, 195, 0));
         btn1.setText("DETALLES");
         List<Venta> Listapro = new ArrayList();
         cn = Unionsis2.getConnection();
         ps = null;
         rs = null;
         try {
             Venta v;
             ps = cn.prepareStatement("select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where (registro.Estado='" + Seleccion.getSelectedItem().toString() + "' AND registro.TipoDocumentoFel='" + Seleccion2.getSelectedItem().toString() + "') ORDER BY IdRegistro DESC");
             rs = ps.executeQuery();

             while (rs.next()) {
                 v = new Venta();
                 v.setIdRegistro(rs.getInt("IdRegistro"));
                 v.setIDENTIFICACION_CLIENTE(rs.getString("NitCliente"));
                 v.setCliente(rs.getString("Cliente"));
                 v.setHora(rs.getString("Hora"));
                 v.setFecha(rs.getString("Fecha"));
                 v.setTotal(rs.getFloat("Total"));
                 v.setPagocon(rs.getFloat("Pago"));
                 v.setCambio(rs.getFloat("Cambio"));
                 v.setNoFactura(rs.getString("NoFactura"));
                 v.setFormaPago(rs.getString("FormaPago"));
                 v.setUsuario(rs.getInt("Usuario"));
                 v.setUSUARIO_REGISTRO_LETRAS(rs.getString("NombreUsuario"));
                 v.setTipoDocumentoFel(rs.getString("TipoDocumentoFel"));
                 v.setEstado(rs.getString("Estado"));
                 v.setDetalles(btn1);
                 Listapro.add(v);
             }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasGenerales, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
     
     public List ListarVentasGeneralesPorDocumento(JTextField NombreProducto, Boolean IncluirFecha){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

       /* Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Settings_30027.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);*/
        btn1.setBackground(new Color(0,195,0));
        btn1.setText("DETALLES");
        List<Venta> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        
        String Sql;
        if(IncluirFecha==true){
          Sql="select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where NoFactura='"+NombreProducto.getText()+"' AND Fecha='"+fecha+"' ORDER BY IdRegistro DESC";
        }else{
         Sql="select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where NoFactura='"+NombreProducto.getText()+"' ORDER BY IdRegistro DESC";   
        }
        
        try {
            Venta v;
             ps = cn.prepareStatement(Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                v = new Venta();
                v.setIdRegistro(rs.getInt("IdRegistro"));
                 v.setIDENTIFICACION_CLIENTE(rs.getString("NitCliente"));
                 v.setCliente(rs.getString("Cliente"));
                 v.setHora(rs.getString("Hora"));
                 v.setFecha(rs.getString("Fecha"));
                 v.setTotal(rs.getFloat("Total"));
                 v.setPagocon(rs.getFloat("Pago"));
                 v.setCambio(rs.getFloat("Cambio"));
                 v.setNoFactura(rs.getString("NoFactura"));
                 v.setFormaPago(rs.getString("FormaPago"));
                 v.setUsuario(rs.getInt("Usuario"));
                 v.setUSUARIO_REGISTRO_LETRAS(rs.getString("NombreUsuario"));
                 v.setTipoDocumentoFel(rs.getString("TipoDocumentoFel"));
                 v.setEstado(rs.getString("Estado"));
                v.setDetalles(btn1);
               Listapro.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasGenerales, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
     
     public List ListarVentasGeneralesPorFecha(JDateChooser FechaInicial, JDateChooser FechaFinal, JComboBox Seleccion, JComboBox Seleccion2){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");
        
        Date date = FechaInicial.getDate();
        Date date2 = FechaFinal.getDate();
        String strDateFormat = "YYYY-MM-dd";
        String strDateFormat2 = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        SimpleDateFormat objSDF2 = new SimpleDateFormat(strDateFormat2);
        String fecha = objSDF.format(date);
        String fecha2 = objSDF2.format(date2);

       /* Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Settings_30027.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);*/
        btn1.setBackground(new Color(0,195,0));
        btn1.setText("DETALLES");
        List<Venta> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        try {
            Venta v;
             ps = cn.prepareStatement("select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where (Fecha BETWEEN '"+fecha+"' AND '"+fecha2+"' AND registro.Estado='" + Seleccion.getSelectedItem().toString() + "' AND registro.TipoDocumentoFel='" + Seleccion2.getSelectedItem().toString() + "') ORDER BY IdRegistro DESC");
            rs = ps.executeQuery();

            while (rs.next()) {
                v = new Venta();
                v.setIdRegistro(rs.getInt("IdRegistro"));
                 v.setIDENTIFICACION_CLIENTE(rs.getString("NitCliente"));
                 v.setCliente(rs.getString("Cliente"));
                 v.setHora(rs.getString("Hora"));
                 v.setFecha(rs.getString("Fecha"));
                 v.setTotal(rs.getFloat("Total"));
                 v.setPagocon(rs.getFloat("Pago"));
                 v.setCambio(rs.getFloat("Cambio"));
                 v.setNoFactura(rs.getString("NoFactura"));
                 v.setFormaPago(rs.getString("FormaPago"));
                 v.setUsuario(rs.getInt("Usuario"));
                 v.setUSUARIO_REGISTRO_LETRAS(rs.getString("NombreUsuario"));
                 v.setTipoDocumentoFel(rs.getString("TipoDocumentoFel"));
                 v.setEstado(rs.getString("Estado"));
                v.setDetalles(btn1);
               Listapro.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasGenerales, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
     
     public List ListarVentasGeneralesPorNombreDetalleProducto(JTextField NombreProducto, Boolean IncluirFecha){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

       /* Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Settings_30027.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);*/
        btn1.setBackground(new Color(0,195,0));
        btn1.setText("DETALLES");
        List<Venta> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Sql;
        if(IncluirFecha==true){
        Sql="select CodigoBarras, Nombre, Cantidad, Total, NoFactura from  detalle where (Fecha='"+fecha+"' AND Nombre LIKE '%' '"+NombreProducto.getText()+"' '%' OR CodigoBarras LIKE '%' '"+NombreProducto.getText()+"' '%') ORDER BY Iddetalle DESC";
        }else{
        Sql="select CodigoBarras, Nombre, Cantidad, Total, NoFactura from  detalle where (Nombre LIKE '%' '"+NombreProducto.getText()+"' '%' OR CodigoBarras LIKE '%' '"+NombreProducto.getText()+"' '%') ORDER BY Iddetalle DESC";   
        }
        try {
            Venta v;
             ps = cn.prepareStatement(Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                v = new Venta();
                v.setCliente(rs.getString("CodigoBarras"));
                v.setHora(rs.getString("Nombre"));
                v.setFecha(rs.getString("Cantidad"));
                v.setTotal(rs.getFloat("Total"));
                v.setNoFactura(rs.getString("NoFactura"));
                v.setDetalles(btn1);
               Listapro.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasGeneralesPorNombreDetalleProducto, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
     
     public List ListarVentasGeneralesPorTipoDocumento(JComboBox Seleccion, Boolean IncluirFecha, Boolean IncluirUSUARIO, String USUARIO){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

       /* Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Settings_30027.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);*/
        btn1.setBackground(new Color(0,195,0));
        btn1.setText("DETALLES");
        List<Venta> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        
        String Sql;
        if(IncluirFecha==true){
          if(IncluirUSUARIO==true){
            Sql="select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where TipoDocumentoFel='"+Seleccion.getSelectedItem().toString()+
                    "' AND Fecha='"+fecha+"' AND Usuario='"+USUARIO+"' ORDER BY IdRegistro DESC";  
          }else{
              Sql="select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where TipoDocumentoFel='"
                  +Seleccion.getSelectedItem().toString()+"' AND Fecha='"+fecha+"' ORDER BY IdRegistro DESC";
          }
        }else{
         if(IncluirUSUARIO==true){
             Sql="select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where TipoDocumentoFel='"
                 +Seleccion.getSelectedItem().toString()+"' AND Usuario='"+USUARIO+"' ORDER BY IdRegistro DESC";  
         }else{
             Sql="select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                     + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                     + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1)"
                     + "where TipoDocumentoFel='"
                 +Seleccion.getSelectedItem().toString()+"' ORDER BY IdRegistro DESC";
         }
        }
        
        try {
            Venta v;
             ps = cn.prepareStatement(Sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                v = new Venta();
                v.setIdRegistro(rs.getInt("IdRegistro"));
                 v.setIDENTIFICACION_CLIENTE(rs.getString("NitCliente"));
                 v.setCliente(rs.getString("Cliente"));
                 v.setHora(rs.getString("Hora"));
                 v.setFecha(rs.getString("Fecha"));
                 v.setTotal(rs.getFloat("Total"));
                 v.setPagocon(rs.getFloat("Pago"));
                 v.setCambio(rs.getFloat("Cambio"));
                 v.setNoFactura(rs.getString("NoFactura"));
                 v.setFormaPago(rs.getString("FormaPago"));
                 v.setUsuario(rs.getInt("Usuario"));
                 v.setUSUARIO_REGISTRO_LETRAS(rs.getString("NombreUsuario"));
                 v.setTipoDocumentoFel(rs.getString("TipoDocumentoFel"));
                 v.setEstado(rs.getString("Estado"));
                v.setDetalles(btn1);
               Listapro.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasGenerales, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
     
     public List ListarVentasGeneralesPorCliente(String Cliente, JComboBox EstadoDocumento){
        JButton btn1 = new JButton();
        JButton btn2 = new JButton();
        btn1.setName("edit");
        btn2.setName("delete");

       /* Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/Settings_30027.png"));
        ImageIcon ro = new ImageIcon(retValue);
        Image retValue2 = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro2= new ImageIcon(retValue2);

        btn1.setIcon(ro);*/
        btn1.setBackground(new Color(0,195,0));
        btn1.setText("DETALLES");
        List<Venta> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        String Filtro ="";
        switch (EstadoDocumento.getSelectedIndex()) {
            case 0:
                Filtro="FACTURADO";
                break;
            case 1:
                Filtro="ANULADO";
                break;
            default:
                break;
        }
        String Sql;
        /*if(IncluirFecha==true){
          Sql="select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                  + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                  + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1) "
                  + "where Cliente LIKE '%' '"+Cliente.getText()+"' '%' AND registro.Estado='"+Filtro+"' "
                  + "AND registro.Fecha='"+fecha+"' ORDER BY IdRegistro DESC";
        }else{*/
         Sql = "select IdRegistro, NitCliente, Cliente, Hora, Fecha, Total, Pago, "
                 + "Cambio, NoFactura, FormaPago, Usuario, login1.Nombre AS NombreUsuario, TipoDocumentoFel, registro.Estado from registro "
                 + "INNER JOIN login1 ON (registro.Usuario = login1.idlogin1) "
                 + "where Cliente LIKE '%' '" + Cliente + "' '%' AND registro.Estado='" + Filtro + "' ORDER BY IdRegistro DESC";
         //}

         try {
             Venta v;
             ps = cn.prepareStatement(Sql);
             rs = ps.executeQuery();

            while (rs.next()) {
                v = new Venta();
                v.setIdRegistro(rs.getInt("IdRegistro"));
                 v.setIDENTIFICACION_CLIENTE(rs.getString("NitCliente"));
                 v.setCliente(rs.getString("Cliente"));
                 v.setHora(rs.getString("Hora"));
                 v.setFecha(rs.getString("Fecha"));
                 v.setTotal(rs.getFloat("Total"));
                 v.setPagocon(rs.getFloat("Pago"));
                 v.setCambio(rs.getFloat("Cambio"));
                 v.setNoFactura(rs.getString("NoFactura"));
                 v.setFormaPago(rs.getString("FormaPago"));
                 v.setUsuario(rs.getInt("Usuario"));
                 v.setUSUARIO_REGISTRO_LETRAS(rs.getString("NombreUsuario"));
                 v.setTipoDocumentoFel(rs.getString("TipoDocumentoFel"));
                 v.setEstado(rs.getString("Estado"));
                v.setDetalles(btn1);
               Listapro.add(v);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasGenerales, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
     
     public static List DETALLE_VENTA(String FACTURA){
        List<Detalle> Listapro = new ArrayList();
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
        try {
            Detalle deta;
             ps = cn.prepareStatement("select iddetalle, CodigoBarras, Nombre, Cantidad, "
                     + "Precio, Descuento_Detalle, Precio_Con_Descuento_Detalle, Total, Validar_Descuento_detalle, ProductoRegistrado, IdProductos "
                     + "from detalle where NoFactura='"+ FACTURA+"'");
            rs = ps.executeQuery();

            while (rs.next()) {
                deta = new Detalle();
                deta.setIddetalle(rs.getInt("iddetalle"));
                deta.setCodigoBarras(rs.getString("CodigoBarras"));
                deta.setNombre(rs.getString("Nombre"));
                deta.setCantidad(rs.getFloat("Cantidad"));
                deta.setPrecio(rs.getFloat("Precio"));
                deta.setDescuento(rs.getFloat("Descuento_Detalle"));
                deta.setPrecio_Con_Descuento(rs.getFloat("Precio_Con_Descuento_Detalle"));
                deta.setTotal(rs.getFloat("Total"));
                deta.setAplicar_Descuento(rs.getBoolean("Validar_Descuento_detalle"));
                deta.setValidacionProductoExistente(rs.getString("ProductoRegistrado"));
                deta.setIdProductos(rs.getInt("IdProductos"));
               Listapro.add(deta);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasDiarias, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Listapro;
    }
}
