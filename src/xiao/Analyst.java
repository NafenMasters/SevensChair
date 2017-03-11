package xiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import chair.Card;
import chair.CardComparator;
import chair.Combo;

public class Analyst {
	/*
	//figure out all combos in the card list from large to small
	public static ArrayList<Combo> quickAnalyze(ArrayList<Card> cards){
		if(cards==null)
			return null;
		if(cards.isEmpty())
			return new ArrayList<Combo>();
		
		Combo maxHomoChain = findMaxHomoChain(cards);
		
	}
	
	public static Combo findMaxHomoBlock(ArrayList<Card> cards){
		
	}
	*/
	public static ArrayList<Card> findTheSuit(ArrayList<Card> cards,int suit){
		
		//return cards;
		Card c;
		ArrayList<Card> a=new ArrayList<Card>();
		for(Iterator<Card> iter = cards.iterator();iter.hasNext();){
			c=iter.next();
			if(c.getSuit()==suit){
				a.add(c);
			}
		}
		return a;
	}
	
	public static Combo findMaxHomoChain(ArrayList<Card> cards){
		Combo chain1 = findMaxChain(findTheSuit(cards,1));
		Combo chain2 = findMaxChain(findTheSuit(cards,2));
		Combo chain3 = findMaxChain(findTheSuit(cards,3));
		Combo chain4 = findMaxChain(findTheSuit(cards,4));
		
		Combo maxHomoChain=chain1;
		int max=chain1.abs();
		
		int s2=chain2.abs();
		int s3=chain3.abs();
		int s4=chain4.abs();
		
		if(s2>max){
			maxHomoChain=chain2;
			max=s2;
		}
		if(s3>max){
			maxHomoChain=chain3;
			max=s3;
		}
		if(s4>max){
			maxHomoChain=chain4;
			//max=s4;
		}
		
		return maxHomoChain;
	}
	
	//simply return the max chain as a combo
	//cards must not contain jokers!!!!
	public static Combo findMaxChain(ArrayList<Card> cards){
		
		if(cards.isEmpty())
			return new Combo();
		
		int j;
		Card c;
		Combo chain=new Combo();
		Combo maxChain=new Combo();
		Collections.sort(cards, new CardComparator());
		
		j=cards.get(0).getNumber();
		chain.add(cards.get(0));
		
		
		for(Iterator<Card> iter = cards.iterator();iter.hasNext();){
			c=iter.next();
			if(c.getNumber()==j+1){
				chain.add(c);
				j+=1;
			}else if(c.getNumber()>=j+2){
				if(chain.size()>=maxChain.size()){
					maxChain=chain;
				}
				chain=new Combo();
				j=c.getNumber();
				chain.add(c);
				
			}
			
		}
		if(chain.get(chain.size()).getNumber()==13 && cards.get(0).getNumber()==1)
		{
			chain.add(cards.get(0));
		}
		
		if(chain.size()>=maxChain.size()){
			maxChain=chain;		
		}
		return maxChain;
		
	}
}
