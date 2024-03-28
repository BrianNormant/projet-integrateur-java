package interfaces;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import panelsSpeciaux.PanelsReservations;

public class ReservationInterface extends JPanel {

	private JPanel contentPane;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public ReservationInterface(int x, int y, int tailleX, int tailleY) {
		setBounds(100, 100, 898, 582);
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		JLabel lblListeReservations = new JLabel("Liste des Réservations");
		lblListeReservations.setFont(new Font("Tahoma", Font.PLAIN, 29));
		lblListeReservations.setBounds(359, 35, 303, 102);
		add(lblListeReservations);
		
		PanelsReservations panelsReservations = new PanelsReservations();
	
		
		JScrollPane scrollPane = new JScrollPane(panelsReservations);
		scrollPane.setBounds(27, 153, 950, 393);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		
	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}
}
