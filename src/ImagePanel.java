import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagePanel extends Panel {
	
	private static int TAILLE_X = 160;
	private static int TAILLE_Y = 100;
	
	private BufferedImage imgFond;
	
	public ImagePanel(BufferedImage img) throws IOException {
		super();
		this.imgFond = img;
		this.setBackground(Color.white);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(TAILLE_X, TAILLE_Y);
	}
	
	public void paint(Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(imgFond, 0, 0, TAILLE_X, TAILLE_Y, null);
	}

}
