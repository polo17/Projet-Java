package Controleur;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * 
 * @author Ludovic
 * Classe permettant de récupérer toutes les propriétés d'un fichier
 */
public class Recuperateur {

	public File file;
	
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
		System.out.println(this.file.toString());
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
	
	public String getPath() {
		return this.file.getPath();
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
	
	
	
	public static String getFileExtension(File f) {
		String nom = f.getName();
		if(nom.lastIndexOf(".")!=-1 && nom.lastIndexOf(".")!=0) {
			return nom.substring(nom.lastIndexOf(".")+1);
		}
		else return "";
	}
	
	public static String getStringExtension(String s) {
		if(s.lastIndexOf(".")!=-1 && s.lastIndexOf(".")!=0) {
			return s.substring(s.lastIndexOf(".")+1);
		}
		else return "";
	}
	
}
	


