import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;

public class Modèle extends Observable {

	public Hashtable<String, String> Nom_Images;
	
	public Hashtable<String, SimpleDateFormat> Dates;
	
	public Hashtable<String, Integer> Tailles;
	
	public Hashtable Couleurs;
	
	public Hashtable Lieux;
		
	
	int sélectionnée;
	ArrayList<Photo> images;

	public Modèle() {
		this.Nom_Images = new Hashtable<String, String>();
		this.Dates = new Hashtable<String, SimpleDateFormat>();
		this.Tailles = new Hashtable<String, Integer>();
		//this.Couleurs = new Hashtable<>;
		this.Lieux = new Hashtable<String, String>();
		
		File repertoire=new File("Images");
		String[] noms=repertoire.list();
		this.images=new ArrayList<>();
		for (int  i=0;i<noms.length;i++) {
			this.images.add(new Photo(noms[i]));
		}
	}

	public void selectionner(int index) {
		this.sélectionnée=index;
		this.notifyObservers(this.images.get(this.sélectionnée));
		this.setChanged();
	}

}