package chair;
import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
	  public int compare(Card c1, Card c2) {
		  if(c1.getNumber()>c2.getNumber()){
			  return 1;
		  }else if(c1.getNumber()<c2.getNumber()){
			  return -1;
		  }else{
			  if(c1.getSuit()>c2.getSuit()){
				  return 1;
			  }else if(c1.getSuit()<c2.getSuit()){
				  return -1;
			  }else{
				  return 0;
			  }
		  }
	  }
}
