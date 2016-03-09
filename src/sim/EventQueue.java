package sim;

import java.util.ArrayList;

import carwash.CarWashEvent;
import carwash.CarWashState;

public class EventQueue {
	public ArrayList<CarWashEvent> carWashEventList;
	SimState state;
	int counter = 0;
	
	public EventQueue(SimState state, CarWashEvent startEvent){
		this.state = state;
		this.carWashEventList = new ArrayList<>();
		carWashEventList.add(startEvent);
	}
	
	public void loop(){
		carWashEventList = state.addInSequence(carWashEventList);
		sort();
		carWashEventList.get(0).execute();
		shift();
	}
	
	private void addInSequence(){
		double time = ((CarWashState) state).getPreviousEventTime()+((CarWashState) state).getExpoRandom();
		CarWashEvent e = new CarWashEvent(time, counter, (CarWashState) state);
		carWashEventList.add(e);
		counter++;
	}
	
	private void sort(){
		carWashEventList.sort((e1, e2) -> Double.compare(e1.getTime(), e2.getTime()));
	}
	
	private void shift(){
		if(carWashEventList.get(0).getRemove()){
			carWashEventList.remove(0);
		}
	}
}
