package chair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class main {
	
	/*
	 * Very important variables for running the game!
	 */
	
	private static Deck d;
	
	private static Player p1,p2;
	
	//Points earned by player 1,2 and round points
	private static int points1=0, points2=0, rpoints=0;
	//Joker points earned by p1 and p2
	private static int jokers1=0,jokers2=0, rjokers=0;
	
	//Record how many rounds have been played
	private static int roundNumber=1;
	
	//Two combos recording plays
	private static	Combo Combo1=new Combo();
	private static	Combo Combo2=new Combo();
	
	//Record handcards of each player to prevent cheating
	private static ArrayList<Card> p1Cards, p2Cards;
	
	//Whose turn to play, = 1 or 2
	private static int turn;
	
	private static boolean gameOver; //Usually false
	
	private static int roundWinner; // = 0 or 1 or 2
	
	private static int winner =0;
	public static void main(String[] args){
		System.out.println("Welcome to Seven's Chair!");

		/*
		 * Initializations
		 */
		
		//A new shuffled 108 deck
		d=new Deck();
		
		//Two AI players to compete
		p1=new Dummy("Ð¡Æß");
		p2=new Dummy("´óÍ·");
		
		//Tell 2 players to get ready
		p1.startGame(1);
		p2.startGame(2);
		
		//Deal 7 cards for each player
		p1Cards = d.deal(p1, 7);
		p2Cards = d.deal(p2, 7);
		
		/*
		 * The Game Loop!
		 */
		gameOver=false;
		turn=1;
		roundNumber=1;
		
		//
		while(!gameOver){
			
			
			
			roundWinner=0;
			
			while(roundWinner==0){
				
				if(turn==1){
					
					/*
					 * Player1's turn
					 */
		            
					//Player1 plays against combo2
					Combo1=p1.play(Combo2);
					
					if(!deleteCombo(p1Cards, Combo1)){
						winner=2;
						gameOver=true;
						System.out.println(p1.getName()+" cheated! Game Over!");
					}
					
					
					turn=2;
					
					//Add points to round points
					rpoints+=Combo1.points();
					rjokers+=Combo1.jokerPoints();
					
					if(Combo1.size()==0){
						//player2 wins this round
						roundWinner=2;
						}else if(Combo1.abs()<=Combo2.abs()){
							winner=2;
							gameOver=true;
							System.out.println(p1.getName()+" cheated! Game Over!");
						}
				}
				else{
					
					/*
					 * Player2's turn
					 */
					
					Combo2=p2.play(Combo1);
					
					if(!deleteCombo(p2Cards, Combo2)){
						winner=1;
						gameOver=true;
						System.out.println(p2.getName()+" cheated! Game Over!");
					}
					
					turn =1;
					
					rpoints+=Combo2.points();
					rjokers+=Combo2.jokerPoints();
					
					if(Combo2.size()==0){
						//player1 wins this round
						roundWinner=1;
					}else if(Combo2.abs()<=Combo1.abs()){
						winner=1;
						gameOver=true;
						System.out.println(p2.getName()+" cheated! Game Over!");
					}
					
				}
				
				
				
				//Print out what happened just now
				printChair();
				
			}
			
			if(gameOver){
				break;
			}
			
			//Someone passed?
			if(Combo1.size()==0 && Combo2.size()==0){
				if(turn==1){
					System.out.println(p2.getName()+" cheated! Game over!");
					winner=1;
				}else{
					System.out.println(p1.getName()+" cheated! Game over!");
					winner=2;
				}
				break;
			}
			
			
			roundNumber+=1;
			
			if(roundWinner==1){
				
				if(d.isEmpty() && p1Cards.isEmpty()){
					winner=1;
					gameOver=true;
					points1=500-points2;
					System.out.println(p1.getName()+" wins the last round and the game!");
					break;
				}
				
				//Player1 win all points in this round
				points1+=rpoints;
				rpoints=0;
				jokers1+=rjokers;
				rjokers=0;
				
				//TODO: Check if end of game
				
				
				//Deal cards, player1 first
				p1Cards.addAll(d.deal(p1, 7-p1.getLeftNum()));
				p2Cards.addAll(d.deal(p2, 7-p2.getLeftNum()));
				
				
				//reset played combos
				Combo1=new Combo();
				Combo2=new Combo();
			}else{
				
				if(d.isEmpty() && p2Cards.isEmpty()){
					winner=2;
					gameOver=true;
					points2=500-points1;
					System.out.println(p2.getName()+" wins the last round and the game!");
					break;
				}
				
				//Player2 win all points in this round
				points2+=rpoints;
				rpoints=0;
				jokers2+=rjokers;
				rjokers=0;
				
				//Deal cards, player2 first
				p1Cards.addAll(d.deal(p1, 7-p1.getLeftNum()));
				p2Cards.addAll(d.deal(p2, 7-p2.getLeftNum()));
				
				//reset played combos
				Combo1=new Combo();
				Combo2=new Combo();			
			}
			
			
			
			switch(checkWinning()){
			
			case 0:
				//Do nothing
				break;
			case 1:
				System.out.println(p1.getName()+" achieved adequete points!!!!!");
				break;
			case 2:
				System.out.println(p2.getName()+" achieved adequete points!!!!!");
				break;
			case 3:
				System.out.println("Well played! Nobody wins!");
				break;
				
			}
			 
		}
		
		
		//System.console().readLine();
	}
	
	
	//Check if anyone has already won the game
	//return 0,1,2,3(draw)
	private static int checkWinning(){
		if(points1>250){
			gameOver=true;
			return 1;
		}else if(points2>250){
			gameOver=true;
			return 2;
		}else if(points1==250 && jokers1<150){
			gameOver=true;
			return 1;
		}else if(points2==250 && jokers2<150){
			gameOver=true;
			return 2;
		}else if(points1==250 && points2==250 && jokers1==150){
			gameOver=true;
			return 3;
		}else{
			return 0;
		}
	}
	
	private static void printChair(){

		System.out.println("*********************************************");
		System.out.print("Round #"+roundNumber+"! "+"Next Turn:"+turn+" ");
		System.out.println("Cards left:"+d.getLeftNum());
		System.out.print(p1.getName()+" Points:"+points1+"|");
		System.out.print(p2.getName()+" Points:"+points2+"|");
		System.out.println("Round Points:"+ rpoints);
		printList(p1.getHandCards());
		printList(Combo1);
		printList(Combo2);
		printList(p2.getHandCards());
		
	}
	
	//print list of cards
	public static void printList(List<Card> cards){
		if(cards.isEmpty()){
			System.out.println("-----");
			return;
		}
		 for (Iterator<Card> iter = cards.iterator(); iter.hasNext();) {
				  String str = iter.next().toString();
				  System.out.print(str+" ");

				 }
			  System.out.println();
	}
	
	//delete a combo from a list of cards. if invalid return false
	private static boolean deleteCombo(List<Card> l,Combo c){
		
		if(c.isEmpty()){
			return true;
		}
		if(l.isEmpty()){
			return false;
		}
		int n,s;
		Card a,b;
		boolean f=false;
		
		for (Iterator<Card> i1 = c.iterator(); i1.hasNext();) {
			b=i1.next();
			n=b.getNumber();
			s=b.getSuit();
			for (Iterator<Card> i2 = l.iterator(); i2.hasNext();) {
				a=i2.next();
				if(a.getNumber()==n && a.getSuit()==s){
					i2.remove();
					f=true;
					break;
				}
			}
			
			if(!f){
				return false;
			}
			
		}
		
		return true;
	}
}
