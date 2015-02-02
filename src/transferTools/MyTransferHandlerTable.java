/*
 * Mise en place d’un langage graphique pour l’éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package transferTools;

import java.awt.Image;
import java.awt.Point;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class MyTransferHandlerTable extends TransferHandler implements MouseListener {

    public MyTransferHandlerTable(){
        super();
        try {
            Image img;
            img = ImageIO.read(new File("src/img/event.png"));
            super.setDragImage(null);
            super.setDragImageOffset(new Point(-10, -10));
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
        System.out.println("transferable object Table created !");
        
        MyTable tab = (MyTable)jc;
        return new StringSelection("new content");
    }

    @Override
    public int getSourceActions(JComponent jc) {
        return super.getSourceActions(jc); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println("Mouse clicked on Table !!!");
        JComponent tab = (JComponent)me.getSource();
        TransferHandler th = tab.getTransferHandler();
        th.exportAsDrag(tab, me, COPY);
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
    
}
