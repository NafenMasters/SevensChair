package yue;

public class HomoPair extends Pair
{
	public void ScoreCalculation(Card c[])
	{
		super.ScoreCalculation(c);
		Operation set=new Operation();
		set.Sort(c);
		copy(c);
		Level=NumberofCards;
		Category="HomogeneousPair";
		boolean A;
	    boolean Two;
	    
	    A=AExamine(c);
	    Two=TwoExamine(c);
	
	    if(A)
	    {
	    	for(int i=0;i<NumberofCards;i++)
	    	{
	    		c[i].Num=14;
	    	}
	    }
	    
	    if(Two)
	    {
	    	for(int i=0;i<NumberofCards;i++)
	    	{
	    		c[i].Num=15;
	    	}
	    }
	    
		Score+=1000+1000*(Level-2)+200;
		for(int i=0;i<NumberofCards;i++)
	{
		Score+=c[i].Num;
	}
		
		set.Sort(c);
		copy(c);
	}
	}
