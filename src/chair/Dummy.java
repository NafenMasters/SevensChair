package chair;
import java.util.ArrayList;

//A very stupid AI who only play one card each turn
//    - Can be used to check the game rules
//    - One could develop strong AIs based on this one
public class Dummy implements Player {

	protected ArrayList<Card> handCards;
	
	protected int points;
	
	protected String myName;
	
	public Dummy(String name){
		myName = name;
	}
	
	public Dummy(){
		myName = "Dummy";
	}
	
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
		return new Combo();
		
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

	@Override
	public String getName() {
		return myName;
	}

}
