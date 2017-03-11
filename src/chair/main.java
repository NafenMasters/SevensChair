package chair;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import yue.Martin;


public class main {
	
	public static void main(String[] args){
		System.out.println("Welcome to Seven's Chair!");
		Player p1,p2;
		
		p1=new Martin("MartinÒ»ºÅ");
		p2=new Martin("Martin¶þºÅ");
		
		Chair.match(p1, p2).print();
		
		
		
	}
	
	
	
	
	
	
}
