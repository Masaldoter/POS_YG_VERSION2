/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista;

import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import Controlador.LicenciaDao;
import Controlador.SerialesDao;
import LICENCIA.SERIALES;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author MASALDOTER_GT
 */
public class LICENCIA extends javax.swing.JDialog {

    /**
     * Creates new form LICENCIA
     */
    public LICENCIA(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        initComponents();
        this.setLocationRelativeTo(null);
        LicenciaDao Lic = new LicenciaDao();
        jLabel6.setText(PARAMETROS_VERSION_SISTEMA.NOMBRE_SISTEMA);
        jLabel8.setText(PARAMETROS_VERSION_SISTEMA.VERSION_SISTEMA);
        jLabel10.setText(PARAMETROS_VERSION_SISTEMA.NOMBRE_DESARROLLADOR);
        this.setVisible(false);
        if (VALIDAR_FECHA()==true) {
        } else {
            jLabel12.setText("NECESITA UNA LICENCIA");
        }
    }
    
    public LICENCIA() {
        initComponents();
        this.setLocationRelativeTo(null);
        LicenciaDao Lic = new LicenciaDao();
        jLabel6.setText(PARAMETROS_VERSION_SISTEMA.NOMBRE_SISTEMA);
        jLabel8.setText(PARAMETROS_VERSION_SISTEMA.VERSION_SISTEMA);
        jLabel10.setText(PARAMETROS_VERSION_SISTEMA.NOMBRE_DESARROLLADOR);
        jLabel12.setText(String.valueOf(Lic.OBTENER_DIAS_RESTANTES()));
        this.setVisible(false);
        if (VALIDAR_FECHA() == true) {
            this.setVisible(false);
            this.setVisible(false);

            LOGIN login = new LOGIN();
            login.setVisible(true);
        } else {
            this.setVisible(true);
        }
    }
    
    public void Limpiar(){
        LicenciaDao Lic = new LicenciaDao();
        jLabel12.setText(String.valueOf(Lic.OBTENER_DIAS_RESTANTES()));
        jTextField1.setText("");
        VALIDAR_FECHA();
    }

    public void VERIFICAR_ENTRADA() {
        SerialesDao SD = new SerialesDao();
        Boolean Validez = SD.VERIFICAR_SERIAL(jTextField1.getText());
        SERIALES Ser ;
        if (Validez == false) {
            Ser = new SERIALES();
            if (Ser.verificarNombreExistente(jTextField1.getText())==true) {
                LicenciaDao Lic = new LicenciaDao();
                boolean Estado= Lic.ACTUALIZAR_DIAS_RESTANTES(Ser.OBTENER_VALOR_SERIAL(jTextField1.getText()));
                if(Estado==true){
                    JOptionPane.showMessageDialog(this, "SE HA AGREGADO "+Ser.OBTENER_VALOR_SERIAL(jTextField1.getText())+" MES(ES) AL SISTEMA", "SERIAL VÁLIDA", JOptionPane.INFORMATION_MESSAGE);
                  SD.RegistrarSerial(String.valueOf(jTextField1.getText()));  
                  javax.swing.JOptionPane.showMessageDialog(null, "DEBE REINICIAR EL SISTEMA");
                    Limpiar();
                }
            } else {
                JOptionPane.showMessageDialog(this, "EL SERIAL "+jTextField1.getText()+" NO EXISTE", "SERIAL INVÁLIDA", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "EL SERIAL "+jTextField1.getText()+" YA SE USÓ", "SERIAL INVÁLIDA", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public Boolean VALIDAR_FECHA(){
        LicenciaDao LicDao= new LicenciaDao();
        Boolean Estado=false;
        // Fecha de vencimiento obtenida de la base de datos

        // Define el formato de entrada para parsear la fecha
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        try {
            // Parsea la fecha de texto desde la base de datos a un objeto LocalDate
            String fechaDesdeBaseDeDatos = LicDao.OBTENER_FECHA_GUARDADA();
// Reemplaza los guiones por barras
            fechaDesdeBaseDeDatos = fechaDesdeBaseDeDatos.replace("-", "/");
// Luego, analiza la fecha
            LocalDate fechaVencimiento = LocalDate.parse(fechaDesdeBaseDeDatos, formato);

            // Obtiene la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Compara las fechas
            if (fechaActual.isBefore(fechaVencimiento)) {
                Estado= true;
                jLabel14.setText("LICENCIA VIGENTE HASTA "+fechaVencimiento);
                jLabel12.setText(String.valueOf(LicDao.OBTENER_DIAS_RESTANTES()));
            } else if (fechaActual.isEqual(fechaVencimiento)) {
                Estado= true;
                jLabel14.setText("LA LICENCIA EXPIRA HOY "+fechaVencimiento);
                javax.swing.JOptionPane.showMessageDialog(null, "LA LICENCIA EXPIRA HOY "+fechaVencimiento);
                jLabel12.setText(String.valueOf(LicDao.OBTENER_DIAS_RESTANTES()));
            } else {
                Estado= false;
                jLabel14.setText("LA LICENCIA HA CADUCADO "+fechaVencimiento);
                jLabel12.setText(String.valueOf(LicDao.OBTENER_DIAS_RESTANTES()));
                javax.swing.JOptionPane.showMessageDialog(null, "LA LICENCIA HA CADUCADO "+fechaVencimiento);
            }
        } catch (Exception e) {
            System.out.println("Error al parsear la fecha en VALIDAR_FECHA: " + e.getMessage());
        }
        return Estado;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel6.setBackground(new java.awt.Color(51, 153, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setToolTipText("");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("VERSIÓN ACTUAL:");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("jLabel3");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("DESARROLLADOR:");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("jLabel5");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 102, 51));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("DÍAS RESTANTES");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel13.setText("SERIAL:");

        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setText("VERSIÓN BÁSICA:\n-POS\n-INVENTARIO\n-PROVEEDORES\n-CATEGORIAS\n-UBICACIÓNES\n-CAJA\n-REPORTES\n\nVERSIÓN PREMIUM:\n-POS\n-INVENTARIO\n-PROVEEDORES\n-CATEGORIAS\n-UBICACIÓNES\n-CAJA\n-REPORTES\n-KARDEX\n-VALES\n-COTIZACIÓNES\n-TRASLADOS\n\nFACTURA ELECTRÓNICA (FEL):\n-PAGO MENSUAL DEPENDIENDO LA CANDTIDAD DE DT'ES EMITIDOS.");
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        VERIFICAR_ENTRADA();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(LICENCIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LICENCIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LICENCIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LICENCIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            LICENCIA dialog = new LICENCIA(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
