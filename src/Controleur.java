import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Controleur implements ActionListener, MouseListener{

	public static String REPERTOIRE = "ImagesTest/"; //Images

	public static String DATA = "."; // Data de Serial

	private Modele modele;

	private String nom; //nom récupéré de la vue

	String[] noms; //noms photos du répertoire

	/**
	 * Permet l'ajout des images dans le dico pricipal
	 * @throws IOException
	 */
	public Controleur (Modele m) {

		this.modele = m ;

		File repertoire = new File(Controleur.REPERTOIRE);
		File data = new File(Controleur.DATA);
		String[] ContenuData = data.list();
		Boolean DataEstPresent = false;

		//		//Test si fichier data exsite
		//		for(String s : ContenuData) {
		//			if(s.equals("photo.dat")) {
		//				DataEstPresent= true;
		//
		//			}
		//		}
		//
		//		System.out.println(DataEstPresent);
		//		Iterator<Photo> it = this.modele.images.iterator();
		//		ArrayList<String> StringNom = new ArrayList<String>();
		//		if(DataEstPresent) {
		//			SerialPhoto sp = new SerialPhoto(modele.images);
		//			modele.images=sp.DeserialPhoto();
		//
		//		}


		this.noms = repertoire.list();

		for (int  i=0 ; i < noms.length ; i++) {
			if (! modele.images.contains(noms[i])){

				Recuperateur rim = new Recuperateur(Controleur.REPERTOIRE+noms[i]);

				modele.Mot_clé.put(noms[i], modele.images);

				try {
					m.ajouterImage(rim);
				} catch (IOException e) {

				}

			}
		}

	}



	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JRadioButton){
			System.out.println("JradioButton");
			ImagePanel panl = Interface_miniatures.panl;
			Photo p = panl.photo;
			JRadioButton btn = (JRadioButton)e.getSource();
			if (btn.isSelected()){
				String note_s = btn.getName();
				int note = note_s.charAt(0)-48;
				
				Iterator<Photo> i2 = Modele.images.iterator();
				while(i2.hasNext()){
					Photo tmp = (Photo)i2.next();
					if (tmp.nom.equals(p.nom)){
						
						//TODO Attribuer la note à la photo et faire en sorte qu'elle reste
						
						tmp.setNote(note);
						p.setNote(note);
						Modele.images.remove(tmp);
						Modele.images.add(p);
						break;
					}
					
				}
				
			}
		}
		
		else if(e.getSource() instanceof JCheckBox){
			System.out.println("Jchechbox");
			JCheckBox cb = (JCheckBox)e.getSource();
			nom = cb.getName();
			modele.triage(nom);
		}
		
		else {
			JTextField saisi = (JTextField)e.getSource();
			nom = (String) saisi.getText();
			modele.triage(nom);
		}

		
		//modele.triage(nom);

		System.out.println(modele.demandes);
		
		/*
		Iterator i = modele.images.iterator();

		while(i.hasNext()){
			Photo tmp = (Photo)i.next();
			System.out.println(tmp.nom + " " + tmp.note);
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
