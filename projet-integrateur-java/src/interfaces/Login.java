package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldMDP;
	private JTextField textFieldNom;

	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnConnecter = new JButton("connecter");
		btnConnecter.setBounds(163, 168, 89, 23);
		contentPane.add(btnConnecter);
		
		textFieldMDP = new JTextField();
		textFieldMDP.setBounds(226, 137, 86, 20);
		contentPane.add(textFieldMDP);
		textFieldMDP.setColumns(10);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(226, 97, 86, 20);
		contentPane.add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblNom = new JLabel("nom d'utilisateur:");
		lblNom.setBounds(120, 100, 96, 14);
		contentPane.add(lblNom);
		
		JLabel lblMDP = new JLabel("mot de passe:");
		lblMDP.setBounds(120, 140, 96, 14);
		contentPane.add(lblMDP);
	}
}
