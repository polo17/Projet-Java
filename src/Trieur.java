import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Trieur {

	String demande; //type de demande (rouge, moyenne, nbr étoiles, etc ...)

	Set<Photo> photos_t = new HashSet<Photo>(); //set de photo triés à envoyer a la vue

	private String[] trieur = {"ordre alphabétique","couleurs","note","taille","date","lieu"};
	private String[] carac_c = {"rouge","vert","bleu"};
	private String[] carac_t = {"petites","moyennes","grandes"};

	private Color[] cou = {Color.red,Color.green,Color.blue};
	private String[] tail = {"Petite","Moyenne","Grande"};

	private String[] notes = {"1 étoile","2 étoiles","3 étoiles","4 étoiles","5 étoiles"};
	private Integer[] not = {1,2,3,4,5};

	public Trieur (String d){
		this.demande = d;
	}

	/**
	 * 
	 * @return fini, le set de photos triés
	 */
	public Object tri(){

		Modele.demandes.remove(Modele.ancienne_demande);

		Set<Photo> photos_t_c= new HashSet<Photo>();
		Set<Photo> photos_t_t= new HashSet<Photo>();
		Set<Photo> photos_t_e= new HashSet<Photo>();

		//Recherche avec le nom :
		Iterator<Photo> i = Modele.images.iterator();
		while (i.hasNext()) {
			Photo tmp = (Photo)i.next();
			String recherche = tmp.nom;
			recherche = recherche.replace(".jpg", "");
			if((recherche).equals(this.demande)) {	
				this.photos_t.add(tmp);
				return this.photos_t;
			}
		}

		for (int j=0; j < trieur.length ; j++){
			if (Modele.demandes.contains(trieur[j])) Modele.demandes.remove(trieur[j]);
		}


		//Ajout des demandes
		if (! Modele.demandes.contains(this.demande)){
			Modele.demandes.add(this.demande);
		}
		else{
			Modele.demandes.remove(this.demande);
		}

		Modele.demandes.remove("");

		if (Modele.demandes.contains("ordre alphabétique")){
			return triAlpha();
		}
		if (Modele.demandes.contains("taille")) {
			return triGrandeur();
		}
		if (Modele.demandes.contains("note")) {
			return triNotation();
		}
		if (Modele.demandes.contains("date")) {
			return triDatation();
		}

		//Tri en fonction des demandes
		for (int c = 0; c < cou.length ; c++){
			if (Modele.demandes.contains(carac_c[c])){
				photos_t_c.addAll(triCouleur(cou[c]));
			}
		}

		for (int t = 0 ; t < tail.length ; t++){
			if (Modele.demandes.contains(carac_t[t])){
				try{
					photos_t_t.addAll(triTaille(tail[t]));
				}
				catch(NullPointerException e){
					System.out.println("Il n'y a pas d'images de taille "+carac_t[t]);
				}
			}
		}

		for (int n = 0 ; n < notes.length ; n++){
			if (Modele.demandes.contains(notes[n])){
				Set<Photo> notés = triNote(not[n]);
				if (! notés.isEmpty()) photos_t_e.addAll(triNote(not[n]));
				else System.out.println("Il n'y a pas d'images de note " + not[n]);
			}
		}

		if(! photos_t_c.isEmpty() && ! photos_t_t.isEmpty() && ! photos_t_e.isEmpty()){
			photos_t_c.retainAll(photos_t_t);
			photos_t_c.retainAll(photos_t_e);
			this.photos_t.addAll(photos_t_c);
		}

		else if (! photos_t_c.isEmpty() && ! photos_t_t.isEmpty()){
			photos_t_c.retainAll(photos_t_t);
			this.photos_t.addAll(photos_t_c);
		}

		else if (! photos_t_e.isEmpty() && ! photos_t_t.isEmpty()){
			photos_t_t.retainAll(photos_t_e);
			this.photos_t.addAll(photos_t_t);
		}

		else if (! photos_t_c.isEmpty() && ! photos_t_e.isEmpty()){
			photos_t_c.retainAll(photos_t_e);
			this.photos_t.addAll(photos_t_c);
		}

		else if(! photos_t_c.isEmpty())
			this.photos_t.addAll(photos_t_c);

		else if(! photos_t_t.isEmpty())
			this.photos_t.addAll(photos_t_t);	

		else this.photos_t.addAll(photos_t_e);
		
		return this.photos_t;
	}


	/**
	 * 
	 * @param c : Couleur à trier
	 * @return Ensemble de photos de couleur sélectionnée
	 */
	public Set<Photo> triCouleur(Color c){
		Set<Photo> couleurs_triés = Modele.Couleurs.get(c);
		return couleurs_triés;
	}

	/**
	 * 
	 * @param t : Taille à tirer (petite,moyenne,grande)
	 * @return Ensemble de photos avec la taille demandée
	 */
	public Set<Photo> triTaille(String t){
		Set<Photo> tailles_triés = Modele.Tailles.get(t);
		return tailles_triés;
	}

	/**
	 * 
	 * @param d : Date à trier
	 * @return Ensemble des photos avec la date sélectionnée
	 */
	public Set<Photo> triDate(String d){
		Set<Photo> dates_triés = Modele.Dates.get(d);
		return dates_triés;
	}

	/**
	 * 
	 * @param n : Note à tirer
	 * @return Ensemble des photos avec la note sélectionnée
	 */
	public Set<Photo> triNote(int n){
		Set<Photo> notes_triés = new HashSet<Photo>();

		Iterator<Photo> i = Modele.images.iterator();
		while(i.hasNext()){
			Photo tmp = (Photo)i.next();
			if (tmp.note==n)
				notes_triés.add(tmp);
		}
		return notes_triés;
	}

	/**
	 * 
	 * @return Liste des photos triées par ordre alphabétique
	 */
	public ArrayList<Photo> triAlpha(){
		TreeSet<Photo> P;
		P = new TreeSet<Photo>(new ComparerNom());
		P.addAll(Modele.images);
		ArrayList<Photo> fin = new ArrayList<Photo>(P);
		return fin;
	}

	/**
	 * 
	 * @return Liste des photos triés par taille
	 */
	public ArrayList<Photo> triGrandeur(){
		ArrayList<Photo> fin = new ArrayList<Photo>();
		for (int j = 0 ; j < this.tail.length ; j++) {
			fin.addAll(triTaille(this.tail[j]));
		}
		return fin;
	}
	
	/**
	 * 
	 * @return Liste des photos triés par note
	 */
	public ArrayList<Photo> triNotation(){
		ArrayList<Photo> fin = new ArrayList<Photo>();
		for (int n = 1 ; n < 6 ; n++){
			fin.addAll(triNote(n));
		}
		return fin;
	}
	
	/**
	 * 
	 * @return Liste des photos triés par date, si la date est identique, le tri se fait par nom
	 */
	public ArrayList<Photo> triDatation(){
		TreeSet<Photo> P;
		P = new TreeSet<Photo>(new ComparerDate());
		P.addAll(Modele.images);
		ArrayList<Photo> fin = new ArrayList<Photo>(P);
		return fin;
	}

}