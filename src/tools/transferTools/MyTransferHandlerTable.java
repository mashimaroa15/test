/*
 * Mise en place d’un langage graphique pour l’éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package tools.transferTools;

import graph.MyGraph;
import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import myComponent.MyTable;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyTransferHandlerTable extends TransferHandler {

    public MyTransferHandlerTable(){
        super();
        try {
            Image img;
            img = ImageIO.read(new File("src/img/eventActive.png"));
            img = img.getScaledInstance(MyGraph.EVT_W, MyGraph.EVT_H, Image.SCALE_SMOOTH);
            super.setDragImage(img);
            super.setDragImageOffset(new Point(20,20));
        } catch (IOException ex) {
            Logger.getLogger(MyTransferHandlerTable.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void exportDone(JComponent jc, Transferable t, int i) {
        super.exportDone(jc, t, i); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Transferable createTransferable(JComponent jc) {
        System.out.println("Transferable object Table created !");
        
        MyTable tab = (MyTable)jc;
        String val = (String) tab.getValueAt(tab.getSelectedRow(), 0);
        System.out.println("Click on item that id is : " + val);
        DataEvtTransferable tf;
        int row = tab.getSelectedRow();
        tf = new DataEvtTransferable((String) tab.getValueAt(row, 0),
                (String) tab.getValueAt(row, 1),
                (String) tab.getValueAt(row, 2));
        return tf;
    }

    @Override
    public int getSourceActions(JComponent jc) {
        return LINK;
    }
}
