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
import epreuves.Balise;
import epreuves.Epreuve;
import epreuves.Parcours;
import modele.Categorie;
import modele.TypeLimite;
import unssGeCO.Preferences;

public class ChargeFichier {
	
	Preferences preferences;
	Vector<Equipe> equipes;
	Vector<Epreuve> epreuves;

	public ChargeFichier(Preferences pPreferences, Vector<Equipe> pEquipes, Vector<Epreuve> pEpreuves, String pCheminFichier) {
		equipes = pEquipes;
		epreuves = pEpreuves;
		preferences = pPreferences;
		
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
			Element prefs = (Element) noeuds.item(i);
			Element pref = (Element) prefs.getElementsByTagName("baliseDefaut").item(0);
	        System.out.println(pref.getNodeName() + " - " + pref.getElementsByTagName("trouveBonif").item(0).getTextContent());
			Balise pBalise = new Balise(Integer.parseInt(pref.getElementsByTagName("code").item(0).getTextContent()));
			pBalise.setTrouveBonif(Boolean.parseBoolean(pref.getElementsByTagName("trouveBonif").item(0).getTextContent()));
			pBalise.setTrouvePoints(Integer.parseInt(pref.getElementsByTagName("trouvePoints").item(0).getTextContent()));
			pBalise.setTrouveHeure(LocalTime.parse(pref.getElementsByTagName("trouveHeure").item(0).getTextContent()));
			pBalise.setPmBonif(Boolean.parseBoolean(pref.getElementsByTagName("pmBonif").item(0).getTextContent()));
			pBalise.setPmPoints(Integer.parseInt(pref.getElementsByTagName("pmPoints").item(0).getTextContent()));
			pBalise.setPmHeure(LocalTime.parse(pref.getElementsByTagName("pmHeure").item(0).getTextContent()));
			preferences.setBaliseDefaut(pBalise);
		}
		
	}
	
	protected void chargeEquipes(NodeList noeuds) {
        System.out.println("Chargement des équipes");
		int nbNode = noeuds.getLength();
		for(int i = 0; i < nbNode; i++) {
			Node noeudEquipe = noeuds.item(i);			
			Element docElement = (Element) noeudEquipe;
			System.out.println("Import équipe N° " + (i+1) + " : " + docElement.getElementsByTagName("nom").item(0).getTextContent());
			
			Equipe nouveauEquipe = new Equipe((Element) noeudEquipe);
			equipes.addElement(nouveauEquipe);
		}
		
	}
	
	protected void chargeEpreuves(NodeList noeuds) {
        System.out.println("Chargement des épreuves");
		int nbNode = noeuds.getLength();
		for(int i = 0; i < nbNode; i++) {
			//Node noeudEpreuve = noeuds.item(i);
			Element noeudEpreuve = (Element) noeuds.item(i);
			System.out.println(noeudEpreuve.getAttributes().getNamedItem("categorie").getNodeName() + " - " + noeudEpreuve.getAttributes().getNamedItem("categorie").getNodeValue());
			Categorie cat = Categorie.valueOf(noeudEpreuve.getAttributes().getNamedItem("categorie").getNodeValue());
			
			String nomEpreuve = noeudEpreuve.getElementsByTagName("nom").item(0).getTextContent();
			System.out.println("nouvelle épreuve " + nomEpreuve);
			
			Epreuve nouvelleEpreuve = new Epreuve(Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("id").getNodeValue()), Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("ordre").getNodeValue()), nomEpreuve, cat);
			
			nouvelleEpreuve.setType(noeudEpreuve.getAttributes().getNamedItem("typeEpreuve").getNodeValue());
			System.out.print(noeudEpreuve.getAttributes().getNamedItem("typeEpreuve").getNodeValue() + " - ");
			nouvelleEpreuve.setActive(Boolean.parseBoolean(noeudEpreuve.getAttribute("active")));
			System.out.print(nouvelleEpreuve.getActive() + " - ");
			nouvelleEpreuve.setTypeDepart(noeudEpreuve.getAttributes().getNamedItem("depart").getNodeValue());
			System.out.print( noeudEpreuve.getAttributes().getNamedItem("depart").getNodeValue()+ " - ");
			nouvelleEpreuve.setHeureDepart(LocalTime.parse(noeudEpreuve.getAttributes().getNamedItem("heureDepart").getNodeValue()));
			System.out.print(noeudEpreuve.getAttributes().getNamedItem("heureDepart").getNodeValue() + " - ");
			nouvelleEpreuve.setHeureDepart2(LocalTime.parse(noeudEpreuve.getAttributes().getNamedItem("heureDepart2").getNodeValue()));
			System.out.print(noeudEpreuve.getAttributes().getNamedItem("heureDepart2").getNodeValue() + " - ");
			nouvelleEpreuve.setEnLigne(Boolean.valueOf(noeudEpreuve.getAttributes().getNamedItem("enLigne").getNodeValue()));
			System.out.print(noeudEpreuve.getAttributes().getNamedItem("enLigne").getNodeValue() + " - ");
			nouvelleEpreuve.setJour(Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("jour").getNodeValue()));
			System.out.print(noeudEpreuve.getAttributes().getNamedItem("jour").getNodeValue() + " - ");
			System.out.print(nouvelleEpreuve.getJour() + " - ");
			nouvelleEpreuve.setCoef(Integer.parseInt(noeudEpreuve.getAttributes().getNamedItem("coef").getNodeValue()));
			System.out.print(nouvelleEpreuve.getCoef() + " - ");
			nouvelleEpreuve.setDepartage(Boolean.valueOf(noeudEpreuve.getAttributes().getNamedItem("departage").getNodeValue()));
			System.out.print(noeudEpreuve.getAttributes().getNamedItem("departage").getNodeValue() + " - ");
			nouvelleEpreuve.setTypeLimite(TypeLimite.valueOf(noeudEpreuve.getAttribute("limiteType")).intitule());
			System.out.print(nouvelleEpreuve.getTypeLimite() + " - ");
			if (!nouvelleEpreuve.getTypeLimite().equals(TypeLimite.sans)) {
				nouvelleEpreuve.setHeureMaxi(LocalTime.parse(noeudEpreuve.getAttributeNode("limiteHeure").getValue()));
				System.out.println(nouvelleEpreuve.getHeureMaxi());
				nouvelleEpreuve.setPointsHeureMaxi(Integer.parseInt(noeudEpreuve.getAttribute("limitePoints")));
				nouvelleEpreuve.setPenaliteHeureMaxi(LocalTime.parse(noeudEpreuve.getAttributeNode("limiteMnPenalite").getValue()));
				nouvelleEpreuve.setParHeureMaxi(LocalTime.parse(noeudEpreuve.getAttributeNode("limiteMnPar").getValue()));
			}
			NodeList listeParcours = noeudEpreuve.getElementsByTagName("parcours");
			if (listeParcours.getLength() > 0) {
				for(int j=0;j<listeParcours.getLength();j++) {
					Element parcours = (Element) listeParcours.item(j);
					System.out.println(parcours.getElementsByTagName("nom").item(0).getTextContent());
					Parcours pParcours = new Parcours(parcours.getElementsByTagName("nom").item(0).getTextContent());
					nouvelleEpreuve.addParcours(pParcours);
					if (parcours.getElementsByTagName("balise").getLength() > 0) {
						NodeList listeBalises = parcours.getElementsByTagName("balise");
						System.out.println(listeBalises.getLength() + " balises à importer");
						for(int k=0;k<listeBalises.getLength();k++) {
							Element balise = (Element) listeBalises.item(k);
							System.out.print(" - code " + balise.getElementsByTagName("code").item(0).getTextContent());
							Balise nouvelleBalise = new Balise(Integer.parseInt(balise.getElementsByTagName("code").item(0).getTextContent()));
							nouvelleBalise.setTrouveBonif(Boolean.parseBoolean(balise.getElementsByTagName("trouveBonif").item(0).getTextContent()));
							nouvelleBalise.setTrouvePoints(Integer.parseInt(balise.getElementsByTagName("trouvePoints").item(0).getTextContent()));
							nouvelleBalise.setTrouveHeure(LocalTime.parse(balise.getElementsByTagName("trouveHeure").item(0).getTextContent()));
							nouvelleBalise.setPmBonif(Boolean.parseBoolean(balise.getElementsByTagName("pmBonif").item(0).getTextContent()));
							nouvelleBalise.setPmPoints(Integer.parseInt(balise.getElementsByTagName("pmPoints").item(0).getTextContent()));
							nouvelleBalise.setPmHeure(LocalTime.parse(balise.getElementsByTagName("pmHeure").item(0).getTextContent()));
							
							pParcours.addBalise(nouvelleBalise);
						}
						System.out.println();
					}
				}
				
			}			
			epreuves.add(nouvelleEpreuve);
		}
	}
	
	protected void chargeTemps(NodeList noeuds) {
        int nbNode = noeuds.getLength();
        System.out.println("Chargement des "+nbNode+" temps");
        if(nbNode > 0) {
    		for(int i = 0; i < nbNode; i++) {
    			Node n = noeuds.item(i);
    			System.out.println("* 	temps N°" + (i+1) + " : " + n.getNodeName() + " - " + n.getNodeValue());
    		}	
        }
	}

}
