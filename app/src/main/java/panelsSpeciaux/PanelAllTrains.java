package panelsSpeciaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import api.RestApi;
import composanteGraphique.Train;
import gestionInformation.Reservation;

public class PanelAllTrains extends JPanel {
	
	private List<Train> allTrains= new ArrayList<Train>() ;
	private List<PanelTrain> panelTrains= new ArrayList<PanelTrain>();
	

	/**
	 * Create the panel.
	 */
	public PanelAllTrains() {
		allTrains=ajouterTrains();
		System.out.println("NBTRAIN "+allTrains.size());
		for(int i=0;i<allTrains.size();i++) {
			panelTrains.add( new PanelTrain(allTrains.get(i)));
			}
		
		setLayout(new GridLayout(panelTrains.size(),1,0,10)); // Exemple de layout
		for (int i = 0; i < panelTrains.size(); i++) {
			add(panelTrains.get(i));

		}
		
		setPreferredSize(new Dimension(900, 230 * allTrains.size()));
		setBackground(Color.BLACK);
	}
		
		
		public ArrayList<Train> ajouterTrains() {

			Optional<List<Train>> trains = RestApi.requestTrains();


			if (!trains.isPresent()) {return new ArrayList<Train>();}

			return (ArrayList<Train>) trains
				.get()
				.stream()
				.collect(Collectors.toList());

		}
		
		

	}


