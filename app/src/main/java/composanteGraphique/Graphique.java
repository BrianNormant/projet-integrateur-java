package composanteGraphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Graphique extends JPanel{
	
	private double ppm;
	private double largeur;
	private ArrayList<Point> points = new ArrayList<>();
	private ArrayList<Ligne> lignes = new ArrayList<>();
	
	public Graphique (double largeur, ArrayList<Point> points, ArrayList<Ligne> lignes) {
		this.largeur = largeur;
		this.points = points;
		this.lignes = lignes;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform mat = new AffineTransform();
		ppm = getWidth() / largeur;
		g2d.translate(getWidth(), 0);
		mat.scale(-ppm, ppm);

        for (Ligne ligne : lignes) {
            ligne.dessiner(g2d, ppm);
        }

        for (Point point : points) {
            point.dessiner(g2d, ppm);
        }

        Rectangle2D.Double test = new Rectangle2D.Double(0, 0, 100, 100);
		g2d.setColor(Color.RED);
		g2d.fill(mat.createTransformedShape(test));
	}
	
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	public void setLignes(ArrayList<Ligne> lignes) {
		this.lignes = lignes;
	}
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	public ArrayList<Ligne> getLines() {
		return lignes;
	}
	
	public double getPpm() {
		return ppm;
	}
}
