import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class PopupMenuExample  extends JPanel{

	public JPopupMenu popup;
	public Photo photo;
	public PopupMenuExample(Photo p){
		this.photo=p;
		popup = new JPopupMenu();
		ActionListener menuListener = new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(event.getActionCommand().equals("renommer")) {
					String nombase = photo.getNom();

					String nom = JOptionPane.showInputDialog(null, "Modifiez le nom de la photo", "Renommer photo", JOptionPane.QUESTION_MESSAGE);
					System.out.println(nom);
					if(!nom.equals(null) &&!nom.equals("") &&!nom.equals(nombase)) {
						JOptionPane.showMessageDialog(null, "La photo à bien été renommée  ", "renommage ok", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else if(nom.equals("")){
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nom correct", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "Veuillez entrer un nom différent de l'ancien", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				else if(event.getActionCommand().equals("ajoutertag")) {
					int l = photo.tags.size();
					String tag = JOptionPane.showInputDialog(null, "Veuillez entrer tag"   , "Ajout de tag", JOptionPane.QUESTION_MESSAGE);
					photo.tags.add(tag);
					if(photo.tags.size() == l+1) {
					JOptionPane.showMessageDialog(null, "Tag ajouté !", "Ajout tag", JOptionPane.INFORMATION_MESSAGE);
					}
					else if (photo.tags.size() != l+1 || tag.equals("")){
						JOptionPane.showMessageDialog(null, "Le tag n'a pas été ajouté, veuillez re-essayer", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(event.getActionCommand().equals("supprimertag")){
					if(photo.tags.size()==0) {
						JOptionPane.showMessageDialog(null, "Cette photo ne comporte pas de tags", "Erreur", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						Object[] tags = (Object[]) photo.tags.toArray();
						String nom = (String)JOptionPane.showInputDialog(null, "Veuillez choisir le tag à supprimer", "Suppression tag", JOptionPane.QUESTION_MESSAGE, null, tags, tags[tags.length-1]);
						photo.tags.remove(nom);
						JOptionPane.showMessageDialog(null, nom + " a bien été retiré de la liste des tags", "Suppresion effectuée", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
				else if(event.getActionCommand().equals("modifierdate")) {
					String date = JOptionPane.showInputDialog(null, "Veuillez entrer une date au format jj/mm/aaaa", "Modifier la date", JOptionPane.QUESTION_MESSAGE);
					if(date.charAt(2)=='/' && date.charAt(5)=='/' && date.length()==10) {
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
					else {
						JOptionPane.showMessageDialog(null, "Veuillez entrer une date valide", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(event.getActionCommand().equals("supprimer")) {
					int suppres = JOptionPane.showConfirmDialog(null, "Etes vous sur de vouloir supprimer cette photo ?", "Suppression photo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(suppres == JOptionPane.OK_OPTION) {
						Modele.images.remove(photo);
						Modele.Couleurs.remove(photo.getColor());
						Modele.Tailles.remove(photo.getTaille());
						Modele.Dates.remove(photo.getDate());
						JOptionPane.showMessageDialog(null, "Photo supprimée avec succés", "Suppression", JOptionPane.INFORMATION_MESSAGE);
					}
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
		popup.add(item = new JMenuItem("Supprimer photo"));
		item.setHorizontalTextPosition(JMenuItem.RIGHT);
		item.setActionCommand("supprimer");
		item.addActionListener(menuListener);
		popup.addSeparator();
		popup.add(item = new JMenuItem("Informations photo"));
		item.addActionListener(menuListener);

		popup.setLabel("Justification");
		popup.setBorder(new BevelBorder(BevelBorder.RAISED));


		addMouseListener(new MousePopupListener());
		
	}

	class MousePopupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			ImagePanel ip  =(ImagePanel) e.getSource();
			checkPopup(e);

		}

		public void mouseClicked(MouseEvent e) {

			checkPopup(e);

		}

		public void mouseReleased(MouseEvent e) {

			checkPopup(e);

		}

		private void checkPopup(MouseEvent e) {
			if (e.isPopupTrigger()) {
				System.out.println(e.getComponent());
				popup.show(PopupMenuExample.this, e.getX(), e.getY());
			}
		}
	}

	// An inner class to show when popup events occur
//	class PopupPrintListener implements PopupMenuListener {
//		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
//			System.out.println("Popup menu will be visible!");
//		}
//
//		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
//			System.out.println("Popup menu will be invisible!");
//		}
//
//		public void popupMenuCanceled(PopupMenuEvent e) {
//			System.out.println("Popup menu is hidden!");
//		}
//	}


}