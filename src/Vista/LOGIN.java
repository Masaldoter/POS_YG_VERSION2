//[52,118,243]
package Vista;

import CLASES_GLOBALES.METODOS_GLOBALES;
import static CLASES_GLOBALES.METODOS_GLOBALES.CargarDatosRutas;
import static CLASES_GLOBALES.METODOS_GLOBALES.ObtenerRutaImagen;
import static CLASES_GLOBALES.METODOS_GLOBALES.executorService;
import CLASES_GLOBALES.PARAMETROS_EMPRESA;
import CLASES_GLOBALES.PARAMETROS_VERSION_SISTEMA;
import Controlador.DatosEmpresaDao;
import Controlador.TextPrompt;
import Modelo.login;
import Controlador.loginDao;
import Modelo.DatosEmpresaGeneral;
import WebServiceDigifact.ObtenerToken;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public final class LOGIN extends javax.swing.JFrame {

    private Icon icono;
    Properties properties;
    InputStream entrada = null;
    FondoPanel fondo = new FondoPanel();
    PARAMETROS_EMPRESA P_E;
    DatosEmpresaGeneral DE = new DatosEmpresaGeneral();

    @Override
    public Image getIconImage() {

        DE = login.VerDatosEmpresaEnLogin();
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(CargarDatosRutas(0) + "\\" + DE.getRutaimagensistema());

        return retValue;
    }
    login lg = new login();
    loginDao login = new loginDao();
    AVISOS AV;
    
    public LOGIN() {
        setContentPane(fondo);
        initComponents();
        TextoEnCajas();
        this.setLocationRelativeTo(null);
        this.setTitle("LOGIN | POS " + PARAMETROS_VERSION_SISTEMA.VERSION_SISTEMA);
        CargarImagen();
        CargarDatos();
        CREAR_ACCESOS_RAPIDOS ();
        
      /*  try {
      BufferedImage originalImage = ImageIO.read(new File(CargarDatosRutas(0) + "\\" + DE.getRutaimagensistema()));
      Image scaledImage = originalImage.getScaledInstance(10, 10, Image.SCALE_SMOOTH);
      BufferedImage icon = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
      Graphics g2 = icon.getGraphics();
      g2.setColor(Color.RED); // Cambia aquí el color de fondo del icono
      g2.fillRect(0, 0, 16, 16);
      g2.drawImage(scaledImage, 3, 3, null);
      // Establece la transparencia del dibujo para que se vean el color de fondo y la imagen
      ((Graphics2D) g2).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
      g2.dispose();
      setIconImage(icon); // Establece la imagen como el icono del JFrame
    } catch (IOException e) {
      e.printStackTrace();
    }*/
    }

    public static void TextoEnCajas() {
        TextPrompt hold;
        hold = new TextPrompt("USUARIO", us);
        hold = new TextPrompt("CONTRASEÑA", pass);
    }

    public final void CargarDatos() {
        try {
            properties = new Properties();
            entrada = new FileInputStream(new File("/Sistema Punto de Venta YG/datos.properties").getAbsolutePath());
            properties.load(entrada);
            String Estado = properties.getProperty("recuerdame");

            if (Estado.equals("true")) {
                jCheckBox1.setSelected(true);
                us.setText(properties.getProperty("usuario"));
                pass.setText(properties.getProperty("contrasenia"));
            } else {
                jCheckBox1.setSelected(false);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public void Recordar() {
        String Estado;
        try {
            if (jCheckBox1.isSelected()) {
                Estado = "true";
                properties = new Properties();
                properties.setProperty("usuario", us.getText());
                properties.setProperty("contrasenia", pass.getText());
                properties.setProperty("recuerdame", Estado);
                properties.store(new FileWriter(new File("/Sistema Punto de Venta YG/datos.properties").getAbsolutePath()), "DATOS DE USUARIO");
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("¡ÉXITO!", "¡DATOS GUARDADOS! ", DesktopNotify.SUCCESS, 14000L);
            } else {
                Estado = "false";
                properties = new Properties();
                properties.setProperty("usuario", "");
                properties.setProperty("contrasenia", "");
                properties.setProperty("recuerdame", Estado);
                properties.store(new FileWriter(new File("/Sistema Punto de Venta YG/datos.properties").getAbsolutePath()), "DATOS DE USUARIO");
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("¡ÉXITO!", "¡DATOS GUARDADOS! ", DesktopNotify.SUCCESS, 14000L);
            }
        } catch (IOException e) {
        }
    }
    
    public final String CargarDatos_ACCESOS_RAPIDOS(String ID, Boolean Ver) {
        try {
            properties = new Properties();
            entrada = new FileInputStream(new File("/Sistema Punto de Venta YG/ACCESOS_RAPIDOS_USUARIOS/" + ID).getAbsolutePath());
            properties.load(entrada);
            jCheckBox1.setSelected(true);
            if (Ver == true) {
                us.setText(properties.getProperty("usuario"));
                pass.setText(properties.getProperty("contrasenia"));
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return properties.getProperty("usuario");
    }

    public final void CargarImagen() {
        P_E = new PARAMETROS_EMPRESA();
        DatosEmpresaGeneral DE = new DatosEmpresaGeneral();
        DE = login.VerDatosEmpresaEnLogin();
        NombreEmpresa.setText("<html>" + DE.getNombreEmpresa() + "</html>");


        Eslogan.setText("<html>" + DE.getEslogan() + "</html>");
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(CargarDatosRutas(0) + "\\" + DE.getRutaimagenlogo());
        ImageIcon bl = new ImageIcon(retValue);
        PintarImagen2(jLabel7, bl);
        PARAMETROS_EMPRESA.NOMBRE_EMPRESA = DE.getNombreEmpresa();
        PARAMETROS_EMPRESA.RUTADEIMAGEN_SISTEMA_EMPRESA = DE.getRutaimagenlogo();
    }

    public void PintarImagen2(JLabel lbl, ImageIcon ruta) {
        new ImageIcon();
        this.icono = new ImageIcon(ruta.getImage().getScaledInstance(
                lbl.getWidth(),
                lbl.getHeight(),
                Image.SCALE_DEFAULT));
        lbl.setIcon(this.icono);
        this.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botonRegistrar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Eslogan = new javax.swing.JLabel();
        NombreEmpresa = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        us = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        pass = new javax.swing.JPasswordField();
        Ver = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Panel_AccesosRapidos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setIconImages(getIconImages());
        setPreferredSize(new java.awt.Dimension(600, 600));

        jPanel1.setBackground(new java.awt.Color(52, 118, 243));

        botonRegistrar.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        botonRegistrar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        botonRegistrar.setText("CONEXIONES");
        botonRegistrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        botonRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botonRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonRegistrarMouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(52, 118, 243));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        Eslogan.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        Eslogan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Eslogan.setText("BY. ALDO YAX");

        NombreEmpresa.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        NombreEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NombreEmpresa.setText("POS YG");
        NombreEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NombreEmpresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                    .addComponent(Eslogan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(NombreEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Eslogan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setText("CERRAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 102, 255));
        jButton1.setText("INICAR SESIÓN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jCheckBox1.setText("RECÚERDAME");

        us.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        us.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        us.setBorder(null);
        us.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usMouseClicked(evt);
            }
        });
        us.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usActionPerformed(evt);
            }
        });
        us.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usKeyPressed(evt);
            }
        });

        pass.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        pass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass.setBorder(null);
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });

        Ver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ojoabierto.png"))); // NOI18N
        Ver.setBorder(null);
        Ver.setContentAreaFilled(false);
        Ver.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Ver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1499345621-contact_85338.png"))); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mbrilock_99595.png"))); // NOI18N

        jScrollPane1.setBorder(null);

        Panel_AccesosRapidos.setBackground(new java.awt.Color(255, 255, 255));
        Panel_AccesosRapidos.setLayout(new java.awt.GridLayout(0, 3));
        jScrollPane1.setViewportView(Panel_AccesosRapidos);

        jLabel1.setText("ACCESOS RÁPIDOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1)
                                    .addComponent(us))
                                .addGap(44, 44, 44))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addComponent(pass, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Ver, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(us, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Ver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonRegistrarMouseClicked
        Conexion BasesdeDatos = new Conexion();
        BasesdeDatos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegistrarMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Recordar();
        Validar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usMouseClicked

    }//GEN-LAST:event_usMouseClicked

    private void VerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerActionPerformed

        if (Ver.isSelected()) {
            Image retValue = Toolkit.getDefaultToolkit().
                    getImage(ClassLoader.getSystemResource("Imagenes/ojocerrado.png"));
            ImageIcon bl = new ImageIcon(retValue);
            Ver.setIcon(bl);
            pass.setEchoChar((char) 0);
        } else {
            Image retValue2 = Toolkit.getDefaultToolkit().
                    getImage(ClassLoader.getSystemResource("Imagenes/ojoabierto.png"));
            ImageIcon bl2 = new ImageIcon(retValue2);
            Ver.setIcon(bl2);
            pass.setEchoChar('*');
        }
    }//GEN-LAST:event_VerActionPerformed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(pass.getText())) {
                Recordar();
                Validar();
            } else {
                JOptionPane.showMessageDialog(null, "¡Ingrese una contraseña válida!");
                pass.requestFocus();
            }
        }
    }//GEN-LAST:event_passKeyPressed

    private void usKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(us.getText())) {
                Recordar();
                Validar();
            } else {
                JOptionPane.showMessageDialog(null, "¡Ingrese un Usuario válido!");
                us.requestFocus();
            }
        }
    }//GEN-LAST:event_usKeyPressed

    private void usActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usActionPerformed

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

        public void Validar() {
            String Nom = us.getText();
            String con = pass.getText();
            if (us.getText().equals("") && pass.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR UN USUARIO Y UNA CONTRASEÑA!");
                us.requestFocus();
            } else if (us.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "¡POR FAVOR, INGRESA UN NOMBRE DE USUARIO!");
                us.requestFocus();
            } else if (pass.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "¡POR FAVOR, INGRESA UNA CONTRASEÑA!");
                pass.requestFocus();
            } else {
                lg = login.log(Nom, con);
                if (lg.getNombre() != null && lg.getContraseña() != null) {
                    if (lg.getEstado_Registro().equals("ACTIVO")) {
                        Principal Aldo = new Principal("g");
                        Aldo.setVisible(true);
                        executorService.execute(new Runnable() {
                            @Override
                            public void run() {
                                AV = new AVISOS("CARGANDO DATOS", "POR FAVOR, ESPERE");
                                AV.setVisible(true);

                                executorService.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        DatosEmpresaDao D_E = new DatosEmpresaDao();
                                        D_E.VerDatos();
                                        D_E.VerDatosCertificador();
                                        ObtenerToken OT = new ObtenerToken();
                                        OT.ObtenerToken();
                                        /*AL_INICIAR INICIO= new AL_INICIAR();
                        INICIO.VERIFICAR_FULLTEXT_PRODUCTOS();*/
                                        /*Promociones promo = new Promociones();
                                        promo.Logo1(false);*/
                                        AV.dispose();
                                    }
                                });

                            }
                        });
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "¡UPSS, EL USUARIO ESTÁ DESACTIVADO!", "ÉRROR AL INICAR SESIÓN", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "¡UPSS, HAS INGRESADO DATOS INCORRECTOS, POR FAVOR VERIFICA!", "DATOS INCORRECTOS", JOptionPane.WARNING_MESSAGE);
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
                java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(LOGIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>
            //</editor-fold>

            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    /*  try {
                //  UIManager.put( "TabbedPane.selectedBackground", new Color(114,190,255));
                
                UIManager.put("Button.arc", 20);
                UIManager.put("Component.arc", 50);
                UIManager.put("ProgressBar.arc", 100);
                UIManager.put("TextComponent.arc", 250 );
                UIManager.setLookAndFeel(new FlatLightLaf());
                } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
                }*/

                    /*try {
                        UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    } catch (UnsupportedLookAndFeelException ex) {
                        Logger.getLogger(LOGIN.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                    new LOGIN().setVisible(true);
                }
            });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Eslogan;
    private javax.swing.JLabel NombreEmpresa;
    private javax.swing.JPanel Panel_AccesosRapidos;
    private javax.swing.JToggleButton Ver;
    private javax.swing.JLabel botonRegistrar;
    private static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private static javax.swing.JPasswordField pass;
    private static javax.swing.JTextField us;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {/*
        private Image imagen;
        
        @Override
        public void paint(Graphics g)
        {
            imagen = new ImageIcon(getClass().getResource("/imagenes/Loginantigua.jpg")).getImage();
            
            g.drawImage(imagen,0, 0, getWidth(), getHeight(),jPanel2);
            
            setOpaque(false);
            
            super.paint(g);
        }*/
    }
    
    public void INICIAR_SESION_CON_ACCESO_RAPIDO(String ID){
        login logi= new login();
        logi = login.ABRIR_SESION_CON_ID(ID);
        us.setText(logi.getNombre());
        pass.setText(logi.getContraseña());
        Validar();
        
    }
    
    public List<String> OBTENER_REGISTROS(){
        List<String> listaRegistros = new ArrayList<>();
        properties = new Properties(); // Manejo de la excepción
        // Manejo de la excepción
        File carpeta = new File("/Sistema Punto de Venta YG/ACCESOS_RAPIDOS_USUARIOS"); // Ruta de la carpeta a recorrer
       if (carpeta.isDirectory()) {
        File[] archivos = carpeta.listFiles();

        for (File archivo : archivos) {
            if (archivo.isFile()) {
                listaRegistros.add(archivo.getName());
            }
        }
    }
        return listaRegistros;
        

    }

    public void CREAR_ACCESOS_RAPIDOS() {
        List<String> listaRegistros = login.VER_USUARIOS_ACCESOS_DIRECTOS();

        // Crear un botón para cada registro y agregarlo al panel de botones
        for (String registro : listaRegistros) {
            login l = new login(); // Crear una nueva instancia de login en cada iteración

            // Obtener los datos del registro actual
            l = login.ABRIR_SESION_CON_ID(registro);
            JButton boton = new JButton(registro);
            boton.setText("" + l.getNombre());
            Color color = Color.decode(l.getColor());
            int borderWidth = 2; // Ancho del borde
            boton.setVerticalTextPosition(JButton.BOTTOM);
            boton.setHorizontalTextPosition(JButton.CENTER);
            boton.setBorder(BorderFactory.createLineBorder(color, borderWidth));
            boton.setName("" + l.getIdlogin1());
            boton.setSize(50, 30);
            METODOS_GLOBALES.PintarImagen_BOTON(boton, CargarDatosRutas(3) + "\\" + l.getImagen());
            boton.setVisible(true);
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    INICIAR_SESION_CON_ACCESO_RAPIDO(boton.getName());
                }
            });
            Panel_AccesosRapidos.add(boton);
            Panel_AccesosRapidos.updateUI();
            l= new login();
        }
    }


}
