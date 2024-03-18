package interfaces;

import java.awt.EventQueue;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {

	private JPanel contentPane;
	private JTextField textFieldMDP;
	private JTextField textFieldNom;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public Login(int x, int y, int tailleX, int tailleY) {
		
		setBounds(100, 100, 730, 451);
		setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(187, 107, 313, 195);
		add(btnLogin);
		requestFocusInWindow();
		
		
	}
	
	public void login() {
		PCS.firePropertyChange("passerCarte", 0, -1);
	}
}
