/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.REPORTES_VENTAS;

import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import Conexiones.ConexionesSQL;
import Modelo.Detalle;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MASALDOTER_GT
 */
public class VENTA_RESUMEN extends javax.swing.JInternalFrame {

    /**
     * Creates new form VENTA_RESUMEN
     */
    public VENTA_RESUMEN() {
        initComponents();
    }
    
    public VENTA_RESUMEN(String VENTA) {
        initComponents();
        this.setTitle("VENTA NUMERO INTERNO: "+VENTA);
        LBL_NUMEROINTERNO.setText(VENTA);
        
        
        OBTENER(VENTA);
    }
    
    public void OBTENER(String VENTA){
        OBTENER_REGISTRO(VENTA);
        OBTENER_DETALLES(VENTA);
    }
    
    public void OBTENER_REGISTRO(String VENTA){
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("SELECT "
                + "r.Cliente, "
                + "r.DireccionCliente, "
                + "r.NitCliente, "
                + "r.Pago, "
                + "r.Cambio, "
                + "r.Total, "
                + "r.FormaPago, "
                + "r.Observacion, "
                + "r.Fecha, "
                + "r.Hora, "
                + "r.Usuario, "
                + "l.NombreUsuario "
                + "FROM registro r "
                + "INNER JOIN login1 l ON r.Usuario = l.idlogin1 "
                + "WHERE r.NoFactura = ?");
            ConexionesSQL.ps.setString(1, VENTA);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            if (ConexionesSQL.rs.next()) {
            LBL_CLIENTE.setText(ConexionesSQL.rs.getString("Cliente"));
            LBL_DIRECCION.setText(ConexionesSQL.rs.getString("DireccionCliente"));
            LBL_IDCLIENTE.setText(ConexionesSQL.rs.getString("NitCliente"));
            LBL_FORMA_DE_PAGO.setText(ConexionesSQL.rs.getString("Pago")+" | "+ConexionesSQL.rs.getString("FormaPago"));
                        LBL_CAMBIO.setText(ConexionesSQL.rs.getString("Cambio"));
            LBL_TOTAL.setText(ConexionesSQL.rs.getString("Total"));
             LBL_DESCRIPCION.setText(ConexionesSQL.rs.getString("Observacion"));
            LBL_FECHA_HORA.setText(ConexionesSQL.rs.getString("Fecha") + " | "+ConexionesSQL.rs.getString("Hora"));
            LBL_VENDEDOR.setText(ConexionesSQL.rs.getString("NombreUsuario"));
            }
        } catch (SQLException e) {
            System.err.println("Error en JFRAME, " + e);
        }finally{
            ConexionesSQL.RsClose(ConexionesSQL.rs);
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
            
        }
    }
    
    public void OBTENER_DETALLES(String VENTA){
        LIMPIAR_TABLA(TABLA);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) TABLA.getModel();
        
        List<Detalle> ListarPr = null;
            ListarPr = DETALLE_VENTA(VENTA);
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNombre();
            ob[1] = ListarPr.get(i).getCantidad();
            ob[2] = ListarPr.get(i).getPrecio_Con_Descuento();
            ob[3] = ListarPr.get(i).getTotal();
            modelo.addRow(ob);
            //[255,230,205]
        }
        TABLA.setModel(modelo);
    }
    
    public List DETALLE_VENTA(String FACTURA){
        List<Detalle> Listapro = new ArrayList();
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            Detalle deta;
             ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("select iddetalle, Nombre, Cantidad, "
                     + "Descuento_Detalle, Precio_Con_Descuento_Detalle, Total "
                     + "from detalle where NoFactura='"+ FACTURA+"'");
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();

            while (ConexionesSQL.rs.next()) {
                deta = new Detalle();
                deta.setIddetalle(ConexionesSQL.rs.getInt("iddetalle"));
                deta.setNombre(ConexionesSQL.rs.getString("Nombre"));
                deta.setCantidad(ConexionesSQL.rs.getFloat("Cantidad"));
                deta.setDescuento(ConexionesSQL.rs.getFloat("Descuento_Detalle"));
                deta.setPrecio_Con_Descuento(ConexionesSQL.rs.getFloat("Precio_Con_Descuento_Detalle"));
                deta.setTotal(Float.parseFloat(ConexionesSQL.rs.getString("Total")));
               Listapro.add(deta);
            }

        } catch (SQLException e) {
            System.err.println("Error ListarVentasDiarias, " + e);
        }finally{
                
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
            ConexionesSQL.RsClose(ConexionesSQL.rs);
        }
        return Listapro;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        LBL_NUMEROINTERNO = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        LBL_CLIENTE = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        LBL_IDCLIENTE = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        LBL_DIRECCION = new javax.swing.JLabel();
        LBL_TIPODOCUMENTO = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        LBL_VENDEDOR = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLA = new javax.swing.JTable();
        LBL_TOTAL = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LBL_FORMA_DE_PAGO = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        LBL_CAMBIO = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        LBL_DESCRIPCION = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        LBL_FECHA_HORA = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        LBL_NUMEROINTERNO.setText("NUMERO INTERNO");

        jLabel8.setText("CLIENTE");

        LBL_CLIENTE.setText("jLabel5");

        jLabel10.setText("IDENT.:");

        LBL_IDCLIENTE.setText("jLabel5");

        jLabel12.setText("DIRECCIÓN");

        LBL_DIRECCION.setText("jLabel5");

        LBL_TIPODOCUMENTO.setText("NUMERO INTERNO");

        jLabel9.setText("VENDEDOR:");

        LBL_VENDEDOR.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_DIRECCION, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(LBL_NUMEROINTERNO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_TIPODOCUMENTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LBL_CLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LBL_IDCLIENTE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_VENDEDOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LBL_TIPODOCUMENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_NUMEROINTERNO, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_VENDEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_IDCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LBL_DIRECCION, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        TABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NOMBRE", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TABLA);

        LBL_TOTAL.setText("jLabel5");

        jLabel2.setText("TOTAL:");

        jLabel3.setText("FORMA DE PAGO:");

        LBL_FORMA_DE_PAGO.setText("jLabel6");

        jLabel4.setText("CAMBIO:");

        LBL_CAMBIO.setText("jLabel6");

        LBL_DESCRIPCION.setEditable(false);
        LBL_DESCRIPCION.setColumns(20);
        LBL_DESCRIPCION.setLineWrap(true);
        LBL_DESCRIPCION.setRows(5);
        jScrollPane2.setViewportView(LBL_DESCRIPCION);

        jLabel5.setText("FECHA Y HORA:");

        LBL_FECHA_HORA.setText("jLabel6");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_FORMA_DE_PAGO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_CAMBIO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(LBL_TOTAL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LBL_FECHA_HORA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_TOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LBL_FORMA_DE_PAGO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_CAMBIO, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBL_FECHA_HORA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LBL_CAMBIO;
    private javax.swing.JLabel LBL_CLIENTE;
    private javax.swing.JTextArea LBL_DESCRIPCION;
    private javax.swing.JLabel LBL_DIRECCION;
    private javax.swing.JLabel LBL_FECHA_HORA;
    private javax.swing.JLabel LBL_FORMA_DE_PAGO;
    private javax.swing.JLabel LBL_IDCLIENTE;
    private javax.swing.JLabel LBL_NUMEROINTERNO;
    private javax.swing.JLabel LBL_TIPODOCUMENTO;
    private javax.swing.JLabel LBL_TOTAL;
    private javax.swing.JLabel LBL_VENDEDOR;
    private javax.swing.JTable TABLA;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
