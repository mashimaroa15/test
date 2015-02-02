/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package myComponent;

import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import rules.Event;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyPanelEvent extends MyPanel {
    
    private MyComponentEvent evt;
    private JComboBox<Event> cb;

    /**
     * Constructeur par défaut (pour exemple)
     */
    public MyPanelEvent() {
        ArrayList<Event> listE = exListEvent();
        this.evt = new MyComponentEvent(listE.get(0));
        this.cb = exCBEvent(listE);      
    }
    
    /**cc
     * Constructeur avec paramètres
     * @param evt
     * @param cb
     */
    public MyPanelEvent(MyComponentEvent evt, JComboBox cb){
        super();
        this.evt = evt;
        this.cb = cb;
        
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setBorder(null);
        this.add(evt);
        this.add(cb);       
    }
    
    /**
     *  Créer un exemple d'une liste des évènements (3 évènements par défaut)
     * @return
     */
    public static ArrayList<Event> exListEvent(){
        ArrayList<Event> res = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String nameE = "E" + i;
            String descE = "Clic sur le bouton 7" + i;
            res.add(new Event(i, nameE, 1, descE));
        }        
        
        return res;
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

    public JComboBox<Event> getCb() {
        return cb;
    }

    public void setCb(JComboBox<Event> cb) {
        this.cb = cb;
    }
}
