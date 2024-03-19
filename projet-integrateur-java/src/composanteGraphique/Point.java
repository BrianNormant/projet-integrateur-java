package composanteGraphique;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Point {

	private int x;
	private int y;
	private String nom;
	private double rayon = 50;
	
	public Point (String nom, int x, int y) {
		this.nom = nom;
		this.x = x;
		this.y = y;
	}
	
	public void dessiner(Graphics2D g2d, double ppm) {
		Graphics2D g2dPrive = (Graphics2D) g2d.create();
		AffineTransform mat = new AffineTransform();
		mat.scale(ppm, -ppm);
		
	}
}
