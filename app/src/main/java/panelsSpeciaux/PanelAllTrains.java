package panelsSpeciaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JPanel;

import api.RestApi;
import composanteGraphique.Train;
import gestionInformation.Reservation;

public class PanelAllTrains extends JPanel {
	
	private List<Train> allTrains= new ArrayList<Train>() ;
	private List<PanelTrain> panelTrains= new ArrayList<PanelTrain>();
	private GridLayout grid;

	/**
	 * Create the panel.
	 */
	public PanelAllTrains() {
		createPanel();
	}
		
		
		public ArrayList<Train> ajouterTrains() {

			Optional<List<Train>> trains = RestApi.requestTrains();


			if (!trains.isPresent()) {return new ArrayList<Train>();}

			return (ArrayList<Train>) trains
				.get()
				.stream()
				.collect(Collectors.toList());

		}
		
		public void Actualisation() {
			allTrains.clear();
			System.out.println("listeTrainStart "+allTrains.size());
			panelTrains.clear();
			System.out.println("panelTrains "+panelTrains.size());
			removeAll();
			createPanel();	
		}
		
		public void createPanel() {
			allTrains=ajouterTrains();
			System.out.println("NBTRAIN "+allTrains.size());
			for(int i=0;i<allTrains.size();i++) {
				panelTrains.add( new PanelTrain(allTrains.get(i)));
				}
			grid=new GridLayout(panelTrains.size(),1,0,10);
			setLayout(grid); // Exemple de layout
			for (int i = 0; i < panelTrains.size(); i++) {
				add(panelTrains.get(i));

			}
			
			setPreferredSize(new Dimension(900, 230 * allTrains.size()));
			setBackground(Color.BLACK);
		}


		
		
		
		

	}


