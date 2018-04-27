import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Photo implements Serializable {	
	
	String nom;
	
	int taille;
	
	String date;
	
	Color couleur;
	
	ArrayList<String> tags;
	
	int note;
	
	BufferedImage img;
	
	byte[] bufferedToByte;
	
	//TODO Faire l'ajout de tag et le tri

	/**
	 * @param n Nom de la photo
	 * @param t Taille de photo
	 * @param d Date de la photo
	 * @param color Couleur dominante de la photo
	 * @param im L'image de la photo
	 */
	public Photo(String n, int t, String d, Color color, BufferedImage im){
		this.nom = n;
		this.taille = t;
		this.date = d;
		this.couleur = color;
		this.tags = new ArrayList<String>();
		this.note = 0;
		this.img=im;
	}
	
	public void setNote(int n){
		this.note = n;
	}
	
	public void setTag(String t) {
		this.tags.add(t);
	}
	
	public String Nom() {
		return this.nom;
	}
	
	public int getNote() {
		return this.note;
	}
	
	public void toByte() throws IOException {
		try {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(img, "jpg", baos);
		baos.flush();
		this.bufferedToByte = baos.toByteArray();
		baos.close();
		this.img=null;
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void toBuffered() {
		try {
			InputStream ips = new ByteArrayInputStream(this.bufferedToByte);
			this.img = ImageIO.read(ips);
			
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

}
