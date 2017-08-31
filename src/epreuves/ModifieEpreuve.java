package epreuves;

import java.util.Vector;

import modele.Categorie;
import modele.TypeLimite;

@SuppressWarnings("serial")
public class ModifieEpreuve extends NouvelleEpreuve {
	
	protected Epreuve epreuve;
	
	public ModifieEpreuve(Vector<Epreuve> pEpreuves, Categorie pCategorie, String pIndex) {
		// TODO Auto-generated constructor stub
		super (pEpreuves, pCategorie);
		

		for (Epreuve epv : pEpreuves) {
			if(epv.getNom().equals(pIndex)) {
				epreuve = epv;
				this.setTitle("Modifier : " + epreuve.getCategorie() + " -> " + epreuve.getNom());
				cbx_Type.setSelectedItem(epreuve.getType());
				txtNomEpreuve.setText(epreuve.getNom());
				chckbxActive.setSelected(epreuve.getActive());
				cbx_depart.setSelectedItem(epreuve.getTypeDepart().intitule());
				spinner_hDepart.setValue(epreuve.getHeureDepart().getHour());
				spinner_mnDepart.setValue(epreuve.getHeureDepart().getMinute());
				spinner_depart2H.setValue(epreuve.getHeureDepart2().getHour());
				spinner_depart2Mn.setValue(epreuve.getHeureDepart2().getMinute());
				chckbxEnLigne.setSelected(epreuve.getActive());
				spinner_jour.setValue(epreuve.getJour());
				spinner_coef.setValue(epreuve.getCoef());
				chckbxPrioritaire.setSelected(epreuve.getDepartage());
				cbx_limite.setSelectedItem(epreuve.getTypeLimite().intitule());
				if (epreuve.getTypeLimite().equals(TypeLimite.heure) || epreuve.getTypeLimite().equals(TypeLimite.temps)) {
					spinner_heureMaxH.setValue(epreuve.getHeureMaxi().getHour());
					spinner_heureMaxMn.setValue(epreuve.getHeureMaxi().getMinute());
					spinner_heureMaxPoint.setValue(epreuve.getPointsHeureMaxi());
					spinner_heureMaxMnPlus.setValue(epreuve.getPenaliteHeureMaxi().getMinute());
					spinner_heureMaxMnPar.setValue(epreuve.getParHeureMaxi().getMinute());
				}
				break;
			}
		}
		

		
		
		
	}
	
	/**
	 * modifie l'épreuve dans le tableau
	 */
	protected void enregistreEpreuve() {
		System.out.println("On modifie une épreuve");
		epreuve.setNom(txtNomEpreuve.getText());
		enregistre(epreuve);
	}
	

}
