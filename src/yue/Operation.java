package yue;

import java.util.ArrayList;
import java.util.Collections;

public class Operation 
{	
	//Generate a random series to shuffle the pack
   public int[] RandSeries()
   {
	   int temp=0;
	  int[] a=new int[108];
	   for (int i=0;i<108;i++)
	   {
		   a[i]=i+1;
	   }
	   
	   for(int m=0;m<108;m++)
	   {int n=108-m;
		   int seed=(int)(Math.random()*n);
	   temp=a[m];
	   a[m]=a[m+seed];
	   a[m+seed]=temp;
	   }
	   
	 /*  for(int i=0;i<108;i++)
	   {
		   System.out.print(a[i]);
		   System.out.print("\n");
	   }
	   */
	   return a;
   }
   
   public void CopyCards(Card c[],Pack p1,Pack p2) //Combine two packs together
   {
	   for(int i=0;i<54;i++)
	   {
		   c[i]=p1.cards[i];
		   c[i+54]=p2.cards[i];
	   }
   }
	
   public int Maximum(Card c[])
   {
	   int max=0;
	   int len;
	   len=c.length;
	   for(int i=0;i<len;i++)
	   {
		   if(c[i].Num>max)
		   {
			   max=c[i].Num;
		   }
	   }
	   
	   return max;
   }
   
   public int Mininum(Card c[])
   {
	   
	   int min=100;
	   int len;
	   len=c.length;
	   for(int i=0;i<len;i++)
	   {
		   if(c[i].Num<min)
		   {
			   min=c[i].Num;
		   }
	   }
	   
	   return min;
   }
   
   //To sort the cards
   public void Sort(Card c[])
   {
	   int len;
	   Card temp=new Card();
	   len=c.length;
	   for(int i=0;i<len;i++)
	   {
		   for(int j=i+1;j<len;j++)
		   {
			   if(c[j].Num<c[i].Num)
			   {
				   temp=c[i];
				   c[i]=c[j];
				   c[j]=temp;
			   }
		   }
	   }
   }
   
   //Special Card sorting method to make A occurs last,then all the hand combo can be attained
   public void CardSort(Card[] handcards,int n)
	{
		int count=0;
		Card temp=new Card();
		for(int i=0;i<n-count;i++)
		{
			if(handcards[i].Num==1)
			{
				temp=handcards[i];
				handcards[i]=handcards[n-count-1];
				handcards[n-count-1]=temp;
				count++;
			}
		}
		Card[] newcards=new Card[n-count];
		for(int i=0;i<n-count;i++)
		{
			newcards[i]=handcards[i];
		}
		Operation set=new Operation();
		set.Sort(newcards);
		for(int i=0;i<n-count;i++)
		{
			handcards[i]=newcards[i];
		}
	}
   
   //To decide the sequence of two combo based on its score
   public boolean ComboCompare(Combo c1, Combo c2)
   {
	   boolean decision=true;
	   if(c1.NumberofCards!=c2.NumberofCards)
	   {
		   decision=false;
	   }
	   else
	   {
		   for(int i=0;i<c1.NumberofCards;i++)
		   {
			   if(  (c1.cards[i].Num!=c2.cards[i].Num)||(c1.cards[i].suits!=c2.cards[i].suits)  )
			   {
				   decision=false;
				   break;
			   }
		   }
	   }
	   return decision;
   }
   
   //To calculate the score of a combo and return a combo format
   //Each combo class has its own calculation function
   //Single: Level:0, Score:0鈥斺�15, 20(small joker), 30(big joker)
   //MixedSeries:Level:1, Score:103鈥斺�127(2 cards, the smallest is "A 2", the biggest is "K A"), 156鈥斺�189(3 cards), 210鈥斺�250(4 cards), 265--310(5 cards), 321--369(6 cards), 378--427(7 cards)
   //(2 cards)Level:2, MixedPair: 1006--1028(2 cards), HomoSeries:1103--1127(2 cards), HomoPair:1206--1230(2 cards), 1240(small joker),1260(big joker)
   //(3 cards)Level:3, MixedPair: 2009--2045(3 cards), HomoSeries:2106--2139, HomoPair:2209--2245
   //(4 cards)Level:4, MixedPair: 3012--3060(4 cards), HomoSeries:3110--3150, HomoPair:3212--3260
   //(5 cards)Level:5, MixedPair: 4015--4075(5 cards), HomoSeries:4115--4160
   //(6 cards)Level:6, MixedPair: 5018--5090(6 cards), HomoSeries:5121--5169
   //(7 cards)Level:7, MixedPair: 6021--6105(7 cards), HomoSeries:6128--6177
   public Combo Scores(Card c[])
   {
	   Combo temp=new Combo();
	   int len;
	   len=c.length;
	   temp.NumberofCards=len;
	   boolean SeriesDecision;
	   boolean PairsDecision;
	   boolean HorMS,HorMP;
	   
	   ////////////////////////
	   if(len==0)
	   {
		   Combo e = new Combo();
		   e.Score=-1000;
		   return e;
	   }
	   ///////////////////////
	   
	   
	   //Deal with the single case
	   if(len==1)
	   {
		   Single s=new Single();
		   s.ScoreCalculation(c);
		   temp=s;
		  
	   }
	   
	   else
	   {
		   //To judge whether it is a series or not.
		   SeriesDecision=temp.SeriesJudgement(c);
		   
		   //Is it a Series?
		   if(SeriesDecision)
		   {
			   Series se=new Series();
			   //Homogeneous or Mixed
			   HorMS=se.HorM(c);
			   
			   if(HorMS)
			   {
				   HomoSeries hs=new HomoSeries();
				   hs.ScoreCalculation(c);
				   temp=hs;
			   }
				   
			   else
			   {
				   MixedSeries ms=new MixedSeries();
				   ms.ScoreCalculation(c);
				   temp=ms;
			   }
			   
		   }
		   
		   else
		   {
			   PairsDecision=temp.PairJudgement(c);
			   
			   //Is it a pair
			   if(PairsDecision)
			   {
				   Pair p=new Pair();
				   //Homogeneous or Mixed
				   HorMP=p.HorMP(c);
				   
				   if(HorMP)
				   {
					   HomoPair hp=new HomoPair();
					   hp.ScoreCalculation(c);
					   temp=hp;
				   }
				   
				   else
				   {
					   MixedPair mp=new MixedPair();
					   mp.ScoreCalculation(c);
					   temp=mp;
				   }
			   
			   }
			   
			   else
			   {
				   temp.Level=-1;
				   temp.Score=-1000;
				   temp.Category="Unset";
			   }
		   }
	   
	   }
	   
	  
	   int HeartTwo;
	   HeartTwo=temp.HeartTwoExamine(c);
	   
	   //When Heart 2 is contained in the combo
	   if(HeartTwo>0)
	   { 
		   int newlen;
		   newlen=len-HeartTwo;
		   Card[] newcard=new Card[newlen];
		   Combo newcombo=new Combo();
		   
		   for(int i=0;i<newlen;i++)
		   {
			   newcard[i]=new Card();
		   }
		   
		  int i=0;
		  int j=0;
		  //copy the combo to newcard[] and delete the Heart 2.
		  while(i<newlen)
		  {
			  if((c[j].Num!=2)||(c[j].suits!="Heart"))
			  {
				  newcard[i]=c[j];
				  i++;
			  }
			  j++;
		  }
		  
		  //If there is only 1 card left, then the combo will be a homogeneous pair.
		  if (newlen==1)
		  {
			  if(newcard[0].Num==1)
			  {
				  newcard[0].Num=14;
			  }
			  if(newcard[0].Num==2)
			  {
				  newcard[0].Num=15;
			  }
			  
			  newcombo.NumberofCards=newlen+HeartTwo;
			  newcombo.Level=newcombo.NumberofCards;
			  newcombo.Score=newcombo.NumberofCards*newcard[0].Num+1000+1000*(newcombo.Level-2)+200;
			  newcombo.Category="HomogeneousPair";
			  
			  if(newcard[0].Num==14)
			  {
				  newcard[0].Num=1;
			  }
			  if(newcard[0].Num==15)
			  {
				  newcard[0].Num=2;
			  }
			  
			  for(int t=0;t<newcombo.NumberofCards;t++)
			  {
				  newcombo.cards[t]=new Card();
				  newcombo.cards[t]=c[t];
			  }
			  
			  if(newcombo.Score>temp.Score)
			  {
				if(newcard[0].Num<18)
				  {
					temp=newcombo;
					}
			  }

		  }
		  
		  //Consider the situation of 2 Heart Two.
		  else if(newlen==0)
		  {
			  newcombo.NumberofCards=newlen+HeartTwo;
			  newcombo.Level=newcombo.NumberofCards;
			  newcombo.Score=newcombo.NumberofCards*15+1000+1000*(newcombo.Level-2)+200;
			  newcombo.Category="HomogeneousPair";
			  for(int t=0;t<newcombo.NumberofCards;t++)
			  {
				  newcombo.cards[t]=new Card();
				  newcombo.cards[t]=c[t];
			  }
			  
			  if(newcombo.Score>temp.Score)
			  {
				if(newcard[0].Num<18)
				  {
					temp=newcombo;
					}
			  }
		  }
		  
		  //When more than 1 cards are left
		  else
		  {
			  boolean PD2;//Pair Decision two
			  //To decide whether the left cards make up a pair
			  PD2=temp.PairJudgement(newcard);
			  if(PD2)
			  {
				  boolean HorMP2;
				  Pair newp=new Pair();
				  HorMP2=newp.HorMP(newcard);
				  
				  if(HorMP2)
				  {
					  if(newcard[0].Num==1)
					  {
						  newcard[0].Num=14;
					  }
					  if(newcard[0].Num==2)
					  {
						  newcard[0].Num=15;
					  }
					  
					  newcombo.NumberofCards=newlen+HeartTwo;
					  newcombo.Level=newcombo.NumberofCards;
					  newcombo.Score=newcombo.NumberofCards*newcard[0].Num+1000+1000*(newcombo.Level-2)+200;
					  newcombo.Category="HomogeneousPair";
					  
					  if(newcard[0].Num==14)
					  {
						  newcard[0].Num=1;
					  }
					  if(newcard[0].Num==15)
					  {
						  newcard[0].Num=2;
					  }
					  
					  for(int t=0;t<newcombo.NumberofCards;t++)
					  {
						  newcombo.cards[t]=new Card();
						  newcombo.cards[t]=c[t];
					  }
					  
					  if(newcombo.Score>temp.Score)
					  {
						  if(newcard[0].Num<18)
						  {
							  temp=newcombo;
					      }
					  }
		  
				  }
				  
			  }
			  
		  }
		     
	   }
	   
//	   System.out.println(temp.Score);
   
	   return temp;
   }
   
   public int RealLength(Card[] c)
   {
	   int length=0;
	   
	   while(c[length].Num>0)
	   {
		   length++;
	   }
	   
	   return length;
   }
   
   public void OutputCombo(ArrayList<Combo> c)
   {
	   int length;
	   length=c.size();
	   Combo temp=new Combo();

	   for(int i=0;i<length;i++)
	   {
		   temp.Output(c.get(i).cards,c.get(i).NumberofCards);
	   }
   }
   
  
   public int PointsCount(ArrayList<Card> c)
   {
	   int Points=0;
	   int length;
	   length=c.size();
	   
	   for(int i=0;i<length;i++)
	   {
		   if(c.get(i).Num==5)
		   {
			   Points+=5;
		   }
		   else if( (c.get(i).Num==10) || (c.get(i).Num==13) )
		   {
			   Points+=10;
		   }
		   else if(c.get(i).Num==20)
		   {
			   Points+=50;
		   }
		   else if (c.get(i).Num==30)
		   {
			   Points+=100;
		   }
	   }
	   
	   return Points;
   }
  
   //If c1.score>c2,score, return true
   public boolean ComboBattle(Combo c1, Combo c2)
   {
	   boolean decision;
	   decision=false;
	   if(c1.Score>c2.Score)
	   {
		   decision=true;
	   }
	   return decision;
   }
   
   public void ArraylistCardSort(ArrayList<Card> c)
   {
	   Collections.sort(c,new CardComparator());
   }
   
   
}
