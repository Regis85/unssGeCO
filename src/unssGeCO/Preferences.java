package unssGeCO;

public class Preferences {

	private String fichierTravail ;
	private static int nextId = 0;

	public Preferences() {
		litXMLPreferences();
		System.out.println(fichierTravail);
	}
	
	public void litXMLPreferences() {
		fichierTravail = "./lib/test/ressources/equipes.csv";
	}


	public String getFichier() {
		return fichierTravail;
	}
	
	public void setFichier(String pNomFichier) {
		fichierTravail = pNomFichier;
	}
	
	public void saveFichier(String pNomFichier) {
		fichierTravail = pNomFichier;
	}
	
	public void idSuivant() {
		nextId ++;
	}
	
	public int getIdSuivant() {
		idSuivant();
		return nextId;
	}
	
}
