/*
 * Mise en place d'un langage graphique pour l'editeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Fevrier 2015
 * Universite Lyon 1 || Departement Informatique
 */

package rules;

import java.util.ArrayList;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class Event {
    
    private int id;
    private String name;
    private int type;
    private String desc;    

    public Event(int id, String name, int type, String desc) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
