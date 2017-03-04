
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

	public String strNumber() {
		switch(number) {
			case 11: return "J";
			case 12: return "Q";
			case 13: return "K";
			case 1: return "A";
			default: return Integer.toString(number);
		}
	}

	public char strSuit() {
		switch(suit) {
			case 0: return (char)'\u2660';
			case 1: return (char)'\u2666';
			case 2: return (char)'\u2663';
			case 3: return (char)'\u2764';
			default: return (char) 'a';
		}
	}
}


