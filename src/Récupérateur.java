import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Récupérateur {
 
    private File file = null;
 
    public Récupérateur(String filename) {
        file = new File(filename);
    }
 
    //Lire la date du fichier
    public long getDate() {
        return this.file.lastModified();
    }
 
    //Afficher la date du fichier
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
 
}

