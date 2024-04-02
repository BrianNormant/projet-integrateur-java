package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import composanteGraphique.Graphique;

public class CarteInterface extends JPanel implements Runnable {

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
		
		graphique = new Graphique(5972);
		graphique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				var behindMouse = graphique.getElementOnPosition(e.getX(), e.getY());

				if (!behindMouse.isPresent()) {
					setInStationFalse(); // What?
					return;
				}

				if (behindMouse.get().isLeft()) {
					station(behindMouse.get().getLeft());
				} else if (behindMouse.get().isRight()) {
					rail(behindMouse.get().get());
				}
				setInStationFalse();

				// j'ai gardé le comportement d'avant mais j'aimerai comprendre a quoi sert setInStationFalse()
				// Surtout que inStation n'est jamais utiliser
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

		var t = new Thread(this);
		t.start(); // start running the graphique
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

	/**
	 * Update the position of each train on the network;
	 * @author Brian Normant
	 */
	@Override
	public void run() {
		// graphique.updateTrains();
		try {
			Thread.sleep(250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
