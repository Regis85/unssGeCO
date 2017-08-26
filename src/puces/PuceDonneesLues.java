package puces;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JTextField;

import net.gecosi.dataframe.SiDataFrame;
import java.awt.Dimension;
import javax.swing.SwingConstants;



@SuppressWarnings("serial")
public class PuceDonneesLues extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JPanel panel_Balises = new JPanel();
	
	private JTextField txtDpart;
	private JTextField txtArrive;
	
	private JLabel lblPuce = new JLabel("Puce : ");

	private static String OK="OK";
	private static String DEL="Supprimer";

	private boolean garde = true;
	private SiDataFrame donneesLues;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			PuceDonneesLues dialog = new PuceDonneesLues();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/*
	 * SiCard 8: 2109613 (Start: 11:19:36  - Finish: 14:54:54 - Check: 10:31:33)
	 * Punches: 8 
	 * 0: 103 11:31:53 - 1: 102 11:37:52 - 2: 112 12:35:17 - 3: 113 12:38:05 - 4: 111 12:44:13 - 5: 107 12:47:54 - 6: 108 13:34:44 - 7: 151 13:40:36 -  
	 *
	 */

	/**
	 * Create the dialog.
	 */
	public PuceDonneesLues() {
		initialise();
	}
	
	public PuceDonneesLues(SiDataFrame dataFrame) {
		initialise();
		this.setLocationRelativeTo(null);
		donneesLues = dataFrame;
		lblPuce.setText("Puce : " + dataFrame.getSiNumber() + " → " + dataFrame.getPunches().length + " balise" + (dataFrame.getPunches().length > 1 ? "s " : " "));
		if(dataFrame.getFinishTime() != SiDataFrame.NO_TIME) {
			txtArrive.setText(tempsLocal(dataFrame.getFinishTime()).toString());
		}
		if(dataFrame.getStartTime() != SiDataFrame.NO_TIME) {
			txtDpart.setText(tempsLocal(dataFrame.getStartTime()).toString());
		}
		
		JPanel panel_Balise[] = new JPanel[dataFrame.getPunches().length];
		JLabel lblBalise[] = new JLabel[dataFrame.getPunches().length];
		JTextField textField_Balise[] = new JTextField[dataFrame.getPunches().length];
				
		for(int i=0; i < dataFrame.getPunches().length;i++) {

			panel_Balise[i] = new JPanel();
			panel_Balises.add(panel_Balise[i]);
			panel_Balise[i].setLayout(new BoxLayout(panel_Balise[i], BoxLayout.X_AXIS));
			panel_Balise[i].setPreferredSize(new Dimension(350, 30));
			panel_Balise[i].setPreferredSize(new Dimension(350, 30));
			
			{
				lblBalise[i] = new JLabel("Balise " + " " + dataFrame.getPunches()[i].code() + " → ");
				panel_Balise[i].add(lblBalise[i]);
			}
			{
				textField_Balise[i] = new JTextField();
				textField_Balise[i].setText(tempsLocal(dataFrame.getPunches()[i].timestamp()).toString());
				//textField_Balise[i].setColumns(10);
				textField_Balise[i].setPreferredSize(new Dimension(100, 20));
				textField_Balise[i].setMinimumSize(new Dimension(100, 20));

				textField_Balise[i].setHorizontalAlignment(SwingConstants.CENTER);
				panel_Balise[i].add(textField_Balise[i]);
				
			}
			
			
		}
	}
	
	public void initialise() {
		this.setModal(true);
		setBounds(100, 100, 450, 511);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setAutoscrolls(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{424, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panelPuce = new JPanel();
			GridBagConstraints gbc_panelPuce = new GridBagConstraints();
			gbc_panelPuce.insets = new Insets(0, 0, 5, 0);
			gbc_panelPuce.gridx = 0;
			gbc_panelPuce.gridy = 0;
			contentPanel.add(panelPuce, gbc_panelPuce);
			{
				
				panelPuce.add(lblPuce);
			}
		}
		{
			JPanel panelDepart = new JPanel();
			panelDepart.setMinimumSize(new Dimension(350, 30));
			panelDepart.setPreferredSize(new Dimension(350, 30));
			GridBagConstraints gbc_panelDepart = new GridBagConstraints();
			gbc_panelDepart.insets = new Insets(0, 0, 5, 0);
			gbc_panelDepart.gridx = 0;
			gbc_panelDepart.gridy = 1;
			contentPanel.add(panelDepart, gbc_panelDepart);
			panelDepart.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblDpart = new JLabel("Départ : ");
				panelDepart.add(lblDpart);
				{
					txtDpart = new JTextField();
					txtDpart.setPreferredSize(new Dimension(150, 20));
					txtDpart.setMinimumSize(new Dimension(150, 20));
					txtDpart.setHorizontalAlignment(SwingConstants.CENTER);
					panelDepart.add(txtDpart);
					txtDpart.setColumns(10);
				}
			}
		}
		{
			JPanel panelArrive = new JPanel();
			panelArrive.setMinimumSize(new Dimension(350, 30));
			panelArrive.setPreferredSize(new Dimension(350, 30));
			GridBagConstraints gbc_panelArrive = new GridBagConstraints();
			gbc_panelArrive.insets = new Insets(0, 0, 5, 0);
			gbc_panelArrive.fill = GridBagConstraints.BOTH;
			gbc_panelArrive.gridx = 0;
			gbc_panelArrive.gridy = 2;
			contentPanel.add(panelArrive, gbc_panelArrive);
			panelArrive.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			JLabel lblArrive = new JLabel("Arrivée :");
			panelArrive.add(lblArrive);
			{
				txtArrive = new JTextField();
				txtArrive.setPreferredSize(new Dimension(150, 20));
				txtArrive.setMinimumSize(new Dimension(150, 20));
				txtArrive.setHorizontalAlignment(SwingConstants.CENTER);
				panelArrive.add(txtArrive);
				txtArrive.setColumns(10);
			}
		}
		{
			//JPanel panel_Balises = new JPanel();
			GridBagConstraints gbc_panel_Balises = new GridBagConstraints();
			gbc_panel_Balises.anchor = GridBagConstraints.NORTH;
			gbc_panel_Balises.insets = new Insets(0, 0, 5, 0);
			gbc_panel_Balises.gridx = 0;
			gbc_panel_Balises.gridy = 3;
			contentPanel.add(panel_Balises, gbc_panel_Balises);
			panel_Balises.setAutoscrolls(true);
		}

		panel_Balises.setLayout(new GridLayout(0, 1));  // any number of rows, 1 column
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setPreferredSize(new Dimension(50, 50));
			buttonPane.setMinimumSize(new Dimension(50, 50));
			FlowLayout fl_buttonPane = new FlowLayout(FlowLayout.CENTER);
			buttonPane.setLayout(fl_buttonPane);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand(OK);
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton delButton = new JButton("Supprimer");
				delButton.setActionCommand(DEL);
				delButton.addActionListener(this);
				buttonPane.add(delButton);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
        if(OK.equals(cmd)) {
        	garde = true;
        	dispose();
        }
        if(cmd.equals(DEL)) {
        	System.out.println("Suppression du temps");
        	new JOptionPane();
			int option = JOptionPane.showConfirmDialog(null, "Vous avez demandé la suppression des temps, êtes-vous sûr ?", "Suppression de l'import", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        	if(option == JOptionPane.OK_OPTION){
        		garde = false;
        		dispose();
        	}
        	
        }
	}
	
	public LocalTime tempsLocal(Long tempsPuce) {
		LocalTime retour = null;
		if(tempsPuce >= 0 && ((tempsPuce) < 86400000)) {
			retour = LocalTime.ofNanoOfDay(tempsPuce*1000000);
		}
		return retour;
	}
	
	public boolean getGarde() {
		return garde;
	}
	
	public SiDataFrame getDonneesLues() {
		return donneesLues;
	}

}
