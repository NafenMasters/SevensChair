package chair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Combo extends ArrayList<Card> {
	
	private int abs=-1;
	
	//先按点数排序再按花色排序，A在最左边
	public void sort(){
		Collections.sort(this, new CardComparator());
	}
	
	/*
	 * 类型数    -1:不合规则
	 *        0-6：单张到七连顺
	 *        7：对子
	 *        8：同花2顺
	 *        9：同花对
	 *        10：三张
	 *        11：同花3顺
	 *        12：同花3张
	 *        .....
	 *        21：不可能
	 *        22：7张
	 *        23：同花7顺
	 *        24：不可能
	 * 绝对值=类型数*1000+指标（一般为最小数，KA顺取13，A取14，2取15）
	 * 
	 */
	
	public int abs(){
		if(abs==-1){
			abs=calculate_abs();
		}
		return abs;
	}
	
	//evaluate the absolute value of this combo. If invalid return 0.
	//TODO: 222222
	public int calculate_abs(){
		if(size()==0)
			return 0;
		if(size()==1)
			return index(get(0).getNumber());
		
		if(contain2hearts()){
			Combo c=new Combo();
			int j=0;
			for(int i=0;i<size();i++)
				if(get(i).getNumber()!=2 || get(i).getSuit()!=1)
					{
					c.add(get(i));
					j+=1;
					}
			int a=c.abs();
			int typ=a/1000;
			int ind=a%1000;
			if(a<50){
				return a+9000;
			}
			if(typ==9&&ind<50){
				return a+3000;
			}
		}
		
		
		
		sort();
		int min = get(0).getNumber();
		int max = get(size()-1).getNumber();
		int type;
		
		
		
		if(min==99 && max==100)
			return 0;
		
		if(max==min){
			if(isSameSuit()){
				//同花多张
				type=size()*3+3;
				return type*1000+index(min);
			}else{
				//不同花多张
				type=size()*3+1;
				return type*1000+index(min);
			}
			
		}else{
			
			//若含有KA
			if(min==1 && max==13){
				for(int i=size()-2;i>0;i--){
					if(get(i).getNumber()!=14-size()+i){
						//非顺子
						return 0;
					}
				}
				
				if(isSameSuit()){
					//同花顺
					type=size()*3+2;
					return type*1000+15-size();
				}else{
					type=size();
					return type*1000+15-size();
					//普通顺
					
				}
				
				
			}
			
			//不含KA
			for(int i=1;i<size();i++){
				if(get(i).getNumber()!=min+i){
					//非顺子
					return 0;
				}
			}
				if(isSameSuit()){
					//同花顺
					type=size()*3+2;
					return type*1000+min;
				}else{
					type=size();
					return type*1000+min;
					//普通顺
					
				}
			
			
		}
	}
	
	//判断是否含有2红桃
	public boolean contain2hearts(){
		for(int i=0;i<size();i++)
			if(get(i).getNumber()==2 && get(i).getSuit()==1)
				return true;
		return false;
	}
	
	//判断是否为同花
	public boolean isSameSuit(){
		int s =get(0).getSuit();
		for(int i =1;i<size();i++){
			if(s!=get(i).getSuit())
				return false;
		}
		return true;
	}
	
	//calculate points contained in this combo
	public int points(){
		int ps=0;
		for(int i=0;i<size();i++)
			switch(get(i).getNumber()){
			case 5:
				ps+=5;
				break;
			case 10:
				ps+=10;
				break;
			case 13:
				ps+=10;
				break;
			case 99:
				ps+=50;
				break;
			case 100:
				ps+=100;
				break;
					
			}
		return ps;
	}
	
	//calculate joker points contained in this combo
	public int jokerPoints(){
			int jps=0;
			for(int i=0;i<size();i++)
				switch(get(i).getNumber()){
				
				case 99:
					jps+=50;
					break;
				case 100:
					jps+=100;
					break;
						
				}
			return jps;
		}
		
	//转化为能表示大小的指标数
	private int index(int num){
		if(num==1)
			return 14;
		if(num==2)
			return 15;
		return num;
	}
}
