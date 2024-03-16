package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Carte extends JFrame {

	private JPanel contentPane;

	
	public Carte() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAllerReserv = new JButton("reservation");
		btnAllerReserv.setBounds(41, 583, 89, 23);
		contentPane.add(btnAllerReserv);
		
		JButton btnAllerStation = new JButton("Station");
		btnAllerStation.setBounds(160, 583, 89, 23);
		contentPane.add(btnAllerStation);
		
		JButton btnNewButton_2 = new JButton("train");
		btnNewButton_2.setBounds(269, 583, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
