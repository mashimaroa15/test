/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package rules;

import java.util.List;
import org.jdom.Element;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class Action {

    public static final String TYPE_MISE_EN_VALEUR = "mise en valeur";
    public static final String TYPE_MESSAGE = "message";
    public static final String TYPE_ACTION_INTERFACE = "action sur l'interface";
    
    private String id;
    private String type;
    private String desc;
    
    private Element groupActionElement;
    private Element actionElement;

    public Action(String id, String type, String desc, Element grp, Element act) {
        this.id = id;
        this.type = type;
        this.desc = desc;
        this.groupActionElement = grp;
        this.actionElement = act;
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

    public Element getGroupActionElement() {
        return groupActionElement;
    }

    public void setGroupActionElement(Element groupActionElement) {
        this.groupActionElement = groupActionElement;
    }

    public Element getActionElement() {
        return actionElement;
    }

    public void setActionElement(Element actionElement) {
        this.actionElement = actionElement;
    }
}
