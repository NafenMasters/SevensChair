import java.util.ArrayList;

//1v1 AI player
public interface Player {
	
	public ArrayList<Card> getHandCards();
	
	public int getPoints();
	
	//Tell who first 1: offensive 2: defensive
	public void startGame(int order);
	
	//Receive new hand cards
	public void startRound(ArrayList<Card> newCards);
	
	//Receive enemy combo and play
	public Combo play(Combo enemyCombo);
	
	//Tell who wins the game, 1 win 0 draw -1 lose
	public void endGame(int gameResult);
	
	//Get number of hand cards 
	public int getLeftNum();
	
}
