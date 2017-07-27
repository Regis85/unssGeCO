package unssGeCO;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import gnu.io.CommPortIdentifier;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.BoxLayout;

public class Fenetre {

	protected JFrame frmCoUnss;
	
	protected JButton btnLanceLecture;
	protected JButton btnConnectEnCours;
	protected JButton btnArreteLecture;
	
	protected JButton btnLanceAutoPuce;
	protected JButton btnConnexionAutoPuce;
	protected JButton btnArreteAutoPuce;
	
	protected JPanel panelEquipes;
	
	protected JLabel lblPuce;

	protected LecteurPuces lecteurPuce;
	protected LecteurPuces lecteurAutoPuce;
	
	protected int nbEquipes;
	
	protected String[] categorie = {"Collège", "Lycée"};
	
	protected Vector<Equipe> equipes = new Vector<Equipe>();
	private String equipeChoisie;
	
	final int arrive = 1;
	final int abandon = 2;
	final int dsq = 3;
	
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
		frmCoUnss.setBounds(100, 100, 860, 491);
		frmCoUnss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmCoUnss.setJMenuBar(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmNouveau = new JMenuItem("Nouveau");
		mntmNouveau.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/new.png")));
		mntmNouveau.setEnabled(false);
		mnFichier.add(mntmNouveau);
		
		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir...");
		mntmOuvrir.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/open.png")));
		mntmOuvrir.setEnabled(false);
		mnFichier.add(mntmOuvrir);
		
		JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
		mntmEnregistrer.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/save.png")));
		mntmEnregistrer.setEnabled(false);
		mnFichier.add(mntmEnregistrer);
		
		JMenuItem mntmEnregistrerSous = new JMenuItem("Enregistrer sous...");
		mntmEnregistrerSous.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/exportMenu.png")));
		mntmEnregistrerSous.setEnabled(false);
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
		
		JButton btnEnregistrer = new JButton("");
		btnEnregistrer.setToolTipText("Enregistrer");
		//Enregistrer
		btnEnregistrer.setPreferredSize(new Dimension(27,27));
		btnEnregistrer.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/save32.png")));
		tbSauve.add(btnEnregistrer);
		
		JButton btnExportEquipe = new JButton("");
		btnExportEquipe.setEnabled(false);
		btnExportEquipe.setToolTipText("Exporter les équipes en CSV");
		tbSauve.add(btnExportEquipe);
		//Exporter les équipes en CSV
		btnExportEquipe.setPreferredSize(new Dimension(27,27));
		btnExportEquipe.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/exportMenu.png")));
		btnExportEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JComboBox comboBoxMinutes = new JComboBox();
		comboBoxMinutes.setEnabled(false);
		comboBoxMinutes.setToolTipText("Sauvegarde automatique toutes les ...");
		for(int i=1;i<60;i++) {
			comboBoxMinutes.addItem(i);
		}
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
		
		JComboBox cmbHeureDepart = new JComboBox();
		cmbHeureDepart.setToolTipText("Heure zéro");
		for(int i=0;i<24;i++) {
			cmbHeureDepart.addItem(i);
		}
		
		JLabel lblHeureZro = new JLabel("Heure zéro : ");
		tbLecture.add(lblHeureZro);
		tbLecture.add(cmbHeureDepart);
		
		JLabel lblHeDepart = new JLabel(" H ");
		lblHeDepart.setLabelFor(cmbHeureDepart);
		tbLecture.add(lblHeDepart);
		
		JComboBox cmbMinDepart = new JComboBox();
		cmbMinDepart.setToolTipText("Minute zéro");
		for(int i=0;i<60;i++) {
			cmbMinDepart.addItem(i);
		}
		tbLecture.add(cmbMinDepart);
		
		JLabel lblMinDepart = new JLabel(" MN ");
		lblMinDepart.setLabelFor(cmbMinDepart);
		tbLecture.add(lblMinDepart);
		
		JLabel lblLectureDesPuces = new JLabel("Lecture des puces : ");
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
		btnConnectEnCours.setToolTipText("Connexion en cours");
		btnConnectEnCours.setPreferredSize(new Dimension(27, 27));
		btnConnectEnCours.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/attente.png")));
		tbLecture.add(btnConnectEnCours);
		
		lblPuce = new JLabel("puce");
		frmCoUnss.getContentPane().add(lblPuce, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 10));
		panel.setBorder(new TitledBorder(null, "\u00C9quipes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmCoUnss.getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblequipes = new JLabel(getLblEquipes());
		panel_1.add(lblequipes);
		
		JButton btnAjouteEquipe = new JButton("");
		btnAjouteEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addEquipe();
			}
		});
		btnAjouteEquipe.setPreferredSize(new Dimension(27, 27));
		btnAjouteEquipe.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/plus.png")));
		panel_1.add(btnAjouteEquipe);
		
		JButton btnModifieEquipe = new JButton("");
		btnModifieEquipe.setPreferredSize(new Dimension(27, 27));
		btnModifieEquipe.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/find.png")));
		panel_1.add(btnModifieEquipe);
		
		JButton btnSupprimeBouton = new JButton("");
		btnSupprimeBouton.setToolTipText("Supprimer l'équipe sélectionnée");
		btnSupprimeBouton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				effaceEquipe();
			}
		});
		btnSupprimeBouton.setPreferredSize(new Dimension(27, 27));
		btnSupprimeBouton.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/delete.png")));
		panel_1.add(btnSupprimeBouton);
		
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
		btnArreteAutoPuce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arreteAutoPuces();
			}
		});
		btnArreteAutoPuce.setPreferredSize(new Dimension(27, 27));
		btnArreteAutoPuce.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/stop.png")));
		panel_1.add(btnArreteAutoPuce);
		
		btnConnexionAutoPuce = new JButton("");
		btnConnexionAutoPuce.setIcon(new ImageIcon(Fenetre.class.getResource("/icones/attente.png")));
		btnConnexionAutoPuce.setPreferredSize(new Dimension(27, 27));
		panel_1.add(btnConnexionAutoPuce);
		
		panelEquipes = new JPanel();
		panelEquipes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelEquipes.setBackground(Color.WHITE);
		panel.add(panelEquipes, BorderLayout.CENTER);
		panelEquipes.setLayout(new BoxLayout(panelEquipes, BoxLayout.Y_AXIS));
		
		afficheBouton(true,false,false);
		afficheBoutonAutoPuce(true,false,false);
		
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
	
	public void setLblPuce(String pLblPuce) {
		lblPuce.setText(pLblPuce);
	}
	
	protected String getLblEquipes() {
		nbEquipes = equipes.size();
		String retour = nbEquipes + " équipe";
		retour = nbEquipes > 1 ? retour + "s " : retour + " ";
		return retour;
	}
	
	public void ajouteUneEquipe(Equipe pNewEquipe) {
		equipes.add(pNewEquipe);
	}
	
	public boolean equipeConnue(String nomEquipe) {
		boolean retour = false;
		for (Equipe eq : equipes) {
			if(eq.getNomEquipe().equals(nomEquipe)) {
				System.out.println("L'équipe " + eq.getNomEquipe() + " est connue");
				retour = true;
				break;
			}
		}
		return retour;
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
	
	protected void addEquipe() {
		try {
			System.out.println("avant " + equipes.size() + " équipes");
			AjouteEquipe frame = new AjouteEquipe(this);
			int nbEquipe = equipes.size();
			frame.setModal(true);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			System.out.println("retour " + equipes.size() + " équipes");
			if(equipes.size() > nbEquipe) {
				afficheEquipes();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void afficheEquipes() {
		panelEquipes.removeAll();
		panelEquipes.updateUI();
		if (equipes.size() > 0) {
			System.out.println("On affiche");
			for(Equipe equipe : equipes) {
				afficheEquipe(equipe);
			}
		} else {
			System.out.println("Rien à afficher");
			panelEquipes.removeAll();
		}
	}
	
	protected void afficheEquipe(Equipe pEquipe) {
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
		
		if (pEquipe.getNomEquipe().equals(equipeChoisie)) {
			lblNewEquipe.setForeground(Color.RED);
		}
		
		lblNewEquipe.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Vous avez clické " + pEquipe.getNomEquipe());
                equipeChoisie = pEquipe.getNomEquipe();
                afficheEquipes();
            }

        });
		
		panelEquipes.add(lblNewEquipe);
		panelEquipes.updateUI();
	}
	
	protected void effaceEquipe() {
		System.out.println("Suppression de " + equipeChoisie + " nombre d'Equipe " + equipes.size());
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
		System.out.println("nombre d'Equipe " + equipes.size());
		afficheEquipes();
	}


}
