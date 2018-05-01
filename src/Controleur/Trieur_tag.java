package Controleur;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import Modele.Modele;
import Modele.Photo;

public class Trieur_tag {

	public static String demande;

	Set<Photo> photos_t = new HashSet<Photo>(); //set de photo triés à envoyer a la vue

	public Trieur_tag (String d){
		Trieur_tag.demande = d;
	}

	/**
	 * 
	 * @return fini, le set de photos triés
	 */
	public Set<Photo> tri(){

		Modele.demandes.remove(Modele.ancienne_demande);

		//Recherche avec le tag :
		Iterator<Photo> i = Modele.images.iterator();
		while (i.hasNext()) {
			Photo tmp = (Photo)i.next();
			for (int j = 0; j<tmp.tags.size();j++) {
				String recherche = tmp.tags.get(j);
				if((recherche).equals(Trieur_tag.demande)) {	
					this.photos_t.add(tmp);
				}
			}
		}
		return this.photos_t;
	}
}