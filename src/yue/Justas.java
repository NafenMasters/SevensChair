package yue;

import java.util.ArrayList;

public class Justas {

	int Points;
	ArrayList<Card> HandCards;
	HandCombo hc;
	String name;
	
	public void Name(String a)
	{
		name=a;
	}
	
	public void Initialize()
	{
		Points=0;
		HandCards=new ArrayList<Card>();
	}
	
	//The player will get the card from p.c[i] and return the number of cards the player get
	public int GetHandCards(Pile p, int i)
	{
		
		int Remaining;
		Remaining=108-i;
		int length;
		length=HandCards.size();
		int Number;
		Number=Math.min(7-length,Remaining);
		for(int j=0;j<Number;j++)
		{
			HandCards.add(p.c[i+j]);
		}
		return Number;
	}
	
	public void Remove(Combo bestcombo)
	{
		for(int i=0;i<bestcombo.NumberofCards;i++)
		{
			for(int j=0;j<this.HandCards.size();j++)
			{
				if((this.HandCards.get(j).Num==bestcombo.cards[i].Num)&&(this.HandCards.get(j).suits==bestcombo.cards[i].suits))
				{
					this.HandCards.remove(j);
					break;
				}
			}
		}
	}
	
	public Combo play(Combo enemycombo)
	{
		Combo c=new Combo();
		c.Level=-1;
		c.NumberofCards=0;
		c.Score=-1000;
		c.Category="Unset";
		Operation set=new Operation();
		int length;
		length=HandCards.size();
		Card[] card=new Card[length];
		for(int i=0;i<length;i++)
		{   card[i]=new Card();
			card[i]=HandCards.get(i);
		}
		hc=new HandCombo();
		hc.GetHandCombo(card, length);
		hc.sort();
		hc.delete();
		Combo bestcombo=new Combo();
		bestcombo=hc.BestCombo();
		boolean compare;
		compare=set.ComboBattle(bestcombo, enemycombo);
		if(compare)
		{
			c=bestcombo;
			this.Remove(bestcombo);
		}
		return c;
	}
	
}
