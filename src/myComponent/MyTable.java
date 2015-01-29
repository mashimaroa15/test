/*
 * Mise en place d’un langage graphique pour l’éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package myComponent;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyTable extends JTable implements MouseListener, MouseMotionListener, Transferable {

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

    //MOUSE LISTENER
    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    // MOUSE MOTION LISTENER
    @Override
    public void mouseDragged(MouseEvent me) {
        System.out.println("mouse dragged at " + me.getX() + ":" + me.getY());
    }

    @Override
    public void mouseMoved(MouseEvent me) {

    }

    // TRANSFERABLE
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor df) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
