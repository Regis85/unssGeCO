package epreuves;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Categorie;
import modele.TypeDepart;
import modele.TypeEpreuve;
import modele.TypeLimite;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class NouvelleEpreuve extends JDialog {
	
	protected Vector<Epreuve> epreuves;

	protected Categorie categorie;

	protected final JPanel contentPanel = new JPanel();
	protected JPanel panel_texte = new JPanel();
	protected JPanel panel_depart2 = new JPanel();
	protected JPanel panel_heureDepart = new JPanel();
	protected JPanel panel_HoraireMax = new JPanel();
	
	protected JTextField txtNomEpreuve;
	
	protected JLabel lblTexteIntroduction = new JLabel("texte");
	protected JLabel lblLimiteHoraire = new JLabel("Limite horaire : ");
	
	protected JComboBox<Object> cbx_Type = new JComboBox<Object>(new Object[]{TypeEpreuve.groupe,TypeEpreuve.relais,TypeEpreuve.reseau});
	protected JComboBox<Object> cbx_depart = new JComboBox<Object>(new Object[]{TypeDepart.Groupé.intitule(),TypeDepart.Boitier.intitule()});
	protected JComboBox<Object> cbx_limite = new JComboBox<Object>(new Object[]{TypeLimite.sans.intitule(),TypeLimite.temps.intitule(),TypeLimite.heure.intitule()});
	
	protected JSpinner spinner_hDepart = new JSpinner();
	protected JSpinner spinner_mnDepart = new JSpinner();
	protected JSpinner spinner_depart2H = new JSpinner();
	protected JSpinner spinner_depart2Mn = new JSpinner();
	protected JSpinner spinner_heureMaxH = new JSpinner();
	protected JSpinner spinner_heureMaxPoint = new JSpinner();
	protected JSpinner spinner_heureMaxMnPlus = new JSpinner();
	protected JSpinner spinner_heureMaxMnPar = new JSpinner();
	protected JSpinner spinner_heureMaxMn = new JSpinner();
	protected JSpinner spinner_coef = new JSpinner();
	protected JSpinner spinner_jour = new JSpinner();
	
	protected JCheckBox chckbxEnLigne = new JCheckBox("Course  à effectuer en ligne");
	protected JCheckBox chckbxPrioritaire = new JCheckBox("Départage");
	protected JCheckBox chckbxActive = new JCheckBox("Active");

	//int indiceType = 0;

	/**
	 * Launch the application.
	 */
	/* *
	public static void main(String[] args) {
		try {
			NouvelleEpreuve dialog = new NouvelleEpreuve(new Vector<Epreuve>(), Categorie.College);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 /* */
	
	/**
	 * Create the dialog.
	 */
	public NouvelleEpreuve(Vector<Epreuve> pEpreuves, Categorie pCategorie) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		categorie = pCategorie;
		epreuves = pEpreuves;
		setModal(true);
		setTitle("Nouvelle épreuve");
		setBounds(100, 100, 508, 582);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel_Type = new JPanel();
			contentPanel.add(panel_Type, BorderLayout.NORTH);
			panel_Type.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblType = new JLabel("Type :");
				panel_Type.add(lblType);
			}
			{
				cbx_Type.setMinimumSize(new Dimension(150, 20));
				cbx_Type.setPreferredSize(new Dimension(150, 20));

				cbx_Type.addActionListener(new ItemAction());
				//indiceType = cbx_Type.getSelectedIndex();
				panel_Type.add(cbx_Type);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setToolTipText("Epreuve active, la lecture de puces lui est affectée");
			contentPanel.add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 76, 72};
			gbl_panel.columnWeights = new double[]{1.0, 1.0, 1.0};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				panel_texte = new JPanel();
				GridBagConstraints gbc_panel_texte = new GridBagConstraints();
				gbc_panel_texte.gridwidth = 3;
				gbc_panel_texte.insets = new Insets(0, 0, 5, 0);
				gbc_panel_texte.fill = GridBagConstraints.BOTH;
				gbc_panel_texte.gridx = 0;
				gbc_panel_texte.gridy = 0;
				panel.add(panel_texte, gbc_panel_texte);
			}
			{
				JLabel lblNom = new JLabel("Nom :");
				lblNom.setAlignmentX(Component.RIGHT_ALIGNMENT);
				GridBagConstraints gbc_lblNom = new GridBagConstraints();
				gbc_lblNom.anchor = GridBagConstraints.EAST;
				gbc_lblNom.insets = new Insets(0, 0, 5, 5);
				gbc_lblNom.gridx = 0;
				gbc_lblNom.gridy = 1;
				panel.add(lblNom, gbc_lblNom);
			}
			{
				txtNomEpreuve = new JTextField();
				GridBagConstraints gbc_txtNomEpreuve = new GridBagConstraints();
				gbc_txtNomEpreuve.insets = new Insets(0, 0, 5, 5);
				gbc_txtNomEpreuve.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtNomEpreuve.gridx = 1;
				gbc_txtNomEpreuve.gridy = 1;
				panel.add(txtNomEpreuve, gbc_txtNomEpreuve);
				txtNomEpreuve.setColumns(10);
			}
			
			/***** Départ *****/
			{
				//JCheckBox chckbxActive = new JCheckBox("Active");
				chckbxActive.setToolTipText("Epreuve active, les données de puces y seront affectés");
				GridBagConstraints gbc_chckbxActive = new GridBagConstraints();
				gbc_chckbxActive.insets = new Insets(0, 0, 5, 5);
				gbc_chckbxActive.gridx = 1;
				gbc_chckbxActive.gridy = 2;
				panel.add(chckbxActive, gbc_chckbxActive);
			}
			
			{
				JLabel lblTypeDeDpart = new JLabel("Type de départ :");
				GridBagConstraints gbc_lblTypeDeDpart = new GridBagConstraints();
				gbc_lblTypeDeDpart.anchor = GridBagConstraints.EAST;
				gbc_lblTypeDeDpart.insets = new Insets(0, 0, 5, 5);
				gbc_lblTypeDeDpart.gridx = 0;
				gbc_lblTypeDeDpart.gridy = 3;
				panel.add(lblTypeDeDpart, gbc_lblTypeDeDpart);
			}
			{
				cbx_depart.setPreferredSize(new Dimension(150, 20));
				cbx_depart.setMinimumSize(new Dimension(150, 20));

				cbx_depart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
				    	departChange(arg0);
				    }
				});
				GridBagConstraints gbc_cbx_depart = new GridBagConstraints();
				gbc_cbx_depart.insets = new Insets(0, 0, 5, 5);
				gbc_cbx_depart.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbx_depart.gridx = 1;
				gbc_cbx_depart.gridy = 3;
				panel.add(cbx_depart, gbc_cbx_depart);
			}
			{
				
				GridBagConstraints gbc_panel_heureDepart = new GridBagConstraints();
				gbc_panel_heureDepart.insets = new Insets(0, 0, 5, 0);
				gbc_panel_heureDepart.gridwidth = 3;
				gbc_panel_heureDepart.fill = GridBagConstraints.BOTH;
				gbc_panel_heureDepart.gridx = 0;
				gbc_panel_heureDepart.gridy = 4;
				panel_heureDepart.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel.add(panel_heureDepart, gbc_panel_heureDepart);
				panel_heureDepart.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel lblHeureDepart = new JLabel("Heure de départ :");
					panel_heureDepart.add(lblHeureDepart);
				}
				{
					spinner_hDepart.setModel(new SpinnerNumberModel(0, null, 23, 1));
					//JSpinner spinner_hDepart = new JSpinner();
					spinner_hDepart.setMinimumSize(new Dimension(40, 20));
					spinner_hDepart.setPreferredSize(new Dimension(40, 20));
					panel_heureDepart.add(spinner_hDepart);
				}
				{
					JLabel lblH = new JLabel("H");
					panel_heureDepart.add(lblH);
				}
				{
					spinner_mnDepart.setModel(new SpinnerNumberModel(0, null, 59, 1));
					//JSpinner spinner_mnDepart = new JSpinner();
					spinner_mnDepart.setPreferredSize(new Dimension(40, 20));
					spinner_mnDepart.setMinimumSize(new Dimension(40, 20));
					panel_heureDepart.add(spinner_mnDepart);
				}
				{
					JLabel lblMn = new JLabel("MN");
					panel_heureDepart.add(lblMn);
				}
			}
			
			/***** Deuxième départ *****/
			{
				GridBagConstraints gbc_panel_depart2 = new GridBagConstraints();
				gbc_panel_depart2.gridwidth = 3;
				gbc_panel_depart2.insets = new Insets(0, 0, 5, 0);
				gbc_panel_depart2.fill = GridBagConstraints.BOTH;
				gbc_panel_depart2.gridx = 0;
				gbc_panel_depart2.gridy = 5;
				panel_depart2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_depart2.setVisible(false);
				panel.add(panel_depart2, gbc_panel_depart2);
				panel_depart2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel lblDeuximeDpart = new JLabel("Deuxième départ à");
					panel_depart2.add(lblDeuximeDpart);
				}
				{
					//JSpinner spinner_depart2H = new JSpinner();
					spinner_depart2H.setPreferredSize(new Dimension(40, 20));
					spinner_depart2H.setMinimumSize(new Dimension(40, 20));
					panel_depart2.add(spinner_depart2H);
				}
				{
					JLabel lblH_3 = new JLabel("H");
					panel_depart2.add(lblH_3);
				}
				{
					//JSpinner spinner_depart2Mn = new JSpinner();
					spinner_depart2Mn.setPreferredSize(new Dimension(40, 20));
					spinner_depart2Mn.setMinimumSize(new Dimension(40, 20));
					panel_depart2.add(spinner_depart2Mn);
				}
				{
					JLabel lblMn_5 = new JLabel("MN");
					panel_depart2.add(lblMn_5);
				}
			}
			

			{
				JPanel panel_options = new JPanel();
				panel_options.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				GridBagConstraints gbc_panel_options = new GridBagConstraints();
				gbc_panel_options.gridwidth = 3;
				gbc_panel_options.insets = new Insets(0, 0, 5, 0);
				gbc_panel_options.fill = GridBagConstraints.BOTH;
				gbc_panel_options.gridx = 0;
				gbc_panel_options.gridy = 6;
				panel.add(panel_options, gbc_panel_options);
				panel_options.setLayout(new BoxLayout(panel_options, BoxLayout.Y_AXIS));
				{
					//JCheckBox chckbxEnLigne = new JCheckBox("Course  à effectuer en ligne");
					panel_options.add(chckbxEnLigne);
				}
				{
					JPanel panel_1 = new JPanel();
					panel_options.add(panel_1);
					{
						JLabel lblJour = new JLabel("jour :");
						panel_1.add(lblJour);
					}
					{
						//JSpinner spinner_jour = new JSpinner();
						spinner_jour.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
						spinner_jour.setPreferredSize(new Dimension(40, 20));
						spinner_jour.setMinimumSize(new Dimension(40, 20));
						panel_1.add(spinner_jour);
					}
				}
				{
					JPanel panel_coef = new JPanel();
					panel_options.add(panel_coef);
					{
						JLabel lblCoefficient = new JLabel("Coefficient  :");
						panel_coef.add(lblCoefficient);
					}
					{
						spinner_coef.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
						//JSpinner spinner_coef = new JSpinner();
						spinner_coef.setToolTipText("Coefficient de l'épreuve");
						spinner_coef.setPreferredSize(new Dimension(40, 20));
						spinner_coef.setMinimumSize(new Dimension(40, 20));
						panel_coef.add(spinner_coef);
					}
					{
						//JCheckBox chckbxPrioritaire = new JCheckBox("Départage");
						chckbxPrioritaire.setToolTipText("Départage sur cette épreuve en cas d'égalité");
						panel_coef.add(chckbxPrioritaire);
					}
				}
			}
			
			/***** limites horaires *****/
			
			{
				JLabel lblLimite = new JLabel("Limite :");
				GridBagConstraints gbc_lblLimite = new GridBagConstraints();
				gbc_lblLimite.anchor = GridBagConstraints.EAST;
				gbc_lblLimite.insets = new Insets(0, 0, 5, 5);
				gbc_lblLimite.gridx = 0;
				gbc_lblLimite.gridy = 7;
				panel.add(lblLimite, gbc_lblLimite);
			}
			{

				cbx_limite.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
				    	limiteChange(arg0);
				    }
				});
				
				GridBagConstraints gbc_cbx_limite = new GridBagConstraints();
				gbc_cbx_limite.insets = new Insets(0, 0, 5, 5);
				gbc_cbx_limite.fill = GridBagConstraints.HORIZONTAL;
				gbc_cbx_limite.gridx = 1;
				gbc_cbx_limite.gridy = 7;
				panel.add(cbx_limite, gbc_cbx_limite);
			}
			{

				GridBagConstraints gbc_panel_HoraireMax = new GridBagConstraints();
				gbc_panel_HoraireMax.insets = new Insets(0, 0, 5, 0);
				gbc_panel_HoraireMax.gridwidth = 3;
				gbc_panel_HoraireMax.fill = GridBagConstraints.BOTH;
				gbc_panel_HoraireMax.gridx = 0;
				gbc_panel_HoraireMax.gridy = 8;
				panel_HoraireMax.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				panel_HoraireMax.setVisible(false);
				panel.add(panel_HoraireMax, gbc_panel_HoraireMax);
				panel_HoraireMax.setLayout(new BorderLayout(0, 0));
				
				
			}
			{
				JPanel panel_hm1 = new JPanel();
				panel_HoraireMax.add(panel_hm1, BorderLayout.NORTH);
				

				{
					//JLabel lblLimiteHoraire = new JLabel("Limite horaire : ");
					panel_hm1.add(lblLimiteHoraire);
				}
				{
					spinner_heureMaxH.setModel(new SpinnerNumberModel(0, null, 23, 1));
					//JSpinner spinner_heureMaxH = new JSpinner();
					spinner_heureMaxH.setPreferredSize(new Dimension(40, 20));
					spinner_heureMaxH.setMinimumSize(new Dimension(40, 20));
					panel_hm1.add(spinner_heureMaxH);
				}
				{
					JLabel lblH_1 = new JLabel("H");
					panel_hm1.add(lblH_1);
				}
					{
						spinner_heureMaxMn.setModel(new SpinnerNumberModel(0, null, 59, 1));
						//JSpinner spinner_heureMaxMn = new JSpinner();
						spinner_heureMaxMn.setPreferredSize(new Dimension(40, 20));
						spinner_heureMaxMn.setMinimumSize(new Dimension(40, 20));
						panel_hm1.add(spinner_heureMaxMn);
					}
					{
						JLabel lblMn_1 = new JLabel("MN");
						panel_hm1.add(lblMn_1);
					}
			}
			{
				JPanel panel_hm2 = new JPanel();
				panel_hm2.setAlignmentY(Component.TOP_ALIGNMENT);
				panel_HoraireMax.add(panel_hm2, BorderLayout.CENTER);
				panel_hm2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				{
					JLabel lblMoins_1 = new JLabel("Moins");
					panel_hm2.add(lblMoins_1);
				}
				{
//				JSpinner spinner_heureMaxPoint = new JSpinner();
					spinner_heureMaxPoint.setPreferredSize(new Dimension(40, 20));
					spinner_heureMaxPoint.setMinimumSize(new Dimension(40, 20));
					panel_hm2.add(spinner_heureMaxPoint);
				}
				{
					JLabel lblPointsEtPlus = new JLabel("points et plus");
					panel_hm2.add(lblPointsEtPlus);
				}
				{
					//JSpinner spinner_heureMaxMnPlus = new JSpinner();
					spinner_heureMaxMnPlus.setPreferredSize(new Dimension(40, 20));
					spinner_heureMaxMnPlus.setMinimumSize(new Dimension(40, 20));
					panel_hm2.add(spinner_heureMaxMnPlus);
				}
				{
					JLabel lblMnPar_1 = new JLabel("mn par");
					panel_hm2.add(lblMnPar_1);
				}
				{
					//JSpinner spinner_heureMaxMnPar = new JSpinner();
					spinner_heureMaxMnPar.setMinimumSize(new Dimension(40, 20));
					spinner_heureMaxMnPar.setPreferredSize(new Dimension(40, 20));
					panel_hm2.add(spinner_heureMaxMnPar);
				}
				{
					JLabel lblMn_4 = new JLabel("mn de dépassement");
					panel_hm2.add(lblMn_4);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				okButton.setPreferredSize(new Dimension(48, 48));
				okButton.setIcon(new ImageIcon(NouvelleEpreuve.class.getResource("/icones/ok64.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						if (txtNomEpreuve.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Une épreuve doit avoir un nom", "Attention", JOptionPane.ERROR_MESSAGE);
						}
						else {
							enregistreEpreuve();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("");
				cancelButton.setPreferredSize(new Dimension(48, 48));
				cancelButton.setIcon(new ImageIcon(NouvelleEpreuve.class.getResource("/icones/annule64.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		affichePrologue();
		departChange(null);
		limiteChange(null);
	}
	
	protected void affichePrologue() {
		panel_texte.add(lblTexteIntroduction);
		lblTexteIntroduction.setText("Toutes les balises, ensemble");
		lblTexteIntroduction.setToolTipText("Tous les coureurs, d'une équipe, doivent valider chaque balise, dans le même ordre");
	}
	
	protected void afficheRelais() {
		lblTexteIntroduction.setText("Chacun son tour");
		lblTexteIntroduction.setToolTipText("Chaque coureur à un circuit à effectuer, on peut définir une 2ème heure de départ");
		panel_depart2.setVisible(true);
	}
	
	protected void afficheReseau() {
		lblTexteIntroduction.setText("Par tous ou tout seul");
		lblTexteIntroduction.setToolTipText("Certaines balises validées par tous, d'autres ne doivent être validées que par un coureur");
	}
	
	protected void departChange(ActionEvent e) {
		boolean visible = false;
		if (cbx_depart.getSelectedIndex() == 0) {
			visible = true;
		}
		panel_heureDepart.setVisible(visible);
		
	}
	
	protected void limiteChange(ActionEvent e) {
		switch(cbx_limite.getSelectedIndex()) {
			case 0 : 
				panel_HoraireMax.setVisible(false);
				//panel_dureeMax.setVisible(false);
				break;
			case 1 :
				panel_HoraireMax.setVisible(true);
				lblLimiteHoraire.setText("durée maximale");
				//panel_dureeMax.setVisible(true);
			break;
			case 2 : 
				panel_HoraireMax.setVisible(true);
				lblLimiteHoraire.setText("Limite horaire");
				//panel_dureeMax.setVisible(false);
			break;
			default : 
				panel_HoraireMax.setVisible(false);
				//panel_dureeMax.setVisible(false);
		}
		
	}
	
	/**
	 * ajoute l'épreuve au tableau
	 */
	protected void enregistreEpreuve() {

		if(nomUnique(txtNomEpreuve.getText())) {
			Epreuve epreuve = new Epreuve(calculId(), calculNumOrdre(), txtNomEpreuve.getText(), categorie);
			epreuves.add(epreuve);
			enregistre(epreuve);
		} else {
			JOptionPane.showMessageDialog(null, "Une épreuve doit avoir un nom unique, " + txtNomEpreuve.getText() + " est déjà utilisé", "Attention", JOptionPane.ERROR_MESSAGE);
		}
		
	}
		
	/**
	 * passe les données à l'épreuve
	 */
	protected void enregistre(Epreuve epreuve) {
		
		epreuve.setType(cbx_Type.getSelectedItem().toString());
		epreuve.setTypeDepart(cbx_depart.getSelectedItem().toString());
		epreuve.setHeureDepart(heure(Integer.parseInt(spinner_hDepart.getValue().toString()), Integer.parseInt(spinner_mnDepart.getValue().toString())));
		epreuve.setHeureDepart2(heure(Integer.parseInt(spinner_depart2H.getValue().toString()), Integer.parseInt(spinner_depart2Mn.getValue().toString())));
		epreuve.setTypeLimite(cbx_limite.getSelectedItem().toString());
		/*
		epreuve.setDureeMaxi(heure(Integer.parseInt(spinner_dureeMaxH.getValue().toString()),Integer.parseInt(spinner_dureeMaxMn.getValue().toString())));
		epreuve.setPointsDureeMaxi(Integer.parseInt(spinner_dureeMaxPoints.getValue().toString()));
		epreuve.setPenaliteDureeMaxi(heure(0, Integer.parseInt(spinner_dureeMaxPlusMn.getValue().toString())));
		epreuve.setParDureeMaxi(heure(0, Integer.parseInt(spinner_dureeMaxPar.getValue().toString())));
		*/
		epreuve.setHeureMaxi(heure(Integer.parseInt(spinner_heureMaxH.getValue().toString()),Integer.parseInt(spinner_heureMaxMn.getValue().toString())));
		epreuve.setPointsHeureMaxi(Integer.parseInt(spinner_heureMaxPoint.getValue().toString()));
		epreuve.setPenaliteHeureMaxi(heure(0, Integer.parseInt(spinner_heureMaxMnPlus.getValue().toString())));
		epreuve.setParHeureMaxi(heure(0, Integer.parseInt(spinner_heureMaxMnPar.getValue().toString())));
		
		epreuve.setEnLigne(chckbxEnLigne.isSelected());
		
		epreuve.setCoef(Integer.parseInt(spinner_coef.getValue().toString()));
		epreuve.setDepartage(chckbxPrioritaire.isSelected()); 
		
		epreuve.setJour(Integer.parseInt(spinner_jour.getValue().toString()));
		
		epreuve.setActive(chckbxActive.isSelected());
		if (chckbxActive.isSelected()) {
			// TODO : il faut désactiver les autres de la même catégorie
			System.out.println(txtNomEpreuve.getText()+ " active, il faut désactiver les autres de la même catégorie");
		}

		dispose();
	}
	
	/**
	 * Renvoie le prochain id
	 * 
	 * @return
	 */
	protected int calculId() {
		int id = 0;
		for(Epreuve epv : epreuves) {
			id = Math.max(id, epv.getId());
		}
		id++;
		return id;
	}
	
	/**
	 * Renvoie le numéro d'ordre suivant
	 * 
	 * @return
	 */
	protected int calculNumOrdre() {
		int id = 0;
		for(Epreuve epv : epreuves) {
			if(epv.getCategorie().equals(categorie)) {
				id = Math.max(id, epv.getNumOrdre());
			}
		}
		id++;
		return id;
	}
	
	/**
	 * Retourne l'heure construite
	 * 
	 * @param pHeure
	 * @param pMinutes
	 * @return LocalTime
	 */
	protected LocalTime heure(int pHeures, int pMinutes) {
		LocalTime time = heure(pHeures,pMinutes, 0);
		return time;
	}
	
	/**
	 * Retourne l'heure construite
	 * 
	 * @param pHeure
	 * @param pMn
	 * @param pSeconde
	 * @return LocalTime 
	 */
	protected LocalTime heure(int pHeures, int pMinutes, int pSecondes) {		
		LocalTime time = LocalTime.of(pHeures, pMinutes,pSecondes);
		return time;
	}
	
	protected boolean nomUnique(String nomCherche) {
		boolean retour = true;
		for(Epreuve epv : epreuves) {
			if(nomCherche.equals(epv.getNom())) {
				retour = false;
				break;
			}
		}
		return retour;
	}
	
	class ItemAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			panel_depart2.setVisible(false);
			switch (cbx_Type.getSelectedItem().toString()) {
				case "prologue" : affichePrologue();
				break;
				case "relais" : afficheRelais();
				break;
				case "réseau" : afficheReseau();
				break;
				default : System.out.println("Choix de type : option inconnue");
			}
		}	
	}
	
}
