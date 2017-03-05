
public class Card {

	private int number;
	// 1-13: A-K 15:red joker 14:black joker 0:unspecified
	
	private int suit;
	// 1: heats  2:spades  3:diamonds  4:clubs
	
	public Card(int number, int suit) {
		this.number = number;
		this.suit = suit;
	}

	public int getNumber() {
		return number;
	}

	public int getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		String s = null;
		switch(suit) {
		
		case 1: s="♥";
		break;
		case 2: s="♠";
		break;
		case 3: s="♦";
		break;
		case 4: s="♣";
		break;
		}
		switch(number) {
			case 11: s += "J";
			break;
			case 12: s += "Q";
			break;
			case 13: s += "K";
			break;
			case 1: s += "A";
			break;
			case 14: s="jk";
			break;
			case 15: s="JK";
			break;
			
			default: s += Integer.toString(number);
		}
		return s;
	}

	
}


