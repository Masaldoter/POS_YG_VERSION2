/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.REPORTES_VENTAS;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CONSULTAS.CONSULTAS_VENTAS;
import Vista.Detalles;
import Vista.Principal;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

/**
 *
 * @author aldoy
 */
public class MOVIMIENTOS_GENERALES extends javax.swing.JInternalFrame {
    CONSULTAS_VENTAS C_V = new CONSULTAS_VENTAS();
    Principal principal;
    int TipoBusqueda = 0;
    public MOVIMIENTOS_GENERALES() {
        initComponents();
    }
    
    public MOVIMIENTOS_GENERALES(Principal principal) {
        initComponents();
        this.principal = principal;
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    
    public void CARGAR_TOTALES(){
        Double TotalPagar2 = 0.00;
        int numFila = TablaReporteVentas.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TablaReporteVentas.getValueAt(i, 3)));
            TotalPagar2 = TotalPagar2 + cal;

        }
        TotalVentaGeneral.setText(PARAMETROS_EMPRESA.SIGNO_MONEDA+PARAMETROS_EMPRESA.formatea.format(TotalPagar2));

        double pago = 0.00;
        for (int i = 0; i < numFila; i++) {
            double cal2 = Double.parseDouble(String.valueOf(TablaReporteVentas.getValueAt(i, 4)));
            pago = pago + cal2;

        }
        EfectivoPagadoVentaGeneral.setText(PARAMETROS_EMPRESA.SIGNO_MONEDA+PARAMETROS_EMPRESA.formatea.format(pago));

        double pago3 = 0.00;
        for (int i = 0; i < numFila; i++) {
            double cal3 = Double.parseDouble(String.valueOf(TablaReporteVentas.getValueAt(i, 5)));
            pago3 = pago3 + cal3;

        }
        CambiosVentaGeneral.setText(PARAMETROS_EMPRESA.SIGNO_MONEDA+PARAMETROS_EMPRESA.formatea.format(pago3));
    }
    
    public void CARGAR_REGISTROS(){
        C_V.ActualizarRegistroVenta(TablaReporteVentas, EstadoVentaGeneral, jComboBox1);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ReporteDeVentas = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        TotalVentaGeneral = new javax.swing.JLabel();
        CambiosVentaGeneral = new javax.swing.JLabel();
        EfectivoPagadoVentaGeneral = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton33 = new javax.swing.JButton();
        EstadoVentaGeneral = new javax.swing.JComboBox<>();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel71 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        jButton30 = new javax.swing.JButton();
        fechabus = new com.toedter.calendar.JDateChooser();
        Fecha2 = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jSeparator34 = new javax.swing.JSeparator();
        jPanel70 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        lblTipoBusquedaVentasGenerales = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaReporteVentas = new javax.swing.JTable();
        jPanel75 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TablaVentasNombreProductos = new javax.swing.JTable();

        jPanel18.setBackground(new java.awt.Color(255, 153, 102));

        TotalVentaGeneral.setFont(new java.awt.Font("Consolas", 0, 30)); // NOI18N
        TotalVentaGeneral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalVentaGeneral.setText("0.00");
        TotalVentaGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL"));

        CambiosVentaGeneral.setFont(new java.awt.Font("Consolas", 0, 30)); // NOI18N
        CambiosVentaGeneral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CambiosVentaGeneral.setText("0.00");
        CambiosVentaGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CAMBIOS"));

        EfectivoPagadoVentaGeneral.setFont(new java.awt.Font("Consolas", 0, 30)); // NOI18N
        EfectivoPagadoVentaGeneral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EfectivoPagadoVentaGeneral.setText("0.00");
        EfectivoPagadoVentaGeneral.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "EFECTIVO PAGADO"));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(EfectivoPagadoVentaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CambiosVentaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalVentaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalVentaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                    .addComponent(CambiosVentaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EfectivoPagadoVentaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(153, 204, 255));

        jButton33.setText("VER TODO");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        EstadoVentaGeneral.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURADO", "ANULADO" }));
        EstadoVentaGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoVentaGeneralActionPerformed(evt);
            }
        });

        jTabbedPane2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane2StateChanged(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(102, 204, 255));

        jLabel4.setText("N° DE DOCUMENTO");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 552, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("BUSQUEDA POR N° INTERNO", jPanel8);

        jPanel71.setBackground(new java.awt.Color(102, 153, 255));

        jLabel47.setText("CÓDIGO DE BARRAS / NOMBRE DEL PRODUCTO");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                        .addGap(135, 135, 135))
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addComponent(jTextField4)
                        .addContainerGap())))
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("POR PRODUCTO", jPanel71);

        jPanel23.setBackground(new java.awt.Color(204, 204, 255));

        jButton30.setText("BÚSCAR");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        fechabus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Fecha2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel31.setText("FECHA DE INICIO");

        jLabel46.setText("FECHA DE FIN");

        jSeparator34.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(156, 156, 156))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(fechabus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fecha2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(125, 125, 125)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel23Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jSeparator34, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fechabus, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Fecha2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("POR FECHAS", jPanel23);

        jPanel70.setBackground(new java.awt.Color(102, 204, 255));

        jLabel45.setText("NIT O NOMBRE DE CLIENTE");

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addComponent(jLabel45)
                        .addGap(0, 514, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("POR CLIENTE", jPanel70);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURA", "PROFORMA", "FACTURA' OR registro.TipoDocumentoFel='PROFORMA" }));
        jComboBox1.setSelectedIndex(2);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setText("FILTRADO POR:");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jTabbedPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(EstadoVentaGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipoBusquedaVentasGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane2)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTipoBusquedaVentasGenerales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EstadoVentaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jComboBox1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        TablaReporteVentas  = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                for(int i = 0; i < TablaReporteVentas.getRowCount(); i ++){
                    if(row < 0){
                        return true;
                    }
                }
                return false;
            }
        };
        TablaReporteVentas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TablaReporteVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NUMERO INTERNO", "CLIENTE", "FECHA", "TOTAL", "EFECTIVO", "CAMBIO", "FORMA DE PAGO", "USUARIO", "DOCUMENTO", "ESTADO", "ACCIÓN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaReporteVentas.setRowHeight(40);
        TablaReporteVentas.getTableHeader().setReorderingAllowed(false);
        TablaReporteVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaReporteVentasMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(TablaReporteVentas);
        if (TablaReporteVentas.getColumnModel().getColumnCount() > 0) {
            TablaReporteVentas.getColumnModel().getColumn(0).setPreferredWidth(5);
            TablaReporteVentas.getColumnModel().getColumn(1).setPreferredWidth(200);
            TablaReporteVentas.getColumnModel().getColumn(2).setPreferredWidth(150);
            TablaReporteVentas.getColumnModel().getColumn(3).setPreferredWidth(20);
            TablaReporteVentas.getColumnModel().getColumn(4).setPreferredWidth(2);
            TablaReporteVentas.getColumnModel().getColumn(5).setPreferredWidth(2);
        }

        jTabbedPane3.addTab("", jScrollPane5);

        TablaVentasNombreProductos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TablaVentasNombreProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO DE BARRAS", "NOMBRE", "CANTIDAD", "TOTAL", "NUMERO DE DOCUMENTO", "ACCIÓN "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVentasNombreProductos.setRowHeight(40);
        TablaVentasNombreProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaVentasNombreProductosMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(TablaVentasNombreProductos);

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("", jPanel75);

        javax.swing.GroupLayout ReporteDeVentasLayout = new javax.swing.GroupLayout(ReporteDeVentas);
        ReporteDeVentas.setLayout(ReporteDeVentasLayout);
        ReporteDeVentasLayout.setHorizontalGroup(
            ReporteDeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReporteDeVentasLayout.createSequentialGroup()
                .addGroup(ReporteDeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        ReporteDeVentasLayout.setVerticalGroup(
            ReporteDeVentasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReporteDeVentasLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ReporteDeVentas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ReporteDeVentas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        C_V.ActualizarRegistroVenta(TablaReporteVentas, EstadoVentaGeneral, jComboBox1);
        jTabbedPane3.setSelectedIndex(0);
        fechabus.setDate(null);
        Fecha2.setDate(null);
        lblTipoBusquedaVentasGenerales.setText("TODAS LAS VENTAS");
        CARGAR_TOTALES();
        TipoBusqueda = 0;
    }//GEN-LAST:event_jButton33ActionPerformed

    private void EstadoVentaGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoVentaGeneralActionPerformed
        if(TipoBusqueda == 3){
            C_V.ActualizarRegistroVentaPorFecha(TablaReporteVentas, fechabus, Fecha2, EstadoVentaGeneral, jComboBox1);
        }else if(TipoBusqueda == 0){
            C_V.ActualizarRegistroVenta(TablaReporteVentas, EstadoVentaGeneral, jComboBox1);
        }else{
          C_V.ActualizarRegistroVenta(TablaReporteVentas, EstadoVentaGeneral, jComboBox1);  
        }
        CARGAR_TOTALES();
    }//GEN-LAST:event_EstadoVentaGeneralActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jTabbedPane3.setSelectedIndex(0);
            lblTipoBusquedaVentasGenerales.setText("POR DOCUMENTO");
            C_V.ActualizarRegistroVentaPorDocumento(jTextField1, false, TablaReporteVentas);
            CARGAR_TOTALES();
            TipoBusqueda = 1;
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jTabbedPane3.setSelectedIndex(0);
        lblTipoBusquedaVentasGenerales.setText("POR DOCUMENTO");
        C_V.ActualizarRegistroVentaPorDocumento(jTextField1, false, TablaReporteVentas);
        CARGAR_TOTALES();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            jTabbedPane3.setSelectedIndex(1);
            C_V.ActualizarRegistroPorNombreDetalleProductos(false, jTextField4, TablaVentasNombreProductos);
            CARGAR_TOTALES();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased

        jTabbedPane3.setSelectedIndex(1);
        lblTipoBusquedaVentasGenerales.setText("CODIGO O NOMBRE");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                C_V.ActualizarRegistroPorNombreDetalleProductos(false, jTextField4, TablaVentasNombreProductos);
                CARGAR_TOTALES();

            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }//GEN-LAST:event_jTextField4KeyReleased


    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        jTabbedPane3.setSelectedIndex(0);
        lblTipoBusquedaVentasGenerales.setText("POR FECHAS");
        C_V.ActualizarRegistroVentaPorFecha(TablaReporteVentas, fechabus, Fecha2, EstadoVentaGeneral, jComboBox1);
        CARGAR_TOTALES();
        TipoBusqueda = 3;
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        jTabbedPane3.setSelectedIndex(1);
        lblTipoBusquedaVentasGenerales.setText("CODIGO O NOMBRE");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                 try {
            lblTipoBusquedaVentasGenerales.setText("POR CLIENTE");
            jTabbedPane3.setSelectedIndex(0);
            C_V.ActualizarRegistroVentaPorNit(jTextField5.getText(), TablaReporteVentas, EstadoVentaGeneral);
            CARGAR_TOTALES();
        } catch (Exception e) {
        }

            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTabbedPane2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane2StateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2StateChanged

    private void TablaReporteVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaReporteVentasMouseClicked
        int fila = TablaReporteVentas.getSelectedRow();

        String codigo = TablaReporteVentas.getValueAt(fila, 0).toString();

        int Columna = TablaReporteVentas.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY()/TablaReporteVentas.getRowHeight();
        if(Fila < TablaReporteVentas.getRowCount() && Fila >= 0 && Columna < TablaReporteVentas.getColumnCount() && Columna >= 0){
            Object value = TablaReporteVentas.getValueAt(Fila, Columna);
            if(value instanceof JButton jButton){
                jButton.doClick();
                Detalles de= new Detalles(codigo, 1, 0, principal.P_O_S, principal);
                de.setVisible(true);
            }
        }
    }//GEN-LAST:event_TablaReporteVentasMouseClicked

    private void TablaVentasNombreProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentasNombreProductosMouseClicked
        int fila = TablaVentasNombreProductos.getSelectedRow();

        String codigo = TablaVentasNombreProductos.getValueAt(fila, 4).toString();

        int Columna = TablaVentasNombreProductos.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY()/TablaVentasNombreProductos.getRowHeight();
        if(Fila < TablaVentasNombreProductos.getRowCount() && Fila >= 0 && Columna < TablaVentasNombreProductos.getColumnCount() && Columna >= 0){
            Object value = TablaVentasNombreProductos.getValueAt(Fila, Columna);
            if(value instanceof JButton jButton){
                jButton.doClick();
                     Detalles de= new Detalles(codigo, 1, 0, principal.P_O_S, principal);
                de.setVisible(true);
            }
        }
    }//GEN-LAST:event_TablaVentasNombreProductosMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(TipoBusqueda == 3){
            C_V.ActualizarRegistroVentaPorFecha(TablaReporteVentas, fechabus, Fecha2, EstadoVentaGeneral, jComboBox1);
        }else if(TipoBusqueda == 0){
            C_V.ActualizarRegistroVenta(TablaReporteVentas, EstadoVentaGeneral, jComboBox1);
        }else{
          C_V.ActualizarRegistroVenta(TablaReporteVentas, EstadoVentaGeneral, jComboBox1);  
        }
        CARGAR_TOTALES();
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel CambiosVentaGeneral;
    private static javax.swing.JLabel EfectivoPagadoVentaGeneral;
    private static javax.swing.JComboBox<String> EstadoVentaGeneral;
    private com.toedter.calendar.JDateChooser Fecha2;
    private javax.swing.JPanel ReporteDeVentas;
    private static javax.swing.JTable TablaReporteVentas;
    private javax.swing.JTable TablaVentasNombreProductos;
    private static javax.swing.JLabel TotalVentaGeneral;
    private com.toedter.calendar.JDateChooser fechabus;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton33;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private static javax.swing.JLabel lblTipoBusquedaVentasGenerales;
    // End of variables declaration//GEN-END:variables
}
