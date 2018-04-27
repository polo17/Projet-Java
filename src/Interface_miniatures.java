import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JScrollPane;

public class Interface_miniatures extends Panel implements MouseListener, WindowListener, Observer{

	Modele modele = Interface_panneau.modele;
	Controleur ctrl = Interface_panneau.ctrl;

	public static ImagePanel panl;

	private Object images_triés; //images triées récupérées du modèle
	public boolean drapeau = true;

	public Interface_miniatures() throws IOException {

		this.setBackground(new Color(204,229,255));
		FlowLayout gl = new FlowLayout(FlowLayout.LEADING);
		this.setLayout(gl);

		// Affichage de toutes les images au lancement de l'applcation
		Iterator<Photo> i = modele.images.iterator();
		Photo myPicture;
		while(i.hasNext()){
			Photo tmp = (Photo)i.next();
			myPicture = tmp;
			ImagePanel cases = new ImagePanel(myPicture);
			cases.addMouseListener(this);
			cases.setName(tmp.nom);
			this.add(cases);
		}
		/*
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(scroll);
		*/
		this.setVisible(true);	    
	}

	/**
	 * Crée une fenêtre pour afficher l'image
	 * appelée à chaque clic sur un pannel
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		this.panl = (ImagePanel)e.getSource();

		try {
			Interface_Agrandie im = new Interface_Agrandie(panl.photo);
			if (drapeau) {
				im.setVisible(true);
				revalidate();}
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}

	/**
	 * Fait le tri à chaque modification du modèle
	 */
	@Override
	public void update(Observable o, Object photo_t) {

		ImagePanel.TAILLE_X=160;
		ImagePanel.TAILLE_Y=90;		

		if (modele.photos_triés instanceof Set) {

			images_triés =  (Set<Photo>)photo_t;

			if (((Set<Photo>) modele.photos_triés).isEmpty() && modele.demandes.isEmpty()) {
				this.removeAll();
				Iterator<Photo> i = modele.images.iterator();
				Photo myPicture;
				while(i.hasNext()){
					try {
						Photo tmp = (Photo)i.next();
						myPicture = tmp;
						ImagePanel cases = new ImagePanel(myPicture);
						cases.addMouseListener(this);
						cases.setName(tmp.nom);
						this.add(cases);		
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				revalidate();
			}
			else if (((Set<Photo>) modele.photos_triés).isEmpty() && ! modele.demandes.isEmpty()) {
				this.removeAll();
			}
			else {
				this.removeAll();
				Iterator<Photo> i = ((Set<Photo>) images_triés).iterator();
				Photo myPicture;
				ImagePanel cases;
				while(i.hasNext()){
					Photo tmp = (Photo)i.next();
					try {
						myPicture = tmp;
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
		}
		else {
			if(((ArrayList<Photo>)modele.photos_triés).isEmpty() && ! modele.demande_tag.equals("")){
				this.removeAll();
			}
			else if (! ((ArrayList<Photo>)modele.photos_triés).isEmpty() && ! modele.demande_tag.equals("")){
				this.removeAll();
				ArrayList<Photo> actu = (ArrayList<Photo>)modele.photos_triés;
				Photo myPicture;
				ImagePanel cases;
				for(int i = 0; i<actu.size();i++) {
					try {
						myPicture = actu.get(i);
						cases = new ImagePanel(myPicture);
						cases.addMouseListener(this);
						cases.setName(actu.get(i).nom);
						this.add(cases);
					} catch (IOException e) {
						e.printStackTrace();
					}
					revalidate();	
				}
			}
			else{
				this.removeAll();
				Iterator<Photo> i = modele.images.iterator();
				Photo myPicture;
				ImagePanel cases;
				while(i.hasNext()){
					Photo tmp = (Photo)i.next();
					try {
						myPicture = tmp;
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