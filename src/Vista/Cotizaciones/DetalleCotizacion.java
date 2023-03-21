package Vista.Cotizaciones;

import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Vista.*;
import Clases_Reportes.DatosEmpresa;
import Configuraciones.Ventas;
import Controlador.CotizacionesDao;
import Controlador.DatosEmpresaDao;
import FEL.DatosCertificador;
import FEL.DocumentoFel;
import Gmail.InterfazGmail;
import Modelo.DatosEmpresaGeneral;
import ReportesImpresion.DatosClienteYFactura;
import ReportesImpresion.Documentos;
import Conexiones.ConexionesSQL;
import static Vista.Cotizaciones.CotizacionesGenerales.ActualizarTablaEstado;
import Vista.POS.POS;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class DetalleCotizacion extends javax.swing.JFrame {
    Ventas v= new Ventas();
    int DiariasGenerales;
    Date fech = new Date();
        String strDateFormat = "YYYY-MM-dd";
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        String fecha=objSDF.format(fech);
        POS pos;
    public DetalleCotizacion() {
        
    }
    public DetalleCotizacion(String NumeroFactura, int ModoAbierto, int TipoDeVista, POS pos) {
        
        initComponents();
        this.pos = pos;
        this.setLocationRelativeTo(null);
        CargarTodosLosDetalles(NumeroFactura);
        v.CargarDatosFormatoImpresion(jComboBox1);
        DiariasGenerales= TipoDeVista;
        if(!EstadoVenta.getText().equals("VIGENTE")){
            jMenuItem2.setEnabled(false);
            jButton4.setEnabled(false);
        }else{
           jMenuItem2.setEnabled(true);
           jButton4.setEnabled(true);
        }
        if(ModoAbierto == 0){
        new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             Cerrar();
         }
     },
             20000
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
            
        }
    
    public void VerDetalles(){
        
        DefaultTableModel modeloTabla = new DefaultTableModel();
        TablaDetalles.setModel(modeloTabla);
        String NoF= Fac.getText();
      
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("select CodigoBarras, NombreProducto, CantidadProductos, PrecioUnitario, Total, ProductoRegistrado from detalle_cotizacion where NoCotizacion=?");
            ConexionesSQL.ps.setString(1, NoF);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();

            modeloTabla.addColumn("Codigo");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Cantidad");
            modeloTabla.addColumn("Precio");
            modeloTabla.addColumn("Total");
            modeloTabla.addColumn("ID");

            ResultSetMetaData rsMD = ConexionesSQL.rs.getMetaData();
            int cantidadColumnas = rsMD.getColumnCount();

            int anchotabla[] = {20,150, 10, 10, 10, 2};

            for (int i = 0; i < cantidadColumnas; i++) {
                TablaDetalles.getColumnModel().getColumn(i).setPreferredWidth(anchotabla[i]);
            }
            while (ConexionesSQL.rs.next()) {

                Object fila[] = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = ConexionesSQL.rs.getObject(i + 1);
                }

                modeloTabla.addRow(fila);

            }

        } catch (SQLException e) {
            System.err.println("Error en JFRAME Detalles, " + e);
        }finally{
            ConexionesSQL.RsClose(ConexionesSQL.rs);
            ConexionesSQL.PsClose(ConexionesSQL.ps);
            ConexionesSQL.ConnectionClose(ConexionesSQL.cn);
        }
    
    }
    
    public void VerDetalles2(){
        String NoF= Fac.getText();
        ConexionesSQL.rs = null;
        ConexionesSQL.ps = null;
        ConexionesSQL.cn= ConexionesSQL.Unionsis2.getConnection();
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("select NombreCliente, NitCliente from cotizacion where NoCotizacion=?");
            ConexionesSQL.ps.setString(1, NoF);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            while (ConexionesSQL.rs.next()) {
            CajaCliente.setText(ConexionesSQL.rs.getString("NombreCliente"));
            CajaNit.setText(ConexionesSQL.rs.getString("NitCliente"));
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
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement("select idCotizacion, TotalCotizacion, IdUsuario, HoraRealizada, FechaRealizada, Observacion, TotalLetras"
                    + ", EstadoCotizacion from cotizacion where NoCotizacion=?");
            ConexionesSQL.ps.setString(1, NoF);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            if (ConexionesSQL.rs.next()) {
                Id.setText(ConexionesSQL.rs.getString("idCotizacion"));
                CajaTotal.setText(ConexionesSQL.rs.getString("TotalCotizacion"));
            CajaVendedor.setText(ConexionesSQL.rs.getString("IdUsuario"));
            HoraVenta.setText(ConexionesSQL.rs.getString("HoraRealizada"));
            Fechaventa.setText(ConexionesSQL.rs.getString("FechaRealizada"));
            CajaObservacion.setText(ConexionesSQL.rs.getString("Observacion"));
            TotalLetras.setText(ConexionesSQL.rs.getString("TotalLetras"));
            TipoDocumento.setText("COTIZACIÓN");
            EstadoVenta.setText(ConexionesSQL.rs.getString("EstadoCotizacion"));
            }
        } catch (SQLException e) {
            System.err.println("Error en JFRAME Detalles3, " + e);
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
                Query = "select idCotizacion, NoCotizacion from cotizacion where idCotizacion = (select min(idCotizacion) from cotizacion where idCotizacion > " + Id.getText() + ")";
            } else if (Seleccion == 1) {
                Query = "select idCotizacion, NoCotizacion from cotizacion where idCotizacion = (select max(idCotizacion) from cotizacion where idCotizacion < " + Id.getText() + ")";
            }
        } else if (TipoDeVista == 1) {
            if (Seleccion == 0) {
                Query = "select idCotizacion, NoCotizacion from cotizacion where idCotizacion = (select min(idCotizacion) from cotizacion where idCotizacion > " + Id.getText() + ") AND FechaRealizada LIKE '%" + fecha + "%'";
            } else if (Seleccion == 1) {
                Query = "select idCotizacion, NoCotizacion from cotizacion where idCotizacion = (select max(idCotizacion) from cotizacion where idCotizacion < " + Id.getText() + ") AND FechaRealizada LIKE '%" + fecha + "%'";
            }
        }
        
        
        try {
            ConexionesSQL.ps = ConexionesSQL.cn.prepareStatement(Query);
            
            ConexionesSQL.rs = ConexionesSQL.ps.executeQuery();
            
            if (ConexionesSQL.rs.next()) {
                Id.setText(ConexionesSQL.rs.getString("idCotizacion"));
                Fac.setText(ConexionesSQL.rs.getString("NoCotizacion"));
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
        EstadoVenta = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        Fechaventa = new javax.swing.JLabel();
        HoraVenta = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CajaVendedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        CajaCliente = new javax.swing.JTextField();
        CajaNit = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        TotalLetras = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        C = new javax.swing.JScrollPane();
        CajaObservacion = new javax.swing.JTextArea();
        jPanel10 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaDetalles = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        CajaTotal = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DETALLE DE COTIZACIÓN");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(735, 520));

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));

        Fac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("NÚMERO INTERNO");

        TipoDocumento.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("TIPO DE DOCUMENTO");

        EstadoVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("ESTADO DE VENTA");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Fac)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TipoDocumento)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EstadoVenta))
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
                .addGap(41, 41, 41))
        );

        jPanel7.setBackground(new java.awt.Color(255, 153, 102));

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
                    .addComponent(Id)
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

        jLabel5.setText("CLIENTE:");

        jLabel6.setText("NIT:");

        jLabel8.setText("VENDEDOR:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CajaCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                    .addComponent(CajaNit)
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
                    .addComponent(CajaVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CLIENTE Y VENDEDOR", jPanel3);

        jPanel8.setBackground(new java.awt.Color(255, 204, 204));

        TotalLetras.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL EN LETRAS:"));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TotalLetras, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(TotalLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        TablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Cantidad", "Precio", "Total", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaDetalles.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TablaDetalles);

        jPanel12.setBackground(new java.awt.Color(153, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTALES"));

        CajaTotal.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        CajaTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CajaTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL:", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CajaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(CajaTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
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

        jButton4.setBackground(new java.awt.Color(255, 153, 102));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("VENDER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jMenu2.setText("EDITAR VENTA");

        jMenuItem2.setText("ANULAR COTIZACION");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

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

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Imprimir();
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public void Imprimir(){
        if(jComboBox1.getSelectedIndex()== 0){
            Proforma(0);
        }else if(jComboBox1.getSelectedIndex()== 1){
            Proforma(1);
       
        }
    }
    
    public void Imprimir2(){
        if(jComboBox1.getSelectedIndex()== 0){
            ProformaGuardar(0);
        }else if(jComboBox1.getSelectedIndex()== 1){
            ProformaGuardar(1);
       
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
 
        DocumentoFel Fel= new DocumentoFel();
        Fel.setTipoDocumento(TipoDocumento.getText());
        DatosClienteYFactura datos;
        datos = new DatosClienteYFactura(CajaCliente.getText(), CajaNit.getText(), "", Fac.getText(), CajaTotal.getText(), "0.00", "0.00", Fac.getText(), Fac.getText(), 
        "", "", CajaVendedor.getText(), CajaObservacion.getText(), TotalLetras.getText(), HoraVenta.getText() + " "+Fechaventa.getText(), Fechaventa.getText());
        
        if(TipoDocumentoImpresion== 0){
            try {    
                documentos.DocumentoCotizacion(datos, datosempresa, "N° Interno", TablaDetalles, Fel, 0);
            } catch (PrinterException ex) {
                Logger.getLogger(Detalles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(TipoDocumentoImpresion== 1){
            try {    
                documentos.DocumentoCotizacion(datos, datosempresa, "N° Interno", TablaDetalles, Fel, 1);
            } catch (PrinterException ex) {
                Logger.getLogger(Detalles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void ProformaGuardar(int TipoDocumentoImpresion) {
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
 
        DocumentoFel Fel= new DocumentoFel();
        Fel.setTipoDocumento(TipoDocumento.getText());
        DatosClienteYFactura datos;
        datos = new DatosClienteYFactura(CajaCliente.getText(), CajaNit.getText(), "", Fac.getText(), CajaTotal.getText(), "0.00", "0.00", Fac.getText(), Fac.getText(), 
        "", "", CajaVendedor.getText(), CajaObservacion.getText(), TotalLetras.getText(), HoraVenta.getText() + " "+Fechaventa.getText(), Fechaventa.getText());
        
        if(TipoDocumentoImpresion== 0){
            try {    
                documentos.DocumentoCotizacionGuardar(datos, datosempresa, "N° Interno", TablaDetalles, Fel, 0);
            } catch (PrinterException ex) {
                Logger.getLogger(Detalles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(TipoDocumentoImpresion== 1){
            try {    
                documentos.DocumentoCotizacionGuardar(datos, datosempresa, "N° Interno", TablaDetalles, Fel, 1);
            } catch (PrinterException ex) {
                Logger.getLogger(Detalles.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void abrirarchivo(String archivo){

     try {
            Desktop.getDesktop().open(new File("/Ferretería El Amigo/Ventas de Ferretería El Amigo/"+archivo));
            /*File objetofile = new File (archivo);
            Desktop.getDesktop().open(objetofile);*/

     }catch (IOException ex) {

            JOptionPane.showMessageDialog(null, "¡El archivo no existe o se eliminó!"+ex);

     }

}
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        int seleccion= JOptionPane.showConfirmDialog(this, "¿ESTÁ SEGURO DE ELIMINAR ESTA COTIZACIÓN?\n*Esto no se puede deshacer");
        if(seleccion==0){
            ModificarCotizacion(0);
            ActualizarTablaEstado();
        this.dispose();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        InterfazGmail VP= new InterfazGmail(Fac.getText(), CajaVendedor.getText());
        VP.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        v.RecordarFormatoImpresion(jComboBox1);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ObtenerId(1, DiariasGenerales);
        CargarTodosLosDetalles(Fac.getText());
        if(EstadoVenta.getText().equals("ANULADO")){
            jMenuItem2.setEnabled(false);
        }else{
           jMenuItem2.setEnabled(true); 
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ObtenerId(0, DiariasGenerales);
        CargarTodosLosDetalles(Fac.getText());
        if(EstadoVenta.getText().equals("ANULADO")){
            jMenuItem2.setEnabled(false);
        }else{
           jMenuItem2.setEnabled(true); 
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        RealizarVenta();
        ActualizarTablaEstado();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Imprimir2();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    
    public void FacturaCopiaSinImprimir(Boolean Guardar, int TipoDocumentoImpresion) {
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
        Fel.setTipoDocumento(TipoDocumento.getText());
        
        
        
        DatosClienteYFactura datos;
        datos = new DatosClienteYFactura(CajaCliente.getText(), CajaNit.getText(), "", Fac.getText(), CajaTotal.getText(), "", "", Fac.getText(), Fac.getText(), 
        "", "", CajaVendedor.getText(), CajaObservacion.getText(), TotalLetras.getText(), HoraVenta.getText() + " "+Fechaventa.getText(), Fechaventa.getText());
        if(Guardar == false){
           //documentos.FacturaaCrearSinImprimir(datos, datosempresa,DatosCertificador, Fel,"N° Interno", TablaDetalles); 
        }else{
            
            if(TipoDocumentoImpresion== 0){
                documentos.CrearYGuardarDocumento(datos, datosempresa,DatosCertificador, Fel,"N° COTIZACIÓN", TablaDetalles, 0);
        }else if(TipoDocumentoImpresion== 1){
            documentos.CrearYGuardarDocumento(datos, datosempresa,DatosCertificador, Fel,"N° COTIZACIÓN", TablaDetalles, 1);
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
            java.util.logging.Logger.getLogger(DetalleCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleCotizacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalleCotizacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane C;
    private javax.swing.JTextField CajaCliente;
    private javax.swing.JTextField CajaNit;
    private javax.swing.JTextArea CajaObservacion;
    private javax.swing.JLabel CajaTotal;
    private javax.swing.JTextField CajaVendedor;
    private javax.swing.JTextField EstadoVenta;
    private javax.swing.JTextField Fac;
    private javax.swing.JLabel Fechaventa;
    private javax.swing.JLabel HoraVenta;
    public final javax.swing.JTextField Id = new javax.swing.JTextField();
    private javax.swing.JTable TablaDetalles;
    private javax.swing.JTextField TipoDocumento;
    private javax.swing.JLabel TotalLetras;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel3;
    public javax.swing.JPanel jPanel4;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JPanel jPanel7;
    public javax.swing.JPanel jPanel8;
    public javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    public javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
    
    public String Hora(){
            Date hour = new Date();
            String strDateFormat = "HH:mm:ss";
            SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
            String hora=objSDF.format(hour);
            
       return hora;
   }   
    
    public String Fecha(){
        Date fech = new Date();
        String strDateFormat1 = "YYYY-MM-dd";
        SimpleDateFormat Fechas = new SimpleDateFormat(strDateFormat1);
        String fecha = Fechas.format(fech);
        
        return fecha;
    }
    
    public void ModificarCotizacion(int Seleccion){
            CotizacionesDao coDao= new CotizacionesDao();
            if(Seleccion==0){
            int Estado= coDao.ModificarCotizacion(String.valueOf(PARAMETROS_USUARIOS.ID_USUARIO), "ANULADO", Fac.getText());
            if(Estado>1){
                this.dispose();
            }    
            }else{
                int Estado= coDao.ModificarCotizacion(String.valueOf(PARAMETROS_USUARIOS.ID_USUARIO), "REALIZADO", Fac.getText());
            this.dispose();
            this.toBack();
            }
            
    }
    
    public void RealizarVenta(){
        int Seleccion= JOptionPane.showConfirmDialog(null, """
                                                           ESTA ACCI\u00d3N ELIMINARA ESTA COTIZACI\u00d3N Y LA PASARA
                                                            A LA SECCI\u00d3N DE VENTAS.
                                                           TENGA EN CUENTA QUE SI TIENE PRODUCTOS EN LA TABLA DE VENTAS, ESTA LA COMPLEMENTAR\u00c1""", "¿ESTÁ SEGURO DE REALIZAR LA VENTA?", JOptionPane.YES_NO_OPTION);
        pos.principal.MoverEntreSistema();
        pos.principal.MoverEntreSistema();
        Boolean VerificarCheckEnVenta = pos.CheckIngresoAutomatico.isSelected();
        if(Seleccion==0){
          //  Principal.MoverEntreSistema();
           // Principal.MoverEntreSistema();
            if(VerificarCheckEnVenta == true){
            pos.CheckIngresoAutomatico.setSelected(false);    
            }
        pos.ConsultarNit_CUIFinal(CajaNit.getText());
        for (int i = 0; i < TablaDetalles.getRowCount(); i++) {
            if(Integer.parseInt(TablaDetalles.getValueAt(i, 5).toString())==0){
                
                pos.NombreVenta.setText(TablaDetalles.getValueAt(i, 1).toString());
                pos.IdVenta.setText(TablaDetalles.getValueAt(i, 0).toString());
                pos.CantidadVenta.setText(TablaDetalles.getValueAt(i, 2).toString());
                pos.Final.setText(TablaDetalles.getValueAt(i, 3).toString());
                pos.AgregarProducto(); 
            }else{
               pos.BusquedaCodigoBarras(TablaDetalles.getValueAt(i, 0).toString());
               pos.Final.setText(TablaDetalles.getValueAt(i, 3).toString());
               pos.CantidadVenta.setText(TablaDetalles.getValueAt(i, 2).toString());
               pos.AgregarProducto(); 
               
            }
        }
        ModificarCotizacion(1);
        if(VerificarCheckEnVenta == true){
            pos.CheckIngresoAutomatico.setSelected(true);    
        }
    }
        }
}
