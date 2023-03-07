
package Vista.ADMINISTRACION.INVENTARIO;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.OBTENER_EXTENSION_ARCHIVO;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import CodigosDeBarras.CodigosDeBarras;
import CodigosDeBarras.ParametrosCodigosDeBarras;
import Controlador.Eventos;
import Controlador.KardexDao;
import Controlador.NumerosALetras;
import Controlador.ProductosDao;
import Controlador.ProveedoresDao;
import Controlador.loginDao;
import Modelo.Categoria;
import Modelo.Combo;
import Modelo.ComboCategoria;
import Modelo.DatosEmpresaGeneral;
import Modelo.Kardex;
import Modelo.Productos;
import Modelo.Proveedor;
import Modelo.SubCategoria;
import Modelo.Ubicacion;
import Tablas.ActualizarTablaVentas;
import Vista.Principal;
import com.groupdocs.conversion.filetypes.ImageFileType;
import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class ADMINISTRARPRODUCTO extends javax.swing.JFrame {
    Principal principal;
    INVENTARIO inventario;
    PARAMETROS_EMPRESA P_E = new PARAMETROS_EMPRESA();
    public String ESTADO_PRODUCTO = null;
    private Float STOCK_INGRESADO;
    String DestinoPath;
    public String RutaDeImagen = "";
    private TextAutoCompleter AutoCompletador;
    private static ImageIcon imagenI;
    private static Icon icono;
    Productos pro = new Productos();
    ProductosDao proDao = new ProductosDao();
    ProveedoresDao proveDao = new ProveedoresDao();
    CodigosDeBarras CodigosBarras = new CodigosDeBarras();
    ParametrosCodigosDeBarras ParametrosCodigosBarras = new ParametrosCodigosDeBarras();
    ActualizarTablaVentas tablasVentas;
    Image retValue = Toolkit.getDefaultToolkit().
            getImage(ObtenerRutaImagen(0));
    ImageIcon bl = new ImageIcon(retValue);

    public boolean VentanaBusquedaProducto = false;
    public ADMINISTRARPRODUCTO() {

    }

    @Override
    public Image getIconImage() {
        /*Image retValue = Toolkit.getDefaultToolkit().
                    getImage(ClassLoader.getSystemResource("Imagenes/FerreteríaIcono.png"));*/

        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("IconosSOciales/business_inventory_maintenance_product_box_boxes_2326.png"));

        return retValue;
    }

    public ADMINISTRARPRODUCTO(String IDUSUARIO, String NOMBREUSUARIO, Principal principal, INVENTARIO inventario) {
        initComponents();
        this.principal = principal;
        this.inventario = inventario;
        DRAG_AND_DROP_IMAGEN();
        VaciarYllenarCategoria(ComboCategorias);
        VaciarYllenarProveedor();
        VaciarYllenarUbicacion();
        
        AutoCompletador = new TextAutoCompleter(Nombreproducto, new AutoCompleterCallback() {
            @Override
            public void callback(Object selectedItem) {
            }
        });
        
        ListarProductosTienda(Nombreproducto);
        IdUsuario.setText(IDUSUARIO);
        NombreUsuarioVista.setText(NOMBREUSUARIO);
        limpiarCajas();
        this.setLocationRelativeTo(null);
        Cerrar();
    }

    public void Cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    ConfirmarSalida();
                }
            });
            this.setVisible(true);

        } catch (Exception e) {
        }
    }
    
    public void ConfirmarSalida() {
        
        if (Id.getText().equals("") && Nombreproducto.getText().equals("") && Cantidad.getText().equals("") && Costo.getText().equals("") && Publico.getText().equals("")) {
            
            principal.VentanaAdministracionDeProductos=false;
            this.dispose();
            
        } else {
            if (EstadoProducto.getText().equals("NO INGRESADO")) {
                int seleccion = JOptionPane.showConfirmDialog(null, "TIENE UN ARTÍCULO SIN INGRESAR, ¿ESTÁ SEGURO DE SALIR?", "ADMINISTRACIÓN DE PRODUCTOS", JOptionPane.YES_NO_OPTION);
                if (seleccion == 0) {
                    principal.VentanaAdministracionDeProductos=false;
                    this.dispose();
                }

            } else if(EstadoProducto.getText().equals("INGRESADO")){
                boolean Estado2 = EstadoProducto2.getText().equals("SI");
                if(Estado2 == true){
                int seleccion = JOptionPane.showConfirmDialog(null, "TIENE UN ARTÍCULO EDITANDO, ¿ESTÁ SEGURO DE SALIR?", "ADMINISTRACIÓN DE PRODUCTOS", JOptionPane.YES_NO_OPTION);
                if (seleccion == 0) {
                    principal.VentanaAdministracionDeProductos=false;
                    this.dispose();
                }    
                }else{
                    principal.VentanaAdministracionDeProductos=false;
                    this.dispose();
                }
                
            }else {
                principal.VentanaAdministracionDeProductos=false;
                this.dispose();
            }
        }
    }
    
    public void DRAG_AND_DROP_IMAGEN(){
        class BuscarProducto_Hilo extends Thread {

            @Override
            public void run() {
                new rsdragdropfiles.RSDragDropFiles(jPanel3, new rsdragdropfiles.RSDragDropFiles.Listener() {
                    @Override
                    public void filesDropped(File[] files) {
                        if (files.length > 1) {
                            javax.swing.JOptionPane.showMessageDialog(null, "¡NO PUEDE INSERTAR MÁS DE DOS IMÁGENES A LA VEZ!");
                        } else {
                            try {
                                if (METODOS_GLOBALES.OBTENER_EXTENSION_ARCHIVO(files[0].getCanonicalPath()).equals("webp")) {
                                    ImageIcon imagenIa = new ImageIcon(ClassLoader.getSystemResource("IconosSOciales/CARGANDO.gif"));
                                    METODOS_GLOBALES.PintarImagen2(labelimagen, imagenIa);
                                    class CARGAR_IMAGEN_CONVERTIR extends Thread {

                                        @Override
                                        public void run() {
                                            String NombreFinal_Webp = String.valueOf(new Random().nextLong()).substring(7) + ".png";
                                            String RutaFinal_Webp = CargarDatosRutas(1) + "\\" + NombreFinal_Webp;
                                            try {
                                                METODOS_GLOBALES.CONVERSION_WEBP_IMAGE(files[0].getCanonicalPath(), RutaFinal_Webp, ImageFileType.Png);
                                            } catch (IOException ex) {
                                                Logger.getLogger(ADMINISTRARPRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            labelruta.removeAll();
                                            labelruta.setText(NombreFinal_Webp);
                                            METODOS_GLOBALES.PintarImagen(labelimagen, RutaFinal_Webp);
                                        }
                                    }

                                    CARGAR_IMAGEN_CONVERTIR Hilo_CARGAR_IMAGEN_CONVERTIR = new CARGAR_IMAGEN_CONVERTIR();
                                    Hilo_CARGAR_IMAGEN_CONVERTIR.start();

                                } else {
                                    labelruta.removeAll();
                                    String NombreFinal = String.valueOf(new Random().nextLong()).substring(7) + "-" + files[0].getName();
                                String RutaFinal = CargarDatosRutas(1) + "\\" + NombreFinal;
                                rsdragdropfiles.RSDragDropFiles.setCopiar(files[0].getCanonicalPath(), RutaFinal);
                                METODOS_GLOBALES.PintarImagen(labelimagen, RutaFinal);
                                labelruta.setText(NombreFinal);    
                                }

                                
                            } catch (IOException ex) {
                                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
              DesktopNotify.showDesktopMessage("ERROR AL CARGAR IMAGEN EN MODO DRAG AND DROP \n", ""+ex, DesktopNotify.ERROR, 14000L);  
                            }
                        }
                    }
                });
            }
        }

        BuscarProducto_Hilo Hilo = new BuscarProducto_Hilo();
        Hilo.start();
    }
    
    public void ActualizarEstado(){
        if (EstadoProducto.getText().equals("INGRESADO")) {
                EstadoProducto2.setText("SI");
            }
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
    
    private void VaciarYllenarSubCategoria(JComboBox Categoria, JComboBox SubCategoria1) {
        proDao = new ProductosDao();
        SubCategoria1.removeAllItems();
        List<SubCategoria> lista = proDao.ListarSubCategoria(proDao.ConsultaIdCategoria(Categoria));
        for (int i = 0; i < lista.size(); i++) {
            SubCategoria1.addItem(lista.get(i).getNombreSubcategoria());
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

    public void PintarImagen(JLabel lbl, String ruta) {
        ADMINISTRARPRODUCTO.imagenI = new ImageIcon(ruta);
        ADMINISTRARPRODUCTO.icono = new ImageIcon(ADMINISTRARPRODUCTO.imagenI.getImage().getScaledInstance(
                lbl.getWidth(),
                lbl.getHeight(),
                Image.SCALE_DEFAULT));
        lbl.setIcon(ADMINISTRARPRODUCTO.icono);
        lbl.repaint();
    }

    public void PintarImagen2(JLabel lbl, ImageIcon ruta) {
        ADMINISTRARPRODUCTO.imagenI = new ImageIcon();
        ADMINISTRARPRODUCTO.icono = new ImageIcon(ruta.getImage().getScaledInstance(
                lbl.getWidth(),
                lbl.getHeight(),
                Image.SCALE_DEFAULT));
        lbl.setIcon(ADMINISTRARPRODUCTO.icono);
        this.repaint();
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
        EstadoProducto.setText("NO INGRESADO");
        EstadoProducto2.setText("NO");
        Id.requestFocus();
        CargarImagen();
        ValidarBotones();
    }
    
    public void ValidarBotones(){
        if(EstadoProducto.getText().equals("INGRESADO")){
            Cantidad.setEditable(false);
         Agregarp.setVisible(false);
        Eliminarp.setVisible(true);
        Editarp.setVisible(true);   
        jButton1.setEnabled(false);
        EditarItem.setEnabled(true);
        AgregarItem.setEnabled(false);
        jButton6.setVisible(true);  
        }else if(EstadoProducto.getText().equals("NO INGRESADO")){
            Cantidad.setEditable(true);
        Agregarp.setVisible(true);
        Eliminarp.setVisible(false);
        Editarp.setVisible(false);   
        jButton1.setEnabled(true);
        EditarItem.setEnabled(false);
        AgregarItem.setEnabled(true);   
        jButton6.setVisible(false);  
        }
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
        }else if (comboubicacion2.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA UBICACION[1]!");
        }else if (comboubicacion.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA UBICACION[2]!");
        } else{
            EstadoCajas = true;
        }

        return EstadoCajas;
    }
    
    public void InsertarDatos(String codigo) {
            proDao= new ProductosDao();
            Productos proo = new Productos();
            proo.setCodigoBarras(codigo);
            proDao.ActualizarTabla(proo);
            
            if(proo.getNombre() != null){
            idbodega.setText(String.valueOf(proo.getIdProductos()));
            Id.setText(proo.getCodigoBarras());
            Nombreproducto.setText(proo.getNombre());
            Cantidad.setText(String.valueOf(proo.getCantidad()));
            Costo.setText(String.valueOf(proo.getCosto()));
            Publico.setText(String.valueOf(proo.getPublico()));
            String Proveedor = new Combo(proo.getIdProveedores(), proo.getProveedorNombre()).toString();
            ProveedoresCombo.setSelectedItem(Proveedor);
            Letras.setText(proo.getCodigoLetras());
            Es.setText(String.valueOf(proo.getPrecioEs()));
            Reve.setText(String.valueOf(proo.getPrecioRe()));
            CajaDescripcion.setText(proo.getDescripcion());
            EstadoProducto.setText("INGRESADO");
            ComboCategorias.setSelectedItem(new ComboCategoria(proo.getCategoria(), proo.getCategoriaNombre()).toString());
            
            ComboSubCategorias.setSelectedItem(proo.getSubcategoriaNombre());
            comboubicacion.setSelectedItem(proo.getUbicacionNombre1());
            comboubicacion2.setSelectedItem(proo.getUbicacionNombre2());
                    
            NombrePrecio1.setSelectedItem("" + proo.getNombreTiposDePrecio1());
                NombrePrecio2.setSelectedItem("" + proo.getNombreTiposDePrecio2());
                NombrePrecio3.setSelectedItem("" + proo.getNombreTiposDePrecio3());
                Combo_ESTADO_Productos.setSelectedItem(proo.getEstado_Producto());
                jCheckBox1.setSelected(proo.getAPLICAR_DESCUENTO());
                ValidarBotones();

            if (proo.getRuta() != null || !"".equals(proo.getRuta())) {
                
                labelruta.setText(proo.getRuta()); 
                PintarImagen(labelimagen, CargarDatosRutas(1) + "\\"+ proo.getRuta());
            } else {
                PintarImagen2(labelimagen, bl);
                labelruta.setText(ObtenerRutaImagen(2));
            }
            }else if (proo.getNombre() == null){
              DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("VERIFIQUE EL CÓDIGO", "EL PRODUCTO " + codigo + " NO EXISTE!", DesktopNotify.ERROR, 14000L);  
            }

        
    }
    
    public void InsertarProductosPorNombre(String Valor){
        Productos pro = new Productos();
        ProductosDao proDao = new ProductosDao();
                pro = proDao.BuscarProNombre(Valor);

                if (pro.getNombre() != null) {
                    Id.setText("" + pro.getCodigoBarras());
                    idbodega.setText("" + pro.getIdProductos());
                    Nombreproducto.setText("" + pro.getNombre());
                    Costo.setText("" + pro.getCosto());
                    Publico.setText("" + pro.getPublico());
                    Es.setText("" + pro.getPrecioEs());
                    Reve.setText("" + pro.getPrecioRe());
                    Cantidad.setText("" + pro.getCantidad());
                    STOCK_INGRESADO = pro.getCantidad(); 
                    Letras.setText("" + pro.getCodigoLetras());
                    ComboCategorias.setSelectedItem("" + pro.getCategoriaNombre());
                    ProveedoresCombo.setSelectedItem("" + pro.getProveedorNombre());
                    CajaDescripcion.setText("" + pro.getDescripcion());
                    labelruta.setText(pro.getRuta());
                    EstadoProducto.setText("INGRESADO");
                    EstadoProducto2.setText("NO");
                    PintarImagen(labelimagen, CargarDatosRutas(1) + "\\" + pro.getRuta());
                    NombrePrecio1.setSelectedItem("" + pro.getNombreTiposDePrecio1());
                    NombrePrecio2.setSelectedItem("" + pro.getNombreTiposDePrecio2());
                    NombrePrecio3.setSelectedItem("" + pro.getNombreTiposDePrecio3());

                    ComboSubCategorias.setSelectedItem(pro.getSubcategoriaNombre());
                    comboubicacion.setSelectedItem(pro.getUbicacionNombre1());
                    comboubicacion2.setSelectedItem(pro.getUbicacionNombre2());
                    Combo_ESTADO_Productos.setSelectedItem(pro.getEstado_Producto());
                    jCheckBox1.setSelected(pro.getAPLICAR_DESCUENTO());

                    ValidarBotones();
                
                } else if (pro.getNombre() == null) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("VERIFIQUE EL NOMBRE", "EL PRODUCTO " + Valor + " NO EXISTE!", DesktopNotify.ERROR, 14000L);
                }
    }
    
    public void Ingreso_Kardex(String MENSAJE) {
        KardexDao kdDao= new KardexDao();
        Kardex Kd ;
        if (ValidarCajas() == true) {
            InsertarDatos(Id.getText());
                Kd = new Kardex();
                Kd.setID_Codigo_Producto_Kardex(Integer.parseInt(idbodega.getText()));
                Kd.setTitulo_Kardex(MENSAJE);
                  Kd.setEntrada_Kardex(Cantidad.getText());  
                  Kd.setSalida_Kardex("0");
                Kd.setAntes_Kardex("0");
                Kd.setDespues_Kardex(Cantidad.getText());
                Kd.setFecha_Modificacion_Kardex(METODOS_GLOBALES.Fecha()+" "+METODOS_GLOBALES.Hora());
                Kd.setUsuario_Modifico_Kardex(PARAMETROS_USUARIOS.ID_USUARIO);
                Kd.setModulo_Kardex("INVENTARIO");
                Boolean ResultadoIngreso = kdDao.RegistrarKARDEX(Kd);
                if (ResultadoIngreso == true) {
                    limpiarCajas();
            }

        }
    }

    public void Ingreso() {
        if (ValidarCajas() == true) {
            pro = new Productos();
            
            String BarrasExistente = proDao.RetornarProductosDuplicados(1, Id.getText());
            String NombreExistente = proDao.RetornarProductosDuplicados(2, Nombreproducto.getText());
            if (!"".equals(BarrasExistente)) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL CÓDIGO DE BARRAS YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);
            } else if (!"".equals(NombreExistente)) {
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL NOMBRE YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);

            } else {
                
                String Letrass = Letras.getText();
                pro = new Productos();
                pro.setCodigoBarras(Id.getText());
                pro.setNombre(Nombreproducto.getText());
                pro.setCantidad(Float.parseFloat(Cantidad.getText()));
                pro.setCosto(Float.parseFloat(Costo.getText()));
                pro.setCodigoLetras(Letrass.toUpperCase());
                pro.setPublico(Float.parseFloat(Publico.getText()));
                pro.setPrecioEs(Float.parseFloat(Es.getText()));
                pro.setPrecioRe(Float.parseFloat(Reve.getText()));
                pro.setRuta(labelruta.getText());
                pro.setUsuarioIngreso(Integer.parseInt(IdUsuario.getText()));
                pro.setUsuarioModifico(Integer.parseInt(IdUsuario.getText()));
                pro.setDescripcion(CajaDescripcion.getText());
                pro.setNombreTiposDePrecio1(NombrePrecio1.getSelectedItem().toString());
                pro.setNombreTiposDePrecio2(NombrePrecio2.getSelectedItem().toString());
                pro.setNombreTiposDePrecio3(NombrePrecio3.getSelectedItem().toString());
                pro.setCategoria(ConsultarIdCategoria(ComboCategorias));
                pro.setIdProveedores(ConsultarIdProveedor(ProveedoresCombo));
                pro.setSubcategoria(ConsultarIdSubCategoria(ComboSubCategorias, ComboCategorias));
                pro.setUbicacion1(ConsultarIdUbicacion(comboubicacion));
                pro.setUbicacion2(ConsultarIdUbicacion(comboubicacion2));
                pro.setEstado_Producto(String.valueOf(Combo_ESTADO_Productos.getSelectedItem()));
                pro.setAPLICAR_DESCUENTO(jCheckBox1.isSelected());
                Boolean ResultadoIngreso = proDao.RegistrarProductos(pro);
                if (ResultadoIngreso == true) {
                    Ingreso_Kardex("SE INGRESÓ | PRODUCTO NUEVO");
                    ListarProductosTienda(Nombreproducto);
                    limpiarCajas();
                    inventario.REFRESCAR_INVENTARIO();
                }

            }
        }
    }

    public void EliminarProducto() {
        int i = JOptionPane.showConfirmDialog(null, "¿SEGURO QUE DESEA ELIMINAR DE LA BASE DE DATOS EL REGISTRO: " + Nombreproducto.getText() + "?");

        if (i == 0) {
            pro.setIdProductos(Integer.parseInt(idbodega.getText()));
            Boolean ResultadoEliminacion = proDao.EliminarProducto(pro);
            if (ResultadoEliminacion == true) {
                principal.VentanaAdministracionDeProductos=false;
                this.dispose();
                inventario.REFRESCAR_INVENTARIO();

            }

        }
    }

    public void EditarProducto() {
        if (ValidarCajas() == true) {

            int i = JOptionPane.showConfirmDialog(null, "¿SEGURO QUE DESEA EDITAR EL PRODUCTO?");
            if (i == 0) {

                String Letrass = Letras.getText();
                pro.setCodigoBarras(Id.getText());
                pro.setNombre(Nombreproducto.getText());
                pro.setCantidad(Float.parseFloat(Cantidad.getText()));
                pro.setCosto(Float.parseFloat(Costo.getText()));
                pro.setCodigoLetras(Letrass.toUpperCase());
                pro.setPublico(Float.parseFloat(Publico.getText()));
                pro.setPrecioEs(Float.parseFloat(Es.getText()));
                pro.setPrecioRe(Float.parseFloat(Reve.getText()));
                pro.setIdProductos(Integer.parseInt(idbodega.getText()));
                pro.setRuta(labelruta.getText());
                pro.setUsuarioModifico(Integer.parseInt(IdUsuario.getText()));
                pro.setDescripcion(CajaDescripcion.getText());
                pro.setNombreTiposDePrecio1(NombrePrecio1.getSelectedItem().toString());
                pro.setNombreTiposDePrecio2(NombrePrecio2.getSelectedItem().toString());
                pro.setNombreTiposDePrecio3(NombrePrecio3.getSelectedItem().toString());
                
                pro.setCategoria(ConsultarIdCategoria(ComboCategorias));
                pro.setIdProveedores(ConsultarIdProveedor(ProveedoresCombo));
                pro.setSubcategoria(ConsultarIdSubCategoria(ComboSubCategorias, ComboCategorias));
                pro.setUbicacion1(ConsultarIdUbicacion(comboubicacion));
                pro.setUbicacion2(ConsultarIdUbicacion(comboubicacion2));
                pro.setEstado_Producto(String.valueOf(Combo_ESTADO_Productos.getSelectedItem()));
                pro.setAPLICAR_DESCUENTO(jCheckBox1.isSelected());
                proDao.EditarProductoSinImagen(pro);
                Id.requestFocus();
                principal.VentanaAdministracionDeProductos=false;
                this.dispose();
                inventario.REFRESCAR_INVENTARIO();
            }
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        labelruta = new javax.swing.JLabel();
        Eliminarp = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        IdUsuario = new javax.swing.JLabel();
        NombreUsuarioVista = new javax.swing.JLabel();
        EstadoProducto = new javax.swing.JLabel();
        EstadoProducto2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel34 = new javax.swing.JPanel();
        Nombreproducto = new javax.swing.JTextField();
        Costo = new javax.swing.JTextField();
        Publico = new javax.swing.JTextField();
        Letras = new javax.swing.JTextField();
        Es = new javax.swing.JTextField();
        Reve = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        comboubicacion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        ProveedoresCombo = new javax.swing.JComboBox<>();
        Id = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        ComboCategorias = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        Cantidad = new javax.swing.JTextField();
        NombrePrecio1 = new javax.swing.JComboBox<>();
        NombrePrecio2 = new javax.swing.JComboBox<>();
        NombrePrecio3 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        CajaDescripcion = new javax.swing.JTextArea();
        jLabel45 = new javax.swing.JLabel();
        ComboSubCategorias = new javax.swing.JComboBox<>();
        comboubicacion2 = new javax.swing.JComboBox<>();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        labelimagen = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        Agregarp = new javax.swing.JButton();
        Editarp = new javax.swing.JButton();
        Nuevop = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        idbodega = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        Combo_ESTADO_Productos = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        AgregarItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        EditarItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem12 = new javax.swing.JMenuItem();

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jMenuItem6.setText("jMenuItem6");

        labelruta.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        labelruta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelruta.setText("");

        Eliminarp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Eliminarp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/borrar.png"))); // NOI18N
        Eliminarp.setText("ELIMINAR");
        Eliminarp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Eliminarp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Eliminarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarpActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADMINISTRACIÓN DE PRODUCTOS");
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(500, 200));

        IdUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IdUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ID USUARIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N

        NombreUsuarioVista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NombreUsuarioVista.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "NOMBRE DE USUARIO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N

        EstadoProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EstadoProducto.setText("NO INGRESADO");
        EstadoProducto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ESTADO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N

        EstadoProducto2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EstadoProducto2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "MODIFICADO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 10))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(IdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreUsuarioVista, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EstadoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(EstadoProducto2, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(IdUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(NombreUsuarioVista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(EstadoProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(EstadoProducto2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(16);

        jPanel34.setMinimumSize(new java.awt.Dimension(350, 480));

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
        Costo.setNextFocusableComponent(Publico);
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
        Publico.setNextFocusableComponent(Es);
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
        Es.setNextFocusableComponent(Reve);
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
        Reve.setNextFocusableComponent(jCheckBox1);
        Reve.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ReveKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ReveKeyTyped(evt);
            }
        });

        jLabel34.setBackground(new java.awt.Color(235, 232, 232));
        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("ÚBICACIONES*");

        comboubicacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tienda" }));
        comboubicacion.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setBackground(new java.awt.Color(235, 232, 232));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("PROVEEDOR *");

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
        Id.setNextFocusableComponent(Nombreproducto);
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

        jLabel44.setBackground(new java.awt.Color(235, 232, 232));
        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("CATEGORÍA *");

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

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jButton1.setText("GENERAR CÓDIGO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("CÓDIGO*");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("NOMBRE*");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("STOCK*");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("COSTO(#)");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("COSTO EN LETRAS");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("PRECIO 1*");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("PRECIO 2*");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("PRECIO 3*");

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
        jScrollPane1.setViewportView(CajaDescripcion);

        jLabel45.setBackground(new java.awt.Color(235, 232, 232));
        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setText("SUBCATEGORÍA*");

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

        comboubicacion2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tienda" }));
        comboubicacion2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        labelimagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelimagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        labelimagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelimagenMouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Nuevo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("AJUSTE");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("DESCUENTOS");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator19)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(Id)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Nombreproducto)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(Costo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Letras))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(NombrePrecio3, 0, 122, Short.MAX_VALUE)
                                    .addComponent(NombrePrecio2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(NombrePrecio1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Es, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Publico)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                                        .addComponent(Reve)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(Cantidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))))
                    .addComponent(jSeparator7, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ProveedoresCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboSubCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(comboubicacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboubicacion2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(1, 1, 1))
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addComponent(ComboCategorias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))))
                    .addComponent(jSeparator18, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(13, 13, 13))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Id, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Nombreproducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Cantidad)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Costo)
                    .addComponent(Letras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NombrePrecio1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Publico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(NombrePrecio2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Es))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NombrePrecio3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Reve)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboSubCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ProveedoresCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboubicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboubicacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel34);

        Agregarp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Agregarp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        Agregarp.setText("AGREGAR (F2)");
        Agregarp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Agregarp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Agregarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarpActionPerformed(evt);
            }
        });

        Editarp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Editarp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        Editarp.setText("EDITAR (F3)");
        Editarp.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Editarp.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Editarp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarpActionPerformed(evt);
            }
        });

        Nuevop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Nuevop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        Nuevop.setText("LIMPIAR (F5)");
        Nuevop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Nuevop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Nuevop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevopActionPerformed(evt);
            }
        });

        Actualizar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Actualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar Pequeño.png"))); // NOI18N
        Actualizar.setText("UPDATE (F6)");
        Actualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Actualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/barcode_code_icon_194645.png"))); // NOI18N
        jButton3.setText("<html>GENERAR (F9)</html>");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerifyInputWhenFocusTarget(false);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        idbodega.setEditable(false);
        idbodega.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idbodega.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ID:");

        Combo_ESTADO_Productos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Combo_ESTADO_Productos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Combo_ESTADO_Productos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idbodega, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Agregarp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Editarp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Nuevop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idbodega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Combo_ESTADO_Productos, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Actualizar, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nuevop, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Editarp, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Agregarp, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("OPCIONES");

        jMenuItem13.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem13.setText("IR A VENTA");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem13);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("ACCIONES");
        jMenu2.add(jSeparator2);

        AgregarItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        AgregarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        AgregarItem.setText("AGREGAR PRODUCTO");
        AgregarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarItemActionPerformed(evt);
            }
        });
        jMenu2.add(AgregarItem);
        jMenu2.add(jSeparator1);

        EditarItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        EditarItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        EditarItem.setText("EDITAR PRODUCTO");
        EditarItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarItemActionPerformed(evt);
            }
        });
        jMenu2.add(EditarItem);
        jMenu2.add(jSeparator3);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        jMenuItem7.setText("ELIMINAR PRODUCTO DE BD");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);
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

        jMenuItem12.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/barcode_code_icon_194645.png"))); // NOI18N
        jMenuItem12.setText("GENERAR ETIQUETA");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void BuscarProducto(JTextField CampoBuscando) {

    }

    public void CargarImagen() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ObtenerRutaImagen(0));
        ImageIcon bl = new ImageIcon(retValue);
        PintarImagen2(labelimagen, bl);
        labelruta.setText(ObtenerRutaImagen(2));
    }

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        VaciarYllenarCategoria(ComboCategorias);
        VaciarYllenarUbicacion();
        VaciarYllenarProveedor();
        ListarProductosTienda(Nombreproducto);
        Id.requestFocus();
    }//GEN-LAST:event_ActualizarActionPerformed

    private void AgregarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarpActionPerformed
         Ingreso();
    }//GEN-LAST:event_AgregarpActionPerformed

    private void EditarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarpActionPerformed
        if (!"".equals(Id.getText())) {
                    EditarProducto();
        } else {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                DesktopNotify.showDesktopMessage("¡ERROR!", "¡AÚN NO HAY UN PRODUCTO PARA EDITAR!", DesktopNotify.FAIL, 14000L);
        }
    }//GEN-LAST:event_EditarpActionPerformed

    private void EliminarpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarpActionPerformed
       /* if (!"".equals(Id.getText())) {
                EliminarProducto();
        } else {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UN PRODUCTO!");
        }*/
    }//GEN-LAST:event_EliminarpActionPerformed

    private void NuevopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevopActionPerformed

            limpiarCajas();
        
    }//GEN-LAST:event_NuevopActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!"".equals(Id.getText())) {

            int seleccion = JOptionPane.showOptionDialog(this, "¡Por Favor elija un tamaño! ", //contenido de la ventana
                    "Etiquetas", //titulo de la ventana
                    JOptionPane.YES_NO_CANCEL_OPTION, //para 3 botones si/no/cancel
                    JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                    null, // null para icono por defecto.
                    new Object[]{"Pequeño 1x1", "Pequeño 1x3", "Mediano", "Grande", "Cancelar"},//objeto para las opciones
                    //null para YES, NO y CANCEL
                    "Pequeño 1x1"); //selección predeterminada
            switch (seleccion) {
                case 0 -> CodigoBarrasPequeño();
                case 1 -> CodigoBarras4();
                case 2 -> CodigoBarras2();
                case 3 -> CodigoBarras3();
                default -> {
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL GENERAR CÓDIGOS", "¡DEBE INGRESAR UN PRODUCTO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        switch (comboubicacion.getSelectedIndex()) {
            case 1 -> {
            }
            case 0 -> Ingreso();
            default -> JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA ÚBICACIÓN!");  
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void AgregarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarItemActionPerformed
        switch (comboubicacion.getSelectedIndex()) {
            case 1 -> {
            }
            case 0 -> Ingreso();
            default -> JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UNA ÚBICACIÓN!");  
        }
    }//GEN-LAST:event_AgregarItemActionPerformed

    private void EditarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarItemActionPerformed
        if (!"".equals(Id.getText())) {
                    EditarProducto();
                    principal.VentanaAdministracionDeProductos=false;
                    this.dispose();
                    inventario.ActualizarTablaCentral();
            
        } else {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UN PRODUCTO!");
        }
    }//GEN-LAST:event_EditarItemActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
    
            limpiarCajas();
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        VaciarYllenarCategoria(ComboCategorias);
        VaciarYllenarProveedor();
        ListarProductosTienda(Nombreproducto);
        Id.requestFocus();
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        if (!"".equals(Id.getText())) {

            int seleccion = JOptionPane.showOptionDialog(null, "¡Por Favor elija un tamaño! ", //contenido de la ventana
                    "Etiquetas", //titulo de la ventana
                    JOptionPane.YES_NO_CANCEL_OPTION, //para 3 botones si/no/cancel
                    JOptionPane.QUESTION_MESSAGE, //tipo de ícono
                    null, // null para icono por defecto.
                    new Object[]{"Pequeño 1x1", "Pequeño 1x3", "Mediano", "Grande", "Cancelar"},//objeto para las opciones
                    //null para YES, NO y CANCEL
                    "Pequeño 1x1"); //selección predeterminada
            switch (seleccion) {
                case 0:
                    CodigoBarrasPequeño();
                    break;
                case 1:
                    CodigoBarras4();
                    break;
                case 2:
                    CodigoBarras2();
                    break;
                case 3:
                    CodigoBarras3();
                    break;
                default:
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR AL GENERAR CÓDIGOS", "¡DEBE INGRESAR UN PRODUCTO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

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
    
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        //MoverEntreSistema();
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CargarImagen();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void labelimagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelimagenMouseClicked
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos PNG, JPG, JPEG, WEBP(*.PNG;*.JPG;*.JPEG;*.WEBP)", "PNG", "JPG", "JPEG", "WEBP");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("JPEG(*.JPEG)", "JPEG");
        FileNameExtensionFilter filtro3 = new FileNameExtensionFilter("JPG(*.JPG;)", "JPG");
        FileNameExtensionFilter filtro4 = new FileNameExtensionFilter("PNG(*.PNG;)", "PNG");
        FileNameExtensionFilter filtro5 = new FileNameExtensionFilter("WEBP(*.WEBP;)", "WEBP");
        JFileChooser archivo = new JFileChooser();
        archivo.setCurrentDirectory(new File(METODOS_GLOBALES.CargarDatosRutasAlBuscarImagen()));
        archivo.addChoosableFileFilter(filtro);
        archivo.addChoosableFileFilter(filtro2);
        archivo.addChoosableFileFilter(filtro3);
        archivo.addChoosableFileFilter(filtro4);
        archivo.addChoosableFileFilter(filtro5);
        archivo.setFileFilter(filtro);
        archivo.setDialogTitle("SELECCIONE UNA IMAGEN");
        int ventana = archivo.showOpenDialog(this);
        if (ventana == JFileChooser.APPROVE_OPTION) {
            try {
                File file = archivo.getSelectedFile();
                if (OBTENER_EXTENSION_ARCHIVO(file.getPath()).equals("webp")) {
                                    ImageIcon imagenIa = new ImageIcon(ClassLoader.getSystemResource("IconosSOciales/CARGANDO.gif"));
                                    METODOS_GLOBALES.PintarImagen2(labelimagen, imagenIa);
                                    class CARGAR_IMAGEN_CONVERTIR extends Thread {

                                        @Override
                                        public void run() {
                                            String NombreFinal_Webp = String.valueOf(new Random().nextLong()).substring(7) + ".png";
                                            String RutaFinal_Webp = CargarDatosRutas(1) + "\\" + NombreFinal_Webp;
                                            METODOS_GLOBALES.CONVERSION_WEBP_IMAGE(file.getPath(), RutaFinal_Webp, ImageFileType.Png);
                                            labelruta.removeAll();
                                            labelruta.setText(NombreFinal_Webp);
                                            METODOS_GLOBALES.PintarImagen(labelimagen, RutaFinal_Webp);
                                        }
                                    }

                                    CARGAR_IMAGEN_CONVERTIR Hilo_CARGAR_IMAGEN_CONVERTIR = new CARGAR_IMAGEN_CONVERTIR();
                                    Hilo_CARGAR_IMAGEN_CONVERTIR.start();

                                } else {
                                    //Origen y Copiar A Servido
                DestinoPath = CargarDatosRutas(1) + "\\"+ file.getName();
                Path Destino = Paths.get(DestinoPath);
                String OrigenPath = file.getPath();
                Path Origen = Paths.get(OrigenPath);
                Files.copy(Origen, Destino, StandardCopyOption.REPLACE_EXISTING);

                File old = new File(DestinoPath);
                String DestinoFinal = CargarDatosRutas(1) + "\\"+String.valueOf(new Random().nextLong()).substring(7)+"-"+ old.getName();
                File newfile = new File(DestinoFinal);
                if (old.renameTo(newfile)) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ÉXITO", "LA IMAGEN SE RENOMBRO:\n "+DestinoFinal , DesktopNotify.SUCCESS, 8000L);
                } else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERROR", "NO SE PUDO RENOMBRAR LA IMAGEN" , DesktopNotify.ERROR, 10000L);
                }

                labelruta.setText(newfile.getName());

                METODOS_GLOBALES.PintarImagen(labelimagen, CargarDatosRutas(1) + "\\"+ newfile.getName());
                    RutaDeImagen = CargarDatosRutas(1) + "\\"+ newfile.getName();   
                                }
                } catch (IOException ex) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERROR", "NO SE PUDO MOVER LA IMAGEN" + ex , DesktopNotify.ERROR, 14000L);

                }
            }
    }//GEN-LAST:event_labelimagenMouseClicked

    private void ComboSubCategoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboSubCategoriasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboSubCategoriasKeyPressed

    private void ComboSubCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboSubCategoriasActionPerformed

    }//GEN-LAST:event_ComboSubCategoriasActionPerformed

    private void ComboSubCategoriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboSubCategoriasItemStateChanged

    }//GEN-LAST:event_ComboSubCategoriasItemStateChanged

    private void CajaDescripcionMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CajaDescripcionMouseReleased
        CajaDescripcion.setToolTipText("INGRESA UNA DESCRIPCIÓN, ÚBICACIÓN O PALABRA CLAVE AL PRODUCTO");
    }//GEN-LAST:event_CajaDescripcionMouseReleased

    private void CajaDescripcionMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CajaDescripcionMouseMoved
        CajaDescripcion.setToolTipText("INGRESA UNA DESCRIPCIÓN, ÚBICACIÓN O PALABRA CLAVE AL PRODUCTO");
    }//GEN-LAST:event_CajaDescripcionMouseMoved

    private void CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Cantidad);
    }//GEN-LAST:event_CantidadKeyTyped

    private void CantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CantidadKeyReleased
        ActualizarEstado();
    }//GEN-LAST:event_CantidadKeyReleased

    private void CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CantidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //co = Id.getText();
        //numero = (int) (Math.random() * 1000000000 + 1);
        /*for (int i = 0; i < 13; i++) {
            numero = (int) (Math.random() * 1234567890 + 1);
            co = String.valueOf(numero);
        }*/

        this.Id.setText(String.valueOf(new Random().nextLong()).substring(7));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ComboCategoriasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ComboCategoriasKeyPressed

    }//GEN-LAST:event_ComboCategoriasKeyPressed

    private void ComboCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboCategoriasActionPerformed
        VaciarYllenarSubCategoria(ComboCategorias, ComboSubCategorias);
    }//GEN-LAST:event_ComboCategoriasActionPerformed

    private void IdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyTyped
        Eventos event = new Eventos();
        event.numberKeyPress(evt);
    }//GEN-LAST:event_IdKeyTyped

    private void IdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyReleased

    }//GEN-LAST:event_IdKeyReleased

    private void IdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(Id.getText())) {
                InsertarDatos(Id.getText());
            } else {
                JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN CÓDIGO DE BARRAS", "PROCESO INVÁLIDO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_IdKeyPressed

    private void ReveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReveKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Reve);
    }//GEN-LAST:event_ReveKeyTyped

    private void ReveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ReveKeyReleased
        ActualizarEstado();
    }//GEN-LAST:event_ReveKeyReleased

    private void EsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EsKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Es);
    }//GEN-LAST:event_EsKeyTyped

    private void EsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EsKeyReleased
        ActualizarEstado();
    }//GEN-LAST:event_EsKeyReleased

    private void LetrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LetrasKeyReleased
        ActualizarEstado();
    }//GEN-LAST:event_LetrasKeyReleased

    private void PublicoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PublicoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Publico);
    }//GEN-LAST:event_PublicoKeyTyped

    private void PublicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PublicoKeyReleased
        ActualizarEstado();
    }//GEN-LAST:event_PublicoKeyReleased

    private void PublicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PublicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PublicoActionPerformed

    private void CostoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, Costo);
    }//GEN-LAST:event_CostoKeyTyped

    private void CostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyReleased
        ActualizarEstado();
        NumerosALetras NAL= new NumerosALetras();
        Letras.setText(NAL.Convertir(Costo.getText()));
    }//GEN-LAST:event_CostoKeyReleased

    private void CostoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CostoKeyPressed

    }//GEN-LAST:event_CostoKeyPressed

    private void NombreproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproductoKeyReleased
        ActualizarEstado();
        /*BuscarProductosEmergente.ConsultaPorNombreInventario(Nombreproducto.getText());
        if(VentanaBusquedaProducto == false){
            VentanaBusquedaProducto=true;
            BPVE.setVisible(true);
            Nombreproducto.requestFocus();
            BPVE.setLocationRelativeTo(ComboCategorias);
            Nombreproducto.requestFocus();
        }*/
    }//GEN-LAST:event_NombreproductoKeyReleased

    private void NombreproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproductoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(Nombreproducto.getText())) {
                InsertarProductosPorNombre(Nombreproducto.getText());

            } else {
                JOptionPane.showMessageDialog(null, "DEBE INGRESAR UN NOMBRE", "PROCESO INVÁLIDO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_NombreproductoKeyPressed

    private void ProveedoresComboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProveedoresComboKeyPressed

    }//GEN-LAST:event_ProveedoresComboKeyPressed

    private void ProveedoresComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedoresComboActionPerformed
        //AutoCompleteDecorator.decorate(ProveedoresCombo);
    }//GEN-LAST:event_ProveedoresComboActionPerformed

    private void ProveedoresComboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProveedoresComboMouseClicked

    }//GEN-LAST:event_ProveedoresComboMouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        EliminarProducto();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(principal.VentanaCategoria==false){
            principal.VentanaCategoria=true;
        principal.Cat= new CategoriaVista(this, true, 0, principal);
        CategoriaVista.ActualizarTablaCategorias(false);     
        }else{
            principal.Cat.toFront();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         if(principal.VentanaUbicaciones==false){
            principal.VentanaUbicaciones=true;
            principal.Ub= new Ubicaciones(this, true, 0, principal);
        }else{
            principal.Ub.toFront();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        AJUSTE_STOCK AJ= new AJUSTE_STOCK(this, true, Id.getText(), false);
        AJ.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        ActualizarEstado();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
    
    public int ConsultarIdSubCategoria(JComboBox ComboSubCategoria, JComboBox ComboCategoria){
        int ResultadoSubCategoria = 0;
      ResultadoSubCategoria = Integer.parseInt(String.valueOf(proDao.ConsultaIdSubCategoria(ConsultarIdCategoria(ComboCategoria), ComboSubCategoria)));
      return ResultadoSubCategoria;
    }
    
    public void CodigoBarras2() {
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id.getText());
        ParametrosCodigosBarras.setNombreproducto(Nombreproducto.getText());
        ParametrosCodigosBarras.setPrecioPublico(Publico.getText());
        ParametrosCodigosBarras.setCostoLetras(Letras.getText());
        CodigosBarras.CodigoBarrasMediano(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }

    public void CodigoBarras3() {
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id.getText());
        ParametrosCodigosBarras.setNombreproducto(Nombreproducto.getText());
        ParametrosCodigosBarras.setPrecioPublico(Publico.getText());
        ParametrosCodigosBarras.setCostoLetras(Letras.getText());
        CodigosBarras.CodigoBarrasGrande(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA, PARAMETROS_EMPRESA.NOMBRE_EMPRESA);
    }

    public void CodigoBarras4() {
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id.getText());
        ParametrosCodigosBarras.setNombreproducto(Nombreproducto.getText());
        ParametrosCodigosBarras.setPrecioPublico(Publico.getText());
        ParametrosCodigosBarras.setCostoLetras(Letras.getText());
        CodigosBarras.CodigoBarras3x1(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
    }

    public void CodigoBarrasPequeño() {
        ParametrosCodigosBarras.setCodigoDeBarrasOId(Id.getText());
        ParametrosCodigosBarras.setNombreproducto(Nombreproducto.getText());
        ParametrosCodigosBarras.setPrecioPublico(Publico.getText());
        ParametrosCodigosBarras.setCostoLetras(Letras.getText());
        CodigosBarras.CodigoBarrasPequeño(ParametrosCodigosBarras, PARAMETROS_EMPRESA.NOMBRE_EN_ETIQUETA_EMPRESA);
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
            java.util.logging.Logger.getLogger(ADMINISTRARPRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADMINISTRARPRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADMINISTRARPRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMINISTRARPRODUCTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new ADMINISTRARPRODUCTO().setVisible(true);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                Logger.getLogger(ADMINISTRARPRODUCTO.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton Actualizar;
    private static javax.swing.JMenuItem AgregarItem;
    private static javax.swing.JButton Agregarp;
    private static javax.swing.JTextArea CajaDescripcion;
    public static javax.swing.JTextField Cantidad;
    private static javax.swing.JComboBox<String> ComboCategorias;
    private static javax.swing.JComboBox<String> ComboSubCategorias;
    private static javax.swing.JComboBox<String> Combo_ESTADO_Productos;
    private static javax.swing.JTextField Costo;
    private static javax.swing.JMenuItem EditarItem;
    private static javax.swing.JButton Editarp;
    private static javax.swing.JButton Eliminarp;
    private static javax.swing.JTextField Es;
    private static javax.swing.JLabel EstadoProducto;
    private static javax.swing.JLabel EstadoProducto2;
    private static javax.swing.JTextField Id;
    private static javax.swing.JLabel IdUsuario;
    private static javax.swing.JTextField Letras;
    private static javax.swing.JComboBox<String> NombrePrecio1;
    private static javax.swing.JComboBox<String> NombrePrecio2;
    private static javax.swing.JComboBox<String> NombrePrecio3;
    private static javax.swing.JLabel NombreUsuarioVista;
    private static javax.swing.JTextField Nombreproducto;
    private static javax.swing.JButton Nuevop;
    private static javax.swing.JComboBox<String> ProveedoresCombo;
    private static javax.swing.JTextField Publico;
    private static javax.swing.JTextField Reve;
    private static javax.swing.JComboBox<String> comboubicacion;
    private static javax.swing.JComboBox<String> comboubicacion2;
    private static javax.swing.JTextField idbodega;
    private static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private static javax.swing.JButton jButton6;
    private static javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private static javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private static javax.swing.JLabel labelimagen;
    private static javax.swing.JLabel labelruta;
    // End of variables declaration//GEN-END:variables

    public void ListarProductosTienda(JTextField Parametro) {
        try {
            tablasVentas = new ActualizarTablaVentas();
            AutoCompletador.removeAllItems();
            AutoCompletador.setMode(0); // infijo
            List<Productos> ListarPr = tablasVentas.ListarProductosTiendaNombre();
            Object[] ob = new Object[1];
            for (int i = 0; i < ListarPr.size(); i++) {
                ob[0] = ListarPr.get(i).getNombre();
                AutoCompletador.addItems(ob);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "NO SE PUDO CARGAR LOS DATOS");
        }
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
    
}
