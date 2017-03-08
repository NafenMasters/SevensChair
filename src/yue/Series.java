package yue;

public class Series extends Combo
{ 
	// To decide whether the series is mixed. False means mixed. True means that they are in the same suit.
	public boolean HorM (Card c[])
	{
		boolean decision;
		decision=true;
		String Suit;
		Suit=c[0].suits;
		int len;
		len=c.length;
		
		for(int i=1;i<len;i++)
		{
			if(!(Suit==c[i].suits))
			{
				decision=false;
				break;
			}
		}
		
		return decision;
	}
	
	
}
