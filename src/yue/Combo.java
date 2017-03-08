package yue;

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
			if((c[i+1].Num-c[i].Num)!=1)
			{
				u=false;
				break;
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
	
	//To calculate the score of each Combo
	public void ScoreCalculation(Card c[])
	{
		Score=0;
	}
	
}
