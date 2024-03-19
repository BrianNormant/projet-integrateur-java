package composanteGraphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class Ligne {

	private String nom;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	public Ligne(Point point1, Point point2) {
		this.nom = "rail "+point1.getNom()+point2.getNom();
		this.x1 = point1.getX();
		this.y1 = point1.getY();
		this.x2 = point2.getX();
		this.y2 = point2.getY();
	}
	
	public void dessiner(Graphics2D g2d, double ppm) {
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(ppm, -ppm);
		Line2D.Double ligne = new Line2D.Double(x1, y1, x2, y2);
		g2dPrive.setColor(Color.black);
		g2dPrive.setStroke(new BasicStroke(50));
		g2d.draw(mat.createTransformedShape(ligne));
	}
}
