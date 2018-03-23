import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;

public class Interface_panneau extends Panel implements WindowListener, MouseListener{
String[] trieur = {"nom","couleurs","note","taille","date","lieu"};
	
	public static void main(String[] args) {

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
		f.setSize(900, 700);
		f.setVisible(true);
	}

	public Interface_panneau() {
		super();
		
		JPanel pan = new JPanel();
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		Label la = new Label("Selectionner par", Label.LEFT);
		JComboBox liste1 = new JComboBox(trieur);
		pan.add(la);
		pan.add(liste1);
		
		JButton trier = new JButton("trier");
		trier.setAlignmentX(CENTER_ALIGNMENT);
		Label la2 = new Label("Trier par", Label.LEFT);
		pan.add(la2);
		pan.add(trier,BorderLayout.SOUTH);
		
		add(pan);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		System.exit(0);		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
