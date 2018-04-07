import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

public class SerialPhoto {

	HashSet<Photo> img;
	File data;

	public SerialPhoto(HashSet<Photo> images) {
		this.img=images;
		this.data = new File("photo.dat");
	}


	public void Serialisation() {
		try {
			FileOutputStream fos = new FileOutputStream(data);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(this.img);

			oos.close();
			fos.close();
		}catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données");
		}
	}

	public HashSet<Photo> Deserialisation() {
		try {
			FileInputStream fis = new FileInputStream(data);
			ObjectInputStream ois = new ObjectInputStream(fis);

			HashSet<Photo> SetReturn = new HashSet<Photo>();
			SetReturn = (HashSet<Photo>) ois.readObject();

			ois.close();
			fis.close();
			return SetReturn;
		}catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException("Lecture des données impossible");
		}
	}


}
