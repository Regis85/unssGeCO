package epreuves;

import java.util.Vector;

import modele.Categorie;

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
