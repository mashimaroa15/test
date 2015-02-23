/*
 * Mise en place d'un langage graphique pour l'éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */
package rules;

import graph.MyGraph;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.View;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class Rule extends Observable {

    public static String filePath = "src/examples/SA_peda.xml";

    Document document;
    Element racine;
    Element racineRules;
    Element rule;

    MyGraph graph;
    View view;

    public Rule(String ruleId, View view) {
        initRule(ruleId);
        this.view = view;
        this.graph = (MyGraph) view.getGraph();
        this.addObserver(graph);
    }

    public void initRule(String ruleId) {
        SAXBuilder sxb = new SAXBuilder();
        try {
            this.document = sxb.build(new File(this.filePath));
        } catch (JDOMException | IOException e) {
            System.err.println("Echec pour lire " + this.filePath + ": " + e.getMessage());
        }
        this.racine = this.document.getRootElement();
        this.racineRules = (Element) (this.racine.getChildren("regles")).get(0);
        changeRule(ruleId);
    }

    public void changeRule(String newRuleId) {
        boolean newRuleIdFound = false;
        if (newRuleId != null) {
            for (Object obj : this.racineRules.getChildren("regle")) {
                Element e = (Element) obj;
                if (e.getAttributeValue("id").equalsIgnoreCase(newRuleId)) {
                    this.rule = e;
                    newRuleIdFound = true;
                }
            }
            //vérifie si le newRuleId est trouvé, sinon quitte le programme
            if (!newRuleIdFound) {
                System.err.println("This RuleId is not found ! : " + newRuleId);
                JOptionPane.showMessageDialog(new JFrame(),
                        "This RuleId is not found ! : " + newRuleId,
                        "Rule not found",
                        JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        }
        if (this.rule == null) {
            System.out.println("Rule: null");
        } else {
            System.out.println("Rule: " + this.rule.getAttributeValue("id"));
            System.out.print("Declencheurs: ");
            this.viewListElem(getEvenementDeclencheur());
            System.out.print("Conditions: ");
            this.viewListElem(getCondition());
            System.out.print("Actions: ");
            this.viewListElem(getAction(getCondition(), -1));
            System.out.print("Fins: ");
            this.viewListElem(getEvenementFin());
        }
        setChanged();
        notifyObservers();
    }

    public List<Element> getEvenementDeclencheur() {
        if (rule != null) {
            return rule.getChildren("evenement_declencheur");
        } else {
            return null;
        }
    }

    public List<Element> getCondition() {
        if (rule != null) {
            return rule.getChildren("alternatives");
        } else {
            return null;
        }
    }

    public List<Element> getAction(List<Element> cond, int numCond) {
        if (cond != null) {
            if (numCond == -1) {
                List<Element> allAction;
                allAction = new ArrayList<>();
                for (int i = 0; i < cond.size(); i++) {
                    for (int j = 0; j < cond.get(i).getChildren("action").size(); j++) {
                        allAction.add((Element) cond.get(i).getChildren("action").get(j));
                    }
                }
                return (List<Element>) allAction;
            } else {
                return cond.get(numCond).getChildren("alternative");
            }
        }
        return null;
    }

    public List<Element> getEvenementFin() {
        if (rule == null) {
            return null;
        } else {
            return rule.getChildren("evenement_de_fin");
        }
    }

    public void viewElem(Element elem) {
        try {
            if (elem != null) {
                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                sortie.output(elem, System.out);
            } else {
                System.out.println("View Elem : null");
            }

        } catch (java.io.IOException e) {
            System.err.println("Erreur viewElem");
        }
    }

    public void viewListElem(List<Element> list) {
        try {
            if (list != null) {
                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                sortie.output(list, System.out);
            } else {
                System.out.println("View List Elem : null");
            }
        } catch (java.io.IOException e) {
            System.err.println("Erreur viewElem");
        }
    }

    public static String getFilePath() {
        return filePath;
    }

    public static void setFilePath(String filePath) {
        Rule.filePath = filePath;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Element getRacine() {
        return racine;
    }

    public void setRacine(Element racine) {
        this.racine = racine;
    }

    public Element getRacineRules() {
        return racineRules;
    }

    public void setRacineRules(Element racineRules) {
        this.racineRules = racineRules;
    }

    public Element getRule() {
        return rule;
    }

    public void setRule(Element rule) {
        this.rule = rule;
    }

    public MyGraph getGraph() {
        return graph;
    }

    public void setGraph(MyGraph graph) {
        this.graph = graph;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
