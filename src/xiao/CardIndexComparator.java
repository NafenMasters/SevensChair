package xiao;

import java.util.Comparator;

import chair.Card;

public class CardIndexComparator implements Comparator<Card> {
	
	public int compare(Card c1, Card c2) {
			 int i1,i2;
			 i1=index(c1.getNumber());
			 i2=index(c2.getNumber());
			 if(i1>i2){
				  return 1;
			  }else if(i1<i2){
				  return -1;
			  }else{
				  return 0;
			  }
			 
		  }
	private int index(int num){
		if(num==1)
			return 14;
		if(num==2)
			return 15;
		return num;
	}
}
