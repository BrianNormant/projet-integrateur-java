package panelsSpeciaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import api.RestApi;
import gestionInformation.Reservation;

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

		// Brian Normant -> Dans ce cas n'est-ce pas plus simple que PanelReservation extends ou compose Reservation?
		// Voir que reservation implements java.awt.Panel?
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

		Optional<List<Reservation>> reservations = RestApi.requestReservations();


		if (!reservations.isPresent()) {return new ArrayList<Reservation>();}

		return (ArrayList<Reservation>) reservations
			.get()
			.stream()
			.collect(Collectors.toList());

	}



}


