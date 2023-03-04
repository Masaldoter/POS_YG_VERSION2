/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.ADMINISTRACION.INVENTARIO;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.INSERTAR_IMAGEN_TABLA;
import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import CodigosDeBarras.CodigosDeBarras;
import CodigosDeBarras.ParametrosCodigosDeBarras;
import Controlador.ProductosDao;
import Controlador.ProveedoresDao;
import Modelo.Categoria;
import Modelo.Combo;
import Modelo.ComboCategoria;
import Modelo.Productos;
import Modelo.Proveedor;
import Modelo.Ubicacion;
import Tablas.Actualizartablas;
import Tablas.RenderTablasJLabel;
import Vista.POS.POS;
import Vista.Principal;
import static Vista.Principal.ABRIR_VENTANAS;
import static Vista.Principal.VentanaAdministracionDeProductos;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aldoy
 */
public class INVENTARIO extends javax.swing.JInternalFrame {
    TOTALES T = new TOTALES();
    CodigosDeBarras CodigosBarras = new CodigosDeBarras();
    ParametrosCodigosDeBarras ParametrosCodigosBarras = new ParametrosCodigosDeBarras();
    static ProductosDao proDao;
    private static int TIPO_BUSQUEDA_FINAL_INVENTARIO;
    POS pos;
    Principal principal;
    public INVENTARIO(POS pos, Principal principal) {
        initComponents();
        this.pos = pos;
        this.principal=principal;
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        LIMPIAR_CAJAS_PRODUCTOS();
        DINAMICA_BOTONES_PRODUCTOS();
        jCheckBox2.setEnabled(false);
    }
    
    public void CARGAR_REGISTROS(){
       // new java.util.Timer().schedule(new java.util.TimerTask() {
       //  @Override
       //  public void run() {
         VaciarYllenarCategoria();
         VaciarYllenarProveedor();
         VaciarYllenarUbicacion();
         REFRESCAR_INVENTARIO();
         //}},0);
    }
    
    public static void REFRESCAR_INVENTARIO(){
       if(TIPO_BUSQUEDA_FINAL_INVENTARIO>0){
           if(TIPO_BUSQUEDA_FINAL_INVENTARIO!=20){
            ConsultasVarias(TIPO_BUSQUEDA_FINAL_INVENTARIO);  
        }else{
            ConsultaPorFechas();  
           }
        }else{
            ActualizarTablaCentral();
        } 
    }
    
    public void LIMPIAR_CAJAS_PRODUCTOS() {
        lblFiltroDeBusqueda.setText("SIN BUSQUEDA, ORGANIZADO POR: "+FiltroOrden.getSelectedItem());
        TIPO_BUSQUEDA_FINAL_INVENTARIO=0;
        Id.setText(null);
        Nombreproducto.setText(null);
        Cantidad.setText(null);
        comboubicacion.setSelectedItem(null);
        ProveedoresCombo.setSelectedItem(null);
        ComboCategorias.setSelectedItem(null);
        idbodega.setText(null);
        FechaIncioInventario.setDate(null);
        FechaFinalInventario.setDate(null);
        Id.requestFocus();
        if(idbodega.getText() != null){
           Editarp.setEnabled(false);
            Agregarp.setEnabled(true);
        }else{
            Editarp.setEnabled(true);
        }
    }
    
    public void DINAMICA_BOTONES_PRODUCTOS(){
        if(lbl_Estado_Registro_Productos.getText().equals("ACTIVO")){
            rSButtonHover1.setBackground(new Color(0,112,192));
            rSButtonHover2.setBackground(new Color(204,204,204));
            rSButtonHover3.setBackground(new Color(204,204,204));
            
            rSButtonHover1.setColorText(Color.WHITE);
            rSButtonHover2.setColorText(Color.BLACK);
            rSButtonHover3.setColorText(Color.BLACK);
        }else if(lbl_Estado_Registro_Productos.getText().equals("INACTIVO")){
            rSButtonHover2.setBackground(new Color(0,112,192));
            rSButtonHover1.setBackground(new Color(204,204,204));
            rSButtonHover3.setBackground(new Color(204,204,204));
            
            rSButtonHover2.setColorText(Color.WHITE);
            rSButtonHover1.setColorText(Color.BLACK);
            rSButtonHover3.setColorText(Color.BLACK);
        }else if(lbl_Estado_Registro_Productos.getText().equals("TODOS")){
            rSButtonHover3.setBackground(new Color(0,112,192));
            rSButtonHover2.setBackground(new Color(204,204,204));
            rSButtonHover1.setBackground(new Color(204,204,204));
            
            rSButtonHover3.setColorText(Color.WHITE);
            rSButtonHover2.setColorText(Color.BLACK);
            rSButtonHover1.setColorText(Color.BLACK);
        }
    }
    
    public static void VaciarYllenarProveedor() {
        ProveedoresCombo.removeAllItems();
        ProveedoresDao proveDao = new ProveedoresDao();
        List<Proveedor> lista = proveDao.ListarProveedor();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdproveedores();
            String Nombre = lista.get(i).getProveedor();
            ProveedoresCombo.addItem(new Combo(id, Nombre).toString());
        }
    }
    
    private static void VaciarYllenarCategoria(){
        ComboCategorias.removeAllItems();
        List<Categoria> lista = proDao.ListarCategoria();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdCategoria();
            String Nombre = lista.get(i).getCategoria();
            ComboCategorias.addItem(new ComboCategoria(id, Nombre).toString());
        }
    }
    
    private static void VaciarYllenarSubCategoria() {
        proDao = new ProductosDao();
        ComboSubCategoria.removeAllItems();
        List<Modelo.SubCategoria> lista = proDao.ListarSubCategoria(proDao.ConsultaIdCategoria(ComboCategorias));
        for (int i = 0; i < lista.size(); i++) {
            ComboSubCategoria.addItem(lista.get(i).getNombreSubcategoria());
        }
    }
    
    private static void VaciarYllenarUbicacion() {
        comboubicacion.removeAllItems();
        List<Ubicacion> lista = proDao.ListarUbicacion();
        for (int i = 0; i < lista.size(); i++) {
            comboubicacion.addItem(lista.get(i).getNombreUbicacion());
        }
    }
    
    public static int ConsultarIdProveedor(JComboBox ComboCategoria){
        int ResultadoProveedor = 0;
        ResultadoProveedor = proDao.ConsultaIdProveedor(ComboCategoria);
      return ResultadoProveedor;
    }
    
    public static int ConsultarIdCategoria(JComboBox ComboCategoria){
        int ResultadoCategoria = 0;
                ResultadoCategoria = proDao.ConsultaIdCategoria(ComboCategoria);
      return ResultadoCategoria;
    }
    
    public static int ConsultarIdUbicacion(JComboBox ComboUbicacion){
        int ResultadoUbicacion = 0;
      ResultadoUbicacion = Integer.parseInt(String.valueOf(proDao.ConsultaIdUbicacion(ComboUbicacion)));
      return ResultadoUbicacion;
    }
    
    public static int ConsultarIdSubCategoria(JComboBox ComboSubCategoria){
        int ResultadoSubCategoria = 0;
      ResultadoSubCategoria = Integer.parseInt(String.valueOf(proDao.ConsultaIdSubCategoria(ConsultarIdCategoria(ComboCategorias), ComboSubCategoria)));
      return ResultadoSubCategoria;
    }
    
    public static void ActualizarTablaCentral() {
        tablaProductos.setDefaultRenderer(Object.class, new RenderTablasJLabel());
        Actualizartablas tablas = new Actualizartablas();
        LIMPIAR_TABLA(tablaProductos);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) tablaProductos.getModel();
        List<Productos> ListarPr = tablas.ListarProductosSistema(FiltroOrden, lbl_Estado_Registro_Productos.getText());
        Object[] ob = new Object[17];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getCodigoBarras();
            ob[1] = ListarPr.get(i).getNombre();
            ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getCosto();
            ob[4] = ListarPr.get(i).getCodigoLetras();
            ob[5] = ListarPr.get(i).getPublico();
            ob[6] = ListarPr.get(i).getPrecioEs();
            ob[7] = ListarPr.get(i).getPrecioRe();
            ob[8] = ListarPr.get(i).getCategoriaNombre();
            ob[9] = ListarPr.get(i).getProveedorNombre();
            ob[10] ="<html>"+ListarPr.get(i).getFechaingreso()+" POR: "+ListarPr.get(i).getUsuarioIngresoLetras()+"</html>";
            ob[11] ="<html>"+ListarPr.get(i).getFechamodificacion()+" POR: "+ListarPr.get(i).getUsuarioModificoLetras()+"</html>";
            ob[12] = ListarPr.get(i).getEstado_Producto();
            ob[13] = ListarPr.get(i).getRuta();
            ob[14] = ListarPr.get(i).getRuta();
            ob[15] = ListarPr.get(i).getBotoneditar();
            ob[16] = ListarPr.get(i).getBotoneliminar();
            modelo.addRow(ob);
            //[255,230,205]
        }
        INSERTAR_IMAGEN_TABLA(tablaProductos, 13, 14, 75, 85);
        tablaProductos.setModel(modelo);
    }

    public static void ConsultasVarias(int TipoBusqueda) {
        List<Productos> ListarPr = null;
        tablaProductos.setDefaultRenderer(Object.class, new RenderTablasJLabel());
        Actualizartablas tablas = new Actualizartablas();
        LIMPIAR_TABLA(tablaProductos);
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo = (DefaultTableModel) tablaProductos.getModel();
        switch (TipoBusqueda) {
            case 1:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(Id.getText(), "", lbl_Estado_Registro_Productos.getText(), 1, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("CODIGO DE BARRAS");
                break;
            case 2:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(Nombreproducto.getText(), "", lbl_Estado_Registro_Productos.getText(), 2, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("NOMBRE O DESCRIPCIÓN");
                break;
            case 3:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdProveedor(ProveedoresCombo)), "", lbl_Estado_Registro_Productos.getText(),3, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("PROVEEDORES");
                break;
            case 4:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(Cantidad.getText(), "", lbl_Estado_Registro_Productos.getText(), 4, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("STOCK");
                break;
            case 5:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdProveedor(ProveedoresCombo)), Cantidad.getText(), lbl_Estado_Registro_Productos.getText(), 5, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("PROVEEDOR --> STOCK");
                break;    
            case 6:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdCategoria(ComboCategorias)), "", lbl_Estado_Registro_Productos.getText(), 6, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("CATEGORIA");
                break;
            case 7:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdCategoria(ComboCategorias)), String.valueOf(ConsultarIdSubCategoria(ComboSubCategoria)), lbl_Estado_Registro_Productos.getText(), 7, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("CATEGORIA --> SUBCATEGORIA");
                break;
            case 8:
                ListarPr = tablas.ConsultaEnTodosLosAmbitos(String.valueOf(ConsultarIdUbicacion(comboubicacion)), String.valueOf(ConsultarIdUbicacion(comboubicacion)), lbl_Estado_Registro_Productos.getText(), 8, jCheckBox2.isSelected());
                lblFiltroDeBusqueda.setText("UBICACION");
                break;
            default:
                break;
        }
        
        Object[] ob = new Object[17];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getCodigoBarras();
            ob[1] = ListarPr.get(i).getNombre();
            ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getCosto();
            ob[4] = ListarPr.get(i).getCodigoLetras();
            ob[5] = ListarPr.get(i).getPublico();
            ob[6] = ListarPr.get(i).getPrecioEs();
            ob[7] = ListarPr.get(i).getPrecioRe();
            ob[8] = ListarPr.get(i).getCategoriaNombre();
            ob[9] = ListarPr.get(i).getProveedorNombre();
            ob[10] ="<html>"+ListarPr.get(i).getFechaingreso()+" POR: "+ListarPr.get(i).getUsuarioIngresoLetras()+"</html>";
            ob[11] ="<html>"+ListarPr.get(i).getFechamodificacion()+" POR: "+ListarPr.get(i).getUsuarioModificoLetras()+"</html>";
            ob[12] = ListarPr.get(i).getEstado_Producto();
            ob[13] = ListarPr.get(i).getRuta();
            ob[14] = ListarPr.get(i).getRuta();
            ob[15] = ListarPr.get(i).getBotoneditar();
            ob[16] = ListarPr.get(i).getBotoneliminar();
            modelo.addRow(ob);
            //[255,230,205]
        }
        INSERTAR_IMAGEN_TABLA(tablaProductos, 13, 14, 75, 85);
        tablaProductos.setModel(modelo);
    }
    
    public static void ConsultaPorFechas() {
        lblFiltroDeBusqueda.setText("FECHAS");
        tablaProductos.setDefaultRenderer(Object.class, new RenderTablasJLabel());
        Actualizartablas tablas = new Actualizartablas();
        List<Productos> ListarPr;
        LIMPIAR_TABLA(tablaProductos);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) tablaProductos.getModel();
        if(jCheckBox1.isSelected()){
            ListarPr = tablas.ConsultaPorFechas(FechaIncioInventario, FechaFinalInventario,lbl_Estado_Registro_Productos.getText(), 2);
        }else{
            ListarPr = tablas.ConsultaPorFechas(FechaIncioInventario, FechaFinalInventario,lbl_Estado_Registro_Productos.getText(), 1);
        }
        Object[] ob = new Object[17];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getCodigoBarras();
            ob[1] = ListarPr.get(i).getNombre();
            ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getCosto();
            ob[4] = ListarPr.get(i).getCodigoLetras();
            ob[5] = ListarPr.get(i).getPublico();
            ob[6] = ListarPr.get(i).getPrecioEs();
            ob[7] = ListarPr.get(i).getPrecioRe();
            ob[8] = ListarPr.get(i).getCategoriaNombre();
            ob[9] = ListarPr.get(i).getProveedorNombre();
            ob[10] ="<html>"+ListarPr.get(i).getFechaingreso()+" POR: "+ListarPr.get(i).getUsuarioIngresoLetras()+"</html>";
            ob[11] = "<html>"+ListarPr.get(i).getFechamodificacion()+" POR: "+ListarPr.get(i).getUsuarioModificoLetras()+"</html>";
            ob[12] = ListarPr.get(i).getEstado_Producto();
            ob[13] = ListarPr.get(i).getRuta();
            ob[14] = ListarPr.get(i).getRuta();
            ob[15] = ListarPr.get(i).getBotoneditar();
            ob[16] = ListarPr.get(i).getBotoneliminar();
            modelo.addRow(ob);
            //[255,230,205]
        }
        INSERTAR_IMAGEN_TABLA(tablaProductos, 13, 14, 75, 85);
        tablaProductos.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OpcionesAdmin = new javax.swing.JPopupMenu();
        codigo = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JPopupMenu.Separator();
        totales = new javax.swing.JMenuItem();
        TablaSistema = new javax.swing.JPopupMenu();
        info = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JPopupMenu.Separator();
        barras = new javax.swing.JMenuItem();
        Productos = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        Nombreproducto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        Id = new javax.swing.JTextField();
        lblFiltroDeBusqueda = new javax.swing.JLabel();
        lbl_Estado_Registro_Productos = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jPanel35 = new javax.swing.JPanel();
        Actualizar = new javax.swing.JButton();
        Agregarp = new javax.swing.JButton();
        Editarp = new javax.swing.JButton();
        Nuevop = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel61 = new javax.swing.JPanel();
        ProveedoresCombo = new javax.swing.JComboBox<>();
        LBLPROVEEDOR = new javax.swing.JLabel();
        Cantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        BtnBuscarAdminstracion = new javax.swing.JButton();
        jPanel63 = new javax.swing.JPanel();
        FechaIncioInventario = new com.toedter.calendar.JDateChooser();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        FechaFinalInventario = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel72 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        comboubicacion = new javax.swing.JComboBox<>();
        idbodega = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ComboSubCategoria = new javax.swing.JComboBox<>();
        LBLCATEGORIA = new javax.swing.JLabel();
        ComboCategorias = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        rSButtonHover3 = new rojerusan.RSButtonHover();
        rSButtonHover2 = new rojerusan.RSButtonHover();
        rSButtonHover1 = new rojerusan.RSButtonHover();
        labelfiltroInventario = new javax.swing.JLabel();
        FiltroOrden = new javax.swing.JComboBox<>();
        jSeparator58 = new javax.swing.JSeparator();

        codigo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/barcode_code_icon_194645.png"))); // NOI18N
        codigo.setText("GENERAR CÓDIGO DE BARRAS");
        codigo.setToolTipText("");
        codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codigoActionPerformed(evt);
            }
        });
        OpcionesAdmin.add(codigo);
        OpcionesAdmin.add(jSeparator21);

        totales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/business_salesreport_salesreport_negocio_2353.png"))); // NOI18N
        totales.setText("TOTALES");
        totales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalesActionPerformed(evt);
            }
        });
        OpcionesAdmin.add(totales);

        info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoActionPerformed(evt);
            }
        });
        TablaSistema.add(info);
        TablaSistema.add(jSeparator20);

        barras.setText("jMenuItem19");
        barras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barrasActionPerformed(evt);
            }
        });
        TablaSistema.add(barras);

        setBorder(null);

        jLabel11.setText("NOMBRE DEL PRODUCTO");

        Nombreproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreproductoActionPerformed(evt);
            }
        });
        Nombreproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NombreproductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreproductoKeyReleased(evt);
            }
        });

        jLabel10.setText("CÓDIGO DE BARRAS/NOMBRE/DESCRIPCION");

        Id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                IdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IdKeyTyped(evt);
            }
        });

        lblFiltroDeBusqueda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblFiltroDeBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("FILTRO DE BUSQUEDA:"));

        lbl_Estado_Registro_Productos.setText("ACTIVO");
        lbl_Estado_Registro_Productos.setBorder(javax.swing.BorderFactory.createTitledBorder("ESTADO REGISTRO:"));

        jCheckBox2.setSelected(true);
        jCheckBox2.setText("ORDEN");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Id, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFiltroDeBusqueda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_Estado_Registro_Productos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(Nombreproducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2)))
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Nombreproducto, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFiltroDeBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_Estado_Registro_Productos, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar Pequeño.png"))); // NOI18N
        Actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Actualizar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar Pequeño.png"))); // NOI18N
        Actualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar grande.png"))); // NOI18N
        Actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        Agregarp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        Agregarp.setText("INGRESAR");
        Agregarp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Agregarp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Agregarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarpActionPerformed(evt);
            }
        });

        Editarp.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        Editarp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        Editarp.setText("DETALLES");
        Editarp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Editarp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Editarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarpActionPerformed(evt);
            }
        });

        Nuevop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        Nuevop.setText("LIMPIAR");
        Nuevop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Nuevop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Nuevop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevopActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/business_salesreport_salesreport_negocio_2353.png"))); // NOI18N
        jButton2.setText("TOTALES");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton2MouseReleased(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("REFRESCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Agregarp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Editarp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Nuevop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(Nuevop, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Editarp, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Agregarp, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        ProveedoresCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProveedoresComboKeyPressed(evt);
            }
        });

        LBLPROVEEDOR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LBLPROVEEDOR.setText("PROVEEDOR:");

        Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadActionPerformed(evt);
            }
        });
        Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CantidadKeyPressed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("STOCK:");

        BtnBuscarAdminstracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarPequeño.png"))); // NOI18N
        BtnBuscarAdminstracion.setText("<html>BUSCAR POR STOCK Y PROVEEDOR</html>");
        BtnBuscarAdminstracion.setBorder(null);
        BtnBuscarAdminstracion.setContentAreaFilled(false);
        BtnBuscarAdminstracion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnBuscarAdminstracion.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarPequeño.png"))); // NOI18N
        BtnBuscarAdminstracion.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarGrande.png"))); // NOI18N
        BtnBuscarAdminstracion.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnBuscarAdminstracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarAdminstracionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LBLPROVEEDOR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ProveedoresCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnBuscarAdminstracion, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel61Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBLPROVEEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ProveedoresCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBuscarAdminstracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("BUSQUEDA COMB.", jPanel61);

        jLabel62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel62.setText("DESDE:");

        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel63.setText("HASTA:");

        jButton5.setText("BUSCAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("MOSTRAR MODIFICADOS");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FechaIncioInventario, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FechaFinalInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel63Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FechaIncioInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FechaFinalInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("FECHAS", jPanel63);

        jLabel34.setText("ÚBICACIÓN:");

        comboubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tienda" }));
        comboubicacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comboubicacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                comboubicacionKeyPressed(evt);
            }
        });

        idbodega.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        idbodega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idbodegaKeyPressed(evt);
            }
        });

        jLabel3.setText("SUBCATEGORÍA:");

        ComboSubCategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboSubCategoriaKeyPressed(evt);
            }
        });

        LBLCATEGORIA.setText("CATEGORÍA:");

        ComboCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboCategoriasActionPerformed(evt);
            }
        });
        ComboCategorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboCategoriasKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboubicacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel72Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboSubCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                            .addGroup(jPanel72Layout.createSequentialGroup()
                                .addComponent(idbodega, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(LBLCATEGORIA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel72Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LBLCATEGORIA, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(ComboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboSubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idbodega)
                .addContainerGap())
        );

        jTabbedPane4.addTab("OTROS", jPanel72);

        tablaProductos  = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                for(int i = 0; i < tablaProductos.getRowCount(); i ++){
                    if(row < 0){
                        return true;
                    }
                }
                return false;
            }
        };
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE", "STOCK", "PRECIO COSTO", "COSTO LETRAS", "PRECIO 1", "PRECIO 2", "PRECIO 3", "CATEGORÍA", "PROVEEDOR", "INGRESO", "MODIFICACION", "ESTADO", "RUTA", "IMAGEN", "EDITAR", "VENDER"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true, true, true, true, true, true, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaProductos.setRowHeight(80);
        tablaProductos.getTableHeader().setReorderingAllowed(false);
        tablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaProductosMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(tablaProductos);
        if (tablaProductos.getColumnModel().getColumnCount() > 0) {
            tablaProductos.getColumnModel().getColumn(1).setPreferredWidth(400);
        }

        rSButtonHover3.setText("TODOS");
        rSButtonHover3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rSButtonHover3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover3ActionPerformed(evt);
            }
        });

        rSButtonHover2.setText("INACTIVOS");
        rSButtonHover2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rSButtonHover2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover2ActionPerformed(evt);
            }
        });

        rSButtonHover1.setText("ACTIVOS");
        rSButtonHover1.setColorHover(new java.awt.Color(68, 140, 184));
        rSButtonHover1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rSButtonHover1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonHover1ActionPerformed(evt);
            }
        });

        labelfiltroInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/filtro.png"))); // NOI18N
        labelfiltroInventario.setText("FILTRO:");
        labelfiltroInventario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        FiltroOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID", "CATEGORÍA", "PROVEEDOR", "USUARIO" }));
        FiltroOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroOrdenActionPerformed(evt);
            }
        });

        jSeparator58.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelfiltroInventario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FiltroOrden, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jSeparator58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonHover1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonHover2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSButtonHover3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelfiltroInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButtonHover1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButtonHover2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rSButtonHover3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(FiltroOrden)
            .addComponent(jSeparator58)
        );

        javax.swing.GroupLayout ProductosLayout = new javax.swing.GroupLayout(Productos);
        Productos.setLayout(ProductosLayout);
        ProductosLayout.setHorizontalGroup(
            ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductosLayout.createSequentialGroup()
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane4)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ProductosLayout.setVerticalGroup(
            ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductosLayout.createSequentialGroup()
                .addGroup(ProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Productos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Productos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NombreproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproductoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(Nombreproducto.getText())) {
                TIPO_BUSQUEDA_FINAL_INVENTARIO=2;
                ConsultasVarias(2);
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese un Nombre de producto");
            }
        }
    }//GEN-LAST:event_NombreproductoKeyPressed

    private void NombreproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproductoKeyReleased

        if(!Nombreproducto.getText().equals("")){
            TIPO_BUSQUEDA_FINAL_INVENTARIO=2;
            ConsultasVarias(2);
        }

    }//GEN-LAST:event_NombreproductoKeyReleased

    private void IdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyReleased

        if(!Id.getText().equals("")){
            TIPO_BUSQUEDA_FINAL_INVENTARIO=1;
            ConsultasVarias(1);
        }

    }//GEN-LAST:event_IdKeyReleased

    private void IdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyTyped
        /* Eventos event = new Eventos();
        event.numberKeyPress(evt);*/
    }//GEN-LAST:event_IdKeyTyped

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        ActualizarTablaCentral();
        VaciarYllenarCategoria();
        VaciarYllenarProveedor();
        VaciarYllenarUbicacion();
    }//GEN-LAST:event_ActualizarActionPerformed

    private void AgregarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarpActionPerformed
        if(VentanaAdministracionDeProductos==false){
            VentanaAdministracionDeProductos=true;
            principal.AdminProduct= new ADMINISTRARPRODUCTO(String.valueOf(PARAMETROS_USUARIOS.ID_USUARIO), PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
            principal.AdminProduct.setVisible(true);
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERRÓR", "NO PUEDE ABRIR MÁS DE 1 VENTANA DE ADMINISTRACIÓN DE PRODUCTOS", DesktopNotify.ERROR, 10000L);
            principal.AdminProduct.toFront();
        }
    }//GEN-LAST:event_AgregarpActionPerformed

    private void EditarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarpActionPerformed
        if(VentanaAdministracionDeProductos==false){
            VentanaAdministracionDeProductos=true;
            principal.AdminProduct= new ADMINISTRARPRODUCTO(String.valueOf(PARAMETROS_USUARIOS.ID_USUARIO), PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
            principal.AdminProduct.InsertarDatos(Id.getText());
            principal.AdminProduct.setVisible(true);
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERRÓR", "NO PUEDE ABRIR MÁS DE 1 VENTANA DE ADMINISTRACIÓN DE PRODUCTOS", DesktopNotify.ERROR, 10000L);
            principal.AdminProduct.toFront();
        }
    }//GEN-LAST:event_EditarpActionPerformed

    private void NuevopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevopActionPerformed
        LIMPIAR_CAJAS_PRODUCTOS();
    }//GEN-LAST:event_NuevopActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if(evt.isPopupTrigger()){
            OpcionesAdmin.show(evt.getComponent(), evt.getX(), evt.getY());

        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        if(evt.isPopupTrigger()){
            OpcionesAdmin.show(evt.getComponent(), evt.getX(), evt.getY());

        }
    }//GEN-LAST:event_jButton2MousePressed

    private void jButton2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseReleased
        if(evt.isPopupTrigger()){
            OpcionesAdmin.show(evt.getComponent(), evt.getX(), evt.getY());

        }
    }//GEN-LAST:event_jButton2MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ABRIR_VENTANAS(T, true);
        TOTALES.TOTALES_INVENTARIO(tablaProductos);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        REFRESCAR_INVENTARIO();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ProveedoresComboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProveedoresComboKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(ProveedoresCombo.getSelectedItem())) {
                //limpiarCajas();
                String a=String.valueOf(ProveedoresCombo.getSelectedItem());
                TIPO_BUSQUEDA_FINAL_INVENTARIO=3;
                ConsultasVarias(3);
                ProveedoresCombo.setSelectedItem(a);
            }
        }
    }//GEN-LAST:event_ProveedoresComboKeyPressed

    private void CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadActionPerformed

    private void CantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(Cantidad.getText())) {
                TIPO_BUSQUEDA_FINAL_INVENTARIO=4;
                ConsultasVarias(4);
            }
        }
    }//GEN-LAST:event_CantidadKeyPressed

    private void BtnBuscarAdminstracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarAdminstracionActionPerformed
        if (!"".equals(ProveedoresCombo.getSelectedItem()) && !"".equals(Cantidad.getText())) {
            TIPO_BUSQUEDA_FINAL_INVENTARIO=5;
            ConsultasVarias(5);
        }
    }//GEN-LAST:event_BtnBuscarAdminstracionActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(FechaIncioInventario.getDate() != null || FechaFinalInventario.getDate() != null){
            ConsultaPorFechas();
            TIPO_BUSQUEDA_FINAL_INVENTARIO=20;
            lblFiltroDeBusqueda.setText("FECHAS");
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("¡ERROR AL CONSULTAR!", "¡DEBES DE LLENAR LOS CAMPOS DE FECHAS",DesktopNotify.ERROR, 10000L);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void comboubicacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboubicacionKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!"".equals(ComboCategorias.getSelectedItem())){
                TIPO_BUSQUEDA_FINAL_INVENTARIO=8;
                ConsultasVarias(8);
            }
        }
    }//GEN-LAST:event_comboubicacionKeyPressed

    private void idbodegaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idbodegaKeyPressed

    }//GEN-LAST:event_idbodegaKeyPressed

    private void ComboSubCategoriaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboSubCategoriaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!"".equals(ComboCategorias.getSelectedItem())){
                TIPO_BUSQUEDA_FINAL_INVENTARIO=7;
                ConsultasVarias(7);
            }
        }
    }//GEN-LAST:event_ComboSubCategoriaKeyPressed

    private void ComboCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCategoriasActionPerformed
        VaciarYllenarSubCategoria();
    }//GEN-LAST:event_ComboCategoriasActionPerformed

    private void ComboCategoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboCategoriasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!"".equals(ComboCategorias.getSelectedItem())){
                TIPO_BUSQUEDA_FINAL_INVENTARIO=6;
                ConsultasVarias(6);
            }
        }
    }//GEN-LAST:event_ComboCategoriasKeyPressed

    private void tablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseClicked
        Editarp.setEnabled(true);
        ProveedoresCombo.setVisible(true);
        LBLPROVEEDOR.setVisible(true);
        LBLCATEGORIA.setVisible(true);
        ComboCategorias.setVisible(true);
        Agregarp.setEnabled(false);
        comboubicacion.setSelectedIndex(0);
        int fila = tablaProductos.getSelectedRow();
        String codigo = tablaProductos.getValueAt(fila, 0).toString();
        String Nombre = tablaProductos.getValueAt(fila, 1).toString();
        String Stock = tablaProductos.getValueAt(fila, 2).toString();
        // String Especial = tablaProductos.getValueAt(fila,6).toString();
        //String Reventa = tablaProductos.getValueAt(fila, 7).toString();
        String Categoria = tablaProductos.getValueAt(fila, 8).toString();
        String Proveedor = tablaProductos.getValueAt(fila, 9).toString();
        /*  String FechaIngreso = tablaProductos.getValueAt(fila,10).toString();
        String UsuarioIngreso = tablaProductos.getValueAt(fila, 11).toString();
        String FechaModificacion = tablaProductos.getValueAt(fila,12).toString();
        String UsuarioModifico = tablaProductos.getValueAt(fila, 13).toString();*/
        Id.setText(codigo);
        Nombreproducto.setText(Nombre);
        Cantidad.setText(Stock);
        ProveedoresCombo.setSelectedItem(Proveedor);
        ComboCategorias.setSelectedItem(Categoria);
        int Columna = tablaProductos.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY()/tablaProductos.getRowHeight();
        if(Fila < tablaProductos.getRowCount() && Fila >= 0 && Columna < tablaProductos.getColumnCount() && Columna >= 0){
            Object value = tablaProductos.getValueAt(Fila, Columna);
            if(value instanceof JButton jButton){
                jButton.doClick();
                JButton boton = jButton;
                if (boton.getName().equals("edit")) {

                    if (VentanaAdministracionDeProductos == false) {
                        VentanaAdministracionDeProductos=true;
                        principal.AdminProduct = new ADMINISTRARPRODUCTO(String.valueOf(PARAMETROS_USUARIOS.ID_USUARIO), PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
                        principal.AdminProduct.InsertarDatos(codigo);
                        principal.AdminProduct.setVisible(true);
                    } else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                        DesktopNotify.showDesktopMessage("ERRÓR", "NO PUEDE ABRIR MÁS DE 1 VENTANA DE ADMINISTRACIÓN DE PRODUCTOS", DesktopNotify.ERROR, 10000L);
                        principal.AdminProduct.toFront();
                    }
                }
                if (boton.getName().equals("delete")) {

                    Boolean ESTADO = pos.BusquedaCodigoBarras(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 0).toString());
                    if (ESTADO == true) {
                        Boolean ESTADO_AGREGAR =pos.AgregarProducto();
                        if(ESTADO_AGREGAR==true){
                            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                            DesktopNotify.showDesktopMessage("ÉXITO", "SE AGREGÓ AL CARRITO 1  " + String.valueOf(tablaProductos.getValueAt(tablaProductos.getSelectedRow(), 1).toString()), DesktopNotify.SUCCESS, 10000L);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tablaProductosMouseClicked

    private void tablaProductosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductosMouseReleased

        if(evt.isPopupTrigger()){
            int fila = tablaProductos.getSelectedRow();
            if(fila >= 0){
                String codigo = tablaProductos.getValueAt(fila, 0).toString();
                ImageIcon Ruta = new ImageIcon(METODOS_GLOBALES.CargarDatosRutas(1)+"\\" +tablaProductos.getValueAt(fila, 13).toString());
                    info.setSize(200, 200);
                    info.setIcon(new ImageIcon(Ruta.getImage().getScaledInstance(
                        info.getWidth(),
                        info.getHeight(),
                        Image.SCALE_DEFAULT)));
            info.setText("VER DETALLES");
            String Barras = codigo;
            barras.setFont(new Font("Barcode", Font.BOLD, 24));
            barras.setHorizontalTextPosition(0);
            barras.setText(codigo);
            TablaSistema.show(evt.getComponent(), evt.getX(), evt.getY());

            if(info.isSelected()){

                if(VentanaAdministracionDeProductos==false){
                    VentanaAdministracionDeProductos=true;
                    principal.AdminProduct= new ADMINISTRARPRODUCTO(String.valueOf(PARAMETROS_USUARIOS.ID_USUARIO), PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
                    principal.AdminProduct.InsertarDatos(codigo);
                    principal.AdminProduct.setVisible(true);
                }else{
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERRÓR", "NO PUEDE ABRIR MÁS DE 1 VENTANA DE ADMINISTRACIÓN DE PRODUCTOS", DesktopNotify.ERROR, 10000L);
                    principal.AdminProduct.toFront();
                }
            }
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERROR", "¡DEBE SELECCIONAR UN PRODUCTO!", DesktopNotify.ERROR, 3000L);
        }

        }
    }//GEN-LAST:event_tablaProductosMouseReleased

    private void rSButtonHover3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover3ActionPerformed
        lbl_Estado_Registro_Productos.setText("TODOS");
        DINAMICA_BOTONES_PRODUCTOS();
        REFRESCAR_INVENTARIO();
    }//GEN-LAST:event_rSButtonHover3ActionPerformed

    private void rSButtonHover2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover2ActionPerformed
        lbl_Estado_Registro_Productos.setText("INACTIVO");
        DINAMICA_BOTONES_PRODUCTOS();
        REFRESCAR_INVENTARIO();
    }//GEN-LAST:event_rSButtonHover2ActionPerformed

    private void rSButtonHover1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonHover1ActionPerformed
        lbl_Estado_Registro_Productos.setText("ACTIVO");
        DINAMICA_BOTONES_PRODUCTOS();
        REFRESCAR_INVENTARIO();
    }//GEN-LAST:event_rSButtonHover1ActionPerformed

    private void FiltroOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroOrdenActionPerformed
        ActualizarTablaCentral();
        lblFiltroDeBusqueda.setText("SIN BUSQUEDA, ORGANIZADO POR: "+FiltroOrden.getSelectedItem());
    }//GEN-LAST:event_FiltroOrdenActionPerformed

    private void codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codigoActionPerformed
        if(!"".equals(Id.getText())){

            int seleccion= JOptionPane.showOptionDialog(null, "¡Por Favor elija un tamaño! ", //contenido de la ventana
                "Etiquetas" , //titulo de la ventana
                JOptionPane.YES_NO_CANCEL_OPTION, //para 3 botones si/no/cancel
                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                null,    // null para icono por defecto.
                new Object[] { "Pequeño 1x1", "Pequeño 1x3", "Mediano", "Grande", "Cancelar"},//objeto para las opciones
                //null para YES, NO y CANCEL
                "Pequeño 1x1"); //selección predeterminada
            switch (seleccion) {
                case 0:
                CodigoBarrasPequeño(tablaProductos);
                break;
                case 1:
                CodigoBarras3x1(tablaProductos);
                break;
                case 2:
                CodigoBarrasMediano(tablaProductos);
                break;
                case 3:
                CodigoBarrasGrande(tablaProductos);
                break;
                default:
                break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para realizar la etiqueta!");
        }
    }//GEN-LAST:event_codigoActionPerformed

    private void totalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalesActionPerformed
        ABRIR_VENTANAS(T, true);
        TOTALES.TOTALES_INVENTARIO(tablaProductos);
    }//GEN-LAST:event_totalesActionPerformed

    private void infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoActionPerformed

        int Fila = tablaProductos.getSelectedRow();
        String Codigo = tablaProductos.getValueAt(Fila, 0).toString();
        if(VentanaAdministracionDeProductos==false){
            VentanaAdministracionDeProductos=true;
            principal.AdminProduct= new ADMINISTRARPRODUCTO(String.valueOf(PARAMETROS_USUARIOS.ID_USUARIO), PARAMETROS_USUARIOS.NOMBREVISTA_USUARIO);
            principal.AdminProduct.InsertarDatos(Codigo);
            principal.AdminProduct.setVisible(true);
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERRÓR", "NO PUEDE ABRIR MÁS DE 1 VENTANA DE ADMINISTRACIÓN DE PRODUCTOS", DesktopNotify.ERROR, 10000L);
            principal.AdminProduct.toFront();
        }

    }//GEN-LAST:event_infoActionPerformed

    private void barrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barrasActionPerformed
        if(!"".equals(barras.getText())){

            int seleccion= JOptionPane.showOptionDialog(null, "¡Por Favor elija un tamaño! ", //contenido de la ventana
                "Etiquetas" , //titulo de la ventana
                JOptionPane.YES_NO_CANCEL_OPTION, //para 3 botones si/no/cancel
                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                null,    // null para icono por defecto.
                new Object[] { "Pequeño 1x1", "Pequeño 1x3", "Mediano", "Grande", "Cancelar"},//objeto para las opciones
                //null para YES, NO y CANCEL
                "Pequeño 1x1"); //selección predeterminada
            if(seleccion==0){
                CodigoBarrasPequeño(tablaProductos);
            }else if(seleccion==1){
                CodigoBarras3x1(tablaProductos);
            }else if(seleccion==2){
                CodigoBarrasMediano(tablaProductos);
            }else if(seleccion==3){
                CodigoBarrasGrande(tablaProductos);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para realizar la etiqueta!");
        }
    }//GEN-LAST:event_barrasActionPerformed

    private void NombreproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreproductoActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if(!Nombreproducto.getText().equals("")){
            TIPO_BUSQUEDA_FINAL_INVENTARIO=2;
            ConsultasVarias(2);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JButton Agregarp;
    private javax.swing.JButton BtnBuscarAdminstracion;
    private static javax.swing.JTextField Cantidad;
    public static javax.swing.JComboBox<String> ComboCategorias;
    private static javax.swing.JComboBox<String> ComboSubCategoria;
    private javax.swing.JButton Editarp;
    private static com.toedter.calendar.JDateChooser FechaFinalInventario;
    private static com.toedter.calendar.JDateChooser FechaIncioInventario;
    private static javax.swing.JComboBox<String> FiltroOrden;
    private static javax.swing.JTextField Id;
    private javax.swing.JLabel LBLCATEGORIA;
    private javax.swing.JLabel LBLPROVEEDOR;
    private static javax.swing.JTextField Nombreproducto;
    private javax.swing.JButton Nuevop;
    private javax.swing.JPopupMenu OpcionesAdmin;
    public javax.swing.JPanel Productos;
    public static javax.swing.JComboBox<String> ProveedoresCombo;
    private javax.swing.JPopupMenu TablaSistema;
    private javax.swing.JMenuItem barras;
    private javax.swing.JMenuItem codigo;
    private static javax.swing.JComboBox<String> comboubicacion;
    private javax.swing.JTextField idbodega;
    private javax.swing.JMenuItem info;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private static javax.swing.JCheckBox jCheckBox1;
    private static javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator20;
    private javax.swing.JPopupMenu.Separator jSeparator21;
    private javax.swing.JSeparator jSeparator58;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel labelfiltroInventario;
    private static javax.swing.JLabel lblFiltroDeBusqueda;
    private static javax.swing.JLabel lbl_Estado_Registro_Productos;
    private rojerusan.RSButtonHover rSButtonHover1;
    private rojerusan.RSButtonHover rSButtonHover2;
    private rojerusan.RSButtonHover rSButtonHover3;
    public static javax.swing.JTable tablaProductos;
    private javax.swing.JMenuItem totales;
    // End of variables declaration//GEN-END:variables

    public void CodigoBarrasMediano(JTable tabla){
         int Seleccion= tabla.getSelectedRow();
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id.getText());
         ParametrosCodigosBarras.setNombreproducto(Nombreproducto.getText());
         ParametrosCodigosBarras.setPrecioPublico(tabla.getValueAt(Seleccion, 5).toString());
         ParametrosCodigosBarras.setCostoLetras(tabla.getValueAt(Seleccion, 4).toString());
        CodigosBarras.CodigoBarrasMediano(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }
    
     public void CodigoBarrasGrande(JTable tabla){
          int Seleccion= tabla.getSelectedRow();
         ParametrosCodigosBarras.setCodigoDeBarrasOId(Id.getText());
         ParametrosCodigosBarras.setNombreproducto(Nombreproducto.getText());
         ParametrosCodigosBarras.setPrecioPublico(tabla.getValueAt(Seleccion, 5).toString());
         ParametrosCodigosBarras.setCostoLetras(tabla.getValueAt(Seleccion, 5).toString());
        CodigosBarras.CodigoBarrasGrande(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA, PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
    }
     
     public void CodigoBarras3x1(JTable tabla){
         int Seleccion = tabla.getSelectedRow();
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id.getText());
         ParametrosCodigosBarras.setNombreproducto(Nombreproducto.getText());
         ParametrosCodigosBarras.setPrecioPublico(tabla.getValueAt(Seleccion, 5).toString());
         ParametrosCodigosBarras.setCostoLetras(tabla.getValueAt(Seleccion, 4).toString());
        CodigosBarras.CodigoBarras3x1(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }
     
     public void CodigoBarrasPequeño(JTable tabla) {
        int Seleccion = tabla.getSelectedRow();
        ParametrosCodigosBarras.setCodigoDeBarrasOId(tabla.getValueAt(Seleccion, 0).toString());
        ParametrosCodigosBarras.setNombreproducto(tabla.getValueAt(Seleccion, 1).toString());
        ParametrosCodigosBarras.setPrecioPublico(tabla.getValueAt(Seleccion, 5).toString());
        ParametrosCodigosBarras.setCostoLetras(tabla.getValueAt(Seleccion, 4).toString());
        CodigosBarras.CodigoBarrasPequeño(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);


    }
}
