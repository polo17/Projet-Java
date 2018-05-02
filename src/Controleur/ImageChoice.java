package Controleur;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImageChoice {
	

	final JFileChooser fc;
	public int result;
	
	/*
	 * Classe utile pour le JOptionPane d'import d'image
	 */
	public ImageChoice() {
		this.fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		this.result = fc.showOpenDialog(new JFrame());
	}
	
	/*
	 * Retourne le fichier selectionné
	 */
	
	public File imageSelected() {
		if(this.result == JFileChooser.APPROVE_OPTION) {
			File f = this.fc.getSelectedFile();
			return f;
		}
		else return null;
	}
	
	
	/*
	 * Test si l'image est au format approprié 
	 */
	public boolean fileIsNotIsNoGood(File f) {
		String extension = Recuperateur.getFileExtension(f);
		if(extension.equals("jpg") || extension.equals("jpeg")) {
			return true;
		}
		else return false;
	}

}
