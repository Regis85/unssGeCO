package unssGeCO;

import net.gecosi.dataframe.SiDataFrame;

public class AutoPuceCoureur extends LecteurPuces {
	
	AjouteEquipe equipe;

	public AutoPuceCoureur(AjouteEquipe pEquipe) {
		equipe = pEquipe;
	}
	
	protected void lecteurPret(String pPort) {
		equipe.afficheBoutonAutoPuce(false, false, true);		
	}
	
	protected void lecteurLecture() {
		equipe.afficheBoutonAutoPuce(false, true, false);
		
	}
	
	protected void lecteurArret() {
		equipe.afficheBoutonAutoPuce(true, false, false);
	}
	
	/**
	 * Affiche les informations lues de la puce
	 * @param dataFrame les données de la puce
	 */
	protected void afficheDonnees(SiDataFrame dataFrame) {
		System.out.println("Série : " + dataFrame.getSiSeries() + " id : " + dataFrame.getSiNumber() + " nb hits : " + dataFrame.getNbPunches());
		equipe.setAutoPuce(dataFrame.getSiNumber());
	}

}
