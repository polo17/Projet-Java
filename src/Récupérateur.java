import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
 
/**
 * Lire les propriétés d'un fichier
 * http://www.fobec.com/java/965/lire-proprietes-fichier.html
 * @author Fobec 2010
 */
public class Récupérateur {
 
    private File file = null;
 
    /**
     * Contructeur
     * @param filename dossier+nom du fichier
     */
    public Récupérateur(String filename) {
        file = new File(filename);
    }
 
    /**
     * Lire la date du fichier
     * @return
     */
    public long getDate() {
        return this.file.lastModified();
    }
 
    /**
     * Afficher la date du fichier
     * @return date au format dd/m/yyyy H:mm:ss
     */
    public String getFormatedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
        Date d = new Date(this.file.lastModified());
        return sdf.format(d);
    }
 
    /**
     * Taille du fichier
     * @return unité byte
     */
    public Long getSize() {
        return this.file.length();
    }
 
    /**
     * Afficher la taille
     * @return taille au format xx Ko ou xx Mo
     */
    public String getFormatedSize() {
        int size = (int) (this.file.length() / 1024) + 1;
        if (size > 1024) {
            return (size / 1024) + " Mo";
        } else {
            return size + " ko";
        }
    }
 
    /**
     * Type du fichier
     * @return par exemple Image bitmap
     */
    public String getType() {
        JFileChooser chooser = new JFileChooser();
        return chooser.getTypeDescription(this.file);
    }
 
    /**
     * Extraire l'extension du fichier
     * @param filename
     * @return format .xxx
     */
    public static String getFileExt(String filename) {
        int pos = filename.lastIndexOf(".");
        if (pos > -1) {
            return filename.substring(pos);
        } else {
            return filename;
        }
    }  
    
    public static void main(String[] args) {
    	  String file = "C:fichierlog.txt";
    	  Récupérateur fileProperty = new Récupérateur(file);
    	  System.out.println("Propriété du fichier: " + file);
    	  System.out.println("Date: " + fileProperty.getFormatedDate());
    	  System.out.println("Taille: " + fileProperty.getFormatedSize());
    	  System.out.println("Type: " + fileProperty.getType());
    	  System.out.println("Extension: " + Récupérateur.getFileExt(file));
    	}
    
}

