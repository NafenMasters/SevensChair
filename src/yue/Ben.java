package yue;

import java.util.ArrayList;

import chair.Card;
import chair.Combo;
import chair.Player;

public class Ben implements Player
{

	protected Felipe f;
	
	protected ArrayList<chair.Card> handcards=new ArrayList<chair.Card>();
	protected int points;
	protected String myname;
	
	public Ben(String name)
	{
		this.myname=name;
	}
	
	//from chair to yue
	public void cardtrans(chair.Card c1, yue.Card c2)
	{
		int num;
	    int suit;
	    num=c1.getNumber();
	    suit=c1.getSuit();
	    int input;
	    if(suit<5)
	    {
	    	input=4*(num-1)+suit;
	    }
	    else
	    {
	    	if(num==99)
	    	{
	    		input=53;
	    	}
	    	else
	    	{
	    		input=54;
	    	}
	    }
		c2.Input(input);
	}
	
	//from yue to chair
	public chair.Card cardtransback(yue.Card c1)
	{
		
			int u;
			if(c1.suits=="Heart")
			{
				u=1;
			}
			else if(c1.suits=="Spade")
			{
				u=2;
			}
			else if(c1.suits=="Diamond")
			{
				u=3;
			}
			else if(c1.suits=="Club")
			{
				u=4;
			}
			
			else
			{
				u=5;
			}
			
			int n;
			
			if(c1.Num==20)
			{
				n=99; //////////
			}
			else if(c1.Num==30)
			{
				n=100;
			}
			else
			{
				n=c1.Num;
			}
			
			chair.Card chaircard=new chair.Card(n,u);
			//c2=chaircard;
			return chaircard;
	}
	
	//from chair to yue
	public yue.Combo combotrans(chair.Combo c1)
	{
		int length;
		length=c1.size();
		yue.Card[] card=new yue.Card[length];
		for(int i=0;i<length;i++)
		{
			card[i]=new yue.Card();
			this.cardtrans(c1.get(i), card[i]);
		}
		Operation set=new Operation();
		return set.Scores(card);
		//System.out.println(c2.Score);
	}
	
	//Remove card (whose mode is chair) from hand cards
	public void Remove(chair.Card c)
	{
		int length;
		length=this.handcards.size();
		for(int i=0;i<length;i++)
		{
			if((c.getNumber()==this.handcards.get(i).getNumber())&&(c.getSuit()==this.handcards.get(i).getSuit()))
			{
				this.handcards.remove(c);
				break;
			}
		}
	}
	
	
	@Override
	public ArrayList<chair.Card> getHandCards() {
		// TODO Auto-generated method stub
		return this.handcards;
	}

	@Override
	public int getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}

	@Override
	public void startGame(int order) {
		// TODO Auto-generated method stub
		this.f=new Felipe();
		this.points=0;
		this.handcards=new ArrayList<chair.Card>();
	}

	@Override
	public void startRound(ArrayList<Card> newCards) {
		// TODO Auto-generated method stub
		this.handcards.addAll(newCards);
		
		int length;
		length=newCards.size();
		yue.Card[] c=new yue.Card[length];
		for(int i=0;i<length;i++)
		{
			c[i]=new yue.Card();
			this.cardtrans(newCards.get(i), c[i]);
			f.GetOwnCards(c[i]);
		}
	}

	@Override
	public Combo play(chair.Combo enemyCombo) {
		// TODO Auto-generated method stub
		
		yue.Combo c=new yue.Combo();
		yue.Combo enemy=new yue.Combo();
		
		enemy=this.combotrans(enemyCombo);
		c=f.play(enemy);
		
		chair.Combo turn=new chair.Combo();
		chair.Card card=new chair.Card(1, 1);
		
		//System.out.print("ahahhasdaksdasd");
		for(int i=0;i<c.NumberofCards;i++)
		{
			card=this.cardtransback(c.cards[i]);
			turn.add(card);
			this.Remove(card);
		}
		
		return turn;
	}

	@Override
	public void endGame(int gameResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLeftNum() {
		// TODO Auto-generated method stub
		return this.handcards.size();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.myname;
	}

}
