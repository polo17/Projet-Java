import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

public class Modèle extends Observable {

	int sélectionnée;
	ArrayList<Photo> images;

	public Modèle() {
		File repertoire=new File("Images");
		String[] noms=repertoire.list();
		this.images=new ArrayList<>();
		for (int  i=0;i<noms.length;i++) {
			this.images.add(new Photo(noms[i]));
		}
	}

	public void selectionner(int index) {
		this.sélectionnée=index;
		this.notifyObservers(this.images.get(this.sélectionnée));
		this.setChanged();
	}

}