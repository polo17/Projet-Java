import java.text.SimpleDateFormat;
import java.util.Hashtable;

public class Modele {
	
	public Hashtable<String, String> Nom_Images;
	
	public Hashtable<String, SimpleDateFormat> Dates;
	
	public Hashtable<String, Integer> Tailles;
	
	public Hashtable Couleurs;
	
	public Hashtable Lieux;
	
	
	
	public Modele(){
		this.Nom_Images = new Hashtable<String, String>();
		this.Dates = new Hashtable<String, SimpleDateFormat>();
		this.Tailles = new Hashtable<String, Integer>();
		//this.Couleurs = new Hashtable<>;
		this.Lieux = new Hashtable<String, String>();
		
	}
	
}
