package yue;
import java.util.*;

public class Single extends Combo
{
	int Level=0;
	
	public void ScoreCalculation(Card c[])
	{
		super.ScoreCalculation(c);
		cards[0]=c[0];
		NumberofCards=1;
		Category="Single";
		if(c[0].Num==1)
		{
			Score+=14;
		}
		
		else if(c[0].Num==2)
		{
			Score+=15;
		}
		
		else 
		{
			Score+=c[0].Num;
		}
	}
}
