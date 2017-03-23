package chair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xiao.Noob;
import xiao.Psycho;
import yue.Ben;
import yue.Martin;


public class main {
	
	
	public static void main(String[] args){
		System.out.println("Welcome to Seven's Chair!");
		Player p1,p2;
		GameResult r;
		int win1 =0;
		for(int i=0;i<200;i++){
			p1=new Ben("Ben");
			p2=new Psycho("Psycho");
			if(Math.random()>0.5){
				r=Chair.match(p2, p1,2);
				if(r.winner==2){
					win1++;
				}
			}else{
				r=Chair.match(p1, p2,2);
				if(r.winner==1){
					win1++;
				}
			}
			r.print();
			
		}
		System.out.print(win1);
		System.out.println(":"+(200-win1));
		
	}
	
	
}
