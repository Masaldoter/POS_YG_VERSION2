/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import javax.swing.UIManager;

/**
 *
 * @author MASALDOTER_GT
 */
public class INICIO {
   public static void main(String[] args) {
        LICENCIA L = new LICENCIA();
        try {
            // Establecemos el "look and feel" que deseamos usar.
            // Puedes elegir el que mejor se adapte a tu aplicación.
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
