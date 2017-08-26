package fichier;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import equipe.Equipe;
import epreuves.Epreuve;
import modele.Categorie;
import unssGeCO.Preferences;

public class ChargeFichier {
	
	Preferences preferences;
	Vector<Equipe> equipes;
	Vector<Epreuve> epreuves;

	public ChargeFichier(Preferences pPreferences, Vector<Equipe> pEquipes, Vector<Epreuve> pEpeuves, String pCheminFichier) {
		equipes = pEquipes;
		epreuves = pEpeuves;
		
		Document xml;
		System.out.println("ouverture du fichier : " + pCheminFichier);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			File fileXML = new File(pCheminFichier);
			xml = builder.parse(fileXML);
			Element root = xml.getDocumentElement();
			System.out.println(root.getNodeName());

			chargePreferences(root.getElementsByTagName("preferences"));
			chargeEquipes(root.getElementsByTagName("equipe"));
			chargeEpreuves(root.getElementsByTagName("epreuve"));
			chargeTemps(root.getElementsByTagName("tps"));

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void chargePreferences(NodeList noeuds) {
        System.out.println("Chargement des préférences");
		int nbNode = noeuds.getLength();
		for(int i = 0; i < nbNode; i++) {
			Node n = noeuds.item(i);
			System.out.println("* 	Preference N°" + (i+1) + " : " + n.getNodeName() + " - " + n.getNodeValue());
		}
		
	}
	
	protected void chargeEquipes(NodeList noeuds) {
        System.out.println("Chargement des équipes");
		int nbNode = noeuds.getLength();
		for(int i = 0; i < nbNode; i++) {
			Node noeudEquipe = noeuds.item(i);			
			Element docElement = (Element) noeudEquipe;
			System.out.println("Import équipe N° " + (i+1) + " : " + docElement.getElementsByTagName("nom").item(0).getTextContent());
			/*
			Equipe nouveauEquipe = new Equipe(new Preferences());
			nouveauEquipe.setNomEquipe(docElement.getElementsByTagName("nom").item(0).getTextContent());
			System.out.println(nouveauEquipe.getNomEquipe());
			*/
			Equipe nouveauEquipe = new Equipe((Element) noeudEquipe);
			equipes.addElement(nouveauEquipe);
		}
		
	}
	
	protected void chargeEpreuves(NodeList noeuds) {
        System.out.println("Chargement des épreuves");
		int nbNode = noeuds.getLength();
		for(int i = 0; i < nbNode; i++) {
			Node noeudEpreuve = noeuds.item(i);
			System.out.println(noeudEpreuve.getAttributes().getNamedItem("categorie").getNodeName() + " - " + noeudEpreuve.getAttributes().getNamedItem("categorie").getNodeValue());
			Categorie cat = Categorie.valueOf(noeudEpreuve.getAttributes().getNamedItem("categorie").getNodeValue());
			Epreuve nouvelleEpreuve = new Epreuve(Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("id").getNodeValue()), Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("ordre").getNodeValue()), noeudEpreuve.getTextContent(), cat);
			System.out.println("nouvelle épreuce " + nouvelleEpreuve.getNom().toString());
			nouvelleEpreuve.setCoef(Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("coef").getNodeValue()));
			nouvelleEpreuve.setTypeDepart(noeudEpreuve.getAttributes().getNamedItem("depart").getNodeValue());
			nouvelleEpreuve.setDepartage(Boolean.valueOf(noeudEpreuve.getAttributes().getNamedItem("departage").getNodeValue()));
			nouvelleEpreuve.setEnLigne(Boolean.valueOf(noeudEpreuve.getAttributes().getNamedItem("enLigne").getNodeValue()));
			nouvelleEpreuve.setHeureDepart(LocalTime.parse(noeudEpreuve.getAttributes().getNamedItem("heureDepart").getNodeValue()));
			nouvelleEpreuve.setHeureDepart2(LocalTime.parse(noeudEpreuve.getAttributes().getNamedItem("heureDepart2").getNodeValue()));
			nouvelleEpreuve.setJour(Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("jour").getNodeValue()));
			nouvelleEpreuve.setTypeLimiteByValue(noeudEpreuve.getAttributes().getNamedItem("limiteType").getNodeValue());
			nouvelleEpreuve.setType(noeudEpreuve.getAttributes().getNamedItem("typeEpreuve").getNodeValue());
			
			epreuves.add(nouvelleEpreuve);
		}
		
	}
	
	protected void chargeTemps(NodeList noeuds) {
        System.out.println("Chargement des temps");
        int nbNode = noeuds.getLength();
		for(int i = 0; i < nbNode; i++) {
			Node n = noeuds.item(i);
			System.out.println("* 	temps N°" + (i+1) + " : " + n.getNodeName() + " - " + n.getNodeValue());
		}
	}

}
