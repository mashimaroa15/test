/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */
package graph;

import com.mxgraph.model.mxCell;
import main.View;
import com.mxgraph.util.mxPoint;
import com.mxgraph.view.mxGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import org.jdom.Element;
import rules.Action;
import rules.Condition;
import rules.Event;
import rules.Rule;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyGraph extends mxGraph implements Observer {

    public static int CELLS_Y_DEFAULT = 50;
    public static int CELLS_Y_SPACE = 80;
    public static int CELLS_X_SPACE = 80;
    public static int CELLS_LABEL_OFFSET = 35;

    public static int EVT_X = 50;
    public static int EVT_W = 40;
    public static int EVT_H = 40;

    public static int COND_X = EVT_X + 100;
    public static int COND_W = 34;
    public static int COND_H = 40;

    public static int ACT_X = COND_X + 100;
    public static int ACT_W = 40;
    public static int ACT_H = 40;

    public static int ACT_X_SPACE = 0;
    public static int END_X = ACT_X + 100;
    public static int END_W = 40;
    public static int END_H = 40;

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
    ArrayList<String> listEvtId;
    HashMap<String, mxCell> listCond;
    ArrayList<String> listCondId;
    HashMap<String, mxCell> listAct;
    ArrayList<String> listActId;
    HashMap<String, mxCell> listEnd;
    ArrayList<String> listEndId;

    ArrayList<String> listEdgeId;

    HashMap<String, mxCell> listLastAct;
    ArrayList<String> listLastActId;

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
        this.listEvtId = new ArrayList<>();
        this.listCond = new HashMap<>();
        this.listCondId = new ArrayList<>();
        this.listAct = new HashMap<>();
        this.listActId = new ArrayList<>();
        this.listEnd = new HashMap<>();
        this.listEndId = new ArrayList<>();

        this.listEdgeId = new ArrayList<>();

        this.listLastAct = new HashMap<>();
        this.listLastActId = new ArrayList<>();

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
     *
     * @param rule
     * @return return 1 si OK, -1 si problème
     */
    public int updateRuleChanged(Rule rule) {
        this.getModel().beginUpdate();
        try {
            mxCell tmp;
            //remettre à défaut les distances
            resetDistance();
            //Evènements déclencheurs
            if (rule.getEvenementDeclencheur() != null && rule.getAction(rule.getCondition(), -1) != null) {
                this.removeCells(this.getChildCells(parent));
                this.listEvt.clear();
                this.listEvtId.clear();
                this.listCond.clear();
                this.listCondId.clear();
                this.listAct.clear();
                this.listActId.clear();
                this.listEnd.clear();
                this.listEndId.clear();
                this.listEdgeId.clear();
                this.listLastAct.clear();
                this.listLastActId.clear();
                int spaceYEvtStandby = 0;

                for (int i = 0; i < rule.getEvenementDeclencheur().size(); i++) {
                    Event evt;
                    String id = randomID();
                    Element elem = rule.getEvenementDeclencheur().get(i);
                    String type = rule.getEvenementDeclencheur().get(i).getAttributeValue("type");
                    if (type.equalsIgnoreCase(Event.TYPE_LANCE_ASSIST) || type.equalsIgnoreCase(Event.TYPE_LANCE_REGLE)) {
                        String idComp = rule.getEvenementDeclencheur().get(i).getAttributeValue("idComp");
                        evt = new Event(null, type, idComp, elem);
                    } else {
                        String idEve = rule.getEvenementDeclencheur().get(i).getAttributeValue("idEve");
                        String idComp = rule.getEvenementDeclencheur().get(i).getAttributeValue("idComp");
                        evt = new Event(idEve, type, idComp, elem);
                    }
                    tmp = (mxCell) this.insertVertex(parent, id, evt, EVT_X, CELLS_Y_DEFAULT + CELLS_Y_SPACE * i,
                            EVT_W, EVT_H, ICON_EVT_ACTIVE);
                    listEvt.put(id, tmp);
                    listEvtId.add(id);
                    spaceYEvtStandby += CELLS_Y_SPACE;
                }
                Event evtStandby = new Event(null, "Ajouter\nEvt Declencheur", null, rule.getRule());
                String id = randomID();
                listEvt.put(id, (mxCell) this.insertVertex(parent, id, evtStandby, EVT_X, CELLS_Y_DEFAULT + spaceYEvtStandby,
                        EVT_W, EVT_H, ICON_EVT_INACTIVE));
                listEvtId.add(id);
            } else {
                System.err.println("Pas de modification, Regle invalide");
                return -1;
            }

            //Conditions
            if (rule.getCondition() != null) {
                for (int i = 0; i < rule.getCondition().size(); i++) {
                    String id = randomID();
                    Element elem = rule.getCondition().get(i);
                    Condition cond;
                    if (elem.getAttributeValue("condition").equals("")) {
                        cond = new Condition("Condition", null, null, elem);
                    } else {
                        cond = new Condition(elem.getAttributeValue("condition"), null, null, elem);
                    }
                    tmp = (mxCell) this.insertVertex(parent, id, cond, COND_X, CELLS_Y_DEFAULT + CELLS_Y_SPACE * i,
                            COND_W, COND_H, ICON_COND_ACTIVE);
                    listCond.put(id, tmp);
                    listCondId.add(id);
                    String idEdge = "e" + randomID();
                    this.listEdgeId.add(idEdge);
                    for (Iterator<String> iterator = this.listEvtId.iterator(); iterator.hasNext();) {
                        String next = iterator.next();
                        this.insertEdge(parent, idEdge, "", listEvt.get(next), tmp);
                    }
                }
            }

            //Actions
            if (rule.getCondition() != null) {
                for (int iteCond = 0; iteCond < this.listCondId.size(); iteCond++) {
                    mxCell tmpCond = this.listCond.get(this.listCondId.get(iteCond));
                    if (rule.getAction(rule.getCondition(), iteCond) != null) {
                        for (int i = 0; i < rule.getAction(rule.getCondition(), iteCond).size(); i++) {
                            mxCell tmpLastAct = null;
                            int spaceActStandby = 0;
                            for (int j = 0; j < rule.getAction(rule.getCondition(), iteCond).get(i).getChildren("action").size(); j++) {
                                String id = randomID();
                                String idAct = ((Element) rule.getAction(rule.getCondition(), iteCond).get(i).getChildren("action").get(j)).getAttributeValue("id");
                                Action act = new Action(idAct, null, null, (Element) rule.getAction(rule.getCondition(), iteCond).get(i),
                                        (Element) rule.getAction(rule.getCondition(), iteCond).get(i).getChildren("action").get(j));
                                tmp = (mxCell) this.insertVertex(parent, id, act, ACT_X + (CELLS_X_SPACE * j), CELLS_Y_DEFAULT + CELLS_Y_SPACE * iteCond + CELLS_Y_SPACE * i,
                                        ACT_W, ACT_H, ICON_ACT_ACTIVE);
                                if (ACT_X_SPACE < (CELLS_X_SPACE * (j + 1))) {
                                    ACT_X_SPACE = CELLS_X_SPACE * (j + 1);
                                }
                                spaceActStandby = CELLS_X_SPACE * (j + 1);
                                listAct.put(id, tmp);
                                listActId.add(id);
                                String idEdge = "e" + randomID();
                                this.listEdgeId.add(idEdge);
                                if (j == 0) {
                                    this.insertEdge(parent, idEdge, "", tmpCond, tmp);
                                } else {
                                    this.insertEdge(parent, idEdge, "", tmpLastAct, tmp);
                                }
                                tmpLastAct = tmp;
                            }
                            String id = randomID();
                            Action actStandby = new Action(null, "Ajouter\nAction", null,
                                    rule.getAction(rule.getCondition(), iteCond).get(i),
                                    rule.getAction(rule.getCondition(), iteCond).get(i));
                            tmp = (mxCell) this.insertVertex(parent, id, actStandby, ACT_X + spaceActStandby, CELLS_Y_DEFAULT + CELLS_Y_SPACE * iteCond + CELLS_Y_SPACE * i,
                                    ACT_W, ACT_H, ICON_ACT_INACTIVE);
                            this.listLastAct.put(id, tmp);
                            this.listLastActId.add(id);
                            String idEdge = "e" + randomID();
                            this.insertEdge(parent, idEdge, "", tmpLastAct, tmp);
                        }
                        END_X += ACT_X_SPACE;
                    }
                }
            }

            //Evènements de Fin
            if (rule.getEvenementFin() != null) {
                for (int i = 0; i < rule.getEvenementFin().size(); i++) {
                    String id = randomID();
                    Event evtEnd;
                    String type = rule.getEvenementFin().get(i).getAttributeValue("type");
                    if (type.equalsIgnoreCase(Event.TYPE_LANCE_ASSIST) || type.equalsIgnoreCase(Event.TYPE_LANCE_REGLE)) {
                        String idComp = rule.getEvenementFin().get(i).getAttributeValue("idComp");
                        evtEnd = new Event(null, type, idComp, rule.getEvenementFin().get(i));
                    } else {
                        String idEve = rule.getEvenementFin().get(i).getAttributeValue("idEve");
                        String idComp = rule.getEvenementFin().get(i).getAttributeValue("idComp");
                        evtEnd = new Event(idEve, type, idComp, rule.getEvenementFin().get(i));
                    }
                    tmp = (mxCell) this.insertVertex(parent, id, evtEnd, END_X, CELLS_Y_DEFAULT,
                            END_W, END_H, ICON_EVT_ACTIVE);
                    listEnd.put(id, tmp);
                    listEndId.add(id);
                }
                for (int i = 0; i < this.listEndId.size(); i++) {
                    for (Iterator<String> it = listLastAct.keySet().iterator(); it.hasNext();) {
                        String key = it.next();
                        String idEdge = "e" + randomID();
                        this.insertEdge(parent, idEdge, "", listLastAct.get(key), listEnd.get(listEndId.get(i)));
                    }
                }
            }

            //Offset pour les labels des vertices
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

    protected void resetDistance() {
        EVT_X = 50;
        COND_X = EVT_X + 100;
        ACT_X = COND_X + 100;
        ACT_X_SPACE = 0;
        END_X = ACT_X + 100;
    }

    /**
     * Changer l'état d'un noeud de actif - inactif ou l'inverse
     *
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

    @Override
    public void update(Observable o, Object o1) {
        Rule ruleChanged = (Rule) o;
        ruleChanged.viewListElem(ruleChanged.getEvenementDeclencheur());
        updateRuleChanged(ruleChanged);
    }

    protected String randomID() {
        return Integer.toString(((Double) (Math.random() * 10000)).intValue());
    }

    public int getCELLS_Y_DEFAULT() {
        return CELLS_Y_DEFAULT;
    }

    public void setCELLS_Y_DEFAULT(int CELLS_Y_DEFAULT) {
        this.CELLS_Y_DEFAULT = CELLS_Y_DEFAULT;
    }

    public int getCELLS_Y_SPACE() {
        return CELLS_Y_SPACE;
    }

    public void setCELLS_Y_SPACE(int CELLS_Y_SPACE) {
        this.CELLS_Y_SPACE = CELLS_Y_SPACE;
    }

    public int getCELLS_X_SPACE() {
        return CELLS_X_SPACE;
    }

    public void setCELLS_X_SPACE(int CELLS_X_SPACE) {
        this.CELLS_X_SPACE = CELLS_X_SPACE;
    }

    public int getCELLS_LABEL_OFFSET() {
        return CELLS_LABEL_OFFSET;
    }

    public void setCELLS_LABEL_OFFSET(int CELLS_LABEL_OFFSET) {
        this.CELLS_LABEL_OFFSET = CELLS_LABEL_OFFSET;
    }

    public int getEVT_X() {
        return EVT_X;
    }

    public void setEVT_X(int EVT_X) {
        this.EVT_X = EVT_X;
    }

    public int getEVT_W() {
        return EVT_W;
    }

    public void setEVT_W(int EVT_W) {
        this.EVT_W = EVT_W;
    }

    public int getEVT_H() {
        return EVT_H;
    }

    public void setEVT_H(int EVT_H) {
        this.EVT_H = EVT_H;
    }

    public int getCOND_X() {
        return COND_X;
    }

    public void setCOND_X(int COND_X) {
        this.COND_X = COND_X;
    }

    public int getCOND_W() {
        return COND_W;
    }

    public void setCOND_W(int COND_W) {
        this.COND_W = COND_W;
    }

    public int getCOND_H() {
        return COND_H;
    }

    public void setCOND_H(int COND_H) {
        this.COND_H = COND_H;
    }

    public int getACT_X() {
        return ACT_X;
    }

    public void setACT_X(int ACT_X) {
        this.ACT_X = ACT_X;
    }

    public int getACT_W() {
        return ACT_W;
    }

    public void setACT_W(int ACT_W) {
        this.ACT_W = ACT_W;
    }

    public int getACT_H() {
        return ACT_H;
    }

    public void setACT_H(int ACT_H) {
        this.ACT_H = ACT_H;
    }

    public int getACT_X_SPACE() {
        return ACT_X_SPACE;
    }

    public void setACT_X_SPACE(int ACT_X_SPACE) {
        this.ACT_X_SPACE = ACT_X_SPACE;
    }

    public int getEND_X() {
        return END_X;
    }

    public void setEND_X(int END_X) {
        this.END_X = END_X;
    }

    public int getEND_W() {
        return END_W;
    }

    public void setEND_W(int END_W) {
        this.END_W = END_W;
    }

    public int getEND_H() {
        return END_H;
    }

    // GETTER SETTER
    public void setEND_H(int END_H) {
        this.END_H = END_H;
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
