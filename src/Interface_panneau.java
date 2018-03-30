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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Interface_panneau extends Panel implements WindowListener, MouseListener{
String[] trieur = {"nom","couleurs","note","taille","date","lieu"};
String[] couleurs = {"rouge","orange","jaune","vert","bleu","violet"};

	
	public static void main(String[] args) throws IOException {
		
		Modele modele = new Modele();
		//Controleur ctrl = new Controleur(modele);

		Interface_panneau p = new Interface_panneau();
		Interface_miniatures i = new Interface_miniatures();
		Interface_image e = new Interface_image();
		
		p.setSize(200,200);
		
		
		Frame f = new Frame();
		f.setLayout(new BorderLayout());
		f.add(p, BorderLayout.WEST);
		f.add(i, BorderLayout.CENTER);
		f.add(e, BorderLayout.SOUTH);
		
		p.setBackground(Color.WHITE);
		p.addMouseListener(p);
		f.addWindowListener(p);
		f.setTitle("Gestionnaire d'images");
		f.setSize(1000, 800);
		f.setVisible(true);
	}

	public Interface_panneau() {
		super();
		
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		
		Label la2 = new Label("Select. couleur :", Label.LEFT);
		pan.add(la2);
		for (int i=0;i<6;i++) {
			JRadioButton cocher_couleur = new JRadioButton(couleurs[i]);
			pan.add(cocher_couleur);
		}
		Label la4 = new Label("Saisir lieu :", Label.LEFT);
		pan.add(la4);
		JTextField saisie_lieu = new JTextField("Veuillez saisir un lieu");
		pan.add(saisie_lieu);
		
		Label la5 = new Label("Saisir date :", Label.LEFT);
		pan.add(la5);
		JTextField saisie_date = new JTextField("Veuillez saisir une date");
		pan.add(saisie_date);


		
		
		Label la = new Label("Selectionner par", Label.LEFT);
		JComboBox liste1 = new JComboBox(trieur);
		pan.add(la);
		pan.add(liste1);
		
		JButton trier = new JButton("trier");
		trier.setAlignmentX(CENTER_ALIGNMENT);
		Label la3 = new Label("Trier par", Label.LEFT);
		pan.add(la3);
		pan.add(trier,BorderLayout.SOUTH);
		
		add(pan);
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
