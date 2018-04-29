package Controleur;

import java.util.Comparator;

import Modele.Photo;

public class ComparerDate implements Comparator<Object> {
	public int compare(Object p1, Object p2) {
		int result =  ((Photo)p1).date.compareTo(((Photo)p2).date);
		if(result == 0){
			result = ((Photo)p1).nom.compareTo(((Photo)p2).nom);
		}
		return result;
	}
}