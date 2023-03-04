package Controlador;

import CLASES_GLOBALES.METODOS_GLOBALES;
import Conexiones.conexion;
import Modelo.Detalle;
import Modelo.Venta;
import Conexiones.ConexionesSQL;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDao extends ConexionesSQL {

    int r = 0;

    public int numeroserie() {
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        int NumeroInterno = 0;
        String sql = "select MAX(NumeroInterno) from seriesfacturas";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                NumeroInterno = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error en numero de serie, " + e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return NumeroInterno;
    }
    
    public int numeroserie_Salidas() {
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        int NumeroInterno_SALIDAS = 0;
        String sql = "select MAX(NumeroInterno_salidas) from seriessalidas";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                NumeroInterno_SALIDAS = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error en numero de seriessalidas, " + e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return NumeroInterno_SALIDAS;
    }

    public void numeroserieIncrementarFactura(int NumeroFactura) {

        int increment = NumeroFactura;
        increment = increment + 1;

        String sql = "INSERT INTO seriesfacturas (NumeroInterno) VALUES(?)";
        try {
            ps = null;
            cn = Unionsis2.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, increment);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Error en numero de numeroserieIncrementarFactura, " + e);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public void numeroserieIncrementarSALIDAS(int NumeroSALIDA) {

        int increment = NumeroSALIDA;
        increment = increment + 1;

        String sql = "INSERT INTO seriessalidas (NumeroInterno_salidas) VALUES(?)";
        try {
            ps = null;
            cn = Unionsis2.getConnection();
            ps = cn.prepareStatement(sql);
            ps.setInt(1, increment);
            boolean resultado = ps.execute();

        } catch (SQLException e) {
            System.out.println("Error en numero de numeroserieIncrementarSALIDAS, " + e);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
    }
    
    public String numeroserieVale() {
        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();
        String seriea = "";
        String sql = "select max(idVale) from vale";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                seriea = rs.getString(1);
            }

        } catch (SQLException e) {
            System.out.println("Error en numero de numeroserieVale, " + e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return seriea;
    }

    public static int BuscarIdProducto(String CodigoBarras) {
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        int idProducto = 0;
        String sql = "SELECT IdProductos FROM productos WHERE CodigoBarras=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, CodigoBarras);
            rs = ps.executeQuery();

            while (rs.next()) {
                idProducto = rs.getInt("IdProductos");
            }
        } catch (SQLException e) {
            System.err.println("Error en VentaDao, BuscarIdProducto" + e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }

        return idProducto;
    }
    
    public static Float BuscarSTOCKProducto(int Id) {
        ps = null;
        rs = null;
        cn = conexion.getInstancia().getConnection();
        Float STOCK = 0f;
        String sql = "SELECT Cantidad FROM productos WHERE IdProductos=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1, Id);
            rs = ps.executeQuery();

            while (rs.next()) {
                STOCK = rs.getFloat("Cantidad");
            }
        } catch (SQLException e) {
            System.err.println("Error en VentaDao, BuscarSTOCKProducto" + e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }

        return STOCK;
    }

    public Boolean RegistrarVenta(Venta v) {
        ps = null;
        cn = conexion.getInstancia().getConnection();
        Boolean ESTADO_REGISTRO=false;

        String sql = "INSERT INTO  registro (Cliente, NitCliente, TIPO_IDENTIFICACION, DireccionCliente, Total, Fecha, Hora, Pago, Cambio, NumeroTransaccion, NoFactura, Usuario, FormaPago, Observacion, TotalEnLetras, NombreCertificador"
                + ", NitCertificador, FechaAutorizacion, NumeroAutorizacion, NumeroDocumento, SerieDocumento, TipoDocumentoFel, Estado, NitEmisor, NumeroDeAccesoInterno, id_CAJA_registro) "
                + "VALUES  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getIDENTIFICACION_CLIENTE());
            ps.setString(3, v.getTIPO_IDENTIFICACION_CLIENTE());
            ps.setString(4, v.getDireccionCliente());
            ps.setDouble(5, v.getTotal());
            ps.setString(6, METODOS_GLOBALES.Fecha());
            ps.setString(7, METODOS_GLOBALES.Hora());
            ps.setFloat(8, v.getPagocon());
            ps.setFloat(9, v.getCambio());
            ps.setString(10, v.getNumeroTransaccion());
            ps.setString(11, v.getNoFactura());
            ps.setInt(12, v.getUsuario());
            ps.setString(13, v.getFormaPago());
            ps.setString(14, v.getObservacion());
            ps.setString(15, v.getTotalEnLetras());
            ps.setString(16, v.getNombreCertificador());
            ps.setString(17, v.getNitCertificador());
            ps.setString(18, v.getFechaAutorizacion());
            ps.setString(19, v.getNumeroAutorizacion());
            ps.setString(20, v.getNumeroDocumento());
            ps.setString(21, v.getSerieDocumento());
            ps.setString(22, v.getTipoDocumentoFel());
            ps.setString(23, "FACTURADO");
            ps.setString(24, v.getNitEmisor());
            ps.setString(25, v.getNUMERO_INTERNO());
            ps.setInt(26, v.getId_CAJA_registro());
            ps.execute();
            ESTADO_REGISTRO=true;

        } catch (SQLException e) {
            ESTADO_REGISTRO=false;
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR EN BASE DE DATOS AL REGISTRAR VENTA", "NO SE PUDO REALIZAR LA ACCIÓN\n"+ e, DesktopNotify.FAIL, 9000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return ESTADO_REGISTRO;
    }
    
    public int ConvertirVentaADTE(Venta v) {
        ps = null;
        cn = conexion.getInstancia().getConnection();

        String sql = "UPDATE registro set FechaAutorizacion=?, NumeroAutorizacion=?, NumeroDocumento=?, SerieDocumento=?, TipoDocumentoFel=?, Estado=?, NumeroDeAccesoInterno=? where NoFactura=?";

        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, v.getFechaAutorizacion());
            ps.setString(2, v.getNumeroAutorizacion());
            ps.setString(3, v.getNumeroDocumento());
            ps.setString(4, v.getSerieDocumento());
            ps.setString(5, v.getTipoDocumentoFel());
            ps.setString(6, "FACTURADO");
            ps.setString(7, v.getNUMERO_INTERNO());
            ps.setString(8, v.getNoFactura());
            ps.executeUpdate();

        } catch (SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR EN BASE DE DATOS AL CONVERTIR DTE", "NO SE PUDO REALIZAR LA ACCIÓN\n"+ e, DesktopNotify.SUCCESS, 9000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return r;
    }

    public boolean RegistrarDetalle(Detalle Dv) {
        boolean Resultado = false;
        ps = null;
        cn = conexion.getInstancia().getConnection();
        String sql = "insert into detalle (CodigoBarras, Nombre, Cantidad, Precio, Descuento_Detalle, Precio_Con_Descuento_Detalle, Total, Validar_Descuento_detalle, ProductoRegistrado, NoFactura, IdProductos)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, Dv.getCodigoBarras());
            ps.setString(2, Dv.getNombre());
            ps.setFloat(3, Dv.getCantidad());
            ps.setFloat(4, Dv.getPrecio());
            ps.setFloat(5, Dv.getDescuento());
            ps.setFloat(6, Dv.getPrecio_Con_Descuento());
            ps.setFloat(7, Dv.getTotal());
            ps.setBoolean(8, Dv.getAplicar_Descuento());
            ps.setString(9, Dv.getValidacionProductoExistente());
            ps.setString(10, Dv.getNoFactura());   
            ps.setInt(11, Dv.getIdProductos());   
            ps.execute();
            Resultado =true;

        } catch (SQLException e) {
            Resultado =false;
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR EN BASE DE DATOS", "ERROR AL REGISTRAR EL DETALLE\n"+ e, DesktopNotify.SUCCESS, 9000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Resultado;
    }
    
    public static boolean ActualizarStock(Float cant, String cod) {
        ps = null;
        cn = Unionsis2.getConnection();

        String sql = "update productos set Cantidad=? where CodigoBarras=?";
        try {
            ps = cn.prepareStatement(sql);
            ps.setFloat(1, cant);
            ps.setString(2, cod);
            ps.execute();
            return true;

        } catch (SQLException e) {
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ERROR AL ACTUALIZAR STOCK", "NO SE ACTUALIZÓ EL STOCK\n"+ e, DesktopNotify.SUCCESS, 9000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return false;
    }
    
    public List ListarVentas() {

        ps = null;
        rs = null;
        cn = Unionsis2.getConnection();

        List<Venta> ListaVenta = new ArrayList();
        String sql = "select * from registro";
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Venta vent = new Venta();
                vent.setIdRegistro(rs.getInt("idregistro"));
                vent.setCliente(rs.getString("Cliente"));
                vent.setTotal(rs.getFloat("Total"));
                vent.setNoFactura(rs.getString("NoFactura"));
                vent.setFormaPago(rs.getString("FormaPago"));
                ListaVenta.add(vent);
            }

        } catch (SQLException e) {
            System.err.println("Error, " + e);
        } finally {
            PsClose(ps);
            RsClose(rs);
            ConnectionClose(cn);
        }
        return ListaVenta;
    }
    
    public Boolean AnularVentaRegistro(String NoFac) {
        Boolean Estado= false;
        try {
            ps = null;
            cn = Unionsis2.getConnection();
            ps = cn.prepareStatement("UPDATE registro SET Estado=? WHERE NoFactura=?");
            ps.setString(1, "ANULADO");
            ps.setString(2, NoFac);
            int resultado = ps.executeUpdate();
            if (resultado > 0) {
                Estado= true;
                DesktopNotify.setDefaultTheme(NotifyTheme.Light);
                DesktopNotify.showDesktopMessage("ANULADO CORRECTAMENTE", "LA VENTA SE ANULÓ CORRECTAMENTE", DesktopNotify.SUCCESS, 9000L);
            } else {
                Estado= false;
                DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
                DesktopNotify.showDesktopMessage("ANULACIÓN FALLIDA", "HUBO UN ERROR AL ELIMINAR LA VENTA", DesktopNotify.FAIL, 9000L);
            }
        } catch (SQLException e) {
            Estado= false;
            DesktopNotify.setDefaultTheme(NotifyTheme.Dark);
            DesktopNotify.showDesktopMessage("ANULACIÓN FALLIDA", "HUBO UN ERROR AL ELIMIAR LA VENTA, " + e, DesktopNotify.ERROR, 9000L);
        } finally {
            PsClose(ps);
            ConnectionClose(cn);
        }
        return Estado;
    }
}
