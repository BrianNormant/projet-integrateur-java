package gestionInformation;

import java.util.HashMap;

import composanteGraphique.Point;

public class Station {

	private static final HashMap<Integer, Station> stations = new HashMap<>();

	private final int id;
	private final String name;
	private final int x;
	private final int y;
	
	private Station(int id, String nom, int x, int y) {
		this.id = id;
		this.name = nom;
		this.x = x;
		this.y = y;
		stations.put(id, this);
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
		else {
			System.exit(1);
			return new Station(id, null, 0, 0);
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Point getPoint() {
		return new Point(name, x, y);
	}
}
