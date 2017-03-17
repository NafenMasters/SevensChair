package xiao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import chair.Card;
import chair.CardComparator;
import chair.Combo;

public class Analyst {
	
	//figure out all combos in the card list from large to small
	public static ArrayList<Combo> quickAnalyze(ArrayList<Card> cards){
		if(cards==null)
			return null;
		if(cards.isEmpty())
			return new ArrayList<Combo>();
		
		Combo maxHomoChain = findMaxHomoChain(cards);
		Combo maxHomoBlock = findMaxHomoBlock(cards);
		Combo maxBlock = findMaxBlock(cards);
		Combo maxChain = findMaxChain(cards);
		
		Combo maxCombo = maxHomoBlock;
		if(maxHomoChain.abs()>maxCombo.abs())
			maxCombo=maxHomoChain;
		if(maxBlock.abs()>maxCombo.abs())
			maxCombo=maxBlock;
		if(maxChain.abs()>maxCombo.abs())
			maxCombo=maxChain;
		
		ArrayList<Combo> result = new ArrayList<Combo>();
		result.add(maxCombo);
		
		ArrayList<Card> _cards=(ArrayList<Card>) cards.clone();
		
		_cards.removeAll(maxCombo);
		result.addAll(quickAnalyze(_cards));
		
		return result;
	}
	
	public static Combo findMaxHomoBlock(ArrayList<Card> cards){
		
		ArrayList<Card> _cards=(ArrayList<Card>) cards.clone();
		
		int num_h2=0;
		Card c;
		
		for(Iterator<Card> iter = cards.iterator();iter.hasNext();){
			c=iter.next();
			if(c.getNumber()==2 && c.getSuit()==1){
				num_h2+=1;
				_cards.remove(c);
			}	
		}
		
		ArrayList<Card> h2 =new ArrayList<Card>();
		for(int i=0;i<num_h2;i++){
			h2.add(new Card(2,1));
		}
		
		Combo block1 = findMaxBlock(findTheSuit(_cards,1));
		block1.addAll(h2);
		Combo block2 = findMaxBlock(findTheSuit(_cards,2));
		block2.addAll(h2);
		Combo block3 = findMaxBlock(findTheSuit(_cards,3));
		block3.addAll(h2);
		Combo block4 = findMaxBlock(findTheSuit(_cards,4));
		block4.addAll(h2);
		Combo block5 = findMaxBlock(findTheSuit(_cards,5));
		
		Combo maxHomoBlock=block1;
		int max=block1.abs();
		
		int s2=block2.abs();
		int s3=block3.abs();
		int s4=block4.abs();
		int s5=block5.abs();
		
		if(s2>max){
			maxHomoBlock=block2;
			max=s2;
		}
		if(s3>max){
			maxHomoBlock=block3;
			max=s3;
		}
		if(s4>max){
			maxHomoBlock=block4;
			max=s4;
		}
		if(s5>max){
			maxHomoBlock=block5;
			max=s5;
		}
		
		return maxHomoBlock;
		
	}
	
	public static Combo findMaxBlock(ArrayList<Card> cards){
		if(cards.isEmpty())
			return new Combo();
		Collections.sort(cards, new CardComparator());
		Card c;
		Combo block,maxBlock;
		block=new Combo();
		maxBlock=new Combo();
		int j=cards.get(0).getNumber();
		for(Iterator<Card> iter = cards.iterator();iter.hasNext();){
			c=iter.next();
			if(c.getNumber()==j){
				block.add(c);
			}else if(c.getNumber()>=j+1){
				if(block.size()>=maxBlock.size()){
					maxBlock=block;
				}
				block=new Combo();
				j=c.getNumber();
				block.add(c);
				
			}	
		}
		if(block.size()>=maxBlock.size())
			maxBlock=block;
		return maxBlock;
	}
	
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
	//ignore jokers
	public static Combo findMaxChain(ArrayList<Card> cards){
		
		ArrayList<Card> _cards =new ArrayList<Card>();
		Card c;
		for(Iterator<Card> iter = cards.iterator();iter.hasNext();){
			c=iter.next();
			if(c.getSuit()<5){
				_cards.add(c);
			}
		}
		
		if(_cards.isEmpty())
			return new Combo();
		
		int j;
		Combo chain=new Combo();
		Combo maxChain=new Combo();
		Collections.sort(_cards, new CardComparator());
		
		j=_cards.get(0).getNumber();
		chain.add(_cards.get(0));
		
		
		for(Iterator<Card> iter = _cards.iterator();iter.hasNext();){
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
		if(chain.get(chain.size()-1).getNumber()==13 && _cards.get(0).getNumber()==1)
		{
			chain.add(_cards.get(0));
		}
		
		if(chain.size()>=maxChain.size()){
			maxChain=chain;		
		}
		return maxChain;
		
	}
}
