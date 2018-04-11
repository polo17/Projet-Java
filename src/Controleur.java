import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Controleur implements ActionListener, MouseListener{

	public static String REPERTOIRE = "ImagesTest/"; //Images

	public static String DATA = "."; // Data de Serial

	Modele modele;

	private String nom; //nom récupéré de la vue

	String[] noms; //noms photos du répertoire

	/**
	 * Permet l'ajout des images dans le dico pricipal
	 * @throws IOException
	 */
	public Controleur (Modele m) throws IOException{

		this.modele = m ;

		File repertoire = new File(Controleur.REPERTOIRE);
		File data = new File(Controleur.DATA);
		String[] ContenuData = data.list();
		Boolean DataEstPresent = false;

		//Test si fichier data exsite
		for(String s : ContenuData) {
			if(s.equals("photo.dat")) {
				DataEstPresent= true;

			}
		}

		System.out.println(DataEstPresent);
		Iterator<Photo> it = this.modele.images.iterator();
		ArrayList<String> StringNom = new ArrayList<String>();
		if(DataEstPresent) {
			SerialPhoto sp = new SerialPhoto(modele.images);
			modele.images=sp.DeserialPhoto();

		}


		this.noms = repertoire.list();

		for (int  i=0 ; i < noms.length ; i++) {
			if (! modele.images.contains(noms[i])){

				Recuperateur rim = new Recuperateur(Controleur.REPERTOIRE+noms[i]);

				modele.Mot_clé.put(noms[i], modele.images);

				m.ajouterImage(rim);

			}
		}
	}




	/**
	 * Permet de modifier le Set
	 */

	/*
		Iterator i = ajouter.iterator();

		while(i.hasNext()){
		   Photo tmp = (Photo)i.next();

		   photos.add(tmp);
		}
		System.out.println(photos.size());
	 */







	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JRadioButton){
			JRadioButton btn = (JRadioButton)e.getSource();
			if (btn.isSelected()){
				nom = btn.getName();
			}
			else{
				nom = btn.getName()+"n";
			}
			//System.out.println(nom);
		}
		else if(e.getSource() instanceof JComboBox){
			JComboBox cb = (JComboBox)e.getSource();
			nom = (String) cb.getSelectedItem();
		}
		else {
			JTextField saisi = (JTextField)e.getSource();
			nom = (String) saisi.getText();
			
		}

		//System.out.println(nom);
		modele.triage(nom);
		//System.out.println(modele.photos_triés);

		/*
		Iterator i = modele.images.iterator();

		while(i.hasNext()){
		   Photo tmp = (Photo)i.next();
		   System.out.println(tmp.nom);
		}
		 */
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


}
