import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

public class Interface_image extends Panel{


	
	public Interface_image() {
		
		setLayout(new BorderLayout());
		this.setName("Image");
		setSize(900, 700);
		setVisible(true);		
		this.setBackground(Color.GRAY);
		
		JRadioButton etoile1 = new JRadioButton("1 étoile ");
	    //étoile1.setMnemonic(KeyEvent.VK_B);
	    //étoile1.setActionCommand("1 étoile");
	   // étoile1.setSelected(true);

	    JRadioButton etoile2 = new JRadioButton("2 étoiles");
	    //étoile2.setMnemonic(KeyEvent.VK_C);
	    //étoile2.setActionCommand("2 étoiles");

	    JRadioButton etoile3 = new JRadioButton("3 étoiles");
	    //étoile3.setMnemonic(KeyEvent.VK_D);
	    //étoile3.setActionCommand("3 étoiles");

	    JRadioButton etoile4 = new JRadioButton("4 étoiles");
	    //étoile4.setMnemonic(KeyEvent.VK_R);
	    //étoile4.setActionCommand("4 étoiles");

	    JRadioButton etoile5 = new JRadioButton("5 étoiles");
	    //étoile5.setMnemonic(KeyEvent.VK_P);
	    //étoile5.setActionCommand("5 étoiles");

	    setLayout(new FlowLayout()); 
	   
	    ButtonGroup group = new ButtonGroup();
	    group.add(etoile1);
	    group.add(etoile2);
	    group.add(etoile3);
	    group.add(etoile4);
	    group.add(etoile4);
	    
	    add(etoile1);
	    add(etoile2);
	    add(etoile3);
	    add(etoile4);
	    add(etoile5);
	    
	   //pack();
	}
	    
	public void actionPerformed(ActionEvent e) {
	   
	}
}


