package unssGeCO;

import java.util.Arrays;

import javax.swing.JOptionPane;

public class Coureur {
	
	protected String nom;
	protected String prenom;
	protected String sexe;
	protected String puce;

	public Coureur() {
		// TODO Auto-generated constructor stub
	}

	public Coureur(String pNom, String pPrenom, String pSexe, String pPuce) {
		nom = pNom;
		prenom = pPrenom;
		sexe = pSexe;
		puce = pPuce;
	}
	
	public void setNom(String pNom) {
		nom = pNom;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setPrenom(String pPrenom) {
		prenom = pPrenom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public void setSexe(String pSexe) {
		String[] bonSexes = {"F" , "G"};
		pSexe = pSexe.toUpperCase();
		if(in_array(bonSexes, pSexe)) {
			sexe = pSexe;
		} else if (!pSexe.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Le sexe ne peut être que F pour fille ou G pour garçon", "Attention", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String getSexe() {
		return sexe;
	}
	
	public void setPuce(String pPuce) {
		if (verifiePuceUnique(pPuce))
			puce = pPuce;
	}
	
	public String getPuce() {
		return puce;
	}
	
	/**
	 * vérifie si une valeur est dans un tableau
	 * 
	 * @param arr String[] Le tableau de valeurs possible
	 * @param targetValue String La valeur recherchée
	 * @return
	 */
	protected static boolean in_array(String[] arr, String targetValue) {
		return Arrays.asList(arr).contains(targetValue);
	}
	
	/**
	 * Vérifie que la puce n'est pas déjà attribuée
	 * 
	 * @param pPuce
	 * @return
	 */
	protected boolean verifiePuceUnique(String pPuce) {
		boolean retour = true;
		return retour;
	}

}
