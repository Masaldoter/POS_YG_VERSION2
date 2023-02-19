    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Masaldoter
 */
public class RenderTablasJLabel extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value
            , boolean isSelected, boolean hasFocus, int row, int column) {
        
        if(value instanceof JLabel){
            JLabel btn= (JLabel)value;
            return btn;
        }
        if(value instanceof JButton){
            JButton boton= (JButton)value;
            return boton;
        }
        return super.getTableCellRendererComponent(table, value, isSelected, 
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
        
    }
    
}
