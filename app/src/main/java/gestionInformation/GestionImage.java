package gestionInformation;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GestionImage {

	public static Image lireImage(String nomFichier) {
		Image img = null;
		URL urlFichier = GestionImage.class.getClassLoader().getResource(nomFichier);
		System.out.println("URL: "+GestionImage.class.getClassLoader() );
		if (urlFichier == null) {
			System.out.println("Fichier d'image introuvable: " + nomFichier);
		} 
		else {
			try {
				img = ImageIO.read(urlFichier);
			} 
			catch (IOException e) {
				System.out.println("Erreur de lecture du fichier d'image: " + nomFichier);
			}
		}
		return(img);
	}
}
