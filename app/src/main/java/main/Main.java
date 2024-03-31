package main;

import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.LoginError;
import api.Users;
import composanteGraphique.Graphique;
import composanteGraphique.Ligne;
import composanteGraphique.Point;
import gestionInformation.ReseauTMP;
import gestionInformation.Train;
import interfaces.CarteInterface;
import interfaces.LoginInterface;
import interfaces.ReservationInterface;
import interfaces.StationInterface;
import interfaces.TrainInterface;
import io.vavr.control.Either;

public class Main extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CarteInterface carte = new CarteInterface(getX(),getY(),getWidth(),getHeight());
	private LoginInterface login = new LoginInterface(getX(),getY(),getWidth(),getHeight());
	private ReservationInterface reservation = new ReservationInterface (getX(),getY(),getWidth(),getHeight());
	private StationInterface station = new StationInterface(getX(),getY(),getWidth(),getHeight());
	private TrainInterface train = new TrainInterface(getX(),getY(),getWidth(),getHeight());
	private String token;
	private String nom;
	private String pwd;
	private boolean autorisation;
	
	private boolean modeFlemme = true;
	


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
		setBounds(100, 100, 730, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(login);
		if(modeFlemme) {
			flemme();
		}
		
		login.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "passerCarte":
					
					setNom(login.getNom());
					String password = new String(login.getPwd());
					setPwd(password);
					token = setToken();
					if(token == null) {
						login.mauvaisIdentifiant();
						break;
					}
					if(!nom.equals("admin")) {
						login.nonAutorise();
						break;
					}
					setBounds(100, 100, 1000, 600);
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
					setBounds(100, 100, 1000, 580);
					carte.setVisible(false);
					reservation.setVisible(true);
					setContentPane(reservation);
					reservation.requestFocusInWindow();
					break;
				case "passerStation":
					
					
					//a mettre les bound
					
					
					carte.setVisible(false);
					station.setVisible(true);
					setContentPane(station);
					station.requestFocusInWindow();
					break;
				case "passerTrain":
					
					
					//a mettre les bound
					
					
					carte.setVisible(false);
					train.setVisible(true);
					setContentPane(train);
					train.requestFocusInWindow();
					break;
				case "logout":
					setBounds(100, 100, 730, 450);
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
					setBounds(100, 100, 1000, 600);
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
					setBounds(100, 100, 1000, 600);
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
					setBounds(100, 100, 1000, 600);
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String setToken() {
		Either<String, LoginError> result = Users.requestLogin(nom, pwd);
		System.out.println(result);
		
		if(result.isLeft()) {
			return result.getLeft();
		}else {
			System.out.println("il y a eu un erreur en essayant d'avoir le token");
			return null;
		}

	}
	
	private void flemme() {
		setNom("admin");
		setPwd("1234");
		token = setToken();
		setBounds(100, 100, 1000, 600);
		login.setVisible(false);
		carte.setVisible(true);
		setContentPane(carte);
		carte.requestFocusInWindow();
	}
}
