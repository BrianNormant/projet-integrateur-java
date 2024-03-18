package interfaces;

import java.awt.EventQueue;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Carte extends JPanel {

	private JPanel contentPane;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public Carte(int x, int y, int tailleX, int tailleY) {
		
		setBounds(100, 100, 1000, 511);
		setLayout(null);
		
		JButton btnReservation = new JButton("Reservation");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservation();
			}
		});
		btnReservation.setBounds(418, 87, 122, 23);
		add(btnReservation);
		
		JButton btnStation = new JButton("Station");
		btnStation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				station();
			}
		});
		btnStation.setBounds(418, 135, 122, 23);
		add(btnStation);
		
		JButton btnTrain = new JButton("Train");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				train();
			}
		});
		btnTrain.setBounds(418, 179, 122, 23);
		add(btnTrain);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setBounds(418, 227, 122, 23);
		add(btnLogout);
		requestFocusInWindow();
		
		
		
	}
	
	public void reservation() {
		PCS.firePropertyChange("passerReservation", 0, -1);
	}
	public void station() {
		PCS.firePropertyChange("passerStation", 0, -1);
	}
	public void train() {
		PCS.firePropertyChange("passerTrain", 0, -1);
	}
	public void logout() {
		PCS.firePropertyChange("logout", 0, -1);
	}
}
