package interfaces;

import java.awt.EventQueue;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class RailInterface extends JPanel {

	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private JPanel contentPane;
	private int id;
	private JLabel lblRailId; 
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}
	
	public RailInterface(int x, int y, int tailleX, int tailleY) {
		setBounds(100, 100, 904, 608);
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		lblRailId = new JLabel("...");
		lblRailId.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRailId.setBounds(155, 16, 200, 47);
		add(lblRailId);
		requestFocusInWindow();
	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}

	public void setId(int idRail) {
		this.id=idRail;
		this.lblRailId.setText("Rail id: "+id);
		
	}

}
