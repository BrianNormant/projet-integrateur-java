package main;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.LoginError;
import api.RestApi;
import composanteGraphique.Train;
import interfaces.CarteInterface;
import interfaces.LoginInterface;
import interfaces.RailInterface;
import interfaces.RechercheInterface;
import interfaces.ReservationInterface;
import interfaces.StationInterface;
import interfaces.AllTrainInterface;
import interfaces.TrainInterface;
import io.vavr.control.Either;

public class Main extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CarteInterface carte = new CarteInterface(getX(),getY(),getWidth(),getHeight());
	private LoginInterface login = new LoginInterface(getX(),getY(),getWidth(),getHeight());
	private ReservationInterface reservation = new ReservationInterface (getX(),getY(),getWidth(),getHeight());
	private StationInterface station = new StationInterface(getX(),getY(),getWidth(),getHeight());
	private RailInterface rail = new RailInterface(getX(),getY(),getWidth(),getHeight());
	private AllTrainInterface train= new AllTrainInterface(getX(),getY(),getWidth(),getHeight());
	private RechercheInterface recherche= new RechercheInterface(getX(),getY(),getWidth(),getHeight());
	private static String token;
	private static String nom;
	private static String pwd;
	private boolean autorisation;
	

	private static boolean modeFlemme = false;

	static {
		if (modeFlemme) {
			nom = "admin";
			pwd = "1234";
			token = setToken();
		}
	}

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.carte.setJFrame(frame);
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
					setBounds(100, 100, 1010, 600);
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
					setBounds(100, 100, 1000, 400);
					int id=(Integer)evt.getNewValue();
					station.setToken(token);
					station.setId(id);
					carte.setVisible(false);
					station.setVisible(true);
					setContentPane(station);
					station.requestFocusInWindow();
					break;
					
				case "passerRail":
					int idRail=(Integer)evt.getNewValue();
					setBounds(100, 100, 1000, 700);
					rail.setId(idRail);
					carte.setVisible(false);
					rail.setVisible(true);
					setContentPane(rail);
					rail.requestFocusInWindow();
					break;
					
				case "logout":
					setBounds(100, 100, 730, 450);
					carte.setVisible(false);
					login.setVisible(true);
					setContentPane(login);
					login.requestFocusInWindow();
					break;
					
				case "passerRecherche":
					carte.setVisible(false);
					recherche.setVisible(true);
					setContentPane(recherche);
					recherche.requestFocusInWindow();
					break;
					
				case "passerTrain":
					carte.setVisible(false);
					train.setVisible(true);
					setContentPane(train);
					train.requestFocusInWindow();
					train.refresh();
					break;
				case "TrainInterface":
					var idTrain = (Integer) evt.getNewValue();
					carte.setVisible(false);
					var t = Train.createOrGet(idTrain);
					var trainI = new TrainInterface(t);
					trainI.addPropertyChangeListener(evt2 -> {
						System.out.println(evt2.getPropertyName());
						switch(evt2.getPropertyName()) {
						case "back" -> {
							System.out.printf("handled protected String name;\n");
							setBounds(100, 100, 1010, 600);
							trainI.setVisible(false);
							carte.setVisible(true);
							setContentPane(carte);
							carte.requestFocusInWindow();
						}
						}
					});
					setContentPane(trainI);
					trainI.requestFocusInWindow();
					pack();
					break;
				}
			}
		}
		);
		reservation.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "back":
					setBounds(100, 100, 1010, 600);
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
					setBounds(100, 100, 1010, 600);
					station.setVisible(false);
					carte.setVisible(true);
					setContentPane(carte);
					carte.requestFocusInWindow();
					break;
				}
			}
		}
		);
		rail.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "back":
					setBounds(100, 100, 1010, 600);
					rail.setVisible(false);
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
					setBounds(100, 100, 1010, 600);
					train.setVisible(false);
					carte.setVisible(true);
					setContentPane(carte);
					carte.requestFocusInWindow();
					break;
				}
			}
		}
		);
		
		recherche.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent evt) {
				switch (evt.getPropertyName()) {
				case "back":
					setBounds(100, 100, 1100, 600);
					recherche.setVisible(false);
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

	public static String setToken() {
		Either<String, LoginError> result = RestApi.requestLogin(nom, pwd);
		System.out.println(result);
		
		if(result.isLeft()) {
			return result.getLeft();
		}else {
			System.out.println("il y a eu un erreur en essayant d'avoir le token");
			return null;
		}

	}

	
	private void flemme() {
		setBounds(100, 100, 1010, 600);
		login.setVisible(false);
		carte.setVisible(true);
		setContentPane(carte);
		carte.requestFocusInWindow();
	}

	public static String getToken() {
		return Main.token;
	}
}
