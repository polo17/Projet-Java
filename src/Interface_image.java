import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

public class Interface_image extends Panel{


	
	public Interface_image() {
		
		setLayout(new BorderLayout());
		this.setName("Image");
		setSize(900, 700);
		setVisible(true);		
		this.setBackground(Color.GRAY);
		
		JRadioButton étoile1 = new JRadioButton("1 étoile ");
	    //étoile1.setMnemonic(KeyEvent.VK_B);
	    //étoile1.setActionCommand("1 étoile");
	   // étoile1.setSelected(true);

	    JRadioButton étoile2 = new JRadioButton("2 étoiles");
	    //étoile2.setMnemonic(KeyEvent.VK_C);
	    //étoile2.setActionCommand("2 étoiles");

	    JRadioButton étoile3 = new JRadioButton("3 étoiles");
	    //étoile3.setMnemonic(KeyEvent.VK_D);
	    //étoile3.setActionCommand("3 étoiles");

	    JRadioButton étoile4 = new JRadioButton("4 étoiles");
	    //étoile4.setMnemonic(KeyEvent.VK_R);
	    //étoile4.setActionCommand("4 étoiles");

	    JRadioButton étoile5 = new JRadioButton("5 étoiles");
	    //étoile5.setMnemonic(KeyEvent.VK_P);
	    //étoile5.setActionCommand("5 étoiles");

	    setLayout(new FlowLayout()); 
	   
	    ButtonGroup group = new ButtonGroup();
	    group.add(étoile1);
	    group.add(étoile2);
	    group.add(étoile3);
	    group.add(étoile4);
	    group.add(étoile5);
	    
	    add(étoile1);
	    add(étoile2);
	    add(étoile3);
	    add(étoile4);
	    add(étoile5);
	    
	   //pack();
	}
	    
	public void actionPerformed(ActionEvent e) {
	   
	}
}


