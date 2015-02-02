/*
 * Mise en place d’un langage graphique pour l’éditeur SEPIA
 * MIF20 - PRIM - TER || Janvier - Février 2015
 * Université Lyon 1 || Département Informatique
 */

package myComponent;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import rules.Event;

/**
 *
 * @author Le Duc Tan NGUYEN
 */
public class MyComponentEvent extends JComponent {
    
    private int id;
    private String name;
    private int type;
    private String desc;
    
    private Image iconActive;
    private Image iconInactive;

    public MyComponentEvent(Event e) {
        try {
            this.id = e.getId();
            this.name = e.getName();
            this.type = e.getType();
            this.desc = e.getDesc();
            
            iconActive = ImageIO.read(new File("src/img/eventActive.png"));
            iconInactive = ImageIO.read(new File("src/img/eventInactive.png"));
            
        } catch (IOException ex) {
            Logger.getLogger(MyComponentEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs); 
        grphcs.drawImage(iconInactive, 0, 0, this);
    }
    
    protected void paintComponent(Graphics grphcs, Image img) {
        super.paintComponent(grphcs); 
        grphcs.drawImage(img, 0, 0, this);
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Image getIconActive() {
        return iconActive;
    }

    public void setIconActive(Image iconActive) {
        this.iconActive = iconActive;
    }

    public Image getIconInactive() {
        return iconInactive;
    }

    public void setIconInactive(Image iconInactive) {
        this.iconInactive = iconInactive;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Image getIcon() {
        return iconActive;
    }

    public void setIcon(Image icon) {
        this.iconActive = icon;
    }
}
