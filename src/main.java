import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class main {
	public static void main(String[] args) {
		System.out.println("Welcome to Seven's Chair!");

		Deck d=new Deck();
		Player p1,p2;
		p1=new Dummy();
		p2=new Dummy();
		p1.startGame(1);
		p2.startGame(2);
		
		d.deal(p1, 7);
		d.deal(p2, 7);
		printRound(1,p1,p2,new Combo(),new Combo(),50,d);
		
	}
	
	private static void printRound(int roundNumber,Player p1, Player p2, Combo c1, Combo c2, int roundPoints ,Deck d){

		System.out.println("*********************************************");
		System.out.print("Round "+roundNumber+"! ");
		System.out.println("Cards left:"+d.getLeftNum());
		System.out.print("P1 Points:"+p1.getPoints()+"|");
		System.out.print("P2 Points:"+p2.getPoints()+"|");
		System.out.println("Round Points:"+ roundPoints);
		printList(p1.getHandCards());
		printList(c1);
		printList(c2);
		printList(p2.getHandCards());

	}
	
	//print list of cards
	public static void printList(List cards){
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
}
