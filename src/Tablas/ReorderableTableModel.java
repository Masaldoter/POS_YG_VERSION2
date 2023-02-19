/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tablas;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaldoter
 */
public class ReorderableTableModel extends DefaultTableModel implements Reorderable {
  public void reorder(int from, int to) {
    Object o = getDataVector().remove(from);
    getDataVector().add(to, (Vector) o);
    fireTableDataChanged();
  }
}
