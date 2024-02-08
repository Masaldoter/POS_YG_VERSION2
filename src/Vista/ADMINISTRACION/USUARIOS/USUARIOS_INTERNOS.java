/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.ADMINISTRACION.USUARIOS;

import Controlador.Eventos;
import Tablas.ActualizarTablaUsuarios;
import Vista.Principal;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author aldoy
 */
public class USUARIOS_INTERNOS extends javax.swing.JInternalFrame {
    Principal principal;
    public USUARIOS_INTERNOS() {
    }
    
    public USUARIOS_INTERNOS(Principal principal) {
        initComponents();
        this.principal = principal;
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    
    public static void Usuarios(){
        ActualizarTablaUsuarios ATU= new ActualizarTablaUsuarios();
        ATU.Usuarios(TablalUsuarios);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        USUARIOS_INTERNOS = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        UsuarioVista = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TablalUsuarios = new javax.swing.JTable();

        jPanel4.setBackground(new java.awt.Color(51, 153, 255));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("USUARIO SELECCIONADO: ");

        UsuarioVista.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UsuarioVista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                UsuarioVistaKeyTyped(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(243, 68, 68));
        jButton19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 255, 255));
        jButton19.setText("ADMINISTRAR");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(0, 215, 0));
        jButton17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("AGREGAR");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UsuarioVista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UsuarioVista, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)))
                .addContainerGap())
        );

        TablalUsuarios  = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                for(int i = 0; i < TablalUsuarios.getRowCount(); i ++){
                    if(row < 0){
                        return true;
                    }
                }
                return false;
            }
        };
        TablalUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TablalUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablalUsuarios.setRowHeight(30);
        TablalUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablalUsuariosMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(TablalUsuarios);

        javax.swing.GroupLayout USUARIOS_INTERNOSLayout = new javax.swing.GroupLayout(USUARIOS_INTERNOS);
        USUARIOS_INTERNOS.setLayout(USUARIOS_INTERNOSLayout);
        USUARIOS_INTERNOSLayout.setHorizontalGroup(
            USUARIOS_INTERNOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        USUARIOS_INTERNOSLayout.setVerticalGroup(
            USUARIOS_INTERNOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, USUARIOS_INTERNOSLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(USUARIOS_INTERNOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(USUARIOS_INTERNOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UsuarioVistaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsuarioVistaKeyTyped
        Eventos event = new Eventos();
        event.numberKeyPress(evt);
    }//GEN-LAST:event_UsuarioVistaKeyTyped

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if(UsuarioVista.getText().equals("")){
            JOptionPane.showMessageDialog(this, "¡DEBE SELECCIONAR UN USUARIO!");
        }else{
            if (principal.VENTANA_ADMINISTRACION_DE_USUARIOS == false) {
                principal.VENTANA_ADMINISTRACION_DE_USUARIOS = true;
                principal.ADMIN_USUARIOS = new ADMINISTRACION_DE_USUARIOS(principal);
                principal.ADMIN_USUARIOS.setVisible(true);
                principal.ADMIN_USUARIOS.BUSCAR_USUARIO(UsuarioVista.getText());
            } else {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ERRÓR", "NO PUEDE ABRIR MÁS DE 1 VENTANA DE ADMINISTRACIÓN DE USUARIOS", DesktopNotify.ERROR, 10000L);
                // Si la ventana ya ha sido creada, lo restauramos si está minimizado
                    if ((principal.ADMIN_USUARIOS.getExtendedState() & JFrame.ICONIFIED) != 0) {
                        principal.ADMIN_USUARIOS.setExtendedState(principal.ADMIN_USUARIOS.getExtendedState() & ~JFrame.ICONIFIED);
                    }
                principal.ADMIN_USUARIOS.toFront();
            }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        if (principal.VENTANA_ADMINISTRACION_DE_USUARIOS == false) {
            principal.VENTANA_ADMINISTRACION_DE_USUARIOS = true;
            principal.ADMIN_USUARIOS = new ADMINISTRACION_DE_USUARIOS(principal);
            principal.ADMIN_USUARIOS.setVisible(true);
        } else {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERRÓR", "NO PUEDE ABRIR MÁS DE 1 VENTANA DE ADMINISTRACIÓN DE USUARIOS", DesktopNotify.ERROR, 10000L);
            // Si la ventana ya ha sido creada, lo restauramos si está minimizado
                    if ((principal.ADMIN_USUARIOS.getExtendedState() & JFrame.ICONIFIED) != 0) {
                        principal.ADMIN_USUARIOS.setExtendedState(principal.ADMIN_USUARIOS.getExtendedState() & ~JFrame.ICONIFIED);
                    }
            principal.ADMIN_USUARIOS.toFront();
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void TablalUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablalUsuariosMouseClicked
        int fila = TablalUsuarios.getSelectedRow();
        UsuarioVista.setText(TablalUsuarios.getValueAt(fila, 0).toString());
    }//GEN-LAST:event_TablalUsuariosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTable TablalUsuarios;
    private javax.swing.JPanel USUARIOS_INTERNOS;
    private javax.swing.JTextField UsuarioVista;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton19;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane7;
    // End of variables declaration//GEN-END:variables
}
