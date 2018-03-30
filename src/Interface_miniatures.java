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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface_miniatures extends Panel implements MouseListener{
	
	ArrayList images = new ArrayList<>();	
	
	public Interface_miniatures() throws IOException {

		this.setBackground(Color.GRAY);

		GridLayout gl = new GridLayout(7,0);	
		this.setLayout(gl);
		gl.setHgap(5); 
		gl.setVgap(5);
		
		for (int i=0; i<40;i++) {
			BufferedImage myPicture = ImageIO.read(new File("Images/cellules.jpg"));
			ImagePanel cases = new ImagePanel(myPicture);
			this.add(cases);
		}
		this.setVisible(true);	    
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*
		int x=e.getX();
		int y=e.getY();
		Point p = new Point(x,y);
		*/
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

}
