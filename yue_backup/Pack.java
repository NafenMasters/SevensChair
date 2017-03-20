package yue;

public class Pack // To generate a pack of cards according to order
{
    Card[] cards=new Card[54];
	
    public void InitPack()
    {
    	int i;
    
    for(i=0;i<54;i++)
    {cards[i]=new Card();
    	cards[i].Input(i+1);
    }
    
    }
    
    public void Output()
    {
    	int i;
    	
    	for(i=0;i<54;i++)
    	{System.out.print(cards[i].suits);
    		System.out.print("  ");
    		System.out.print(cards[i].Num);
    		System.out.print("\n");
    	}
    	System.out.print("\n");
    }
	
}
