package yue;

import java.util.ArrayList;

public class Combo {

	int Level;
	int NumberofCards;//Number of Cards will be generated after copy.
	int Score;
	Card[] cards=new Card[7];
	String Category;
	
	//copy the Combo and dicide the number of cards
	public void copy(Card c[])
	{
		int len;
		len=c.length;
		for(int i=0;i<len;i++)
		{
			
			cards[i]=c[i];
		}
		NumberofCards=c.length;
	}
	
	
	//To examine whether it contains KA or not
	public boolean KAExamine(Card c[])
	{
		boolean decision;
		decision=false;
		Operation set=new Operation();
		set.Sort(c);
		int len;
		len=c.length;
		
		if(c[0].Num==1&&c[len-1].Num==13)
		{
			decision=true;
		}
		
		return decision;
	}
	
	//To examine whether the combo is a series of not
	public boolean SeriesJudgement(Card c[])
	{
		boolean u;
		u=true;
		
		boolean KA;
		KA=KAExamine(c);
		
		//When KA is contained in the series, the value of A will be changed to 14 from 1
		Operation set=new Operation();
		set.Sort(c);
		int len;
		len=c.length;
		if (KA)
		{
			c[0].Num=14;
			set.Sort(c);
		}
		
		if(len==1)
		{
			u=false;
		}
		else 
		{
			for(int i=0;i<len-1;i++)
		{
				//The Numbers of two jokers are 20 and 30 respectively which will tell that they are not a mixed series.
			if((c[i+1].Num-c[i].Num)!=1)
			{
				u=false;
				break;
			}
		}
		}
		
		if (KA)
		{
			for(int i=1;i<len;i++)
			{		
				if(c[i].Num==14)
				{			
					c[i].Num=1;
					set.Sort(c);
				}
			}
			}
		
		return u;
	}
	
	//To examine whether the A is contained in the combo
	public boolean AExamine(Card c[])
	{
		boolean decision;
		decision=false;
		
		Operation set=new Operation();
		set.Sort(c);
		int len;
		len=c.length;
		
		for(int i=0;i<len;i++)
		{
			if(c[i].Num==1)
			{
				decision=true;
				break;
			}
		}
		
		return decision;
	}
	
	//To examine whether 2 is contained in the combo
	public boolean TwoExamine(Card c[])
	{
		boolean decision;
		decision=false;
		
		Operation set=new Operation();
		set.Sort(c);
		int len;
		len=c.length;
		
		for(int i=0;i<len;i++)
		{
			if(c[i].Num==2)
			{
				decision=true;
				break;
			}
		}
		
		return decision;
	}
	
	public int HeartTwoExamine(Card c[])
	{
		int number=0;
		
		Operation set=new Operation();
		set.Sort(c);
		int len;
		len=c.length;
		for(int i=0;i<len;i++)
		{ 
			if(c[i].Num==2&&c[i].suits=="Heart")
			{ 
				number++;
			}
		}
		
		return number;
	}
	
	
	
	//To judge whether the combo is pair or not
	public boolean PairJudgement(Card c[])
	{
		boolean decision;
		decision=true;
		int len;
		len=c.length;
		for(int i=0;i<len-1;i++)
		{
			if(c[i].Num!=c[i+1].Num)
			{
				decision=false;
				break;
			}
		}
		
		return decision;
	}
	
	public void Output(Card c[],int n)
	   {
		   System.out.print("\n");
		   for(int i=0;i<n;i++)
		   {
			   if(c[i].suits=="Heart")
			   {
				   System.out.print("♥️");
			   }
			   else if(c[i].suits=="Spade")
			   {
				   System.out.print("♠️");
			   }
			   else if(c[i].suits=="Diamond")
			   {
				   System.out.print("♦️");
			   }
			   else if(c[i].suits=="Club")
			   {
				   System.out.print("♣️");
			   }
			   
			   
			   if(c[i].Num<11&&c[i].Num>1)
			   {
				   System.out.print(c[i].Num);
			   }
			   else if(c[i].Num==1||c[i].Num==14)
			   {
				   System.out.print("A");
			   }
			   else if(c[i].Num==15)
			   {
				   System.out.print("2");
			   }
			   else if(c[i].Num==11)
			   {
				   System.out.print("J");
			   }
			   else if(c[i].Num==12)
			   {
				   System.out.print("Q");
			   }
			   else if(c[i].Num==13)
			   {
				   System.out.print("K");
			   }
			   else if(c[i].Num==20)
			   {
				   System.out.print("小鬼");
			   }
			   else if(c[i].Num==30)
			   {
				   System.out.print("大鬼");
			   }
			   
			   System.out.print("  ");	   
		   }
	   System.out.print("\n");
	   }
	public void Output2(ArrayList<Card> c)
	   {
		   int n;
		   n=c.size();
		   System.out.print("\n");
		   for(int i=0;i<n;i++)
		   {
			   if(c.get(i).suits=="Heart")
			   {
				   System.out.print("♥️");
			   }
			   else if(c.get(i).suits=="Spade")
			   {
				   System.out.print("♠️");
			   }
			   else if(c.get(i).suits=="Diamond")
			   {
				   System.out.print("♦️");
			   }
			   else if(c.get(i).suits=="Club")
			   {
				   System.out.print("♣️");
			   }
			   
			   
			   if(c.get(i).Num<11&&c.get(i).Num>1)
			   {
				   System.out.print(c.get(i).Num);
			   }
			   else if(c.get(i).Num==1||c.get(i).Num==14)
			   {
				   System.out.print("A");
			   }
			   else if(c.get(i).Num==15)
			   {
				   System.out.print("2");
			   }
			   else if(c.get(i).Num==11)
			   {
				   System.out.print("J");
			   }
			   else if(c.get(i).Num==12)
			   {
				   System.out.print("Q");
			   }
			   else if(c.get(i).Num==13)
			   {
				   System.out.print("K");
			   }
			   else if(c.get(i).Num==20)
			   {
				   System.out.print("小鬼");
			   }
			   else if(c.get(i).Num==30)
			   {
				   System.out.print("大鬼");
			   }
			   
			   System.out.print("  ");	   
		   }
	   System.out.print("\n");
	   }
	
	//To calculate the score of each Combo
	public void ScoreCalculation(Card c[])
	{
		Score=0;
	}
	
}
