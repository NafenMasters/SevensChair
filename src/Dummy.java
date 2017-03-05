import java.util.ArrayList;

//A very stupid AI who only play one card each turn
//    - Can be used to check the game rules
//    - One could develop strong AIs based on this one
public class Dummy implements Player {

	private ArrayList<Card> handCards;
	
	private int points;
	
	@Override
	public void startGame(int order) {
		points =0;
		handCards=new ArrayList();
		
		// Literally Nothing Else
		
	}

	@Override
	public void startRound(ArrayList<Card> newCards) {
		handCards.addAll(newCards);
		
		// Literally Nothing Else
		
	}

	@Override
	public Combo play(Combo enemyCombo) {
		Combo c = new Combo();
		c.add(handCards.get(getLeftNum()-1));
		if(c.abs()>enemyCombo.abs()){
			handCards.remove(getLeftNum()-1);
			return c;
		}
		return null;
		
	}

	@Override
	public void endGame(int gameResult) {
		// Literally Nothing
		
	}

	@Override
	public ArrayList<Card> getHandCards() {

		return handCards;
	}

	@Override
	public int getPoints() {

		return points;
	}

	@Override
	public int getLeftNum() {
		return handCards.size();
	}

}
