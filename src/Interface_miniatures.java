import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface_miniatures extends Panel{
	
	ArrayList images = new ArrayList<>();	
	
	public Interface_miniatures() throws IOException {

		this.setBackground(Color.GRAY);

		GridLayout gl = new GridLayout(7,0);	
		this.setLayout(gl);
		gl.setHgap(5); 
		gl.setVgap(5);
		
		for (int i=0; i<40;i++) {
			BufferedImage myPicture = ImageIO.read(new File("Images/cellules.jpg"));
			//JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			ImagePanel cases = new ImagePanel(myPicture);
			//picLabel.setSize(50, 30);
			this.add(cases);
		}

		this.setVisible(true);

	
		/*
	    BufferedImage resizedImg = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(new ImageIcon("Images/cellules.jpg").getImage(), 0, 0, 40, 40, null);
	    g2.dispose();
	    */
	    
	}
	/*
	public void paint (Graphics g) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("Images/tigre.jpg"));
			g.drawImage(img,0,0,50,50, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/

}
