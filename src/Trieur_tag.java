import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Trieur_tag {


	public static String demande; //type de demande (rouge, moyenne, nbr étoiles, etc ...)

	ArrayList<Photo> photos_t = new ArrayList<Photo>(); //set de photo triés à envoyer a la vue
	Set set = new HashSet() ; //Set temporaire

	public Trieur_tag (String d){
		this.demande = d;
	}

	/**
	 * 
	 * @return fini, le set de photos triés
	 */
	public ArrayList<Photo> tri(){

		Modele.demandes.remove(Modele.ancienne_demande);

		//Recherche avec le tag :
		Iterator<Photo> i = Modele.images.iterator();
		while (i.hasNext()) {
			Photo tmp = (Photo)i.next();
			for (int j = 0; j<tmp.tags.size();j++) {
				String recherche = tmp.tags.get(j);
				if((recherche).equals(this.demande)) {	
					this.photos_t.add(tmp);
					
				}
			}
		}
		this.set.addAll(photos_t);
		ArrayList liste_finale = new ArrayList(set); //Supprime les doubons de la liste
		return liste_finale;
	}
}