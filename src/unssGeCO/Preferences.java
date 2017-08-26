package unssGeCO;

import java.io.File;

import epreuves.Balise;

public class Preferences {

	private String fichierTravail ;
	private String dossierTravail ;
	private Balise baliseDefaut = new Balise();
	
	private static int nextId = 0;

	public Preferences() {
		
	}
	
	public void litXMLPreferences() {
		
	}
	
	public void idSuivant() {
		nextId ++;
	}
	
	public int getIdSuivant() {
		idSuivant();
		return nextId;
	}
	
	public void setBaliseDefaut(Balise pBalise) {
		baliseDefaut = pBalise;
	}
	
	public Balise donneesBaliseDefaut(Balise pBalise, int pCode) {
		
		pBalise.setCode(pCode);
		pBalise.setTrouveBonif(this.getBaliseDefaut().getTrouveBonif());
		pBalise.setTrouvePoints(this.getBaliseDefaut().getTrouvePoints());
		pBalise.setTrouveHeure(this.getBaliseDefaut().getTrouveHeure());
		pBalise.setPmBonif(this.getBaliseDefaut().getPmBonif());
		pBalise.setPmPoints(this.getBaliseDefaut().getPmPoints());
		pBalise.setPmHeure(this.getBaliseDefaut().getPmHeure());
		
		return pBalise;
	}
	
	public Balise getBaliseDefaut() {
		return baliseDefaut;
	}
	/*
	public String[] getCategories() {
		return categories;
	}
	*/
	
	public void setFichierTravail(String pFichierTravail) {
		fichierTravail = pFichierTravail;
		dossierTravail = fichierTravail.substring(0,fichierTravail.lastIndexOf(File.separator));
	}
	
	public String getFichierTravail() {
		return fichierTravail;
	}

	public void setDossierTravail(String pDossierTravail) {
		dossierTravail = pDossierTravail;
	}
	
	public String getDossierTravail() {
		String retour = dossierTravail != null ? dossierTravail : "";
		return retour;
	}
	
}
