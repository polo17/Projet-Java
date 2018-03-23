import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Interface_miniatures extends Panel{
	
	ArrayList images = new ArrayList<>();
	
	
	public Interface_miniatures() {
		this.setName("Miniatures");

		setVisible(true);		
		this.setBackground(Color.GRAY);
		/*
		GridLayout gl = new GridLayout();
		gl.setColumns(2);
		gl.setRows(3);
		this.setLayout(gl);
		gl.setHgap(5); 
		gl.setVgap(5);
		

		
		JButton bouton1= new JButton(new ImageIcon("Images/cellules.jpg"));
		JButton bouton2= new JButton(new ImageIcon("Images/tigre.jpg"));
		JButton bouton3= new JButton(new ImageIcon("Images/espace.jpg"));
		JButton bouton4= new JButton(new ImageIcon("Images/bretagne.jpg"));
		JButton bouton5= new JButton(new ImageIcon("Images/lucane.jpg"));
		JButton bouton6= new JButton(new ImageIcon("Images/paris.jpg"));
		//bouton1.setPreferredSize(new Dimension(50,50));
		//bouton1.setSize(50,50);
		
		add(bouton2);
		add(bouton1);
		add(bouton3);
		add(bouton4);
		add(bouton5);
		add(bouton6);
		setVisible(true);
		*/
		
		for (int i=0; i<50; i++){
		    JPanel cell1 = new JPanel();

		    cell1.setBackground(Color.YELLOW);

		    cell1.setPreferredSize(new Dimension(60, 40)); 
		}
	    JPanel content = new JPanel();

	    content.setPreferredSize(new Dimension(300, 120));
	    //setContentPane(content);

	}
	/*
	public void paint (Graphics g) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("Images/tigre.jpg"));
			g.drawImage(img,0,0,50,50, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/

}
