package yue;

import java.util.Comparator;

public class SpecialCardComparator  implements Comparator<Card>
{
	public int compare(Card c1,Card c2)
	 {
		 if((c1.Num!=1)&&(c2.Num!=1)&&(c1.Num>c2.Num))
		 {
			 return 1;
		 }
		 else if((c1.Num!=1)&&(c2.Num!=1)&&(c1.Num<c2.Num))
		 {
			 return -1;
		 }
		 else if(c1.Num==1)
		 {
			 return 1;
		 }
		 else if(c2.Num==1)
		 {
			 return -1;
		 }
		 
			 return 0;

		 }
}
