package yue;

public class main {

	public static void main (String[] arcs)
	
	{
		
	/*	
		Pile pp=new Pile();
		pp.StartGame();
		
		
		Operation set=new Operation();
		set.Output(pp.c, 108);
		
		Card[] c=new Card[1];
		c[0]=new Card();
		c[0].Input(4);
		Single s=new Single();
		s.ScoreCalculation(c, 1);
		System.out.println("\n"+s.Score);
		MixedSeries m=new MixedSeries();

		Card[] cc=new Card[3];
		for(int i=0;i<3;i++)
		{
			cc[i]=new Card();
			cc[i].Input(i*2+10);
		}
		
		System.out.println("\n"+set.Maximum(cc)+"\n"+set.Mininum(cc));
		
		m.ScoreCalculation(cc, 3);
		System.out.println("\n"+m.Score);
		
		
		Card[] card=new Card[50];
		for(int i=0;i<50;i++)
		{
			card[i]=new Card();
			card[i].Input((int)(Math.random()*54));
		}
		
		set.Sort(card);
		set.Output(card, 50);
	*/	
	
		
		/*Card[] c=new Card[7];
		for(int i=0;i<7;i++)
		{
			c[i]=new Card();
			c[i].Input(3+4*i);
		}
		
		c[0].Input(19);
		c[1].Input(21);
		
		Single s=new Single();
		s.ScoreCalculation(c);
		System.out.println(s.Score);
		System.out.println(s.Level);
		
		Card[] cc=new Card[1];
		cc[0]=new Card();
		cc[0].Input(54);
		Single st= new Single();
		st.ScoreCalculation(cc);
		System.out.println(st.Score);
		System.out.println(st.Level);
		
		boolean t;
		t=s.SeriesJudgement(c);
		System.out.println(t);
		
		Series ss=new Series();
		System.out.println(ss.HorM(c));
		
		MixedSeries ms=new MixedSeries();
		ms.ScoreCalculation(c);
		System.out.println(ms.Score);
		System.out.println(ms.cards[4].Num);
		
		System.out.println(ss.AExamine(c));
		System.out.println(ss.TwoExamine(c));
		
		Card[] ca=new Card[1];
		ca[0]=new Card();
		ca[0].Input(54);
		Operation sss=new Operation();
		Combo com=new Combo();
		com=sss.Scores(ca);
		System.out.println(com.Score+"  "+com.Level+"  "+com.NumberofCards);
		sss.Output(com.cards, 1);
		*/
		
		Card[] c=new Card[3];
		for(int i=0;i<3;i++)
		{
			c[i]=new Card();
		}
		c[0].Input(10);
		c[1].Input(5);
		c[2].Input(10);
		
		
		Operation set=new Operation();
		Combo com=new Combo();
		com=set.Scores(c);
		System.out.println(com.Level+" "+com.Score+" "+com.Category);
		
		/*Card[] c2=new Card[7];
		for(int i=0;i<7;i++)
		{
			c2[i]=new Card();
		}
		
		for(int i=0;i<7;i++)
		{
			System.out.println(c2[i].Num);
		}*/
		
		
	}
	

	    
}
