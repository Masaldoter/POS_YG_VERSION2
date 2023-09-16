//Numeros a letras Interno
/*
Codigo:
MANFREDITO
1234567890
*/
package Controlador;

import CLASES_GLOBALES.PARAMETROS_EMPRESA;

public class NumerosALetras {
    
    public String Convertir(String Valor){
        String Resultado="";
        for (int i = 0; i < Valor.length(); i++) {
            String Entrada = String.valueOf(Valor.charAt(i));
            switch (Entrada) {
                case "1" -> Resultado += PARAMETROS_EMPRESA.UNO;
                case "2" -> Resultado += PARAMETROS_EMPRESA.DOS;
                case "3" -> Resultado += PARAMETROS_EMPRESA.TRES;
                case "4" -> Resultado += PARAMETROS_EMPRESA.CUATRO;
                case "5" -> Resultado += PARAMETROS_EMPRESA.CINCO;
                case "6" -> Resultado += PARAMETROS_EMPRESA.SEIS;
                case "7" -> Resultado += PARAMETROS_EMPRESA.SIETE;
                case "8" -> Resultado += PARAMETROS_EMPRESA.OCHO;
                case "9" -> Resultado += PARAMETROS_EMPRESA.NUEVE;
                case "0" -> Resultado += PARAMETROS_EMPRESA.CERO;
                case "." -> Resultado += "-";
                case " " -> Resultado += "-";
                default -> System.out.println("Error");
            }
            
        }
        return Resultado;
    }
}
