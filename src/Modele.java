import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Observable;

public class Modele extends Observable {

	public Hashtable<String, HashSet<Photo> > Mot_clé;

	public static Hashtable<String, HashSet<Photo>> Dates;

	public static Hashtable<String, HashSet<Photo>> Tailles;

	public static Hashtable <Color, HashSet<Photo>> Couleurs;

	public Hashtable <String, HashSet<Photo>> Lieux;

	public static Object photos_triés = new Object();

	public static HashSet<Photo> images;

	public static ArrayList<String> demandes = new ArrayList<String>();
	public static String ancienne_demande;

	public static String demande_tag = "";
	public static File data;
	

	public Modele(){

		this.Mot_clé = new Hashtable<String, HashSet<Photo>>();
		this.Dates = new Hashtable<String, HashSet<Photo>>();
		this.Tailles = new Hashtable<String, HashSet<Photo>>();
		this.Couleurs = new Hashtable<Color, HashSet<Photo>>();
		this.Lieux = new Hashtable<String, HashSet<Photo>>();

		this.images = new HashSet<Photo>();
		this.data=new File("Photo.dat");
	}

	public void ajouterImage(Recuperateur rim) throws IOException{
		Photo photo = new Photo(
				rim.file.getName(),
				rim.obtenirTaille(),
				rim.obtenirDate(),
				rim.obtenirCouleur(),
				rim.obtenirImage());
		//Ajout dans la base des photos de l'appli.
		this.images.add(photo);

		//Ajout dans les différents index
		Color couleur = rim.obtenirCouleur();

		if (!this.Couleurs.containsKey(couleur)) 
			this.Couleurs.put(couleur, new HashSet());
		this.Couleurs.get(couleur).add(photo);

		int taille = rim.obtenirTaille();

		if (taille < 150) {
			if (!this.Tailles.containsKey("Petite"))
				this.Tailles.put("Petite", new HashSet());
			this.Tailles.get("Petite").add(photo);
		}

		if (151 < taille && taille < 200) {
			if (!this.Tailles.containsKey("Moyenne"))
				this.Tailles.put("Moyenne", new HashSet());
			this.Tailles.get("Moyenne").add(photo);
		}

		if (taille > 200) {
			if (!this.Tailles.containsKey("Grande"))
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

		Trieur t = new Trieur(n);
		this.photos_triés = t.tri();
		this.setChanged();
		this.notifyObservers(this.photos_triés);
	}

	public void triage_tag(String n) {
		this.demande_tag = n;
		Trieur_tag t = new Trieur_tag(n);
		this.photos_triés = t.tri();
		this.setChanged();
		this.notifyObservers(this.photos_triés);
	}

	public static void serialPhoto() {
		try {
			FileOutputStream fos = new FileOutputStream(Modele.data);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(Modele.images);

			oos.close();
			fos.close();
		}catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données");
		}
	}
	
	public static void deserialPhoto() {
		try {
			FileInputStream fis = new FileInputStream(Modele.data);
			ObjectInputStream ois = new ObjectInputStream(fis);

			Modele.images = (HashSet<Photo>) ois.readObject();

			ois.close();
			fis.close();
		}catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException("Lecture des données impossible");
		}
	}
	
}