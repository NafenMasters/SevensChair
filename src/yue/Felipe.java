package yue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Felipe {
	
	private int ownpoints;
	private int enemypoints;
	private ArrayList<Card> remainingCards;
	private ArrayList<Card> handCards;
	private HandCombo handcombo;
	private String name;
	//to tell whether joker has been played in a round
	boolean joker;

	public Felipe()
	{	
		this.ownpoints=0;
		this.enemypoints=0;
		this.remainingCards=new ArrayList<Card>();
		this.handCards=new ArrayList<Card>();
		this.name="Felipe";
		this.joker=false;
		
		//while initializing, the remaining cards are two intact packs.
		for(int i=0;i<54;i++)
		{
			Card c=new Card();
			c.Input(i+1);
			this.remainingCards.add(c);
			this.remainingCards.add(c);
		}
	}
	
	public void RemoveRemainingCards(Card c)
	{
		int length;
		length=this.remainingCards.size();
		for(int i=0;i<length;i++)
		{
			if(this.remainingCards.get(i).Num==c.Num&&this.remainingCards.get(i).suits==c.suits)
			{
				this.remainingCards.remove(i);
				break;
			}
		}
	}
	
	public void AddPoints(int mypoints, int yourpoints)
	{
		this.ownpoints+=mypoints;
		this.enemypoints+=yourpoints;
	}
	
	
	//get cards from the pile
	public void GetOwnCards(Card c)
	{
		this.handCards.add(c);
		//System.out.println(c.suits+c.Num);
		this.RemoveRemainingCards(c);
	}
	
	public void ReattainOwnCards(Card c)
	{
		this.handCards.add(c);
	}
	
	public int GetHandCards(Pile p, int i)
	{
		
		int Remaining;
		Remaining=108-i;
		int length;
		length=this.handCards.size();
		int Number;
		Number=Math.min(7-length,Remaining);
		for(int j=0;j<Number;j++)
		{
			this.GetOwnCards(p.c[i+j]);
		}
		return Number;
	}
	
	public ArrayList<Card> HandCard()
	{
		return this.handCards;
	}
	
	public ArrayList<Card> Remaining()
	{
		return this.remainingCards;
	}
	
	
	public int GetOwnPoints()
	{
		return this.ownpoints;
	}
	
	public int GetEnemyPoints()
	{
		return this.enemypoints;
	}
	
	public void GetName(String a)
	{
		this.name=a;
	}
	
	public String PrintName()
	{
		return this.name;
	}
	
	public void Remove(Combo playedcombo)
	{
		for(int i=0;i<playedcombo.NumberofCards;i++)
		{
			int length;
			length=this.handCards.size();
			for(int j=0;j<length;j++)
			{
				if((this.handCards.get(j).Num==playedcombo.cards[i].Num)&&(this.handCards.get(j).suits==playedcombo.cards[i].suits))
				{
					this.handCards.remove(j);
					break;
				}
			}
		}
	}
	
	public void RemoveRemaining(Combo enemycombo)
	{
		for(int i=0;i<enemycombo.NumberofCards;i++)
		{
			int length;
			length=this.remainingCards.size();
			for(int j=0;j<length;j++)
			{
				if((this.remainingCards.get(j).Num==enemycombo.cards[i].Num)&&(this.remainingCards.get(j).suits==enemycombo.cards[i].suits))
				{
					/*Card[] c=new Card[enemycombo.NumberofCards];
					for(int t=0;t<enemycombo.NumberofCards;t++)
					{
						c[t]=new Card();
						c[t]=enemycombo.cards[t];
					}
					enemycombo.Output(c, enemycombo.NumberofCards);
					*/
					this.remainingCards.remove(j);
					break;
				}
			}
		}
	}
	
	public void RemoveJoker()
	{
		int length;
		length=handCards.size();
		for(int i=0;i<length;i++)
		{
			if(this.handCards.get(i).Num>18)
			{
				this.handCards.remove(i);
				i--;
				length--;
			}
		}
	}
	
	//method under the circumstance of no joker
	public Combo nojokerplay(Combo enemycombo)
	{
		//Initialize the combo, make some praparation
		Combo c=new Combo();
		c.Level=-1;
		c.NumberofCards=0;
		c.Score=-1000;
		c.Category="Unset";
		
		Operation set=new Operation();
		int length;
		length=handCards.size();
		Card[] card=new Card[length];
		for(int i=0;i<length;i++)
		{   card[i]=new Card();
			card[i]=handCards.get(i);
		}
		
		//To find all the combo in hand and sort them.
		handcombo=new HandCombo();
		handcombo.GetHandCombo(card, length);
		handcombo.sort();
		handcombo.delete();
		Combo bestcombo=new Combo();
		bestcombo=handcombo.BestCombo();
		boolean compare;
		compare=set.ComboBattle(bestcombo, enemycombo);
		
		//When the best combo is better than the enemy's combo, we will consider whether we have a smaller choice
		if(compare)
		{
			//When our best combo is less than 3 cards
			if(bestcombo.Score<2000)
			{
				//Delete the best combo from hand card, and then sort the remaining combo to decide whether we have a smaller choice 
				this.Remove(bestcombo);
				int len;
				len=this.handCards.size();
			    Card[] card2=new Card[len];
			    for(int i=0;i<len;i++)
			    {
			    	card2[i]=new Card();
				    card2[i]=handCards.get(i);
				}
			    
			    HandCombo test=new HandCombo();
			    test.GetHandCombo(card2, len);
			    test.sort();
			    test.delete();
			    Combo secondbestcombo=new Combo();
			    secondbestcombo=test.BestCombo();
			    boolean compare2;
			    compare2=set.ComboBattle(secondbestcombo, enemycombo);
			    
			    //when the second best combo is still larger than enemy's combo, play it. it is to avoid the situation that enemy combo is null and our second best combo is null too. Under such circumstance, we will just play null as our score is 0 and theirs is -1000.
			    if(compare2&&secondbestcombo.Score>1)
			    {
			    	
			    	c=secondbestcombo;
			    	//Add the cards of best combo back to hand cards 
				    for(int i=0;i<bestcombo.NumberofCards;i++)
				    {
				    	this.ReattainOwnCards(bestcombo.cards[i]);
				    }
				    this.Remove(secondbestcombo);
				}
			    
			    else
			    {
			    	c=bestcombo;
			    	}
			    }
			
			//When we have a combo consists of 3 or more cards, we will play the card from the least one we can play
			else
			{
				this.Remove(bestcombo);
				int len;
				len=this.handCards.size();
				 Card[] card2=new Card[len];
				    for(int i=0;i<len;i++)
				    {
				    	card2[i]=new Card();
					    card2[i]=handCards.get(i);
					}
				    
				    HandCombo test=new HandCombo();
				    test.GetHandCombo(card2, len);
				    test.sort();
				    test.delete();
				    
				    //When no card is left except for the cards in the best combo, we decide to play best combo.
				    if(len==0)
				    {
				    	 for(int i=0;i<bestcombo.NumberofCards;i++)
						    {
						    	this.ReattainOwnCards(bestcombo.cards[i]);
						    }
				    	 
				    	 c=bestcombo;
				    	 this.Remove(c);
				    }
				    
				    else
				    {				    
				    	Combo secondbestcombo=new Combo();
				        secondbestcombo=test.BestCombo();
				        boolean compare2;
				        compare2=set.ComboBattle(secondbestcombo, enemycombo);
				    
				    //To find out whether the best combo in the remaining cards is greater than enemy's combo.
				        if(compare2)
				        {
				        	int combosize=test.size();
				    	   c=secondbestcombo;
				    	   this.Remove(c);
				        	/* for(int i=0;i<combosize;i++)
				    	    {
				    	    	boolean compare3;
				    		    compare3=set.ComboBattle(test.get(i), enemycombo);
				    		    if(compare3)
				    		    {
				    		    	//ticked out the best combo and choose a combo in the remaining hand cards randomly
				    		    	int choices=combosize-i;
				    		    	c=test.get((int)(Math.random()*choices)+i);
				    		    	this.Remove(c);
				    			    break;
				    		    }
				    	    }*/
				        }
				    
				        for(int i=0;i<bestcombo.NumberofCards;i++)
				        {
				    	    this.ReattainOwnCards(bestcombo.cards[i]);
				        }
				    
				 /*   if(bestcombo.NumberofCards==this.handCards.size()&&enemycombo.Score<1)
				    {
				    	Card[] card3=new Card[bestcombo.NumberofCards];
					    for(int i=0;i<bestcombo.NumberofCards;i++)
					    {
					    	card3[i]=new Card();
						    card3[i]=handCards.get(i);
						}
					    
					    HandCombo test2=new HandCombo();
					    test2.GetHandCombo(card3, bestcombo.NumberofCards);
					    test2.sort();
					    test2.delete();
					    c=test2.get(0);
					    this.Remove(c);
				    }
				    
				 */  
				    }
				
			}
			
			}
		
		return c;
	}
	
	//Generate the opposite's hand card randomly and then find out whether we will catch this joker 
	public boolean jokersimulation(Combo bestcombo)
	{
		boolean compare;
		Collections.shuffle(this.remainingCards);
		//number of enemy's cards
		int NOEC;
		NOEC=Math.min(7, this.remainingCards.size());
		Card[] enemycards=new Card[NOEC];
		for(int i=0;i<NOEC;i++)
		{
			enemycards[i]=new Card();
			enemycards[i]=this.remainingCards.get(i);
		}
		
		Combo bestenemycombo=new Combo();
		HandCombo enemycombo=new HandCombo();
		enemycombo.GetHandCombo(enemycards, NOEC);
		enemycombo.sort();
		enemycombo.delete();
		bestenemycombo=enemycombo.BestCombo();
		
		Operation set=new Operation();
		compare=set.ComboBattle(bestcombo, bestenemycombo);
		return compare;
	}
	
	//draw the conclusion of whether we should play joker after several simulations
	public boolean jokerdecision(Combo bestcombo)
	{
		boolean decision;
		//Stimulation times
		
		////
		int n=1000;
		////
		
		
		int wintimes=0;
		//winning possibility
		double p;
		//decision possibility which means that we will play joker when the possibility is larger than this.
		
		////
		double DP=0.6;
		////
		
		for(int i=0;i<n;i++)
		{
			if(this.jokersimulation(bestcombo))
			{
				wintimes++;
			}
		}
		
		p=(double)wintimes/(double)n;
		if(p>DP)
		{
			decision=true;
		}
		else
		{
			decision=false;
		}
		
		return decision;
	}
	
	
	//The way to play when there is a joker occurs in the battle field
	public Combo jokerplay(Combo enemycombo)
	{
		Combo c=new Combo();
		c.Level=-1;
		c.NumberofCards=0;
		c.Score=-1000;
		c.Category="Unset";
		Operation set=new Operation();
		int length;
		length=this.handCards.size();
		Card[] card=new Card[length];
		for(int i=0;i<length;i++)
		{   card[i]=new Card();
			card[i]=this.handCards.get(i);
		}
		
		this.handcombo=new HandCombo();
		this.handcombo.GetHandCombo(card, length);
		this.handcombo.sort();
		this.handcombo.delete();
		Combo bestcombo=new Combo();
		bestcombo=this.handcombo.BestCombo();
		boolean compare;
		compare=set.ComboBattle(bestcombo, enemycombo);
		if(compare)
		{
			c=bestcombo;
			this.Remove(bestcombo);
		}
		return c;
	}
	
	public Combo regularplay(Combo enemycombo)
	{
		
		this.RemoveRemaining(enemycombo);
		//System.out.println(this.name+this.remainingCards.size());
		//enemycombo.Output2(this.remainingCards);
		
		//while the opponent has no combo to play, the round is over and we need to discard the changes;
		if(enemycombo.Score<1)
		{
			this.joker=false;
		}
		
		Operation set= new Operation();
		ArrayList<Card> enemycard=new ArrayList<Card>();
		Combo c=new Combo();
		for(int i=0;i<enemycombo.NumberofCards;i++)
		{
			enemycard.add(enemycombo.cards[i]);
		}
		
		//when joker is false, to decide whether there is a joker in this round.
		if(this.joker==false)
		{		
			this.joker=set.jokersearch(enemycard);
		}
		
		if(this.joker)
		{
			//System.out.println("a");
			c=this.jokerplay(enemycombo);
		}
		else
		{
			//System.out.println("b");
			c=this.nojokerplay(enemycombo);
		}
		
		boolean ownjoker;
		ArrayList <Card> owncombocards=new ArrayList<Card>();
		for(int i=0;i<c.NumberofCards;i++)
		{
			owncombocards.add(c.cards[i]);
		}
		ownjoker=set.jokersearch(owncombocards);
		if(ownjoker)
		{
			this.joker=true;
		}
		
		//when we have no combo to play, reset the joker
		if(c.Score<1)
		{
			this.joker=false;
		}
		
		return c;
	}
	
	public Combo play(Combo enemycombo)
	{
		//enemycombo.Output2(this.remainingCards);
		
		boolean Onlyjoker;
		Onlyjoker=false;
		Combo c=new Combo();
		Operation set=new Operation();
		boolean ownjoker;
		ownjoker=set.jokersearch(this.handCards);
		if(ownjoker)
		{
			Combo temp=new Combo();
			//Generate two cards, small joker and big joker.
			Card sj=new Card();
			Card bj=new Card();
			sj.Input(53);
			bj.Input(54);
			
			int smalljoker,bigjoker;
			int jokercase;
			smalljoker=set.smalljokersearch(this.handCards);
			bigjoker=set.bigjokersearch(this.handCards);
			if(smalljoker==1&&bigjoker==0)
			{
				jokercase=1;
			}
			else if(smalljoker==2&&bigjoker==0)
			{
				jokercase=2;
			}
			else if(smalljoker==0&&bigjoker==1)
			{
				jokercase=3;
			}
			else if(smalljoker==0&&bigjoker==2)
			{
				jokercase=4;
			}
			else if(smalljoker==1&&bigjoker==1)
			{
				jokercase=5;
			}
			else if(smalljoker==1&&bigjoker==2)
			{
				jokercase=6;
			}
			else if(smalljoker==2&&bigjoker==1)
			{
				jokercase=7;
			}
			else 
			{
				jokercase=8;
			}
			
			int length;
			length=this.handCards.size();
			Card[] card=new Card[length];
			for(int i=0;i<length;i++)
			{   card[i]=new Card();
				card[i]=this.handCards.get(i);
			}
			
			this.handcombo=new HandCombo();
			this.handcombo.GetHandCombo(card, length);
			this.handcombo.sort();
			this.handcombo.delete();
			Combo bestcombo=new Combo();
			bestcombo=this.handcombo.BestCombo();
			
			boolean Toplayjoker;
			Toplayjoker=this.jokerdecision(bestcombo);
			
			//System.out.println(smalljoker);
			//System.out.println(jokercase);
			this.RemoveJoker();
			
			if(this.handCards.size()==0)
			{
				Onlyjoker=true;
			}
			
			//enemycombo.Output2(this.handCards);
			
			switch(jokercase)
			{
			case 1:
			{
				Card[] cards=new Card[1];
				cards[0]=new Card();
				cards[0]=sj;
				temp=set.Scores(cards);
				break;
			}
			case 2:
			{
				Card[] cards=new Card[2];
				for(int j=0;j<2;j++)
				{
					cards[j]=new Card();
					cards[j]=sj;
				}
				temp=set.Scores(cards);
				break;
			}
			case 3:
			{
				Card[] cards=new Card[1];
				cards[0]=new Card();
				cards[0]=bj;
				temp=set.Scores(cards);
				break;
			}
			case 4:
			{
				Card[] cards=new Card[2];
				for(int j=0;j<2;j++)
				{
					cards[j]=new Card();
					cards[j]=bj;
				}
				temp=set.Scores(cards);
				break;
			}
			case 5:
			{
				Card[] cards=new Card[1];
				cards[0]=new Card();
				cards[0]=bj;
				temp=set.Scores(cards);
				break;
			}
			case 6:
			{
				Card[] cards=new Card[2];
				for(int j=0;j<2;j++)
				{
					cards[j]=new Card();
					cards[j]=bj;
				}
				temp=set.Scores(cards);
				break;
			}
			case 7:
			{
				Card[] cards=new Card[1];
				cards[0]=new Card();
				cards[0]=bj;
				temp=set.Scores(cards);
				break;
			}
			case 8:
			{
				Card[] cards=new Card[2];
				for(int j=0;j<2;j++)
				{
					cards[j]=new Card();
					cards[j]=bj;
				}
				temp=set.Scores(cards);
				break;
			}
			}
			
			boolean IsJokerLarger;
			IsJokerLarger=set.ComboBattle(temp, enemycombo);
			
		/*	int length;
			length=this.handCards.size();
			Card[] card=new Card[length];
			for(int i=0;i<length;i++)
			{   card[i]=new Card();
				card[i]=this.handCards.get(i);
			}
			
			this.handcombo=new HandCombo();
			this.handcombo.GetHandCombo(card, length);
			this.handcombo.sort();
			this.handcombo.delete();
			Combo bestcombo=new Combo();
			bestcombo=this.handcombo.BestCombo();
			
			boolean Toplayjoker;
			Toplayjoker=this.jokerdecision(bestcombo);
			*/
			
			if((Toplayjoker||Onlyjoker||this.remainingCards.size()==0)&&IsJokerLarger)
			{
				this.RemoveRemaining(enemycombo);
				
				this.joker=true;
				c=temp;
				switch(jokercase)
				{
				case 5:
				{
					this.ReattainOwnCards(sj);
					break;
				}
				case 6:
				{
					this.ReattainOwnCards(sj);
					break;
				}
				case 7:
				{
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(sj);
					break;
				}
				case 8:
				{
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(sj);
					break;
				}
				}
			}
			
			else
			{	
				c=this.regularplay(enemycombo);
				switch(jokercase)
				{
				case 1:
				{
					this.ReattainOwnCards(sj);
					break;
				}
				case 2:
				{
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(sj);
					break;
				}
				case 3:
				{
					this.ReattainOwnCards(bj);
					break;
				}
				case 4:
				{
					this.ReattainOwnCards(bj);
					this.ReattainOwnCards(bj);
					break;
				}
				case 5:
				{
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(bj);
					break;
				}
				case 6:
				{
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(bj);
					this.ReattainOwnCards(bj);
					break;
				}
				case 7:
				{
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(bj);
					break;
				}
				case 8:
				{
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(sj);
					this.ReattainOwnCards(bj);
					this.ReattainOwnCards(bj);
					break;
				}
				
				}
			}
			
		}
		
		else
		{
			c=this.regularplay(enemycombo);
		}
		
		//enemycombo.Output2(this.remainingCards);
		return c;
	}
	
	
}
