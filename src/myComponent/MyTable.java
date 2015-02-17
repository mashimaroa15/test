/*
 * Mise en place d’un langage graphique pour l’éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package myComponent;

import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;
import tools.transferTools.DataEvtTransferable;

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
        
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent me) {
                System.out.println("Clic sur le Table");
                
            }

            @Override
            public void mouseDragged(MouseEvent me) {
                System.out.println("Mouse Dragged from MyTable");
                JComponent tmp = (JComponent)me.getSource();
                TransferHandler handler = tmp.getTransferHandler();
                handler.exportAsDrag(tmp, me, TransferHandler.LINK); 
                //LINK permet de relier les données même après le transfert 
                //et non seulement les cloner
            }
            
        });
        
    }
    
    private void updateRow(JTable table, int numRow, String[] data) {
        if (data.length > table.getColumnCount()) {
            throw new IllegalArgumentException("data is too long");
        }
        for (int j = 0; j < data.length; j++) {
            table.setValueAt(data[j], numRow - 1, j);
        }
    }
    
    public Transferable getTransferable() {
        DataEvtTransferable tf;
        int row = this.getSelectedRow();
        tf = new DataEvtTransferable((String) this.getValueAt(row, 0),
                (String) this.getValueAt(row, 1),
                (String) this.getValueAt(row, 2));
        return tf;
    }
}
