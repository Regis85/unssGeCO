package epreuves;

import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import au.com.bytecode.opencsv.CSVReader;
import unssGeCO.Preferences;

public class ImportCSVParcours {
	
	private Epreuve epreuve;
	private Preferences preferences;
	
	private CSVReader csvReader = null;

	@SuppressWarnings("static-access")
	public ImportCSVParcours(Epreuve pEpreuve, Preferences pPreferences) {
		epreuve = pEpreuve;
		preferences = pPreferences;
		
        JFileChooser dialogue = new JFileChooser();
        FileFilter csvFilter = new FileNameExtensionFilter("Fichiers csv", "csv");
        dialogue.addChoosableFileFilter(csvFilter);
        dialogue.setAcceptAllFileFilterUsed(false);
        dialogue.setCurrentDirectory(new File(preferences.getDossierTravail()));
         
        // affichage
		if(dialogue.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        
	        File fichierCsv = dialogue.getSelectedFile();
	        
	        if (fichierCsv != null) {
				try 
				{
					String dossier =  fichierCsv.getPath();
					preferences.setDossierTravail(dossier.substring(0, dossier.lastIndexOf(File.separator)));
					char separateur = ',';
					JOptionPane jop = new JOptionPane();
					try 
					{
						String separe = (String) jop.showInputDialog(null, "Veuillez indiquer le séparateur de champ du fichier \n, par défaut, seul le premier caractère est pris en compte !", "Importation des équipes", JOptionPane.QUESTION_MESSAGE, null ,null ,"," );
						if (separe != null && separe.length() > 0) separateur = separe.charAt(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				    csvReader = new CSVReader(new FileReader(fichierCsv),separateur,'"',0);
				    
				    String[] epreuveDetails = null;
		            while((epreuveDetails = csvReader.readNext())!=null)
		            {
		            	String nomParcours = nomUnique(epreuveDetails[0].toString());
		            	if (!epreuveDetails[0].toString().equals(nomParcours)) {
		            		new JOptionPane().showMessageDialog(null, epreuveDetails[0].toString() + " existait déjà, il a été changé pour " + nomParcours, "Attention", JOptionPane.WARNING_MESSAGE);
							
		            	}
		            	Parcours nouveauParcours = new Parcours(nomParcours);
		            	Balise nouvelleBalise;
		            	for (int i=1;i<epreuveDetails.length;i++) {
		            		nouvelleBalise = preferences.donneesBaliseDefaut(new Balise(), Integer.parseInt(epreuveDetails[i]));
		            		nouveauParcours.addBalise(nouvelleBalise);
		            	}
		            	epreuve.addParcours(nouveauParcours);
		            }
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
		        finally
		        {
					try
						{
							//closing the reader
							csvReader.close();
						}
					catch(Exception ee)
						{
							ee.printStackTrace();
						}
				}
	        }
	        else {
	        	System.out.println("Aucun fichier choisi");
	        }
		}
	}
	
	private String nomUnique(String nomParcours) {
		while (!estUnique(nomParcours)) {
			nomParcours += "1";
		}
		return nomParcours;
	}
	
	private boolean estUnique(String nomParcours) {
		boolean retour = true;
		for(Parcours parc : epreuve.getParcours()) {
			if(parc.getNom().equals(nomParcours)) {
				retour = false;
				break;
			}
		}
		return retour;
	}

}
