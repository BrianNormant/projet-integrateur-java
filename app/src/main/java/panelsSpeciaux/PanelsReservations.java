package panelsSpeciaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import api.Users;
import composanteGraphique.Point;
import gestionInformation.Rail;
import gestionInformation.Reservation;
import gestionInformation.Station;
import gestionInformation.Train;

public class PanelsReservations extends JPanel {
	
	private List<Reservation> reservations = new ArrayList<Reservation>() ;

	/**
	 * Create the panel.
	 */
	public PanelsReservations() {
		
		reservations= ajouterReservations();
		System.out.println(reservations.size());
		
		
		setLayout(new GridLayout(reservations.size(),1,0,10)); // Exemple de layout
        for (int i = 1; i <= reservations.size(); i++) {
            add(new PanelReservation());
            
        }
        
       
        setPreferredSize(new Dimension(900,165 * reservations.size()));
        setBackground(Color.BLACK);
    }
	
	public ArrayList<Reservation> ajouterReservations() {
		
		Optional<List<Reservation>> reservations = Users.requestReservations();
		

		if (!reservations.isPresent()) {return new ArrayList<Reservation>();}

		return (ArrayList<Reservation>) reservations.get();
	
			}
		
	

	}


