
package Modelo;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Productos {
    private int idProductos;
    private String Nombre;
    private Float Cantidad;
    private Float Costo;
    private Float Publico;
    private int IdProveedores;
    private String ProveedorNombre;
    private String CodigoBarras;
    private String CodigoLetras;
    private Float PrecioRe;
    private Float PrecioEs;
    private int Categoria;
    private String CategoriaNombre;
    private String ruta;
    private String fechaingreso;
    private String fechamodificacion;
    private int UsuarioIngreso, UsuarioModifico;
    private String Descripcion;
    private String UsuarioIngresoLetras, UsuarioModificoLetras;
    private JButton botoneditar, botoneliminar;
    private JLabel Imagen, Label1Adicional,Label2Adicional;
    private String NombreTiposDePrecio1, NombreTiposDePrecio2, NombreTiposDePrecio3;
    private String subcategoriaNombre, UbicacionNombre1, UbicacionNombre2, NombreImagen, Estado_Producto;
    private int subcategoria, Ubicacion1, Ubicacion2, idservidorimagenes;
    private Boolean APLICAR_DESCUENTO;
    private String Fecha_Historial, Hora_Historial;
    
    public Productos(){
        
    }

    public Productos(int idProductos, String Nombre, Float Cantidad, Float Costo, Float Publico, int IdProveedores, String ProveedorNombre, String CodigoBarras, String CodigoLetras, Float PrecioRe, Float PrecioEs, int Categoria, String CategoriaNombre, String ruta, String fechaingreso, String fechamodificacion, int UsuarioIngreso, int UsuarioModifico, String Descripcion, String UsuarioIngresoLetras, String UsuarioModificoLetras, JButton botoneditar, JButton botoneliminar, JLabel Imagen, JLabel Label1Adicional, JLabel Label2Adicional, String NombreTiposDePrecio1, String NombreTiposDePrecio2, String NombreTiposDePrecio3, String subcategoriaNombre, String UbicacionNombre1, String UbicacionNombre2, String NombreImagen, String Estado_Producto, int subcategoria, int Ubicacion1, int Ubicacion2, int idservidorimagenes, Boolean APLICAR_DESCUENTO, String Fecha_Historial, String Fecha_Modificacion_Historial) {
        this.idProductos = idProductos;
        this.Nombre = Nombre;
        this.Cantidad = Cantidad;
        this.Costo = Costo;
        this.Publico = Publico;
        this.IdProveedores = IdProveedores;
        this.ProveedorNombre = ProveedorNombre;
        this.CodigoBarras = CodigoBarras;
        this.CodigoLetras = CodigoLetras;
        this.PrecioRe = PrecioRe;
        this.PrecioEs = PrecioEs;
        this.Categoria = Categoria;
        this.CategoriaNombre = CategoriaNombre;
        this.ruta = ruta;
        this.fechaingreso = fechaingreso;
        this.fechamodificacion = fechamodificacion;
        this.UsuarioIngreso = UsuarioIngreso;
        this.UsuarioModifico = UsuarioModifico;
        this.Descripcion = Descripcion;
        this.UsuarioIngresoLetras = UsuarioIngresoLetras;
        this.UsuarioModificoLetras = UsuarioModificoLetras;
        this.botoneditar = botoneditar;
        this.botoneliminar = botoneliminar;
        this.Imagen = Imagen;
        this.Label1Adicional = Label1Adicional;
        this.Label2Adicional = Label2Adicional;
        this.NombreTiposDePrecio1 = NombreTiposDePrecio1;
        this.NombreTiposDePrecio2 = NombreTiposDePrecio2;
        this.NombreTiposDePrecio3 = NombreTiposDePrecio3;
        this.subcategoriaNombre = subcategoriaNombre;
        this.UbicacionNombre1 = UbicacionNombre1;
        this.UbicacionNombre2 = UbicacionNombre2;
        this.NombreImagen = NombreImagen;
        this.Estado_Producto = Estado_Producto;
        this.subcategoria = subcategoria;
        this.Ubicacion1 = Ubicacion1;
        this.Ubicacion2 = Ubicacion2;
        this.idservidorimagenes = idservidorimagenes;
        this.APLICAR_DESCUENTO = APLICAR_DESCUENTO;
        this.Fecha_Historial = Fecha_Historial;
        this.Hora_Historial = Fecha_Modificacion_Historial;
    }

    public int getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(int idProductos) {
        this.idProductos = idProductos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Float getCantidad() {
        return Cantidad;
    }

    public void setCantidad(Float Cantidad) {
        this.Cantidad = Cantidad;
    }

    public Float getCosto() {
        return Costo;
    }

    public void setCosto(Float Costo) {
        this.Costo = Costo;
    }

    public Float getPublico() {
        return Publico;
    }

    public void setPublico(Float Publico) {
        this.Publico = Publico;
    }

    public int getIdProveedores() {
        return IdProveedores;
    }

    public void setIdProveedores(int IdProveedores) {
        this.IdProveedores = IdProveedores;
    }

    public String getProveedorNombre() {
        return ProveedorNombre;
    }

    public void setProveedorNombre(String ProveedorNombre) {
        this.ProveedorNombre = ProveedorNombre;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String CodigoBarras) {
        this.CodigoBarras = CodigoBarras;
    }

    public String getCodigoLetras() {
        return CodigoLetras;
    }

    public void setCodigoLetras(String CodigoLetras) {
        this.CodigoLetras = CodigoLetras;
    }

    public Float getPrecioRe() {
        return PrecioRe;
    }

    public void setPrecioRe(Float PrecioRe) {
        this.PrecioRe = PrecioRe;
    }

    public Float getPrecioEs() {
        return PrecioEs;
    }

    public void setPrecioEs(Float PrecioEs) {
        this.PrecioEs = PrecioEs;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }

    public String getCategoriaNombre() {
        return CategoriaNombre;
    }

    public void setCategoriaNombre(String CategoriaNombre) {
        this.CategoriaNombre = CategoriaNombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(String fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public int getUsuarioIngreso() {
        return UsuarioIngreso;
    }

    public void setUsuarioIngreso(int UsuarioIngreso) {
        this.UsuarioIngreso = UsuarioIngreso;
    }

    public int getUsuarioModifico() {
        return UsuarioModifico;
    }

    public void setUsuarioModifico(int UsuarioModifico) {
        this.UsuarioModifico = UsuarioModifico;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getUsuarioIngresoLetras() {
        return UsuarioIngresoLetras;
    }

    public void setUsuarioIngresoLetras(String UsuarioIngresoLetras) {
        this.UsuarioIngresoLetras = UsuarioIngresoLetras;
    }

    public String getUsuarioModificoLetras() {
        return UsuarioModificoLetras;
    }

    public void setUsuarioModificoLetras(String UsuarioModificoLetras) {
        this.UsuarioModificoLetras = UsuarioModificoLetras;
    }

    public JButton getBotoneditar() {
        return botoneditar;
    }

    public void setBotoneditar(JButton botoneditar) {
        this.botoneditar = botoneditar;
    }

    public JButton getBotoneliminar() {
        return botoneliminar;
    }

    public void setBotoneliminar(JButton botoneliminar) {
        this.botoneliminar = botoneliminar;
    }

    public JLabel getImagen() {
        return Imagen;
    }

    public void setImagen(JLabel Imagen) {
        this.Imagen = Imagen;
    }

    public JLabel getLabel1Adicional() {
        return Label1Adicional;
    }

    public void setLabel1Adicional(JLabel Label1Adicional) {
        this.Label1Adicional = Label1Adicional;
    }

    public JLabel getLabel2Adicional() {
        return Label2Adicional;
    }

    public void setLabel2Adicional(JLabel Label2Adicional) {
        this.Label2Adicional = Label2Adicional;
    }

    public String getNombreTiposDePrecio1() {
        return NombreTiposDePrecio1;
    }

    public void setNombreTiposDePrecio1(String NombreTiposDePrecio1) {
        this.NombreTiposDePrecio1 = NombreTiposDePrecio1;
    }

    public String getNombreTiposDePrecio2() {
        return NombreTiposDePrecio2;
    }

    public void setNombreTiposDePrecio2(String NombreTiposDePrecio2) {
        this.NombreTiposDePrecio2 = NombreTiposDePrecio2;
    }

    public String getNombreTiposDePrecio3() {
        return NombreTiposDePrecio3;
    }

    public void setNombreTiposDePrecio3(String NombreTiposDePrecio3) {
        this.NombreTiposDePrecio3 = NombreTiposDePrecio3;
    }

    public String getSubcategoriaNombre() {
        return subcategoriaNombre;
    }

    public void setSubcategoriaNombre(String subcategoriaNombre) {
        this.subcategoriaNombre = subcategoriaNombre;
    }

    public String getUbicacionNombre1() {
        return UbicacionNombre1;
    }

    public void setUbicacionNombre1(String UbicacionNombre1) {
        this.UbicacionNombre1 = UbicacionNombre1;
    }

    public String getUbicacionNombre2() {
        return UbicacionNombre2;
    }

    public void setUbicacionNombre2(String UbicacionNombre2) {
        this.UbicacionNombre2 = UbicacionNombre2;
    }

    public String getNombreImagen() {
        return NombreImagen;
    }

    public void setNombreImagen(String NombreImagen) {
        this.NombreImagen = NombreImagen;
    }

    public String getEstado_Producto() {
        return Estado_Producto;
    }

    public void setEstado_Producto(String Estado_Producto) {
        this.Estado_Producto = Estado_Producto;
    }

    public int getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(int subcategoria) {
        this.subcategoria = subcategoria;
    }

    public int getUbicacion1() {
        return Ubicacion1;
    }

    public void setUbicacion1(int Ubicacion1) {
        this.Ubicacion1 = Ubicacion1;
    }

    public int getUbicacion2() {
        return Ubicacion2;
    }

    public void setUbicacion2(int Ubicacion2) {
        this.Ubicacion2 = Ubicacion2;
    }

    public int getIdservidorimagenes() {
        return idservidorimagenes;
    }

    public void setIdservidorimagenes(int idservidorimagenes) {
        this.idservidorimagenes = idservidorimagenes;
    }

    public Boolean getAPLICAR_DESCUENTO() {
        return APLICAR_DESCUENTO;
    }

    public void setAPLICAR_DESCUENTO(Boolean APLICAR_DESCUENTO) {
        this.APLICAR_DESCUENTO = APLICAR_DESCUENTO;
    }

    public String getFecha_Historial() {
        return Fecha_Historial;
    }

    public void setFecha_Historial(String Fecha_Historial) {
        this.Fecha_Historial = Fecha_Historial;
    }

    public String getHora_Historial() {
        return Hora_Historial;
    }

    public void setHora_Historial(String Hora_Historial) {
        this.Hora_Historial = Hora_Historial;
    }
   
}
