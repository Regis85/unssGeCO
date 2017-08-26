/*
 * Copyright 2017 Régis Bouguin
 * 
 * This file is part of unssGeCO
 * 
 * coUnss is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 * coUnss is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with unssGeCO.  If not, see <http://www.gnu.org/licenses/>
 * 
 */
package unssGeCO;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import epreuves.Balise;
import epreuves.CreeBalise;
import epreuves.Epreuve;
import epreuves.ImportCSVParcours;
import epreuves.ModifieEpreuve;
import epreuves.NouvelleEpreuve;
import epreuves.Parcours;
import epreuves.Relais;
import equipe.AjouteEquipe;
import equipe.Coureur;
import equipe.Equipe;
import equipe.ImportCSVEquipe;
import equipe.InitialiseDossards;
import fichier.ChargeFichier;
import fichier.Fichier;
import modele.Categorie;
import modele.TypeEpreuve;
import net.gecosi.dataframe.SiDataFrame;
import puces.AutoPuce;
import puces.LecteurPuces;
import puces.LitBalisesPuces;
import temps.TempsCourse;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Fenetre {

	protected JFrame frmCoUnss;
	
	//private Fichier donneesCourse;
	
	protected int newId;
	
	protected JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
	protected JMenuItem mntmEnregistrerSous = new JMenuItem("Enregistrer sous...");
	
	protected JButton btnLanceLecture;
	protected JButton btnConnectEnCours;
	protected JButton btnArreteLecture;
	
	protected JButton btnLanceAutoPuce;
	protected JButton btnConnexionAutoPuce;
	protected JButton btnArreteAutoPuce;
	
	protected JPanel panelEquipes;
	protected JPanel panelEtapes = new JPanel();
	protected JPanel panel_parcours = new JPanel();
	protected JPanel panel_balises = new JPanel();
	protected JPanel panel_afficheBalise = new JPanel();
	
	protected JLabel lblPuce;
	protected JLabel lblLectureDesPuces = new JLabel("Lecture des puces : ");

	protected LecteurPuces lecteurPuce;
	protected LecteurPuces lecteurAutoPuce;
	
	protected int nbEquipes;
	
	protected Vector<Equipe> equipes = new Vector<Equipe>();
	private Vector<Epreuve> epreuves = new Vector<Epreuve>();
	private Vector<TempsCourse> tempsEnCourse = new Vector<TempsCourse>();
	
	private String equipeChoisie;
	
	private Preferences preferences = new Preferences();
	
	protected JComboBox<String> comboBoxCategories;
	protected JComboBox<String> comboBoxEtapes = new JComboBox<String>();
	protected JComboBox<String> comboBox_parcours = new JComboBox<String>();
	
	protected JSpinner spinner_jour = new JSpinner();
	
	final int arrive = 1;
	final int abandon = 2;
	final int dsq = 3;
	
	protected Categorie[] categories = Categorie.values();
	
	private FileNameExtensionFilter filter = new FileNameExtensionFilter( "Fichiers CO", "uco","xml");
	private FileNameExtensionFilter filter2 = new FileNameExtensionFilter( "Fichiers xml", "xml");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fenetre window = new Fenetre();
					window.frmCoUnss.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fenetre() {
		initialize();
	}
	
	public void visible() {
		this.frmCoUnss.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	protected void initialize() {
		frmCoUnss = new JFrame();
		frmCoUnss.getContentPane().setEnabled(false);
		frmCoUnss.setTitle("C.O. UNSS");
		frmCoUnss.setBounds(100, 100, 1029, 493);
		frmCoUnss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmCoUnss.setJMenuBar(menuBar);
		
		/***** menu fichier *****/
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmNouveau = new JMenuItem("Nouveau");
		mntmNouveau.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/new.png")));
		//mntmNouveau.setEnabled(false);
		mntmNouveau.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				equipes = new Vector<Equipe>();
				afficheEquipes();
				epreuves = new Vector<Epreuve>();
				afficheEpreuves();
			}
		});
		mnFichier.add(mntmNouveau);
		
		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir...");
		mntmOuvrir.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/open.png")));
		mntmOuvrir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ouvrir();
			}
		});
		mnFichier.add(mntmOuvrir);
		
		mntmEnregistrer.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/save.png")));
		//mntmEnregistrer.setEnabled(false);
		mntmEnregistrer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				enregistre();
			}
		});
		mnFichier.add(mntmEnregistrer);
		
		mntmEnregistrerSous.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/exportMenu.png")));
		mntmEnregistrerSous.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				enregistreSous();
			}
		});
		//mntmEnregistrerSous.setEnabled(false);
		mnFichier.add(mntmEnregistrerSous);
		
		JSeparator separator = new JSeparator();
		mnFichier.add(separator);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/exit.png")));
		mntmQuitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFichier.add(mntmQuitter);
		
		/***** Menu préférences *****/
		JMenu mnOutils = new JMenu("Outils");
		menuBar.add(mnOutils);
		
		JMenuItem mntmPreferences = new JMenuItem("Préférences");
		mnOutils.add(mntmPreferences);
		
		JMenuItem mntmBalises = new JMenuItem("Balise par défaut");
		mntmBalises.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				setBaliseDefaut();
			}
		});
		mnOutils.add(mntmBalises);
		
		/***** Menu aide *****/
		JMenu mnAides = new JMenu("Aides");
		menuBar.add(mnAides);
		
		JSeparator separator2 = new JSeparator();
		mnAides.add(separator2);
		
		JMenuItem mntmAProposDe = new JMenuItem("À propos de...");
		mnAides.add(mntmAProposDe);
		
		JMenuItem mntmAide = new JMenuItem("Aide");
		mnAides.add(mntmAide);
		
		/***** Panneau supérieur *****/
		JPanel pnlSauvegarde = new JPanel();
		frmCoUnss.getContentPane().add(pnlSauvegarde, BorderLayout.NORTH);
		pnlSauvegarde.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JToolBar tbSauve = new JToolBar();
		tbSauve.setEnabled(false);
		tbSauve.setPreferredSize(new Dimension(100, 50));
		tbSauve.setToolTipText("Sauvegarde");
		tbSauve.setBorder(new TitledBorder(null, "Sauvegarde", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pnlSauvegarde.add(tbSauve);
		
		JToolBar tbLanceLecture = new JToolBar();
		tbLanceLecture.setEnabled(false);
		tbLanceLecture.setPreferredSize(new Dimension(150, 50));
		tbLanceLecture.setToolTipText("Sauvegarde automatique");
		tbLanceLecture.setBorder(new TitledBorder(null, "Sauvegarde automatique", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pnlSauvegarde.add(tbLanceLecture);
		
		//bouton Enregistrer
		JButton btnEnregistrer = new JButton("");
		btnEnregistrer.setToolTipText("Enregistrer");
		btnEnregistrer.setPreferredSize(new Dimension(27,27));
		btnEnregistrer.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/save32.png")));
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enregistre();
			}
		});

		tbSauve.add(btnEnregistrer);
		
		JButton btnExportEquipe = new JButton("");
		btnExportEquipe.setEnabled(false);
		btnExportEquipe.setToolTipText("Exporter les équipes en CSV");
		//Exporter les équipes en CSV
		btnExportEquipe.setPreferredSize(new Dimension(27,27));
		btnExportEquipe.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/exportMenu.png")));
		btnExportEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		tbSauve.add(btnExportEquipe);

		JSpinner comboBoxMinutes = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		comboBoxMinutes.setEnabled(false);
		comboBoxMinutes.setToolTipText("Sauvegarde automatique toutes les ...");
		tbLanceLecture.add(comboBoxMinutes);
		
		JLabel lblMn = new JLabel("MN");
		lblMn.setEnabled(false);
		lblMn.setPreferredSize(new Dimension(60, 24));
		lblMn.setLabelFor(comboBoxMinutes);
		tbLanceLecture.add(lblMn);
		
		JButton btnSauvegardeAutomatique = new JButton("");
		btnSauvegardeAutomatique.setEnabled(false);
		btnSauvegardeAutomatique.setToolTipText("Lancer la sauvegarde automatique");
		btnSauvegardeAutomatique.setPreferredSize(new Dimension(27,27));
		btnSauvegardeAutomatique.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/run.png")));
		tbLanceLecture.add(btnSauvegardeAutomatique);
		
		pnlSauvegarde.setBorder(new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION)); 
		
		JToolBar tbLecture = new JToolBar();
		tbLecture.setToolTipText("Heure zéro et lecture des puces");
		tbLecture.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u00C9preuve", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlSauvegarde.add(tbLecture);
		
		JSpinner cmbHeureDepart = new JSpinner(new SpinnerNumberModel(0, 0, 24, 1));
		cmbHeureDepart.setToolTipText("Heure zéro");
		cmbHeureDepart.setPreferredSize(new Dimension(45, 20));
		
		JLabel lblJour = new JLabel("jour ");
		tbLecture.add(lblJour);
		
		//JSpinner spinner_jour = new JSpinner();
		spinner_jour.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner_jour.setPreferredSize(new Dimension(40, 20));
		spinner_jour.setMinimumSize(new Dimension(40, 20));
		tbLecture.add(spinner_jour);
		
		JLabel lblHeureZro = new JLabel("Heure zéro : ");
		lblHeureZro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeureZro.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblHeureZro.setPreferredSize(new Dimension(80, 14));
		lblHeureZro.setMinimumSize(new Dimension(80, 14));
		tbLecture.add(lblHeureZro);
		tbLecture.add(cmbHeureDepart);
		
		JLabel lblHeDepart = new JLabel(" H ");
		lblHeDepart.setLabelFor(cmbHeureDepart);
		tbLecture.add(lblHeDepart);
		
		JSpinner cmbMinDepart = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		cmbMinDepart.setToolTipText("Minute zéro");
		cmbMinDepart.setPreferredSize(new Dimension(45, 20));
		tbLecture.add(cmbMinDepart);
		
		JLabel lblMinDepart = new JLabel(" MN ");
		lblMinDepart.setLabelFor(cmbMinDepart);
		tbLecture.add(lblMinDepart);
		
		//JLabel lblLectureDesPuces = new JLabel("Lecture des puces : ");
		lblLectureDesPuces.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLectureDesPuces.setPreferredSize(new Dimension(120, 14));
		lblLectureDesPuces.setLabelFor(btnSauvegardeAutomatique);
		tbLecture.add(lblLectureDesPuces);
		
		btnLanceLecture = new JButton("");
		btnLanceLecture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				litPuces();
			}
		});
		btnLanceLecture.setToolTipText("Lancer la lecture des puces");
		btnLanceLecture.setPreferredSize(new Dimension(27,27));
		btnLanceLecture.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/play.png")));
		tbLecture.add(btnLanceLecture);
		
		btnArreteLecture = new JButton("");
		btnArreteLecture.setVisible(false);
		btnArreteLecture.setToolTipText("Arrêter la lecture des puces");
		btnArreteLecture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arreteLitPuces();
			}
		});
		btnArreteLecture.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/stop.png")));
		btnArreteLecture.setPreferredSize(new Dimension(27, 27));
		btnArreteLecture.setSelectedIcon(null);
		tbLecture.add(btnArreteLecture);
		
		btnConnectEnCours = new JButton("");
		btnConnectEnCours.setVisible(false);
		btnConnectEnCours.setToolTipText("Connexion en cours");
		btnConnectEnCours.setPreferredSize(new Dimension(27, 27));
		btnConnectEnCours.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/attente.png")));
		tbLecture.add(btnConnectEnCours);
		
		lblPuce = new JLabel("");
		frmCoUnss.getContentPane().add(lblPuce, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setAutoscrolls(true);
		panel.setPreferredSize(new Dimension(450, 10));
		panel.setBorder(new TitledBorder(null, "équipes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmCoUnss.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblequipes = new JLabel(getLblEquipes());
		panel_1.add(lblequipes);
		
		JButton btnAjouteEquipe = new JButton("");
		btnAjouteEquipe.setToolTipText("Ajouter une équipe");
		btnAjouteEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEquipe();
			}
		});
		btnAjouteEquipe.setPreferredSize(new Dimension(32, 32));
		btnAjouteEquipe.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/plus.png")));
		panel_1.add(btnAjouteEquipe);
		
		JButton btnModifieEquipe = new JButton("");
		btnModifieEquipe.setToolTipText("Modifier l'équipe sélectionnée");
		btnModifieEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifieEquipe();
			}
		});
		btnModifieEquipe.setPreferredSize(new Dimension(32, 32));
		btnModifieEquipe.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/find.png")));
		panel_1.add(btnModifieEquipe);
		
		JButton btnSupprimeBouton = new JButton("");
		btnSupprimeBouton.setToolTipText("Supprimer l'équipe sélectionnée");
		btnSupprimeBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				effaceEquipe();
			}
		});
		btnSupprimeBouton.setPreferredSize(new Dimension(32, 32));
		btnSupprimeBouton.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/delete.png")));
		panel_1.add(btnSupprimeBouton);
		
		JButton btnAutodossard = new JButton("");
		btnAutodossard.setToolTipText("Attribuer des numéros de dossard automatiquement");
		btnAutodossard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InitialiseDossards autoDossards = new InitialiseDossards(equipes, Categorie.valueOfByCategorie(comboBoxCategories.getSelectedItem().toString()));
				autoDossards.setModal(true);
				autoDossards.setLocationRelativeTo(null);
				autoDossards.setVisible(true);
				afficheEquipes();
			}
		});
		
		btnAutodossard.setPreferredSize(new Dimension(32, 32));
		btnAutodossard.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/dossard.png")));
		panel_1.add(btnAutodossard);
		
		JButton btnImportecsv = new JButton("");
		btnImportecsv.setToolTipText("Importer les équipes à partir d'un fichier CSV");
		btnImportecsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importeEquipe();
			}
		});
		btnImportecsv.setPreferredSize(new Dimension(32, 32));
		btnImportecsv.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/import.png")));
		panel_1.add(btnImportecsv);
		
		JLabel lblAutoPuce = new JLabel("Auto puce :");
		panel_1.add(lblAutoPuce);
		
		btnLanceAutoPuce = new JButton("");
		btnLanceAutoPuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				litAutoPuces();
			}
		});
		btnLanceAutoPuce.setPreferredSize(new Dimension(27, 27));
		btnLanceAutoPuce.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/play.png")));
		panel_1.add(btnLanceAutoPuce);
		
		btnArreteAutoPuce = new JButton("");
		btnArreteAutoPuce.setVisible(false);
		btnArreteAutoPuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arreteAutoPuces();
			}
		});
		btnArreteAutoPuce.setPreferredSize(new Dimension(27, 27));
		btnArreteAutoPuce.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/stop.png")));
		panel_1.add(btnArreteAutoPuce);
		
		btnConnexionAutoPuce = new JButton("");
		btnConnexionAutoPuce.setVisible(false);
		btnConnexionAutoPuce.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/attente.png")));
		btnConnexionAutoPuce.setPreferredSize(new Dimension(27, 27));
		panel_1.add(btnConnexionAutoPuce);
		
		panelEquipes = new JPanel();
		panelEquipes.setAutoscrolls(true);
		panelEquipes.setToolTipText("Attribuer des dossards");
		panelEquipes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelEquipes.setBackground(Color.WHITE);
		panel.add(panelEquipes, BorderLayout.CENTER);
		panelEquipes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmCoUnss.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		/***** Choix de la catégorie active *****/
		
		JPanel panelCategorie = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCategorie.getLayout();
		flowLayout.setHgap(15);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelCategorie.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Catégories", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		
		comboBoxCategories = new JComboBox<String>();
		comboBoxCategories.setAlignmentX(Component.RIGHT_ALIGNMENT);
		comboBoxCategories.setPreferredSize(new Dimension(150, 20));
		comboBoxCategories.addItemListener( new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	categorieChangee();
		    }
		});
		
		for (Categorie cat : categories) {
			comboBoxCategories.addItem(cat.intitule());
		}
		panelCategorie.add(comboBoxCategories);
		panel_2.add(panelCategorie, BorderLayout.NORTH);
		panelEtapes.setBorder(new TitledBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), "Épreuve", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		for (Epreuve epv : epreuves) {
			comboBoxEtapes.addItem(epv.getNom());
		}
		GridBagLayout gbl_panelEtapes = new GridBagLayout();
		gbl_panelEtapes.columnWidths = new int[]{428, 0};
		gbl_panelEtapes.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelEtapes.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelEtapes.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelEtapes.setLayout(gbl_panelEtapes);
		
		comboBoxEtapes = new JComboBox<String>();
		comboBoxEtapes.setPreferredSize(new Dimension(150, 20));
		comboBoxEtapes.addItemListener( new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	panel_parcours.setVisible(false);
				panel_balises.setVisible(false);
		    	if (comboBoxEtapes.getSelectedIndex() != -1) {
		    		Epreuve epv = epreuveParNom(comboBoxEtapes.getSelectedItem().toString());
					if (epv.getType().equals(TypeEpreuve.valueOfByEpreuve("relais"))) {
						panel_parcours.setVisible(true);
					}
					panel_balises.setVisible(true);
		    	}
		    	
		    }
		});
		
		JPanel panel_epreuve = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_epreuve.getLayout();
		flowLayout_2.setHgap(10);
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		panel_epreuve.setPreferredSize(new Dimension(350, 40));
		panel_epreuve.setMinimumSize(new Dimension(350, 40));
		panel_epreuve.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagConstraints gbc_panel_epreuve = new GridBagConstraints();
		gbc_panel_epreuve.anchor = GridBagConstraints.WEST;
		gbc_panel_epreuve.insets = new Insets(0, 0, 5, 0);
		gbc_panel_epreuve.gridx = 0;
		gbc_panel_epreuve.gridy = 0;
		panelEtapes.add(panel_epreuve, gbc_panel_epreuve);
		panel_epreuve.add(comboBoxEtapes);
		
		JButton btnAjouteEpreuve = new JButton("");
		btnAjouteEpreuve.setToolTipText("Ajouter une épreuve");
		btnAjouteEpreuve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEpreuve();
			}
		});
		btnAjouteEpreuve.setPreferredSize(new Dimension(30, 30));
		btnAjouteEpreuve.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/plus.png")));
		panel_epreuve.add(btnAjouteEpreuve);
		
		JButton btnModifieepreuve = new JButton("");
		btnModifieepreuve.setToolTipText("Modifier l'épreuve sélectionnée");
		btnModifieepreuve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifieEpreuve();
			}
		});
		btnModifieepreuve.setPreferredSize(new Dimension(30, 30));
		btnModifieepreuve.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/find.png")));
		panel_epreuve.add(btnModifieepreuve);
		
		JButton btnSupprimeepreuve = new JButton("");
		btnSupprimeepreuve.setToolTipText("Supprimer l'épreuve sélectionnée");
		btnSupprimeepreuve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				supprimeEpreuve();
			}
		});
		btnSupprimeepreuve.setPreferredSize(new Dimension(30, 30));
		btnSupprimeepreuve.setMinimumSize(new Dimension(30, 30));
		btnSupprimeepreuve.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/delete.png")));
		panel_epreuve.add(btnSupprimeepreuve);
		panel_2.add(panelEtapes, BorderLayout.CENTER);
		
		//JPanel  = new JPanel();
		panel_parcours.setVisible(false);
		GridBagConstraints gbc_panel_parcours = new GridBagConstraints();
		gbc_panel_parcours.anchor = GridBagConstraints.WEST;
		gbc_panel_parcours.insets = new Insets(0, 0, 5, 0);
		gbc_panel_parcours.fill = GridBagConstraints.VERTICAL;
		gbc_panel_parcours.gridx = 0;
		gbc_panel_parcours.gridy = 1;
		panelEtapes.add(panel_parcours, gbc_panel_parcours);
		
		JLabel lblParcours = new JLabel("Parcours :");
		panel_parcours.add(lblParcours);
		
		//JComboBox<String> comboBox_parcours = new JComboBox<String>();
		comboBox_parcours.addItemListener( new ItemListener() {
		    public void itemStateChanged(ItemEvent e) {
		    	if (e.getStateChange() == ItemEvent.SELECTED) {
		    		System.out.println(e.getItem().toString());
		    		afficheBalises();
		    	}
		    }
		});
		comboBox_parcours.setMinimumSize(new Dimension(150, 20));
		comboBox_parcours.setPreferredSize(new Dimension(150, 20));
		panel_parcours.add(comboBox_parcours);
		
		JButton btnNouveauparcours = new JButton("");
		btnNouveauparcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajouteParcours();
			}
		});
		
		JButton btnImportparcours = new JButton("");
		btnImportparcours.setPreferredSize(new Dimension(33, 33));
		btnImportparcours.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/import.png")));
		btnImportparcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importeParcours();
			}
		});
		panel_parcours.add(btnImportparcours);
		btnNouveauparcours.setPreferredSize(new Dimension(30, 30));
		btnNouveauparcours.setMinimumSize(new Dimension(30, 30));
		btnNouveauparcours.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/plus.png")));
		panel_parcours.add(btnNouveauparcours);
		
		JButton btnModifieparcours = new JButton("");
		btnModifieparcours.setToolTipText("Modifier le relais");
		btnModifieparcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modifieRelais();
			}
		});
		btnModifieparcours.setPreferredSize(new Dimension(30, 30));
		btnModifieparcours.setMinimumSize(new Dimension(30, 30));
		btnModifieparcours.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/find.png")));
		panel_parcours.add(btnModifieparcours);
		
		JButton btnSupprimeparcours = new JButton("");
		btnSupprimeparcours.setPreferredSize(new Dimension(30, 30));
		btnSupprimeparcours.setMinimumSize(new Dimension(30, 30));
		btnSupprimeparcours.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/delete.png")));
		btnSupprimeparcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				supprimeParcours();
				afficheEpreuves();
			}
		});
		panel_parcours.add(btnSupprimeparcours);
		
		//JPanel panel_balises = new JPanel();
		panel_balises.setVisible(false);
		GridBagConstraints gbc_panel_balises = new GridBagConstraints();
		gbc_panel_balises.fill = GridBagConstraints.BOTH;
		gbc_panel_balises.gridx = 0;
		gbc_panel_balises.gridy = 2;
		panelEtapes.add(panel_balises, gbc_panel_balises);
		panel_balises.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_5 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panel_balises.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblBalises = new JLabel("Balises");
		panel_5.add(lblBalises);
		
		JButton btnAjouteepreuve = new JButton("");
		btnAjouteepreuve.setPreferredSize(new Dimension(30, 30));
		btnAjouteepreuve.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/plus.png")));
		panel_5.add(btnAjouteepreuve);
		
		
		JPanel panel_6 = new JPanel();
		panel_balises.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(40, 10));
		panel_7.setMinimumSize(new Dimension(40, 10));
		panel_6.add(panel_7, BorderLayout.WEST);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JButton btnMonterbalise = new JButton("");
		btnMonterbalise.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnMonterbalise.setMargin(new Insets(2, 24, 2, 24));
		btnMonterbalise.setMaximumSize(new Dimension(30, 30));
		btnMonterbalise.setMinimumSize(new Dimension(30, 30));
		btnMonterbalise.setPreferredSize(new Dimension(30, 30));
		btnMonterbalise.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/up.png")));
		panel_7.add(btnMonterbalise);
		
		JButton btnDescendrebalise = new JButton("");
		btnDescendrebalise.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDescendrebalise.setMargin(new Insets(2, 24, 2, 24));
		btnDescendrebalise.setMaximumSize(new Dimension(30, 30));
		btnDescendrebalise.setMinimumSize(new Dimension(30, 30));
		btnDescendrebalise.setPreferredSize(new Dimension(30, 30));
		btnDescendrebalise.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/down.png")));
		panel_7.add(btnDescendrebalise);

		//JPanel panel_afficheBalise = new JPanel();
		panel_afficheBalise.setAutoscrolls(true);
		panel_afficheBalise.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_afficheBalise.setBackground(Color.WHITE);
		panel_6.add(panel_afficheBalise, BorderLayout.CENTER);
		panel_afficheBalise.setLayout(new BoxLayout(panel_afficheBalise, BoxLayout.Y_AXIS));
		
		
		
	}
	
	/**
	 * Affiche ou cache les boutons d'état de la lecture des puces
	 * 
	 * @param pLit boolean bouton Lancer la lecture
	 * @param pConnexion boolean bouton Connexion en cours
	 * @param pArrete boolean bouton arrêter la lecture
	 */
	public void afficheBouton(boolean pLit, boolean pConnexion, boolean pArrete) {
		btnLanceLecture.setVisible(pLit);
		btnConnectEnCours.setVisible(pConnexion);
		btnArreteLecture.setVisible(pArrete);
		btnLanceAutoPuce.setEnabled(!(pArrete || pConnexion));
	}

	/**
	 * Démarre la lecture des puces
	 */
	protected void litPuces() {
		lecteurPuce = new LitBalisesPuces(this);
		
		
	}
	
	/**
	 * Arrête la lecture des puces
	 */
	protected void arreteLitPuces() {
		lecteurPuce.arreteLecture();
	}
	
	/**
	 * Affiche ou cache les boutons d'état de auto puce
	 * 
	 * @param pLit boolean bouton Lancer la lecture
	 * @param pConnexion boolean bouton Connexion en cours
	 * @param pArrete boolean bouton arrêter la lecture
	 */
	public void afficheBoutonAutoPuce(boolean pLit, boolean pConnexion, boolean pArrete) {
		btnLanceAutoPuce.setVisible(pLit);
		btnConnexionAutoPuce.setVisible(pConnexion);
		btnArreteAutoPuce.setVisible(pArrete);
		btnLanceLecture.setEnabled(!(pArrete || pConnexion));
	}

	/**
	 * Démarre la lecture de auto puce
	 */
	protected void litAutoPuces() {
		lecteurAutoPuce = new AutoPuce(this);
	}
	
	/**
	 * Arrête la lecture de auto puces
	 */
	protected void arreteAutoPuces() {
		lecteurAutoPuce.arreteLecture();
	}
	
	/**
	 * Affichage des actions en cours dans le panneau South
	 * 
	 * @param pLblPuce String Le message à afficher
	 */
	public void setLblPuce(String pLblPuce) {
		lblPuce.setText(pLblPuce);
	}
	
	public Vector<Equipe> getEquipe(){
		return equipes;
	}
	
	public String getLblEquipes() {
		nbEquipes = equipes.size();
		String retour = nbEquipes + " équipe";
		retour = nbEquipes > 1 ? retour + "s " : retour + " ";
		return retour;
	}
	
	public void ajouteUneEquipe(Equipe pNewEquipe) {
		equipes.add(pNewEquipe);
	}
	
	public Preferences getPreferences() {
		return preferences;
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
	 * Affiche la boite de dialogue de création d'une équipe
	 */
	protected void addEquipe() {
		try {
			new AjouteEquipe(this);			
			afficheEquipes();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void afficheEquipes() {
		panelEquipes.removeAll();
		panelEquipes.updateUI();
		if (equipes.size() > 0) {
			for(Equipe equipe : equipes) {
				afficheEquipe(equipe);
			}
		} else {
			panelEquipes.removeAll();
		}
	}
	
	protected void afficheEquipe(Equipe pEquipe) {
		if(!pEquipe.getCategorie().equals(this.comboBoxCategories.getSelectedItem().toString())) {
			return;
		}
		String contenuLbl = pEquipe.getCategorie() + " - " + pEquipe.getDossard() + " - " + pEquipe.getNomEquipe();
		for(Coureur coureur : pEquipe.getCoureurs()) {
			contenuLbl += " - " + coureur.getNom() + " " + coureur.getPrenom() + " " + coureur.getPuce();
		}
		JLabel lblNewEquipe = new JLabel(contenuLbl);
		lblNewEquipe.setOpaque(true);
		
		lblNewEquipe.setBackground(Color.WHITE);
		if (pEquipe.getAbsent()) {
			lblNewEquipe.setBackground(Color.YELLOW);
			contenuLbl = "AB - " + contenuLbl;
			lblNewEquipe.setText(contenuLbl);
		}
		if (pEquipe.getDsq()) {
			lblNewEquipe.setBackground(Color.ORANGE);
			contenuLbl = "DSQ - " + contenuLbl;
			lblNewEquipe.setText(contenuLbl);
		}
		if (pEquipe.getArrive()) {
			lblNewEquipe.setBackground(Color.GREEN);
		}
		if (pEquipe.getArrive() && pEquipe.getDsq()) {
			lblNewEquipe.setBackground(Color.BLUE);
		}
		
		lblNewEquipe.setToolTipText(contenuLbl);
		
		lblNewEquipe.setPreferredSize(new Dimension(400, 14));
		lblNewEquipe.setMaximumSize(new Dimension(400, 14));
		
		if (equipeChoisie !=null && pEquipe.getNomEquipe().equals(equipeChoisie)) {
			lblNewEquipe.setForeground(Color.RED);
		}
		
		lblNewEquipe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                equipeChoisie = pEquipe.getNomEquipe();
                afficheEquipes();
            }

        });
		
		panelEquipes.add(lblNewEquipe);
		panelEquipes.updateUI();
	}
	
	/**
	 * Supprime une équipe
	 */
	protected void effaceEquipe() {
		for(Equipe eqActive : equipes) {
			if (eqActive.getNomEquipe().equals(equipeChoisie)) {
				String message = "Êtes-vous sûr de vouloir supprimer " + eqActive.getNomEquipe() + ". \nCette action est irréversible.";
				int dialogResult = JOptionPane.showConfirmDialog (null, message,"ATTENTION",JOptionPane.YES_NO_OPTION);
				if(dialogResult == JOptionPane.YES_OPTION){
					equipes.removeElement(eqActive);
					equipeChoisie = null;
				}
                break;
			}
		}
		afficheEquipes();
	}
	
	/**
	 * Modifier les données d'une équipe
	 */
	protected void modifieEquipe() {

		try {
			for(Equipe modifieEquipe : equipes) {
				if (modifieEquipe.getNomEquipe().equals(equipeChoisie)) {
					new AjouteEquipe(this, equipes.indexOf(modifieEquipe));
					break;
				}
			}
			afficheEquipes();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Importe les équipes depuis un fichier CSV
	 */
	protected void importeEquipe() {
		new ImportCSVEquipe(equipes, preferences);
		afficheEquipes();
	}
	
	/**
	 * Actions à effectuer si la categorie est modifiée
	 */
	protected void categorieChangee() {
		afficheEquipes();
		afficheEpreuves();
	}

	/**
	 * Ajout d'une équipe
	 */
	protected void addEpreuve() {
		try {
			int nbEpreuve = epreuves.size();
			NouvelleEpreuve frame = new NouvelleEpreuve(epreuves, Categorie.valueOfByCategorie(comboBoxCategories.getSelectedItem().toString()));
			frame.setVisible(true);
			afficheEpreuves();
			if(epreuves.size() > nbEpreuve) {
				comboBoxEtapes.setSelectedItem(epreuves.lastElement().getNom());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void modifieEpreuve() {
		if(comboBoxEtapes.getItemCount() > 0) {
			String epreuveModifiee = comboBoxEtapes.getSelectedItem().toString();
			int idModifie = comboBoxEtapes.getSelectedIndex();
			ModifieEpreuve frame = new ModifieEpreuve(epreuves, Categorie.valueOfByCategorie(comboBoxCategories.getSelectedItem().toString()), epreuveModifiee);
			frame.setVisible(true);
			afficheEpreuves();
			comboBoxEtapes.setSelectedIndex(idModifie);
		}
		
	}
	
	/**
	 * Supprime une épreuve définitivement
	 */
	protected void supprimeEpreuve() {
		if(comboBoxEtapes.getSelectedItem() == null) return;
		String fichier = comboBoxEtapes.getSelectedItem().toString();
		int option = JOptionPane.showConfirmDialog(null, "Vous avez demandé la suppression de " + fichier + " êtes-vous sûr ?", "Supprimer l'épreuve " + fichier, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(option == JOptionPane.OK_OPTION) {
			for (Epreuve epv : epreuves) {
				if(epv.getNom().equals(comboBoxEtapes.getSelectedItem().toString())) {
					epreuves.remove(epv);
					break;
				}
				
			}
		}
		afficheEpreuves();
	}
	
	/**
	 * Affiche les épreuves dans le panneau
	 */
	protected void afficheEpreuves() {
		panel_parcours.setVisible(false);
		panel_balises.setVisible(false);
		comboBoxEtapes.removeAllItems();
		frmCoUnss.setTitle("C.O. UNSS : ");
		for (Epreuve epv : epreuves) {
			if(epv.getCategorie().intitule().equals(comboBoxCategories.getSelectedItem().toString())) {
				comboBoxEtapes.addItem(epv.getNom());
				if (epv.getType().equals(TypeEpreuve.valueOfByEpreuve("relais"))) {
					panel_parcours.setVisible(true);
					Vector<Parcours> parcours = epv.getParcours();
					comboBox_parcours.removeAllItems();
					for(Parcours parc : parcours) {
						comboBox_parcours.addItem(parc.getNom());
					}
				}
				panel_balises.setVisible(true);
			}
			if(epv.getActive()) {
				frmCoUnss.setTitle(frmCoUnss.getTitle() + epv.getCategorie() + " -> " + epv.getNom() + " - ");
				frmCoUnss.repaint();
			}

			//afficheBalises();
			
		}
	}

	protected void afficheBalises() {
		panel_afficheBalise.removeAll();
		panel_afficheBalise.updateUI();
		if (balisesSelectionnees() != null && balisesSelectionnees().size() > 0) {
			afficheBalises(balisesSelectionnees());
		}
		
	}

	protected void afficheBalises(Vector<Balise> pBalises) {
				
		for(Balise balise : pBalises) {
			System.out.println("balise - " + balise.getCode());
			
			String texteBalise = balise.getCode() + " : si trouvée → ";
			if (balise.getTrouveBonif()) {
				texteBalise += "Bonification ";
			}
			else {
				texteBalise += "Penalité ";
			}
			texteBalise += balise.getTrouvePoints() + " points et " + balise.getTrouveHeure();
			texteBalise += " - si poste manquant → ";
			if (balise.getPmBonif()) {
				texteBalise += "Bonification ";
			}
			else {
				texteBalise += "Penalité ";
			}
			texteBalise += balise.getPmPoints() + " points et " + balise.getPmHeure();
			
			JLabel lblNewBalise = new JLabel(texteBalise);
			lblNewBalise.setToolTipText(texteBalise);
			panel_afficheBalise.add(lblNewBalise);
		}

		panel_afficheBalise.updateUI();
	}
	
	protected void ouvrir() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(filter);
		chooser.addChoosableFileFilter(filter2);
		chooser.setCurrentDirectory(new File(preferences.getDossierTravail()));
		equipes = new Vector<Equipe>();
		epreuves = new Vector<Epreuve>();
		if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			preferences.setFichierTravail(chooser.getSelectedFile().getAbsolutePath());
            new ChargeFichier(preferences, equipes, epreuves, chooser.getSelectedFile().getAbsolutePath());
            afficheTout();
        }
	}
	
	/** 
	 * Enregistre le fichier sans demander son nom s'il est déjà connu
	 * 
	 */
	protected void enregistre() {
		if(preferences.getFichierTravail() == null || preferences.getFichierTravail().isEmpty()) {
			enregistreSous();
		} 
		else {
			enregistreFichier(preferences.getFichierTravail());
		}
	}
	
	/**
	 * Enregistre le fichier en imposant de choisir un nom
	 * 
	 */
	protected void enregistreSous() {
		
		JFileChooser filechoose = new JFileChooser();
		//filechoose.setCurrentDirectory(new File("."));  /* ouvrir la boite de dialogue dans répertoire courant */
		filechoose.setDialogTitle("Enregistrer la rencontre"); /* nom de la boite de dialogue */
		filechoose.setFileFilter(filter);
		filechoose.addChoosableFileFilter(filter2);
		filechoose.setCurrentDirectory(new File(preferences.getDossierTravail()));
		String approve = new String("Enregistrer"); /* Le bouton pour valider l’enregistrement portera la mention Enregistrer */
		int resultatEnregistrer = filechoose.showDialog(filechoose, approve); 
		if (resultatEnregistrer == JFileChooser.APPROVE_OPTION){ /* Si l’utilisateur clique sur le bouton Enregistrer */
			String fichier = filechoose.getSelectedFile().getAbsolutePath();
			if(new File(fichier).exists()) {
				int option;
				option = JOptionPane.showConfirmDialog(null, "Le fichier " + fichier + " existe déjà, voulez-vous le remplacer ?", "Enregistrer la rencontre", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(option == JOptionPane.OK_OPTION) {
					//adresseSauvegarde = fichier;
					preferences.setFichierTravail(fichier);
				    enregistreFichier(preferences.getFichierTravail());
				}
			} 
			else {
				//adresseSauvegarde = fichier;
				preferences.setFichierTravail(fichier);
				enregistreFichier(preferences.getFichierTravail());
			}
			
		}
	}
	
	/**
	 * Enregistre le fichier 
	 * 
	 * @param pAdresse String l'adresse où enregistrer le fichier
	 */
	protected void enregistreFichier(String pAdresse) {
		new Fichier(preferences, equipes, epreuves, tempsEnCourse, pAdresse );
	}
	
	/**
	 * Reprend tous les affichages
	 */
	protected void afficheTout() {
		afficheEpreuves();
		afficheEquipes();
	}

	/**
	 * Recherche une épreuve par son nom
	 * 
	 * @param pNom String le nom de l'épreuve recherchée
	 * @return Epreuve
	 */
	protected Epreuve epreuveParNom(String pNom) {
		Epreuve epvRetour = new Epreuve();
		for (Epreuve epv : epreuves) {
			if (epv.getNom().equals(pNom)) {
				return epv;
			}
		}
		return epvRetour;
	}
	
	/**
	 * Création d'un parcours de relais
	 */
	protected void ajouteParcours() {
		System.out.println("On ajoute un parcours");
		Relais nouveauRelais = new Relais(comboBoxCategories.getSelectedItem().toString() + " → " + comboBoxEtapes.getSelectedItem().toString(), Categorie.valueOfByCategorie(comboBoxCategories.getSelectedItem().toString()));
		nouveauRelais.setModal(true);
		String retour = nouveauRelais.getNom();
		if(retour != null) {
			System.out.println(retour);
			epreuveSelectionnee().addParcours(new Parcours(retour));
			afficheEpreuves();
			comboBox_parcours.setSelectedIndex(comboBox_parcours.getItemCount() - 1); 
		}
	}
	
	protected void importeParcours() {
		for (Epreuve epv : epreuves) {
			if (epv.getNom().toUpperCase().equals(comboBoxEtapes.getSelectedItem().toString().toUpperCase())) {
				new ImportCSVParcours(epv, preferences);
				break;
			}
		}
		afficheEpreuves();
	}
	
	protected void setBaliseDefaut() {
		CreeBalise dialog = new CreeBalise(preferences.getBaliseDefaut());
		dialog.setTitle("Balise par défaut");
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
	
	/**
	 * Modification d'un parcours
	 */
	protected void modifieRelais() {
		System.out.println("On modifie un parcours du relais");
		if(comboBox_parcours.getSelectedIndex() != -1) {
			new MonRelais(comboBoxEtapes.getSelectedItem().toString(), Categorie.valueOfByCategorie(comboBoxCategories.getSelectedItem().toString()));
			
		}
	}
	
	/**
	 * Supprime le parcours sélectionné
	 */
	protected void supprimeParcours() {
		if(comboBoxEtapes.getSelectedIndex() != -1) {
			for (Epreuve epv : epreuves) {
				if (epv.getNom().toUpperCase().equals(comboBoxEtapes.getSelectedItem().toString().toUpperCase())) {
					Parcours parcours = parcoursActif(epv);
					if(parcours != null) {
						int option = JOptionPane.showConfirmDialog(null, "Vous avez demandé la suppression du parcours " + parcours.getNom() + ", êtes-vous sûr ?", "Supprimer le parcours", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if(option == JOptionPane.OK_OPTION) {
							epv.getParcours().remove(parcours);
						}
					}
				}
			}
		}		
	}
	
	/**
	 * Renvoie le parcours sélectionné
	 * 
	 * @return Parcours
	 */
	protected Parcours parcoursActif(Epreuve epv) {
		Parcours retour = null; 
		//for (Epreuve epv : epreuves) {
			//if (epv.getNom().toUpperCase().equals(comboBoxEtapes.getSelectedItem().toString().toUpperCase())) {
		for(Parcours parc : epv.getParcours()) {
			if(parc.getNom().toUpperCase().equals(comboBox_parcours.getSelectedItem().toString().toUpperCase())) {
				retour = parc;
				break;
			}
		}
		return retour;
	}
	
	protected Vector<Balise> balisesSelectionnees() {
		Vector<Balise> retour = null;
		if (epreuveSelectionnee() != null && parcoursActif(epreuveSelectionnee()) != null) {
			Parcours parc = parcoursActif(epreuveSelectionnee());
			retour = parc.getBalises();
		}
		return retour;
	}
	
	protected Epreuve epreuveSelectionnee() {
		Epreuve retour = null;
		for(Epreuve epv : epreuves) {
			if(epv.getNom().toUpperCase().equals(comboBoxEtapes.getSelectedItem().toString().toUpperCase())) {
				retour = epv;
			}
		}
		return retour;
	}
	
	/**
	 * Renvoie la catégorie active
	 * 
	 * @return
	 */
	protected Categorie categorieSelectionne() {
		Categorie retour = Categorie.valueOfByCategorie(comboBoxCategories.getSelectedItem().toString());
		return retour;
	}
	
	public void addTempsCourse(SiDataFrame tempsCourseLues) {
		// temps départ et arrivée = -1 si pas de temps
		if (tempsCourseLues.getFinishTime() != -1) {
			TempsCourse nouvelleCourse= new TempsCourse(tempsCourseLues);
			nouvelleCourse.setJour(Integer.parseInt(spinner_jour.getValue().toString()));
			System.out.println("jour " + nouvelleCourse.getJour());
			if (tempsUnique(nouvelleCourse)) {
				tempsEnCourse.add(nouvelleCourse);
			}
			System.out.println(tempsEnCourse.size() + " temps enregistrés");
		}
	}
	
	public boolean tempsUnique(TempsCourse nouvelleCourse) {
		boolean retour = true;
		for (TempsCourse tec : tempsEnCourse) {
			if((tec.getPuce().equals(nouvelleCourse.getPuce())) && (tec.getJour() == nouvelleCourse.getJour()) && (tec.getArrive() == nouvelleCourse.getArrive())) {
				retour = false;
				break;
			}
		}
		return retour;
	}
	
	@SuppressWarnings("serial")
	protected class MonRelais extends Relais {
		
		MonRelais(String pNom, Categorie pCategorie){
			super(pNom, pCategorie);
		}
		
	}
	
	
}
