/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista.ADMINISTRACION.INVENTARIO;

import Conexiones.conexion;
import Controlador.TextPrompt;
import Modelo.Categoria;
import Tablas.ActualizarTablasCategorias_SubCategorias_Ubicaciones;
import Tablas.RenderTablas;
import Vista.Principal;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aldoy
 */
public class CategoriaVista extends javax.swing.JDialog {
    Principal principal;
    /**
     * Creates new form CategoriaVista2
     */
    public CategoriaVista(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public CategoriaVista(java.awt.Frame parent, boolean modal, int Rol, Principal principal) {
        super(parent, modal);
        initComponents();
        this.principal = principal;
        this.setLocationRelativeTo(null);
        this.setTitle("ADMINISTRACIÓN DE CATEGORÍAS");
        ActualizarTablaCategorias(false);
        TextoEnCajas();
        BloquearBotones();
        //TablaCategorias.setShowHorizontalLines(true);
        Cerrar();
        if (Rol == 0) {
            jPanel4.setVisible(true);
            jPanel3.setVisible(true);
        } else {
            jPanel4.setVisible(false);
            jPanel3.setVisible(false);
        }
    }

    public static void TextoEnCajas() {
        TextPrompt hold;
        hold = new TextPrompt("BUSCAR UNA CATEGORIA", jTextField1);
    }

    public final void BloquearBotones() {
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    public void DesBloquearBotones() {
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
    }

    public void LimpiarCategoria() {
        CajaNombre.setText(null);
        CajaId.setText(null);
    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Imagenes/Categoria.png"));

        return retValue;
    }

    public final void Cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ConfirmarSalida();
                }
            });
            this.setVisible(true);

        } catch (Exception e) {
        }
    }

    public void ConfirmarSalida() {
        principal.VentanaCategoria = false;
        this.dispose();
    }

    public static void LimpiarTablaVenta(JTable Tabla) {
        DefaultTableModel modelo = new DefaultTableModel();
        for (int e = 0; e < 15; e++) {

            for (int i = 0; i < Tabla.getRowCount(); i++) {
                modelo = (DefaultTableModel) Tabla.getModel();
                modelo.removeRow(i);
            }
        }
    }

    public static void ActualizarTablaCategorias(Boolean Buscar) {
        TablaCategorias.setDefaultRenderer(Object.class, new RenderTablas());
        ActualizarTablasCategorias_SubCategorias_Ubicaciones tablas = new ActualizarTablasCategorias_SubCategorias_Ubicaciones();
        LimpiarTablaVenta(TablaCategorias);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) TablaCategorias.getModel();
        List<Categoria> ListarPr = null;
        if (Buscar == true) {
            ListarPr = tablas.ActualizarCategorias(jTextField1.getText(), true);
        } else {
            ListarPr = tablas.ActualizarCategorias("", false);
        }
        Object[] ob = new Object[3];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdCategoria();
            ob[1] = ListarPr.get(i).getCategoria();
            ob[2] = ListarPr.get(i).getVerSubCategoria();
            modelo.addRow(ob);
            //[255,230,205]
        }
        TablaCategorias.setModel(modelo);
    }

    public void Registrar() {
        conexion cn = conexion.getInstancia();
        Connection Union = cn.getConnection();
        PreparedStatement ps = null;
        String a = CajaNombre.getText();

        if (a.equals("")) {
            JOptionPane.showMessageDialog(null, "¡El nombre de la Categoría no puede ir vacía!");
        } else {
            try {

                for (int i = 0; i < TablaCategorias.getRowCount(); i++) {
                    if (TablaCategorias.getValueAt(i, 1).equals(CajaNombre.getText())) {
                        JOptionPane.showMessageDialog(null, "¡LA CATEGORÍA YA ESTÁ REGISTRADA!");
                        return;
                    }
                }

                ps = Union.prepareStatement("insert into categoria (Categoria) values (?)");
                ps.setString(1, a);
                int resultado = ps.executeUpdate();
                if (resultado > 0) {

                    JOptionPane.showMessageDialog(null, "¡Categoría " + a + " registrada exitosamente!");
                    LimpiarCategoria();
                    ActualizarTablaCategorias(false);
                } else {
                    JOptionPane.showMessageDialog(null, "¡Hubo un error en el registro!");
                }

            } catch (HeadlessException | SQLException e) {
                System.err.println("Error, " + e);
            } finally {
                try {
                    ps.close();
                    Union.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCategorias = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CajaNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CajaId = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TablaCategorias  = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                for(int i = 0; i < TablaCategorias.getRowCount(); i ++){
                    if(row < 0){
                        return true;
                    }
                }
                return false;
            }
        };
        TablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CATEGORIA", "ENLACES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCategorias.setRowHeight(35);
        TablaCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCategoriasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaCategorias);

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField1.setBorder(null);
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/icons8-busca-mas-30.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setText("NOMBRE DE CATEGORÍA");

        CajaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CajaNombreKeyPressed(evt);
            }
        });

        jLabel3.setText("ID");

        CajaId.setEditable(false);
        CajaId.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaNombre)
                    .addComponent(CajaId)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaId, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setBackground(new java.awt.Color(40, 165, 240));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("EDITAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 180, 75));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(240, 30, 78));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("ELIMINAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 0, 0));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("NUEVO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCategoriasMouseClicked

        conexion cn = conexion.getInstancia();
        Connection Union = cn.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int fila = TablaCategorias.getSelectedRow();
        try {

            String codigo = TablaCategorias.getValueAt(fila, 0).toString();
            ps = Union.prepareStatement("select idCategoria, Categoria from categoria where idCategoria=?");
            ps.setString(1, codigo);
            rs = ps.executeQuery();

            while (rs.next()) {
                CajaId.setText(String.valueOf(rs.getInt("idCategoria")));
                CajaNombre.setText(rs.getString("Categoria"));
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        } finally {
            try {
                ps.close();
                rs.close();
                Union.close();
            } catch (SQLException e) {
            }
        }
        DesBloquearBotones();

        int Columna = TablaCategorias.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY() / TablaCategorias.getRowHeight();
        if (Fila < TablaCategorias.getRowCount() && Fila >= 0 && Columna < TablaCategorias.getColumnCount() && Columna >= 0) {
            Object value = TablaCategorias.getValueAt(Fila, Columna);
            if (value instanceof JButton jButton) {
                jButton.doClick();
                JButton boton = jButton;
                if (boton.getName().equals("botonsubcategorias")) {
                    SubCategoria AdminProduct = new SubCategoria(null, true);
                    SubCategoria.jLabel2.setText(TablaCategorias.getValueAt(fila, 0).toString());
                    SubCategoria.jLabel3.setText(TablaCategorias.getValueAt(fila, 1).toString());
                    SubCategoria.ActualizarTablaSubCategorias();
                    AdminProduct.setLocationRelativeTo(this);
                    AdminProduct.setVisible(true);

                }

            }
        }
    }//GEN-LAST:event_TablaCategoriasMouseClicked

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        ActualizarTablaCategorias(true);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void CajaNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaNombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            Registrar();

        }
    }//GEN-LAST:event_CajaNombreKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String a = CajaId.getText();

        conexion cn= conexion.getInstancia();
        Connection Union=cn.getConnection();
        PreparedStatement ps = null;

        if (!"".equals(a)) {
            try {
                ps = Union.prepareStatement("update categoria set Categoria=? where idCategoria=?");
                ps.setString(1, CajaNombre.getText());
                ps.setInt(2, Integer.parseInt(CajaId.getText()));

                int resultado = ps.executeUpdate();

                if (resultado > 0) {
                    JOptionPane.showMessageDialog(null, "¡Categoría "+CajaNombre.getText()+ " modificada exitosamente!");

                    LimpiarCategoria();
                    ActualizarTablaCategorias(false);
                } else {
                    JOptionPane.showMessageDialog(null, "¡Hubo un fallo en la modificación de la Categoría!");
                }

            } catch (SQLException e) {
                System.err.println("Error, " + e);
            }finally{
                try {
                    ps.close();
                    Union.close();
                } catch (SQLException e) {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡Debe seleccionar una Categoría!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Registrar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        conexion cn= conexion.getInstancia();
        Connection Union=cn.getConnection();
        PreparedStatement ps = null;
        if(CajaId.getText() != null){
            int i = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar la Categoría "+CajaNombre.getText()+"?\n¡ES POSIBLE QUE UNOS PRODUCTOS QUE ESTÉN CON ESTA CATEGORÍA NO FUNCIONEN CORRECTAMENTE!", "IMPORTANTE", JOptionPane.WARNING_MESSAGE);

            if (i == 0) {
                try {
                    ps = Union.prepareStatement("delete from categoria where idCategoria=?");
                    ps.setInt(1, Integer.parseInt(CajaId.getText()));

                    int resultado = ps.executeUpdate();

                    if (resultado > 0) {
                        JOptionPane.showMessageDialog(null, "¡Categoría Eliminada correctamente!");
                        LimpiarCategoria();
                        ActualizarTablaCategorias(false);
                    }

                } catch (SQLException e) {
                    System.err.println("Error, " + e);
                }finally{
                    try {
                        ps.close();
                        Union.close();
                    } catch (SQLException e) {
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar una Categoría!");
            }

        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        LimpiarCategoria();
        BloquearBotones();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(CategoriaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoriaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoriaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoriaVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CategoriaVista dialog = new CategoriaVista(new javax.swing.JFrame(), true);
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
    public javax.swing.JTextField CajaId;
    private javax.swing.JTextField CajaNombre;
    private static javax.swing.JTable TablaCategorias;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
