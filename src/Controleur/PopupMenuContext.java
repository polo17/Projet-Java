package Controleur;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

import Modele.Modele;
import Modele.Photo;
import Vue.ImagePanel;
import Vue.Interface_miniatures;

public class PopupMenuContext  extends JPanel{

	public JPopupMenu popup;
	public Photo photo;
	public static Interface_miniatures im;
	public PopupMenuContext(Photo p, Interface_miniatures im){
		this.photo=p;
		PopupMenuContext.im=im;
		popup = new JPopupMenu();
		ActionListener menuListener = new ActionListener() {


			public void actionPerformed(ActionEvent event) {

				if(event.getActionCommand().equals("renommer")) {
					String nombase = photo.getNom();
					String nom = JOptionPane.showInputDialog(null, "Modifiez le nom de la photo", "Renommer photo", JOptionPane.QUESTION_MESSAGE);
					String ext = Recuperateur.getStringExtension(photo.getNom());

					if(nom!=null && !nom.equals("") &&!nom.equals(nombase)) {
						photo.nom=nom+ext;
						PopupMenuContext.im.miseAJour();
						JOptionPane.showMessageDialog(null, "La photo à bien été renommée  ", "renommage ok", JOptionPane.INFORMATION_MESSAGE);
					}

					else if(nom!=null) {
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nom différent de l'ancien", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else if(event.getActionCommand().equals("ajoutertag")) {
					String tag = JOptionPane.showInputDialog(null, "Veuillez entrer tag"   , "Ajout de tag", JOptionPane.QUESTION_MESSAGE);
					if(tag!=null && !tag.equals("") && tag!=null) {
						photo.tags.add(tag);
						JOptionPane.showMessageDialog(null, "Tag ajouté !", "Ajout tag", JOptionPane.INFORMATION_MESSAGE);
					}
					else if (tag!=null && tag.equals("")){
						JOptionPane.showMessageDialog(null, "Le tag n'a pas été ajouté, veuillez re-essayer", "Erreur", JOptionPane.ERROR_MESSAGE);
					}

				}

				else if(event.getActionCommand().equals("supprimertag")){
					if(photo.tags.size()==0) {
						JOptionPane.showMessageDialog(null, "Cette photo ne comporte pas de tags", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					else {
						Object[] tags = (Object[]) photo.tags.toArray();
						String nom = (String)JOptionPane.showInputDialog(null, "Veuillez choisir le tag à supprimer", "Suppression tag", JOptionPane.QUESTION_MESSAGE, null, tags, tags[tags.length-1]);
						if(nom!=null) {
							photo.tags.remove(nom);
							JOptionPane.showMessageDialog(null, nom + " a bien été retiré de la liste des tags", "Suppresion effectuée", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}

				else if(event.getActionCommand().equals("modifierdate")) {
					String date = JOptionPane.showInputDialog(null, "Veuillez entrer une date au format jj/mm/aaaa", "Modifier la date", JOptionPane.QUESTION_MESSAGE);
					if(date!=null &&date.charAt(2)=='/' && date.charAt(5)=='/' && date.length()==10) {
						HashSet<Photo> hs = Modele.Dates.get(photo.getDate());
						Iterator<Photo> it = hs.iterator();
						while(it.hasNext()) {
							if(it.next().equals(photo)) {
								it.next().date=date;
							}
						}
						photo.date=date;
						JOptionPane.showMessageDialog(null, "Date modifiée avec succès", "Date modifiée", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(date!=null){

						JOptionPane.showMessageDialog(null, "Veuillez entrer une date valide", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}


				else if(event.getActionCommand().equals("ajouterphoto")) {
					ChoixImage ci = new ChoixImage();
					File f = ci.imageSelected();
					if(f!=null && ci.fileIsNotIsNoGood(f)) {
						Recuperateur r = new Recuperateur(f.getPath());
						try {
							Modele.ajouterImage(r);
							PopupMenuContext.im.miseAJour();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Photo importée avec succés", "Importation", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				else if(event.getActionCommand().equals("supprimer")) {
					int suppres = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir supprimer cette photo ?", "Suppression photo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(suppres == JOptionPane.OK_OPTION) {
						Modele.Couleurs.remove(photo.getColor());
						Modele.Tailles.remove(photo.getTaille());
						Modele.Dates.remove(photo.getDate());
						Modele.images.remove(photo);
						PopupMenuContext.im.miseAJour();
						JOptionPane.showMessageDialog(null, "Photo supprimée avec succés", "Suppression", JOptionPane.INFORMATION_MESSAGE);
						
					}
				}

				else {
					Color c = photo.getColor();
					String color;
					if(c.equals(Color.red)){
						color = "Rouge";
					}
					else if (c.equals(Color.blue)) {
						color = "Bleue";
					}
					else
						color = "Vert";


					String info = "Nom de l'image : "+photo.getNom()+"\n"
							+ "Taille de l'image : "+String.valueOf(photo.getTaille()+" Ko"+"\n"
									+ "Emplacement : "+photo.getPath()+"\n"
									+ "Date de la photo : "+photo.getDate()+"\n"
									+ "Couleur principale : "+color+"\n"
									+ "Liste de tags : "+photo.getTags().toString()+"\n"
									+ "Note de l'image : "+String.valueOf(photo.getNote()));
					JOptionPane.showMessageDialog(null, info, "Information de l'image", JOptionPane.INFORMATION_MESSAGE);


				}

				
				}
			
			
		};
		JMenuItem item;
		popup.add(item = new JMenuItem("Renommer"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("renommer");
		item.addActionListener(menuListener);

		popup.add(item = new JMenuItem("Ajouter un tag"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("ajoutertag");
		item.addActionListener(menuListener);
		popup.add(item = new JMenuItem("Supprimer un tag"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("supprimertag");
		item.addActionListener(menuListener);
		popup.add(item = new JMenuItem("Modifier date"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("modifierdate");
		item.addActionListener(menuListener);
		popup.add(item = new JMenuItem("Importer une photo"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("ajouterphoto");
		item.addActionListener(menuListener);
		popup.addSeparator();
		popup.add(item = new JMenuItem("Supprimer photo"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("supprimer");
		item.addActionListener(menuListener);
		popup.addSeparator();
		popup.add(item = new JMenuItem("Informations photo"));
		item.setActionCommand("info");
		item.addActionListener(menuListener);

		popup.setLabel("Justification");
		popup.setBorder(new BevelBorder(BevelBorder.RAISED));


		addMouseListener(new MousePopupListener());






	}

	class MousePopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {


		}

		public void mouseClicked(MouseEvent e) {

			checkPopup(e);

		}

		public void mouseReleased(MouseEvent e) {



		}

		private void checkPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				popup.show(PopupMenuContext.this, e.getX(), e.getY());
			}
		}
	}
}