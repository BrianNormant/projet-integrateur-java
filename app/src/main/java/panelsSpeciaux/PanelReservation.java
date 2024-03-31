package panelsSpeciaux;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PanelReservation extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelReservation() {
		setBorder(new LineBorder(Color.BLACK));
		setBounds(0,0,900,165);
		setLayout(null);
		setBackground(Color.WHITE);
		
		
		JLabel lblDateAff = new JLabel("Date:  ");
		lblDateAff.setBounds(10, 68, 126, 37);
		add(lblDateAff);
		
		JLabel lblPlageHoraireAff = new JLabel("Plage Horaire: ");
		lblPlageHoraireAff.setBounds(10, 118, 126, 37);
		add(lblPlageHoraireAff);
		
		JLabel lblCompanyAff = new JLabel("Nom de la companie: ");
		lblCompanyAff.setBounds(10, 20, 126, 37);
		add(lblCompanyAff);
		
		JLabel lblCompany = new JLabel("...");
		lblCompany.setBounds(158, 20, 126, 37);
		add(lblCompany);
		
		JLabel lblDate = new JLabel("...");
		lblDate.setBounds(158, 68, 126, 37);
		add(lblDate);
		
		JLabel lblPlageHoraire = new JLabel("...");
		lblPlageHoraire.setBounds(158, 118, 126, 37);
		add(lblPlageHoraire);
		
		JLabel lblOrigineAff = new JLabel("Station Connectée 1: ");
		lblOrigineAff.setBounds(311, 32, 126, 37);
		add(lblOrigineAff);
		
		JLabel lblOrigine = new JLabel("...");
		lblOrigine.setBounds(311, 91, 126, 37);
		add(lblOrigine);
		
		JLabel lblDestinationAff = new JLabel("Station Connectée 2: ");
		lblDestinationAff.setBounds(678, 32, 126, 37);
		add(lblDestinationAff);
		
		JLabel lblDestination = new JLabel("...");
		lblDestination.setBounds(678, 91, 126, 37);
		add(lblDestination);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(409, 79, 259, 15);
		add(separator);
		
		JLabel lblRail = new JLabel("Rail Id:  ");
		lblRail.setBounds(495, 32, 126, 37);
		add(lblRail);

	}
}
