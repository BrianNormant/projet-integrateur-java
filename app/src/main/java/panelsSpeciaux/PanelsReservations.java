package panelsSpeciaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;


import gestionInformation.Reservation;

public class PanelsReservations extends JPanel {
	
	private List<Reservation> reservations = new ArrayList<Reservation>() ;

	/**
	 * Create the panel.
	 */
	public PanelsReservations() {
		setLayout(new GridLayout(/*reservations.size()*/2,1,0,10)); // Exemple de layout
        for (int i = 1; i <= 2/*reservations.size()*/; i++) {
            add(new PanelReservation());
            
        }
        
       
        setPreferredSize(new Dimension(900,165 * 2/*reservations.size()*/));
        setBackground(Color.BLACK);
    }
	}


