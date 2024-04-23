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
import gestionInformation.Reservation;

public class PanelsConteneurRails extends JPanel {
	
	private ArrayList<Reservation> listeTrains = new ArrayList<>();
	private List<PanelTrainVersRails> panels = new ArrayList<PanelTrainVersRails>() ;
	private int id;
	private String token;
	private int nbPanel = 0;

	/**
	 * Create the panel.
	 */
	public PanelsConteneurRails() {
		
    }
	
	public ArrayList<Reservation> ajouterTrains() {
		
		Optional<List<Reservation>> reservations = RestApi.requestReservations();


		if (!reservations.isPresent()) {return new ArrayList<Reservation>();}

		return (ArrayList<Reservation>) reservations
			.get()
			.stream()
			.collect(Collectors.toList());
	
			}
	
	public void initialisation() {
		reinitialisation();
		listeTrains= ajouterTrains();
		System.out.println("nb Trains: "+listeTrains.size());
		System.out.println(id);
		for (int i = 0; i < listeTrains.size(); i++) {
			
			System.out.println(listeTrains.get(i).getRail().getId());
			if(listeTrains.get(i).getRail().getId() == id) {
				panels.add(new PanelTrainVersRails());
				panels.get(nbPanel).setAll(listeTrains.get(i).getCompany_id(), listeTrains.get(i).getDate(), listeTrains.get(i).getPeriod());
				nbPanel = nbPanel+1;
			}
		}
		
		
		
		setLayout(new GridLayout(panels.size(),1,0,10)); // Exemple de layout
        for (int i = 0; i < panels.size(); i++) {
            add(panels.get(i));
            
        }
        
       if(nbPanel == 0) {
    	   setPreferredSize(new Dimension(900,200));
    	   add(new aucunTrain());
       }else {
    	   setPreferredSize(new Dimension(900,200 * nbPanel));
       }
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
		
	private void reinitialisation() {
		this.removeAll();
		setPreferredSize(new Dimension(900,200));
		nbPanel = 0;
		listeTrains.clear();
		panels.clear();
	}

}


