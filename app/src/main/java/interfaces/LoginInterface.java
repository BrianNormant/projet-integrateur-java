package interfaces;

import java.awt.EventQueue;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gestionInformation.GestionImage;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class LoginInterface extends JPanel {

	private JPanel contentPane;
	private JTextField textFieldMDP;
	private JTextField textFieldNom;
	private JLabel lblErreur;
	private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);
	private JPasswordField pwdPassword;
	private String hint = "Password";
	private String username = "Username";
	private JTextField textNom;
	private Image img;
	private String nom;
	private char[] pwd;
	private JLabel lblNonAutorise;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	
	public LoginInterface(int x, int y, int tailleX, int tailleY) {
		img = GestionImage.lireImage("trainLogin.jpg");
		setBounds(100, 100, 730, 450);
		setLayout(null);
		
		JButton btnLogin = new JButton("Sign in");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 128, 255));
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setNom(textNom.getText());
				setPwd(pwdPassword.getPassword());
				login();
			}
		});
		btnLogin.setBounds(480, 313, 217, 64);
		add(btnLogin);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText(hint);
		pwdPassword.setEchoChar((char) 0);
		pwdPassword.setFont(pwdPassword.getFont().deriveFont(Font.ITALIC));
		pwdPassword.setForeground(new Color(128, 128, 128, 128));
		pwdPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (String.valueOf(pwdPassword.getPassword()).equals(hint)) {
					pwdPassword.setText("");
					pwdPassword.setEchoChar('●');
					pwdPassword.setFont(pwdPassword.getFont().deriveFont(Font.PLAIN));
					pwdPassword.setForeground(new Color(0, 0, 0, 255));
                }
			}
			@Override
			public void focusLost(FocusEvent e) {
				 if (String.valueOf(pwdPassword.getPassword()).isEmpty()) {
					 pwdPassword.setText(hint);
					 pwdPassword.setFont(pwdPassword.getFont().deriveFont(Font.ITALIC));
					 pwdPassword.setForeground(new Color(128, 128, 128, 128));
					 pwdPassword.setEchoChar((char) 0);
	                }
			}
		});
		pwdPassword.setToolTipText("");
		pwdPassword.setBounds(480, 229, 217, 38);
		add(pwdPassword);
		
		textNom = new JTextField();
		textNom.setText(username);
		textNom.setFont(textNom.getFont().deriveFont(Font.ITALIC));
		textNom.setForeground(new Color(128, 128, 128, 128));
		textNom.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textNom.getText().equals(username)) {
					textNom.setText("");
					textNom.setFont(textNom.getFont().deriveFont(Font.PLAIN));
					textNom.setForeground(new Color(0, 0, 0, 255));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (textNom.getText().equals("")) {
					textNom.setText(username);
					textNom.setFont(textNom.getFont().deriveFont(Font.ITALIC));
					textNom.setForeground(new Color(128, 128, 128, 128));	
				}
			}
		});
		textNom.setBounds(480, 147, 217, 38);
		add(textNom);
		textNom.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sign in");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		lblNewLabel.setBounds(480, 41, 217, 72);
		add(lblNewLabel);
		
		lblErreur = new JLabel("<html>Wrong username or password.<br/>Try again.</html>");
		lblErreur.setVerticalAlignment(SwingConstants.TOP);
		lblErreur.setVisible(false);
		lblErreur.setForeground(new Color(255, 0, 0));
		lblErreur.setBounds(490, 277, 207, 38);
		add(lblErreur);
		
		lblNonAutorise = new JLabel("<html>You don't have the necessary authorisation<br/>to access this application.</html>");
		lblNonAutorise.setVisible(false);
		lblNonAutorise.setForeground(new Color(255, 0, 0));
		lblNonAutorise.setVerticalAlignment(SwingConstants.TOP);
		lblNonAutorise.setBounds(490, 277, 230, 38);
		add(lblNonAutorise);
		requestFocusInWindow();
		
		
	}
	
	public void login() {
		
		PCS.firePropertyChange("passerCarte", 0, -1);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, 450, getHeight(), this);
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public char[] getPwd() {
		return pwd;
	}


	public void setPwd(char[] cs) {
		this.pwd = cs;
	}
	public void nonAutorise() {
		lblNonAutorise.setVisible(true);
		lblErreur.setVisible(false);
	}


	public void mauvaisIdentifiant() {
		lblErreur.setVisible(true);
		lblNonAutorise.setVisible(false);
	}
}
