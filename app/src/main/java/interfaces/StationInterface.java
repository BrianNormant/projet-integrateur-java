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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import composanteGraphique.Point;
import gestionInformation.ReseauTMP;

import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class StationInterface extends JPanel {

	private JPanel contentPane;
	private JList list;
	private JLabel lblNom;
	private int id;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public StationInterface(int x, int y, int tailleX, int tailleY) {
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
		
		JList listTrains = new JList();
		listTrains.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		listTrains.setBounds(34, 72, 539, 463);
		add(listTrains);
		
		lblNom = new JLabel("New label");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNom.setBounds(154, 19, 279, 42);
		add(lblNom);
		requestFocusInWindow();
	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}
	
	public void ajouterInfoStation(int x, int y) {
		ArrayList<Point> listePoints = ReseauTMP.ajouterStation();
		
		for(Point point: listePoints) {
			if(point.contains(x, y)) {
				lblNom.setText(point.getNom());
				
			}
		}
	}


	public void setId(int id) {
		this.id=id;
		this.lblNom.setText("STATION ID: "+ this.id);
		
	}
}
