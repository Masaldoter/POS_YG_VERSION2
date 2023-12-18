/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.POS;

import Controlador.Eventos;
import Controlador.FullSelectorListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class FormaDePago extends javax.swing.JFrame {
        Float IVA;
        
    POS pos;
    
    public FormaDePago() {
    }
    
    public FormaDePago(POS pos) {
        initComponents();
        this.setLocationRelativeTo(null);
        Cerrar();
        this.pos = pos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        EfectivoPagado = new javax.swing.JTextField();
        TarjetaPagado = new javax.swing.JTextField();
        DepositoPagado = new javax.swing.JTextField();
        ChequePagado = new javax.swing.JTextField();
        SeleccionNombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        NTransacciones = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        SeleccionId = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        TotalPagado = new javax.swing.JLabel();
        labeltotal = new javax.swing.JLabel();
        cambio = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        TotalIva = new javax.swing.JLabel();
        SubTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MÉTODOS DE PAGO");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(797, 495));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "FORMA DE PAGO"));
        jPanel1.setInheritsPopupMenu(true);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesFormaDePago/1495815224-jd15_84582.png"))); // NOI18N
        jLabel1.setText("TARJETA");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesFormaDePago/cash_40532.png"))); // NOI18N
        jLabel2.setText("EFECTIVO");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesFormaDePago/payment_method_cash_credit_card_money_finance_icon_153122.png"))); // NOI18N
        jLabel3.setText("COMPARTIDO");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesFormaDePago/mobile_transfer_icon_194096.png"))); // NOI18N
        jLabel4.setText("DEPÓSITO O TRANFERENCIA");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagenesFormaDePago/1486564168-finance-bank-check_81495.png"))); // NOI18N
        jLabel5.setText("CHEQUE");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        EfectivoPagado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        EfectivoPagado.setText("0.00");
        EfectivoPagado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EfectivoPagadoMouseClicked(evt);
            }
        });
        EfectivoPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EfectivoPagadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EfectivoPagadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EfectivoPagadoKeyTyped(evt);
            }
        });

        TarjetaPagado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TarjetaPagado.setText("0.00");
        TarjetaPagado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TarjetaPagadoMouseClicked(evt);
            }
        });
        TarjetaPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TarjetaPagadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TarjetaPagadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TarjetaPagadoKeyTyped(evt);
            }
        });

        DepositoPagado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        DepositoPagado.setText("0.00");
        DepositoPagado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DepositoPagadoMouseClicked(evt);
            }
        });
        DepositoPagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DepositoPagadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                DepositoPagadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DepositoPagadoKeyTyped(evt);
            }
        });

        ChequePagado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ChequePagado.setText("0.00");
        ChequePagado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChequePagadoMouseClicked(evt);
            }
        });
        ChequePagado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ChequePagadoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ChequePagadoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ChequePagadoKeyTyped(evt);
            }
        });

        SeleccionNombre.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SELECCIÓN"));

        NTransacciones.setColumns(20);
        NTransacciones.setRows(5);
        jScrollPane1.setViewportView(NTransacciones);

        jLabel9.setText("N° DE TRANSACCIÓNES");

        SeleccionId.setText("1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(EfectivoPagado, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TarjetaPagado, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DepositoPagado, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChequePagado, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeleccionNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SeleccionId))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SeleccionId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TarjetaPagado)
                    .addComponent(EfectivoPagado)
                    .addComponent(DepositoPagado)
                    .addComponent(ChequePagado)
                    .addComponent(SeleccionNombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addContainerGap())
        );

        TotalPagado.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TotalPagado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalPagado.setText("0.00");
        TotalPagado.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL PAGADO"));

        labeltotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labeltotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labeltotal.setText("500");
        labeltotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL A PAGAR"));

        cambio.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        cambio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cambio.setText("0.00");
        cambio.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CAMBIO EN EFECTIVO:"));

        jButton1.setBackground(new java.awt.Color(240, 140, 90));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LISTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TotalIva.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        TotalIva.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TotalIva.setText("0.00");
        TotalIva.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TOTAL DE IVA"));

        SubTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        SubTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SubTotal.setText("0.00");
        SubTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUBTOTAL"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TotalIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalPagado, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labeltotal, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cambio, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(TotalPagado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(labeltotal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cambio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    @Override
        public Image getIconImage() {
            Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("IconosSOciales/chart_sales_performance_coins_money_icon_157294.png"));

            return retValue;
        }
        
        public final void Cerrar() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    NingunCampoNulo();
                }
            });
            this.setVisible(true);

        } catch (Exception e) {
        }
    }
        
    public void RELLENAR_PARAMETROS_FORMA_DE_PAGO(String Total, String Pago, String Cambio, String Efectivo, String Tarjeta, String Transferencia, String Cheque, 
            String NumeroTransacciones, String Metodo, int MetodoPago, String SubTotalIvaAPagar, String Iva, String SubTotal){
        
        Seleccion(MetodoPago);
        Seleccion2(MetodoPago);
        labeltotal.setText(Total);
        TotalPagado.setText(Pago);
        cambio.setText(Cambio);
        NTransacciones.setText(NumeroTransacciones);
        
        EfectivoPagado.setText(Efectivo);
        TarjetaPagado.setText(Tarjeta);
        DepositoPagado.setText(Transferencia);
        ChequePagado.setText(Cheque);
        Float PagoCliente= Float.parseFloat(TotalPagado.getText());
        Float TotalPago= Float.parseFloat(labeltotal.getText());
        TotalIva.setText(SubTotalIvaAPagar);
        IVA = Float.parseFloat(Iva);
        this.SubTotal.setText(SubTotal);
        ValidacionDePago(Double.parseDouble(Pago));
        TotalIva.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "TOTAL DE IVA ("+Iva+"%)", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255,0,102)));
        CalcularIva();
        labeltotal.setBackground(Color.red);
          if(PagoCliente >= TotalPago){
            cambio.setBackground(Color.GREEN);
            TotalPagado.setBackground(Color.GREEN);
            jButton1.setBackground(Color.GREEN);
        }else{
            cambio.setBackground(Color.RED);
            TotalPagado.setBackground(Color.RED);
            jButton1.setBackground(Color.red);
        }
    }
        
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
        Seleccion(1);
        EfectivoPagado.addFocusListener(new FullSelectorListener());
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Seleccion(2);
        TarjetaPagado.addFocusListener(new FullSelectorListener());
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Seleccion(3);
        DepositoPagado.addFocusListener(new FullSelectorListener());
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        Seleccion(4);
        ChequePagado.addFocusListener(new FullSelectorListener());
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Seleccion(5);
    }//GEN-LAST:event_jLabel3MouseClicked
    public void Seleccion(int Seleccion){
        switch (Seleccion) {

            case 1:
                jLabel1.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel4.setBorder(null);
                jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(true);
                TarjetaPagado.setEnabled(false);
                DepositoPagado.setEnabled(false);
                ChequePagado.setEnabled(false);
                SeleccionNombre.setText("EFECTIVO");
                SeleccionId.setText("1");
                NTransacciones.setText(null);
                NTransacciones.setVisible(false);
                jLabel9.setVisible(false);

                EfectivoPagado.setText("0.00");
                TarjetaPagado.setText("0.00");
                DepositoPagado.setText("0.00");
                ChequePagado.setText("0.00");

                TotalPagado.setText("0.00");
                cambio.setText("0.00");
                EfectivoPagado.requestFocus();
                EfectivoPagado.addFocusListener(new FullSelectorListener());
                break;

            case 2:
                jLabel2.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel4.setBorder(null);
                jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(false);
                TarjetaPagado.setEnabled(true);
                DepositoPagado.setEnabled(false);
                ChequePagado.setEnabled(false);
                SeleccionNombre.setText("TARJETA");
                SeleccionId.setText("2");

                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);
                NTransacciones.setText("N° TARJETA: ");
                EfectivoPagado.setText("0.00");
                TarjetaPagado.setText("0.00");
                DepositoPagado.setText("0.00");
                ChequePagado.setText("0.00");

                TotalPagado.setText("0.00");
                cambio.setText("0.00");
                TarjetaPagado.requestFocus();
                TarjetaPagado.addFocusListener(new FullSelectorListener());
                break;

            case 3:
                jLabel2.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel1.setBorder(null);
                jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(false);
                TarjetaPagado.setEnabled(false);
                DepositoPagado.setEnabled(true);
                ChequePagado.setEnabled(false);
                SeleccionNombre.setText("DEPÓSITO O TRANSFERENCIA");
                SeleccionId.setText("3");
                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);
                NTransacciones.setText("N° DEPÓSITO: ");
                EfectivoPagado.setText("0.00");
                TarjetaPagado.setText("0.00");
                DepositoPagado.setText("0.00");
                ChequePagado.setText("0.00");

                TotalPagado.setText("0.00");
                cambio.setText("0.00");
                DepositoPagado.requestFocus();
                DepositoPagado.addFocusListener(new FullSelectorListener());
                break;

            case 4:
                jLabel2.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel4.setBorder(null);
                jLabel1.setBorder(null);
                jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));

                EfectivoPagado.setEnabled(false);
                TarjetaPagado.setEnabled(false);
                DepositoPagado.setEnabled(false);
                ChequePagado.setEnabled(true);
                SeleccionNombre.setText("CHEQUE");
                SeleccionId.setText("4");

                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);
                NTransacciones.setText("CHEQUE:");

                EfectivoPagado.setText("0.00");
                TarjetaPagado.setText("0.00");
                DepositoPagado.setText("0.00");
                ChequePagado.setText("0.00");

                TotalPagado.setText("0.00");
                cambio.setText("0.00");
                ChequePagado.requestFocus();
                ChequePagado.addFocusListener(new FullSelectorListener());
                break;

            case 5:
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel2.setBorder(null);
                jLabel4.setBorder(null);
                jLabel1.setBorder(null);
                jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(true);
                TarjetaPagado.setEnabled(true);
                DepositoPagado.setEnabled(true);
                ChequePagado.setEnabled(true);
                SeleccionNombre.setText("COMPARTIDO");
                SeleccionId.setText("5");
                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);

                NTransacciones.setText("N° DEPÓSITO: \nN° TARJETA: \nCHEQUE:");

                EfectivoPagado.setText("0.00");
                TarjetaPagado.setText("0.00");
                DepositoPagado.setText("0.00");
                ChequePagado.setText("0.00");

                TotalPagado.setText("0.00");
                cambio.setText("0.00");
                break;
        }
        
        
    }
    
    public final void Seleccion2(int Seleccion){
        switch (Seleccion) {

            case 1:
                jLabel1.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel4.setBorder(null);
                jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(true);
                TarjetaPagado.setEnabled(false);
                DepositoPagado.setEnabled(false);
                ChequePagado.setEnabled(false);
                SeleccionNombre.setText("EFECTIVO");
                NTransacciones.setText(null);
                NTransacciones.setVisible(false);
                jLabel9.setVisible(false);

                break;

            case 2:
                jLabel2.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel4.setBorder(null);
                jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(false);
                TarjetaPagado.setEnabled(true);
                DepositoPagado.setEnabled(false);
                ChequePagado.setEnabled(false);
                SeleccionNombre.setText("TARJETA");

                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);
                break;

            case 3:
                jLabel2.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel1.setBorder(null);
                jLabel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(false);
                TarjetaPagado.setEnabled(false);
                DepositoPagado.setEnabled(true);
                ChequePagado.setEnabled(false);
                SeleccionNombre.setText("DEPÓSITO O TRANSFERENCIA");
                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);
                break;

            case 4:
                jLabel2.setBorder(null);
                jLabel3.setBorder(null);
                jLabel2.setBorder(null);
                jLabel4.setBorder(null);
                jLabel1.setBorder(null);
                jLabel5.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));
                
                EfectivoPagado.setEnabled(false);
                TarjetaPagado.setEnabled(false);
                DepositoPagado.setEnabled(false);
                ChequePagado.setEnabled(true);
                SeleccionNombre.setText("CHEQUE");
                
                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);
                break;

            case 5:
                jLabel2.setBorder(null);
                jLabel5.setBorder(null);
                jLabel2.setBorder(null);
                jLabel4.setBorder(null);
                jLabel1.setBorder(null);
                jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "SELECCIONADO", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.CENTER, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 0, 102)));

                EfectivoPagado.setEnabled(true);
                TarjetaPagado.setEnabled(true);
                DepositoPagado.setEnabled(true);
                ChequePagado.setEnabled(true);
                SeleccionNombre.setText("COMPARTIDO");
                NTransacciones.setVisible(true);
                jLabel9.setVisible(true);
                break;
        }
        
        
    }
    
    public void TotalCancelado(Double Efectivo){
        double total = Double.parseDouble(labeltotal.getText());
        TotalPagado();
            Cambio();
            
    }
    
    public final void CalcularIva(){
        Float TotalPagar = Float.parseFloat(labeltotal.getText());
        Float TotalPagarIva = TotalPagar * IVA /100;
        Float SubTotalAPagar =TotalPagar - TotalPagarIva;
        TotalIva.setText(String.format("%.2f",TotalPagarIva));
        SubTotal.setText(String.format("%.2f",SubTotalAPagar));
    }
    
    private void EfectivoPagadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EfectivoPagadoKeyReleased
        ValidacionDePago(Double.parseDouble(EfectivoPagado.getText()));
    }//GEN-LAST:event_EfectivoPagadoKeyReleased

    private void TarjetaPagadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TarjetaPagadoKeyReleased
        ValidacionDePago(Double.parseDouble(TarjetaPagado.getText()));
    }//GEN-LAST:event_TarjetaPagadoKeyReleased

    private void TarjetaPagadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TarjetaPagadoMouseClicked
        if(TarjetaPagado.getText().equals("0.00")){
        TarjetaPagado.setText(null);    
        }
        
    }//GEN-LAST:event_TarjetaPagadoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        NingunCampoNulo();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    public void NingunCampoNulo(){
        int Valor= Integer.parseInt(SeleccionId.getText());
            switch (Valor) {
                case 1 -> {
                    if(EfectivoPagado.getText().equals("")){
                        EfectivoPagado.requestFocus();
                        JOptionPane.showMessageDialog(null, "DEBE INGRESAR ALGUN PAGO EN EFECTIVO", "NO SE PERMITE UN VALOR NULO", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Totales();
                    }
                }
                case 2 -> {
                    if(TarjetaPagado.getText().equals("")){
                        TarjetaPagado.requestFocus();
                        JOptionPane.showMessageDialog(null, "DEBE INGRESAR LO PAGADO CON TARJETA", "NO SE PERMITE UN VALOR NULO", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Totales();
                    }
                }
                case 3 -> {
                    if(DepositoPagado.getText().equals("")){
                        DepositoPagado.requestFocus();
                        JOptionPane.showMessageDialog(null, "DEBE INGRESAR LO PAGADO EN DEPOSITO O TRANSFERENCIA", "NO SE PERMITE UN VALOR NULO", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Totales();
                    }
                }
                case 4 -> {
                    if(ChequePagado.getText().equals("")){
                        ChequePagado.requestFocus();
                        JOptionPane.showMessageDialog(null, "DEBE INGRESAR LO PAGADO CON CHEQUE", "NO SE PERMITE UN VALOR NULO", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Totales();
                    }
                }
                case 5 -> {
                    if(EfectivoPagado.getText().equals("") || TarjetaPagado.getText().equals("") || DepositoPagado.getText().equals("") || ChequePagado.getText().equals("")){
                        EfectivoPagado.requestFocus();
                        JOptionPane.showMessageDialog(null, "DEBE INGRESAR LAS FORMAS DE PAGO", "NO SE PERMITE UN VALOR NULO", JOptionPane.ERROR_MESSAGE);
                    }else{
                        Totales();
                    }
                }
                default -> {
                }
            }
    }
    
    public final void ValidacionDePago(Double TotalCancelado){
        TotalCancelado(TotalCancelado);
        Float PagoCliente= Float.parseFloat(TotalPagado.getText());
        Float TotalPago= Float.parseFloat(labeltotal.getText());
        labeltotal.setBackground(Color.red);
          if(PagoCliente >= TotalPago){
            cambio.setBackground(Color.GREEN);
            TotalPagado.setBackground(Color.GREEN);
            jButton1.setBackground(Color.GREEN);
        }else{
            cambio.setBackground(Color.RED);
            TotalPagado.setBackground(Color.RED);
            jButton1.setBackground(Color.red);
        } 
    }
    
    public void Totales(){
        retornarValoresFinales();
        if(Double.parseDouble(this.TotalPagado.getText()) >= Double.parseDouble(this.labeltotal.getText())){
            this.pos.RellenarMetodoPago(this.TotalPagado.getText(), this.cambio.getText(), this.EfectivoPagado.getText(), this.DepositoPagado.getText(), 
                    this.TarjetaPagado.getText(), this.ChequePagado.getText(), this.NTransacciones.getText(), this.SeleccionNombre.getText(), 
                    this.SeleccionId.getText(), this.TotalIva.getText(), this.SubTotal.getText());
            this.pos.VentanaFormaPago = false;
                    this.setVisible(false);
        }else{
            int Seleccion = JOptionPane.showConfirmDialog(null, "AÚN NO HA TERMINADO EL PROCESO.\n¿ESTÁ SEGURO DE TERMINAR?","ALERTA", JOptionPane.WARNING_MESSAGE);
            if(Seleccion == 0){
                this.pos.RellenarMetodoPago(this.TotalPagado.getText(), this.cambio.getText(), this.EfectivoPagado.getText(), this.DepositoPagado.getText(), this.TarjetaPagado.getText(), this.ChequePagado.getText()
                    , this.NTransacciones.getText(), this.SeleccionNombre.getText(), this.SeleccionId.getText(), this.TotalIva.getText(), this.SubTotal.getText()); 
                this.pos.VentanaFormaPago = false;
                this.setVisible(false);
            }
        }
    }
    
    public void TOTALES_SIN_INTERACCION(){
        retornarValoresFinales();
            this.pos.RellenarMetodoPago(this.TotalPagado.getText(), this.cambio.getText(), this.EfectivoPagado.getText(), this.DepositoPagado.getText(),
                    TarjetaPagado.getText(), this.ChequePagado.getText()
                    , this.NTransacciones.getText(), this.SeleccionNombre.getText(), this.SeleccionId.getText(), this.TotalIva.getText(), this.SubTotal.getText());  
        
    }
        
    private void EfectivoPagadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EfectivoPagadoMouseClicked
        if(EfectivoPagado.getText().equals("0.00")){
        EfectivoPagado.setText(null);    
        }
    }//GEN-LAST:event_EfectivoPagadoMouseClicked

    private void DepositoPagadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DepositoPagadoMouseClicked
        if(DepositoPagado.getText().equals("0.00")){
        DepositoPagado.setText(null);    
        }
    }//GEN-LAST:event_DepositoPagadoMouseClicked

    private void ChequePagadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChequePagadoMouseClicked
        if(ChequePagado.getText().equals("0.00")){
        ChequePagado.setText(null);    
        }
    }//GEN-LAST:event_ChequePagadoMouseClicked

    private void DepositoPagadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepositoPagadoKeyReleased
        ValidacionDePago(Double.parseDouble(DepositoPagado.getText()));
    }//GEN-LAST:event_DepositoPagadoKeyReleased

    private void ChequePagadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChequePagadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NingunCampoNulo();
        }
    }//GEN-LAST:event_ChequePagadoKeyPressed

    private void ChequePagadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChequePagadoKeyReleased
        ValidacionDePago(Double.parseDouble(ChequePagado.getText()));
    }//GEN-LAST:event_ChequePagadoKeyReleased

    private void EfectivoPagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EfectivoPagadoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, EfectivoPagado);
    }//GEN-LAST:event_EfectivoPagadoKeyTyped

    private void TarjetaPagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TarjetaPagadoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, TarjetaPagado);
    }//GEN-LAST:event_TarjetaPagadoKeyTyped

    private void DepositoPagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepositoPagadoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, DepositoPagado);
    }//GEN-LAST:event_DepositoPagadoKeyTyped

    private void ChequePagadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ChequePagadoKeyTyped
        Eventos event = new Eventos();
        event.numberDecimalKeyPress(evt, ChequePagado);
    }//GEN-LAST:event_ChequePagadoKeyTyped

    private void EfectivoPagadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EfectivoPagadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NingunCampoNulo();
        }
    }//GEN-LAST:event_EfectivoPagadoKeyPressed

    private void TarjetaPagadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TarjetaPagadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NingunCampoNulo();
        }
    }//GEN-LAST:event_TarjetaPagadoKeyPressed

    private void DepositoPagadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepositoPagadoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            NingunCampoNulo();
        }
    }//GEN-LAST:event_DepositoPagadoKeyPressed
    
    public void TotalPagado(){
        Double Pagado = 0.00;
        Double Efectivo = Double.parseDouble(this.EfectivoPagado.getText());
        if(Efectivo == null){
            this.EfectivoPagado.setText("0.00");
        }
        Double Tarjeta = Double.parseDouble(this.TarjetaPagado.getText());
        if(Tarjeta == null){
            this.TarjetaPagado.setText("0.00");
        }
        Double Deposito = Double.parseDouble(this.DepositoPagado.getText());
        if(Deposito == null){
            this.DepositoPagado.setText("0.00");
        }
        Double Cheque = Double.parseDouble(this.ChequePagado.getText());
        if(Cheque == null){
            this.ChequePagado.setText("0.00");
        }
        
        Pagado = Efectivo + Tarjeta + Deposito + Cheque;
        
        
        if(Pagado >= Double.parseDouble(this.labeltotal.getText())){
            this.TotalPagado.setForeground(Color.GREEN);
        }else if(Pagado <= Double.parseDouble(this.labeltotal.getText())){
            this.TotalPagado.setForeground(Color.RED);
        }
        
        this.TotalPagado.setText(String.format("%.2f",Pagado));
    }
    
    
    
     public void Cambio() {
        if (!"".equals(this.labeltotal.getText())) {
            Double Pagado = 0.00;
            Double TotalSemifinal = 0.00;
            Double TotalFinal = 0.00;
            
        Double Efectivo = Double.parseDouble(this.EfectivoPagado.getText());
        if(Efectivo == null){
            this.EfectivoPagado.setText("0.00");
            Efectivo = 0.00;
        }
        Double Tarjeta = Double.parseDouble(this.TarjetaPagado.getText());
        if(Tarjeta == null){
            this.TarjetaPagado.setText("0.00");
            Tarjeta = 0.00;
        }
        Double Deposito = Double.parseDouble(this.DepositoPagado.getText());
        if(Deposito == null){
            this.DepositoPagado.setText("0.00");
            Deposito = 0.00;
        }
        Double Cheque = Double.parseDouble(this.ChequePagado.getText());
        if(Cheque == null){
            this.ChequePagado.setText("0.00");
            Cheque = 0.00;
        }
        
        Pagado = Tarjeta + Deposito + Cheque;
        Double Total = Double.parseDouble(this.labeltotal.getText());
        
        TotalSemifinal = Total - Pagado;
        
        TotalFinal = Efectivo - TotalSemifinal; 
           this.cambio.setText(String.format("%.2f",TotalFinal));
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
            java.util.logging.Logger.getLogger(FormaDePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormaDePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormaDePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormaDePago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormaDePago().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField ChequePagado;
    public javax.swing.JTextField DepositoPagado;
    public javax.swing.JTextField EfectivoPagado;
    public javax.swing.JTextArea NTransacciones;
    public static javax.swing.JLabel SeleccionId;
    public javax.swing.JLabel SeleccionNombre;
    public javax.swing.JLabel SubTotal;
    public javax.swing.JTextField TarjetaPagado;
    public javax.swing.JLabel TotalIva;
    public javax.swing.JLabel TotalPagado;
    public javax.swing.JLabel cambio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel labeltotal;
    // End of variables declaration//GEN-END:variables

    public PagosTotales retornarValoresFinales(){
        ObtenerTotales OT= new ObtenerTotales();
        
        PagosTotales PT= new PagosTotales();
        PT.setFormaDePago(SeleccionNombre.getText());
        PT.setTotalAPagar(labeltotal.getText());
        PT.setTotalEfectivoPagado(TotalPagado.getText());
        PT.setTotalCambio(cambio.getText());
        PT.setTotalIvaAPagar(TotalIva.getText());
        PT.setTipoMoneda("GTQ");
        OT.ObtenerTotalesEnGeneral(PT);
        return PT;
    }
}
