package xiao;

import java.util.ArrayList;

import chair.Card;
import chair.Combo;
import chair.Deck;

public class GameState {
	
	public static Deck d;
	
	//Points earned by player 1,2 and round points
	public static int points1, points2, rpoints;
	//Joker points earned by p1 and p2
	public static int jokers1,jokers2, rjokers;
	
	//Record how many rounds have been played
	public static int roundNumber;
	
	//Two combos recording plays
	public static	Combo Combo1;
	public static	Combo Combo2;
	
	//Whose turn to play, = 1 or 2
	public static int turn;
	
	public GameState(){
		points1=0;
		points2=0;
		rpoints=0;
		jokers1=0;
		jokers2=0;
		rjokers=0;
		roundNumber=1;
		Combo1=new Combo();
		Combo2=new Combo();
		turn=1;
	}

}
