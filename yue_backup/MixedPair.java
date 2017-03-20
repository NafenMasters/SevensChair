package yue;

public class MixedPair extends Pair
{
	public void ScoreCalculation(Card c[])
	{	
		super.ScoreCalculation(c);
		Operation set=new Operation();
		set.Sort(c);
		copy(c);
		Level=NumberofCards;
		Category="MixedPair";
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
	    
		Score+=1000+1000*(Level-2);
		for(int i=0;i<NumberofCards;i++)
	{
		Score+=c[i].Num;
	}
		
		//To avoid the Heart 2 situations(the value of 2 had been changed to 15 which will lead a score misjudgement of the case of one Heart 2 and one diamond 2)
		  if(Two)
		    {
		    	for(int i=0;i<NumberofCards;i++)
		    	{
		    		c[i].Num=2;
		    	}
		    }
		  
		  if(A)
		    {
		    	for(int i=0;i<NumberofCards;i++)
		    	{
		    		c[i].Num=1;
		    	}
		    }
		
		set.Sort(c);
		copy(c);
	}
}
