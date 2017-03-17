package chair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import xiao.Noob;
import yue.Martin;


public class main {
	
	public static void main(String[] args){
		System.out.println("Welcome to Seven's Chair!");
		Player p1,p2;
		Game r;
		int win1 =0;
		for(int i=0;i<1;i++){
			p1=new Noob("Noob");
			p2=new Martin("Martin");
			r=Chair.match(p1, p2,1);
			r.print();
			if(r.winner==1){
				win1++;
			}
		}
		System.out.print(win1);
		System.out.println(":"+(1000-win1));
		
	}
	
	
}
