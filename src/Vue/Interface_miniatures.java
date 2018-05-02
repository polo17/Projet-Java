package Vue;

import java.awt.Color;
import java.awt.GridLayout;
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

import javax.swing.JPanel;

import Controleur.Controleur;
import Controleur.PopupMenuContext;
import Vue.ImagePanel;
import Modele.Modele;
import Modele.Photo;

public class Interface_miniatures extends JPanel implements MouseListener, WindowListener, Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Modele modele = Interface_panneau.modele;
	Controleur ctrl = Interface_panneau.ctrl;
	PopupMenuContext popup;
	public static ImagePanel panl;

	private Object images_triés; //images triées récupérées du modèle
	public boolean drapeau = true; // ce drapeau sert à la fermeture de l'interface_Agrandie sans la fermeture du programme

	public Interface_miniatures() throws IOException {

		setBackground(new Color(204,229,255));
		//FlowLayout gl = new FlowLayout(FlowLayout.LEADING);

		GridLayout gl = new GridLayout(0,4,10,10);
		setLayout(gl);	

		// Affichage de toutes les images au lancement de l'application
		Iterator<Photo> i = Modele.images.iterator();
		Photo myPicture;
		while(i.hasNext()){
			Photo tmp = (Photo)i.next();
			myPicture = tmp;
			ImagePanel cases = new ImagePanel(myPicture);
			cases.addMouseListener(this);
			cases.setName(tmp.nom);
			this.popup = new PopupMenuContext(myPicture, this);
			cases.setComponentPopupMenu(popup.popup);
			add(cases);
		}
		this.setVisible(true);	    
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// A chaque clic sur une miniature, création d'une nouvelle fenêtre contenant l'image et d'autres fonctions (interface_Agrandie)
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

	@Override
	public void update(Observable o, Object photo_t) {

		ImagePanel.TAILLE_X=160;
		ImagePanel.TAILLE_Y=90;	


		this.removeAll(); // supprime l'ancien contenu pour afficher le nouveau
		
		// Affiche le nouveau contenu selon la recherche demandée (chaque modification du modèle)
		if (modele.photos_triés instanceof Set) {

			images_triés =  (Set<Photo>)photo_t;

			// S'il n'y a pas de recherche effectuée, on affiche toutes les images, sion on affiche la recheche
			if (((Set<Photo>) modele.photos_triés).isEmpty() && modele.demandes.isEmpty() && modele.demande_tag.equals("")) {
				Iterator<Photo> i = modele.images.iterator();

				Photo myPicture;
				while(i.hasNext()){
					try {
						Photo tmp = (Photo)i.next();
						myPicture = tmp;
						ImagePanel cases = new ImagePanel(myPicture);
						cases.addMouseListener(this);
						cases.setName(tmp.nom);
						this.popup = new PopupMenuContext(myPicture,this);
						cases.setComponentPopupMenu(popup.popup);
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
						this.popup = new PopupMenuContext(myPicture, this);
						cases.setComponentPopupMenu(popup.popup);
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
						this.popup = new PopupMenuContext(myPicture, this);
						cases.setComponentPopupMenu(popup.popup);
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
					this.popup = new PopupMenuContext(myPicture, this);
					cases.setComponentPopupMenu(popup.popup);
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
	
	
	public void miseAJour() {
		ImagePanel.TAILLE_X=160;
		ImagePanel.TAILLE_Y=90;	

		this.removeAll();
		
		Iterator<Photo> i = Modele.images.iterator();
		Photo myPicture;
		while(i.hasNext()){
			try {
				Photo tmp = (Photo)i.next();
				myPicture = tmp;
				ImagePanel cases = new ImagePanel(myPicture);
				cases.addMouseListener(this);
				cases.setName(tmp.nom);
				this.popup = new PopupMenuContext(myPicture, this);
				cases.setComponentPopupMenu(popup.popup);
				this.add(cases);		
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		revalidate();
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
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		drapeau=false; // pour la fermeture de la fenêtre agrandie
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