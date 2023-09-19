package Vista;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import static CLASES_GLOBALES.METODOS_GLOBALES.executorService;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Clases_Reportes.DatosEmpresa;
import Configuraciones.Ventas;
import Controlador.ClientesDao;
import Controlador.VentaDao;
import Controlador.DatosEmpresaDao;
import FEL.DatosCertificador;
import FEL.DocumentoFel;
import FEL.XML_DTE.GenerarXML;
import FEL.XML_DTE.XMLAnularFactura;
import Gmail.InterfazGmail;
import Modelo.Clientes;
import Modelo.DatosEmpresaGeneral;
import Modelo.Venta;
import ModeloWebService.DatosConsultarDTE;
import ModeloWebService.DatosUsuario;
import ModeloWebService.EnvioDatosAnularFactura;
import ModeloWebService.EnvioDatosFacturar;
import ModeloWebService.RespuestaCertificacion;
import ModeloWebService.RespuestaDatosAnularFactura;
import ReportesImpresion.DatosClienteYFactura;
import ReportesImpresion.Documentos;
import Conexiones.ConexionesSQL;
import Controlador.Eventos;
import Controlador.KardexDao;
import Controlador.ProductosDao;
import Modelo.Detalle;
import Modelo.FormaDePago;
import Modelo.Kardex;
import Modelo.Productos;
import Tablas.ActualizarTablaVentasDiariasYGenerales;
import Vista.POS.POS;
import WebServiceDigifact.AnularFactura;
import WebServiceDigifact.CertificarFactura;
import WebServiceDigifact.ConsultarDTE;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Vista.REPORTES_VENTAS.MOVIMIENTOS_DIARIOS;
import java.util.List;

public final class Detalles extends javax.swing.JFrame {
    Ventas v= new Ventas();
    Principal principal;
    PARAMETROS_EMPRESA P_E;
    int DiariasGenerales;
    Date fech = new Date();
        String strDateFormat = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha=objSDF.format(fech);
        DatosUsuario DU;
    private String NombreCliente, NitCliente, DireccionCliente, CodigoPostalCliente, MunicipioCliente, DepartamentoCliente, PaisCliente;
    private String NUMERO_INTERNO_FACTURA_ELECTRONICA="";
    private AVISOS VENTANA_AVISO;
    POS pos;
    private String NumeroFactura;
    private int TipoDeVista;
    public Detalles() { 
    }
    
    public Detalles(String NumeroFactura, int ModoAbierto, int TipoDeVista, POS pos, Principal principal) {
        initComponents();
        jMenu4.setEnabled(false);
        this.principal = principal;
        this.pos = pos;
        //TablaDetalles.setShowHorizontalLines(true);
        this.setLocationRelativeTo(null);
        Editar();
        this.NumeroFactura=NumeroFactura;
        this.TipoDeVista=TipoDeVista;
        CargarTodosLosDetalles(NumeroFactura);
        v.CargarDatosFormatoImpresion(jComboBox1);
        DiariasGenerales= TipoDeVista;
        ValidarBotones();
        if(ModoAbierto == 0){
        new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             Cerrar();
         }
     },
             50000
     );    
        }else{
          new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             Cerrar();
         }
     },
             2000000
     );  
        }
    }
    
    public void Cerrar(){
        this.dispose();
    }
    
    @Override
        public Image getIconImage() {
            Image retValue = Toolkit.getDefaultToolkit().
                    getImage(ClassLoader.getSystemResource("Imagenes/Detalle.png"));
            return retValue;
        }
        
        public void CargarTodosLosDetalles(String NumeroFactura){
            Fac.setText(NumeroFactura);
           // CajaNumeroAutorizacion.setText(NumeroAutorizacion);
            VerDetalles();
            VerDetalles2();
            VerDetalles3();  
            ObtenerPagos();
            ValidarBotones();
        }
        
        public void ValidarBotones(){
            if(PARAMETROS_USUARIOS.ROL_USUARIO.equals("Usuario")){
                jMenu2.setVisible(false);
            }else{
                jMenu2.setVisible(true);
            }
            
            if(TipoDocumento.getText().equals("FACTURA") && EstadoVenta.getSelectedItem().toString().equals("ANULADO") || !EstadoVenta.getSelectedItem().toString().equals("FACTURADO")){
            jTabbedPane1.setEnabledAt(5, true);
            jTabbedPane1.setEnabledAt(4, true);
            jMenuItem2.setEnabled(false); 
           jMenuItem1.setEnabled(false);
           jMenu1.setVisible(false);
        }if(TipoDocumento.getText().equals("FACTURA") && !EstadoVenta.getSelectedItem().toString().equals("ANULADO")){
            jTabbedPane1.setEnabledAt(5, true);
            jTabbedPane1.setEnabledAt(4, true);
            jMenuItem2.setEnabled(true); 
           jMenuItem1.setEnabled(true);
           jMenu1.setVisible(true);
        }else if(TipoDocumento.getText().equals("PROFORMA") && EstadoVenta.getSelectedItem().toString().equals("ANULADO")){
            jTabbedPane1.setEnabledAt(5, false);
            jTabbedPane1.setEnabledAt(4, false);
            jMenuItem2.setEnabled(false); 
           jMenuItem1.setEnabled(false);
           jMenu1.setVisible(false);
        }if(TipoDocumento.getText().equals("PROFORMA") && !EstadoVenta.getSelectedItem().toString().equals("ANULADO")){
            jTabbedPane1.setEnabledAt(5, false);
            jTabbedPane1.setEnabledAt(4, false);
            jMenuItem2.setEnabled(true); 
           jMenuItem1.setEnabled(true);
           jMenu1.setVisible(true);
        }
        }
        
        public void Editar(){
            if(PanelEditar.isVisible()){
                jButton1.setEnabled(true);
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                jMenu3.setEnabled(true);
                jMenu1.setEnabled(true);
                jMenuItem1.setEnabled(true);
                jMenuItem2.setEnabled(false);
                PanelEditar.setVisible(false);
                jMenuItem6.setEnabled(true);
                Fac.setEditable(false);
                TipoDocumento.setEditable(false);
                EstadoVenta.setEnabled(false);
                CajaCliente.setEditable(false);
                CajaNit.setEditable(false);
                CajaDireccion.setEditable(false);
                CajaVendedor.setEditable(false);
                CajaObservacion.setEditable(false);
                CajaCertificador.setEditable(false);
                NitCertificador.setEditable(false);
                CajaNitEmisor.setEditable(false);
                NumeroDocumento.setEditable(false);
                CajaSerieCertificacion.setEditable(false);
                FechaCertificacion.setEditable(false);
                CajaNumeroAutorizacion.setEditable(false);
                Efectivo.setEditable(false);
                Tarjeta.setEditable(false);
                Transferencia.setEditable(false);
                Cheque.setEditable(false);
                Otro.setEditable(false);
            }else{
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
                jButton3.setEnabled(false);
                jMenu3.setEnabled(false);
                jMenu1.setEnabled(false);
                jMenuItem1.setEnabled(false);
                jMenuItem2.setEnabled(false);
                PanelEditar.setVisible(true);
                jMenuItem6.setEnabled(false);
                Fac.setEditable(true);
                TipoDocumento.setEditable(true);
                EstadoVenta.setEnabled(true);
                CajaCliente.setEditable(true);
                CajaNit.setEditable(true);
                CajaDireccion.setEditable(true);
                CajaVendedor.setEditable(true);
                CajaObservacion.setEditable(true);
                CajaCertificador.setEditable(true);
                NitCertificador.setEditable(true);
                CajaNitEmisor.setEditable(true);
                NumeroDocumento.setEditable(true);
                CajaSerieCertificacion.setEditable(true);
                FechaCertificacion.setEditable(true);
                CajaNumeroAutorizacion.setEditable(true);
                Efectivo.setEditable(true);
                Tarjeta.setEditable(true);
                Transferencia.setEditable(true);
                Cheque.setEditable(true);
                Otro.setEditable(true);
        }
    }

    public void Editar_Logica() {
        VentaDao V = new VentaDao();
        Venta datos_venta = new Venta();
        datos_venta.setNoFactura(Fac.getText());
        datos_venta.setTipoDocumentoFel(TipoDocumento.getText());
        datos_venta.setEstado(EstadoVenta.getSelectedItem().toString());
        datos_venta.setCliente(CajaCliente.getText());
        datos_venta.setIDENTIFICACION_CLIENTE(CajaNit.getText());
        datos_venta.setDireccionCliente(CajaDireccion.getText());
        datos_venta.setUsuario(Integer.parseInt(CajaVendedor.getText()));
        datos_venta.setObservacion(CajaObservacion.getText());
        datos_venta.setNombreCertificador(CajaCertificador.getText());
        datos_venta.setNitCertificador(NitCertificador.getText());
        datos_venta.setNitEmisor(CajaNitEmisor.getText());
        datos_venta.setNumeroDocumento(NumeroDocumento.getText());
        datos_venta.setSerieDocumento(CajaSerieCertificacion.getText());
        datos_venta.setFechaAutorizacion(FechaCertificacion.getText());
        datos_venta.setNumeroAutorizacion(CajaNumeroAutorizacion.getText());
        datos_venta.setIdRegistro(Integer.parseInt(Id.getText()));
        datos_venta.setPagocon(Float.valueOf(CajaPago.getText()));
        datos_venta.setCambio(Float.valueOf(CajaCambio.getText()));
        Boolean Resultado = V.Editar_Registro(datos_venta);
        
        FormaDePago formaPago = new FormaDePago();
        formaPago.setEfectivo(Float.valueOf(Efectivo.getText()));
        formaPago.setTarjeta(Float.valueOf(Tarjeta.getText()));
        formaPago.setTransferencia(Float.valueOf(Transferencia.getText()));
        formaPago.setCheque(Float.valueOf(Cheque.getText()));
        formaPago.setOtro(Float.valueOf(Otro.getText()));
        formaPago.setNumero(Fac.getText());
        V.Editar_Registro_FormaPago(formaPago);
        if (Resultado == true) {
            Editar();
            CargarTodosLosDetalles(NumeroFactura);
            this.v.CargarDatosFormatoImpresion(jComboBox1);
            DiariasGenerales = TipoDeVista;
            ValidarBotones();
        }
    }
    
    public void VerDetalles(){
        ActualizarTablaVentasDiariasYGenerales tablas = new ActualizarTablaVentasDiariasYGenerales();
        LIMPIAR_TABLA(TablaDetalles);
        DefaultTableModel modelo = new DefaultTableModel();
        modelo = (DefaultTableModel) TablaDetalles.getModel();
        List<Detalle> ListarPr = null;
            ListarPr = tablas.DETALLE_VENTA(Fac.getText());
        Object[] ob = new Object[9];
        for (int i = 0; i < ListarPr.size(); i++) {
            //ob[0] = ListarPr.get(i).getIddetalle();
            ob[0] = ListarPr.get(i).getCodigoBarras();
            ob[1] = ListarPr.get(i).getNombre();
            ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getPrecio();
            ob[4] = ListarPr.get(i).getDescuento();
            ob[5] = ListarPr.get(i).getPrecio_Con_Descuento();
            ob[6] = ListarPr.get(i).getTotal();
            ob[7] = ListarPr.get(i).getAplicar_Descuento();
            ob[8] = ListarPr.get(i).getValidacionProductoExistente();
            
            
            modelo.addRow(ob);
            //[255,230,205]
        }
        TablaDetalles.setModel(modelo);
    
    }
    
    public void VerDetalles2(){
        String NoF= Fac.getText();
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("select Cliente, DireccionCliente, NitCliente, Pago, Cambio, Total, NumeroTransaccion from registro where NoFactura=?");
            ConexionesSQL.ps.setString(1, NoF);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            while (ConexionesSQL.rs.next()) {
            CajaCliente.setText(ConexionesSQL.rs.getString("Cliente"));
            CajaDireccion.setText(ConexionesSQL.rs.getString("DireccionCliente"));
            CajaNit.setText(ConexionesSQL.rs.getString("NitCliente"));
            CajaPago.setText(ConexionesSQL.rs.getString("Pago"));
                        CajaCambio.setText(ConexionesSQL.rs.getString("Cambio"));
            CajaTotal.setText(ConexionesSQL.rs.getString("Total"));
            CajaTransaccionDetalle.setText(ConexionesSQL.rs.getString("NumeroTransaccion"));
            }
        } catch (SQLException e) {
            System.err.println("Error en JFRAME Detalles2, " + e);
        }finally{
            ConexionesSQL.RsClose(ConexionesSQL.rs);
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
        }
    }
    
    public void VerDetalles3(){
        String NoF= Fac.getText();
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("select idregistro, Usuario, Hora, Fecha, FormaPago, Observacion, TotalEnLetras, NombreCertificador, NitCertificador, FechaAutorizacion, NumeroAutorizacion, NumeroDocumento"
                    + ", SerieDocumento, TipoDocumentoFel, Estado, NitEmisor from registro where NoFactura=?");
            ConexionesSQL.ps.setString(1, NoF);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            if (ConexionesSQL.rs.next()) {
                Id.setText(ConexionesSQL.rs.getString("idregistro"));
            CajaVendedor.setText(ConexionesSQL.rs.getString("Usuario"));
            HoraVenta.setText(ConexionesSQL.rs.getString("Hora"));
            Fechaventa.setText(ConexionesSQL.rs.getString("Fecha"));
            FormapagoDetalleVenta.setText(ConexionesSQL.rs.getString("FormaPago"));
            CajaObservacion.setText(ConexionesSQL.rs.getString("Observacion"));
            TotalLetras.setText(ConexionesSQL.rs.getString("Observacion"));
            TotalLetras.setText(ConexionesSQL.rs.getString("TotalEnLetras"));
            CajaCertificador.setText(ConexionesSQL.rs.getString("NombreCertificador"));
            
            CajaCertificador.setText(ConexionesSQL.rs.getString("NombreCertificador"));
            NitCertificador.setText(ConexionesSQL.rs.getString("NitCertificador"));
            FechaCertificacion.setText(ConexionesSQL.rs.getString("FechaAutorizacion"));
            CajaNumeroAutorizacion.setText(ConexionesSQL.rs.getString("NumeroAutorizacion"));
            NumeroDocumento.setText(ConexionesSQL.rs.getString("NumeroDocumento"));
            CajaSerieCertificacion.setText(ConexionesSQL.rs.getString("SerieDocumento"));
            TipoDocumento.setText(ConexionesSQL.rs.getString("TipoDocumentoFel"));
            EstadoVenta.setSelectedItem(ConexionesSQL.rs.getString("Estado"));
            CajaNitEmisor.setText(ConexionesSQL.rs.getString("NitEmisor"));
            }
        } catch (SQLException e) {
            System.err.println("Error en JFRAME Detalles2, " + e);
        }finally{
            ConexionesSQL.RsClose(ConexionesSQL.rs);
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
        }
    
    }
    
    public void ObtenerPagos(){
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("select Efectivo, Tarjeta, Transferencia, Cheque, Otro from forma_pago where Numero=?");
            ConexionesSQL.ps.setString(1, Fac.getText());
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            if (ConexionesSQL.rs.next()) {
                Efectivo.setText(ConexionesSQL.rs.getString("Efectivo"));
            Tarjeta.setText(ConexionesSQL.rs.getString("Tarjeta"));
            Transferencia.setText(ConexionesSQL.rs.getString("Transferencia"));
            Cheque.setText(ConexionesSQL.rs.getString("Cheque"));
            Otro.setText(ConexionesSQL.rs.getString("Otro"));
            }
        } catch (SQLException e) {
            System.err.println("Error en ObtenerPagos, " + e);
        }finally{
            ConexionesSQL.RsClose(ConexionesSQL.rs);
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
        }
    
    }
    
    public void ObtenerId(int Seleccion, int TipoDeVista){
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        String Query="";
        if (TipoDeVista == 0) {
            if (Seleccion == 0) {
                Query = "select idregistro, NoFactura from registro where idregistro = (select min(idregistro) from registro where idregistro > " + Id.getText() + ")";
            } else if (Seleccion == 1) {
                Query = "select idregistro, NoFactura from registro where idregistro = (select max(idregistro) from registro where idregistro < " + Id.getText() + ")";
            }
        } else if (TipoDeVista == 1) {
            if (Seleccion == 0) {
                Query = "select idregistro, NoFactura from registro where idregistro = (select min(idregistro) from registro where idregistro > " + Id.getText() + ") AND Fecha LIKE '%" + fecha + "%'";
            } else if (Seleccion == 1) {
                Query = "select idregistro, NoFactura from registro where idregistro = (select max(idregistro) from registro where idregistro < " + Id.getText() + ") AND Fecha LIKE '%" + fecha + "%'";
            }
        }
        
        
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement(Query);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            if (ConexionesSQL.rs.next()) {
                Id.setText(ConexionesSQL.rs.getString("idregistro"));
                Fac.setText(ConexionesSQL.rs.getString("NoFactura"));
            }
        } catch (SQLException e) {
            System.err.println("Error en JFRAME Detalles2, " + e);
        }finally{
            ConexionesSQL.RsClose(ConexionesSQL.rs);
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        Fac = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TipoDocumento = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        EstadoVenta = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        Fechaventa = new javax.swing.JLabel();
        HoraVenta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CajaVendedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CajaCliente = new javax.swing.JTextField();
        CajaNit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CajaDireccion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        TotalLetras = new javax.swing.JLabel();
        FormapagoDetalleVenta = new javax.swing.JLabel();
        CajaTransaccionDetalle = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        Efectivo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        Tarjeta = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        Transferencia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        Cheque = new javax.swing.JTextField();
        Otro = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        C = new javax.swing.JScrollPane();
        CajaObservacion = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        CajaNitEmisor = new javax.swing.JTextField();
        NitCertificador = new javax.swing.JTextField();
        CajaCertificador = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        CajaNumeroAutorizacion = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        FechaCertificacion = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        CajaSerieCertificacion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        NumeroDocumento = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDetalles = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        CajaTotal = new javax.swing.JLabel();
        CajaPago = new javax.swing.JLabel();
        CajaCambio = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        PanelEditar = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        IR_A_VENTA = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DETALLE DE VENTA");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(735, 520));
        setPreferredSize(new java.awt.Dimension(795, 650));

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));

        Fac.setEditable(false);
        Fac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NÚMERO INTERNO");

        TipoDocumento.setEditable(false);
        TipoDocumento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("TIPO DE DOCUMENTO");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ESTADO DE VENTA");

        EstadoVenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURADO", "ANULADO" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(EstadoVenta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Fac, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TipoDocumento, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Fac, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(2, 2, 2)
                .addComponent(EstadoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jPanel7.setBackground(new java.awt.Color(255, 153, 102));

        Id.setEditable(false);

        Fechaventa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        Fechaventa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fechaventa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "FECHA DE VENTA"));

        HoraVenta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        HoraVenta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HoraVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "HORA DE VENTA:"));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Id, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                    .addComponent(Fechaventa, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                    .addComponent(HoraVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Fechaventa, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HoraVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jTabbedPane1.addTab("DETALLE DE DOCUMENTO", jPanel4);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        CajaVendedor.setEditable(false);

        jLabel5.setText("CLIENTE:");

        CajaCliente.setEditable(false);

        CajaNit.setEditable(false);

        jLabel6.setText("NIT:");

        jLabel7.setText("DIRECCIÓN:");

        CajaDireccion.setEditable(false);

        jLabel8.setText("VENDEDOR:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaCliente)
                    .addComponent(CajaNit)
                    .addComponent(CajaDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addComponent(CajaVendedor))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaNit, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaDireccion, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CLIENTE Y VENDEDOR", jPanel3);

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

        TotalLetras.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL EN LETRAS:"));

        FormapagoDetalleVenta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "FORMA DE PAGO"));

        CajaTransaccionDetalle.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "N° DE TRANSACCIÓN"));

        jPanel13.setBackground(new java.awt.Color(255, 204, 204));

        Efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EfectivoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EfectivoKeyTyped(evt);
            }
        });

        jLabel13.setText("EFECTIVO:");

        Tarjeta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TarjetaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TarjetaKeyTyped(evt);
            }
        });

        jLabel14.setText("TARJETA:");

        Transferencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TransferenciaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TransferenciaKeyTyped(evt);
            }
        });

        jLabel15.setText("TRANSFERENCIA:");

        Cheque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ChequeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ChequeKeyTyped(evt);
            }
        });

        Otro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                OtroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                OtroKeyTyped(evt);
            }
        });

        jLabel17.setText("OTRO:");

        jLabel16.setText("CHEQUE:");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Efectivo)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Tarjeta)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Transferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Cheque)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Otro)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Tarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Transferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cheque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Otro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalLetras, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(FormapagoDetalleVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CajaTransaccionDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaTransaccionDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(FormapagoDetalleVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("FORMAS DE PAGO/TOTALES", jPanel9);

        CajaObservacion.setEditable(false);
        CajaObservacion.setColumns(20);
        CajaObservacion.setRows(5);
        CajaObservacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OBSERVACIÓNES"));
        C.setViewportView(CajaObservacion);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(C, javax.swing.GroupLayout.DEFAULT_SIZE, 745, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(C, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("OBSERVACIÓNES", jPanel2);

        jLabel30.setText("CERTIFICADOR:");

        jLabel33.setText("NIT CERTIFICADOR:");

        jLabel35.setText("NIT EMISOR:");

        CajaNitEmisor.setEditable(false);

        NitCertificador.setEditable(false);
        NitCertificador.setText("CF");

        CajaCertificador.setEditable(false);
        CajaCertificador.setText("GUATEFACTURAS, SOCIEDAD ANÓNIMA");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NitCertificador)
                    .addComponent(CajaCertificador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
                    .addComponent(CajaNitEmisor, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaCertificador, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NitCertificador, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaNitEmisor, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CERTIFICADOR", jPanel6);

        CajaNumeroAutorizacion.setEditable(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("AUTORIZACIÓN:");

        FechaCertificacion.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("FECHA DE CERTIFICACIÓN:");

        CajaSerieCertificacion.setEditable(false);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("SERIE:");

        NumeroDocumento.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("NÚMERO:");

        jButton4.setText("VERIFICAR EN SAT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("VERIFICAR EN CERT.");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaNumeroAutorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(FechaCertificacion)
                    .addComponent(CajaSerieCertificacion)
                    .addComponent(NumeroDocumento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NumeroDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaSerieCertificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FechaCertificacion, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(CajaNumeroAutorizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("AUT.", jPanel11);

        jPanel10.setBackground(new java.awt.Color(255, 204, 153));

        jButton1.setBackground(new java.awt.Color(0, 195, 0));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setText("IMPRIMIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HOJA CARTA", "TICKET" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("TAMAÑO DE IMPRESIÓN");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jComboBox1)
                        .addContainerGap())))
        );

        TablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE", "CANTIDAD", "PRECIO NORMAL", "%", "PRECIO CON %", "TOTAL", "VALIDACIÓN", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaDetalles.setRowHeight(25);
        TablaDetalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TablaDetalles);
        if (TablaDetalles.getColumnModel().getColumnCount() > 0) {
            TablaDetalles.getColumnModel().getColumn(1).setPreferredWidth(150);
        }

        jPanel12.setBackground(new java.awt.Color(153, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTALES"));

        CajaTotal.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        CajaTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CajaTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL:"));

        CajaPago.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        CajaPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CajaPago.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "EFECTIVO:"));

        CajaCambio.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        CajaCambio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CajaCambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CAMBIO:"));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CajaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaPago, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaCambio, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/Siguiente.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/Atras.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/ACEPTAR_32PX.png"))); // NOI18N
        jButton6.setText("GUARDAR CAMBIOS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/CANCELAR_32PX.png"))); // NOI18N
        jButton7.setText("CANCELAR");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelEditarLayout = new javax.swing.GroupLayout(PanelEditar);
        PanelEditar.setLayout(PanelEditarLayout);
        PanelEditarLayout.setHorizontalGroup(
            PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEditarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );
        PanelEditarLayout.setVerticalGroup(
            PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelEditarLayout.createSequentialGroup()
                .addGroup(PanelEditarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jMenu2.setText("EDITAR VENTA");

        jMenuItem1.setText("RE-ABRIR VENTA");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator2);

        jMenuItem2.setText("ANULAR VENTA");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator3);

        IR_A_VENTA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        IR_A_VENTA.setText("IR A VENTA");
        IR_A_VENTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IR_A_VENTAActionPerformed(evt);
            }
        });
        jMenu2.add(IR_A_VENTA);
        jMenu2.add(jSeparator4);

        jMenuItem6.setText("EDITAR");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("COMPARTIR DOCUMENTO");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/gmail_new_logo_icon_159149.png"))); // NOI18N
        jMenuItem3.setText("ENVIAR");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator1);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/PDFPequeño.png"))); // NOI18N
        jMenuItem4.setText("PDF");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("SAT");

        jMenuItem5.setText("FACTURA ELECTRÓNICA");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);
        jMenu1.add(jSeparator5);

        jMenu4.setText("NOTAS DE CRÉDITO/DÉBITO");

        jMenuItem7.setText("NOTA DE CRÉDITO");
        jMenu4.add(jMenuItem7);
        jMenu4.add(jSeparator6);

        jMenuItem8.setText("NOTA DE DÉBITO");
        jMenu4.add(jMenuItem8);

        jMenu1.add(jMenu4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(PanelEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Imprimir();
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void Imprimir(){
        if(jComboBox1.getSelectedIndex()== 0){
        if(TipoDocumento.getText().equals("FACTURA")){
            FacturaCopia(0);
        }else{
          Proforma(0);  
        }    
        }else if(jComboBox1.getSelectedIndex()== 1){
            if(TipoDocumento.getText().equals("FACTURA")){
            FacturaCopia(1);
        }else{
          Proforma(1);  
        }
        }
    }
    
    public void FacturaCopia(int TipoDocumentoImpresion) {
        Documentos documentos = new Documentos();
        DatosEmpresaDao datosDao= new DatosEmpresaDao();
        DatosEmpresaGeneral DaEm = new DatosEmpresaGeneral();
        DaEm = datosDao.VerDatos();      
        DatosEmpresa datosempresa= new DatosEmpresa();
        datosempresa.setUsuario(CajaVendedor.getText());
        datosempresa.setNombreEmpresa(DaEm.getNombreEmpresa());
        datosempresa.setNit(DaEm.getNit());
        datosempresa.setDireccion(DaEm.getDireccion());
        datosempresa.setTel(DaEm.getTel());
        datosempresa.setEslogan(DaEm.getEslogan());
        datosempresa.setPoliticas(DaEm.getPoliticas());
        
        //DATOS DE AUTORIZACION DE DTE
        DatosCertificador DatosCertificador = new DatosCertificador(); 
        DocumentoFel Fel= new DocumentoFel();
        
        DatosCertificador = datosDao.VerDatosCertificador();
        Fel.setFechaAutorizacion(FechaCertificacion.getText());
        Fel.setNumeroAutorizacion(CajaNumeroAutorizacion.getText());
        Fel.setNumeroDocumento(NumeroDocumento.getText());
        Fel.setSerieDocumento(CajaSerieCertificacion.getText());
        Fel.setTipoDocumento(TipoDocumento.getText());
        
        ///DATOS CERTIFICADOR
        
        DatosCertificador.setNombreCertificador(CajaCertificador.getText());
        DatosCertificador.setNitCertificador(NitCertificador.getText());
        
        
        DatosClienteYFactura datos;
        datos = new DatosClienteYFactura(CajaCliente.getText(), CajaNit.getText(), CajaDireccion.getText(), Fac.getText(), CajaTotal.getText(), CajaPago.getText(), CajaCambio.getText(), Fac.getText(), Fac.getText(), 
        FormapagoDetalleVenta.getText(), CajaTransaccionDetalle.getText(), CajaVendedor.getText(), CajaObservacion.getText(), TotalLetras.getText(), HoraVenta.getText() + " "+Fechaventa.getText(), Fechaventa.getText());
        if(TipoDocumentoImpresion == 0){
            try {
                documentos.Facturaa(datos, datosempresa, DatosCertificador, Fel, "N° Interno", TablaDetalles, 0, "https://felpub.c.sat.gob.gt/verificador-web/publico/vistas/verificacionDte.jsf?tipo=autorizacion&\n"
                        + "numero=" + CajaNumeroAutorizacion.getText() + "&emisor=" + CajaNitEmisor.getText() + "&receptor=" + CajaNit.getText() + "&monto=" + CajaTotal.getText());
            } catch (Exception e) {
            }
        } else if (TipoDocumentoImpresion == 1) {
            try {
                documentos.Facturaa(datos, datosempresa, DatosCertificador, Fel, "N° Interno", TablaDetalles, 1, "https://felpub.c.sat.gob.gt/verificador-web/publico/vistas/verificacionDte.jsf?tipo=autorizacion&\n"
                        + "numero=" + CajaNumeroAutorizacion.getText() + "&emisor=" + CajaNitEmisor.getText() + "&receptor=" + CajaNit.getText() + "&monto=" + CajaTotal.getText());
            } catch (Exception e) {
            }
        }
        
        

    }
    
    public void Proforma(int TipoDocumentoImpresion) {
        Documentos documentos = new Documentos();
        DatosEmpresaDao datosDao= new DatosEmpresaDao();
        DatosEmpresaGeneral DaEm = new DatosEmpresaGeneral();
        DaEm = datosDao.VerDatos();      
        DatosEmpresa datosempresa= new DatosEmpresa();
        datosempresa.setUsuario(CajaVendedor.getText());
        datosempresa.setNombreEmpresa(DaEm.getNombreEmpresa());
        datosempresa.setNit(DaEm.getNit());
        datosempresa.setDireccion(DaEm.getDireccion());
        datosempresa.setTel(DaEm.getTel());
        datosempresa.setEslogan(DaEm.getEslogan());
        datosempresa.setPoliticas(DaEm.getPoliticas());
        
        //DATOS DE AUTORIZACION DE DTE
        DatosCertificador DatosCertificador = new DatosCertificador(); 
        DocumentoFel Fel= new DocumentoFel();
        
        DatosCertificador = datosDao.VerDatosCertificador();
        Fel.setFechaAutorizacion(FechaCertificacion.getText());
        Fel.setTipoDocumento(TipoDocumento.getText());
        DatosClienteYFactura datos;
        datos = new DatosClienteYFactura(CajaCliente.getText(), CajaNit.getText(), CajaDireccion.getText(), Fac.getText(), CajaTotal.getText(), CajaPago.getText(), CajaCambio.getText(), Fac.getText(), Fac.getText(),
                FormapagoDetalleVenta.getText(), CajaTransaccionDetalle.getText(), CajaVendedor.getText(), CajaObservacion.getText(), TotalLetras.getText(), HoraVenta.getText() + " " + Fechaventa.getText(), Fechaventa.getText());

        if (TipoDocumentoImpresion == 0) {
            try {

                documentos.FacturaNoDTE(datos, datosempresa, "N° Interno", TablaDetalles, Fel, 0);
            } catch (PrinterException ex) {
                Logger.getLogger(Detalles.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (TipoDocumentoImpresion == 1) {
            try {
                documentos.FacturaNoDTE(datos, datosempresa, "N° Interno", TablaDetalles, Fel, 1);
            } catch (PrinterException ex) {
                Logger.getLogger(Detalles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
    public String FacturaCopiaSinImprimir(Boolean Guardar, int TipoDocumentoImpresion) {
        String RUTA = "";
        Documentos documentos = new Documentos();
        DatosEmpresaDao datosDao= new DatosEmpresaDao();
        DatosEmpresaGeneral DaEm = new DatosEmpresaGeneral();
        DaEm = datosDao.VerDatos();      
        DatosEmpresa datosempresa= new DatosEmpresa();
        datosempresa.setUsuario(CajaVendedor.getText());
        datosempresa.setNombreEmpresa(DaEm.getNombreEmpresa());
        datosempresa.setNit(DaEm.getNit());
        datosempresa.setDireccion(DaEm.getDireccion());
        datosempresa.setTel(DaEm.getTel());
        datosempresa.setEslogan(DaEm.getEslogan());
        datosempresa.setPoliticas(DaEm.getPoliticas());
        
        //DATOS DE AUTORIZACION DE DTE
        DatosCertificador DatosCertificador = new DatosCertificador(); 
        DocumentoFel Fel= new DocumentoFel();
        
        DatosCertificador = datosDao.VerDatosCertificador();
        Fel.setFechaAutorizacion(FechaCertificacion.getText());
        Fel.setNumeroAutorizacion(CajaNumeroAutorizacion.getText());
        Fel.setNumeroDocumento(NumeroDocumento.getText());
        Fel.setSerieDocumento(CajaSerieCertificacion.getText());
        Fel.setTipoDocumento(TipoDocumento.getText());
        
        ///DATOS CERTIFICADOR
        
        DatosCertificador.setNombreCertificador(CajaCertificador.getText());
        DatosCertificador.setNitCertificador(NitCertificador.getText());
        
        
        DatosClienteYFactura datos;
        datos = new DatosClienteYFactura(CajaCliente.getText(), CajaNit.getText(), CajaDireccion.getText(), Fac.getText(), CajaTotal.getText(), CajaPago.getText(), CajaCambio.getText(), Fac.getText(), Fac.getText(), 
        FormapagoDetalleVenta.getText(), CajaTransaccionDetalle.getText(), CajaVendedor.getText(), CajaObservacion.getText(), TotalLetras.getText(), HoraVenta.getText() + " "+Fechaventa.getText(), Fechaventa.getText());
        if(Guardar == false){
           //documentos.FacturaaCrearSinImprimir(datos, datosempresa,DatosCertificador, Fel,"N° Interno", TablaDetalles); 
        }else{
            
            if(TipoDocumentoImpresion== 0){
                RUTA = documentos.CrearYGuardarDocumento(datos, datosempresa,DatosCertificador, Fel,"N° Interno", TablaDetalles, 0);
        }else if(TipoDocumentoImpresion== 1){
            RUTA =  documentos.CrearYGuardarDocumento(datos, datosempresa,DatosCertificador, Fel,"N° Interno", TablaDetalles, 1);
        }
            
        }
        return RUTA;
    }
    
    public void REABRIR() {
        int Seleccion = JOptionPane.showConfirmDialog(null, """
                                                            ESTA ACCI\u00d3N REABRIRA LA VENTA, POR LO TANTO ESTA SE ANULAR\u00c1
                                                            TENGA EN CUENTA QUE SI TIENE PRODUCTOS EN LA TABLA DE VENTAS, ESTA LA COMPLEMENTAR\u00c1""", "¿ESTÁ SEGURO DE RE-ABRIR LA VENTA?", JOptionPane.YES_NO_OPTION);
        Boolean VerificarCheckEnVenta = pos.CheckIngresoAutomatico.isSelected();
        if (Seleccion == 0) {
            Boolean Anular = AnularTodaLaVenta();
            if (Anular == true) {
                pos.principal.MoverEntreSistema();
                if (VerificarCheckEnVenta == true) {
                    pos.CheckIngresoAutomatico.setSelected(false);
                }
                for (int i = 0; i < TablaDetalles.getRowCount(); i++) {
                    pos.LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
                    if (Integer.parseInt(TablaDetalles.getValueAt(i, 8).toString()) == 0) {
                        pos.NombreVenta.setText(TablaDetalles.getValueAt(i, 1).toString());
                        pos.IdVenta.setText(TablaDetalles.getValueAt(i, 0).toString());
                        pos.CantidadVenta.setText(TablaDetalles.getValueAt(i, 2).toString());
                        pos.Final.setText(TablaDetalles.getValueAt(i, 3).toString());
                        pos.AgregarProducto();
                    } else {
                        pos.BusquedaCodigoBarras(TablaDetalles.getValueAt(i, 0).toString());
                        pos.CantidadVenta.setText(TablaDetalles.getValueAt(i, 2).toString());
                        pos.Final.setText(TablaDetalles.getValueAt(i, 3).toString());
                        pos.AgregarProducto();

                    }
                }
                if (VerificarCheckEnVenta == true) {
                    pos.CheckIngresoAutomatico.setSelected(true);
                }
                pos.ConsultarNit_CUIFinal(CajaNit.getText());
                pos.ConsultarNit_CUIFinal(CajaNit.getText());
                pos.LIMPIAR_CAJA_CONSULTA_PRODUCTOS();
                this.dispose();
            }
        }
    }

    public void ANULAR() {
        int seleccion = JOptionPane.showConfirmDialog(this, "¿ESTÁ SEGURO DE ELIMINAR ESTA VENTA?\n*Esto no se puede deshacer");
        if (seleccion == 0) {
            if (TipoDocumento.getText().equals("FACTURA")) {
                AnularDTE();
            } else {
                AnularVenta();
            }
            principal.MD.CARGAR_REGISTROS();
            this.dispose();
        }
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (METODOS_GLOBALES.VALIDAR_ANULAR_DOCUMENTO(Fechaventa.getText()) == false) {
            int seleccionValidacion = JOptionPane.showConfirmDialog(this, "ESTA FACTURA TIENE MÁS DE 20 DÍAS DE EMTIDA\n¿ESTÁ SEGURO DE ANULARLA?");
            if (seleccionValidacion == 0) {
                ANULAR();
            }
        } else {
            ANULAR();
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        InterfazGmail VP= new InterfazGmail(Fac.getText(), CajaVendedor.getText(), FacturaCopiaSinImprimir(true, jComboBox1.getSelectedIndex()), TipoDocumento.getText());
        VP.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        FacturaCopiaSinImprimir(true, jComboBox1.getSelectedIndex());
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        v.RecordarFormatoImpresion(jComboBox1);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ObtenerId(1, DiariasGenerales);
        CargarTodosLosDetalles(Fac.getText());
        if(EstadoVenta.getSelectedItem().toString().equals("ANULADO")){
            jMenuItem2.setEnabled(false);
        }else{
           jMenuItem2.setEnabled(true); 
        }
        ValidarBotones();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ObtenerId(0, DiariasGenerales);
        CargarTodosLosDetalles(Fac.getText());
        if(EstadoVenta.getSelectedItem().toString().equals("ANULADO")){
            jMenuItem2.setEnabled(false);
        }else{
           jMenuItem2.setEnabled(true); 
        }
        ValidarBotones();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        VerificarDTE();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        VerificarDTEConCertificador();
    }//GEN-LAST:event_jButton5ActionPerformed

    public boolean VALIDAR_TOTAL(){
        boolean ESTADO=false;
        if(Float.parseFloat(CajaTotal.getText())>=2500 && CajaNit.getText().equals("CF") || CajaNit.getText().equals("cf") || CajaNit.getText().equals("CONSUMIDOR FINAL")){
            ESTADO=false;
            JOptionPane.showMessageDialog(this, "¡DEBE INGRESAR UN NÚMERO DE NIT O CUI PARA PODER GENERAR ESTA FACTURA!", "*****MAYOR A 2500****", JOptionPane.ERROR_MESSAGE);
        }else{
            ESTADO=true;
        }
        return ESTADO;
    }
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if(VALIDAR_TOTAL()==true){
        int Seleccion= JOptionPane.showConfirmDialog(null, "ESTA ACCIÓN CONVERTIRÁ ESTA VENTA A FACTURA ELECTRÓNICA\n ¿ESTÁ SEGURO DE REALIZAR LA ACCIÓN?");
        if(Seleccion== 0) {
                GenerarFacturaElectronica();
            }
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (METODOS_GLOBALES.VALIDAR_ANULAR_DOCUMENTO(Fechaventa.getText()) == false) {
            int seleccionValidacion = JOptionPane.showConfirmDialog(this, "ESTA FACTURA TIENE MÁS DE 20 DÍAS DE EMTIDA\n¿ESTÁ SEGURO DE ANULARLA?");
            if (seleccionValidacion == 0) {
                REABRIR();
            }
        }else{
            REABRIR();
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void IR_A_VENTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IR_A_VENTAActionPerformed
        principal.MoverEntreSistema();
    }//GEN-LAST:event_IR_A_VENTAActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        GUARDAR_KARDEX();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        Editar();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Editar();
        CargarTodosLosDetalles(NumeroFactura);
        v.CargarDatosFormatoImpresion(jComboBox1);
        DiariasGenerales= TipoDeVista;
        ValidarBotones();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Editar_Logica();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void EfectivoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EfectivoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Efectivo);
    }//GEN-LAST:event_EfectivoKeyTyped

    private void TarjetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TarjetaKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Tarjeta);
    }//GEN-LAST:event_TarjetaKeyTyped

    private void TransferenciaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TransferenciaKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Transferencia);
    }//GEN-LAST:event_TransferenciaKeyTyped

    private void ChequeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChequeKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Cheque);
    }//GEN-LAST:event_ChequeKeyTyped

    private void OtroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OtroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_OtroKeyTyped

    private void EfectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EfectivoKeyReleased
        if(Efectivo.getText() != ""){
            METODOS_GLOBALES.DESVACIAR_CAJAS(jPanel13);
            SUMAR_CANTIDADES();
        }
    }//GEN-LAST:event_EfectivoKeyReleased

    private void TarjetaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TarjetaKeyReleased
        if(Tarjeta.getText() != ""){
            METODOS_GLOBALES.DESVACIAR_CAJAS(jPanel13);
            SUMAR_CANTIDADES();
        }
    }//GEN-LAST:event_TarjetaKeyReleased

    private void TransferenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TransferenciaKeyReleased
        if(Transferencia.getText() != ""){
            METODOS_GLOBALES.DESVACIAR_CAJAS(jPanel13);
            SUMAR_CANTIDADES();
        }
    }//GEN-LAST:event_TransferenciaKeyReleased

    private void ChequeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChequeKeyReleased
        if(Cheque.getText() != ""){
            METODOS_GLOBALES.DESVACIAR_CAJAS(jPanel13);
            SUMAR_CANTIDADES();
        }
    }//GEN-LAST:event_ChequeKeyReleased

    private void OtroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_OtroKeyReleased
        if(Otro.getText() != ""){
            METODOS_GLOBALES.DESVACIAR_CAJAS(jPanel13);
            SUMAR_CANTIDADES();
        }
    }//GEN-LAST:event_OtroKeyReleased

    
    public boolean AnularTodaLaVenta() {
        Boolean Resultado = false;
        int seleccion = JOptionPane.showConfirmDialog(this, "¿ESTÁ SEGURO DE ELIMINAR ESTA VENTA?\n*Esto no se puede deshacer");
        if (seleccion == 0) {
            Resultado = true;
            if (TipoDocumento.getText().equals("FACTURA")) {
                AnularDTE();
            } else {
                AnularVenta();
            }
        }
        return Resultado;
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
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detalles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Detalles().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane C;
    private javax.swing.JLabel CajaCambio;
    private javax.swing.JTextField CajaCertificador;
    private javax.swing.JTextField CajaCliente;
    private javax.swing.JTextField CajaDireccion;
    private javax.swing.JTextField CajaNit;
    private javax.swing.JTextField CajaNitEmisor;
    private javax.swing.JTextField CajaNumeroAutorizacion;
    private javax.swing.JTextArea CajaObservacion;
    private javax.swing.JLabel CajaPago;
    private javax.swing.JTextField CajaSerieCertificacion;
    private javax.swing.JLabel CajaTotal;
    public javax.swing.JLabel CajaTransaccionDetalle;
    private javax.swing.JTextField CajaVendedor;
    private javax.swing.JTextField Cheque;
    private javax.swing.JTextField Efectivo;
    private javax.swing.JComboBox<String> EstadoVenta;
    private javax.swing.JTextField Fac;
    private javax.swing.JTextField FechaCertificacion;
    private javax.swing.JLabel Fechaventa;
    public javax.swing.JLabel FormapagoDetalleVenta;
    private javax.swing.JLabel HoraVenta;
    private javax.swing.JMenuItem IR_A_VENTA;
    public final javax.swing.JTextField Id = new javax.swing.JTextField();
    private javax.swing.JTextField NitCertificador;
    private javax.swing.JTextField NumeroDocumento;
    private javax.swing.JTextField Otro;
    private javax.swing.JPanel PanelEditar;
    private javax.swing.JTable TablaDetalles;
    private javax.swing.JTextField Tarjeta;
    private javax.swing.JTextField TipoDocumento;
    private javax.swing.JLabel TotalLetras;
    private javax.swing.JTextField Transferencia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    public javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
    public String Hora() {
        Date hour = new Date();
        String strDateFormat = "HH:mm:ss";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String hora = objSDF.format(hour);

        return hora;
    }

    public String Fecha() {
        Date fech = new Date();
        String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fecha = Fechas.format(fech);

        return fecha;
    }

    public void AnularDTE() {
        String MotivoAnulacion = JOptionPane.showInputDialog("INGRESE EL MOTIVO DE ANULACIÓN DE LA FACTURA CON AUTORIZACION:\n" + CajaNumeroAutorizacion.getText(), "CAMBIOS");
        AnularFactura AF = new AnularFactura();
        EnvioDatosAnularFactura EDAF = new EnvioDatosAnularFactura();
        XMLAnularFactura XMLAF = new XMLAnularFactura();
        RespuestaDatosAnularFactura RDAF = new RespuestaDatosAnularFactura();

        EDAF.setNumeroDocumentoAAnularDatosGenerales(CajaNumeroAutorizacion.getText());
        EDAF.setNitReceptor(CajaNit.getText());
        EDAF.setNITEmisor(CajaNitEmisor.getText());
        EDAF.setFechaEmisionDocumentoAnular(FechaCertificacion.getText());
        EDAF.setFechaHoraAnulacion(Fecha() + "T" + Hora());
        EDAF.setMotivoAnulacion(MotivoAnulacion);
        Boolean ResultadoXML = XMLAF.GenerarXMLAnularFactura(EDAF);

        if (ResultadoXML == true) {
            RDAF = AF.AnularFactura();

            if (RDAF.getESTADO() == true) {
                AnularVenta();
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("¡ÉXITO!", "¡LA FACTURA SE ANULÓ CORRECTAMENTE!\n" + RDAF.getMensaje() + "ACUSE DE RECIBIDO POR LA SAT: " + RDAF.getAcuseReciboSAT(), DesktopNotify.SUCCESS, 10000L);
            }
        } else {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("¡FRACASO!", "¡LA FACTURA NO SE ANULÓ CORRECTAMENTE!\n" + RDAF.getMensaje(), DesktopNotify.ERROR, 10000L);
        }

    }

    public void VerificarDTE() {
        if (!CajaNumeroAutorizacion.getText().equals("")) {
            if (java.awt.Desktop.isDesktopSupported()) {
                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    try {
                        java.net.URI uri = new java.net.URI("https://felpub.c.sat.gob.gt/verificador-web/publico/vistas/verificacionDte.jsf?tipo=autorizacion&numero=" + CajaNumeroAutorizacion.getText() + "&emisor=" + CajaNitEmisor.getText() + "&receptor=" + CajaNit.getText() + "&monto=" + CajaTotal.getText());
                        desktop.browse(uri);
                    } catch (URISyntaxException | IOException ex) {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                        DesktopNotify.showDesktopMessage("¡FRACASO!", "¡ESTE DOCUMENTO NO ES ELECTRÓNICO O NO SE CERTIFICÓ CORRECTAMENTE!\n" + ex, DesktopNotify.ERROR, 10000L);
                    }
                }
            }
        } else {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("¡FRACASO!", "¡ESTE DOCUMENTO NO ES ELECTRÓNICO O NO SE CERTIFICÓ CORRECTAMENTE!\n", DesktopNotify.ERROR, 10000L);
        }
    }

    public void VerificarDTEConCertificador() {
        Runnable runnable_AVISO_DETALLES = new Runnable() {
            @Override
            public void run() {
                VENTANA_AVISO= new AVISOS("CONSULTANDO FACTURA, ¡ESPERE!", "ESPERE UN MOMENTO");
                VENTANA_AVISO.setVisible(true);
                VENTANA_AVISO.toFront();
            }
        };
        Thread hiloAviso = new Thread(runnable_AVISO_DETALLES);
        hiloAviso.start();
        
        Runnable runnable_AVISO_VerificarDTEConCertificador = new Runnable() {
            @Override
            public void run() {
        ConsultarDTE CDTE = new ConsultarDTE();
        DatosConsultarDTE DCDTE = new DatosConsultarDTE();
        DU = new DatosUsuario();

        DCDTE.setGUID(CajaNumeroAutorizacion.getText());
        DCDTE = CDTE.ObtenerDTE(DCDTE);
        if (DCDTE.getEstado() == true) {
            VENTANA_AVISO.dispose();
            JOptionPane.showMessageDialog(null, "ESTADO: " + DCDTE.getESTATUS() + "\nTIPO DE DTE: " + DCDTE.getTIPO_DTE() + "\nAUTH: " + DCDTE.getGUID() + "\nSERIE: " + DCDTE.getSERIE() + "\nNIT CLIENTE: " + DCDTE.getNIT_COMPRADOR()
                            + "\nCLIENTE: " + DCDTE.getNIT_COMPRADOR() + "\nACUSE DTE: " + DCDTE.getACUSE_RECIBO_SAT_DTE() + "\nACUSE ANULACIÓN: " + DCDTE.getACUSE_RECIBO_ANULACION(), "CONSULTA DE DTE", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        };
        Thread hilo_VerificarDTEConCertificador = new Thread(runnable_AVISO_VerificarDTEConCertificador);
        hilo_VerificarDTEConCertificador.start();
    }

    public void AumentarStock() {
        for (int i = 0; i < TablaDetalles.getRowCount(); i++) {
            if (Integer.parseInt(TablaDetalles.getValueAt(i, 8).toString()) == 1) {
                String cod = TablaDetalles.getValueAt(i, 0).toString();
                Float cant = Float.parseFloat(TablaDetalles.getValueAt(i, 2).toString());
                Productos pro = new Productos();
                ProductosDao proDao = new ProductosDao();
                pro = proDao.BuscarPro(cod);
                Float StockActual = pro.getCantidad() + cant;
                Boolean ResultadoAumento = VentaDao.ActualizarStock(StockActual, cod);
                if (ResultadoAumento == false) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("¡FRACASO AL AUMENTAR STOCK!", "¡EL PRODUCTO CON CÓDIGO \n" + cod + " NO EXISTE!", DesktopNotify.ERROR, 10000L);
                }
            }
        }
    }

    public void AnularVenta() {
        VentaDao Vdao= new VentaDao();
        Boolean EstadoDeAnulacion = Vdao.AnularVentaRegistro(Fac.getText());
        if (EstadoDeAnulacion == true) {
            GUARDAR_KARDEX();
            AumentarStock();
        }
    }
    
    private synchronized Boolean GUARDAR_KARDEX() {
        Boolean Estado=false;
        KardexDao kdDao = new KardexDao();
        Kardex Kd;
        for (int i = 0; i < TablaDetalles.getRowCount(); i++) {
            if (TablaDetalles.getValueAt(i, 8).toString().equals("1")) {
                Kd = new Kardex();
                int Id_Producto = VentaDao.BuscarIdProducto(TablaDetalles.getValueAt(i, 0).toString());
                String CANTIDAD_A_DEVOLVER= TablaDetalles.getValueAt(i, 2).toString();
                String STOCK_ANTES= String.valueOf(VentaDao.BuscarSTOCKProducto(Id_Producto));
                String STOCK_DESPUES= String.valueOf(Float.parseFloat(STOCK_ANTES)+Float.parseFloat(CANTIDAD_A_DEVOLVER));
                Kd.setID_Codigo_Producto_Kardex(Id_Producto);
                Kd.setTitulo_Kardex(" SE REINGRESO DE "+TipoDocumento.getText()+": "+Fac.getText());
                Kd.setEntrada_Kardex(CANTIDAD_A_DEVOLVER);
                Kd.setSalida_Kardex("0");
                Kd.setAntes_Kardex(STOCK_ANTES);
                Kd.setDespues_Kardex(STOCK_DESPUES);
                Kd.setFecha_Modificacion_Kardex(METODOS_GLOBALES.Fecha() + " " + METODOS_GLOBALES.Hora());
                Kd.setUsuario_Modifico_Kardex(PARAMETROS_USUARIOS.ID_USUARIO);
                Kd.setModulo_Kardex("MOVIMIENTOS");
                Estado = kdDao.RegistrarKARDEX(Kd);
            }
        }
        return Estado;
    }

    public void GenerarFacturaElectronica() {
        EnviarParametrosAXML();
    }

    public void EnviarParametrosAXML() {
        Runnable runnable_AVISO = new Runnable() {
            @Override
            public void run() {
                VENTANA_AVISO= new AVISOS("CERTIFICANDO FACTURA, ¡ESPERE!", "ESPERE UN MOMENTO");
                VENTANA_AVISO.setVisible(true);
                VENTANA_AVISO.toFront();
            }
        };
        Thread hiloAviso = new Thread(runnable_AVISO);
        hiloAviso.start();
        
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
        if (PARAMETROS_EMPRESA.TOKEN_CERTIFICADOR.equals("")) {
            pos.OT.ObtenerToken();
        } else {
            
            GenerarXML GenerarXMLFactura = new GenerarXML();
            EnvioDatosFacturar EDF = new EnvioDatosFacturar();
            CertificarFactura CFact = new CertificarFactura();
            RespuestaCertificacion CFACTMODEL = new RespuestaCertificacion();

            EDF.setFechaHoraEmision(Fecha() + "T" + Hora());
            EDF.setCodigoMoneda("GTQ");
            EDF.setTipoDocumento("FACT");

            EDF.setNITEmisor(PARAMETROS_EMPRESA.NIT_EMPRESA);
            EDF.setNombreEmisor(PARAMETROS_EMPRESA.PROPIETARIO_EMPRESA);
            EDF.setCodigoEstablecimiento(PARAMETROS_EMPRESA.CODIGOESTABLECIMIENTO_EMPRESA);
            EDF.setNombreComercial(PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
            EDF.setAfiliacionIVA(PARAMETROS_EMPRESA.AFILICACIONIVA_EMPRESA);
            EDF.setDireccionEmisor(PARAMETROS_EMPRESA.DIRECCION_EMPRESA);
            EDF.setCodigoPostalEmisor(PARAMETROS_EMPRESA.CODIGOPOSTAL_EMPRESA);
            EDF.setMunicipioEmisor(PARAMETROS_EMPRESA.MUNICIPIO_EMPRESA);
            EDF.setDepartamentoEmisor(PARAMETROS_EMPRESA.DEPARTAMENTO_EMPRESA);
            EDF.setPaisEmisor(PARAMETROS_EMPRESA.PAIS_EMPRESA);

            ObtenerCliente(CajaNit.getText());
            
            if(jLabel6.getText().equals("CUI")){
             EDF.setTipo_Especial("CUI");   
            }

            EDF.setNombreReceptor(NombreCliente);
            EDF.setIDReceptor(NitCliente);
            EDF.setDireccionReceptor(DireccionCliente);
            EDF.setCodigoPostalReceptor(CodigoPostalCliente);
            EDF.setMunicipioReceptor(MunicipioCliente);
            EDF.setDepartamentoReceptor(DepartamentoCliente);
            EDF.setPaisReceptor(PaisCliente);
            EDF.setNombreReceptor(NombreCliente);
            

            EDF.setTipoFrase("1");
            EDF.setCodigoEscenario("1");

            EDF.setNombreCortoTotales("IVA");
            EDF.setGranTotalTotales(CajaTotal.getText());
            NUMERO_INTERNO_FACTURA_ELECTRONICA = String.valueOf(new Random().nextLong()).substring(7);

            EDF.setREFERENCIA_INTERNA(NUMERO_INTERNO_FACTURA_ELECTRONICA);
            EDF.setFECHA_REFERENCIA(Fecha() + "T" + Hora());
            EDF.setVALIDAR_REFERENCIA_INTERNA("VALIDAR");
            Boolean Resultado = GenerarXMLFactura.GenerarXMLFactura(EDF, TablaDetalles, jLabel6.getText());
            if (Resultado == true) {
                CFACTMODEL = CFact.CertificarFactura();
                FechaCertificacion.setText(CFACTMODEL.getFecha_de_certificacion());
                CajaNumeroAutorizacion.setText(CFACTMODEL.getAutorizacion());
                CajaSerieCertificacion.setText(CFACTMODEL.getSerie());
                NumeroDocumento.setText(CFACTMODEL.getNUMERO());
                        if (CFACTMODEL.isValidado() == true) {
                            VENTANA_AVISO.dispose();

                            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                            DesktopNotify.showDesktopMessage("ESTADO DE CERTIFICACIÓN", CFACTMODEL.getEstadoCertificacion(), DesktopNotify.SUCCESS, 10000L);
                            ConvertirVentaRegistradaAFacturaElectronica();
                            CargarTodosLosDetalles(Fac.getText());
                            ValidarBotones();
                        } else {
                            VENTANA_AVISO.dispose();

                            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                            DesktopNotify.showDesktopMessage("ESTADO DE CERTIFICACIÓN", CFACTMODEL.getEstadoCertificacion(), DesktopNotify.INFORMATION, 10000L);
                        }

                    }
                }
            }
        };
        Thread hilo = new Thread(runnable);
        hilo.start();
    }
    
    public static void CERRAR_VENTANA(){
    }

    public Boolean ObtenerCliente(String Nit) {
        Clientes cliente = new Clientes();
        ClientesDao cliDao = new ClientesDao();
        cliente = ClientesDao.BuscarClie(Nit);
        Boolean Resultado= cliente.getResutaldoConsulta();
        if (cliente.getResutaldoConsulta() == true) {
            Resultado = true;
            NombreCliente = cliente.getNombre();
            NitCliente = cliente.getIDENTIFICACION();
            DireccionCliente = cliente.getDireccion();
            jLabel6.setText(cliente.getTIPO_IDENTIFICACION());
            String.valueOf(cliente.getIdclientes());
            CodigoPostalCliente = cliente.getCodigoPostal();
            MunicipioCliente = cliente.getMunicipio();
            DepartamentoCliente = cliente.getDepartamento();
            JComboBox Paises = new JComboBox();
            Paises.addItem(cliente.getPais());
            Paises.setSelectedItem(cliente.getPais());
            PaisCliente = cliDao.ConsultaSiglaPais(Paises);
        } else {
            Resultado = false;
        }
        return Resultado;
    }

    public void ConvertirVentaRegistradaAFacturaElectronica() {
        VentaDao vDao = new VentaDao();
        Venta v = new Venta();
        v.setFechaAutorizacion(Fecha() + "T" + Hora());
        v.setNumeroAutorizacion(CajaNumeroAutorizacion.getText());
        v.setSerieDocumento(CajaSerieCertificacion.getText());
        v.setNumeroDocumento(NumeroDocumento.getText());
        v.setTipoDocumentoFel("FACTURA");
        v.setNoFactura(Fac.getText());
        v.setNUMERO_INTERNO(NUMERO_INTERNO_FACTURA_ELECTRONICA);
        vDao.ConvertirVentaADTE(v);
    }
    
    public void SUMAR_CANTIDADES(){
        if(METODOS_GLOBALES.VERIFICAR_LLENADO_CAJAS(jPanel13) == true){
          try {
            Float TOTAL_FINAL_EN_CAJA_PAGO = Float.parseFloat(Efectivo.getText()) + Float.parseFloat(Tarjeta.getText())
                + Float.parseFloat(Transferencia.getText()) + Float.parseFloat(Cheque.getText()) + Float.parseFloat(Otro.getText());
        CajaPago.setText(String.valueOf(TOTAL_FINAL_EN_CAJA_PAGO));
        CajaCambio.setText(String.valueOf(TOTAL_FINAL_EN_CAJA_PAGO-Float.valueOf(CajaTotal.getText())));
        } catch (NumberFormatException e) {
            System.out.println(e.fillInStackTrace());
        }  
        }
    }
}
