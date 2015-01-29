/*
 * Mise en place d’un langage graphique pour l’éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package myComponent;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JPanel;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyPanel extends JPanel implements Transferable {

    public MyPanel() {
        super();
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return null;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor df) {
        return false;
    }

    @Override
    public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
        return null;
    }

}
