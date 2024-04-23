package panelsSpeciaux;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class aucunTrain extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public aucunTrain() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AUCUN TRAIN NE PASSE PAR ICI :(");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 880, 178);
		add(lblNewLabel);

	}

}
