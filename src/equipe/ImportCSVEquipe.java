package equipe;


import java.io.File;
import java.io.FileReader;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import au.com.bytecode.opencsv.CSVReader;
import unssGeCO.Preferences;

public class ImportCSVEquipe {
	
	private Vector<Equipe> equipes;
	private Preferences preferences;
	
	private CSVReader csvReader = null;

	public ImportCSVEquipe(Vector<Equipe> pEquipes, Preferences pPreferences) {
		
		equipes = pEquipes;
		preferences = pPreferences;
		/*
		File repertoireCourant = null;
        try {
            repertoireCourant = new File(".").getCanonicalFile();
        } catch(IOException e) {
			e.printStackTrace();
		}
		*/
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
						@SuppressWarnings("static-access")
						String separe = (String) jop.showInputDialog(null, "Veuillez indiquer le séparateur de champ du fichier \n, par défaut, seul le premier caractère est pris en compte !", "Importation des équipes", JOptionPane.QUESTION_MESSAGE, null ,null ,"," );
						if (separe != null && separe.length() > 0) separateur = separe.charAt(0);
					} catch (Exception e) {
						e.printStackTrace();
					}
				    csvReader = new CSVReader(new FileReader(fichierCsv),separateur,'"',1);
				    
	
		            //equipeDetails stores the values current line
		            String[] equipeDetails = null;
		            while((equipeDetails = csvReader.readNext())!=null)
		            {
		            	// System.out.println(Arrays.toString(equipeDetails));
		            	Equipe nouvelleEquipe = new Equipe(equipeDetails, preferences);
		            	String exNom = nouvelleEquipe.getNomEquipe();
		            	while (nouvelleEquipe.equipeConnue(equipes)) {
		            		nouvelleEquipe.setNomEquipe(nouvelleEquipe.getNomEquipe() + "1");
		            		JOptionPane.showMessageDialog(null, "L'équipe " + exNom + " existe déjà, " + nouvelleEquipe.getNomEquipe() + " lui a été attribué", "Attention", JOptionPane.ERROR_MESSAGE);
		            	}
		            	equipes.add(nouvelleEquipe);
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

}
