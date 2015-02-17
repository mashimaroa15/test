/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */
package tools.transferTools;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class DataEvt {
    
    String id;
    String type;
    String desc;

    public DataEvt(String id, String type, String desc) {
        this.id = id;
        this.type = type;
        this.desc = desc;
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
}
