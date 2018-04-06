import java.awt.Color;

public class Photo {	
	
	String nom;
	
	int taille;
	
	String date;
	
	Color couleur;
	
	String lieu;

	/**
	 * @param n Nom de la photo
	 * @param t Taille de photo
	 * @param d Date de la photo
	 * @param color Couleur dominante de la photo
	 */
	public Photo(String n, int t, String d, Color color){
		this.nom = n;
		this.taille = t;
		this.date = d;
		this.couleur = color;
		this.lieu = null;
	}
	
	public void setLieu(String l) {
		this.lieu = l;
	}

}
