import java.awt.Color;
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
			if (this.demande.equals("rouge")){
				this.photos_t.addAll(triCouleur(Color.red));
			}
			if (this.demande.equals("bleu")){
				this.photos_t.addAll(triCouleur(Color.blue));
			}
			if (this.demande.equals("vert")){
				this.photos_t.addAll(triCouleur(Color.green));
			}

			if (this.demande.equals("petites")){
				this.photos_t.addAll(triTaille("Petite"));
			}
			if (this.demande.equals("moyennes")){
				try{
					this.photos_t.addAll(triTaille("Moyenne"));
				}
				catch(NullPointerException e){
					System.out.println("Il n'y a pas d'images de taille moyennes");
				}
			}
			if (this.demande.equals("grandes")){
					this.photos_t.addAll(triTaille("Grande"));
			}
			
		if (! this.photos_t.isEmpty()){
			//Enlever des photos
			if (this.demande.equals("rougen")){
				this.photos_t.removeAll(triCouleur(Color.red));
			}
			if (this.demande.equals("bleun")){
				this.photos_t.removeAll(triCouleur(Color.blue));
			}
			if (this.demande.equals("vertn")){
				this.photos_t.removeAll(triCouleur(Color.green));
			}

			if (this.demande.equals("petitesn")){
				this.photos_t.removeAll(triTaille("Petite"));
			}
			if (this.demande.equals("moyennesn")){
				this.photos_t.removeAll(triTaille("Moyenne"));
				}
			if (this.demande.equals("grandesn")){
				this.photos_t.removeAll(triTaille("Grande"));
			}
		}
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