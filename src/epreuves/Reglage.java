package epreuves;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Reglage extends JPanel {

	
	protected JPanel panel_1 = new JPanel();
	private JPanel panel_HeureMax = new JPanel();
	private JPanel panel_tempsMax = new JPanel();
	protected JPanel panel_Specifique = new JPanel();
	
	protected JTextField txtNom;
	protected JTextArea txtrTousLesCoureurs = new JTextArea();
	
	private JComboBox<String> comboBoxDepart = new JComboBox<String>(new String[]{"Groupé","Au boitier"});
	private JLabel lblHeure = new JLabel("Heure de départ");
	private JSpinner spinnerHeureDepart = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
	private JLabel lblH = new JLabel("H");
	private JSpinner spinnerMnDepart = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
	private JLabel lblMn = new JLabel("Mn");
	
	private JComboBox<String> comboBox_Limite = new JComboBox<String>(new String[]{"Sans limite","Avec limite de temps","Avec limite horaire"});
	
	/**
	 * Create the panel.
	 */

	public Reglage() {	

	//public Prologue() {
		setLayout(new BorderLayout(0, 0));
		
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
//		JTextArea txtrTousLesCoureurs = new JTextArea();
		txtrTousLesCoureurs.setOpaque(false);
		txtrTousLesCoureurs.setFocusable(false);
		txtrTousLesCoureurs.setEditable(false);
		txtrTousLesCoureurs.setText("Toutes les balises, ensemble");
		txtrTousLesCoureurs.setToolTipText("Tous les coureurs, d'une équipe, doivent valider chaque balise, dans le même ordre");
		panel.add(txtrTousLesCoureurs);
		
		
		//panel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		add(panel_1, BorderLayout.WEST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{15, 0, 0, 0, 0, 0,0, 0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0,0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0,0.0,0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNom = new GridBagConstraints();
		gbc_lblNom.insets = new Insets(0, 0, 5, 5);
		gbc_lblNom.anchor = GridBagConstraints.EAST;
		gbc_lblNom.gridx = 1;
		gbc_lblNom.gridy = 0;
		panel_1.add(lblNom, gbc_lblNom);
		
		txtNom = new JTextField();
		GridBagConstraints gbc_txtNom = new GridBagConstraints();
		gbc_txtNom.gridwidth = 10;
		gbc_txtNom.insets = new Insets(0, 0, 5, 5);
		gbc_txtNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNom.gridx = 2;
		gbc_txtNom.gridy = 0;
		panel_1.add(txtNom, gbc_txtNom);
		txtNom.setColumns(10);
		
		/***** Heure de départ *****/
		
		JLabel lblTypeDeDpart = new JLabel("Type de départ :");
		GridBagConstraints gbc_lblTypeDeDpart = new GridBagConstraints();
		gbc_lblTypeDeDpart.insets = new Insets(0, 0, 5, 5);
		gbc_lblTypeDeDpart.anchor = GridBagConstraints.EAST;
		gbc_lblTypeDeDpart.gridx = 1;
		gbc_lblTypeDeDpart.gridy = 1;
		panel_1.add(lblTypeDeDpart, gbc_lblTypeDeDpart);

		comboBoxDepart.setMaximumSize(new Dimension(150, 32767));

		comboBoxDepart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	departChange(arg0);
		    }
		});
		GridBagConstraints gbc_comboBoxDepart = new GridBagConstraints();
		gbc_comboBoxDepart.gridwidth = 5;
		gbc_comboBoxDepart.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDepart.gridx = 2;
		gbc_comboBoxDepart.gridy = 1;
		panel_1.add(comboBoxDepart, gbc_comboBoxDepart);
		
		//JLabel lblHeure = new JLabel("Heure de départ");
		GridBagConstraints gbc_lblHeure = new GridBagConstraints();
		gbc_lblHeure.anchor = GridBagConstraints.EAST;
		gbc_lblHeure.insets = new Insets(0, 0, 5, 5);
		gbc_lblHeure.gridx = 1;
		gbc_lblHeure.gridy = 2;
		panel_1.add(lblHeure, gbc_lblHeure);
		
		//JSpinner spinnerHeureDepart = new JSpinner();
		spinnerHeureDepart.setMinimumSize(new Dimension(40, 20));
		spinnerHeureDepart.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinnerHeureDepart = new GridBagConstraints();
		gbc_spinnerHeureDepart.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerHeureDepart.gridx = 2;
		gbc_spinnerHeureDepart.gridy = 2;
		panel_1.add(spinnerHeureDepart, gbc_spinnerHeureDepart);
		
		//JLabel lblH = new JLabel("H");
		GridBagConstraints gbc_lblH = new GridBagConstraints();
		gbc_lblH.insets = new Insets(0, 0, 5, 5);
		gbc_lblH.gridx = 3;
		gbc_lblH.gridy = 2;
		panel_1.add(lblH, gbc_lblH);
		
		//JSpinner spinnerMnDepart = new JSpinner();
		spinnerMnDepart.setMinimumSize(new Dimension(40, 20));
		spinnerMnDepart.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinnerMnDepart = new GridBagConstraints();
		gbc_spinnerMnDepart.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerMnDepart.gridx = 4;
		gbc_spinnerMnDepart.gridy = 2;
		panel_1.add(spinnerMnDepart, gbc_spinnerMnDepart);
		
		//JLabel lblMn = new JLabel("Mn");
		GridBagConstraints gbc_lblMn = new GridBagConstraints();
		gbc_lblMn.insets = new Insets(0, 0, 5, 5);
		gbc_lblMn.gridx = 5;
		gbc_lblMn.gridy = 2;
		panel_1.add(lblMn, gbc_lblMn);
		
		/***** Limite horaire *****/		
		
		JLabel lblLimite = new JLabel("Limite :");
		GridBagConstraints gbc_lblLimite = new GridBagConstraints();
		gbc_lblLimite.anchor = GridBagConstraints.EAST;
		gbc_lblLimite.insets = new Insets(0, 0, 5, 5);
		gbc_lblLimite.gridx = 1;
		gbc_lblLimite.gridy = 3;
		panel_1.add(lblLimite, gbc_lblLimite);
		
		//JComboBox comboBox_Limite = new JComboBox(new Object[]{"Sans limite","Avec limite de temps","Avec limite horaire"});
		GridBagConstraints gbc_comboBox_Limite = new GridBagConstraints();

		comboBox_Limite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		    	limiteChange(arg0);
		    }
		});
		gbc_comboBox_Limite.gridwidth = 5;
		gbc_comboBox_Limite.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_Limite.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Limite.gridx = 2;
		gbc_comboBox_Limite.gridy = 3;
		panel_1.add(comboBox_Limite, gbc_comboBox_Limite);
		
		/***** Temps maximum *****/
		
		//JPanel panel_tempsMax = new JPanel();
		panel_tempsMax.setVisible(false);
		GridBagConstraints gbc_panel_tempsMax = new GridBagConstraints();
		gbc_panel_tempsMax.gridwidth = 11;
		gbc_panel_tempsMax.insets = new Insets(0, 0, 5, 5);
		gbc_panel_tempsMax.fill = GridBagConstraints.BOTH;
		gbc_panel_tempsMax.gridx = 1;
		gbc_panel_tempsMax.gridy = 4;
		panel_1.add(panel_tempsMax, gbc_panel_tempsMax);
		GridBagLayout gbl_panel_tempsMax = new GridBagLayout();
		gbl_panel_tempsMax.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_tempsMax.rowHeights = new int[]{0, 0, 0};
		gbl_panel_tempsMax.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_tempsMax.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_tempsMax.setLayout(gbl_panel_tempsMax);
		
		JLabel lblTempsLimite = new JLabel("Temps limite :");
		GridBagConstraints gbc_lblTempsLimite = new GridBagConstraints();
		gbc_lblTempsLimite.insets = new Insets(0, 0, 5, 5);
		gbc_lblTempsLimite.gridx = 0;
		gbc_lblTempsLimite.gridy = 0;
		panel_tempsMax.add(lblTempsLimite, gbc_lblTempsLimite);
		
		JSpinner spinner_HeureMax = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		GridBagConstraints gbc_spinner_HeureMax = new GridBagConstraints();
		gbc_spinner_HeureMax.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_HeureMax.gridx = 1;
		gbc_spinner_HeureMax.gridy = 0;
		panel_tempsMax.add(spinner_HeureMax, gbc_spinner_HeureMax);
		spinner_HeureMax.setPreferredSize(new Dimension(40, 20));
		spinner_HeureMax.setMinimumSize(new Dimension(40, 20));
		
		JLabel lblH_1 = new JLabel("H");
		GridBagConstraints gbc_lblH_1 = new GridBagConstraints();
		gbc_lblH_1.anchor = GridBagConstraints.WEST;
		gbc_lblH_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblH_1.gridx = 2;
		gbc_lblH_1.gridy = 0;
		panel_tempsMax.add(lblH_1, gbc_lblH_1);
		
		JSpinner spinner_MnMax = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		GridBagConstraints gbc_spinner_MnMax = new GridBagConstraints();
		gbc_spinner_MnMax.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_MnMax.gridx = 3;
		gbc_spinner_MnMax.gridy = 0;
		panel_tempsMax.add(spinner_MnMax, gbc_spinner_MnMax);
		spinner_MnMax.setPreferredSize(new Dimension(40, 20));
		spinner_MnMax.setMinimumSize(new Dimension(40, 20));
		
		JLabel lblMn_1 = new JLabel("Mn");
		GridBagConstraints gbc_lblMn_1 = new GridBagConstraints();
		gbc_lblMn_1.anchor = GridBagConstraints.WEST;
		gbc_lblMn_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblMn_1.gridx = 4;
		gbc_lblMn_1.gridy = 0;
		panel_tempsMax.add(lblMn_1, gbc_lblMn_1);
		
		JLabel lblMoins = new JLabel("Moins :");
		GridBagConstraints gbc_lblMoins = new GridBagConstraints();
		gbc_lblMoins.anchor = GridBagConstraints.EAST;
		gbc_lblMoins.insets = new Insets(0, 0, 0, 5);
		gbc_lblMoins.gridx = 0;
		gbc_lblMoins.gridy = 1;
		panel_tempsMax.add(lblMoins, gbc_lblMoins);
		
		JSpinner spinner_pointsMax = new JSpinner();
		spinner_pointsMax.setMinimumSize(new Dimension(40, 20));
		spinner_pointsMax.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_pointsMax = new GridBagConstraints();
		gbc_spinner_pointsMax.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_pointsMax.gridx = 1;
		gbc_spinner_pointsMax.gridy = 1;
		panel_tempsMax.add(spinner_pointsMax, gbc_spinner_pointsMax);
		
		JLabel lblPointsEtPlus = new JLabel("Points et plus");
		GridBagConstraints gbc_lblPointsEtPlus = new GridBagConstraints();
		gbc_lblPointsEtPlus.insets = new Insets(0, 0, 0, 5);
		gbc_lblPointsEtPlus.gridx = 2;
		gbc_lblPointsEtPlus.gridy = 1;
		panel_tempsMax.add(lblPointsEtPlus, gbc_lblPointsEtPlus);
		
		JSpinner spinner_MnPlusMax = new JSpinner();
		spinner_MnPlusMax.setPreferredSize(new Dimension(40, 20));
		spinner_MnPlusMax.setMinimumSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_MnPlusMax = new GridBagConstraints();
		gbc_spinner_MnPlusMax.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_MnPlusMax.gridx = 3;
		gbc_spinner_MnPlusMax.gridy = 1;
		panel_tempsMax.add(spinner_MnPlusMax, gbc_spinner_MnPlusMax);
		
		JLabel lblMn_2 = new JLabel("mn par ");
		GridBagConstraints gbc_lblMn_2 = new GridBagConstraints();
		gbc_lblMn_2.insets = new Insets(0, 0, 0, 5);
		gbc_lblMn_2.gridx = 4;
		gbc_lblMn_2.gridy = 1;
		panel_tempsMax.add(lblMn_2, gbc_lblMn_2);
		
		JSpinner spinner_MnParMax = new JSpinner();
		spinner_MnParMax.setMinimumSize(new Dimension(40, 20));
		spinner_MnParMax.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_MnParMax = new GridBagConstraints();
		gbc_spinner_MnParMax.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_MnParMax.gridx = 5;
		gbc_spinner_MnParMax.gridy = 1;
		panel_tempsMax.add(spinner_MnParMax, gbc_spinner_MnParMax);
		
		JLabel lblMn_3 = new JLabel("MN");
		GridBagConstraints gbc_lblMn_3 = new GridBagConstraints();
		gbc_lblMn_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblMn_3.gridx = 6;
		gbc_lblMn_3.gridy = 1;
		panel_tempsMax.add(lblMn_3, gbc_lblMn_3);
		
		/***** Heure Maximum *****/
		
		//JPanel panel_HeureMax = new JPanel();
		panel_HeureMax.setVisible(false);
		GridBagConstraints gbc_panel_HeureMax = new GridBagConstraints();
		gbc_panel_HeureMax.gridwidth = 12;
		gbc_panel_HeureMax.insets = new Insets(0, 0, 5, 0);
		gbc_panel_HeureMax.fill = GridBagConstraints.BOTH;
		gbc_panel_HeureMax.gridx = 1;
		gbc_panel_HeureMax.gridy = 5;
		panel_1.add(panel_HeureMax, gbc_panel_HeureMax);
		GridBagLayout gbl_panel_HeureMax = new GridBagLayout();
		gbl_panel_HeureMax.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_HeureMax.rowHeights = new int[]{0, 0, 0};
		gbl_panel_HeureMax.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_HeureMax.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_HeureMax.setLayout(gbl_panel_HeureMax);
		
		JLabel lblLimiteHoraire = new JLabel("Limite horaire");
		GridBagConstraints gbc_lblLimiteHoraire = new GridBagConstraints();
		gbc_lblLimiteHoraire.insets = new Insets(0, 0, 5, 5);
		gbc_lblLimiteHoraire.gridx = 0;
		gbc_lblLimiteHoraire.gridy = 0;
		panel_HeureMax.add(lblLimiteHoraire, gbc_lblLimiteHoraire);
		
		JSpinner spinner_HeureFin = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
		spinner_HeureFin.setMinimumSize(new Dimension(40, 20));
		spinner_HeureFin.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_HeureFin = new GridBagConstraints();
		gbc_spinner_HeureFin.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_HeureFin.gridx = 1;
		gbc_spinner_HeureFin.gridy = 0;
		panel_HeureMax.add(spinner_HeureFin, gbc_spinner_HeureFin);
		
		JLabel lblH_2 = new JLabel("H");
		GridBagConstraints gbc_lblH_2 = new GridBagConstraints();
		gbc_lblH_2.anchor = GridBagConstraints.WEST;
		gbc_lblH_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblH_2.gridx = 2;
		gbc_lblH_2.gridy = 0;
		panel_HeureMax.add(lblH_2, gbc_lblH_2);
		
		JSpinner spinner_MnFin = new JSpinner(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_MnFin.setMinimumSize(new Dimension(40, 20));
		spinner_MnFin.setPreferredSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_MnFin = new GridBagConstraints();
		gbc_spinner_MnFin.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_MnFin.gridx = 3;
		gbc_spinner_MnFin.gridy = 0;
		panel_HeureMax.add(spinner_MnFin, gbc_spinner_MnFin);
		
		JLabel lblMn_4 = new JLabel("Mn");
		GridBagConstraints gbc_lblMn_4 = new GridBagConstraints();
		gbc_lblMn_4.anchor = GridBagConstraints.WEST;
		gbc_lblMn_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblMn_4.gridx = 4;
		gbc_lblMn_4.gridy = 0;
		panel_HeureMax.add(lblMn_4, gbc_lblMn_4);
		
		JLabel lblMoins_1 = new JLabel("Moins :");
		GridBagConstraints gbc_lblMoins_1 = new GridBagConstraints();
		gbc_lblMoins_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblMoins_1.gridx = 0;
		gbc_lblMoins_1.gridy = 1;
		panel_HeureMax.add(lblMoins_1, gbc_lblMoins_1);
		
		JSpinner spinner_PointsFin = new JSpinner();
		spinner_PointsFin.setPreferredSize(new Dimension(40, 20));
		spinner_PointsFin.setMinimumSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_PointsFin = new GridBagConstraints();
		gbc_spinner_PointsFin.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_PointsFin.gridx = 1;
		gbc_spinner_PointsFin.gridy = 1;
		panel_HeureMax.add(spinner_PointsFin, gbc_spinner_PointsFin);
		
		JLabel lblH_3 = new JLabel("Points et plus");
		GridBagConstraints gbc_lblH_3 = new GridBagConstraints();
		gbc_lblH_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblH_3.gridx = 2;
		gbc_lblH_3.gridy = 1;
		panel_HeureMax.add(lblH_3, gbc_lblH_3);
		
		JSpinner spinner_MnPlusFin = new JSpinner();
		spinner_MnPlusFin.setPreferredSize(new Dimension(40, 20));
		spinner_MnPlusFin.setMinimumSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_MnPlusFin = new GridBagConstraints();
		gbc_spinner_MnPlusFin.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_MnPlusFin.gridx = 3;
		gbc_spinner_MnPlusFin.gridy = 1;
		panel_HeureMax.add(spinner_MnPlusFin, gbc_spinner_MnPlusFin);
		
		JLabel lblMnPar = new JLabel("mn par");
		GridBagConstraints gbc_lblMnPar = new GridBagConstraints();
		gbc_lblMnPar.insets = new Insets(0, 0, 0, 5);
		gbc_lblMnPar.gridx = 4;
		gbc_lblMnPar.gridy = 1;
		panel_HeureMax.add(lblMnPar, gbc_lblMnPar);
		
		JSpinner spinner_LnParFin = new JSpinner();
		spinner_LnParFin.setPreferredSize(new Dimension(40, 20));
		spinner_LnParFin.setMinimumSize(new Dimension(40, 20));
		GridBagConstraints gbc_spinner_LnParFin = new GridBagConstraints();
		gbc_spinner_LnParFin.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_LnParFin.gridx = 5;
		gbc_spinner_LnParFin.gridy = 1;
		panel_HeureMax.add(spinner_LnParFin, gbc_spinner_LnParFin);
		
		JLabel lblMn_5 = new JLabel("Mn");
		GridBagConstraints gbc_lblMn_5 = new GridBagConstraints();
		gbc_lblMn_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblMn_5.gridx = 6;
		gbc_lblMn_5.gridy = 1;
		panel_HeureMax.add(lblMn_5, gbc_lblMn_5);
		
		/***** Options *****/
		
		JCheckBox chckbxEnligne = new JCheckBox("");
		GridBagConstraints gbc_chckbxEnligne = new GridBagConstraints();
		gbc_chckbxEnligne.anchor = GridBagConstraints.EAST;
		gbc_chckbxEnligne.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxEnligne.gridx = 1;
		gbc_chckbxEnligne.gridy = 6;
		panel_1.add(chckbxEnligne, gbc_chckbxEnligne);
		
		JLabel lblNewLabel = new JLabel("Course à effectuer en ligne");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 10;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 6;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		
		JCheckBox chckbxAvantsuivante = new JCheckBox("");
		GridBagConstraints gbc_chckbxAvantsuivante = new GridBagConstraints();
		gbc_chckbxAvantsuivante.anchor = GridBagConstraints.EAST;
		gbc_chckbxAvantsuivante.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAvantsuivante.gridx = 1;
		gbc_chckbxAvantsuivante.gridy = 7;
		panel_1.add(chckbxAvantsuivante, gbc_chckbxAvantsuivante);
		
		JLabel lblPostesFaire = new JLabel("Postes à faire avant l'épreuve suivante");
		GridBagConstraints gbc_lblPostesFaire = new GridBagConstraints();
		gbc_lblPostesFaire.gridwidth = 10;
		gbc_lblPostesFaire.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostesFaire.anchor = GridBagConstraints.WEST;
		gbc_lblPostesFaire.gridx = 2;
		gbc_lblPostesFaire.gridy = 7;
		panel_1.add(lblPostesFaire, gbc_lblPostesFaire);
		
		JCheckBox chckbxApresprecedente = new JCheckBox("");
		GridBagConstraints gbc_chckbxApresprecedente = new GridBagConstraints();
		gbc_chckbxApresprecedente.anchor = GridBagConstraints.EAST;
		gbc_chckbxApresprecedente.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxApresprecedente.gridx = 1;
		gbc_chckbxApresprecedente.gridy = 8;
		panel_1.add(chckbxApresprecedente, gbc_chckbxApresprecedente);
		
		JLabel lblPostesFaire_1 = new JLabel("Postes à faire après l'épreuve précédente");
		GridBagConstraints gbc_lblPostesFaire_1 = new GridBagConstraints();
		gbc_lblPostesFaire_1.gridwidth = 10;
		gbc_lblPostesFaire_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostesFaire_1.anchor = GridBagConstraints.WEST;
		gbc_lblPostesFaire_1.gridx = 2;
		gbc_lblPostesFaire_1.gridy = 8;
		panel_1.add(lblPostesFaire_1, gbc_lblPostesFaire_1);
		
		JLabel lblCoefficient = new JLabel("Coefficient :");
		GridBagConstraints gbc_lblCoefficient = new GridBagConstraints();
		gbc_lblCoefficient.anchor = GridBagConstraints.EAST;
		gbc_lblCoefficient.insets = new Insets(0, 0, 0, 5);
		gbc_lblCoefficient.gridx = 1;
		gbc_lblCoefficient.gridy = 9;
		panel_1.add(lblCoefficient, gbc_lblCoefficient);
		
		JSpinner spinner_11 = new JSpinner();
		spinner_11.setPreferredSize(new Dimension(40, 20));
		spinner_11.setMinimumSize(new Dimension(40, 20));
		spinner_11.setValue(1);
		GridBagConstraints gbc_spinner_11 = new GridBagConstraints();
		gbc_spinner_11.insets = new Insets(0, 0, 0, 5);
		gbc_spinner_11.gridx = 2;
		gbc_spinner_11.gridy = 9;
		panel_1.add(spinner_11, gbc_spinner_11);
		
		/***** Panneau spécifique *****/

		//JPanel panel_Specifique = new JPanel();
		panel_Specifique.setVisible(false);
		GridBagConstraints gbc_panel_Specifique = new GridBagConstraints();
		gbc_panel_Specifique.gridwidth = 12;
		gbc_panel_Specifique.insets = new Insets(0, 0, 0, 0);
		gbc_panel_Specifique.fill = GridBagConstraints.EAST;
		gbc_panel_Specifique.gridx = 1;
		gbc_panel_Specifique.gridy = 10;
		panel_1.add(panel_Specifique, gbc_panel_Specifique);
		
		/***** Boutons *****/
		JPanel panel_Boutons = new JPanel();
		GridBagConstraints gbc_panel_Boutons = new GridBagConstraints();
		gbc_panel_Boutons.gridwidth = 13;
		gbc_panel_Boutons.insets = new Insets(0, 0, 0, 0);
		gbc_panel_Boutons.fill = GridBagConstraints.EAST;
		gbc_panel_Boutons.gridx = 1;
		gbc_panel_Boutons.gridy = 11;
		
		JButton okButton = new JButton("OK");
		okButton.setPreferredSize(new Dimension(91, 23));
		okButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	 System.out.println("Nouvelle épreuve -> OK cliqué ici");
	        	 //enregistreEquipe();
	        	 
	          }
	       });
		okButton.setActionCommand("OK");
		panel_Boutons.add(okButton);
		panel_1.add(panel_Boutons, gbc_panel_Boutons);
		

		
		JButton cancelButton = new JButton("Abandonner");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	        	 System.out.println("Nouvelle épreuve -> Echappe cliqué");
	        	 //this.dispose();
	          }
	       });
		panel_Boutons.add(cancelButton);
		
		

	}
	
	private void departChange(ActionEvent e) {
		System.out.println(e.toString());
		System.out.println(comboBoxDepart.getSelectedIndex());
		boolean visible = false;
		if (comboBoxDepart.getSelectedIndex() == 0) {
			visible = true;
		}
		lblHeure.setVisible(visible);
		spinnerHeureDepart.setVisible(visible);
		lblH.setVisible(visible);
		spinnerMnDepart.setVisible(visible);
		lblMn.setVisible(visible);
		panel_1.updateUI();
	}
	
	private void limiteChange(ActionEvent e) {
		System.out.println(comboBox_Limite.getSelectedIndex());
		switch(comboBox_Limite.getSelectedIndex()) {
		case 0 : System.out.println(comboBox_Limite.getItemAt(0).toString());
			panel_HeureMax.setVisible(false);
			panel_tempsMax.setVisible(false);
			break;
		case 1 : System.out.println(comboBox_Limite.getItemAt(1).toString());
			panel_HeureMax.setVisible(false);
			panel_tempsMax.setVisible(true);
		break;
		case 2 : System.out.println(comboBox_Limite.getItemAt(2).toString());
			panel_HeureMax.setVisible(true);
			panel_tempsMax.setVisible(false);
		break;
		default :System.out.println("Choix de limite : option inconnue");
			panel_HeureMax.setVisible(false);
			panel_tempsMax.setVisible(false);
		}
		
	}
	
	
}
