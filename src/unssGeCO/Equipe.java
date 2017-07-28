package unssGeCO;

public class Equipe {
	
	protected String etablissement;
	protected String ville;
	protected String nomEquipe;
	protected String dossard;
	protected boolean nonClasse = false;
	protected boolean dsq = false;
	protected boolean absent = false;
	protected boolean arrive = false;
	protected String categorie;
	
	protected Coureur[] coureurs = new Coureur[4];

	public Equipe() {
		// TODO Auto-generated constructor stub
		for (int i=0;i<4;i++) coureurs[i] = new Coureur("", "", "", "");
		/*
		etablissement = "Collège Beaussire";
		ville = "Luçon";
		nomEquipe = "Beaussire 1";
		dossard = "1";
		categorie = "Collège";
		coureurs[0] = new Coureur("Bouguin", "Régis", "G", "2109614");
		*/
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
