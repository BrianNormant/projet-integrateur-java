package composanteGraphique;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Ligne {

	private String nom;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int id;
	private Line2D.Double ligneAttribute;
	
	public Ligne(Point point1, Point point2, int id) {
		this.nom = "rail "+point1.getNom()+point2.getNom();
		this.x1 = point1.getX();
		this.y1 = point1.getY();
		this.x2 = point2.getX();
		this.y2 = point2.getY();
		this.id=id;
		System.out.println(x2-x1 + " " + (y2-y1));
	}
	
	public void dessiner(Graphics2D g2d, double ppm) {
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(-ppm, ppm);
		Line2D.Double ligne = new Line2D.Double(x1, y1, x2, y2);
		ligneAttribute=ligne;
		g2dPrive.setColor(Color.black);
		g2dPrive.setStroke(new BasicStroke(50));
		g2d.draw(mat.createTransformedShape(ligne));
	
	}
	
	public int containsID(double posX, double posY) {
		int id=0;
		Rectangle2D.Double cercleClick = new Rectangle2D.Double(posX-30,posY-30,60,60);
		if(ligneAttribute.intersects(cercleClick)) {
			id=this.id;
		}
		return id;	
	}
}
