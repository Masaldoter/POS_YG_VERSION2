/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

import java.text.DecimalFormat;

/**
 *
 * @author aldoy
 */
public class PARAMETROS_EMPRESA {
   public static String NOMBRE_EMPRESA, NIT_EMPRESA, DIRECCION_EMPRESA, TEL_EMPRESA, ESLOGAN_EMPRESA, POLITICAS_EMPRESA, CORREO_EMPRESA,
            CONTRASENIA_EMPRESA, IVA_EMPRESA, NOMBRE_EN_ETIQUETA_EMPRESA, MUNICIPIO_EMPRESA, DEPARTAMENTO_EMPRESA, PAIS_EMPRESA,
            CODIGOPOSTAL_EMPRESA, AFILICACIONIVA_EMPRESA, PROPIETARIO_EMPRESA, CODIGOESTABLECIMIENTO_EMPRESA, RUTADEIMAGEN_DOCUMENTOS_EMPRESA, RUTADEIMAGEN_DOCUMENTOS_EMPRESA2,
            RUTADEIMAGEN_SISTEMA_EMPRESA, ClaveInternaCostos;
    public static String MONEDA = "QUETZALES", SIGNO_MONEDA = "Q";
    public static DecimalFormat formatea = new DecimalFormat("###,###.##");
    public static String NOMBRE_CERTIFICADOR, NIT_CERTIFICADOR, TELEFONO_CERTIFICADOR, CORREO_CERTIFICADOR, USUARIO_CERTIFICADOR, CONTRASENIA_CERTIFICADOR, TOKEN_CERTIFICADOR;

    public static String UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, CERO;
    
    static char[] letras;
    
    public static void ACTUALIZAR_CLAVE(){
        letras = new char[ClaveInternaCostos.length()];
         for (int i = 0; i < ClaveInternaCostos.length(); i++) {
            letras[i] = ClaveInternaCostos.charAt(i);
        } 
       
       UNO = String.valueOf(letras[0]);
       DOS = String.valueOf(letras[1]);
       TRES = String.valueOf(letras[2]);
       CUATRO = String.valueOf(letras[3]);
       CINCO = String.valueOf(letras[4]);
       SEIS = String.valueOf(letras[5]);
       SIETE = String.valueOf(letras[6]);
       OCHO = String.valueOf(letras[7]);
       NUEVE = String.valueOf(letras[8]);
       CERO = String.valueOf(letras[9]);
       
    }
    

}
