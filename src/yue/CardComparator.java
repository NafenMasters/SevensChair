package yue;

import java.util.Comparator;

public class CardComparator implements Comparator<Card>
{

	@Override
	public int compare(Card o1, Card o2) {
		// TODO Auto-generated method stub
		if(o1.Num>o2.Num)
		{
			return 1;
		}
		else if(o1.Num<o2.Num)
		{
			return -1;
		}
		return 0;
	}

}
