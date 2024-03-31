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
	private List<PanelReservation> panels = new ArrayList<PanelReservation>() ;

	/**
	 * Create the panel.
	 */
	public PanelsReservations() {
		
		reservations= ajouterReservations();
		System.out.println("nb Reservation: "+reservations.size());
		
		
			for (int i = 0; i < reservations.size(); i++) {
				panels.add(new PanelReservation());
			}
			
			for (int i = 0; i < panels.size(); i++) {
				panels.get(i).setAll(reservations.get(i).getDate(), reservations.get(i).getPeriod(), reservations.get(i).getCompany_id(), reservations.get(i).getRail().getCon1().getName(), reservations.get(i).getRail().getCon2().getName(), reservations.get(i).getRail().getId());
			}
		
		
		
		setLayout(new GridLayout(panels.size(),1,0,10)); // Exemple de layout
        for (int i = 0; i < panels.size(); i++) {
            add(panels.get(i));
            
        }
        
       
        setPreferredSize(new Dimension(900,165 * reservations.size()));
        setBackground(Color.BLACK);
    }
	
	public ArrayList<Reservation> ajouterReservations() {
		
		Optional<List<Reservation>> reservations = Users.requestReservations();
		

		if (!reservations.isPresent()) {return new ArrayList<Reservation>();}

		return (ArrayList<Reservation>) reservations
			.get()
			.stream()
			.collect(Collectors.toList());
	
			}
		
	

	}


