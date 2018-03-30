import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;


/**
 * 
 * @author ltourn01
 *
 */
public class Recuperateur {

	File file;
	
	public Recuperateur(String filename) {
		this.file = new File(filename);
	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	public String getFormatedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
		Date d = new Date(this.file.lastModified());
		return sdf.format(d);
	}

	//Afficher la taille
		public int obtenirTaille() {
			int size = (int) (this.file.length() / 1024) + 1;
			return size;
		}

	//Récupère couleur dominante
	public MaxColor getMainColor() throws IOException{
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
			MaxColor mc = new MaxColor(Color.red, red/(img.getHeight()*img.getWidth()));
			return mc;
		}
		else if(green>red && green>blue){
			MaxColor mc = new MaxColor(Color.green, green/(img.getHeight()*img.getWidth()));
			return mc;    		
		}
		else{
			MaxColor mc = new MaxColor(Color.blue, blue/(img.getHeight()*img.getWidth()));
			return mc;
		}

	}


}

