package equipe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import equipe.Equipe;
import modele.Categorie;
import unssGeCO.Fenetre;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JSpinner;

@SuppressWarnings("serial")
public class InitialiseDossards extends JDialog {
	
	protected Fenetre maFenetre;
	protected Vector<Equipe> equipes;
	protected Categorie categorie;

	private JPanel contentPane;
	private JTextField txtPrefixe;
	private JTextField txtSuffixe;
	private JSpinner spinnerNum;

	/**
	 * Launch the application.
	 */
	/**
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitialiseDossards frame = new InitialiseDossards(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */

	/**
	 * Create the frame.
	 */
	public InitialiseDossards(Vector<Equipe> pEquipes, Categorie pCategorie) {
		equipes = pEquipes;
		categorie = pCategorie;
		initialise();
	}
	
	/**
	 * Create the frame.
	 */
	protected void initialise() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Dossards");
		setBounds(100, 100, 250, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPrefixe = new JLabel("Préfixe :");
		GridBagConstraints gbc_lblPrefixe = new GridBagConstraints();
		gbc_lblPrefixe.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrefixe.anchor = GridBagConstraints.EAST;
		gbc_lblPrefixe.gridx = 0;
		gbc_lblPrefixe.gridy = 0;
		contentPane.add(lblPrefixe, gbc_lblPrefixe);
		
		txtPrefixe = new JTextField();
		GridBagConstraints gbc_txtPrefixe = new GridBagConstraints();
		gbc_txtPrefixe.insets = new Insets(0, 0, 5, 0);
		gbc_txtPrefixe.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPrefixe.gridx = 1;
		gbc_txtPrefixe.gridy = 0;
		contentPane.add(txtPrefixe, gbc_txtPrefixe);
		txtPrefixe.setColumns(10);
		
		JLabel lblPremierNumro = new JLabel("Premier numéro :");
		GridBagConstraints gbc_lblPremierNumro = new GridBagConstraints();
		gbc_lblPremierNumro.anchor = GridBagConstraints.EAST;
		gbc_lblPremierNumro.insets = new Insets(0, 0, 5, 5);
		gbc_lblPremierNumro.gridx = 0;
		gbc_lblPremierNumro.gridy = 1;
		contentPane.add(lblPremierNumro, gbc_lblPremierNumro);	
		
		spinnerNum = new JSpinner();
		spinnerNum.setValue(1);
		spinnerNum.setPreferredSize(new Dimension(45, 20));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.anchor = GridBagConstraints.WEST;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 1;
		contentPane.add(spinnerNum, gbc_spinner);
		
		JLabel label = new JLabel("Suffixe :");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		contentPane.add(label, gbc_label);
		
		txtSuffixe = new JTextField();
		GridBagConstraints gbc_txtSuffixe = new GridBagConstraints();
		gbc_txtSuffixe.insets = new Insets(0, 0, 5, 0);
		gbc_txtSuffixe.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSuffixe.gridx = 1;
		gbc_txtSuffixe.gridy = 2;
		contentPane.add(txtSuffixe, gbc_txtSuffixe);
		txtSuffixe.setColumns(10);
		
		JButton btnValider = new JButton("");
		btnValider.setToolTipText("Lancer la numérotation");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				creeDossards();
			}
		});
		btnValider.setPreferredSize(new Dimension(52, 52));
		btnValider.setIcon(new ImageIcon(InitialiseDossards.class.getResource("/icones/ok64.png")));
		GridBagConstraints gbc_btnValider = new GridBagConstraints();
		gbc_btnValider.insets = new Insets(0, 0, 0, 5);
		gbc_btnValider.gridx = 0;
		gbc_btnValider.gridy = 4;
		contentPane.add(btnValider, gbc_btnValider);
		
		JButton btnAbandonne = new JButton("");
		btnAbandonne.setToolTipText("Abandonner");
		btnAbandonne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnAbandonne.setPreferredSize(new Dimension(52, 52));
		btnAbandonne.setIcon(new ImageIcon(InitialiseDossards.class.getResource("/icones/annule64.png")));
		GridBagConstraints gbc_btnAbandonne = new GridBagConstraints();
		gbc_btnAbandonne.gridx = 1;
		gbc_btnAbandonne.gridy = 4;
		contentPane.add(btnAbandonne, gbc_btnAbandonne);
	}
	
	/**
	 * Crée automatiquement les dossards pour les élèves qui n'en ont pas
	 */
	protected void creeDossards() {
		String prefixe = txtPrefixe.getText();
		String suffixe = txtSuffixe.getText();
		int indice = Integer.parseInt(spinnerNum.getValue().toString());
		String newDossard = prefixe + indice + suffixe;
		while (chercheNoDossard() != -1)  {
			while (!dossardInconnu(newDossard)) {
				indice++;
				newDossard = prefixe + indice + suffixe;
			}
			equipes.elementAt(chercheNoDossard()).setDossard(newDossard);
			indice++;
			newDossard = prefixe + indice + suffixe;
		}
		dispose();
		
	}
	
	/**
	 * Cherche si le dossard n'est pas déjà attribué
	 * 
	 * @param pDossard String le dossard recherché
	 * @return boolean faux si le dossard est déjà attribué
	 */
	private boolean dossardInconnu(String pDossard) {
		boolean retour = true;
		for (Equipe equipe : equipes) {
			if (equipe.getDossard().equals(pDossard)) {
				retour = false;
				break;
			}
		}
		return retour;
	}
	
	/**
	 * Recherche la première équipe sans dossard
	 * 
	 * @return l'indice de l'équipe qui n'a pas de dossard ou -1
	 */
	private int chercheNoDossard() {
		int retour = -1;
		for (Equipe equipe : equipes) {
			System.out.println(equipe.getCategorie() + " -> " + categorie + " dossard " + equipe.getDossard());
			if (equipe.getDossard().equals("") && equipe.getCategorie().toString().equals(categorie.toString())) {
				retour = equipes.indexOf(equipe);
				break;
			}
		}
		return retour;
	}

}
