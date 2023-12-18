/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista.VENTAS.CAJA.NOTAS_CREDITO_DEBITO;

import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import Controlador.VentaDao;
import Modelo.Detalle;
import Modelo.Venta;
import Tablas.ActualizarTablaVentasDiariasYGenerales;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MASALDOTER_GT
 */
public class NOTAS_DEBITO_CREDITO extends javax.swing.JDialog {

    /**
     * Creates new form NOTAS_DEBITO_CREDITO
     */
    public NOTAS_DEBITO_CREDITO(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public NOTAS_DEBITO_CREDITO(java.awt.Frame parent, boolean modal, String NumeroDocumento, String ID) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        jTextField1.setText(NumeroDocumento);
        VerDetalles_Documento(ID);
        VerDetalles();
        if(CajaTipoDocumento.getText().equals("FACTURA")){
            jPanel4.setVisible(true);
        }else{
            jPanel4.setVisible(false);
        }
    }
    
    public void VerDetalles_Documento(String ID){
        VentaDao VD= new VentaDao();
        Venta v= new Venta();
        v = VD.BUSCAR_REGISTRO(ID);
        CajaTipoDocumento.setText(v.getTipoDocumentoFel());
        CajaNitEmisor.setText(v.getNitEmisor());
        CajaNumeroDocumentoFEL.setText(v.getNumeroDocumento());
        CajaSerieFel.setText(v.getSerieDocumento());
        CajaAutorizacionFEL.setText(v.getNumeroAutorizacion());
        CajaAutorizacionFEL2.setText(v.getFechaAutorizacion());
        CajaAutorizacionFEL1.setText(v.getFecha()+ " | "+v.getHora());
        v.setHora(ID);
        v.setTipoDocumentoFel(ID);
        v.setNumeroDocumento(ID);
        v.setNumeroAutorizacion(ID);
        v.setSerieDocumento(ID);
    }
    
    public void VerDetalles(){
        ActualizarTablaVentasDiariasYGenerales tablas = new ActualizarTablaVentasDiariasYGenerales();
        LIMPIAR_TABLA(TablaDetalles);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) TablaDetalles.getModel();
        List<Detalle> ListarPr = null;
            ListarPr = tablas.DETALLE_VENTA(jTextField1.getText());
        Object[] ob = new Object[9];
        for (int i = 0; i < ListarPr.size(); i++) {
            //ob[0] = ListarPr.get(i).getIddetalle();
            ob[0] = ListarPr.get(i).getCodigoBarras();
            ob[1] = ListarPr.get(i).getNombre();
            ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getPrecio();
            ob[4] = ListarPr.get(i).getDescuento();
            ob[5] = ListarPr.get(i).getPrecio_Con_Descuento();
            ob[6] = ListarPr.get(i).getTotal();
            ob[7] = ListarPr.get(i).getAplicar_Descuento();
            ob[8] = ListarPr.get(i).getValidacionProductoExistente();
            
            
            modelo.addRow(ob);
            //[255,230,205]
        }
        TablaDetalles.setModel(modelo);
    
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
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        CajaTipoDocumento = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        lblNitEmisor = new javax.swing.JLabel();
        CajaNitEmisor = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        CajaAutorizacionFEL = new javax.swing.JTextField();
        lblAutorizacionFEL = new javax.swing.JLabel();
        CajaSerieFel = new javax.swing.JTextField();
        lblSerieFel = new javax.swing.JLabel();
        CajaNumeroDocumentoFEL = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblAutorizacionFEL2 = new javax.swing.JLabel();
        CajaAutorizacionFEL2 = new javax.swing.JTextField();
        lblAutorizacionFEL1 = new javax.swing.JLabel();
        CajaAutorizacionFEL1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDetalles = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NOTAS DE CRÉDITO Y DÉBITO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );

        CajaTipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CajaTipoDocumento.setForeground(new java.awt.Color(255, 0, 51));
        CajaTipoDocumento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CajaTipoDocumento.setText("DATOS DE DOCUMENTO");

        jLabel2.setText("NUMERO DE DOCUMENTO:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        lblNitEmisor.setText("NIT RECEPTOR:");

        CajaNitEmisor.setEditable(false);

        CajaAutorizacionFEL.setEditable(false);

        lblAutorizacionFEL.setText("AUTORIZACION DTE:");

        CajaSerieFel.setEditable(false);

        lblSerieFel.setText("SERIE DTE:");

        CajaNumeroDocumentoFEL.setEditable(false);

        jLabel4.setText("NUMERO DTE:");

        lblAutorizacionFEL2.setText("FECHA Y HORA CERTIFICACIÓN:");

        CajaAutorizacionFEL2.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaNumeroDocumentoFEL, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblSerieFel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaSerieFel, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lblAutorizacionFEL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaAutorizacionFEL, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblAutorizacionFEL2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaAutorizacionFEL2, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaNumeroDocumentoFEL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSerieFel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaSerieFel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAutorizacionFEL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaAutorizacionFEL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAutorizacionFEL2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaAutorizacionFEL2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblAutorizacionFEL1.setText("FECHA Y HORA REALIZADA:");

        CajaAutorizacionFEL1.setEditable(false);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/BUSCAR_DERECHA_32PX.png"))); // NOI18N

        jLabel3.setText("TIPO DE DOCUMENTO:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblNitEmisor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaNitEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblAutorizacionFEL1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaAutorizacionFEL1, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaTipoDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaTipoDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNitEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaNitEmisor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAutorizacionFEL1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaAutorizacionFEL1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("TOTAL DE PRODUCTOS");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));

        TablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE", "CANTIDAD", "PRECIO NORMAL", "%", "PRECIO CON %", "TOTAL", "VALIDACIÓN", "ID", "DEVOLUCION", "DEVOLUCION", "TOTAL DEVOLUCION"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaDetalles.setRowHeight(25);
        TablaDetalles.getTableHeader().setReorderingAllowed(false);
        TablaDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaDetallesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaDetalles);
        // Agregar un ItemListener al checkbox en la columna "Seleccionar"
        TablaDetalles.getColumnModel().getColumn(9).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        TablaDetalles.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 9) {
                    int row = e.getFirstRow();
                    boolean selected = (boolean) TablaDetalles.getValueAt(row, 9);

                    // Habilitar o deshabilitar las columnas Columna1 y Columna2 según si el checkbox está seleccionado
                    TablaDetalles.getColumnModel().getColumn(10).setPreferredWidth(selected ? 100 : 0);
                    TablaDetalles.getColumnModel().getColumn(11).setPreferredWidth(selected ? 100 : 0);
                    TablaDetalles.getTableHeader().resizeAndRepaint();
                }
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            VerDetalles();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void TablaDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaDetallesMouseClicked
       if(Boolean.parseBoolean(TablaDetalles.getValueAt(TablaDetalles.getSelectedRow(), 9).toString())==true){
           TablaDetalles.setEditingColumn(TablaDetalles.getSelectedRow());
       }
    }//GEN-LAST:event_TablaDetallesMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NOTAS_DEBITO_CREDITO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NOTAS_DEBITO_CREDITO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NOTAS_DEBITO_CREDITO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NOTAS_DEBITO_CREDITO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NOTAS_DEBITO_CREDITO dialog = new NOTAS_DEBITO_CREDITO(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CajaAutorizacionFEL;
    private javax.swing.JTextField CajaAutorizacionFEL1;
    private javax.swing.JTextField CajaAutorizacionFEL2;
    private javax.swing.JTextField CajaNitEmisor;
    private javax.swing.JTextField CajaNumeroDocumentoFEL;
    private javax.swing.JTextField CajaSerieFel;
    private javax.swing.JLabel CajaTipoDocumento;
    private javax.swing.JTable TablaDetalles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAutorizacionFEL;
    private javax.swing.JLabel lblAutorizacionFEL1;
    private javax.swing.JLabel lblAutorizacionFEL2;
    private javax.swing.JLabel lblNitEmisor;
    private javax.swing.JLabel lblSerieFel;
    // End of variables declaration//GEN-END:variables
}
