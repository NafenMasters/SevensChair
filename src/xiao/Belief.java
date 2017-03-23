package xiao;

import chair.Combo;
import chair.Deck;

public interface Belief {
	
	
	public void adapt(GameState gs, int turn);
	
	public void refresh(int t);
	
	public Combo play(GameState gs, int turn);
	
}
