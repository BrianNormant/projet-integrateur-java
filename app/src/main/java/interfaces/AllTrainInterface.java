package interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import panelsSpeciaux.PanelAllTrains;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AllTrainInterface extends JPanel {
	
	
private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

private PanelAllTrains panelAllTrains;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}


	/**
	 * Create the panel.
	 */
	public AllTrainInterface(int x, int y, int tailleX, int tailleY) {

		setBounds(100, 100, 610, 600);
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		panelAllTrains = new PanelAllTrains();
		
		JScrollPane scrollPane = new JScrollPane(panelAllTrains);
		scrollPane.setBounds(27, 153, 950, 393);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblTitre = new JLabel("Train en circulation sur le réseau");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTitre.setBounds(27, 75, 950, 50);
		add(lblTitre);
		
	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}


	public void refresh() {
		panelAllTrains = new PanelAllTrains();
		//etant donner que le system demande les trains en mouvement seulement au debut de l'app refresh permet de faire un nouveau get au serveur pour les trains sur le reseau. 
		
	}


}
