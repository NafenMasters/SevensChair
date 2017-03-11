package chair;
public class Card {

	private int number;
	// 1-13: A-K 100:red joker 99:black joker 0:unspecified
	
	private int suit;
	// 1: heats  2:spades  3:diamonds  4:clubs 5:joker
	
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
	public boolean equals(Object c){
		if(c.getClass()!=this.getClass()){
			return false;
		}
		Card cc = (Card) c;
		if(cc.getNumber()==number && cc.getSuit()==suit)
			return true;
		else	
			return false;
		
	}
	
	@Override
	public String toString() {
		String s = null;
		switch(suit) {
		
		case 1: s="h";
		break;
		case 2: s="s";
		break;
		case 3: s="d";
		break;
		case 4: s="c";
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
			case 99: s="jk";
			break;
			case 100: s="JK";
			break;
			
			default: s += Integer.toString(number);
		}
		return s;
	}

	
}


