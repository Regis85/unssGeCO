package unssGeCO;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;

import gnu.io.CommPortIdentifier;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Fenetre {

	private JFrame frmCoUnss;
	
	private JButton btnLanceLecture;
	private JButton btnConnectEnCours;
	private JButton btnArreteLecture;
	
	private JLabel lblPuce;
	
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
	private void initialize() {
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
		mntmNouveau.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\new.png"));
		mntmNouveau.setEnabled(false);
		mnFichier.add(mntmNouveau);
		
		JMenuItem mntmOuvrir = new JMenuItem("Ouvrir...");
		mntmOuvrir.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\open.png"));
		mntmOuvrir.setEnabled(false);
		mnFichier.add(mntmOuvrir);
		
		JMenuItem mntmEnregistrer = new JMenuItem("Enregistrer");
		mntmEnregistrer.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\save.png"));
		mntmEnregistrer.setEnabled(false);
		mnFichier.add(mntmEnregistrer);
		
		JMenuItem mntmEnregistrerSous = new JMenuItem("Enregistrer sous...");
		mntmEnregistrerSous.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\exportMenu.png"));
		mntmEnregistrerSous.setEnabled(false);
		mnFichier.add(mntmEnregistrerSous);
		
		JSeparator separator = new JSeparator();
		mnFichier.add(separator);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\exit.png"));
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
		btnEnregistrer.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\save32.png"));
		tbSauve.add(btnEnregistrer);
		
		JButton btnExportEquipe = new JButton("");
		btnExportEquipe.setEnabled(false);
		btnExportEquipe.setToolTipText("Exporter les équipes en CSV");
		tbSauve.add(btnExportEquipe);
		//Exporter les équipes en CSV
		btnExportEquipe.setPreferredSize(new Dimension(27,27));
		btnExportEquipe.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\export.png"));
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
		btnSauvegardeAutomatique.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\run.png"));
		tbLanceLecture.add(btnSauvegardeAutomatique);
		
		pnlSauvegarde.setBorder(new TitledBorder(null, "", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION)); 
		
		JToolBar tbLecture = new JToolBar();
		tbLecture.setToolTipText("Heure zéro et lecture des puces");
		tbLecture.setBorder(new TitledBorder(null, "Puces", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		pnlSauvegarde.add(tbLecture);
		
		JComboBox cmbHeureDepart = new JComboBox();
		cmbHeureDepart.setToolTipText("Heure zéro");
		for(int i=0;i<24;i++) {
			cmbHeureDepart.addItem(i);
		}
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
		btnLanceLecture.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\play.png"));
		tbLecture.add(btnLanceLecture);
		
		btnConnectEnCours = new JButton("");
		btnConnectEnCours.setPreferredSize(new Dimension(27, 27));
		btnConnectEnCours.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\attente.png"));
		tbLecture.add(btnConnectEnCours);
		
		btnArreteLecture = new JButton("");
		btnArreteLecture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arreteLitPuces();
			}
		});
		btnArreteLecture.setIcon(new ImageIcon("C:\\Users\\Régis\\eclipse-workspace\\unssGeCO\\icones\\stop.png"));
		btnArreteLecture.setPreferredSize(new Dimension(27, 27));
		btnArreteLecture.setSelectedIcon(null);
		tbLecture.add(btnArreteLecture);
		
		lblPuce = new JLabel("puce");
		frmCoUnss.getContentPane().add(lblPuce, BorderLayout.SOUTH);


		afficheBouton(true,false,false);
		
	}

	/**
	 * Démarre la lecture
	 */
	private void litPuces() {
		lblPuce.setText("Connecté à la station");
		afficheBouton(false,false,true);
	}
	
	public void afficheBouton(boolean pLit, boolean pConnexion, boolean pArrete) {
		btnLanceLecture.setVisible(pLit);
		btnConnectEnCours.setVisible(pConnexion);
		btnArreteLecture.setVisible(pArrete);
	}
	

	/**
	 * Démarre la lecture
	 */
	private void arreteLitPuces() {
		lblPuce.setText("Déonnecté de la station");
		afficheBouton(true,false,false);
	}
	

}
