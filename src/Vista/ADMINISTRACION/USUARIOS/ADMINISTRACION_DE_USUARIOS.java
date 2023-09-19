/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista.ADMINISTRACION.USUARIOS;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.CargarDatosRutas;
import static CLASES_GLOBALES.METODOS_GLOBALES.OBTENER_EXTENSION_ARCHIVO;
import static CLASES_GLOBALES.METODOS_GLOBALES.PintarImagen;
import static CLASES_GLOBALES.METODOS_GLOBALES.PintarImagen2;
import static CLASES_GLOBALES.METODOS_GLOBALES.executorService;
import static CLASES_GLOBALES.METODOS_GLOBALES.validarCorreoElectronico;
import Controlador.loginDao;
import Modelo.DatosEmpresaGeneral;
import Modelo.login;
import static Vista.ADMINISTRACION.USUARIOS.USUARIOS_INTERNOS.Usuarios;
import Vista.Principal;
import com.groupdocs.conversion.filetypes.ImageFileType;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Color;
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
import java.util.Properties;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aldoy
 */
public class ADMINISTRACION_DE_USUARIOS extends javax.swing.JFrame {
    Principal principal;
    loginDao logDao;
    login log;
    private String RutaDeImagen = "";
    String DestinoPath;
    public ADMINISTRACION_DE_USUARIOS() {
    }
    
    public ADMINISTRACION_DE_USUARIOS(Principal principal) {
        initComponents();
        this.principal = principal;
        VaciarUsuario();
        Cerrar();
        CargarImagen();
        this.setLocationRelativeTo(null);
        DRAG_AND_DROP_IMAGEN();
        
    }
    
    public void DRAG_AND_DROP_IMAGEN() {
    executorService.execute(new Runnable() {
        @Override
        public void run() {
            new rsdragdropfiles.RSDragDropFiles(jPanel1, new rsdragdropfiles.RSDragDropFiles.Listener() {
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
                                        String NombreFinal_Webp = String.valueOf(new Random().nextLong()).substring(15) + ".png";
                                        String RutaFinal_Webp = CargarDatosRutas(3) + "\\" + NombreFinal_Webp;
                                        try {
                                            METODOS_GLOBALES.CONVERSION_WEBP_IMAGE(files[0].getCanonicalPath(), RutaFinal_Webp, ImageFileType.Png);
                                        } catch (IOException ex) {
                                           
                                        }
                                        labelruta.removeAll();
                                        labelruta.setText(NombreFinal_Webp);
                                        METODOS_GLOBALES.PintarImagen(labelimagen, RutaFinal_Webp);
                                    }
                                }

                                CARGAR_IMAGEN_CONVERTIR hilo_CARGAR_IMAGEN_CONVERTIR = new CARGAR_IMAGEN_CONVERTIR();
                                hilo_CARGAR_IMAGEN_CONVERTIR.start();
                            } else {
                                labelruta.removeAll();
                                String NombreFinal = String.valueOf(new Random().nextLong()).substring(13) + "-" + files[0].getName();
                                String RutaFinal = CargarDatosRutas(3) + "\\" + NombreFinal;
                                rsdragdropfiles.RSDragDropFiles.setCopiar(files[0].getCanonicalPath(), RutaFinal);
                                METODOS_GLOBALES.PintarImagen(labelimagen, RutaFinal);
                                labelruta.setText(NombreFinal);
                            }
                        } catch (IOException ex) {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                            DesktopNotify.showDesktopMessage("ERROR AL CARGAR IMAGEN EN MODO DRAG AND DROP \n", "" + ex, DesktopNotify.ERROR, 14000L);
                        }
                    }
                }
            });
        }
    });
    }

    public void CargarImagen() {
        labelruta.removeAll();
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ObtenerRutaImagen(0));
        ImageIcon bl = new ImageIcon(retValue);
        PintarImagen2(labelimagen, bl);
        labelruta.setText(ObtenerRutaImagen(2));
        RutaDeImagen = ObtenerRutaImagen(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        labelruta = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel58 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        CajaNombre = new javax.swing.JTextField();
        CajaContraseña = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        NombreUsuario = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        CajaId = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        ComboRol = new javax.swing.JComboBox<>();
        Btn_Color_RGB = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        Caja_COLOR_FX = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel49 = new javax.swing.JPanel();
        jButton45 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        combo_Estado = new javax.swing.JComboBox<>();
        Check_AccesoDirecto = new javax.swing.JCheckBox();
        labelimagen = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        labelruta.setFont(new java.awt.Font("Dialog", 1, 8)); // NOI18N
        labelruta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelruta.setText("");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADMINISTRACIÓN DE USUARIOS");
        setMinimumSize(new java.awt.Dimension(500, 490));
        setPreferredSize(new java.awt.Dimension(400, 590));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel32.setText("*NOMBRE DE INICIO DE SESIÓN:");

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel65.setText("* CONTRASEÑA DE INICIO DE SESIÓN:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("* NOMBRE DE USUARIO:");

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel59.setText("DIRECCIÓN DE CORREO ELECTRÓNICO:");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel60.setText("CONTRASEÑA DE CORREO ELECTRÓNICO:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("ID DEL USUARIO:");

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel66.setText("ROL:");

        ComboRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Usuario" }));

        Btn_Color_RGB.setBackground(new java.awt.Color(255, 51, 0));
        Btn_Color_RGB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Color_RGBActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("COLOR USUARIO:");

        Caja_COLOR_FX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Caja_COLOR_FXKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2)
                    .addComponent(NombreUsuario)
                    .addComponent(CajaContraseña)
                    .addComponent(CajaNombre)
                    .addComponent(jTextField3)
                    .addComponent(CajaId)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ComboRol, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addComponent(Caja_COLOR_FX)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Color_RGB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel59)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboRol, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Btn_Color_RGB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Caja_COLOR_FX, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });

        jButton47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });

        jButton46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });

        jLabel1.setText("ESTADO:");

        combo_Estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        Check_AccesoDirecto.setText("ACCESO DIRECTO");

        labelimagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelimagenMouseClicked(evt);
            }
        });

        jButton1.setText("LIMPIAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(combo_Estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Check_AccesoDirecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelimagen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel49Layout.createSequentialGroup()
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Check_AccesoDirecto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelimagen, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            principal.VENTANA_ADMINISTRACION_DE_USUARIOS=false;
            this.dispose();
    }
    
    public void VaciarUsuario(){
        CajaNombre.setText(null);
        CajaContraseña.setText(null);
        ComboRol.setSelectedItem(null);
        CajaNombre.requestFocus();
        NombreUsuario.setText(null);
        jButton47.setVisible(false);
        jButton45.setVisible(true);
    }
    
    public Boolean VALIDAR_CAMPOS(){
        Boolean Estado=false;
        if(CajaNombre.getText().equals("")){
            Estado=false;
            JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR UN NOMBRE PARA INICIO DE SESIÓN!");
            CajaNombre.requestFocus();
        }else if(CajaContraseña.getText().equals("")){
            Estado=false;
            JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR LA CONTRASEÑIA DE USUARIO!");
            CajaContraseña.requestFocus();
        }else if(ComboRol.getSelectedItem().equals("")){
            Estado=false;
            JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR EL ROL DEL USUARIO!");
            ComboRol.requestFocus();
        }else if(NombreUsuario.equals("")){
            Estado=false;
            JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR UN NOMBRE DE USUARIO!");
            NombreUsuario.requestFocus();
        }else{
            Estado=true;
        }
        return Estado;
    }
    
    public void BUSCAR_USUARIO(String ID_USUARIO){
        logDao = new loginDao();
        log = new login();
        log.setIdlogin1(Integer.parseInt(ID_USUARIO));
        logDao.VerDatosTablaLogin(log);

        CajaId.setText(String.valueOf(log.getIdlogin1()));
        CajaNombre.setText(log.getNombre());
        CajaContraseña.setText(log.getContraseña());
        NombreUsuario.setText(log.getNombreUsuario());
        ComboRol.setSelectedItem(log.getRol());
        combo_Estado.setSelectedItem(log.getEstado_Registro());
        jTextField2.setText(log.getCorreo());
        jTextField3.setText(log.getContrasenia_Correo());
        Caja_COLOR_FX.setText(log.getColor());
        Cargar_Color(log.getColor());
        Check_AccesoDirecto.setSelected(Boolean.valueOf(log.getAccesoDirecto()));
        jButton47.setVisible(true);
        jButton45.setVisible(false);
        PintarImagen(labelimagen, CargarDatosRutas(3) + "/" + log.getImagen());
    }

    public void INGRESAR_USUARIO() {
        String NombreExistente = loginDao.RetornarNombreUsuarioDuplicados(NombreUsuario.getText());
        if (!"".equals(NombreExistente)) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Light);
            DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL NOMBRE YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);
        } else {
            if (VALIDAR_CAMPOS() == true) {
                login lg = new login();
                loginDao logina = new loginDao();
                String Nom = CajaNombre.getText();
                String con = CajaContraseña.getText();
                String ro = (String) ComboRol.getSelectedItem();
                lg.setNombre(Nom);
                lg.setContraseña(con);
                lg.setRol(ro);
                lg.setNombreUsuario(NombreUsuario.getText());
                lg.setColor(Caja_COLOR_FX.getText());
                lg.setEstado_Registro(combo_Estado.getSelectedItem().toString());
                lg.setAccesoDirecto(String.valueOf(Check_AccesoDirecto.isSelected()));
                lg.setImagen(labelruta.getText());
                lg.setCorreo(jTextField2.getText());
                lg.setCorreo(jTextField3.getText());
                logina.Registrar(lg);
                VaciarUsuario();
                Usuarios();
                principal.VENTANA_ADMINISTRACION_DE_USUARIOS=false;
            }
        }
    }

    public void EDITAR_USUARIOS() {
        login lg = new login();
        loginDao logina = new loginDao();
        if (!"".equals(CajaId.getText())) {

            if (VALIDAR_CAMPOS() == true) {
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE EDITAR EL USUARIO: " + CajaNombre.getText() + "?");
                if (confirmacion == 0) {
                    lg.setNombre(CajaNombre.getText());
                    lg.setContraseña(CajaContraseña.getText());
                    lg.setRol(String.valueOf(ComboRol.getSelectedItem()));
                    lg.setNombreUsuario(NombreUsuario.getText());
                    lg.setIdlogin1(Integer.parseInt(CajaId.getText()));
                    lg.setColor(Caja_COLOR_FX.getText());
                    lg.setEstado_Registro(combo_Estado.getSelectedItem().toString());
                    lg.setAccesoDirecto(String.valueOf(Check_AccesoDirecto.isSelected()));
                    lg.setImagen(labelruta.getText());
                    lg.setCorreo(jTextField2.getText());
                    lg.setContrasenia_Correo(jTextField3.getText());
                    if (combo_Estado.getSelectedIndex() == 1) {
                        int Seleccion = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO QUE QUIERE DESACTIVAR EL USUARIO: " + CajaNombre.getText()+"?");
                        if (Seleccion == 0) {
                            logina.EditarUsuarios(lg);
                            VaciarUsuario();
                            Usuarios();
                            principal.VENTANA_ADMINISTRACION_DE_USUARIOS = false;
                            this.dispose();
                        }
                    }else{
                        logina.EditarUsuarios(lg);
                            VaciarUsuario();
                            Usuarios();
                            principal.VENTANA_ADMINISTRACION_DE_USUARIOS = false;
                            this.dispose();
                    }

                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "¡DEBE SELECCIONAR UN USUARIO!");
        }
    }
    
    public void ELIMINAR_USUARIO(){
        login lg= new login();
        loginDao logina=new loginDao();
        int confirmacion= JOptionPane.showConfirmDialog(null, "Esta seguro que desea eliminar el Usuario: "+CajaNombre.getText());
        if(confirmacion==0){
            String cod= CajaId.getText();
            int cod2= Integer.parseInt(cod);
            lg.setIdlogin1(cod2);
            logina.EliminarUsuario(lg);
            VaciarUsuario();
            Usuarios();
            principal.VENTANA_ADMINISTRACION_DE_USUARIOS=false;
            this.dispose();
        }
    }
    
    public void Cargar_Color(String selectedColor){
        try {
            Color color = Color.decode(selectedColor);
        Caja_COLOR_FX.setText(selectedColor);
            Btn_Color_RGB.setBackground(color);
            
        } catch (Exception e) {
            selectedColor = "#FFFFFF";
            Color color = Color.decode(selectedColor);
        Caja_COLOR_FX.setText(selectedColor);
            Btn_Color_RGB.setBackground(color);
        }
    }

    
    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        if(validarCorreoElectronico(jTextField2.getText())==true){
            INGRESAR_USUARIO();
        }else {
                JOptionPane.showMessageDialog(null, "INGRESE UN CORREO ELECTRÓNICO VÁLIDO");
            }
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        if(validarCorreoElectronico(jTextField2.getText())==true || jTextField2.getText().equals("")){
            EDITAR_USUARIOS();
        }else {
                JOptionPane.showMessageDialog(null, "INGRESE UN CORREO ELECTRÓNICO VÁLIDO");
            }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
        VaciarUsuario();
    }//GEN-LAST:event_jButton46ActionPerformed

    private void Btn_Color_RGBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Color_RGBActionPerformed
        Color selectedColor = jColorChooser1.showDialog(this, "SELECCIONA EL COLOR", Color.WHITE);
        if (selectedColor != null) {
             String rgbValue = String.format("#%06X", (0xFFFFFF & selectedColor.getRGB()));
            Cargar_Color(rgbValue);
        }
    }//GEN-LAST:event_Btn_Color_RGBActionPerformed

    private void Caja_COLOR_FXKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Caja_COLOR_FXKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            Cargar_Color(Caja_COLOR_FX.getText());
        }
    }//GEN-LAST:event_Caja_COLOR_FXKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CargarImagen();
    }//GEN-LAST:event_jButton1ActionPerformed

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
                                            String NombreFinal_Webp = String.valueOf(new Random().nextLong()).substring(13) + ".png";
                                            String RutaFinal_Webp = CargarDatosRutas(3) + "\\" + NombreFinal_Webp;
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
                DestinoPath = CargarDatosRutas(3) + "\\"+ file.getName();
                Path Destino = Paths.get(DestinoPath);
                String OrigenPath = file.getPath();
                Path Origen = Paths.get(OrigenPath);
                Files.copy(Origen, Destino, StandardCopyOption.REPLACE_EXISTING);

                File old = new File(DestinoPath);
                String DestinoFinal = CargarDatosRutas(3) + "\\"+String.valueOf(new Random().nextLong()).substring(13)+"-"+ old.getName();
                File newfile = new File(DestinoFinal);
                if (old.renameTo(newfile)) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ÉXITO", "LA IMAGEN SE RENOMBRO:\n "+DestinoFinal , DesktopNotify.SUCCESS, 8000L);
                } else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERROR", "NO SE PUDO RENOMBRAR LA IMAGEN" , DesktopNotify.ERROR, 10000L);
                }

                labelruta.setText(newfile.getName());

                METODOS_GLOBALES.PintarImagen(labelimagen, CargarDatosRutas(3) + "\\"+ newfile.getName());
                    RutaDeImagen = CargarDatosRutas(3) + "\\"+ newfile.getName();   
                                }
                } catch (IOException ex) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                    DesktopNotify.showDesktopMessage("ERROR", "NO SE PUDO MOVER LA IMAGEN" + ex , DesktopNotify.ERROR, 14000L);

                }
            }
    }//GEN-LAST:event_labelimagenMouseClicked

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
            java.util.logging.Logger.getLogger(ADMINISTRACION_DE_USUARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ADMINISTRACION_DE_USUARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ADMINISTRACION_DE_USUARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ADMINISTRACION_DE_USUARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ADMINISTRACION_DE_USUARIOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Color_RGB;
    private javax.swing.JTextField CajaContraseña;
    private javax.swing.JTextField CajaId;
    private javax.swing.JTextField CajaNombre;
    private javax.swing.JTextField Caja_COLOR_FX;
    private javax.swing.JCheckBox Check_AccesoDirecto;
    private javax.swing.JComboBox<String> ComboRol;
    private javax.swing.JTextField NombreUsuario;
    private javax.swing.JComboBox<String> combo_Estado;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel labelimagen;
    private static javax.swing.JLabel labelruta;
    // End of variables declaration//GEN-END:variables

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
