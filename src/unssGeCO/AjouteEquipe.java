package unssGeCO;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.FlowLayout;

public class AjouteEquipe extends JDialog {
	
	protected Fenetre maFenetre;

	protected JPanel contentPane;
	protected JLabel lblDossard;
	protected JTextField txtDossard;
	protected JTextField txtNom;
	protected JCheckBox chckbxNonClasse;
	protected JCheckBox chckbxAbsentOuAbandon;
	protected JLabel lblCatgorie;
	protected JComboBox<String> cbxCategorie;
	
	protected Vector<String> Categories = new Vector<String>();
	protected JPanel panCoureur;
	protected JPanel panCoureurs;
	protected JTable tableCoureurs;
	protected final JButton btnEnregistre = new JButton("");
	protected JButton btnQuitter;
	protected JTextField txtEtablissement;
	protected JTextField txtVille;
	
	protected Equipe equipe;
	protected JCheckBox chckbxDisqualifie;
	protected JPanel panel;
	protected JLabel lblAutoPuce;
	protected JButton btnLanceAutopuce;
	protected JButton btnConnexionStation;
	protected JButton btnArreteAutopuce;
	
	protected LecteurPuces lecteurAutoPuce;

	protected boolean modifie = false;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouteEquipe frame = new AjouteEquipe();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	
	public AjouteEquipe(Fenetre pFenetre) {
		maFenetre = pFenetre;
		equipe = new Equipe();
		initialiseEquipe();
	}

	public AjouteEquipe(Fenetre pFenetre, int pIndex) {
		maFenetre = pFenetre;
		equipe = maFenetre.getEquipe().get(pIndex);
		modifie = true;
		initialiseEquipe();
	}

	/**
	 * Create the frame.
	 */
	protected void initialiseEquipe() {
		Categories.addElement("Collège");
		Categories.addElement("Lycée");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 600);
		setTitle("Équipe");
		contentPane = new JPanel();
		contentPane.setToolTipText("Valider les modifications");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{86, 0, 0};
		gbl_contentPane.rowHeights = new int[]{14,14,14, 14, 30, 0, 210, 0,20,0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblEtablissement = new JLabel("Établissement : ");
		GridBagConstraints gbc_lblEtablissement = new GridBagConstraints();
		gbc_lblEtablissement.anchor = GridBagConstraints.EAST;
		gbc_lblEtablissement.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtablissement.gridx = 0;
		gbc_lblEtablissement.gridy = 0;
		contentPane.add(lblEtablissement, gbc_lblEtablissement);
		
		txtEtablissement = new JTextField();
		txtEtablissement.setText(equipe.getEtablissement());
		GridBagConstraints gbc_txtEtablissement = new GridBagConstraints();
		gbc_txtEtablissement.insets = new Insets(0, 0, 5, 5);
		gbc_txtEtablissement.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEtablissement.gridx = 1;
		gbc_txtEtablissement.gridy = 0;
		contentPane.add(txtEtablissement, gbc_txtEtablissement);
		txtEtablissement.setColumns(10);
		
		JLabel lblVille = new JLabel("Ville : ");
		GridBagConstraints gbc_lblVille = new GridBagConstraints();
		gbc_lblVille.anchor = GridBagConstraints.EAST;
		gbc_lblVille.insets = new Insets(0, 0, 5, 5);
		gbc_lblVille.gridx = 0;
		gbc_lblVille.gridy = 1;
		contentPane.add(lblVille, gbc_lblVille);
		
		txtVille = new JTextField();
		txtVille.setText(equipe.getVille());
		GridBagConstraints gbc_txtVille = new GridBagConstraints();
		gbc_txtVille.insets = new Insets(0, 0, 5, 5);
		gbc_txtVille.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVille.gridx = 1;
		gbc_txtVille.gridy = 1;
		contentPane.add(txtVille, gbc_txtVille);
		txtVille.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom : ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		txtNom = new JTextField();
		txtNom.setText(equipe.getNomEquipe());
		GridBagConstraints gbc_txtNom = new GridBagConstraints();
		gbc_txtNom.insets = new Insets(0, 0, 5, 5);
		gbc_txtNom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNom.gridx = 1;
		gbc_txtNom.gridy = 2;
		contentPane.add(txtNom, gbc_txtNom);
		txtNom.setColumns(10);
		
		lblDossard = new JLabel("Dossard : ");
		GridBagConstraints gbc_lblDossard = new GridBagConstraints();
		gbc_lblDossard.anchor = GridBagConstraints.EAST;
		gbc_lblDossard.insets = new Insets(0, 0, 5, 5);
		gbc_lblDossard.gridx = 0;
		gbc_lblDossard.gridy = 3;
		contentPane.add(lblDossard, gbc_lblDossard);
		
		txtDossard = new JTextField();
		txtDossard.setText(equipe.getDossard());
		GridBagConstraints gbc_txtDossard = new GridBagConstraints();
		gbc_txtDossard.insets = new Insets(0, 0, 5, 5);
		gbc_txtDossard.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDossard.gridx = 1;
		gbc_txtDossard.gridy = 3;
		contentPane.add(txtDossard, gbc_txtDossard);
		txtDossard.setColumns(10);
		
		chckbxNonClasse = new JCheckBox("Non classé");
		chckbxNonClasse.setSelected(equipe.getNonClasse());
		GridBagConstraints gbc_chckbxNonClasse = new GridBagConstraints();
		gbc_chckbxNonClasse.anchor = GridBagConstraints.EAST;
		gbc_chckbxNonClasse.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNonClasse.gridx = 0;
		gbc_chckbxNonClasse.gridy = 4;
		contentPane.add(chckbxNonClasse, gbc_chckbxNonClasse);
		
		chckbxAbsentOuAbandon = new JCheckBox("Absent ou abandon");
		chckbxAbsentOuAbandon.setSelected(equipe.getAbsent());
		GridBagConstraints gbc_chckbxAbsentOuAbandon = new GridBagConstraints();
		gbc_chckbxAbsentOuAbandon.anchor = GridBagConstraints.WEST;
		gbc_chckbxAbsentOuAbandon.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxAbsentOuAbandon.gridx = 1;
		gbc_chckbxAbsentOuAbandon.gridy = 4;
		contentPane.add(chckbxAbsentOuAbandon, gbc_chckbxAbsentOuAbandon);
		
		chckbxDisqualifie = new JCheckBox("Disqualifiée");
		chckbxDisqualifie.setSelected(equipe.getDsq());
		GridBagConstraints gbc_chckbxDisqualifie = new GridBagConstraints();
		gbc_chckbxDisqualifie.anchor = GridBagConstraints.WEST;
		gbc_chckbxDisqualifie.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxDisqualifie.gridx = 2;
		gbc_chckbxDisqualifie.gridy = 4;
		contentPane.add(chckbxDisqualifie, gbc_chckbxDisqualifie);
		
		lblCatgorie = new JLabel("Catégorie : ");
		lblCatgorie.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCatgorie = new GridBagConstraints();
		gbc_lblCatgorie.anchor = GridBagConstraints.EAST;
		gbc_lblCatgorie.insets = new Insets(0, 0, 5, 5);
		gbc_lblCatgorie.gridx = 0;
		gbc_lblCatgorie.gridy = 5;
		contentPane.add(lblCatgorie, gbc_lblCatgorie);
		
		cbxCategorie = new JComboBox<String>();
		for (String cat : Categories) {
			cbxCategorie.addItem(cat);
		}
		cbxCategorie.setSelectedItem(equipe.getCategorie());
		
		
		GridBagConstraints gbc_cbxCategorie = new GridBagConstraints();
		gbc_cbxCategorie.insets = new Insets(0, 0, 5, 5);
		gbc_cbxCategorie.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbxCategorie.gridx = 1;
		gbc_cbxCategorie.gridy = 5;
		contentPane.add(cbxCategorie, gbc_cbxCategorie);
		
		panCoureur = new JPanel();
		panCoureur.setPreferredSize(new Dimension(200, 10));
		panCoureur.setBorder(new TitledBorder(null, "Coureurs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panCoureur = new GridBagConstraints();
		gbc_panCoureur.gridwidth = 3;
		gbc_panCoureur.insets = new Insets(0, 0, 5, 0);
		gbc_panCoureur.fill = GridBagConstraints.BOTH;
		gbc_panCoureur.gridx = 0;
		gbc_panCoureur.gridy = 6;
		contentPane.add(panCoureur, gbc_panCoureur);
		
		
		panCoureur.setLayout(new BorderLayout(0, 0));
		
		panCoureurs = new JPanel();
		panCoureur.add(panCoureurs, BorderLayout.CENTER);
		
		tableCoureurs = new JTable();
		//{"B", "C", "G", "123456789"},
		tableCoureurs.setModel(new DefaultTableModel(
			new Object[][] {				
				{equipe.getCoureurs()[0].getNom(), equipe.getCoureurs()[0].getPrenom(), equipe.getCoureurs()[0].getSexe(), equipe.getCoureurs()[0].getPuce()},		
				{equipe.getCoureurs()[1].getNom(), equipe.getCoureurs()[1].getPrenom(), equipe.getCoureurs()[1].getSexe(), equipe.getCoureurs()[1].getPuce()},		
				{equipe.getCoureurs()[2].getNom(), equipe.getCoureurs()[2].getPrenom(), equipe.getCoureurs()[2].getSexe(), equipe.getCoureurs()[2].getPuce()},		
				{equipe.getCoureurs()[3].getNom(), equipe.getCoureurs()[3].getPrenom(), equipe.getCoureurs()[3].getSexe(), equipe.getCoureurs()[3].getPuce()},
			},
			new String[] {
				"Nom", "Pr\u00E9nom", "Sexe", "Puce"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCoureurs.getColumnModel().getColumn(0).setPreferredWidth(120);
		tableCoureurs.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableCoureurs.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableCoureurs.getColumnModel().getColumn(3).setPreferredWidth(80);
		tableCoureurs.setCellSelectionEnabled(true);
		tableCoureurs.setAutoCreateRowSorter(true);
		
		JScrollPane jsp = new JScrollPane(tableCoureurs);
		jsp.setPreferredSize(new Dimension(500, 120));
	    panCoureurs.add(jsp, BorderLayout.CENTER);
	    
	    panel = new JPanel();
	    GridBagConstraints gbc_panel = new GridBagConstraints();
	    gbc_panel.insets = new Insets(0, 0, 5, 5);
	    gbc_panel.fill = GridBagConstraints.BOTH;
	    gbc_panel.gridx = 1;
	    gbc_panel.gridy = 7;
	    contentPane.add(panel, gbc_panel);
	    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    
	    lblAutoPuce = new JLabel("Auto puce : ");
	    panel.add(lblAutoPuce);
	    
	    btnLanceAutopuce = new JButton("");
	    btnLanceAutopuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				litAutoPuces();
			}
		});
	    btnLanceAutopuce.setPreferredSize(new Dimension(27, 27));
	    btnLanceAutopuce.setIcon(new ImageIcon(AjouteEquipe.class.getResource("/icones/run.png")));
	    panel.add(btnLanceAutopuce);
	    
	    btnConnexionStation = new JButton("");
	    btnConnexionStation.setPreferredSize(new Dimension(27, 27));
	    btnConnexionStation.setIcon(new ImageIcon(AjouteEquipe.class.getResource("/icones/attente.png")));
	    panel.add(btnConnexionStation);
	    
	    btnArreteAutopuce = new JButton("");
	    btnArreteAutopuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arreteAutoPuces();
			}
		});
	    btnArreteAutopuce.setPreferredSize(new Dimension(27, 27));
	    btnArreteAutopuce.setIcon(new ImageIcon(AjouteEquipe.class.getResource("/icones/stop.png")));
	    panel.add(btnArreteAutopuce);
	    GridBagConstraints gbc_btnEnregistre = new GridBagConstraints();
	    gbc_btnEnregistre.anchor = GridBagConstraints.EAST;
	    gbc_btnEnregistre.insets = new Insets(0, 0, 5, 5);
	    gbc_btnEnregistre.gridx = 0;
	    gbc_btnEnregistre.gridy = 8;
	    btnEnregistre.setToolTipText("Valider les modifications");
	    
	    afficheBoutonAutoPuce(true, false, false);
	    
	    btnEnregistre.setPreferredSize(new Dimension(64, 64));
	    btnEnregistre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				retourneEquipe();
			}
		});
	    
	    
	    btnEnregistre.setIcon(new ImageIcon(AjouteEquipe.class.getResource("/icones/ok64.png")));
	    contentPane.add(btnEnregistre, gbc_btnEnregistre);
	    
	    btnQuitter = new JButton("");
	    btnQuitter.setToolTipText("Abandonner les modifications");
	    btnQuitter.setPreferredSize(new Dimension(64, 64));
	    btnQuitter.setIcon(new ImageIcon(AjouteEquipe.class.getResource("/icones/annule64.png")));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				equipe=null;
				dispose();
			}
		});
	    GridBagConstraints gbc_btnQuitter = new GridBagConstraints();
	    gbc_btnQuitter.anchor = GridBagConstraints.WEST;
	    gbc_btnQuitter.insets = new Insets(0, 0, 5, 0);
	    gbc_btnQuitter.gridx = 2;
	    gbc_btnQuitter.gridy = 8;
	    contentPane.add(btnQuitter, gbc_btnQuitter);
		
		
	}


	/**
	 * Démarre la lecture de auto puce
	 */
	protected void litAutoPuces() {
		lecteurAutoPuce = new AutoPuceCoureur(this);
	}
	
	/**
	 * Arrête la lecture de auto puces
	 */
	protected void arreteAutoPuces() {
		lecteurAutoPuce.arreteLecture();
	}

	/**
	 * Affiche ou cache les boutons d'état de auto puce
	 * 
	 * @param pLit boolean bouton Lancer la lecture
	 * @param pConnexion boolean bouton Connexion en cours
	 * @param pArrete boolean bouton arrêter la lecture
	 */
	public void afficheBoutonAutoPuce(boolean pLit, boolean pConnexion, boolean pArrete) {
		btnLanceAutopuce.setVisible(pLit);
		btnConnexionStation.setVisible(pConnexion);
		btnArreteAutopuce.setVisible(pArrete);
	}
	
	/**
	 * Attribue une puce au premier coureur sans puce
	 * 
	 * @param pIdPuce
	 */
	public void setAutoPuce(String pIdPuce) {
		System.out.println("autopuce : " + pIdPuce);
		for (int i=0;i<4;i++) {
			if (tableCoureurs.getModel().getValueAt(i, 3).toString().equals(pIdPuce)) {
				JOptionPane.showMessageDialog(null, "La puce "+pIdPuce+" est déjà attribuée", "Attention", JOptionPane.ERROR_MESSAGE);
				break;
			}
			if (tableCoureurs.getModel().getValueAt(i, 3).toString().isEmpty()) {
				tableCoureurs.getModel().setValueAt(pIdPuce, i, 3);
				break;
			}
			else {
				System.out.println(i + " a déjà une puce");
			}
		}
	}
	
	public void retourneEquipe() {
		equipe.setEtablissement(txtEtablissement.getText());
		equipe.setVille(txtVille.getText());
		equipe.setNomEquipe(txtNom.getText());
		equipe.setDossard(txtDossard.getText());
		equipe.setNonClasse(chckbxNonClasse.isSelected());
		equipe.setAbsent(chckbxAbsentOuAbandon.isSelected());
		equipe.setDsq(chckbxDisqualifie.isSelected());
		if (cbxCategorie.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "L'équipe doit avoir une catégorie", "Attention", JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			equipe.setCategorie(cbxCategorie.getSelectedItem().toString());
		}
		
		if (equipe.getNomEquipe().length() < 1) {
			JOptionPane.showMessageDialog(null, "L'équipe doit avoir un nom", "Attention", JOptionPane.ERROR_MESSAGE);
		} else {
			for (int i=0;i<4;i++) {
				equipe.coureurs[i].setNom(tableCoureurs.getModel().getValueAt(i, 0).toString());
				equipe.coureurs[i].setPrenom(tableCoureurs.getModel().getValueAt(i, 1).toString());
				equipe.coureurs[i].setSexe(tableCoureurs.getModel().getValueAt(i, 2).toString());
				equipe.coureurs[i].setPuce(tableCoureurs.getModel().getValueAt(i, 3).toString());			
			}			
			if (modifie) {
				//maFenetre.modifieUneEquipe(equipe);
				this.dispose();	
				
			} 
			else if (!maFenetre.equipeConnue(equipe.getNomEquipe())){
				maFenetre.ajouteUneEquipe(equipe);
				this.dispose();	
			} 
			else {
				JOptionPane.showMessageDialog(null, "L'équipe " + equipe.getNomEquipe() + " éxiste déjà, vous devez choisir un autre nom", "Attention", JOptionPane.ERROR_MESSAGE);
			}	
		}
	}
	
}
