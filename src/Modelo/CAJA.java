/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author aldoy
 */
public class CAJA {
    int idcaja, USUARIO_APERTURO_CAJA, USUARIO_CERRO_CAJA;
    String ESTADO_DE_CAJA, FECHA_HORA_APERTURA_CAJA, FECHA_HORA_CIERRE_CAJA, ARQUEO_DE_CAJA, USUARIO_APERTURO_CAJA_LETRAS, USUARIO_CERRO_CAJA_LETRAS, FECHA;
    float Total_inicial_CAJA, Total_Compras_CAJA, Total_Ventas_CAJA;

    public CAJA(){
        
    }
    
    public CAJA(int idcaja, int USUARIO_APERTURO_CAJA, int USUARIO_CERRO_CAJA, String ESTADO_DE_CAJA, String FECHA_HORA_APERTURA_CAJA, String FECHA_HORA_CIERRE_CAJA, String ARQUEO_DE_CAJA, String USUARIO_APERTURO_CAJA_LETRAS, String USUARIO_CERRO_CAJA_LETRAS, String FECHA, float Total_inicial_CAJA, float Total_Compras_CAJA, float Total_Ventas_CAJA) {
        this.idcaja = idcaja;
        this.USUARIO_APERTURO_CAJA = USUARIO_APERTURO_CAJA;
        this.USUARIO_CERRO_CAJA = USUARIO_CERRO_CAJA;
        this.ESTADO_DE_CAJA = ESTADO_DE_CAJA;
        this.FECHA_HORA_APERTURA_CAJA = FECHA_HORA_APERTURA_CAJA;
        this.FECHA_HORA_CIERRE_CAJA = FECHA_HORA_CIERRE_CAJA;
        this.ARQUEO_DE_CAJA = ARQUEO_DE_CAJA;
        this.USUARIO_APERTURO_CAJA_LETRAS = USUARIO_APERTURO_CAJA_LETRAS;
        this.USUARIO_CERRO_CAJA_LETRAS = USUARIO_CERRO_CAJA_LETRAS;
        this.FECHA = FECHA;
        this.Total_inicial_CAJA = Total_inicial_CAJA;
        this.Total_Compras_CAJA = Total_Compras_CAJA;
        this.Total_Ventas_CAJA = Total_Ventas_CAJA;
    }

    public int getIdcaja() {
        return idcaja;
    }

    public void setIdcaja(int idcaja) {
        this.idcaja = idcaja;
    }

    public int getUSUARIO_APERTURO_CAJA() {
        return USUARIO_APERTURO_CAJA;
    }

    public void setUSUARIO_APERTURO_CAJA(int USUARIO_APERTURO_CAJA) {
        this.USUARIO_APERTURO_CAJA = USUARIO_APERTURO_CAJA;
    }

    public int getUSUARIO_CERRO_CAJA() {
        return USUARIO_CERRO_CAJA;
    }

    public void setUSUARIO_CERRO_CAJA(int USUARIO_CERRO_CAJA) {
        this.USUARIO_CERRO_CAJA = USUARIO_CERRO_CAJA;
    }

    public String getESTADO_DE_CAJA() {
        return ESTADO_DE_CAJA;
    }

    public void setESTADO_DE_CAJA(String ESTADO_DE_CAJA) {
        this.ESTADO_DE_CAJA = ESTADO_DE_CAJA;
    }

    public String getFECHA_HORA_APERTURA_CAJA() {
        return FECHA_HORA_APERTURA_CAJA;
    }

    public void setFECHA_HORA_APERTURA_CAJA(String FECHA_HORA_APERTURA_CAJA) {
        this.FECHA_HORA_APERTURA_CAJA = FECHA_HORA_APERTURA_CAJA;
    }

    public String getFECHA_HORA_CIERRE_CAJA() {
        return FECHA_HORA_CIERRE_CAJA;
    }

    public void setFECHA_HORA_CIERRE_CAJA(String FECHA_HORA_CIERRE_CAJA) {
        this.FECHA_HORA_CIERRE_CAJA = FECHA_HORA_CIERRE_CAJA;
    }

    public String getARQUEO_DE_CAJA() {
        return ARQUEO_DE_CAJA;
    }

    public void setARQUEO_DE_CAJA(String ARQUEO_DE_CAJA) {
        this.ARQUEO_DE_CAJA = ARQUEO_DE_CAJA;
    }

    public String getUSUARIO_APERTURO_CAJA_LETRAS() {
        return USUARIO_APERTURO_CAJA_LETRAS;
    }

    public void setUSUARIO_APERTURO_CAJA_LETRAS(String USUARIO_APERTURO_CAJA_LETRAS) {
        this.USUARIO_APERTURO_CAJA_LETRAS = USUARIO_APERTURO_CAJA_LETRAS;
    }

    public String getUSUARIO_CERRO_CAJA_LETRAS() {
        return USUARIO_CERRO_CAJA_LETRAS;
    }

    public void setUSUARIO_CERRO_CAJA_LETRAS(String USUARIO_CERRO_CAJA_LETRAS) {
        this.USUARIO_CERRO_CAJA_LETRAS = USUARIO_CERRO_CAJA_LETRAS;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public float getTotal_inicial_CAJA() {
        return Total_inicial_CAJA;
    }

    public void setTotal_inicial_CAJA(float Total_inicial_CAJA) {
        this.Total_inicial_CAJA = Total_inicial_CAJA;
    }

    public float getTotal_Compras_CAJA() {
        return Total_Compras_CAJA;
    }

    public void setTotal_Compras_CAJA(float Total_Compras_CAJA) {
        this.Total_Compras_CAJA = Total_Compras_CAJA;
    }

    public float getTotal_Ventas_CAJA() {
        return Total_Ventas_CAJA;
    }

    public void setTotal_Ventas_CAJA(float Total_Ventas_CAJA) {
        this.Total_Ventas_CAJA = Total_Ventas_CAJA;
    }
    
}
