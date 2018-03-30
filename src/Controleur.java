import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Controleur implements ActionListener, MouseListener{

	public static String REPERTOIRE = "Images/";
	
	Modele modele;
	
	public Controleur (Modele m) throws IOException{
		
		this.modele = m ;
		
		File repertoire = new File(Controleur.REPERTOIRE);
		
		String[] noms = repertoire.list();
			
		for (int  i=0 ; i < noms.length ; i++) {
			if (! modele.images.contains(noms[i])){

				Recuperateur rim = new Recuperateur(Controleur.REPERTOIRE+noms[i]);
				
				String date = rim.getFormatedDate();
				MaxColor couleur = rim.getMainColor();
				int taille = rim.obtenirTaille();
				
				modele.mot.put(noms[i], modele.images);
				modele.Couleurs.put(couleur, modele.images);
				modele.Dates.put(date, modele.images);
				modele.Tailles.put(taille, modele.images);
				
				m.ajouterImage(rim);
				
			}
		}
	}

	public void tri(){

	}



	public static void main(String[] args) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
