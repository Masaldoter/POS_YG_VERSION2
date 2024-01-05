/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista.ADMINISTRACION.PROMOCIONES;

import CLASES_GLOBALES.METODOS_GLOBALES;
import Vista.ADMINISTRACION.PROVEEDORES.*;
import static CLASES_GLOBALES.METODOS_GLOBALES.LIMPIAR_TABLA;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Controlador.Eventos;
import Controlador.PromocionesDao;
import Controlador.ProveedoresDao;
import Modelo.Promociones;
import Modelo.Proveedor;
import Tablas.ActualizarTablaProveedor;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
/*
idpromociones int AI PK 
CodigoPromocion varchar(45) 
NombrePromocion varchar(45) 
PorcentajeDescuento float 
FechaInicio date 
FechaVencimiento date 
HoraRegistro time 
UsuarioRegistro int 
UsuarioModifico int 
FechaRegistro date 
Estado
*/
/**
 *
 * @author aldoy
 */
public class PROMOCIONES extends javax.swing.JInternalFrame {

    PromocionesDao PromosDao = new PromocionesDao();
    Promociones Promos = new Promociones();

    public PROMOCIONES() {
        initComponents();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        LIMPIAR_CAJAS();
    }

    public static void ACTUALIZAR() {
        ActualizarTabla();
    }

     public void LIMPIAR_CAJAS() {
        CAJA_CODIGO_PROMOCION_VISTA.setText("");
        CAJA_NOMBRE_PROMOCION_VISTA.setText(null);
        CAJA_FECHA_INICIO_VISTA.setDate(null);
        CAJA_FECHA_VENCIMIENTO_VISTA.setDate(null);
        CAJA_PORCENTAJE_VISTA.setText(null);
        CAJA_ID_VISTA.setText(null);
        filtro("", TABLA_PROMOCIONES_VISTA, 1);
        CAJA_CODIGO_PROMOCION_VISTA.requestFocus();
        BTN_AGREGAR_VISTA.setVisible(true);
        BTN_EDITAR_VISTA.setVisible(false);
    }

    private void filtro(String consulta, JTable jtableBuscar, int TipoBusqueda) {
        DefaultTableModel dm;
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + consulta, TipoBusqueda));
    }

    public void AGREGAR() 
    {
        if (CAJA_CODIGO_PROMOCION_VISTA.getText().equals("") && CAJA_NOMBRE_PROMOCION_VISTA.getText().equals("") && CAJA_PORCENTAJE_VISTA.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "¡TODOS LOS CAMPOS SON NECESARIOS");
        }else{
             for (int i = 0; i < TABLA_PROMOCIONES_VISTA.getRowCount(); i++) {
                    if (TABLA_PROMOCIONES_VISTA.getValueAt(i, 1).equals(CAJA_CODIGO_PROMOCION_VISTA.getText())) {
                        JOptionPane.showMessageDialog(null, "¡EL NOMBRE DE EMPRESA YA ESTÁ REGISTRADA!");
                        return;
                  }
            }
            Promos.setCodigoPromocion(CAJA_CODIGO_PROMOCION_VISTA.getText());
            Promos.setNombrePromocion(CAJA_NOMBRE_PROMOCION_VISTA.getText());
            Promos.setPorcentajeDescuento(Float.parseFloat(CAJA_PORCENTAJE_VISTA.getText()));
            Promos.setFechaInicio(METODOS_GLOBALES.CONVERTIR_FECHA_DATE_STRING(CAJA_FECHA_INICIO_VISTA.getDate()));
            Promos.setFechaVencimiento(METODOS_GLOBALES.CONVERTIR_FECHA_DATE_STRING(CAJA_FECHA_VENCIMIENTO_VISTA.getDate()));
            Promos.setFechaRegistro(METODOS_GLOBALES.Fecha());
            Promos.setHoraRegistro(METODOS_GLOBALES.Hora());
            Promos.setUsuarioRegistro(PARAMETROS_USUARIOS.ID_USUARIO);
            Promos.setUsuarioModifico(PARAMETROS_USUARIOS.ID_USUARIO);
            Promos.setEstado(CAJA_ESTADO_VISTA.getSelectedItem().toString());
            PromosDao.AGREGAR(Promos);
            LIMPIAR_CAJAS();
        }

    }

    public void EDITAR() {
        if (CAJA_CODIGO_PROMOCION_VISTA.getText().equals("") && CAJA_NOMBRE_PROMOCION_VISTA.getText().equals("") && CAJA_PORCENTAJE_VISTA.getText().equals("")) {
        JOptionPane.showMessageDialog(null, "¡TODOS LOS CAMPOS SON NECESARIOS!");
    } else {
        // Obtén el ID de la promoción que deseas editar (puedes obtenerlo de alguna manera, por ejemplo, desde una fila seleccionada en la tabla)
        int idPromocionAEditar = Integer.parseInt(CAJA_ID_VISTA.getText()); // Implementa este método según tu lógica

        if (idPromocionAEditar == -1) {
            JOptionPane.showMessageDialog(null, "¡SELECCIONA UNA PROMOCIÓN PARA EDITAR!");
            return;
        }
        Promos.setIdpromociones(idPromocionAEditar);
        Promos.setCodigoPromocion(CAJA_CODIGO_PROMOCION_VISTA.getText());
        Promos.setNombrePromocion(CAJA_NOMBRE_PROMOCION_VISTA.getText());
        Promos.setPorcentajeDescuento(Float.parseFloat(CAJA_PORCENTAJE_VISTA.getText()));
        Promos.setFechaInicio(METODOS_GLOBALES.CONVERTIR_FECHA_DATE_STRING(CAJA_FECHA_INICIO_VISTA.getDate()));
        Promos.setFechaVencimiento(METODOS_GLOBALES.CONVERTIR_FECHA_DATE_STRING(CAJA_FECHA_VENCIMIENTO_VISTA.getDate()));
        Promos.setFechaRegistro(METODOS_GLOBALES.Fecha());
        Promos.setHoraRegistro(METODOS_GLOBALES.Hora());
        Promos.setUsuarioRegistro(PARAMETROS_USUARIOS.ID_USUARIO);
        Promos.setUsuarioModifico(PARAMETROS_USUARIOS.ID_USUARIO);
        Promos.setEstado(CAJA_ESTADO_VISTA.getSelectedItem().toString());
        PromosDao.EDITAR(Promos, Integer.parseInt(CAJA_ID_VISTA.getText()));
        LIMPIAR_CAJAS();
    }
    }


    public static void ActualizarTabla() {
        LIMPIAR_TABLA(TABLA_PROMOCIONES_VISTA);
    DefaultTableModel modelo = new DefaultTableModel();
    modelo = (DefaultTableModel) TABLA_PROMOCIONES_VISTA.getModel();
    
    // Llamar al método LISTAR para obtener la lista actualizada de promociones
    PromocionesDao PromosDao = new PromocionesDao();
    List<Promociones> listarPromociones = PromosDao.LISTAR();
    
    Object[] ob = new Object[7]; // Ajusta el número de columnas según tu tabla

    for (int i = 0; i < listarPromociones.size(); i++) {
        ob[0] = listarPromociones.get(i).getIdpromociones();
        ob[1] = listarPromociones.get(i).getCodigoPromocion();
        ob[2] = listarPromociones.get(i).getNombrePromocion();
        ob[3] = listarPromociones.get(i).getPorcentajeDescuento();
        ob[4] = listarPromociones.get(i).getFechaInicio();
        ob[5] = listarPromociones.get(i).getFechaVencimiento();
        ob[6] = listarPromociones.get(i).getEstado();

        modelo.addRow(ob);
    }

    TABLA_PROMOCIONES_VISTA.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Proveedores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLA_PROMOCIONES_VISTA = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        BTN_AGREGAR_VISTA = new javax.swing.JButton();
        BTN_EDITAR_VISTA = new javax.swing.JButton();
        BTN_NUEVO_VISTA = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        CAJA_NOMBRE_PROMOCION_VISTA = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        CAJA_CODIGO_PROMOCION_VISTA = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        CAJA_ESTADO_VISTA = new javax.swing.JComboBox<>();
        CAJA_FECHA_INICIO_VISTA = new com.toedter.calendar.JDateChooser();
        CAJA_PORCENTAJE_VISTA = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        CAJA_FECHA_VENCIMIENTO_VISTA = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        CAJA_ID_VISTA = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setBorder(null);

        TABLA_PROMOCIONES_VISTA  = new javax.swing.JTable(){
            public boolean isCellEditable(int row,int column){
                for(int i = 0; i < TABLA_PROMOCIONES_VISTA.getRowCount(); i ++){
                    if(row < 0){
                        return true;
                    }
                }
                return false;
            }
        };
        TABLA_PROMOCIONES_VISTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TABLA_PROMOCIONES_VISTA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CODIGO", "NOMBRE", "PORCENTAJE", "FECHA INICIO-FINAL", "FECHA FINAL", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TABLA_PROMOCIONES_VISTA.setRowHeight(30);
        TABLA_PROMOCIONES_VISTA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TABLA_PROMOCIONES_VISTAMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TABLA_PROMOCIONES_VISTA);
        if (TABLA_PROMOCIONES_VISTA.getColumnModel().getColumnCount() > 0) {
            TABLA_PROMOCIONES_VISTA.getColumnModel().getColumn(0).setPreferredWidth(5);
            TABLA_PROMOCIONES_VISTA.getColumnModel().getColumn(4).setPreferredWidth(100);
            TABLA_PROMOCIONES_VISTA.getColumnModel().getColumn(6).setPreferredWidth(8);
        }

        jPanel37.setBackground(new java.awt.Color(51, 153, 255));

        BTN_AGREGAR_VISTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        BTN_AGREGAR_VISTA.setText("AGREGAR");
        BTN_AGREGAR_VISTA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_AGREGAR_VISTA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTN_AGREGAR_VISTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_AGREGAR_VISTAActionPerformed(evt);
            }
        });

        BTN_EDITAR_VISTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar.png"))); // NOI18N
        BTN_EDITAR_VISTA.setText("EDITAR");
        BTN_EDITAR_VISTA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_EDITAR_VISTA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTN_EDITAR_VISTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EDITAR_VISTAActionPerformed(evt);
            }
        });

        BTN_NUEVO_VISTA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconosSOciales/NUEVO_32PX.png"))); // NOI18N
        BTN_NUEVO_VISTA.setText("NUEVO");
        BTN_NUEVO_VISTA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BTN_NUEVO_VISTA.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BTN_NUEVO_VISTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NUEVO_VISTAActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("ESTADO:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("FECHA DE INICIO-VENCIMIENTO");

        CAJA_NOMBRE_PROMOCION_VISTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("NOMBRE DE LA PROMOCIÓN");

        CAJA_CODIGO_PROMOCION_VISTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CAJA_CODIGO_PROMOCION_VISTA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CAJA_CODIGO_PROMOCION_VISTAKeyReleased(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("CÓDIGO DE PROMOCIÓN");

        CAJA_ESTADO_VISTA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        CAJA_PORCENTAJE_VISTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CAJA_PORCENTAJE_VISTA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CAJA_PORCENTAJE_VISTAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CAJA_PORCENTAJE_VISTAKeyTyped(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("PORCENTAJE DE DESCUENTO");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("%");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel37Layout.createSequentialGroup()
                                .addComponent(CAJA_PORCENTAJE_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(CAJA_CODIGO_PROMOCION_VISTA, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CAJA_NOMBRE_PROMOCION_VISTA, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel37Layout.createSequentialGroup()
                                .addComponent(BTN_AGREGAR_VISTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BTN_EDITAR_VISTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(BTN_NUEVO_VISTA, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel37Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CAJA_ESTADO_VISTA, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel37Layout.createSequentialGroup()
                                .addComponent(CAJA_FECHA_INICIO_VISTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CAJA_FECHA_VENCIMIENTO_VISTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(12, 12, 12))))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CAJA_CODIGO_PROMOCION_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CAJA_NOMBRE_PROMOCION_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CAJA_PORCENTAJE_VISTA)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CAJA_FECHA_INICIO_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CAJA_FECHA_VENCIMIENTO_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CAJA_ESTADO_VISTA)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_AGREGAR_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTN_EDITAR_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BTN_NUEVO_VISTA)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel1.setText("SELECCIONE PARA VER DETALLES");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("ID");

        CAJA_ID_VISTA.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jButton1.setText("ACTUALIZAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ProveedoresLayout = new javax.swing.GroupLayout(Proveedores);
        Proveedores.setLayout(ProveedoresLayout);
        ProveedoresLayout.setHorizontalGroup(
            ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProveedoresLayout.createSequentialGroup()
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ProveedoresLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CAJA_ID_VISTA)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE))
                .addContainerGap())
        );
        ProveedoresLayout.setVerticalGroup(
            ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ProveedoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(ProveedoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CAJA_ID_VISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Proveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TABLA_PROMOCIONES_VISTAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TABLA_PROMOCIONES_VISTAMouseClicked
        int fila = TABLA_PROMOCIONES_VISTA.getSelectedRow();
        CAJA_ID_VISTA.setText(TABLA_PROMOCIONES_VISTA.getValueAt(fila, 0).toString());
        CAJA_CODIGO_PROMOCION_VISTA.setText(TABLA_PROMOCIONES_VISTA.getValueAt(fila, 1).toString());
        CAJA_NOMBRE_PROMOCION_VISTA.setText(TABLA_PROMOCIONES_VISTA.getValueAt(fila, 2).toString());
        CAJA_PORCENTAJE_VISTA.setText(TABLA_PROMOCIONES_VISTA.getValueAt(fila, 3).toString());
        CAJA_FECHA_INICIO_VISTA.setDate(METODOS_GLOBALES.CONVERTIR_FECHA_STRING_A_DATE(TABLA_PROMOCIONES_VISTA.getValueAt(fila, 4).toString()));
        CAJA_FECHA_VENCIMIENTO_VISTA.setDate(METODOS_GLOBALES.CONVERTIR_FECHA_STRING_A_DATE(TABLA_PROMOCIONES_VISTA.getValueAt(fila, 5).toString()));
        CAJA_ESTADO_VISTA.setSelectedItem(TABLA_PROMOCIONES_VISTA.getValueAt(fila, 6).toString());
        BTN_AGREGAR_VISTA.setVisible(false);
        BTN_EDITAR_VISTA.setVisible(true);
    }//GEN-LAST:event_TABLA_PROMOCIONES_VISTAMouseClicked

    private void BTN_AGREGAR_VISTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_AGREGAR_VISTAActionPerformed
        AGREGAR();
        LIMPIAR_CAJAS();
        ActualizarTabla();
    }//GEN-LAST:event_BTN_AGREGAR_VISTAActionPerformed

    private void BTN_EDITAR_VISTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EDITAR_VISTAActionPerformed
        EDITAR();
        LIMPIAR_CAJAS();
        ActualizarTabla();
    }//GEN-LAST:event_BTN_EDITAR_VISTAActionPerformed

    private void BTN_NUEVO_VISTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NUEVO_VISTAActionPerformed
        LIMPIAR_CAJAS();
        CAJA_ID_VISTA.setText(null);
    }//GEN-LAST:event_BTN_NUEVO_VISTAActionPerformed

    private void CAJA_CODIGO_PROMOCION_VISTAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CAJA_CODIGO_PROMOCION_VISTAKeyReleased
        if(CAJA_CODIGO_PROMOCION_VISTA.getText().equals("")){
            filtro("", TABLA_PROMOCIONES_VISTA, 1);
        }else{
            filtro(CAJA_CODIGO_PROMOCION_VISTA.getText(), TABLA_PROMOCIONES_VISTA, 1);
        }
    }//GEN-LAST:event_CAJA_CODIGO_PROMOCION_VISTAKeyReleased

    private void CAJA_PORCENTAJE_VISTAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CAJA_PORCENTAJE_VISTAKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_CAJA_PORCENTAJE_VISTAKeyReleased

    private void CAJA_PORCENTAJE_VISTAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CAJA_PORCENTAJE_VISTAKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, CAJA_PORCENTAJE_VISTA);
    }//GEN-LAST:event_CAJA_PORCENTAJE_VISTAKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ActualizarTabla();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_AGREGAR_VISTA;
    private javax.swing.JButton BTN_EDITAR_VISTA;
    private javax.swing.JButton BTN_NUEVO_VISTA;
    private javax.swing.JTextField CAJA_CODIGO_PROMOCION_VISTA;
    private javax.swing.JComboBox<String> CAJA_ESTADO_VISTA;
    private com.toedter.calendar.JDateChooser CAJA_FECHA_INICIO_VISTA;
    private com.toedter.calendar.JDateChooser CAJA_FECHA_VENCIMIENTO_VISTA;
    private javax.swing.JTextField CAJA_ID_VISTA;
    private javax.swing.JTextField CAJA_NOMBRE_PROMOCION_VISTA;
    private javax.swing.JTextField CAJA_PORCENTAJE_VISTA;
    private javax.swing.JPanel Proveedores;
    private static javax.swing.JTable TABLA_PROMOCIONES_VISTA;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
