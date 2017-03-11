package xiao;

import java.util.ArrayList;

import chair.Card;
import chair.Combo;
import chair.Player;

public class Noob extends chair.Dummy {


	public Noob(String name){
		myName = name;
	}
	
	public Noob(){
		myName = "XIAO";
	}
	
	@Override
	public Combo play(Combo enemyCombo) {
		
		return new Combo();
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
