package composanteGraphique;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Graphique extends JPanel{
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(0, getHeight());
        g2d.scale(1, -1);
	}
}
