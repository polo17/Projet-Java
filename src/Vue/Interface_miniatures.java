package Vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
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


import javax.swing.JPanel;


import Controleur.Controleur;
import Controleur.PopupMenuContext;
import Modele.ImagePanel;
import Modele.Modele;
import Modele.Photo;


public class Interface_miniatures extends JPanel implements MouseListener, WindowListener, Observer{

	Modele modele = Interface_panneau.modele;
	Controleur ctrl = Interface_panneau.ctrl;
	public static ImagePanel panl;

	private Object images_triés; //images triées récupérées du modèle
	public boolean drapeau = true;
	public Interface_miniatures() throws IOException {




		// Affichage de toutes les images au lancement de l'applcation
		Iterator<Photo> i = Modele.images.iterator();

		setBackground(new Color(204,229,255));
		//FlowLayout gl = new FlowLayout(FlowLayout.LEADING);

		GridLayout gl = new GridLayout(0,4,10,10);
		setLayout(gl);	


		Photo myPicture;
		while(i.hasNext()){

			Photo tmp = (Photo)i.next();
			PopupMenuContext popup = new PopupMenuContext(tmp);
			myPicture = tmp;
			ImagePanel cases = new ImagePanel(myPicture);
			cases.addMouseListener(this);
			cases.setName(tmp.nom);

			cases.setComponentPopupMenu(popup.popup);
			this.add(cases);}



	}



	/**
	 * Crée une fenêtre pour afficher l'image
	 * appelée à chaque clic sur un panel
	 */

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==1) {
			Interface_miniatures.panl = (ImagePanel)e.getSource();

			try {
				Interface_Agrandie im = new Interface_Agrandie(panl.photo);
				if (drapeau) {
					im.setVisible(true);
					revalidate();}
			} catch (IOException e1) {
				e1.printStackTrace();
			}	
		}




	}



	/**
	 * Fait le tri à chaque modification du modèle
	 */

	public void update(Observable o, Object photo_t) {

		ImagePanel.TAILLE_X=160;
		ImagePanel.TAILLE_Y=90;	

		this.removeAll();

		if (Modele.photos_triés instanceof Set) {

			images_triés =  (Set<Photo>)photo_t;

			if (((Set<Photo>) Modele.photos_triés).isEmpty() && Modele.demandes.isEmpty() && Modele.demande_tag.equals("")) {
				Iterator<Photo> i = Modele.images.iterator();
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
			else if (! ((Set<Photo>)Modele.photos_triés).isEmpty() && ! Modele.demande_tag.equals("")){
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
			else {
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
		else { //si c'est par ordre particulier avec la combo box
			ArrayList<Photo> actu = (ArrayList<Photo>) Modele.photos_triés;
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

		repaint();
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