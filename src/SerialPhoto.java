import java.io.File;
import java.util.HashSet;

public class SerialPhoto {

	HashSet<Photo> img;
	String[] noms;
	File data;
	File NomsString;

	public SerialPhoto(HashSet<Photo> img) {
		this.img=img;
		this.data = new File("photo.dat");
	}


//	public void serialPhoto() {
//		try {
//			FileOutputStream fos = new FileOutputStream(data);
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//
//			oos.writeObject(this.img);
//
//			oos.close();
//			fos.close();
//		}catch (IOException e) {
//			throw new RuntimeException("Impossible d'écrire les données");
//		}
//	}
//	
//	public HashSet<Photo> deserialPhoto() {
//		try {
//			FileInputStream fis = new FileInputStream(data);
//			ObjectInputStream ois = new ObjectInputStream(fis);
//
//			HashSet<Photo> SetReturn = new HashSet<Photo>();
//			SetReturn = (HashSet<Photo>) ois.readObject();
//
//			ois.close();
//			fis.close();
//			return SetReturn;
//		}catch (IOException | ClassNotFoundException e) {
//			throw new RuntimeException("Lecture des données impossible");
//		}
//	}
//	
	


}
