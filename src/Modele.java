import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class Modele extends Observable {

	public Hashtable<String, String> Nom_Images;

	public Hashtable<String, SimpleDateFormat> Dates;

	public Hashtable<String, Integer> Tailles;

	public Hashtable Couleurs;

	public Hashtable Lieux;

	int selectionne;
	ArrayList<Photo> images;

	public Modele() {
		this.Nom_Images = new Hashtable<String, String>();
		this.Dates = new Hashtable<String, SimpleDateFormat>();
		this.Tailles = new Hashtable<String, Integer>();
		//this.Couleurs = new Hashtable<>;
		this.Lieux = new Hashtable<String, String>();
		this.images = new ArrayList<Photo>();
	}

	public void selectionner(int index) {
		this.selectionne=index;
		this.notifyObservers(this.images.get(this.selectionne));
		this.setChanged();
	}

	public void triAlpha(){
		ArrayList<Photo> sorted = new ArrayList<Photo>();
		ArrayList<Photo> sortinprogress = this.images;
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
	}

}