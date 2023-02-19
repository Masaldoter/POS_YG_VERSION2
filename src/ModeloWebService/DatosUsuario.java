
package ModeloWebService;

/**
 *
 * @author Masaldoter
 */
public class DatosUsuario {
    private String Nit, Usuario, Contrasenia;
    public DatosUsuario(){
        
    }

    public DatosUsuario(String Nit, String Usuario, String Contrasenia) {
        this.Nit = Nit;
        this.Usuario = Usuario;
        this.Contrasenia = Contrasenia;
    }

    public String getNit() {
        return Nit;
    }

    public void setNit(String Nit) {
        this.Nit = Nit;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }
}
