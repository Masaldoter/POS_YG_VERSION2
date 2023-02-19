/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.POS;

import Controlador.ProductosDao;
import Controlador.loginDao;
import Modelo.DatosEmpresaGeneral;
import Modelo.Productos;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Masaldoter
 */
public class DETALLE_PRODUCTO extends javax.swing.JFrame {

    Float Stock;
    ProductosDao proDao= new ProductosDao();
    private static ImageIcon imagenI;
    private static Icon icono;
    private static String Ruta;
    
    public DETALLE_PRODUCTO() {
        initComponents();
        this.setLocationRelativeTo(null);
        new java.util.Timer().schedule(new java.util.TimerTask() {
         @Override
         public void run() {
             Cerrar();
         }
     },
             100000
     ); 
    }
    
    public void Cerrar(){
        this.dispose();
    }
    
    public static String ObtenerRutaImagen(int Seleccion){
        String Ruta;
        DatosEmpresaGeneral DE= new DatosEmpresaGeneral();
        loginDao login = new loginDao();
        DE = login.VerDatosEmpresaEnLogin();    
        Ruta = switch (Seleccion) {
            case 0 -> CargarDatosRutas(0)+"\\"+DE.getRutaimagenlogo();
            case 2 -> DE.getRutaimagenlogo();
            default -> CargarDatosRutas(0)+"\\"+DE.getRutaimagensistema();
        };
        return Ruta;
    }
    
    public static String CargarDatosRutas(int TipoRuta){
        String Ruta="";
            try {
                Properties propertie3= new Properties();
    InputStream entrada;
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
    
    public void PintarImagen(JLabel lbl, String ruta){
        DETALLE_PRODUCTO.imagenI = new ImageIcon(ruta);
        DETALLE_PRODUCTO.icono = new ImageIcon(DETALLE_PRODUCTO.imagenI.getImage().getScaledInstance(
                lbl.getWidth(), 
                lbl.getHeight(), 
                Image.SCALE_DEFAULT));
        lbl.setIcon(DETALLE_PRODUCTO.icono);
        lbl.repaint();
    }
    public static void PintarImagen2(JLabel lbl, ImageIcon ruta){
        DETALLE_PRODUCTO.imagenI = new ImageIcon();
        DETALLE_PRODUCTO.icono = new ImageIcon(ruta.getImage().getScaledInstance(
                lbl.getWidth(), 
                lbl.getHeight(), 
                Image.SCALE_DEFAULT));
        lbl.setIcon(DETALLE_PRODUCTO.icono);
        lbl.repaint();
    }
    
    public static void ImagenAmigoVenta(){
        Image retValue = Toolkit.getDefaultToolkit().
         getImage(ObtenerRutaImagen(0));
        ImageIcon bl = new ImageIcon(retValue);
        
        PintarImagen2(lblVentaImagen3, bl);
        Ruta = ObtenerRutaImagen(0);
    }

    public void VerProducto(String Codigo){
        
            proDao= new ProductosDao();
            Productos proo = new Productos();
            proo.setCodigoBarras(Codigo);
            proDao.ActualizarTabla(proo);
                    
                    if (proo.getNombre() != null) {
                            CodigoBarrasDetalle.setText(proo.getCodigoBarras());
                            NombreDetalle.setText(proo.getNombre());
                            PrecioNormal.setText("" + proo.getPublico());
                            Especial.setText("" + proo.getPrecioEs());
                            Reventa.setText("" + proo.getPrecioRe());
                            StockDetalle.setText("" + proo.getCantidad());
                            CostoLetrasDetalle.setText("" + proo.getCodigoLetras());
                            PrecioNormal.setText("" +proo.getPublico());
                            Categoria.setText(proo.getCategoriaNombre());
                            Proveedor.setText(proo.getProveedorNombre());
                            Detalle.setText(proo.getDescripcion());
                            TipoPrecio1.setText(proo.getNombreTiposDePrecio1());
                            TipoPrecio2.setText(proo.getNombreTiposDePrecio2());
                            TipoPrecio3.setText(proo.getNombreTiposDePrecio3());
                            SubCategoria.setText(proo.getSubcategoriaNombre());
                            Ubicacion1.setText(proo.getUbicacionNombre1());
                            Ubicacion2.setText(proo.getUbicacionNombre2());      
                        if (proo.getRuta() != null) {
                            PintarImagen(lblVentaImagen3, CargarDatosRutas(1)+"\\"+proo.getRuta());
                            Ruta = CargarDatosRutas(1)+"\\"+proo.getRuta();
                        } else {
                            ImagenAmigoVenta();
                        }
                        
                    
        }else{
                            JOptionPane.showMessageDialog(null, "EL PRODUCTO NO EXISTE EN TIENDA", "VERIFIQUE EL CÓDIGO", JOptionPane.INFORMATION_MESSAGE);
                        }
         
        
        this.setTitle("DETALLE DEL PRODUCTO "+Codigo);
        Stock = Float.parseFloat(StockDetalle.getText());
        if(Stock <= 0 || Stock == 1){
            StockDetalle.setForeground(Color.red);
         }else{
            StockDetalle.setForeground(Color.BLACK);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        CodigoBarrasDetalle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        NombreDetalle = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        CostoLetrasDetalle = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        Ubicacion1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        StockDetalle = new javax.swing.JLabel();
        PrecioNormal = new javax.swing.JLabel();
        TipoPrecio1 = new javax.swing.JLabel();
        Especial = new javax.swing.JLabel();
        TipoPrecio2 = new javax.swing.JLabel();
        Reventa = new javax.swing.JLabel();
        TipoPrecio3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Categoria = new javax.swing.JLabel();
        SubCategoria = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Proveedor = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        Ubicacion2 = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        lblVentaImagen3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        Detalle = new javax.swing.JTextArea();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DETALLE DE PRODUCTO");

        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        CodigoBarrasDetalle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        CodigoBarrasDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CodigoBarrasDetalle.setText("jLabel1");
        CodigoBarrasDetalle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel2.setText("CÓDIGO:");

        jLabel1.setText("NOMBRE:");

        jSeparator2.setForeground(new java.awt.Color(51, 51, 51));

        NombreDetalle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombreDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NombreDetalle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jSeparator3.setForeground(new java.awt.Color(51, 51, 51));

        jLabel3.setText("COSTO:");

        CostoLetrasDetalle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        CostoLetrasDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CostoLetrasDetalle.setText("jLabel4");
        CostoLetrasDetalle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jSeparator4.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator5.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator6.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator7.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator8.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator10.setForeground(new java.awt.Color(51, 51, 51));

        jLabel5.setText("ÚBICACIÓNES:");

        Ubicacion1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Ubicacion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ubicacion1.setText("jLabel6");
        Ubicacion1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel6.setText("STOCK:");

        StockDetalle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        StockDetalle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        StockDetalle.setText("jLabel7");
        StockDetalle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        PrecioNormal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        PrecioNormal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PrecioNormal.setText("jLabel7");
        PrecioNormal.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        TipoPrecio1.setText("PRECIO 1:");

        Especial.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Especial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Especial.setText("jLabel8");
        Especial.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        TipoPrecio2.setText("PRECIO 2:");

        Reventa.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Reventa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reventa.setText("jLabel9");
        Reventa.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        TipoPrecio3.setText("PRECIO 3:");

        jLabel10.setText("CATEGORÍA:");

        Categoria.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Categoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Categoria.setText("jLabel12");
        Categoria.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        SubCategoria.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        SubCategoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SubCategoria.setText("jLabel13");
        SubCategoria.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jLabel11.setText("SUBCATEGORIA:");

        jLabel14.setText("PROVEEDOR:");

        Proveedor.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Proveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Proveedor.setText("jLabel15");
        Proveedor.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jSeparator12.setForeground(new java.awt.Color(51, 51, 51));

        jSeparator13.setForeground(new java.awt.Color(51, 51, 51));

        Ubicacion2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        Ubicacion2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ubicacion2.setText("jLabel15");
        Ubicacion2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jSeparator14.setForeground(new java.awt.Color(51, 51, 51));

        lblVentaImagen3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblVentaImagen3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblVentaImagen3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblVentaImagen3MouseClicked(evt);
            }
        });

        Detalle.setEditable(false);
        Detalle.setColumns(20);
        Detalle.setRows(5);
        Detalle.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLES"));
        jScrollPane4.setViewportView(Detalle);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVentaImagen3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblVentaImagen3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TipoPrecio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TipoPrecio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TipoPrecio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CodigoBarrasDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(jSeparator2)
                            .addComponent(NombreDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CostoLetrasDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator3)
                            .addComponent(StockDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PrecioNormal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Especial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Categoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SubCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Reventa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator12)
                            .addComponent(jSeparator13)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator10)
                            .addComponent(Proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addComponent(Ubicacion1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Ubicacion2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator14))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CodigoBarrasDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(NombreDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CostoLetrasDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(StockDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PrecioNormal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TipoPrecio1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Especial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TipoPrecio2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Reventa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TipoPrecio3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SubCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Proveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Ubicacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Ubicacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblVentaImagen3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblVentaImagen3MouseClicked
       if (evt.getClickCount()> 1){
        VisualizarImagen VI = new VisualizarImagen(this, true, Ruta);
        VI.setVisible(true);
        }
    }//GEN-LAST:event_lblVentaImagen3MouseClicked

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(DETALLE_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DETALLE_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DETALLE_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DETALLE_PRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DETALLE_PRODUCTO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Categoria;
    private javax.swing.JLabel CodigoBarrasDetalle;
    private javax.swing.JLabel CostoLetrasDetalle;
    private javax.swing.JTextArea Detalle;
    private javax.swing.JLabel Especial;
    private javax.swing.JLabel NombreDetalle;
    private javax.swing.JLabel PrecioNormal;
    private javax.swing.JLabel Proveedor;
    private javax.swing.JLabel Reventa;
    private javax.swing.JLabel StockDetalle;
    private javax.swing.JLabel SubCategoria;
    private javax.swing.JLabel TipoPrecio1;
    private javax.swing.JLabel TipoPrecio2;
    private javax.swing.JLabel TipoPrecio3;
    private javax.swing.JLabel Ubicacion1;
    private javax.swing.JLabel Ubicacion2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    public static javax.swing.JLabel lblVentaImagen3;
    // End of variables declaration//GEN-END:variables
}
