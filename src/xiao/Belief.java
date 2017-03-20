package xiao;

import chair.Combo;
import chair.Deck;

public class Belief {
	
	private int num;
	private double power; //from 0 to 1   first round 0.3
	private double joker; //from 0 to 1   first round 0.3
	
	public Belief(int n, int p ,int j){
		num=n;
		power=p;
		joker=j;
	}
	
	public Belief(){
		num=7;
		power=0.3;
		joker=0.3;
	}
	
	public void adapt(Combo c){
		num-=c.size();
		
	}
	
	public void refresh(int t){
		
	}
	
	public Combo play(Combo enemyCombo,int roundPoints){
		
		return enemyCombo;
		
	}
	
}
