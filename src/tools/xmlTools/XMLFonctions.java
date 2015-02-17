package tools.xmlTools;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Blandine
 */
public class XMLFonctions {
    static Element racine;
    static org.jdom.Document document;

//affiche tout le contenu du fichier XML dans netbeans, util pour le debugage surtout
public static void affiche()
{
    try
    {
        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        sortie.output(document, System.out);
    }
    catch (java.io.IOException e){}
}

static void enregistre(String fichier, String chemin)
{
    try
    {
        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        sortie.output(document, new FileOutputStream(chemin+fichier));
    }
    catch (java.io.IOException e){}
}

static void enregistre(String fichier)
{
    try
    {
        XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
        sortie.output(document, new FileOutputStream(fichier));
    }
    catch (java.io.IOException e){}
}

static void creerXML(String nomRacine)
{
    racine = new Element(nomRacine);
    document = new Document(racine);
}

static void creerXML(Element elem)
{
    racine = elem;
    document = new Document(racine);
}

static void AjoutNoeudRacine(String nom)
{
    Element elem = new Element(nom);
    racine.addContent(elem);
}

static Element AjoutNoeudRacine_Elem(String nom)
{
    Element elem = new Element(nom);
    racine.addContent(elem);
    return elem;
}

static void AjoutAttribut(Element elem, String nom, String val)
{
    Attribute classe = new Attribute(nom,val);
    elem.setAttribute(classe);
}

static void AjoutNoeud(Element elem, String nom, String val)
{
    Element noeud = new Element(nom);
    noeud.setText(val);
    elem.addContent(noeud);
}

static Element AjoutNoeud_Elem(Element elem, String nom, String val)
{
    Element noeud = new Element(nom);
    noeud.setText(val);
    elem.addContent(noeud);
    return noeud;
}

static void afficheALL()
{
    List listEtudiants = racine.getChildren("etudiant");
    Iterator i = listEtudiants.iterator();
    while(i.hasNext())
    {
        Element courant = (Element)i.next();
        System.out.println(courant.getChild("nom").getText());
    }
}

static Element enfantParNum(Element pere, Integer i)
{
    Element courant = (Element) pere.getChildren().get(i);
    return courant;
}

public static void OuvrirXML(String fichier)
{
   SAXBuilder sxb = new SAXBuilder();
   try
   {
       XMLFonctions.document = sxb.build(new File(fichier));
   }
   catch(Exception e){
       System.err.println("echec pour lire " + fichier + ": " + e.getMessage());
   }
   XMLFonctions.racine = XMLFonctions.document.getRootElement();
}

static public void afficheElem(Element elem)
{
    try
        {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(elem, System.out);
        }
        catch (java.io.IOException e){}
}
}

