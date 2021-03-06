package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

import Modele.Photo;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	public static int TAILLE_X = 160;
	public static int TAILLE_Y = 90; // taille des images miniatures

	public BufferedImage imgFond;
	public Photo photo;

	public ImagePanel(Photo img) throws IOException {

		super();
		this.photo = img;
		this.imgFond = (BufferedImage)img.img; 		
		this.setBackground(new Color(155,205,255));
	}

	public Dimension getPreferredSize() {

		return new Dimension(TAILLE_X+20, TAILLE_Y+50); // taille du panel ImagePanel 
	}

	public void paint(Graphics g) {

		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(imgFond, 10, 10, TAILLE_X, TAILLE_Y, null); // Affichage de l'image
		g.drawString(photo.nom, 20, TAILLE_Y+30); // Affichage du nom de l'image en dessous
	}
}