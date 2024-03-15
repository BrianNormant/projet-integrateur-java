package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 912, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Voir la carte");
		btnNewButton.setBounds(379, 85, 137, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Voir les r\u00E9servation");
		btnNewButton_1.setBounds(379, 119, 137, 23);
		contentPane.add(btnNewButton_1);
	}

}
