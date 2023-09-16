/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Conexiones.ConexionesSQL;
import LICENCIA.SERIALES;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author MASALDOTER_GT
 */
public class LicenciaDao extends ConexionesSQL {

    public Long OBTENER_DIAS_RESTANTES() {
        LocalDate fechaVencimiento = null;
        long diasRestantes = 0;
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        int Datos = 0;
        String sql = "SELECT Dias_Restantes_Basico from licencia where idLicencia=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            if (rs.next()) {

                // Define el formato de entrada para parsear la fecha
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");

                try {
                    String fechaDesdeBaseDeDatos = rs.getString("Dias_Restantes_Basico");
// Reemplaza los guiones por barras
                    fechaDesdeBaseDeDatos = fechaDesdeBaseDeDatos.replace("-", "/");
// Luego, analiza la fecha
                    fechaVencimiento = LocalDate.parse(fechaDesdeBaseDeDatos, formato);
                } catch (Exception e) {
                    System.out.println("Error al parsear la fecha en OBTENER_DIAS_RESTANTES: " + e.getMessage());
                }
                // Obtén la fecha actual
                LocalDate fechaActual = LocalDate.now();

                // Calcula la diferencia en días entre la fecha de vencimiento y la fecha actual
                diasRestantes = ChronoUnit.DAYS.between(fechaActual, fechaVencimiento);

            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return diasRestantes;
    }
    
    public Long OBTENER_MESES_RESTANTES() {
    LocalDate fechaVencimiento = null;
    long mesesRestantes = 0;
    ps = null;
    rs = null;
    cn = Unionsis2.getConnection();
    int Datos = 0;
    String sql = "SELECT Dias_Restantes_Basico from licencia where idLicencia=?";
    try {
        ps = cn.prepareStatement(sql);
        ps.setInt(1, 1);
        rs = ps.executeQuery();
        if (rs.next()) {

            // Define el formato de entrada para parsear la fecha
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");

            try {
                String fechaDesdeBaseDeDatos = rs.getString("Dias_Restantes_Basico");
                // Reemplaza los guiones por barras
                fechaDesdeBaseDeDatos = fechaDesdeBaseDeDatos.replace("-", "/");
                // Luego, analiza la fecha
                fechaVencimiento = LocalDate.parse(fechaDesdeBaseDeDatos, formato);
            } catch (Exception e) {
                System.out.println("Error al parsear la fecha en OBTENER_MESES_RESTANTES: " + e.getMessage());
            }
            // Obtén la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Calcula la diferencia en meses entre la fecha de vencimiento y la fecha actual
            mesesRestantes = ChronoUnit.MONTHS.between(fechaActual, fechaVencimiento);

        }

    } catch (SQLException e) {
    } finally {
        PsClose(ps);
        RsClose(rs);
        ConnectionClose(cn);
    }
    return mesesRestantes;
}
    
    public String OBTENER_FECHA_GUARDADA() {
        String Fecha = "";
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        int Datos = 0;
        String sql = "SELECT Dias_Restantes_Basico from licencia where idLicencia=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, 1);
            rs = ps.executeQuery();
            if (rs.next()) {
                    Fecha=rs.getString("Dias_Restantes_Basico");
            }

        } catch (SQLException e) {
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return Fecha;
    }
    
    public boolean ACTUALIZAR_DIAS_RESTANTES(int Total_Dias) {
        int Total=0;
        if(OBTENER_MESES_RESTANTES()<0){
            Total = Integer.parseInt(String.valueOf(Total_Dias));
        }else{
            Total = Integer.parseInt(String.valueOf(OBTENER_MESES_RESTANTES()+Total_Dias));
        }
        Boolean Estado=false;
        ps = null;
        cn = Unionsis2.getConnection();

        String sql = "update licencia set Dias_Restantes_Basico=? where idLicencia=?";
        try {
            ps = cn.prepareStatement(sql);
            
            ps.setString(1, SERIALES.CALCULAR_FECHA(Total));
            ps.setInt(2, 1);
            ps.executeUpdate();
            Estado=true;

        } catch (SQLException e) {
            Estado=false;
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR AL ACTUALIZAR DÍAS", "NO SE ACTUALIZÓ\n"+ e, DesktopNotify.ERROR, 9000L);
            System.out.println(""+e);
            
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Estado;
    }
    
    
}
