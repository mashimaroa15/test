/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */
package tools.transferTools;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author 
 */
public class DataCondTransferable implements Transferable {
    
    public static DataFlavor FLAVOR = new DataFlavor(DataEvt.class,"DataEvt");
    
    DataFlavor flavors[];
    DataEvt dataEvt;    

    public DataCondTransferable(String id, String type, String desc) {
        this.dataEvt = new DataEvt(id, type, desc);
        this.flavors = new DataFlavor[] { FLAVOR };
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return flavors;    
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if(flavor.equals(FLAVOR)){
            return dataEvt;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
