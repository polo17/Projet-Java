import java.io.File;
import java.util.ArrayList;

public class Controleur extends Recuperateur{

	Modele modele;

	
	public Controleur (Modele m){
		this.modele = m ;
		
		File repertoire = new File("Images");
		
		String[] noms = repertoire.list();
	
		for (int  i=0 ; i < noms.length ; i++) {
			m.images.add(new Photo(m.Nom_Images.get(noms[i]),m.Tailles.get(key),m.Dates.get(key),m.Couleurs, m.Lieux.get(key)));
		}
	}

	public void tri(){

	}



	public static void main(String[] args) {

	}

}
