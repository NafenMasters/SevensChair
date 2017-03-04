import java.util.ArrayList;
import java.util.Arrays;


public class Combo {
	Card[] cards;
	
	public Combo(){
		
	}
	
	public int getLength(){
		return cards.length;
	}
	
	public void addCard(Card c){
		cards[cards.length]=c;
	}
	
	public void sort(){
		Arrays.sort(cards, new CardComparator());
	}
}
