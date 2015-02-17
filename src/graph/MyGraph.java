/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */
package graph;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import main.View;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxMultiplicity;
import java.util.HashMap;
import java.util.Iterator;
import rules.Rule;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyGraph extends mxGraph {

    public static final int CELLS_Y_DEFAULT = 100;
    public static final int CELLS_LABEL_OFFSET = 35;

    public static final int EVT_X = 100;
    public static final int EVT_W = 56;
    public static final int EVT_H = 50;

    public static final int COND_X = 200;
    public static final int COND_W = 42;
    public static final int COND_H = 50;

    public static final int ACT_X = 300;
    public static final int ACT_W = 51;
    public static final int ACT_H = 50;

    public static final int END_X = 400;
    public static final int END_W = 56;
    public static final int END_H = 50;
    
    //style pour les icons
    public static final String ICON_EVT_ACTIVE = "shape=image;image=/img/eventActive.png";
    public static final String ICON_EVT_INACTIVE = "shape=image;image=/img/eventInactive.png";
    public static final String ICON_COND_ACTIVE = "shape=image;image=/img/conditionActive.png";
    public static final String ICON_COND_INACTIVE = "shape=image;image=/img/conditionInactive.png";
    public static final String ICON_ACT_ACTIVE = "shape=image;image=/img/actionActive.png";
    public static final String ICON_ACT_INACTIVE = "shape=image;image=/img/actionInactive.png";
    
    //status d'un noeud
    public static final boolean VERTEX_ACTIVE = true;
    public static final boolean VERTEX_INACTIVE = false;

    Object parent;
    View container;
    
    //Map pour les listes des composants
    HashMap<String, mxCell> listEvt;
    HashMap<String, mxCell> listCond;
    HashMap<String, mxCell> listAct;
    HashMap<String, mxCell> listEnd;

    /**
     * Constructeur par défaut
     */
    public MyGraph() {
        super();
    }

    /**
     * Initialisation, création de 4 noeuds inactifs
     */
    public void init() {
        this.setCellsDisconnectable(false);
        this.setCellsEditable(false);
        this.setCellsResizable(false);
        this.setCellsMovable(false);

        this.parent = this.getDefaultParent();
        
        this.listEvt = new HashMap<>();
        this.listCond = new HashMap<>();
        this.listAct = new HashMap<>();
        this.listEnd = new HashMap<>();

        this.getModel().beginUpdate();
        try {
            //bouton au-dessus
            mxCell v0 = (mxCell) this.insertVertex(parent, null, "Hit this button to read graph from xml",
                    200, 10, 200, 30);
            
            

            //les 4 noeuds
            mxCell v1 = (mxCell) this.insertVertex(parent, "E0", "E0", EVT_X, CELLS_Y_DEFAULT, EVT_W, EVT_H,
                    ICON_EVT_INACTIVE);
            listEvt.put(v1.getId(), v1);
            
            mxCell v2 = (mxCell) this.insertVertex(parent, "C0", "C0", COND_X, CELLS_Y_DEFAULT, COND_W, COND_H,
                    ICON_COND_INACTIVE);
            listCond.put(v2.getId(), v2);
            
            mxCell v3 = (mxCell) this.insertVertex(parent, "A0", "A0", ACT_X, CELLS_Y_DEFAULT, ACT_W, ACT_H,
                    ICON_ACT_INACTIVE);
            listAct.put(v3.getId(), v3);
            
            mxCell v4 = (mxCell) this.insertVertex(parent, "E1", "E1", END_X, CELLS_Y_DEFAULT, END_W, END_H,
                    ICON_EVT_INACTIVE);
            listEnd.put(v4.getId(), v4);

            //set offset pour les étiquettes
            this.getCellGeometry(v1).setOffset(new mxPoint(0, CELLS_LABEL_OFFSET));
            this.getCellGeometry(v2).setOffset(new mxPoint(0, CELLS_LABEL_OFFSET));
            this.getCellGeometry(v3).setOffset(new mxPoint(0, CELLS_LABEL_OFFSET));
            this.getCellGeometry(v4).setOffset(new mxPoint(0, CELLS_LABEL_OFFSET));

            //les flèches
            Object e12 = this.insertEdge(parent, null, "", v1, v2);
            Object e23 = this.insertEdge(parent, null, "", v2, v3);
            Object e34 = this.insertEdge(parent, null, "", v3, v4);
        } catch (Exception e) {
            System.err.println("Erreur init graph");
        } finally {
            this.getModel().endUpdate();
        }
    }

    /**
     * Lire la règle en paramètre et reconstruire le graphe
     * @param rule
     * @return return 1 si OK, -1 si problème
     */
    public int update(Rule rule) {
        this.getModel().beginUpdate();
        try {
            mxCell tmp;
            //Evènement déclencheur
            if(rule.getEvenementDeclencheur() != null && rule.getAction() != null){
//                ((mxGraphModel)this.getModel()).clear();    //vider le graphe
                this.removeCells(this.getChildCells(parent));
//                this.refresh();
                this.listEvt.clear();
                this.listCond.clear();
                this.listAct.clear();
                this.listEnd.clear();
                for (int i = 0; i < rule.getEvenementDeclencheur().size(); i++) {
                    String id = "E" + i;
                    listEvt.put(id, (mxCell) this.insertVertex(parent, id, id, EVT_X, CELLS_Y_DEFAULT, 
                            EVT_W, EVT_H, ICON_EVT_ACTIVE));
                }
            } else {
                System.out.println("Pas de modification, Regle invalide");
                return -1;
            }
            if(rule.getCondition() != null){
                for (int i = 0; i < rule.getCondition().size(); i++) {
                    String id = "C" + i;
                    tmp = (mxCell) this.insertVertex(parent, id, id, COND_X, CELLS_Y_DEFAULT + 70*i, 
                            COND_W, COND_H, ICON_COND_ACTIVE);
                    listCond.put(id, tmp);
                    String idEdge = "e1"+i;
                    this.insertEdge(parent, idEdge, "", listEvt.get("E0"), tmp);
                }
            }
            if(rule.getAction()!= null){
                for (int i = 0; i < rule.getAction().size(); i++) {
                    String id = "A" + i;
                    tmp = (mxCell) this.insertVertex(parent, id, id, ACT_X, CELLS_Y_DEFAULT + 70*i, 
                            ACT_W, ACT_H, ICON_ACT_ACTIVE);
                    listAct.put(id, tmp);
                    String idEdge = "e2"+i;
                    this.insertEdge(parent, idEdge, "", listCond.get("C0"), tmp);
                }
            }
            if(rule.getEvenementFin()!= null){
                for (int i = 0; i < rule.getEvenementFin().size(); i++) {
                    String id = "End" + i;
                    tmp = (mxCell) this.insertVertex(parent, id, id, END_X, CELLS_Y_DEFAULT, 
                            END_W, END_H, ICON_EVT_ACTIVE);
                    listEnd.put(id, tmp);
                }
                int i = 0;
                for (Iterator<String> it = listAct.keySet().iterator(); it.hasNext();) {
                    String key = it.next();
                    String idEdge = "e3"+i;
                    this.insertEdge(parent, idEdge, "", listAct.get(key), listEnd.get("End0"));
                    i++;
                }
            }
            for (Object cell : this.getChildVertices(parent)) {
                this.getCellGeometry(cell).setOffset(new mxPoint(0, CELLS_LABEL_OFFSET));
            }
        } catch (Exception e) {
            System.err.println("Erreur update graph");
        } finally {
            this.getModel().endUpdate();
//            this.refresh();
        }
        return 1;
    }

    /**
     * Changer l'état d'un noeud de actif - inactif ou l'inverse
     * @param c
     * @param stat
     */
    public void toggleVertex(mxCell c, boolean stat) {
        String id = c.getId();
        String type = id.substring(0, 1);
        switch (type) {
            case "E":
                if (stat) {
                    c.setStyle(ICON_EVT_ACTIVE);
                } else {
                    c.setStyle(ICON_EVT_INACTIVE);
                }
                break;
            case "C":
                if (stat) {
                    c.setStyle(ICON_COND_ACTIVE);
                } else {
                    c.setStyle(ICON_COND_INACTIVE);
                }
                break;
            case "A":
                if (stat) {
                    c.setStyle(ICON_ACT_ACTIVE);
                } else {
                    c.setStyle(ICON_ACT_INACTIVE);
                }
                break;

        }
    }
    
    
    

    
    
    
    
    
    
    
    
    public Object getParent() {
        return parent;
    }

    public void setParent(Object parent) {
        this.parent = parent;
    }

    public View getContainer() {
        return container;
    }

    public void setContainer(View container) {
        this.container = container;
    }
}
