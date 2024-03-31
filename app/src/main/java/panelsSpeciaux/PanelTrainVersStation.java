package panelsSpeciaux;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;

public class PanelTrainVersStation extends JPanel {
	
	private JLabel lblCompany,lblDate,lblPlageHoraire,lblOrigine,lblDestination,lblRail;
	private JSeparator separator_1;

	/**
	 * Create the panel.
	 */
	public PanelTrainVersStation() {
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
		
		lblCompany = new JLabel("...");
		lblCompany.setBounds(158, 20, 126, 37);
		add(lblCompany);
		
		lblDate = new JLabel("...");
		lblDate.setBounds(158, 68, 126, 37);
		add(lblDate);
		
		lblPlageHoraire = new JLabel("...");
		lblPlageHoraire.setBounds(158, 118, 126, 37);
		add(lblPlageHoraire);
		
		JLabel lblOrigineAff = new JLabel("Station Connectée 1: ");
		lblOrigineAff.setBounds(311, 32, 126, 37);
		add(lblOrigineAff);
		
		lblOrigine = new JLabel("...");
		lblOrigine.setBounds(311, 79, 126, 37);
		add(lblOrigine);
		
		JLabel lblDestinationAff = new JLabel("Station Connectée 2: ");
		lblDestinationAff.setBounds(678, 32, 126, 37);
		add(lblDestinationAff);
		
		lblDestination = new JLabel("...");
		lblDestination.setBounds(678, 79, 126, 37);
		add(lblDestination);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(410, 90, 259, 15);
		add(separator);
		
		lblRail = new JLabel("Rail Id:  ");
		lblRail.setBounds(495, 32, 126, 37);
		add(lblRail);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(257, 10, 14, 140);
		add(separator_1);

	}
	public void setAll(String date, String plage, String company,String station1, String station2, int rail_id) {
		lblCompany.setText(company);
		lblDate.setText(date);
		lblPlageHoraire.setText(plage);
		lblOrigine.setText(station1);
		lblDestination.setText(station2);
		lblRail.setText("Rail Id: "+rail_id);
		
	}
	
}
