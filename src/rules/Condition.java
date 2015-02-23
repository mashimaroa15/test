/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package rules;

import org.jdom.Element;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class Condition {

    public static final String TYPE_CONSULTATION = "consultation";
    public static final String TYPE_HISTORIQUE = "historique";
    public static final String TYPE_COMBINAISION = "combinaison";
    public static final String TYPE_CONTEXT = "contexte";    
    
    private String id;
    private String type;
    private String desc;
    
    private Element element;

    public Condition(String id, String type, String desc, Element e) {
        this.id = id;
        this.type = type;
        this.desc = desc;
        this.element = e;
    }

    @Override
    public String toString() {
        if (this.id == null) {
            return this.type;
        }
        return this.id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
