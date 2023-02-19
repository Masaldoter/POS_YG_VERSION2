/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.KeyStroke;

/**
 *
 * @author aldoy
 */
public class ATAJOSDETECLADO{

public static void clickOnKey(
    final AbstractButton button, String actionName, int key )
{
    button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW )
        .put( KeyStroke.getKeyStroke( key, 0 ), actionName );

    button.getActionMap().put( actionName, new AbstractAction()
    {
        @Override
        public void actionPerformed( ActionEvent e )
        {
            button.doClick();
        }
    } );
}
}