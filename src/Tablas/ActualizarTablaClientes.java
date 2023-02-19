/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import Conexiones.ConexionesSQL;
import Conexiones.conexion;
import Modelo.Clientes;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.Unionsis2;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import java.awt.Color;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Masaldoter
 */
public class ActualizarTablaClientes extends ConexionesSQL{
    String Parametro="";
    String sql="";
    int anchotabla[] = {10, 60, 20, 20, 45};
    public void ActualizarTablaCliente(JTable tabla) {
        cn = Unionsis2.getConnection();
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tabla.setModel(modeloTabla);
        try {
            ps = cn.prepareStatement("select idclientes, Nombre, Nit, TIPO_IDENTIFICACION, Direccion, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono from clientes");
            rs = ps.executeQuery();
            
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("NOMBRE");
            modeloTabla.addColumn("IDENTIFICACION");
            modeloTabla.addColumn("TIPO");
            modeloTabla.addColumn("DIRECCIÓN");

            ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            

            for (int i = 0; i < cantidadColumnas; i++) {
                tabla.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }
            while (rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
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
    
    public List ListarClientes(String TIPO_CONSULTA, String PARAMETRO){
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
        btn1.setBackground(new Color(102,204,255));
        btn1.setText("DETALLES");
        List<Clientes> Listapro = new ArrayList();
        String SQL="";
        cn = Unionsis2.getConnection();
        ps= null;
        rs= null;
       if(TIPO_CONSULTA.equals("FILTRADO")){
           SQL="select Nombre, Nit, TIPO_IDENTIFICACION, Direccion, idclientes, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono from clientes WHERE Nombre LIKE '%" + PARAMETRO + "%' OR Nit LIKE '%" + PARAMETRO + "%' OR Direccion LIKE '%" + PARAMETRO + "%'";
           
       }else{
           SQL="select Nombre, Nit, TIPO_IDENTIFICACION, Direccion, idclientes, CodigoPostal, Municipio, Departamento, Pais, Correo, Telefono from clientes ORDER BY idclientes DESC";
       }
        try {
            Clientes cl;
            ps = cn.prepareStatement(SQL);
             rs = ps.executeQuery();

            while (rs.next()) {
                cl = new Clientes();
                cl.setIdclientes(rs.getInt("idclientes"));
                cl.setNombre(rs.getString("Nombre"));
                cl.setIDENTIFICACION(rs.getString("Nit"));
                cl.setTIPO_IDENTIFICACION(rs.getString("TIPO_IDENTIFICACION"));
                cl.setDireccion(rs.getString("Direccion"));
                cl.setMunicipio(rs.getString("Municipio"));
                cl.setDepartamento(rs.getString("Departamento"));
                cl.setPais(rs.getString("Pais"));
                cl.setCodigoPostal(rs.getString("CodigoPostal"));
                cl.setCorreo(rs.getString("Correo"));
                cl.setTelefono(rs.getString("Telefono"));
                cl.setBotoneditar(btn1);
               Listapro.add(cl);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarClientes, " + e);
        }finally{
                RsClose(rs);
            PsClose(ps);
            ConnectionClose(cn);
        }
       
       return Listapro;
    }
    
    //En Consultas Frame
    
    
    public Clientes BuscarClientesEnTabla(String Nombre, String Nit, String Id, int Filtro, JTable tablaClientes){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        tablaClientes.setModel(modeloTabla);
        TableRowSorter<TableModel> elQueOrdena = new TableRowSorter<TableModel>(modeloTabla);
        tablaClientes.setRowSorter(elQueOrdena);
        cn = Unionsis2.getConnection();
        
        Clientes cl= new Clientes();
        
        if(Filtro == 0){
            Parametro = Nombre;
            sql="SELECT ID, NOMBRE, NIT, TIPO_IDENTIFICACION, DIRECCIÓN FROM clientes WHERE Nombre LIKE '%" + Nombre + "%'";
        }else if(Filtro == 1){
            Parametro = Nit;
            sql="SELECT ID, NOMBRE, NIT, TIPO_IDENTIFICACION, DIRECCIÓN FROM clientes WHERE Nit LIKE '%" + Nit + "%'";
        }else if(Filtro == 2){
            Parametro = Id;
            sql="SELECT ID, NOMBRE, NIT, TIPO_IDENTIFICACION, DIRECCIÓN FROM clientes WHERE idclientes LIKE '%" + Id + "%'";
        }
        System.out.println(sql);
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("NOMBRE");
            modeloTabla.addColumn("IDENTIFICACION");
            modeloTabla.addColumn("TIPO");
            modeloTabla.addColumn("DIRECCIÓN");
            
             ResultSetMetaData rsMD = rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();
            for (int i = 0; i < cantidadColumnas; i++) {
                tablaClientes.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }
            
            while (rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }

                modeloTabla.addRow(fila);

            }
            
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "¡ERROR EN LA CONSULTA!", "ERROR EN EL MÉTODO BuscarClientesEnTabla/ TABLASCLIENTES", JOptionPane.ERROR_MESSAGE);
        }finally{
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return cl;
    }
}
