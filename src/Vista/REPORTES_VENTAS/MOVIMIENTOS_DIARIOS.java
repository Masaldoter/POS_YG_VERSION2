/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.REPORTES_VENTAS;

import static CLASES_GLOBALES.METODOS_GLOBALES.Fecha;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import CONSULTAS.CONSULTAS_VENTAS;
import Controlador.ProductosDao;
import Modelo.ComboUsuarios;
import Modelo.Usuarios;
import Vista.Detalles;
import Vista.Principal;
import java.util.List;
import javax.swing.JButton;

/**
 *
 * @author aldoy
 */
public class MOVIMIENTOS_DIARIOS extends javax.swing.JInternalFrame {
    ProductosDao proDao = new ProductosDao();
    Principal principal;
    public MOVIMIENTOS_DIARIOS() {
        initComponents();
    }
    
    public MOVIMIENTOS_DIARIOS(Principal principal) {
        initComponents();
        this.principal = principal;
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        if (PARAMETROS_USUARIOS.ROL_USUARIO.equals("Usuario")) {
            ListaUsuarios.setEnabled(false);
            jButton38.setEnabled(false);
            CajaProductoBusquedaVentasDiarias.setVisible(false);
            jLabel49.setVisible(false);
            jSeparator47.setVisible(false);
            jTabbedPane6.setEnabledAt(1, false);
        }
    }
    
    public void CARGAR_TOTALES(){
        Double TotalPagar2 = 0.00;
        int numFila = VD.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(VD.getValueAt(i, 4)));
            TotalPagar2 = TotalPagar2 + cal;

        }
        TP.setText(PARAMETROS_EMPRESA.SIGNO_MONEDA+PARAMETROS_EMPRESA.formatea.format(TotalPagar2));
        
        double pago= 0.00;
        for (int i = 0; i < numFila; i++) {
            double cal2 = Double.parseDouble(String.valueOf(VD.getValueAt(i, 5)));
            pago = pago + cal2;

        }
        Pago.setText(PARAMETROS_EMPRESA.SIGNO_MONEDA+PARAMETROS_EMPRESA.formatea.format(pago));
        
        double pago3= 0.00;
        for (int i = 0; i < numFila; i++) {
            double cal3 = Double.parseDouble(String.valueOf(VD.getValueAt(i, 6)));
            pago3 = pago3 + cal3;

        }
        CambiosVentasDiarias.setText(PARAMETROS_EMPRESA.SIGNO_MONEDA+PARAMETROS_EMPRESA.formatea.format(pago3));
    }
    
    public void CARGAR_REGISTROS(){
        fechas.setText(Fecha());
        ListaUsuarios.removeAllItems();
        VaciarYllenarUsuariosVenta();
        REFRESCAR_VENTAS_DIARIAS();
        CARGAR_TOTALES();
    }
    
    private void VaciarYllenarUsuariosVenta(){
        ListaUsuarios.removeAllItems();
        List<Usuarios> lista = proDao.Usuarios();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdUsuario();
            String Nombre = lista.get(i).getNombreUsuario();
            ListaUsuarios.addItem(new ComboUsuarios(id, Nombre).toString());
        }
    }
    
    
    public void REFRESCAR_VENTAS_DIARIAS(){
        if(!PARAMETROS_USUARIOS.ROL_USUARIO.equals("Administrador")){
            Sele.setText(PARAMETROS_USUARIOS.NOMBRE_USUARIO);
        ListaUsuarios.setSelectedItem(PARAMETROS_USUARIOS.NOMBRE_USUARIO);    
        }else{
          Sele.setText("General");
        ListaUsuarios.setSelectedItem(null);  
        }
        
        
        switch (lblTipoDeBusquedaVentasDiarias.getText()) {
            case "TIPO DOCUMENTO":
                jTabbedPane6.setSelectedIndex(0);
                CONSULTAS_VENTAS.ActualizarRegistroVentaPorTipoDocumento(jComboBox1, true, VD);
                CARGAR_TOTALES();
                break;
            case "USUARIOS":
                jTabbedPane6.setSelectedIndex(0);
        if(ListaUsuarios.getSelectedItem() == null){
            CONSULTAS_VENTAS.Registros(VD, EstadoVentas);
            CARGAR_TOTALES();
        }else{
            Sele.setText(String.valueOf(proDao.ConsultaIdUsuario(ListaUsuarios)));
            CONSULTAS_VENTAS.RegistrosUsuario(VD, EstadoVentas, Sele.getText());
            CARGAR_TOTALES();
        }
                break;
            case "PRODUCTO":
                jTabbedPane6.setSelectedIndex(1);
                CONSULTAS_VENTAS.ActualizarRegistroPorNombreDetalleProductos(Boolean.TRUE, CajaProductoBusquedaVentasDiarias, TablaVentasNombreProductos1);
                CARGAR_TOTALES();
                break;
            case "SIN FILTRO":
                jTabbedPane6.setSelectedIndex(0);
                CONSULTAS_VENTAS.Registros(VD, EstadoVentas);
                CARGAR_TOTALES();
                break;
            default:
                break;
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

        VentasDia = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        Pago = new javax.swing.JLabel();
        CambiosVentasDiarias = new javax.swing.JLabel();
        TP = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        jButton39 = new javax.swing.JButton();
        EstadoVentas = new javax.swing.JComboBox<>();
        jButton38 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jPanel20 = new javax.swing.JPanel();
        jPanel68 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        Sele = new javax.swing.JLabel();
        ListaUsuarios = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        CajaProductoBusquedaVentasDiarias = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jSeparator45 = new javax.swing.JSeparator();
        jSeparator46 = new javax.swing.JSeparator();
        jSeparator47 = new javax.swing.JSeparator();
        CajaIdVenta = new javax.swing.JTextField();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        VD = new javax.swing.JTable();
        jPanel77 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        TablaVentasNombreProductos1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblTipoDeBusquedaVentasDiarias = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        fechas = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator30 = new javax.swing.JSeparator();

        setBorder(null);

        jPanel12.setBackground(new java.awt.Color(204, 204, 255));

        Pago.setFont(new java.awt.Font("Consolas", 0, 30)); // NOI18N
        Pago.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "EFECTIVO PAGADO"));

        CambiosVentasDiarias.setFont(new java.awt.Font("Consolas", 0, 30)); // NOI18N
        CambiosVentasDiarias.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL DE CAMBIOS"));

        TP.setFont(new java.awt.Font("Consolas", 0, 30)); // NOI18N
        TP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL"));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Pago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CambiosVentasDiarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TP, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(CambiosVentasDiarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Pago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel50.setBackground(new java.awt.Color(255, 153, 51));

        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar Peque??o.png"))); // NOI18N
        jButton39.setText("ACTUALIZAR");
        jButton39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton39.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar Peque??o.png"))); // NOI18N
        jButton39.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar grande.png"))); // NOI18N
        jButton39.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        EstadoVentas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURADO", "ANULADO" }));
        EstadoVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadoVentasActionPerformed(evt);
            }
        });

        jButton38.setText("VER TODOS");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jScrollPane13.getVerticalScrollBar().setUnitIncrement(16);

        jPanel68.setBackground(new java.awt.Color(255, 153, 102));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURA", "PROFORMA", "SALIDAS" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel25.setText("TIPO DE DOCUMENTO");

        Sele.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Sele.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        ListaUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaUsuariosActionPerformed(evt);
            }
        });

        jLabel52.setText("SELECCIONE UN USUARIO");

        CajaProductoBusquedaVentasDiarias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaProductoBusquedaVentasDiariasKeyReleased(evt);
            }
        });

        jLabel49.setText("C??DIGO/NOMBRE PRODUCTO");

        jSeparator45.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator46.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator47.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator45)
                    .addComponent(jSeparator46)
                    .addComponent(jSeparator47)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                        .addComponent(jLabel52)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ListaUsuarios, 0, 280, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sele, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                        .addComponent(jLabel49)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaProductoBusquedaVentasDiarias)))
                .addContainerGap())
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator45, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ListaUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Sele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator46, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaProductoBusquedaVentasDiarias, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator47, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jScrollPane13.setViewportView(jPanel20);

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addComponent(jScrollPane13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(CajaIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EstadoVentas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EstadoVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CajaIdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane13)
        );

        jTabbedPane6.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        VD.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        VD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Venta", "CLIENTE", "HORA", "FECHA", "TOTAL", "EFECTIVO", "CAMBIO", "NUMERO INTERNO", "FORMA DE PAGO", "USUARIO", "DOCUMENTO", "ESTADO", "ACCI??NES"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        VD.setRowHeight(28);
        VD.getTableHeader().setReorderingAllowed(false);
        VD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                VDMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(VD);

        jTabbedPane6.addTab("", jScrollPane10);

        TablaVentasNombreProductos1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        TablaVentasNombreProductos1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO DE BARRAS", "NOMBRE", "CANTIDAD", "TOTAL", "NUMERO DE DOCUMENTO", "ACCI??N "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVentasNombreProductos1.setRowHeight(25);
        TablaVentasNombreProductos1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaVentasNombreProductos1MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(TablaVentasNombreProductos1);

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
        );

        jTabbedPane6.addTab("", jPanel77);

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        lblTipoDeBusquedaVentasDiarias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoDeBusquedaVentasDiarias.setText("SIN FILTRO");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setText("TIPO DE BUSQUEDA:");

        fechas.setBackground(new java.awt.Color(204, 204, 255));
        fechas.setFont(new java.awt.Font("DialogInput", 3, 24)); // NOI18N
        fechas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fechas.setText("Fecha");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel73.setText("FECHA DE VENTAS:");

        jSeparator30.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator30.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipoDeBusquedaVentasDiarias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lblTipoDeBusquedaVentasDiarias, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator30, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel73, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout VentasDiaLayout = new javax.swing.GroupLayout(VentasDia);
        VentasDia.setLayout(VentasDiaLayout);
        VentasDiaLayout.setHorizontalGroup(
            VentasDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane6)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        VentasDiaLayout.setVerticalGroup(
            VentasDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentasDiaLayout.createSequentialGroup()
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(VentasDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(VentasDia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        REFRESCAR_VENTAS_DIARIAS();
    }//GEN-LAST:event_jButton39ActionPerformed

    private void EstadoVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadoVentasActionPerformed
        jTabbedPane6.setSelectedIndex(0);
        String a= (String) ListaUsuarios.getSelectedItem();
        if(a == null){
            CONSULTAS_VENTAS.Registros(VD, EstadoVentas);
            CARGAR_TOTALES();
        }else if(a != null){

            Sele.setText(String.valueOf(proDao.ConsultaIdUsuario(ListaUsuarios)));
            CONSULTAS_VENTAS.RegistrosUsuario(VD, EstadoVentas, Sele.getText());
            CARGAR_TOTALES();
        }
    }//GEN-LAST:event_EstadoVentasActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        jTabbedPane6.setSelectedIndex(0);
        CONSULTAS_VENTAS.Registros(VD, EstadoVentas);
        CARGAR_TOTALES();
        Sele.setText("General");
        ListaUsuarios.setSelectedItem(null);
        lblTipoDeBusquedaVentasDiarias.setText("SIN FILTRO");
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jTabbedPane6.setSelectedIndex(0);
        lblTipoDeBusquedaVentasDiarias.setText("TIPO DOCUMENTO");
        CONSULTAS_VENTAS.ActualizarRegistroVentaPorTipoDocumento(jComboBox1, true, VD);
        CARGAR_TOTALES();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void ListaUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaUsuariosActionPerformed
        jTabbedPane6.setSelectedIndex(0);
        lblTipoDeBusquedaVentasDiarias.setText("USUARIOS");
        String a= (String) ListaUsuarios.getSelectedItem();
        if(a == null){
            CONSULTAS_VENTAS.Registros(VD, EstadoVentas);
        }else if(a != null){

            Sele.setText(String.valueOf(proDao.ConsultaIdUsuario(ListaUsuarios)));
            CONSULTAS_VENTAS.RegistrosUsuario(VD, EstadoVentas, Sele.getText());
        }
        CARGAR_TOTALES();
    }//GEN-LAST:event_ListaUsuariosActionPerformed

    private void CajaProductoBusquedaVentasDiariasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaProductoBusquedaVentasDiariasKeyReleased
        jTabbedPane6.setSelectedIndex(1);
        lblTipoDeBusquedaVentasDiarias.setText("PRODUCTO");
        CONSULTAS_VENTAS.ActualizarRegistroPorNombreDetalleProductos(Boolean.TRUE, CajaProductoBusquedaVentasDiarias, TablaVentasNombreProductos1);
    }//GEN-LAST:event_CajaProductoBusquedaVentasDiariasKeyReleased

    private void VDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VDMouseClicked
        int fila = VD.getSelectedRow();

        int Columna = VD.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY()/VD.getRowHeight();
        if(Fila < VD.getRowCount() && Fila >= 0 && Columna < VD.getColumnCount() && Columna >= 0){
            Object value = VD.getValueAt(Fila, Columna);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                String codigo = VD.getValueAt(fila, 7).toString();
                String Id = VD.getValueAt(fila, 0).toString();
                CajaIdVenta.setText(Id);
                JButton boton = (JButton) value;
                Detalles de= new Detalles(codigo, 1, 1, principal.P_O_S, principal);
                de.setVisible(true);

            }
        }
    }//GEN-LAST:event_VDMouseClicked

    private void TablaVentasNombreProductos1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentasNombreProductos1MouseClicked
        int fila = TablaVentasNombreProductos1.getSelectedRow();

        String codigo = TablaVentasNombreProductos1.getValueAt(fila, 4).toString();

        int Columna = TablaVentasNombreProductos1.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY()/TablaVentasNombreProductos1.getRowHeight();
        if(Fila < TablaVentasNombreProductos1.getRowCount() && Fila >= 0 && Columna < TablaVentasNombreProductos1.getColumnCount() && Columna >= 0){
            Object value = TablaVentasNombreProductos1.getValueAt(Fila, Columna);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                String Id = TablaVentasNombreProductos1.getValueAt(fila, 0).toString();
                JButton boton = (JButton) value;
                Detalles de= new Detalles(codigo, 1, 0, this.principal.P_O_S, principal);
                de.setVisible(true);
            }
        }
    }//GEN-LAST:event_TablaVentasNombreProductos1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextField CajaIdVenta;
    private static javax.swing.JTextField CajaProductoBusquedaVentasDiarias;
    private static javax.swing.JLabel CambiosVentasDiarias;
    private static javax.swing.JComboBox<String> EstadoVentas;
    public static javax.swing.JComboBox<String> ListaUsuarios;
    private static javax.swing.JLabel Pago;
    public static javax.swing.JLabel Sele;
    private static javax.swing.JLabel TP;
    private static javax.swing.JTable TablaVentasNombreProductos1;
    private static javax.swing.JTable VD;
    private javax.swing.JPanel VentasDia;
    private static javax.swing.JLabel fechas;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator45;
    private javax.swing.JSeparator jSeparator46;
    private javax.swing.JSeparator jSeparator47;
    private static javax.swing.JTabbedPane jTabbedPane6;
    private static javax.swing.JLabel lblTipoDeBusquedaVentasDiarias;
    // End of variables declaration//GEN-END:variables
}
