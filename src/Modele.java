import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Observable;

public class Modele extends Observable {

	public Hashtable<String, HashSet<Photo> > Mot_clé;

	public Hashtable<String, HashSet<Photo>> Dates;

	public Hashtable<Integer, HashSet<Photo>> Tailles;

	public Hashtable <MaxColor, HashSet<Photo>> Couleurs;

	public Hashtable <String, HashSet<Photo>> Lieux;

	int selectionne;
	HashSet<Photo> images;

	public Modele() {
		this.Mot_clé = new Hashtable<String, HashSet<Photo>>();
		this.Dates = new Hashtable<String, HashSet<Photo>>();
		this.Tailles = new Hashtable<Integer, HashSet<Photo>>();
		this.Couleurs = new Hashtable<MaxColor, HashSet<Photo>>();
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
				rim.getFormatedDate(),
				rim.getMainColor());
		//Ajout dans la base des photos de l'appli.
		this.images.add(photo);
		
		
		//Ajout dans les diffÃ©rents index
		MaxColor couleur = rim.getMainColor();
		if (!this.Couleurs.containsKey(couleur)) 
			this.Couleurs.put(couleur, new HashSet());
		this.Couleurs.get(couleur).add(photo);
		
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