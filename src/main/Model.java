/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package main;

import graph.MyGraph;
import java.awt.Dimension;
import java.util.Observable;
import rules.Rule;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class Model implements Runnable {

    private Rule rule;
    private View view;
    private MyGraph graph;

    public Model(){
        this.graph = new MyGraph();
        this.view = new View(graph);
        this.rule = new Rule(null, view);
        this.graph.setContainer(view);        
        this.graph.init();
    }
    
    public static void main(String[] args) {
        Model model = new Model();
        
        InterfaceJFrame itf = new InterfaceJFrame(model, "Test");

        itf.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        itf.setMinimumSize(new Dimension(600, 450));
        itf.setVisible(true);

        new Thread(model).start();
    }

    @Override
    public void run() {
        int updateRes = this.graph.updateRuleChanged(rule);        
    }
    
    
    
    
    
    
    
    

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public MyGraph getGraph() {
        return graph;
    }

    public void setGraph(MyGraph graph) {
        this.graph = graph;
    }
}
