package puces;

import net.gecosi.dataframe.SiDataFrame;
import unssGeCO.Fenetre;

public class LitBalisesPuces extends LecteurPuces {
	
	Fenetre maFenetre;
	
	public LitBalisesPuces(Fenetre pFenetre) {
		super();
		maFenetre = pFenetre;
	}
	
	protected void lecteurPret(String pPort) {
		maFenetre.afficheBouton(false, false, true);
		
	}
	
	protected void lecteurLecture() {
		maFenetre.afficheBouton(false, true, false);
		
	}
	
	protected void lecteurArret() {
		maFenetre.afficheBouton(true, false, false);
	}
	

	
	/**
	 * Affiche les informations lues de la puce
	 * @param dataFrame les données de la puce
	 */
	protected void afficheDonnees(SiDataFrame dataFrame) {
		String serie = dataFrame.getSiSeries();
		String numPuce = dataFrame.getSiNumber();
		int nbTemps = dataFrame.getNbPunches();
		String balises = " balise";
		if(nbTemps > 1) 
			balises += "s";
		balises += " ";
		maFenetre.setLblPuce("Puce -> série " + serie + " : " + numPuce + " - " + nbTemps + balises);
		PuceDonneesLues donneesLues = new PuceDonneesLues(dataFrame);
		donneesLues.setVisible(true);
		System.out.println(donneesLues.getGarde());
		if(donneesLues.getGarde()) {
			maFenetre.addTempsCourse(donneesLues.getDonneesLues());
		}
		
	}

}
