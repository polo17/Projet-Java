import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Interface_panneau extends Panel implements WindowListener, MouseListener, Observer{

	String[] trieur = {"ordre alphabétique","couleurs","note","taille","date","lieu"};
	String[] couleurs = {"rouge","vert","bleu"};
	String[] tailles = {"petites","moyennes","grandes"};
	public static String[] notes = {"1 étoile","2 étoiles","3 étoiles","4 étoiles","5 étoiles"};

	static Modele modele = new Modele();
	Controleur ctrl = new Controleur(modele);

	public static void main(String[] args) throws IOException {

		Interface_panneau p = new Interface_panneau();
		Interface_miniatures i = new Interface_miniatures();

		modele.addObserver(i);

		Frame f = new Frame();
		f.setLayout(new BorderLayout());		
		f.add(p, BorderLayout.WEST);
		f.add(i, BorderLayout.CENTER);
		p.setBackground(new Color(102,255,178));;
		p.addMouseListener(p);
		f.addWindowListener(p);
		f.setTitle("Gestionnaire d'images");
		f.setSize(1000, 800);
		/*
		JScrollPane scroll = new JScrollPane(i);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		f.add(scroll);
		 */
		f.setVisible(true);
	}

	public Interface_panneau() throws IOException {
		super();

		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.setBackground(new Color(102,255,178));
		Label la1 = new Label("Saisir nom :", Label.LEFT);
		pan.add(la1);
		JTextField saisie_nom = new JTextField();
		saisie_nom.addActionListener(ctrl);
		pan.add(saisie_nom);

		Label la2 = new Label("Saisir lieu :", Label.LEFT);
		pan.add(la2);
		JTextField saisie_lieu = new JTextField();
		pan.add(saisie_lieu);

		Label la3 = new Label("Séléctionner couleur :", Label.LEFT);
		pan.add(la3);
		for (int i=0;i<couleurs.length;i++) {
			JCheckBox cocher_couleur = new JCheckBox(couleurs[i]);
			cocher_couleur.setAlignmentX(CENTER_ALIGNMENT);
			cocher_couleur.setBackground(new Color(102,255,178));
			cocher_couleur.setName(couleurs[i]);
			cocher_couleur.addActionListener(ctrl);
			pan.add(cocher_couleur);
		}

		Label la4 = new Label("Séléctionner taille :", Label.LEFT);
		pan.add(la4);
		for (int i=0;i<tailles.length;i++) {
			JCheckBox cocher_taille = new JCheckBox(tailles[i]);
			cocher_taille.setAlignmentX(CENTER_ALIGNMENT);
			cocher_taille.setBackground(new Color(102,255,178));
			cocher_taille.setName(tailles[i]);
			cocher_taille.addActionListener(ctrl);			
			pan.add(cocher_taille);
		}		

		Label la5 = new Label("Séléctionner note :", Label.LEFT);
		pan.add(la5);
		for (int i=0;i<notes.length;i++) {
			JCheckBox cocher_note = new JCheckBox(notes[i]);
			cocher_note.setAlignmentX(CENTER_ALIGNMENT);
			cocher_note.setBackground(new Color(102,255,178));
			cocher_note.setName(notes[i]);
			cocher_note.addActionListener(ctrl);
			
			pan.add(cocher_note);
		}

		Label la6 = new Label("Trier par : ", Label.LEFT);
		pan.add(la6);
		JComboBox l_trier = new JComboBox(trieur);
		l_trier.addActionListener(ctrl);		
		pan.add(l_trier);

		JButton trier = new JButton("trier");
		trier.setAlignmentX(CENTER_ALIGNMENT);
		pan.add(trier,BorderLayout.SOUTH);

		add(pan);
	}

	@Override
	public void update(Observable arg0, Object arg1) {

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
	public void windowActivated(WindowEvent arg0) {

	}

	@Override
	public void windowClosed(WindowEvent arg0) {

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}

	@Override
	public void windowIconified(WindowEvent arg0) {

	}

	@Override
	public void windowOpened(WindowEvent arg0) {

	}
}
