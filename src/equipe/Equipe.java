package equipe;

import java.util.Vector;

import javax.swing.JOptionPane;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import modele.Categorie;
import unssGeCO.Preferences;

public class Equipe {

	protected int idEquipe;
	
	protected String categorie;
	protected String dossard = "";
	protected String etablissement;
	protected String nomEquipe;
	protected String ville;
	
	protected boolean absent = false;
	protected boolean dsq = false;
	protected boolean nonClasse = false;
	
	protected Coureur[] coureurs = new Coureur[4];
	
	protected boolean arrive = false;
	
	protected Preferences preferences;
	
	public Equipe(Preferences pPreferences) {

		preferences = pPreferences;
		
		idEquipe = preferences.getIdSuivant();
		for (int i=0;i<4;i++) coureurs[i] = new Coureur("", "", "", "");
	}

	public Equipe(String[] pNewEquipe, Preferences pPreferences) {

		preferences = pPreferences;

		System.out.println("on construit");
		
		idEquipe = preferences.getIdSuivant();
		//System.out.println("indice " + idEquipe);
		
		if (pNewEquipe[2].isEmpty()) {
			nomEquipe = pNewEquipe[0] + "_" + idEquipe;
			JOptionPane.showMessageDialog(null, "Une équipe doit avoir un nom\n" + nomEquipe + " lui a été attribué.", "Attention", JOptionPane.ERROR_MESSAGE);
			
		}
		else {
			nomEquipe = pNewEquipe[2];
		}
		
		etablissement = pNewEquipe[0];
		
		ville = pNewEquipe[1];
		
		if (pNewEquipe[3].isEmpty()) {
			categorie = Categorie.College.intitule();
			JOptionPane.showMessageDialog(null, "Une équipe doit avoir une catégorie\n" + Categorie.College.intitule() + " lui a été attribué.", "Attention", JOptionPane.ERROR_MESSAGE);
			
		}
		else if(!Categorie.estConnu(pNewEquipe[3].toString())) {
			categorie = Categorie.College.intitule();
			JOptionPane.showMessageDialog(null, "la catégorie " + pNewEquipe[3].toString() + " n'existe pas, " + Categorie.College.intitule() + " lui a été attribué.", "Attention", JOptionPane.ERROR_MESSAGE);
		}
		else {
			categorie = pNewEquipe[3];
		}
		
		for(int i=0;i<4;i++) {
			coureurs[i] = new Coureur( pNewEquipe[4+(4*i)], pNewEquipe[5+(4*i)], pNewEquipe[6+(4*i)], pNewEquipe[7+(4*i)]);			
		}
		//System.out.println("on finit de construire " + idEquipe);
	}
	
	public Equipe(Element pEquipe) {
		this.setNomEquipe(pEquipe.getElementsByTagName("nom").item(0).getTextContent());
		this.setCategorie(pEquipe.getAttribute("categorie"));
		this.setAbsent(Boolean.valueOf(pEquipe.getAttribute("absent")));
		this.setDossard(pEquipe.getAttribute("dossard"));
		this.setDsq(Boolean.valueOf(pEquipe.getAttribute("dsq")));
		this.setEtablissement(pEquipe.getAttribute("etablissement"));
		this.setId(Integer.parseInt(pEquipe.getAttribute("id")));
		this.setNonClasse(Boolean.valueOf(pEquipe.getAttribute("nonClasse")));
		this.setVille(pEquipe.getAttribute("ville"));
		
		NodeList nouveauCoureurs = pEquipe.getElementsByTagName("coureur");
		
		//listNo = root.getElementsByTagName("titi");
		for (int i = 0; i < nouveauCoureurs.getLength(); i++) {
			Element e = (Element) nouveauCoureurs.item(i);
			coureurs[i] = new Coureur(e.getAttribute("nom"), e.getAttribute("prenom"), e.getAttribute("sexe"), e.getTextContent());
			
		}
		
		//for (int i=0;i<4;i++) coureurs[i] = new Coureur("", "", "", "");
		System.out.println("On a une équipe " + this.getNomEquipe());
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
	
	public boolean equipeConnue(Vector<Equipe> pEquipes) {
		boolean retour = false;
		for (Equipe eq : pEquipes) {
			if(eq.getNomEquipe().equals(this.getNomEquipe())) {
				retour = true;
				break;
			}
		}
		return retour;
	}
	
	
	
}
