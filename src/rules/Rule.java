/*
 * Mise en place d'un langage graphique pour l'éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package rules;

import java.io.File;
import java.io.IOException;
import java.util.List;
import main.View;
import myComponent.MyPanel;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import tools.xmlTools.XMLFonctions;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class Rule {
    
    String filePath = "src/examples/ex.xml";
    
    Element racine;
    Document document;    
    
    public Rule(){
        updateRule(this.filePath);
        this.viewElem(racine);
        System.out.println("\n");
        this.viewListElem(getEvenementDeclencheur());
        System.out.println("\n");
        this.viewListElem(getAction());
        System.out.println("\n");
    }
    
    public List<Element> getEvenementDeclencheur() {
        return racine.getChildren("evenement_declencheur");
    }

    public List<Element> getCondition() {
        return racine.getChildren("alternatives");
    }
    
    public List<Element> getAction()
    {
        List<Element> cond = getCondition();
        List<Element> alter = cond.get(0).getChildren("alternative");
        return alter.get(0).getChildren("action");
    }
    
    public List<Element> getEvenementFin() {
        return racine.getChildren("evenement_de_fin");
    }
    
    public void updateRule(String filePath) {
        SAXBuilder sxb = new SAXBuilder();
        try {
            this.document = sxb.build(new File(filePath));
        } catch (JDOMException | IOException e) {
            System.err.println("Echec pour lire " + filePath + ": " + e.getMessage());
        }
        this.racine = this.document.getRootElement();
    }
    
    // A IMPLEMENTER : UPDATE LES ELEMENTS DANS XML
    
    
    
    public void viewElem(Element elem){
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(elem, System.out);
        } catch (java.io.IOException e) {
            System.err.println("Erreur viewElem");
        }
    }
    
    public void viewListElem(List<Element> list){
        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(list, System.out);
        } catch (java.io.IOException e) {
            System.err.println("Erreur viewElem");
        }
    }
}
