package Vista;
import static CLASES_GLOBALES.METODOS_GLOBALES.ObtenerRutaImagen;
import CLASES_GLOBALES.PARAMETROS_BASE_DE_DATOS;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import CONTROL_DE_ACTUALIZACIÓNES.CONTROL_DE_ACTUALIZACIÓNES;
import Vista.ADMINISTRACION.INVENTARIO.Ubicaciones;
import Vista.ADMINISTRACION.INVENTARIO.CategoriaVista;
import Modelo.Productos;
import Controlador.ProductosDao;
import Controlador.loginDao;
import Excel.Excel;
import Configuraciones.Ventas;
import Controlador.DatosEmpresaDao;
import PROMOCIONES.ETIQUETAS.Promociones;
import Reportes.Reportes;
import Vista.ADMINISTRACION.CLIENTES.CLIENTES;
import Vista.ADMINISTRACION.PROVEEDORES.PROVEEDORES;
import Vista.ADMINISTRACION.USUARIOS.ADMINISTRACION_DE_USUARIOS;
import Vista.ADMINISTRACION.USUARIOS.USUARIOS_INTERNOS;
import Vista.CONFIGURACION.EMPRESA;
import Vista.Cotizaciones.CotizacionesGenerales;
import Vista.DASHBOARD.DASHBOARD;
import Vista.ADMINISTRACION.INVENTARIO.ADMINISTRARPRODUCTO;
import Vista.ADMINISTRACION.INVENTARIO.INVENTARIO;
import Vista.ADMINISTRACION.INVENTARIO.ImportarExcel;
import Vista.ADMINISTRACION.INVENTARIO.KARDEX;
import Vista.ADMINISTRACION.PROVEEDORES.GASTOS_NUEVO;
import Vista.ADMINISTRACION.PROVEEDORES.HISTORIAL_GASTOS;
import Vista.POS.MODO_ESPERA;
import Vista.POS.POS;
import Vista.REPORTES_VENTAS.MOVIMIENTOS_DIARIOS;
import Vista.REPORTES_VENTAS.MOVIMIENTOS_GENERALES;
import Vista.SESION.SESION;
import Vista.VENTAS.CAJA.INTERNAL_CAJA_PRINCIPAL;
import Vista.VENTAS.CAJA.VER_CAJAS;
import WebServiceDigifact.ObtenerToken;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public final class Principal extends javax.swing.JFrame {
    ProductosDao proDao;
    Productos pro = new Productos();
    PARAMETROS_EMPRESA P_E = new PARAMETROS_EMPRESA();;
    Ventas ConfigVentas = new Ventas();

    Reportes Re = new Reportes();
    
    loginDao logina = new loginDao();
    public ADMINISTRARPRODUCTO AdminProduct;
    public CategoriaVista Cat;
    public Ubicaciones Ub;
    Config conf;
    public int ContabilizadorDeVentanasCliente = 0;
    public boolean VentanaAdministracionDeProductos = false;
    public boolean VENTANA_ADMINISTRACION_DE_USUARIOS = false;
    public boolean VentanaBusquedaProducto = false;
    public boolean VentanaCategoria = false;
    public boolean VentanaUbicaciones = false;
    public boolean VentanaConfiguraciones = false;
    public boolean VentanaDetalleDeVenta = false;
    
    //VENTANAS
    MOVIMIENTOS_GENERALES MG= new MOVIMIENTOS_GENERALES(this);
    USUARIOS_INTERNOS UI= new USUARIOS_INTERNOS(this);
    public CLIENTES C= new CLIENTES(this);
    PROVEEDORES P= new PROVEEDORES();
    SESION S= new SESION();
    DASHBOARD D= new DASHBOARD(this);
    EMPRESA E = new EMPRESA(this);
    public POS P_O_S = new POS(this);
    CotizacionesGenerales CG = new CotizacionesGenerales(P_O_S);
    MOVIMIENTOS_DIARIOS MD= new MOVIMIENTOS_DIARIOS(this);
    INVENTARIO I = new INVENTARIO(P_O_S, this);
    //POS P_O_S= new POS();
    INTERNAL_CAJA_PRINCIPAL INTERNAL_CAJA_P = new INTERNAL_CAJA_PRINCIPAL(this);
    MODO_ESPERA M_E= new MODO_ESPERA();
    KARDEX K= new KARDEX();
    //VENTANAS EMERGENTES
    public ADMINISTRACION_DE_USUARIOS ADMIN_USUARIOS;
    //NUMERO INTERNO DE VENTA    
    
    public Principal(){
        
    }
    
    public Principal(String Validar) {
        initComponents();
        this.setExtendedState(Principal.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Excel EX = new Excel();
                EX.exportarExcel_JTABLE(P_O_S.TablaVentas, PARAMETROS_USUARIOS.NOMBRE_USUARIO);
            }
        });
        CARGAR_TITULO();
        Ventas.CargarDatosImpresionRapida(CheckBoxImpresionRapida);
        ConfigVentas.CargarDatosModoReinventario(CheckBoxModoStockCero);
        ConfigVentas.CargarDatosProductosPersonalizados(CheckPermitirProductosPersonalizados);
        HORA_FECHA();
        CARGAR_INICIO();
        if (PARAMETROS_USUARIOS.ROL_USUARIO.equals("Usuario")) {
            ItemInventario.setVisible(false);
            ItemUsuariosSistema.setVisible(false);
            jMenuItem4.setVisible(false);
            ItemUsuarios.setVisible(false);
            jSeparator14.setVisible(false);
            ItemVentasGenerales.setVisible(false);
            jSeparator11.setVisible(false);
            jMenu4.setVisible(false);
            jMenuItem21.setVisible(false);
            jSeparator24.setVisible(false);
            jMenu9.setVisible(false);
            jMenu6.setVisible(false);
            jMenuItem28.setVisible(false);
            jSeparator1.setVisible(false);
            jMenuItem14.setVisible(false);
            jSeparator3.setVisible(false);
            jButton5.setVisible(false);
            jButton6.setVisible(false);
        } else {
        }
        AlIniciarSesion();
        Cerrar();
    }
    
    public void CARGAR_TITULO(){
        this.setTitle(PARAMETROS_VERSION_SISTEMA.NOMBRE_SISTEMA+" "+PARAMETROS_VERSION_SISTEMA.VERSION_SISTEMA+
        " | "+PARAMETROS_EMPRESA.NOMBRE_EMPRESA.toUpperCase()+ " | "+PARAMETROS_USUARIOS.NOMBRE_USUARIO+ 
        " | "+PARAMETROS_USUARIOS.ROL_USUARIO.toUpperCase());
    }

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ObtenerRutaImagen(1));


    return retValue;
    }
    
    public void Cerrar(){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                ConfirmarSalida();     
            }
        });
            this.setVisible(true);
                  
        } catch (Exception e) {
        }
    }
    
    public void ConfirmarSalida(){
        if(P_O_S.TablaVentas.getRowCount() > 0){
            JOptionPane.showMessageDialog(null, "¡TIENE UNA VENTA PENDIENTE, TERMINELA O CANCELELA!");
        }else if(VentanaAdministracionDeProductos==true){
            JOptionPane.showMessageDialog(null, "¡TIENE UNA VENTANA DE ADMINISTRACIÓN ABIERTA, TERMÍNELA O CIÉRRELA!");
            AdminProduct.toFront();
        }else if(VENTANA_ADMINISTRACION_DE_USUARIOS==true){
            JOptionPane.showMessageDialog(null, "¡TIENE UNA VENTANA DE ADMINISTRACIÓN DE USUARIOS ABIERTA, TERMÍNELA O CIÉRRELA!");
            ADMIN_USUARIOS.toFront();
        } else{
            int seleccion = JOptionPane.showConfirmDialog(null, "¿ESTA SEGURO DE SALIR DEL SISTEMA?","SISTEMA EL AMIGO", JOptionPane.YES_NO_OPTION);
            if(seleccion==0){
                AlCerrarSesion();
                JOptionPane.showMessageDialog(null, "¡FUE UN GUSTO VERTE "+PARAMETROS_USUARIOS.NOMBRE_USUARIO.toUpperCase() +" ! :)", "SISTEMA EL AMIGO", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);    
            }
            
        }
    }
    
    
    public void AlCerrarSesion(){
            logina.EditarUsuarios2(PARAMETROS_USUARIOS.ID_USUARIO);         
    }
    
    public void AlIniciarSesion(){
        logina.MovimientosUsuarios(PARAMETROS_USUARIOS.ID_USUARIO);    
    }
    
    public void HORA_FECHA(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Date objDate = new Date();
                String strDateFormat = "dd-MMM-yyyy"; // El formato de fecha está especificado  
                SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
                //lblFecha_Principal.setText(objSDF.format(objDate));
                while (true) {
                    try {
                Thread.sleep(500);
                SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss aa");
                Date date = new Date();
                lblHora_Principal.setText(formatHora.format(date));
            } catch (InterruptedException e) {
                        
            }
        }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }
    
    public void CARGAR_INICIO(){
        lblNombre_BD_Principal.setText(PARAMETROS_BASE_DE_DATOS.NOMBRE_BASE_DE_DATOS);
        lblURL_Principal.setText(PARAMETROS_BASE_DE_DATOS.URL_BASE_DE_DATOS);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel62 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Filtro = new javax.swing.JPopupMenu();
        filtroid = new javax.swing.JCheckBoxMenuItem();
        jSeparator25 = new javax.swing.JPopupMenu.Separator();
        filtrocategorias = new javax.swing.JCheckBoxMenuItem();
        jSeparator26 = new javax.swing.JPopupMenu.Separator();
        filtroproveedores = new javax.swing.JCheckBoxMenuItem();
        jSeparator27 = new javax.swing.JPopupMenu.Separator();
        filtrousuario = new javax.swing.JCheckBoxMenuItem();
        jSeparator43 = new javax.swing.JSeparator();
        DESKTOP_PRINCIPAL = new javax.swing.JDesktopPane();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblNombre_BD_Principal = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator30 = new javax.swing.JSeparator();
        lblURL_Principal = new javax.swing.JLabel();
        jSeparator35 = new javax.swing.JSeparator();
        lblHora_Principal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator36 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        CheckPermitirProductosPersonalizados = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        CheckBoxModoStockCero = new javax.swing.JCheckBoxMenuItem();
        jSeparator31 = new javax.swing.JPopupMenu.Separator();
        CheckBoxImpresionRapida = new javax.swing.JCheckBoxMenuItem();
        jSeparator22 = new javax.swing.JPopupMenu.Separator();
        ItemVentas = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem28 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        ItemInventario = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenu14 = new javax.swing.JMenu();
        ItemProveedores1 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        jMenuItem27 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        ItemUsuariosSistema = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator34 = new javax.swing.JPopupMenu.Separator();
        jMenuItem31 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        ItemVentasGenerales = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        ItemProductos = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator29 = new javax.swing.JPopupMenu.Separator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JPopupMenu.Separator();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JPopupMenu.Separator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        ItemClientes = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        ItemProveedores = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JPopupMenu.Separator();
        ItemUsuarios = new javax.swing.JMenuItem();
        jSeparator32 = new javax.swing.JPopupMenu.Separator();
        ItemUsuarios1 = new javax.swing.JMenuItem();
        jSeparator37 = new javax.swing.JPopupMenu.Separator();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator38 = new javax.swing.JPopupMenu.Separator();
        jMenuItem26 = new javax.swing.JMenuItem();
        jSeparator39 = new javax.swing.JPopupMenu.Separator();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JPopupMenu.Separator();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator56 = new javax.swing.JPopupMenu.Separator();
        jMenuItem30 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        filtroid.setSelected(true);
        filtroid.setText("jCheckBoxMenuItem1");
        Filtro.add(filtroid);
        Filtro.add(jSeparator25);

        filtrocategorias.setSelected(true);
        filtrocategorias.setText("jCheckBoxMenuItem2");
        Filtro.add(filtrocategorias);
        Filtro.add(jSeparator26);

        filtroproveedores.setSelected(true);
        filtroproveedores.setText("jCheckBoxMenuItem3");
        Filtro.add(filtroproveedores);
        Filtro.add(jSeparator27);

        filtrousuario.setSelected(true);
        filtrousuario.setText("jCheckBoxMenuItem4");
        Filtro.add(filtrousuario);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(800, 700));

        DESKTOP_PRINCIPAL.setAutoscrolls(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/data-connection_icon-icons.com_52841.png"))); // NOI18N
        jButton1.setText("DASHBOARD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/shoppingcart_77968.png"))); // NOI18N
        jButton2.setText("POINT OF SALE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/business_salesreport_salesreport_negocio_2353.png"))); // NOI18N
        jButton3.setText("MOVIMIENTOS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Detalle.png"))); // NOI18N
        jButton4.setText("COTIZACIÓNES");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ENTRADAS_SALIDAS_32PX.png"))); // NOI18N
        jButton5.setText("ENTRADAS Y SALIDAS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/CAJA_32PX.png"))); // NOI18N
        jButton6.setText("CAJA");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        DESKTOP_PRINCIPAL.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DESKTOP_PRINCIPAL.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DESKTOP_PRINCIPAL.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DESKTOP_PRINCIPAL.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DESKTOP_PRINCIPAL.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        DESKTOP_PRINCIPAL.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout DESKTOP_PRINCIPALLayout = new javax.swing.GroupLayout(DESKTOP_PRINCIPAL);
        DESKTOP_PRINCIPAL.setLayout(DESKTOP_PRINCIPALLayout);
        DESKTOP_PRINCIPALLayout.setHorizontalGroup(
            DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DESKTOP_PRINCIPALLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        DESKTOP_PRINCIPALLayout.setVerticalGroup(
            DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DESKTOP_PRINCIPALLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addGroup(DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DESKTOP_PRINCIPALLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("BASE DE DATOS:");

        lblNombre_BD_Principal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNombre_BD_Principal.setForeground(new java.awt.Color(255, 255, 255));
        lblNombre_BD_Principal.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("URL");

        jSeparator30.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblURL_Principal.setBackground(new java.awt.Color(255, 255, 255));
        lblURL_Principal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblURL_Principal.setForeground(new java.awt.Color(255, 255, 255));
        lblURL_Principal.setText("jLabel4");

        jSeparator35.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblHora_Principal.setBackground(new java.awt.Color(255, 255, 255));
        lblHora_Principal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHora_Principal.setForeground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("HORA:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("CAJA:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombre_BD_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblURL_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHora_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHora_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(lblURL_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNombre_BD_Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator30, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(jSeparator35)))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jMenu1.setText("DASHBOARD");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem12.setText("INICIO");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem12);

        jMenuBar2.add(jMenu1);

        jMenu2.setText("VENTAS");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/shoppingcart_77968.png"))); // NOI18N
        jMenuItem1.setText("POS");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator36);

        jMenu5.setText("PREFERENCIAS DE VENTAS");

        CheckPermitirProductosPersonalizados.setSelected(true);
        CheckPermitirProductosPersonalizados.setText("PERMITIR VENDER PRODUCTOS PERSONALIZADOS");
        CheckPermitirProductosPersonalizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckPermitirProductosPersonalizadosActionPerformed(evt);
            }
        });
        jMenu5.add(CheckPermitirProductosPersonalizados);
        jMenu5.add(jSeparator2);

        CheckBoxModoStockCero.setSelected(true);
        CheckBoxModoStockCero.setText("PERMITIR VENDER PRODUCTOS CON STOCK 0");
        CheckBoxModoStockCero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxModoStockCeroActionPerformed(evt);
            }
        });
        jMenu5.add(CheckBoxModoStockCero);
        jMenu5.add(jSeparator31);

        CheckBoxImpresionRapida.setSelected(true);
        CheckBoxImpresionRapida.setText("IMPRESION RAPIDA (BETA)");
        CheckBoxImpresionRapida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxImpresionRapidaActionPerformed(evt);
            }
        });
        jMenu5.add(CheckBoxImpresionRapida);

        jMenu2.add(jMenu5);
        jMenu2.add(jSeparator22);

        ItemVentas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        ItemVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/business_salesreport_salesreport_negocio_2353.png"))); // NOI18N
        ItemVentas.setText("MOVIMIENTOS DEL DÍA");
        ItemVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemVentasActionPerformed(evt);
            }
        });
        jMenu2.add(ItemVentas);
        jMenu2.add(jSeparator23);

        jMenuItem20.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        jMenuItem20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Detalle.png"))); // NOI18N
        jMenuItem20.setText("COTIZACIÓNES");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem20);
        jMenu2.add(jSeparator3);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ENTRADAS_SALIDAS_32PX.png"))); // NOI18N
        jMenuItem14.setText("ENTRADAS/SALIDAS");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);
        jMenu2.add(jSeparator1);

        jMenuItem28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/CAJA_32PX.png"))); // NOI18N
        jMenuItem28.setText("CAJA");
        jMenuItem28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem28ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem28);

        jMenuBar2.add(jMenu2);

        jMenu4.setText("ADMINISTRACION");

        ItemInventario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_DOWN_MASK));
        ItemInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/business_inventory_maintenance_product_box_boxes_2326.png"))); // NOI18N
        ItemInventario.setText("INVENTARIO");
        ItemInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemInventarioActionPerformed(evt);
            }
        });
        jMenu4.add(ItemInventario);
        jMenu4.add(jSeparator17);

        jMenu14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/PROVEEDORES_32PX.png"))); // NOI18N
        jMenu14.setText("PROVEEDORES");

        ItemProveedores1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_DOWN_MASK));
        ItemProveedores1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/checklist_106575.png"))); // NOI18N
        ItemProveedores1.setText("PROVEEDORES");
        ItemProveedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemProveedores1ActionPerformed(evt);
            }
        });
        jMenu14.add(ItemProveedores1);
        jMenu14.add(jSeparator4);

        jMenuItem24.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/shoppingcart_77968.png"))); // NOI18N
        jMenuItem24.setText("COMPRAS");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu14.add(jMenuItem24);

        jMenu4.add(jMenu14);
        jMenu4.add(jSeparator21);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/1486486297-attribute-category-label-shop-price-price-tag-tag_81213.png"))); // NOI18N
        jMenuItem4.setText("CATEGORÍAS");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem4);
        jMenu4.add(jSeparator20);

        jMenuItem27.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ubicaciones.png"))); // NOI18N
        jMenuItem27.setText("UBICACIÓNES");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem27);
        jMenu4.add(jSeparator10);

        ItemUsuariosSistema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.ALT_DOWN_MASK));
        ItemUsuariosSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/community_users_12977.png"))); // NOI18N
        ItemUsuariosSistema.setText("USUARIOS INTERNOS");
        ItemUsuariosSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemUsuariosSistemaActionPerformed(evt);
            }
        });
        jMenu4.add(ItemUsuariosSistema);
        jMenu4.add(jSeparator5);

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/Clients_27015.png"))); // NOI18N
        jMenuItem13.setText("CLIENTES");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);
        jMenu4.add(jSeparator34);

        jMenuItem31.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_8, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/KARDEX_32PX.png"))); // NOI18N
        jMenuItem31.setText("KARDEX");
        jMenuItem31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem31ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem31);

        jMenuBar2.add(jMenu4);

        jMenu6.setText("REPORTES");

        ItemVentasGenerales.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_DOWN_MASK));
        ItemVentasGenerales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/artboard_123065.png"))); // NOI18N
        ItemVentasGenerales.setText("Ventas Generales");
        ItemVentasGenerales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemVentasGeneralesActionPerformed(evt);
            }
        });
        jMenu6.add(ItemVentasGenerales);
        jMenu6.add(jSeparator11);

        ItemProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/graphs_46879.png"))); // NOI18N
        ItemProductos.setText("PRODUCTOS");

        jMenu11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/iconfinder-documents01-1622837_121952.png"))); // NOI18N
        jMenu11.setText("REPORTES DE PRODUCTOS");

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/iconfinder-documents01-1622837_121952.png"))); // NOI18N
        jMenuItem10.setText("REPORTE STANDAR");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem10);
        jMenu11.add(jSeparator29);

        jMenuItem22.setText("REPORTE PARA CATÁLOGO");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem22);

        ItemProductos.add(jMenu11);
        ItemProductos.add(jSeparator28);

        jMenu10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/microsoft_office_excel_logo_icon_145720.png"))); // NOI18N
        jMenu10.setText("EXCEL");

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/microsoft_office_excel_logo_icon_145720.png"))); // NOI18N
        jMenuItem11.setText("EXPORTAR");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem11);
        jMenu10.add(jSeparator24);

        jMenuItem21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/microsoft_office_excel_logo_icon_145720.png"))); // NOI18N
        jMenuItem21.setText("IMPORTAR");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem21);

        ItemProductos.add(jMenu10);

        jMenu6.add(ItemProductos);
        jMenu6.add(jSeparator12);

        ItemClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/graphs_46879.png"))); // NOI18N
        ItemClientes.setText("REPORTE DE CLIENTES");
        ItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemClientesActionPerformed(evt);
            }
        });
        jMenu6.add(ItemClientes);
        jMenu6.add(jSeparator13);

        ItemProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/graphs_46879.png"))); // NOI18N
        ItemProveedores.setText("REPORTE DE PROVEEDORES");
        ItemProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemProveedoresActionPerformed(evt);
            }
        });
        jMenu6.add(ItemProveedores);
        jMenu6.add(jSeparator14);

        ItemUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/graphs_46879.png"))); // NOI18N
        ItemUsuarios.setText("REPORTE DE USUARIOS");
        ItemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemUsuariosActionPerformed(evt);
            }
        });
        jMenu6.add(ItemUsuarios);
        jMenu6.add(jSeparator32);

        ItemUsuarios1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/graphs_46879.png"))); // NOI18N
        ItemUsuarios1.setText("REPORTE DE VENTAS");
        ItemUsuarios1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemUsuarios1ActionPerformed(evt);
            }
        });
        jMenu6.add(ItemUsuarios1);
        jMenu6.add(jSeparator37);

        jMenu12.setText("ETIQUETAS PERSONALIZADAS");

        jMenuItem17.setText("TEXTO Y LOGO");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem17);
        jMenu12.add(jSeparator38);

        jMenuItem26.setText("PARA OFERTAS");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem26);
        jMenu12.add(jSeparator39);

        jMenuItem25.setText("LOGO");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu12.add(jMenuItem25);

        jMenu6.add(jMenu12);

        jMenuBar2.add(jMenu6);

        jMenu9.setText("CONFIGURACIÓN");

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/data-connection_icon-icons.com_52841.png"))); // NOI18N
        jMenuItem15.setText("Conexión");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem15);
        jMenu9.add(jSeparator18);

        jMenuItem16.setIcon(new ImageIcon(ObtenerRutaImagen(1)));
        jMenuItem16.setText("EMPRESA");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem16);
        jMenu9.add(jSeparator19);

        jMenuItem18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/Settings_30027.png"))); // NOI18N
        jMenuItem18.setText("CONFIGURACIÓN DE SISTEMA");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem18);

        jMenuBar2.add(jMenu9);

        jMenu7.setText("CONTACTO");

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/programmermale_101089.png"))); // NOI18N
        jMenu8.setText("Programador");

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/whatsapp_icon-icons.com_62756.png"))); // NOI18N
        jMenuItem6.setText("Whatsapp");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem6);
        jMenu8.add(jSeparator15);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/4202110facebooklogosocialsocialmedia-115707_115594.png"))); // NOI18N
        jMenuItem7.setText("Facebook");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem7);
        jMenu8.add(jSeparator16);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/gmail_new_logo_icon_159149.png"))); // NOI18N
        jMenuItem5.setText("Email");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem5);

        jMenu7.add(jMenu8);
        jMenu7.add(jSeparator56);

        jMenuItem30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ACERCA_DE_32_PX.png"))); // NOI18N
        jMenuItem30.setText("ACERCA DE");
        jMenuItem30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem30ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem30);

        jMenuBar2.add(jMenu7);

        jMenu3.setText("SESIÓN");

        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/businessapplication_accept_ok_male_man_you_negocio_2311.png"))); // NOI18N
        jMenuItem2.setText("Ver Sesión");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);
        jMenu3.add(jSeparator8);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/4115235-exit-logout-sign-out_114030.png"))); // NOI18N
        jMenuItem3.setText("Cerrar Sesión");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator9);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/delete_40623.png"))); // NOI18N
        jMenuItem9.setText("Salir del Sistema");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);
        jMenu3.add(jSeparator7);

        jMenuItem19.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jMenuItem19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ESPERA_32PX.png"))); // NOI18N
        jMenuItem19.setText("MODO DE ESPERA");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem19);
        jMenu3.add(jSeparator6);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ActualiarNaranja.png"))); // NOI18N
        jMenuItem8.setText("ACTUALIZAR TOKEN");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar2.add(jMenu3);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(DESKTOP_PRINCIPAL)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(DESKTOP_PRINCIPAL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        MoverEntreSistema();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    
    public void MoverEntreSistema() {
        if (jLabel4.getText().equals("")) {
            VER_CAJAS CA = new VER_CAJAS(this, true, this);
            CA.setVisible(true);
            if (jLabel4.getText().equals("")) {
                
            } else {
                ABRIR_VENTANAS(P_O_S, true);
                P_O_S.IdVenta.requestFocus();
                P_O_S.ListarProductosPOS_NOMBRE();
            }
        } else {
            ABRIR_VENTANAS(P_O_S, true);
            P_O_S.IdVenta.requestFocus();
            P_O_S.ListarProductosPOS_NOMBRE();
        }
    }

    private void ItemProveedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemProveedores1ActionPerformed
        PROVEEDORES.ACTUALIZAR_PROVEEDOR();
        ABRIR_VENTANAS(P, true);
    }//GEN-LAST:event_ItemProveedores1ActionPerformed
 
    private void ItemInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemInventarioActionPerformed
        ABRIR_VENTANAS(I, true);
        I.CARGAR_REGISTROS();
    }//GEN-LAST:event_ItemInventarioActionPerformed

    private void ItemUsuariosSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUsuariosSistemaActionPerformed
        UI.Usuarios();
        ABRIR_VENTANAS(UI, true);
    }//GEN-LAST:event_ItemUsuariosSistemaActionPerformed

    private void ItemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUsuariosActionPerformed
        ReporteUsuarios();
    }//GEN-LAST:event_ItemUsuariosActionPerformed

    private void ItemVentasGeneralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemVentasGeneralesActionPerformed
        if(jLabel4.getText().equals("")) {
            VER_CAJAS CA = new VER_CAJAS(this, true, this);
            CA.setVisible(true);
            if (jLabel4.getText().equals("")) {
            } else {
                ABRIR_VENTANAS(MG, true);
                MG.CARGAR_REGISTROS();
            }
        } else {
            ABRIR_VENTANAS(MG, true);
        MG.CARGAR_REGISTROS();
        }
    }//GEN-LAST:event_ItemVentasGeneralesActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
                ReporteProductos();  
        
        
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        int Seleccion = JOptionPane.showConfirmDialog(null, "(SI)REPORTE PARA POS\n(NO)REPORTE PARA CERTIFICADOR");
        try {
            Excel Ex = new Excel();
            if (Seleccion == 0) {
                Ex.reporte();
            } else {
                Ex.reporteACertificador();
            }

        } catch (URISyntaxException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void ItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemClientesActionPerformed
        ReporteClientes();
    }//GEN-LAST:event_ItemClientesActionPerformed

    private void ItemProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemProveedoresActionPerformed
        ReporteProveedores();
    }//GEN-LAST:event_ItemProveedoresActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if(P_O_S.TablaVentas.getRowCount() > 0){
            JOptionPane.showMessageDialog(null, "¡TIENE UNA VENTA PENDIENTE, TERMINELA O CANCELELA!");
            
        }else{
            if(VentanaCategoria==true){
        Cat.toFront(); 
        }else if(VentanaUbicaciones==true){
            Ub.toFront();
        }else if(P_O_S.VentanaBuscarProducto==true){
            P_O_S.BP.toFront();
        }else if(VentanaConfiguraciones==true){
            conf.toFront();
        }else if(VentanaAdministracionDeProductos==true){
            JOptionPane.showMessageDialog(null, "¡TIENE UNA VENTANA DE ADMINISTRACIÓN ABIERTA, TERMÍNELA O CIÉRRELA!");
            AdminProduct.toFront();
        }else if(VENTANA_ADMINISTRACION_DE_USUARIOS==true){
            ADMIN_USUARIOS.toFront();
        }else{
            AlCerrarSesion();
            JOptionPane.showMessageDialog(null, "¡FUE UN GUSTO VERTE "+PARAMETROS_USUARIOS.NOMBRE_USUARIO.toUpperCase() +" ! :)", "SISTEMA EL AMIGO", JOptionPane.INFORMATION_MESSAGE);
           LOGIN Aldo = new LOGIN();
        Aldo.setVisible(true);
        this.dispose(); 
        }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        S.CARGAR_DATOS_USUARIO();
        ABRIR_VENTANAS(S, true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

         
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if(VentanaCategoria==false){
            VentanaCategoria=true;
        Cat= new CategoriaVista(this, true, 0, this);
        CategoriaVista.ActualizarTablaCategorias(false);     
        }else{
            Cat.toFront();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void ItemVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemVentasActionPerformed
        if (jLabel4.getText().equals("")) {
            VER_CAJAS CA = new VER_CAJAS(this, true, this);
            CA.setVisible(true);
            if (jLabel4.getText().equals("")) {
            } else {
                ABRIR_VENTANAS(MD, true);
                MD.CARGAR_REGISTROS();
            }
        } else {
            ABRIR_VENTANAS(MD, true);
            MD.CARGAR_REGISTROS();
        }
    }//GEN-LAST:event_ItemVentasActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        ConfirmarSalida();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        WhatsApp();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        Facebook();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        Email();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        D.CARGAR_DASHBOARD();
        ABRIR_VENTANAS(D, true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
       /* tablamadre.setSelectedIndex(9);
        Movimientos.Tabla(tablamadre, tablamadre.getSelectedIndex());*/
        ABRIR_VENTANAS(C, true);
        C.CARGAR_CLIENTES();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        ABRIR_VENTANAS(E, true);
        E.CargarDatosEmpresa();
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        Conexion BasesdeDatos= new Conexion();
        BasesdeDatos.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        if(VentanaConfiguraciones==false){
            VentanaConfiguraciones=true;
            conf= new Config(this);
          conf.setVisible(true);
        conf.setLocationRelativeTo(null);  
        }else{
            conf.toFront();
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    
    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        ImportarExcel IE= new ImportarExcel();
        IE.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
       /* int TotalDeFila= tablaProductos.getRowCount();
        if(TotalDeFila<= 300){
        ReporteProductosCatalago();    
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERROR AL GENERAR CATÁLOGO", "NO PUEDE GENERAR UN REPORTE DE CATÁLOGO CON MAS DE 50 REGISTRO :(",DesktopNotify.ERROR, 14000L);
        }*/
        
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        if(jLabel4.getText().equals("")){
            JOptionPane.showMessageDialog(this, "SI NO EXISTE, PIDELE AL ADMINISTRADOR QUE APERTURA UNA :)", "NECESITAS SELECCIONAR UNA CAJA", JOptionPane.WARNING_MESSAGE);
            VER_CAJAS CA= new VER_CAJAS(this, true, this);
            CA.setVisible(true);
        }else{
            ABRIR_VENTANAS(CG, true);
        CG.ActualizarTablaEstado();
        }
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void ItemUsuarios1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUsuarios1ActionPerformed
        ReporteVentas();
    }//GEN-LAST:event_ItemUsuarios1ActionPerformed

    private void CheckBoxModoStockCeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxModoStockCeroActionPerformed
        ConfigVentas.RecordarModoReinventario(CheckBoxModoStockCero);
    }//GEN-LAST:event_CheckBoxModoStockCeroActionPerformed

    private void CheckPermitirProductosPersonalizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckPermitirProductosPersonalizadosActionPerformed
        ConfigVentas.RecordarProductosPersonalizados(CheckPermitirProductosPersonalizados);
    }//GEN-LAST:event_CheckPermitirProductosPersonalizadosActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        Promociones promo= new Promociones();
        String Detalle = JOptionPane.showInputDialog("INGRESE EL TEXTO A MOSTRAR EN LA ETIQUETA");
        promo.LogoYNombre(Detalle, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        Promociones promo= new Promociones();
        promo.Logo1();
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        Promociones promo= new Promociones();
        promo.Ofertas();
    }//GEN-LAST:event_jMenuItem26ActionPerformed
        //BusquedaProductosEmergente BPVE= new BusquedaProductosEmergente(this, true);
        
    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        if(VentanaUbicaciones==false){
            VentanaUbicaciones=true;
            Ub= new Ubicaciones(this, true, 0, this);
        }else{
            Ub.toFront();
        }
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        
    }//GEN-LAST:event_jMenuItem24ActionPerformed
 
    private void CheckBoxImpresionRapidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxImpresionRapidaActionPerformed
        ConfigVentas.RecordarImpresionRapida(CheckBoxImpresionRapida);
    }//GEN-LAST:event_CheckBoxImpresionRapidaActionPerformed

    private void jMenuItem28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem28ActionPerformed
        ABRIR_VENTANAS(INTERNAL_CAJA_P, true);
        INTERNAL_CAJA_P.ACTUALIZAR_CAJAS();
    }//GEN-LAST:event_jMenuItem28ActionPerformed

    private void jMenuItem30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem30ActionPerformed
        CONTROL_DE_ACTUALIZACIÓNES Control= new CONTROL_DE_ACTUALIZACIÓNES(this, true);
        Control.setVisible(true);
    }//GEN-LAST:event_jMenuItem30ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        ABRIR_VENTANAS(M_E, true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem31ActionPerformed
        ABRIR_VENTANAS(K, true);
        I.CARGAR_REGISTROS();
        K.REFRESCAR_KARDEX();
    }//GEN-LAST:event_jMenuItem31ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        D.CARGAR_DASHBOARD();
        ABRIR_VENTANAS(D, true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MoverEntreSistema();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jLabel4.getText().equals("")) {
            VER_CAJAS CA = new VER_CAJAS(this, true, this);
            CA.setVisible(true);
            if (jLabel4.getText().equals("")) {
            } else {
                ABRIR_VENTANAS(MD, true);
                MD.CARGAR_REGISTROS();
            }
        } else {
            ABRIR_VENTANAS(MD, true);
            MD.CARGAR_REGISTROS();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(jLabel4.getText().equals("")){
            VER_CAJAS CA= new VER_CAJAS(this, true, this);
            CA.setVisible(true);
        }else{
            ABRIR_VENTANAS(CG, true);
        CG.ActualizarTablaEstado();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        if (jLabel4.getText().equals("")) {
            VER_CAJAS CA = new VER_CAJAS(this, true, this);
            CA.setVisible(true);
            if (jLabel4.getText().equals("")) {
            } else {
                GASTOS_NUEVO G_N= new GASTOS_NUEVO(this, true);
                G_N.setVisible(true);
            }
        } else {
            GASTOS_NUEVO G_N= new GASTOS_NUEVO(this, true);
            G_N.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (jLabel4.getText().equals("")) {
            VER_CAJAS CA = new VER_CAJAS(this, true, this);
            CA.setVisible(true);
            if (jLabel4.getText().equals("")) {
            } else {
                GASTOS_NUEVO G_N= new GASTOS_NUEVO(this, true);
                G_N.setVisible(true);
            }
        } else {
            GASTOS_NUEVO G_N= new GASTOS_NUEVO(this, true);
            G_N.setVisible(true);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        ABRIR_VENTANAS(INTERNAL_CAJA_P, true);
        INTERNAL_CAJA_P.ACTUALIZAR_CAJAS();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                AVISOS AV = new AVISOS("CARGANDO DATOS", "POR FAVOR, ESPERE");
                AV.setVisible(true);

                Runnable runnable2 = new Runnable() {
                    @Override
                    public void run() {
                        ObtenerToken OT = new ObtenerToken();
                        OT.ObtenerToken();
                        AV.dispose();
                    }
                };
                Thread hilo2 = new Thread(runnable2);
                hilo2.start();

            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    public static void CAMBIAR_ICONO(){
        ImageIcon icono = new ImageIcon(ObtenerRutaImagen(1)); // carga el icono desde archivo

        // crea una imagen en blanco del mismo tamaño que el icono
        BufferedImage imagen = new BufferedImage(icono.getIconWidth(), icono.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        // dibuja el icono sobre la imagen
        Graphics2D g = imagen.createGraphics();
        icono.paintIcon(null, g, 0, 0);
        g.dispose();

        // obtiene la imagen modificada con el nuevo color
        BufferedImage imagenModificada = cambiarColorImagen(imagen, Color.GREEN); // aquí se cambia el color a rojo

        // crea un nuevo icono a partir de la imagen modificada
        ImageIcon nuevoIcono = new ImageIcon(imagenModificada);

        // muestra el nuevo icono
        // por ejemplo, en un JLabel:
        JLabel etiqueta = new JLabel(nuevoIcono);
        JOptionPane.showMessageDialog(null, "AQUÍ HAGO UNA PRUEBA DE CAMBIAR EL COLOR DEL ICONO", "MUESTRA DE ICONO", JOptionPane.OK_CANCEL_OPTION, nuevoIcono);
    }
    
    private static BufferedImage cambiarColorImagen(BufferedImage imagen, Color nuevoColor) {
        // crea una copia de la imagen
        BufferedImage nuevaImagen = new BufferedImage(imagen.getWidth(), imagen.getHeight(), BufferedImage.TYPE_INT_ARGB);
        nuevaImagen.createGraphics().drawImage(imagen, 0, 0, null);

        // obtiene los componentes RGB del nuevo color
        int r = nuevoColor.getRed();
        int g = nuevoColor.getGreen();
        int b = nuevoColor.getBlue();

        // recorre los píxeles de la imagen y modifica su color
        for (int x = 0; x < nuevaImagen.getWidth(); x++) {
            for (int y = 0; y < nuevaImagen.getHeight(); y++) {
                int colorActual = nuevaImagen.getRGB(x, y);
                int alpha = (colorActual >> 24) & 0xff;
                int red = (colorActual >> 16) & 0xff;
                int green = (colorActual >> 8) & 0xff;
                int blue = colorActual & 0xff;
                if (red == 0 && green == 0 && blue == 0) { // cambia solo los píxeles negros
                    red = r;
                    green = g;
                    blue = b;
                    int nuevoColorRGB = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    nuevaImagen.setRGB(x, y, nuevoColorRGB);
                }
            }
        }

        return nuevaImagen;
    }
    
    //Redireccionamiento a facebook
    public void Facebook() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("https://www.facebook.com/Masaldotergt/");
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                }
            }
        }
    }
    
    public void WhatsApp() {
        String Tel="50240805837";
        String Numero="https://api.whatsapp.com/send?phone="+Tel+"&text=Hola,%20Necesito%20De%20Tú%20Ayuda!.";
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI(Numero);
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                }
            }
        }
    }
    
    public void Email() {
        if (java.awt.Desktop.isDesktopSupported()) {
            java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
            if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                try {
                    java.net.URI uri = new java.net.URI("https://mail.google.com/mail/u/0/#inbox?compose=DmwnWrRvwMBbCFDkcdgrNzgrtGnZhkGsLsxJJscDTDTtPGPgQzgDGJxMNzqFPBxMbtcXNhmzcTKG");
                    desktop.browse(uri);
                } catch (URISyntaxException | IOException ex) {
                }
            }
        }
    }

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                         /* try {
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }*/
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem CheckBoxImpresionRapida;
    private static javax.swing.JCheckBoxMenuItem CheckBoxModoStockCero;
    private static javax.swing.JCheckBoxMenuItem CheckPermitirProductosPersonalizados;
    public static javax.swing.JDesktopPane DESKTOP_PRINCIPAL;
    private javax.swing.JPopupMenu Filtro;
    private javax.swing.JMenuItem ItemClientes;
    private javax.swing.JMenuItem ItemInventario;
    private javax.swing.JMenu ItemProductos;
    private javax.swing.JMenuItem ItemProveedores;
    private javax.swing.JMenuItem ItemProveedores1;
    private javax.swing.JMenuItem ItemUsuarios;
    private javax.swing.JMenuItem ItemUsuarios1;
    private javax.swing.JMenuItem ItemUsuariosSistema;
    private javax.swing.JMenuItem ItemVentas;
    private javax.swing.JMenuItem ItemVentasGenerales;
    private javax.swing.JCheckBoxMenuItem filtrocategorias;
    private javax.swing.JCheckBoxMenuItem filtroid;
    private javax.swing.JCheckBoxMenuItem filtroproveedores;
    private javax.swing.JCheckBoxMenuItem filtrousuario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu14;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem28;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem30;
    private javax.swing.JMenuItem jMenuItem31;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator14;
    private javax.swing.JPopupMenu.Separator jSeparator15;
    private javax.swing.JPopupMenu.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator18;
    private javax.swing.JPopupMenu.Separator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JPopupMenu.Separator jSeparator22;
    private javax.swing.JPopupMenu.Separator jSeparator23;
    private javax.swing.JPopupMenu.Separator jSeparator24;
    private javax.swing.JPopupMenu.Separator jSeparator25;
    private javax.swing.JPopupMenu.Separator jSeparator26;
    private javax.swing.JPopupMenu.Separator jSeparator27;
    private javax.swing.JPopupMenu.Separator jSeparator28;
    private javax.swing.JPopupMenu.Separator jSeparator29;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JPopupMenu.Separator jSeparator31;
    private javax.swing.JPopupMenu.Separator jSeparator32;
    private javax.swing.JPopupMenu.Separator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JPopupMenu.Separator jSeparator36;
    private javax.swing.JPopupMenu.Separator jSeparator37;
    private javax.swing.JPopupMenu.Separator jSeparator38;
    private javax.swing.JPopupMenu.Separator jSeparator39;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator43;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator56;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblHora_Principal;
    private javax.swing.JLabel lblNombre_BD_Principal;
    private javax.swing.JLabel lblURL_Principal;
    // End of variables declaration//GEN-END:variables

    //reporte de Productos
    public void ReporteProductos() {
        /*DatosEmpresa datos= new DatosEmpresa();
        datos.setUsuario(PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
        datos.setNombreEmpresa(PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
        datos.setNit(PARAMETROS_EMPRESA.NIT_EMPRESA);
        datos.setDireccion(PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
        datos.setTel(PARAMETROS_EMPRESA.TEL_EMPRESA);
        datos.setLugar(PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
        datos.setEslogan(PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
        documentos.Productos(datos, tablaProductos, false);*/
        //Re.ReporteProductos();
    }
    
    public void ReporteProductosCatalago() {
       /* DatosEmpresa datos= new DatosEmpresa();
        datos.setUsuario(PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
        datos.setNombreEmpresa(PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
        datos.setNit(PARAMETROS_EMPRESA.NIT_EMPRESA);
        datos.setDireccion(PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
        datos.setTel(PARAMETROS_EMPRESA.TEL_EMPRESA);
        datos.setLugar(PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
        datos.setEslogan(PARAMETROS_EMPRESA.ESLOGAN_EMPRESA);
        documentos.Productos(datos, tablaProductos, true);
        //Re.ReporteProductos();*/
    }

    public void ReporteVentas() {
        Re.ReporteVentas();
    }

    public void ReporteClientes() {
        Re.ReporteClientes();
    }

    public void ReporteProveedores() {
        Re.ReporteProveedores();
    }

    public void ReporteUsuarios() {
        Re.ReporteUsuarios();
    }
    
    /*public void EnviarParametrosNotaDeCreditoXML(){
        if(CajaToken.getText().equals("")){
            GenerarToken();
        }else{
        Double CalcularImpuestoMontoGravable;
        Double CalcularImpuestoMontoImpuesto;
        Double Total=0.00;
        XMLNotaDeCredito GenerarXMLNotaDeCredito= new XMLNotaDeCredito();
        EnviarDatosNotaDeCredito EDNDC= new EnviarDatosNotaDeCredito();
        CertificarNotaDeCredito CNota= new CertificarNotaDeCredito();
        RespuestaCertificacion CFACTMODEL= new RespuestaCertificacion();
        
        EDNDC.setFechaHoraEmision(Fecha()+"T"+Hora());
        EDNDC.setCodigoMoneda("GTQ");
        EDNDC.setTipoDocumento("FACT");
        
        EDNDC.setNITEmisor(NitEmpresa.getText());
        EDNDC.setNombreEmisor(CajaPropietario.getText());
        EDNDC.setCodigoEstablecimiento(CajaCodigoEstablecimientoEmpresa.getText());
        EDNDC.setNombreComercial(NombreEmpresa.getText());
        EDNDC.setAfiliacionIVA(CajaAfilicacionEmpresa.getText());
        EDNDC.setDireccionEmisor(DireccionEmpresa.getText());
        EDNDC.setCodigoPostalEmisor(CajaCodigoPostalEmpresa.getText());
        EDNDC.setMunicipioEmisor(CajaMunicipioEmpresa.getText());
        EDNDC.setDepartamentoEmisor(CajaDepartamentoEmpresa.getText());
        EDNDC.setPaisEmisor(CajaPaisEmpresa.getText());
        
        EDNDC.setNombreReceptor(nombre.getText());
        EDNDC.setIDReceptor(nit.getText());
        EDNDC.setDireccionReceptor(direccion.getText());
        EDNDC.setCodigoPostalReceptor(CodigoPostalCliente.getText());
        EDNDC.setMunicipioReceptor(MunicipioCliente.getText());
        EDNDC.setDepartamentoReceptor(DepartamentoCliente.getText());
        EDNDC.setPaisReceptor(SiglaPais.getText());
        
        EDNDC.setTipoFrase("1");
        EDNDC.setCodigoEscenario("1");
        
        
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
        EDNDC.setTotalDeProductos(Integer.parseInt(TotalTipoDeProductosPOS.getText()));
        EDNDC.setNumeroLineaProducto(String.valueOf(i+1));
        EDNDC.setBienOServicioProducto("B");   
        EDNDC.setCantidadProducto(TablaVentas.getValueAt(i, 2).toString());
        EDNDC.setUnidadMedidaProducto("UN");
        EDNDC.setDescripcionProducto(TablaVentas.getValueAt(i, 1).toString());
        EDNDC.setPrecioUnitarioProducto(TablaVentas.getValueAt(i, 3).toString());
        EDNDC.setPrecioProducto(TablaVentas.getValueAt(i,3).toString());
        EDNDC.setDescuentoProducto("0.00");
        EDNDC.setTotalProducto(TablaVentas.getValueAt(i, 4).toString());
        
        CalcularImpuestoMontoGravable= Double.parseDouble(TablaVentas.getValueAt(i, 3).toString()) / 1.12;
        CalcularImpuestoMontoImpuesto= Double.parseDouble(TablaVentas.getValueAt(i, 3).toString()) - CalcularImpuestoMontoGravable;
        Total+= CalcularImpuestoMontoImpuesto;
        EDNDC.setNombreCortoImpuesto("IVA");
        EDNDC.setCodigoUnidadGravableImpuesto("1");
        EDNDC.setMontoGravableImpuesto(String.format("%.4f", CalcularImpuestoMontoGravable));
        EDNDC.setMontoImpuesto(String.format("%.4f", CalcularImpuestoMontoImpuesto));
        
        }
        
        EDNDC.setNombreCortoTotales("IVA");
        EDNDC.setTotalMontoImpuestoTotales(String.format("%.4f", Total));
        EDNDC.setGranTotalTotales(labeltotalenfacturacion.getText());
        
        EDNDC.setNumeroAutorizacionDocumentoOrigen("");
        EDNDC.setFechaEmisionDocumentoOrigen("");
        EDNDC.setMotivoAjuste(Articulos);
        EDNDC.setNumeroDocumentoOrigen(strDateFormat);
        EDNDC.setSerieDocumentoOrigen(strDateFormat);
        
        Boolean Resultado= GenerarXMLNotaDeCredito.GenerarXMLNotaDeCredito(EDNDC, TablaVentas);
        if(Resultado==true){
        DatosUsuario DU = new DatosUsuario();
        DU.setNit(NitEmpresa.getText());
        DU.setUsuario(CajaSesionUsuarioCertificadoraEmpresa.getText());
        DU.setContrasenia(CajaContraseniaCertificadoraEmpresa.getText());
        
        CFACTMODEL= CNota.CertificarDTE(DU, CajaToken.getText());
        CajaFechaAutorizacion.setText(CFACTMODEL.getFecha_de_certificacion());
        CajaNumeroAutorizacion.setText(CFACTMODEL.getAutorizacion());
        CajaSerieCertificacion.setText(CFACTMODEL.getSerie());
        CajaNumeroDocumento.setText(CFACTMODEL.getNUMERO());
        if(CFACTMODEL.isValidado()== true){
        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
        DesktopNotify.showDesktopMessage("ESTADO DE CERTIFICACIÓN", CFACTMODEL.getEstadoCertificacion(), DesktopNotify.SUCCESS, 10000L);
        Imprimir();   
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
        DesktopNotify.showDesktopMessage("ESTADO DE CERTIFICACIÓN", CFACTMODEL.getEstadoCertificacion(), DesktopNotify.INFORMATION, 10000L);
        }
        
        }
        }
    }*/
    //[255,230,205]
    
    public static void ABRIR_VENTANAS(JInternalFrame VENTANA, Boolean CERRAR_VENTANA){
        if(!VENTANA.isVisible()){
            try {
                DESKTOP_PRINCIPAL.removeAll();
                DESKTOP_PRINCIPAL.removeAll();
                DESKTOP_PRINCIPAL.add(VENTANA);
                DESKTOP_PRINCIPAL.updateUI();
                VENTANA.setVisible(true);
                VENTANA.setMaximum(true);
                VENTANA.toFront();
               // VENTANA.updateUI();
            } catch (PropertyVetoException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
                DESKTOP_PRINCIPAL.removeAll();
                DESKTOP_PRINCIPAL.removeAll();
                DESKTOP_PRINCIPAL.add(VENTANA);
                VENTANA.toFront();
                //VENTANA.updateUI();
            }  
        
        
    }
}
    
