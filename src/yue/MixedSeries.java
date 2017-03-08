package yue;
import java.util.*;

public class MixedSeries extends Series
// Mixed Series
{	
	
	public void ScoreCalculation(Card c[])
	{
		Level=1;
		super.ScoreCalculation(c);
		Operation set=new Operation();
		set.Sort(c);
		copy(c);
		Category="MixedSeries";
		boolean KA;
		
		KA=KAExamine(c);
		if(KA)
		{
			c[0].Num=14;
		}
		
		Score+=100+(NumberofCards-2)*50;
		for(int i=0;i<NumberofCards;i++)
		{
			Score+=c[i].Num;
		}
		
		set.Sort(c);
		copy(c);
	}
}
