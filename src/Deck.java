import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Deck extends Stack<Card> {

	//�ƶ���ջ��ʵ�֣��������ƱȽ�����
	
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
		add(new Card(14,1));
		add(new Card(14,1));
		add(new Card(15,1));
		add(new Card(15,1));
		
		Collections.shuffle(this);
	}
	
	public int getLeftNum(){
		return size();
	}
	
	//����
	public void deal(Player p, int num){
		
		if(num > size()){
			num=size();
		}
		
		//Ҫ��������
		ArrayList<Card> giveout=new ArrayList();
		
		for(int i=0;i<num; i++){
			giveout.add(pop());
		}
		
		p.startRound(giveout);
		
	}
}
