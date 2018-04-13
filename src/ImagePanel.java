import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagePanel extends Panel {
	
	public static int TAILLE_X = 160;
	public static int TAILLE_Y = 90;
	
	public BufferedImage imgFond;
	
	//TODO
	//public ImagePanel(Photo img) throws IOException {
	//this.photo = img;
	//this.imgFond = new BufferedImage(...); 
	public ImagePanel(BufferedImage img) throws IOException {
		super();
		this.imgFond = img;
		this.setBackground(new Color(102,200,255));
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(TAILLE_X+20, TAILLE_Y+50);
	}
	
	public void paint(Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(imgFond, 10, 10, TAILLE_X, TAILLE_Y, null);
	    g.drawString("image", 20, TAILLE_Y+20);
	}

}
