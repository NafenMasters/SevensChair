package yue;

import java.util.ArrayList;

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
		/*
		Card[] c=new Card[3];
		for(int i=0;i<3;i++)
		{
			c[i]=new Card();
		}
		c[0].Input(9);
		c[1].Input(5);
		c[2].Input(9);
		
		*/
		
		
		/*Combo com=new Combo();
		com=set.Scores(c);
		System.out.println(com.Level+" "+com.Score+" "+com.Category);
		
		Single s=new Single();
		Combo cc=new Combo();
		cc=s;
		*/
		/*Card[] c2=new Card[7];
		for(int i=0;i<7;i++)
		{
			c2[i]=new Card();
		}
		
		for(int i=0;i<7;i++)
		{
			System.out.println(c2[i].Num);
		}*/
		
		/*Card e=new Card();
		e.Input(8);
		ArrayList<Card> cards=new ArrayList<Card>();
		cards.add(e);
		*/
		
		
		
		
		/*long startTime =System.currentTimeMillis();
		Card[] test=new Card[7];
		Operation set=new Operation();
		
		
		Pile pp=new Pile();
		pp.StartGame();
		
		for(int j=0;j<15;j++)
		{		for(int i=0;i<7;i++)
		{
			test[i]=new Card();
			test[i]=pp.c[i+7*j];
		}
		
		Combo combo=new Combo();
		combo.Output(pp.c,108);
		
		HandCombo hc=new HandCombo();
		hc.GetHandCombo(test, 7);
		hc.sort();
		hc.delete();
		set.OutputCombo(hc);
		combo=hc.BestCombo();
		combo.Output(combo.cards, combo.NumberofCards);
		}
		long endtime=System.currentTimeMillis();
		System.out.println(endtime);
		System.out.println(startTime);
		System.out.println(endtime-startTime);
	*/
		
		
		
		
		
		
	/*	for(int i=0;i<7;i++)
		{
			test[i]=new Card();
		}
		test[0].Input(50);
		test[1].Input(5);
		test[2].Input(46);
		test[3].Input(53);
		test[4].Input(54);
		test[5].Input(54);
		test[6].Input(2);
		
		
		HandCombo hc=new HandCombo();
		hc.GetHandCombo(test, 7);
        hc.sort();
        hc.delete();
		set.OutputCombo(hc);*/
		
		//set.CardSort(test, 7);
		//Combo com=new Combo();
		//com.Output(test, 7);
		
	/*Card[] test2=new Card[7];
		for(int i=0;i<7;i++)
	{
		test2[i]=new Card();
	}*/
		
		/*Combo com=new Combo();
		com=set.Scores(test);
		com.Output(test, 7);
		System.out.println(com.Level+" "+com.Score+" "+com.Category);*/
		
		/*test2[1].Input(2);
		test2[0].Input(50);
		test2[2].Input(46);
		test2[3].Input(42);
		test2[4].Input(38);
		test2[5].Input(34);
		test2[6].Input(30);
		
		com=set.Scores(test2);
		System.out.println(com.Level+" "+com.Score+" "+com.Category);*/
		
		
		
		
		/*Card[] test2=new Card[7];
		for(int i=0;i<7;i++)
	{
		test2[i]=new Card();
	}
		test2[0].Input(20);
		test2[1].Input(18);
		test2[2].Input(40);
		test2[3].Input(52);
		test2[4].Input(51);
		test2[5].Input(49);
		test2[6].Input(39);
		
		ArrayList<Card> c=new ArrayList<Card>();
		for(int i=0;i<7;i++)
		{
			c.add(test2[i]);
		}
		
		Operation set=new Operation();
		System.out.println(set.PointsCount(c));*/
		
		
		
		/*Pile p=new Pile();
		p.StartGame();
		Justas j=new Justas();
		j.Initialize();
		j.GetHandCards(p, 0);
		HandCombo hc=new HandCombo();
		int length;
		length=j.HandCards.size();
		Card[] c=new Card[length];
		for(int i=0;i<length;i++)
		{
			c[i]=j.HandCards.get(i);
		}
		hc.GetHandCombo(c, j.HandCards.size());
		hc.sort();
		hc.delete();
		Operation set=new Operation();
		set.OutputCombo(hc);*/
		
		for(int i=0;i<10000;i++)
		{		Game g=new Game();
		g.JGame();
		}
	}
	
	

	    
}
