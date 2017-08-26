package modele;

public class EquipeImport {
	
	private int id;
	private String etablissement;
	private String ville;
	private String nom_equipe;
	private String categorie;
	private String coureur_1;
	private String prenom_1;
	private String sexe_1;
	private String puce_1;
	private String coureur_2;
	private String prenom_2;
	private String sexe_2;
	private String puce_2;
	private String coureur_3;
	private String prenom_3;
	private String sexe_3;
	private String puce_3;
	private String coureur_4;
	private String prenom_4;
	private String sexe_4;
	private String puce_4;
	private String dossard;

	public EquipeImport() {
		// TODO Auto-generated constructor stub
	}
	
	public void setId(int pId) {
		id = pId;
	}
	public int getId() {
		return id;
	}
	public String getEtablissement() {
		return etablissement;
	}
	public String getVille() {
		return ville;
	}
	public String getNom_equipe() {
		return nom_equipe;
	}
	public String getCategorie() {
		return categorie;
	}
	public String getCoureur_1() {
		return coureur_1;
	}
	public String getPrenom_1() {
		return prenom_1;
	}
	public String getSexe_1() {
		return sexe_1;
	}
	public String getPuce_1() {
		return puce_1;
	}
	public String getCoureur_2() {
		return coureur_2;
	}
	public String getPrenom_2() {
		return prenom_2;
	}
	public String getSexe_2() {
		return sexe_2;
	}
	public String getPuce_2() {
		return puce_2;
	}
	public String getCoureur_3() {
		return coureur_3;
	}
	public String getPrenom_3() {
		return prenom_3;
	}
	public String getSexe_3() {
		return sexe_3;
	}
	public String getPuce_3() {
		return puce_3;
	}
	public String getCoureur_4() {
		return coureur_4;
	}
	public String getPrenom_4() {
		return prenom_4;
	}
	public String getSexe_4() {
		return sexe_4;
	}
	public String getPuce_4() {
		return puce_4;
	}
	public String getDossard() {
		return dossard;
	}
	
	public void setEtablissement(String pEtablissement) {
		etablissement = pEtablissement;
	}
	public void setVille(String pVille) {
		ville = pVille;
	}
	public void setNom_equipe(String pNom_equipe) {
		nom_equipe = pNom_equipe;
	}
	public void setCategorie(String pCategorie) {
		categorie = pCategorie;
	}
	public void setCoureur_1(String pCoureur_1) {
		coureur_1 = pCoureur_1;
	}
	public void setPrenom_1(String pPrenom_1) {
		prenom_1 = pPrenom_1;
	}
	public void setSexe_1(String pSexe_1) {
		sexe_1 = pSexe_1;
	}
	public void setPuce_1(String pPuce_1) {
		puce_1 = pPuce_1;
	}
	public void setCoureur_2(String pCoureur_2) {
		coureur_2 = pCoureur_2;
	}
	public void setPrenom_2(String pPrenom_2) {
		prenom_2 = pPrenom_2;
	}
	public void setSexe_2(String pSexe_2) {
		sexe_2 = pSexe_2;
	}
	public void setPuce_2(String pPuce_2) {
		puce_2 = pPuce_2;
	}
	public void setCoureur_3(String pCoureur_3) {
		coureur_3 = pCoureur_3;
	}
	public void setPrenom_3(String pPrenom_3) {
		prenom_3 = pPrenom_3;
	}
	public void setSexe_3(String pSexe_3) {
		sexe_3 = pSexe_3;
	}
	public void setPuce_3(String pPuce_3) {
		puce_3 = pPuce_3;
	}
	public void setCoureur_4(String pCoureur_4) {
		coureur_4 = pCoureur_4;
	}
	public void setPrenom_4(String pPrenom_4) {
		prenom_4 = pPrenom_4;
	}
	public void setSexe_4(String pSexe_4) {
		sexe_4 = pSexe_4;
	}
	public void setPuce_4(String pPuce_4) {
		puce_4 = pPuce_4;
	}
	public void setDossard(String pDossard) {
		dossard = pDossard;
	}
	
	@Override
	public String toString() {
		return (dossard+" "+etablissement+" -> " + ville+" "+nom_equipe+" "+categorie+" "+coureur_1+" "+prenom_1+" "+sexe_1+" "+puce_1+" "+coureur_2+" "+prenom_2+" "+sexe_2+" "+puce_2+" "+coureur_3+" "+prenom_3+" "+sexe_3+" "+puce_3+" "+coureur_4+" "+ prenom_4+" "+sexe_4+" "+puce_4);		
	}

}
