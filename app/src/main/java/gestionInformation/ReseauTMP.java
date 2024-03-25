package gestionInformation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import api.Endpoint;
import api.Users;
import composanteGraphique.Ligne;
import composanteGraphique.Point;

public class ReseauTMP {

	public ReseauTMP() {
		
	}
	
	public static ArrayList<Point> ajouterStation() {
		Optional<List<Station>> optional = Users.requestStations();

		if (!optional.isPresent()) {return new ArrayList<Point>();}

		return (ArrayList<Point>) optional
			.get()
			.stream()
			.map(s -> s.getPoint())
			.collect(Collectors.toList());
	}
	
	public static ArrayList<Ligne> ajouterRails(ArrayList<Point> listePoint){
		Optional<List<Rail>> optional = Users.requestRails();

		if (!optional.isPresent()) {return new ArrayList<Ligne>();}

		return (ArrayList<Ligne>) optional
			.get()
			.stream()
			.map(r -> new Ligne(r.getPoint1(), r.getPoint2()))
			.collect(Collectors.toList());
	}
}
