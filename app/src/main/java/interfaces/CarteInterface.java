package interfaces;

import java.awt.Dimension;
import java.awt.Toolkit;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CarteInterface extends JPanel implements Runnable {

	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private Graphique graphique;
	private boolean inStation=false;
	private JCheckBox chckbxPleinEcran;
	private JFrame jframe;
	private JLabel lblPleinEcran;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public CarteInterface(int x, int y, int tailleX, int tailleY) {
		setBounds(100, 100, 1000, 600);
		setLayout(null);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JButton btnReservation = new JButton("Reservation");
		
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservation();
			}
		});
		
		btnReservation.setBounds(274, 11, 122, 23);
		add(btnReservation);
		
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		btnLogout.setBounds(10, 11, 122, 23);
		add(btnLogout);
		
		graphique = new Graphique(5972);
		graphique.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				var behindMouse = graphique.getElementOnPosition(e.getX(), e.getY());

				behindMouse.ifPresent(el -> {
					if (el[2] != null) train(el[2]);
					if (el[0] != null) station(el[0]);
					if (el[1] != null) rail(el[1]);
					
				});
				setInStationFalse();
			}
		});
		graphique.setBounds(10, 62, 980, 318);
		add(graphique);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recherche();
			}
		});
		btnRecherche.setBounds(406, 11, 122, 23);
		add(btnRecherche);
		
		JButton btnTrain = new JButton("Train");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				train();
			}
		});
		btnTrain.setBounds(142, 11, 122, 23);
		add(btnTrain);
		
		chckbxPleinEcran = new JCheckBox("");
		chckbxPleinEcran.setBounds(969, 11, 21, 23);
		chckbxPleinEcran.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				 if (e.getStateChange() == ItemEvent.SELECTED) {
					 jframe.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
                     setBounds(10, 10, (int) screenSize.getWidth() - 10, (int) screenSize.getHeight() - 10);
                     graphique.setBounds(10, 30, (int) screenSize.getWidth()-10, (int) (screenSize.getWidth()*0.33908));
                     chckbxPleinEcran.setBounds((int) screenSize.getWidth()-  chckbxPleinEcran.getWidth()-20, 11, 21, 23);
                     lblPleinEcran.setBounds((int) screenSize.getWidth() - lblPleinEcran.getWidth() - chckbxPleinEcran.getWidth()-20, 11, 66, 23);
                 } else {
                	 jframe.setBounds(100, 100, 1010, 600);
                	 setBounds(100, 100, 1000, 600);
                	 graphique.setBounds(10, 62, 980, 318);
                	 lblPleinEcran.setBounds(897, 11, 66, 23);
                	 chckbxPleinEcran.setBounds(969, 11, 21, 23);
                 }
			}
		});
		
		add(chckbxPleinEcran);
		
		lblPleinEcran = new JLabel("Plein Écran");
		lblPleinEcran.setBounds(897, 11, 66, 23);
		add(lblPleinEcran);
		requestFocusInWindow();

		var t = new Thread(this);
		t.start(); // start running the graphique
	}
	public void reservation() {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("passerReservation", 0, -1);
	}
	public void station() {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("passerStation", 0, -1);
	}
	
	public void station(int id) {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("passerStation", 0, id);
	}
	
	public void rail() {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("passerRail", 0, -1);
	}
	public void rail(int id) {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("passerRail", 0, id);
	}
	public void logout() {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("logout", 0, -1);
	}
	public void recherche() {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("passerRecherche", 0, -1);
	}
	public void train() {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("passerTrain", 0, -1);
	}

	public void train(int id) {
		chckbxPleinEcran.setSelected(false);
		PCS.firePropertyChange("TrainInterface", 0, id);
	}
	
	public void setInStationFalse() {
		this.inStation=false;
	}
	
	public void setJFrame(JFrame jframe) {
		this.jframe = jframe;
	}

	/**
	 * Update the position of each train on the network;
	 * @author Brian Normant
	 */
	@Override
	public void run() {
		while (true) {
			graphique.tick();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
