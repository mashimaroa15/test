/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package myComponent;

import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import rules.Rule;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyCanvas extends MyPanel {
    Rule rule;

    /**
     * Creates new form MyCanvas
     */
    public MyCanvas() {
        MyPanelEvent pnlEvent = new MyPanelEvent(rule);
        pnlEvent.setPreferredSize(new Dimension(200, 100));
        pnlEvent.setOpaque(true);
//        MyPanel pnlCond = new MyPanel();
//        MyPanel pnlAction = new MyPanel();
//        MyPanel pnlEnd = new MyPanel();
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        pnlEvent.setPreferredSize(new Dimension(200, 200));
        this.add(pnlEvent);
//        this.add(pnlCond);
//        this.add(pnlAction);
//        this.add(pnlEnd);
        
        this.setVisible(true);
        this.repaint();
        this.setOpaque(true);
        
    }
    
    public void setRegle(Rule rule){
        
    }
}
