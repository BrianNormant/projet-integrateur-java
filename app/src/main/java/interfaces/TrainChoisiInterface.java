package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TrainChoisiInterface extends JPanel {
	
	
private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}


	/**
	 * Create the panel.
	 */
	public TrainChoisiInterface(int x, int y, int tailleX, int tailleY) {

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
		
		
	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}

}
