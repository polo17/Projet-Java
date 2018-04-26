import java.util.Comparator;

public class ComparerDate implements Comparator {
	public int compare(Object p1, Object p2) {
		int result =  ((Photo)p1).date.compareTo(((Photo)p2).date);
		if(result == 0){
			result = ((Photo)p1).nom.compareTo(((Photo)p2).nom);
		}
		return result;
	}
}