import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class main {
	public static void main(String[] args) {
		System.out.println("Welcome to Seven's Chair!");

		Deck d=new Deck();
		Player p1,p2;
		int points1=0,points2=0,rpoints=0;
		p1=new Dummy();
		p2=new Dummy();
		p1.startGame(1);
		p2.startGame(2);
		d.deal(p1, 7);
		d.deal(p2, 7);
		Combo c=new Combo();
		Combo cc=new Combo();
		
		boolean gameOver=false;
		int turn=1;
		int sn=1;
		while(!gameOver){
			while(true){
				if(turn==1){
					
					cc=c;
					c=p1.play(c);
					turn=2;
					rpoints+=c.points();
					
					System.out.println("*********************************************");
					System.out.print("Round "+sn+"! "+"Next Turn:"+turn+" ");
					System.out.println("Cards left:"+d.getLeftNum());
					System.out.print("P1 Points:"+points1+"|");
					System.out.print("P2 Points:"+points2+"|");
					System.out.println("Round Points:"+ rpoints);
					printList(p1.getHandCards());
					printList(c);
					printList(cc);
					printList(p2.getHandCards());
					
					
				}
				else{
					
					cc=c;
					c=p2.play(c);
					turn =1;
					rpoints+=c.points();
					
					System.out.println("*********************************************");
					System.out.print("Round "+sn+"! "+"Next Turn:"+turn+" ");
					System.out.println("Cards left:"+d.getLeftNum());
					System.out.print("P1 Points:"+points1+"|");
					System.out.print("P2 Points:"+points2+"|");
					System.out.println("Round Points:"+ rpoints);
					printList(p1.getHandCards());
					printList(cc);
					printList(c);
					printList(p2.getHandCards());
					
				}
				
				if(c.size()==0)
					break;
			}
			sn+=1;
			if(turn==1){
				points1+=rpoints;
				rpoints=0;
				d.deal(p1, 7-p1.getLeftNum());
				d.deal(p2, 7-p2.getLeftNum());
				c=new Combo();
			}else{
				points2+=rpoints;
				rpoints=0;
				d.deal(p2, 7-p2.getLeftNum());
				d.deal(p1, 7-p1.getLeftNum());
				c=new Combo();
			}
			
			//TODO
			if(points1>250){
				System.out.println("P1 Win!");
				gameOver=true;
			}else if(points2>250){
				System.out.println("P2 Win!");
				gameOver=true;
			}
		}
		
		
		//System.console().readLine();
	}
	
	private static void printRound(int sectionNumber,int turn,int pt1,int pt2,Player p1, Player p2, Combo c1, Combo c2, int roundPoints ,Deck d){

		System.out.println("*********************************************");
		System.out.print("Round "+sectionNumber+"! "+"Turn:"+turn+" ");
		System.out.println("Cards left:"+d.getLeftNum());
		System.out.print("P1 Points:"+pt1+"|");
		System.out.print("P2 Points:"+pt2+"|");
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
