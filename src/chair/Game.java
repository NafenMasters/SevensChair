package chair;

public class Game{
	Player p1,p2;
	
	int winner;
	int roundNumber;
	int p1Points;
	int p2Points;
	int p1Jokers;
	int p2Jokers;
	boolean isCheating;
	public Game(Player v1, Player v2){
		p1=v1;
		p2=v2;
	}
	public void setResult(int w,int r,int pt1, int pt2,int p1j, int p2j,boolean c){
		winner=w;
		roundNumber=r;
		p1Points=pt1;
		p2Points=pt2;
		p1Jokers=p1j;
		p2Jokers=p2j;
		isCheating=c;
	}
	
	public void print(){
		
		switch(winner){
		case 1:
			System.out.println(p1.getName()+" has defeated "+p2.getName()+" with "+p1Points+"("+p1Jokers+")"+":"+p2Points+"("+p2Jokers+")"+" at round #"+roundNumber+" !");

			break;
		case 2:
			System.out.println(p1.getName()+" has been defeated by "+p2.getName()+" with "+p1Points+"("+p1Jokers+")"+":"+p2Points+"("+p2Jokers+")"+" at round #"+roundNumber+" !");

			break;
		case 3:
			System.out.println(p1.getName()+" and "+p2.getName()+" got a draw in this game at round #"+roundNumber+" !");

			break;
			
		}
		if(isCheating==true)
		System.out.println("His rival lost because of cheating!");
	}
}
