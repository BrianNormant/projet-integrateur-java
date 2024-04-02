package composanteGraphique;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;
public class Station implements Dessinable {

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
			return new Station(id, null, 0, 0);
		}
	}
	public static Station getStationIfExists(int id) {
		if (stations.containsKey(id)) {
			return stations.get(id);
		} else return null;
	}
	public static Boolean stationIfExists(int id) {
		if (stations.containsKey(id)) {
			return true;
		} else return false;
	}

	public static Optional<Station> stationByName(String name) {
		var matchs = stations.values()
			.stream()
			.filter(station -> station.getName().equals(name))
			.collect(Collectors.toList());
		if (matchs.size() == 0) return Optional.empty();
		return Optional.of(matchs.get(0));
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
	private static final double SIZE = 50;

	public void dessiner(Graphics2D g2d, double ppm) {
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(-ppm, ppm);
		var cercle = new Ellipse2D.Double(x - (SIZE/2), y - (SIZE/2), SIZE, SIZE);
		g2dPrive.setColor(Color.black);
		g2dPrive.draw(mat.createTransformedShape(cercle));
		g2dPrive.setColor(Color.red);
		g2dPrive.fill(mat.createTransformedShape(cercle));
	}

	public boolean contains(double x, double y) {

		var cercle = new Ellipse2D.Double(this.x - (SIZE/2), this.y - (SIZE/2), SIZE, SIZE);

		if (cercle.contains(x,y)) {
			System.out.printf("Station id:%d contain pos (%.1f, %.1f)\n", id, x, y);
		}
		return cercle.contains(x, y);
	}
}
