package unssGeCO;

import java.util.Arrays;

public class Equipe {

	protected int idEquipe;
	protected String etablissement;
	protected String ville;
	protected String nomEquipe;
	protected String dossard = "";
	protected boolean nonClasse = false;
	protected boolean dsq = false;
	protected boolean absent = false;
	protected boolean arrive = false;
	protected String categorie;
	

	protected Preferences preferences;
	
	protected Coureur[] coureurs = new Coureur[4];

	public Equipe( Preferences pPreferences) {

		preferences = pPreferences;
		
		idEquipe = preferences.getIdSuivant();
		for (int i=0;i<4;i++) coureurs[i] = new Coureur("", "", "", "");
	}

	public Equipe(String[] pNewEquipe, Preferences pPreferences) {

		preferences = pPreferences;

		System.out.println("on construit");
		idEquipe = preferences.getIdSuivant();
		etablissement = pNewEquipe[0];
		ville = pNewEquipe[1];
		nomEquipe = pNewEquipe[2];
		categorie = pNewEquipe[3];
		for(int i=0;i<4;i++) {
			coureurs[i] = new Coureur( pNewEquipe[4+(4*i)], pNewEquipe[5+(4*i)], pNewEquipe[6+(4*i)], pNewEquipe[7+(4*i)]);			
		}
		System.out.println("on finit de construire " + idEquipe);
	}
	
	public int getId() {
		return idEquipe;
	}
	
	public void setId(int pId) {
		idEquipe = pId;
	}
	
	public Coureur[] getCoureurs() {
		return coureurs;
	}
	
	public String getEtablissement() {
		return etablissement;
	}
	
	public void setEtablissement(String pNom) {
		etablissement = pNom;
	}
	
	public String getVille() {
		return ville;
	}
	
	public void setVille(String pVille) {
		ville = pVille;
	}
	
	public String getNomEquipe() {
		return nomEquipe;
	}
		
	public void setNomEquipe(String pEquipe) {
		nomEquipe = pEquipe;
	}

	public String getDossard() {
		return dossard;
	}
	
	public void setDossard(String pDossard) {
		dossard = pDossard;
	}

	public boolean getNonClasse() {
		return nonClasse;
	}
	
	public void setNonClasse(boolean pNonClasse) {
		nonClasse = pNonClasse;
	}
	
	public boolean getAbsent() {
		return absent;
	}
	
	public void setAbsent(boolean pAbsent) {
		absent = pAbsent;
	}
	
	public void setDsq(boolean pDsq) {
		dsq = pDsq;
	}
	public boolean getDsq() {
		return dsq;
	}
	
	public void setArrive(boolean pArrive) {
		arrive = pArrive;
	}
	public boolean getArrive() {
		return arrive;
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public void setCategorie(String pCategorie) {
		categorie = pCategorie;
	}
	
}
