/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package main;

import com.mxgraph.swing.handler.mxGraphHandler;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import graph.MyGraph;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.TransferHandler;
import rules.Rule;
import tools.transferTools.MyGraphHandler;
import tools.transferTools.MyTransferHandlerGraph;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class View extends mxGraphComponent implements Observer {
    
    Model model;
    MyGraph graph;
    Rule rule;

    public View(mxGraph graph, Model model, Rule rule) {
        super(graph);
        this.graph = (MyGraph)graph;
        this.model = model;
        this.rule = rule;
        
        this.setConnectable(false);
        this.setMinimumSize(new Dimension(400, 300));
    }

    @Override
    public void update(Observable o, Object o1) {
        
    
    }

    @Override
    protected TransferHandler createTransferHandler() {
        return new MyTransferHandlerGraph();
    }
    
    @Override
    protected mxGraphHandler createGraphHandler()
	{
            return new MyGraphHandler(this);
		
	}
    
}
