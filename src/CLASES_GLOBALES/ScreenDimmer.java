/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScreenDimmer {
    private JFrame window;

    public ScreenDimmer(JFrame window) {
        this.window = window;
    }

    public void dimScreen() {
        // Oculta la ventana actual
        window.setVisible(false);

        // Crea una nueva ventana de oscurecimiento
        JFrame dimFrame = new JFrame();
        dimFrame.setUndecorated(true);
        dimFrame.setBackground(new Color(0, 0, 0, 150)); // Ajusta el color y la transparencia del oscurecimiento
        dimFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        dimFrame.setLocationRelativeTo(null);
        dimFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Hace visible la nueva ventana de oscurecimiento
        dimFrame.setVisible(true);

        // Muestra la ventana original nuevamente al cerrar la ventana de oscurecimiento
        dimFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dimFrame.dispose();
                window.setVisible(true);
                window.toFront();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        ScreenDimmer screenDimmer = new ScreenDimmer(frame);
        screenDimmer.dimScreen();
    }
}




