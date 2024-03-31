package interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class RechercheInterface extends JPanel {

private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

	private String idAff = "ID";
	private JTextField textid;
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		PCS.addPropertyChangeListener(listener);
	}

	/**
	 * Create the panel.
	 */
	public RechercheInterface(int x, int y, int tailleX, int tailleY) {

		setBounds(100, 100, 1000, 580);
		setLayout(null);
		
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		add(btnBack);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(189, 41, 172, 48);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"RAIL", "STATION"}));
		add(comboBox);
	
		textid = new JTextField();
		textid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
					}
				}
		});
		textid.setText(idAff);
		textid.setFont(textid.getFont().deriveFont(Font.ITALIC));
		textid.setForeground(new Color(128, 128, 128, 128));
		textid.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textid.getText().equals(idAff)) {
					textid.setText("");
					textid.setFont(textid.getFont().deriveFont(Font.PLAIN));
					textid.setForeground(new Color(0, 0, 0, 255));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (textid.getText().equals("")) {
					textid.setText(idAff);
					textid.setFont(textid.getFont().deriveFont(Font.ITALIC));
					textid.setForeground(new Color(128, 128, 128, 128));	
				}
			}
		});
		textid.setBounds(550, 44, 172, 44);
		add(textid);
		textid.setColumns(10);
		
		JButton btnRecherche = new JButton("Recherche");
		btnRecherche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get type et id pour afficher bon type de panel
			}
		});
		btnRecherche.setBounds(774, 45, 172, 41);
		add(btnRecherche);
		
		JLabel lblChoice = new JLabel("Type de Recherche");
		lblChoice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblChoice.setBounds(38, 44, 141, 44);
		add(lblChoice);
		
		JLabel lblIdentifiant = new JLabel("Identifiant: ");
		lblIdentifiant.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdentifiant.setBounds(399, 41, 141, 44);
		add(lblIdentifiant);
	}
	
	public void back() {
		PCS.firePropertyChange("back", 0, -1);
	}
}
