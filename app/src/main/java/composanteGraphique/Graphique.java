package composanteGraphique;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import api.RestApi;
import gestionInformation.GestionImage;
import io.vavr.control.Either;

public class Graphique extends JPanel {
	
	private double ppm;
	private double largeur;
	private Image img;


	private ArrayList<Station> stations = new ArrayList<>();
	private ArrayList<Rail> rails = new ArrayList<>();
	private ArrayList<Train> trains = new ArrayList<>();
	private ArrayList<Dessinable> dessinables = new ArrayList<>();
	
	public void tick() {
		// clear last data
		dessinables.removeAll(trains);
		trains.clear();

		// get new data
		trains = (ArrayList<Train>) RestApi.requestTrains().get();
		trains.forEach(t -> RestApi.requestTrain(t.getId()));
		dessinables.addAll(trains);

		// refresh
		this.repaint();
	}

	/**
	 * Se charge de creer un graphique avec la largeur donne a l'element
	 * Puisque le reseaux et fix, les station et rail ne seront recuper qu'a la creation
	 * @param largeur la largeur de l'element graphique dans la fenetre en pixel
	 */
	public Graphique (double largeur) {
		img = GestionImage.lireImage("canada.gif");
		this.largeur = largeur;

		stations = (ArrayList<Station>) RestApi.requestStations().get();
		rails = (ArrayList<Rail>) RestApi.requestRails().get();
		trains = (ArrayList<Train>) RestApi.requestTrains().get();
		
		trains.forEach(t -> RestApi.requestTrain(t.getId()));

		// first draw rail, then station and lastly trains
		dessinables.addAll(rails);
		dessinables.addAll(stations);
		dessinables.addAll(trains);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		AffineTransform mat = new AffineTransform();
		ppm = getWidth() / largeur;
		g2d.translate(getWidth(), 0);
		mat.scale(-ppm, ppm);

		dessinables.forEach(e -> e.dessiner(g2d, ppm));

        /*Rectangle2D.Double test = new Rectangle2D.Double(0, 0, 100, 100);
		g2d.setColor(Color.RED);
		g2d.fill(mat.createTransformedShape(test));*/
	}
	public double getPpm() {
		return ppm;
	}

	/**
	 * renvoi l'id du rail ou de la station sous le curseur a la position x, y
	 */
	public Optional<Integer[]> getElementOnPosition(int x_full, int y_full) {
		double x = (this.getWidth() - x_full)/ppm,
			   y = y_full/ppm;
		var matchedTrains = trains
			.stream()
			.filter(e -> e.contains(x, y))
			.map(Train::getId)
			.collect(Collectors.toList());
		var matchedStations = stations
			.stream()
			.filter(e -> e.contains(x, y))
			.map(Station::getId)
			.collect(Collectors.toList());

		var matchedRails = rails
			.stream()
			.filter(e -> e.contains(x, y))
			.map(Rail::getId)
			.collect(Collectors.toList());

		
		if (matchedTrains.size() > 0)
			return  Optional.of(new  Integer[]  {null,  null,  matchedTrains.get(0)});
		
		else if (matchedStations.size() > 0)
			return  Optional.of(new  Integer[]  {matchedStations.get(0),  null,  null});
		else if (matchedRails.size() > 0)
			return  Optional.of(new  Integer[]  {null,  matchedRails.get(0),  null});
		else 
			return Optional.empty();
	}
}
