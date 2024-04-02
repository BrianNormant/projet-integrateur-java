package composanteGraphique;

import java.awt.Graphics2D;
import java.util.HashMap;

public class Train implements Dessinable {

	private static final HashMap<Integer, Train> TRAINS = new HashMap<>();

	public static Train createOrGet(int id) {
		if (TRAINS.containsKey(id)) {
			return TRAINS.get(id);
		} else {
			return new Train(id);
		}
	}

	public static Train createOrGet(int id, int railId, double pos) {
		return TRAINS.containsKey(id)? 
			TRAINS.get(id) :
			new Train(id, railId, pos);
	}

	private final int id;


	private Rail rail;

	private double pos;

	private Train(int id) {
		this.id = id;
		TRAINS.put(this.id, this);
	}

	private Train(int id, int railId, double pos) {
		this(id);
		this.rail = Rail.getRailIfExists(railId);
		this.pos = pos;
	}

	public void setRail(int railId) {
		this.rail = Rail.getRailIfExists(railId);
	}

	public void setPos(double pos) {
		this.pos = pos;
	}

	public Rail getRail() {
		return rail;
	}

	public int getId() { return id;}
	public double getPos() { return pos;}

	public void dessiner(Graphics2D g2d, double ppm) {

	}
}
