package yue;

import java.util.Comparator;

public class ComboComparator implements Comparator<Combo>
{
 public int compare(Combo c1,Combo c2)
 {
	 if(c1.Score>c2.Score)
	 {
		 return 1;
	 }
	 else if(c1.Score<c2.Score)
	 {
		 return -1;
	 }
	 
		 return 0;

	 }
}
