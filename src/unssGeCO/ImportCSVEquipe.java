package unssGeCO;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameMappingStrategy;
import model.EquipeImport;

public class ImportCSVEquipe {
	
	private Vector<Equipe> equipes;
	private Preferences preferences = new Preferences();
	
	private CSVReader csvReader = null;

	public ImportCSVEquipe(Vector<Equipe> pEquipes) {
		
		equipes = pEquipes;
		
		File initialFile = new File(preferences.getFichier());
		
		File repertoireCourant = null;
        try {
            repertoireCourant = new File(".").getCanonicalFile();
        } catch(IOException e) {
			e.printStackTrace();
		}
        JFileChooser dialogue = new JFileChooser(preferences.getFichier());
        FileFilter csvFilter = new FileNameExtensionFilter("Fichiers csv.", "csv");
        dialogue.addChoosableFileFilter(csvFilter);
        dialogue.setAcceptAllFileFilterUsed(false);
         
        // affichage
        dialogue.showOpenDialog(null);
        
        File fichierCsv = dialogue.getSelectedFile();
        
        if (fichierCsv != null) {
			try 
			{
				// System.out.println("Import des équipes depuis " + dialogue.getSelectedFile().getPath());
				// System.out.println("Import des équipes depuis " + dialogue.getSelectedFile().getName());
				preferences.setFichier(fichierCsv.getPath());
				char separateur = ',';
				JOptionPane jop = new JOptionPane();
				try 
				{
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
	            	//Printing to the console
	            	System.out.println(Arrays.toString(equipeDetails));
	            	equipes.add(new Equipe(equipeDetails, preferences));
	            }

			} catch (Exception e) {
				e.printStackTrace();
			}
	        finally
	        {
				try
					{
						//closing the reader
						//csvReader.close();
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
