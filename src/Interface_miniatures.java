import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Interface_miniatures extends Panel implements MouseListener, WindowListener, Observer{
	
	Modele modele = new Modele();
	Controleur ctrl = new Controleur(modele);
	
	private Set<Photo> images_triés; //images triées récupérées du modèle
	public boolean drapeau = true;

	public Interface_miniatures() throws IOException {

		this.setBackground(Color.black);
		GridLayout gl = new GridLayout(0,5,5,5);	
		this.setLayout(gl);

		// Affichage de toutes les images au lancement de l'applcation
		for (int i=0; i < ctrl.noms.length;i++) {
			BufferedImage myPicture = ImageIO.read(new File(ctrl.REPERTOIRE+ctrl.noms[i]));
			ImagePanel cases = new ImagePanel(myPicture);
			cases.addMouseListener(this);
			cases.setName(ctrl.noms[i]);
			this.add(cases);
		}
		this.setVisible(true);	    
	}

	/**
	 * Crée une fenêtre pour afficher l'image
	 * appelée à chaque clic sur un pannel
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		drapeau=true;
		ImagePanel ip = ((ImagePanel)e.getSource());
		JFrame f = new JFrame();
		f.setSize(720,450);
		f.addWindowListener(this);
		ip.TAILLE_X=700;
		ip.TAILLE_Y=400;
		f.add(ip); 

		Interface_etoiles im;
		try {
			im = new Interface_etoiles();
			f.add(im, BorderLayout.SOUTH);
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
		if (drapeau) {
			f.setVisible(true);}		
	}

	/**
	 * Fait le tri à chaque modification du modèle
	 */
	@Override
	public void update(Observable o, Object photo_t) {
	
		ImagePanel.TAILLE_X=160;
		ImagePanel.TAILLE_Y=90;		
		images_triés = ((Set<Photo>) photo_t);

		if (images_triés.isEmpty()) {
			this.removeAll();
			for (int i=0; i < ctrl.noms.length;i++) {
				try {
					BufferedImage myPicture;
					myPicture = ImageIO.read(new File(ctrl.REPERTOIRE+ctrl.noms[i]));
					ImagePanel cases = new ImagePanel(myPicture);
					cases.addMouseListener(this);
					cases.setName(ctrl.noms[i]);
					this.add(cases);		
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			revalidate();
		}
		else {
			this.removeAll();
			Iterator<Photo> i = images_triés.iterator();
			BufferedImage myPicture;
			ImagePanel cases;
			while(i.hasNext()){
				Photo tmp = (Photo)i.next();
				try {
					myPicture = ImageIO.read(new File(ctrl.REPERTOIRE+tmp.nom));
					cases = new ImagePanel(myPicture);
					cases.addMouseListener(this);
					cases.setName(tmp.nom);
					this.add(cases);
				} catch (IOException e) {
					e.printStackTrace();
				}
				revalidate();	
			}
		}
		this.setVisible(true);
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

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		SerialPhoto sp = new SerialPhoto(this.modele.images);
		sp.SerialPhoto();
		drapeau=false;
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}
}