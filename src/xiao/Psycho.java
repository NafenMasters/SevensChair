package xiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import chair.Card;
import chair.Combo;

public class Psycho extends Noob{
	
	private int roundPoints;
	
	public Psycho(String name){
		myName = name;
	}
	
	public Psycho(){
		myName = "XIAO";
	}
	
	@Override
	public Combo play(Combo enemyCombo) {
		roundPoints+=enemyCombo.points();
		int e=enemyCombo.abs();
		ArrayList<Combo> my_combos = Analyst.quickAnalyze(this.handCards);
		Collections.sort(my_combos,new ComboAbsComparator());
		if(my_combos.size()==0){
			
			return new Combo();
			
		}
		Combo c;
		int m=my_combos.get(my_combos.size()-1).abs();
		Iterator<Combo> iter=my_combos.iterator();
		
		/*
		if(Math.random()>0.9){
			if(iter.hasNext()){
				iter.next();
			}
		}
		if(Math.random()>0.9){
			if(iter.hasNext()){
				iter.next();
			}
		}
		*/
		
		for(;iter.hasNext();){
			c=iter.next();
			if(c.points()>=50&&c.size()<2 && c.abs()>e){
				if(m>8000){
					deleteList(handCards,c); 
					roundPoints+=c.points();
					return c;
				}else{
					continue;
				}
			}
			if((c.abs()<10000 ||roundPoints>40) && c.abs()>e){
				deleteList(handCards,c); 
				roundPoints+=c.points();
				return c;
			}
		}
		if(e!=0)
			return new Combo();
		for(iter=my_combos.iterator();iter.hasNext();){
			c=iter.next();
			
			if(c.abs()>e){
				deleteList(handCards,c); 
				roundPoints+=c.points();
				return c;
			}
		}
		return new Combo();
	}
	
	@Override
	public void startRound(ArrayList<Card> newCards) {
		super.startRound(newCards);
		roundPoints=0;
		// Literally Nothing Else
		
	}

}
