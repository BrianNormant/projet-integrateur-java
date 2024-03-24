package gestionInformation;

import java.util.ArrayList;
import java.util.Random;

import composanteGraphique.Ligne;
import composanteGraphique.Point;

public class ReseauTMP {

	public ReseauTMP() {
		
	}
	
	public static ArrayList<Point> ajouterStation(){
		ArrayList<Point> points = new ArrayList();
		for (int i = 0; i<10; i++) {
			Random random = new Random();
			int x = random.nextInt(5973);
			int y = random.nextInt(2026);
			Point point = new Point((String)"Ville"+i, x, y);
			points.add(point);
		}
		return points;
	}
	
	public static ArrayList<Ligne> ajouterRails(ArrayList<Point> listePoint){
		ArrayList<Ligne> lignes = new ArrayList();
		boolean exit = false;
		for (int i = 0; i<10; i++) {
			Random random = new Random();
			Point point1 = listePoint.get(random.nextInt(10));
			Point point2 = listePoint.get(random.nextInt(10));
			while(!exit) {
				if(point2.getX() == point1.getX() && point2.getY() == point1.getY()) {
					point2 = listePoint.get(random.nextInt(10));
				}else {
					exit = true;
				}
			}
			Ligne ligne = new Ligne(point1, point2);
			lignes.add(ligne);
		}
		return lignes;
	}
}
