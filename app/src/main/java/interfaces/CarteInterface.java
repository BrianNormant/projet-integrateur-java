package interfaces;

import java.awt.EventQueue;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import composanteGraphique.Graphique;
import composanteGraphique.Ligne;
import composanteGraphique.Point;
import gestionInformation.ReseauTMP;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CarteInterface extends JPanel {

	private JPanel contentPane;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private Graphique graphique;
	private boolean inStation=false;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public CarteInterface(int x, int y, int tailleX, int tailleY) {
		
		setBounds(100, 100, 1000, 600);
		setLayout(null);
		
		JButton btnReservation = new JButton("Reservation");
		
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservation();
			}
		});
		
		btnReservation.setBounds(10, 477, 122, 23);
		add(btnReservation);
		
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setBounds(678, 477, 122, 23);
		add(btnLogout);
		
		ArrayList<Point> listePoints = ReseauTMP.ajouterStation();
		ArrayList<Ligne> listeLignes = ReseauTMP.ajouterRails(listePoints);
		graphique = new Graphique(5972, listePoints, listeLignes);
		graphique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("postion X:"+(graphique.getWidth()-e.getX())+" position y:"+ e.getY());
				graphique.getPpm();
				for(int i =0;i<graphique.getPoints().size();i++) {
					int id=graphique.getPoints().get(i).containsID((graphique.getWidth() - e.getX()) / graphique.getPpm(), (e.getY()) / graphique.getPpm());
					if(id>0) {
						inStation=true;
						station(id);
						break;
					}
				}
				if(!inStation) {
					for(int i =0;i<graphique.getLines().size();i++) {
						int id=graphique.getLines().get(i).containsID((graphique.getWidth() - e.getX()) / graphique.getPpm(), (e.getY()) / graphique.getPpm());
						if(id>0) {
							rail(id);
							break;
						}
					}
				}
				setInStationFalse();
			}
		});
		graphique.setBounds(10, 11, 980, 318);
		add(graphique);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recherche();
			}
		});
		btnRecherche.setBounds(409, 477, 122, 23);
		add(btnRecherche);
		
		JButton btnTrain = new JButton("Train");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				train();
			}
		});
		btnTrain.setBounds(541, 478, 122, 23);
		add(btnTrain);
		requestFocusInWindow();
		
		
		
	}
	public void reservation() {
		PCS.firePropertyChange("passerReservation", 0, -1);
	}
	public void station() {
		PCS.firePropertyChange("passerStation", 0, -1);
	}
	
	public void station(int id) {
		PCS.firePropertyChange("passerStation", 0, id);
	}
	
	public void rail() {
		PCS.firePropertyChange("passerRail", 0, -1);
	}
	public void rail(int id) {
		PCS.firePropertyChange("passerRail", 0, id);
	}
	public void logout() {
		PCS.firePropertyChange("logout", 0, -1);
	}
	public void recherche() {
		PCS.firePropertyChange("passerRecherche", 0, -1);
	}
	public void train() {
		PCS.firePropertyChange("passerTrain", 0, -1);
	}
	
	public void setInStationFalse() {
		this.inStation=false;
	}
}
