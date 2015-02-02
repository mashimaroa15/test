/*
 * Mise en place d’un langage graphique pour l’éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package myComponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyTable extends JTable {

    public MyTable() {
        super();
        DefaultTableModel tm = new DefaultTableModel(
                new Object[][] {{null,null,null}, {"test",null,null}},
                new String [] {"Nom", "Type", "Brève Description"}
        );
        this.setModel(tm);
        this.setDragEnabled(true);
        this.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        String[] row1 = new String[]{"E0", "Action de l'utilisateur", "Clic sur le composant 36"};
        updateRow(this, 1, row1);
    }
    
    private void updateRow(JTable table, int numRow, String[] data) {
        if (data.length > table.getColumnCount()) {
            throw new IllegalArgumentException("data is too long");
        }
        for (int j = 0; j < data.length; j++) {
            table.setValueAt(data[j], numRow - 1, j);
        }
    }
}
