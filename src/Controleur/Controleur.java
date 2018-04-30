package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Modele.ImagePanel;
import Modele.Modele;
import Modele.Photo;
import Vue.Interface_miniatures;
import Vue.Interface_panneau;

public class Controleur implements ActionListener, MouseListener{

	public static String REPERTOIRE = "ImagesTest/"; //Images


	private Modele modele;

	private String nom; //nom récupéré de la vue
	private String nom_tri = "ordre alphabétique";

	String[] noms; //noms photos du répertoire

	/**
	 * Permet l'ajout des images dans le dico pricipal
	 * @throws IOException
	 */
	public Controleur (Modele m) {

		this.modele = m ;
		File data = new File(".");
		String[] ContenuData = data.list();
		Boolean DataEstPresent = false;
		File repertoire = new File(Controleur.REPERTOIRE);

		for(String s : ContenuData) {
			if(s.equals("Photo.dat")) {
				DataEstPresent= true;
			}
		}

		System.out.println(DataEstPresent);
/**
 * Deserialisation
 */
		if(DataEstPresent) {
			System.out.println("Chargement des images depuis le fichier .dat");
			Modele.deserialPhoto();
			Iterator<Photo> it = Modele.images.iterator();
			while(it.hasNext()) {
				Photo myCurrentPhoto = it.next();
				myCurrentPhoto.toBuffered();

				String date = myCurrentPhoto.getDate();
				Modele.Dates.put(myCurrentPhoto.date, Modele.images);
				if (!Modele.Dates.containsKey(myCurrentPhoto.date))
					Modele.Dates.put(myCurrentPhoto.date, new HashSet());
				Modele.Dates.get(myCurrentPhoto.date).add(myCurrentPhoto);

				int taille = myCurrentPhoto.getTaille();

				if (taille < 150) {
					if (!Modele.Tailles.containsKey("Petite"))
						Modele.Tailles.put("Petite", new HashSet());
					Modele.Tailles.get("Petite").add(myCurrentPhoto);
				}

				else if (151 < taille && taille < 200) {
					if (!Modele.Tailles.containsKey("Moyenne"))
						Modele.Tailles.put("Moyenne", new HashSet());
					Modele.Tailles.get("Moyenne").add(myCurrentPhoto);
				}

				else {
					if (!Modele.Tailles.containsKey("Grande"))
						Modele.Tailles.put("Grande", new HashSet());
					Modele.Tailles.get("Grande").add(myCurrentPhoto);
				}


				if (!Modele.Couleurs.containsKey(myCurrentPhoto.getColor())) 
					Modele.Couleurs.put(myCurrentPhoto.getColor(), new HashSet());
				Modele.Couleurs.get(myCurrentPhoto.getColor()).add(myCurrentPhoto);


				//Test de serial en affichant les notes
				System.out.println(myCurrentPhoto.getNote());
			}
		}
		else {
			this.noms = repertoire.list();

			for (int  i=0 ; i < noms.length ; i++) {
				if (! Modele.images.contains(noms[i])){

					Recuperateur rim = new Recuperateur(Controleur.REPERTOIRE+noms[i]);

					try {
						Modele.ajouterImage(rim);
					} catch (IOException e) {

					}

				}

			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JRadioButton){
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
						tmp.setNote(note);
						p.setNote(note);
						Modele.images.remove(tmp);
						Modele.images.add(p);
						break;
					}
				}
			}
		}

		else if (e.getSource() instanceof JTextField) {
			JTextField saisie = (JTextField)e.getSource();
			if (saisie.getName().equals("tageur")) {
				String tag = saisie.getText();
				ImagePanel panl = Interface_miniatures.panl;
				Photo p = panl.photo;

				Iterator<Photo> i2 = Modele.images.iterator();
				while(i2.hasNext()){
					Photo tmp = (Photo)i2.next();
					if (tmp.nom.equals(p.nom)){						
						tmp.setTag(tag);
						p.setTag(tag);
						Modele.images.remove(tmp);
						Modele.images.add(p);
						break;
					}
				}
			}
			else if (saisie.getName().equals("nameur")){
				nom = (String) saisie.getText();
				modele.triage(nom);
				Modele.ancienne_demande = nom;
			}
			else {
				nom = (String) saisie.getText();
				modele.triage_tag(nom);
				Modele.ancienne_demande = nom;
			}
		}

		else if(e.getSource() instanceof JCheckBox){
			JCheckBox cb = (JCheckBox)e.getSource();
			nom = cb.getName();
			modele.triage(nom);
		}

		else if(e.getSource() instanceof JComboBox){
			JComboBox cob = (JComboBox)e.getSource();
			nom_tri = (String)cob.getSelectedItem();
		}

		else if (e.getSource() instanceof JButton) {
			JButton bt = (JButton) e.getSource();
			if (bt.getName().equals("b_trier")) {
				for (int k = 0 ; k < Interface_panneau.trieur.length ; k++) {
					if (Interface_panneau.trieur[k].equals(nom_tri)) modele.triage(nom_tri);
				}
			}
			else {
				ImagePanel panl = Interface_miniatures.panl;
				Photo p = panl.photo;

				Iterator<Photo> i2 = Modele.images.iterator();
				while(i2.hasNext()){
					Photo tmp = (Photo)i2.next();
					if (tmp.nom.equals(p.nom)){						
						tmp.rmTags();
						p.rmTags();
						Modele.images.remove(tmp);
						Modele.images.add(p);
						break;
					}

				}
			}

		}

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
