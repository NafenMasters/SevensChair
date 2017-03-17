package xiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import chair.Card;
import chair.Combo;
import chair.Player;

public class Noob extends chair.Dummy {


	public Noob(String name){
		myName = name;
	}
	
	public Noob(){
		myName = "XIAO";
	}
	
	@Override
	public Combo play(Combo enemyCombo) {
		int e=enemyCombo.abs();
		ArrayList<Combo> my_combos = Analyst.quickAnalyze(this.handCards);
		Collections.sort(my_combos,new ComboAbsComparator());
		Combo c;
		for(Iterator<Combo> iter = my_combos.iterator();iter.hasNext();){
			c=iter.next();
			if(c.abs()>e){
				deleteList(handCards,c); 
				
				return c;
			}
		}
		return new Combo();
	}

	public static boolean deleteList(ArrayList<Card> a,List<Card> cards){
		if(cards==null||cards.isEmpty() ){
			return true;
		}
		if(a==null)
			return false;
		for(Iterator<Card> iter = cards.iterator();iter.hasNext();){
			if(!a.remove(iter.next())){
				return false;
			}
		}
		return true;
	}
	

}
