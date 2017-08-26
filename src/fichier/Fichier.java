/*
 * Copyright 2017 Régis Bouguin
 * 
 * This file is part of unssGeCO
 * 
 * coUnss is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * coUnss is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with unssGeCO.  If not, see <http://www.gnu.org/licenses/>
 * 
 */
package fichier;

import java.time.format.DateTimeFormatter;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import epreuves.Balise;
import epreuves.Epreuve;
import epreuves.Parcours;
import equipe.Coureur;
import equipe.Equipe;
import modele.TypeLimite;
import temps.BaliseTrouvee;
import temps.TempsCourse;
import unssGeCO.Preferences;

public class Fichier implements SaveFichierXML  {

	private Preferences preferences;
	private Document docCo;
	private String fichierXSD = new String("unssGeCO.xsd");
	private String fichierXML;

	public Fichier(Preferences pPreferences, Vector<Equipe> pEquipes, Vector<Epreuve>pEpreuves, Vector<TempsCourse> tempsEnCourse, String pAdresse) {
		preferences = pPreferences;
				
		fichierXML = pAdresse;

		try {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();

			// élément racine
			docCo = docBuilder.newDocument();
			Element racine = docCo.createElement("unssGeCO");
			docCo.appendChild(racine);
			
			Element version = docCo.createElement("version");
			version.setTextContent("0.1.0");
			racine.appendChild(version);

			// éléments de préférences
			Element elPreferences = docCo.createElement("preferences");
			docCo.getDocumentElement().appendChild(elPreferences);
			
			Element balise = docCo.createElement("baliseDefaut");
			elPreferences.appendChild(balise);
			
			Element code = docCo.createElement("code");
			code.setTextContent(String.valueOf(preferences.getBaliseDefaut().getCode()));
			balise.appendChild(code);
			
			Element trouveBonif = docCo.createElement("trouveBonif");
			trouveBonif.setTextContent(String.valueOf(preferences.getBaliseDefaut().getTrouveBonif()));
			balise.appendChild(trouveBonif);
			
			Element trouvePoints = docCo.createElement("trouvePoints");
			trouvePoints.setTextContent(String.valueOf(preferences.getBaliseDefaut().getTrouvePoints()));
			balise.appendChild(trouvePoints);
			
			Element trouveHeure = docCo.createElement("trouveHeure");
			trouveHeure.setTextContent(String.valueOf(preferences.getBaliseDefaut().getTrouveHeure().format(DateTimeFormatter.ISO_LOCAL_TIME)));
			balise.appendChild(trouveHeure);
			
			Element pmBonif = docCo.createElement("pmBonif");
			pmBonif.setTextContent(String.valueOf(preferences.getBaliseDefaut().getPmBonif()));
			balise.appendChild(pmBonif);
			
			Element pmPoints = docCo.createElement("pmPoints");
			pmPoints.setTextContent(String.valueOf(preferences.getBaliseDefaut().getPmPoints()));
			balise.appendChild(pmPoints);

			Element pmHeure = docCo.createElement("pmHeure");
			pmHeure.setTextContent(String.valueOf(preferences.getBaliseDefaut().getPmHeure().format(DateTimeFormatter.ISO_LOCAL_TIME)));
			balise.appendChild(pmHeure);
			
			// éléments de Equipe
			Element elEquipes = docCo.createElement("equipes");
			docCo.getDocumentElement().appendChild(elEquipes);
			
			for (Equipe eqp : pEquipes) {
				Element elEquipe = docCo.createElement("equipe");
				elEquipes.appendChild(elEquipe);
				
				elEquipe.setAttribute("id", String.valueOf(eqp.getId()));
				elEquipe.setAttribute("etablissement", eqp.getEtablissement());
				elEquipe.setAttribute("ville", eqp.getVille());
				elEquipe.setAttribute("dossard", eqp.getDossard());
				elEquipe.setAttribute("categorie", eqp.getCategorie());
				elEquipe.setAttribute("nonClasse", String.valueOf(eqp.getNonClasse()));
				elEquipe.setAttribute("absent", String.valueOf(eqp.getAbsent()));
				elEquipe.setAttribute("dsq", String.valueOf(eqp.getDsq()));
		        
				Element elNom = docCo.createElement("nom");
				elNom.setTextContent(eqp.getNomEquipe());
		        elEquipe.appendChild(elNom);
		        Element elCoureurs = docCo.createElement("coureurs");
		        elEquipe.appendChild(elCoureurs);
		        
		        Coureur[] coureurs = eqp.getCoureurs();
		        
		        for (Coureur cour :  coureurs) {
		        	Element elCoureur = docCo.createElement("coureur");
		        	elCoureurs.appendChild(elCoureur);
		        	elCoureur.setTextContent(cour.getPuce());
		        	elCoureur.setAttribute("nom", cour.getNom());
		        	elCoureur.setAttribute("prenom", cour.getPrenom());
		        	elCoureur.setAttribute("sexe", cour.getSexe());
		        	
		        }
	        }

			//élément de Epreuves
	        Element elEpreuves = docCo.createElement("epreuves");
	        docCo.getDocumentElement().appendChild(elEpreuves);
	        
	        for (Epreuve epv : pEpreuves) {
				Element elEpreuve = docCo.createElement("epreuve");
				elEpreuves.appendChild(elEpreuve);
				
				Element elNom = docCo.createElement("nom");
				elNom.setTextContent(epv.getNom());
				elEpreuve.appendChild(elNom);
				
				elEpreuve.setAttribute("id",String.valueOf(epv.getId()));
				elEpreuve.setAttribute("categorie",String.valueOf(epv.getCategorie()));
				elEpreuve.setAttribute("ordre",String.valueOf(epv.getNumOrdre()));
				elEpreuve.setAttribute("typeEpreuve",epv.getType().toString());
				elEpreuve.setAttribute("depart",String.valueOf(epv.getTypeDepart()));
				elEpreuve.setAttribute("heureDepart",String.valueOf(epv.getHeureDepart().format(DateTimeFormatter.ISO_LOCAL_TIME)));
				elEpreuve.setAttribute("heureDepart2",String.valueOf(epv.getHeureDepart2()) + ":00");
				elEpreuve.setAttribute("enLigne",String.valueOf(epv.getEnLigne()));
				elEpreuve.setAttribute("jour",String.valueOf(epv.getJour()));
				//elEpreuve.setAttribute("apresSuivante",String.valueOf(epv.getApresPrecedente()));
				elEpreuve.setAttribute("coef",String.valueOf(epv.getCoef()));
				elEpreuve.setAttribute("departage",String.valueOf(epv.getDepartage()));
				elEpreuve.setAttribute("limiteType",String.valueOf(epv.getTypeLimite()));
				if (epv.getTypeLimite().equals(TypeLimite.heure)) {
					elEpreuve.setAttribute("limiteHeure",String.valueOf(epv.getHeureMaxi().format(DateTimeFormatter.ISO_LOCAL_TIME)));
					elEpreuve.setAttribute("limitePoints",String.valueOf(epv.getPointsHeureMaxi()));
					elEpreuve.setAttribute("limiteMnPenalite",String.valueOf(epv.getPenaliteHeureMaxi().format(DateTimeFormatter.ISO_LOCAL_TIME)));
					elEpreuve.setAttribute("limiteMnPar",String.valueOf(epv.getParHeureMaxi().format(DateTimeFormatter.ISO_LOCAL_TIME)));
				}
				else if (epv.getTypeLimite().equals(TypeLimite.temps)) {
					elEpreuve.setAttribute("limiteHeure",String.valueOf(epv.getDureeMaxi().format(DateTimeFormatter.ISO_LOCAL_TIME)));
					elEpreuve.setAttribute("limitePoints",String.valueOf(epv.getPointsDureeMaxi()));
					elEpreuve.setAttribute("limiteMnPenalite",String.valueOf(epv.getPenaliteDureeMaxi().format(DateTimeFormatter.ISO_LOCAL_TIME)));
					elEpreuve.setAttribute("limiteMnPar",String.valueOf(epv.getParDureeMaxi().format(DateTimeFormatter.ISO_LOCAL_TIME)));
				}
	        	
				Element elParcourss = docCo.createElement("parcourss");
				elEpreuve.appendChild(elParcourss);
				
				if(epv.getParcours().size() > 0) {
					for(Parcours parc : epv.getParcours()) {
						Element elParcours = docCo.createElement("parcours");
						elParcourss.appendChild(elParcours);

						Element elNomParcours = docCo.createElement("nom");
						elNomParcours.setTextContent(parc.getNom());
						elParcours.appendChild(elNomParcours);
						
						Element elBalises = docCo.createElement("balises");
						elParcours.appendChild(elBalises);
						
						//if(parc.getBalises().size() > 0) {
						for(Balise balis : parc.getBalises()) {	
							Element elBalise = docCo.createElement("balise");
							elBalises.appendChild(elBalise);
							
							Element elNomBalise = docCo.createElement("code");
							elNomBalise.setTextContent(String.valueOf(balis.getCode()));
							elBalise.appendChild(elNomBalise);
							
							Element elTrouveBonif = docCo.createElement("trouveBonif");
							elTrouveBonif.setTextContent(String.valueOf(balis.getTrouveBonif()));
							elBalise.appendChild(elTrouveBonif);

							Element elTrouvePoints = docCo.createElement("trouvePoints");
							elTrouvePoints.setTextContent(String.valueOf(balis.getTrouvePoints()));
							elBalise.appendChild(elTrouvePoints);

							Element elTrouveHeure = docCo.createElement("trouveHeure");
							elTrouveHeure.setTextContent(String.valueOf(balis.getTrouveHeure().format(DateTimeFormatter.ISO_LOCAL_TIME)));
							elBalise.appendChild(elTrouveHeure);
							
							Element elPmBonif = docCo.createElement("pmBonif");
							elPmBonif.setTextContent(String.valueOf(balis.getPmBonif()));
							elBalise.appendChild(elPmBonif);
							
							Element elPmPoints = docCo.createElement("pmPoints");
							elPmPoints.setTextContent(String.valueOf(balis.getPmPoints()));
							elBalise.appendChild(elPmPoints);

							Element elPmHeure = docCo.createElement("pmHeure");
							elPmHeure.setTextContent(String.valueOf(balis.getPmHeure().format(DateTimeFormatter.ISO_LOCAL_TIME)));
							elBalise.appendChild(elPmHeure);


							
						}
						
					}
					
				}
				
	        }

			//élément de Temps (les données des puces
	        Element elTempss = docCo.createElement("tempss");
	        docCo.getDocumentElement().appendChild(elTempss);
	        
	        for(TempsCourse tps : tempsEnCourse) {
	        	Element elTemps = docCo.createElement("temps");
	        	elTempss.appendChild(elTemps);
	        	
	        	Element elPuceLue = docCo.createElement("puce");
	        	elPuceLue.setTextContent(tps.getPuce());
	        	elTemps.appendChild(elPuceLue);
	        	
	        	Element elJourLu = docCo.createElement("jour");
	        	elJourLu.setTextContent(String.valueOf(tps.getJour()));
	        	elTemps.appendChild(elJourLu);

	        	Element elDepartLu = docCo.createElement("depart");
	        	elDepartLu.setTextContent(String.valueOf(tps.getDepart()));
	        	elTemps.appendChild(elDepartLu);
	        	
	        	Element elArriveLu = docCo.createElement("arrive");
	        	elArriveLu.setTextContent(String.valueOf(tps.getArrive()));
	        	elTemps.appendChild(elArriveLu);
	        	
	        	if (tps.getBalisesTrouvees().size() > 0) {
	        		Element elTempsBalises = docCo.createElement("tempsBalises");
	        		elTemps.appendChild(elTempsBalises);
	        		
	        		for (BaliseTrouvee bal : tps.getBalisesTrouvees()) {		        		
		        		Element elTempsBalise = docCo.createElement("tempsBalise");
		        		elTempsBalises.appendChild(elTempsBalise);
		        		
		        		Element elBaliseCode = docCo.createElement("code");
		        		elBaliseCode.setTextContent(String.valueOf(bal.getCode()));
		        		elTempsBalise.appendChild(elBaliseCode);
		        		
		        		Element elBaliseTemps = docCo.createElement("temps");
		        		elBaliseTemps.setTextContent(String.valueOf(bal.getTemps()));
		        		elTempsBalise.appendChild(elBaliseTemps);
		        		
		        	}
	        	}
	        	
		        	 
	        	
	        }
	        
	        
	        
	        enregistreFichier(docCo, fichierXSD, fichierXML);
		
		} 
		catch (ParserConfigurationException pce) {
			System.out.println("Erreur de configuration");
			pce.printStackTrace();
		}
	}

}
