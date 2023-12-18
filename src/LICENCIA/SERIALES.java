/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LICENCIA;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 *
 * @author MASALDOTER_GT
 */
public class SERIALES {
    int BASIC_VERSION_UN_MES_FNEUOWFFNEOWCLB = 1;
    //int BASIC_VERSION_UN_MES_WEFRHTRSJYTJRTB = 1;
    int BASIC_VERSION_UN_MES_IUGJHDBFWRGRWWQ = 1;
    int BASIC_VERSION_UN_MES_HMGDFGSGYWARFRT = 1;
    int BASIC_VERSION_UN_MES_MGFSJTYUQREWTGK = 1;
    int BASIC_VERSION_UN_MES_AAERGRGRGJKYHTD = 1;
    int BASIC_VERSION_UN_MES_KIOYIKTUJYHGTWQ = 1;
    int BASIC_VERSION_UN_MES_ERGTYREHTNRDSVF = 1;
    int BASIC_VERSION_UN_MES_HUYTJYHWT4RQQRG = 1;
    int BASIC_VERSION_UN_MES_MJYFNHBIKLKITWQ = 1;
    int BASIC_VERSION_UN_MES_HTRHREFUEJ7WFJJ = 1;
    int BASIC_VERSION_UN_MES_NCYHTF636V7VWHV = 1;
    int BASIC_VERSION_UN_MES_BUTG87RBEWVVTWT = 1;
    int BASIC_VERSION_UN_MES_VJYDSTAC256JYBT = 1;
    int BASIC_VERSION_UN_MES_MLN0Y9J8N742BDE = 1;
    int BASIC_VERSION_UN_MES_NB9TUBY765VWGC2 = 1;
    int BASIC_VERSION_UN_MES_E56IG7H8O98733B = 1;
    int BASIC_VERSION_UN_MES_NIT6N7652C5X2VV = 1;
    int BASIC_VERSION_UN_MES_B89658746VW52CB = 1;
    int BASIC_VERSION_UN_MES_B896OUNO9898HYB = 1;
    int BASIC_VERSION_UN_MES_MI890N8BYUCUJNN = 1;
    int BASIC_VERSION_UN_MES_LLAOSIFN01NO9NN = 1;
    int BASIC_VERSION_UN_MES_CLM9DQADAD123D2 = 1;
    int BASIC_VERSION_UN_MES_CLAVE9102JEDWDD = 1;
    int BASIC_VERSION_UN_MES_M9IJ8MDNIUDN232 = 1;
    
    
    int BASIC_VERSION_SEIS_MESES_RTHHTRHRTHRTJHS = 6;
    int BASIC_VERSION_SEIS_MESES_KUYRTUJWQEFEWWE = 6;
    int BASIC_VERSION_SEIS_MESES_NBVRECWQWERWWEF = 6;
    int BASIC_VERSION_SEIS_MESES_KI8UJYTBTWEFWAA = 6;
    int BASIC_VERSION_SEIS_MESES_MOIUTAQWEHGTBGV = 6;
    int BASIC_VERSION_SEIS_MESES_MNNBVCSXARETVQE = 6;
    int BASIC_VERSION_SEIS_MESES_LKOIJUHYVTECXWC = 6;
    int BASIC_VERSION_SEIS_MESES_POIUYTLKJHBVWVW = 6;
    int BASIC_VERSION_SEIS_MESES_DFGHUHGRQWETYSE = 6;
    int BASIC_VERSION_SEIS_MESES_DFGHJUYTRQWERTG = 6;
    
    int BASIC_VERSION_DOCE_MESES_ASDFGHTREYHKLOL = 12;
    int BASIC_VERSION_DOCE_MESES_MONIUBHASZXCVBT = 12;
    int BASIC_VERSION_DOCE_MESES_WEZXCVBEE6UE6UN = 12;
    int BASIC_VERSION_DOCE_MESES_UNRBYECTVDBYJDB = 12;
    int BASIC_VERSION_DOCE_MESES_KIMUNBYVTCRZQWX = 12;
    int BASIC_VERSION_DOCE_MESES_QXCRVGTJHYHYJYN = 12;
    int BASIC_VERSION_DOCE_MESES_NUFBDYVTXACTTVH = 12;
    int BASIC_VERSION_DOCE_MESES_YNIYBVDCTXARZRC = 12;
    int BASIC_VERSION_DOCE_MESES_JNHVTHBDVBYUNUU = 12;
    int BASIC_VERSION_DOCE_MESES_MIUNYTBCEZWBTYV = 12;
    
    int BASIC_VERSION_24_MESES_GRG8UYGN52354Y7 = 24;
    int BASIC_VERSION_24_MESES_87564Y6G5245UJK = 24;
    int BASIC_VERSION_24_MESES_09MN8B76WCE2CTV = 24;
    int BASIC_VERSION_24_MESES_9K6J85H47G63F5V = 24;
    int BASIC_VERSION_24_MESES_09MJ8H7G6FTR2VC = 24;
    int BASIC_VERSION_24_MESES_6B5VY3BNBVCXC23 = 24;
    int BASIC_VERSION_24_MESES_C13V24B53NBVC45 = 24;
    int BASIC_VERSION_24_MESES_109876V4V25VBJB = 24;
    int BASIC_VERSION_24_MESES_KHTFBTWVC256V76 = 24;
    int BASIC_VERSION_24_MESES_098N7B6VW45VY4V = 24;
    
    String[] Seriales_Premium_MES;
    
    String[] Seriales_Premium_SEIS_MESES;
    
    String[] Seriales_Premium_DOCE_MESES;
    
    public boolean verificarNombreExistente(String nombre) {
        try {
            Field field = getClass().getDeclaredField(nombre);
            field.setAccessible(true); // Hacer la variable accesible si es privada
            Object valor = field.get(this); // Obtener el valor de la variable
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false; // Si la variable no existe o hay un error, retorna false
        }
    }
    public int OBTENER_VALOR_SERIAL(String nombre) {
        int TOTAL=0;
        try {
            Field field = getClass().getDeclaredField(nombre);
            field.setAccessible(true); // Hacer la variable accesible si es privada
            Object valor = field.get(this); // Obtener el valor de la variable
            TOTAL = Integer.parseInt(String.valueOf(valor));
            CALCULAR_FECHA(TOTAL);
            return TOTAL;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return TOTAL; // Si la variable no existe o hay un error, retorna false
        }
    }
    
    


    public static String CALCULAR_FECHA(int CANTIDAD_MESES){
        
        LocalDate fechaActual = LocalDate.now();

        // Agrega un mes a la fecha actual
        LocalDate fechaNueva = fechaActual.plusMonths(CANTIDAD_MESES);
        return String.valueOf(fechaNueva);
    }

}
