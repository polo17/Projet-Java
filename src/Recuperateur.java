import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

/**
 * 
 * @author Ludovic
 * Classe permettant de récupérer toutes les propriétés d'un fichier
 */
public class Recuperateur {

	File file;
	
	public Recuperateur(String filename) {
		this.file = new File(filename);
	}

	/**
	 * 
	 * @param f
	 * @return la date en String
	 */
	public String obtenirDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = new Date(this.file.lastModified());
		return sdf.format(d);
	}

	/**
	 * 
	 * @return la taille du fichier
	 */
		public int obtenirTaille() {
			int size = (int) (this.file.length() / 1024) + 1;
			return size;
		}

	/**
	 * 
	 * @return la couleur dominante de l'image
	 * @throws IOException
	 */
	public Color obtenirCouleur() throws IOException{
		BufferedImage img = ImageIO.read(this.file);
		int red = 0;
		int green = 0;
		int blue = 0;
		for(int i = 0; i<img.getHeight(); i++){
			for(int j =0; j<img.getWidth(); j++){
				Color mycolor = new Color(img.getRGB(j, i));
				red+=mycolor.getRed();
				green+=mycolor.getGreen();
				blue+=mycolor.getBlue();
			}
		}
		if(red>green && red>blue){
			return Color.red;
		}
		else if(green>red && green>blue){
			return Color.green;    		
		}
		else{
			return Color.blue;
		}

	}

	/**
	 * 
	 * @return l'image du fichier
	 * @throws IOException
	 */
	public BufferedImage obtenirImage() throws IOException{
		BufferedImage img = ImageIO.read(this.file);
		return img;
	}
	
}

