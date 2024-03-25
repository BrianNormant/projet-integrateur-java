package gestionInformation;

import java.util.HashMap;

public class Station {

	private static final HashMap<Integer, Station> stations = new HashMap<>();

	private final int id;
	private final String nom;
	private final int x;
	private final int y;
	
	private Station(int id, String nom, int x, int y) {
		this.id = id;
		this.nom = nom;
		this.x = x;
		this.y = y;

	}

	public static Station createOrGetStation(int id, String nom, int x, int y) {
		if (stations.containsKey(id))
			return stations.get(id);
		else
			return new Station(id, nom, x, y);
	}
	public static Station createOrGetStation(int id) {
		if (stations.containsKey(id))
			return stations.get(id);
		else
			return new Station(id, null, 0, 0);
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
