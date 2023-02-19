
package Modelo;

public class Proveedor {
   private String Proveedor;
   private String Vendedor;
   private String Tel; 
   private int idproveedores;
   public Proveedor(){
       
   }

    public Proveedor(String Proveedor, String Vendedor, String Tel, int idproveedores) {
        this.Proveedor = Proveedor;
        this.Vendedor = Vendedor;
        this.Tel = Tel;
        this.idproveedores = idproveedores;
    }

    public String getProveedor() {
        return Proveedor;
    }

    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

    public String getVendedor() {
        return Vendedor;
    }

    public void setVendedor(String Vendedor) {
        this.Vendedor = Vendedor;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public int getIdproveedores() {
        return idproveedores;
    }

    public void setIdproveedores(int idproveedores) {
        this.idproveedores = idproveedores;
    }

}
