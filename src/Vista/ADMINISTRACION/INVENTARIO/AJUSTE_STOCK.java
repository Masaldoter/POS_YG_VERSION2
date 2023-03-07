/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vista.ADMINISTRACION.INVENTARIO;

import CLASES_GLOBALES.METODOS_GLOBALES;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Controlador.KardexDao;
import Controlador.ProductosDao;
import Controlador.VentaDao;
import Modelo.Kardex;
import Modelo.Productos;
import static Vista.ADMINISTRACION.INVENTARIO.INVENTARIO.REFRESCAR_INVENTARIO;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author aldoy
 */
public class AJUSTE_STOCK extends javax.swing.JDialog {

    Boolean VENTANA_INDEPENDIENTE;
    public AJUSTE_STOCK(java.awt.Frame parent, boolean modal) {
    }
    public AJUSTE_STOCK(java.awt.Frame parent, boolean modal, String Codigo, Boolean Independiente) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        InsertarDatos(Codigo);
        VENTANA_INDEPENDIENTE = Independiente;
        OPERACION();
    }
    
    public static void limpiarCajas() {
        CODIGO.setText(null);
        ID.setText("");
        NOMBRE.setText(null);
        STOCK_ACTUAL.setText(null);
        MOTIVO_AJUSTE.setSelectedItem(null);
        TIPO_AJUSTE.setSelectedItem(null);
        CANTIDAD_A_AJUSTAR.setText(null);
        NUEVO_AJUSTE.setText(null);
        ValidarBotones();
        CODIGO.requestFocus();
    }
    
    public static void InsertarDatos(String codigo) {
            ProductosDao proDao= new ProductosDao();
            Productos proo = new Productos();
        proo.setCodigoBarras(codigo);
        proDao.ActualizarTabla(proo);
        if (proo.getNombre() != null) {
            ID.setText(String.valueOf(proo.getIdProductos()));
            CODIGO.setText(proo.getCodigoBarras());
            NOMBRE.setText(proo.getNombre());
            STOCK_ACTUAL.setText(String.valueOf(proo.getCantidad()));
            CANTIDAD_A_AJUSTAR.setText("0");
            NUEVO_AJUSTE.setText("0");
            ValidarBotones();
            CANTIDAD_A_AJUSTAR.requestFocus(); 
        } else if (proo.getNombre() == null) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("VERIFIQUE EL CÓDIGO", codigo + " NO EXISTE!", DesktopNotify.ERROR, 14000L);
        }

    }

    public Boolean ValidarCajas() {
        Boolean EstadoCajas = false;
        if (CODIGO.getText().equals("")) {
            CODIGO.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL CÓDIGO NO PUEDE IR VACÍO!");
        } else if (ID.getText().equals("")) {
            ID.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL ID NO PUEDE IR VACÍO!");
        } else if (NOMBRE.getText().equals("")) {
            NOMBRE.requestFocus();
            JOptionPane.showMessageDialog(null, "¡LA CANTIDAD DEL PRODUCTO NO PUEDE IR VACÍA!");
        } else if (STOCK_ACTUAL.getText().equals("")) {
            STOCK_ACTUAL.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL PRECIO COSTO DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (CANTIDAD_A_AJUSTAR.getText().equals("")) {
            CANTIDAD_A_AJUSTAR.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL PRECIO ESPECIAL DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (NUEVO_AJUSTE.getText().equals("")) {
            NUEVO_AJUSTE.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL PRECIO DE REVENTA DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (TIPO_AJUSTE.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA CATEGORÍA[1]!");
        } else if (MOTIVO_AJUSTE.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UN PROVEEDOR!");
        } else {
            EstadoCajas = true;
        }

        return EstadoCajas;
    }

    public static void ValidarBotones() {
        if (ID.getText().equals("")) {
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        } else {
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        }
    }

    public static Float OPERACION() {
        Float Total = 0f;
        if (TIPO_AJUSTE.getSelectedIndex() == 0) {
            if (!STOCK_ACTUAL.getText().equals("") && !CANTIDAD_A_AJUSTAR.getText().equals("")) {
                Total = Float.parseFloat(STOCK_ACTUAL.getText()) + Float.parseFloat(CANTIDAD_A_AJUSTAR.getText());
            }
        } else {
            if (!STOCK_ACTUAL.getText().equals("") && !CANTIDAD_A_AJUSTAR.getText().equals("")) {
                Total = Float.parseFloat(STOCK_ACTUAL.getText()) - Float.parseFloat(CANTIDAD_A_AJUSTAR.getText());
            }
        }
        NUEVO_AJUSTE.setText(String.valueOf(Total));
        return Total;
    }
    
    public void Ingreso_Kardex() {
        KardexDao kdDao= new KardexDao();
        Kardex Kd ;
        if (ValidarCajas() == true) {
                Kd = new Kardex();
                Kd.setID_Codigo_Producto_Kardex(Integer.parseInt(ID.getText()));
                Kd.setTitulo_Kardex(MOTIVO_AJUSTE.getSelectedItem().toString()+" | "+TIPO_AJUSTE.getSelectedItem().toString());
                if(TIPO_AJUSTE.getSelectedIndex()==0){
                  Kd.setEntrada_Kardex(CANTIDAD_A_AJUSTAR.getText());  
                  Kd.setSalida_Kardex("0");
                }else{
                    Kd.setEntrada_Kardex("0");
                    Kd.setSalida_Kardex(CANTIDAD_A_AJUSTAR.getText());  
                }
                Kd.setAntes_Kardex(STOCK_ACTUAL.getText());
                Kd.setDespues_Kardex(NUEVO_AJUSTE.getText());
                Kd.setFecha_Modificacion_Kardex(METODOS_GLOBALES.Fecha()+" "+METODOS_GLOBALES.Hora());
                Kd.setUsuario_Modifico_Kardex(PARAMETROS_USUARIOS.ID_USUARIO);
                Kd.setModulo_Kardex("INVENTARIO");
                Boolean ResultadoIngreso = kdDao.RegistrarKARDEX(Kd);
                if (ResultadoIngreso == true) {
                    limpiarCajas();
                    this.dispose();
            }

        }
    }

    public void AJUSTAR_STOCK() {
        ProductosDao proDao = new ProductosDao();
        if (ValidarCajas() == true) {

            int i = JOptionPane.showConfirmDialog(null, "¿SEGURO QUE DESEA AJUSTAR?");
            if (i == 0) {
                if (proDao.ActualizarStock(Float.parseFloat(NUEVO_AJUSTE.getText()), Integer.parseInt(ID.getText())) == true) {
                    if(VENTANA_INDEPENDIENTE==false){
                        ADMINISTRARPRODUCTO.Cantidad.setText(String.valueOf(VentaDao.BuscarSTOCKProducto(Integer.parseInt(ID.getText()))));
                    }
                    Ingreso_Kardex();
                    this.dispose();
                    REFRESCAR_INVENTARIO();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CODIGO = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        NOMBRE = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        STOCK_ACTUAL = new javax.swing.JTextField();
        TIPO_AJUSTE = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        CANTIDAD_A_AJUSTAR = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        NUEVO_AJUSTE = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        MOTIVO_AJUSTE = new javax.swing.JComboBox<>();
        ID = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(435, 391));

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("AJUSTE DE STOCK INDIVIDUAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("CÓDIGO:");

        CODIGO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CODIGO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CODIGOKeyPressed(evt);
            }
        });

        jLabel3.setText("NOMBRE:");

        NOMBRE.setEditable(false);
        NOMBRE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NOMBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NOMBREActionPerformed(evt);
            }
        });

        jLabel4.setText("STOCK ACTUAL:");

        STOCK_ACTUAL.setEditable(false);

        TIPO_AJUSTE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AUMENTAR", "DISMINUIR" }));
        TIPO_AJUSTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TIPO_AJUSTEActionPerformed(evt);
            }
        });

        jLabel5.setText("TIPO:");

        jLabel6.setText("CANTIDAD:");

        CANTIDAD_A_AJUSTAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CANTIDAD_A_AJUSTARKeyReleased(evt);
            }
        });

        jLabel7.setText("NUEVO STOCK:");

        NUEVO_AJUSTE.setEditable(false);

        jLabel8.setText("MOTIVO AJUSTE:");

        MOTIVO_AJUSTE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "RECONTEO FISICO", "TRASLADO DE PRODUCTO", "PRODUCTO INEXISTENTE" }));

        ID.setEditable(false);

        jLabel9.setText("ID:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NOMBRE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(CODIGO)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TIPO_AJUSTE, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(STOCK_ACTUAL)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CANTIDAD_A_AJUSTAR))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NUEVO_AJUSTE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MOTIVO_AJUSTE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(CODIGO, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(ID, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(STOCK_ACTUAL, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MOTIVO_AJUSTE, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TIPO_AJUSTE, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CANTIDAD_A_AJUSTAR, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(NUEVO_AJUSTE, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ACEPTAR_32PX.png"))); // NOI18N
        jButton1.setText("AJUSTAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/CANCELAR_32PX.png"))); // NOI18N
        jButton2.setText("CANCELAR");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/NUEVO_32PX.png"))); // NOI18N
        jButton4.setText("LIMPIAR TODO");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NOMBREActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NOMBREActionPerformed

    private void CODIGOKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CODIGOKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(CODIGO.getText())) {
                InsertarDatos(CODIGO.getText());
            } else {
                JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN CÓDIGO DE BARRAS", "PROCESO INVÁLIDO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_CODIGOKeyPressed

    private void CANTIDAD_A_AJUSTARKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CANTIDAD_A_AJUSTARKeyReleased
        OPERACION();
    }//GEN-LAST:event_CANTIDAD_A_AJUSTARKeyReleased

    private void TIPO_AJUSTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TIPO_AJUSTEActionPerformed
        OPERACION();
    }//GEN-LAST:event_TIPO_AJUSTEActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiarCajas();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(ValidarCajas()==true){
        if(Float.parseFloat(NUEVO_AJUSTE.getText())<0f){
              JOptionPane.showMessageDialog(this, "CANTIDAD NO PUEDE SER MAYOR AL STOCK ACTUAL", "PROCESO INVÁLIDO", JOptionPane.ERROR_MESSAGE);  
              CANTIDAD_A_AJUSTAR.requestFocus();
            }else{
            AJUSTAR_STOCK();
        }    
        }
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
            java.util.logging.Logger.getLogger(AJUSTE_STOCK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AJUSTE_STOCK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AJUSTE_STOCK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AJUSTE_STOCK.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AJUSTE_STOCK dialog = new AJUSTE_STOCK(new javax.swing.JFrame(), true);
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
    private static javax.swing.JTextField CANTIDAD_A_AJUSTAR;
    private static javax.swing.JTextField CODIGO;
    private static javax.swing.JTextField ID;
    private static javax.swing.JComboBox<String> MOTIVO_AJUSTE;
    private static javax.swing.JTextField NOMBRE;
    private static javax.swing.JTextField NUEVO_AJUSTE;
    private static javax.swing.JTextField STOCK_ACTUAL;
    private static javax.swing.JComboBox<String> TIPO_AJUSTE;
    private static javax.swing.JButton jButton1;
    private static javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
