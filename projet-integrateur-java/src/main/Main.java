package main;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import interfaces.Carte;
import interfaces.Login;
import interfaces.Reservation;
import interfaces.Station;
import interfaces.Train;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Carte carte = new Carte(getX(),getY(),getWidth(),getHeight());
	private Login login = new Login(getX(),getY(),getWidth(),getHeight());
	private Reservation reservation = new Reservation (getX(),getY(),getWidth(),getHeight());
	private Station station = new Station(getX(),getY(),getWidth(),getHeight());
	private Train train = new Train(getX(),getY(),getWidth(),getHeight());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					frame.login.requestFocusInWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 623);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(login);
		
		login.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "passerCarte":
					login.setVisible(false);
					carte.setVisible(true);
					setContentPane(carte);
					carte.requestFocusInWindow();
					break;
				}
			}
		}
		);
		
		carte.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "passerReservation":
					carte.setVisible(false);
					reservation.setVisible(true);
					setContentPane(reservation);
					reservation.requestFocusInWindow();
					break;
				case "passerStation":
					carte.setVisible(false);
					station.setVisible(true);
					setContentPane(station);
					station.requestFocusInWindow();
					break;
				case "passerTrain":
					carte.setVisible(false);
					train.setVisible(true);
					setContentPane(train);
					train.requestFocusInWindow();
					break;
				case "logout":
					carte.setVisible(false);
					login.setVisible(true);
					setContentPane(login);
					login.requestFocusInWindow();
					break;
				}
			}
		}
		);
		reservation.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "back":
					reservation.setVisible(false);
					carte.setVisible(true);
					setContentPane(carte);
					carte.requestFocusInWindow();
					break;
				}
			}
		}
		);
		station.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "back":
					station.setVisible(false);
					carte.setVisible(true);
					setContentPane(carte);
					carte.requestFocusInWindow();
					break;
				}
			}
		}
		);
		train.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "back":
					train.setVisible(false);
					carte.setVisible(true);
					setContentPane(carte);
					carte.requestFocusInWindow();
					break;
				}
			}
		}
		);
	}

}
