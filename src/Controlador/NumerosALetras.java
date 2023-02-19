//Numeros a letras Interno
/*
Codigo:
MANFREDITO
1234567890
*/
package Controlador;

public class NumerosALetras {
    
    public String Convertir(String Valor){
        String Resultado="";
        for (int i = 0; i < Valor.length(); i++) {
            String Entrada = String.valueOf(Valor.charAt(i));
            switch (Entrada) {
                case "1" -> Resultado += "M";
                case "2" -> Resultado += "A";
                case "3" -> Resultado += "N";
                case "4" -> Resultado += "F";
                case "5" -> Resultado += "R";
                case "6" -> Resultado += "E";
                case "7" -> Resultado += "D";
                case "8" -> Resultado += "I";
                case "9" -> Resultado += "T";
                case "0" -> Resultado += "O";
                case "." -> Resultado += "-";
                case " " -> Resultado += "-";
                default -> System.out.println("Error");
            }
            
        }
        return Resultado;
    }
}
