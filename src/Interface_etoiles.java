import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class Interface_etoiles extends Panel{

	Modele modele = new Modele();
	Controleur ctrl = new Controleur(modele);

	public Interface_etoiles() throws IOException{

		setLayout(new BorderLayout());
		setVisible(true);		
		this.setBackground(Color.GRAY);

		Label lab = new Label("Notez l'image :", Label.LEFT);
		this.add(lab);
		setLayout(new FlowLayout());
		ButtonGroup group = new ButtonGroup();
		for (int i=0;i<Interface_panneau.notes.length;i++) {
			JRadioButton cocher_note2 = new JRadioButton(Interface_panneau.notes[i]);
			cocher_note2.setName(Interface_panneau.notes[i]);
			cocher_note2.addActionListener(ctrl);		
			group.add(cocher_note2);
			this.add(cocher_note2);
		}
	}

	public void actionPerformed(ActionEvent e) {

	}
}


