/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.ADMINISTRACION.PROVEEDORES;

import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import Controlador.ProveedoresDao;
import Modelo.Proveedor;
import Tablas.ActualizarTablaProveedor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author aldoy
 */
public class PROVEEDORES extends javax.swing.JInternalFrame {

    ActualizarTablaProveedor tablasproveedores = new ActualizarTablaProveedor();
    ProveedoresDao proveDao = new ProveedoresDao();
    Proveedor prove = new Proveedor();

    public PROVEEDORES() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        LimpiarProveedor();
    }

    public static void ACTUALIZAR_PROVEEDOR() {
        ActualizarTablaProveedores();
    }

     public void LimpiarProveedor() {
        Prov.setText("");
        Vendedor.setText(null);
        Cel.setText(null);
        filtro("", TablaProveedores, 1);
        Prov.requestFocus();
        AgregarProv.setVisible(true);
        EditarProv.setVisible(false);
    }

    private void filtro(String consulta, JTable jtableBuscar, int TipoBusqueda) {
        DefaultTableModel dm;
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + consulta, TipoBusqueda));
    }

    public void IngresoProveedoruno() 
    {
        DefaultTableModel modelo = new DefaultTableModel();
        String b = Vendedor.getText()+" ";
        String c = Cel.getText()+" ";
        if (Prov.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "¡El nombre del Proveedor no puede ir vacío!");
        }else if (b.equals("")){
        }else{
             for (int i = 0; i < TablaProveedores.getRowCount(); i++) {
                    if (TablaProveedores.getValueAt(i, 1).equals(Prov.getText())) {
                        JOptionPane.showMessageDialog(null, "¡EL NOMBRE DE EMPRESA YA ESTÁ REGISTRADA!");
                        return;
                    }
                }
                        prove.setProveedor(Prov.getText());
                        prove.setVendedor(c);
                        prove.setTel(c);
                        proveDao.IngresoProveedor(prove);
                    modelo = (DefaultTableModel) TablaProveedores.getModel();    
                    LimpiarProveedor();
            
        }

    }
    
    public void EliminarProveedor() {
        Proveedor prove = new Proveedor();
        if (!"".equals(IdProveedor.getText())) {
            int i = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE ELIMINAR EL PROVEEDOR: " + Prov.getText() + "?");
            if (i == 0) {
                prove.setIdproveedores(Integer.parseInt(IdProveedor.getText()));
                prove.setProveedor(Prov.getText());
                prove.setVendedor(Vendedor.getText());
                prove.setTel(Cel.getText());
                proveDao.EliminarProveedor(prove);
                LimpiarProveedor();
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡AÚN NO HA SELECCIONADO NINGÚN PROVEEDOR!");
        }
    }

    public void EditarProveedor() {
        if (!"".equals(IdProveedor.getText())) {
            prove.setIdproveedores(Integer.parseInt(IdProveedor.getText()));
            prove.setProveedor(Prov.getText());
            prove.setVendedor(Vendedor.getText());
            prove.setTel(Cel.getText());
            proveDao.EditarProducto(prove);
            LimpiarProveedor();
        } else {
            JOptionPane.showMessageDialog(null, "¡AÚN NO HA SELECCIONADO NINGÚN PROVEEDOR!");
        }
    }

    public void ConsultaProveedor() {
        Proveedor prove;
        String idProveedor = IdProveedor.getText();
        prove = proveDao.BuscarProveedor(idProveedor);

        IdProveedor.setText(String.valueOf(prove.getIdproveedores()));
        Prov.setText(prove.getProveedor());
        Vendedor.setText(prove.getVendedor());
        Cel.setText(prove.getTel());
    }

    public static void ActualizarTablaProveedores() {
        LIMPIAR_TABLA(TablaProveedores);
        LIMPIAR_TABLA(TablaProveedores);
        DefaultTableModel modelo2 = new DefaultTableModel();
        modelo2 = (DefaultTableModel) TablaProveedores.getModel();
        List<Proveedor> ListarPr = ActualizarTablaProveedor.ListarProveedores();
        Object[] ob = new Object[4];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdproveedores();
            ob[1] = ListarPr.get(i).getProveedor();
            ob[2] = ListarPr.get(i).getVendedor();
            ob[3] = ListarPr.get(i).getTel();
            modelo2.addRow(ob);

        }
        TablaProveedores.setModel(modelo2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EliminarProv = new javax.swing.JButton();
        Proveedores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProveedores = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        AgregarProv = new javax.swing.JButton();
        EditarProv = new javax.swing.JButton();
        NuevoProv = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        IdProveedor = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        Cel = new javax.swing.JTextField();
        Vendedor = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        Prov = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        EliminarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        EliminarProv.setText("ELIMINAR");
        EliminarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarProvActionPerformed(evt);
            }
        });

        setBorder(null);

        TablaProveedores  = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                for(int i = 0; i < TablaProveedores.getRowCount(); i ++){
                    if(row < 0){
                        return true;
                    }
                }
                return false;
            }
        };
        TablaProveedores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "PROVEEDOR", "VENDEDOR", "TEL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaProveedores.setRowHeight(30);
        TablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProveedoresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProveedores);

        jPanel37.setBackground(new java.awt.Color(51, 153, 255));

        AgregarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        AgregarProv.setText("AGREGAR");
        AgregarProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        AgregarProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        AgregarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarProvActionPerformed(evt);
            }
        });

        EditarProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        EditarProv.setText("EDITAR");
        EditarProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        EditarProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        EditarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarProvActionPerformed(evt);
            }
        });

        NuevoProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/NUEVO_32PX.png"))); // NOI18N
        NuevoProv.setText("NUEVO");
        NuevoProv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NuevoProv.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NuevoProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoProvActionPerformed(evt);
            }
        });

        jButton18.setForeground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarPequeño.png"))); // NOI18N
        jButton18.setText("BUSCAR POR ID");
        jButton18.setBorder(null);
        jButton18.setContentAreaFilled(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarPequeño.png"))); // NOI18N
        jButton18.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarGrande.png"))); // NOI18N
        jButton18.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("ID");

        IdProveedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("NÚMERO DE TELÉFONO");

        Cel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        Vendedor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("VENDEDOR");

        Prov.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Prov.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ProvKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("NOMBRE DE EMPRESA *");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Prov)
                    .addComponent(Vendedor)
                    .addComponent(Cel)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel37Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(IdProveedor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(AgregarProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditarProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(NuevoProv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Prov, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Vendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(IdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AgregarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditarProv, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NuevoProv, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ProveedoresLayout = new javax.swing.GroupLayout(Proveedores);
        Proveedores.setLayout(ProveedoresLayout);
        ProveedoresLayout.setHorizontalGroup(
            ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProveedoresLayout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 582, Short.MAX_VALUE))
        );
        ProveedoresLayout.setVerticalGroup(
            ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProveedoresMouseClicked
        int fila = TablaProveedores.getSelectedRow();
        String codigo = TablaProveedores.getValueAt(fila, 0).toString();
        String Empresa = TablaProveedores.getValueAt(fila, 1).toString();
        String VendedorEmpresa = TablaProveedores.getValueAt(fila, 2).toString();
        String Tel = TablaProveedores.getValueAt(fila, 3).toString();
        IdProveedor.setText(codigo);
        Prov.setText(Empresa);
        Vendedor.setText(VendedorEmpresa);
        Cel.setText(Tel);
        AgregarProv.setVisible(false);
        EditarProv.setVisible(true);
    }//GEN-LAST:event_TablaProveedoresMouseClicked

    private void AgregarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarProvActionPerformed
        IngresoProveedoruno();
        LimpiarProveedor();
        ActualizarTablaProveedores();
    }//GEN-LAST:event_AgregarProvActionPerformed

    private void EditarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarProvActionPerformed
        EditarProveedor();
        LimpiarProveedor();
        ActualizarTablaProveedores();
    }//GEN-LAST:event_EditarProvActionPerformed

    private void EliminarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarProvActionPerformed
        EliminarProveedor();
        LimpiarProveedor();
        ActualizarTablaProveedores();
    }//GEN-LAST:event_EliminarProvActionPerformed

    private void NuevoProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoProvActionPerformed
        LimpiarProveedor();
        IdProveedor.setText(null);
    }//GEN-LAST:event_NuevoProvActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        ConsultaProveedor();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void ProvKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProvKeyReleased
        if(Prov.getText().equals("")){
            filtro("", TablaProveedores, 1);
        }else{
            filtro(Prov.getText(), TablaProveedores, 1);
        }
    }//GEN-LAST:event_ProvKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AgregarProv;
    private javax.swing.JTextField Cel;
    private javax.swing.JButton EditarProv;
    private javax.swing.JButton EliminarProv;
    private javax.swing.JTextField IdProveedor;
    private javax.swing.JButton NuevoProv;
    private javax.swing.JTextField Prov;
    private javax.swing.JPanel Proveedores;
    private static javax.swing.JTable TablaProveedores;
    private javax.swing.JTextField Vendedor;
    private javax.swing.JButton jButton18;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
