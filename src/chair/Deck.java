package chair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck extends Stack<Card> {

	//牌堆用栈来实现，这样发牌比较容易
	
	//generate a shuffled 108 deck
	public Deck(){
		super();
		
		for(int i=1;i<14;i++){
			
			add(new Card(i, 1));
			add(new Card(i, 1));
			add(new Card(i, 2));
			add(new Card(i, 2));
			add(new Card(i, 3));
			add(new Card(i, 3));
			add(new Card(i, 4));
			add(new Card(i, 4));
		}
		
		//Add 4 jokers
		add(new Card(99,5));
		add(new Card(99,5));
		add(new Card(100,5));
		add(new Card(100,5));
		
		Collections.shuffle(this);
	}
	
	public int getLeftNum(){
		return size();
	}
	
	//发牌
	public ArrayList<Card> deal(Player p, int num){
		
		if(num > size()){
			num=size();
		}
		
		//要发出的牌
		ArrayList<Card> giveout=new ArrayList();
		
		for(int i=0;i<num; i++){
			giveout.add(pop());
		}
		
		p.startRound(giveout);
		
		return giveout;
	}
}
