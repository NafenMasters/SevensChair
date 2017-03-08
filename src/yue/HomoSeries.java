package yue;

public class HomoSeries extends Series
{
	public void ScoreCalculation(Card c[])
	{	
		super.ScoreCalculation(c);
		Operation set=new Operation();
		set.Sort(c);
		copy(c);
		Level=NumberofCards;
		Category="HomogeneousSeries";
		boolean KA;
	
		KA=KAExamine(c);
	
		if(KA)
	{
		c[0].Num=14;
	}
	
		Score+=1000+1000*(Level-2)+100;
		for(int i=0;i<NumberofCards;i++)
	{
		Score+=c[i].Num;
	}
		
		set.Sort(c);
		copy(c);
	}
}
