package composanteGraphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class Rail implements Dessinable {
	
	private static final HashMap<Integer, Rail> railMap = new HashMap<>();

	private final Station con1, con2;
	private final int id;

	public static Rail createRail(int con1, int con2, int id) {
		if (railMap.containsKey(id)) {
			return railMap.get(id);
		} else {
			return new Rail(con1, con2, id);
		}
	}

	public static Rail getRailIfExists(int id) {
		if (railMap.containsKey(id)) {
			return railMap.get(id);
		} else return null;
	}
	
	public static Boolean stationIfExists(int id) {
		if (railMap.containsKey(id)) {
			return true;
		} else return false;
	}

	private Rail(int con1, int con2, int id) {
		this.con1 = Station.createOrGetStation(con1);
		this.con2 = Station.createOrGetStation(con2);
		this.id = id;

		this.x1 = this.con1.getX();
		this.y1 = this.con1.getY();
		this.x2 = this.con2.getX();
		this.y2 = this.con2.getY();

		railMap.put(id, this);
	}
	

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o instanceof Rail rail) {
			return this.con1 == rail.con1 && this.con2 == rail.con2 ||
				   this.con1 == rail.con2 && this.con2 == rail.con1;
		}
		return false;
	}

	public Station getCon1() {
		return con1;
	}

	public Station getCon2() {
		return con2;
	}

	public int getId() {
		return id;
	}

	public double getXLen() { return Math.abs(x1 - x2); }
	public double getYLen() { return Math.abs(y1 - y2); }
	public double getLen() {
		return Math.sqrt(
				getXLen() * getXLen() +
				getYLen() * getYLen()
				);
	}


	// Ces methodes s'occupe  de rendre cette classe dessinable
	protected final int x1;
	protected final int x2;
	protected final int y1;
	protected final int y2;
	public void dessiner(Graphics2D g2d, double ppm) {
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(-ppm, ppm);
		Line2D.Double ligne = new Line2D.Double(x1, y1, x2, y2);
		g2dPrive.setColor(Color.black);
		g2dPrive.setStroke(new BasicStroke(50));
		g2d.draw(mat.createTransformedShape(ligne));
	}

	public boolean contains(double x, double y) {
		Line2D.Double ligne = new Line2D.Double(x1, y1, x2, y2);
		Rectangle2D.Double cercleClick = new Rectangle2D.Double(x-30,y-30,60,60);
		return ligne.intersects(cercleClick);
	}
}
