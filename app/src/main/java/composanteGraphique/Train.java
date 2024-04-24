package composanteGraphique;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

import gestionInformation.GestionImage;

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

	private Station lastStation, nextStation;
	private Rail rail;

	private double pos;

	public Station getLastStation() { return lastStation; }
	public Station getNextStation() { return nextStation; }
	

	private Train(int id) {
		this.id = id;
		TRAINS.put(this.id, this);
	}

	private Train(int id, int railId, double pos) {
		this(id);
		this.rail = Rail.getRailIfExists(railId);
		this.pos = pos;
	}

	public void setLastStation(int id) {
		this.lastStation = Station.getStationIfExists(id);
	}
	public void setNextStation(int id) {
		this.nextStation = Station.getStationIfExists(id);
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

	private static final Image IMAGE = GestionImage.lireImage("Train.png");
	private static final double SIZE = 25;

	public int getId() { return id;}
	public double getPos() { return pos;}

	public void dessiner(Graphics2D g2d, double ppmX, double ppmY) {
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();

		double x, y;
		{
			double x_off,y_off;
			if (this.lastStation != rail.getCon1()) {
				x_off = rail.getXLen() * this.pos/100;
				y_off = rail.getYLen() * this.pos/100;
			} else {
				x_off = rail.getXLen() * (100 - this.pos)/100;
				y_off = rail.getYLen() * (100 - this.pos)/100;
			}

			x = rail.getCon1().getX() + x_off * (rail.x1 < rail.x2?1:-1);
			y = rail.getCon1().getY() + y_off * (rail.y1 < rail.y2?1:-1);
		}
		// System.out.printf("Train %d at %.2f %.2f\n", this.id, x, y);

		var rec = new Rectangle2D.Double(x - SIZE/2, y - SIZE/2, SIZE, SIZE);

		// mat.translate(-x, -y);
		mat.scale(-ppmX, ppmY);
		// g2dPrive.drawImage(IMAGE, mat, null);
		g2dPrive.setColor(Color.black);
		g2dPrive.fill(mat.createTransformedShape(rec));
	}

	public boolean contains(double px, double py) {
		double x_off,y_off;
		if (this.lastStation != rail.getCon1()) {
			x_off = rail.getXLen() * this.pos/100;
			y_off = rail.getYLen() * this.pos/100;
		} else {
			x_off = rail.getXLen() * (100 - this.pos)/100;
			y_off = rail.getYLen() * (100 - this.pos)/100;
		}

		var x = rail.getCon1().getX() + x_off * (rail.x1 < rail.x2?1:-1);
		var y = rail.getCon1().getY() + y_off * (rail.y1 < rail.y2?1:-1);

		var rec = new Rectangle2D.Double(x - SIZE/2, y - SIZE/2, SIZE, SIZE);
		return rec.contains(px, py);
	}
}
