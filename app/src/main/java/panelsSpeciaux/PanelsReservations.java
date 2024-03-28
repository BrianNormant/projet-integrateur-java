package panelsSpeciaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelsReservations extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelsReservations() {
		setLayout(new GridLayout(20,1,0,10)); // Exemple de layout
        for (int i = 1; i <= 20; i++) {
            add(new PanelReservation());
            
        }
        
       
        setPreferredSize(new Dimension(900,3500));
        setBackground(Color.BLACK);
    }
	}


