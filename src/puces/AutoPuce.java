package puces;

import net.gecosi.dataframe.SiDataFrame;
import unssGeCO.Fenetre;

public class AutoPuce extends LecteurPuces {
	
	Fenetre maFenetre;

	public AutoPuce(Fenetre pFenetre) {
		maFenetre = pFenetre;
	}
	
	protected void lecteurPret(String pPort) {
		maFenetre.afficheBoutonAutoPuce(false, false, true);		
	}
	
	protected void lecteurLecture() {
		maFenetre.afficheBoutonAutoPuce(false, true, false);
		
	}
	
	protected void lecteurArret() {
		maFenetre.afficheBoutonAutoPuce(true, false, false);
	}
	
	/**
	 * Affiche les informations lues de la puce
	 * @param dataFrame les données de la puce
	 */
	protected void afficheDonnees(SiDataFrame dataFrame) {
		String serie = dataFrame.getSiSeries();
		String numPuce = dataFrame.getSiNumber();
		maFenetre.setLblPuce("Puce -> série " + serie + " : " + numPuce);
	}

}
