package yue;

public class Operation 
{	
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
   
   public void Output(Card c[],int n)
   {
	   System.out.print("\n");
	   for(int i=0;i<n;i++)
	   {
		   System.out.println(c[i].Num+"  "+c[i].suits+"\n");
	   }
   System.out.print("\n");
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
   
   public Combo Scores(Card c[])
   {
	   Combo temp=new Combo();
	   int len;
	   len=c.length;
	   temp.NumberofCards=len;
	   boolean SeriesDecision;
	   boolean PairsDecision;
	   boolean HorMS,HorMP;
	   
	   //Deal the single case
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
   
}
