package view;

import java.util.*;

import state.CarWashState;

public class SimView implements Observer{
	CarWashView c;
	CarWashState t;
	int counter = 0;
	
	public SimView(CarWashState state){
		c = new CarWashView(state);
		t = state;
		 t.addObserver(this);
		c.printHeader();
	}
	
	
	public void update(Observable arg0, Object arg1) {
		if(t.getTime() == t.getMaxTime()){
			c.printCurrentState();
			c.printFooter();
		}
		else{
			c.printCurrentState();
		}
	}
}
