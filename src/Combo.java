import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;




public class Combo extends ArrayList<Card> {
	
	public void sort(){
		Collections.sort(this, new CardComparator());
	}
	
	//evaluate the absolute value of this combo. If invalid return 0.
	public int abs(){
		return 0;
	}
	
	//calculate points contained in this combo
	public int points(){
		return 0;
	}
	
	
}
