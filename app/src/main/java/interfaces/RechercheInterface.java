package interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import composanteGraphique.Rail;
import composanteGraphique.Station;
import panelsSpeciaux.PanelTrainVersRails;
import panelsSpeciaux.PanelTrainVersStation;
import panelsSpeciaux.PanelsConteneurRails;
import panelsSpeciaux.PanelsConteneurStation;

public class RechercheInterface extends JPanel {

private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

	private String idAff = "ID";
	private JTextField textId;
	private JComboBox cmbType;
	private boolean exist;
	private JLabel lblErrorIdNotFound;
	private String error="Erreur, l'identifiant entrée ne correspond à aucun";
	private PanelsConteneurRails rail = new PanelsConteneurRails();
	private PanelsConteneurStation stations = new PanelsConteneurStation();
	private JScrollPane scrollPaneStation = new JScrollPane(stations);
	private JScrollPane scrollPaneRail = new JScrollPane(rail);
	private JLabel lblId = new JLabel();
	private JLabel lblType = new JLabel();
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	/**
	 * Create the panel.
	 */
	public RechercheInterface(int x, int y, int tailleX, int tailleY) {

		setBounds(100, 100, 1010, 470);
		setLayout(null);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		cmbType = new JComboBox();
		cmbType.setBackground(Color.WHITE);
		cmbType.setBounds(189, 41, 172, 48);
		cmbType.setModel(new DefaultComboBoxModel<String>(new String[] {"RAIL", "STATION"}));
		add(cmbType);
	
		textId = new JTextField();
		textId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (cmbType.getSelectedIndex() != 0) return;
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
					}
				}
		});
		textId.setText(idAff);
		textId.setFont(textId.getFont().deriveFont(Font.ITALIC));
		textId.setForeground(new Color(128, 128, 128, 128));
		textId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textId.getText().equals(idAff)) {
					textId.setText("");
					textId.setFont(textId.getFont().deriveFont(Font.PLAIN));
					textId.setForeground(new Color(0, 0, 0, 255));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (textId.getText().equals("")) {
					textId.setText(idAff);
					textId.setFont(textId.getFont().deriveFont(Font.ITALIC));
					textId.setForeground(new Color(128, 128, 128, 128));	
				}
			}
		});
		textId.setBounds(550, 44, 172, 44);
		add(textId);
		textId.setColumns(10);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(cmbType.getSelectedIndex()) {
				case 0: //Rail
					exist=Rail.railIfExists(Integer.parseInt(textId.getText()));
					System.out.println("Rail "+ exist);
					if(exist) {
						lblErrorIdNotFound.setText("");
						rail.removeAll();
						stations.removeAll();
						scrollPaneStation.setVisible(false);
						rail.setId(Integer.parseInt(textId.getText()));
						lblType.setText("Rail Id: ");
						lblType.setVisible(true);
						lblId.setText(textId.getText());
						lblId.setVisible(true);
						scrollPaneRail.setVisible(true);
						rail.initialisation();
						rail.revalidate();
						rail.repaint();
						
					}else {
						rail.removeAll();
						stations.removeAll();
						scrollPaneRail.setVisible(false);
						scrollPaneStation.setVisible(false);
						lblType.setText("");
						lblType.setVisible(false);
						lblId.setText(textId.getText());
						lblId.setVisible(false);
						lblErrorIdNotFound.setText(error+" rail.");
					}
					break;
					
				case 1: // Station
					boolean exist = Station.stationIfExists(Integer.parseInt(textId.getText()));
					if (!exist) {
						rail.removeAll();
						stations.removeAll();
						scrollPaneRail.setVisible(false);
						scrollPaneStation.setVisible(false);
						lblType.setText("");
						lblType.setVisible(false);
						lblId.setText(textId.getText());
						lblId.setVisible(false);
						lblErrorIdNotFound.setText(error+"e station.");
					}else {
						rail.removeAll();
						stations.removeAll();
						lblErrorIdNotFound.setText("");
						scrollPaneRail.setVisible(false);
						stations.setId(Integer.parseInt(textId.getText()));
						lblType.setText("Station: ");
						lblType.setVisible(true);
						lblId.setText(Station.createOrGetStation(Integer.parseInt(textId.getText())).getName());
						lblId.setVisible(true);
						stations.revalidate();
						stations.repaint();
						scrollPaneStation.setVisible(true);
						
					}

					break;
				}
				
			}
		});
		btnRecherche.setBounds(774, 45, 172, 41);
		add(btnRecherche);
		
		JLabel lblChoice = new JLabel("Type de Recherche");
		lblChoice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChoice.setBounds(38, 44, 141, 44);
		add(lblChoice);
		
		JLabel lblIdentifiant = new JLabel("Identifiant: ");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdentifiant.setBounds(399, 41, 141, 44);
		add(lblIdentifiant);
		
		lblErrorIdNotFound = new JLabel("");
		lblErrorIdNotFound.setForeground(Color.RED);
		lblErrorIdNotFound.setBounds(48, 99, 708, 23);
		add(lblErrorIdNotFound);
		
		
		
		scrollPaneRail.setBounds(48, 168, 887, 263);
		scrollPaneRail.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneRail.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPaneRail, BorderLayout.CENTER);
		
	
		scrollPaneStation.setBounds(48, 168, 887, 263);
		scrollPaneStation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneStation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPaneStation, BorderLayout.CENTER);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		
		lblType.setBounds(62, 132, 172, 26);
		lblType.setVisible(false);
		add(lblType);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		
		lblId.setBounds(264, 132, 172, 26);
		lblId.setVisible(false);
		add(lblId);
		

	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}
}
