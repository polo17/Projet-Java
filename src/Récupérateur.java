import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Récupérateur {

	private File file = null;

	public Récupérateur(String filename) {
		file = new File(filename);
	}

	//Afficher la date
	public String getFormatedDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
		Date d = new Date(this.file.lastModified());
		return sdf.format(d);
	}

	//Afficher la taille
	public int getFormatedSize() {
		int size = (int) (this.file.length() / 1024) + 1;
		return size;
	}


	public static MaxColor getMainColor(BufferedImage img){
		int red = 0;
		int green = 0;
		int blue = 0;
		for(int i = 0; i<img.getHeight(); i++){
			for(int j =0; j<img.getWidth(); j++){
				Color mycolor = new Color(img.getRGB(i, j));
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

