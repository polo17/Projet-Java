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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Interface_Agrandie extends JFrame{

	Modele modele = Interface_panneau.modele;
	Controleur ctrl = Interface_panneau.ctrl;
	public BufferedImage select;

	public Interface_Agrandie (BufferedImage img) throws IOException{
		super();
		this.select = img;
		setLayout(new FlowLayout());
		setSize(700,490);
		setBackground(new Color(204, 255, 255));

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
		
		Label la2 = new Label("Saisir tag :", Label.LEFT);
		tags.add(la2);
		JTextField saisie_tag = new JTextField();
		saisie_tag.setName("tageur");
		saisie_tag.addActionListener(ctrl);
		tags.add(saisie_tag);
		//String tag = "Tags de la photo : " + 
		//Label tags = new Label("Tags de la photo :" , Label.RIGHT);
		//Label note = new Label("Note de la photo :", Label.RIGHT);
		revalidate();
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(select, 0, 100, 700, 390, null);
	}

	public void actionPerformed(ActionEvent e) {

	}
}


