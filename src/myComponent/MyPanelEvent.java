/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package myComponent;

import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import main.Model;
import rules.Event;
import rules.Rule;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyPanelEvent extends MyPanel {
    
    private MyComponentEvent evt;
    private JComboBox<String> cb;
    
    /**
     * Constructeur avec paramètres
     * @param evt
     * @param cb
     */
    public MyPanelEvent(Rule rule){
        super();
        this.evt = new MyComponentEvent();
        
        this.cb = new JComboBox<>(new String[]{"E0", "E1"});
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(null);
        this.add(evt);
        this.add(cb);    
    }
    
    /**
     * Créer un exemple d'un JComboBox pour ajouter dans MyPanelEvent
     * @return
     */
    public static JComboBox<Event> exCBEvent(ArrayList<Event> listE){
        JComboBox<Event> res = new JComboBox<>();
        for (Event listE1 : listE) {
            res.addItem(listE1);
        }
        return res;
    }

    public MyComponentEvent getEvt() {
        return evt;
    }

    public void setEvt(MyComponentEvent evt) {
        this.evt = evt;
    }

}
