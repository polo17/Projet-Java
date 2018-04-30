package Modele;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class Photo implements Serializable {	
	
	public String nom;
	
	public int taille;
	
	public String date;
	
	public Color couleur;
	
	public ArrayList<String> tags;
	
	public int note;
	
	public BufferedImage img;
	
	byte[] bufferedToByte;
	
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
	
	public void rmTags() {
		this.tags.clear();
	}
	
	public void setNote(int n){
		this.note = n;
	}
	
	public void setTag(String t) {
		this.tags.add(t);
	}
	
	
	public int getNote() {
		return this.note;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public int getTaille() {
		return this.taille;
	}
	
	public Color getColor() {
		return this.couleur;
	}
	
	public ArrayList<String> getTags(){
		return this.tags;
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
