package panelsSpeciaux;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelTrainVersRails extends JPanel {

	
	private JLabel lblNom, lblDate, lblPlage;
	/**
	 * Create the panel.
	 */
	public PanelTrainVersRails() {
		setBorder(new LineBorder(Color.BLACK));
		setBounds(0,0,900,200);
		setLayout(null);
		setBackground(Color.WHITE);
		
		JLabel lbltrain = new JLabel("Compagnie: ");
		lbltrain.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltrain.setBounds(10, 63, 127, 68);
		add(lbltrain);
		
		lblNom = new JLabel("...");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNom.setBounds(147, 63, 228, 68);
		add(lblNom);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(328, 0, 18, 204);
		add(separator);
		
		JLabel lblArrivee = new JLabel("date d'arrivée: ");
		lblArrivee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblArrivee.setBounds(356, 11, 169, 68);
		add(lblArrivee);
		
		lblDate = new JLabel("...");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDate.setBounds(535, 11, 334, 68);
		add(lblDate);
		
		JLabel lblNewLabel = new JLabel("Plage d'arrivée:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(356, 117, 181, 76);
		add(lblNewLabel);
		
		lblPlage = new JLabel("...");
		lblPlage.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlage.setBounds(535, 117, 334, 76);
		add(lblPlage);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 198, 900, 2);
		add(separator_1);

	}
	public void setAll(String nomCompagnie, String date, String plage) {
		this.lblNom.setText(nomCompagnie+"");
		this.lblDate.setText(date+"");
		this.lblPlage.setText(plage+"");
	}
}
