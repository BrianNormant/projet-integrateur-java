package composanteGraphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class Rail implements Dessinable {
	
	private static final HashMap<Integer, Rail> railMap = new HashMap<>();

	private final Station con1, con2;
	private final int id;
	private final Color color;

	public static Rail createRail(int con1, int con2, int id, Color color) {
		if (railMap.containsKey(id)) {
			return railMap.get(id);
		} else {
			return new Rail(con1, con2, id, color);
		}
	}

	public static Rail getRailIfExists(int id) {
		if (railMap.containsKey(id)) {
			return railMap.get(id);
		} else return null;
	}
	
	public static Boolean railIfExists(int id) {
		if (railMap.containsKey(id)) {
			return true;
		} else return false;
	}

	private Rail(int con1, int con2, int id, Color color) {
		this.con1 = Station.createOrGetStation(con1);
		this.con2 = Station.createOrGetStation(con2);
		this.id = id;
		this.color = color;

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


	private Stroke stroke = new BasicStroke(2);

	public void fullScreen(boolean f) {
		this.stroke = f? new BasicStroke(5) : new BasicStroke(2);
	}
	// Ces methodes s'occupe  de rendre cette classe dessinable
	protected final int x1;
	protected final int x2;
	protected final int y1;
	protected final int y2;
	public void dessiner(Graphics2D g2d, double ppmX, double ppmY) {
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(-ppmX, ppmY);
		Line2D.Double ligne = new Line2D.Double(x1, y1, x2, y2);
		g2dPrive.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2dPrive.setColor(color);
		g2dPrive.setStroke(stroke);
		g2dPrive.draw(mat.createTransformedShape(ligne));
	}

	public boolean contains(double x, double y) {
		Line2D.Double ligne = new Line2D.Double(x1, y1, x2, y2);
		Rectangle2D.Double cercleClick = new Rectangle2D.Double(x-30,y-30,60,60);
		return ligne.intersects(cercleClick);
	}
}
