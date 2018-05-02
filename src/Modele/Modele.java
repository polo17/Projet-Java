package Modele;

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

import Controleur.Recuperateur;
import Controleur.Trieur;
import Controleur.Trieur_tag;

public class Modele extends Observable {

	public static Hashtable<String, HashSet<Photo>> Dates;
	public static Hashtable<String, HashSet<Photo>> Tailles;
	public static Hashtable <Color, HashSet<Photo>> Couleurs;

	public static Object photos_triés = new Object();

	public static HashSet<Photo> images; //Ensemble principal des photos

	public static ArrayList<String> demandes = new ArrayList<String>();
	public static String ancienne_demande;

	public static String demande_tag = "";
	public static File data;
	

	public Modele(){

		this.Dates = new Hashtable<String, HashSet<Photo>>();
		this.Tailles = new Hashtable<String, HashSet<Photo>>();
		this.Couleurs = new Hashtable<Color, HashSet<Photo>>();

		this.images = new HashSet<Photo>();
		this.data=new File("Photo.dat");
	}
	
	public static void addImageToData(Photo p) {
		
		String date = p.getDate();
		Modele.Dates.put(p.date, Modele.images);
		if (!Modele.Dates.containsKey(p.date))
			Modele.Dates.put(p.date, new HashSet());
		Modele.Dates.get(p.date).add(p);

		int taille = p.getTaille();

		if (taille < 150) {
			if (!Modele.Tailles.containsKey("Petite"))
				Modele.Tailles.put("Petite", new HashSet());
			Modele.Tailles.get("Petite").add(p);
		}

		else if (151 < taille && taille < 200) {
			if (!Modele.Tailles.containsKey("Moyenne"))
				Modele.Tailles.put("Moyenne", new HashSet());
			Modele.Tailles.get("Moyenne").add(p);
		}

		else {
			if (!Modele.Tailles.containsKey("Grande"))
				Modele.Tailles.put("Grande", new HashSet());
			Modele.Tailles.get("Grande").add(p);
		}


		if (!Modele.Couleurs.containsKey(p.getColor())) 
			Modele.Couleurs.put(p.getColor(), new HashSet());
		Modele.Couleurs.get(p.getColor()).add(p);
		
		
		
		
	}
	

	public static void ajouterImage(Recuperateur rim) throws IOException{
		Photo photo = new Photo(
				rim.file.getName(),
				rim.obtenirTaille(),
				rim.obtenirDate(),
				rim.obtenirCouleur(),
				rim.obtenirImage(),
				rim.getPath(),
				rim.getFile());
		//Ajout dans la base des photos de l'appli.
		Modele.images.add(photo);

		//Ajout dans les différents index
		Color couleur = rim.obtenirCouleur();

		if (!Modele.Couleurs.containsKey(couleur)) 
			Modele.Couleurs.put(couleur, new HashSet<Photo>());
		Modele.Couleurs.get(couleur).add(photo);

		int taille = rim.obtenirTaille();

		if (taille < 150) {
			if (!Modele.Tailles.containsKey("Petite"))
				Modele.Tailles.put("Petite", new HashSet<Photo>());
			Modele.Tailles.get("Petite").add(photo);
		}

		if (151 < taille && taille < 200) {
			if (!Modele.Tailles.containsKey("Moyenne"))
				Modele.Tailles.put("Moyenne", new HashSet<Photo>());
			Modele.Tailles.get("Moyenne").add(photo);
		}

		if (taille > 200) {
			if (!Modele.Tailles.containsKey("Grande"))
				Modele.Tailles.put("Grande", new HashSet<Photo>());
			Modele.Tailles.get("Grande").add(photo);
		}

		String date = rim.obtenirDate();

		Modele.Dates.put(date, Modele.images);
		if (!Modele.Dates.containsKey(date))
			Modele.Dates.put(date, new HashSet<Photo>());
		Modele.Dates.get(date).add(photo);
	}
	
	public static void removeImage(Photo p) {
		Modele.Couleurs.remove(p.getColor());
		Modele.Tailles.remove(p.getTaille());
		Modele.Dates.remove(p.getDate());
		Modele.images.remove(p);
		
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