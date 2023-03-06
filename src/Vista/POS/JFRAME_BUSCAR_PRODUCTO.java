/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.POS;

import static CLASES_GLOBALES.METODOS_GLOBALES.INSERTAR_IMAGEN_TABLA;
import Controlador.ProductosDao;
import Controlador.ProveedoresDao;
import Controlador.loginDao;
import Modelo.Categoria;
import Modelo.Combo;
import Modelo.ComboCategoria;
import Modelo.DatosEmpresaGeneral;
import Modelo.Productos;
import Modelo.Proveedor;
import Modelo.Ubicacion;
import Tablas.ActualizarTablaVentas;
import Tablas.RenderTablasJLabel;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaldoter
 */

public class JFRAME_BUSCAR_PRODUCTO extends javax.swing.JFrame {
    static ProductosDao proDao = new ProductosDao();
    ActualizarTablaVentas tablasVentas = new ActualizarTablaVentas();
    ProveedoresDao proveDao = new ProveedoresDao();
    DefaultTableModel modelo = new DefaultTableModel();
    static int BusquedaHecha=1;
    Boolean Resultado= false;
    POS pos;
    
   public JFRAME_BUSCAR_PRODUCTO() {
        initComponents();
    }
    
    public JFRAME_BUSCAR_PRODUCTO(POS pos) {
        initComponents();
        this.pos = pos;
        this.setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize().width/2, java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-200);
        this.setLocationRelativeTo(null);
        VaciarYllenarCategoria();
        VaciarYllenarProveedor();
        VaciarYllenarUbicacion();
        ConsultasVarias(1);
        BusquedaHecha=1;
        Cerrar();
        jTextField1.requestFocus();
        jCheckBox2.setEnabled(false);
    }
    
    public final void Cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    POS.VentanaBuscarProducto=false;
                    dispose();
                }
            });
            this.setVisible(true);

        } catch (Exception e) {
        }
    }
    
     @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ObtenerRutaImagen(1));


    return retValue;
    }
    public static String ObtenerRutaImagen(int Seleccion){
        String Ruta;
        DatosEmpresaGeneral DE= new DatosEmpresaGeneral();
        loginDao login = new loginDao();
        DE = login.VerDatosEmpresaEnLogin();    
        if(Seleccion==0){
        Ruta = CargarDatosRutas(0)+"\\"+DE.getRutaimagenlogo();
        }else{
        Ruta = CargarDatosRutas(0)+"\\"+DE.getRutaimagensistema();
        }
        return Ruta;
    }
    
    public static String CargarDatosRutas(int TipoRuta){
        String Ruta="";
            try {
                Properties propertie3= new Properties();
    InputStream entrada = null;
            entrada = new FileInputStream(new File ("/Sistema Punto de Venta YG/CONFIGURACIONES/RUTASERVIDORIMAGENES.properties").getAbsolutePath());
            propertie3.load(entrada);
            if(TipoRuta==0){
                Ruta=propertie3.getProperty("rutasistema");
            }else{
               Ruta=  propertie3.getProperty("ruta");
            }
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
            return Ruta;
    }

    public void LimpiarTablaVenta(JTable Tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        for (int e = 0; e < 15; e++) {
            
        
        for (int i = 0; i < Tabla.getRowCount(); i++) {
            modelo = (DefaultTableModel) Tabla.getModel();
            modelo.removeRow(i);
        }
        }
    }

    public final void ActualizarTabla(){
        LimpiarTablaVenta(TablaVentas1);
        ActualizarTablaVentas tablasVentas = new ActualizarTablaVentas();
        tablasVentas.ActualizarTablaVenta(TablaVentas1);
    }
    
    private void VaciarYllenarProveedor(){
        ComboProveedoresBusquedaProductos.removeAllItems();
        
        List<Proveedor> lista = proveDao.ListarProveedor();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdproveedores();
            String Nombre = lista.get(i).getProveedor();
            ComboProveedoresBusquedaProductos.addItem(new Combo(id, Nombre).toString());
        }
    }
    
    private void VaciarYllenarCategoria(){
        ComboCategoriaBusquedaProductos.removeAllItems();
        List<Categoria> lista = proDao.ListarCategoria();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdCategoria();
            String Nombre = lista.get(i).getCategoria();
            ComboCategoriaBusquedaProductos.addItem(new ComboCategoria(id, Nombre).toString());
        }
    }
    
    private void VaciarYllenarUbicacion() {
        ComboUbicacionesBusquedaProductos.removeAllItems();
        List<Ubicacion> lista = proDao.ListarUbicacion();
        for (int i = 0; i < lista.size(); i++) {
            ComboUbicacionesBusquedaProductos.addItem(lista.get(i).getNombreUbicacion());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        ComboCategoriaBusquedaProductos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        ComboUbicacionesBusquedaProductos = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ComboProveedoresBusquedaProductos = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btnDetallesProductoVenta = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVentas1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalResultados = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblTipoBusqueda = new javax.swing.JLabel();

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("IMÁGEN"));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BÚSQUEDA DE PRODUCTO");
        setBackground(new java.awt.Color(90, 190, 240));
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(500, 500));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel9.setText("CÓDIGO-NOMBRE-DESCRIPCIÓN");

        ComboCategoriaBusquedaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboCategoriaBusquedaProductosKeyPressed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CATEGORÍA:");

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("ORDEN");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboCategoriaBusquedaProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboCategoriaBusquedaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("DESC.", jPanel7);

        ComboUbicacionesBusquedaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboUbicacionesBusquedaProductosKeyPressed(evt);
            }
        });

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("ÚBICACIÓNES:");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("PROVEEDORES:");

        ComboProveedoresBusquedaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboProveedoresBusquedaProductosKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboUbicacionesBusquedaProductos, 0, 116, Short.MAX_VALUE)
                            .addComponent(ComboProveedoresBusquedaProductos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboUbicacionesBusquedaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboProveedoresBusquedaProductos, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PROV-UBICACIONES", jPanel9);

        jButton3.setText("LIMPIAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnDetallesProductoVenta.setText("DETALLES");
        btnDetallesProductoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesProductoVentaActionPerformed(evt);
            }
        });

        jButton4.setText("ACTUALIZAR");
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetallesProductoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDetallesProductoVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder("PRODUCTO SELECCIONADO"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

        TablaVentas1  = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                for(int i = 0; i < TablaVentas1.getRowCount(); i ++){
                    if(row < 0){
                        return true;
                    }
                }
                return false;
            }
        };
        TablaVentas1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TablaVentas1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE", "STOCK", "COSTO", "PRECIO", "RUTA", "IMÁGEN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVentas1.setDragEnabled(true);
        TablaVentas1.setRowHeight(160);
        TablaVentas1.setShowGrid(false);
        TablaVentas1.setShowHorizontalLines(true);
        TablaVentas1.getTableHeader().setReorderingAllowed(false);
        TablaVentas1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaVentas1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                TablaVentas1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaVentas1);
        if (TablaVentas1.getColumnModel().getColumnCount() > 0) {
            TablaVentas1.getColumnModel().getColumn(0).setPreferredWidth(20);
            TablaVentas1.getColumnModel().getColumn(1).setPreferredWidth(150);
            TablaVentas1.getColumnModel().getColumn(2).setPreferredWidth(2);
            TablaVentas1.getColumnModel().getColumn(3).setPreferredWidth(2);
            TablaVentas1.getColumnModel().getColumn(4).setPreferredWidth(2);
            TablaVentas1.getColumnModel().getColumn(5).setPreferredWidth(1);
            TablaVentas1.getColumnModel().getColumn(6).setPreferredWidth(95);
        }

        jLabel6.setText("TOTAL DE RESULTADOS:");

        lblTotalResultados.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTotalResultados.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalResultados, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
            .addComponent(lblTotalResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel4.setText("FILTRO:");

        lblTipoBusqueda.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipoBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblTipoBusqueda, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaVentas1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentas1MouseClicked
       int fila = TablaVentas1.getSelectedRow();
        String codigo = TablaVentas1.getValueAt(fila, 0).toString();
       
            jLabel3.setText(codigo);
       
       if (evt.getClickCount()> 1)
{
    this.pos.BuscarProductoVenta(jLabel3.getText());
    this.toFront();
}
    }//GEN-LAST:event_TablaVentas1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        ConsultasVarias(1);
        BusquedaHecha =1;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased

    }//GEN-LAST:event_jLabel1MouseReleased

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(!jTextField1.getText().equals("")){
            ConsultasVarias(2);
        BusquedaHecha =2;
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jTextField1.setText(null);
        jLabel3.setText(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnDetallesProductoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesProductoVentaActionPerformed
        if(jLabel3.getText() != null){
            DETALLE_PRODUCTO DP= new DETALLE_PRODUCTO();
            DP.VerProducto(jLabel3.getText());
            DP.setVisible(true);
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("¡NO SE PUEDE PROCESAR!","¡AÚN NO HA SELECCIONADO NINGÚN PRODUCTO!", DesktopNotify.ERROR, 4000L);
        }
    }//GEN-LAST:event_btnDetallesProductoVentaActionPerformed

    private void TablaVentas1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentas1MouseReleased
        TablaVentas1.setToolTipText("HAZ DOBLEC CLIC PARA AGREGAR EL PRODUCTO");
    }//GEN-LAST:event_TablaVentas1MouseReleased

    private void ComboUbicacionesBusquedaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboUbicacionesBusquedaProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        ConsultasVarias(6);
        BusquedaHecha =6;
        }
    }//GEN-LAST:event_ComboUbicacionesBusquedaProductosKeyPressed

    private void ComboProveedoresBusquedaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboProveedoresBusquedaProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        ConsultasVarias(3);
        BusquedaHecha =3;
        }
    }//GEN-LAST:event_ComboProveedoresBusquedaProductosKeyPressed

    private void ComboCategoriaBusquedaProductosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboCategoriaBusquedaProductosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ConsultasVarias(4);
            BusquedaHecha =4;
        }
    }//GEN-LAST:event_ComboCategoriaBusquedaProductosKeyPressed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if(!jTextField1.getText().equals("")){
            ConsultasVarias(2);
        BusquedaHecha =2;
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    
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
            java.util.logging.Logger.getLogger(JFRAME_BUSCAR_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFRAME_BUSCAR_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFRAME_BUSCAR_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFRAME_BUSCAR_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JFRAME_BUSCAR_PRODUCTO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboCategoriaBusquedaProductos;
    private javax.swing.JComboBox<String> ComboProveedoresBusquedaProductos;
    private javax.swing.JComboBox<String> ComboUbicacionesBusquedaProductos;
    public static javax.swing.JTable TablaVentas1;
    private javax.swing.JButton btnDetallesProductoVenta;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private static javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblTipoBusqueda;
    private static javax.swing.JLabel lblTotalResultados;
    // End of variables declaration//GEN-END:variables

        
        public void ConsultaPorCodigo(){
        //tablasVentas.ConsultaPorCodigo(TablaVentas1, IdVenta.getText());
        }
        
        public final void ConsultasVarias(int TipoBusqueda) {
            Resultado=false;
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        int Inicio = 0, Final = 0;
        List<Productos> ListarPr = null;
        TablaVentas1.setDefaultRenderer(Object.class, new RenderTablasJLabel());
        ActualizarTablaVentas tablas = new ActualizarTablaVentas();
        LimpiarTablaVenta(TablaVentas1);
        modelo = (DefaultTableModel) TablaVentas1.getModel();
        
        switch (TipoBusqueda) {
            case 1:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos("", "", 1, jCheckBox2.isSelected());
                lblTipoBusqueda.setText("ORDENADO POR ID DE PRODUCTO");
                break;
            case 2:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(jTextField1.getText(), "", 2, jCheckBox2.isSelected());
                lblTipoBusqueda.setText("CÓDIGO, NOMBRE O DESCRIPCIÓN");
                break;
            case 3:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdProveedor(ComboProveedoresBusquedaProductos)), null, 3, jCheckBox2.isSelected());
                lblTipoBusqueda.setText("PROVEEDORES");
                break;
            case 4:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdCategoria(ComboCategoriaBusquedaProductos)), "", 4, jCheckBox2.isSelected());
                lblTipoBusqueda.setText("CATEGORIA");
                break;
            case 6:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdUbicacion(ComboUbicacionesBusquedaProductos)), String.valueOf(ConsultarIdUbicacion(ComboUbicacionesBusquedaProductos)), 6, jCheckBox2.isSelected());
                lblTipoBusqueda.setText("UBICACION");
                break;
            default:
                break;
        }
        Object[] ob = new Object[7];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getCodigoBarras();
            ob[1] = "<html>"+ListarPr.get(i).getNombre()+"</html>";
            ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getCodigoLetras();
            ob[4] = ListarPr.get(i).getPublico();
            ob[5] = ListarPr.get(i).getRuta();
            ob[6] = ListarPr.get(i).getRuta();
            modelo.addRow(ob);
        }
        TablaVentas1.setModel(modelo);
            INSERTAR_IMAGEN_TABLA(TablaVentas1, 5, 6, 140, 170);
        lblTotalResultados.setText(""+TablaVentas1.getRowCount());
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }
        
        /*public final void ConsultasVarias(int TipoBusqueda) {
        setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        int Inicio = 0, Final = 0;
        List<PRODUCTOS_MODELO> ListarPr = null;
        TablaVentas1.setDefaultRenderer(Object.class, new RenderTablasJLabel());
        ActualizarTablaVentas tablas = new ActualizarTablaVentas();
        LimpiarTablaVenta(TablaVentas1);
        modelo = (DefaultTableModel) TablaVentas1.getModel();
        switch (ComboBusqueda.getSelectedIndex()) {
            case 0 -> {
                Inicio = 0;
                Final = 50;
            }
            case 1 -> {
                Inicio = 50;
                Final = 50;
            }
            case 2 -> {
                Inicio = 100;
                Final = 50;
            }
            case 3 -> {
                Inicio = 150;
                Final = 50;
            }
            case 4 -> {
                Inicio = 200;
                Final = 50;
            }
            case 5 -> {
                Inicio = 250;
                Final = 50;
            }
            case 6 -> {
                Inicio = 300;
                Final = 50;
            }
            case 7 -> {
                Inicio = 350;
                Final = 50;
            }
            default -> {
            }
        }
        switch (TipoBusqueda) {
            case 1:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos("", "", Inicio, Final, 1);
                lblTipoBusqueda.setText("ORDENADO POR ID DE PRODUCTO");
                break;
            case 2:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(jTextField1.getText(), "", Inicio, Final, 2);
                lblTipoBusqueda.setText("CÓDIGO, NOMBRE O DESCRIPCIÓN");
                break;
            case 3:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdProveedor(ComboProveedoresBusquedaProductos)), null, Inicio, Final, 3);
                lblTipoBusqueda.setText("PROVEEDORES");
                break;
            case 4:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdCategoria(ComboCategoriaBusquedaProductos)), "", Inicio, Final, 4);
                lblTipoBusqueda.setText("CATEGORIA");
                break;
            case 5:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdCategoria(ComboProveedoresBusquedaProductos)), String.valueOf(ConsultarIdSubCategoria(ComboCategoriaBusquedaProductos, ComboSubCategoriaBusquedaProductos)), Inicio, Final, 5);
                lblTipoBusqueda.setText("CATEGORIA --> SUBCATEGORIA");
                break;
            case 6:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdUbicacion(ComboUbicacionesBusquedaProductos)), String.valueOf(ConsultarIdUbicacion(ComboUbicacionesBusquedaProductos)), Inicio, Final, 6);
                lblTipoBusqueda.setText("UBICACION");
                break;
            default:
                break;
        }
        Object[] ob = new Object[6];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getCODIGOBARRAS();
            ob[1] = ListarPr.get(i).getNOMBRE_PRODUCTO();
            ob[2] = ListarPr.get(i).getSTOCK_PRODUCTO();
            ob[3] = ListarPr.get(i).getCOSTO_LETRAS();
            ob[4] = ListarPr.get(i).getPRECIO_1();
            ob[5] = ListarPr.get(i).getRUTA_IMAGEN();
            modelo.addRow(ob);
        }
        TablaVentas1.setModel(modelo);
            INSERTAR_IMAGEN_TABLA(TablaVentas1);
        
        lblTotalResultados.setText(""+TablaVentas1.getRowCount());
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }*/
        
        
        
        
        
        public int ConsultarIdProveedor(JComboBox ComboCategoria){
        int ResultadoProveedor = 0;
        ResultadoProveedor = proDao.ConsultaIdProveedor(ComboCategoria);
      return ResultadoProveedor;
    }
    
    public int ConsultarIdCategoria(JComboBox ComboCategoria){
        int ResultadoCategoria = 0;
                ResultadoCategoria = proDao.ConsultaIdCategoria(ComboCategoria);
      return ResultadoCategoria;
    }
    
    public int ConsultarIdUbicacion(JComboBox ComboUbicacion){
        int ResultadoUbicacion = 0;
      ResultadoUbicacion = Integer.parseInt(String.valueOf(proDao.ConsultaIdUbicacion(ComboUbicacion)));
      return ResultadoUbicacion;
    }
    
    public int ConsultarIdSubCategoria(JComboBox ComboCategoria, JComboBox ComboSubCategoria){
        int ResultadoSubCategoria = 0;
      ResultadoSubCategoria = Integer.parseInt(String.valueOf(proDao.ConsultaIdSubCategoria(ConsultarIdCategoria(ComboCategoria), ComboSubCategoria)));
      return ResultadoSubCategoria;
    }
}
