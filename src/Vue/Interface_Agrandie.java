package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Controleur.Controleur;
import Modele.Modele;
import Modele.Photo;

@SuppressWarnings("serial")
public class Interface_Agrandie extends JFrame{

	Modele modele = Interface_panneau.modele;
	Controleur ctrl = Interface_panneau.ctrl;
	
	public BufferedImage select;
	public Photo photo;
	
	public static int TAILLE_X = 700;
	public static int TAILLE_Y = 390;

	public Interface_Agrandie (Photo img) throws IOException{
		
		super();
		this.photo = img;
		this.select = (BufferedImage)img.img; 
		setLayout(new FlowLayout());
		setSize(TAILLE_X+200,TAILLE_Y+100);
		setBackground(new Color(204, 255, 255));
		String titre = photo.nom;
		titre = titre.replace(".jpg", "");
		setTitle(titre);
		this.setBackground(Color.black);

		Panel etoiles = new Panel();
		etoiles.setLayout(new BoxLayout(etoiles, BoxLayout.Y_AXIS));
		etoiles.setBackground(new Color(204, 255, 255));
		add(etoiles);

		ButtonGroup group = new ButtonGroup();
		Label lab = new Label("Notez l'image :", Label.LEFT);
		etoiles.add(lab);
		etoiles.setLayout(new FlowLayout());
		for (int i=0;i<Interface_panneau.notes.length;i++) {
			JRadioButton cocher_etoile = new JRadioButton(Interface_panneau.notes[i]);
			cocher_etoile.setName(Interface_panneau.notes[i]);
			cocher_etoile.setBackground(new Color(204, 255, 255));
			cocher_etoile.addActionListener(ctrl);
			group.add(cocher_etoile);
			etoiles.add(cocher_etoile);
		}
		
		Panel tags = new Panel();
		tags.setLayout(new BoxLayout(tags, BoxLayout.Y_AXIS));
		tags.setBackground(new Color(204, 255, 255));
		add(tags);

		Label la2 = new Label("Ajoutez un tag :", Label.LEFT);
		tags.add(la2);
		JTextField saisie_tag = new JTextField();
		saisie_tag.setName("tageur");
		saisie_tag.addActionListener(ctrl);
		tags.add(saisie_tag);
		JButton effacer = new JButton("effacer les tags");
		effacer.setName("b_effacer");
		effacer.addActionListener(ctrl);
		effacer.setAlignmentX(CENTER_ALIGNMENT);
		tags.add(effacer,BorderLayout.SOUTH);

		Panel list_tags = new Panel();
		list_tags.setLayout(new BoxLayout(list_tags, BoxLayout.Y_AXIS));
		list_tags.setBackground(new Color(204, 255, 255));
		Label les_tags = new Label("Liste des tags : ", Label.LEFT);
		list_tags.add(les_tags);
		add(list_tags);

		for (int i=0; i<photo.tags.size()-1;i++){
			Label la0 = new Label(photo.tags.get(i), Label.LEFT);
			list_tags.add(la0);
		}
		revalidate();
		setVisible(true);
	}

	public void paint(Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(select, 0, 100, TAILLE_X, TAILLE_Y, null);
	}

	public void actionPerformed(ActionEvent e) {

	}
}


