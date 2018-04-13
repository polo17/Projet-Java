import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Trieur {


	String demande; //type de demande (rouge, moyenne, sans vert, etc ...)

	Set<Photo> photos_t; //set de photo triés à envoyer a la vue

	public Trieur (String d, Set<Photo> f){
		this.demande = d;
		this.photos_t = f;
	}


	/**
	 * 
	 * @return fini, le set de photos triés
	 */
	public Set<Photo> tri(){

		this.photos_t.clear();
		
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
		
		//Ajout des demandes
		if (! Modele.demandes.contains(this.demande)){
			Modele.demandes.add(this.demande);
		}
		else{
			Modele.demandes.remove(this.demande);
		}


		if (Modele.demandes.contains("rouge")){
			photos_t_c.addAll(triCouleur(Color.red));
		}
		
		
		if (Modele.demandes.contains("bleu")){
			photos_t_c.addAll(triCouleur(Color.blue));
		}

		
		if (Modele.demandes.contains("vert")){
			photos_t_c.addAll(triCouleur(Color.green));
		}


		if (Modele.demandes.contains("petites")){
			photos_t_t.addAll(triTaille("Petite"));
		}

		
		if (Modele.demandes.contains("moyennes")){
			try{
				photos_t_t.addAll(triTaille("Moyenne"));
			}
			catch(NullPointerException e){
				System.out.println("Il n'y a pas d'images de taille moyennes");
			}
		}
		
		if (Modele.demandes.contains("grandes")){
			try{
				photos_t_t.addAll(triTaille("Grande"));
			}
			catch(NullPointerException e){
				System.out.println("Il n'y a pas d'images de taille grandes");
			}
		}
		if(! photos_t_c.isEmpty() && ! photos_t_t.isEmpty()){
			photos_t_c.retainAll(photos_t_t);
			this.photos_t.addAll(photos_t_c);
		}
		else if (! photos_t_c.isEmpty())
				this.photos_t.addAll(photos_t_c);
		else this.photos_t.addAll(photos_t_t);
		
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


}