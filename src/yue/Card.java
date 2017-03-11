package yue;
// "1" means "Heart", "2" means "Spade"
// "3" means "Diamond", "4" means "Club"


public class Card {
	
	int Num;
	String suits;

	public void Input(int a)
	{
		int t;
		t=a%4;
		
		if(t>0)
		{
			Num=(a-t)/4+1;
		}
		else
		{
			Num=a/4;
		}
		
			if (t==1)
			{
				suits="Heart";
			}
			
			else if (t==2)
			{
				suits="Spade";
			}
			
			else if (t==3)
			{
				suits="Diamond";
			}
			
			else
			{
				suits="Club";
			}
			
			if (a==53)
			{
				Num=20;
				suits="SmallJoker";
			}
			
			if(a==54)
			{
				Num=30;
				suits="BigJoker";
			}
	}
	
	// The sequence of cards decided by this function is "AAAA,2222,3333,……,QQQQ,KKKK",which is ordered by "1234".
	// 1,2,3,4 represents the suits defined at the top.
	// a should range from 1 to 54.
	
}
