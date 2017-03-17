package chair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Chair {
	private static Deck d;
		
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
	
	private static boolean isCheating=false;

	//mode: 1 printchair 2 no printing
	public static Game match(Player p1, Player p2, int mode){
		/*
		 * Initializations
		 */
		
		//A new shuffled 108 deck
		d=new Deck();
		
		isCheating=false;
				
		winner =0;
				
		points1=0;
		points2=0;
		rpoints=0;
		
		jokers1=0;
		jokers2=0;
		rjokers=0;
				
		Combo1=new Combo();
		Combo2=new Combo();
		//Tell 2 players to get ready
		p1.startGame(1);
		p2.startGame(2);
		
		//Deal 7 cards for each player
		p1Cards = d.deal(p1, 7);
		p2Cards = d.deal(p2, 7);
		
		/*
		 * The Game Loop!
		 */
		gameOver=false; //////>>>>>>>!!!!!!!!!!!!!!!
		turn=1;
		roundNumber=0;
		
		//
		while(!gameOver){
			
			
			
			roundWinner=0;
			roundNumber+=1;
			
			while(roundWinner==0){
				
				if(turn==1){
					
					/*
					 * Player1's turn
					 */
		            
					//Player1 plays against combo2
					Combo1=p1.play(Combo2);
					
					if(!deleteList(p1Cards,Combo1)){
						roundWinner=2;
						winner=2;
						gameOver=true;
						isCheating =true;
						
						//System.out.println(p1.getName()+" cheated! Game Over!");
					}
					
					
					turn=2;
					
					//Add points to round points
					rpoints+=Combo1.points();
					rjokers+=Combo1.jokerPoints();
					
					if(Combo1.size()==0){
						//player2 wins this round
						roundWinner=2;
						}else if(Combo1.abs()<=Combo2.abs()){
							roundWinner=2;
							winner=2;
							gameOver=true;
							isCheating =true;
							//System.out.println(p1.getName()+" cheated! Game Over!");
							
						}
				}
				else{
					
					/*
					 * Player2's turn
					 */
					
					Combo2=p2.play(Combo1);
					
					if(!deleteList(p2Cards,Combo2)){
						winner=1;
						roundWinner=1;
						gameOver=true;
						isCheating =true;
					}
					
					turn =1;
					
					rpoints+=Combo2.points();
					rjokers+=Combo2.jokerPoints();
					
					if(Combo2.size()==0){
						//player1 wins this round
						roundWinner=1;
					}else if(Combo2.abs()<=Combo1.abs()){
						roundWinner=1;
						winner=1;
						gameOver=true;
						isCheating =true;
					}
					
				}
				
				
				
				//Print out what happened just now
				if(mode==1)
				printChair(p1,p2);
				
			
			
			//Someone passed?
			if(Combo1.size()==0 && Combo2.size()==0){
				if(turn==1){
					
					isCheating =true;
					roundWinner=1;
					winner=1;
					gameOver=true;
					
				}else{
					//System.out.println(p1.getName()+" cheated! Game over!");
					isCheating =true;
					winner=2;
					roundWinner=2;
					gameOver=true;
					
				}
			
				
			}
			}
			
			if(gameOver){
				break;
			}
			
			if(roundWinner==1){
				
				if(d.isEmpty() && p1Cards.isEmpty()){
					gameOver=true;
					points1=500-points2; // p1 gets all remaining points
				}else{
				
					//Player1 win all points in this round
					points1+=rpoints;
					rpoints=0;
					jokers1+=rjokers;
					rjokers=0;
				
				
					//Deal cards, player1 first
					p1Cards.addAll(d.deal(p1, 7-p1.getLeftNum()));
					p2Cards.addAll(d.deal(p2, 7-p2.getLeftNum()));
					
				
					//reset played combos
					Combo1=new Combo();
					Combo2=new Combo();
				}
			}else{
				
				if(d.isEmpty() && p2Cards.isEmpty()){

					points2=500-points1;
					
				}else{
				
					//Player2 win all points in this round
					points2+=rpoints;
					rpoints=0;
					jokers2+=rjokers;
					rjokers=0;
				
				
				
					//Deal cards, player2 first
					p2Cards.addAll(d.deal(p2, 7-p2.getLeftNum()));
					p1Cards.addAll(d.deal(p1, 7-p1.getLeftNum()));
				
					//reset played combos
					Combo1=new Combo();
					Combo2=new Combo();	
				}
			}
			
			
			
			switch(checkWinning()){
			
			case 0:
				
				break;
			case 1:
				winner=1;
				gameOver=true;
				break;
			case 2:
				winner=2;
				gameOver=true;
				break;
			case 3:
				winner=3;
				gameOver=true;
				break;
				
			}
			 
		}
		
		Game g = new Game(p1,p2);
		g.setResult(winner,roundNumber,points1,points2,jokers1,jokers2,isCheating);
		return g;
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
		
	private static void printChair(Player p1,Player p2){

		String[] s={" ","!"};
		System.out.println("*********************************************");
		System.out.print("Round #"+roundNumber+"! ");
		System.out.println("Cards left:"+d.getLeftNum());
		System.out.print(p1.getName()+" Points:"+points1+"|");
		System.out.print(p2.getName()+" Points:"+points2+"|");
		System.out.println("Round Points:"+ rpoints);
		printList(p1Cards);
		System.out.print(s[turn-1]);
		printList(Combo1);
		//System.out.println(Combo1.abs());
		//System.out.println(Combo2.abs());
		System.out.print(s[2-turn]);
		printList(Combo2);
		printList(p2Cards);
		
	}
	
	//print list of cards
	public static void printList(List<Card> cards){
		if(cards.isEmpty()){
			System.out.println("-----");
			return;
		}
		Collections.sort(cards,new CardComparator());
		 for (Iterator<Card> iter = cards.iterator(); iter.hasNext();) {
				  String str = iter.next().toString();
				  System.out.print(str+" ");

				 }
			  System.out.println();
	}
	
	public static boolean deleteList(ArrayList<Card> a,List<Card> cards){
		if(cards==null||cards.isEmpty() ){
			return true;
		}
		if(a==null)
			return false;
		for(Iterator<Card> iter = cards.iterator();iter.hasNext();){
			if(!a.remove(iter.next())){
				return false;
			}
		}
		return true;
	}
}
