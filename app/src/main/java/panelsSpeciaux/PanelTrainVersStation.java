package panelsSpeciaux;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PanelTrainVersStation extends JPanel {

	
	private JLabel lblId, lblETA;
	/**
	 * Create the panel.
	 */
	public PanelTrainVersStation() {
		setBorder(new LineBorder(Color.BLACK));
		setBounds(0,0,900,90);
		setLayout(null);
		setBackground(Color.WHITE);
		
		JLabel lbltrain = new JLabel("Train: ");
		lbltrain.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbltrain.setBounds(24, 11, 127, 68);
		add(lbltrain);
		
		lblId = new JLabel("...");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblId.setBounds(128, 11, 251, 68);
		add(lblId);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(389, 0, 18, 90);
		add(separator);
		
		JLabel lblArrivee = new JLabel("Arrivée: ");
		lblArrivee.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblArrivee.setBounds(417, 11, 169, 68);
		add(lblArrivee);
		
		lblETA = new JLabel("...");
		lblETA.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblETA.setBounds(572, 11, 307, 68);
		add(lblETA);

	}
	public void setAll(int trainId, double pos) {
		this.lblId.setText(trainId+"");
		this.lblETA.setText(pos+"");
	}
}
