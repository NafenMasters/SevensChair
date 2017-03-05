import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Combo extends ArrayList<Card> {
	
	//�Ȱ����������ٰ���ɫ����A�������
	public void sort(){
		Collections.sort(this, new CardComparator());
	}
	
	/*
	 * ������    -1:���Ϲ���
	 *        0-6�����ŵ�����˳
	 *        7������
	 *        8��ͬ��2˳
	 *        9��ͬ����
	 *        10������
	 *        11��ͬ��3˳
	 *        12��ͬ��3��
	 *        .....
	 *        21��������
	 *        22��7��
	 *        23��ͬ��7˳
	 *        24��������
	 * ����ֵ=������*1000+ָ�꣨һ��Ϊ��С����KA˳ȡ13��Aȡ14��2ȡ15��
	 * 
	 */
	
	//evaluate the absolute value of this combo. If invalid return 0.
	//TODO: 222222
	public int abs(){
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
				//ͬ������
				type=size()*3+3;
				return type*1000+index(min);
			}else{
				//��ͬ������
				type=size()*3+1;
				return type*1000+index(min);
			}
			
		}else{
			
			//������KA
			if(min==1 && max==13){
				for(int i=size()-2;i>0;i--){
					if(get(i).getNumber()!=14-size()+i){
						//��˳��
						return 0;
					}
				}
				
				if(isSameSuit()){
					//ͬ��˳
					type=size()*3+2;
					return type*1000+15-size();
				}else{
					type=size();
					return type*1000+15-size();
					//��ͨ˳
					
				}
				
				
			}
			
			//����KA
			for(int i=1;i<size();i++){
				if(get(i).getNumber()!=min+i){
					//��˳��
					return 0;
				}
			}
				if(isSameSuit()){
					//ͬ��˳
					type=size()*3+2;
					return type*1000+min;
				}else{
					type=size();
					return type*1000+min;
					//��ͨ˳
					
				}
			
			
		}
	}
	
	//�ж��Ƿ���2����
	public boolean contain2hearts(){
		for(int i=0;i<size();i++)
			if(get(i).getNumber()==2 && get(i).getSuit()==1)
				return true;
		return false;
	}
	
	//�ж��Ƿ�Ϊͬ��
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
	
	//ת��Ϊ�ܱ�ʾ��С��ָ����
	public int index(int num){
		if(num==1)
			return 14;
		if(num==2)
			return 15;
		return num;
	}
}
