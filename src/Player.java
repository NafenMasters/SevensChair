public interface Player {
	
	//receive 7 new cards
	public void startGame(Card[] handCards);
	
	//Receive new hand cards
	public void startRound(Card[] handCards);
	
	//Receive enemy combo and play
	public void play(Combo enemyCombo);
	
	//Tell who wins the game, 1 win 0 draw -1 lose
	public void endGame(int gameResult);
	
}
