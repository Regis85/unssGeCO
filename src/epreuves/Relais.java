package epreuves;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.Categorie;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Relais extends JDialog {

	protected JPanel contentPane;
	
	protected Categorie categorie;
	private JTextField txtNomrelais;
	protected Vector<Parcours> parcours;
	
	private String retourClasse;

	/**
	 * Launch the application.
	 */
	
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relais frame = new Relais("Nouveau relais", Categorie.College);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * @wbp.parser.constructor
	 */
	public Relais(String pTitre, Categorie pCategorie) {
		categorie = pCategorie;
		this.setTitle(pTitre);
		Initialise();
	}
	
	/**
	 * Create the frame.
	 */
	private void Initialise() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JCheckBox chckbxCollge = new JCheckBox("Collège");
		chckbxCollge.setEnabled(false);
		panel.add(chckbxCollge);
		if(categorie !=null && categorie.equals(Categorie.valueOfByCategorie("Collège"))){
			chckbxCollge.setSelected(true);
		}
		
		JCheckBox chckbxLyce = new JCheckBox("Lycée");
		chckbxLyce.setEnabled(false);
		panel.add(chckbxLyce);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JLabel lblNomDuParcours = new JLabel("Nom du parcours :");
		panel_1.add(lblNomDuParcours);
		
		txtNomrelais = new JTextField();
		txtNomrelais.setPreferredSize(new Dimension(250, 20));
		txtNomrelais.setMinimumSize(new Dimension(250, 20));
		panel_1.add(txtNomrelais);
		txtNomrelais.setColumns(10);
		if(categorie !=null && categorie.equals(Categorie.valueOfByCategorie("Lycée"))){
			chckbxLyce.setSelected(true);
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
						System.out.println("nom parcours " + txtNomrelais.getText());
						retourClasse = txtNomrelais.getText();
					    setVisible(false);
						dispose();
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
		
	}
	
	protected boolean nomUnique(String nomCherche) {
		boolean retour = true;
		for(Parcours parc : parcours) {
			if(nomCherche.equals(parc.getNom())) {
				retour = false;
				break;
			}
		}
		return retour;
	}
	
	public void setNom(String pNomRelais) {
		txtNomrelais.setText(pNomRelais);
	}
	
	public String getNom() {
		this.setVisible(true);
		return retourClasse;
	}

}
