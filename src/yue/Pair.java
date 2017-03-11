package yue;

public class Pair extends Combo
{
	public boolean HorMP (Card c[])
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
