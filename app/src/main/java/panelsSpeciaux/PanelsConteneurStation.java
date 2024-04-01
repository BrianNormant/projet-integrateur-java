package panelsSpeciaux;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

import javax.swing.JPanel;

import api.RestApi;
import composanteGraphique.Train;

public class PanelsConteneurStation extends JPanel {
	
	private ArrayList<Train> listeTrains = new ArrayList<>();
	private List<PanelTrainVersStation> panels = new ArrayList<PanelTrainVersStation>() ;
	private int id;
	private String token;

	/**
	 * Create the panel.
	 */
	public PanelsConteneurStation() {
		
    }
	
	public ArrayList<Train> ajouterTrains() {
		
		Optional<List<Train>> trains = RestApi.requestTrainsPourStation(token, id);
		

		if (!trains.isPresent()) {return new ArrayList<Train>();}

		return (ArrayList<Train>) trains
			.get()
			.stream()
			.collect(Collectors.toList());
	
			}
	
	public void initialisation() {
		listeTrains= ajouterTrains();
		System.out.println("nb Trains: "+listeTrains.size());
		
		
			for (int i = 0; i < listeTrains.size(); i++) {
				panels.add(new PanelTrainVersStation());
			}
			
			for (int i = 0; i < panels.size(); i++) {
				//panels.get(i).setAll(reservations.get(i).getDate(), reservations.get(i).getPeriod(), reservations.get(i).getCompany_id(), reservations.get(i).getRail().getCon1().getName(), reservations.get(i).getRail().getCon2().getName(), reservations.get(i).getRail().getId());
			}
		
		
		
		setLayout(new GridLayout(panels.size(),1,0,10)); // Exemple de layout
        for (int i = 0; i < panels.size(); i++) {
            add(panels.get(i));
            
        }
        
       
        //setPreferredSize(new Dimension(900,165 * reservations.size()));
        setBackground(Color.BLACK);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
		
	

}


