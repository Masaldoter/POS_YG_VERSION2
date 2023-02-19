/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Vista.Compras;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CodigosDeBarras.CodigosDeBarras;
import CodigosDeBarras.ParametrosCodigosDeBarras;
import Controlador.Eventos;
import Controlador.NumerosALetras;
import Controlador.ProductosDao;
import Controlador.ProveedoresDao;
import Controlador.loginDao;
import Excel.Excel;
import Excel.Importar;
import Modelo.Categoria;
import Modelo.Combo;
import Modelo.ComboCategoria;
import Modelo.DatosEmpresaGeneral;
import Modelo.Productos;
import Modelo.Proveedor;
import Modelo.SubCategoria;
import Modelo.Ubicacion;
import Tablas.ActualizarTablaVentas;
import Tablas.RenderTablas;
import Vista.Principal;
import com.mxrck.autocompleter.TextAutoCompleter;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class CompraProductos extends javax.swing.JInternalFrame {
    
    private static ImageIcon imagenI;
    private static Icon icono;
    private TextAutoCompleter AutoCompletador;
    int item;
    int numero;
    String co;
    Excel excel= new Excel(); 
    ParametrosCodigosDeBarras ParametrosCodigosBarras = new ParametrosCodigosDeBarras();
    CodigosDeBarras CodigosBarras = new CodigosDeBarras();
    ProveedoresDao proveDao= new ProveedoresDao();
    ProductosDao proDao = new ProductosDao();
    ActualizarTablaVentas tablasVentas;
    Principal FramePrin= new Principal();
    
    public CompraProductos() {
        initComponents();
        VaciarYllenarProveedor();
        VaciarYllenarCategoria(ComboCategorias);
        VaciarYllenarCategoria(ComboCategorias2);
            jTable1.setDragEnabled(true);
    }
    
    public void limpiarCajas() {
        Id.setText(null);
        Nombreproducto.setText(null);
        Cantidad.setText(null);
        Costo.setText(null);
        Publico.setText(null);
        Letras.setText(null);
        Reve.setText(null);
        Es.setText(null);
        CajaDescripcion.setText(null);
        Id.requestFocus();
    }
    
    public void CargarImagen() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ObtenerRutaImagen(0));
        ImageIcon bl = new ImageIcon(retValue);
        PintarImagen2(labelimagen, bl);
        labelruta.setText(ObtenerRutaImagen(2));
    }

    private void VaciarYllenarProveedor() {
        ProveedoresCombo.removeAllItems();

        List<Proveedor> lista = proveDao.ListarProveedor();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdproveedores();
            String Nombre = lista.get(i).getProveedor();
            ProveedoresCombo.addItem(new Combo(id, Nombre).toString());
        }
    }

    private void VaciarYllenarCategoria(JComboBox Categoria) {
        Categoria.removeAllItems();
        List<Categoria> lista = proDao.ListarCategoria();
        for (int i = 0; i < lista.size(); i++) {
            int id = lista.get(i).getIdCategoria();
            String Nombre = lista.get(i).getCategoria();
            Categoria.addItem(new ComboCategoria(id, Nombre).toString());
        }
    }
    
    private void VaciarYllenarSubCategoria(JComboBox Categoria, JComboBox SubCategoria1, JComboBox SubCategoria2) {
        proDao = new ProductosDao();
        SubCategoria1.removeAllItems();
        SubCategoria2.removeAllItems();
        List<SubCategoria> lista = proDao.ListarSubCategoria(proDao.ConsultaIdCategoria(Categoria));
        for (int i = 0; i < lista.size(); i++) {
            SubCategoria1.addItem(lista.get(i).getNombreSubcategoria());
            SubCategoria2.addItem(lista.get(i).getNombreSubcategoria());
        }
    }
    
    private void VaciarYllenarUbicacion() {
        comboubicacion.removeAllItems();
        comboubicacion2.removeAllItems();
        List<Ubicacion> lista = proDao.ListarUbicacion();
        for (int i = 0; i < lista.size(); i++) {
            comboubicacion.addItem(lista.get(i).getNombreUbicacion());
            comboubicacion2.addItem(lista.get(i).getNombreUbicacion());
        }
    }
    
    public static void PintarImagen(JLabel lbl, String ruta) {
        CompraProductos.imagenI = new ImageIcon(ruta);
        CompraProductos.icono = new ImageIcon(CompraProductos.imagenI.getImage().getScaledInstance(
                lbl.getWidth(),
                lbl.getHeight(),
                Image.SCALE_DEFAULT));
        lbl.setIcon(CompraProductos.icono);
        lbl.repaint();
    }
    
    public void PintarImagen2(JLabel lbl, ImageIcon ruta){
        lbl.removeAll();
        this.imagenI = new ImageIcon();
        this.icono = new ImageIcon(ruta.getImage().getScaledInstance(
                lbl.getWidth(), 
                lbl.getHeight(), 
                Image.SCALE_DEFAULT));
        lbl.setIcon(this.icono);
        this.repaint();
    }
    
    public Boolean ValidarCajas() {
        Boolean EstadoCajas = false;
        if (Id.getText().equals("")) {
            Id.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL CÓDIGO DE BARRAS NO PUEDE IR VACÍO!");
        } else if (Nombreproducto.getText().equals("")) {
            Nombreproducto.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL NOMBRE DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (Cantidad.getText().equals("")) {
            Cantidad.requestFocus();
            JOptionPane.showMessageDialog(null, "¡LA CANTIDAD DEL PRODUCTO NO PUEDE IR VACÍA!");
        } else if (Costo.getText().equals("")) {
            Costo.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL PRECIO COSTO DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (Letras.getText().equals("")) {
            Letras.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL COSTO EN LETRAS DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (Publico.getText().equals("")) {
            Publico.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL PRECIO PUBLICO DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (Es.getText().equals("")) {
            Es.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL PRECIO ESPECIAL DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (Reve.getText().equals("")) {
            Reve.requestFocus();
            JOptionPane.showMessageDialog(null, "¡EL PRECIO DE REVENTA DEL PRODUCTO NO PUEDE IR VACÍO!");
        } else if (ComboCategorias.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA CATEGORÍA[1]!");
        } else if (ProveedoresCombo.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UN PROVEEDOR!");
        }else if (ComboSubCategorias.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA SUBCATEGORIA[1]!");
        }else if (ComboSubCategorias1.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA SUBCATEGORIA[2]!");
        }else if (comboubicacion2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA UBICACION[1]!");
        }else if (comboubicacion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA UBICACION[2]!");
        }else if (ComboCategorias2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA CATEGORÍA[2]!");
        }else if (ComboSubCategorias2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA SUBCATEGORIA[3]!");
        }else if (ComboSubCategorias3.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA SUBCATEGORIA[4]!");
        } else{
            EstadoCajas = true;
        }

        return EstadoCajas;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TablaSistema = new javax.swing.JPopupMenu();
        barras = new javax.swing.JMenuItem();
        jPanel36 = new javax.swing.JPanel();
        Agregarp1 = new javax.swing.JButton();
        Nuevop1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        TotalTiposProductos = new javax.swing.JLabel();
        TotalStock = new javax.swing.JLabel();
        TotalCostos = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel35 = new javax.swing.JPanel();
        Nombreproducto = new javax.swing.JTextField();
        Costo = new javax.swing.JTextField();
        Publico = new javax.swing.JTextField();
        Letras = new javax.swing.JTextField();
        Es = new javax.swing.JTextField();
        Reve = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        comboubicacion = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        ProveedoresCombo = new javax.swing.JComboBox<>();
        Id = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        ComboCategorias = new javax.swing.JComboBox<>();
        jButton7 = new javax.swing.JButton();
        Cantidad = new javax.swing.JTextField();
        NombrePrecio1 = new javax.swing.JComboBox<>();
        NombrePrecio2 = new javax.swing.JComboBox<>();
        NombrePrecio3 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        CajaDescripcion = new javax.swing.JTextArea();
        jLabel46 = new javax.swing.JLabel();
        ComboSubCategorias = new javax.swing.JComboBox<>();
        ComboSubCategorias1 = new javax.swing.JComboBox<>();
        comboubicacion2 = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        ComboCategorias2 = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        ComboSubCategorias2 = new javax.swing.JComboBox<>();
        ComboSubCategorias3 = new javax.swing.JComboBox<>();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        labelimagen = new javax.swing.JLabel();
        labelruta = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        NombreUsuarioVista = new javax.swing.JLabel();
        IdUsuario = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        Condicional = new javax.swing.JCheckBoxMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();

        barras.setFont(new java.awt.Font("BarCode", 0, 12)); // NOI18N
        barras.setText("jMenuItem19");
        barras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barrasActionPerformed(evt);
            }
        });
        TablaSistema.add(barras);

        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("INGRESO DE PRODUCTOS");
        setMinimumSize(new java.awt.Dimension(105, 100));
        setVisible(true);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        Agregarp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        Agregarp1.setText("Agregar");
        Agregarp1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Agregarp1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Agregarp1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Agregarp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Agregarp1ActionPerformed(evt);
            }
        });

        Nuevop1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        Nuevop1.setText("LIMPIAR");
        Nuevop1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Nuevop1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Nuevop1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Nuevop1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nuevop1ActionPerformed(evt);
            }
        });

        jButton6.setText("ACTUALIZAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nuevop1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Agregarp1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Agregarp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Nuevop1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "NOMBRE", "STOCK", "COSTO", "COSTO LETRAS", "P/PÚBLICO", "P/ESPECIAL", "P/REVENTA", "CATEGORIA", "PROVEEDOR", "ÚBICACIÓN", "DESC.", "T/P1", "T/P2", "T/P3", "ELIMINAR"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, true, true, false, false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setDragEnabled(true);
        jTable1.setDropMode(javax.swing.DropMode.ON_OR_INSERT);
        jTable1.setRowHeight(32);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(11).setPreferredWidth(20);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PRODUCTOS INGRESADOS *AÚN NO EN LA BASE DE DATOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "GUARDAR DATOS"));

        jButton3.setBackground(new java.awt.Color(0, 195, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("SUBIR A LA BASE DE DATOS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(243, 85, 85));
        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("EXPORTAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(40, 165, 240));
        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("IMPORTAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTALES"));

        TotalTiposProductos.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        TotalTiposProductos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalTiposProductos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TIPOS DE PRODUCTOS"));

        TotalStock.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        TotalStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalStock.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "STOCK"));

        TotalCostos.setFont(new java.awt.Font("Serif", 0, 12)); // NOI18N
        TotalCostos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalCostos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL DE COSTOS"));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(TotalTiposProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(TotalStock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalCostos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(TotalTiposProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(TotalStock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TotalCostos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);

        jPanel35.setMinimumSize(new java.awt.Dimension(350, 480));

        Nombreproducto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Nombreproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Nombreproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NombreproductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreproductoKeyReleased(evt);
            }
        });

        Costo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Costo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Costo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CostoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CostoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CostoKeyTyped(evt);
            }
        });

        Publico.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Publico.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Publico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PublicoActionPerformed(evt);
            }
        });
        Publico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PublicoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PublicoKeyTyped(evt);
            }
        });

        Letras.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Letras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Letras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                LetrasKeyReleased(evt);
            }
        });

        Es.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Es.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Es.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EsKeyTyped(evt);
            }
        });

        Reve.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Reve.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Reve.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ReveKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ReveKeyTyped(evt);
            }
        });

        jLabel35.setBackground(new java.awt.Color(235, 232, 232));
        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("ÚBICACIONES*");

        comboubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tienda" }));
        comboubicacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel11.setBackground(new java.awt.Color(235, 232, 232));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("PROVEEDOR *");

        ProveedoresCombo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProveedoresComboMouseClicked(evt);
            }
        });
        ProveedoresCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProveedoresComboActionPerformed(evt);
            }
        });
        ProveedoresCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProveedoresComboKeyPressed(evt);
            }
        });

        Id.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                IdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                IdKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IdKeyTyped(evt);
            }
        });

        jLabel45.setBackground(new java.awt.Color(235, 232, 232));
        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("CATEGORÍA *");

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

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton7.setText("GENERAR CÓDIGO");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        Cantidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CantidadActionPerformed(evt);
            }
        });
        Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CantidadKeyTyped(evt);
            }
        });

        NombrePrecio1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombrePrecio1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNIDAD", "ESPECIAL", "REVENTA", "CAJA", "ROLLO", "CIENTO", "DOCENA", "PAR", "BOLSA", "LIBRA", "ONZA", "METRO", "YARDA", "VARA", "PIE", "GALON", "LITRO", "BOTELLA", "OCTAVO", "JUEGO" }));

        NombrePrecio2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombrePrecio2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNIDAD", "ESPECIAL", "REVENTA", "CAJA", "ROLLO", "CIENTO", "DOCENA", "PAR", "BOLSA", "LIBRA", "ONZA", "METRO", "YARDA", "VARA", "PIE", "GALON", "LITRO", "BOTELLA", "OCTAVO", "JUEGO" }));
        NombrePrecio2.setSelectedIndex(1);

        NombrePrecio3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        NombrePrecio3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNIDAD", "ESPECIAL", "REVENTA", "CAJA", "ROLLO", "CIENTO", "DOCENA", "PAR", "BOLSA", "LIBRA", "ONZA", "METRO", "YARDA", "VARA", "PIE", "GALON", "LITRO", "BOTELLA", "OCTAVO", "JUEGO" }));
        NombrePrecio3.setSelectedIndex(2);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("CÓDIGO*");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("NOMBRE*");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("STOCK*");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("COSTO(#)");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("COSTO EN LETRAS");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("PRECIO 1*");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("PRECIO 2*");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("PRECIO 3*");

        CajaDescripcion.setColumns(20);
        CajaDescripcion.setRows(5);
        CajaDescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DESCRIPCIÓN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        CajaDescripcion.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                CajaDescripcionMouseMoved(evt);
            }
        });
        CajaDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                CajaDescripcionMouseReleased(evt);
            }
        });
        jScrollPane4.setViewportView(CajaDescripcion);

        jLabel46.setBackground(new java.awt.Color(235, 232, 232));
        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setText("SUBCATEGORÍAS*");

        ComboSubCategorias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboSubCategoriasItemStateChanged(evt);
            }
        });
        ComboSubCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSubCategoriasActionPerformed(evt);
            }
        });
        ComboSubCategorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboSubCategoriasKeyPressed(evt);
            }
        });

        ComboSubCategorias1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSubCategorias1ActionPerformed(evt);
            }
        });
        ComboSubCategorias1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboSubCategorias1KeyPressed(evt);
            }
        });

        comboubicacion2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tienda" }));
        comboubicacion2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel47.setBackground(new java.awt.Color(235, 232, 232));
        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("CATEGORÍA2*");

        ComboCategorias2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboCategorias2ActionPerformed(evt);
            }
        });
        ComboCategorias2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboCategorias2KeyPressed(evt);
            }
        });

        jLabel48.setBackground(new java.awt.Color(235, 232, 232));
        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("SUBCATEGORÍAS*");

        ComboSubCategorias2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboSubCategorias2ItemStateChanged(evt);
            }
        });
        ComboSubCategorias2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSubCategorias2ActionPerformed(evt);
            }
        });
        ComboSubCategorias2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboSubCategorias2KeyPressed(evt);
            }
        });

        ComboSubCategorias3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboSubCategorias3ActionPerformed(evt);
            }
        });
        ComboSubCategorias3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ComboSubCategorias3KeyPressed(evt);
            }
        });

        labelimagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelimagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelimagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelimagenMouseClicked(evt);
            }
        });

        labelruta.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        labelruta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelruta.setText("");

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setText("Nuevo");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                        .addComponent(labelruta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(labelimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 30, Short.MAX_VALUE)
                .addComponent(labelruta, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        NombreUsuarioVista.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        NombreUsuarioVista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NombreUsuarioVista.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "NOMBRE USUARIO"));

        IdUsuario.setFont(new java.awt.Font("DialogInput", 0, 14)); // NOI18N
        IdUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IdUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ID USUARIO"));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(IdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NombreUsuarioVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator10)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(ComboSubCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboSubCategorias1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ComboCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ProveedoresCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboubicacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboubicacion2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator19)
                    .addComponent(jSeparator18)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel47, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(ComboSubCategorias2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ComboSubCategorias3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ComboCategorias2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator8)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(NombrePrecio3, 0, 122, Short.MAX_VALUE)
                                    .addComponent(NombrePrecio2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(NombrePrecio1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Es, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Publico)
                                    .addComponent(Reve)))
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(Costo, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Letras, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Cantidad)
                            .addGroup(jPanel35Layout.createSequentialGroup()
                                .addComponent(Id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Nombreproducto))))
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Nombreproducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Cantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Costo)
                    .addComponent(Letras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NombrePrecio1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Publico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(NombrePrecio2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Es))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NombrePrecio3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Reve))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSubCategorias1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboSubCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboCategorias2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ComboSubCategorias3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboSubCategorias2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ProveedoresCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(comboubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboubicacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IdUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                    .addComponent(NombreUsuarioVista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jScrollPane2.setViewportView(jPanel35);

        jMenu1.setText("OPCIONES");
        jMenu1.add(jSeparator6);

        Condicional.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, 0));
        Condicional.setText("MODO DE BÚSQUEDA");
        Condicional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CondicionalActionPerformed(evt);
            }
        });
        jMenu1.add(Condicional);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("ACCIONES");
        jMenu2.add(jSeparator2);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        jMenuItem7.setText("AGREGAR PRODUCTO");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);
        jMenu2.add(jSeparator1);
        jMenu2.add(jSeparator3);
        jMenu2.add(jSeparator5);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        jMenuItem10.setText("LIMPIAR CAMPOS");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);
        jMenu2.add(jSeparator4);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actual.png"))); // NOI18N
        jMenuItem11.setText("ACTUALIZAR DATOS");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);
        jMenu2.add(jSeparator9);

        jMenuItem1.setText("VACIAR TABLA");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);
        jMenu2.add(jSeparator7);

        jMenuItem2.setText("SUMAR COSTOS");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Agregarp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Agregarp1ActionPerformed
        
        boolean Vacio= ValidarCajas();
        if(Vacio == false){
            AgregarProductoSinExistenciaEnTabla();
        }
        
        /* if(labelruta.getText().equals("")){
            CargarImagen();

            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("¡CONSEJO!", "¡RECUERDA ACTUALIZAR LOS DATOS! :3",DesktopNotify.TIP, 14000L);
        }else{

            String a = String.valueOf(comboubicacion.getSelectedItem());
            if (comboubicacion.getSelectedIndex() == 1) {
                IngresoBodega();
                CargarImagen();
            } else if (comboubicacion.getSelectedIndex() == 0) {

                String Categoria = ComboCategorias.getSelectedItem().toString();
                String Proveedor = ProveedoresCombo.getSelectedItem().toString();
                if(!"".equals(Categoria)){

                    if(!"".equals(Proveedor)){
                        Ingreso();
                        CargarImagen();
                        Principal.ActualizarTablaCentral();
                    }else{
                        JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UN PROVEEDOR!");
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA CATEGORÍA!");
                }

            } else {
                JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA ÚBICACIÓN!");
            }

        }*/
    }//GEN-LAST:event_Agregarp1ActionPerformed

    private void Nuevop1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nuevop1ActionPerformed
        limpiarCajas();
    }//GEN-LAST:event_Nuevop1ActionPerformed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        if(evt.isPopupTrigger()){
            int fila = jTable1.getSelectedRow();
            if(fila >= 0){
            String codigo = jTable1.getValueAt(fila, 0).toString();
            String Barras = codigo;
            barras.setFont(new Font("Barcode", Font.BOLD, 24));
            barras.setText(codigo);
            TablaSistema.show(evt.getComponent(), evt.getX(), evt.getY());
            
            }else{
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("ERROR", "¡DEBE SELECCIONAR UN PRODUCTO!", DesktopNotify.ERROR, 3000L);
            }
        
            
        }
    }//GEN-LAST:event_jTable1MouseReleased

    private void barrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barrasActionPerformed
        if(!"".equals(barras.getText())){
            int Fila = jTable1.getSelectedRow();
            String Codigo = jTable1.getValueAt(Fila, 0).toString();
            String Nombre = jTable1.getValueAt(Fila, 1).toString();
            String Precio = jTable1.getValueAt(Fila, 5).toString();
            String Costo = jTable1.getValueAt(Fila, 4).toString();

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
                    CodigoBarrasPequeño(Codigo, Nombre, Precio, Costo);
                    break;
                case 1:
                    CodigoBarras4(Codigo, Nombre, Precio, Costo);
                    break;
                case 2:
                    CodigoBarras2(Codigo, Nombre, Precio, Costo);
                    break;
                case 3:
                    CodigoBarras3(Codigo, Nombre, Precio, Costo);
                    break;
                default:
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para realizar la etiqueta!");
        }
    }//GEN-LAST:event_barrasActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       int Seleccion = jTable1.getSelectedRow();
        //jButton7.setText("EDITAR");
        String CodigoBarras = String.valueOf(jTable1.getValueAt(Seleccion, 0));
        String Ubicacion = String.valueOf(jTable1.getValueAt(Seleccion, 6));
        Id.setText(CodigoBarras);
        Nombreproducto.setText(String.valueOf(jTable1.getValueAt(Seleccion, 1)));
        
        Cantidad.setText(String.valueOf(jTable1.getValueAt(Seleccion, 2)));
        Costo.setText(String.valueOf(jTable1.getValueAt(Seleccion, 3)));
        Letras.setText(String.valueOf(jTable1.getValueAt(Seleccion, 4)));
        Publico.setText(String.valueOf(jTable1.getValueAt(Seleccion, 5)));
        Es.setText(String.valueOf(jTable1.getValueAt(Seleccion, 6)));
        Reve.setText(String.valueOf(jTable1.getValueAt(Seleccion, 7)));
        ComboCategorias.setSelectedItem(String.valueOf(jTable1.getValueAt(Seleccion, 8)));
        ProveedoresCombo.setSelectedItem(String.valueOf(jTable1.getValueAt(Seleccion, 9)));
        comboubicacion.setSelectedItem(String.valueOf(jTable1.getValueAt(Seleccion, 10)));
        
        int Columna = jTable1.getColumnModel().getColumnIndexAtX(evt.getX());
        int Fila = evt.getY()/jTable1.getRowHeight();
        if(Fila < jTable1.getRowCount() && Fila >= 0 && Columna < jTable1.getColumnCount() && Columna >= 0){
            Object value = jTable1.getValueAt(Fila, Columna);
            if(value instanceof JButton){
                ((JButton)value).doClick();
                JButton boton= (JButton)value;
                EliminarVenta();
                //jButton7.setText("AGREGAR");
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DesktopNotify.setDefaultTheme(NotifyTheme.Light);
        
        int seleccion= JOptionPane.showOptionDialog(null, "¿ESTÁ SEGURO QUE NO HAY DATOS REPETIDOS?\n EL PROCESO ES MÁS RÁPIDO SI SELECCIONA 'SI'", //contenido de la ventana
                "MODO DE SUBIDA" , //titulo de la ventana
                JOptionPane.YES_NO_CANCEL_OPTION, //para 3 botones si/no/cancel
                JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                null,    // null para icono por defecto.
                new Object[] { "SI", "NO", "Cancelar"},//objeto para las opciones
                //null para YES, NO y CANCEL
                "NO"); //selección predeterminada
            if(seleccion==0){
                DesktopNotify.showDesktopMessage("SUBIENDO ARCHIVOS", "TEN PACIENCIA, EL PROCESO PUEDE TARDAR APROXIMADAMENTE DE 1-3 MINUTOS", DesktopNotify.INFORMATION, 14000L);
                SubirProductosBaseDatosConSeguridad();
            }else if(seleccion==1){
                DesktopNotify.showDesktopMessage("SUBIENDO ARCHIVOS", "TEN PACIENCIA, EL PROCESO PUEDE TARDAR APROXIMADAMENTE DE 1-3 MINUTOS", DesktopNotify.INFORMATION, 14000L);
                SubirProductosBaseDatosSinSeguridad();
            }
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            excel.exportarExcelProductosNoBD(jTable1);
            
//Productos pro= new Productos();
//for (int i = 0; i < jTable1.getRowCount(); i++) {

//  pro.setCodigoBarras(jTable1.getValueAt(i, 0).toString());
//  pro.setNombre(jTable1.getValueAt(i, 1).toString());
/*pro.setCantidad(Float.parseFloat(jTable1.getValueAt(i, 2).toString()));
pro.setCosto(Float.parseFloat(jTable1.getValueAt(i, 3).toString()));
pro.setCodigoLetras(jTable1.getValueAt(i, 4).toString());
pro.setPublico(Float.parseFloat(jTable1.getValueAt(i, 5).toString()));
pro.setPrecioEs(Float.parseFloat(jTable1.getValueAt(i, 6).toString()));
pro.setPrecioRe(Float.parseFloat(jTable1.getValueAt(i, 7).toString()));
pro.setIdProveedores(Integer.parseInt(jTable1.getValueAt(i, 9).toString()));
pro.setCategoria(Integer.parseInt(jTable1.getValueAt(i, 8).toString()));
pro.setUbicacion(jTable1.getValueAt(i, 10).toString());
pro.setRuta(labelruta.getText());
pro.setUsuarioIngreso(Integer.parseInt(IdUsuario.getText()));
pro.setUsuarioModifico(Integer.parseInt(IdUsuario.getText()));
pro.setDescripcion(CajaDescripcion.getText());
proDao.RegistrarProductos(pro);
LimpiarTablaVenta(jTable1);
LimpiarTablaVenta(jTable1);*/

// }
//GuardarDatosLocalMente(pro);
        } catch (IOException ex) {
            Logger.getLogger(CompraProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("EXCEL XLSX(*.XLSX;)","XLSX");
        JFileChooser archivo= new JFileChooser();
        archivo.addChoosableFileFilter(filtro);
        archivo.setFileFilter(filtro);
        archivo.setDialogTitle("SELECCIONE UN ARCHIVO EXCEL");
        int ventana = archivo.showOpenDialog(null);
        if(ventana == JFileChooser.APPROVE_OPTION){
            File file= archivo.getSelectedFile();
            
            Importar im= new Importar();
            im.CargarExcelProductosSinBD(file, jTable1);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        VaciarYllenarProveedor();
        VaciarYllenarCategoria(ComboCategorias);
        VaciarYllenarCategoria(ComboCategorias2);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void CondicionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CondicionalActionPerformed
        if (Condicional.isSelected()) {
            ListarProductosTienda(Nombreproducto);
            VistaRapidaProductos(Id);
        }
    }//GEN-LAST:event_CondicionalActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        VaciarYllenarProveedor();
        VaciarYllenarCategoria(ComboCategorias);
        VaciarYllenarCategoria(ComboCategorias2);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed

        limpiarCajas();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
         boolean Vacio= ValidarCajas();
        if(Vacio == false){
            AgregarProductoSinExistenciaEnTabla();
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        int Condicion= JOptionPane.showConfirmDialog(null, "ESTÁ SEGURO DE VACIAR LA TABLA, ESTÁ OPCIÓN NO SE PUEDE DESHACER");
        if(Condicion== 0){
            LimpiarTablaVenta(jTable1);
            LimpiarTablaVenta(jTable1);
            LimpiarTablaVenta(jTable1);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Totales();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void NombreproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproductoKeyPressed
        
    }//GEN-LAST:event_NombreproductoKeyPressed

    private void NombreproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproductoKeyReleased
       
    }//GEN-LAST:event_NombreproductoKeyReleased

    private void CostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyPressed

    }//GEN-LAST:event_CostoKeyPressed

    private void CostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyReleased
        
        NumerosALetras NAL= new NumerosALetras();
        Letras.setText(NAL.Convertir(Costo.getText()));
    }//GEN-LAST:event_CostoKeyReleased

    private void CostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Costo);
    }//GEN-LAST:event_CostoKeyTyped

    private void PublicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PublicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PublicoActionPerformed

    private void PublicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PublicoKeyReleased
        
    }//GEN-LAST:event_PublicoKeyReleased

    private void PublicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PublicoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Publico);
    }//GEN-LAST:event_PublicoKeyTyped

    private void LetrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LetrasKeyReleased
     
    }//GEN-LAST:event_LetrasKeyReleased

    private void EsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EsKeyReleased
        
    }//GEN-LAST:event_EsKeyReleased

    private void EsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EsKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Es);
    }//GEN-LAST:event_EsKeyTyped

    private void ReveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReveKeyReleased
        
    }//GEN-LAST:event_ReveKeyReleased

    private void ReveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReveKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Reve);
    }//GEN-LAST:event_ReveKeyTyped

    private void ProveedoresComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProveedoresComboMouseClicked

    }//GEN-LAST:event_ProveedoresComboMouseClicked

    private void ProveedoresComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedoresComboActionPerformed
        //AutoCompleteDecorator.decorate(ProveedoresCombo);
    }//GEN-LAST:event_ProveedoresComboActionPerformed

    private void ProveedoresComboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProveedoresComboKeyPressed

    }//GEN-LAST:event_ProveedoresComboKeyPressed

    private void IdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyPressed
        
    }//GEN-LAST:event_IdKeyPressed

    private void IdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyReleased

    }//GEN-LAST:event_IdKeyReleased

    private void IdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyTyped
        Eventos event = new Eventos();
        event.numberKeyPress(evt);
    }//GEN-LAST:event_IdKeyTyped

    private void ComboCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCategoriasActionPerformed
        VaciarYllenarSubCategoria(ComboCategorias, ComboSubCategorias, ComboSubCategorias1);
    }//GEN-LAST:event_ComboCategoriasActionPerformed

    private void ComboCategoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboCategoriasKeyPressed

    }//GEN-LAST:event_ComboCategoriasKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.Id.setText(String.valueOf(new Random().nextLong()).substring(7));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadActionPerformed

    private void CantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyReleased
       
    }//GEN-LAST:event_CantidadKeyReleased

    private void CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Cantidad);
    }//GEN-LAST:event_CantidadKeyTyped

    private void CajaDescripcionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CajaDescripcionMouseMoved
        CajaDescripcion.setToolTipText("INGRESA UNA DESCRIPCIÓN, ÚBICACIÓN O PALABRA CLAVE AL PRODUCTO");
    }//GEN-LAST:event_CajaDescripcionMouseMoved

    private void CajaDescripcionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CajaDescripcionMouseReleased
        CajaDescripcion.setToolTipText("INGRESA UNA DESCRIPCIÓN, ÚBICACIÓN O PALABRA CLAVE AL PRODUCTO");
    }//GEN-LAST:event_CajaDescripcionMouseReleased

    private void ComboSubCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboSubCategoriasItemStateChanged

    }//GEN-LAST:event_ComboSubCategoriasItemStateChanged

    private void ComboSubCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSubCategoriasActionPerformed

    }//GEN-LAST:event_ComboSubCategoriasActionPerformed

    private void ComboSubCategoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboSubCategoriasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategoriasKeyPressed

    private void ComboSubCategorias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSubCategorias1ActionPerformed
        //int ResultadoCategoria = proDao.ConsultaIdCategoria(ComboCategorias);
        //lblSubCategoria1.setText(String.valueOf(proDao.ConsultaIdSubCategoria(ResultadoCategoria, ComboSubCategorias)));
    }//GEN-LAST:event_ComboSubCategorias1ActionPerformed

    private void ComboSubCategorias1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboSubCategorias1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategorias1KeyPressed

    private void ComboCategorias2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCategorias2ActionPerformed
        VaciarYllenarSubCategoria(ComboCategorias2, ComboSubCategorias2, ComboSubCategorias3);
    }//GEN-LAST:event_ComboCategorias2ActionPerformed

    private void ComboCategorias2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboCategorias2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboCategorias2KeyPressed

    private void ComboSubCategorias2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboSubCategorias2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategorias2ItemStateChanged

    private void ComboSubCategorias2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSubCategorias2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategorias2ActionPerformed

    private void ComboSubCategorias2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboSubCategorias2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategorias2KeyPressed

    private void ComboSubCategorias3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSubCategorias3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategorias3ActionPerformed

    private void ComboSubCategorias3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboSubCategorias3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategorias3KeyPressed

    private void labelimagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelimagenMouseClicked
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos PNG, JPG Y JPEG(*.PNG;*.JPG;*.JPEG)", "PNG", "JPG", "JPEG");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("JPEG(*.JPEG)", "JPEG");
        FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("JPG(*.JPG;)", "JPG");
        FileNameExtensionFilter filtro4 = new FileNameExtensionFilter("PNG(*.PNG;)", "PNG");
        JFileChooser archivo = new JFileChooser();
        archivo.setCurrentDirectory(new File(CargarDatosRutasAlBuscarImagen()));
        archivo.addChoosableFileFilter(filtro);
        archivo.addChoosableFileFilter(filtro2);
        archivo.addChoosableFileFilter(filtro3);
        archivo.addChoosableFileFilter(filtro4);
        archivo.setFileFilter(filtro);
        archivo.setDialogTitle("SELECCIONE UNA IMAGEN");
        int ventana = archivo.showOpenDialog(null);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            try {
                File file = archivo.getSelectedFile();
                String DestinoPath = CargarDatosRutas(1) + "\\"+ file.getName();
                Path Destino = Paths.get(DestinoPath);

                String OrigenPath = file.getPath();
                Path Origen = Paths.get(OrigenPath);

                Files.copy(Origen, Destino, StandardCopyOption.REPLACE_EXISTING);
                labelruta.setText(file.getName());

                PintarImagen(labelimagen, CargarDatosRutas(1) + "\\"+ file.getName());
                } catch (IOException ex) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERROR", "NO SE PUDO MOVER LA IMAGEN" + ex , DesktopNotify.ERROR, 14000L);

                }
            }
    }//GEN-LAST:event_labelimagenMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        CargarImagen();
    }//GEN-LAST:event_jButton8ActionPerformed

    public void CodigoBarras2(String Id, String Nombreproducto, String Publico, String Letras){
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id);
         ParametrosCodigosBarras.setNombreproducto(Nombreproducto);
         ParametrosCodigosBarras.setPrecioPublico(Publico);
         ParametrosCodigosBarras.setCostoLetras(Letras);
        CodigosBarras.CodigoBarrasMediano(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }
    
     public void CodigoBarras3(String Id, String Nombreproducto, String Publico, String Letras){
         ParametrosCodigosBarras.setCodigoDeBarrasOId(Id);
         ParametrosCodigosBarras.setNombreproducto(Nombreproducto);
         ParametrosCodigosBarras.setPrecioPublico(Publico);
         ParametrosCodigosBarras.setCostoLetras(Letras);
        CodigosBarras.CodigoBarrasGrande(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA, PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
    }
     
     public void CodigoBarras4(String Id, String Nombreproducto, String Publico, String Letras){
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id);
         ParametrosCodigosBarras.setNombreproducto(Nombreproducto);
         ParametrosCodigosBarras.setPrecioPublico(Publico);
         ParametrosCodigosBarras.setCostoLetras(Letras);
        CodigosBarras.CodigoBarras3x1(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }
     
     public void CodigoBarrasPequeño(String Id, String Nombreproducto, String Publico, String Letras){
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id);
         ParametrosCodigosBarras.setNombreproducto(Nombreproducto);
         ParametrosCodigosBarras.setPrecioPublico(Publico);
         ParametrosCodigosBarras.setCostoLetras(Letras);
        CodigosBarras.CodigoBarrasPequeño(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }
     
     
     public void VistaRapidaProductos(JTextField Parametro){
        Parametro.setFocusTraversalKeysEnabled(false);
        AutoCompletador = new TextAutoCompleter(Parametro);
        tablasVentas = new ActualizarTablaVentas();
        
         
        List<Productos> ListarPr = tablasVentas.ListarProductosTienda();
        Object[] ob = new Object[1];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getCodigoBarras();
            /*ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getCosto();
            ob[4] = ListarPr.get(i).getCodigoLetras();
            ob[5] = ListarPr.get(i).getPublico();
            ob[6] = ListarPr.get(i).getPrecioEs();
            ob[7] = ListarPr.get(i).getPrecioRe();
            ob[8] = ListarPr.get(i).getProveedores();
            ob[9] = ListarPr.get(i).getCategoria();*/
            
            AutoCompletador.addItems(ob);
            
        }
        Parametro.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "commit");
    }
    
    
    public void ListarProductosTienda(JTextField Parametro){
        AutoCompletador = new TextAutoCompleter(Parametro);
        tablasVentas = new ActualizarTablaVentas();
        AutoCompletador.setMode(0); // infijo
         
        List<Productos> ListarPr = tablasVentas.ListarProductosTienda();
        Object[] ob = new Object[1];
        for (int i = 0; i < ListarPr.size(); i++) {
            ob[0] = ListarPr.get(i).getNombre();
            /*ob[2] = ListarPr.get(i).getCantidad();
            ob[3] = ListarPr.get(i).getCosto();
            ob[4] = ListarPr.get(i).getCodigoLetras();
            ob[5] = ListarPr.get(i).getPublico();
            ob[6] = ListarPr.get(i).getPrecioEs();
            ob[7] = ListarPr.get(i).getPrecioRe();
            ob[8] = ListarPr.get(i).getProveedores();
            ob[9] = ListarPr.get(i).getCategoria();*/
            AutoCompletador.addItems(ob);
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Agregarp1;
    private static javax.swing.JTextArea CajaDescripcion;
    private static javax.swing.JTextField Cantidad;
    private static javax.swing.JComboBox<String> ComboCategorias;
    private static javax.swing.JComboBox<String> ComboCategorias2;
    private static javax.swing.JComboBox<String> ComboSubCategorias;
    private static javax.swing.JComboBox<String> ComboSubCategorias1;
    private static javax.swing.JComboBox<String> ComboSubCategorias2;
    private static javax.swing.JComboBox<String> ComboSubCategorias3;
    private javax.swing.JCheckBoxMenuItem Condicional;
    private static javax.swing.JTextField Costo;
    private static javax.swing.JTextField Es;
    private static javax.swing.JTextField Id;
    public static javax.swing.JLabel IdUsuario;
    private static javax.swing.JTextField Letras;
    private static javax.swing.JComboBox<String> NombrePrecio1;
    private static javax.swing.JComboBox<String> NombrePrecio2;
    private static javax.swing.JComboBox<String> NombrePrecio3;
    public static javax.swing.JLabel NombreUsuarioVista;
    private static javax.swing.JTextField Nombreproducto;
    private javax.swing.JButton Nuevop1;
    private static javax.swing.JComboBox<String> ProveedoresCombo;
    private static javax.swing.JTextField Publico;
    private static javax.swing.JTextField Reve;
    private javax.swing.JPopupMenu TablaSistema;
    private javax.swing.JLabel TotalCostos;
    private javax.swing.JLabel TotalStock;
    private javax.swing.JLabel TotalTiposProductos;
    private javax.swing.JMenuItem barras;
    private static javax.swing.JComboBox<String> comboubicacion;
    private static javax.swing.JComboBox<String> comboubicacion2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private static javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private static javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    public static javax.swing.JTable jTable1;
    private static javax.swing.JLabel labelimagen;
    private static javax.swing.JLabel labelruta;
    // End of variables declaration//GEN-END:variables
    
    public void EliminarVenta() {
        DefaultTableModel modelo = new DefaultTableModel();
        int fila1 = jTable1.getSelectedRow();
        String fila = String.valueOf(fila1);

        String codigo = jTable1.getValueAt(fila1, 0).toString();
        if (fila != null) {
            modelo = (DefaultTableModel) jTable1.getModel();
            modelo.removeRow(jTable1.getSelectedRow());
        } else {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR UN PRODUCTO");
        }
    }
    String CodigodeBarrasExiste, NombreProducto;
    public void AgregarProductoSinExistenciaEnTabla() {
        //EliminarVenta();
        float EnStock;

        jTable1.setDefaultRenderer(Object.class, new RenderTablas());

        JButton btn1 = new JButton("ELIMINAR");

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/eliminar.png"));
        ImageIcon ro = new ImageIcon(retValue);

        btn1.setIcon(ro);
        DefaultTableModel modelo = new DefaultTableModel();

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (jTable1.getValueAt(i, 0).equals(Id.getText())) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL CÓDIGO DE BARRAS YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);
                return;
            }
            
            if (jTable1.getValueAt(i, 1).equals(Nombreproducto.getText())) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL NOMBRE DEL PRODUCTO YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);
                return;
            }

        }

        item = item + 1;
        modelo = (DefaultTableModel) jTable1.getModel();
        int cat = ComboCategorias.getSelectedIndex();
        int g = ProveedoresCombo.getSelectedIndex();
        int Resultado = proDao.ConsultaIdProveedor(ProveedoresCombo);
        int ResultadoCategoria = proDao.ConsultaIdCategoria(ComboCategorias);
        ArrayList lista = new ArrayList();
        lista.add(item);
        lista.add(Id.getText());
        lista.add(Nombreproducto.getText());
        lista.add(String.format("%.2f",Float.parseFloat(Cantidad.getText())));
        lista.add(String.format("%.2f",Float.parseFloat(Costo.getText())));
        lista.add(Letras.getText());
        lista.add(String.format("%.2f",Float.parseFloat(Publico.getText())));
        lista.add(String.format("%.2f",Float.parseFloat(Es.getText())));
        lista.add(String.format("%.2f",Float.parseFloat(Reve.getText())));
        lista.add(ResultadoCategoria);
        lista.add(Resultado);
        lista.add(comboubicacion.getSelectedItem().toString());
        lista.add(CajaDescripcion.getText());
        lista.add(NombrePrecio1.getSelectedItem().toString());
        lista.add(NombrePrecio2.getSelectedItem().toString());
        lista.add(NombrePrecio3.getSelectedItem().toString());
        lista.add(btn1);
        //lista.add(ubicacion2);

        Object[] O = new Object[16];
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
        O[10] = lista.get(11);
        O[11] = lista.get(12);
        O[12] = lista.get(13);
        O[13] = lista.get(14);
        O[14] = lista.get(15);
        O[15] = lista.get(16);
        modelo.addRow(O);
        jTable1.setModel(modelo);
        CargarImagen();
        limpiarCajas();
        Totales();
    }
    
    
    
    public void SubirProductosBaseDatosConSeguridad(){
        
        Productos pro= new Productos();
        for (int j = 0; j < jTable1.getRowCount(); j++) {
            
            String BarrasExistente= proDao.RetornarProductosDuplicados(1, jTable1.getValueAt(j, 0).toString());
            String NombreExistente = proDao.RetornarProductosDuplicados(2, jTable1.getValueAt(j, 1).toString());
            
            if(!"".equals(BarrasExistente)){
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL CÓDIGO DE BARRAS: "+ BarrasExistente+" YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 20000L);
            }else if(!"".equals(NombreExistente)){
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                      DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL NOMBRE: "+NombreExistente+" YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 20000L);
            
            }else{
                
                for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                
            pro.setCodigoBarras(jTable1.getValueAt(i, 0).toString());
            pro.setNombre(jTable1.getValueAt(i, 1).toString());
            pro.setCantidad(Float.parseFloat(jTable1.getValueAt(i, 2).toString()));
            pro.setCosto(Float.parseFloat(jTable1.getValueAt(i, 3).toString()));
            pro.setCodigoLetras(jTable1.getValueAt(i, 4).toString());
            pro.setPublico(Float.parseFloat(jTable1.getValueAt(i, 5).toString()));
            pro.setPrecioEs(Float.parseFloat(jTable1.getValueAt(i, 6).toString()));
            pro.setPrecioRe(Float.parseFloat(jTable1.getValueAt(i, 7).toString()));
            pro.setIdProveedores(Integer.parseInt(jTable1.getValueAt(i, 9).toString()));
            pro.setCategoria(Integer.parseInt(jTable1.getValueAt(i, 8).toString()));
            pro.setRuta(labelruta.getText());
            pro.setUsuarioIngreso(Integer.parseInt(IdUsuario.getText()));
            pro.setUsuarioModifico(Integer.parseInt(IdUsuario.getText()));
            pro.setDescripcion(jTable1.getValueAt(i, 11).toString());
            pro.setNombreTiposDePrecio1(jTable1.getValueAt(i, 12).toString());
            pro.setNombreTiposDePrecio2(jTable1.getValueAt(i, 13).toString());
            pro.setNombreTiposDePrecio3(jTable1.getValueAt(i, 14).toString());
            proDao.RegistrarProductos(pro);
                }
            LimpiarTablaVenta(jTable1);
            LimpiarTablaVenta(jTable1);
            }
        }
        
        
    }
    
    public void SubirProductosBaseDatosSinSeguridad(){
        
        Productos pro= new Productos();
        
                
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                    
                
            pro.setCodigoBarras(jTable1.getValueAt(i, 0).toString());
            pro.setNombre(jTable1.getValueAt(i, 1).toString());
            pro.setCantidad(Float.parseFloat(jTable1.getValueAt(i, 2).toString()));
            pro.setCosto(Float.parseFloat(jTable1.getValueAt(i, 3).toString()));
            pro.setCodigoLetras(jTable1.getValueAt(i, 4).toString());
            pro.setPublico(Float.parseFloat(jTable1.getValueAt(i, 5).toString()));
            pro.setPrecioEs(Float.parseFloat(jTable1.getValueAt(i, 6).toString()));
            pro.setPrecioRe(Float.parseFloat(jTable1.getValueAt(i, 7).toString()));
            pro.setIdProveedores(Integer.parseInt(jTable1.getValueAt(i, 9).toString()));
            pro.setCategoria(Integer.parseInt(jTable1.getValueAt(i, 8).toString()));
            pro.setRuta(labelruta.getText());
            pro.setUsuarioIngreso(Integer.parseInt(IdUsuario.getText()));
            pro.setUsuarioModifico(Integer.parseInt(IdUsuario.getText()));
            pro.setDescripcion(jTable1.getValueAt(i, 11).toString());
            pro.setNombreTiposDePrecio1(jTable1.getValueAt(i, 12).toString());
            pro.setNombreTiposDePrecio2(jTable1.getValueAt(i, 13).toString());
            pro.setNombreTiposDePrecio3(jTable1.getValueAt(i, 14).toString());
            proDao.RegistrarProductos(pro);
                
            }
                
            LimpiarTablaVenta(jTable1);
            LimpiarTablaVenta(jTable1);
            
    }
    
     public static void LimpiarTablaVenta(JTable Tabla){
        DefaultTableModel modelo = new DefaultTableModel();
        for (int e = 0; e < 15; e++) {
            
        
        for (int i = 0; i < Tabla.getRowCount(); i++) {
            modelo = (DefaultTableModel) Tabla.getModel();
            modelo.removeRow(i);
        }
        }
    }
     
     Vector DatosTabla= new Vector();
     
     public void GuardarDatosTabla(Productos pro){
         DatosTabla.addElement(pro.getCodigoBarras());
         DatosTabla.addElement("|"+pro.getNombre());
     }
     
     
     
     public void GuardarDatosLocalMente(Productos pro){
         File Archivo= new File("C:\\Sistema Punto de Venta YG\\compras.txt");
         try {
             FileWriter fw = new FileWriter(Archivo, true);
             BufferedWriter bw= new BufferedWriter(fw);
             PrintWriter pw= new PrintWriter(bw);
             pw.print(pro.getCodigoBarras());
             pw.print(pro.getNombre());
             pw.close();
         } catch (Exception e) {
         }
     }
     
     
     public void MostrarDatos(){
         File Archivo= new File("C:\\Sistema Punto de Venta YG\\compras.txt");

         DefaultTableModel modelo= new DefaultTableModel();
         try {
             FileReader fr= new FileReader(Archivo);
             BufferedReader br= new BufferedReader(fr);
             String d;
             while((d= br.readLine())!= null){
                 StringTokenizer dato= new StringTokenizer( d, "|");
                 Vector x = new Vector();
                 while(dato.hasMoreTokens()){
                     x.addElement(dato.nextToken());
                 }
                 modelo.addRow(x);
             }
             
             jTable1.setModel(modelo);
         } catch (Exception e) {
         }
     }
    
     
     public void Totales(){
        int nu= jTable1.getRowCount();
        
        TotalTiposProductos.setText(""+nu);
               
        Double TotalStock23 = 0.00;
        Double TotalCosto = 0.00;
        Double TotalCosto2 = 0.00;
        Double TotalPublicosAmigo = 0.00;
        for (int i = 0; i < nu; i++) {
            double cal = Double.parseDouble(String.valueOf(jTable1.getModel().getValueAt(i, 2)));
            TotalStock23 = TotalStock23 + cal;

        }
        TotalStock.setText(String.format("%.2f", TotalStock23));
        
        
        
        for (int i = 0; i < nu; i++) {
            double cal = Double.parseDouble(String.valueOf(jTable1.getModel().getValueAt(i, 3)));
            double todo = Double.parseDouble(String.valueOf(jTable1.getModel().getValueAt(i, 2)));
            TotalCosto = TotalCosto + cal*todo;

        }
        TotalCostos.setText(String.format("%.2f", TotalCosto));
        
        
      /*  for (int i = 0; i < nu; i++) {
            double cal = Double.parseDouble(String.valueOf(jTable1.getModel().getValueAt(i, 5)));
            double todo = Double.parseDouble(String.valueOf(jTable1.getModel().getValueAt(i, 2)));
            TotalPublicosAmigo = TotalPublicosAmigo + cal*todo;

        }
        TotalPublicos.setText(String.format("%.2f", TotalPublicosAmigo));
        Double TotalGanancias = TotalPublicosAmigo - TotalCosto;
        GananciaNumeros.setText(String.format("%.2f", TotalGanancias));
        GananciaPorcentaje.setText(PorcentajesTotal(Double.parseDouble(TotalCostoAmigo1.getText()), Double.parseDouble(TotalPublicos.getText())));*/
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
    
    public String ObtenerRutaImagen(int Seleccion){
        String Ruta ="";
        DatosEmpresaGeneral DE= new DatosEmpresaGeneral();
        loginDao login = new loginDao();
        DE = login.VerDatosEmpresaEnLogin();    
        if(Seleccion==0){
        Ruta = CargarDatosRutas(0)+"\\"+DE.getRutaimagenlogo();
        }else if(Seleccion==2){
        Ruta = DE.getRutaimagenlogo();
        }
        else{
        Ruta = CargarDatosRutas(0)+"\\"+DE.getRutaimagensistema();
        }
        return Ruta;
    }
    
    public final String CargarDatosRutasAlBuscarImagen(){
        String RutaDeBusquedas="";
        Properties propertie3= new Properties();
        InputStream entrada = null;
            try {
            entrada = new FileInputStream(new File ("/Sistema Punto de Venta YG/CONFIGURACIONES/RUTASERVIDORIMAGENES.properties").getAbsolutePath());
            propertie3.load(entrada);
            RutaDeBusquedas = propertie3.getProperty("rutabusqueda");
        } catch (FileNotFoundException e) {
        }catch(IOException e){
        }  
            return RutaDeBusquedas;
    }
    
    
    public int ConsultarIdSubCategoria(JComboBox ComboSubCategoria, JComboBox ComboCategoria){
        int ResultadoSubCategoria = 0;
      ResultadoSubCategoria = Integer.parseInt(String.valueOf(proDao.ConsultaIdSubCategoria(ConsultarIdCategoria(ComboCategoria), ComboSubCategoria)));
      return ResultadoSubCategoria;
    }
}
