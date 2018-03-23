import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Interface_miniatures extends Canvas{
	
	ArrayList images = new ArrayList<>();

	public void paint (Graphics g) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File("Images/tigre.jpg"));
			g.drawImage(img,0,0,50,50, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
