/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */
package tools.transferTools;

import com.mxgraph.swing.handler.mxGraphHandler;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.util.mxGraphTransferable;
import com.mxgraph.swing.util.mxSwingConstants;
import com.mxgraph.view.mxGraph;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceAdapter;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyGraphHandler extends mxGraphHandler {
    
    String filePath = "src/examples/ex.xml";

    public MyGraphHandler(mxGraphComponent graphComponent) {
        super(graphComponent);
    }

    @Override
    public void dragEnter(DropTargetDragEvent e) {
        JComponent component = getDropTarget(e);
        TransferHandler th = component.getTransferHandler();
        boolean isLocal = th instanceof com.mxgraph.swing.handler.mxGraphTransferHandler
                && ((com.mxgraph.swing.handler.mxGraphTransferHandler) th).isLocalDrag();

        canImport = true;

        if (canImport) {
            transferBounds = null;
            setVisible(false);

            try {
                Transferable t = e.getTransferable();

                if (t.isDataFlavorSupported(mxGraphTransferable.dataFlavor)) {
                    mxGraphTransferable gt = (mxGraphTransferable) t
                            .getTransferData(mxGraphTransferable.dataFlavor);
                    dragCells = gt.getCells();

                    if (gt.getBounds() != null) {
                        mxGraph graph = graphComponent.getGraph();
                        double scale = graph.getView().getScale();
                        transferBounds = gt.getBounds();
                        int w = (int) Math.ceil((transferBounds.getWidth() + 1)
                                * scale);
                        int h = (int) Math
                                .ceil((transferBounds.getHeight() + 1) * scale);
                        setPreviewBounds(new Rectangle(
                                (int) transferBounds.getX(),
                                (int) transferBounds.getY(), w, h));

                        if (imagePreview) {
                            // Does not render fixed cells for local preview
                            // but ignores movable state for non-local previews
                            if (isLocal) {
                                if (!isLivePreview()) {
                                    updateDragImage(graph
                                            .getMovableCells(dragCells));
                                }
                            } else {
                                Object[] tmp = graphComponent
                                        .getImportableCells(dragCells);
                                updateDragImage(tmp);

                                // Shows no drag icon if import is allowed but none
                                // of the cells can be imported
                                if (tmp == null || tmp.length == 0) {
                                    canImport = false;
                                    e.rejectDrag();

                                    return;
                                }
                            }
                        }

                        setVisible(true);
                    }
                }

                e.acceptDrag(TransferHandler.COPY_OR_MOVE);
            } catch (UnsupportedFlavorException | IOException ex) {
                // do nothing
                ex.printStackTrace();
            }

        } else {
            e.rejectDrag();
        }
    }

    @Override
    protected void installDragGestureHandler() {
        DragGestureListener dragGestureListener = new DragGestureListener() {
            @Override
            public void dragGestureRecognized(DragGestureEvent e) {
                if (graphComponent.isDragEnabled() && first != null) {
                    final TransferHandler th = graphComponent
                            .getTransferHandler();

                    if (th instanceof MyTransferHandlerGraph) {
                        final DataEvtTransferable t = (DataEvtTransferable) ((MyTransferHandlerGraph) th)
                                .createTransferable(graphComponent);

                        if (t != null) {
                            e.startDrag(null, mxSwingConstants.EMPTY_IMAGE,
                                    new Point(), t, new DragSourceAdapter() {

                                        /**
                                         *
                                         */
                                        @Override
                                        public void dragDropEnd(
                                                DragSourceDropEvent dsde) {
                                                    ((MyTransferHandlerGraph) th)
                                                    .exportDone(
                                                            graphComponent,
                                                            t,
                                                            TransferHandler.NONE);
                                                    first = dsde.getLocation();
                                                }
                                    });
                        }
                    }
                }
            }
        };

        DragSource dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(graphComponent
                .getGraphControl(),
                (isCloneEnabled()) ? DnDConstants.ACTION_COPY_OR_MOVE
                        : DnDConstants.ACTION_MOVE, dragGestureListener);
    }
    
    public void updateXML() {
        
    }
    
}
