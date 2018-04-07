import java.awt.Color;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Set;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.omg.Messaging.SyncScopeHelper;

public class Modele extends Observable {

	public Hashtable<String, HashSet<Photo> > Mot_clé;

	public static Hashtable<String, HashSet<Photo>> Dates;

	public static Hashtable<String, HashSet<Photo>> Tailles;

	public static Hashtable <Color, HashSet<Photo>> Couleurs;

	public Hashtable <String, HashSet<Photo>> Lieux;
	
	public Set<Photo> photos_triés = new HashSet<Photo>();

	int selectionne;
	
	HashSet<Photo> images;

	public Modele() {

		//this.photos_triés = new HashSet<Photo>();
		
		this.Mot_clé = new Hashtable<String, HashSet<Photo>>();
		this.Dates = new Hashtable<String, HashSet<Photo>>();
		this.Tailles = new Hashtable<String, HashSet<Photo>>();
		this.Couleurs = new Hashtable<Color, HashSet<Photo>>();
		this.Lieux = new Hashtable<String, HashSet<Photo>>();
		
		this.images = new HashSet<Photo>();
	}

	public void selectionner(int index) {
		this.selectionne=index;
		//this.notifyObservers(this.images.get(this.selectionne));
		this.setChanged();
	}

	public void ajouterImage(Recuperateur rim) throws IOException {
		Photo photo = new Photo(
				rim.file.getName(),
				rim.obtenirTaille(),
				rim.obtenirDate(),
				rim.obtenirCouleur());
		//Ajout dans la base des photos de l'appli.
		this.images.add(photo);
		
		
		//Ajout dans les différents index
		Color couleur = rim.obtenirCouleur();
		
		if (!this.Couleurs.containsKey(couleur)) 
			this.Couleurs.put(couleur, new HashSet());
		this.Couleurs.get(couleur).add(photo);
		
		
		int taille = rim.obtenirTaille();
		
		if (taille < 300) {
			if (!this.Tailles.containsKey("Petite")){
				this.Tailles.put("Petite", new HashSet());
				}
			this.Tailles.get("Petite").add(photo);
		}
		
		if (351 < taille && taille < 600) {
			if (!this.Tailles.containsKey("Moyenne"));
				
			this.Tailles.get("Moyenne").add(photo);
		}
		
		if (taille > 601) {
			if (!this.Tailles.containsKey("Grande"));
				this.Tailles.put("Grande", new HashSet());
			this.Tailles.get("Grande").add(photo);
		}
		
		String date = rim.obtenirDate();
		
		this.Dates.put(date, this.images);
		if (!this.Dates.containsKey(date))
			this.Dates.put(date, new HashSet());
		this.Dates.get(date).add(photo);
	}
	
	public void triage(String n){
		
		Trieur t = new Trieur(n,this.photos_triés);
		this.photos_triés = t.tri();
		this.setChanged();
		this.notifyObservers(this.photos_triés);
	}
	
	
/*	
	public void triAlpha(){
		ArrayList<Photo> sorted = new ArrayList<Photo>();
		HashSet<Photo> sortinprogress = this.images;
		Photo maxi = sortinprogress.get(0);
		while(sortinprogress.size()!=0) {
			for(int j=1; j<sortinprogress.size(); j++) {
				String s1=sortinprogress.get(j).nom;
				int k =0;
				while(k<5 && maxi.nom.charAt(k)==s1.charAt(k)) {
					k+=1;
				}
				if(maxi.nom.charAt(k)<s1.charAt(k)) {
					maxi.nom = s1;
				}
			}
			sortinprogress.remove(maxi);
			sorted.add(maxi);
			
		}
*/
	
}