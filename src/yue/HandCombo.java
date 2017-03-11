package yue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandCombo extends ArrayList<Combo>
{
	
	
	
	public void GetHandCombo(Card[] handcards, int n)
	{
		Operation o=new Operation();
		o.CardSort(handcards, n);
		
		if(n==1)
		{
			Single s=new Single();
			s.ScoreCalculation(handcards);
			this.add(s);
		}
		
		else
		{   
			//Recycle this function till there is only one card left.
			GetHandCombo(handcards, n-1);
			
			int NoC;
			//NumberofCombos
			
			NoC=this.size();
			//To get how many combos has already been attained.
			
			//To test whether the new card generalize a new combo with those existing ones.
			for(int i=0;i<NoC;i++)
			{	
				Combo temp=new Combo();
				int NoCiOC;
				//Number of Cards in Original Combo
				
				temp=this.get(i);
				//temp is equal to the ith combo in the list.
				
				
				NoCiOC=this.get(i).NumberofCards;
				//to get the length of cards in this temp combo
				
				Card[] testcards=new Card[NoCiOC+1];
				
				for(int j=0;j<NoCiOC;j++)
				{
					//Copy the cards in this combo
					testcards[j]=temp.cards[j];
				}
				
				//Add this card into the existing combo
				testcards[NoCiOC]=handcards[n-1];
				
				//System.out.println(NoCiOC);
				//temp.Output(testcards);
				//temp.Output(temp.cards);
				
				
				Operation set=new Operation();
				Combo test=new Combo();
				test=set.Scores(testcards);
				//When a new combo occurs, plug it into the existing list of combo.
				if(test.Level>-0.5)
				{
					this.add(test);
					
					
				}
				
				
				
			}
			
			//Take the single card into consideration
			Single s=new Single();
			Card[] singlecard=new Card[1];
			singlecard[0]=new Card();
			singlecard[0]=handcards[n-1];
			s.ScoreCalculation(singlecard);
			this.add(s);
			
		}
				
	}
	
	
	
	public void sort()
	{
		Collections.sort(this,new ComboComparator());
	}
	
	public void delete()
	{
		int t=this.size();
		Combo temp=new Combo();
		Operation set=new Operation();
		int count=0;
		for(int i=0;i<t-1-count;i++)
		{
			temp=this.get(i);
			if(set.ComboCompare(temp, this.get(i+1)))
			{
				this.remove(i+1);
				i--;
				count++;
			}
		}
	}

	public Combo BestCombo()
	{
		Combo temp=new Combo();
		Collections.sort(this,new ComboComparator());
		int length;
		length=this.size();
		temp=this.get(length-1);
		return temp;
	}
	
}
