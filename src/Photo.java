
public class Photo {	
	
	String nom;
	
	int taille;
	
	String date;
	
	MaxColor couleur;
	
	String lieu;

	/**
	 * @param n Nom de la photo
	 * @param t Taille de photo
	 * @param d Date de la photo
	 * @param c Couleur dominante de la photo
	 */
	public Photo(String n, int t, String d, MaxColor c){
		this.nom = n;
		this.taille = t;
		this.date = d;
		this.couleur = c;
		this.lieu = null;
	}
	
	public void setLieu(String l) {
		this.lieu = l;
	}

}
