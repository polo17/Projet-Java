import java.io.File;
import java.util.ArrayList;

public class Contrôleur extends Récupérateur{

	Modèle modl;

	public Contrôleur (Modèle m){
		this.modl = m ;
		
		File repertoire = new File("Images");
		
		String[] noms = repertoire.list();
		
		m.images = new ArrayList<>();
	
		
		for (int  i=0 ; i < noms.length ; i++) {
			
			m.images.add(new Photo(m.Nom_Images.get(noms[i]),m.Tailles.get(key),m.Dates.get(key),m.Couleurs), m.Lieux.get(key));
		}
	}

	public void tri(){

	}



	public static void main(String[] args) {

	}

}
