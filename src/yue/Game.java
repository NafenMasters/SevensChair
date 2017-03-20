package yue;

import java.util.ArrayList;

public class Game {

	 public void JGame()
	   {
		   int TakenCards;
		   TakenCards=0;
		   //To count the number of card of player j[i]
		   int[] jhc=new int[2];
		   Justas[] j=new Justas[2];
		   for(int i=0;i<2;i++)
		   {
			   j[i]=new Justas();
			   j[i].Initialize();
		   }
		   String a="Daddy";
		   String b="Mummy";
		   
		   j[0].Name(a);
		   j[1].Name(b);
		   
		  /* Operation s=new Operation();
		   System.out.println(s.PointsCount(j[1].HandCards));
		   System.out.println(j[1].Points);
		   */
		   
		   Pile p=new Pile();
		   p.StartGame();
	
		   
		   for(int i=0;i<7;i++)
		   {
			   for(int k=0;k<2;k++)
			   {
				   j[k].HandCards.add(p.c[k+2*i]);
			   }
		   }
		   TakenCards+=14;
		   
		   int seed;
		   seed=(int)(Math.random()*2);
		   int Result=0;
		   //0 means no result, 1 means j[1] win,2 means j[2] win
		   int first=seed;
		   
		   while(Result==0)
		   {
			   
			   jhc[first]=j[first].GetHandCards(p, TakenCards);
			   TakenCards+=jhc[first];
			   jhc[1-first]=j[1-first].GetHandCards(p, TakenCards);
			   TakenCards+=jhc[1-first];
			   
					   
			   Combo enemycombo=new Combo();
			   enemycombo.NumberofCards=0;
			   enemycombo.Score=-1000;
			   enemycombo.Level=-1;
			   enemycombo.Category="Unset";
			   Combo playcombo=new Combo();
			   boolean Round=true;
			   Operation s=new Operation();
			   for (int i=0;i<2;i++)
			   {
				   s.ArraylistCardSort(j[i].HandCards);
			   }
			   
			   ArrayList<Card> OutCards = new ArrayList<Card>();
			   
			   System.out.println("-------------------------------------");
			   System.out.print(j[first].name);
			   playcombo.Output2(j[first].HandCards);
			   System.out.print("\n");
			   System.out.print(j[1-first].name);
			   playcombo.Output2(j[1-first].HandCards);
			   System.out.print("\n\n");
			   System.out.print("Round begins:\n\n");
			   
			   while(Round)
			   {
				   if(j[first].HandCards.size()==0)
				   {
					   first=1-first;
					   break;
				   }
				   
				   playcombo=j[first].play(enemycombo);
				 
				   first=1-first;
				   enemycombo=playcombo;
				   if(playcombo.Score<0)
				   {
					   Round=false;
					   break;
				   }
				   System.out.print(j[1-first].name);
				   playcombo.Output(playcombo.cards, playcombo.NumberofCards);
				   System.out.print("\n");
				   int length;
				   length=playcombo.NumberofCards;
				   for(int u=0;u<length;u++)
				   {
					   OutCards.add(playcombo.cards[u]);
				   } 
			   }
			   
			   Operation set=new Operation();
			   int PointsofthisRound=0;
			   PointsofthisRound=set.PointsCount(OutCards);
			   j[first].Points+=PointsofthisRound;
			   System.out.println(j[first].name+"'s Points : "+j[first].Points);
			   
			   if(j[first].Points>=250)
			   {
				   Result=first+1;
				   break;
			   }
			   
			   if(TakenCards==108&&((j[first].HandCards.size()==0)||(j[1-first].HandCards.size()==0)))
			   {
				   Result=first+1;
				   break;
			   }
			   
		    
		   }
		   
		   System.out.print("\n-----------------------"+"\n\n");
		   if(Result==1)
		   {
			   System.out.println("Daddy wins! Fantastic Daddy");
		   }
		   else 
		   {
			   System.out.println("Mummy wins!\nMummy beats Daddy!\nCool Mummy!");
		   }
		   
	   }
	 
	 
	 public void FGame()
	 {
		 int TakenCards;
		   TakenCards=0;
		   //To count the number of card of player j[i]
		   int[] fhc=new int[2];
		   Felipe f[]=new Felipe[2];
		   for(int i=0;i<2;i++)
		   {
			   f[i]=new Felipe();
		   }
		   String a="Daddy";
		   String b="Mummy";
		   
		   f[0].GetName(a);
		   f[1].GetName(b);
		   
		  /* Operation s=new Operation();
		   System.out.println(s.PointsCount(j[1].HandCards));
		   System.out.println(j[1].Points);
		   */
		   
		   Pile p=new Pile();
		   p.StartGame();
	
		   
		   for(int i=0;i<7;i++)
		   {
			   for(int k=0;k<2;k++)
			   {
				   f[k].GetOwnCards(p.c[k+2*i]);
			   }
		   }
		   TakenCards+=14;
		   
		   int seed;
		   seed=(int)(Math.random()*2);
		   int Result=0;
		   //0 means no result, 1 means j[1] win,2 means j[2] win
		   int first=seed;
		   
		   while(Result==0)
		   {
			   fhc[first]=f[first].GetHandCards(p, TakenCards);
			   TakenCards+=fhc[first];
			   fhc[1-first]=f[1-first].GetHandCards(p, TakenCards);
			   TakenCards+=fhc[1-first];
			  // System.out.println(fhc[first]+"  "+fhc[1-first]);
			   
			   
					   
			   Combo enemycombo=new Combo();
			   enemycombo.NumberofCards=0;
			   enemycombo.Score=-1000;
			   enemycombo.Level=-1;
			   enemycombo.Category="Unset";
			   Combo playcombo=new Combo();
			   boolean Round=true;
			   Operation s=new Operation();
			   
			   ArrayList<Card> c=new ArrayList<Card>();
			   ArrayList<Card> d=new ArrayList<Card>();
			   c=f[first].HandCard();
			   d=f[1-first].HandCard();
			   s.ArraylistCardSort(c);
			   s.ArraylistCardSort(d);
			   
			   
			  /* for (int i=0;i<2;i++)
			   {
				   s.ArraylistCardSort(f[first].HandCard());
			   }
			   */
			   
			   ArrayList<Card> OutCards = new ArrayList<Card>();
			   
			   System.out.println("-------------------------------------");
			   System.out.print(f[first].PrintName());
			   playcombo.Output2(f[first].HandCard());
			   System.out.print("\n");
			   System.out.print(f[1-first].PrintName());
			   playcombo.Output2(f[1-first].HandCard());
			   System.out.print("\n\n");
			   System.out.print("Round begins:\n\n");
			   
			   while(Round)
			   {
				   if(f[first].HandCard().size()==0)
				   {
					   first=1-first;
					   break;
				   }
				   
				   playcombo=f[first].play(enemycombo);
				 
				   first=1-first;
				   enemycombo=playcombo;
				   if(playcombo.Score<0)
				   {
					   Round=false;
					   break;
				   }
				   System.out.print(f[1-first].PrintName());
				   playcombo.Output(playcombo.cards, playcombo.NumberofCards);
				   System.out.print("\n");
				   int length;
				   length=playcombo.NumberofCards;
				   for(int u=0;u<length;u++)
				   {
					   OutCards.add(playcombo.cards[u]);
				   } 
			   }
			   
			   Operation set=new Operation();
			   int PointsofthisRound=0;
			   PointsofthisRound=set.PointsCount(OutCards);
			   f[first].AddPoints(PointsofthisRound, 0);
			   System.out.println(f[first].PrintName()+"'s Points : "+f[first].GetOwnPoints());
			   
			   if(f[first].GetOwnPoints()>=250)
			   {
				   Result=first+1;
				   break;
			   }
			   
			   if(TakenCards==108&&((f[first].HandCard().size()==0)||(f[1-first].HandCard().size()==0)))
			   {
				   Result=first+1;
				   break;
			   }
			   
		    
		   }
		   
		   System.out.print("\n-----------------------"+"\n\n");
		   if(Result==1)
		   {
			   System.out.println("Daddy wins! Fantastic Daddy");
		   }
		   else 
		   {
			   System.out.println("Mummy wins!\nMummy beats Daddy!\nCool Mummy!");
		   }
		   
	 }
	
}
