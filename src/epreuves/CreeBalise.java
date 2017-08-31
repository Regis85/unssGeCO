package epreuves;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class CreeBalise extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private Balise balise = new Balise();
	
	JSpinner spinner_code = new JSpinner();
	
	JSpinner spinner_trouvePoints = new JSpinner();
	JSpinner spinner_trouveH = new JSpinner();
	JSpinner spinner_trouveMn = new JSpinner();
	JSpinner spinner_trouveSec = new JSpinner();
	
	JSpinner spinner_PmPoints = new JSpinner();
	JSpinner spinner_PmH = new JSpinner();
	JSpinner spinner_PmMn = new JSpinner();
	JSpinner spinner_PmSec = new JSpinner();
	
	ButtonGroup bg_Trouve = new ButtonGroup();
	
	JRadioButton rdbtnTrouveBonif = new JRadioButton("Bonification");
	JRadioButton rdbtnTrouvePnalit = new JRadioButton("Pénalité");

	JRadioButton rdbtnPmBonif = new JRadioButton("Bonification");
	JRadioButton rdbtnPmPnalit = new JRadioButton("Pénalité");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreeBalise dialog = new CreeBalise();
			dialog.setLocationRelativeTo(null);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CreeBalise() {
		balise = new Balise();
		Initialise();
	}
	

	public CreeBalise(Balise pBalise) {
		balise = pBalise;
		System.out.println(balise.getTrouveBonif() + " - " + balise.getTrouvePoints());
		Initialise();
	}

	/**
	 * Create the dialog.
	 */
	public void Initialise() {
		
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		this.setTitle("Balise");
		setBounds(100, 100, 287, 411);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_mode = new JPanel();
		contentPanel.add(panel_mode, BorderLayout.NORTH);

		/***** Code de la balise *****/
		JLabel lblCode = new JLabel("Code :");
		panel_mode.add(lblCode);
		//JSpinner spinner_code = new JSpinner();
		spinner_code.setModel(new SpinnerNumberModel(new Integer(30), new Integer(30), null, new Integer(1)));
		spinner_code.setMinimumSize(new Dimension(50, 20));
		spinner_code.setPreferredSize(new Dimension(50, 20));
		if (balise.getCode() > 30) {
			spinner_code.setValue(balise.getCode());
		}
		panel_mode.add(spinner_code);
		
		/***** Balise découverte *****/
		JPanel panel_Donnees = new JPanel();
		contentPanel.add(panel_Donnees, BorderLayout.CENTER);
		panel_Donnees.setLayout(new BoxLayout(panel_Donnees, BoxLayout.Y_AXIS));

		JPanel panel_decouvert = new JPanel();
		panel_decouvert.setBorder(new TitledBorder(null, "Si découvert", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Donnees.add(panel_decouvert);
		panel_decouvert.setLayout(new BoxLayout(panel_decouvert, BoxLayout.Y_AXIS));

		JPanel panel_trouveMode = new JPanel();
		panel_decouvert.add(panel_trouveMode);
		
		//JRadioButton rdbtnTrouveBonif = new JRadioButton("Bonification");
		panel_trouveMode.add(rdbtnTrouveBonif);
		
		//JRadioButton rdbtnTrouvePnalit = new JRadioButton("Pénalité");
		panel_trouveMode.add(rdbtnTrouvePnalit);
		if (balise.getTrouveBonif()) {
			rdbtnTrouveBonif.setSelected(true);
		} 
		else {
			rdbtnTrouvePnalit.setSelected(true);
		}

		//ButtonGroup bg_Trouve = new ButtonGroup();
		bg_Trouve.add(rdbtnTrouveBonif);
		bg_Trouve.add(rdbtnTrouvePnalit);

		JPanel panel_TrouvePoints = new JPanel();
		panel_decouvert.add(panel_TrouvePoints);

		JLabel lblPoints = new JLabel("Points :");
		panel_TrouvePoints.add(lblPoints);

		//JSpinner spinner_trouvePoints = new JSpinner();
		spinner_trouvePoints.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner_trouvePoints.setPreferredSize(new Dimension(50, 20));
		spinner_trouvePoints.setMinimumSize(new Dimension(50, 20));
		spinner_trouvePoints.setValue(balise.getTrouvePoints());
		panel_TrouvePoints.add(spinner_trouvePoints);

		JPanel panel_trouveTemps = new JPanel();
		panel_decouvert.add(panel_trouveTemps);

		JLabel lblTemps = new JLabel("Temps :");
		panel_trouveTemps.add(lblTemps);

		//JSpinner spinner_trouveH = new JSpinner();
		spinner_trouveH.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner_trouveH.setMinimumSize(new Dimension(40, 20));
		spinner_trouveH.setPreferredSize(new Dimension(40, 20));
		spinner_trouveH.setValue(balise.getTrouveHeure().getHour());
		panel_trouveTemps.add(spinner_trouveH);

		JLabel lblH = new JLabel("H");
		panel_trouveTemps.add(lblH);

		//JSpinner spinner_trouveMn = new JSpinner();
		spinner_trouveMn.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_trouveMn.setPreferredSize(new Dimension(40, 20));
		spinner_trouveMn.setMinimumSize(new Dimension(40, 20));
		spinner_trouveMn.setValue(balise.getTrouveHeure().getMinute());
		panel_trouveTemps.add(spinner_trouveMn);

		JLabel lblMn = new JLabel("MN");
		panel_trouveTemps.add(lblMn);

		//JSpinner spinner_trouveSec = new JSpinner();
		spinner_trouveSec.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_trouveSec.setMinimumSize(new Dimension(40, 20));
		spinner_trouveSec.setPreferredSize(new Dimension(40, 20));
		spinner_trouveSec.setValue(balise.getTrouveHeure().getSecond());
		panel_trouveTemps.add(spinner_trouveSec);

		JLabel lblS = new JLabel("S");
		panel_trouveTemps.add(lblS);

		/***** Poste manquant *****/		
		
		JPanel panel_posteManquant = new JPanel();
		panel_posteManquant.setBorder(new TitledBorder(null, "Si poste manquant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Donnees.add(panel_posteManquant);
		panel_posteManquant.setLayout(new BoxLayout(panel_posteManquant, BoxLayout.Y_AXIS));

		JPanel panel_PmMode = new JPanel();
		panel_posteManquant.add(panel_PmMode);

		//JRadioButton rdbtnPmBonif = new JRadioButton("Bonification");
		panel_PmMode.add(rdbtnPmBonif);

		//JRadioButton rdbtnPmPnalit = new JRadioButton("Pénalité");
		panel_PmMode.add(rdbtnPmPnalit);
		if (balise.getPmBonif()) {
			rdbtnPmBonif.setSelected(true);
		} 
		else {
			rdbtnPmPnalit.setSelected(true);
		}
		
		ButtonGroup bg_Pm = new ButtonGroup();
		bg_Pm.add(rdbtnPmBonif);
		bg_Pm.add(rdbtnPmPnalit);

		JPanel panel_PmPoints = new JPanel();
		panel_posteManquant.add(panel_PmPoints);

		JLabel lblPmPoints = new JLabel("Points :");
		panel_PmPoints.add(lblPmPoints);

		//JSpinner spinner_PmPoints = new JSpinner();
		spinner_PmPoints.setPreferredSize(new Dimension(50, 20));
		spinner_PmPoints.setMinimumSize(new Dimension(50, 20));
		spinner_PmPoints.setValue(balise.getPmPoints());
		panel_PmPoints.add(spinner_PmPoints);

		JPanel panel_PmTemps = new JPanel();
		panel_posteManquant.add(panel_PmTemps);

		JLabel lblPmTemps = new JLabel("Temps :");
		panel_PmTemps.add(lblPmTemps);

		//JSpinner spinner_PmH = new JSpinner();
		spinner_PmH.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		spinner_PmH.setMinimumSize(new Dimension(40, 20));
		spinner_PmH.setPreferredSize(new Dimension(40, 20));
		spinner_PmH.setValue(balise.getPmHeure().getHour());
		panel_PmTemps.add(spinner_PmH);

		JLabel lblPmH = new JLabel("H");
		panel_PmTemps.add(lblPmH);

		//JSpinner spinner_PmMn = new JSpinner();
		spinner_PmMn.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_PmMn.setPreferredSize(new Dimension(40, 20));
		spinner_PmMn.setMinimumSize(new Dimension(40, 20));
		spinner_PmMn.setValue(balise.getPmHeure().getMinute());
		panel_PmTemps.add(spinner_PmMn);

		JLabel lblPmMn = new JLabel("MN");
		panel_PmTemps.add(lblPmMn);

		//JSpinner spinner_PmSec = new JSpinner();
		spinner_PmSec.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		spinner_PmSec.setMinimumSize(new Dimension(40, 20));
		spinner_PmSec.setPreferredSize(new Dimension(40, 20));
		spinner_PmSec.setValue(balise.getPmHeure().getSecond());
		panel_PmTemps.add(spinner_PmSec);

		JLabel lblPmS = new JLabel("S");
		panel_PmTemps.add(lblPmS);		
		
		/***** Boutons *****/

		//panel_Donnee.FlowLayout(FlowLayout.RIGHT));
		JPanel buttonPane = new JPanel();
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setActionCommand("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sauveBalise();
				dispose();
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);

	}
	
	protected void sauveBalise() {

		balise.setCode(Integer.parseInt(spinner_code.getValue().toString()));
		balise.setTrouveBonif(rdbtnTrouveBonif.isSelected());
		balise.setTrouvePoints(Integer.parseInt(spinner_trouvePoints.getValue().toString()));
		balise.setTrouveHeure(LocalTime.of(Integer.parseInt(spinner_trouveH.getValue().toString()), Integer.parseInt(spinner_trouveMn.getValue().toString()), Integer.parseInt(spinner_trouveSec.getValue().toString())));
		balise.setPmBonif(rdbtnPmBonif.isSelected());
		balise.setPmPoints(Integer.parseInt(spinner_PmPoints.getValue().toString()));
		balise.setPmHeure(LocalTime.of(Integer.parseInt(spinner_PmH.getValue().toString()), Integer.parseInt(spinner_PmMn.getValue().toString()), Integer.parseInt(spinner_PmSec.getValue().toString())));
	}

}
