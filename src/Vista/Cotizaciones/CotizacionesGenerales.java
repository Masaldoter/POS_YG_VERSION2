package Vista.Cotizaciones;

import Controlador.Eventos;
import Modelo.Cotizaciones;
import Tablas.ConsultasCotizacion;
import Tablas.RenderTablas;
import Vista.POS.POS;
import Vista.Principal;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaldoter
 */
public class CotizacionesGenerales extends javax.swing.JInternalFrame {
    POS pos;
    public String Parametro1, Parametro2;
    public int FiltroBusqueda;
    public CotizacionesGenerales() {
        initComponents();
    }
    
    public CotizacionesGenerales(POS pos) {
        this.pos = pos;
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        FiltroBusqueda = 1;
       /* ActualizarTablaEstado(FiltroBusqueda, this.Parametro1, this.Parametro2);
        lblTipoDeBusquedaRealizada.setText("ORDENADO POR ID");*/
    }
    public static void LimpiarTabla(JTable Tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        for (int e = 0; e < 15; e++) {
            
        
        for (int i = 0; i < Tabla.getRowCount(); i++) {
            modelo = (DefaultTableModel) Tabla.getModel();
            modelo.removeRow(i);
        }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        EstadoCotizacion = new javax.swing.JComboBox<>();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CajaNumeroDeCotizacion = new javax.swing.JTextField();
        BtnBuscarDocumento = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CajaBuscarPorNit = new javax.swing.JTextField();
        BtnBuscarNit = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        FechaInicial = new com.toedter.calendar.JDateChooser();
        jSeparator34 = new javax.swing.JSeparator();
        FechaFin = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        BtnBuscarPorFechas = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCotizaciones = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTipoDeBusquedaRealizada = new javax.swing.JLabel();
        id_TipoBusqueda = new javax.swing.JLabel();

        setBorder(null);
        setTitle("ADMINISTRACIÓN DE COTIZACIÓNES");

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));

        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        EstadoCotizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VIGENTE", "VENCIDO", "REALIZADO", "ANULADO" }));
        EstadoCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoCotizacionActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setText("INGRESE EL NÚMERO DE COTIZACION");

        CajaNumeroDeCotizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaNumeroDeCotizacionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CajaNumeroDeCotizacionKeyTyped(evt);
            }
        });

        BtnBuscarDocumento.setText("BUSCAR");
        BtnBuscarDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarDocumentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaNumeroDeCotizacion)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBuscarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnBuscarDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaNumeroDeCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("BUSQUEDA POR DOCUMENTO", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setText("INGRESE EL NUMERO DE NIT O EL NOMBRE DEL CLIENTE:");

        CajaBuscarPorNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaBuscarPorNitKeyReleased(evt);
            }
        });

        BtnBuscarNit.setText("BUSCAR");
        BtnBuscarNit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarNitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaBuscarPorNit)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBuscarNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarNit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaBuscarPorNit)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("NIT-NOMBRE", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 204, 204));

        jLabel31.setText("FECHA DE INICIO");

        jLabel46.setText("FECHA DE FIN");

        BtnBuscarPorFechas.setText("BUSCAR");
        BtnBuscarPorFechas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarPorFechasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FechaInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBuscarPorFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(FechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(BtnBuscarPorFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("FECHA", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addComponent(EstadoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(EstadoCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        TablaCotizaciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TablaCotizaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "N° COTIZACIÓN", "CLIENTE", "NIT", "TOTAL", "PRODUCTOS", "GENERADO", "VENCIMIENTO", "ESTADO", "ACCIÓNES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaCotizaciones.setRowHeight(50);
        TablaCotizaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCotizacionesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaCotizaciones);
        if (TablaCotizaciones.getColumnModel().getColumnCount() > 0) {
            TablaCotizaciones.getColumnModel().getColumn(0).setPreferredWidth(10);
            TablaCotizaciones.getColumnModel().getColumn(1).setPreferredWidth(40);
            TablaCotizaciones.getColumnModel().getColumn(4).setPreferredWidth(25);
            TablaCotizaciones.getColumnModel().getColumn(6).setPreferredWidth(25);
            TablaCotizaciones.getColumnModel().getColumn(8).setPreferredWidth(25);
            TablaCotizaciones.getColumnModel().getColumn(9).setPreferredWidth(15);
        }

        jLabel3.setText("TOTAL DE REGISTROS:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("FILTRO:");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("BUSCAR POR PRODUCTO:");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/BUSCAR_DERECHA_32PX.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblTipoDeBusquedaRealizada.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTipoDeBusquedaRealizada.setForeground(new java.awt.Color(255, 0, 0));
        lblTipoDeBusquedaRealizada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDeBusquedaRealizada.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipoDeBusquedaRealizada, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id_TipoBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTipoDeBusquedaRealizada, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(id_TipoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaCotizacionesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCotizacionesMouseClicked
        int fila = TablaCotizaciones.getSelectedRow();

        String codigo = TablaCotizaciones.getValueAt(fila, 1).toString();

        int Columna = TablaCotizaciones.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY()/TablaCotizaciones.getRowHeight();
        if(Fila < TablaCotizaciones.getRowCount() && Fila >= 0 && Columna < TablaCotizaciones.getColumnCount() && Columna >= 0){
            Object value = TablaCotizaciones.getValueAt(Fila, Columna);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                String Id = TablaCotizaciones.getValueAt(fila, 0).toString();
                JButton boton = (JButton) value;
                DetalleCotizacion de= new DetalleCotizacion(codigo, 1, 0, pos);
                de.setVisible(true);
            }
        }
    }//GEN-LAST:event_TablaCotizacionesMouseClicked

    private void EstadoCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoCotizacionActionPerformed
        ActualizarTablaEstado(Integer.parseInt(id_TipoBusqueda.getText()), this.Parametro1, this.Parametro2);
    }//GEN-LAST:event_EstadoCotizacionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        id_TipoBusqueda.setText("1");
        FiltroBusqueda = 1;
        lblTipoDeBusquedaRealizada.setText("ORDENADO POR ID");
        ActualizarTablaEstado(FiltroBusqueda, this.Parametro1, this.Parametro2);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CajaNumeroDeCotizacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaNumeroDeCotizacionKeyReleased
        id_TipoBusqueda.setText("2");
        FiltroBusqueda = 2;
        this.Parametro1 = CajaNumeroDeCotizacion.getText();
        lblTipoDeBusquedaRealizada.setText("POR NUMERO DE COTIZACION");
        if (CajaNumeroDeCotizacion.getText().equals("")) {

        } else {
            ActualizarTablaEstado(FiltroBusqueda, this.Parametro1, this.Parametro2);
        }
    }//GEN-LAST:event_CajaNumeroDeCotizacionKeyReleased

    private void CajaNumeroDeCotizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaNumeroDeCotizacionKeyTyped
        Eventos event = new Eventos();
        event.numberKeyPress(evt);
    }//GEN-LAST:event_CajaNumeroDeCotizacionKeyTyped

    private void BtnBuscarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarDocumentoActionPerformed
        id_TipoBusqueda.setText("2");
        FiltroBusqueda = 2;
        lblTipoDeBusquedaRealizada.setText("POR NUMERO DE VALE");
        this.Parametro1 = CajaNumeroDeCotizacion.getText();
        ActualizarTablaEstado(FiltroBusqueda, this.Parametro1, this.Parametro2);
    }//GEN-LAST:event_BtnBuscarDocumentoActionPerformed

    private void CajaBuscarPorNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaBuscarPorNitKeyReleased
        id_TipoBusqueda.setText("3");
        FiltroBusqueda = 3;
        lblTipoDeBusquedaRealizada.setText("POR NIT-NOMBRE DE CLIENTE");
        this.Parametro1 = CajaBuscarPorNit.getText();
        ActualizarTablaEstado(FiltroBusqueda, this.Parametro1, this.Parametro2);
    }//GEN-LAST:event_CajaBuscarPorNitKeyReleased

    private void BtnBuscarNitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarNitActionPerformed
        id_TipoBusqueda.setText("3");
        FiltroBusqueda = 3;
        lblTipoDeBusquedaRealizada.setText("POR NIT-NOMBRE DE CLIENTE");
        this.Parametro1 = CajaBuscarPorNit.getText();
        ActualizarTablaEstado(FiltroBusqueda, this.Parametro1, this.Parametro2);
    }//GEN-LAST:event_BtnBuscarNitActionPerformed

    private void BtnBuscarPorFechasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarPorFechasActionPerformed
        id_TipoBusqueda.setText("4");
        FiltroBusqueda = 4;
        lblTipoDeBusquedaRealizada.setText("POR FECHAS");
        // Obtener el campo de texto subyacente
        JTextField Fecha1 = (JTextField) FechaInicial.getDateEditor().getUiComponent();

        // Obtener el texto ingresado en el JDateChooser
        String Fecha_Final_1 = Fecha1.getText();

        // Obtener el campo de texto subyacente
        JTextField Fecha2 = (JTextField) FechaFin.getDateEditor().getUiComponent();

        // Obtener el texto ingresado en el JDateChooser
        String Fecha_Final_2 = Fecha2.getText();

        this.Parametro1 = Fecha_Final_1;
        this.Parametro2 = Fecha_Final_2;
        ActualizarTablaEstado(FiltroBusqueda, this.Parametro1, this.Parametro2);
    }//GEN-LAST:event_BtnBuscarPorFechasActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        id_TipoBusqueda.setText("1");
        FiltroBusqueda = 1;
        lblTipoDeBusquedaRealizada.setText("POR PRODUCTO");
        this.Parametro1 = CajaNumeroDeCotizacion.getText();
        ActualizarTablaEstado_POR_PRODUCTO(jTextField1.getText());
    }//GEN-LAST:event_jTextField1KeyReleased

    public void ActualizarTablaEstado(int Filtro_Busqueda, String Parametro1, String Parametro2){
        ConsultasCotizacion tablas = new ConsultasCotizacion();
        TablaCotizaciones.setDefaultRenderer(Object.class, new RenderTablas());
        LimpiarTabla(TablaCotizaciones);
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) TablaCotizaciones.getModel();
        List<Cotizaciones> ListarPr = tablas.ListarCotizaciones(EstadoCotizacion, Filtro_Busqueda, Parametro1, Parametro2);
        Object[] ob = new Object[10];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdCotizacion();
            ob[1] = ListarPr.get(i).getNoCotizacion();
            ob[2] = ListarPr.get(i).getNombreClienteString();
            ob[3] = ListarPr.get(i).getNitClienteString();
            ob[4] = ListarPr.get(i).getTotalCotizacion();
            ob[5] = ListarPr.get(i).getTotalProductos();
            ob[6] = "<html>"+ListarPr.get(i).getFechaRealizada() +" | "+ListarPr.get(i).getHoraRealizada() + " POR: "+ListarPr.get(i).getNombreUsuarioString()+"</html>";
            ob[7] = "<html>"+ListarPr.get(i).getFechaVencimiento()+ " POR: "+ListarPr.get(i).getNombreUsuarioModificoString()+"</html>";
            ob[8] = ListarPr.get(i).getEstadoCotizacion();
            ob[9] = ListarPr.get(i).getBtnDetalles();
            modelo.addRow(ob);
           //[255,230,205]
        }
        TablaCotizaciones.setModel(modelo);
        SUMAR_REGISTROS();
    }
    
    public void ActualizarTablaEstado_POR_PRODUCTO(String Parametro1){
        ConsultasCotizacion tablas = new ConsultasCotizacion();
        TablaCotizaciones.setDefaultRenderer(Object.class, new RenderTablas());
        LimpiarTabla(TablaCotizaciones);
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) TablaCotizaciones.getModel();
        List<Cotizaciones> ListarPr = tablas.ListarCotizaciones_POR_PRODUCTO(Parametro1);
        Object[] ob = new Object[10];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getIdCotizacion();
            ob[1] = ListarPr.get(i).getNoCotizacion();
            ob[2] = ListarPr.get(i).getNombreClienteString();
            ob[3] = ListarPr.get(i).getNitClienteString();
            ob[4] = ListarPr.get(i).getTotalCotizacion();
            ob[5] = ListarPr.get(i).getTotalProductos();
            ob[6] = "<html>"+ListarPr.get(i).getFechaRealizada() +" | "+ListarPr.get(i).getHoraRealizada() + " POR: "+ListarPr.get(i).getNombreUsuarioString()+"</html>";
            ob[7] = "<html>"+ListarPr.get(i).getFechaVencimiento()+ " POR: "+ListarPr.get(i).getNombreUsuarioModificoString()+"</html>";
            ob[8] = ListarPr.get(i).getEstadoCotizacion();
            ob[9] = ListarPr.get(i).getBtnDetalles();
            modelo.addRow(ob);
           //[255,230,205]
        }
        TablaCotizaciones.setModel(modelo);
        SUMAR_REGISTROS();
    }

    public void SUMAR_REGISTROS(){
        jLabel7.setText(String.valueOf(TablaCotizaciones.getRowCount()));
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarDocumento;
    private javax.swing.JButton BtnBuscarNit;
    private javax.swing.JButton BtnBuscarPorFechas;
    private javax.swing.JTextField CajaBuscarPorNit;
    private javax.swing.JTextField CajaNumeroDeCotizacion;
    private static javax.swing.JComboBox<String> EstadoCotizacion;
    private com.toedter.calendar.JDateChooser FechaFin;
    private com.toedter.calendar.JDateChooser FechaInicial;
    private static javax.swing.JTable TablaCotizaciones;
    private javax.swing.JLabel id_TipoBusqueda;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblTipoDeBusquedaRealizada;
    // End of variables declaration//GEN-END:variables
}
