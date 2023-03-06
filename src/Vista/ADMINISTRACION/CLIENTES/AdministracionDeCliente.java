
package Vista.ADMINISTRACION.CLIENTES;

import Controlador.ClientesDao;
import Controlador.Eventos;
import Modelo.Clientes;
import Vista.Principal;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
/**
 *
 * @author Masaldoter
 */
public final class AdministracionDeCliente extends javax.swing.JFrame {
    Principal principal;
    Clientes cli;
    ClientesDao cliDao;    
    
    public AdministracionDeCliente() {
    }
    
    public AdministracionDeCliente(int FormaInicio, int ValorBusqueda, Principal principal) {
        initComponents();
        this.principal = principal;
        if(FormaInicio==0){
          CargarPaises();  
            LimpiarCajasClientes();
        }else{
            CargarPaises();  
            BuscarCliente(ValorBusqueda);
        }
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
                principal.ContabilizadorDeVentanasCliente =0;
                this.dispose();
    }

    public void LimpiarCajasClientes() {
        CajaNombre.setText(null);
        CajaNit.setText(null);
        CajaDireccion.setText(null);
        CajaId.setText(null);
        CajaMunicipio.setText(null);
        CajaDepartamento.setText(null);
        CajaCodigoPostal.setText(null);
        CajaCorreo.setText(null);
        CajaTelefono.setText(null);
        CajaId.setText(null);
        BtnEliminar.setEnabled(false);
        BtnEditar.setEnabled(false);
        BtnAgregarCliente.setEnabled(true);
        ComboPais.setSelectedItem("GUATEMALA");
    }
    
    public void BuscarCliente(int ID){
        cli= new Clientes();
        cliDao = new ClientesDao();   
        cli.setIdclientes(ID);
        cliDao.BuscarClienteTabla(cli);
        
        BtnEliminar.setEnabled(true);
        BtnEditar.setEnabled(true);
        BtnAgregarCliente.setEnabled(false);  
        
        CajaNombre.setText(cli.getNombre());
        CajaNit.setText(cli.getIDENTIFICACION());
        Combo_TIPO_IDENTIFICACION.setSelectedItem(cli.getTIPO_IDENTIFICACION());
        CajaDireccion.setText(cli.getDireccion());
        CajaMunicipio.setText(cli.getMunicipio());
        CajaDepartamento.setText(cli.getDepartamento());
        ComboPais.setSelectedItem(cli.getPais());
        CajaCodigoPostal.setText(cli.getCodigoPostal());
        CajaId.setText(String.valueOf(cli.getIdclientes()));
        CajaTelefono.setText(cli.getTelefono());
        CajaCorreo.setText(cli.getCorreo());
    }
    
    public void IngresoClientes(String Nombre, String IDENTIFICACION, String TIPO_IDENTIFICACION, String Direccion, String Municipio, String Departamento, String Pais, String CodigoPostal, String Tel, String Correo) {
        cli= new Clientes();
        cliDao = new ClientesDao();    
        if (Nombre.equals("")) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                      DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "¡EL NOMBRE DEL CLIENTE NO PUEDE IR VACÍO!", DesktopNotify.ERROR, 14000L);
        } else if (IDENTIFICACION.equals("")) {
            JOptionPane.showMessageDialog(null, "¡EL NIT DEL CLIENTE NO PUEDE IR VACÍO!");
        } else if (Direccion.equals("")) {
            JOptionPane.showMessageDialog(null, "¡LA DIRECCIÓN DEL CLIENTE NO PUEDE IR VACÍA!");
        } else {
                String Resultado =cliDao.RetornarNit(IDENTIFICACION);
                  if(Resultado == null){
                      cli.setNombre(Nombre);
                    cli.setIDENTIFICACION(IDENTIFICACION);
                    cli.setTIPO_IDENTIFICACION(TIPO_IDENTIFICACION);
                    cli.setDireccion(Direccion);
                    cli.setCodigoPostal(CodigoPostal);
                    cli.setMunicipio(Municipio);
                    cli.setDepartamento(Departamento);
                    cli.setPais(Pais);
                    cli.setTelefono(Tel);
                    cli.setCorreo(Correo);
                    cliDao.IngresoCliente(cli);
                  }else{
                        DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                      DesktopNotify.showDesktopMessage("REGISTRO TRUNCADO", "EL CLIENTE YA ESTÁ REGISTRADO", DesktopNotify.ERROR, 14000L);
                   }     
        }

    }
    
    public void EliminarCliente(String IDENTIFICACION) {
        ClientesDao clDao= new ClientesDao();
        Clientes cl= new Clientes();
        int i = JOptionPane.showConfirmDialog(null, "¿ESTÁ SEGURO DE ELIMINAR EL CLIENTE CON NIT: "+IDENTIFICACION+"?");

        if (i == 0) {
            cl.setIDENTIFICACION(IDENTIFICACION);
            cl.setIdclientes(Integer.parseInt(CajaId.getText()));
            clDao.EliminarCliente(cl);
            principal.C.ACTUALIZAR_CLIENTES();
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel57 = new javax.swing.JPanel();
        CajaNombre = new javax.swing.JTextField();
        CajaNit = new javax.swing.JTextField();
        CajaDireccion = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        CajaMunicipio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CajaDepartamento = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CajaCodigoPostal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ComboPais = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        CajaCorreo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        CajaTelefono = new javax.swing.JTextField();
        Combo_TIPO_IDENTIFICACION = new javax.swing.JComboBox<>();
        jPanel56 = new javax.swing.JPanel();
        BtnEliminar = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        BtnEditar = new javax.swing.JButton();
        BtnAgregarCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        CajaId = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ADMINISTRACIÓN DE CLIENTE");

        jPanel57.setBackground(new java.awt.Color(153, 204, 255));

        CajaNombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CajaNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaNombreKeyReleased(evt);
            }
        });

        CajaNit.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CajaNit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CajaNitKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaNitKeyReleased(evt);
            }
        });

        CajaDireccion.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel87.setText("NOMBRE DEL CLIENTE:");

        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel88.setText("TIPO:");

        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel89.setText("DIRECCIÓN DEL CLIENTE:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("MUNICIPIO:");

        CajaMunicipio.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CajaMunicipio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaMunicipioKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("DEPARTAMENTO:");

        CajaDepartamento.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CajaDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaDepartamentoKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CÓDIGO POSTAL:");

        CajaCodigoPostal.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CajaCodigoPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CajaCodigoPostalKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("PAÍS:");

        ComboPais.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("CORREO:");

        CajaCorreo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CajaCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CajaCorreoKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("TELÉFONO:");

        CajaTelefono.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        CajaTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CajaTelefonoKeyTyped(evt);
            }
        });

        Combo_TIPO_IDENTIFICACION.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NIT", "CUI" }));

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CajaNombre)
                    .addComponent(CajaNit)
                    .addComponent(CajaDireccion)
                    .addComponent(jLabel89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaMunicipio)
                    .addComponent(ComboPais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaCodigoPostal)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CajaDepartamento)
                    .addComponent(CajaCorreo)
                    .addComponent(CajaTelefono)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addComponent(jLabel88)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Combo_TIPO_IDENTIFICACION, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel88, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(Combo_TIPO_IDENTIFICACION))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaNit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaDepartamento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ComboPais, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaCodigoPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel56.setBackground(new java.awt.Color(255, 204, 153));

        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        BtnEliminar.setText("ELIMINAR");
        BtnEliminar.setToolTipText("");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        jButton22.setText("NUEVO");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        BtnEditar.setText("EDITAR");
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/clientes.png"))); // NOI18N
        BtnAgregarCliente.setText("AGREGAR");
        BtnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarClienteActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID:");

        CajaId.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregarCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(CajaId))
                .addContainerGap())
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CajaId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CajaNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaNombreKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CajaNombreKeyReleased

    private void CajaNitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaNitKeyPressed
        cliDao = new ClientesDao();
        cli = new Clientes();

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (CajaNit.getText() == null) {
                JOptionPane.showMessageDialog(null, "¡DEBE INGRESAR UN NÚMERO DE NIT!");
            } else {
                cli = cliDao.BuscarClie(CajaNit.getText());
                Boolean Resultado= cli.getResutaldoConsulta();
                if (Resultado==true) {
                    CajaNombre.setText(cli.getNombre());
                    CajaNit.setText(cli.getIDENTIFICACION());
                    Combo_TIPO_IDENTIFICACION.setSelectedItem(cli.getTIPO_IDENTIFICACION());
                    CajaDireccion.setText(cli.getDireccion());
                    CajaId.setText(String.valueOf(cli.getIdclientes()));
                    CajaMunicipio.setText(cli.getMunicipio());
                    CajaMunicipio.setText(cli.getMunicipio());
                    CajaDepartamento.setText(cli.getDepartamento());
                    ComboPais.setSelectedItem(cli.getPais().toString());
                    CajaCodigoPostal.setText(cli.getCodigoPostal());
                    CajaTelefono.setText(cli.getTelefono());
                    CajaCorreo.setText(cli.getCorreo());
                    
                    BtnEliminar.setEnabled(false);
                    BtnEditar.setEnabled(false);
                    BtnAgregarCliente.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "¡NO HAY REGISTROS DE CLIENTES CON EL NIT: " + CajaNit.getText() + "!");
                }
            }
        }
    }//GEN-LAST:event_CajaNitKeyPressed

    private void CajaNitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaNitKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CajaNitKeyReleased

    private void CajaMunicipioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaMunicipioKeyReleased
        CajaMunicipio.setText(CajaMunicipio.getText().toUpperCase());
    }//GEN-LAST:event_CajaMunicipioKeyReleased

    private void CajaDepartamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaDepartamentoKeyReleased
        CajaDepartamento.setText(CajaDepartamento.getText().toUpperCase());
    }//GEN-LAST:event_CajaDepartamentoKeyReleased

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        EliminarCliente(CajaId.getText());
        ConfirmarSalida();
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        CargarPaises();  
        LimpiarCajasClientes();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed
        cli= new Clientes();
        cliDao = new ClientesDao();  
        int i= JOptionPane.showConfirmDialog(null, "¿Seguro que desea editar el cliente?");
        if(i==0){

            if(CajaNombre.getText().equals("")){
                //  JOptionPane.showMessageDialog(null, "¡El Nombre del Cliente no puede ir vacío!");
            }else if(CajaNit.getText().equals("")){
                // JOptionPane.showMessageDialog(null, "¡El Nit del Cliente no puede ir vacío!");
            }else if(CajaDireccion.getText().equals("")){
                // JOptionPane.showMessageDialog(null, "¡La Dirección del Cliente no puede ir vacío!");
            }
            else{
                cli.setNombre(CajaNombre.getText());
                cli.setIDENTIFICACION(CajaNit.getText());
                cli.setTIPO_IDENTIFICACION(String.valueOf(Combo_TIPO_IDENTIFICACION.getSelectedItem()));
                cli.setDireccion(CajaDireccion.getText());
                cli.setIdclientes(Integer.parseInt(CajaId.getText()));
                cli.setCodigoPostal(CajaCodigoPostal.getText());
                cli.setMunicipio(CajaMunicipio.getText());
                cli.setDepartamento(CajaDepartamento.getText());
                cli.setPais(ComboPais.getSelectedItem().toString());
                cli.setTelefono(CajaTelefono.getText());
                cli.setCorreo(CajaCorreo.getText());
                cliDao.EditarCliente(cli);
                principal.C.ACTUALIZAR_CLIENTES();
                ConfirmarSalida();
            }

        }
    }//GEN-LAST:event_BtnEditarActionPerformed

    private void BtnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarClienteActionPerformed
        IngresoClientes(CajaNombre.getText(), CajaNit.getText(), String.valueOf(Combo_TIPO_IDENTIFICACION.getSelectedItem()), CajaDireccion.getText(), CajaMunicipio.getText(), CajaDepartamento.getText(), String.valueOf(ComboPais.getSelectedItem()), CajaCodigoPostal.getText(), CajaTelefono.getText(), CajaCorreo.getText());
        // tablasclientes.ActualizarTablaCliente(TablaClientes1);
        LimpiarCajasClientes();
    }//GEN-LAST:event_BtnAgregarClienteActionPerformed

    private void CajaCodigoPostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaCodigoPostalKeyTyped
        Eventos event = new Eventos();
        event.numberKeyPress(evt);
    }//GEN-LAST:event_CajaCodigoPostalKeyTyped

    private void CajaTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaTelefonoKeyTyped
        Eventos event = new Eventos();
        event.numberKeyPress(evt);
    }//GEN-LAST:event_CajaTelefonoKeyTyped

    private void CajaCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CajaCorreoKeyReleased
        CajaDepartamento.setText(CajaDepartamento.getText().toLowerCase());
    }//GEN-LAST:event_CajaCorreoKeyReleased

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
            java.util.logging.Logger.getLogger(AdministracionDeCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdministracionDeCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdministracionDeCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdministracionDeCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdministracionDeCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregarCliente;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    public javax.swing.JTextField CajaCodigoPostal;
    public javax.swing.JTextField CajaCorreo;
    public javax.swing.JTextField CajaDepartamento;
    public javax.swing.JTextField CajaDireccion;
    public static javax.swing.JTextField CajaId;
    public javax.swing.JTextField CajaMunicipio;
    public javax.swing.JTextField CajaNit;
    public javax.swing.JTextField CajaNombre;
    public javax.swing.JTextField CajaTelefono;
    public javax.swing.JComboBox<String> ComboPais;
    private javax.swing.JComboBox<String> Combo_TIPO_IDENTIFICACION;
    private javax.swing.JButton jButton22;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    // End of variables declaration//GEN-END:variables

    public void CargarPaises(){
        cliDao = new ClientesDao();
        ComboPais.removeAll();
        cliDao.Pais(ComboPais);
        
    }
    
}
