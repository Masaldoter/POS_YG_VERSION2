/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.POS;

import CLASES_GLOBALES.ATAJOSDETECLADO;
import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.CargarDatosRutas;
import static CLASES_GLOBALES.METODOS_GLOBALES.Fecha;
import static CLASES_GLOBALES.METODOS_GLOBALES.Hora;
import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import static CLASES_GLOBALES.METODOS_GLOBALES.PintarImagen;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import CLASES_GLOBALES.PARAMETROS_VENTAS;
import static CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxImpresionRapida;
import static CLASES_GLOBALES.PARAMETROS_VENTAS.CheckBoxModoStockCero;
import static CLASES_GLOBALES.PARAMETROS_VENTAS.CheckPermitirProductosPersonalizados;
import Configuraciones.Ventas;
import Controlador.ClientesDao;
import Controlador.CotizacionesDao;
import Controlador.Eventos;
import Controlador.FullSelectorListener;
import Controlador.KardexDao;
import Controlador.ProductosDao;
import Controlador.TextPrompt;
import Controlador.VentaDao;
import FEL.XML_DTE.GenerarXML;
import Modelo.Clientes;
import Modelo.Cotizaciones;
import Modelo.Detalle;
import Modelo.DetalleCotizaciones;
import Modelo.Kardex;
import Modelo.Productos;
import Modelo.Venta;
import ModeloWebService.DatosPersonaCliente;
import ModeloWebService.DatosUsuario;
import ModeloWebService.EnvioDatosFacturar;
import ModeloWebService.RespuestaCertificacion;
import NumerosALetras.Numero_Letras;
import Tablas.ActualizarTablaVentas;
import Tablas.RenderTablas;
import Vista.Cotizaciones.DetalleCotizacion;
import Vista.Detalles;
import Vista.Principal;
import Vista.VENTAS.CAJA.VER_CAJAS;
import WebServiceDigifact.CertificarFactura;
import WebServiceDigifact.ConsultarCUIWebService;
import WebServiceDigifact.ConsultarNitWebService;
import WebServiceDigifact.ObtenerToken;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class POS extends javax.swing.JInternalFrame {
    static TextAutoCompleter AutoCompletador_PRODUCTOS, AUTOCOMPLETADOR_CLIENTES_NOMBRE, AUTOCOMPLETADOR_CLIENTE_NIT;
    static double TotalPagar = 0.00;
    static int item;
    private static String NUMERO_INTERNO_FACTURA_ELECTRONICA="";
    private static String NumeroInternoFinal;
    Venta v = new Venta();
    static ClientesDao cliDao = new ClientesDao();
    FormaDePago Pagos = new FormaDePago(this);
    public JFRAME_BUSCAR_PRODUCTO BP;
    Detalles de;
    Observaciones observaciones;
    static ProductosDao proDao ;
    static Productos pro;
    static VentaDao Vdao = new VentaDao();
    Cotizaciones CotizacionesParametros;
    CotizacionesDao CotizacionDao;
    Ventas ConfigVentas = new Ventas();
    public Boolean VentanaFormaPago= false;
    public static Boolean VentanaObservacion= false;
    public Principal principal;
    POS pos;
    PARAMETROS_EMPRESA P_E = new PARAMETROS_EMPRESA();
             public ObtenerToken OT = new ObtenerToken();       
    
    public POS(Principal principal) {
        initComponents();
        this.pos = this;
        this.principal = principal;
        ((javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ATAJOSDETECLADO.clickOnKey(jButton8, "LimpiarCajas2", KeyEvent.VK_F5);
        ATAJOSDETECLADO.clickOnKey(BtnGenerarVentaPOS, "GenerarVenta", KeyEvent.VK_F8);
        ATAJOSDETECLADO.clickOnKey(BtnAgregarPagoPOS, "BtnAgregarPagoPOS", KeyEvent.VK_F12);
        ATAJOSDETECLADO.clickOnKey(BtnBuscarProductoPOS, "BtnBuscarProductoPOS", KeyEvent.VK_F10);
        ATAJOSDETECLADO.clickOnKey(BtnAgregarObservacionPOS, "BtnAgregarObservacionPOS", KeyEvent.VK_F11);
        ConfigVentas.CargarDatos(CheckIngresoAutomatico);
        ConfigVentas.CargarDatosVistaPreviaVenta(CheckVistaPreviaVenta);
        ConfigVentas.CargarDatosImprimir(CheckImprimir);
        ConfigVentas.CargarDatosTipoDocumento(TipoDocumento);
        AutoCompletador_PRODUCTOS = new TextAutoCompleter(NombreVenta, new AutoCompleterCallback() {
            @Override
            public void callback(Object selectedItem) {
                BuscarProductoVentaPorNombre(String.valueOf(selectedItem));
            }
        });
        
        AUTOCOMPLETADOR_CLIENTES_NOMBRE = new TextAutoCompleter(nombre, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                BUSCAR_CLIENTE_NOMBRE(String.valueOf(o));
            }
        });
        AUTOCOMPLETADOR_CLIENTE_NIT = new TextAutoCompleter(Caja_IDENTIFICACION, new AutoCompleterCallback() {
            @Override
            public void callback(Object o) {
                ConsultarNit_CUIFinal(String.valueOf(o));
            }
        });
        ListarProductosPOS_NOMBRE();
        LISTAR_CLIENTES_CAJAS_NOMBRES();
        LISTAR_CLIENTES_CAJAS_NIT();
        LIMPIAR_DATOS_RESUMEN_VENTA();
        CargarPaises();       
        Pagos.setVisible(false);
        Pagos.setVisible(false);
        VentanaFormaPago = false;
        TextoEnCajas();
        PaisCliente.setSelectedItem("GUATEMALA");
        SiglaPais();
        OT.ObtenerToken();
        OBTENERSERIES();
        IdVenta.requestFocus();
        
    }
    public void TextoEnCajas(){
        TextPrompt hold;
        hold= new TextPrompt("ID*", IdCliente);
        hold= new TextPrompt("NIT*", Caja_IDENTIFICACION);
        hold= new TextPrompt("DIRECCIÓN DE CLIENTE*", direccion);
        hold= new TextPrompt("MUNICIPIO*", MunicipioCliente);
        hold= new TextPrompt("DEPARTAMENTO*", DepartamentoCliente);
        hold= new TextPrompt("CÓDIGO POSTAL*", CodigoPostalCliente);
        hold= new TextPrompt("CÓDIGO*", SiglaPais);
    }
    
    public void ObtenerNumeroInterno() {
        generarserie();
        if(TipoDocumento.getSelectedIndex()==0){
         NumeroInternoFinal = factura.getText()+"-FAC-"+String.valueOf(new Random().nextLong()).substring(15);   
        }else if(TipoDocumento.getSelectedIndex()==1){
            NumeroInternoFinal = factura.getText()+"-PROF-"+String.valueOf(new Random().nextLong()).substring(15);   
        }
        
        Vdao.numeroserieIncrementarFactura(Integer.parseInt(factura.getText()));
    }
    
    public void OBTENERSERIES(){
        generarserie();
        generarserieCotizacion();
        generarserieVale();
        generarserie_SALIDAS();
    }
    
    //Limpiar cajas de Venta
    public void LIMPIAR_CAJA_CONSULTA_PRODUCTOS() {
        IdVenta.setText(null);
        NombreVenta.setText(null);
        PrecioPublico.setText(null);
        CantidadVenta.setText(null);
        Cantidad2.setText(null);
        PrecioRe.setSelected(false);
        PrecioRe.setText(null);
        PrecioEs.setText(null);
        PrecioPublico.setText(null);
        PrecioEs.setSelected(false);
        Final.setText(null);
        PrecioPublico.setSelected(false);
        CajaNumeroTransacción.setText(null);
        ComboFormaPago.setText(null);
        TotalLetras.setText("0.00");
        ObservacionVenta.setText(null);
        jButton7.setText("AGREGAR");
        IdVenta.requestFocus();
        EstadoProducto.setText("NO INGRESADO");
        lblVentaPrecio1.setText("PRECIO 1");
                lblVentaPrecio2.setText("PRECIO 2");
                lblVentaPrecio3.setText("PRECIO 3");
                DescripcionProductoVenta.setText(null);
        ID_PRODUCTO.setText("");
        TOTAL_INGRESADO.setText("0.0");
        CajaNumeroAutorizacion.setText(null);
        CajaNumeroDocumento.setText(null);
        CajaSerieCertificacion.setText(null);
        CajaFechaAutorizacion.setText(null);
        NUMERO_INTERNO_FACTURA_ELECTRONICA = null;
        lblImagenVenta.setName(null);
                ImagenAmigoVenta();
    }
    
    public void LIMPIAR_DATOS_RESUMEN_VENTA(){
       TotalLetras.setText("CERO QUETZALES");
       pagocon.setText("0.00");
       cambio.setText("0.00");
       labeltotal.setText("0.00");
       TotalTipoDeProductosPOS.setText("0");
       jLabel44.setText("0.00");
       labeltotalenfacturacion.setText("0.00");
       Efectivo.setText("0.00");
       Deposito.setText("0.00");
       Tarjeta.setText("0.00");
       Cheque.setText("0.00");
       labeltotal.setText("0.00");
    }

    //Limpiar cajas Clientes
    public void LIMPIAR_CLIENTE_POS() {
        nombre.setText(null);
        Caja_IDENTIFICACION.setText(null);
        direccion.setText(null);
        IdCliente.setText(null);
        MunicipioCliente.setText(null);
        DepartamentoCliente.setText(null);
        CodigoPostalCliente.setText(null);
    }
    
    public void ImagenAmigoVenta(){
        Image retValue = Toolkit.getDefaultToolkit().
         getImage(METODOS_GLOBALES.ObtenerRutaImagen(0));
        ImageIcon bl = new ImageIcon(retValue);
        
        METODOS_GLOBALES.PintarImagen2(lblImagenVenta, bl);
    }
    
    public void ListarProductosPOS_NOMBRE() {
        try {
            /*TextAutoCompleter AutoCompletador_PRODUCTOS=null;
            AutoCompletador_PRODUCTOS = new TextAutoCompleter(Parametro, new AutoCompleterCallback() {
                @Override
                public void callback(Object selectedItem) {
                    BuscarProductoVentaPorNombre(String.valueOf(selectedItem));
                }
            });*/
            AutoCompletador_PRODUCTOS.removeAllItems();
            ActualizarTablaVentas tablasVentas = new ActualizarTablaVentas();
            Object[] ob = new Object[1];
            List<Productos> ListarPr = null;
            ListarPr = tablasVentas.ListarProductosTiendaNombre();
            AutoCompletador_PRODUCTOS.setMode(0); // infijo
            for (int i = 0; i < ListarPr.size(); i++) {
                ob[0] = ListarPr.get(i).getNombre();
                AutoCompletador_PRODUCTOS.addItems(ob);
            }
        } catch (Exception e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("FATAL", "ÉRROR AL ACTUALIZAR LOS PRODUCTOS", DesktopNotify.FAIL, 10000L);
        }
    }

    public void LISTAR_CLIENTES_CAJAS_NOMBRES() {

        try {
            AUTOCOMPLETADOR_CLIENTES_NOMBRE.removeAllItems();
            List<Clientes> ListarCliente = cliDao.BuscarClienteLista(COMBO_TIPO_IDENTIFICACION.getSelectedItem().toString());  
            AUTOCOMPLETADOR_CLIENTES_NOMBRE.setMode(0);
            Object[] ob = new Object[1];
            for (int i = 0; i < ListarCliente.size(); i++) {
                ob[0] = ListarCliente.get(i).getNombre();
                AUTOCOMPLETADOR_CLIENTES_NOMBRE.addItems(ob);
            }
        } catch (Exception e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("FATAL", "ÉRROR AL ACTUALIZAR LOS PRODUCTOS", DesktopNotify.FAIL, 10000L);
        }
    }

    public void LISTAR_CLIENTES_CAJAS_NIT() {
        AUTOCOMPLETADOR_CLIENTE_NIT.removeAllItems();
        List<Clientes> ListarCliente = cliDao.BuscarClienteLista(COMBO_TIPO_IDENTIFICACION.getSelectedItem().toString());
        AUTOCOMPLETADOR_CLIENTE_NIT.setMode(0);
        Object[] ob = new Object[1];
        for (int i = 0; i < ListarCliente.size(); i++) {
            ob[0] = ListarCliente.get(i).getIDENTIFICACION();
            AUTOCOMPLETADOR_CLIENTE_NIT.addItems(ob);
        }
    }
    
    public void IngresoClientes(String Nombre, String IDENTIFICACION, String TIPO_IDENTIFICACION, String Direccion, String Municipio, String Departamento, String Pais, String CodigoPostal) {
        ClientesDao clDao = new ClientesDao();
        Clientes cl = new Clientes();
        if (Nombre.equals("")) {
        } else if (IDENTIFICACION.equals("")) {
            JOptionPane.showMessageDialog(null, "¡EL NIT DEL CLIENTE NO PUEDE IR VACÍO!");
        } else if (Direccion.equals("")) {
            JOptionPane.showMessageDialog(null, "¡LA DIRECCIÓN DEL CLIENTE NO PUEDE IR VACÍA!");
        } else {
            String Resultado = clDao.RetornarNit(IDENTIFICACION);
            if (Resultado == null) {
                cl.setNombre(Nombre);
                cl.setIDENTIFICACION(IDENTIFICACION);
                cl.setTIPO_IDENTIFICACION(TIPO_IDENTIFICACION);
                cl.setDireccion(Direccion);
                cl.setCodigoPostal(CodigoPostal);
                cl.setMunicipio(Municipio);
                cl.setDepartamento(Departamento);
                cl.setPais(Pais);
                clDao.IngresoCliente(cl);
                ObtenerCliente(IDENTIFICACION);
            }
        }

    }

    public void BUSCAR_CLIENTE_NOMBRE(String Parametro){
        Clientes cli = new Clientes();
        if (Parametro.equals("")) {
            JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR UN NOMBRE DE CLIENTE");
        } else {
            cli = cliDao.BuscarClieNombre(Parametro);
            Caja_IDENTIFICACION.setText(cli.getIDENTIFICACION());
            COMBO_TIPO_IDENTIFICACION.setSelectedItem(cli.getTIPO_IDENTIFICACION());
            direccion.setText(cli.getDireccion());
            nombre.setText(cli.getNombre());
            MunicipioCliente.setText(cli.getMunicipio());
            DepartamentoCliente.setText(cli.getDepartamento());
            CodigoPostalCliente.setText(cli.getCodigoPostal());
            if (CodigoPostalCliente.getText().equals("")) {
                CodigoPostalCliente.setText("0");
            }
            PaisCliente.setSelectedItem(cli.getPais());
        }
    }
    
    public void ConsultarNit_CUIFinal(String Nit){
        cliDao = new ClientesDao();
              boolean ESTADO_CONSULTA_NIT = ObtenerCliente(Nit);
             if(ESTADO_CONSULTA_NIT==false){
                 System.out.println(P_E.TOKEN_CERTIFICADOR);
                 if (P_E.TOKEN_CERTIFICADOR.equals("")) {
                            OT.ObtenerToken();
                        }
                        DatosPersonaCliente DPC = new DatosPersonaCliente();
                        DPC.setNIT_CUI(Nit);
                        DatosUsuario DU = new DatosUsuario();
                        DU.setNit(P_E.NIT_EMPRESA);
                        DU.setUsuario(P_E.USUARIO_CERTIFICADOR);
                        DU.setContrasenia(P_E.CONTRASENIA_CERTIFICADOR);
                 if(COMBO_TIPO_IDENTIFICACION.getSelectedItem().equals("NIT")){
                     ConsultarNitWebService CNWS = new ConsultarNitWebService();
                     DPC = CNWS.ObtenerCliente(DPC, DU, P_E.TOKEN_CERTIFICADOR);
                     nombre.setText(DPC.getNombre());
                        Caja_IDENTIFICACION.setText(Nit);
                        direccion.setText(DPC.getDireccion());
                        MunicipioCliente.setText(DPC.getMunicipio());
                        DepartamentoCliente.setText(DPC.getDepartamento());
                        PaisCliente.setSelectedItem(DPC.getPais());
                        CodigoPostalCliente.setText("0");
                        IngresoClientes(nombre.getText(), Caja_IDENTIFICACION.getText(), String.valueOf(COMBO_TIPO_IDENTIFICACION.getSelectedItem()), direccion.getText(), MunicipioCliente.getText(), DepartamentoCliente.getText(), PaisCliente.getSelectedItem().toString(), CodigoPostalCliente.getText());
                 }else{
                     ConsultarCUIWebService CCWS = new ConsultarCUIWebService();
                     DPC = CCWS.ObtenerCliente(DPC, DU, P_E.TOKEN_CERTIFICADOR);
                     nombre.setText(DPC.getNombre());
                        Caja_IDENTIFICACION.setText(Nit);
                        direccion.setText(DPC.getDireccion());
                        MunicipioCliente.setText(DPC.getMunicipio());
                        DepartamentoCliente.setText(DPC.getDepartamento());
                        PaisCliente.setSelectedItem(DPC.getPais());
                        CodigoPostalCliente.setText("0");
                 }
             }else{
              ObtenerCliente(Nit);   
             }
    }
    
    public Boolean ObtenerCliente(String Nit) {
        Clientes cliente = new Clientes();
        cliente = ClientesDao.BuscarClie(Nit);
        Boolean Existe = cliente.getResutaldoConsulta();
        if (Existe == true) {
            Existe = true;
            COMBO_TIPO_IDENTIFICACION.setSelectedItem(cliente.getTIPO_IDENTIFICACION());
            nombre.setText(cliente.getNombre());
            Caja_IDENTIFICACION.setText(cliente.getIDENTIFICACION());
            direccion.setText(cliente.getDireccion());
            IdCliente.setText(String.valueOf(cliente.getIdclientes()));
        CodigoPostalCliente.setText(cliente.getCodigoPostal());
        MunicipioCliente.setText(cliente.getMunicipio());
        DepartamentoCliente.setText(cliente.getDepartamento());
        PaisCliente.setSelectedItem(cliente.getPais());
        }else{
        Existe=false;    
        }
        return Existe;
    }

    public synchronized boolean AgregarProducto() {
        Boolean ESTADO_PRODUCTO_AGREGADO=false;
        if (EstadoProducto.getText().equals("INGRESADO")) {
            if (CheckBoxModoStockCero==true) {
                if (Cantidad2.getText().equals("0.0")) {
                    AgregarProductoSinBD();
                    ESTADO_PRODUCTO_AGREGADO=true;
                } else {
                    AgregarProductoSinExistenciaEnTabla();
                    ESTADO_PRODUCTO_AGREGADO=true;
                }
            } else {
                AgregarProductoSinExistenciaEnTabla();
                ESTADO_PRODUCTO_AGREGADO=true;
            }
        } else {
            if (CheckPermitirProductosPersonalizados==true) {

                AgregarProductoSinBD();
                ESTADO_PRODUCTO_AGREGADO=true;
            } else {
                JOptionPane.showMessageDialog(null, "EL PRODUCTO NO EXISTE");
                ESTADO_PRODUCTO_AGREGADO=false;
            }

        }
        return ESTADO_PRODUCTO_AGREGADO;
    }

    public synchronized void EditarProductoPOS() {

        if (EstadoProducto.getText().equals("INGRESADO")) {
            if (CheckBoxModoStockCero==true) {

                if (Cantidad2.getText().equals("0.0")) {
                    if(ID_PRODUCTO.getText().equals("1")){
                        AgregarProductoConExistenciaEnTabla();
                    }else{
                        EliminarVentaSinAumentarStock();
                    AgregarProductoSinBD();
                    }
                } else {
                    AgregarProductoConExistenciaEnTabla();
                }
            } else {
                AgregarProductoConExistenciaEnTabla();
            }
        } else {
            if (CheckPermitirProductosPersonalizados==true) {

                EliminarVentaSinAumentarStock();
                AgregarProductoSinBD();
            } else {
                JOptionPane.showMessageDialog(null, "EL PRODUCTO NO EXISTE");
            }

        }

    }
    
    public void SUMAR_TABLA(){
        Float CANTIDAD, PRECIO_NORMAL, DESCUENTO, PRECIO_CON_DESCUENTO, TOTAL;
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            CANTIDAD = Float.parseFloat(TablaVentas.getValueAt(i, 2).toString());
            PRECIO_NORMAL = Float.parseFloat(TablaVentas.getValueAt(i, 3).toString());
            DESCUENTO = Float.parseFloat(TablaVentas.getValueAt(i, 4).toString());
            TablaVentas.setValueAt(String.valueOf(PRECIO_NORMAL-DESCUENTO), i, 5);
            PRECIO_CON_DESCUENTO = Float.parseFloat(TablaVentas.getValueAt(i, 5).toString());
            TablaVentas.setValueAt(String.valueOf(CANTIDAD*PRECIO_CON_DESCUENTO), i, 6);
        }
    }
    
    public void APLICAR_DESCUENTO_TABLA(Float DESCUENTO_PORCENTAJE){
        Float PRECIO_NORMAL, DESCUENTO = 0F;
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            if(TablaVentas.getValueAt(i, 7).toString().equals("true")){
             PRECIO_NORMAL = Float.parseFloat(TablaVentas.getValueAt(i, 3).toString());
            //DESCUENTO = (PRECIO_NORMAL/100)*DESCUENTO;
            DESCUENTO = (PRECIO_NORMAL*DESCUENTO_PORCENTAJE)/100;
            TablaVentas.setValueAt(String.valueOf(DESCUENTO), i, 4);   
            }
            }
        SUMAR_TABLA();
        TotalPagar();
    }
    
    public void VERIFICAR_DESCUENTO(JComboBox COMBO){
        if(COMBO.getSelectedIndex()==0){
            APLICAR_DESCUENTO_TABLA(0f);
        }else{
            APLICAR_DESCUENTO_TABLA(5f);
        }
    }

    public synchronized void AgregarProductoSinExistenciaEnTabla() {
        Productos pro = new Productos();
        ProductosDao proDao = new ProductosDao();
        float EnStock = 0;

        TablaVentas.setDefaultRenderer(Object.class, new RenderTablas());

        JButton btn1 = new JButton("ELIMINAR");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro = new ImageIcon(retValue);

        btn1.setIcon(ro);
        DefaultTableModel modelo = new DefaultTableModel();

        if (!"".equals(Final.getText())) {
            if (!"".equals(CantidadVenta.getText())) {

                String cod = (IdVenta.getText());
                String descripcion = NombreVenta.getText();
                float cantidad = Float.parseFloat(CantidadVenta.getText());
                Float PrecioNormal = Float.parseFloat(PrecioPublico.getText());
                float PRECIO_FINAL = Float.parseFloat(Final.getText());
                Float TotalDescuento = 0.000f;
                if(PRECIO_FINAL<=PrecioNormal){
                    TotalDescuento=PrecioNormal - PRECIO_FINAL;
                }
                    pro = proDao.VerStock(cod);
                    EnStock = pro.getCantidad();
                

                if (EnStock >= cantidad) {
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "¡NO PUEDE INGRESAR UNA CANTIDAD MENOR A 1!");
                    } else {
                        
                        item = item + 1;
                        modelo = (DefaultTableModel) TablaVentas.getModel();

                        ArrayList lista = new ArrayList();
                        lista.add(item);
                        lista.add(cod);
                        lista.add(descripcion);
                        lista.add(cantidad);
                        lista.add(String.format("%.4f", PRECIO_FINAL));
                        lista.add(String.format("%.4f", 0.0));
                        lista.add(0);
                        lista.add(0);
                        lista.add(jCheckBox1.isSelected());
                        lista.add("1");
                        lista.add(btn1);
                        //lista.add(ubicacion2);

                        Object[] O = new Object[10];
                        O[0] = lista.get(1);
                        O[1] = lista.get(2);
                        O[2] = lista.get(3);
                        O[3] = lista.get(4);
                        O[4] = lista.get(5);
                        O[5] = lista.get(6);
                        O[6] = lista.get(7);
                        O[7] = lista.get(8);
                        O[8] = lista.get(9);
                        O[9] = lista.get(10);
                        modelo.addRow(O);
                        TablaVentas.setModel(modelo);
                        SUMAR_TABLA();
                        RestarStock();
                        SumarProductos();
                        TotalPagar();
                        VERIFICAR_DESCUENTO(jComboBox1);
                        LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
                        FORMA_DE_PAGO();
                        IdVenta.requestFocus();
                        BAJAR_SCROLL(jScrollPane2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡STOCK NO DISPONIBLE!\n EN STOCK: " + EnStock, "STOCK INSUFICIENTE", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE PRODUCTOS A VENDER");
            }
        } else {
            // JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR O INGRESAR UN PRECIO!");
        }
    }

    @SuppressWarnings("unchecked")
    public synchronized void AgregarProductoConExistenciaEnTabla() {
        float EnStock = 0;

        TablaVentas.setDefaultRenderer(Object.class, new RenderTablas());

        JButton btn1 = new JButton("ELIMINAR");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro = new ImageIcon(retValue);

        btn1.setIcon(ro);
        DefaultTableModel modelo = new DefaultTableModel();
        Productos pro100 = new Productos();
        ProductosDao proDao100 = new ProductosDao();
        int item100 = 0;

        if (!"".equals(Final.getText())) {
            if (!"".equals(CantidadVenta.getText())) {
                String cod = (IdVenta.getText());
                String descripcion = NombreVenta.getText();
                float cantidad = Float.parseFloat(CantidadVenta.getText());
                Float PrecioNormal = Float.parseFloat(PrecioPublico.getText());
                float PRECIO_FINAL = Float.parseFloat(Final.getText());
                Float TotalDescuento = 0f;
                if(PRECIO_FINAL<=PrecioNormal){
                    TotalDescuento=PrecioNormal - PRECIO_FINAL;
                }

                    pro100 = proDao100.VerStock(cod);
                    EnStock = pro100.getCantidad()+Float.parseFloat(TOTAL_INGRESADO.getText());
                

                if (EnStock >= cantidad ) {
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "¡NO PUEDE INGRESAR UNA CANTIDAD MENOR A 1!");
                    } else {
                        EliminarVenta();

                        item100 = item100 + 1;
                        modelo = (DefaultTableModel) TablaVentas.getModel();

                        ArrayList lista = new ArrayList();
                        lista.add(item);
                        lista.add(cod);
                        lista.add(descripcion);
                        lista.add(cantidad);
                        lista.add(String.format("%.4f", PRECIO_FINAL));
                        lista.add(String.format("%.4f", TotalDescuento));
                        lista.add(0);
                        lista.add(0);
                        lista.add(jCheckBox1.isSelected());
                        lista.add("1");
                        lista.add(btn1);
                        //lista.add(ubicacion2);

                        Object[] O = new Object[10];
                        O[0] = lista.get(1);
                        O[1] = lista.get(2);
                        O[2] = lista.get(3);
                        O[3] = lista.get(4);
                        O[4] = lista.get(5);
                        O[5] = lista.get(6);
                        O[6] = lista.get(7);
                        O[7] = lista.get(8);
                        O[8] = lista.get(9);
                        O[9] = lista.get(10);
                        modelo.addRow(O);
                        TablaVentas.setModel(modelo);
                        SUMAR_TABLA();
                        RestarStock();
                        SumarProductos();
                        TotalPagar();
                        VERIFICAR_DESCUENTO(jComboBox1);
                        LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
                        FORMA_DE_PAGO();
                        IdVenta.requestFocus();
                        BAJAR_SCROLL(jScrollPane2);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "¡STOCK NO DISPONIBLE!\n EN STOCK: " + EnStock, "STOCK INSUFICIENTE", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE PRODUCTOS A VENDER");
            }
        } else {
            //   JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR O INGRESAR UN PRECIO!");
        }
    }

    public synchronized void AgregarProductoSinBD() {
        TablaVentas.setDefaultRenderer(Object.class, new RenderTablas());

        JButton btn1 = new JButton("ELIMINAR");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro = new ImageIcon(retValue);

        btn1.setIcon(ro);
        DefaultTableModel modelo = new DefaultTableModel();
        int item = 0;

        if (!"".equals(Final.getText())) {
            if (!"".equals(CantidadVenta.getText())) {
                float precio = Float.parseFloat(Final.getText());
                float total = Float.parseFloat(CantidadVenta.getText()) * precio;
                if (Float.parseFloat(CantidadVenta.getText()) <= 0) {
                    JOptionPane.showMessageDialog(null, "¡NO PUEDE INGRESAR UNA CANTIDAD MENOR A 1!");
                } else {

                    item = item + 1;
                    modelo = (DefaultTableModel) TablaVentas.getModel();

                    ArrayList lista = new ArrayList();
                    lista.add(item);
                    lista.add(IdVenta.getText());
                    lista.add(NombreVenta.getText());
                    lista.add(Float.parseFloat(CantidadVenta.getText()));
                    lista.add(String.format("%.4f", Float.parseFloat(Final.getText())));
                    lista.add(0);
                    lista.add(0);
                    lista.add(0);
                    lista.add(jCheckBox1.isSelected());
                    lista.add("0");
                    lista.add(btn1);
                    //lista.add(ubicacion2);

                    Object[] O = new Object[10];
                    O[0] = lista.get(1);
                    O[1] = lista.get(2);
                    O[2] = lista.get(3);
                    O[3] = lista.get(4);
                    O[4] = lista.get(5);
                    O[5] = lista.get(6);
                    O[6] = lista.get(7);
                    O[7] = lista.get(8);
                    O[8] = lista.get(9);
                    O[9] = lista.get(10);
                    modelo.addRow(O);
                    TablaVentas.setModel(modelo);
                    SUMAR_TABLA();
                    SumarProductos();
                    VERIFICAR_DESCUENTO(jComboBox1);
                    TotalPagar();
                    LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
                    FORMA_DE_PAGO();
                    IdVenta.requestFocus();
                    BAJAR_SCROLL(jScrollPane2);
                    
                }
            } else {
                JOptionPane.showMessageDialog(null, "INGRESE LA CANTIDAD DE PRODUCTOS A VENDER");
            }
        } else {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR O INGRESAR UN PRECIO!");
        }
    }

    public synchronized void EliminarVenta() {
        var modelo = new DefaultTableModel();
        AumentarStockUnidad();
        int fila1 = TablaVentas.getSelectedRow();
        String fila = String.valueOf(fila1);

        if (fila != null) {
            modelo = (DefaultTableModel) TablaVentas.getModel();
            modelo.removeRow(TablaVentas.getSelectedRow());
            TotalPagar();
            TotalTipoDeProductosPOS.setText("0");
            jLabel44.setText("0.00");
            IdVenta.requestFocus();
            TOTAL_INGRESADO.setText("0.0");
            FORMA_DE_PAGO();
            ID_PRODUCTO.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN PRODUCTO");
        }
    }

    public synchronized void EliminarVentaSinAumentarStock() {
        DefaultTableModel modelo = new DefaultTableModel();
        int fila1 = TablaVentas.getSelectedRow();
        String fila = String.valueOf(fila1);

        if (fila != null) {
            modelo = (DefaultTableModel) TablaVentas.getModel();
            modelo.removeRow(TablaVentas.getSelectedRow());
            TotalPagar();
            TotalTipoDeProductosPOS.setText("0");
            jLabel44.setText("0.00");
            TOTAL_INGRESADO.setText("0.0");
            FORMA_DE_PAGO();
            ID_PRODUCTO.setText("");
            IdVenta.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN PRODUCTO");
        }
    }
    
    public void BAJAR_SCROLL(javax.swing.JScrollPane Scroll){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Scroll.getVerticalScrollBar().setValue(Scroll.getVerticalScrollBar().getMaximum());
            }
        });
    }
    
    private synchronized void TotalPagar() {
        TotalPagar = 0.00;
        int numFila = TablaVentas.getRowCount();
        for (int i = 0; i < numFila; i++) {
            double cal = Double.parseDouble(String.valueOf(TablaVentas.getModel().getValueAt(i, 6)));
            TotalPagar = TotalPagar + cal;

        }
        labeltotal.setText(String.format("%.2f",TotalPagar));
        labeltotalenfacturacion.setText(String.format("%.2f",TotalPagar));
    }
    
    public Boolean VALIDAR_CAJAS_CLIENTE_POS(){
        Boolean Estado = false;
        if (nombre.getText().equals("") || Caja_IDENTIFICACION.getText().equals("") || direccion.getText().equals("")
                || MunicipioCliente.getText().equals("") || DepartamentoCliente.getText().equals("")
                || PaisCliente.getSelectedItem().equals("") || SiglaPais.getText().equals("") || CodigoPostalCliente.getText().equals("")) {
            Estado = false;
            JOptionPane.showMessageDialog(null, "DEBE RELLENAR TODOS LOS CAMPOS DEL CLIENTE");
        } else {
            Estado = true;
        }
        return Estado;
    }

    //Cuando el cliente esta vacío 
    public synchronized void Imprimir() {
        //Detalles D= new Detalles(factura.getText(), factura.getText());
        if (VALIDAR_CAJAS_CLIENTE_POS() == false) {
            int i = JOptionPane.showConfirmDialog(null, "¿DESEA COMPLETAR LA VENTA SIN UN CLIENTE?\n *Esta acción asignará automaticamente un CONSUMIDOR FINAL a la venta");
            if (i == 0) {
                ConsultarNit_CUIFinal("CF");
                ObtenerNumeroInterno();
                RegistrarVenta();
                if (VentanaFormaPago == true) {
                    Pagos.setVisible(false);
                    VentanaFormaPago = false;
                }
            }
        } else {
            ObtenerNumeroInterno();
                    RegistrarVenta();
            if (VentanaFormaPago == true) {
                Pagos.setVisible(false);
                VentanaFormaPago = false;
            }
        }
    }
    
    public String TIPO_NUMERO_INTERNO(){
        String NUMERO_INTERNO = null;
        
        if(TipoDocumento.getSelectedIndex()==0 || TipoDocumento.getSelectedIndex()==1){
           NUMERO_INTERNO = NumeroInternoFinal ;
        }else if(TipoDocumento.getSelectedIndex()==3){
           generarserie_SALIDAS();
           NUMERO_INTERNO = lblSerie_Salidas.getText();
        }
        return NUMERO_INTERNO;
    }

    

    private synchronized void RegistrarVenta() {
        v.setCliente(nombre.getText());
        v.setDireccionCliente(direccion.getText());
        v.setIDENTIFICACION_CLIENTE(Caja_IDENTIFICACION.getText());
        v.setTIPO_IDENTIFICACION_CLIENTE(String.valueOf(COMBO_TIPO_IDENTIFICACION.getSelectedItem()));
        v.setTotal(Float.parseFloat(labeltotalenfacturacion.getText()));
        v.setPagocon(Float.parseFloat(pagocon.getText()));
        v.setCambio(Float.parseFloat(cambio.getText()));
        v.setUsuario(PARAMETROS_USUARIOS.ID_USUARIO);
        v.setNoFactura(TIPO_NUMERO_INTERNO());
        v.setId_CAJA_registro(Integer.parseInt(PARAMETROS_VENTAS.NUMERO_CAJA));
        String FormaPago;
        if (ComboFormaPago.getText() == null) {
            FormaPago = "EFECTIVO";
        } else {
            FormaPago = ComboFormaPago.getText();
        }
        v.setFormaPago(FormaPago);
        v.setNumeroTransaccion(CajaNumeroTransacción.getText());
        v.setTotalEnLetras(TotalLetras.getText());

        v.setObservacion(ObservacionVenta.getText());
        v.setNombreCertificador(P_E.NOMBRE_CERTIFICADOR);
        v.setNitCertificador(P_E.NIT_CERTIFICADOR);
        v.setFechaAutorizacion(CajaFechaAutorizacion.getText());
        v.setNumeroAutorizacion(CajaNumeroAutorizacion.getText());
        v.setNumeroDocumento(CajaNumeroDocumento.getText());
        v.setSerieDocumento(CajaSerieCertificacion.getText());
        v.setTipoDocumentoFel(TipoDocumento.getSelectedItem().toString());
        v.setNUMERO_INTERNO(NUMERO_INTERNO_FACTURA_ELECTRONICA);
        v.setNitEmisor(P_E.NIT_EMPRESA);
        Boolean ESTADO_REGISTRO_VENTA = Vdao.RegistrarVenta(v);
        if (ESTADO_REGISTRO_VENTA == true) {
            GUARDAR_KARDEX();
            RegistarDetalle();
        }
    }

    private synchronized void RegistarDetalle() {
        Detalle Dv = new Detalle();
        Boolean ESTADO_REGISTRO_DETALLES=false;
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            int Id_Producto = Vdao.BuscarIdProducto(TablaVentas.getValueAt(i, 0).toString());
            Dv.setCodigoBarras(TablaVentas.getValueAt(i, 0).toString());
            Dv.setNombre(TablaVentas.getValueAt(i, 1).toString());
            Dv.setCantidad(Float.parseFloat(TablaVentas.getValueAt(i, 2).toString()));
            Dv.setPrecio(Float.parseFloat(TablaVentas.getValueAt(i, 3).toString()));
            Dv.setDescuento(Float.parseFloat(TablaVentas.getValueAt(i, 4).toString()));
            Dv.setPrecio_Con_Descuento(Float.parseFloat(TablaVentas.getValueAt(i, 5).toString()));
            Dv.setTotal(Float.parseFloat(TablaVentas.getValueAt(i, 6).toString()));
            Dv.setAplicar_Descuento(Boolean.parseBoolean(TablaVentas.getValueAt(i, 7).toString()));
            Dv.setValidacionProductoExistente(TablaVentas.getValueAt(i, 8).toString());
            Dv.setNoFactura(TIPO_NUMERO_INTERNO());
            Dv.setIdProductos(Id_Producto);
            ESTADO_REGISTRO_DETALLES = Vdao.RegistrarDetalle(Dv);
            
        }
        if (ESTADO_REGISTRO_DETALLES == true) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO ÉXITOSO", "LA VENTA SE REGISTRÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 14000L);
                if (CheckVistaPreviaVenta.isSelected()) {
                    VerDetalleMOVIMIENTO(String.valueOf(ComboFormaPago.getText()), TIPO_NUMERO_INTERNO(), 0);

                } else {
                    VerDetalleMOVIMIENTO(String.valueOf(ComboFormaPago.getText()), TIPO_NUMERO_INTERNO(), 1);
                }

                LIMPIAR_DATOS_RESUMEN_VENTA();
                LIMPIAR_CLIENTE_POS();
                LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
                LIMPIAR_TABLA(TablaVentas);
                OBTENERSERIES();
            }
    }

    private synchronized Boolean GUARDAR_KARDEX() {
        Boolean Estado=false;
        KardexDao kdDao = new KardexDao();
        Kardex Kd;
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            if (TablaVentas.getValueAt(i, 8).toString().equals("1")) {
                Kd = new Kardex();
                int Id_Producto = Vdao.BuscarIdProducto(TablaVentas.getValueAt(i, 0).toString());
                String CANTIDAD_A_VENDER= TablaVentas.getValueAt(i, 2).toString();
                String STOCK_ANTES= String.valueOf(VentaDao.BuscarSTOCKProducto(Id_Producto)+ Float.parseFloat(CANTIDAD_A_VENDER));
                String STOCK_DESPUES= String.valueOf(Float.parseFloat(STOCK_ANTES)-Float.parseFloat(CANTIDAD_A_VENDER));
                Kd.setID_Codigo_Producto_Kardex(Id_Producto );
                Kd.setTitulo_Kardex(" SE DESCONTO EN "+TipoDocumento.getSelectedItem().toString()+": "+TIPO_NUMERO_INTERNO());
                Kd.setEntrada_Kardex("0");
                Kd.setSalida_Kardex(CANTIDAD_A_VENDER);
                Kd.setAntes_Kardex(STOCK_ANTES);
                Kd.setDespues_Kardex(STOCK_DESPUES);
                Kd.setFecha_Modificacion_Kardex(METODOS_GLOBALES.Fecha() + " " + METODOS_GLOBALES.Hora());
                Kd.setUsuario_Modifico_Kardex(PARAMETROS_USUARIOS.ID_USUARIO);
                Kd.setModulo_Kardex("POS");
                Estado = kdDao.RegistrarKARDEX(Kd);
            }
        }
        return Estado;
    }

    public synchronized void ActualizarStock() {
        pro = new Productos();
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            String cod = TablaVentas.getValueAt(i, 0).toString();
            Float cant = Float.parseFloat(TablaVentas.getValueAt(i, 2).toString());
                pro = proDao.BuscarPro(cod);
                Float StockActual = pro.getCantidad() - cant;
                VentaDao.ActualizarStock(StockActual, String.valueOf(cod));
            
        }
    }

    public synchronized void RestarStock() {
        pro = new Productos();
        proDao = new ProductosDao();
        String cod = IdVenta.getText();
        Float cant = Float.parseFloat(CantidadVenta.getText());
        pro = proDao.VerStock(cod);
        Float StockActual = pro.getCantidad() - cant;
        VentaDao.ActualizarStock(StockActual, String.valueOf(cod));
    }

    public synchronized void AumentarStock() {
        pro = new Productos();
        proDao = new ProductosDao();
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {

            String cod = TablaVentas.getValueAt(i, 0).toString();
            Float cant = Float.parseFloat(TablaVentas.getValueAt(i, 2).toString());
            if (Integer.parseInt(TablaVentas.getValueAt(i, 8).toString()) == 1) {

                pro = proDao.VerStock(cod);
                Float StockActual = pro.getCantidad() + cant;
                VentaDao.ActualizarStock(StockActual, String.valueOf(cod));
            }

        }

    }

    public synchronized void AumentarStockUnidad() {
        pro = new Productos();
        proDao = new ProductosDao();
        int x = TablaVentas.getSelectedRow();
        String cod = TablaVentas.getValueAt(x, 0).toString();
        Float cant = Float.parseFloat(TablaVentas.getValueAt(x, 2).toString());
            pro = proDao.VerStock(cod);
            Float StockActual = pro.getCantidad() + cant;
            VentaDao.ActualizarStock(StockActual, String.valueOf(cod));
        

    }

    public synchronized void GenerarCotizacion() {
        CotizacionDao = new CotizacionesDao();
        //Detalles D= new Detalles(factura.getText(), factura.getText());
        if (nombre.getText().equals("") && Caja_IDENTIFICACION.getText().equals("")) {
            int i = JOptionPane.showConfirmDialog(null, "¿DESEA COMPLETAR LA VENTA SIN UN CLIENTE?\n *Esta acción asignará automaticamente un CONSUMIDOR FINAL a la venta");
            if (i == 0) {
                generarserieCotizacion();
                ConsultarNit_CUIFinal("CF");
                RegistraCotizacion();
            }
        } else if (!"".equals(nombre.getText()) && !"".equals(Caja_IDENTIFICACION.getText())) {
            generarserieCotizacion();
            RegistraCotizacion();
            CotizacionDao.numeroserieIncrementarCotizacion(Integer.parseInt(lblNumeroSerieCotizacion.getText()));
        } else if (nombre.getText().equals("") && !"".equals(Caja_IDENTIFICACION.getText())) {
            JOptionPane.showMessageDialog(null, "DEBE RELLENAR EL NOMBRE");
            JOptionPane.showMessageDialog(null, "DEBE COMPLETAR EL NIT");
        }
    }

    public synchronized void RegistraCotizacion() {
        CotizacionesParametros = new Cotizaciones();
        CotizacionesParametros.setNitCliente(Caja_IDENTIFICACION.getText());
        CotizacionesParametros.setNombreCliente(nombre.getText());
        CotizacionesParametros.setTotalProductos(Float.parseFloat(jLabel44.getText()));
        CotizacionesParametros.setTotalCotizacion(Float.parseFloat(labeltotalenfacturacion.getText()));
        CotizacionesParametros.setTotalLetras(TotalLetras.getText());
        CotizacionesParametros.setNoCotizacion(lblNumeroSerieCotizacion.getText());
        CotizacionesParametros.setIdUsuario(PARAMETROS_USUARIOS.ID_USUARIO);
        CotizacionesParametros.setNombreUsuario(PARAMETROS_USUARIOS.ID_USUARIO);
        CotizacionesParametros.setObservacion(ObservacionVenta.getText());
        CotizacionDao.RegistraCotizacion(CotizacionesParametros);
        RegistraDetallesCotizacion();
    }

    public synchronized void RegistraDetallesCotizacion() {
        DetalleCotizaciones DetallesCotizacionesParametros = new DetalleCotizaciones();
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            String cod = TablaVentas.getValueAt(i, 0).toString();
            Float cant = Float.parseFloat(TablaVentas.getValueAt(i, 2).toString());
            Float precio = Float.parseFloat(TablaVentas.getValueAt(i, 3).toString());
            Float Desc = Float.parseFloat(TablaVentas.getValueAt(i, 5).toString());
            String Total = TablaVentas.getValueAt(i, 4).toString();
            String Nombre = TablaVentas.getValueAt(i, 1).toString();
            DetallesCotizacionesParametros.setNoCotizacion(String.valueOf(lblNumeroSerieCotizacion.getText()));
            DetallesCotizacionesParametros.setCodigoBarras(cod);
            DetallesCotizacionesParametros.setCantidadProductos(cant);
            DetallesCotizacionesParametros.setPrecioUnitario(precio);
            DetallesCotizacionesParametros.setDescuento(Desc);
            DetallesCotizacionesParametros.setTotal(Float.parseFloat(Total));
            DetallesCotizacionesParametros.setNombreProducto(Nombre);
            DetallesCotizacionesParametros.setProductoRegistrado(Integer.parseInt(TablaVentas.getValueAt(i, 6).toString()));
            CotizacionDao.RegistraDetallesCotizacion(DetallesCotizacionesParametros);
        }

        if (CheckVistaPreviaVenta.isSelected()) {
            VerDetalleCotizacion(lblNumeroSerieCotizacion.getText(), 0);
        } else {
            VerDetalleCotizacion(lblNumeroSerieCotizacion.getText(), 1);
        }
        LIMPIAR_DATOS_RESUMEN_VENTA();
        CotizacionDao.numeroserieIncrementarCotizacion(Integer.parseInt(lblNumeroSerieCotizacion.getText()));
        LIMPIAR_CLIENTE_POS();
        LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
        LIMPIAR_TABLA(TablaVentas);

    }
    
public void GenerarVenta() {
        //Detalles D;
        switch (TipoDocumento.getSelectedIndex()) {
            case 0 -> {
            switch (pagocon.getText()) {
                case "" -> JOptionPane.showMessageDialog(null, "¡AÚN NO INGRESO LA CANTIDAD DE PAGO!");
                case "0.00" -> {
                    if(VentanaFormaPago==true){
                        Pagos.toFront();
                    }else{
                        VentanaFormaPago = true;
                        Pagos.RELLENAR_PARAMETROS_FORMA_DE_PAGO(labeltotal.getText(), pagocon.getText(), cambio.getText(), Efectivo.getText(), Tarjeta.getText()
                                , Deposito.getText(), Cheque.getText(), CajaNumeroTransacción.getText(), ComboFormaPago.getText(), Integer.parseInt(MetodoPagoEntero.getText()), TotalIva.getText(), P_E.IVA_EMPRESA, SubTotal.getText());
                        Pagos.setVisible(true);
                    }
                }
                default -> GenerarFacturaElectronica();
            }
            }
            case 1 -> {
            switch (pagocon.getText()) {
                case "" -> JOptionPane.showMessageDialog(null, "¡AÚN NO INGRESO LA CANTIDAD DE PAGO!");
                case "0.00" -> {
                    int seleccion= JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO QUE EL PAGO SERÁ DE Q0.00?");
                    if(seleccion == 0){
                        Imprimir();
                        //  Ticket();
                    }else{
                        if(VentanaFormaPago==true){
                            Pagos.toFront();
                        }else{
                            VentanaFormaPago = true;
                            Pagos.RELLENAR_PARAMETROS_FORMA_DE_PAGO(this.labeltotal.getText(), this.pagocon.getText(), this.cambio.getText(), this.Efectivo.getText(), 
                                    this.Tarjeta.getText(), this.Deposito.getText(), this.Cheque.getText(), this.CajaNumeroTransacción.getText(), this.ComboFormaPago.getText(), 
                                    Integer.parseInt(this.MetodoPagoEntero.getText()), this.TotalIva.getText(), 
                                    P_E.IVA_EMPRESA, this.SubTotal.getText());
                            Pagos.setVisible(true);    
                        }
                    }
                }
                default -> Imprimir();
                // Ticket();
            }
            }
            case 2 -> {
                if(!"".equals(nombre.getText())){
                    generarserieCotizacion();
                    generarserieCotizacion();
                    AumentarStock();
                    GenerarCotizacion();
                }else{
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("RELLENAR CAMPO", "DEBE INGRESAR UN NOMBRE DE CLIENTE", DesktopNotify.ERROR, 14000L);
                }
            }
            case 3 -> {
                Imprimir();
            }
            case 4 -> {
                generarserieVale();
                JOptionPane.showMessageDialog(null, "¡ESTA OPCIÓN AÚN NO ESTÁ DISPONIBLE :(");
            }
            default -> {
            }
        }
    }
           
    public void VerDetalleMOVIMIENTO(String FormaDePago, String NumeroDeFactura, int AbrirVentana){
        int TiempoDeEspera;
        if(CheckBoxImpresionRapida==true){
            TiempoDeEspera = 500;
        }else{
            TiempoDeEspera = 2000;
        }
        
        if(!"".equals(NumeroDeFactura)){
            de= new Detalles(NumeroDeFactura, 0, 0, this.principal.P_O_S);
            
            if(AbrirVentana== 0){
             new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             de.setVisible(true);
             if(CheckImprimir.isSelected()){
            de.Imprimir();
        }
         }
     },
             TiempoDeEspera
     );   
            }else{
               new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             de.setVisible(false);
             if(CheckImprimir.isSelected()){
            de.Imprimir();
        }
         }
     },
             TiempoDeEspera
     );   
            }
            
        }else{
            JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR UN NÚMERO DE FACTURA!", "CONSULTA ERRÓNEA", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void VerDetalleCotizacion(String NumeroDeFactura, int AbrirVentana){
        if(!"".equals(NumeroDeFactura)){
            //DetalleCotizacion de= new DetalleCotizacion(NumeroDeFactura, 0, 0 this.principal.P_O_S);
            DetalleCotizacion de = new DetalleCotizacion(NumeroDeFactura, 0, 0, this.principal.P_O_S);
            if(AbrirVentana== 0){
             new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             de.setVisible(true);
             if(CheckImprimir.isSelected()){
            de.Imprimir();
        }
         }
     },
             2000
     );   
            }else{
               new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             de.setVisible(false);
             if(CheckImprimir.isSelected()){
            de.Imprimir();
        }
         }
     },
             2000
     );   
            }
            
            
        }else{
            JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR UN NÚMERO DE FACTURA!", "CONSULTA ERRÓNEA", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static Boolean VentanaBuscarProducto = false;

    public void SumarProductos() {
        int nu = TablaVentas.getRowCount();
        Double TotalStock23 = 0.00;
        if (nu != 0) {
            for (int i = 0; i < nu; i++) {
                double cal = Double.parseDouble(String.valueOf(TablaVentas.getModel().getValueAt(i, 2)));
                TotalStock23 = TotalStock23 + cal;
            }
            TotalDeProductosVendidos.setText(String.format("%.2f", TotalStock23));
            TotalTipoDeProductosPOS.setText(String.valueOf(nu));
            jLabel44.setText(String.format("%.2f", TotalStock23));
        }
    }

    public void BuscarProductoVenta_ID_VENTA(String Codigo) {

        if (!"".equals(Codigo)) {
            PrecioRe.setSelected(false);
            PrecioEs.setSelected(false);
            Final.setText(PrecioPublico.getText());
            PrecioPublico.setFont(new Font("Consolas", Font.BOLD, 18));
            PrecioRe.setFont(new Font("Consolas", Font.BOLD, 16));
            PrecioEs.setFont(new Font("Consolas", Font.BOLD, 16));

            Busqueda_ID_VENTA(Codigo);

        } else {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN CÓDIGO DE BARRAS", "CAMPO VACÍO", JOptionPane.ERROR_MESSAGE);
            IdVenta.requestFocus();
        }
    }

    public void BuscarProductoVenta(String Codigo) {

        if (!"".equals(Codigo)) {
            PrecioRe.setSelected(false);
            PrecioEs.setSelected(false);
            Final.setText(PrecioPublico.getText());
            PrecioPublico.setFont(new Font("Consolas", Font.BOLD, 18));
            PrecioRe.setFont(new Font("Consolas", Font.BOLD, 16));
            PrecioEs.setFont(new Font("Consolas", Font.BOLD, 16));

            BusquedaCodigoBarras(Codigo);

        } else {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN CÓDIGO DE BARRAS", "CAMPO VACÍO", JOptionPane.ERROR_MESSAGE);
            IdVenta.requestFocus();
        }
    }

    public Boolean Busqueda_ID_VENTA(String Codigo) {
        Boolean ESTADO_BUSQUEDA=false;
        pro = new Productos();
        proDao = new ProductosDao();
        //pro.setCodigoBarras(Codigo);
        //ImageIcon foto = proDao.BuscarProId(pro);
        pro = proDao.BuscarProID_VENTA(Codigo);
        if (pro.getNombre() != null) {
            if (pro.getEstado_Producto().equals("INACTIVO")) {
                JOptionPane.showMessageDialog(null, "¡PRODUCTO INACTIVO!\nNECESITA DARSE DE ALTA PARA PODER VENDER", "¡ATENCIÓN!", JOptionPane.ERROR_MESSAGE);
                IdVenta.requestFocus();
                ESTADO_BUSQUEDA=false;
            } else {
                ESTADO_BUSQUEDA=true;
                IdVenta.setText("" + pro.getCodigoBarras());
                NombreVenta.setText("" + pro.getNombre());
                PrecioPublico.setText("" + pro.getPublico().toString());
                PrecioEs.setText("" + pro.getPrecioEs().toString());
                PrecioRe.setText("" + pro.getPrecioRe().toString());
                Cantidad2.setText("" + pro.getCantidad());
                Final.setText("" + pro.getPublico().toString());
                lblVentaPrecio1.setText("" + pro.getNombreTiposDePrecio1());
                lblVentaPrecio2.setText("" + pro.getNombreTiposDePrecio2());
                lblVentaPrecio3.setText("" + pro.getNombreTiposDePrecio3());
                DescripcionProductoVenta.setText(" " + pro.getDescripcion());
                jCheckBox1.setSelected(pro.getAPLICAR_DESCUENTO());
                if (pro.getRuta() != null) {
                    PintarImagen(lblImagenVenta, CargarDatosRutas(1) + "\\" + pro.getRuta());
                    lblImagenVenta.setName(pro.getRuta());
                } else {
                    ImagenAmigoVenta();
                }
                if (pro.getCantidad() == 0) {
                    EstadoProducto.setText("INGRESADO");
                } else {
                    EstadoProducto.setText("INGRESADO");
                }
                PrecioPublico.setSelected(true);
                CantidadVenta.setText("1");
                CantidadVenta.requestFocus();
                CantidadVenta.addFocusListener(new FullSelectorListener());
                if ("0".equals(Cantidad2.getText()) || Cantidad2.getText() == "0.00") {
                    Cantidad2.setForeground(Color.red);
                } else {
                    Cantidad2.setForeground(Color.GREEN);
                }

                if (CheckIngresoAutomatico.isSelected()) {
                    AgregarProducto();
                }
            }
        } else {
            ESTADO_BUSQUEDA=false;
            JOptionPane.showMessageDialog(null, "¡EL PRODUCO NO EXISTE");
            IdVenta.requestFocus();
        }
        return ESTADO_BUSQUEDA;
    }
    
    public Boolean BusquedaCodigoBarras(String Codigo) {
        Boolean ESTADO_BUSQUEDA=false;
        pro = new Productos();
        proDao = new ProductosDao();
        //pro.setCodigoBarras(Codigo);
        //ImageIcon foto = proDao.BuscarProId(pro);
        pro = proDao.BuscarProCodigoBarras(Codigo);
        if (pro.getNombre() != null) {
            if (pro.getEstado_Producto().equals("INACTIVO")) {
                JOptionPane.showMessageDialog(null, "¡PRODUCTO INACTIVO!\nNECESITA DARSE DE ALTA PARA PODER VENDER", "¡ATENCIÓN!", JOptionPane.ERROR_MESSAGE);
                IdVenta.requestFocus();
                ESTADO_BUSQUEDA=false;
            } else {
                ESTADO_BUSQUEDA=true;
                IdVenta.setText("" + pro.getCodigoBarras());
                NombreVenta.setText("" + pro.getNombre());
                PrecioPublico.setText("" + pro.getPublico().toString());
                PrecioEs.setText("" + pro.getPrecioEs().toString());
                PrecioRe.setText("" + pro.getPrecioRe().toString());
                Cantidad2.setText("" + pro.getCantidad());
                Final.setText("" + pro.getPublico().toString());
                lblVentaPrecio1.setText("" + pro.getNombreTiposDePrecio1());
                lblVentaPrecio2.setText("" + pro.getNombreTiposDePrecio2());
                lblVentaPrecio3.setText("" + pro.getNombreTiposDePrecio3());
                DescripcionProductoVenta.setText(" " + pro.getDescripcion());
                jCheckBox1.setSelected(pro.getAPLICAR_DESCUENTO());
                if (pro.getRuta() != null) {
                    PintarImagen(lblImagenVenta, CargarDatosRutas(1) + "\\" + pro.getRuta());
                    lblImagenVenta.setName(pro.getRuta());
                } else {
                    ImagenAmigoVenta();
                }
                if (pro.getCantidad() == 0) {
                    EstadoProducto.setText("INGRESADO");
                } else {
                    EstadoProducto.setText("INGRESADO");
                }
                PrecioPublico.setSelected(true);
                CantidadVenta.setText("1");
                Final.requestFocus();
                Final.addFocusListener(new FullSelectorListener());
                if ("0".equals(Cantidad2.getText()) || Cantidad2.getText() == "0.00") {
                    Cantidad2.setForeground(Color.red);
                } else {
                    Cantidad2.setForeground(Color.GREEN);
                }

                if (CheckIngresoAutomatico.isSelected()) {
                    AgregarProducto();
                }
            }
        } else {
            ESTADO_BUSQUEDA=false;
            JOptionPane.showMessageDialog(null, "¡EL PRODUCO NO EXISTE");
            IdVenta.requestFocus();
        }
        return ESTADO_BUSQUEDA;
    }

    public void BuscarProductoVentaPorNombre(String Nombre) {
        PrecioPublico.setFont(new Font("Consolas", Font.BOLD, 18));
        PrecioRe.setFont(new Font("Consolas", Font.BOLD, 16));
        PrecioEs.setFont(new Font("Consolas", Font.BOLD, 16));
        if (!"".equals(Nombre)) {
            pro = new Productos();
            proDao = new ProductosDao();
            pro = proDao.BuscarProPorNombre(Nombre);
            if (pro.getNombre() != null) {
                if (pro.getEstado_Producto().equals("INACTIVO")) {
                    JOptionPane.showMessageDialog(null, "¡PRODUCTO INACTIVO!\nNECESITA DARSE DE ALTA PARA PODER VENDER", "¡ATENCIÓN!", JOptionPane.ERROR_MESSAGE);
                    IdVenta.requestFocus();
                } else {
                    IdVenta.setText("" + pro.getCodigoBarras());
                    NombreVenta.setText("" + pro.getNombre());
                    PrecioPublico.setText("" + pro.getPublico());
                    PrecioEs.setText("" + pro.getPrecioEs());
                    PrecioRe.setText("" + pro.getPrecioRe());
                    Cantidad2.setText("" + pro.getCantidad());
                    lblVentaPrecio1.setText("" + pro.getNombreTiposDePrecio1());
                    lblVentaPrecio2.setText("" + pro.getNombreTiposDePrecio2());
                    lblVentaPrecio3.setText("" + pro.getNombreTiposDePrecio3());
                    DescripcionProductoVenta.setText(" " + pro.getDescripcion());
                    jCheckBox1.setSelected(pro.getAPLICAR_DESCUENTO());
                    if (pro.getRuta() != null) {
                        PintarImagen(lblImagenVenta, CargarDatosRutas(1) + "\\" + pro.getRuta());
                    } else {
                        ImagenAmigoVenta();
                    }

                    if (pro.getCantidad() == 0) {
                        EstadoProducto.setText("INGRESADO");
                    } else {
                        EstadoProducto.setText("INGRESADO");
                    }
                    Final.requestFocus();
                    Final.setText("" + pro.getPublico());
                    PrecioPublico.setSelected(true);
                    CantidadVenta.setText("1");
                    Final.addFocusListener(new FullSelectorListener());
                    if (Float.parseFloat(Cantidad2.getText()) == 0) {
                        Cantidad2.setForeground(Color.red);
                    } else {
                        Cantidad2.setForeground(Color.GREEN);
                    }
                }
            } else if (pro.getNombre() == null) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("VERIFIQUE EL NOMBRE", "EL PRODUCTO " + NombreVenta.getText() + " NO EXISTE!", DesktopNotify.ERROR, 14000L);
            }

        } else {
            JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NOMBRE", "CAMPO VACÍO", JOptionPane.ERROR_MESSAGE);
            NombreVenta.requestFocus();
        }
    }
    
    public void generarserie() {
    int NumeroInterno = Vdao.numeroserie();
        if (NumeroInterno == 0) {
            factura.setText("1");

        } else {
            /*int increment = NumeroInterno;
            increment = increment + 1;*/
            factura.setText("" + NumeroInterno);
            
        }
    }
    
    public void generarserieCotizacion() {
        CotizacionDao = new CotizacionesDao();
        String serie = CotizacionDao.numeroserieCotizaciones();
        if (serie == null) {
            lblNumeroSerieCotizacion.setText("1");

        } else {
            lblNumeroSerieCotizacion.setText("" + serie);
        }
    }
    
    public void generarserie_SALIDAS() {
    int NumeroInterno_Salidas = Vdao.numeroserie_Salidas();
        if (NumeroInterno_Salidas == 0) {
            lblSerie_Salidas.setText("1");

        } else {
            /*int increment = NumeroInterno;
            increment = increment + 1;*/
            lblSerie_Salidas.setText("" + NumeroInterno_Salidas);
            
        }
    }
    
    public void generarserieVale() {
        String serie = Vdao.numeroserieVale();
        if (serie == null) {
            Vale.setText("1");

        } else {
            int increment = Integer.parseInt(serie);
            increment = increment + 1;
            Vale.setText("" + increment);
        }
    }

    public void Cambio() {
        if (!"".equals(labeltotal.getText())) {
            Double a = Double.parseDouble(labeltotal.getText());
            Double b = Double.parseDouble(pagocon.getText());
            Double c = b - a;

            cambio.setText(String.format("%.2f",c));
        }
    }
       
    public synchronized void CargarPaises(){
        cliDao.Pais(PaisCliente);
        SiglaPais();
    }
    
    public synchronized void SiglaPais(){
        SiglaPais.setText(cliDao.ConsultaSiglaPais(PaisCliente));
    }
    
    public void GenerarFacturaElectronica(){
        if (VALIDAR_CAJAS_CLIENTE_POS() == false) {
            if(Float.parseFloat(labeltotalenfacturacion.getText())>=2500){
            JOptionPane.showMessageDialog(this, "¡DEBE INGRESAR UN NÚMERO DE NIT O CUI PARA PODER GENERAR ESTA FACTURA!", "*****MAYOR A 2500****", JOptionPane.ERROR_MESSAGE);
        }else{
              int i = JOptionPane.showConfirmDialog(null, "¿DESEA COMPLETAR LA VENTA SIN UN CLIENTE?\n *Esta acción asignará automaticamente un CONSUMIDOR FINAL a la venta");
            if (i == 0) {
                ConsultarNit_CUIFinal("CF");
                EnviarParametrosAXML();
            }else{
            EnviarParametrosAXML();
        }
            }
        }else{
            EnviarParametrosAXML();
        }
    }

    public void EnviarParametrosAXML() {
        if (P_E.TOKEN_CERTIFICADOR.equals("")) {
            OT.ObtenerToken();
        }
        try {

            Double CalcularImpuestoMontoGravable;
            Double CalcularImpuestoMontoImpuesto;
            Double Total = 0.00;
            GenerarXML GenerarXMLFactura = new GenerarXML();
            EnvioDatosFacturar EDF = new EnvioDatosFacturar();
            CertificarFactura CFact = new CertificarFactura();
            RespuestaCertificacion CFACTMODEL = new RespuestaCertificacion();

            EDF.setFechaHoraEmision(Fecha() + "T" + Hora());
            EDF.setCodigoMoneda("GTQ");
            EDF.setTipoDocumento("FACT");

            EDF.setNITEmisor(P_E.NIT_EMPRESA);
            EDF.setNombreEmisor(P_E.PROPIETARIO_EMPRESA);
            EDF.setCodigoEstablecimiento(P_E.CODIGOESTABLECIMIENTO_EMPRESA);
            EDF.setNombreComercial(P_E.NOMBRE_EMPRESA);
            EDF.setAfiliacionIVA(P_E.AFILICACIONIVA_EMPRESA);
            EDF.setDireccionEmisor(P_E.DIRECCION_EMPRESA);
            EDF.setCodigoPostalEmisor(P_E.CODIGOPOSTAL_EMPRESA);
            EDF.setMunicipioEmisor(P_E.MUNICIPIO_EMPRESA);
            EDF.setDepartamentoEmisor(P_E.DEPARTAMENTO_EMPRESA);
            EDF.setPaisEmisor(P_E.PAIS_EMPRESA);

            if(COMBO_TIPO_IDENTIFICACION.getSelectedItem().equals("CUI")){
             EDF.setTipo_Especial("CUI");   
            }
            EDF.setNombreReceptor(nombre.getText());
            EDF.setIDReceptor(Caja_IDENTIFICACION.getText());
            EDF.setDireccionReceptor(direccion.getText());
            EDF.setCodigoPostalReceptor(CodigoPostalCliente.getText());
            EDF.setMunicipioReceptor(MunicipioCliente.getText());
            EDF.setDepartamentoReceptor(DepartamentoCliente.getText());
            EDF.setPaisReceptor(SiglaPais.getText());

            EDF.setTipoFrase("1");
            EDF.setCodigoEscenario("1");

            for (int i = 0; i < TablaVentas.getRowCount(); i++) {
                EDF.setTotalDeProductos(Integer.parseInt(TotalTipoDeProductosPOS.getText()));
                EDF.setNumeroLineaProducto(String.valueOf(i + 1));
                EDF.setBienOServicioProducto("B");
                EDF.setCantidadProducto(TablaVentas.getValueAt(i, 2).toString());
                EDF.setUnidadMedidaProducto("UN");
                EDF.setDescripcionProducto(TablaVentas.getValueAt(i, 1).toString());
                EDF.setPrecioUnitarioProducto(TablaVentas.getValueAt(i, 3).toString());
                EDF.setPrecioProducto(TablaVentas.getValueAt(i, 3).toString());
                EDF.setDescuentoProducto(TablaVentas.getValueAt(i, 4).toString());
                EDF.setTotalProducto(TablaVentas.getValueAt(i, 6).toString());

                CalcularImpuestoMontoGravable = Double.parseDouble(TablaVentas.getValueAt(i, 6).toString()) / 1.12;
                CalcularImpuestoMontoImpuesto = Double.parseDouble(TablaVentas.getValueAt(i, 6).toString()) - CalcularImpuestoMontoGravable;
                Total += CalcularImpuestoMontoImpuesto;
                EDF.setNombreCortoImpuesto("IVA");
                EDF.setCodigoUnidadGravableImpuesto("1");
                EDF.setMontoGravableImpuesto(String.format("%.4f", CalcularImpuestoMontoGravable));
                EDF.setMontoImpuesto(String.format("%.4f", CalcularImpuestoMontoImpuesto));

            }

            EDF.setNombreCortoTotales("IVA");
            EDF.setTotalMontoImpuestoTotales(String.format("%.4f", Total));
            EDF.setGranTotalTotales(labeltotalenfacturacion.getText());
            NUMERO_INTERNO_FACTURA_ELECTRONICA = "0000"+factura.getText();
            EDF.setREFERENCIA_INTERNA(NUMERO_INTERNO_FACTURA_ELECTRONICA);
            EDF.setFECHA_REFERENCIA(Fecha() + "T" + Hora());
            EDF.setVALIDAR_REFERENCIA_INTERNA("VALIDAR");
            Boolean Resultado = GenerarXMLFactura.GenerarXMLFactura(EDF, TablaVentas, String.valueOf(COMBO_TIPO_IDENTIFICACION.getSelectedItem()));
            if (Resultado == true) {
                DatosUsuario DU = new DatosUsuario();
                DU.setNit(P_E.NIT_EMPRESA);
                DU.setUsuario(P_E.USUARIO_CERTIFICADOR);
                DU.setContrasenia(P_E.CONTRASENIA_CERTIFICADOR);

                CFACTMODEL = CFact.CertificarFactura(DU, P_E.TOKEN_CERTIFICADOR);
                CajaFechaAutorizacion.setText(CFACTMODEL.getFecha_de_certificacion());
                CajaNumeroAutorizacion.setText(CFACTMODEL.getAutorizacion());
                CajaSerieCertificacion.setText(CFACTMODEL.getSerie());
                CajaNumeroDocumento.setText(CFACTMODEL.getNUMERO());
                if (CFACTMODEL.isValidado() == true) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ESTADO DE CERTIFICACIÓN", CFACTMODEL.getEstadoCertificacion(), DesktopNotify.SUCCESS, 10000L);
                    Imprimir();
                } else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ESTADO DE CERTIFICACIÓN", CFACTMODEL.getEstadoCertificacion(), DesktopNotify.INFORMATION, 10000L);
                }

            }
        } catch (NumberFormatException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ESTADO DE CERTIFICACIÓN", "ERROR AL ENVIAR PARAMETROS A XML MÉTODO:(EnviarParametrosAXML)\n" + e, DesktopNotify.ERROR, 10000L);
        }
    }
    
    public void GenerarNotaDeCredito(){
        //EnviarParametrosNotaDeCreditoXML();
    }
    
    public void FORMA_DE_PAGO(){
                Pagos.RELLENAR_PARAMETROS_FORMA_DE_PAGO(labeltotal.getText(), pagocon.getText(), cambio.getText(), Efectivo.getText(), 
                        Tarjeta.getText(), Deposito.getText(), Cheque.getText(), CajaNumeroTransacción.getText(), ComboFormaPago.getText(), 
                Integer.parseInt(MetodoPagoEntero.getText()), TotalIva.getText(), P_E.IVA_EMPRESA, SubTotal.getText());
            Pagos.TOTALES_SIN_INTERACCION();
        
    }
    
    public void RellenarMetodoPago(String Pago, String Cambio, String EfectivoPagado, String DepositoPagado, String TarjetaPagada, String ChequePagado, String NumeroTransacciones, String Metodo, String MetodoPagoHecho, 
            String TotalIvaPagado, String SubTotalPagado){
        pagocon.setText(Pago);
        cambio.setText(Cambio);
        Efectivo.setText(EfectivoPagado);
        Deposito.setText(DepositoPagado);
        Tarjeta.setText(TarjetaPagada);
        Cheque.setText(ChequePagado);
        ComboFormaPago.setText(Metodo);
        MetodoPagoEntero.setText(MetodoPagoHecho);
        CajaNumeroTransacción.setText(NumeroTransacciones);
        TotalIva.setText(TotalIvaPagado);
        SubTotal.setText(SubTotalPagado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Facturacion = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        lblNumeroTransacción = new javax.swing.JLabel();
        ComboFormaPago = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        CajaNumeroTransacción = new javax.swing.JTextArea();
        MetodoPagoEntero = new javax.swing.JLabel();
        jPanel60 = new javax.swing.JPanel();
        labeltotal = new javax.swing.JLabel();
        Efectivo = new javax.swing.JLabel();
        Deposito = new javax.swing.JLabel();
        Cheque = new javax.swing.JLabel();
        Tarjeta = new javax.swing.JLabel();
        TotalIva = new javax.swing.JLabel();
        SubTotal = new javax.swing.JLabel();
        jPanel59 = new javax.swing.JPanel();
        PANELPRODUCTOS = new javax.swing.JPanel();
        TotalDeProductosVendidos = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TotalLetras = new javax.swing.JTextArea();
        jLabel99 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        ObservacionVenta = new javax.swing.JTextArea();
        jPanel67 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        CajaNumeroAutorizacion = new javax.swing.JTextField();
        CajaNumeroDocumento = new javax.swing.JTextField();
        CajaSerieCertificacion = new javax.swing.JTextField();
        CajaFechaAutorizacion = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        IMAGEN = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DESCRIPCION = new javax.swing.JTextArea();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        Venta = new javax.swing.JPanel();
        PanleDatosProductoPOS = new javax.swing.JPanel();
        jPanel73 = new javax.swing.JPanel();
        lblVentaPrecio1 = new javax.swing.JLabel();
        PrecioPublico = new javax.swing.JRadioButton();
        lblVentaPrecio2 = new javax.swing.JLabel();
        PrecioEs = new javax.swing.JRadioButton();
        lblVentaPrecio3 = new javax.swing.JLabel();
        PrecioRe = new javax.swing.JRadioButton();
        Final = new javax.swing.JTextField();
        IdVenta = new javax.swing.JTextField();
        lblCodigoPOS = new javax.swing.JLabel();
        NombreVenta = new javax.swing.JTextField();
        lblNombrePOS = new javax.swing.JLabel();
        lblCantidadPOS = new javax.swing.JLabel();
        CantidadVenta = new javax.swing.JTextField();
        lblPrecioFinalPOS = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel41 = new javax.swing.JLabel();
        Cantidad2 = new javax.swing.JTextField();
        CheckIngresoAutomatico = new javax.swing.JCheckBox();
        CheckVistaPreviaVenta = new javax.swing.JCheckBox();
        CheckImprimir = new javax.swing.JCheckBox();
        jScrollPane20 = new javax.swing.JScrollPane();
        jPanel82 = new javax.swing.JPanel();
        lblImagenVenta = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DescripcionProductoVenta = new javax.swing.JTextArea();
        EstadoProducto = new javax.swing.JLabel();
        ID_PRODUCTO = new javax.swing.JLabel();
        TOTAL_INGRESADO = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel69 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaVentas = new javax.swing.JTable();
        factura = new javax.swing.JTextField();
        lblNumeroSerieCotizacion = new javax.swing.JTextField();
        Vale = new javax.swing.JTextField();
        lblSerie_Salidas = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        PanelBotonesPOS = new javax.swing.JPanel();
        BtnGenerarVentaPOS = new javax.swing.JButton();
        BtnCancelarVentaPOS = new javax.swing.JButton();
        BtnBuscarProductoPOS = new javax.swing.JButton();
        BtnAgregarPagoPOS = new javax.swing.JButton();
        BtnAgregarObservacionPOS = new javax.swing.JButton();
        TipoDocumento = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        lbl_CANTIDAD_DESCUENTO = new javax.swing.JLabel();
        PanelTotalVentaPOS = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        cambio = new javax.swing.JLabel();
        pagocon = new javax.swing.JLabel();
        labeltotalenfacturacion = new javax.swing.JLabel();
        TotalTipoDeProductosPOS = new javax.swing.JLabel();
        PanelClientePOS = new javax.swing.JPanel();
        IdCliente = new javax.swing.JTextField();
        Caja_IDENTIFICACION = new javax.swing.JTextField();
        direccion = new javax.swing.JTextField();
        BtnNuevoClientePOS = new javax.swing.JButton();
        BtnAgregarClientePOS = new javax.swing.JButton();
        MunicipioCliente = new javax.swing.JTextField();
        DepartamentoCliente = new javax.swing.JTextField();
        PaisCliente = new javax.swing.JComboBox<>();
        CodigoPostalCliente = new javax.swing.JTextField();
        SiglaPais = new javax.swing.JTextField();
        nombre = new rojerusan.RSMetroTextFullPlaceHolder();
        jLabel8 = new javax.swing.JLabel();
        COMBO_TIPO_IDENTIFICACION = new javax.swing.JComboBox<>();

        Facturacion.setBackground(new java.awt.Color(231, 140, 90));

        jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Forma de Pago"));

        lblNumeroTransacción.setText("N° de transacción");

        ComboFormaPago.setText("EFECTIVO");

        CajaNumeroTransacción.setColumns(20);
        CajaNumeroTransacción.setRows(5);
        CajaNumeroTransacción.setText("CERO QUETZALES");
        jScrollPane9.setViewportView(CajaNumeroTransacción);

        MetodoPagoEntero.setText("1");
        MetodoPagoEntero.setBorder(javax.swing.BorderFactory.createTitledBorder("ID METÓDO DE PAGO"));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(lblNumeroTransacción)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(ComboFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MetodoPagoEntero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ComboFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MetodoPagoEntero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumeroTransacción)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTALES"));

        labeltotal.setBackground(new java.awt.Color(226, 226, 226));
        labeltotal.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        labeltotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeltotal.setText("0.00");
        labeltotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL"));

        Efectivo.setText("0.00");
        Efectivo.setBorder(javax.swing.BorderFactory.createTitledBorder("EFECTIVO"));

        Deposito.setText("0.00");
        Deposito.setBorder(javax.swing.BorderFactory.createTitledBorder("DEPÓSITO"));

        Cheque.setText("0.00");
        Cheque.setBorder(javax.swing.BorderFactory.createTitledBorder("CHEQUE"));

        Tarjeta.setText("0.00");
        Tarjeta.setBorder(javax.swing.BorderFactory.createTitledBorder("TARJETA"));

        TotalIva.setText("0.00");
        TotalIva.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TotalIVA"));

        SubTotal.setText("0.00");
        SubTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SubTotal"));

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Tarjeta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Deposito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Efectivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labeltotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cheque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(581, 581, 581))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(labeltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Deposito, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Cheque, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Facturacion.addTab("FORMA DE PAGO", jPanel11);

        jPanel59.setBackground(new java.awt.Color(231, 165, 90));

        PANELPRODUCTOS.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL DE PRODUCTOS"));

        TotalDeProductosVendidos.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        TotalDeProductosVendidos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PANELPRODUCTOSLayout = new javax.swing.GroupLayout(PANELPRODUCTOS);
        PANELPRODUCTOS.setLayout(PANELPRODUCTOSLayout);
        PANELPRODUCTOSLayout.setHorizontalGroup(
            PANELPRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TotalDeProductosVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PANELPRODUCTOSLayout.setVerticalGroup(
            PANELPRODUCTOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TotalDeProductosVendidos, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        TotalLetras.setColumns(20);
        TotalLetras.setRows(5);
        jScrollPane12.setViewportView(TotalLetras);

        jLabel99.setText("TOTAL EN LETRAS:");

        ObservacionVenta.setColumns(20);
        ObservacionVenta.setRows(5);
        ObservacionVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "OBSERVACIÓNES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jScrollPane15.setViewportView(ObservacionVenta);

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PANELPRODUCTOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addComponent(jLabel99)
                        .addGap(0, 553, Short.MAX_VALUE))
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PANELPRODUCTOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        Facturacion.addTab("RESÚMEN", jPanel59);

        jLabel37.setText("NÚMERO DE AUTORIZACIÓN:");

        CajaNumeroAutorizacion.setText(" ");

        CajaNumeroDocumento.setText(" ");
        CajaNumeroDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CajaNumeroDocumentoActionPerformed(evt);
            }
        });

        CajaSerieCertificacion.setText(" ");

        CajaFechaAutorizacion.setText(" ");

        jLabel38.setText("NÚMERO DE DOCUMENTO");

        jLabel39.setText("SERIE");

        jLabel42.setText("FECHA DE AUTORIZACIÓN");

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CajaSerieCertificacion)
                            .addComponent(CajaFechaAutorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)))
                    .addGroup(jPanel67Layout.createSequentialGroup()
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CajaNumeroAutorizacion)
                            .addComponent(CajaNumeroDocumento))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel67Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaNumeroAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaSerieCertificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaFechaAutorizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(258, 258, 258))
        );

        Facturacion.addTab("AUTORIZACIÓNES", jPanel67);

        DESCRIPCION.setColumns(20);
        DESCRIPCION.setRows(5);
        jScrollPane3.setViewportView(DESCRIPCION);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBorder(null);
        setAutoscrolls(true);

        PanleDatosProductoPOS.setBackground(new java.awt.Color(239, 118, 0));
        PanleDatosProductoPOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PanleDatosProductoPOSMouseReleased(evt);
            }
        });

        jPanel73.setBackground(new java.awt.Color(239, 118, 0));

        lblVentaPrecio1.setText("P/UNITARIO");

        PrecioPublico.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        PrecioPublico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioPublicoActionPerformed(evt);
            }
        });

        lblVentaPrecio2.setText("P/ESPECIAL");

        PrecioEs.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        PrecioEs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioEsActionPerformed(evt);
            }
        });

        lblVentaPrecio3.setText("P/REVENTA");

        PrecioRe.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        PrecioRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrecioReActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVentaPrecio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVentaPrecio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(lblVentaPrecio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PrecioRe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PrecioPublico, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                    .addComponent(PrecioEs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVentaPrecio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PrecioPublico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PrecioEs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(lblVentaPrecio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PrecioRe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblVentaPrecio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        Final.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Final.setForeground(new java.awt.Color(255, 0, 51));
        Final.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                FinalKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                FinalKeyTyped(evt);
            }
        });

        IdVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IdVentaKeyPressed(evt);
            }
        });

        lblCodigoPOS.setText("CÓDIGO");

        NombreVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NombreVentaKeyPressed(evt);
            }
        });

        lblNombrePOS.setText("NOMBRE");

        lblCantidadPOS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblCantidadPOS.setText("CANTIDAD ");

        CantidadVenta.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        CantidadVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadVentaActionPerformed(evt);
            }
        });
        CantidadVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CantidadVentaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadVentaKeyTyped(evt);
            }
        });

        lblPrecioFinalPOS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblPrecioFinalPOS.setText("PRECIO FINAL");

        jPanel29.setBackground(new java.awt.Color(239, 118, 0));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compras.png"))); // NOI18N
        jButton7.setText("AGREGAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        jButton8.setText("NUEVO (F5)");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel41.setText("STOCK:");

        Cantidad2.setEditable(false);
        Cantidad2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        CheckIngresoAutomatico.setText("AUTOMATICO");
        CheckIngresoAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckIngresoAutomaticoActionPerformed(evt);
            }
        });

        CheckVistaPreviaVenta.setText("VISTA PREVIA");
        CheckVistaPreviaVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckVistaPreviaVentaActionPerformed(evt);
            }
        });

        CheckImprimir.setText("PRINT");
        CheckImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckImprimirActionPerformed(evt);
            }
        });

        jScrollPane20.getVerticalScrollBar().setUnitIncrement(16);
        jScrollPane20.setBorder(null);
        jScrollPane20.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel82.setBackground(new java.awt.Color(239, 118, 0));

        lblImagenVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImagenVentaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblImagenVentaMouseReleased(evt);
            }
        });

        DescripcionProductoVenta.setEditable(false);
        DescripcionProductoVenta.setColumns(20);
        DescripcionProductoVenta.setRows(5);
        DescripcionProductoVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DescripcionProductoVentaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                DescripcionProductoVentaMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(DescripcionProductoVenta);

        javax.swing.GroupLayout jPanel82Layout = new javax.swing.GroupLayout(jPanel82);
        jPanel82.setLayout(jPanel82Layout);
        jPanel82Layout.setHorizontalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel82Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblImagenVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel82Layout.setVerticalGroup(
            jPanel82Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel82Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImagenVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane20.setViewportView(jPanel82);

        EstadoProducto.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        TOTAL_INGRESADO.setText("0.0");

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jCheckBox1.setText("%");

        javax.swing.GroupLayout PanleDatosProductoPOSLayout = new javax.swing.GroupLayout(PanleDatosProductoPOS);
        PanleDatosProductoPOS.setLayout(PanleDatosProductoPOSLayout);
        PanleDatosProductoPOSLayout.setHorizontalGroup(
            PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanleDatosProductoPOSLayout.createSequentialGroup()
                .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanleDatosProductoPOSLayout.createSequentialGroup()
                        .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(PanleDatosProductoPOSLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cantidad2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCantidadPOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanleDatosProductoPOSLayout.createSequentialGroup()
                                .addComponent(Final, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblPrecioFinalPOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PanleDatosProductoPOSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(IdVenta)
                            .addComponent(jScrollPane20)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanleDatosProductoPOSLayout.createSequentialGroup()
                                .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblCodigoPOS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CheckImprimir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanleDatosProductoPOSLayout.createSequentialGroup()
                                        .addComponent(CheckIngresoAutomatico)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CheckVistaPreviaVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(PanleDatosProductoPOSLayout.createSequentialGroup()
                                        .addComponent(EstadoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ID_PRODUCTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TOTAL_INGRESADO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(lblNombrePOS, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NombreVenta, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap())
        );
        PanleDatosProductoPOSLayout.setVerticalGroup(
            PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanleDatosProductoPOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckIngresoAutomatico)
                    .addComponent(CheckVistaPreviaVenta)
                    .addComponent(CheckImprimir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblCodigoPOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ID_PRODUCTO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EstadoProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TOTAL_INGRESADO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IdVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombrePOS, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanleDatosProductoPOSLayout.createSequentialGroup()
                        .addComponent(lblPrecioFinalPOS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Final, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadPOS)
                        .addGap(8, 8, 8)
                        .addComponent(CantidadVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanleDatosProductoPOSLayout.createSequentialGroup()
                        .addGroup(PanleDatosProductoPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Cantidad2, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel73, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        TablaVentas.setBackground(new java.awt.Color(242, 242, 242));
        TablaVentas.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        TablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto", "Cantidad", "PRECIO", "%", "PRECIO CON %", "TOTAL", "VALIDAR", "ID", "Acciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaVentas.setAutoscrolls(false);
        TablaVentas.setFocusable(false);
        TablaVentas.setRowHeight(32);
        TablaVentas.getTableHeader().setReorderingAllowed(false);
        TablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaVentasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaVentas);
        if (TablaVentas.getColumnModel().getColumnCount() > 0) {
            TablaVentas.getColumnModel().getColumn(0).setPreferredWidth(30);
            TablaVentas.getColumnModel().getColumn(1).setPreferredWidth(300);
            TablaVentas.getColumnModel().getColumn(8).setMaxWidth(30);
        }

        factura.setEditable(false);
        factura.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        factura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        factura.setBorder(null);
        factura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturaMouseClicked(evt);
            }
        });

        lblNumeroSerieCotizacion.setEditable(false);
        lblNumeroSerieCotizacion.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblNumeroSerieCotizacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblNumeroSerieCotizacion.setBorder(null);

        Vale.setEditable(false);
        Vale.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Vale.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Vale.setBorder(null);

        lblSerie_Salidas.setEditable(false);
        lblSerie_Salidas.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        lblSerie_Salidas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSerie_Salidas.setBorder(null);
        lblSerie_Salidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblSerie_SalidasActionPerformed(evt);
            }
        });

        jLabel1.setText("INTERNO");

        jLabel2.setText("COTIZACION");

        jLabel3.setText("VALES");

        jLabel4.setText("SALIDAS");

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addComponent(factura, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(lblNumeroSerieCotizacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Vale, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(lblSerie_Salidas, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel69Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(factura, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lblNumeroSerieCotizacion)
                    .addComponent(Vale)
                    .addComponent(lblSerie_Salidas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        PanelBotonesPOS.setBackground(new java.awt.Color(51, 153, 255));

        BtnGenerarVentaPOS.setBackground(new java.awt.Color(0, 200, 0));
        BtnGenerarVentaPOS.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        BtnGenerarVentaPOS.setText("GENERAR(F8)");
        BtnGenerarVentaPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGenerarVentaPOSActionPerformed(evt);
            }
        });

        BtnCancelarVentaPOS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BtnCancelarVentaPOS.setForeground(new java.awt.Color(255, 0, 0));
        BtnCancelarVentaPOS.setText("CANCELAR VENTA");
        BtnCancelarVentaPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarVentaPOSActionPerformed(evt);
            }
        });

        BtnBuscarProductoPOS.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        BtnBuscarProductoPOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarPequeño.png"))); // NOI18N
        BtnBuscarProductoPOS.setText("BUSCAR PRODUCTO(F10)");
        BtnBuscarProductoPOS.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnBuscarProductoPOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnBuscarProductoPOS.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarPequeño.png"))); // NOI18N
        BtnBuscarProductoPOS.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/BuscarGrande.png"))); // NOI18N
        BtnBuscarProductoPOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnBuscarProductoPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarProductoPOSActionPerformed(evt);
            }
        });

        BtnAgregarPagoPOS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        BtnAgregarPagoPOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/chart_sales_performance_coins_money_icon_157294.png"))); // NOI18N
        BtnAgregarPagoPOS.setText("AGREGAR PAGO (F12)");
        BtnAgregarPagoPOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnAgregarPagoPOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnAgregarPagoPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarPagoPOSActionPerformed(evt);
            }
        });

        BtnAgregarObservacionPOS.setText("<html>AGREGAR OBSERVACIÓN (F11)</html>");
        BtnAgregarObservacionPOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarObservacionPOSActionPerformed(evt);
            }
        });

        TipoDocumento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURA", "PROFORMA", "COTIZACIÓN", "SALIDAS" }));
        TipoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDocumentoActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--SIN PROMOCIÓN--", "DIA DEL PADRE | 5%" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText("TOTAL %:");

        lbl_CANTIDAD_DESCUENTO.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_CANTIDAD_DESCUENTO.setForeground(new java.awt.Color(255, 255, 255));
        lbl_CANTIDAD_DESCUENTO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_CANTIDAD_DESCUENTO.setText("5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_CANTIDAD_DESCUENTO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 150, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_CANTIDAD_DESCUENTO))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelBotonesPOSLayout = new javax.swing.GroupLayout(PanelBotonesPOS);
        PanelBotonesPOS.setLayout(PanelBotonesPOSLayout);
        PanelBotonesPOSLayout.setHorizontalGroup(
            PanelBotonesPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotonesPOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelBotonesPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnGenerarVentaPOS, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(BtnCancelarVentaPOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnAgregarObservacionPOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelBotonesPOSLayout.createSequentialGroup()
                        .addComponent(BtnAgregarPagoPOS, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(BtnBuscarProductoPOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelBotonesPOSLayout.setVerticalGroup(
            PanelBotonesPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotonesPOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnBuscarProductoPOS, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAgregarPagoPOS, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAgregarObservacionPOS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TipoDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnCancelarVentaPOS, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnGenerarVentaPOS, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelTotalVentaPOS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PanelTotalVentaPOSMouseClicked(evt);
            }
        });

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("0");
        jLabel44.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ITEMS UNIDAD"));

        cambio.setBackground(new java.awt.Color(226, 226, 226));
        cambio.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        cambio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cambio.setText("0.00");
        cambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CAMBIO"));

        pagocon.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        pagocon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pagocon.setText("0.00");
        pagocon.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "EFECTIVO"));

        labeltotalenfacturacion.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        labeltotalenfacturacion.setForeground(new java.awt.Color(255, 0, 51));
        labeltotalenfacturacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeltotalenfacturacion.setText("0.00");
        labeltotalenfacturacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL"));

        TotalTipoDeProductosPOS.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TotalTipoDeProductosPOS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalTipoDeProductosPOS.setText("0");
        TotalTipoDeProductosPOS.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ITEMS"));

        javax.swing.GroupLayout PanelTotalVentaPOSLayout = new javax.swing.GroupLayout(PanelTotalVentaPOS);
        PanelTotalVentaPOS.setLayout(PanelTotalVentaPOSLayout);
        PanelTotalVentaPOSLayout.setHorizontalGroup(
            PanelTotalVentaPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTotalVentaPOSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalTipoDeProductosPOS, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labeltotalenfacturacion, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pagocon, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cambio, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        PanelTotalVentaPOSLayout.setVerticalGroup(
            PanelTotalVentaPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTotalVentaPOSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelTotalVentaPOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pagocon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labeltotalenfacturacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cambio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalTipoDeProductosPOS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelClientePOS.setBackground(new java.awt.Color(0, 204, 255));
        PanelClientePOS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        IdCliente.setEditable(false);
        IdCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                IdClienteMouseReleased(evt);
            }
        });

        Caja_IDENTIFICACION.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Caja_IDENTIFICACIONKeyPressed(evt);
            }
        });

        direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                direccionKeyReleased(evt);
            }
        });

        BtnNuevoClientePOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        BtnNuevoClientePOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNuevoClientePOSActionPerformed(evt);
            }
        });

        BtnAgregarClientePOS.setText("AGREGAR");
        BtnAgregarClientePOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarClientePOSActionPerformed(evt);
            }
        });

        MunicipioCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MunicipioClienteKeyReleased(evt);
            }
        });

        DepartamentoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DepartamentoClienteKeyReleased(evt);
            }
        });

        PaisCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PaisClienteActionPerformed(evt);
            }
        });

        CodigoPostalCliente.setText("0");
        CodigoPostalCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CodigoPostalClienteKeyTyped(evt);
            }
        });

        SiglaPais.setEditable(false);

        nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        nombre.setForeground(new java.awt.Color(0, 0, 0));
        nombre.setCaretColor(new java.awt.Color(0, 112, 192));
        nombre.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        nombre.setMargin(new java.awt.Insets(0, 0, 0, 0));
        nombre.setMayusculas(true);
        nombre.setPhColor(new java.awt.Color(0, 0, 0));
        nombre.setPlaceholder("NOMBRE DEL CLIENTE*");
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nombreKeyPressed(evt);
            }
        });

        jLabel8.setText("NIT | CUI");

        COMBO_TIPO_IDENTIFICACION.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        COMBO_TIPO_IDENTIFICACION.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIT", "CUI" }));
        COMBO_TIPO_IDENTIFICACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COMBO_TIPO_IDENTIFICACIONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelClientePOSLayout = new javax.swing.GroupLayout(PanelClientePOS);
        PanelClientePOS.setLayout(PanelClientePOSLayout);
        PanelClientePOSLayout.setHorizontalGroup(
            PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelClientePOSLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelClientePOSLayout.createSequentialGroup()
                        .addComponent(MunicipioCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DepartamentoCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                    .addGroup(PanelClientePOSLayout.createSequentialGroup()
                        .addComponent(IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelClientePOSLayout.createSequentialGroup()
                        .addComponent(Caja_IDENTIFICACION)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(COMBO_TIPO_IDENTIFICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(PaisCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(direccion)
                    .addGroup(PanelClientePOSLayout.createSequentialGroup()
                        .addComponent(SiglaPais, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CodigoPostalCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnNuevoClientePOS, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAgregarClientePOS)
                .addGap(2, 2, 2))
        );
        PanelClientePOSLayout.setVerticalGroup(
            PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClientePOSLayout.createSequentialGroup()
                .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelClientePOSLayout.createSequentialGroup()
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(IdCliente, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(Caja_IDENTIFICACION, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(direccion, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelClientePOSLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(COMBO_TIPO_IDENTIFICACION, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(MunicipioCliente)
                            .addComponent(CodigoPostalCliente)
                            .addComponent(DepartamentoCliente)
                            .addComponent(PaisCliente)
                            .addComponent(SiglaPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelClientePOSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelClientePOSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnAgregarClientePOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnNuevoClientePOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout VentaLayout = new javax.swing.GroupLayout(Venta);
        Venta.setLayout(VentaLayout);
        VentaLayout.setHorizontalGroup(
            VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentaLayout.createSequentialGroup()
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentaLayout.createSequentialGroup()
                        .addComponent(PanleDatosProductoPOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PanelTotalVentaPOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBotonesPOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(PanelClientePOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        VentaLayout.setVerticalGroup(
            VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(VentaLayout.createSequentialGroup()
                .addComponent(PanelClientePOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(VentaLayout.createSequentialGroup()
                        .addGroup(VentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanleDatosProductoPOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelTotalVentaPOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelBotonesPOS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Venta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PrecioPublicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioPublicoActionPerformed
        PrecioRe.setSelected(false);
        PrecioEs.setSelected(false);
        Final.setText(PrecioPublico.getText());
        PrecioPublico.setFont(new Font("Consolas", Font.BOLD, 18));
        PrecioRe.setFont(new Font("Consolas", Font.BOLD, 16));
        PrecioEs.setFont(new Font("Consolas", Font.BOLD, 16));
        Final.requestFocus();
    }//GEN-LAST:event_PrecioPublicoActionPerformed

    private void PrecioEsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioEsActionPerformed
        PrecioPublico.setSelected(false);
        PrecioRe.setSelected(false);
        Final.setText(PrecioEs.getText());

        PrecioEs.setFont(new Font("Consolas", Font.BOLD, 18));
        PrecioRe.setFont(new Font("Consolas", Font.BOLD, 16));
        PrecioPublico.setFont(new Font("Consolas", Font.BOLD, 16));
        Final.requestFocus();
    }//GEN-LAST:event_PrecioEsActionPerformed

    private void PrecioReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrecioReActionPerformed
        PrecioEs.setSelected(false);
        PrecioPublico.setSelected(false);
        Final.setText(PrecioRe.getText());

        PrecioRe.setFont(new Font("Consolas", Font.BOLD, 18));
        PrecioEs.setFont(new Font("Consolas", Font.BOLD, 16));
        PrecioPublico.setFont(new Font("Consolas", Font.BOLD, 16));
        Final.requestFocus();
    }//GEN-LAST:event_PrecioReActionPerformed

    private void FinalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FinalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           /* if (jButton7.getText().equals("AGREGAR")) {
                AgregarProducto();
            } else {

                EditarProductoPOS();

            }*/
           CantidadVenta.requestFocus();
           CantidadVenta.addFocusListener(new FullSelectorListener());
        }
    }//GEN-LAST:event_FinalKeyPressed

    private void FinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FinalKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Final);
    }//GEN-LAST:event_FinalKeyTyped

    private void IdVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BuscarProductoVenta(IdVenta.getText());
        }
    }//GEN-LAST:event_IdVentaKeyPressed

    private void NombreVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            BuscarProductoVentaPorNombre(NombreVenta.getText());
        }
    }//GEN-LAST:event_NombreVentaKeyPressed

    private void CantidadVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadVentaActionPerformed

    private void CantidadVentaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadVentaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
           if(jButton7.getText().equals("AGREGAR")){
                AgregarProducto();
            }else{
                EditarProductoPOS();
            }
          //  BuscarProductoVenta_ID_VENTA(CantidadVenta.getText());
        }
    }//GEN-LAST:event_CantidadVentaKeyPressed

    private void CantidadVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadVentaKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, CantidadVenta);
    }//GEN-LAST:event_CantidadVentaKeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(jButton7.getText().equals("AGREGAR")){
            AgregarProducto();
            
        }else{
            EditarProductoPOS();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        IdVenta.requestFocus();
        LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
        LISTAR_CLIENTES_CAJAS_NOMBRES();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void CheckIngresoAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckIngresoAutomaticoActionPerformed
        ConfigVentas.Recordar(CheckIngresoAutomatico);
        IdVenta.requestFocus();
    }//GEN-LAST:event_CheckIngresoAutomaticoActionPerformed

    private void CheckVistaPreviaVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckVistaPreviaVentaActionPerformed
        ConfigVentas.RecordarVistaPreviaVenta(CheckVistaPreviaVenta);
        IdVenta.requestFocus();
    }//GEN-LAST:event_CheckVistaPreviaVentaActionPerformed

    private void CheckImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckImprimirActionPerformed
        ConfigVentas.RecordarImprimir(CheckImprimir);
        IdVenta.requestFocus();
    }//GEN-LAST:event_CheckImprimirActionPerformed

    private void lblImagenVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenVentaMouseClicked
        if(IdVenta.getText().equals("") || IdVenta.getText() == null){
            JOptionPane.showMessageDialog(null, "¡AÚN NO HA SELECCIONADO NINGÚN PRODUCTO!");
        }else{
            DETALLE_PRODUCTO DP= new DETALLE_PRODUCTO();
            DP.VerProducto(IdVenta.getText());
            DP.setVisible(true);
        }
    }//GEN-LAST:event_lblImagenVentaMouseClicked

    private void lblImagenVentaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImagenVentaMouseReleased
        lblImagenVenta.setToolTipText("VER DETALLES");
    }//GEN-LAST:event_lblImagenVentaMouseReleased

    private void DescripcionProductoVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DescripcionProductoVentaMouseClicked
        if(IdVenta.getText().equals("") || IdVenta.getText() == null){
            JOptionPane.showMessageDialog(null, "¡AÚN NO HA SELECCIONADO NINGÚN PRODUCTO!");
        }else{
            DETALLE_PRODUCTO DP= new DETALLE_PRODUCTO();
            DP.VerProducto(IdVenta.getText());
            DP.setVisible(true);
        }
    }//GEN-LAST:event_DescripcionProductoVentaMouseClicked

    private void DescripcionProductoVentaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DescripcionProductoVentaMouseReleased
        DescripcionProductoVenta.setToolTipText("VER DETALLES");
    }//GEN-LAST:event_DescripcionProductoVentaMouseReleased

    private void TablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentasMouseClicked
        
        Boolean EstadoIngresoAutomatico = CheckIngresoAutomatico.isSelected();
        if(EstadoIngresoAutomatico == true){
            CheckIngresoAutomatico.setSelected(false);
        }
        int Seleccion = TablaVentas.getSelectedRow();
        
        int FormaqueIngreso = Integer.parseInt(TablaVentas.getValueAt(Seleccion, 8).toString());
        jButton7.setText("EDITAR");
        if (Integer.parseInt(TablaVentas.getValueAt(Seleccion, 8).toString()) == 1) {
            ID_PRODUCTO.setText("1");
            TOTAL_INGRESADO.setText(TablaVentas.getValueAt(Seleccion, 2).toString());
            EstadoProducto.setText("INGRESADO");
            BuscarProductoVenta(TablaVentas.getValueAt(Seleccion, 0).toString());
            CantidadVenta.setText(TablaVentas.getValueAt(Seleccion, 2).toString());
            Final.setText(TablaVentas.getValueAt(Seleccion, 5).toString());
            CantidadVenta.requestFocus();
            CantidadVenta.addFocusListener(new FullSelectorListener());
            jButton7.setText("EDITAR");
        } else {
            LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
            jButton7.setText("EDITAR");
            ID_PRODUCTO.setText("0");
            IdVenta.setText(TablaVentas.getValueAt(Seleccion, 0).toString());
            NombreVenta.setText(String.valueOf(TablaVentas.getValueAt(Seleccion, 1)));
            CantidadVenta.setText(String.valueOf(TablaVentas.getValueAt(Seleccion, 2)));
            Final.setText(String.valueOf(TablaVentas.getValueAt(Seleccion, 5)));
            CantidadVenta.requestFocus();
            CantidadVenta.addFocusListener(new FullSelectorListener());

        }

        int Columna = TablaVentas.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY() / TablaVentas.getRowHeight();
        if (Fila < TablaVentas.getRowCount() && Fila >= 0 && Columna < TablaVentas.getColumnCount() && Columna >= 0) {
            Object value = TablaVentas.getValueAt(Fila, Columna);
            if (value instanceof JButton jButton) {
                jButton.doClick();

                if (FormaqueIngreso == 1) {
                    EliminarVenta();
                    BuscarProductoVenta(IdVenta.getText());
                    SumarProductos();
                    if (TablaVentas.getRowCount() < 1) {
                        LIMPIAR_DATOS_RESUMEN_VENTA();
                    } else {
                        FORMA_DE_PAGO();
                    }
                } else {
                    EliminarVentaSinAumentarStock();
                    SumarProductos();
                    if (TablaVentas.getRowCount() < 1) {
                        LIMPIAR_DATOS_RESUMEN_VENTA();
                    } else {
                        FORMA_DE_PAGO();
                    }
                }
                jButton7.setText("AGREGAR");
            }
        }
        if(EstadoIngresoAutomatico == true){
            CheckIngresoAutomatico.setSelected(true);
        }

    }//GEN-LAST:event_TablaVentasMouseClicked

    private void BtnGenerarVentaPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGenerarVentaPOSActionPerformed
        Double TotalParaConvertir= Double.parseDouble(labeltotal.getText());
        Numero_Letras NumLe= new Numero_Letras();
        if(TablaVentas.getRowCount() > 0){
            BtnGenerarVentaPOS.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
            TotalLetras.setText(NumLe.Convertir(TotalParaConvertir + "", true));
            IngresoClientes(nombre.getText(), Caja_IDENTIFICACION.getText(), String.valueOf(COMBO_TIPO_IDENTIFICACION.getSelectedItem()), direccion.getText(), MunicipioCliente.getText(), DepartamentoCliente.getText(), PaisCliente.getSelectedItem().toString(), CodigoPostalCliente.getText());
            if(pagocon.getText().equals("0.00")){
                GenerarVenta();
            }else{
                if(Float.parseFloat(labeltotalenfacturacion.getText())>Float.parseFloat(pagocon.getText())){
                    int seleccion= JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO QUE EL PAGO ("+pagocon.getText()+") SEA MENOR A EL TOTAL ("+labeltotalenfacturacion.getText()+")?");
                    if(seleccion == 0){
                        GenerarVenta();
                    }else{
                        if(VentanaFormaPago==true){
                            Pagos.toFront();
                        }else{
                            VentanaFormaPago = true;
                            Pagos.RELLENAR_PARAMETROS_FORMA_DE_PAGO(labeltotal.getText(), pagocon.getText(), cambio.getText(), Efectivo.getText(), Tarjeta.getText()
                                , Deposito.getText(), Cheque.getText(), CajaNumeroTransacción.getText(), ComboFormaPago.getText(), Integer.parseInt(MetodoPagoEntero.getText()), TotalIva.getText(), P_E.IVA_EMPRESA, SubTotal.getText());
                            Pagos.setVisible(true);
                        }
                    }
                }else{
                    GenerarVenta();
                    //if(Float.parseFloat(labeltotalenfacturacion.getText())<Float.parseFloat(pagocon.getText()))
                }
            }

            BtnGenerarVentaPOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }else{
            JOptionPane.showMessageDialog(null, "¡AÚN NO HAY PRODUCTOS EN EL CARRITO!");
        }
        //Ticket();
    }//GEN-LAST:event_BtnGenerarVentaPOSActionPerformed

    private void BtnCancelarVentaPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarVentaPOSActionPerformed
        int seleccion= JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE ANULAR LA VENTA?\n SE ELIMINARÁN TODOS LOS DATOS QUE HAYAS INGRESADO", "¡IMPORTANTE!", JOptionPane.WARNING_MESSAGE);
        if(seleccion==0){
            AumentarStock();
            LIMPIAR_CLIENTE_POS();
            LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
            LIMPIAR_DATOS_RESUMEN_VENTA();
            for (int i = 0; i < TablaVentas.getRowCount(); i++) {
                LIMPIAR_TABLA(TablaVentas);
            }
        }
    }//GEN-LAST:event_BtnCancelarVentaPOSActionPerformed

    private void BtnBuscarProductoPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarProductoPOSActionPerformed

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(VentanaBuscarProducto == false) {
                    VentanaBuscarProducto = true;
                    BtnBuscarProductoPOS.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                    BP = new JFRAME_BUSCAR_PRODUCTO(pos);
                    BP.setVisible(true);
                    BtnBuscarProductoPOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                } else {
                    BP.toFront();
                    BP.jTextField1.requestFocus();
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }//GEN-LAST:event_BtnBuscarProductoPOSActionPerformed

    private void BtnAgregarPagoPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarPagoPOSActionPerformed
        if(jLabel44.getText().equals("0") || jLabel44.getText().equals("0.00") || jLabel44.getText().equals("0.0")){
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("¡ACCIÓN NO VÁLIDA!", "¡DEBE INGRESAR ALGÚN PRODUCTO!", DesktopNotify.FAIL, 20000L);
        }else{
            if(VentanaFormaPago == true){
                Pagos.toFront();
                Pagos.toFront();
            }else{
                VentanaFormaPago = true;
                Pagos.RELLENAR_PARAMETROS_FORMA_DE_PAGO(labeltotal.getText(), pagocon.getText(), cambio.getText(), Efectivo.getText(), Tarjeta.getText()
                    , Deposito.getText(), Cheque.getText(), CajaNumeroTransacción.getText(), ComboFormaPago.getText(), Integer.parseInt(MetodoPagoEntero.getText()), TotalIva.getText(), P_E.IVA_EMPRESA, SubTotal.getText());
                Pagos.setVisible(true);
            }
        }
    }//GEN-LAST:event_BtnAgregarPagoPOSActionPerformed

    private void BtnAgregarObservacionPOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarObservacionPOSActionPerformed
        if(VentanaObservacion == false){
            VentanaObservacion = true;
            observaciones= new Observaciones(ObservacionVenta.getText(), pos);
            observaciones.setVisible(true);
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("¡ACCIÓN NO VÁLIDA!", "¡YA HAY UNA VENTANA DE OBSERVACIÓN ABIERTA!", DesktopNotify.FAIL, 20000L);
        }

    }//GEN-LAST:event_BtnAgregarObservacionPOSActionPerformed

    private void TipoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDocumentoActionPerformed
        ConfigVentas.RecordarTipoDocumento(TipoDocumento);
        switch (TipoDocumento.getSelectedIndex()) {
            case 0:
            factura.setForeground(Color.red);
            lblNumeroSerieCotizacion.setForeground(Color.BLACK);
            Vale.setForeground(Color.BLACK);
            break;
            case 1:
            factura.setForeground(Color.red);
            lblNumeroSerieCotizacion.setForeground(Color.BLACK);
            Vale.setForeground(Color.BLACK);
            break;
            case 2:
            lblNumeroSerieCotizacion.setForeground(Color.red);
            factura.setForeground(Color.BLACK);
            Vale.setForeground(Color.BLACK);
            break;
            case 3:
            lblNumeroSerieCotizacion.setForeground(Color.BLACK);
            factura.setForeground(Color.BLACK);
            Vale.setForeground(Color.red);
            break;
            default:
            break;
        }
    }//GEN-LAST:event_TipoDocumentoActionPerformed

    private void IdClienteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IdClienteMouseReleased
        cliDao = new ClientesDao();
        Clientes cli = new Clientes();
        TextAutoCompleter AutoCompletador = new TextAutoCompleter(IdCliente);
        List<Clientes> ListarCliente =cliDao. BuscarClienteLista(COMBO_TIPO_IDENTIFICACION.getSelectedItem().toString());
        Object[] ob= new Object[1];
        for (int i = 0; i < ListarCliente.size(); i++) {
            ob[0] = ListarCliente.get(i).getIdclientes();
            AutoCompletador.addItems(ob);
            AutoCompletador.getItemSelected();
        }
    }//GEN-LAST:event_IdClienteMouseReleased

    private void Caja_IDENTIFICACIONKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Caja_IDENTIFICACIONKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ConsultarNit_CUIFinal(Caja_IDENTIFICACION.getText());
        }
    }//GEN-LAST:event_Caja_IDENTIFICACIONKeyPressed

    private void direccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_direccionKeyReleased
        String Direccion= direccion.getText().toUpperCase();
        Direccion.toUpperCase();
        direccion.setText(Direccion);
    }//GEN-LAST:event_direccionKeyReleased

    private void BtnNuevoClientePOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNuevoClientePOSActionPerformed
       LIMPIAR_CLIENTE_POS();
    }//GEN-LAST:event_BtnNuevoClientePOSActionPerformed

    private void BtnAgregarClientePOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarClientePOSActionPerformed
        /* tablasclientes.ActualizarTablaCliente(TablaClientes1);
        BTNLISTOCLIENTES.setVisible(true);
        tablamadre.setSelectedIndex(9);
        Mov.Tabla(tablamadre, tablamadre.getSelectedIndex());*/
        IngresoClientes(nombre.getText(), Caja_IDENTIFICACION.getText(), String.valueOf(COMBO_TIPO_IDENTIFICACION.getSelectedItem()), direccion.getText(), MunicipioCliente.getText(), DepartamentoCliente.getText(), PaisCliente.getSelectedItem().toString(), CodigoPostalCliente.getText());
    }//GEN-LAST:event_BtnAgregarClientePOSActionPerformed

    private void MunicipioClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MunicipioClienteKeyReleased
        MunicipioCliente.setText(MunicipioCliente.getText().toUpperCase());
    }//GEN-LAST:event_MunicipioClienteKeyReleased

    private void DepartamentoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepartamentoClienteKeyReleased
        DepartamentoCliente.setText(DepartamentoCliente.getText().toUpperCase());
    }//GEN-LAST:event_DepartamentoClienteKeyReleased

    private void PaisClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PaisClienteActionPerformed
        if(PaisCliente.getSelectedIndex()>=1){
            SiglaPais();
        }
    }//GEN-LAST:event_PaisClienteActionPerformed

    private void CodigoPostalClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoPostalClienteKeyTyped
        Eventos event = new Eventos();
        event.numberKeyPress(evt);
    }//GEN-LAST:event_CodigoPostalClienteKeyTyped

    private void nombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BUSCAR_CLIENTE_NOMBRE(nombre.getText());
        }
    }//GEN-LAST:event_nombreKeyPressed

    private void COMBO_TIPO_IDENTIFICACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COMBO_TIPO_IDENTIFICACIONActionPerformed
        String IDENTIFICACION = Caja_IDENTIFICACION.getText();
        LIMPIAR_CLIENTE_POS();
        Caja_IDENTIFICACION.setText(IDENTIFICACION);
        Caja_IDENTIFICACION.requestFocus();
    }//GEN-LAST:event_COMBO_TIPO_IDENTIFICACIONActionPerformed

    private void lblSerie_SalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblSerie_SalidasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblSerie_SalidasActionPerformed

    private void CajaNumeroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajaNumeroDocumentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CajaNumeroDocumentoActionPerformed

    private void PanleDatosProductoPOSMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanleDatosProductoPOSMouseReleased
        if(evt.isPopupTrigger()){
            if(!IdVenta.getText().equals(null)){
                ImageIcon Ruta = new ImageIcon(METODOS_GLOBALES.CargarDatosRutas(1)+"\\" +lblImagenVenta.getName());
                    IMAGEN.setSize(300, 300);
                    IMAGEN.setIcon(new ImageIcon(Ruta.getImage().getScaledInstance(
                        IMAGEN.getWidth(),
                        IMAGEN.getHeight(),
                        Image.SCALE_DEFAULT)));
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
            //DESCRIPCION.setText("<html>"+DescripcionProductoVenta.getText()+"</html>");
            DESCRIPCION.setText(DescripcionProductoVenta.getText());
            jScrollPane3.setSize(300, 100);
            jPopupMenu1.add(IMAGEN);
            jPopupMenu1.add(jSeparator1);
            jPopupMenu1.add(jScrollPane3);
        }else{
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERROR", "¡DEBE SELECCIONAR UN PRODUCTO!", DesktopNotify.ERROR, 3000L);
        }

        }
    }//GEN-LAST:event_PanleDatosProductoPOSMouseReleased

    private void PanelTotalVentaPOSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PanelTotalVentaPOSMouseClicked
        if(PanleDatosProductoPOS.isVisible()){
            PanleDatosProductoPOS.setVisible(false);
        }else{
            PanleDatosProductoPOS.setVisible(true);
        }
    }//GEN-LAST:event_PanelTotalVentaPOSMouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
         VERIFICAR_DESCUENTO(jComboBox1);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void facturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturaMouseClicked
        Principal.DESKTOP_PRINCIPAL.add(jLayeredPane1);
        jLayeredPane1.setVisible(true);
    }//GEN-LAST:event_facturaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarClientePOS;
    private javax.swing.JButton BtnAgregarObservacionPOS;
    private javax.swing.JButton BtnAgregarPagoPOS;
    private javax.swing.JButton BtnBuscarProductoPOS;
    private javax.swing.JButton BtnCancelarVentaPOS;
    private javax.swing.JButton BtnGenerarVentaPOS;
    private javax.swing.JButton BtnNuevoClientePOS;
    private javax.swing.JComboBox<String> COMBO_TIPO_IDENTIFICACION;
    public static javax.swing.JTextField CajaFechaAutorizacion;
    public static javax.swing.JTextField CajaNumeroAutorizacion;
    public static javax.swing.JTextField CajaNumeroDocumento;
    public static javax.swing.JTextArea CajaNumeroTransacción;
    public static javax.swing.JTextField CajaSerieCertificacion;
    private javax.swing.JTextField Caja_IDENTIFICACION;
    public javax.swing.JTextField Cantidad2;
    public javax.swing.JTextField CantidadVenta;
    private javax.swing.JCheckBox CheckImprimir;
    public javax.swing.JCheckBox CheckIngresoAutomatico;
    private javax.swing.JCheckBox CheckVistaPreviaVenta;
    public static javax.swing.JLabel Cheque;
    private javax.swing.JTextField CodigoPostalCliente;
    public static javax.swing.JLabel ComboFormaPago;
    private static javax.swing.JTextArea DESCRIPCION;
    private javax.swing.JTextField DepartamentoCliente;
    public static javax.swing.JLabel Deposito;
    public javax.swing.JTextArea DescripcionProductoVenta;
    public static javax.swing.JLabel Efectivo;
    public static javax.swing.JLabel EstadoProducto;
    private javax.swing.JTabbedPane Facturacion;
    public javax.swing.JTextField Final;
    private static javax.swing.JLabel ID_PRODUCTO;
    private javax.swing.JLabel IMAGEN;
    private javax.swing.JTextField IdCliente;
    public javax.swing.JTextField IdVenta;
    public static javax.swing.JLabel MetodoPagoEntero;
    private javax.swing.JTextField MunicipioCliente;
    public javax.swing.JTextField NombreVenta;
    public static javax.swing.JTextArea ObservacionVenta;
    private javax.swing.JPanel PANELPRODUCTOS;
    private javax.swing.JComboBox<String> PaisCliente;
    private javax.swing.JPanel PanelBotonesPOS;
    private javax.swing.JPanel PanelClientePOS;
    private javax.swing.JPanel PanelTotalVentaPOS;
    private javax.swing.JPanel PanleDatosProductoPOS;
    public javax.swing.JRadioButton PrecioEs;
    public javax.swing.JRadioButton PrecioPublico;
    public javax.swing.JRadioButton PrecioRe;
    private javax.swing.JTextField SiglaPais;
    public static javax.swing.JLabel SubTotal;
    private static javax.swing.JLabel TOTAL_INGRESADO;
    public javax.swing.JTable TablaVentas;
    public static javax.swing.JLabel Tarjeta;
    private javax.swing.JComboBox<String> TipoDocumento;
    public static javax.swing.JLabel TotalDeProductosVendidos;
    public static javax.swing.JLabel TotalIva;
    public static javax.swing.JTextArea TotalLetras;
    public javax.swing.JLabel TotalTipoDeProductosPOS;
    private javax.swing.JTextField Vale;
    private javax.swing.JPanel Venta;
    public javax.swing.JLabel cambio;
    private javax.swing.JTextField direccion;
    private javax.swing.JTextField factura;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private static javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel82;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    public javax.swing.JScrollPane jScrollPane15;
    private static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public static javax.swing.JLabel labeltotal;
    public javax.swing.JLabel labeltotalenfacturacion;
    private javax.swing.JLabel lblCantidadPOS;
    private javax.swing.JLabel lblCodigoPOS;
    public javax.swing.JLabel lblImagenVenta;
    private javax.swing.JLabel lblNombrePOS;
    private javax.swing.JTextField lblNumeroSerieCotizacion;
    private javax.swing.JLabel lblNumeroTransacción;
    private javax.swing.JLabel lblPrecioFinalPOS;
    private javax.swing.JTextField lblSerie_Salidas;
    public static javax.swing.JLabel lblVentaPrecio1;
    public static javax.swing.JLabel lblVentaPrecio2;
    public static javax.swing.JLabel lblVentaPrecio3;
    private javax.swing.JLabel lbl_CANTIDAD_DESCUENTO;
    private static rojerusan.RSMetroTextFullPlaceHolder nombre;
    public javax.swing.JLabel pagocon;
    // End of variables declaration//GEN-END:variables
}
