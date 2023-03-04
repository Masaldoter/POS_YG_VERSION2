/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CLASES_GLOBALES;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author MASALDOTER_GT
 */
public class MyCellRenderer extends DefaultTableCellRenderer {
    public boolean VALIDAR=true;
    
   @Override
   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      if (VALIDAR==true) {
         c.setForeground(Color.RED);
      } else {
         c.setForeground(Color.GREEN);
      }
      return c;
   }
}
