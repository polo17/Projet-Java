import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Interface_miniatures extends Panel implements MouseListener, WindowListener{
	
	ArrayList images = new ArrayList<>();	
	Modele modele = new Modele();
	Controleur ctrl = new Controleur(modele);
	public boolean drapeau = true;
	public BufferedImage img2;
	
	public Interface_miniatures() throws IOException {

		this.setBackground(Color.GRAY);

		GridLayout gl = new GridLayout(7,0);	
		this.setLayout(gl);
		gl.setHgap(5); 
		gl.setVgap(5);
		
		for (int i=0; i<ctrl.noms.length;i++) {
			BufferedImage myPicture = ImageIO.read(new File(ctrl.REPERTOIRE+ctrl.noms[i]));
			ImagePanel cases = new ImagePanel(myPicture);
			cases.addMouseListener(this);
			cases.setName(ctrl.noms[i]);
			this.add(cases);
		}
		this.setVisible(true);	    
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		drapeau=true;
		ImagePanel ip = ((ImagePanel)e.getSource());
		System.out.println(ip.getName());
		JFrame f = new JFrame();
		f.setSize(700,400);
		f.addWindowListener(this);
		img2=(BufferedImage)ip.imgFond;
		ip.TAILLE_X=700;
		ip.TAILLE_Y=400;
		f.add(ip); 
		f.add(ip);
		if (drapeau) {
		f.setVisible(true);}
		repaint();
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



	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		//System.exit(0);
		drapeau=false;
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
