package Controleur;
import Vue.ImagePanel;
import Vue.Interface_miniatures;
import Modele.Modele;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import Modele.Photo;

public class PopupMenuContext  extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JPopupMenu popup;
	public Photo photo;
	public Interface_miniatures im;

	public PopupMenuContext(Photo p, Interface_miniatures im){
		this.photo=p;
		popup = new JPopupMenu();
		this.im=im;
		ActionListener menuListener = new ActionListener() {


			public void actionPerformed(ActionEvent event) {
				
				/*
				 * Renommage de l'image, que ce soit au niveau du fichier ou de l'objet 
				 */

				if(event.getActionCommand().equals("renommer")) {
					String nombase = photo.getNom();
					String nom = JOptionPane.showInputDialog(null, "Modifiez le nom de la photo", "Renommer photo", JOptionPane.QUESTION_MESSAGE);
					String ext = Recuperateur.getStringExtension(photo.getNom());

					if(nom!=null && !nom.equals("") &&!nom.equals(nombase)) {
						photo.nom=nom+"."+ext;
						File path = new File("Images/");

						boolean rename = photo.getFile().renameTo(new File(path.getAbsolutePath()+File.separator+nom+"."+ext));
						photo.path=path.getAbsolutePath()+File.separator+nom+"."+ext;
						im.miseAJour();
						if(rename)
							JOptionPane.showMessageDialog(null, "La photo à bien été renommée", "renommage ok", JOptionPane.INFORMATION_MESSAGE);
						else
							JOptionPane.showMessageDialog(null, "La photo n'a pas été renommée", "renommage ok", JOptionPane.ERROR_MESSAGE);
					}

					else if (nom!=null) {
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nom différent de l'ancien", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				/*
				 * Ajout d'un tag à la photo
				 */
				else if(event.getActionCommand().equals("ajoutertag")) {
					String tag = JOptionPane.showInputDialog(null, "Veuillez entrer tag"   , "Ajout de tag", JOptionPane.QUESTION_MESSAGE);
					if(tag!=null && !tag.equals("")) {
						photo.tags.add(tag);
						JOptionPane.showMessageDialog(null, "Tag ajouté !", "Ajout tag", JOptionPane.INFORMATION_MESSAGE);
					}
					else if (tag!=null && tag.equals("")){
						JOptionPane.showMessageDialog(null, "Le tag n'a pas été ajouté, veuillez re-essayer", "Erreur", JOptionPane.ERROR_MESSAGE);
					}

				}

				
				/*
				 * Suppression d'un tag de la photo
				 */
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

				/*
				 * Supprime tout les tags de la photo
				 */
				else if(event.getActionCommand().equals("supprimertags")) {
					int suppres = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir supprimer tout les tags de cette photo ?", "Suppression photo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(suppres == JOptionPane.OK_OPTION) {
						photo.rmTags();
						JOptionPane.showMessageDialog(null, "Tags supprimés avec succès", "Suppression", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				/*
				 * Modifier la date de la photo
				 */
				else if(event.getActionCommand().equals("modifierdate")) {
					String date = JOptionPane.showInputDialog(null, "Veuillez entrer une date au format jj/mm/aaaa", "Modifier la date", JOptionPane.QUESTION_MESSAGE);
					if( date!=null &&date.charAt(2)=='/' && date.charAt(5)=='/' && date.length()==10) {
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


				/*
				 * Importe une photo choisi dans la banque d'image et copie l'image fichier dans le repertoire "Images/"
				 */
				else if(event.getActionCommand().equals("ajouterphoto")) {
					ImageChoice ci = new ImageChoice();
					File f = ci.imageSelected();
					if(f!=null && ci.fileIsNotIsNoGood(f)) {
						Path source = Paths.get(f.getAbsolutePath());
						Path dest = Paths.get("Images/"+f.getName());
						boolean exist = false;

						try {
							Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "L'image existe déjà", "Erreur", JOptionPane.ERROR_MESSAGE);
							exist=true;
						}
						if(!exist) {
							Recuperateur r = new Recuperateur("Images/"+f.getName());
							try {

								Modele.ajouterImage(r);
								im.miseAJour();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Probleme lors de l'import de l'image", "Erreur", JOptionPane.ERROR_MESSAGE);
								e.printStackTrace();
							}
						}
					}

				}
				
				/*
				 * Supprime l'image de la banque d'image et du dossier "Images/"
				 */
				else if(event.getActionCommand().equals("supprimer")) {
					int suppres = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir supprimer cette photo ?", "Suppression photo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(suppres == JOptionPane.OK_OPTION) {
						try {
							Files.delete(Paths.get(photo.getPath()));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Modele.removeImage(photo);

						im.miseAJour();
						JOptionPane.showMessageDialog(null, "Photo supprimée avec succés", "Suppression", JOptionPane.INFORMATION_MESSAGE);
					}
				}

				/*
				 * Panneau d'information de l'image
				 */
				else {
					String s;
					if (photo.getColor().equals(Color.red)){
						s="Rouge";
					}
					else if(photo.getColor().equals(Color.green)) {
						s="Vert";
					}
					else
						s="Bleu";

					String info = "Nom de l'image : "+photo.getNom()+"\n"
							+ "Taille de l'image : "+String.valueOf(photo.getTaille()+" Ko \n"
									+ "Emplacement : "+photo.getPath()+"\n"
									+ "Date de la photo : "+photo.getDate()+"\n"
									+ "Couleur principale : "+s+"\n"
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
		
		popup.add(item = new JMenuItem("Supprimer les tags de l'image"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("supprimertags");
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
				System.out.println(e.getComponent());
				popup.show(PopupMenuContext.this, e.getX(), e.getY());
			}
		}
	}



}