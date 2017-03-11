package yue;

public class Pile {

	Card[] c=new Card[108];
	
	public void StartGame() // Initialize the beginning status of the cards pile
	{
		int[] a=new int[108];
		Card[] TempCard=new Card[108];
		Operation set=new Operation();
		
		Pack p1=new Pack();
		Pack p2=new Pack();
		for(int i=0;i<108;i++)
		{
			c[i]=new Card();
			TempCard[i]=new Card();
		}
		
		//Generate two packs in order
		p1.InitPack();
		p2.InitPack();
		
		a=set.RandSeries();
		set.CopyCards(TempCard, p1, p2);
		
	//	set.Output(TempCard,108);

		//c[0]=TempCard[0];
	
		
		for(int i=0;i<108;i++)
		{
			int t=a[i]-1;
			//System.out.print(t);
			//System.out.print("\n");
			c[i]=TempCard[t];
			/*System.out.print(c[i].Num);
			System.out.print("   ");
			System.out.print(c[i].suits);
			System.out.print("\n");
		*/
		}
		
		
	}
	
	
}
