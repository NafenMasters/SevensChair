package xiao;
import java.util.Comparator;
import chair.Combo;
public class ComboAbsComparator implements Comparator<Combo> {

	@Override
	public int compare(Combo arg0, Combo arg1) {
		int a,b;
		a=arg0.abs();
		b=arg1.abs();
		if(a>b){
			return 1;
		}else if(a<b){
			return -1;
		}else{
			return 0;
		}
	}

}
