/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */
package tools.transferTools;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import graph.MyGraph;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import myComponent.MyTable;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyTransferHandlerGraph extends com.mxgraph.swing.handler.mxGraphTransferHandler {

    @Override
    public boolean importData(JComponent c, Transferable t) {

        boolean result = true;
        System.out.println("Begin to import...");
//        if (!isLocalDrag()) {
//            result = false;
//        } else {
        try {
            updateImportCount(t);

            if (c instanceof mxGraphComponent) {
                mxGraphComponent graphComponent = (mxGraphComponent) c;

                if (graphComponent.isEnabled()
                        && t.isDataFlavorSupported(DataEvtTransferable.FLAVOR)) {
                    DataEvt gt = (DataEvt) t.getTransferData(DataEvtTransferable.FLAVOR);

                    if (gt.getId() != null) {
                        mxGraphComponent comp = (mxGraphComponent) c;

                        Point mousePos = c.getMousePosition();

                        mxCell cellToModify = (mxCell) comp.getCellAt((int) mousePos.getX(), (int) mousePos.getY());

                        comp.getGraph().getModel().beginUpdate();

                        cellToModify.setValue(gt.getId());
                        cellToModify.setStyle(MyGraph.ICON_EVT_ACTIVE);

                        comp.getGraph().getModel().endUpdate();
                        comp.getGraph().refresh();
                        //result = importGraphTransferable(graphComponent, gt);
                        System.out.println("Import completed : " + gt.getId());
                    }

                }
            }
        } catch (UnsupportedFlavorException | IOException | HeadlessException ex) {
            ex.printStackTrace();
        }
//        }

        return result;

    }

    @Override
    public boolean canImport(JComponent comp, DataFlavor[] flavors) {
        //prendre la position de la souris
        Point mousePos = comp.getMousePosition();
        mxGraphComponent c = (mxGraphComponent) comp;
        Object o = c.getCellAt((int) mousePos.getX(), (int) mousePos.getY());
        if (o == null) {
            return false;
        }
        mxCell cell = (mxCell) o;
        if (cell == null) {
            System.out.println("Cell null cannot import");
            return false;
        } else {
            if (cell.isEdge()) {
                System.out.println("Edge, cannot import");
                return false;
            }
        }
        //vérifie si objet transféré est de type DavaEvt
        for (int i = 0; i < flavors.length; i++) {
            if (flavors[i] != null
                    && flavors[i].equals(DataEvtTransferable.FLAVOR)) {
                System.out.println("canImport OK");
                return true;
            }
        }
        System.out.println("cannot Import");
        return false;
    }

    @Override
    public Transferable createTransferable(JComponent jc) {
        DataVertexTransferable tf = null;
        Point mousePos = jc.getMousePosition();
        mxGraphComponent comp = (mxGraphComponent) jc;
        Object o = comp.getCellAt((int) mousePos.getX(), (int) mousePos.getY());
        

        return tf;
    }
}
