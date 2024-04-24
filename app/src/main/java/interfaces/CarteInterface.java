package interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import composanteGraphique.Graphique;

public class CarteInterface extends JPanel implements Runnable {

	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private Graphique graphique;
	private boolean inStation = false;
	private JCheckBox chckbxPleinEcran;
	private JFrame jframe;
	private JLabel lblPleinEcran;
	private JButton btnTrain;
	private JButton btnRecherche;
	private JButton btnLogout;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public CarteInterface(int x, int y, int tailleX, int tailleY, JFrame j) {
		// j.setBounds(1925, 28, 1907, 762);
		this.jframe = j;

		setLayout(new GridBagLayout());
		var gbc = new GridBagConstraints();
		gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
		gbc.weightx = 1; gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		// back btn
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logout();
			}
		});
		// btnLogout.setBounds(10, 11, 122, 23);
		add(btnLogout, gbc);

		gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
		gbc.weightx = 20; gbc.weighty = 20;
		graphique = new Graphique(5973, 2025);
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
		graphique.setPreferredSize(new Dimension(
					(int)(800*3), 
					(int)(304*3)));
		// graphique.setBounds(10, 62, 980, 320);
		add(graphique, gbc);
		// btn reservation, recherche, train.

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JButton btnReservation = new JButton("Reservation");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reservation();
			}
		});
		// btnReservation.setBounds(142, 391, 122, 23);
		gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
		gbc.weightx = 1; gbc.weighty = 1;
		add(btnReservation, gbc);
		
		btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recherche();
			}
		});
		// btnRecherche.setBounds(274, 391, 122, 23);
		gbc.gridx = 1; gbc.gridy = 2; gbc.gridwidth = 1;
		add(btnRecherche, gbc);
		
		btnTrain = new JButton("Train");
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				train();
			}
		});
		// btnTrain.setBounds(10, 391, 122, 23);
		gbc.gridx = 2; gbc.gridy = 2; gbc.gridwidth = 1;
		add(btnTrain, gbc);
	

		chckbxPleinEcran = new JCheckBox("Plein Écran");
		// chckbxPleinEcran.setBounds(969, 11, 21, 23);
		chckbxPleinEcran.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					jframe.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
					// setBounds(10, 10, (int) screenSize.getWidth() - 10, (int) screenSize.getHeight() - 10);
					// graphique.setBounds(10, 30, (int) screenSize.getWidth()-10, (int) (screenSize.getWidth()*0.33908));
					// chckbxPleinEcran.setBounds((int) screenSize.getWidth()-  chckbxPleinEcran.getWidth()-20, 11, 21, 23);
					// lblPleinEcran.setBounds((int) screenSize.getWidth() - lblPleinEcran.getWidth() - chckbxPleinEcran.getWidth()-20, 11, 66, 23);
					// btnTrain.setBounds(10, 700, 122, 23);
					// btnRecherche.setBounds(274, 700, 122, 23);
					// btnReservation.setBounds(142, 700, 122, 23);
					// graphique.fullScreen();
				} else {
					jframe.setBounds(100, 100, 1010, 470);
					// setBounds(100, 100, 1000, 470);
					// graphique.setBounds(10, 62, 980, 318);
					// graphique.fullScreen();
					// lblPleinEcran.setBounds(897, 11, 66, 23);
					// chckbxPleinEcran.setBounds(969, 11, 21, 23);
					// btnTrain.setBounds(10, 391, 122, 23);
					// btnRecherche.setBounds(274, 391, 122, 23);
					// btnReservation.setBounds(142, 391, 122, 23);
				}
			}
		});

		gbc.gridx = 2; gbc.gridy = 0; gbc.gridwidth = 1;
		add(chckbxPleinEcran, gbc);

		requestFocusInWindow();
		new Thread(this).start();
		revalidate();
		jframe.pack();

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
