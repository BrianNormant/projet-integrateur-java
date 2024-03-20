package composanteGraphique;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Point {

	private int x;
	private int y;
	private String nom;
	private double rayon = 50;
	private Ellipse2D cercle;
	private double ppm;
	
	public Point (String nom, int x, int y) {
		this.nom = nom;
		this.x = x;
		this.y = y;
	}
	
	public void dessiner(Graphics2D g2d, double ppm) {
		this.ppm=ppm;
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(ppm, -ppm);
		cercle = new Ellipse2D.Double(x - (rayon/2), y - (rayon/2), rayon, rayon);
		g2dPrive.setColor(Color.black);
		g2dPrive.draw(mat.createTransformedShape(cercle));
		g2dPrive.setColor(Color.red);
		g2dPrive.fill(mat.createTransformedShape(cercle));
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
	public boolean contains(double posX, double posY) {
		return cercle.contains(posX,posY);
		
	}
}
