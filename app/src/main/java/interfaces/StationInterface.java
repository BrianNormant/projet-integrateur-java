package interfaces;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import api.RestApi;
import composanteGraphique.Station;
import composanteGraphique.Train;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import panelsSpeciaux.PanelsConteneurStation;
import javax.swing.JScrollPane;

public class StationInterface extends JPanel {

	private JPanel contentPane;
	private JList list;
	private JLabel lblNom;
	private JLabel lblId;
	private int id;
	private String token;
	private ArrayList<Train> listeTrains = new ArrayList<>();
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private PanelsConteneurStation panelsConteneurStation;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public StationInterface(int x, int y, int tailleX, int tailleY) {
		setBounds(100, 100, 800, 600);
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		lblNom = new JLabel("...");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNom.setBounds(174, 20, 225, 42);
		add(lblNom);
		
		lblId = new JLabel("...");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblId.setBounds(523, 18, 267, 46);
		add(lblId);
		
		panelsConteneurStation = new PanelsConteneurStation();
		panelsConteneurStation.setBounds(10, 123, 780, 466);
		add(panelsConteneurStation);
		requestFocusInWindow();
	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}
	
	public void ajouterInfoStation(int x, int y) {
		// pas utiliser?
		var ss = RestApi.requestStations()
			.get()
			.stream()
			.filter(s -> s.contains(x,y))
			.map(Station::getName)
			.collect(Collectors.toList());

		if (ss.size() == 0)
			lblNom.setText(ss.get(0));
	}


	public void setId(int id) {
		this.id=id;
		this.lblId.setText("STATION ID: "+ this.id);
		this.lblNom.setText(Station.createOrGetStation(id).getName());
		panelsConteneurStation.setId(id);
	}

	public void setToken(String token) {
		this.token = token;
		panelsConteneurStation.setToken(token);
	}
	
	public void setListe() {
		RestApi.requestTrainsPourStation(token, id);
	}
}
