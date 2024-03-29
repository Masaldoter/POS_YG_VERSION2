/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista.REPORTES_VENTAS;

import Conexiones.ConexionesSQL;
import java.sql.SQLException;

/**
 *
 * @author MASALDOTER_GT
 */
public class VISTA_VENTAS extends javax.swing.JFrame {

    /**
     * Creates new form VISTA_VENTAS
     */
    public VISTA_VENTAS() {
        initComponents();
        this.setLocationRelativeTo(null);
        iniciarHilo();
    }
    
    public String OBTENER_VENTAS_ULTIMOS_5_SEGUNDOS(){
        String NUMERO_INTERNO = null;
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("SELECT NoFactura FROM registro WHERE Hora >= (CURRENT_TIME - INTERVAL 10 SECOND) ORDER BY Hora DESC;");
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            if (ConexionesSQL.rs.next()) {
           NUMERO_INTERNO = ConexionesSQL.rs.getString("NoFactura");
           
            }
        } catch (SQLException e) {
            System.err.println("Error en JFRAME, " + e);
        }finally{
            ConexionesSQL.RsClose(ConexionesSQL.rs);
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
            
        }
        
        VENTA_RESUMEN v= new VENTA_RESUMEN(NUMERO_INTERNO);
            jDesktopPane1.add(v);
            v.setVisible(true);
        return NUMERO_INTERNO;
    }
    
    private void iniciarHilo() {
        Thread hiloProcesador = new Thread(() -> {
            while (true) {
                OBTENER_VENTAS_ULTIMOS_5_SEGUNDOS();
                // Pausa el hilo por un tiempo antes de volver a revisar la cola
                try {
                    Thread.sleep(5000); // Puedes ajustar el tiempo según tus necesidades
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        hiloProcesador.start();
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VISTA_VENTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VISTA_VENTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VISTA_VENTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VISTA_VENTAS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VISTA_VENTAS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane1;
    // End of variables declaration//GEN-END:variables
}
