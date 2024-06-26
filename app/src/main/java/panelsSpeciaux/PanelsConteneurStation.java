package panelsSpeciaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.json.JSONObject;

import api.RestApi;
import composanteGraphique.Train;

public class PanelsConteneurStation extends JPanel {
	
	private ArrayList<JSONObject> listeTrains = new ArrayList<>();
	private List<PanelTrainVersStation> panels = new ArrayList<PanelTrainVersStation>() ;
	private int id;
	private String token;

	/**
	 * Create the panel.
	 */
	public PanelsConteneurStation() {
		
    }
	
	
	public ArrayList<JSONObject> ajouterTrains() {
		
		Optional<List<JSONObject>> trains = RestApi.requestTrainsPourStation(token, id);
		

		if (!trains.isPresent()) {return new ArrayList<JSONObject>();}

		return (ArrayList<JSONObject>) trains
			.get()
			.stream()
			.collect(Collectors.toList());
	
			}
	
	public void initialisation() {
		reinitialisation();
		listeTrains= ajouterTrains();
		System.out.println("nb Trains: "+listeTrains.size());
		
		for (int i = 0; i < listeTrains.size(); i++) {
				panels.add(new PanelTrainVersStation());
		}
			
		for (int i = 0; i < panels.size(); i++) {
			double pos;
			if (listeTrains.get(i).get("ETA") instanceof BigDecimal bd) {
				pos = bd.doubleValue();
			} else if (listeTrains.get(i).get("ETA") instanceof Double d) {
				pos = d;
			} else if (listeTrains.get(i).get("ETA") instanceof Integer j) {
				pos = j;
			} else {
				pos = 50;
			}

				panels.get(i).setAll(listeTrains.get(i).getInt("id"), pos);
		}
		
		
		
		setLayout(new GridLayout(panels.size(),1,0,10)); // Exemple de layout
        for (int i = 0; i < panels.size(); i++) {
            add(panels.get(i));
            
        }
        
        if(listeTrains.size() == 0) {
     	   setPreferredSize(new Dimension(900,200));
     	   add(new aucunTrain());
        }else {
        	setPreferredSize(new Dimension(900, 100 * listeTrains.size()));
        }
        setBackground(Color.BLACK);
        
	}

	private void reinitialisation() {
		this.removeAll();
		setPreferredSize(new Dimension(900,200));
		listeTrains.clear();
		panels.clear();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		initialisation();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
		
	

}


