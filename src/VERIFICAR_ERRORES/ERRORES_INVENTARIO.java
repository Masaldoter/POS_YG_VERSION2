/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VERIFICAR_ERRORES;

import static CLASES_GLOBALES.METODOS_GLOBALES.tray;
import static CLASES_GLOBALES.METODOS_GLOBALES.trayIcon;
import CLASES_GLOBALES.PARAMETROS_USUARIOS;
import Conexiones.ConexionesSQL;
import static Conexiones.ConexionesSQL.ConnectionClose;
import static Conexiones.ConexionesSQL.PsClose;
import static Conexiones.ConexionesSQL.RsClose;
import static Conexiones.ConexionesSQL.cn;
import static Conexiones.ConexionesSQL.ps;
import static Conexiones.ConexionesSQL.rs;
import Conexiones.conexion;
import Modelo.Productos;
import Vista.Principal;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;

/**
 *
 * @author MASALDOTER_GT
 */
public class ERRORES_INVENTARIO extends ConexionesSQL {

    public static List PRODUCTOS_CON_ERRORES_TABLAS() {

        List<Productos> Listapro = new ArrayList();
        String sql = "select CodigoBarras, Nombre, Cantidad, ruta from productos where Cantidad < 0";
        try {
            cn = conexion.getInstancia().getConnection();
            rs = null;
            ps = null;
            Productos pro1;
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                pro1 = new Productos();
                //pro1.setIdProductos(rs.getInt("productos.CodigoBarras"));
                pro1.setCodigoBarras(rs.getString("CodigoBarras"));
                pro1.setNombre(rs.getString("Nombre"));
                pro1.setCantidad(rs.getFloat("Cantidad"));
                pro1.setRuta(rs.getString("ruta"));
                Listapro.add(pro1);
            }

        } catch (SQLException e) {
            System.err.println("Error PRODUCTOS_CON_ERRORES_TABLAS, " + e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }

        return Listapro;
    }

    public static int STOCK_MENOR_A_CERO() {
        int TOTAL_REGISTROS = 0;
        Productos pro = null;
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "select CodigoBarras, Nombre, Cantidad, ruta, count(*) AS total_registros from productos where Cantidad < 0";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                pro = new Productos();
                pro.setCodigoBarras(rs.getString("CodigoBarras"));
                pro.setNombre(rs.getString("Nombre"));
                pro.setCantidad(rs.getFloat("Cantidad"));
                pro.setRuta(rs.getString("ruta"));
                TOTAL_REGISTROS = rs.getInt("total_registros");
            }
            if(TOTAL_REGISTROS<0){
                TOTAL_REGISTROS=0;
            }

        } catch (SQLException e) {
            System.err.println("NO HAY DATOS O HAY ERRORES EN STOCK_MENOR_A_CERO\n"+e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return TOTAL_REGISTROS;
    }

    public static void VERIFICAR_ERROR(Principal prin) {

        // Crea e inicia el SwingWorker para la tarea repetida
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                while (true) {
                    int Total = STOCK_MENOR_A_CERO();
                    if (Total > 0) {
                        trayIcon.setImageAutoSize(true);
                        tray.add(trayIcon);
                        trayIcon.setToolTip("CLICK PARA IR A INVENTARIO");
                        trayIcon.displayMessage("ERROR EN INVENTARIO", "EXISTEN " + Total + " PRODUCTOS QUE TIENEN STOCK MENOR A CERO", TrayIcon.MessageType.WARNING);
                        trayIcon.addActionListener((e) -> {
                            if (PARAMETROS_USUARIOS.ROL_USUARIO.equals("Administrador")) {
                                prin.ABRIR_VENTANAS(prin.I, true);
                                prin.I.CARGAR_REGISTROS();
                            }
                            ERRORES_INVENTARIO_PRODUCTOS E= new ERRORES_INVENTARIO_PRODUCTOS();
                            E.setVisible(true);
                        });
                    }
                    Thread.sleep(600000); // Espera 5 segundos
                }
            }
        };
        worker.execute();
    }
}
