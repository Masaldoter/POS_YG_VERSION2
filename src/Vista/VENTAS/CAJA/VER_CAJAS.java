/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista.VENTAS.CAJA;

import static CLASES_GLOBALES.METODOS_GLOBALES.Fecha;
import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import CLASES_GLOBALES.PARAMETROS_VENTAS;
import Modelo.CAJA;
import Tablas.ACTUALIZA_CAJA;
import Vista.Principal;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MASALDOTER_GT
 */
public class VER_CAJAS extends javax.swing.JDialog {

    Principal principal;
    public VER_CAJAS(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }
    public VER_CAJAS(java.awt.Frame parent, boolean modal, Principal principal) {
        super(parent, modal);
        initComponents();
        this.principal = principal;
        ACTUALIZAR_CAJAS();
        this.setLocationRelativeTo(this);
    }
    
    public void ACTUALIZAR_CAJAS(){
       LIMPIAR_TABLA(jTable1);
        LIMPIAR_TABLA(jTable1);
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) jTable1.getModel();
        
        List<CAJA> ListarPr = ACTUALIZA_CAJA.LISTAR_CAJAS_ACTIVAS(Fecha());
        Object[] ob = new Object[3];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdcaja();
            ob[1] = ListarPr.get(i).getESTADO_DE_CAJA();
            ob[2] = ListarPr.get(i).getTotal_inicial_CAJA();
            modelo2.addRow(ob);
        }
        jTable1.setModel(modelo2);
       //return ob;
       
       // Obtener el modelo de selección de filas
        ListSelectionModel selectionModel = jTable1.getSelectionModel();
        
        // Establecer el modo de selección a selección única
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Seleccionar la primera fila
        selectionModel.setSelectionInterval(0, 0);
        
        jTable1.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SELECCIONE UNA CAJA");

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "# CAJA", "ESTADO", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(40);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        PARAMETROS_VENTAS.NUMERO_CAJA = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
        principal.jLabel4.setText(PARAMETROS_VENTAS.NUMERO_CAJA);
        this.dispose();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            PARAMETROS_VENTAS.NUMERO_CAJA = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            principal.jLabel4.setText(PARAMETROS_VENTAS.NUMERO_CAJA);
            this.dispose();
        }
    }//GEN-LAST:event_jTable1KeyPressed

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
            java.util.logging.Logger.getLogger(VER_CAJAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VER_CAJAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VER_CAJAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VER_CAJAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VER_CAJAS dialog = new VER_CAJAS(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
