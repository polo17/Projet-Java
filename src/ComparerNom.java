import java.util.Comparator;

public class ComparerNom implements Comparator<Object> {
  public int compare(Object p1, Object p2) {
    return ((Photo)p1).nom.compareTo(((Photo)p2).nom);
  }
}
