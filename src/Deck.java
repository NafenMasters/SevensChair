import java.util.ArrayList;

public class Deck {

	ArrayList cards;
	
	public void Deck(){
		cards=new ArrayList();
		for(int i=1;i<14;i++){
			Card c= new Card(i, 1);
			cards.add(c);
			c= new Card(i, 2);
			cards.add(c);
			c= new Card(i, 3);
			cards.add(c);
			c= new Card(i, 4);
			cards.add(c);
			
		}
	}
	
	public void shuffle(){
		
	}
	
	public int getLeftNum(){
		return cards.size();
	}
	
	//make sure num<=leftnum
	public void deal(Player p, int num){
		Card[] out;
		int leftNum=this.getLeftNum();
		for(int i=0;i<num; i++){
			cards[leftNum-i-1]
					cards.
		}
		p.startRound(cards[]);
	}
}
