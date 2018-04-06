import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Controleur implements ActionListener, MouseListener{

	public static String REPERTOIRE = "Images/";
	
	Modele modele;
	
<<<<<<< HEAD
	String[] noms;// = repertoire.list();
=======
	String[] noms;
>>>>>>> 8a16cf3561675df155363747bdbbc29becfbdf59
	
	public Controleur (Modele m) throws IOException{
		
		this.modele = m ;
		
		File repertoire = new File(Controleur.REPERTOIRE);
		
		this.noms = repertoire.list();
			
		for (int  i=0 ; i < noms.length ; i++) {
			if (! modele.images.contains(noms[i])){

				Recuperateur rim = new Recuperateur(Controleur.REPERTOIRE+noms[i]);
				
				//String date = rim.getFormatedDate();
				//MaxColor couleur = rim.getMainColor();
				//int taille = rim.obtenirTaille();
				
				modele.Mot_clé.put(noms[i], modele.images);
				//modele.Couleurs.put(couleur, modele.images);
				//modele.Dates.put(date, modele.images);
				//modele.Tailles.put(taille, modele.images);

				m.ajouterImage(rim);
				
			}
		}
		/*
		Set<Photo> test = triCouleur(Color.red);
		Iterator i = test.iterator();
		while(i.hasNext()){
		   Photo tmp = (Photo)i.next();
		   System.out.println(tmp.nom);
		}
		//System.out.println(test.size());
		
		*/
	}

	public Set<Photo> triCouleur(Color c){
		Set<Photo> couleurs_triés = modele.Couleurs.get(c);
		return couleurs_triés;
	}
	
	public Set<Photo> triTaille(String t){
		Set<Photo> tailles_triés = modele.Tailles.get(t);
		return tailles_triés;
	}

	public Set<Photo> triDate(String d){
		Set<Photo> dates_triés = modele.Dates.get(d);
		return dates_triés;
	}


	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
