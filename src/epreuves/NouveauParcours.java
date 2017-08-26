package epreuves;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.SwingConstants;

import equipe.Coureur;
import equipe.Equipe;
import modele.Categorie;
import unssGeCO.Preferences;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class NouveauParcours extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JTextField textField_Nom;
	
	private Vector<Equipe> equipes = new Vector<Equipe>();
	
	private Epreuve epreuve;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NouveauParcours dialog = new NouveauParcours();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public NouveauParcours(Vector<Equipe> pEquipes, Epreuve pEpreuve) {
		equipes = pEquipes;
		epreuve = pEpreuve;
		initialise();
	}
	
	public NouveauParcours() {
		
		for(int i=0;i<4;i++) {
			String[] eqp = {"Beaussire","Luçon","Beaussire "+i,"Collège","Bouguin "+i,"Régis "+i,"G","","Bouguin "+i,"Pascale "+i,"F","","Bouguin "+i,"Chloé "+i,"F","","Bouguin "+i,"Fanny "+i,"F","",""};
			Equipe newEquipe = new Equipe(eqp, new Preferences());
			equipes.addElement(newEquipe);
			epreuve = new Epreuve();
			epreuve.setId(1);;
			epreuve.setNumOrdre(1);
			epreuve.setNom("Relais collège");
			epreuve.setCategorie(Categorie.College);
		}
		
		initialise();
	}
	/**
	 * Create the dialog.
	 */
	public void initialise() {
		setTitle("Création d'un parcours du relais " + epreuve.getCategorie().intitule() + " : " + epreuve.getNom());
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			{
				JLabel lblNom = new JLabel("Nom :");
				lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNom.setAlignmentX(Component.RIGHT_ALIGNMENT);
				lblNom.setPreferredSize(new Dimension(100, 14));
				panel.add(lblNom);
			}
			{
				textField_Nom = new JTextField();
				textField_Nom.setPreferredSize(new Dimension(300, 20));
				textField_Nom.setMinimumSize(new Dimension(300, 20));
				panel.add(textField_Nom);
				textField_Nom.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

			{
				JPanel panel_1;
				for(int i=0;i<20;i++) {
				}	
				for(Equipe eqp : equipes) {
					
					panel_1 = new JPanel();
					panel_1.setBackground(Color.WHITE);
					panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
					JLabel lblNewLabel = new JLabel(eqp.getNomEquipe());
					panel_1.add(lblNewLabel);
					JComboBox<String> cbx_Coureur = new JComboBox<String>();
					cbx_Coureur.setPreferredSize(new Dimension(200, 20));
					cbx_Coureur.setMinimumSize(new Dimension(200, 20));
					Coureur[] coureurs = eqp.getCoureurs();
					cbx_Coureur.addItem("aucun");
					for(Coureur coureur : coureurs) {
						cbx_Coureur.addItem(coureur.getNom()+" "+coureur.getPrenom());
					}
					panel_1.add(cbx_Coureur);
					
					panel.add(panel_1);
				}
				
			}
			{
				
				JScrollPane scrollPane = new JScrollPane(panel);
				
				contentPanel.add(scrollPane, BorderLayout.CENTER);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			{
				JLabel lblPourChaquequipe = new JLabel("Pour chaque équipe, sélectionnez le coureur concerné ");
				panel.add(lblPourChaquequipe);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						sauveParcours();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			}
		}
	}
	
	public void sauveParcours() {
		System.out.println("On crée le parcours : " + textField_Nom.getText());
		dispose();
	}
	

}
